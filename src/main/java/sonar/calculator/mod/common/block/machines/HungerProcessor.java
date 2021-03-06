package sonar.calculator.mod.common.block.machines;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sonar.calculator.mod.Calculator;
import sonar.calculator.mod.common.tileentity.machines.TileEntityHungerProcessor;
import sonar.calculator.mod.network.CalculatorGui;
import sonar.core.common.block.SonarMachineBlock;
import sonar.core.common.block.SonarMaterials;
import sonar.core.helpers.FontHelper;
import sonar.core.utils.BlockInteraction;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HungerProcessor extends SonarMachineBlock {
	@SideOnly(Side.CLIENT)
	private IIcon iconFront, front, front2, slot1, slot2;

	public HungerProcessor() {
		super(SonarMaterials.machine);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.front = iconRegister.registerIcon("Calculator:hungerprocessor_front2");
		this.front2 = iconRegister.registerIcon("Calculator:hungerprocessor_front1");
		this.slot1 = iconRegister.registerIcon("Calculator:hungerprocessor_slot2");
		this.slot2 = iconRegister.registerIcon("Calculator:hungerprocessor_slot1");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
		TileEntityHungerProcessor t = (TileEntityHungerProcessor) access.getTileEntity(x, y, z);
		int metadata = access.getBlockMetadata(x, y, z);
		if (side != metadata) {
			return t.getBlockTexture(side, metadata) ? this.slot1 : this.slot2;
		}
		if (side == metadata) {
			return t.getBlockTexture(side, metadata) ? this.front : this.front2;
		}

		return this.slot1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side == metadata ? this.slot2 : side == 0 ? this.slot1 : side == 1 ? this.slot1 : (metadata == 0) && (side == 3) ? this.front : this.slot1;
	}

	@Override
	public boolean operateBlock(World world, int x, int y, int z, EntityPlayer player, BlockInteraction interact) {
		if ((player.getHeldItem() != null) && (player.getHeldItem().getItem() == Calculator.wrench)) {
			return false;
		}
		if (player != null && !world.isRemote) {
			player.openGui(Calculator.instance, CalculatorGui.HungerProcessor, world, x, y, z);

		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityHungerProcessor();
	}

	@Override
	public void addSpecialToolTip(ItemStack stack, EntityPlayer player, List list) {

		int hunger = stack.getTagCompound().getInteger("Food");
		if (hunger != 0) {
			list.add(FontHelper.translate("points.hunger") + ": " + hunger);
		}
	}

}

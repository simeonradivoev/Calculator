package sonar.calculator.mod.common.block.machines;

import java.util.Random;

import net.minecraft.block.BlockFire;

public class EternalFire extends BlockFire {
	public EternalFire() {
		this.setBlockBounds(0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		this.setTickRandomly(false);
	}

	public int quantityDropped(Random p_149745_1_) {
		return 1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 3;
	}

	public boolean isCollidable() {
		return true;
	}
}

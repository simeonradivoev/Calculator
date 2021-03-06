package sonar.calculator.mod.common.item.modules;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import sonar.calculator.mod.CalculatorConfig;

public class TerrainModule extends BaseTerrainModule {

	public TerrainModule() {
		super.replacable = new Block[]{ Blocks.grass, Blocks.dirt, Blocks.stone};
		capacity = CalculatorConfig.getInteger("Terrain Module");
		maxReceive = CalculatorConfig.getInteger("Terrain Module");
		maxExtract = CalculatorConfig.getInteger("Terrain Module");
		maxTransfer = CalculatorConfig.getInteger("Terrain Module");
	}
}

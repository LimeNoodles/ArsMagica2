package am2.client.blocks.colorizers;

import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;

public class MonoColorizer implements IBlockColor{
	
	private int color;

	public MonoColorizer(int color) {
		this.color = color;
	}
	
	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		return color;
	}
}

package am2.api.compendium;

import java.util.HashMap;

import am2.common.LogHelper;

import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class BlockRenderWorld implements IBlockAccess {
	
	HashMap<BlockPos, IForgeBlockState> states = new HashMap<>();

	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		return null;
	}

	@Override
	public int getCombinedLight(BlockPos pos, int lightValue) {
		return 15;
	}

	@Override
	public IForgeBlockState getBlockState(BlockPos pos) {
		IForgeBlockState state = states.get(pos);
		return state == null ? Blocks.AIR.getDefaultState() : state;
	}

	@Override
	public boolean isAirBlock(BlockPos pos) {
		return getBlockState(pos) == Blocks.AIR.getDefaultState();
	}

	@Override
	public Biome getBiome(BlockPos pos) {
		return Biomes.OCEAN;
	}
	
	public void setBlockState(BlockPos pos, IForgeBlockState state) {
		if (state == null)
			state = Blocks.AIR.getDefaultState();
		if (pos == null)
			return;
		states.put(pos, state);
	}
	
	@Override
	public int getStrongPower(BlockPos pos, Direction direction) {
		return 0;
	}

	@Override
	public WorldType getWorldType() {
		return WorldType.DEFAULT;
	}
	
	public void clear() {
		this.states.clear();
	}

	@Override
	public boolean isSideSolid(BlockPos pos, Direction side, boolean _default) {
//		return false;
		try {
			return this.getBlockState(pos).getBlockState().isSolidSide(this, pos, side);
		} catch (Throwable t) {
			LogHelper.debug("Error check with isSideSolid.", t);;
			return false;
		}
	}

}

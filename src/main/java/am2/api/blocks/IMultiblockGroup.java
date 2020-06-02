package am2.api.blocks;

import java.util.ArrayList;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.common.extensions.IForgeBlockState;

public interface IMultiblockGroup {
	boolean matches(World world, BlockPos pos);
	void addBlock(BlockPos pos);
	int getMinX();
	int getMinY();
	int getMinZ();
	int getMaxX();
	int getMaxY();
	int getMaxZ();
	ImmutableList<BlockPos> getPositions();
	ImmutableList<IForgeBlockState> getStates();
	void addState(IForgeBlockState state);
	ArrayList<IForgeBlockState> getState(BlockPos pos);
	
}

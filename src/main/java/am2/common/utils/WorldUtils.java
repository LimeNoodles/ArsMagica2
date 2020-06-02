package am2.common.utils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class WorldUtils {
	
	public static Block getBlockAt (World world, BlockPos pos) {
		return getBlockStateAt(world, pos).getBlock();
	}
	
	public static IForgeBlockState getBlockStateAt (World world, BlockPos pos) {
		return world.getBlockState(pos);
	}
	
	public static boolean hasBlockUnder (World world, Block block, BlockPos checkPos) {
		boolean flag = false;
		BlockPos checkStart = checkPos.down();
		for (int x = -1; x < 2 || flag; x++) {
			for (int y = -1; y < 1 || flag; y++) {
				for (int z = -1; z < 2 || flag; z++) {
					BlockPos currentPos = new BlockPos(checkStart.getX() + x, checkStart.getY() + y, checkStart.getZ() + z);
					if (getBlockAt(world, currentPos).equals(block)) {
						flag = true;
					}
				}
			}
		}
		return flag;
	}
	
	public static int getBlockMeta(IForgeBlockState state) {
		return state.getBlockState().getMetaFromState(state);
	}
	
	public static void freeze(BlockPos pos, World world) {
		Block block = WorldUtils.getBlockAt(world, pos);
		if (!world.isAirBlock(pos.up())) return;
		if (block.equals(Blocks.WATER) || block.equals(Blocks.WATER))
			world.setBlockState(pos, Blocks.ICE.getDefaultState(), 3);
		else if ((block.equals(Blocks.LAVA) || block.equals(Blocks.LAVA)) && block.getMetaFromState(world.getBlockState(pos)) == 0) {
			world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState(), 3);
		} else if (block.equals(Blocks.LAVA) || block.equals(Blocks.LAVA)) {
			world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState(), 3);
		}
	}
	
	public static void runSided(Side side, Runnable runnable) {
		if (side == Side.CLIENT)
			Minecraft.getInstance().deferTask(runnable);
		else if (side == Side.SERVER)
			FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(runnable);
	}
}

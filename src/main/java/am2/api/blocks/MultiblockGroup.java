package am2.api.blocks;

import java.util.ArrayList;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class MultiblockGroup implements IMultiblockGroup{
	
	protected ArrayList<BlockPos> positions;
	protected ArrayList<IForgeBlockState> states;
	protected boolean ignoreState;
	protected String name;
	
	public MultiblockGroup(String name, ArrayList<IForgeBlockState> arrayList, boolean ignoreState) {
		positions = new ArrayList<BlockPos>();
		this.states = arrayList;
		this.ignoreState = ignoreState;
		this.name = name;
	}
	
	@Override
	public void addBlock(BlockPos position) {
		positions.add(position);
	}
	
	@Override
	public void addState (IForgeBlockState state) {
		states.add(state);
	}
	
	@Override
	public boolean matches (World world, BlockPos startCheckPos) {
		boolean flag = true;
		for (BlockPos pos : positions) {
			if (ignoreState) {
				boolean subFlag = false;
				for (IForgeBlockState state : states) {
					subFlag = world.getBlockState(startCheckPos.add(pos)).getBlock().equals(state.getBlockState());
					if (subFlag)
						break;
				}
				flag = subFlag;
			}
			else {
				boolean subFlag = false;
				for (IForgeBlockState state : states) {
					subFlag = world.getBlockState(startCheckPos.add(pos)).equals(state);
					//System.out.println(this.name + " " + pos + " " + subFlag);
					if (subFlag)
						break;
				}
				flag = subFlag;
				
			}
			if (!flag) {
				break;
			}
		}
		
		return flag;
	}
	
	@Override
	public int getMinX () {
		int min = Integer.MAX_VALUE;
		for (BlockPos pos : positions) {
			if (pos.getX() < min)
				min = pos.getX();
		}
		return min;
	}
	
	@Override
	public int getMinY () {
		int min = Integer.MAX_VALUE;
		for (BlockPos pos : positions) {
			if (pos.getY() < min)
				min = pos.getY();
		}
		return min;
	}
	
	@Override
	public int getMinZ () {
		int min = Integer.MAX_VALUE;
		for (BlockPos pos : positions) {
			if (pos.getZ() < min)
				min = pos.getZ();
		}
		return min;
	}
	
	@Override
	public int getMaxX () {
		int max = Integer.MIN_VALUE;
		for (BlockPos pos : positions) {
			if (pos.getX() + 1 > max)
				max = pos.getX() + 1;
		}
		return max;
	}
	
	@Override
	public int getMaxY () {
		int max = Integer.MIN_VALUE;
		for (BlockPos pos : positions) {
			if (pos.getY() + 1 > max)
				max = pos.getY() + 1;
		}
		return max;
	}
	
	@Override
	public int getMaxZ () {
		int max = Integer.MIN_VALUE;
		for (BlockPos pos : positions) {
			if (pos.getZ() + 1 > max)
				max = pos.getZ() + 1;
		}
		return max;
	}
	
	/**
	 * 
	 * @param mode 1 orientation, 2 invert
	 * @return
	 */
	public MultiblockGroup rotate (int mode) {
		MultiblockGroup group = new MultiblockGroup(name, states, ignoreState);
		ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
		
		for (BlockPos pos : positions) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (mode % 2 == 1) {
				int saveX = z;
				int saveZ = x;
				x = saveX;
				z = saveZ;
			}
			if (mode % 4 >= 2) {
				x = -x;
				z = -z;
			}
			
			positions.add(new BlockPos(x, y, z));
		}
		group.positions = positions;
		return group;
	}
	
	@Override
	public ImmutableList<BlockPos> getPositions() {
		return ImmutableList.copyOf(positions);
	}
	
	@Override
	public ImmutableList<IForgeBlockState> getStates() {
		return ImmutableList.copyOf(states);
	}

	@Override
	public ArrayList<IForgeBlockState> getState(BlockPos pos) {
		return states;
	}
}

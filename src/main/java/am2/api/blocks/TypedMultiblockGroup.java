package am2.api.blocks;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.common.extensions.IForgeBlockState;

public class TypedMultiblockGroup extends MultiblockGroup{
	
	protected ArrayList<HashMap<Integer, IForgeBlockState >> states;
	protected HashMap<BlockPos, Integer> groups = new HashMap<>();
	
	private static ArrayList<IForgeBlockState> createStateList(ArrayList<HashMap<Integer, IForgeBlockState>> arrayList) {
		ArrayList<IForgeBlockState> stateMap = new ArrayList<>();
		for (HashMap<Integer, IForgeBlockState> map : arrayList) {
			stateMap.addAll(map.values());
		}
		return stateMap;
	}
	
	public TypedMultiblockGroup(String name, ArrayList<HashMap<Integer, IForgeBlockState>> arrayList, boolean ignoreState) {
		super(name, createStateList(arrayList), ignoreState);
		this.states = arrayList;
	}
	
	public void addBlock(BlockPos position, int group) {
		positions.add(position);
		groups.put(position, group);
	}
	
	@Override
	@Deprecated
	public void addBlock(BlockPos position) {
		addBlock(position, 0);
	}
	
	public int getGroup(BlockPos pos) {
		return groups.get(pos);
	}
	
	@Override
	public ArrayList<IForgeBlockState> getState(BlockPos pos) {
		ArrayList<IForgeBlockState> state = new ArrayList<>();
		for (HashMap<Integer, IForgeBlockState> map : states) {
			state.add(map.get(getGroup(pos)));
		}
		return state;
	}
	
	@Override
	public boolean matches(World world, BlockPos startCheckPos) {
		for (HashMap<Integer, IForgeBlockState> map : states) {
			boolean subFlag = false;
			for (BlockPos pos : positions) {
				IForgeBlockState checkState = world.getBlockState(startCheckPos.add(pos));
				IForgeBlockState state = map.get(getGroup(pos));
				if (ignoreState) {
					subFlag = checkState.getBlockState().equals(state.getBlockState());
				}
				else {
					subFlag = checkState.getBlockState() == state.getBlockState() && checkState.getBlockState().getMetaFromState(checkState) == state.getBlockState().getMetaFromState(state);
				}
				if (!subFlag)
					break;
			}
			if (subFlag)
				return true;
		}
		return false;
	}
}

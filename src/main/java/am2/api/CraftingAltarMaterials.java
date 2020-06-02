package am2.api;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;

import am2.common.defs.BlockDefs;
import am2.common.utils.KeyValuePair;

import net.minecraft.block.Blocks;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class CraftingAltarMaterials {
	
	private static final HashMap<IForgeBlockState, Integer> caps = new HashMap<>();
	private static final HashMap<KeyValuePair<IForgeBlockState, IForgeBlockState>, Integer> main = new HashMap<>();
	
	static {
		addCapsMaterial(Blocks.GLASS.getDefaultState(), 1);
		addCapsMaterial(Blocks.COAL_BLOCK.getDefaultState(), 2);
		addCapsMaterial(Blocks.REDSTONE_BLOCK.getDefaultState(), 3);
		addCapsMaterial(Blocks.IRON_BLOCK.getDefaultState(), 4);
		addCapsMaterial(Blocks.LAPIS_BLOCK.getDefaultState(), 5);
		addCapsMaterial(Blocks.GOLD_BLOCK.getDefaultState(), 6);
		addCapsMaterial(Blocks.DIAMOND_BLOCK.getDefaultState(), 7);
		addCapsMaterial(Blocks.EMERALD_BLOCK.getDefaultState(), 8);
		addCapsMaterial(BlockDefs.blocks.getDefaultState(), 9);
		addCapsMaterial(BlockDefs.blocks.getDefaultState(), 10);
		
		addMainMaterial(Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.ACACIA_PLANKS.getDefaultState(), Blocks.ACACIA_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.BIRCH_PLANKS.getDefaultState(), Blocks.BIRCH_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.SPRUCE_PLANKS.getDefaultState(), Blocks.SPRUCE_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.JUNGLE_PLANKS.getDefaultState(), Blocks.JUNGLE_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.DARK_OAK_PLANKS.getDefaultState(), Blocks.DARK_OAK_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICK_STAIRS.getDefaultState(), 3);
		addMainMaterial(Blocks.QUARTZ_BLOCK.getDefaultState(), Blocks.QUARTZ_STAIRS.getDefaultState(), 3);
		addMainMaterial(Blocks.STONE_BRICKS.getDefaultState(), Blocks.STONE_BRICK_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.SANDSTONE.getDefaultState(), Blocks.SANDSTONE_STAIRS.getDefaultState(), 1);
		addMainMaterial(Blocks.PURPUR_BLOCK.getDefaultState(), Blocks.PURPUR_STAIRS.getDefaultState(), 4);
		addMainMaterial(Blocks.BRICKS.getDefaultState(), Blocks.BRICK_STAIRS.getDefaultState(), 2);
		addMainMaterial(Blocks.RED_SANDSTONE.getDefaultState(), Blocks.RED_SANDSTONE_STAIRS.getDefaultState(), 2);
		addMainMaterial(BlockDefs.witchwoodPlanks.getDefaultState(), BlockDefs.witchwoodStairs.getDefaultState(), 3);
	}
	
	public static void addCapsMaterial (IForgeBlockState state, int value) {
		caps.put(state, Integer.valueOf(value));
	}
	
	public static void addMainMaterial (IForgeBlockState state, IForgeBlockState stairs, int value) {
		main.put(new KeyValuePair<>(state, stairs), Integer.valueOf(value));
	}
	
	public static ImmutableMap<IForgeBlockState, Integer> getCapsMap() {
		return ImmutableMap.copyOf(caps);
	}
	
	public static ImmutableMap<KeyValuePair<IForgeBlockState, IForgeBlockState>, Integer> getMainMap() {
		return ImmutableMap.copyOf(main);
	}
	
	public static ImmutableMap<IForgeBlockState, Integer> getSimpleMainMap() {
		ImmutableMap.Builder<IForgeBlockState, Integer> builder = ImmutableMap.builder();
		for (Entry<KeyValuePair<IForgeBlockState, IForgeBlockState>, Integer> entry : getMainMap().entrySet()) {
			builder.put(entry.getKey().key, entry.getValue());
		}
		return builder.build();
	}
}
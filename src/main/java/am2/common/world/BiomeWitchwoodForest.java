package am2.common.world;

import java.util.Random;

import am2.ArsMagica2;
import am2.common.entity.EntityDryad;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class BiomeWitchwoodForest extends Biome{

	public static final Biome instance = new BiomeWitchwoodForest(new BiomeProperties("WitchwoodForest"));
	private static final WitchwoodTreeHuge hugeTree = new WitchwoodTreeHuge(true);
	private static final WitchwoodTreeSmall smallTree = new WitchwoodTreeSmall(true);
	private static int biomeId;

	public BiomeWitchwoodForest(BiomeProperties par1){
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(WolfEntity.class, 5, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityDryad.class, 5, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.theBiomeDecorator.flowersPerChunk = 10;
		biomeId = ArsMagica2.config.getWitchwoodForestID();
	}

	@Override
	public int getWaterColorMultiplier(){
		return 0x0a2a72;
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0xdbe6e5;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0xdbe6e5;
	}

	@Override
	public int getSkyColorByTemp(float par1){
		return 0x6699ff;
	}
	
	
	
	@Override
	public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
	{
	  return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? hugeTree : smallTree);
	}

	public static int getBiomeId()
	{
		return biomeId;
	}

	public static int getNextFreeBiomeId() {
		for (int i = 0; i < 256; i++) {
			if (Biome.getBiome(i) != null) {
				if (i == 255) throw new IllegalArgumentException("No more biome ids are avaliable");
				continue;
			}
			return i;
		}
		return -1;
	}
}

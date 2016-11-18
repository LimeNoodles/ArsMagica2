package am2.experimental;

import am2.api.affinity.Affinity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CombinedAffinities {
	
	private static final ResourceLocation ARCANE_LOC = new ResourceLocation("arsmagica2", "arcane");
	private static final ResourceLocation WATER_LOC = new ResourceLocation("arsmagica2", "water");
	private static final ResourceLocation FIRE_LOC = new ResourceLocation("arsmagica2", "fire");
	private static final ResourceLocation EARTH_LOC = new ResourceLocation("arsmagica2", "earth");
	private static final ResourceLocation AIR_LOC = new ResourceLocation("arsmagica2", "air");
	private static final ResourceLocation LIGHTNING_LOC = new ResourceLocation("arsmagica2", "lightning");
	private static final ResourceLocation ICE_LOC = new ResourceLocation("arsmagica2", "ice");
	private static final ResourceLocation NATURE_LOC = new ResourceLocation("arsmagica2", "nature");
	private static final ResourceLocation LIFE_LOC = new ResourceLocation("arsmagica2", "life");
	private static final ResourceLocation ENDER_LOC = new ResourceLocation("arsmagica2", "ender");
	
	public static final Affinity MAGMA = new Affinity("magma", 0x990000, EARTH_LOC, FIRE_LOC).setRegistryName(new ResourceLocation("arsmagica2", "magma"));
	public static final Affinity STORM = new Affinity("storm", 0xdddd66, LIGHTNING_LOC, WATER_LOC, AIR_LOC).setRegistryName(new ResourceLocation("arsmagica2", "storm"));
	public static final Affinity DEATH = new Affinity("death", 0x333333, ENDER_LOC, LIFE_LOC).setRegistryName(new ResourceLocation("arsmagica2", "death"));
	public static final Affinity BLIZZARD = new Affinity("blizzard", 0xaaaaff, ICE_LOC, AIR_LOC).setRegistryName(new ResourceLocation("arsmagica2", "blizzard"));
	
	static {
		GameRegistry.register(MAGMA);
		GameRegistry.register(STORM);
		GameRegistry.register(DEATH);
		GameRegistry.register(BLIZZARD);
	}
}

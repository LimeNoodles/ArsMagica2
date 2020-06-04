package am2.common.defs;

import am2.ArsMagica2;
import am2.client.bosses.renderers.RenderAirGuardian;
import am2.client.bosses.renderers.RenderArcaneGuardian;
import am2.client.bosses.renderers.RenderEarthGuardian;
import am2.client.bosses.renderers.RenderEnderGuardian;
import am2.client.bosses.renderers.RenderFireGuardian;
import am2.client.bosses.renderers.RenderIceGuardian;
import am2.client.bosses.renderers.RenderLifeGuardian;
import am2.client.bosses.renderers.RenderLightningGuardian;
import am2.client.bosses.renderers.RenderPlantGuardian;
import am2.client.bosses.renderers.RenderThrownRock;
import am2.client.bosses.renderers.RenderThrownSickle;
import am2.client.bosses.renderers.RenderWaterGuardian;
import am2.client.bosses.renderers.RenderWinterGuardianArm;
import am2.client.entity.render.*;
import am2.common.LogHelper;
import am2.common.bosses.EntityAirGuardian;
import am2.common.bosses.EntityArcaneGuardian;
import am2.common.bosses.EntityEarthGuardian;
import am2.common.bosses.EntityEnderGuardian;
import am2.common.bosses.EntityFireGuardian;
import am2.common.bosses.EntityLifeGuardian;
import am2.common.bosses.EntityLightningGuardian;
import am2.common.bosses.EntityNatureGuardian;
import am2.common.bosses.EntityWaterGuardian;
import am2.common.bosses.EntityWinterGuardian;
import am2.common.entity.EntityAirSled;
import am2.common.entity.EntityBoundArrow;
import am2.common.entity.EntityBroom;
import am2.common.entity.EntityDarkMage;
import am2.common.entity.EntityDarkling;
import am2.common.entity.EntityDryad;
import am2.common.entity.EntityEarthElemental;
import am2.common.entity.EntityFireElemental;
import am2.common.entity.EntityFlicker;
import am2.common.entity.EntityHecate;
import am2.common.entity.EntityHellCow;
import am2.common.entity.EntityLightMage;
import am2.common.entity.EntityManaCreeper;
import am2.common.entity.EntityManaElemental;
import am2.common.entity.EntityManaVortex;
import am2.common.entity.EntityRiftStorage;
import am2.common.entity.EntityShadowHelper;
import am2.common.entity.EntityShockwave;
import am2.common.entity.EntitySpellEffect;
import am2.common.entity.EntitySpellProjectile;
import am2.common.entity.EntityThrownRock;
import am2.common.entity.EntityThrownSickle;
import am2.common.entity.EntityWaterElemental;
import am2.common.entity.EntityWhirlwind;
import am2.common.entity.EntityWinterGuardianArm;
import am2.common.utils.RenderFactory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityManager {
	
	public static final EntityManager instance = new EntityManager();
	
	private EntityManager() {
		
	}
	
	public void registerEntities() { ;
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntitySpellProjectile.class, "SpellProjectile", 0, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityRiftStorage.class, "RiftStorage", 1, ArsMagica2.instance, 64, 2, false);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntitySpellEffect.class, "SpellEffect", 2, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityThrownRock.class, "ThrownRock", 3, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityBoundArrow.class, "BoundArrow", 4, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityDarkling.class, "Darkling", 5, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityDarkMage.class, "DarkMage", 6, ArsMagica2.instance, 64, 2, true, 0xaa00ff, 0x660066);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityDryad.class, "Dryad", 7, ArsMagica2.instance, 64, 2, true, 0x00ff00, 0x34e122);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityEarthElemental.class, "EarthElemental", 8, ArsMagica2.instance, 64, 2, true, 0x61330b, 0x00ff00);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityFireElemental.class, "FireElemental", 9, ArsMagica2.instance, 64, 2, true, 0xef260b, 0xff0000);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityLightMage.class, "LightMage", 10, ArsMagica2.instance, 64, 2, true, 0xaa00ff, 0xff00ff);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityManaElemental.class, "ManaElemental", 11, ArsMagica2.instance, 64, 2, true, 0xcccccc, 0xb935cd);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityManaVortex.class, "ManaVortex", 12, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityShockwave.class, "Shockwave", 13, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityThrownSickle.class, "ThrownSickle", 14, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityWhirlwind.class, "Whirlwind", 15, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityWinterGuardianArm.class, "WinterGuardianArm", 16, ArsMagica2.instance, 64, 2, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityAirGuardian.class, "AirGuardian", 17, ArsMagica2.instance, 64, 2, true, 0xFFFFFF, 0xFFCC00);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityArcaneGuardian.class, "ArcaneGuardian", 18, ArsMagica2.instance, 64, 2, true, 0x999999, 0xcc00cc);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityEarthGuardian.class, "EarthGuardian", 19, ArsMagica2.instance, 64, 2, true, 0x663300, 0x339900);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityEnderGuardian.class, "EnderGuardian", 20, ArsMagica2.instance, 64, 2, true, 0x000000, 0x6633);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityFireGuardian.class, "FireGuardian", 21, ArsMagica2.instance, 64, 2, true, 0xFFFFFF, 0xFF0000);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityLifeGuardian.class, "LifeGuardian", 22, ArsMagica2.instance, 64, 2, true, 0x00E6FF, 0xFFE600);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityLightningGuardian.class, "LightningGuardian", 23, ArsMagica2.instance, 64, 2, true, 0xFFE600, 0x00C4FF);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityNatureGuardian.class, "NatureGuardian", 24, ArsMagica2.instance, 64, 2, true, 0x44FF00, 0x307D0F);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityWaterGuardian.class, "WaterGuardian", 25, ArsMagica2.instance, 64, 2, true, 0x0F387D, 0x0097CE);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityWinterGuardian.class, "WinterGuardian", 26, ArsMagica2.instance, 64, 2, true, 0x00CEBA, 0x104742);

		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityAirSled.class, "AirSled", 27, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityBroom.class, "Broom", 28, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityWaterElemental.class, "WaterElemental", 29, ArsMagica2.instance, 64, 2, true, 0x0b5cef, 0x0000ff);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityManaCreeper.class, "ManaCreeper", 30, ArsMagica2.instance, 64, 2, true, 0x0b5cef, 0xb935cd);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityHecate.class, "Hecate", 31, ArsMagica2.instance, 64, 2, true, 0xef260b, 0x3f043d);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityFlicker.class, "Flicker", 32, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityHellCow.class, "HellCow", 33, ArsMagica2.instance, 64, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation("arsmagica2"), EntityShadowHelper.class, "ShadowHelper", 34, ArsMagica2.instance, 64, 2, true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityRiftStorage.class, new RenderFactory(RenderRiftStorage.class));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpellProjectile.class, new RenderFactory(RenderSpellProjectile.class));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpellEffect.class, new RenderFactory(RenderHidden.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownRock.class, new RenderFactory(RenderThrownRock.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoundArrow.class, new RenderFactory(RenderBoundArrow.class));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownSickle.class, new RenderFactory(RenderThrownSickle.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityWinterGuardianArm.class, new RenderFactory(RenderWinterGuardianArm.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityAirSled.class, new RenderFactory(RenderAirSled.class));
		//Bosses
		RenderingRegistry.registerEntityRenderingHandler(EntityAirGuardian.class, new RenderFactory(RenderAirGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityArcaneGuardian.class, new RenderFactory(RenderArcaneGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityEarthGuardian.class, new RenderFactory(RenderEarthGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireGuardian.class, new RenderFactory(RenderFireGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnderGuardian.class, new RenderFactory(RenderEnderGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireGuardian.class, new RenderFactory(RenderFireGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityLifeGuardian.class, new RenderFactory(RenderLifeGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityLightningGuardian.class, new RenderFactory(RenderLightningGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityNatureGuardian.class, new RenderFactory(RenderPlantGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityWaterGuardian.class, new RenderFactory(RenderWaterGuardian.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityWinterGuardian.class, new RenderFactory(RenderIceGuardian.class));

		RenderingRegistry.registerEntityRenderingHandler(EntityManaElemental.class, new RenderFactory(RenderManaElemental.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityWaterElemental.class, new RenderFactory(RenderWaterElemental.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireElemental.class, new RenderFactory(RenderFireElemental.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityEarthElemental.class, new RenderFactory(RenderEarthElemental.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityManaCreeper.class, new RenderFactory(RenderManaCreeper.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityLightMage.class, new RenderFactory(RenderLightMage.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkMage.class, new RenderFactory(RenderDarkMage.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityManaVortex.class, new RenderFactory(RenderManaVortex.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityHecate.class, new RenderFactory(RenderHecate.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityDryad.class, new RenderFactory(RenderDryad.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityFlicker.class, new RenderFactory(RenderFlicker.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityHellCow.class, new RenderFactory(RenderHellCow.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkling.class, new RenderFactory(RenderDarkling.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityShadowHelper.class, new RenderFactory(RenderShadowHelper.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, new RenderFactory(RenderBroom.class));
		RenderingRegistry.registerEntityRenderingHandler(EntityShockwave.class, new RenderFactory(RenderShockwave.class));
	}
	
	public void initializeSpawns(){
		//todo BiomeDictionary.registerAllBiomes();

		//SpawnListEntry wisps = new SpawnListEntry(EntityWisp.class, 1, 1, 1);
		SpawnListEntry manaElementals = new SpawnListEntry(EntityManaElemental.class, ArsMagica2.config.GetManaElementalSpawnRate(), 1, 1);
		SpawnListEntry dryads = new SpawnListEntry(EntityDryad.class, ArsMagica2.config.GetDryadSpawnRate(), 1, 2);
		SpawnListEntry hecates_nonHell = new SpawnListEntry(EntityHecate.class, ArsMagica2.config.GetHecateSpawnRate(), 1, 1);
		SpawnListEntry hecates_hell = new SpawnListEntry(EntityHecate.class, ArsMagica2.config.GetHecateSpawnRate() * 2, 1, 2);
		SpawnListEntry manaCreepers = new SpawnListEntry(EntityManaCreeper.class, ArsMagica2.config.GetManaCreeperSpawnRate(), 1, 1);
		SpawnListEntry lightMages = new SpawnListEntry(EntityLightMage.class, ArsMagica2.config.GetMageSpawnRate(), 1, 3);
		SpawnListEntry darkMages = new SpawnListEntry(EntityDarkMage.class, ArsMagica2.config.GetMageSpawnRate(), 1, 3);
		SpawnListEntry waterElementals = new SpawnListEntry(EntityWaterElemental.class, ArsMagica2.config.GetWaterElementalSpawnRate(), 1, 3);
		SpawnListEntry darklings = new SpawnListEntry(EntityDarkling.class, ArsMagica2.config.GetDarklingSpawnRate(), 4, 8);
		SpawnListEntry earthElementals = new SpawnListEntry(EntityEarthElemental.class, ArsMagica2.config.GetEarthElementalSpawnRate(), 1, 2);
		SpawnListEntry fireElementals = new SpawnListEntry(EntityFireElemental.class, ArsMagica2.config.GetFireElementalSpawnRate(), 1, 1);
		SpawnListEntry flickers = new SpawnListEntry(EntityFlicker.class, ArsMagica2.config.GetFlickerSpawnRate(), 1, 1);

		initSpawnsForBiomeTypes(manaElementals, EnumCreatureType.MONSTER, new Type[]{Type.BEACH, Type.DRY, Type.FOREST, Type.COLD, Type.HILLS, Type.JUNGLE, Type.MAGICAL, Type.MOUNTAIN, Type.PLAINS, Type.SWAMP, Type.WASTELAND}, new Type[]{Type.END, Type.NETHER, Type.MUSHROOM});

		initSpawnsForBiomeTypes(dryads, EnumCreatureType.CREATURE, new Type[]{Type.BEACH, Type.FOREST, Type.MAGICAL, Type.HILLS, Type.JUNGLE, Type.MOUNTAIN, Type.PLAINS}, new Type[]{Type.END, Type.COLD, Type.MUSHROOM, Type.NETHER, Type.WASTELAND, Type.SWAMP, Type.DRY});

		initSpawnsForBiomeTypes(hecates_nonHell, EnumCreatureType.MONSTER, new Type[]{Type.BEACH, Type.DRY, Type.FOREST, Type.COLD, Type.HILLS, Type.JUNGLE, Type.MAGICAL, Type.MOUNTAIN, Type.PLAINS, Type.SWAMP, Type.WASTELAND}, new Type[]{Type.END, Type.NETHER, Type.MUSHROOM});

		initSpawnsForBiomeTypes(hecates_hell, EnumCreatureType.MONSTER, new Type[]{Type.NETHER}, new Type[]{Type.MUSHROOM});

		initSpawnsForBiomeTypes(darklings, EnumCreatureType.MONSTER, new Type[]{Type.NETHER}, new Type[]{Type.MUSHROOM});

		initSpawnsForBiomeTypes(manaCreepers, EnumCreatureType.MONSTER, new Type[]{Type.BEACH, Type.DRY, Type.FOREST, Type.COLD, Type.HILLS, Type.JUNGLE, Type.MAGICAL, Type.MOUNTAIN, Type.PLAINS, Type.SWAMP, Type.WASTELAND}, new Type[]{Type.END, Type.NETHER, Type.MUSHROOM});

		initSpawnsForBiomeTypes(lightMages, EnumCreatureType.MONSTER, new Type[]{Type.BEACH, Type.DRY, Type.FOREST, Type.COLD, Type.HILLS, Type.JUNGLE, Type.MAGICAL, Type.MOUNTAIN, Type.PLAINS, Type.SWAMP, Type.WASTELAND}, new Type[]{Type.END, Type.NETHER, Type.MUSHROOM});

		initSpawnsForBiomeTypes(darkMages, EnumCreatureType.MONSTER, new Type[]{Type.BEACH, Type.DRY, Type.FOREST, Type.COLD, Type.HILLS, Type.JUNGLE, Type.MAGICAL, Type.MOUNTAIN, Type.PLAINS, Type.SWAMP, Type.WASTELAND}, new Type[]{Type.END, Type.NETHER, Type.MUSHROOM});

		initSpawnsForBiomeTypes(waterElementals, EnumCreatureType.MONSTER, new Type[]{Type.WATER}, new Type[]{Type.END, Type.NETHER, Type.MUSHROOM});
		initSpawnsForBiomeTypes(waterElementals, EnumCreatureType.WATER_CREATURE, new Type[]{Type.WATER}, new Type[]{Type.END, Type.NETHER, Type.MUSHROOM});

		initSpawnsForBiomeTypes(earthElementals, EnumCreatureType.MONSTER, new Type[]{Type.HILLS, Type.MOUNTAIN}, new Type[]{Type.MUSHROOM});
		initSpawnsForBiomeTypes(fireElementals, EnumCreatureType.MONSTER, new Type[]{Type.NETHER}, new Type[]{Type.MUSHROOM});
		
		//todo initSpawnsForBiomeTypes(flickers, EnumCreatureType.AMBIENT, Type.values(), new Type[0]);

	}

	private void initSpawnsForBiomeTypes(SpawnListEntry spawnListEntry, EnumCreatureType creatureType, Type[] types, Type[] exclusions){
		if (spawnListEntry.itemWeight == 0){
			LogHelper.info("Skipping spawn list entry for %s (as type %s), as the weight is set to 0.  This can be changed in config.", spawnListEntry.entityClass.getName(), creatureType.toString());
			return;
		}
		for (Type type : types){
			//todo initSpawnsForBiomes(BiomeDictionary.getBiomesForType(type), spawnListEntry, creatureType, exclusions);
		}
	}

	private void initSpawnsForBiomes(Biome[] biomes, SpawnListEntry spawnListEntry, EnumCreatureType creatureType, Type[] exclusions){
		if (biomes == null) return;
		for (Biome biome : biomes){
			if (biomeIsExcluded(biome, exclusions)) continue;
			if (!biome.getSpawnableList(creatureType).contains(spawnListEntry))
				biome.getSpawnableList(creatureType).add(spawnListEntry);
		}
	}

	private boolean biomeIsExcluded(Biome biome, Type[] exclusions){

		//todo Type biomeTypes[] = BiomeDictionary.getBiomes(biome);

		//for (Type exclusion : exclusions){
		//	for (Type biomeType : biomeTypes){
		//		if (biomeType == exclusion) return true;
		//	}
		//}
		return false;
	}}

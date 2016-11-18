package am2.experimental.skills;

import am2.api.DamageSources;
import am2.api.affinity.Affinity;
import am2.api.skill.SkillPoint;
import am2.experimental.AffinitySkill;
import am2.experimental.skills.action.AugmentDamage;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AffinitySkillDefs {
	
	private static final DamageSource FIRE_DAMAGE = DamageSources.causeFireDamage(null);
	private static final DamageSource FROST_DAMAGE = DamageSources.causeFrostDamage(null);
	private static final DamageSource WATER_DAMAGE = DamageSources.causeDrownDamage(null);
	private static final DamageSource LIGHTNING_DAMAGE = DamageSources.causeLightningDamage(null);
	private static final DamageSource EARTH_DAMAGE = DamageSources.causePhysicalDamage(null);
	
	//Fire Tree
	public static final AffinitySkill basicFireMastery = new AffinitySkill(Affinity.FIRE, 0.1f, SkillPoint.SKILL_POINT_1, new AugmentDamage(FIRE_DAMAGE, 1.2f)).setRegistryName(new ResourceLocation("arsmagica2", "basic_fire_mastery"));
	public static final AffinitySkill advancedFireMastery = new AffinitySkill(Affinity.FIRE, 0.5f, SkillPoint.SKILL_POINT_2, new AugmentDamage(FIRE_DAMAGE, 1.3f)).setRegistryName(new ResourceLocation("arsmagica2", "advanced_fire_mastery"));
	public static final AffinitySkill masterFireMastery = new AffinitySkill(Affinity.FIRE, 1.0f, SkillPoint.SKILL_POINT_3, new AugmentDamage(FIRE_DAMAGE, 1.4f)).setRegistryName(new ResourceLocation("arsmagica2", "master_fire_mastery"));
	
	//Ice Tree
	public static final AffinitySkill basicIceMastery = new AffinitySkill(Affinity.ICE, 0.1f, SkillPoint.SKILL_POINT_1, new AugmentDamage(FROST_DAMAGE, 1.2f)).setRegistryName(new ResourceLocation("arsmagica2", "basic_ice_mastery"));
	public static final AffinitySkill advancedIceMastery = new AffinitySkill(Affinity.ICE, 0.5f, SkillPoint.SKILL_POINT_2, new AugmentDamage(FROST_DAMAGE, 1.3f)).setRegistryName(new ResourceLocation("arsmagica2", "advanced_ice_mastery"));
	public static final AffinitySkill masterIceMastery = new AffinitySkill(Affinity.ICE, 1.0f, SkillPoint.SKILL_POINT_3, new AugmentDamage(FROST_DAMAGE, 1.4f)).setRegistryName(new ResourceLocation("arsmagica2", "master_ice_mastery"));

	//Water Tree
	public static final AffinitySkill basicWaterMastery = new AffinitySkill(Affinity.WATER, 0.1f, SkillPoint.SKILL_POINT_1, new AugmentDamage(WATER_DAMAGE, 1.2f)).setRegistryName(new ResourceLocation("arsmagica2", "basic_water_mastery"));
	public static final AffinitySkill advancedWaterMastery = new AffinitySkill(Affinity.WATER, 0.5f, SkillPoint.SKILL_POINT_2, new AugmentDamage(WATER_DAMAGE, 1.3f)).setRegistryName(new ResourceLocation("arsmagica2", "advanced_water_mastery"));
	public static final AffinitySkill masterWaterMastery = new AffinitySkill(Affinity.WATER, 1.0f, SkillPoint.SKILL_POINT_3, new AugmentDamage(WATER_DAMAGE, 1.4f)).setRegistryName(new ResourceLocation("arsmagica2", "master_water_mastery"));

	//Ice Tree
	public static final AffinitySkill basicLightningMastery = new AffinitySkill(Affinity.LIGHTNING, 0.1f, SkillPoint.SKILL_POINT_1, new AugmentDamage(LIGHTNING_DAMAGE, 1.2f)).setRegistryName(new ResourceLocation("arsmagica2", "basic_lightning_mastery"));
	public static final AffinitySkill advancedLightningMastery = new AffinitySkill(Affinity.LIGHTNING, 0.5f, SkillPoint.SKILL_POINT_2, new AugmentDamage(LIGHTNING_DAMAGE, 1.3f)).setRegistryName(new ResourceLocation("arsmagica2", "advanced_lightning_mastery"));
	public static final AffinitySkill masterLightningMastery = new AffinitySkill(Affinity.LIGHTNING, 1.0f, SkillPoint.SKILL_POINT_3, new AugmentDamage(LIGHTNING_DAMAGE, 1.4f)).setRegistryName(new ResourceLocation("arsmagica2", "master_lightning_mastery"));

	//Ice Tree
	public static final AffinitySkill basicEarthMastery = new AffinitySkill(Affinity.EARTH, 0.1f, SkillPoint.SKILL_POINT_1, new AugmentDamage(EARTH_DAMAGE, 1.2f)).setRegistryName(new ResourceLocation("arsmagica2", "basic_earth_mastery"));
	public static final AffinitySkill advancedEarthMastery = new AffinitySkill(Affinity.EARTH, 0.5f, SkillPoint.SKILL_POINT_2, new AugmentDamage(EARTH_DAMAGE, 1.3f)).setRegistryName(new ResourceLocation("arsmagica2", "advanced_earth_mastery"));
	public static final AffinitySkill masterEarthMastery = new AffinitySkill(Affinity.EARTH, 1.0f, SkillPoint.SKILL_POINT_3, new AugmentDamage(EARTH_DAMAGE, 1.4f)).setRegistryName(new ResourceLocation("arsmagica2", "master_earth_mastery"));

	static {
		GameRegistry.register(basicFireMastery);
		GameRegistry.register(advancedFireMastery);
		GameRegistry.register(masterFireMastery);

		GameRegistry.register(basicIceMastery);
		GameRegistry.register(advancedIceMastery);
		GameRegistry.register(masterIceMastery);

		GameRegistry.register(basicWaterMastery);
		GameRegistry.register(advancedWaterMastery);
		GameRegistry.register(masterWaterMastery);

		GameRegistry.register(basicLightningMastery);
		GameRegistry.register(advancedLightningMastery);
		GameRegistry.register(masterLightningMastery);

		GameRegistry.register(basicEarthMastery);
		GameRegistry.register(advancedEarthMastery);
		GameRegistry.register(masterEarthMastery);
	}
}

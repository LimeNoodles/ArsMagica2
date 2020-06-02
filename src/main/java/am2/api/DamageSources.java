package am2.api;

import am2.api.sources.DamageSourceDarkNexus;
import am2.api.sources.DamageSourceFire;
import am2.api.sources.DamageSourceFrost;
import am2.api.sources.DamageSourceHoly;
import am2.api.sources.DamageSourceLightning;
import am2.api.sources.DamageSourceUnsummon;
import am2.api.sources.DamageSourceWTFBoom;
import am2.api.sources.DamageSourceWind;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class DamageSources{
	public static DamageSourceUnsummon unsummon = new DamageSourceUnsummon();
	public static DamageSourceWTFBoom wtfBoom = new DamageSourceWTFBoom();
	public static DamageSourceDarkNexus darkNexus = new DamageSourceDarkNexus();

	public enum DamageSourceTypes{
		PHYSICAL,
		FIRE,
		FROST,
		LIGHTNING,
		MAGIC,
		WITHER,
		THORNS,
		CACTUS,
		HOLY
	}

	public static DamageSourceFire causeFireDamage(LivingEntity source){
		DamageSourceFire fire = new DamageSourceFire(source);
		fire.setDifficultyScaled();
		return fire;
	}

	public static DamageSourceFrost causeFrostDamage(LivingEntity source){
		DamageSourceFrost frost = new DamageSourceFrost(source);
		frost.setDifficultyScaled();
		return frost;
	}

	public static DamageSourceLightning causeLightningDamage(LivingEntity source){
		DamageSourceLightning lightning = new DamageSourceLightning(source);
		lightning.setDifficultyScaled();
		return lightning;
	}

	public static DamageSourceWind causeWindDamage(LivingEntity source){
		DamageSourceWind wind = new DamageSourceWind(source);
		wind.setDifficultyScaled();
		return wind;
	}

	public static DamageSourceHoly causeHolyDamage(LivingEntity source){
		DamageSourceHoly holy = new DamageSourceHoly(source);
		holy.setDifficultyScaled();
		return holy;
	}

	public static DamageSource causeDamage(DamageSourceTypes type, LivingEntity source){
		switch (type){
		case FIRE:
			DamageSourceFire fire = new DamageSourceFire(source);
			fire.setDifficultyScaled();
			return fire;
		case FROST:
			DamageSourceFrost frost = new DamageSourceFrost(source);
			frost.setDifficultyScaled();
			return frost;
		case LIGHTNING:
			DamageSourceLightning lightning = new DamageSourceLightning(source);
			lightning.setDifficultyScaled();
			return lightning;
		case MAGIC:
			return DamageSource.causeIndirectMagicDamage(source, source);
		case WITHER:
			return causeWitherDamage(source);
		case THORNS:
			return causeThornsDamage(source);
		case CACTUS:
			return causeCactusDamage(source);
		case PHYSICAL:
		default:
			return DamageSource.causeMobDamage(source);
		}
	}

	public static DamageSource causeDamage(DamageSourceTypes type, LivingEntity source, boolean unblockable){
		DamageSource ds = causeDamage(type, source);
		if (unblockable)
			setDamageSourceUnblockable(ds);
		return ds;
	}

	public static DamageSource causeWitherDamage(LivingEntity source){
		return (new EntityDamageSource("wither", source));
	}

	public static DamageSource causeThornsDamage(LivingEntity source){
		return (new EntityDamageSource("thorns", source));
	}

	public static DamageSource causeCactusDamage(LivingEntity source){
		return (new EntityDamageSource("cactus", source));
	}

	public static DamageSource causeMagicDamage(LivingEntity source){
		DamageSource ds = new EntityDamageSource("magic", source);
		ds.setMagicDamage();
		setDamageSourceUnblockable(ds);
		return ds;
	}

	public static DamageSource causePhysicalDamage(Entity source){
		if (source instanceof PlayerEntity)
			return (new EntityDamageSource("player", source));
		return (new EntityDamageSource("mob", source));
	}

	public static DamageSource causeWitherDamage(LivingEntity source, boolean unblockable){
		DamageSource ds = causeWitherDamage(source);
		if (unblockable)
			setDamageSourceUnblockable(ds);
		return ds;
	}

	public static DamageSource causeThornsDamage(LivingEntity source, boolean unblockable){
		DamageSource ds = causeThornsDamage(source);
		if (unblockable)
			setDamageSourceUnblockable(ds);
		return ds;
	}

	public static DamageSource causeCactusDamage(LivingEntity source, boolean unblockable){
		DamageSource ds = causeCactusDamage(source);
		if (unblockable)
			setDamageSourceUnblockable(ds);
		return ds;
	}

	public static DamageSource setDamageSourceUnblockable(DamageSource original){
		ObfuscationReflectionHelper.setPrivateValue(DamageSource.class, original, true, "field_76374_o", "isUnblockable");
		return original;
	}

	public static DamageSource causeDrownDamage(LivingEntity source){
		return (new EntityDamageSource("drown", source));
	}


}

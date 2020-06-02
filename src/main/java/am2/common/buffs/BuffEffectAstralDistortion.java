package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectAstralDistortion extends BuffEffect{

	public BuffEffectAstralDistortion(int duration, int amplifier){
		super(PotionEffectsDefs.ASTRAL_DISTORTION, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	protected String spellBuffName(){
		return "Astral Distortion";
	}

}

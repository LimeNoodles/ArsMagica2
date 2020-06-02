package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectWaterBreathing extends BuffEffect{

	int breath;

	public BuffEffectWaterBreathing(int duration, int amplifier){
		super(PotionEffectsDefs.WATER_BREATHING, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
		breath = entityliving.getAir();
	}

	@Override
	public void performEffect(LivingEntity entityliving){
		if (entityliving.isInWater()){
			entityliving.setAir(breath);
		}else{
			breath = entityliving.getAir();
		}
	}

	@Override
	public void stopEffect(LivingEntity entityliving){

	}

	@Override
	public String spellBuffName(){
		return "Water Breathing";
	}

}

package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectGravityWell extends BuffEffect{

	public BuffEffectGravityWell(int duration, int amplifier){
		super(PotionEffectsDefs.GRQVITY_WELL, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	public void performEffect(LivingEntity entityliving){

	}

	@Override
	protected String spellBuffName(){
		return "Gravity Well";
	}

}

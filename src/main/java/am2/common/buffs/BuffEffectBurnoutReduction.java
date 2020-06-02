package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectBurnoutReduction extends BuffEffect{
	public BuffEffectBurnoutReduction(int duration, int amplifier){
		super(PotionEffectsDefs.BURNOUT_REDUCTION, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	protected String spellBuffName(){
		return "Burnout Reduction";
	}
}

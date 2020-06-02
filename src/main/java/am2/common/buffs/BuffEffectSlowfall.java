package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectSlowfall extends BuffEffect{

	public BuffEffectSlowfall(int duration, int amplifier){
		super(PotionEffectsDefs.SLOWFALL, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	protected String spellBuffName(){
		return "Slowfall";
	}

}

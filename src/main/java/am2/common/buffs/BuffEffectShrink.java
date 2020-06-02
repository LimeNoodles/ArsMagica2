package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectShrink extends BuffEffect{

	public BuffEffectShrink(int duration, int amplifier){
		super(PotionEffectsDefs.SHRINK, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){

	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	protected String spellBuffName(){
		return "Shrunken";
	}

}

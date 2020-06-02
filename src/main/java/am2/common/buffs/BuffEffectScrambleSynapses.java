package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;

import net.minecraft.entity.LivingEntity;

public class BuffEffectScrambleSynapses extends BuffEffect{

	public BuffEffectScrambleSynapses(int duration, int amplifier){
		super(PotionEffectsDefs.SCRAMBLE_SYNAPSES, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	protected String spellBuffName(){
		return "Scramble Synapses";
	}

}

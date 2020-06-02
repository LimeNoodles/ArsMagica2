package am2.common.buffs;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Potion;

public class BuffEffectShield extends BuffEffect{

	public BuffEffectShield(Potion buffID, int duration, int amplifier){
		super(buffID, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
		
	}

	@Override
	protected String spellBuffName(){
		return "Magic Shield";
	}
}

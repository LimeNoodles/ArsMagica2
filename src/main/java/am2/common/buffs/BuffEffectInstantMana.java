package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectInstantMana extends BuffEffect{

	public BuffEffectInstantMana(int duration, int amplifier){
		super(PotionEffectsDefs.INSTANT_MANA, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){

	}

	@Override
	public void stopEffect(LivingEntity entityliving){

	}

	@Override
	protected String spellBuffName(){
		return "Instant Mana";
	}

}

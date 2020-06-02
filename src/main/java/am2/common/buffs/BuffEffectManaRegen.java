package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectManaRegen extends BuffEffect{

	public BuffEffectManaRegen(int duration, int amplifier){
		super(PotionEffectsDefs.MANA_REGEN, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	protected String spellBuffName(){
		return "Mana Regen";
	}

}

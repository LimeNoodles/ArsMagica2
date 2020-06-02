package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectAgility extends BuffEffect{

	public BuffEffectAgility(int duration, int amplifier){
		super(PotionEffectsDefs.AGILITY, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	public void performEffect(LivingEntity entityliving){
		entityliving.setAIMoveSpeed(entityliving.getAIMoveSpeed() * 1.2f);
	}

	@Override
	protected String spellBuffName(){
		return "Agility";
	}

}

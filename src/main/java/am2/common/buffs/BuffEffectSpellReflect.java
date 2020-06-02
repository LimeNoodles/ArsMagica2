package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;

public class BuffEffectSpellReflect extends BuffEffect{

	public BuffEffectSpellReflect(int duration, int amplifier){
		super(PotionEffectsDefs.SPELL_REFLECT, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	protected String spellBuffName(){
		return "Spell Reflect";
	}

}

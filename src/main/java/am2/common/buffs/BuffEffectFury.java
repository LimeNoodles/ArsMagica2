package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class BuffEffectFury extends BuffEffect{

	public BuffEffectFury(int duration, int amplifier){
		super(PotionEffectsDefs.FURY, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
		if (!entityliving.world.isRemote){
			entityliving.addPotionEffect(new Effect(Effects.HUNGER, 200, 1));
			entityliving.addPotionEffect(new Effect(Effects.NAUSEA, 200, 1));
		}
	}

	@Override
	public void combine(Effect potioneffect){
	}

	@Override
	protected String spellBuffName(){
		return "Fury";
	}

}

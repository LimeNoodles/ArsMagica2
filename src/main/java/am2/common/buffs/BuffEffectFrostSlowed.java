package am2.common.buffs;

import java.util.UUID;

import am2.common.defs.PotionEffectsDefs;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class BuffEffectFrostSlowed extends BuffEffect{

	private static final UUID frostSlowID = UUID.fromString("03B0A79B-9569-43AE-BFE3-820D993D4A64");

	public BuffEffectFrostSlowed(int duration, int amplifier){
		super(PotionEffectsDefs.FROST_SLOW, duration, amplifier);
	}

	@Override
	public boolean shouldNotify(){
		return false;
	}

	@Override
	protected String spellBuffName(){
		return "Frost Slow";
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
		IAttributeInstance attributeinstance = entityliving.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

		if (attributeinstance.getModifier(frostSlowID) != null){
			attributeinstance.removeModifier(attributeinstance.getModifier(frostSlowID));
		}
		
		attributeinstance.applyModifier(new AttributeModifier(frostSlowID, "Frost Slow", -0.2 -(0.3 * this.getAmplifier()), 2));
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
		IAttributeInstance attributeinstance = entityliving.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

		if (attributeinstance.getModifier(frostSlowID) != null){
			attributeinstance.removeModifier(attributeinstance.getModifier(frostSlowID));
		}
	}

	@Override
	public void performEffect(LivingEntity entityliving){
	}
}

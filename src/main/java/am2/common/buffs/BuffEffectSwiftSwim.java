package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;

import net.minecraft.entity.LivingEntity;

public class BuffEffectSwiftSwim extends BuffEffect{

	public BuffEffectSwiftSwim(int duration, int amplifier){
		super(PotionEffectsDefs.SWIFT_SWIM, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
	}

	@Override
	public void performEffect(LivingEntity entityliving){
		if (entityliving.isInWater()){
			if (!(entityliving instanceof EntityPlayer) || !((EntityPlayer)entityliving).capabilities.isFlying){
				entityliving.motionX *= (1.133f + 0.03 * this.getAmplifier());
				entityliving.motionZ *= (1.133f + 0.03 * this.getAmplifier());

				if (entityliving.motionY > 0){
					entityliving.motionY *= 1.134;
				}
			}
		}
	}

	@Override
	protected String spellBuffName(){
		return "Swift Swim";
	}

}

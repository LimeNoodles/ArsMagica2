package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BuffEffectLevitation extends BuffEffect{

	public BuffEffectLevitation(int duration, int amplifier){
		super(PotionEffectsDefs.LEVITATION, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
		if (entityliving instanceof PlayerEntity){
			((PlayerEntity)entityliving).capabilities.allowFlying = true;
			((PlayerEntity)entityliving).sendPlayerAbilities();
		}
	}

	@Override
	public void performEffect(LivingEntity entityliving){
		if (entityliving instanceof PlayerEntity){
			if (((PlayerEntity)entityliving).capabilities.isFlying){
				float factor = 0.4f;
				entityliving.motionX *= factor;
				entityliving.motionZ *= factor;
				entityliving.motionY *= 0.0001f;
			}
		}
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
		if (entityliving instanceof PlayerEntity){
			PlayerEntity player = (PlayerEntity)entityliving;
			if (!player.capabilities.isCreativeMode){
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
				player.fallDistance = 0f;
				player.sendPlayerAbilities();
			}
		}
	}

	@Override
	protected String spellBuffName(){
		return "Levitation";
	}

}

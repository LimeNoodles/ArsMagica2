package am2.common.buffs;

import am2.common.defs.PotionEffectsDefs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerEntity;

public class BuffEffectFlight extends BuffEffect{
	
	private boolean enableFlight = true;

	public BuffEffectFlight(int duration, int amplifier){
		super(PotionEffectsDefs.FLIGHT, duration, amplifier);
	}

	@Override
	public void applyEffect(LivingEntity entityliving){
		enableFlight = true;
	}

	@Override
	public void performEffect(LivingEntity entityliving){
		if ( enableFlight ){
			if (entityliving instanceof EntityPlayerMP){
				PlayerEntity player = (PlayerEntity)entityliving;
				player.capabilities.allowFlying = true;
				player.sendPlayerAbilities();		
			} 
		} else {
			dispellFlight(entityliving);
		}
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
		enableFlight = false;
		dispellFlight(entityliving);
	}

	@Override
	protected String spellBuffName(){
		return "Flight";
	}
	
	private void dispellFlight(LivingEntity entityliving) {
		if (entityliving instanceof EntityPlayerMP){
			PlayerEntity player = (PlayerEntity) entityliving;
			if (!player.isCreative()){
				player.allowFlying = false;
				player.capabilities.isFlying = false;
				player.fallDistance = 0f;
				player.sendPlayerAbilities();
			}
		}
	}
}

package am2.api.sources;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceWind extends EntityDamageSource{
	public DamageSourceWind(LivingEntity source){
		super("am2.wind", source);
		this.setDamageBypassesArmor();
	}
}

package am2.api.sources;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceLightning extends EntityDamageSource{
	public DamageSourceLightning(LivingEntity source){
		super("am2.lightning", source);
		this.setDamageBypassesArmor();
	}
}

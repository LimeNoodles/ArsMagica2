package am2.api.sources;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceFire extends EntityDamageSource{
	public DamageSourceFire(LivingEntity source){
		super("am2.fire", source);
		this.setFireDamage();
		this.setDamageBypassesArmor();
	}
}

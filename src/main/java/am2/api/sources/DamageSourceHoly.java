package am2.api.sources;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceHoly extends EntityDamageSource{
	public DamageSourceHoly(LivingEntity source){
		super("am2.holy", source);
		this.setDamageBypassesArmor();
	}
}

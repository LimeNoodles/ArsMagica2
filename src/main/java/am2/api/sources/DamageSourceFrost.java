package am2.api.sources;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceFrost extends EntityDamageSource{
	public DamageSourceFrost(LivingEntity source){
		super("am2.frost", source);
		this.setDamageBypassesArmor();
	}
}

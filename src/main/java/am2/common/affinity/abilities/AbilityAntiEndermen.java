package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;

import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AbilityAntiEndermen extends AbstractAffinityAbility {

	public AbilityAntiEndermen() {
		super(new ResourceLocation("arsmagica2", "antiendermen"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.9f;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.WATER;
	}
	
	@Override
	public void applyHurt(PlayerEntity player, LivingHurtEvent event, boolean isAttacker) {
		if (!isAttacker && event.getSource().getTrueSource() instanceof EndermanEntity ){
			event.getSource().getTrueSource().attackEntityFrom(DamageSource.DROWN, 2);
		}
	}

}

package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;
import am2.common.extensions.AffinityData;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AbilityThorns extends AbstractAffinityAbility {

	public AbilityThorns() {
		super(new ResourceLocation("arsmagica2", "thorns"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.5f;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.NATURE;
	}
	
	@Override
	public void applyHurt(PlayerEntity player, LivingHurtEvent event, boolean isAttacker) {
		if (!isAttacker && event.getSource().getTrueSource() instanceof LivingEntity){
			double natureDepth = AffinityData.For(player).getAffinityDepth(Affinity.NATURE);
			if (natureDepth == 1.0f){
				((LivingEntity)event.getSource().getTrueSource()).attackEntityFrom(DamageSource.CACTUS, 3);
			}else if (natureDepth >= 0.75f){
				((LivingEntity)event.getSource().getTrueSource()).attackEntityFrom(DamageSource.CACTUS, 2);
			}else if (natureDepth >= 0.5f){
				((LivingEntity)event.getSource().getTrueSource()).attackEntityFrom(DamageSource.CACTUS, 1);
			}
		}
	}

}

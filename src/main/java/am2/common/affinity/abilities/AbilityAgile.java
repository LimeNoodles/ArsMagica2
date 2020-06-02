package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;
import am2.common.extensions.AffinityData;
import am2.common.extensions.EntityExtension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class AbilityAgile extends AbstractAffinityAbility {

	public AbilityAgile() {
		super(new ResourceLocation("arsmagica2", "agile"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.5f;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.AIR;
	}
	
	@Override
	public void applyJump(PlayerEntity player, LivingJumpEvent event) {
		double airDepth = AffinityData.For(player).getAffinityDepth(Affinity.AIR);
		double velocity = airDepth * 0.35f;
		if (EntityExtension.For(player).getIsFlipped())
			velocity *= -1;
		player.addVelocity(0, velocity, 0);
	}
	
	@Override
	public void applyFall(PlayerEntity player, LivingFallEvent event) {
		double airDepth = AffinityData.For(player).getAffinityDepth(Affinity.AIR);
		event.setDistance((float) (event.getDistance() - (2 * airDepth)));
		if (event.getDistance() < 0) event.setDistance(0);
	}

}

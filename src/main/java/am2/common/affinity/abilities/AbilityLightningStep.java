package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class AbilityLightningStep extends AbstractAffinityAbility {

	public AbilityLightningStep() {
		super(new ResourceLocation("arsmagica2", "lightningstep"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.5f;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.LIGHTNING;
	}
	
	@Override
	public void applyTick(PlayerEntity player) {
		player.stepHeight = 1.014F;
	}
	
	@Override
	public void removeEffects(PlayerEntity player) {
		if (player.stepHeight == 1.014F)
			player.stepHeight = 0.6F;
	}

}

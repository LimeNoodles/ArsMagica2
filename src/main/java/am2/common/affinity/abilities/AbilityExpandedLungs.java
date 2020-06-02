package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class AbilityExpandedLungs extends AbstractAffinityAbility {

	public AbilityExpandedLungs() {
		super(new ResourceLocation("arsmagica2", "expandedlungs"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.4f;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.WATER;
	}

	@Override
	public void applyTick(PlayerEntity player) {
		if (player.isInWater() && player.world.rand.nextInt(20) < 4) {
			player.setAir(player.getAir() + 1);
		}
	}

}

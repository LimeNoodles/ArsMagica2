package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;
import am2.api.event.SpellCastEvent.Pre;
import am2.common.defs.PotionEffectsDefs;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class AbilityOneWithMagic extends AbstractAffinityAbility {

	public AbilityOneWithMagic() {
		super(new ResourceLocation("arsmagica2", "onewithmagic"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.5f;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.ARCANE;
	}
	
	@Override
	public void applyPreSpellCast(PlayerEntity player, Pre event) {
		event.manaCost *= 0.95f;
		event.burnout *= 0.95f;

		if (event.entityLiving.isPotionActive(PotionEffectsDefs.CLARITY)){
			event.manaCost = 0f;
			event.burnout = 0f;
		}
	}
}

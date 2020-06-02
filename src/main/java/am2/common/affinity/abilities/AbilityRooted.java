package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;
import am2.common.affinity.AffinityAbilityModifiers;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class AbilityRooted extends AbstractAffinityAbility {

	public AbilityRooted() {
		super(new ResourceLocation("arsmagica2", "rooted"));
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
	public void applyTick(PlayerEntity player) {
		IAttributeInstance attribute = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		AffinityAbilityModifiers.instance.applyOrRemoveModifier(attribute, AffinityAbilityModifiers.natureAffinityRoots, true);
	}
	
	@Override
	public void removeEffects(PlayerEntity player) {
		IAttributeInstance attribute = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		AffinityAbilityModifiers.instance.applyOrRemoveModifier(attribute, AffinityAbilityModifiers.natureAffinityRoots, false);
	}

}

package am2.api.affinity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractToggledAffinityAbility extends AbstractAffinityAbility{

	protected AbstractToggledAffinityAbility(ResourceLocation identifier) {
		super(identifier);
	}
	
	protected abstract boolean isEnabled(PlayerEntity player);
	
	@Override
	public boolean canApply(PlayerEntity player) {
		return super.canApply(player) && isEnabled(player);
	}
}

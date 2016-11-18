package am2.experimental.skills.action;

import am2.api.event.SpellCastEvent.Post;
import am2.api.event.SpellCastEvent.Pre;
import am2.experimental.IAffinitySkillAction;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class AugmentDamage implements IAffinitySkillAction {
	
	private final DamageSource source;
	private final float amplifier;

	public AugmentDamage(DamageSource source, float amplifier) {
		this.source = source;
		this.amplifier = amplifier;
	}

	@Override
	public boolean canApply(EntityPlayer player, Class<? extends Event> clazz) {
		return clazz.equals(LivingHurtEvent.class);
	}

	@Override
	public void apply(EntityPlayer player, LivingUpdateEvent event) {}

	@Override
	public void apply(EntityPlayer player, LivingHurtEvent event, boolean isAttacker) {
		if (!isAttacker) return;
		if (event.getSource() == null) return;
		if (!event.getSource().damageType.equals(source.damageType)) return;
		event.setAmount(event.getAmount() * amplifier);
	}

	@Override
	public void apply(EntityPlayer player, LivingFallEvent event) {}

	@Override
	public void apply(EntityPlayer player, Post event) {}

	@Override
	public void apply(EntityPlayer player, Pre event) {}

	@Override
	public void apply(EntityPlayer player, LivingDeathEvent event, boolean isKiller) {}

	@Override
	public void apply(EntityPlayer player, LivingJumpEvent event) {}

	@Override
	public void removeEffects(EntityPlayer player) {}

}

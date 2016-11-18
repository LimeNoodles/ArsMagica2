package am2.experimental;

import am2.api.event.SpellCastEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/**
 * Represents the action that a skill can lead to.
 * 
 * @author EdwinMindcraft
 *
 */
public interface IAffinitySkillAction {
	/**
	 * Can this skill applies to the player in the current conditions?
	 * 
	 * @param player : The player to check for
	 * @param clazz : The type of event
	 * @return true if the skill can apply, false otherwise
	 */
	public boolean canApply(EntityPlayer player, Class<? extends Event> clazz);
	/**
	 * Applies the effect every tick
	 * 
	 * @param player : The owner of the skill
	 * @param event
	 */
	public void apply(EntityPlayer player, LivingUpdateEvent event);
	/**
	 * Applies the effect on attacking or being attacked
	 * 
	 * @param player : The owner of the skill
	 * @param event
	 * @param isAttacker : <code>true</code> if the player is the source of damage, <code>false</code> otherwise
	 */
	public void apply(EntityPlayer player, LivingHurtEvent event, boolean isAttacker);
	/**
	 * Applies the effect just before fall damage is applied
	 * 
	 * @param player : The owner of the skill
	 * @param event
	 */
	public void apply(EntityPlayer player, LivingFallEvent event);
	/**
	 * Applies the effect after a spell cast
	 * 
	 * @param player : The owner of the skill
	 * @param event
	 */
	public void apply(EntityPlayer player, SpellCastEvent.Post event);
	/**
	 * Applies the effect before a spell cast
	 * 
	 * @param player : The owner of the skill
	 * @param event
	 */
	public void apply(EntityPlayer player, SpellCastEvent.Pre event);
	/**
	 * Applies the effect when the player kills or is killed
	 * 
	 * @param player : The owner of the skill
	 * @param event
	 * @param isKiller : <code>true</code> if the player is the killer, <code>false</code> otherwise
	 */
	public void apply(EntityPlayer player, LivingDeathEvent event, boolean isKiller);
	/**
	 * Applies the effect when the player jumps.
	 * 
	 * @param player : The owner of the skill
	 * @param event
	 */
	public void apply(EntityPlayer player, LivingJumpEvent event);
	/**
	 * Called every tick if the effect isn't active.
	 * 
	 * @param player
	 */
	public void removeEffects(EntityPlayer player);
}

package am2.api.spell;

import am2.api.affinity.Affinity;
import am2.common.spell.SpellCastResult;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class SpellShape extends AbstractSpellPart{
		
	/**
	 * Is this shape a valid shape for a channeled/maintained spell?
	 */
	public abstract boolean isChanneled();

	/**
	 * Allows different shapes to vary the mana cost of a spell
	 */
	public abstract float manaCostMultiplier();

	/**
	 * Is the spell a terminus shape?  Return true if this component does not continue the spell chain when proccing.
	 */
	public abstract boolean isTerminusShape();

	/**
	 * Is the shape a principal shape?  Return true if this spell requires another shape to proc (like runes and zones)
	 */
	public abstract boolean isPrincipumShape();

	/**
	 * Play the sound for the specified affinity
	 */
	public abstract SoundEvent getSoundForAffinity(Affinity affinity, SpellData stack, World world);
	
	/**
	 * Creates the target area/entity list and applies the effects to ground/mobs
	 *
	 * @param spell    The spell data
	 * @param caster   The caster of the spell
	 * @param target   The specified target of the spell.  If this is not NULL, this is a forced target, and should be included with any other targets of the shape.  Otherwise the default spell shape logic should apply.
	 * @param world    The world the spell is being cast in
	 * @param x        The x-coordinate of the spell's effect
	 * @param y        The y-coordinate of the spell's effect
	 * @param z        The z-coordinate of the spell's effect
	 * @param side     The side the spell is applied on
	 * @param giveXP   This is passed along to be given back to the SpellHelper where needed.
	 * @param useCount The number of ticks the spell item has been in use for
	 * @return The result of the spell cast.
	 */
	public abstract SpellCastResult beginStackStage(SpellData spell, LivingEntity caster, LivingEntity target, World world, double x, double y, double z, Direction side, boolean giveXP, int useCount);

	
}

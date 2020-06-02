package am2.api.spell;

import java.util.EnumSet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class SpellModifier extends AbstractSpellPart{
	/**
	 * Returns a list of the aspects of a spell that this modifier can change.
	 *
	 * @return
	 */
	public abstract EnumSet<SpellModifiers> getAspectsModified();
	
	@Override
	public EnumSet<SpellModifiers> getModifiers() {
		return getAspectsModified();
	}
	

	public abstract float getModifier(SpellModifiers type, LivingEntity caster, Entity target, World world, CompoundNBT nbt);


	public abstract float getManaCostMultiplier();

	//todo params
}

package am2.api.spell;

import java.util.EnumSet;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.registries.IForgeRegistryEntry;


public abstract class AbstractSpellPart implements Comparable<AbstractSpellPart>, IForgeRegistryEntry {

	/**
	 * Supports :
	 *     ItemStacks
	 *     OreDict String
	 *     Essence Strings ("E:mask1|mask2" (* for any), num)
	 * @return
	 */
	public abstract Object[] getRecipe();
	
	public abstract void encodeBasicData(CompoundNBT tag, Object[] recipe);
	
	/**
	 * What modifier affect this spell part?
	 * 
	 * @return
	 */
	public abstract EnumSet<SpellModifiers> getModifiers();
	
	@Override
	public int compareTo(AbstractSpellPart o) {
		if (this instanceof SpellShape && o instanceof SpellShape)
			return 0;
		if (this instanceof SpellShape)
			return -1;
		if (o instanceof SpellShape)
			return 1;
		return 0;
	}
}

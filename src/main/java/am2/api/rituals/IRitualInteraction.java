package am2.api.rituals;

import am2.api.blocks.IMultiblock;
import net.minecraft.item.ItemStack;

public interface IRitualInteraction {
	
	public ItemStack[] getRitualReagents();
	public int getRitualReagentSearchRadius();
	public IMultiblock getRitualShape();
	public ItemStack getResult();
	
	public static class Wrapper {
		
		private final IRitualInteraction interaction;
		
		public Wrapper(IRitualInteraction interaction) {
			this.interaction = interaction;
		}
		
		public IRitualInteraction getRitualInteraction() {
			return interaction;
		}
		
	}
}

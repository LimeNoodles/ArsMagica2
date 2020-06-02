package am2.api.event;

import am2.api.spell.SpellData;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class SpellCastEvent extends Event {
	
	public SpellData spell;
	public float manaCost;
	public LivingEntity entityLiving;
	public float burnout;
	
	public SpellCastEvent(LivingEntity caster, SpellData spell, float manaCost) {
		this.spell = spell;
		this.manaCost = manaCost;
		this.entityLiving = caster;
	}
	
	public static class Pre extends SpellCastEvent {


		public Pre(LivingEntity caster, SpellData spell, float manaCost) {
			super(caster, spell, manaCost);
		}
		
	}
	
	public static class Post extends SpellCastEvent {

		public Post(LivingEntity caster, SpellData spell, float manaCost) {
			super(caster, spell, manaCost);
		}
		
	}

}

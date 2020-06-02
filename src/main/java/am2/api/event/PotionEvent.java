package am2.api.event;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class PotionEvent extends Event
{
	public Potion id;
	public int duration, amplifier;
	public boolean ambient, showParticules;
	public Effect effect;

	protected PotionEvent(Potion id, int duration, int amplifier, boolean ambient, boolean showParticules) {
		this.id = id;
		this.duration = duration;
		this.amplifier = amplifier;
		this.ambient = ambient;
		this.showParticules = showParticules;
		this.effect = new Effect(id, duration, amplifier, ambient, showParticules);
	}
	
	protected PotionEvent(Effect effect) {
		this.effect = effect;
		id = effect.getPotion();
		duration = effect.getDuration();
		amplifier = effect.getAmplifier();
		showParticules = effect.doesShowParticles();
		ambient = effect.getIsAmbient();
	}
	
	public Effect getEffect() {
		return effect;
	}
	
	public static class EventPotionAdded extends PotionEvent {

		public EventPotionAdded(Potion id, int duration, int amplifier, boolean ambient, boolean showParticules) {
			super(id, duration, amplifier, ambient, showParticules);
		}
		
		public EventPotionAdded(Effect effect) {
			super(effect);
		}

	}
	
	public static class EventPotionLoaded extends PotionEvent {
		
		private CompoundNBT compound;
		
		public EventPotionLoaded(Effects effect, CompoundNBT compound) {
			super(effect);
			this.compound = compound;
		}
		
		public CompoundNBT getCompound() {
			return compound;
		}
		
		public static Effect post(Effect effect, CompoundNBT compound) {
			EventPotionLoaded event = new EventPotionLoaded(effect, compound);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getEffect();
		}
	}

}

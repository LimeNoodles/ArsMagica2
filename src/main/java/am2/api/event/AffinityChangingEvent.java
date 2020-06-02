package am2.api.event;

import am2.api.affinity.Affinity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class AffinityChangingEvent extends Event {
	public final PlayerEntity player;
	public float amount;
	public final Affinity affinity;

	public AffinityChangingEvent(PlayerEntity player, Affinity affinity, float amt){
		this.player = player;
		this.amount = amt;
		this.affinity = affinity;
	}
}

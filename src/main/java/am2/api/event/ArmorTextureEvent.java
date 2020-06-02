package am2.api.event;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.Event;

public class ArmorTextureEvent extends Event {
	public final EquipmentSlotType slot;
	public final int renderIndex;

	public String texture;

	public ArmorTextureEvent(EquipmentSlotType slot2, int renderIndex){
		this.slot = slot2;
		this.renderIndex = renderIndex;
	}
}

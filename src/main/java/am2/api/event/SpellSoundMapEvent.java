package am2.api.event;

import am2.api.affinity.Affinity;
import am2.common.LogHelper;

import com.google.common.collect.ImmutableMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.Map;

public class SpellSoundMapEvent extends Event {

	private final ResourceLocation mapName;
	private final ImmutableMap.Builder<Affinity, SoundEvent> map;

	public SpellSoundMapEvent(ResourceLocation mapName) {
		this.mapName = mapName;
		this.map = ImmutableMap.builder();
	}

	public Map<Affinity, SoundEvent> getMap() {
		return this.map.build();
	}

	public ResourceLocation getMapName() {
		return this.mapName;
	}

	public void put(Affinity aff, SoundEvent sound) {
		if (aff == null)
			aff = Affinity.NONE;
		if (sound == null) {
			LogHelper.error("A mod tried to add a null sound to {0}", aff);
			return;
		}
		map.put(aff, sound);
	}
}

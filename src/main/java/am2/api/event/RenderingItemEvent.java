package am2.api.event;

import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class RenderingItemEvent extends Event {
	
	private final ItemStack stack;
	private final ItemCameraTransforms.TransformType cameraTransformType;
	private final LivingEntity entity;
	
	public RenderingItemEvent(ItemStack stack, ItemCameraTransforms.TransformType cameraTransformType, LivingEntity entity) {
		this.stack = stack;
		this.cameraTransformType = cameraTransformType;
		this.entity = entity;
	}
	
	public ItemStack getStack() {
		return stack;
	}
	
	public ItemCameraTransforms.TransformType getCameraTransformType() {
		return cameraTransformType;
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
}

package am2.client.entity.render;

import am2.client.models.ModelAirGuardianHoverball;
import am2.common.entity.EntityAirSled;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderAirSled extends LivingRenderer<EntityAirSled> {

	private static final ResourceLocation rLoc = new ResourceLocation("arsmagica2", "textures/mobs/bosses/air_guardian.png");

	public RenderAirSled(RenderManager manager){
		super(manager, new ModelAirGuardianHoverball(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAirSled entity){
		return rLoc;
	}

}

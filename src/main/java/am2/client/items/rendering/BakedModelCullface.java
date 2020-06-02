package am2.client.items.rendering;

import com.mojang.blaze3d.platform.GlStateManager;

import javafx.util.Pair;

import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.util.Direction;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import org.lwjgl.opengl.GL11;

public class BakedModelCullface implements IPerspectiveAwareModel{
	
	IBakedModel parent;
	private ImmutableMap<TransformType, TRSRTransformation> transforms;
	
	public BakedModelCullface(IBakedModel parent, ImmutableMap<TransformType, TRSRTransformation> transforms) {
		this.parent = parent;
		this.transforms = transforms;
	}
	
	@Override
	public List<BakedQuad> getQuads(IForgeBlockState state, Direction side, long rand) {
		GL11.glDisable(GL11.GL_CULL_FACE);
		return parent.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return parent.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return parent.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return parent.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return parent.getParticleTexture();
	}

	@SuppressWarnings("deprecation")
	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return parent.getItemCameraTransforms();
	}

	@Override
	public ItemOverrideList getOverrides() {
		GlStateManager.disableCull();
		return parent.getOverrides();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		return IPerspectiveAwareModel.MapWrapper.handlePerspective(this, transforms, cameraTransformType);
	}

}

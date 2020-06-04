package am2.client.items.rendering;

import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;

public class BakedModelCullface //todo implements IPerspectiveAwareModel
{
	
	IBakedModel parent;
	private ImmutableMap<TransformType, TRSRTransformation> transforms;
	
	public BakedModelCullface(IBakedModel parent, ImmutableMap<TransformType, TRSRTransformation> transforms) {
		this.parent = parent;
		this.transforms = transforms;
	}
	
	//todo @Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		GL11.glDisable(GL11.GL_CULL_FACE);
		return parent.getQuads(state, side, rand);
	}

	//todo @Override
	public boolean isAmbientOcclusion() {
		return parent.isAmbientOcclusion();
	}

	//todo @Override
	public boolean isGui3d() {
		return parent.isGui3d();
	}

	//todo @Override
	public boolean isBuiltInRenderer() {
		return parent.isBuiltInRenderer();
	}

	//todo @Override
	public TextureAtlasSprite getParticleTexture() {
		return parent.getParticleTexture();
	}

	@SuppressWarnings("deprecation")
	//todo @Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return parent.getItemCameraTransforms();
	}

	//todo @Override
	public ItemOverrideList getOverrides() {
		GlStateManager.disableCull();
		return parent.getOverrides();
	}

	//todo @Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		//todo return IPerspectiveAwareModel.MapWrapper.handlePerspective(this, transforms, cameraTransformType);
		return null;
	}

}

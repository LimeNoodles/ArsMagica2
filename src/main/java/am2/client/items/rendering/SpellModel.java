package am2.client.items.rendering;

import java.util.Collection;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import am2.common.utils.ModelUtils;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
//import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public class SpellModel //implements IModel
 {
	
	private ImmutableList<ResourceLocation> textures;

	public SpellModel(ImmutableList<ResourceLocation> textures) {
		this.textures = textures;
	}
	
	//todo @Override
	public Collection<ResourceLocation> getDependencies() {
		return ImmutableList.of();
	}

	//todo @Override
	public Collection<ResourceLocation> getTextures() {
		return textures;
	}

	/*@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		ImmutableMap<TransformType, TRSRTransformation> map = IPerspectiveAwareModel.MapWrapper.getTransforms(state);
		IBakedModel model = new ItemLayerModel(textures).bake(state, format, bakedTextureGetter);
		return new SpellBakedModel(model, map);
	}*/

	//todo @Override
	public IModelState getDefaultState() {
		return ModelUtils.DEFAULT_ITEM_STATE;
	}

}

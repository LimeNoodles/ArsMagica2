package am2.blocks.render;

import am2.blocks.tileentity.TileEntityKeystoneChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileKeystoneChestRenderer extends TileEntitySpecialRenderer<TileEntityKeystoneChest>{

	private ModelChest model;

	private ResourceLocation rLoc;

	public TileKeystoneChestRenderer(){
		model = new ModelChest();
		rLoc = new ResourceLocation("arsmagica2", "textures/blocks/custom/keystoneChest.png");
	}
	public void renderTileEntityAt(TileEntityKeystoneChest chest, double d, double d1, double d2, float var8, int destroyStage){
		int i = 2;

		if (chest.getWorld() != null){
			i = chest.getBlockMetadata() & 3;
		}
		int j = 0;

		if (i == 3){
			j = 90;
		}else if (i == 2){
			j = 180;
		}else if (i == 1){
			j = 270;
		}else if (i == 0){
			j = 0;
		}

		bindTexture(rLoc);
		GlStateManager.pushMatrix(); //start

		if (i == 2){
			GlStateManager.translate((float)d + 1f, (float)d1 + 1f, (float)d2); //size
		}else if (i == 1){
			GlStateManager.translate((float)d, (float)d1 + 1f, (float)d2); //size
		}else if (i == 0){
			GlStateManager.translate((float)d, (float)d1 + 1f, (float)d2 + 1f); //size
		}else{
			GlStateManager.translate((float)d + 1f, (float)d1 + 1f, (float)d2 + 1f); //size
		}
		GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GlStateManager.scale(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens

		float f1 = chest.getPrevLidAngle() + (chest.getLidAngle() - chest.getPrevLidAngle()) * var8;
		f1 = 1.0F - f1;
		f1 = 1.0F - f1 * f1 * f1;
		model.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
		model.renderAll();
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.enableBlend();
		GlStateManager.popMatrix(); //end
	}

}

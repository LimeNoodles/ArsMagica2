package am2.blocks.render;

import am2.blocks.tileentity.TileEntityAstralBarrier;
import am2.models.ModelAstralBarrier;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileAstralBarrierRenderer extends TileEntitySpecialRenderer<TileEntityAstralBarrier>{

	private ModelAstralBarrier model;
	private ResourceLocation rLoc;

	public TileAstralBarrierRenderer(){
		model = new ModelAstralBarrier();
		rLoc = new ResourceLocation("arsmagica2", "textures/blocks/custom/blockAstralBarrier.png");
	}

	public void renderTileEntityAt(TileEntityAstralBarrier tile, double d, double d1, double d2, float f, int destroyStage){
		int i = 0;

		if (destroyStage != -10 && tile.getWorld() != null){
			i = tile.getBlockMetadata();
		}
		int j = 0;

		if (i == 0){
			j = 0;
		}

		if (i == 1){
			j = 90;
		}

		if (i == 2){
			j = 180;
		}

		if (i == 3){
			j = 270;
		}

		bindTexture(rLoc);
		GlStateManager.pushMatrix(); //start
		GlStateManager.translate((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); //size
		GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GlStateManager.scale(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens
		model.renderModel(0.0625F); //renders and yes 0.0625 is a random number
		GlStateManager.popMatrix(); //end	
	}

}

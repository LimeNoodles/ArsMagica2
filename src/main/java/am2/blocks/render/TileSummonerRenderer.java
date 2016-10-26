package am2.blocks.render;

import am2.blocks.tileentity.TileEntitySummoner;
import am2.models.ModelSummoner;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileSummonerRenderer extends TileEntitySpecialRenderer<TileEntitySummoner>{
	private ResourceLocation rLoc;
	private ResourceLocation powered;
	private ModelSummoner model;

	public TileSummonerRenderer(){
		model = new ModelSummoner();
		rLoc = new ResourceLocation("arsmagica2", "textures/blocks/custom/blockSummoner.png");
		powered = new ResourceLocation("arsmagica2", "textures/blocks/custom/essenceConduit.png");
	}
	
	@Override
	public void renderTileEntityAt(TileEntitySummoner tile, double x, double y, double z, float partialTicks, int destroyStage) {
		int i = 2;
		int y2 = 0;

		if (destroyStage != -10){
			i = tile.getBlockMetadata() & 3;
			y2 = (tile.getBlockMetadata() & 12) >> 2;
		}
		int j = (i + 1) * 90;
		int n = 0;
		if (y2 == 1){
			n = 90;
		}else if (y2 == 2){
			n = -90;
		}

		bindTexture(rLoc);
		GlStateManager.pushMatrix(); //start
		GlStateManager.translate((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f); //size
		GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GlStateManager.translate(0, -1f, 0);
		GlStateManager.rotate(n, 0.0f, 0.0f, 1.0f);
		GlStateManager.translate(0, 1f, 0);
		GlStateManager.scale(1.0F, -1F, -1F);
		model.renderModel(0.0625F);
		bindTexture(powered);
		model.renderCrystal(tile, 0.0625f);
		GlStateManager.popMatrix(); //end
	}

}

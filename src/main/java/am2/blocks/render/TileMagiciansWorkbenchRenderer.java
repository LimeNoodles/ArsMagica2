package am2.blocks.render;


import am2.blocks.tileentity.TileEntityMagiciansWorkbench;
import am2.models.ModelMagiciansWorkbench;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileMagiciansWorkbenchRenderer extends TileEntitySpecialRenderer<TileEntityMagiciansWorkbench>{

	private final ResourceLocation rLoc;

	public TileMagiciansWorkbenchRenderer() {
		model = new ModelMagiciansWorkbench();
		rLoc = new ResourceLocation("arsmagica2", "textures/blocks/custom/magiciansWorkbench.png");
	}

	@Override
	public void renderTileEntityAt(TileEntityMagiciansWorkbench tile, double x, double y, double z, float f, int destroyStage){
		int i = 3;

		if (destroyStage != -10 && tile.getWorld() != null){
			i = tile.getBlockMetadata();
		}
		int j = i * -90;

		bindTexture(rLoc);
		GlStateManager.pushMatrix(); //start
		GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F); //size
		GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GlStateManager.scale(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens
		model.drawerOffset = tile.getDrawerOffset();
		model.renderModel(i);
		GlStateManager.popMatrix(); //end

	}

	private final ModelMagiciansWorkbench model;

}

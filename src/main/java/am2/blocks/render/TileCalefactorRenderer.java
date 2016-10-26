package am2.blocks.render;

import am2.blocks.tileentity.TileEntityCalefactor;
import am2.gui.AMGuiHelper;
import am2.models.ModelCalefactor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TileCalefactorRenderer extends TileEntitySpecialRenderer<TileEntityCalefactor>{

	private final ResourceLocation rLoc;
	RenderEntityItem renderItem;

	public TileCalefactorRenderer(){
		model = new ModelCalefactor();

		rLoc = new ResourceLocation("arsmagica2", "textures/blocks/custom/blockCalefactor.png");
	}

	@Override
	public void renderTileEntityAt(TileEntityCalefactor tile, double x, double y, double z, float partialTicks, int destroyStage) {
		if (renderItem == null)
			renderItem = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem());
		int i = 0;

		if (tile.getWorld() != null){
			i = tile.getBlockMetadata();
		}

		GlStateManager.pushMatrix(); //start

		int meta = i;

		double offsetX, offsetY, offsetZ;

		offsetX = 0;
		offsetY = 0;
		offsetZ = 0;


		switch (meta){
		case 1:
			GlStateManager.translate((float)x + 0.5f, (float)y - 0.5F, (float)z + 0.5F); //size
			GlStateManager.rotate(180, 1.0F, 0.0F, 0.0F); //rotate based on metadata
			offsetX = 0.5;
			offsetY = -1.5;
			offsetZ = 0.5;
			break;
		case 0:
		case 2:
			GlStateManager.translate((float)x + 0.5f, (float)y + 1.5F, (float)z + 0.5F); //size
			offsetX = 0.5;
			offsetY = 0.0;
			offsetZ = 0.5;
			break;
		case 3:
			GlStateManager.translate((float)x + 0.5f, (float)y + 0.5F, (float)z - 0.5F); //size
			GlStateManager.rotate(270, 1.0F, 0.0F, 0.0F); //rotate based on metadata
			offsetX = 0.5;
			offsetY = -0.8;
			offsetZ = -0.2;
			break;
		case 4:
			GlStateManager.translate((float)x + 0.5f, (float)y + 0.5F, (float)z + 1.5F); //size
			GlStateManager.rotate(90, 1.0F, 0.0F, 0.0F); //rotate based on metadata
			offsetX = 0.5;
			offsetY = -0.8;
			offsetZ = 1.2;
			break;
		case 5:
			GlStateManager.translate((float)x - 0.5f, (float)y + 0.5F, (float)z + 0.5F); //size
			GlStateManager.rotate(90, 0.0F, 0.0F, 1.0F); //rotate based on metadata
			offsetX = -0.2;
			offsetY = -0.8;
			offsetZ = 0.5;
			break;
		case 6:
			GlStateManager.translate((float)x + 1.5F, (float)y + 0.5F, (float)z + 0.5F); //size
			GlStateManager.rotate(270, 0.0F, 0.0F, 1.0F); //rotate based on metadata
			offsetX = 1.2;
			offsetY = -0.8;
			offsetZ = 0.5;
			break;
		}


		GlStateManager.scale(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens
		bindTexture(rLoc);

		if (destroyStage == -10)
			GlStateManager.translate(0, 0.2f, 0);

		model.renderModel(tile.getRotationX(), tile.getRotationY(), tile.getRotationZ(), 0.0625F); //renders and yes 0.0625 is a random number
		GlStateManager.popMatrix(); //end

		//render item being cooked, if any
		ItemStack item = tile.getItemBeingCooked();
		if (item != null){
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 1f, 0);
			RenderItemAtCoords(item, x + offsetX, y + offsetY, z + offsetZ, partialTicks);
			GlStateManager.popMatrix();
		}

	}

	private void RenderItemAtCoords(ItemStack item, double x, double y, double z, float partialTicks){
		AMGuiHelper.instance.dummyItem.setEntityItemStack(item);
		renderItem.doRender(AMGuiHelper.instance.dummyItem, x, y, z, AMGuiHelper.instance.dummyItem.rotationYaw, partialTicks);
	}

	private final ModelCalefactor model;
}

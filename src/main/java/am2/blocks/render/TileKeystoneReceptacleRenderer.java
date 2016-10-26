package am2.blocks.render;

import org.lwjgl.opengl.GL11;

import am2.blocks.tileentity.TileEntityKeystoneRecepticle;
import am2.entity.render.RenderManaVortex;
import am2.gui.AMGuiIcons;
import am2.models.ModelKeystoneRecepticle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileKeystoneReceptacleRenderer extends TileEntitySpecialRenderer<TileEntityKeystoneRecepticle>{

	private final ModelKeystoneRecepticle model;
	private static final ResourceLocation portal = TextureMap.LOCATION_BLOCKS_TEXTURE;
	private final ResourceLocation rLoc;

	public TileKeystoneReceptacleRenderer(){
		model = new ModelKeystoneRecepticle();

		rLoc = new ResourceLocation("arsmagica2", "textures/blocks/custom/KeystoneReceptacle.png");
	}

	public void renderTileEntityAt(TileEntityKeystoneRecepticle tile, double x, double y, double z, float partialTicks,int destroyStage){
		int i = 3;

		if (tile.getWorld() != null){
			i = tile.getBlockMetadata();
		}
		int j = 0;

		if (i == 0 || i == 4){
			j = 0;
		}

		if (i == 1){
			j = 270;
		}

		if (i == 2){
			j = 180;
		}

		if (i == 3){
			j = 90;
		}

		bindTexture(rLoc);
		GlStateManager.pushMatrix(); //start
		GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F); //size
		GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GlStateManager.scale(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens
		model.renderModel(0.0625F); //renders and yes 0.0625 is a random number
		GlStateManager.popMatrix(); //end	

		if (tile.isActive()){
			bindTexture(portal);

			GlStateManager.pushMatrix();

			GlStateManager.pushAttrib();

			GlStateManager.translate((float)x + 0.5f, (float)y - 2.5F, (float)z + 0.5f);
			GlStateManager.scale(4, 4, 4);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
			GlStateManager.depthMask(false);
			Tessellator tessellator = Tessellator.getInstance();

			GlStateManager.rotate(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata

			//apply portal colors here
			GlStateManager.color(1, 1, 1, 1);

			renderArsMagicaEffect(tessellator);

			GlStateManager.disableBlend();

			GlStateManager.popAttrib();
			GlStateManager.popMatrix();
		}
	}

	private void renderArsMagicaEffect(Tessellator tessellator){
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		renderSprite(tessellator);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	private void renderSprite(Tessellator tessellator){

		TextureAtlasSprite IIcon = AMGuiIcons.gatewayPortal;

		float min_u = IIcon.getMinU();
		float max_u = IIcon.getMaxU();
		float min_v = IIcon.getMinV();
		float max_v = IIcon.getMaxV();

		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.25F;
		int i = 0xF00F0;
        int j = i >> 16 & 65535;
        int k = i & 65535;
		tessellator.getBuffer().begin(7, RenderManaVortex.POS_TEX_LIGHTMAP);
		tessellator.getBuffer().pos(0.0F - f5, 0.0F - f6, 0.0D).tex(min_u, max_v).lightmap(j, k).endVertex();
		tessellator.getBuffer().pos(f4 - f5, 0.0F - f6, 0.0D).tex(max_u, max_v).lightmap(j, k).endVertex();
		tessellator.getBuffer().pos(f4 - f5, f4 - f6, 0.0D).tex(max_u, min_v).lightmap(j, k).endVertex();
		tessellator.getBuffer().pos(0.0F - f5, f4 - f6, 0.0D).tex(min_u, min_v).lightmap(j, k).endVertex();
		tessellator.draw();

		GlStateManager.rotate(180, 0, 1, 0);

		tessellator.getBuffer().begin(7, RenderManaVortex.POS_TEX_LIGHTMAP);
		tessellator.getBuffer().pos(0.0F - f5, 0.0F - f6, 0.0D).tex(min_u, max_v).lightmap(j, k).endVertex();
		tessellator.getBuffer().pos(f4 - f5, 0.0F - f6, 0.0D).tex(max_u, max_v).lightmap(j, k).endVertex();
		tessellator.getBuffer().pos(f4 - f5, f4 - f6, 0.0D).tex(max_u, min_v).lightmap(j, k).endVertex();
		tessellator.getBuffer().pos(0.0F - f5, f4 - f6, 0.0D).tex(min_u, min_v).lightmap(j, k).endVertex();
		tessellator.draw();
	}

}

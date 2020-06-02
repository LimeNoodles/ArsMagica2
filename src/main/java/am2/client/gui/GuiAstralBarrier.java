package am2.client.gui;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

import am2.common.blocks.tileentity.TileEntityAstralBarrier;
import am2.common.container.ContainerAstralBarrier;
import net.minecraft.util.ResourceLocation;

public class GuiAstralBarrier extends ContainerScreen
{
	Button radiusButton;

	private static final ResourceLocation background = new ResourceLocation("arsmagica2", "textures/gui/astralBarrierGui.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j){
		mc.renderEngine.bindTexture(background);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}

	public GuiAstralBarrier(PlayerInventory inventoryplayer, TileEntityAstralBarrier astralBarrierEntity){
		super(new ContainerAstralBarrier(inventoryplayer, astralBarrierEntity));
		xSize = 176;
		ySize = 180;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
	}
}

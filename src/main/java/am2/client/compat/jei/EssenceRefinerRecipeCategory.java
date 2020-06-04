package am2.client.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EssenceRefinerRecipeCategory implements IRecipeCategory<EssenceRefinerRecipeWrapper> {

	IDrawableStatic background;
	
	public EssenceRefinerRecipeCategory(IGuiHelper helpers) {
		this.background = helpers.createDrawable(new ResourceLocation("arsmagica2:textures/gui/essenceExtractorGui.png"), 3, 25, 170, 114);
	}

	@Override
	public String getTitle() {
		return I18n.format(getUid());
	}

	@Override
	public String getModName() {
		//todo return null;
		return null;
	}

	@Override
	public String getUid() {
		return "am2.essence_refiner";
	}
	
	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, EssenceRefinerRecipeWrapper recipeWrapper, IIngredients ingredients) {

	}

	//todo @Override
	public void drawAnimations(Minecraft minecraft) {
		
	}

	//todo move this above @Override
	public void setRecipe(IRecipeLayout recipeLayout, EssenceRefinerRecipeWrapper recipeWrapper) {
		recipeLayout.getItemStacks().init(0, true, 76, 16);
		recipeLayout.getItemStacks().init(1, true, 44, 48);
		recipeLayout.getItemStacks().init(2, true, 76, 48);
		recipeLayout.getItemStacks().init(3, true, 108, 48);
		recipeLayout.getItemStacks().init(4, true, 76, 81);
		recipeLayout.getItemStacks().init(5, false, 139, 84);
		
		recipeLayout.getItemStacks().set(0, (ItemStack)recipeWrapper.getInputs().get(0));
		recipeLayout.getItemStacks().set(1, (ItemStack)recipeWrapper.getInputs().get(1));
		recipeLayout.getItemStacks().set(2, (ItemStack)recipeWrapper.getInputs().get(2));
		recipeLayout.getItemStacks().set(3, (ItemStack)recipeWrapper.getInputs().get(3));
		recipeLayout.getItemStacks().set(4, (ItemStack)recipeWrapper.getInputs().get(4));
		recipeLayout.getItemStacks().set(5, (ItemStack)recipeWrapper.getOutputs().get(0));
	}

}

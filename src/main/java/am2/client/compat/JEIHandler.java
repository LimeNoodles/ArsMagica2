package am2.client.compat;

import am2.api.recipes.RecipesEssenceRefiner;
import am2.client.compat.jei.EssenceRefinerRecipeCategory;
import am2.client.compat.jei.EssenceRefinerRecipeHandler;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Resource;


@JeiPlugin
public class JEIHandler implements IModPlugin{

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeCategories(new EssenceRefinerRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeHandlers(new EssenceRefinerRecipeHandler());
		registry.addRecipes(RecipesEssenceRefiner.essenceRefinement().getAllRecipes());
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		new ResourceLocation()
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
		
	}

}

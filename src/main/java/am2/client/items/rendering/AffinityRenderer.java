package am2.client.items.rendering;

import am2.api.ArsMagicaAPI;
import am2.api.affinity.Affinity;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AffinityRenderer implements ItemMeshDefinition{
	
	private final String prefix;
	
	public AffinityRenderer(String prefix) {
		this.prefix = prefix;
	}
	
	public AffinityRenderer addModels(Item item) {
		//todo for (Affinity aff : ArsMagicaAPI.getAffinityRegistry().getValues())
			//ModelBakery.registerItemVariants(item, new ModelResourceLocation(new ResourceLocation(aff.getRegistryName().getResourceDomain(), prefix + aff.getRegistryName().getResourcePath()), "inventory"));
		return this;
	}
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		//todo Affinity aff = ArsMagicaAPI.getAffinityRegistry().getObjectById(stack.getItemDamage());
	//todo 	return new ModelResourceLocation(new ResourceLocation(aff.getRegistryName().getResourceDomain(), prefix + aff.getRegistryName().getResourcePath()), "inventory");
		return null;
	}
	
}

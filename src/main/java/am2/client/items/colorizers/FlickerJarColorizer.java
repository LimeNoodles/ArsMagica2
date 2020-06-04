package am2.client.items.colorizers;

import am2.api.ArsMagicaAPI;
import am2.api.affinity.Affinity;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class FlickerJarColorizer implements IItemColor{

	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		if (tintIndex == 0)
			return 0xffffff;
		int meta = stack.getItemDamage();
		//todo Affinity aff = ArsMagicaAPI.getAffinityRegistry().getObjectById(meta);
		//todo return aff.getColor();
		return 0;
	}

}

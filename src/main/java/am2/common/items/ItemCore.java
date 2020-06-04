package am2.common.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCore extends ItemArsMagica {
	
	public static final int META_BASE_CORE = 0;
	public static final int META_HIGH_CORE = 1;
	public static final int META_PURE = 2;
	
	//todo @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		subItems.add(new ItemStack(this, 1, META_BASE_CORE));
		subItems.add(new ItemStack(this, 1, META_HIGH_CORE));
		subItems.add(new ItemStack(this, 1, META_PURE));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		String name = I18n.format("item.arsmagica2:core." + (stack.getItemDamage() == META_BASE_CORE ? "base" : (stack.getItemDamage() == META_HIGH_CORE ? "high" : "pure")) + ".name");
		return name;
	}
	
}

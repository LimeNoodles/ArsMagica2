package am2.common.items;

import java.util.List;

import am2.common.defs.ItemDefs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArcaneGuardianSpellbook extends ItemSpellBook {

	public ItemArcaneGuardianSpellbook(){
		super();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4){
		par3List.add(I18n.format("am2.tooltip.arcanespellbook"));
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

	@Override
	public int getItemEnchantability(){
		return 0;
	}

	@Override
	public boolean isBookEnchantable(ItemStack bookStack, ItemStack enchantBook){
		return false;
	}

	//todo @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List){
		par3List.add(ItemDefs.arcaneSpellBookEnchanted.copy());
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack){
		return EnumRarity.EPIC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack par1ItemStack){
		ItemStack activeSpell = GetActiveItemStack(par1ItemStack);
		if (activeSpell != null){
			return String.format("\2477%s \2477(" + activeSpell.getDisplayName() + "\2477)", I18n.format("item.arsmagica2:arcaneSpellBook.name"));
		}
		return I18n.format("item.arsmagica2:arcane_spellbook.name");
	}
}

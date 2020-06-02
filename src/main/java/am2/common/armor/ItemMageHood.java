package am2.common.armor;

import am2.common.armor.infusions.GenericImbuement;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class ItemMageHood extends AMArmor{

	public ItemMageHood(ArmorMaterial inheritFrom, ArsMagicaArmorMaterial enumarmormaterial, int par3, EquipmentSlotType par4){
		super(inheritFrom, enumarmormaterial, par3, par4);
	}

	public boolean showNodes(ItemStack stack, LivingEntity player){
		return ArmorHelper.isInfusionPreset(stack, GenericImbuement.thaumcraftNodeReveal);
	}

	public boolean showIngamePopups(ItemStack stack, LivingEntity player){
		return ArmorHelper.isInfusionPreset(stack, GenericImbuement.thaumcraftNodeReveal);
	}
}

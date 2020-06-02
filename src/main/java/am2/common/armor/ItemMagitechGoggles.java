package am2.common.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class ItemMagitechGoggles extends AMArmor{

	public ItemMagitechGoggles(ArmorMaterial inheritFrom, int renderIndex){
		super(inheritFrom, ArsMagicaArmorMaterial.UNIQUE, renderIndex, EquipmentSlotType.HEAD);
	}
	@Override
	public int getArmorDisplay(PlayerEntity player, ItemStack armor, int slot){
		return 2;
	}

	@Override
	public int GetDamageReduction(){
		return 2;
	}
}

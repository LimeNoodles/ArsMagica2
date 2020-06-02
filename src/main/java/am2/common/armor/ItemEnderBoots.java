package am2.common.armor;

import java.util.List;

import am2.common.extensions.EntityExtension;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnderBoots extends AMArmor{

	public ItemEnderBoots(ArmorMaterial inheritFrom, ArsMagicaArmorMaterial enumarmormaterial, int par3, EquipmentSlotType par4){
		super(inheritFrom, enumarmormaterial, par3, par4);
	}

	@Override
	public int getArmorDisplay(PlayerEntity player, ItemStack armor, int slot){
		return 0;
	}

	@Override
	public void onArmorTick(World world, PlayerEntity player, ItemStack itemStack){
		if (player.getPosY() >= world.getActualHeight() && EntityExtension.For(player).getIsFlipped())
			EntityExtension.For(player).setInverted(!EntityExtension.For(player).isInverted());
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, PlayerEntity par2EntityPlayer, List<String> par3List, boolean par4){
		par3List.add(I18n.format("am2.tooltip.ender_boots"));
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}
}

package am2.common.armor;

import java.util.List;

import am2.client.utils.ModelLibrary;
import am2.common.defs.ItemDefs;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEarthGuardianArmor extends AMArmor{

	public ItemEarthGuardianArmor(ArmorMaterial inheritFrom, ArsMagicaArmorMaterial enumarmormaterial, int par3, EquipmentSlotType par4){
		super(inheritFrom, enumarmormaterial, par3, par4);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel _default) {
		return ModelLibrary.instance.earthArmor;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type){
		return "arsmagica2:textures/mobs/bosses/earth_guardian.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, PlayerEntity par2PlayerEntity, List<String> par3List, boolean par4){
		par3List.add(I18n.format("am2.tooltip.earth_armor"));
	}

	@Override
	public int getArmorDisplay(PlayerEntity player, ItemStack armor, int slot){
		return 16;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List){
		par3List.add(ItemDefs.earthArmorEnchanted.copy());
	}
}

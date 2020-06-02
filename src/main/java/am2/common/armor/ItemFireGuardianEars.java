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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFireGuardianEars extends AMArmor{

	public ItemFireGuardianEars(ArmorMaterial inheritFrom, ArsMagicaArmorMaterial enumarmormaterial, int par3, EquipmentSlotType par4){
		super(inheritFrom, enumarmormaterial, par3, par4);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel _default) {
		return ModelLibrary.instance.fireEars;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getArmorDisplay(PlayerEntity player, ItemStack armor, int slot){
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, PlayerEntity par2EntityPlayer, List<String> par3List, boolean par4){
		par3List.add(I18n.format("am2.tooltip.fire_ears"));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type){
		return "arsmagica2:textures/mobs/bosses/fire_guardian.png";
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List){
		par3List.add(ItemDefs.fireEarsEnchanted.copy());
	}

	@Override
	public void onArmorTick(World world, PlayerEntity plr, ItemStack stack) {
		super.onArmorTick(world, plr, stack);
		Potion fireResist = Potion.getPotionFromResourceLocation(new ResourceLocation("fire_resistance").toString());
		if (!plr.isPotionActive(fireResist))
			plr.addPotionEffect(new PotionEffect(fireResist));
	}
}

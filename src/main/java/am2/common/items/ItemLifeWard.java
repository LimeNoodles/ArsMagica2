package am2.common.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLifeWard extends ItemArsMagica{

	public ItemLifeWard(){
		super();
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		if (par4 < 9 && par3Entity.ticksExisted % 80 == 0 && par3Entity instanceof EntityLivingBase){
			float abs = ((EntityLivingBase)par3Entity).getAbsorptionAmount();
			if (abs < 20){
				abs++;
				((EntityLivingBase)par3Entity).setAbsorptionAmount(abs);
			}
		}
	}

	//todo @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4){
		par3List.add(I18n.format("am2.tooltip.life_ward"));
		par3List.add(I18n.format("am2.tooltip.life_ward2"));
		//todo super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

}

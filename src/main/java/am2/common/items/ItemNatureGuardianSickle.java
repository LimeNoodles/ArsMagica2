package am2.common.items;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import am2.ArsMagica2;
import am2.common.defs.ItemDefs;
import am2.common.entity.EntityThrownSickle;
import am2.common.extensions.EntityExtension;
import am2.common.utils.DummyEntityPlayer;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNatureGuardianSickle extends ItemArsMagica{

	public ItemNatureGuardianSickle(){
		super();
		setMaxStackSize(1);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot){
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
		if (slot.equals(EntityEquipmentSlot.MAINHAND)) {
			//todo multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 9, 0));
			//todo multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3, 0));
		}
		return multimap;
	}

	//todo @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4){
		par3List.add(I18n.format("am2.tooltip.nature_scythe"));
		//todo super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		int radius = 1;
		for (int i = -radius; i <= radius; ++i){
			for (int j = -radius; j <= radius; ++j){
				for (int k = -radius; k <= radius; ++k){

					if (EntityExtension.For(entityLiving).getCurrentMana() < 5f){
						if (worldIn.isRemote)
							ArsMagica2.proxy.flashManaBar();
						return false;
					}

					IBlockState nextBlock = worldIn.getBlockState(pos.add(i, j, k));
					if (nextBlock == null) continue;
					if (nextBlock.getBlock() instanceof BlockLeaves){
						if (ForgeEventFactory.doPlayerHarvestCheck(DummyEntityPlayer.fromEntityLiving(entityLiving), nextBlock, true))
							if (!worldIn.isRemote)
								worldIn.destroyBlock(pos.add(i, j, k), true);
						EntityExtension.For(entityLiving).deductMana(5f);
					}
				}
			}
		}

		return false;
	}

	//todo @Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand){
		if (flingSickle(par1ItemStack, par2World, par3EntityPlayer)){
			par3EntityPlayer.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, null);//inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, null);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
	}

	public boolean flingSickle(ItemStack stack, World world, EntityPlayer player){
		if (!EntityExtension.For(player).hasEnoughMana(250) && !player.capabilities.isCreativeMode){
			if (world.isRemote)
				ArsMagica2.proxy.flashManaBar();
			return false;
		}
		if (!world.isRemote){
			EntityThrownSickle projectile = new EntityThrownSickle(world, player, 1.25f);
			projectile.setSickleNBT(stack);
			projectile.setThrowingEntity(player);
			projectile.setProjectileSpeed(2.0);
			//projectile.setInMotion(1.25);
			world.spawnEntity(projectile);
			EntityExtension.For(player).deductMana(250f);
		}
		return true;
	}

	//todo @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List){
		par3List.add(ItemDefs.natureScytheEnchanted.copy());
	}
}

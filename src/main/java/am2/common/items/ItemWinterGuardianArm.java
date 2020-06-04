package am2.common.items;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import am2.ArsMagica2;
import am2.client.particles.AMParticle;
import am2.common.buffs.BuffEffectFrostSlowed;
import am2.common.defs.ItemDefs;
import am2.common.entity.EntityWinterGuardianArm;
import am2.common.extensions.EntityExtension;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
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
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWinterGuardianArm extends ItemArsMagica{

	public ItemWinterGuardianArm(){
		super();
		setMaxStackSize(1);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot){
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
		if (slot.equals(EntityEquipmentSlot.MAINHAND)) {
			//todo multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 6, 0));
			//todo multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1, 0));
		}
		return multimap;
	}

	//todo @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4){
		par3List.add(I18n.format("am2.tooltip.winter_arm"));
		//todo super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
		if (entity instanceof EntityLivingBase){
			((EntityLivingBase)entity).addPotionEffect(new BuffEffectFrostSlowed(60, 3));
			if (player.world.isRemote){
				for (int i = 0; i < 5; ++i){
					AMParticle particle = (AMParticle)ArsMagica2.proxy.particleManager.spawn(player.world, "snowflakes", entity.posX + 0.5, entity.posY + 0.5, entity.posZ + 0.5);
					if (particle != null){
						particle.addRandomOffset(1, 0.5, 1);
						particle.addVelocity(player.world.rand.nextDouble() * 0.2 - 0.1, 0.3, player.world.rand.nextDouble() * 0.2 - 0.1);
						particle.setAffectedByGravity();
						particle.setDontRequireControllers();
						particle.setMaxAge(10);
						particle.setParticleScale(0.1f);
					}
				}
			}
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	//todo @Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand){
		if (flingArm(par1ItemStack, par2World, par3EntityPlayer)){
			par3EntityPlayer.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, null);//inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, null);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, par1ItemStack);
	}

	public boolean flingArm(ItemStack stack, World world, EntityPlayer player){
		if (!EntityExtension.For(player).hasEnoughMana(250) && !player.capabilities.isCreativeMode){
			if (world.isRemote)
				ArsMagica2.proxy.flashManaBar();
			return false;
		}
		if (!world.isRemote){
			EntityWinterGuardianArm projectile = new EntityWinterGuardianArm(world, player, 1.25f);
			projectile.setThrowingEntity(player);
			projectile.setProjectileSpeed(2.0);
			world.spawnEntity(projectile);
		}
		EntityExtension.For(player).deductMana(250f);
		return true;
	}

	//todo @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List){
		par3List.add(ItemDefs.winterArmEnchanted.copy());
	}
}

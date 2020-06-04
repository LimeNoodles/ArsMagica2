package am2.common.items;

import am2.api.IBoundItem;
import am2.api.extensions.ISpellCaster;
import am2.common.defs.ItemDefs;
import am2.common.spell.SpellCaster;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBoundAxe extends ItemAxe implements IBoundItem {

	public ItemBoundAxe() {
		super(ItemDefs.BOUND, 8, -3);
		this.maxStackSize = 1;
		this.setMaxDamage(0);
		this.setCreativeTab(null);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!stack.hasTagCompound())
			return true;
		ItemStack copiedStack = stack.copy();
		ISpellCaster caster = stack.copy().getCapability(SpellCaster.INSTANCE, null);
		if (caster != null)
			caster.createSpellData(copiedStack).execute(worldIn, entityLiving, null, pos.getX(), pos.getY(), pos.getZ(), null);
		return true;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		//todo item.setItem(ItemDefs.spell);
		return false;
	}

	@Override
	public float maintainCost(EntityPlayer player, ItemStack stack) {
		return normalMaintain;
	}

	public ItemBoundAxe registerAndName(String name) {
		this.setUnlocalizedName(new ResourceLocation("arsmagica2", name).toString());
	//tddo 	GameRegistry.register(this, new ResourceLocation("arsmagica2", name));
		return this;
	}

}

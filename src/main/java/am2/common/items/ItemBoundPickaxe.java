package am2.common.items;

import am2.api.IBoundItem;
import am2.api.extensions.ISpellCaster;
import am2.common.defs.ItemDefs;
import am2.common.spell.SpellCaster;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBoundPickaxe extends PickaxeItem implements IBoundItem
{

	public ItemBoundPickaxe()
	{
		super(ItemDefs.BOUND);
		this.maxStackSize = 1;
		this.setMaxDamage(0);
		this.setCreativeTab(null);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IForgeBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
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
	public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
		item.setItem(ItemDefs.spell);
		return false;
	}

	@Override
	public float maintainCost(PlayerEntity player, ItemStack stack) {
		return normalMaintain;
	}

	public PickaxeItem registerAndName(String name) {
		this.setUnlocalizedName(new ResourceLocation("arsmagica2", name).toString());
		GameRegistry.register(this, new ResourceLocation("arsmagica2", name));
		return this;
	}

}

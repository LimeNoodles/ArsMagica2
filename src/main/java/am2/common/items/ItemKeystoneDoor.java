package am2.common.items;

import java.util.List;

import am2.common.defs.BlockDefs;
import am2.common.defs.CreativeTabsDefs;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKeystoneDoor extends Item{

	public static final int KEYSTONE_DOOR = 0;
	public static final int SPELL_SEALED_DOOR = 1;

	public ItemKeystoneDoor(){
		super();
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabsDefs.tabAM2Items);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack){
		switch (stack.getItemDamage()){
		case KEYSTONE_DOOR:
			return I18n.format("item.arsmagica2:keystone_door.name");
		case SPELL_SEALED_DOOR:
			return I18n.format("item.arsmagica2:spell_sealed_door.name");
		default:
			//todo return I18n.format("item.arsmagica2:unknown.name");
			return null;
		}
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	//todo @Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		if (facing != EnumFacing.UP){
			return EnumActionResult.PASS;
		}else{
			pos = pos.up();
			Block block;
			if (stack.getItemDamage() == KEYSTONE_DOOR)
				block = BlockDefs.keystoneDoor;
			else
				block = BlockDefs.spellSealedDoor;

			if (playerIn.canPlayerEdit(pos, facing, stack) && playerIn.canPlayerEdit(pos.up(), facing, stack)){
				if (!block.canPlaceBlockAt(worldIn, pos)){
					return EnumActionResult.FAIL;
				}else{
					EnumFacing enumfacing = EnumFacing.fromAngle((double)playerIn.rotationYaw);
					int i = enumfacing.getFrontOffsetX();
	                int j = enumfacing.getFrontOffsetZ();
					boolean flag = i < 0 && hitZ < 0.5F || i > 0 && hitZ > 0.5F || j < 0 && hitX > 0.5F || j > 0 && hitX < 0.5F;
					ItemDoor.placeDoor(worldIn, pos, enumfacing, block, flag);
					//todo --stack.stackSize;
					return EnumActionResult.SUCCESS;
				}
			}else{
				return EnumActionResult.FAIL;
			}
		}
	}

	//todo @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list){
		list.add(new ItemStack(this, 1, KEYSTONE_DOOR));
		list.add(new ItemStack(this, 1, SPELL_SEALED_DOOR));
	}
	
	public Item registerAndName(String name) {
		this.setUnlocalizedName(name);
		//todo GameRegistry.register(this, new ResourceLocation("arsmagica2", name));
		return this;
	}
}

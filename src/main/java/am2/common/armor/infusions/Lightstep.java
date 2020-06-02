package am2.common.armor.infusions;

import java.util.EnumSet;

import am2.api.items.armor.ArmorImbuement;
import am2.api.items.armor.ImbuementApplicationTypes;
import am2.api.items.armor.ImbuementTiers;
import am2.common.defs.BlockDefs;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Lightstep extends ArmorImbuement{

	@Override
	public String getID(){
		return "lightstep";
	}

	@Override
	public ImbuementTiers getTier(){
		return ImbuementTiers.FOURTH;
	}

	@Override
	public EnumSet<ImbuementApplicationTypes> getApplicationTypes(){
		return EnumSet.of(ImbuementApplicationTypes.ON_TICK);
	}

	@Override
	public boolean applyEffect(PlayerEntity player, World world, ItemStack stack, ImbuementApplicationTypes matchedType, Object... params){

		if (world.isRemote)
			return false;

		if (player.isSneaking())
			return false;
		BlockPos pos = player.getPosition().up();
		int ll = world.getLightFor(EnumSkyBlock.BLOCK, pos);
		if (ll < 7 && world.isAirBlock(pos)){
			world.setBlockState(pos, BlockDefs.blockMageLight.getDefaultState(), 2);
			return true;
		}
		return false;
	}

	@Override
	public EquipmentSlotType[] getValidSlots(){
		return new EquipmentSlotType[]{EquipmentSlotType.FEET};
	}

	@Override
	public boolean canApplyOnCooldown(){
		return true;
	}

	@Override
	public int getCooldown(){
		return 0;
	}

	@Override
	public int getArmorDamage(){
		return 1;
	}
}

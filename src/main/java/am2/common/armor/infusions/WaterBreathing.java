package am2.common.armor.infusions;

import java.util.EnumSet;

import am2.api.items.armor.ArmorImbuement;
import am2.api.items.armor.ImbuementApplicationTypes;
import am2.api.items.armor.ImbuementTiers;
import am2.common.buffs.BuffEffectWaterBreathing;
import am2.common.defs.PotionEffectsDefs;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WaterBreathing extends ArmorImbuement{

	@Override
	public String getID(){
		return "wtrbrth";
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

		if (player.getAir() < 10){
			if (!player.isPotionActive(PotionEffectsDefs.WATER_BREATHING)){
				BuffEffectWaterBreathing wb = new BuffEffectWaterBreathing(200, 0);
				player.addPotionEffect(wb);
				return true;
			}
		}
		return false;
	}

	@Override
	public EquipmentSlotType[] getValidSlots(){
		return new EquipmentSlotType[]{EquipmentSlotType.HEAD};
	}

	@Override
	public boolean canApplyOnCooldown(){
		return false;
	}

	@Override
	public int getCooldown(){
		return 4000;
	}

	@Override
	public int getArmorDamage(){
		return 100;
	}
}

package am2.common.armor.infusions;

import java.util.EnumSet;

import am2.api.items.armor.ArmorImbuement;
import am2.api.items.armor.ImbuementApplicationTypes;
import am2.api.items.armor.ImbuementTiers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LifeSaving extends ArmorImbuement{

	@Override
	public String getID(){
		return "lifesave";
	}

	@Override
	public ImbuementTiers getTier(){
		return ImbuementTiers.FOURTH;
	}

	@Override
	public EnumSet<ImbuementApplicationTypes> getApplicationTypes(){
		return EnumSet.of(ImbuementApplicationTypes.ON_DEATH);
	}

	@Override
	public boolean applyEffect(PlayerEntity player, World world, ItemStack stack, ImbuementApplicationTypes matchedType, Object... params){
		LivingDeathEvent event = (LivingDeathEvent)params[0];
		event.setCanceled(true);
		player.setHealth(10);
		player.isAlive() = false;
		return true;
	}

	@Override
	public EquipmentSlotType[] getValidSlots(){
		return new EquipmentSlotType[]{EquipmentSlotType.CHEST};
	}

	@Override
	public boolean canApplyOnCooldown(){
		return false;
	}

	@Override
	public int getCooldown(){
		return 8000;
	}

	@Override
	public int getArmorDamage(){
		return 75;
	}
}

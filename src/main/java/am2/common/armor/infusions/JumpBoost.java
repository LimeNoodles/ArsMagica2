package am2.common.armor.infusions;

import java.util.EnumSet;

import am2.api.items.armor.ArmorImbuement;
import am2.api.items.armor.ImbuementApplicationTypes;
import am2.api.items.armor.ImbuementTiers;
import am2.common.extensions.EntityExtension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class JumpBoost extends ArmorImbuement{

	@Override
	public String getID(){
		return "highjump";
	}

	@Override
	public ImbuementTiers getTier(){
		return ImbuementTiers.FOURTH;
	}

	@Override
	public EnumSet<ImbuementApplicationTypes> getApplicationTypes(){
		return EnumSet.of(ImbuementApplicationTypes.ON_JUMP, ImbuementApplicationTypes.ON_TICK);
	}

	@Override
	public boolean applyEffect(PlayerEntity player, World world, ItemStack stack, ImbuementApplicationTypes matchedType, Object... params){
		if (matchedType == ImbuementApplicationTypes.ON_JUMP){
			Vec3d vec = player.getLookVec().normalize();
			double yVelocity = 1;
			double xVelocity = player.getMotion().getX() * 3.5 * Math.abs(vec.getX());
			double zVelocity = player.getMotion().getY() * 3.5 * Math.abs(vec.getZ());

			if (EntityExtension.For(player).getIsFlipped()){
				yVelocity *= -1;
			}

			player.addVelocity(xVelocity, yVelocity, zVelocity);
		}else if (matchedType == ImbuementApplicationTypes.ON_TICK){
			EntityExtension.For(player).setFallProtection(20);
		}
		return true;
	}

	@Override
	public EquipmentSlotType[] getValidSlots(){
		return new EquipmentSlotType[][]{EquipmentSlotType.LEGS};
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
		return 0;
	}
}

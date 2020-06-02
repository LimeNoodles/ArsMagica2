package am2.common.armor.infusions;

import java.util.EnumSet;

import am2.api.items.armor.ArmorImbuement;
import am2.api.items.armor.ImbuementApplicationTypes;
import am2.api.items.armor.ImbuementTiers;
import am2.api.math.AMVector3;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class WaterWalking extends ArmorImbuement{

	@Override
	public String getID(){
		return "wtrwalk";
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
		Block[] blocks = new Block[4];
		AMVector3[] vectors = new AMVector3[4];
		int posY = (int)Math.floor(player.getPosY());

		vectors[0] = new AMVector3((int)Math.floor(player.getPosX()), posY, (int)Math.floor(player.posZ));
		vectors[1] = new AMVector3((int)Math.ceil(player.getPosX()), posY, (int)Math.floor(player.posZ));
		vectors[2] = new AMVector3((int)Math.floor(player.getPosX()), posY, (int)Math.ceil(player.posZ));
		vectors[3] = new AMVector3((int)Math.ceil(player.getPosX()), posY, (int)Math.ceil(player.posZ));

		blocks[0] = world.getBlockState(vectors[0].toBlockPos()).getBlock();
		blocks[1] = world.getBlockState(vectors[1].toBlockPos()).getBlock();
		blocks[2] = world.getBlockState(vectors[2].toBlockPos()).getBlock();
		blocks[3] = world.getBlockState(vectors[3].toBlockPos()).getBlock();

		boolean onWater = false;
		for (int i = 0; i < 4 && !onWater; ++i){
			onWater |= (blocks[i] == Blocks.WATER || blocks[i] == Blocks.WATER);
		}

		if (!player.isInWater() && onWater && !player.isSneaking()){
			player.fallDistance = 0;
			player.onGround = true;
			player.isAirBorne = false;
			player.collidedVertically = true;
			player.collided = true;
			if (player.getMotion().getY() < 0){
				player.motionY = 0;
			}

			if (player.world.isRemote && player.ticksExisted % 5 == 0 && (Math.abs(player.motionX) > 0.1f || Math.abs(player.motionZ) > 0.1f)){
				player.playSound(SoundEvents.ENTITY_PLAYER_SWIM, 0.02f, 1.0F + (player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.4F);
				for (float l = 0; l < 5; ++l){
					float f5 = (player.getRNG().nextFloat() * 2.0F - 1.0F) * player.width;
					float f4 = (player.getRNG().nextFloat() * 2.0F - 1.0F) * player.width;
					player.world.spawnParticle(ParticleType.WATER_SPLASH, player.getPosX() + f5, player.getPosY(), player.getPosZ() + f4, (player.getRNG().nextFloat() - 0.5f) * 0.2f, player.getRNG().nextFloat() * 0.1f, (player.getRNG().nextFloat() - 0.5f) * 0.2f);
				}
			}
		}
		return false;
	}

	@Override
	public EquipmentSlotType[] getValidSlots(){
		return new EquipmentSlotType[][]{EquipmentSlotType.FEET};
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

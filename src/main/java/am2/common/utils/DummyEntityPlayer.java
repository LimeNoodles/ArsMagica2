package am2.common.utils;

import java.util.UUID;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import com.mojang.authlib.GameProfile;

public class DummyEntityPlayer extends PlayerEntity
{

	private LivingEntity trackEntity = null;

	public DummyEntityPlayer(World world){
		super(world, new GameProfile(UUID.randomUUID(), "dummyplayer"));
	}

	public static PlayerEntity fromEntityLiving(LivingEntity entity){
		if (entity instanceof EntityPlayer) return (PlayerEntity) entity;

		DummyEntityPlayer dep = new DummyEntityPlayer(entity.world);
		dep.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
		dep.setRotation(entity.rotationYaw, entity.rotationPitch);
		dep.trackEntity = entity;

		return dep;
	}

	public void copyEntityLiving(LivingEntity entity){
		this.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
		this.setRotation(entity.rotationYaw, entity.rotationPitch);
		this.trackEntity = entity;
		this.world = entity.world;
	}

	@Override
	public void onUpdate(){
		if (trackEntity != null){
			this.setPosition(trackEntity.getPosX(), trackEntity.getPosY(), trackEntity.getPosZ());
			this.setRotation(trackEntity.rotationYaw, trackEntity.rotationPitch);

			this.motionX = trackEntity.motionX;
			this.motionY = trackEntity.motionY;
			this.motionZ = trackEntity.motionZ;
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(int i, String s){
		return false;
	}

	@Override
	public void addChatMessage(ITextComponent arg0){
	}

	@Override
	public boolean isSpectator() {
		return false;
	}

	@Override
	public boolean isCreative() {
		return false;
	}

}

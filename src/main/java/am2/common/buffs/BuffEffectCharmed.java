package am2.common.buffs;

import java.util.UUID;

import am2.common.defs.PotionEffectsDefs;
import am2.common.utils.EntityUtils;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class BuffEffectCharmed extends BuffEffect{


	public static final int CHARM_TO_PLAYER = 1;
	public static final int CHARM_TO_MONSTER = 2;

	private LivingEntity charmer;
	
	public BuffEffectCharmed(int duration, int amplifier){
		super(PotionEffectsDefs.CHARME, duration, amplifier);
	}

	public void setCharmer(LivingEntity entity){
		charmer = entity;
	}

	@Override
	public void applyEffect(LivingEntity entityliving) {
		if (getAmplifier() == CHARM_TO_PLAYER && entityliving instanceof CreatureEntity && charmer instanceof PlayerEntity){
			EntityUtils.makeSummon_PlayerFaction((CreatureEntity)entityliving, (PlayerEntity)charmer, true);
		}else if (getAmplifier() == CHARM_TO_MONSTER && entityliving instanceof CreatureEntity){
			EntityUtils.makeSummon_MonsterFaction((CreatureEntity)entityliving, true);
		}
		EntityUtils.setOwner(entityliving, charmer);
		EntityUtils.setSummonDuration(entityliving, -1);
	}

	@Override
	public void stopEffect(LivingEntity entityliving){
		if (entityliving instanceof CreatureEntity){
			EntityUtils.revertAI((CreatureEntity)entityliving);
		}
	}
	
	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setString("Charmer", charmer.getUniqueID().toString());
	}
	
	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		try {
			charmer = (LivingEntity) FMLCommonHandler.instance().getMinecraftServerInstance().worldServers[0].getEntityFromUuid(UUID.fromString(nbt.getString("Charmer")));
		} catch (Throwable t) {
		}
	}
	
	@Override
	protected String spellBuffName(){
		return "Charmed";
	}

}

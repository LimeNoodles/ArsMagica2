package am2.api.extensions;

import java.util.concurrent.Callable;

import am2.api.spell.SpellData;
import am2.common.extensions.EntityExtension;
import am2.common.spell.ContingencyType;
import am2.common.utils.NBTUtils;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public interface IEntityExtension {

	boolean hasEnoughMana(float f);
		
	void setContingency(ContingencyType type, SpellData stack);
	
	ContingencyType getContingencyType();
	
	SpellData getContingencyStack();
	
	double getMarkX();
	
	double getMarkY();
	
	double getMarkZ();
	
	int getMarkDimensionID();
	
	float getCurrentMana();
	
	int getCurrentLevel();
	
	float getCurrentBurnout();
	
	int getCurrentSummons();
	
	float getCurrentXP();
	
	int getHealCooldown();
	
	void lowerHealCooldown(int amount);
	
	void placeHealOnCooldown();
	
	void lowerAffinityHealCooldown(int amount);
	
	int getAffinityHealCooldown();
	
	void placeAffinityHealOnCooldown(boolean full);
	
	float getMaxMana();
	
	float getMaxXP();
	
	float getMaxBurnout();
	
	void setAffinityHealCooldown(int affinityHealCooldown);
	
	void setCurrentBurnout(float currentBurnout);
	
	void setCurrentLevel(int currentLevel);
	
	void setCurrentMana(float currentMana);
	
	void setCurrentSummons(int currentSummons);
	
	void setCurrentXP(float currentXP);
	
	void setHealCooldown(int healCooldown);
	
	void setMarkX(double markX);
	
	void setMarkY(double markY);
	
	void setMarkZ(double markZ);
	
	void setMarkDimensionID(int markDimensionID);
	
	void setMark(double x, double y, double z, int dim);
	
	void setShrunk(boolean shrunk);
	
	boolean isShrunk();

	void setInverted(boolean inverted);

	void setFallProtection(float fallProtection);
	
	boolean isInverted();
	
	void addEntityReference(LivingEntity entity);
	
	void init(LivingEntity entity);
	
	boolean canHeal();
	
	int getMaxSummons();
	
	boolean shouldUpdate();
	byte[] generateUpdatePacket();
	void handleUpdatePacket(byte[] bytes);
	void forceUpdate();
	
	class Storage implements IStorage<IEntityExtension> {
		
		@Override
		public CompoundNBT writeNBT(Capability<IEntityExtension> capability, IEntityExtension instance, EnumFacing side) {
			CompoundNBT compound = new CompoundNBT();
			CompoundNBT am2tag = NBTUtils.getAM2Tag(compound);
			am2tag.putFloat("CurrentMana", instance.getCurrentMana());
			am2tag.putInt("CurrentLevel", instance.getCurrentLevel());
			am2tag.putFloat("CurrentXP", instance.getCurrentXP());
			am2tag.putFloat("CurrentBurnout", instance.getCurrentBurnout());
			am2tag.putInt("CurrentSummons", instance.getCurrentSummons());
			
			am2tag.putInt("HealCooldown", instance.getHealCooldown());
			am2tag.putInt("AffinityHealCooldown", instance.getAffinityHealCooldown());
			
			am2tag.putBoolean("Shrunk", instance.isShrunk());
			am2tag.putBoolean("Inverted", instance.isInverted());
			am2tag.putFloat("FallProtection", instance.getFallProtection());
			
			am2tag.putDouble("MarkX", instance.getMarkX());
			am2tag.putDouble("MarkY", instance.getMarkY());
			am2tag.putDouble("MarkZ", instance.getMarkZ());
			am2tag.putInt("MarkDimensionId", instance.getMarkDimensionID());
			am2tag.putFloat("TK_Distance", instance.getTKDistance());
			am2tag.putFloat("ManaShielding", instance.getManaShielding());
			CompoundNBT contingencyTag = NBTUtils.addTag(am2tag, "Contingency");
			if (instance.getContingencyType() != ContingencyType.NULL) {
				contingencyTag.putString("Type", instance.getContingencyType().name().toLowerCase());
				contingencyTag.put("Spell", instance.getContingencyStack().writeToNBT(new CompoundNBT()));
			} else {
				contingencyTag.putString("Type", "null");
			}
			return compound;
		}
	
		@Override
		public void readNBT(Capability<IEntityExtension> capability, IEntityExtension instance, Direction side, CompoundNBT nbt) {
			CompoundNBT am2tag = NBTUtils.getAM2Tag((CompoundNBT)nbt);
			instance.setCurrentMana(am2tag.getFloat("CurrentMana"));
			instance.setCurrentLevel(am2tag.getInt("CurrentLevel"));
			instance.setCurrentXP(am2tag.getFloat("CurrentXP"));
			instance.setCurrentBurnout(am2tag.getFloat("CurrentBurnout"));
			instance.setCurrentSummons(am2tag.getInt("CurrentSummons"));
			
			instance.setHealCooldown(am2tag.getInt("HealCooldown"));
			instance.setAffinityHealCooldown(am2tag.getInt("AffinityHealCooldown"));
			
			instance.setShrunk(am2tag.getBoolean("Shrunk"));
			instance.setInverted(am2tag.getBoolean("Inverted"));
			instance.setFallProtection(am2tag.getFloat("FallProtection"));
			
			instance.setMarkX(am2tag.getDouble("MarkX"));
			instance.setMarkY(am2tag.getDouble("MarkY"));
			instance.setMarkZ(am2tag.getDouble("MarkZ"));
			instance.setMarkDimensionID(am2tag.getInteger("MarkDimensionId"));
			
			instance.setTKDistance(am2tag.getFloat("TK_Distance"));
			instance.setManaShielding(am2tag.getFloat("ManaShielding"));
			
			CompoundNBT contingencyTag = NBTUtils.addTag(am2tag, "Contingency");
			if (!contingencyTag.hasKey("Type") || !contingencyTag.getString("Type").equals("null")) {
				instance.setContingency(ContingencyType.fromName(contingencyTag.getString("Type")), SpellData.readFromNBT(contingencyTag.getCompound("Spell")));
			} else {
				instance.setContingency(ContingencyType.NULL, null);
			}
		}
	}
	
	class Factory implements Callable<IEntityExtension> {

		@Override
		public IEntityExtension call() {
			return new EntityExtension();
		}
		
	}

	boolean addSummon(CreatureEntity entityliving);

	boolean getCanHaveMoreSummons();

	void updateManaLink(LivingEntity caster);

	void deductMana(float amt);

	void spawnManaLinkParticles();

	boolean removeSummon();

	boolean isManaLinkedTo(EntityLivingBase entity);

	void cleanupManaLinks();

	float getBonusMaxMana();

	float getBonusCurrentMana();

	boolean shouldReverseInput();

	boolean getIsFlipped();

	float getFlipRotation();

	float getPrevFlipRotation();

	float getShrinkPct();

	float getPrevShrinkPct();
	
	void setTKDistance(float newDist);
	
	void addToTKDistance(float toAdd);
	
	float getTKDistance();

	void syncTKDistance();

	float getFallProtection();

	void manaBurnoutTick();

	boolean setMagicLevelWithMana(int level);

	void addMagicXP(float xp);

	void setDisableGravity(boolean b);

	boolean isGravityDisabled();

	Entity getInanimateTarget();

	void setInanimateTarget(Entity ent);

	void setFlipRotation(float rot);

	void setPrevFlipRotation(float rot);

	float getManaShielding();
	
	void setManaShielding(float manaShielding);
}

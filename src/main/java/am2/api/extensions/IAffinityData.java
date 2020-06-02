package am2.api.extensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import am2.api.affinity.Affinity;
import am2.common.extensions.AffinityData;
import am2.common.utils.NBTUtils;
import net.minecraftforge.common.util.Constants;

public interface IAffinityData {	

	public double getAffinityDepth(Affinity aff);
	
	public void setAffinityDepth (Affinity name, double value);
	
	public HashMap<Affinity, Double> getAffinities();

	public void init(PlayerEntity entity);
	
	public static class Storage implements IStorage<IAffinityData> {
		
		@Override
		public CompoundNBT writeNBT(Capability<IAffinityData> capability, IAffinityData instance, EnumFacing side) {
			CompoundNBT nbt = new CompoundNBT();
			for (Entry<Affinity, Double> entry : instance.getAffinities().entrySet()) {
				Affinity.writeToNBT(nbt, entry.getKey(), entry.getValue());
			}
			CompoundNBT am2Tag = NBTUtils.getAM2Tag(nbt);
			ListNBT cooldowns = NBTUtils.addCompoundList(am2Tag, "Cooldowns");
			for (Entry<String, Integer> entry : instance.getCooldowns().entrySet()) {
				CompoundNBT tmp = new CompoundNBT();
				tmp.put("Name", entry.getKey());
				tmp.put("Value", entry.getValue());
				cooldowns.add(tmp);
			}
			am2Tag.setTag("Cooldowns", cooldowns);
			ListNBT floats = NBTUtils.addCompoundList(am2Tag, "Floats");
			List booleans = NBTUtils.addCompoundList(am2Tag, "Booleans");
			for (Entry<String, Float> entry : instance.getAbilityFloatMap().entrySet()) {
				CompoundNBT tmp = new CompoundNBT();
				tmp.putString("Name", entry.getKey());
				tmp.putFloat("Value", entry.getValue());
				floats.add(tmp);
			}
			for (Entry<String, Boolean> entry : instance.getAbilityBooleanMap().entrySet()) {
				CompoundNBT tmp = new CompoundNBT();
				tmp.putString("Name", entry.getKey());
				tmp.putBoolean("Value", entry.getValue());
				booleans.add(tmp);
			}
			am2Tag.put("Floats", floats);
			am2Tag.setTag("Booleans", booleans);
			
			return nbt;
		}

		@Override
		public void readNBT(Capability<IAffinityData> capability, IAffinityData instance, Direction side, CompoundNBT nbt) {
			ArrayList<Affinity> affinities = Affinity.readFromNBT((CompoundNBT) nbt);
			for (Affinity aff : affinities) {
				instance.setAffinityDepth(aff, aff.readDepth((CompoundNBT) nbt));
			}

			CompoundNBT am2Tag = NBTUtils.getAM2Tag((CompoundNBT) nbt);
			ListNBT cooldowns = NBTUtils.addCompoundList(am2Tag, "Cooldowns");
			ListNBT floats = NBTUtils.addCompoundList(am2Tag, "Floats");
			ListNBT booleans = NBTUtils.addCompoundList(am2Tag, "Booleans");
			for (int i = 0; i < cooldowns.tagCount(); i++) {
				CompoundNBT tmp = cooldowns.getCompoundTagAt(i);
				instance.addCooldown(tmp.getString("Name"), tmp.getInteger("Value"));
			}
			for (int i = 0; i < floats.tagCount(); i++) {
				CompoundNBT tmp = floats.getCompoundTagAt(i);
				instance.addAbilityFloat(tmp.getString("Name"), tmp.getFloat("Value"));
			}
			for (int i = 0; i < booleans.tagCount(); i++) {
				CompoundNBT tmp = booleans.get(i);
				instance.addAbilityBoolean(tmp.getString("Name"), tmp.getBoolean("Value"));
			}
		}
	}
	
	public static class Factory implements Callable<IAffinityData> {
		@Override
		public IAffinityData call() throws Exception {
			return new AffinityData();
		}
	}

	public Affinity[] getHighestAffinities();

	public float getDiminishingReturnsFactor();

	public void tickDiminishingReturns();

	public void addDiminishingReturns(boolean isChanneled);

	public void addCooldown(String name, int cooldown);

	public Map<String, Integer> getCooldowns();

	public int getCooldown(String name);

	public Map<String, Float> getAbilityFloatMap();

	public Map<String, Boolean> getAbilityBooleanMap();

	public boolean getAbilityBoolean(String name);

	public void addAbilityBoolean(String name, boolean bool);

	public float getAbilityFloat(String name);

	public void addAbilityFloat(String name, float f);

	public void incrementAffinity(Affinity affinity, float amount);

	public void setLocked(boolean b);

	public boolean isLocked();

	byte[] generateUpdatePacket();

	void handleUpdatePacket(byte[] bytes);
	
	boolean shouldUpdate();
	public void forceUpdate();
}

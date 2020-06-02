package am2.api.extensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import am2.api.SkillPointRegistry;
import am2.api.skill.Skill;
import am2.api.skill.SkillPoint;
import am2.common.extensions.SkillData;
import am2.common.utils.NBTUtils;

public interface ISkillData {
	
	public boolean hasSkill (String name);
	
	public void unlockSkill (String name);
	
	public HashMap<Skill, Boolean> getSkills();
	
	public HashMap<SkillPoint, Integer> getSkillPoints();
	
	public int getSkillPoint(SkillPoint skill);
	
	public void setSkillPoint(SkillPoint point, int num);

	public boolean canLearn(String name);
	public boolean shouldUpdate();
	public byte[] generateUpdatePacket();
	public void handleUpdatePacket(byte[] bytes);
	public void forceUpdate();

	
	public static class Storage implements IStorage<ISkillData> {
		
		@Override
		public CompoundNBT writeNBT(Capability<ISkillData> capability, ISkillData instance, EnumFacing side) {
			CompoundNBT nbt = new CompoundNBT();
			CompoundNBT am2Tag = NBTUtils.getAM2Tag(nbt);
			ListNBT skillList = NBTUtils.addCompoundList(am2Tag, "Skills");
			ListNBT skillPointList = NBTUtils.addCompoundList(am2Tag, "SkillPoints");
			for (Entry<Skill, Boolean> skill : instance.getSkills().entrySet()) {
				if (skill.getKey() == null)
					continue;
				CompoundNBT tmp = new CompoundNBT();
				tmp.putString("Skill", skill.getKey().getID());
				tmp.putBoolean("Unlocked", skill.getValue());
				skillList.add(tmp);
			}
			for (Entry<SkillPoint, Integer> skill : instance.getSkillPoints().entrySet()) {
				if (skill.getKey() == null)
					continue;
				CompoundNBT tmp = new CompoundNBT();
				tmp.putString("Type", skill.getKey().getName());
				tmp.putInt("Number", skill.getValue());
				skillPointList.add(tmp);
			}
			am2Tag.setTag("Skills", skillList);
			am2Tag.setTag("SkillPoints", skillPointList);
			return nbt;
		}

		@Override
		public void readNBT(Capability<ISkillData> capability, ISkillData instance, Direction side, CompoundNBT nbt) {
			CompoundNBT am2Tag = NBTUtils.getAM2Tag((CompoundNBT) nbt);
			ListNBT skillList = NBTUtils.addCompoundList(am2Tag, "Skills");
			ListNBT skillPointList = NBTUtils.addCompoundList(am2Tag, "SkillPoints");
			for (int i = 0; i < skillList.size(); i++) {
				CompoundNBT tmp = skillList.getCompound(i);
				if (tmp.getBoolean("Unlocked"))
					instance.unlockSkill(tmp.getString("Skill"));
			}
			for (int i = 0; i < skillPointList.size(); i++) {
				CompoundNBT tmp = skillPointList.getCompound(i);
				instance.setSkillPoint(SkillPointRegistry.fromName(tmp.getString("Type")), tmp.getInteger("Number"));
			}
		}
	}
	
	public static class Factory implements Callable<ISkillData> {
		@Override
		public ISkillData call() throws Exception {
			return new SkillData();
		}
	}

	public ArrayList<String> getKnownShapes();

	public ArrayList<String> getKnownComponents();

	public ArrayList<String> getKnownModifiers();
}

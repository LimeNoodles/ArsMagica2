package am2.api.extensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Nullable;

import am2.api.ArsMagicaAPI;
import am2.api.affinity.Affinity;
import am2.api.spell.AbstractSpellPart;
import am2.api.spell.SpellData;
import am2.common.utils.NBTUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.Constants;

/**
 * Capability for spells.<BR>
 * Doing a write -> read of this will remove any invalid data.
 */
public interface ISpellCaster {
	float getManaCost(@Nullable World world, LivngEntity caster);
	SpellData createSpellData(ItemStack source);
	boolean cast(ItemStack source, World world, LivngEntity caster);
	
	//Getters
	List<List<AbstractSpellPart>> getSpellCommon();
	List<List<List<AbstractSpellPart>>> getShapeGroups();
	Map<Affinity, Float> getAffinityShift();
	int getShapeGroupCount();
	float getBaseManaCost(int shapeGroup);
	UUID getSpellUUID();
	int getCurrentShapeGroup();
	CompoundNBT getStoredData(int shapeGroup);
	CompoundNBT getCommonStoredData();
	//Setters
	void setSpellCommon(@Nullable List<List<AbstractSpellPart>> data);
	void setShapeGroups(@Nullable List<List<List<AbstractSpellPart>>> data);
	void setAffinityShift(@Nullable Map<Affinity, Float> shift);
	void setBaseManaCost(int shapeGroup, float manaCost);
	void setUUID(@Nullable UUID uuid);
	void setCurentShapeGroup(int shapeGroup);
	void setStoredData(int shapeGroup, CompoundNBT tag);
	void setCommonStoredData(CompoundNBT tag);
	
	//Gatherers
	void gatherBaseManaCosts();
	void gatherAffinityShift();
	void generateUUID();
	
	boolean validate();
	
	
	public static class Storage implements IStorage<ISpellCaster> {
		
		private static final String KEY_BASE_MANA_COST = "BaseManaCost";
		private static final String KEY_UUID_MOST = "UUIDMostSignificantBits";
		private static final String KEY_UUID_LEAST = "UUIDLeastSignificantBits";
		private static final String KEY_SPELL_COMMON = "SpellCommon";
		private static final String KEY_PARTS = "Parts";
		private static final String KEY_ID = "ID";
		private static final String KEY_SHAPE_GROUPS = "ShapeGroups";
		private static final String KEY_GROUP = "Group";
		private static final String KEY_AFFINITY = "AffinityShift";
		private static final String KEY_AFFINITY_TYPE = "Type";
		private static final String KEY_AFFINITY_DEPTH = "Depth";
		private static final String KEY_CURRENT_SHAPE_GROUP = "CurrentShapeGroup";
		private static final String KEY_STORED_DATA = "StoredData";
		
		
		@Override
		public CompoundNBT writeNBT(Capability<ISpellCaster> capability, ISpellCaster instance, EnumFacing side) {
			CompoundNBT compound = new CompoundNBT();
			compound.putLong(KEY_UUID_MOST, instance.getSpellUUID().getMostSignificantBits());
			compound.putLong(KEY_UUID_LEAST, instance.getSpellUUID().getLeastSignificantBits());
			compound.putInt(KEY_CURRENT_SHAPE_GROUP, instance.getCurrentShapeGroup());
			ListNBT spellCommon = new ListNBT();
			List<List<AbstractSpellPart>> commonStages = instance.getSpellCommon();
			if (commonStages != null) {
				for (int i = 0; i < commonStages.size(); i++) {
					CompoundNBT tmp = new CompoundNBT();
					tmp.putInt(KEY_ID, i);
					ListNBT stageTag = new ListNBT();
					List<AbstractSpellPart> parts = commonStages.get(i);
					if (parts != null) {
						for (AbstractSpellPart p : parts) {
							stageTag.add(new StringNBT(p.getRegistryName().toString()));
						}
					}
					tmp.put(KEY_PARTS, stageTag);
					spellCommon.add(tmp);
				}
			}
			compound.put(KEY_SPELL_COMMON, spellCommon);
			
			List<List<List<AbstractSpellPart>>> shapeGroups = instance.getShapeGroups();
			ListNBT shapeGroupsTag = new ListNBT();
			if (shapeGroups != null) {
				for (int i = 0; i < shapeGroups.size(); i++) {
					CompoundNBT groupTag = new CompoundNBT();
					groupTag.putInt(KEY_ID, i);
					groupTag.put(KEY_STORED_DATA, instance.getStoredData(i));
					groupTag.putFloat(KEY_BASE_MANA_COST, instance.getBaseManaCost(i));
					ListNBT stagesTag = new ListNBT();
					List<List<AbstractSpellPart>> stages = shapeGroups.get(i);
					if (stages != null) {
						for (int j = 0; j < stages.size(); j++) {
							CompoundNBT tmp = new CompoundNBT();
							tmp.putInt(KEY_ID, j);
							ListNBT stageTag = new ListNBT();
							List<AbstractSpellPart> parts = stages.get(j);
							if (parts != null) {
								for (AbstractSpellPart p : parts) {
									stageTag.add(new StringNBT(p.getRegistryName().toString()));
								}
							}
							tmp.put(KEY_PARTS, stageTag);
							stagesTag.appendTag(tmp);
						}
					}
					groupTag.put(KEY_GROUP, stagesTag);
					shapeGroupsTag.appendTag(groupTag);
				}
			}
			compound.put(KEY_SHAPE_GROUPS, shapeGroupsTag);
			ListNBT affinityShift = new ListNBT();
			for (Entry<Affinity, Float> entry : instance.getAffinityShift().entrySet()) {
				if (entry.getKey() != null && entry.getValue() != null) {
					CompoundNBT tmp = new CompoundNBT();
					tmp.putString(KEY_AFFINITY_TYPE, entry.getKey().getRegistryName().toString());
					tmp.putFloat(KEY_AFFINITY_DEPTH, entry.getValue().floatValue());
					affinityShift.add(tmp);
				}
			}
			compound.put(KEY_AFFINITY, affinityShift);
			compound.put(KEY_STORED_DATA, instance.getCommonStoredData());
			return compound;
		}

		@Override
		public void readNBT(Capability<ISpellCaster> capability, ISpellCaster instance, Direction side, CompoundNBT nbt) {
			CompoundNBT compound = (CompoundNBT) nbt;
			instance.setUUID(new UUID(compound.getLong(KEY_UUID_MOST), compound.getLong(KEY_UUID_LEAST)));
			instance.setCurentShapeGroup(compound.getInt(KEY_CURRENT_SHAPE_GROUP));
			ListNBT spellCommon = compound.getList(KEY_SPELL_COMMON, Constants.NBT.TAG_COMPOUND);
			ArrayList<List<AbstractSpellPart>> commonStages = new ArrayList<>(spellCommon.size());
			for (int i = 0; i < spellCommon.size(); i++) {
				CompoundNBT tmp = spellCommon.getCompound(i);
				int id = tmp.getInt(KEY_ID);
				ListNBT parts = tmp.getList(KEY_PARTS, Constants.NBT.TAG_STRING);
				ArrayList<AbstractSpellPart> pts = new ArrayList<>();
				for (int j = 0; j < parts.size(); j++) {
					AbstractSpellPart part = ArsMagicaAPI.getSpellRegistry().getObject(new ResourceLocation(parts.getString(j)));
					if (part != null) {
						pts.add(part);
					}
				}
				NBTUtils.ensureSize(commonStages, id + 1);
				commonStages.set(id, pts);
			}
			instance.setSpellCommon(commonStages);
			ListNBT shapeGroupsTag = compound.getList(KEY_SHAPE_GROUPS, Constants.NBT.TAG_COMPOUND);
			ArrayList<List<List<AbstractSpellPart>>> shapeGroups = new ArrayList<>(shapeGroupsTag.size());
			for (int i = 0; i < shapeGroupsTag.size(); i++) {
				CompoundNBT group = shapeGroupsTag.getCompound(i);
				int gid = group.getInt(KEY_ID);
				instance.setBaseManaCost(gid, group.getFloat(KEY_BASE_MANA_COST));
				instance.setStoredData(gid, group.getCompound(KEY_STORED_DATA));
				ListNBT stagesLs = group.getList((KEY_GROUP, Constants.NBT.TAG_COMPOUND);
				ArrayList<List<AbstractSpellPart>> stages = new ArrayList<>(stagesLs.size());
				for (int j = 0; j < stagesLs.size(); j++) {
					CompoundNBT tmp = stagesLs.getCompound(j);
					int id = tmp.getInt(KEY_ID);
					ListNBT parts = tmp.getList(KEY_PARTS, Constants.NBT.TAG_STRING);
					ArrayList<AbstractSpellPart> pts = new ArrayList<>();
					for (int k = 0; k < parts.size(); k++) {
						AbstractSpellPart part = ArsMagicaAPI.getSpellRegistry().getObject(new ResourceLocation(parts.getStringTagAt(k)));
						if (part != null) {
							pts.add(part);
						}
					}
					NBTUtils.ensureSize(stages, id + 1);
					stages.set(id, pts);
				}
				NBTUtils.ensureSize(shapeGroups, gid + 1);
				shapeGroups.set(gid, stages);
			}
			instance.setShapeGroups(shapeGroups);
			ListNBT affinityShift = compound.getList(KEY_AFFINITY, Constants.NBT.TAG_COMPOUND);
			HashMap<Affinity, Float> affMap = new HashMap<>();
			for (int i = 0; i < affinityShift.size(); i++) {
				CompoundNBT tmp = affinityShift.getCompound(i);
				Affinity aff = ArsMagicaAPI.getAffinityRegistry().getObject(new ResourceLocation(tmp.getString(KEY_AFFINITY_TYPE)));
				float depth = tmp.getFloat(KEY_AFFINITY_DEPTH);
				if (depth != 0 && aff != null)
					affMap.put(aff, depth);
			}
			instance.setAffinityShift(affMap);
			instance.setCommonStoredData(compound.getCompoundTag(KEY_STORED_DATA));
		}
	}


}

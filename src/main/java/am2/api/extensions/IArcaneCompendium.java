package am2.api.extensions;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import am2.api.compendium.CompendiumCategory;
import am2.api.compendium.CompendiumEntry;
import am2.common.lore.ArcaneCompendium;
import am2.common.utils.NBTUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public interface IArcaneCompendium {
	
	/**
	 * Unlocks a compendium entry
	 * @param entry : id of the entry
	 */
	public void unlockEntry(String entry);
	
	/**
	 * Unlock related entries to this one
	 * @param crafting
	 */
	public void unlockRelatedItems(ItemStack crafting);
	
	/**
	 * 
	 * @param string
	 * @return true if the entry/category is unlocked
	 */
	public boolean isUnlocked(String string);
	
	public String getPath();
	
	public void setPath(String str);
	
	public boolean shouldUpdate();
	public byte[] generateUpdatePacket();
	public void handleUpdatePacket(byte[] bytes);
	public void forceUpdate();

	
	public static class Storage implements IStorage<IArcaneCompendium> {

		@Override
		public CompoundNBT writeNBT(Capability<IArcaneCompendium> capability, IArcaneCompendium instance, Direction side) {
			CompoundNBT compound = new CompoundNBT();
			CompoundNBT am2tag = NBTUtils.getAM2Tag(compound);
			ListNBT unlocks = NBTUtils.addCompoundList(am2tag, "Unlocks");
			for (CompendiumCategory categroy : CompendiumCategory.getCategories()) {
				for (CompendiumEntry entry : categroy.getEntries()) {
					CompoundNBT tmp = new CompoundNBT();
					tmp.putString("ID", entry.getID());
					tmp.putBoolean("Unlocked", instance.isUnlocked(entry.getID()));
					unlocks.add(tmp);
				}
			}
			am2tag.putString("Path", instance.getPath());
			return compound;
		}

		@Override
		public void readNBT(Capability<IArcaneCompendium> capability, IArcaneCompendium instance, EnumFacing side, NBTBase nbt) {
			CompoundNBT am2tag = NBTUtils.getAM2Tag((CompoundNBT) nbt);
			ListNBT unlocks = NBTUtils.addCompoundList(am2tag, "Unlocks");
			for (int i = 0; i < unlocks.tagCount(); i++) {
				CompoundNBT tmp = unlocks.getCompoundTagAt(i);
				if (tmp.getBoolean("Unlocked")) {
					instance.unlockEntry(tmp.getString("ID"));
				}
			}
			instance.setPath(am2tag.getString("Path"));
		}
	}
	
	public static class Factory implements Callable<IArcaneCompendium> {

		@Override
		public IArcaneCompendium call() throws Exception {
			return new ArcaneCompendium();
		}
		
	}

	public ArrayList<CompendiumEntry> getEntriesForCategory(String categoryName);

	public boolean isNew(String id);

}

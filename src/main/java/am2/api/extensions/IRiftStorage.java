package am2.api.extensions;

import java.util.concurrent.Callable;

import am2.common.extensions.RiftStorage;
import am2.common.utils.NBTUtils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public interface IRiftStorage extends IInventory {
	
	
	public static class Storage implements IStorage<IRiftStorage> {
		
		@Override
		public CompoundNBT writeNBT(Capability<IRiftStorage> capability, IRiftStorage instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			CompoundNBT am2Tag = NBTUtils.getAM2Tag(nbt);
			ListNBT list = NBTUtils.addCompoundList(am2Tag, "RiftInventory");
			for (int i = 0; i < instance.getSizeInventory(); i++) {
				if (instance.getStackInSlot(i) == null)
					continue;
				CompoundNBT tmp = new CompoundNBT();
				instance.getStackInSlot(i).writeToNBT(tmp);
				tmp.putInt("Slot", i);
				list.add(tmp);
			}
			return nbt;
		}

		@Override
		public void readNBT(Capability<IRiftStorage> capability, IRiftStorage instance, Direction side, CompoundNBT nbt) {
			CompoundNBT am2Tag = NBTUtils.getAM2Tag((CompoundNBT) nbt);
			ListNBT list = NBTUtils.addCompoundList(am2Tag, "RiftInventory");
			for (int i = 0; i < list.size(); i++) {
				//LogHelper.info("Found a tag ");
				CompoundNBT compound = list.getCompound(i);
				instance.setInventorySlotContents(compound.getInt("Slot"), ItemStack.loadItemStackFromNBT(compound));
			}
		}
	}
	
	public static class Factory implements Callable<IRiftStorage> {
		@Override
		public IRiftStorage call() throws Exception {
			return new RiftStorage();
		}
	}

	int getAccessLevel();

	void setAccessLevel(int accessLevel);
	
}

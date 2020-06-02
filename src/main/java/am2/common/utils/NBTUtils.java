package am2.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class NBTUtils {
	
	public static CompoundNBT getAM2Tag (CompoundNBT baseTag) {
		return addTag(baseTag, "AM2");
	}
	
	public static CompoundNBT getEssenceTag (CompoundNBT baseTag) {
		return addTag(getAM2Tag(baseTag), "Essence");
	}
	
	public static void writeVecToNBT(Vec3d vec, CompoundNBT nbt) {
		nbt.putDouble("X", vec.getX());
		nbt.putDouble("Y", vec.getY());
		nbt.putDouble("Z", vec.getZ());
	}
	
	public static void writeBlockPosToNBT(BlockPos pos, CompoundNBT nbt) {
		nbt.putInt("X", pos.getX());
		nbt.putInt("Y", pos.getY());
		nbt.putInt("Z", pos.getZ());
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends List<?>> void ensureSize(ArrayList<T> list, int size) {
	    list.ensureCapacity(size);
	    while (list.size() < size) {
	        list.add((T) Lists.newArrayList());
	    }
	}
	
	public static Vec3d readVecFromNBT(CompoundNBT nbt) {
		Vec3d vec = new Vec3d(nbt.getDouble("X"), nbt.getDouble("Y"), nbt.getDouble("Z"));
		return vec;
	}
	
	public static BlockPos readBlockPosFromNBT(CompoundNBT nbt) {
		BlockPos pos = new BlockPos(nbt.getInt("X"), nbt.getInt("Y"), nbt.getInt("Z"));
		return pos;
	}
	
	public static Object getValueAt (CompoundNBT baseTag, String tagName) {
		CompoundNBT base = baseTag.getCompound(tagName);
		switch (base.getId()) {
		case 0: return null;
		case 1: return (base).getByte(tagName);
		case 2: return (base).getShort(tagName);
		case 3: return (base).getInt(tagName);
		case 4: return (base).getLong(tagName);
		case 5: return (base).getFloat(tagName);
		case 6: return (base).getDouble(tagName);
		case 7: return (base).getByteArray(tagName);
		case 8: return (base).getString(tagName);
		case 9: return (base);
		case 10: return (base).getIntArray(tagName);
		default:
			return null;
		}
	}
	
	public static CompoundNBT addTag (CompoundNBT upper, String name) {
		if (upper == null) throw new IllegalStateException("Base Tag must exist");
		CompoundNBT newTag = new CompoundNBT();
		if (upper.getCompound(name) != null) {
			newTag = upper.getCompound(name);
		}
		upper.put(name, newTag);
		return newTag;		
	}
	
	public static CompoundNBT addTag (CompoundNBT upper, CompoundNBT compound, String name) {
		if (upper == null) throw new IllegalStateException("Base Tag must exist");
		upper.put(name, compound);
		return upper;		
	}
	
	public static ListNBT addList (CompoundNBT upper, int type, String name) {
		if (upper == null) throw new IllegalStateException("Base Tag must exist");
		ListNBT newTag = new ListNBT();
		if (upper.getList(name, type) != null) {
			newTag = upper.getList(name, type);
		}
		upper.put(name, newTag);
		return newTag;
	}
	
	public static ListNBT addCompoundList(CompoundNBT upper, String name) {
		return addList(upper, 10, name);
	}
	
	public static boolean contains(CompoundNBT container, CompoundNBT check) {
		if (container == null) return true;
		if (check == null) return false;
		boolean match = true;
		for (String key : container.keySet()) {
			CompoundNBT tag = container.getCompound(key);
			CompoundNBT checkTag = check.getCompound(key);
			if (tag == null)
				continue;
			if (checkTag == null) return false;
			if (tag instanceof CompoundNBT && checkTag instanceof CompoundNBT)
				match &= contains((CompoundNBT)tag, (CompoundNBT) checkTag);
			else
				match &= tag.equals(checkTag);
			if (!match)
				break;
		}
		return match;
	}

	public static ItemStack[] getItemStackArray(CompoundNBT tagCompound, String string) {
		ListNBT list = addCompoundList(tagCompound, string);
		ItemStack[] array = new ItemStack[list.size()];
		for (int i = 0; i < list.size(); i++) {
			CompoundNBT tmp = list.getCompound(i);
			ItemStack is = ItemStack.loadItemStackFromNBT(tmp);
			if (is != null)
				is.setCount(tmp.getInt("ActualStackSize"));
			array[tmp.getInt("ID")] = is;
		}
		return array;
	}

	public static void setItemStackArray(CompoundNBT tagCompound, String string, ItemStack[] recipeData) {
		ListNBT list = addCompoundList(tagCompound, string);
		for (int i = 0; i < recipeData.length; i++) {
			CompoundNBT tmp = new CompoundNBT();
			tmp.putInt("ID", i);
			tmp.putInt("ActualStackSize", recipeData[i].getCount());
			recipeData[i].serializeNBT().put(string, tmp);
			list.add(tmp);
		}
		tagCompound.put(string, list);
	}

}

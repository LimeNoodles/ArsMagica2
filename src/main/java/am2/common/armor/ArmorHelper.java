package am2.common.armor;

import am2.api.items.armor.ArmorImbuement;
import am2.api.items.armor.ImbuementTiers;
import am2.common.armor.infusions.ImbuementRegistry;
import am2.common.extensions.EntityExtension;
import am2.common.utils.EntityUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class ArmorHelper{

//	private static final HashMap<String, Integer> armorRenderPrefixes = new HashMap<String, Integer>();

	private static final int IMBUE_TIER_COST = 12;

	public static boolean PlayerHasArmorInSlot(PlayerEntity player, EquipmentSlotType armorSlot){
		return (player.getItemStackFromSlot(armorSlot) != null);
	}

	//======================================================================================================
	// Infusion
	//======================================================================================================
	public static void HandleArmorInfusion(PlayerEntity player){
		EntityExtension ext = EntityExtension.For(player);

		float infusionCost = 0.0f;
		boolean bFullSet = getFullArsMagicaArmorSet(player) != -1;
		for (EquipmentSlotType i : EquipmentSlotType.values()){
			if (i.getSlotType() != Type.ARMOR)
				continue;
			infusionCost += GetArsMagicaArmorInfusionCostFromSlot(player, i);
		}
		if (bFullSet){
			infusionCost *= 0.75;
		}
		if (infusionCost > 0 && ext.hasEnoughMana(infusionCost)) {
			//deduct mana
			ext.deductMana(infusionCost);
			//bank infusion
			ext.bankedInfusionHelm += GetArsMagicaArmorRepairAmountFromSlot(player, EquipmentSlotType.HEAD);
			ext.bankedInfusionChest += GetArsMagicaArmorRepairAmountFromSlot(player, EquipmentSlotType.CHEST);
			ext.bankedInfusionLegs += GetArsMagicaArmorRepairAmountFromSlot(player, EquipmentSlotType.LEGS);
			ext.bankedInfusionBoots += GetArsMagicaArmorRepairAmountFromSlot(player, EquipmentSlotType.FEET);


			//repair armor if infusion bank is above 1
			if (ext.bankedInfusionHelm > 1){
				int repairAmount = (int)Math.floor(ext.bankedInfusionHelm);
				RepairEquippedArsMagicaArmorItem(player, repairAmount, EquipmentSlotType.HEAD);
				ext.bankedInfusionHelm -= repairAmount;
			}

			if (ext.bankedInfusionChest > 1){
				int repairAmount = (int)Math.floor(ext.bankedInfusionChest);
				RepairEquippedArsMagicaArmorItem(player, repairAmount, EquipmentSlotType.CHEST);
				ext.bankedInfusionChest -= repairAmount;
			}

			if (ext.bankedInfusionLegs > 1){
				int repairAmount = (int)Math.floor(ext.bankedInfusionLegs);
				RepairEquippedArsMagicaArmorItem(player, repairAmount, EquipmentSlotType.LEGS);
				ext.bankedInfusionLegs -= repairAmount;
			}

			if (ext.bankedInfusionBoots > 1){
				int repairAmount = (int)Math.floor(ext.bankedInfusionBoots);
				RepairEquippedArsMagicaArmorItem(player, repairAmount, EquipmentSlotType.FEET);
				ext.bankedInfusionBoots -= repairAmount;
			}
		}
	}
	
	private static void RepairEquippedArsMagicaArmorItem(PlayerEntity player, int repairAmount, EquipmentSlotType slot){
		if (!PlayerHasArmorInSlot(player, slot)){
			return;
		}
		ItemStack armor = player.getItemStackFromSlot(slot);
		if (armor.isItemDamaged()){
			armor.setItemDamage(armor.getItemDamage() - repairAmount);
		}
	}

	private static boolean PlayerHasArsInfusableInSlot(PlayerEntity player, EquipmentSlotType armorSlot){
		return (player.getItemStackFromSlot(armorSlot) != null && (player.getItemStackFromSlot(armorSlot).getItem() instanceof AMArmor));
	}

	private static float GetArsMagicaArmorInfusionCostFromSlot(PlayerEntity player, EquipmentSlotType armorSlot){
		if (!PlayerHasArsInfusableInSlot(player, armorSlot)){
			return 0;
		}
		if (player.getItemStackFromSlot(armorSlot).isItemDamaged()){
			AMArmor armor = (AMArmor)player.getItemStackFromSlot(armorSlot).getItem();
			return armor.GetInfusionCost();
		}
		return 0;
	}

	public static int getFullArsMagicaArmorSet(PlayerEntity player){
		int matlID = -1;
		for (EquipmentSlotType slot : EquipmentSlotType.values()){
			if (slot.getSlotType() != EquipmentSlotType.Type.ARMOR)
				continue;
			if (!PlayerHasArsInfusableInSlot(player, slot)){
				return -1;
			}
			AMArmor armor = (AMArmor)player.getItemStackFromSlot(slot).getItem();
			if (matlID == -1){
				matlID = armor.getMaterialID();
			}else{
				if (matlID != armor.getMaterialID()){
					return -1;
				}
			}
		}
		return matlID;
	}
	
	private static float GetArsMagicaArmorRepairAmountFromSlot(PlayerEntity player, EquipmentSlotType armorSlot){
		if (!PlayerHasArsInfusableInSlot(player, armorSlot)){
			return 0;
		}
		if (player.getItemStackFromSlot(armorSlot).isDamaged()){
			AMArmor armor = (AMArmor)player.getItemStackFromSlot(armorSlot).getItem();
			return armor.GetInfusionRepair();
		}
		return 0;
	}


	//======================================================================================================
	// Armor Effects
	//======================================================================================================
//	public static void HandleArmorEffects(PlayerEntity player, World world){
//		if (world.isRemote){
//			return;
//		}
//		EntityExtension EntityExtension = EntityExtension.For(player);
//
//		//decrement counters
//		for (int i = 0; i < EntityExtension.armorProcCooldowns.length; ++i){
//			if (EntityExtension.armorProcCooldowns[i] > 0)
//				EntityExtension.armorProcCooldowns[i]--;
//		}
//	}

//	private static ItemStack GetAMProtectiveArmorInSlot(PlayerEntity player, int armorSlot){
//		ItemStack[] armor = player.inventory.armorInventory;
//		if (armor[armorSlot] != null){
//			for (Item i : ItemDefs.protectiveArmors){
//				if (armor[armorSlot].getItem() == i){
//					return armor[armorSlot];
//				}
//			}
//		}
//		return null;
//	}

	public static ArmorImbuement[] getInfusionsOnArmor(PlayerEntity player, EquipmentSlotType armorSlot){
		ItemStack stack = player.getItemStackFromSlot(armorSlot);
		return getInfusionsOnArmor(stack);
	}

	public static ArmorImbuement[] getInfusionsOnArmor(ItemStack stack){
		if (stack == null || !stack.hasTag() || !(stack.getItem() instanceof ArmorItem))
			return new ArmorImbuement[0];
		CompoundNBT armorProps = (CompoundNBT) stack.getTagCompound().getTag(AMArmor.NBT_KEY_AMPROPS);
		if (armorProps != null){
			String infusionList = armorProps.getString(AMArmor.NBT_KEY_EFFECTS);
			if (infusionList != null && infusionList != ""){
				String[] ids = infusionList.split(AMArmor.INFUSION_DELIMITER);
				ArmorImbuement[] infusions = new ArmorImbuement[ids.length];
				for (int i = 0; i < ids.length; ++i){
					infusions[i] = ImbuementRegistry.instance.getImbuementByID(new ResourceLocation(ids[i]));
				}
				return infusions;
			}
		}
		return new ArmorImbuement[0];
	}

	public static boolean isInfusionPreset(ItemStack stack, String id){
		if (stack == null || !stack.hasTagCompound())
			return false;
		CompoundNBT armorProps = (CompoundNBT)stack.getTagCompound().getTag(AMArmor.NBT_KEY_AMPROPS);
		if (armorProps != null){
			String infusionList = armorProps.getString(AMArmor.NBT_KEY_EFFECTS);
			if (infusionList != null){
				return infusionList.contains(id);
			}
		}
		return false;
	}

	public static void imbueArmor(ItemStack armorStack, ResourceLocation id, boolean ignoreLevelRequirement){
		ArmorImbuement imbuement = ImbuementRegistry.instance.getImbuementByID(id);
		if (armorStack != null && imbuement != null && armorStack.getItem() instanceof ItemArmor){

			if (!ignoreLevelRequirement && getArmorLevel(armorStack) < getImbueCost(imbuement.getTier()))
				return;

			for (EquipmentSlotType i : imbuement.getValidSlots()){
				if (i == ((ItemArmor)armorStack.getItem()).armorType){
					if (!armorStack.hasTagCompound())
						armorStack.setTagCompound(new CompoundNBT());
					CompoundNBT armorProps = (CompoundNBT)armorStack.getTagCompound().getTag(AMArmor.NBT_KEY_AMPROPS);
					if (armorProps == null)
						armorProps = new CompoundNBT();
					String infusionList = armorProps.getString(AMArmor.NBT_KEY_EFFECTS);
					if (infusionList == null || infusionList == "")
						infusionList = id.toString();
					else
						infusionList += "|" + id;
					armorProps.putString(AMArmor.NBT_KEY_EFFECTS, infusionList);
					armorStack.getTagCompound().setTag(AMArmor.NBT_KEY_AMPROPS, armorProps);

					deductXPFromArmor(EntityUtils.getXPFromLevel(getImbueCost(imbuement.getTier())), armorStack);
					break;
				}
			}
		}
	}

	public static int getArmorLevel(ItemStack stack){
		if (stack == null || !stack.hasTag())
			return 0;
		CompoundNBT armorProps = (CompoundNBT)stack.getTagCompound().getTag(AMArmor.NBT_KEY_AMPROPS);
		if (armorProps != null){
			return armorProps.getInt(AMArmor.NBT_KEY_ARMORLEVEL);
		}
		return 0;
	}

	public static int getImbueCost(ImbuementTiers tier){
		return 30 + tier.ordinal() * IMBUE_TIER_COST;
	}

	public static void addXPToArmor(float totalAmt, PlayerEntity player){
		int numPieces = 0;
		for (EquipmentSlotType slot : EquipmentSlotType.values()) {
			if (slot.getSlotType() != EquipmentSlotType.Type.ARMOR)
				continue;
			if (player.getItemStackFromSlot(slot) != null)
				numPieces++;
		}
		float xpPerPiece = totalAmt / numPieces;
		for (EquipmentSlotType slot : EquipmentSlotType.values()){
			if (slot.getSlotType() != EquipmentSlotType.Type.ARMOR)
				continue;
			addXPToArmor(xpPerPiece, player.getItemStackFromSlot(slot));
		}
	}

	public static void deductXPFromArmor(float amt, ItemStack armor){
		if (armor != null && armor.getItem() instanceof ItemArmor){
			if (!armor.hasTag())
				armor.setTag(new CompoundNBT());
			CompoundNBT armorProps = (CompoundNBT)armor.getTagCompound().getTag(AMArmor.NBT_KEY_AMPROPS);
			if (armorProps == null)
				armorProps = new CompoundNBT();
			armorProps.putDouble(AMArmor.NBT_KEY_TOTALXP, Math.max(armorProps.getDouble(AMArmor.NBT_KEY_TOTALXP) - amt, 0));
			armorProps.putInt(AMArmor.NBT_KEY_ARMORLEVEL, EntityUtils.getLevelFromXP((float)armorProps.getDouble(AMArmor.NBT_KEY_TOTALXP)));
			armor.getTagCompound().setTag(AMArmor.NBT_KEY_AMPROPS, armorProps);
		}
	}

	public static void addXPToArmor(float amt, ItemStack armor){
		if (armor != null && armor.getItem() instanceof ItemArmor){
			if (!armor.hasTag())
				armor.setTag(new CompoundNBT());
			CompoundNBT armorProps = (CompoundNBT)armor.getTagCompound().getTag(AMArmor.NBT_KEY_AMPROPS);
			if (armorProps == null)
				armorProps = new CompoundNBT();
			armorProps.putDouble(AMArmor.NBT_KEY_TOTALXP, armorProps.getDouble(AMArmor.NBT_KEY_TOTALXP) + amt);
			armorProps.putInt(AMArmor.NBT_KEY_ARMORLEVEL, EntityUtils.getLevelFromXP((float)armorProps.getDouble(AMArmor.NBT_KEY_TOTALXP)));
			armor.getTagCompound().setTag(AMArmor.NBT_KEY_AMPROPS, armorProps);
		}
	}
}

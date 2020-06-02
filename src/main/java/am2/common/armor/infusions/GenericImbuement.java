package am2.common.armor.infusions;

import java.util.EnumSet;
import java.util.UUID;

import am2.api.items.armor.ArmorImbuement;
import am2.api.items.armor.ImbuementApplicationTypes;
import am2.api.items.armor.ImbuementTiers;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GenericImbuement extends ArmorImbuement{

	private String id = "";
	private ImbuementTiers tier;
	private EquipmentSlotType[] validSlots;

	public static final UUID imbuedHasteID = UUID.fromString("3b51a94c-8866-470b-8b69-e1d5cb50a72f");
	public static final AttributeModifier imbuedHaste = new AttributeModifier(imbuedHasteID, "Imbued Haste", 0.2, 2);

	public static final String manaRegen = "mn_reg";
	public static final String burnoutReduction = "bn_red";
	public static final String flickerLure = "fl_lure";
	public static final String magicXP = "mg_xp";
	public static final String pinpointOres = "pp_ore";
	public static final String magitechGoggleIntegration = "mg_gog";
	public static final String thaumcraftNodeReveal = "tc_nrv";
	public static final String stepAssist = "step_up";
	public static final String runSpeed = "run_spd";
	public static final String soulbound = "soulbnd";

	public GenericImbuement(String id, ImbuementTiers tier, EquipmentSlotType[] validSlots){
		this.id = id;
		this.tier = tier;
		this.validSlots = validSlots;
	}

	public static void registerAll(){
		//all armors
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(manaRegen, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.CHEST, EquipmentSlotType.HEAD}));
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(burnoutReduction, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.CHEST, EquipmentSlotType.HEAD}));
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(soulbound, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.CHEST, EquipmentSlotType.HEAD}));

		//chest
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(flickerLure, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.CHEST}));
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(magicXP, ImbuementTiers.FOURTH, new EquipmentSlotType[]{EquipmentSlotType.CHEST}));

		//helmet
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(pinpointOres, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.HEAD}));
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(magitechGoggleIntegration, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.HEAD}));
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(thaumcraftNodeReveal, ImbuementTiers.FOURTH, new EquipmentSlotType[]{EquipmentSlotType.HEAD}));

		//legs
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(stepAssist, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.LEGS}));

		//boots
		ImbuementRegistry.instance.registerImbuement(new GenericImbuement(runSpeed, ImbuementTiers.FIRST, new EquipmentSlotType[]{EquipmentSlotType.FEET}));
	}

	@Override
	public String getID(){
		return id;
	}

	@Override
	public ImbuementTiers getTier(){
		return tier;
	}

	@Override
	public EnumSet<ImbuementApplicationTypes> getApplicationTypes(){
		return EnumSet.of(ImbuementApplicationTypes.NONE);
	}

	@Override
	public boolean applyEffect(PlayerEntity player, World world, ItemStack stack, ImbuementApplicationTypes matchedType, Object... params){
		return false;
	}

	@Override
	public EquipmentSlotType[] getValidSlots(){
		return validSlots;
	}

	@Override
	public boolean canApplyOnCooldown(){
		return true;
	}

	@Override
	public int getCooldown(){
		return 0;
	}

	@Override
	public int getArmorDamage(){
		return 0;
	}
}

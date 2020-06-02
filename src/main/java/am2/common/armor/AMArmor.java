package am2.common.armor;

import am2.common.defs.CreativeTabsDefs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMArmor extends ArmorItem implements ISpecialArmor{

	private static final int maxDamageArray[] = {
			11, 16, 15, 13
	};
	public final EquipmentSlotType armorType;
	public final int damageReduceAmount;
	private final ArsMagicaArmorMaterial material;
	private final int damageReduction;
	private final float infusionCost;
	private final float infusionRepair;

	public static final String NBT_KEY_AMPROPS = "AMArmorProperties";
	public static final String NBT_KEY_EFFECTS = "armorEffects";
	public static final String NBT_KEY_TOTALXP = "infusedXP";
	public static final String NBT_KEY_ARMORLEVEL = "XPLevel";
	public static final String INFUSION_DELIMITER = "\\|";

	public AMArmor(ArmorMaterial inheritFrom, ArsMagicaArmorMaterial enumarmormaterial, int par3, EquipmentSlotType par4){
		super(inheritFrom, par3, par4);
		material = enumarmormaterial;
		armorType = par4;
		damageReduceAmount = 0;
		setMaxDamage(enumarmormaterial.getMaxDamage(par4));
		setCreativeTab(CreativeTabsDefs.tabAM2Items);
		maxStackSize = 1;
		damageReduction = enumarmormaterial.getDamageReductionAmount(par4);
		infusionCost = enumarmormaterial.getInfusionCost();
		infusionRepair = enumarmormaterial.getInfusionRepair();
	}

	public AMArmor registerAndName(String name) {
		this.setUnlocalizedName("arsmagica2:" + name);
		GameRegistry.register(this, new ResourceLocation("arsmagica2", name));
		return this;
	}

	@Override
	public int getItemEnchantability(){
		return material.getEnchantability();
	}

	static int[] getMaxDamageArray(){
		return maxDamageArray;
	}

	public int GetDamageReduction(){
		return damageReduction;
	}

	public float GetInfusionCost(){
		return infusionCost;
	}

	public float GetInfusionRepair(){
		return infusionRepair;
	}

	public int getMaterialID(){
		return material.getMaterialID();
	}

	@Override
	public int getArmorDisplay(PlayerEntity player, ItemStack armor, int slot){
		return GetDamageReduction();
	}

	@Override
	public ArmorProperties getProperties(PlayerEntity player, ItemStack armor, DamageSource source, double damage, int slot){
		ArmorProperties ap = new ArmorProperties(1, material.getDamageReduceRatio(slot), 1000);
		return ap;
	}

	@Override
	public void damageArmor(PlayerEntity entity, ItemStack stack, DamageSource source, int damage, int slot){
		if (source == DamageSource.IN_FIRE){
			stack.damageItem(damage * 7, entity);
		}else if (source == DamageSource.FALL || source == DamageSource.IN_WALL || source == DamageSource.DROWN || source == DamageSource.STARVE){
			return;
		}else if (source == DamageSource.MAGIC){
			stack.damageItem(damage * 7, entity);
		}else if (source.isUnblockable()){
			return;
		}
		else{
			stack.damageItem(damage * 10, entity);
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type){
//		int armorType = -1;
//		if (stack.getItem() instanceof AMArmor){
//			armorType = ((AMArmor)stack.getItem()).renderIndex;
//		}
//
//		ArmorTextureEvent event = new ArmorTextureEvent(slot, armorType);
//		MinecraftForge.EVENT_BUS.post(event);

		return null; //event.texture;
	}

	@Override
	public boolean hasEffect(ItemStack stack){
		if (stack.hasTag() && stack.getTag().contains(NBT_KEY_AMPROPS)){
			String s = ((NBTTagCompound)stack.getTagCompound().getTag(NBT_KEY_AMPROPS)).getString(NBT_KEY_EFFECTS);
			return s != null && s.length() > 0;
		}
		return super.hasEffect(stack);

	}
}

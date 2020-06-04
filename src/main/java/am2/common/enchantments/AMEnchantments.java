package am2.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AMEnchantments{
	public static EnchantMagicResist magicResist = new EnchantMagicResist(Rarity.COMMON);
	public static EnchantmentSoulbound soulbound = new EnchantmentSoulbound(Rarity.RARE);

	@SubscribeEvent
	public static void Init(RegistryEvent.Register<Enchantment> ev)
	{
		ev.getRegistry().register(magicResist);  //, new ResourceLocation("arsmagica2", "magicResist"));
		ev.getRegistry().register(soulbound);//, new ResourceLocation("arsmagica2", "soulbound"));
	}

	public static int GetEnchantmentLevelSpecial(Enchantment ench, ItemStack stack){
		int baseEnchLvl = EnchantmentHelper.getEnchantmentLevel(ench, stack);
		/*if (enchID == imbuedArmor.effectId || enchID == imbuedBow.effectId || enchID == imbuedWeapon.effectId){
			if (baseEnchLvl > 3)
				return (baseEnchLvl & 0x6000) >> 13;
		}*/
		return baseEnchLvl;
	}
}

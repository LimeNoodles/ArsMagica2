package am2.common.defs;

import java.util.List;

import am2.api.ArsMagicaAPI;
import am2.api.affinity.Affinity;
import am2.api.flickers.AbstractFlickerFunctionality;
import am2.common.LogHelper;
import am2.common.ObeliskFuelHelper;
import am2.common.blocks.BlockArsMagicaBlock.EnumBlockType;
import am2.common.blocks.BlockArsMagicaOre.EnumOreType;
import am2.common.blocks.BlockCrystalMarker;
import am2.common.items.ItemBindingCatalyst;
import am2.common.items.ItemCore;
import am2.common.items.ItemKeystoneDoor;
import am2.common.items.ItemOre;
import am2.common.utils.RecipeUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class AMRecipes {
	//TODO public static void addRecipes() {
}

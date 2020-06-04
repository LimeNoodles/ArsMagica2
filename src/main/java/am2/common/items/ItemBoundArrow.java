package am2.common.items;

import java.util.List;

import am2.api.extensions.ISpellCaster;
import am2.common.entity.EntityBoundArrow;
import am2.common.spell.SpellCaster;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBoundArrow extends ItemArrow {
	
	public ItemBoundArrow() {
		setCreativeTab(null);
	}
	
	//todo @Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
	}
	
	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		EntityBoundArrow arrow = new EntityBoundArrow(worldIn, shooter);

		ISpellCaster caster = stack.getCapability(SpellCaster.INSTANCE, null);
		if (caster != null)
			arrow.setSpellStack(caster.createSpellData(stack.copy()));
		return arrow;
	}
	
	public ItemBoundArrow registerAndName(String name) {
		this.setUnlocalizedName(new ResourceLocation("arsmagica2", name).toString());
		//todo GameRegistry.register(this, new ResourceLocation("arsmagica2", name));
		return this;
	}
}

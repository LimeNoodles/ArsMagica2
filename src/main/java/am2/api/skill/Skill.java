package am2.api.skill;

import am2.api.ArsMagicaAPI;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public class Skill implements IForgeRegistryEntry{
	
	private int posX, posY;
	private SkillTree tree;
	private String[] parents;
	private ResourceLocation icon;
	private SkillPoint point;
	
	public Skill(ResourceLocation icon, SkillPoint point, int posX, int posY, SkillTree tree, String... string) {
		this.posX = posX;
		this.posY = posY;
		this.tree = tree;
		this.parents = string;
		this.icon = icon;
		this.point = point;
	}
	
	public Skill(String string, ResourceLocation icon, SkillPoint point, int posX, int posY, SkillTree tree, String... strings) {
		this(icon, point, posX, posY, tree, strings);
		this.setRegistryName(new ResourceLocation(ArsMagicaAPI.getCurrentModId(), string));
	}

	public String getID() {
		return getRegistryName().toString();
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public ResourceLocation getIcon() {
		return icon;
	}
	
	public SkillTree getTree() {
		return tree;
	}
	
	public String[] getParents() {
		return parents;
	}
	
	public void writeToNBT (CompoundNBT tag) {
		tag.put("ID", getID());
	}
	
	public SkillPoint getPoint() {
		return point;
	}
	
	@Override
	public String toString() {
		return getID();
	}
	
	public String getName() {
		return I18n.format("skill." + getID() + ".name");
	}
	
	public String getOcculusDesc() {
		return I18n.format("skill." + getID() + ".occulusdesc");
	}

	@Override
	public Object setRegistryName(ResourceLocation name) {
		return null;
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return null;
	}

	@Override
	public Class getRegistryType() {
		return null;
	}
}

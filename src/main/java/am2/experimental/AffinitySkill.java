package am2.experimental;

import am2.api.affinity.Affinity;
import am2.api.skill.SkillPoint;
import am2.extensions.AffinityData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

@SuppressWarnings("deprecation")
public class AffinitySkill extends IForgeRegistryEntry.Impl<AffinitySkill> {
	
	private final Affinity tree;
	private final double minDepth;
	private final SkillPoint point;
	private final IAffinitySkillAction action;

	public AffinitySkill(Affinity tree, double minDepth, SkillPoint point, IAffinitySkillAction action) {
		this.tree = tree;
		this.minDepth = minDepth;
		this.point = point;
		this.action = action;
	}
	
	public Affinity getTree() {
		return tree;
	}
	
	public double getMinDepth() {
		return minDepth;
	}
	
	public IAffinitySkillAction getAction() {
		return action;
	}
	
	public SkillPoint getPoint() {
		return point;
	}
	
	public String getName() {
		return I18n.translateToLocal("affinity_skill." + getRegistryName().toString() + ".name");
	}
	
	public String getDesc() {
		return I18n.translateToLocal("affinity_skill." + getRegistryName().toString() + ".desc");
	}
	
	public boolean canApply(EntityPlayer player, Class<? extends Event> clazz) {
		if (AffinityData.For(player).getAffinityDepth(tree) < minDepth) return false;
		return action.canApply(player, clazz);
	}
}

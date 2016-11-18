package am2.experimental;

import am2.LogHelper;
import am2.api.ArsMagicaAPI.ObjectCallbacks;
import am2.api.affinity.Affinity;
import am2.experimental.skills.AffinitySkillDefs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.PersistentRegistryManager;

/**
 * ArsMagica 2 Experimental is a module for AM2 that contains all the unfinished code<BR>
 * It will include mostly all the combined affinity tweaks.
 * 
 * @author EdwinMindcraft
 * @see {@link Affinity} for the combined affinity feature.
 */
public class AM2Experimental {
	
	private static final ObjectCallbacks<AffinitySkill> AFFINITY_SKILL_CALLBACKS = new ObjectCallbacks<>();
	private static final FMLControlledNamespacedRegistry<AffinitySkill> AFFINITY_SKILLS_REGISTRY = PersistentRegistryManager.createRegistry(new ResourceLocation("arsmagica2", "affinity_skills"), AffinitySkill.class, null, 0, Short.MAX_VALUE, false, AFFINITY_SKILL_CALLBACKS, AFFINITY_SKILL_CALLBACKS, AFFINITY_SKILL_CALLBACKS);
	public static FMLControlledNamespacedRegistry<AffinitySkill> getAffinitySkillsRegistry() {return AFFINITY_SKILLS_REGISTRY;}
	
	static {
		new CombinedAffinities();
		new AffinitySkillDefs();
		LogHelper.info("Finished experimental initialization.");
	}
	
}

package am2.common.affinity.abilities;

import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class AbilityPacifist extends AbstractAffinityAbility {

	public AbilityPacifist() {
		super(new ResourceLocation("arsmagica2", "pacifist"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.6f;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.LIFE;
	}
	
	@Override
	public void applyKill(PlayerEntity player, LivingDeathEvent event) {
		if (event.getEntityLiving().getCreatureAttribute() != CreatureAttribute.UNDEAD) {
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 100, 1));
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("hunger"), 40, 1));
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("mining_fatigue"), 100, 1));
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 100, 1));
		}
	}

}

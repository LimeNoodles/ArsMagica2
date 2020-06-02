package am2.common.affinity;

import java.util.Map.Entry;

import am2.ArsMagica2;
import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;
import am2.api.event.SpellCastEvent;
import am2.common.affinity.abilities.AbilityAgile;
import am2.common.affinity.abilities.AbilityAntiEndermen;
import am2.common.affinity.abilities.AbilityClearCaster;
import am2.common.affinity.abilities.AbilityColdBlooded;
import am2.common.affinity.abilities.AbilityExpandedLungs;
import am2.common.affinity.abilities.AbilityFastHealing;
import am2.common.affinity.abilities.AbilityFirePunch;
import am2.common.affinity.abilities.AbilityFireResistance;
import am2.common.affinity.abilities.AbilityFireWeakness;
import am2.common.affinity.abilities.AbilityFluidity;
import am2.common.affinity.abilities.AbilityFulmination;
import am2.common.affinity.abilities.AbilityLavaFreeze;
import am2.common.affinity.abilities.AbilityLeafLike;
import am2.common.affinity.abilities.AbilityLightAsAFeather;
import am2.common.affinity.abilities.AbilityLightningStep;
import am2.common.affinity.abilities.AbilityMagicWeakness;
import am2.common.affinity.abilities.AbilityNightVision;
import am2.common.affinity.abilities.AbilityOneWithMagic;
import am2.common.affinity.abilities.AbilityPacifist;
import am2.common.affinity.abilities.AbilityPhotosynthesis;
import am2.common.affinity.abilities.AbilityPoisonResistance;
import am2.common.affinity.abilities.AbilityReflexes;
import am2.common.affinity.abilities.AbilityRelocation;
import am2.common.affinity.abilities.AbilityRooted;
import am2.common.affinity.abilities.AbilityShortCircuit;
import am2.common.affinity.abilities.AbilitySolidBones;
import am2.common.affinity.abilities.AbilitySunlightWeakness;
import am2.common.affinity.abilities.AbilitySwiftSwim;
import am2.common.affinity.abilities.AbilityThorns;
import am2.common.affinity.abilities.AbilityThunderPunch;
import am2.common.affinity.abilities.AbilityWaterFreeze;
import am2.common.affinity.abilities.AbilityWaterWeakness;
import am2.common.extensions.AffinityData;
import am2.common.packet.AMDataWriter;
import am2.common.packet.AMNetHandler;
import am2.common.packet.AMPacketIDs;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AffinityAbilityHelper {
	
	static {
		//AIR
		GameRegistry.register(new AbilityLightAsAFeather());
		GameRegistry.register(new AbilityAgile());
		
		//ARCANE
		GameRegistry.register(new AbilityClearCaster());
		GameRegistry.register(new AbilityMagicWeakness());
		GameRegistry.register(new AbilityOneWithMagic());
		
		//EARTH
		GameRegistry.register(new AbilitySolidBones());
		
		//ENDER
		GameRegistry.register(new AbilityRelocation());
		GameRegistry.register(new AbilityNightVision());
		GameRegistry.register(new AbilityWaterWeakness(Affinity.ENDER));
		GameRegistry.register(new AbilityPoisonResistance());
		GameRegistry.register(new AbilitySunlightWeakness());
		
		//FIRE
		GameRegistry.register(new AbilityFireResistance());
		GameRegistry.register(new AbilityFirePunch());
		GameRegistry.register(new AbilityWaterWeakness(Affinity.FIRE));
		
		//ICE
		GameRegistry.register(new AbilityLavaFreeze());
		GameRegistry.register(new AbilityWaterFreeze());
		GameRegistry.register(new AbilityColdBlooded());
		
		//LIFE
		GameRegistry.register(new AbilityFastHealing());
		GameRegistry.register(new AbilityPacifist());
		
		//WATER
		GameRegistry.register(new AbilityExpandedLungs());
		GameRegistry.register(new AbilityFluidity());
		GameRegistry.register(new AbilitySwiftSwim());
		GameRegistry.register(new AbilityFireWeakness());
		GameRegistry.register(new AbilityAntiEndermen());
		
		//NATURE
		GameRegistry.register(new AbilityRooted());
		GameRegistry.register(new AbilityThorns());
		GameRegistry.register(new AbilityLeafLike());
		GameRegistry.register(new AbilityPhotosynthesis());
		
		//LIGHTNING
		GameRegistry.register(new AbilityLightningStep());
		GameRegistry.register(new AbilityReflexes());
		GameRegistry.register(new AbilityFulmination());
		GameRegistry.register(new AbilityShortCircuit());
		GameRegistry.register(new AbilityThunderPunch());
		GameRegistry.register(new AbilityWaterWeakness(Affinity.LIGHTNING));
	}
	
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
			if (ability.getKey() != null && ability.getKey().isPressed()) {
				PlayerEntity player = ArsMagica2.proxy.getLocalPlayer();
				if (ability.canApply(player)) {
					AMDataWriter syncPacket = new AMDataWriter();
					syncPacket.add(player.getEntityId());
					syncPacket.add(ability.getRegistryName().toString());
					ability.applyKeyPress(player);
					AMNetHandler.INSTANCE.sendPacketToServer(AMPacketIDs.KEY_ABILITY_PRESS, syncPacket.generate());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(LivingUpdateEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			if (!event.getEntityLiving().world.isRemote) {
				for (Entry<String, Integer> entry : AffinityData.For(event.getEntityLiving()).getCooldowns().entrySet()) {
					if (entry.getValue() > 0)
						AffinityData.For(event.getEntityLiving()).addCooldown(entry.getKey(), entry.getValue() - 1);
				}
			}
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.getEntityLiving()))
					ability.applyTick((PlayerEntity) event.getEntityLiving());
				else
					ability.removeEffects((PlayerEntity) event.getEntityLiving());
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.getEntityLiving()))
					ability.applyHurt((PlayerEntity) event.getEntityLiving(), event, false);
			}
		}
		if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.getSource().getEntity()))
					ability.applyHurt((PlayerEntity) event.getSource().getEntity(), event, true);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerFall(LivingFallEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.getEntityLiving()))
					ability.applyFall((PlayerEntity) event.getEntityLiving(), event);
			}
		}
	}
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.getEntityLiving()))
					ability.applyDeath((PlayerEntity) event.getEntityLiving(), event);
			}
		}
		if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.getSource().getEntity()))
					ability.applyKill((PlayerEntity) event.getSource().getEntity(), event);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerJump(LivingJumpEvent event) {
		if (event.getEntityLiving() instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.getEntityLiving()))
					ability.applyJump((PlayerEntity) event.getEntityLiving(), event);
			}
		}
	}
	
	@SubscribeEvent
	public void onSpellCast(SpellCastEvent.Post event) {
		if (event.entityLiving instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.entityLiving))
					ability.applySpellCast((PlayerEntity) event.entityLiving, event);
			}
		}
	}
	
	@SubscribeEvent
	public void onPreSpellCast(SpellCastEvent.Pre event) {
		if (event.entityLiving instanceof PlayerEntity) {
			for (AbstractAffinityAbility ability : GameRegistry.findRegistry(AbstractAffinityAbility.class).getValues()) {
				if (ability.canApply((PlayerEntity) event.entityLiving))
					ability.applyPreSpellCast((PlayerEntity) event.entityLiving, event);
			}
		}
	}
}

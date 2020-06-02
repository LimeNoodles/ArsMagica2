package am2.api.event;

import am2.api.skill.Skill;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SkillLearnedEvent extends PlayerEvent{
	
	protected final Skill skill;

	public SkillLearnedEvent(PlayerEntity player, Skill skill) {
		super(player);
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}

}

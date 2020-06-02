package am2.common.affinity.abilities;

import java.util.List;

import am2.ArsMagica2;
import am2.api.affinity.AbstractAffinityAbility;
import am2.api.affinity.Affinity;
import am2.common.extensions.AffinityData;

import net.minecraft.block.Blocks;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class AbilityFulmination extends AbstractAffinityAbility {

	public AbilityFulmination() {
		super(new ResourceLocation("arsmagica2", "fulmination"));
	}

	@Override
	public float getMinimumDepth() {
		return 0.5f;
	}
	
	@Override
	public float getMaximumDepth() {
		return 0.95F;
	}

	@Override
	public Affinity getAffinity() {
		return Affinity.LIGHTNING;
	}

	@Override
	public void applyTick(PlayerEntity player) {
		applyFulmintion(player, AffinityData.For(player).getAffinityDepth(getAffinity()));
	}
	
	@SuppressWarnings("unchecked")
	private void applyFulmintion(PlayerEntity ent, double lightningDepth){
		//chance to light nearby TNT
		if (!ent.world.isRemote){
			if (lightningDepth <= 0.8f){
				BlockPos offsetPos = new BlockPos(ent.getPosX() - 5 + ent.getRNG().nextInt(11), ent.getPosY() - 5 + ent.getRNG().nextInt(11), ent.getPosZ() - 5 + ent.getRNG().nextInt(11));
				IForgeBlockState block = ent.world.getBlockState(offsetPos);
				if (block.getBlockState().getBlock() == Blocks.TNT){
					ent.world.setBlockToAir(offsetPos);
					//TODO ((TNTBlock)Blocks.TNT).explode(ent.world, offsetPos, ent);
				}
			}
			//chance to supercharge nearby creepers
			if (lightningDepth >= 0.7f && ent.getRNG().nextDouble() < 0.05f){
				List<CreeperEntity> creepers = ent.world.getEntitiesWithinAABB(CreeperEntity.class, ent.getBoundingBox().expand(5, 5, 5));
				for (CreeperEntity creeper : creepers){
					try {
						creeper.getDataManager().set((DataParameter<Boolean>) ObfuscationReflectionHelper.findField(CreeperEntity.class, "POWERED", "field_184714_b").get(creeper), true);
					} catch (IllegalArgumentException | IllegalAccessException e) {
					}
					ArsMagica2.proxy.particleManager.BoltFromEntityToEntity(ent.world, ent, ent, creeper, 0, 1, -1);
				}
			}
		}
	}
}

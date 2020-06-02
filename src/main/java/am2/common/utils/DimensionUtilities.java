package am2.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import am2.common.blocks.tileentity.TileEntityAstralBarrier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DimensionUtilities{
	
	private static final HashMap<Integer, ArrayList<TileEntityAstralBarrier>> barrierMap = new HashMap<>();
	
	public static void doDimensionTransfer(PlayerEntity entity, int dimension){

		if (entity instanceof PlayerEntity){
			PlayerEntity player = (PlayerEntity)entity;
			new AMTeleporter(player.mcServer.worldServerForDimension(dimension)).teleport(entity);
		}else{
			entity.world.getProfiler().startSection("changeDimension");
			MinecraftServer minecraftserver = FMLCommonHandler.instance().getMinecraftServerInstance();
			int j = entity.dimension;
			WorldServer worldserver = minecraftserver.worldServerForDimension(j);
			WorldServer worldserver1 = minecraftserver.worldServerForDimension(dimension);
			entity.dimension = dimension;
			entity.world.removeEntity(entity);
			entity.setDe.isDead = false;
			entity.world.theProfiler.startSection("reposition");
			minecraftserver.getPlayerList().transferEntityToWorld(entity, j, worldserver, worldserver1, new AMTeleporter(worldserver1));
			entity.world.theProfiler.endStartSection("reloading");
			Entity e = EntityList.createEntityByName(EntityList.getEntityString(entity), worldserver1);

			if (e != null){
				e.readFromNBT(entity.serializeNBT().put(new CompoundNBT()));
				worldserver1.spawnEntityInWorld(e);
			}

			entity.isDead = true;
			entity.world.theProfiler.endSection();
			worldserver.resetUpdateEntityTick();
			worldserver1.resetUpdateEntityTick();
			entity.world.theProfiler.endSection();
		}
	}
	
	public static void registerAstralBarrier(TileEntityAstralBarrier barrier) {
		int dimension = barrier.getWorld().provider.getDimension();
		ArrayList<TileEntityAstralBarrier> barriers = barrierMap.get(dimension);
		if (barriers == null) {
			barriers = new ArrayList<>();
			barrierMap.put(dimension, barriers);
		}
		if (!barriers.contains(barrier))
			barriers.add(barrier);
	}
	
	public static void invalidateAstralBarrier(TileEntityAstralBarrier barrier) {
		int dimension = barrier.getWorld().provider.getDimension();
		ArrayList<TileEntityAstralBarrier> barriers = barrierMap.get(dimension);
		if (barriers == null)
			return;
		if (barriers.contains(barrier))
			barriers.remove(barrier);
	}

	public static TileEntityAstralBarrier GetBlockingAstralBarrier(World world, BlockPos pos, ArrayList<Long> keys) {
		int dimension = world.provider.getDimension();
		ArrayList<TileEntityAstralBarrier> barriers = barrierMap.get(dimension);
		if (barriers == null) {
			barriers = new ArrayList<>();
			barrierMap.put(dimension, barriers);
		}
		AxisAlignedBB aabb = new AxisAlignedBB(pos).expandXyz(20.01); // .01 Because isVecInside is a strict inequality.
		Iterator<TileEntityAstralBarrier> iter = barriers.iterator();
		while (iter.hasNext()) {
			TileEntityAstralBarrier barrier = iter.next();
			if (barrier == null || barrier.getPos() == null) {
				//Invalid TE
				iter.remove();
				continue;
			}
			
			//Checks if any of the keystone in inventory are valid for the barrier.
			long barrierKey = KeystoneUtilities.instance.getKeyFromRunes(barrier.getRunesInKey());
			if ((barrierKey != 0 && keys.contains(barrierKey)) || !barrier.IsActive())
				continue;
			
			if (aabb.isVecInside(new Vec3d(barrier.getPos())))
				return barrier;
		}
		//Old code
		//check for Astral Barrier
//		for (int i = -20; i <= 20; ++i){
//			for (int j = -20; j <= 20; ++j){
//				for (int k = -20; k <= 20; ++k){
//					if (world.getBlockState(pos.add(i, j, k)).getBlock() == BlockDefs.astralBarrier){
//
//						TileEntity te = world.getTileEntity(pos.add(i, j, k));
//						if (te == null || !(te instanceof TileEntityAstralBarrier)){
//							continue;
//						}
//						TileEntityAstralBarrier barrier = (TileEntityAstralBarrier)te;
//
//						long barrierKey = KeystoneUtilities.instance.getKeyFromRunes(barrier.getRunesInKey());
//						if ((barrierKey != 0 && keys.contains(barrierKey)) || !barrier.IsActive()) continue;
//
//						int sqDist = (int) pos.distanceSq(barrier.getPos());
//
//						if (sqDist < (barrier.getRadius() * barrier.getRadius())) return barrier;
//					}
//				}
//			}
//		}
		return null;
	}
}

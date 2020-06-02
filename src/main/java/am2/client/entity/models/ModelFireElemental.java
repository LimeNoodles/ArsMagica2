package am2.client.entity.models;


import net.minecraft.client.renderer.entity.model.BipedModel;

@SideOnly(Side.CLIENT)
public class ModelFireElemental extends BipedModel
{
	public ModelFireElemental(){
		super();
		this.rightArmPose = BipedModel.ArmPose.ITEM;
	}
}

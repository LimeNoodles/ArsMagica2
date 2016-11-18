package am2.particles;

import java.util.HashMap;
import java.util.Random;

import am2.ArsMagica2;
import am2.api.ArsMagicaAPI;
import am2.api.affinity.Affinity;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AMParticleIcons{

	public static final AMParticleIcons instance = new AMParticleIcons();

	private final HashMap<String, TextureAtlasSprite> icons;
	private final HashMap<String, TextureAtlasSprite> hiddenIcons;
	private static final Random rand = new Random();

	private AMParticleIcons(){
		icons = new HashMap<String, TextureAtlasSprite>();
		hiddenIcons = new HashMap<String, TextureAtlasSprite>();
		MinecraftForge.EVENT_BUS.register(this);
	}

	public int numParticles(){
		return icons.size();
	}
	
	@SubscribeEvent
	public void init(TextureStitchEvent.Pre e){
		icons.clear();
		TextureMap textureMap = e.getMap();

		loadAndInitIcon("arcane", "arcane", textureMap);
		loadAndInitIcon("clock", "clock", textureMap);
		loadAndInitIcon("ember", "ember", textureMap);
		loadAndInitIcon("explosion_2", "explosion_2", textureMap);
		loadAndInitIcon("ghost", "ghost", textureMap);
		loadAndInitIcon("heart", "heart", textureMap);
		loadAndInitIcon("leaf", "leaf", textureMap);
		loadAndInitIcon("lens_flare", "lens_flare", textureMap);
		loadAndInitIcon("lights", "lights", textureMap);
		loadAndInitIcon("plant", "plant", textureMap);
		loadAndInitIcon("pulse", "pulse", textureMap);
		loadAndInitIcon("rock", "rock", textureMap);
		loadAndInitIcon("rotating_rings", "rotating_rings", textureMap);
		loadAndInitIcon("smoke", "smoke", textureMap);
		loadAndInitIcon("sparkle", "sparkle", textureMap);
		loadAndInitIcon("sparkle2", "sparkle2", textureMap);
		loadAndInitIcon("water_ball", "water_ball", textureMap);
		loadAndInitIcon("wind", "wind", textureMap);

//		loadAndInitIcon("air_hand", "air_hand", textureMap);
//		loadAndInitIcon("arcane_hand", "arcane_hand", textureMap);
//		loadAndInitIcon("earth_hand", "earth_hand", textureMap);
//		loadAndInitIcon("ender_hand", "ender_hand", textureMap);
//		loadAndInitIcon("fire_hand", "fire_hand", textureMap);
//		loadAndInitIcon("ice_hand", "ice_hand", textureMap);
//		loadAndInitIcon("life_hand", "life_hand", textureMap);
//		loadAndInitIcon("lightning_hand", "lightning_hand", textureMap);
//		loadAndInitIcon("nature_hand", "nature_hand", textureMap);
//		loadAndInitIcon("none_hand", "none_hand", textureMap);
//		loadAndInitIcon("water_hand", "water_hand", textureMap);
		
		for (Affinity aff : ArsMagicaAPI.getAffinityRegistry()) {
			ResourceLocation rl = new ResourceLocation(aff.getRegistryName().getResourceDomain(), "items/particles/" + aff.getRegistryName().getResourcePath() + "_hand");
			textureMap.registerSprite(rl);
			TextureAtlasSprite icon = textureMap.getAtlasSprite(rl.toString());
			String name = (aff.getRegistryName().getResourceDomain().equals(ArsMagica2.MODID) ? "" : (aff.getRegistryName().getResourceDomain() + ":")) + aff.getRegistryName().getResourcePath() + "_hand";
			icons.put(name, icon);
		}
		
		loadAndInitIcon("beam", "beam", textureMap, false);
		loadAndInitIcon("beam1", "beam1", textureMap, false);
		loadAndInitIcon("beam2", "beam2", textureMap, false);

		for (int i = 1; i <= 28; ++i){
			loadAndInitIcon("Symbols" + i, "symbols/Symbols" + i, textureMap, false);
		}

		for (int i = 1; i <= 9; ++i){
			loadAndInitIcon("snowflake" + i, "snowflakes/snowflake" + i, textureMap, false);
		}

		icons.put("symbols", null);
		icons.put("snowflakes", null);

		AMParticle.particleTypes = icons.keySet().toArray(new String[icons.size() + 1]);
		AMParticle.particleTypes[AMParticle.particleTypes.length - 1] = "radiant";
	}

	private void loadAndInitIcon(String name, String TextureAtlasSpritePath, TextureMap register){
		loadAndInitIcon(name, TextureAtlasSpritePath, register, true);
	}

	private void loadAndInitIcon(String name, String TextureAtlasSpritePath, TextureMap register, boolean registerName){
		register.registerSprite(new ResourceLocation("arsmagica2:items/particles/" + TextureAtlasSpritePath));
		TextureAtlasSprite icon = register.getTextureExtry(new ResourceLocation("arsmagica2:items/particles/" + TextureAtlasSpritePath).toString());
		if (registerName){
			if (icon != null){
				icons.put(name, icon);
			}
		}else{
			if (icon != null){
				hiddenIcons.put(name, icon);
			}
		}
	}

	public void RegisterIcon(String name, TextureAtlasSprite TextureAtlasSprite){
		icons.put(name, TextureAtlasSprite);
	}

	public TextureAtlasSprite getIconByName(String name){
		TextureAtlasSprite icon = null;
		if (name.equals("symbols")){
			icon = hiddenIcons.get("Symbols" + (rand.nextInt(28) + 1));
		} else if (name.equals("snowflakes")){
			icon = hiddenIcons.get("snowflake" + (rand.nextInt(9) + 1));
		} else
			icon = icons.get(name);
		if (icon == null)
			return icons.get("lights");
		return icon;
	}

	public TextureAtlasSprite getHiddenIconByName(String name){
		TextureAtlasSprite icon = hiddenIcons.get(name);
		if (icon == null) return icons.get("lights");
		return icon;
	}

	public String getParticleForAffinity(Affinity aff){
		if (aff.equals(Affinity.AIR)) return "wind";
		if (aff.equals(Affinity.ARCANE)) return "arcane";
		if (aff.equals(Affinity.EARTH)) return "rock";
		if (aff.equals(Affinity.ENDER)) return "pulse";
		if (aff.equals(Affinity.FIRE)) return "explosion_2";
		if (aff.equals(Affinity.ICE)) return "ember";
		if (aff.equals(Affinity.LIFE)) return "sparkle";
		if (aff.equals(Affinity.LIGHTNING)) return "lightning_hand";
		if (aff.equals(Affinity.NATURE)) return "plant";
		if (aff.equals(Affinity.WATER)) return "water_ball";
		if (aff.equals(Affinity.NONE)) return "lens_flare";
		return "lens_flare";
	}

	public String getSecondaryParticleForAffinity(Affinity aff){
		if (aff.equals(Affinity.AIR)) return "air_hand";
		if (aff.equals(Affinity.ARCANE)) return "symbols";
		if (aff.equals(Affinity.EARTH)) return "earth_hand";
		if (aff.equals(Affinity.ENDER)) return "ghost";
		if (aff.equals(Affinity.FIRE)) return "smoke";
		if (aff.equals(Affinity.ICE)) return "snowflakes";
		if (aff.equals(Affinity.LIFE)) return "sparkle2";
		if (aff.equals(Affinity.LIGHTNING)) return "lightning_hand";
		if (aff.equals(Affinity.NATURE)) return "leaf";
		if (aff.equals(Affinity.WATER)) return "water_hand";
		if (aff.equals(Affinity.NONE)) return "lights";
		return "lights";

	}
}

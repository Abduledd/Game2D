package mmorpg.cadre.resources;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Chargeur {
	
	public static void load() {
		try {
			File texturesFolder = new File("res/rooms");
			//ouvrir toutes les images dans le dossier rooms 
			for(File imgFile : texturesFolder.listFiles()) {
					Resources.CHAMBRES.put(imgFile.getName(), ImageIO.read(imgFile));
			}
			//ouvrir toutes les images dans le dossier textures 
			Resources.TEXTURES.add(Resources.FLOOR, ImageIO.read(new File("res/textures/floor.png")));
			Resources.TEXTURES.add(Resources.PIERRE, ImageIO.read(new File("res/textures/stone.png")));
			Resources.TEXTURES.add(Resources.WALL, ImageIO.read(new File("res/textures/wall.png")));
			Resources.TEXTURES.add(Resources.TUILE, ImageIO.read(new File("res/textures/tile.png")));
			Resources.TEXTURES.add(Resources.HERBE, ImageIO.read(new File("res/textures/grass.png")));
			Resources.TEXTURES.add(Resources.JOUEUR, ImageIO.read(new File("res/textures/player.png")));
			Resources.TEXTURES.add(Resources.JOUEUR_2, ImageIO.read(new File("res/textures/player_2.png")));
			Resources.TEXTURES.add(Resources.JOUEUR_LEFT, ImageIO.read(new File("res/textures/player_left.png")));
			Resources.TEXTURES.add(Resources.JOUEUR_LEFT_2, ImageIO.read(new File("res/textures/player_left_2.png")));
			Resources.TEXTURES.add(Resources.JOUEUR_RIGHT, ImageIO.read(new File("res/textures/player_right.png")));
			Resources.TEXTURES.add(Resources.JOUEUR_RIGHT_2, ImageIO.read(new File("res/textures/player_right_2.png")));
			Resources.TEXTURES.add(Resources.JOUEUR_BACK, ImageIO.read(new File("res/textures/player_back.png")));
			Resources.TEXTURES.add(Resources.JOUEUR_BACK_2, ImageIO.read(new File("res/textures/player_back_2.png")));
			Resources.TEXTURES.add(Resources.STAIRS, ImageIO.read(new File("res/textures/stairs.png")));
			Resources.TEXTURES.add(Resources.CHEST, ImageIO.read(new File("res/textures/chest.png")));
			Resources.TEXTURES.add(Resources.ENEMY, ImageIO.read(new File("res/textures/enemy.png")));
			Resources.TEXTURES.add(Resources.ENEMY_2, ImageIO.read(new File("res/textures/enemy_2.png")));
			Resources.TEXTURES.add(Resources.ATTACK, ImageIO.read(new File("res/textures/attack.png")));
			Resources.TEXTURES.add(Resources.HEART, ImageIO.read(new File("res/textures/heart.png")));
			Resources.TEXTURES.add(Resources.ARMOR, ImageIO.read(new File("res/textures/armor.png")));
			Resources.TEXTURES.add(Resources.OR, ImageIO.read(new File("res/textures/gold.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

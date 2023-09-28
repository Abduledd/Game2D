package mmorpg.cadre.resources;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources {
	// affecter un entier à chaque constante
	public static final byte FLOOR = 0;
	public static final byte PIERRE = 1;
	public static final byte WALL = 2;
	public static final byte TUILE = 3;
	public static final byte HERBE = 4;
	public static final byte JOUEUR = 5;
	public static final byte JOUEUR_2 = 6;
	public static final byte JOUEUR_LEFT = 7;
	public static final byte JOUEUR_LEFT_2 = 8;
	public static final byte JOUEUR_RIGHT = 9;
	public static final byte JOUEUR_RIGHT_2 = 10;
	public static final byte JOUEUR_BACK = 11;
	public static final byte JOUEUR_BACK_2 = 12;
	public static final byte STAIRS = 13;
	public static final byte CHEST = 14;
	public static final byte ENEMY = 15;
	public static final byte ENEMY_2 = 16;
	public static final byte ATTACK = 17;
	public static final byte HEART = 18;
	public static final byte ARMOR = 19;
	public static final byte OR = 20;
	
	public static final HashMap<String, BufferedImage> CHAMBRES = new HashMap<>(); //dictionnaire des chambres
	public static final ArrayList<BufferedImage> TEXTURES = new ArrayList<>(); //dictionnaire des textures
}

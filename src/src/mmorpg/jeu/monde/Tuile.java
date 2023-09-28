package mmorpg.jeu.monde;

import java.awt.Rectangle;

public class Tuile extends Rectangle {

	
	public static final int taille = 50;
	
	private byte tuileID;
	private boolean Mur;
	
	public Tuile(byte id, int posXinRoom, int posYinRoom, boolean estMur) {
		//constructeur de rectangle : 
		// Rectangle(int x, int y, int width, int height)
		// Construit un nouveau Rectangle dont le coin sup�rieur gauche
		// est sp�cifi� par (x,y) et dont la largeur et la hauteur sont 
		// sp�cifi�es par les arguments width et height
		super(posXinRoom * taille, posYinRoom * taille, taille, taille);
		this.tuileID = id;
		this.Mur = estMur;
	}
	
	public byte getID() {
		return tuileID;
	}
	
	public boolean estMur() {
		return Mur;
	}
}



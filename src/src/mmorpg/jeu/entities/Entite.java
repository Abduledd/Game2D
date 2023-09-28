package mmorpg.jeu.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import mmorpg.cadre.resources.Resources;
import mmorpg.cadre.outils.MathOutil;
import mmorpg.jeu.monde.Tuile;

/*Classe qu'on va utilisé pour les mouvements d'un joueur.*/

public class Entite extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	/*Les attributs.*/
	protected byte entityID;
	protected MathOutil.Direction face;
	protected boolean haut;
	protected boolean bas;
	protected boolean gauche;
	protected boolean droit;
	
	protected int speed;  // va nous aider pour manipuler le mouvement

	
	protected byte animationFrame;
	protected byte animationDelay;
	
	/*Constructeur de Entity.*/
	public Entite(byte id, int posXinRoom, int posYinRoom) {
		super(posXinRoom*Tuile.taille, posYinRoom*Tuile.taille, Tuile.taille, Tuile.taille);
		this.entityID = id;
		this.haut = false;
		this.bas = false;
		this.gauche = false;
		this.droit = false;
		this.speed = 5;
		this.face = MathOutil.Direction.SOUTH;
		this.animationFrame = 0;
	}
	
	public byte getID() {
		return entityID;
	}
	
	public void move() {
		if(haut) {
			//on va en haut
			super.y-=this.speed;
			this.face = MathOutil.Direction.NORTH;
		}
		if(bas) {
			//on va en bas
			super.y+=this.speed;
			this.face = MathOutil.Direction.SOUTH;
		}
		if(gauche) {
			//on va à gauche
			super.x-=this.speed;
			this.face = MathOutil.Direction.WEST;
		}
		if(droit) {
			//on va à droite
			super.x+=this.speed;
			this.face = MathOutil.Direction.EAST;
		}
	}
	
	public void setMovingHaut(boolean haut) {
		this.haut = haut;
	}
	
	public void setMovingBas(boolean bas) {
		this.bas = bas;
	}
	
	public void setMovingGauche(boolean gauche) {
		this.gauche = gauche;
	}
	
	public void setMovingDroit(boolean droit) {
		this.droit = droit;
	}
	
	public void setCentreX(int x) {
		super.x = x - super.width/2;
	}

	public void setCentreY(int y) {
		super.y = y - super.height/2;
	}
	
	public void render(Graphics graphics) {
		if(haut || bas || gauche || droit) {
			this.animationDelay++;
			if(this.animationDelay == 70) {
				this.animationDelay = 0;
				this.animationFrame = (byte) (1 - this.animationFrame);
			}
		}
		graphics.drawImage(Resources.TEXTURES.get(entityID + animationFrame), super.x, super.y, super.width, super.height, null);
	}
	
	public void handleCollisionWith(Tuile tuile) {
		Rectangle intersection = this.intersection(tuile);
		if(intersection.isEmpty() || !tuile.estMur())
			return;
			
		if(intersection.width > intersection.height) {
			if(this.y < tuile.y)
				this.y = tuile.y - this.height;
			else
				this.y = tuile.y + this.height;
		}
		else {
			if(this.x < tuile.x)
				this.x = tuile.x - this.width;
			else
				this.x = tuile.x + this.width;
		}
	}
	
	public MathOutil.Direction getFace() {
		return face;
	}
}

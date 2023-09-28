package mmorpg.jeu.entities;


import java.awt.Graphics;
import java.awt.Rectangle;

import mmorpg.cadre.Engine;
import mmorpg.cadre.resources.Resources;
import mmorpg.cadre.outils.MathOutil;
import mmorpg.jeu.monde.Tuile;

public class Joueur extends Entite {

	private static final long serialVersionUID = 1L;
	
	/*Les attributs*/
	private int hp; 			// le sant� du joueur comme tous les jeux.
	private int maxHp;		   // valeur maximale du HP
	private byte regenDelay;
	private int armure;			//l'armure de joueur
	private int gold;			// la monnaie du jeu ramass�e
	
	private byte attackTime;
	private byte damageTime;
	
	/*Constructeur de la classe Player*/
	public Joueur() {
		super(Resources.JOUEUR, MathOutil.randomInt(2, 5), MathOutil.randomInt(2, 5));
		this.hp = 20;
		this.maxHp = 20;
		this.regenDelay = 0;
		this.armure = 0;
		this.gold = 0;
		this.attackTime = 0;
		this.damageTime = 0;
	}
	
	public void replaceRandomly() {
		super.x = MathOutil.randomInt(2, 5)*Tuile.taille;
		super.y = MathOutil.randomInt(2, 5)*Tuile.taille;
	}

	public int getHp() {
		return hp;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	/*augumenter HP d'une quantit� amount*/
	public void instantHeal(int amount) {
		this.hp += amount;
		if(this.hp > this.maxHp) this.hp = this.maxHp;
	}
	
	public void regenerateHealth() {
		if(this.hp < this.maxHp) this.regenDelay++;
		else this.regenDelay = 0;
		
		if(this.regenDelay == 50) {
			this.hp++;
			this.regenDelay = 0;
		}
	}
	
	public int getArmure() {
		return armure;
	}
	
	public void addArmure(int amount) {
		this.armure += amount;
		if(this.armure > 75) this.armure = 75;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void giveGold(int amount) {
		this.gold += amount;
	}
	
	@Override
	public void move() {
		if(this.attackTime == 0) {
			super.move();
			switch(super.face) {
			case NORTH: super.entityID = Resources.JOUEUR_BACK; break;
			case SOUTH: super.entityID = Resources.JOUEUR; break;
			case WEST: super.entityID = Resources.JOUEUR_LEFT; break;
			case EAST: super.entityID = Resources.JOUEUR_RIGHT; break;
			}
		}
	}
	
	public void decreaseTime() {
		if(this.attackTime > 0) this.attackTime--;
		if(this.damageTime > 0) this.damageTime--;
	}
	
	public void attack() {
		if(this.attackTime == 0) this.attackTime = 30;
	}
	
	public Rectangle getAttackBox() {
		if(this.attackTime == 20) {
			switch(super.face) {
			case NORTH:
				return new Rectangle(super.x, super.y - super.height, super.width, super.height);
			case SOUTH:
				return new Rectangle(super.x, super.y + super.height, super.width, super.height);
			case WEST:
				return new Rectangle(super.x - super.width, super.y, super.width, super.height);
			case EAST:
				return new Rectangle(super.x + super.width, super.y, super.width, super.height);
			default:
				break;
			}
		}
		return new Rectangle(0, 0, 0, 0);
	}
	
	@Override
	public void render(Graphics graphics) {
		if((haut || bas || gauche || droit) && this.attackTime == 0) {
			super.animationDelay++;
			if(super.animationDelay == 70) {
				super.animationDelay = 0;
				super.animationFrame = (byte) (1 - super.animationFrame);
			}
		}
		graphics.drawImage(Resources.TEXTURES.get(entityID + animationFrame), super.x, super.y, super.width, super.height, null);
	}
	
	/*Quand l'ennemie attaque le joueur*/
	public void damage(int amount) {
		if(this.damageTime == 0) {
			if (this.hp > 0) {
				this.hp -= Math.min(amount, this.hp);
				if (this.hp == 0) {
					//System.exit(0);
					Engine.Demarrer(false);
				}
			}
			
			this.damageTime = 50;
		}
	}
}

package mmorpg.jeu.entities;

import mmorpg.cadre.outils.MathOutil;

public class Ennemi extends Entite {

	private static final long serialVersionUID = 1L;

	private Joueur target;
	
	private int hp;
	
	public Ennemi(byte id, int health, Joueur target) {
		super(id, MathOutil.randomInt(2, 14), MathOutil.randomInt(2, 7));
		this.target = target;
		super.speed = 2;
		this.hp = health;
	}
	
	public Ennemi(Ennemi copy) {
		this(copy.getID(), copy.hp, copy.target);
	}

	@Override
	public void move() {
		super.move();
		float angCoeff = ((float) this.target.y - (float) super.y) / ((float) this.target.x - (float) super.x);
		if(angCoeff < 1 && angCoeff > -1) {
			if(this.target.x < super.x) {
				super.haut = false;
				super.bas = false;
				super.gauche = true;
				super.droit = false;
			} else {
				super.haut = false;
				super.bas = false;
				super.gauche = false;
				super.droit = true;
			}
		}
		else if(angCoeff > 1 || angCoeff < -1) {
			if(this.target.y < super.y) {
				super.haut = true;
				super.bas = false;
				super.gauche = false;
				super.droit = false;
			} else {
				super.haut = false;
				super.bas = true;
				super.gauche = false;
				super.droit = false;
			}
		}
		else {
			if(this.target.x < super.x) {
				super.gauche = true;
				super.droit = false;
			} else {
				super.gauche = false;
				super.droit = true;
			}
		}
	}
	
	@Override
	public void setMovingHaut(boolean haut) {
		return;
	}
	
	@Override
	public void setMovingBas(boolean bas) {
		return;
	}
	
	@Override
	public void setMovingGauche(boolean gauche) {
		return;
	}
	
	@Override
	public void setMovingDroit(boolean droit) {
		return;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void damage(int amount, MathOutil.Direction knockback) {
		this.hp -= amount;
		super.x += knockback.dirX * 90;
		super.y += knockback.dirY * 90;
	}
}

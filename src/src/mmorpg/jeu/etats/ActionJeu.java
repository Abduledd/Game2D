package mmorpg.jeu.etats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import mmorpg.cadre.etatsdujeu.EtatsDuJeu;
import mmorpg.cadre.etatsdujeu.EtatsDuJeuManager;
import mmorpg.cadre.resources.Resources;
import mmorpg.cadre.outils.MathOutil;
import mmorpg.jeu.entities.Ennemi;
import mmorpg.jeu.entities.Joueur;
import mmorpg.jeu.monde.Fonctionalite;
import mmorpg.jeu.monde.Tuile;
import mmorpg.jeu.monde.Monde;
import mmorpg.jeu.monde.generateur.GenerateurDeNiveau;
import mmorpg.jeu.monde.generateur.RoomData;

public class ActionJeu extends EtatsDuJeu {

	private GenerateurDeNiveau generator;
	private Monde monde;
	private Joueur joueur;
	
	protected ActionJeu(EtatsDuJeuManager manager) {
		super(manager);
		this.generator = new GenerateurDeNiveau();
		this.joueur = new Joueur();
		this.generateLevel();
	}

	@Override
	protected void loop() {
		this.joueur.move();
		this.monde.changerPiece(joueur);
		
		this.collisions();
		
		this.monde.getPiece().InteractionFonctionalite(joueur);
		
		this.joueur.regenerateHealth();
		this.joueurAttacks();
	}

	@Override
	protected void render(Graphics graphics) {
		this.monde.getPiece().render(graphics);
		this.joueur.render(graphics);
		
		graphics.drawImage(Resources.TEXTURES.get(Resources.ATTACK), this.joueur.getAttackBox().x, 
				this.joueur.getAttackBox().y, this.joueur.getAttackBox().width, this.joueur.getAttackBox().height, null);
		
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.PLAIN, 15));
		graphics.drawImage(Resources.TEXTURES.get(Resources.HEART), 0, 0, Tuile.taille*2/3, Tuile.taille*2/3, null);
		graphics.drawString(this.joueur.getHp()+"/"+this.joueur.getMaxHp(), Tuile.taille*2/3+5, 20);
		graphics.drawImage(Resources.TEXTURES.get(Resources.ARMOR), 80, 0, Tuile.taille*2/3, Tuile.taille*2/3, null);
		graphics.drawString(this.joueur.getArmure()+"", Tuile.taille*2/3+85, 20);
		graphics.drawImage(Resources.TEXTURES.get(Resources.OR), 160, 0, Tuile.taille*2/3, Tuile.taille*2/3, null);
		graphics.drawString(this.joueur.getGold()+"", Tuile.taille*2/3+165, 20);
	}

	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP:
			this.joueur.setMovingHaut(true);
			break;
		case KeyEvent.VK_LEFT:
			this.joueur.setMovingGauche(true);
			break;
		case KeyEvent.VK_DOWN:
			this.joueur.setMovingBas(true);
			break;
		case KeyEvent.VK_RIGHT:
			this.joueur.setMovingDroit(true);
			break;
		case KeyEvent.VK_SPACE:
			this.joueur.attack();
			break;
		}
	}

	@Override
	protected void keyReleased(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP:
			this.joueur.setMovingHaut(false);
			break;
		case KeyEvent.VK_LEFT:
			this.joueur.setMovingGauche(false);
			break;
		case KeyEvent.VK_DOWN:
			this.joueur.setMovingBas(false);
			break;
		case KeyEvent.VK_RIGHT:
			this.joueur.setMovingDroit(false);
			break;
		}
	}
	
	private void generateLevel() {
		this.generator.reset(); // 
		while(!this.generator.finished()) {
			this.generator.generate();
		}
		this.monde = new Monde(this.generator.getRoomsData());
		
		this.monde.getPieceRandom().placefonctionalite(new Fonctionalite(Resources.STAIRS, this::generateLevel));
		
		for(int i=0;i<12;i++) {
			this.monde.getPieceRandom().placefonctionalite(new Fonctionalite(Resources.CHEST, this::giveJoueurRandomLoot));
		}
		
		for(int i=0;i<25;i++) {
			this.monde.getPieceRandom().creeEnnemi(new Ennemi(Resources.ENEMY, 5, this.joueur));
		}
		
		this.spawnJoueur();
	}
	
	private void spawnJoueur() {
		if(this.monde.getPiece(0, 0).getInfo().getTuileAt(joueur.x / Tuile.taille, joueur.y / Tuile.taille).getID() != Resources.FLOOR) {
			this.joueur.replaceRandomly();
			this.spawnJoueur();
		}
	}

	private void collisions() {
		RoomData roomIn = this.monde.getPiece().getInfo();
		for(int i=0;i<roomIn.getSizeX();i++) {
			for(int j=0;j<roomIn.getSizeY();j++) {
				this.joueur.handleCollisionWith(roomIn.getTuileAt(i, j));
				for(Ennemi ennemi : this.monde.getPiece().getEnemies()) {
					ennemi.handleCollisionWith(roomIn.getTuileAt(i, j));
				}
			}
		}
	}
	
	private void giveJoueurRandomLoot() {
		switch(MathOutil.randomInt(3)) {
		case 0: this.joueur.addArmure(MathOutil.randomInt(3, 5)); break;
		case 1: this.joueur.giveGold(MathOutil.randomInt(3, 7)); break;
		case 2: this.joueur.instantHeal(MathOutil.randomInt(2, 5)); break;
		}
	}
	
	private void joueurAttacks() {
		this.joueur.decreaseTime();
		for(int i=0;i<this.monde.getPiece().getEnemies().size();i++) {
			this.monde.getPiece().getEnemies().get(i).move();
			
			if(this.monde.getPiece().getEnemies().get(i).intersects(this.joueur)) {
				this.joueur.damage(5 -  5*this.joueur.getArmure()/100);
			}
			
			if(this.monde.getPiece().getEnemies().get(i).intersects(this.joueur.getAttackBox())) {
				this.monde.getPiece().getEnemies().get(i).damage(3, this.joueur.getFace());
				if(this.monde.getPiece().getEnemies().get(i).getHp() <= 0) {
					this.monde.getPiece().getEnemies().remove(i);
					this.joueur.giveGold(MathOutil.randomInt(2, 5));
				}
			}
		}
	}
}
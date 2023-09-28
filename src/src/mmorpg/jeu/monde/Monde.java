package mmorpg.jeu.monde;

import java.util.HashSet;

import mmorpg.cadre.vue.FenetreManager;
import mmorpg.cadre.outils.MathOutil;
import mmorpg.jeu.Init;
import mmorpg.jeu.entities.Entite;
import mmorpg.jeu.monde.generateur.GenerateurDeNiveau;
import mmorpg.jeu.monde.generateur.RoomData;


public class Monde {

	private Piece[][] piece;
	private int leX;
	private int leY;
	private int k;
	public Monde(HashSet<MathOutil.Direction>[][] roomsData) {
		this.piece = new Piece[roomsData.length][roomsData[0].length];
		for(int i=0;i<GenerateurDeNiveau.MONDE_TAILLE;i++) {
			for(int j=0;j<GenerateurDeNiveau.MONDE_TAILLE;j++) {
				for(RoomData roomData : Init.ROOMS) {
					if(roomData.getExits().equals(roomsData[i][j]))
						this.piece[i][j] = new Piece(roomData);
				}
			}
		}
		this.leX = 0;
		this.leY = 0;
	}
	
	public Piece getPiece(int x, int y) {
		return piece[x][y];
	}
	
	public Piece getPiece() {
		return piece[leX][leY];
	}
	
	public Piece getPieceRandom() {
		return piece[MathOutil.randomInt(GenerateurDeNiveau.MONDE_TAILLE)][MathOutil.randomInt(GenerateurDeNiveau.MONDE_TAILLE)];
	}
	
	public void changerPiece(Entite joueur) {
		if(joueur.getCenterX() < 0) {
			this.leX--;
			joueur.setCentreX(FenetreManager.LARGEUR);
		}
		else if(joueur.getCenterX() > FenetreManager.LARGEUR) {
			this.leX++;
			joueur.setCentreX(0);
		}
		
		else if (joueur.getCenterX() > FenetreManager.HAUTEUR) {
			this.leY++;
			joueur.setCentreY(0);
					
		}
		else if(joueur.getCenterX() < 0) {
			this.leY--;
			joueur.setCentreY(FenetreManager.HAUTEUR);
		}
			
		
	}
}


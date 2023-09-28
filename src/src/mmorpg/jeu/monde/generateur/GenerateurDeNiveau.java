package mmorpg.jeu.monde.generateur;

import java.util.HashSet;

import mmorpg.cadre.outils.MathOutil;

public class GenerateurDeNiveau {

	public static final int MONDE_TAILLE = 5;
	
	private int posX;
	private int posY;
	
	private HashSet<MathOutil.Direction>[][] roomsData; //dictionnaire des directions
	private boolean generated[][];

	@SuppressWarnings("unchecked")
	public void reset() {
		this.roomsData = new HashSet[MONDE_TAILLE][MONDE_TAILLE];
		this.generated = new boolean[MONDE_TAILLE][MONDE_TAILLE];
		for(int i=0;i<this.roomsData.length;i++) {
			for(int j=0;j<this.roomsData[i].length;j++) {
				this.roomsData[i][j] = new HashSet<>();
				this.generated[i][j] = false;
			}
		}
		this.setRandomPosition();
	}
	
	public void generate() {
		MathOutil.Direction direction = MathOutil.randomDirection();
		if(this.isValidPosition(posX + direction.dirX, posY + direction.dirY)) {
			if(!this.generated[posX + direction.dirX][posY + direction.dirY]) {
				this.roomsData[posX][posY].add(direction);
				this.roomsData[posX + direction.dirX][posY + direction.dirY].add(direction.opposite);
			}
			this.posX += direction.dirX;
			this.posY += direction.dirY;
			this.generated[posX][posY] = true;
		}
		else {
			this.generate();
		}
	}
	
	private void setRandomPosition() {
		this.posX = MathOutil.randomInt(MONDE_TAILLE);
		this.posY = MathOutil.randomInt(MONDE_TAILLE);
		this.generated[posX][posY] = true;
	}
	
	private boolean isValidPosition(int x, int y) {
		return x >= 0 && y >= 0 && x < MONDE_TAILLE && y < MONDE_TAILLE;
	}
	
	public boolean finished() {
		for(int i=0;i<this.generated.length;i++) {
			for(int j=0;j<this.generated[i].length;j++) {
				if(!this.generated[i][j]) return false;
			}
		}
		return true;
	}
	
	public HashSet<MathOutil.Direction>[][] getRoomsData() {
		return roomsData;
	}
}

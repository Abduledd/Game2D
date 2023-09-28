  package mmorpg.jeu.monde.generateur;

import java.awt.Graphics;
import java.util.HashSet;

import mmorpg.cadre.resources.Resources;
import mmorpg.cadre.outils.MathOutil;
import mmorpg.jeu.monde.Tuile;

public class RoomData {

	private Tuile[][] tilesData;
	
	private HashSet<MathOutil.Direction> exits;
	
	public RoomData(byte[][] tilesData, MathOutil.Direction... exits) {
		this.tilesData = new Tuile[tilesData.length][tilesData[0].length];
		for(int i=0;i<this.tilesData.length;i++) {
			for(int j=0;j<this.tilesData[i].length;j++) {
				this.tilesData[i][j] = new Tuile(tilesData[i][j], j, i, tilesData[i][j] == 1 || tilesData[i][j] == 2);
			}
		}
		this.exits = new HashSet<>();
		for(MathOutil.Direction direction : exits) {
			this.exits.add(direction);
		}
	}
	
	public void render(Graphics graphics) {
		for(int i=0;i<this.tilesData.length;i++) {
			for(int j=0;j<this.tilesData[i].length;j++) {
				graphics.drawImage(Resources.TEXTURES.get(this.tilesData[i][j].getID()), j*Tuile.taille, i*Tuile.taille, Tuile.taille, Tuile.taille, null);
			}
		}
	}
	
	public HashSet<MathOutil.Direction> getExits() {
		return exits;
	}
	
	public Tuile getTuileAt(int x, int y) {
		return tilesData[y][x];
	}
	
	public int getSizeY() {
		return tilesData.length;
	}
	
	public int getSizeX() {
		return tilesData[0].length;
	}
}

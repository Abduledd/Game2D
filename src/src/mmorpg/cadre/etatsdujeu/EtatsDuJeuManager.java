package mmorpg.cadre.etatsdujeu;

import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Stack;

public class EtatsDuJeuManager {

	private Stack<EtatsDuJeu> etats;
	
	public EtatsDuJeuManager() {
		this.etats = new Stack<>();
	}
	
	public void stackState(EtatsDuJeu etat) {
		this.etats.add(etat);
	}
	
	
	public void loop() {
		try {
			this.etats.peek().loop();
		} catch(EmptyStackException e) {
			System.err.println("GameState stack est vide!");
			System.exit(-1);
		}
	}

	public void render(Graphics graphics) {
		try {
			this.etats.peek().render(graphics);
		} catch(EmptyStackException e) {
			System.err.println("GameState stack est vide!");
			System.exit(-1);
		}
	}
	
	public void keyPressed(int keyCode) {
		try {
			this.etats.peek().keyPressed(keyCode);
		} catch(EmptyStackException e) {
			System.err.println("GameState stack est vide!");
			System.exit(-1);
		}
	}
	
	public void keyReleased(int keyCode) {
		try {
			this.etats.peek().keyReleased(keyCode);
		} catch(EmptyStackException e) {
			System.err.println("GameState stack est vide!");
			System.exit(-1);
		}
	}
}

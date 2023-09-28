package mmorpg.cadre.etatsdujeu;

import java.awt.Graphics;

public abstract class EtatsDuJeu {

	protected EtatsDuJeuManager etatsDuJeuManager;
	
	protected EtatsDuJeu(EtatsDuJeuManager manager) {
		this.etatsDuJeuManager = manager;
	}
	
	protected abstract void loop();
	
	protected abstract void render(Graphics graphics);
	
	protected abstract void keyPressed(int keyCode);
	
	protected abstract void keyReleased(int keyCode);
}

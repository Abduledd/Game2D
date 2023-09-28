package mmorpg.jeu.etats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mmorpg.cadre.etatsdujeu.EtatsDuJeu;
import mmorpg.cadre.etatsdujeu.EtatsDuJeuManager;
import mmorpg.cadre.vue.FenetreManager;

public class MenuPrincipal extends EtatsDuJeu {

	private String[] options;
	private static String JOUER = "Jouer";
	private static final String QUITTER = "Quitter";
	private int selected;
	private boolean first;
		
	public MenuPrincipal(EtatsDuJeuManager manager, boolean first) {
		super(manager);
		if(!first) JOUER = "Rejouer";
		this.options = new String[] {JOUER, QUITTER};
		this.selected = 0; //initialisation à jouer
		this.first = first;
	}

	@Override
	protected void loop() {
	}
	
	// configurer la VUE du menu principal
	@Override
	protected void render(Graphics graphics) {
		graphics.setColor(new Color(10, 30, 70));
		graphics.fillRect(0, 0, FenetreManager.LARGEUR, FenetreManager.HAUTEUR);
		
		graphics.setFont(new Font("SansSerif", Font.BOLD, 25));
		if(first) {
			premierJouer(graphics);
		} else {
			gameOver(graphics);
		}
		
	}

	@Override
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		//changer les options
		case KeyEvent.VK_UP:
			if(this.selected > 0) this.selected--;
			break;
		case KeyEvent.VK_DOWN:
			if(this.selected < this.options.length-1) this.selected++;
			break;
		
		case KeyEvent.VK_ENTER:
			if(this.options[selected] == JOUER) { // demarrer le jeu
				super.etatsDuJeuManager.stackState(new ActionJeu(etatsDuJeuManager));
			} else { // quitter le jeu
				System.exit(0);
			}
		}
	}

	@Override
	protected void keyReleased(int keyCode) {}
	
	public void premierJouer(Graphics graphics) {
		for(int i=0;i<this.options.length;i++) {
			if(i==this.selected) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			graphics.drawString(this.options[i], FenetreManager.LARGEUR/2-40+7*(1-i), FenetreManager.HAUTEUR/2-30 + i * 40);
			
			// ajouter le logo de l'ENSEEIHT
			try
			{
				BufferedImage img = ImageIO.read(new File("res/ENSEEIHT.png"));
				int width          = img.getWidth();
				int height         = img.getHeight();
				graphics.drawImage(img, (FenetreManager.LARGEUR-width)/2, FenetreManager.HAUTEUR-height,null);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void gameOver(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawString("Game Over", (FenetreManager.LARGEUR)/2-60, (FenetreManager.HAUTEUR)/2-130);
		try{Thread.sleep(1000);}
		catch(InterruptedException ex)
		{Thread.currentThread().interrupt();}
		premierJouer(graphics);
	}
}

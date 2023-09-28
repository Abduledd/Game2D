package mmorpg.cadre.vue;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreManager { 

	private JFrame frame;
	private JPanel panel;
	
	public static final int LARGEUR = 800; //largeur du fen�tre
	public static final int HAUTEUR = 450; //hauteur du fen�tre
	
	public FenetreManager() {
		this.frame = new JFrame("Mmorpg");
		this.frame.setBounds(70, 70, 0, 0);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
	}
	
	public void addPanel(JPanel panel) {
		this.panel = panel;
		this.panel.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		this.panel.setFocusable(true); //composant actif
		this.panel.requestFocusInWindow();
	}
	
	public void addKeyListener(KeyListener listener) {
		try {
			this.panel.addKeyListener(listener);
		} catch(NullPointerException e) {
			System.err.println("erreur KeyListener");
			System.exit(-1);
		}
	}
	
	public void creerFenetre() {
		this.frame.setContentPane(panel);
		this.frame.pack();
		this.frame.setVisible(true);
	}
}

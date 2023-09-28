package mmorpg.cadre;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import mmorpg.cadre.etatsdujeu.EtatsDuJeuManager;
import mmorpg.cadre.vue.FenetreManager;
import mmorpg.jeu.etats.MenuPrincipal;

public class Engine {

	private static EtatsDuJeuManager etatsDuJeuManager;
	
	private static FenetreManager fenetreManager; //
	private static Timer timer;
	
	public static void initialiser() {
		etatsDuJeuManager = new EtatsDuJeuManager();
		fenetreManager = new FenetreManager();
		timer = new Timer(20, new MainGameLoop());
	}
	
	public static void Demarrer(boolean first) {
		etatsDuJeuManager.stackState(new MenuPrincipal(etatsDuJeuManager, first));
		if(first) {
			fenetreManager.addPanel(new GameScreen());
			fenetreManager.addKeyListener(new Keyboard());
			fenetreManager.creerFenetre();
			
		}
		timer.start();
	}
	
	private static class MainGameLoop implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			etatsDuJeuManager.loop();
		}
		
	}
	
	private static class GameScreen extends JPanel {
		
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			etatsDuJeuManager.render(g);
			repaint();
		}
	}
	
	private static class Keyboard implements KeyListener {

		@Override
		public void keyPressed(KeyEvent key) {
			etatsDuJeuManager.keyPressed(key.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent key) {
			etatsDuJeuManager.keyReleased(key.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}
}

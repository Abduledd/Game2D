package mmorpg;

import javax.swing.SwingUtilities;

import mmorpg.cadre.Engine;
import mmorpg.cadre.resources.Chargeur;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Chargeur.load(); //charger tous les images 
				Engine.initialiser();
				Engine.Demarrer(true);
			}
		});
	}
}
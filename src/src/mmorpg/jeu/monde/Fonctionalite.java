package mmorpg.jeu.monde;

import java.awt.Graphics;
import java.awt.Rectangle;

import mmorpg.cadre.resources.Resources;
import mmorpg.cadre.outils.InterfaceFonctionelle;
import mmorpg.cadre.outils.MathOutil;

public class Fonctionalite extends Tuile {

	private static final long serialVersionUID = 1L;

	private InterfaceFonctionelle action;
	
	public Fonctionalite(byte id, InterfaceFonctionelle action) {
		super(id, MathOutil.randomInt(2, 14), MathOutil.randomInt(2, 7), false);
		this.action = action;
	}
	
	public Fonctionalite(Fonctionalite copy) {
		this(copy.getID(), copy.action);
	}
	
	@Override
	public boolean intersects(Rectangle r) {
		if(super.intersects(r)) {
			this.action.action();
			return true;
		}
		return false;
	}
	
	
	public void render(Graphics graphics) {
		graphics.drawImage(Resources.TEXTURES.get(super.getID()), super.x, super.y, super.width, super.height, null);
	}
}

package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.game.assets;

public class obstacle extends object{
	
	public ArrayList<Integer> hasHit = new ArrayList<Integer>();
	public int attack;
	
	public obstacle(double x, double y, int w, int h) {
		super(x, y, w, h, new Color(6, 6, 8));
		dx = -3;
		attack = 10;
	}
	
	public void draw(Graphics g, assets a) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, w, h);
	}
}

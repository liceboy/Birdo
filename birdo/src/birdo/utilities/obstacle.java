package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;

import birdo.game.assets;

public class obstacle extends object{
	
	public obstacle(double x, double y, int w, int h) {
		super(x, y, w, h, new Color(6, 6, 8));
		dx = -3;
	}
	
	public void draw(Graphics g, assets a) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, w, h);
	}
}

package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;

public class obstacle extends object{
	
	public obstacle(double x, double y, int w, int h) {
		super(x, y, w, h, new Color(6, 6, 8));
		dx = -3;
	}
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, w, h);
	}
}

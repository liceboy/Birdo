package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class object {

	public int x;
	public int y; // location
	public int w;
	public int h; // dimensions
	public double dx;
	public double dy; // directional movement

	public Color c;

	public object(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		dx = 0;
		dy = 0;
		this.c = c;
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
	}

	public void move() {
		if(this instanceof feather)
			System.out.println(dx + " " + dy);
		x += dx;
		y += dy;
	}

	public Rectangle getHitBox() {
		return new Rectangle(x, y, w, h);
	}

	public Rectangle getFutureHitBox() {
		return new Rectangle((int) (x + dx), (int) (y + dy), w, h);
	}
}

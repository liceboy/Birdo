package birdo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class object {
	
	int x; int y; // location
	int w; int h; // dimensions
	int dx; int dy; // directional movement
	
	Color c;
	
	public object(int x, int y, int w, int h, Color c) {
		this.x = x; this.y = y;
		this.w = w; this.h = h;
		dx = 0; dy = 0;
		this.c = c;
	}
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
	}
	
	public void move() {
		x += dx; y += dy;
	}

	public Rectangle getHitBox() {
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getFutureHitBox() {
		return new Rectangle(x + dx, y + dy, w, h);
	}
}

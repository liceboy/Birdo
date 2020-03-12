package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import birdo.game.assets;

public abstract class object {

	public double x;
	public double y; // location
	public int w;
	public int h; // dimensions
	public double dx;
	public double dy; // directional movement
	private Rectangle hitbox;

	public Color c;
	public Color cDefault;
	
	public int hash;

	public object(double x, double y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		hitbox = new Rectangle((int) x, (int) y, w, h);
		dx = 0;
		dy = 0;
		this.c = c;
		hash = this.hashCode();
	}

	public void draw(Graphics g, assets a) {
		g.setColor(c);
		g.fillOval((int) x, (int) y, w, h);
	}

	public void move() {
		
		x += dx;
		y += dy;
		
		x = Math.floor(x * 10) / 10;
		y = Math.floor(y * 10) / 10;
		dx = Math.floor(dx * 10) / 10;
		dy = Math.floor(dy * 10) / 10;
	}
	
	public Rectangle updateHitbox() {
		return new Rectangle((int) x, (int) y, w, h);
	}
	public Rectangle getHitBox() {
		hitbox = updateHitbox();
		return hitbox;
	}
	public void setNewHitbox(Rectangle newHitbox) {
		hitbox = newHitbox;
	}
	public Rectangle getFutureHitBox() {
		return new Rectangle((int) (x + dx), (int) (y + dy), w, h);
	}
}

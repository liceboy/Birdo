package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.game.assets;

public class obstacle extends object{
	
	public ArrayList<Integer> hasHit = new ArrayList<Integer>();
	public int attack;
	public player p;
	public boolean isLaser;
	
	public obstacle(double x, double y, int w, int h) {
		super(x, y, w, h, new Color(6, 6, 8));
		attack = 10;
	}
	
	public void draw(Graphics g, assets a) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, w, h);
	}
	
	public void move() {
		dx = -3;
		laser();
		super.move();
	}
	
	public void laser() {
	if (!isLaser)
		return;
	if (isLaser && p.player == true) {
		x = p.alignedX;
		y = p.alignedY;
	}
	}
}

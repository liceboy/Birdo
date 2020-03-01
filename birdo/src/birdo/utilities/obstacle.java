package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.game.assets;

public class obstacle extends object{
	
	public ArrayList<Integer> hasHit = new ArrayList<Integer>();
	public int attack;
	public player p;
	public enemy shooter;
	public ArrayList<enemy> enemies = new ArrayList<enemy>();
	public boolean fromPlayer;
	public boolean isLaser;
	
	public obstacle(double x, double y, int w, int h, boolean fromPlayer) {
		super(x, y, w, h, new Color(6, 6, 8));
		fromPlayer = true;
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
	if (isLaser && fromPlayer) {
		x = p.alignedX + p.dx;
		y = p.alignedY + p.dy;
	}
	if (isLaser && shooter != null && shooter.player == false ) {
		x = shooter.alignedX-800 + shooter.dx;
		y = shooter.alignedY + shooter.dy;
	}
	}
}

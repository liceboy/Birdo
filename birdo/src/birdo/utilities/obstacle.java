package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import birdo.game.assets;

public class obstacle extends attack {
	
	public int lockX = 0;
	public int lockY = 0;
	
	public boolean isLaser;
	public int lifeCount;
	public int damageRate = 10;
	public int damageCount = 0;
	
	public obstacle(double x, double y, int w, int h) {
		super(x, y, w, h, new Color(6, 6, 8));
		attack = 10;
		lifeCount = -1;
		dx = -3;
	}
	
	public void draw(Graphics2D g, assets a) {
		setColor();
		g.setColor(c);
		g.fillRect((int) x, (int) y, w, h);
	}
	
	public void move() {
		laser();
		dx = 0;
		dy = 0;
		super.move();
	}
	
	public void laser() {
		if (!isLaser)
			return;
		
		if (damageCount == damageRate) {
			hasHit.clear();
			damageCount = 0;
		}
		if (fromPlayer) {
			x = owner.alignedX + owner.dx + lockX;
			y = owner.alignedY + owner.dy + lockY;
		}
		else {
			x = owner.alignedX - w + owner.dx + lockX;
			y = owner.alignedY + owner.dy + lockY;
		}
		
		damageCount++;
		lifeCount--;
	}
}

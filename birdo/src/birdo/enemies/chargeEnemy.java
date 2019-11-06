package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;
import birdo.utilities.enemy;

// fast enemy that doesn't shoot or poop, only charges at the player

public class chargeEnemy extends enemy {

	public chargeEnemy(int x, int y) {
		super(x, y);
		c = Color.GRAY;
		health = 2;
	}

	public void move() {

		if (x > 800) {
			super.move();
			return;
		}

		if (!isDead) {
			int deltaX = p.x - x;
			int deltaY = p.y - y;

			double theta = Math.atan((double) deltaY / (double) deltaX);
			
			dx = -1 * (int) (6 * Math.cos(theta));
			dy = -1 * (int) (6 * Math.sin(theta));
			
			if(p.x > x) { 
				dy = 0;
				if(p.x - x > 30)
					dx = -8;
			}
			
			if(p.y > 500) {
				dy = 0;
				dx = -8;
			}

			super.move();
			return;
		}

		super.move();
	}

	public void shootFeather() {
		return;
	}

	public void poop() {
		return;
	}

}

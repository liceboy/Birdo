package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class steadyEnemy extends enemy {

	public steadyEnemy(int x, int y) {
		super(x, y);
		c = Color.DARK_GRAY;
	}
	
	public void poop () {
		return;
	}
	
	public void shootFeather () {
		if (shootcount == 0) {
			if (!isDead) {

				feather f = new feather(this.x, this.y, false);
				int deltaX = p.x - x;
				int deltaY = p.y - y;

				double theta = Math.atan((double) deltaY / (double) deltaX);
				f.dx = -1 * (int) (5 * Math.cos(theta));
				f.dy = -1 * (int) (5 * Math.sin(theta));

				feathers.add(f);
				// adds a feather if alive
				shootcount = 30;
			}
		}
		shootcount--;
	}
	
	public void move () {
		if (x > 700)
			dx = -3;
		if (x < 700)
			dx = 0;
		super.move();
	}

}

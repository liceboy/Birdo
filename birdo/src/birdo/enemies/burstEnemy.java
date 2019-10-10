package birdo.enemies;

import birdo.utilities.enemy;
import birdo.utilities.feather;

import java.awt.Color;

public class burstEnemy extends enemy {

	public burstEnemy(int x, int y) {
		super(x, y);
		c = Color.CYAN;
	}

	public void move() {
		if (x > 700)
			dx = -3;
		if (x < 700)
			dx = 0;
		super.move();
	}

	public void shootFeather() {
		if (x > 700)
			return;
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
				shootcount = 100;
			}
		}
		if (shootcount == 90) {
			feather f = new feather(this.x, this.y, false);
			int deltaX = p.x - x;
			int deltaY = p.y - y;

			double theta = Math.atan((double) deltaY / (double) deltaX);
			f.dx = -1 * (int) (5 * Math.cos(theta));
			f.dy = -1 * (int) (5 * Math.sin(theta));

			feathers.add(f);
		}
		if (shootcount == 80) {
			feather f = new feather(this.x, this.y, false);
			int deltaX = p.x - x;
			int deltaY = p.y - y;

			double theta = Math.atan((double) deltaY / (double) deltaX);
			f.dx = -1 * (int) (5 * Math.cos(theta));
			f.dy = -1 * (int) (5 * Math.sin(theta));

			feathers.add(f);
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

}

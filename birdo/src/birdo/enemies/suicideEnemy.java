package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// enemy that slowly drifts toward the player, shooting triple shots until its death

public class suicideEnemy extends enemy {

	public suicideEnemy(int x, int y) {
		super(x, y);
		health = 2;
		c = Color.GREEN;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				customShot("bloomShot");
				shootcount = 100;
			}
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

	public void move() {
		if (!isDead) {

			if (x > 800) {
				dx = -3;
				super.move();
				return;
			}

			int deltaX = p.x - x;
			int deltaY = p.y - y;

			double theta = Math.atan((double) deltaY / (double) deltaX);

			dx = -1 * (int) (3 * Math.cos(theta));
			dy = -1 * (int) (3 * Math.sin(theta));

			super.move();

		}

		if (isDead)
			super.move();
	}
}

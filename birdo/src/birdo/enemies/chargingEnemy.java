package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;

import birdo.utilities.enemy;

public class chargingEnemy extends enemy {

	public chargingEnemy(int x, int y, Color c) {
		super(x, y, c);
		health = 1;
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

			this.dx = -1 * (int) (8 * Math.cos(theta));
			this.dy = -1 * (int) (8 * Math.sin(theta));

			super.move();

		}

		if (isDead)
			super.move();
	}

	public void shootFeather() {
		return;
	}

	public void poop() {
		return;
	}

}

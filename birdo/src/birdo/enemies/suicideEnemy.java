package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class suicideEnemy extends enemy {

	public suicideEnemy(int x, int y) {
		super(x, y);
		health = 2;
		c = Color.GREEN;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				feather f = new feather(this.x, this.y, false);
				f.dx = 5;
				feathers.add(f);
				feather f1 = new feather(this.x, this.y, false);
				f1.dx = -5;
				feathers.add(f1);
				feather f2 = new feather(this.x, this.y, false);
				f2.dx = -1 * (int) (5 * Math.cos(-1 * Math.PI / 4));
				f2.dy = -1 * (int) (5 * Math.sin(-1 * Math.PI / 4));
				feathers.add(f2);
				feather f3 = new feather(this.x, this.y, false);
				f3.dx = -1 * (int) (5 * Math.cos(Math.PI / 4));
				f3.dy = -1 * (int) (5 * Math.sin(Math.PI / 4));
				feathers.add(f3);
				feather f4 = new feather(this.x, this.y, false);
				f4.dx = -1 * (int) (5 * Math.cos(3 * Math.PI / 4));
				f4.dy = -1 * (int) (5 * Math.sin(3 * Math.PI / 4));
				feathers.add(f4);
				feather f5 = new feather(this.x, this.y, false);
				f5.dx = -1 * (int) (5 * Math.cos(5 * Math.PI / 4));
				f5.dy = -1 * (int) (5 * Math.sin(5 * Math.PI / 4));
				feathers.add(f5);

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

			this.dx = -1 * (int) (3 * Math.cos(theta));
			this.dy = -1 * (int) (3 * Math.sin(theta));

			super.move();

		}

		if (isDead)
			super.move();
	}
}

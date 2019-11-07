package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class miniBoss2 extends enemy {
	int movecount;

	public miniBoss2(int x, int y) {
		super(x, y);
		health = 20;
		score = 1500;
		w = 40;
		h = 40;
		movecount = 300;
	}

	public void shootFeather() {
		if (!isDead && health >= 10) {
			if (shootcount <= 0 && !isDead) {
				customShot("normal");
				shootcount = 100;
			}
			if (shootcount % 10 == 0 && (shootcount / 10) < 8) {
				if(shootcount / 10 < 5) customShot("normal");
				else customShot("homing");
			}
		}
		if (!isDead && health < 10) {
			if (shootcount <= 0) {
				customShot("spinShot");
				shootcount = 15;
				}
			}
		shootcount--;
	}

	public void poop() {
		return;
	}

	public void move() {
		if (!isDead) {
			if (x > 700)
				dx = -3;
			if (x < 700 && health >= 10) {
				if (movecount > 150) {
					dx = 0;
					dy = -2;
				}
				if (movecount < 150) {
					dx = 0;
					dy = 2;
				}
				if (movecount <= 0) {
					movecount = 300;
				}
				movecount--;
			}
			if (health < 10) {
				if (x > 400)
					dx = -3;
				if (y < 250)
					dy = 3;
				if (y > 250)
					dy = -3;
				if (x <= 400)
					dx = 0;
				if (y == 250)
					dy = 0;
			}
		}
		if (isDead) {
			dx = 0;
			dy = 7;
		}
		super.move();
	}

}

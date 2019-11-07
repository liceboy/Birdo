package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;



public class miniBoss1 extends enemy {
	int movecount;

	public miniBoss1(int x, int y) {
		super(x, y);
		health = 10;
		score = 1500;
		w = 40;
		h = 40;
		movecount = 300;
	}

	public void shootFeather() {
		if (shootcount <= 0) {
			if (!isDead && health > 8) {
				customShot("buckShot");
				shootcount = 100;
			}
			if (!isDead && health <= 7) {
				customShot("tripleShot");
				shootcount = 50;
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
			if (x < 700) {
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
		}
		if (isDead) {
			dx = 0;
			dy = 7;
		}
		super.move();
	}

}

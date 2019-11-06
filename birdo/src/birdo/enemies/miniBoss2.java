package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;



public class miniBoss2 extends enemy {
	int movecount;

	public miniBoss2(int x, int y) {
		super(x, y);
		health = 10;
		score = 1500;
		w = 40;
		h = 40;
		movecount = 300;
	}

	public void shootFeather() {
		if 
		if (shootcount == 0 && !isDead) {
			customShot("normal");
			shootcount = 100;
		}
		if (shootcount == 90) {
			customShot("normal");
		}
		if (shootcount == 80) {
			customShot("normal");
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

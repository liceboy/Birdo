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
		w = 20;
		h = 20;
		movecount = 300;
	}

	public void shootFeather() {
		if (!isDead && health >= 10) {
			if (shootcount <= 0 && !isDead) {
				customShot("homing");
				shootcount = 100;
			}
			if (shootcount % 10 == 0 && (shootcount / 10) < 80) {
				if (shootcount / 10 < 5)
					customShot("normal");
				else
					customShot("homing");
			}
		}
		
		if (!isDead && health < 10) {
			if (shootcount <= 0) {
				customShot("spinShot");
				shootcount = 10;
			}
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

	public void move() {

		if (x < 700 && health >= 10) 
			customMove("upDown");
		
		if (health < 10) 
			customMove("moveCenter");

		super.move();
	}

}

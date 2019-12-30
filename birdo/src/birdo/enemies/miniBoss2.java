package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class miniBoss2 extends enemy {

	public miniBoss2(int x, int y) {
		super(x, y);
		health = 20;
		score = 1500;
		w = 30;
		h = 30;
		c = new Color(109, 117, 141);
	}

	public void move() {
		if (x < 700 && health >= 10)
			customMove("upDown");
		if (health < 10)
			customMove("moveCenter");
		super.move();
	}

	public void shoot() {
		if (health >= 10) {
			if (shootCount <= 0) {
				customShot("homing");
				shootCount = 200;
			}
			if (shootCount % 20 == 0 && (shootCount / 20) < 80) {
				if (shootCount / 20 < 5)
					customShot("normal");
				else
					customShot("homing");
			}
		}

		if (health < 10 ) {
			if (shootCount <= 0) {
				customShot("spin");
				shootCount = 10;
			}
		}
		shootCount--;
	}

	public void poop() {
		return;
	}

}

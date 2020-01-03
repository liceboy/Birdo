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
		int[] stats = {-1000, attack, 1};
		if (health >= 10) {
			if (shotCount <= 0) {
				customShot("homing", stats);
				shotCount = 200;
			}
			if (shotCount % 20 == 0 && (shotCount / 20) < 80) {
				if (shotCount / 20 < 5)
					customShot("normal", stats);
				else
					customShot("homing", stats);
			}
		}

		if (health < 10 ) {
			if (shotCount <= 0) {
				customShot("spin", stats);
				shotCount = 10;
			}
		}
		shotCount--;
	}

	public void poop() {
		return;
	}

}

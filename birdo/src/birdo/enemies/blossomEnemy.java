package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class blossomEnemy extends enemy {

	public blossomEnemy(int x, int y) {
		super(x, y);
		health = 2;
		c = new Color(232, 106, 115);
	}

	public void move() {
		if (x < 800)
			customMove("default");
		super.move();
	}

	public void shoot() {
		if (shootCount == 0) {
			customShot("bloom");
			shootCount = 100;
		}
		shootCount--;
	}

	public void poop() {
		return;
	}
}

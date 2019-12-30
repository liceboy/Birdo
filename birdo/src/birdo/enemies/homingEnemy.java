package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class homingEnemy extends enemy {

	public homingEnemy(int x, int y) {
		super(x, y);
		c = new Color(121, 103, 85);
		health = 2;
	}

	public void move() {
		customMove("default");
		super.move();
	}

	public void shoot() {
		if (shootCount == 0) {
			customShot("homing");
			shootCount = 100;
		}
		shootCount--;
	}

	public void poop() {
		return;
	}

}

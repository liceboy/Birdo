package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// simple enemy that shoots homing shots

public class homingEnemy extends enemy {

	public homingEnemy(int x, int y) {
		super(x, y);
		c = Color.DARK_GRAY;
		health = 2;
	}

	public void move() {
		customMove("default");
		super.move();
	}

	public void shootFeather() {
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

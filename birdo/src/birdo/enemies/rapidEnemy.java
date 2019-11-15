package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// enemy that shoots very, very quickly

public class rapidEnemy extends enemy {

	public rapidEnemy(int x, int y) {
		super(x, y);
		c = Color.PINK;
	}

	public void move() {
		customMove("default");
		super.move();
	}

	public void shootFeather() {
		if (shootCount == 0) {
			customShot("normal");
			shootCount = 20;
		}
		shootCount--;
	}

	public void poop() {
		return;
	}
}
package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class rapidEnemy extends enemy {

	public rapidEnemy(int x, int y) {
		super(x, y);
		c = new Color(188, 74, 155);
	}

	public void move() {
		customMove("default");
		super.move();
	}

	public void shoot() {
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
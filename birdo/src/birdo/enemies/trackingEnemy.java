package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class trackingEnemy extends enemy{

	public trackingEnemy(int x, int y) {
		super(x, y);
		c = Color.RED;
	}
	
	public void move() {
		customMove("stop");
		super.move();
	}

	public void shoot() {
		if (shootCount == 0) {
			customShot("tracking");
			shootCount = 100;
		}
		shootCount--;
	}

	public void poop() {
		return;
	}

}
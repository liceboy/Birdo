package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class explodeEnemy extends enemy {

	public explodeEnemy(int x, int y) {
		super(x, y);
		c = Color.GREEN;
		health = 2;
	}

	public void shootFeather() {
		if (shootCount == 0) {
			customShot("tracking");
			shootCount = 20;
		}
		shootCount--;
	}

	public void move() {
		if (x < 700)
			dx = 0;
		else {
			dx = -3;
		}
		super.move();
	}

	public void poop() {
		return;
	}

}

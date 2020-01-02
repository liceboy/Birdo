package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class explodeEnemy extends enemy {

	public explodeEnemy(int x, int y) {
		super(x, y);
		c = new Color(88, 141, 190);
		health = 2;
	}

	public void shoot() {
		if (shotCount == 0) {
			customShot("explode");
			shotCount = 100;
		}
		shotCount--;
	}

	public void move() {
		customMove("normal");
		super.move();
	}

	public void poop() {
		return;
	}

}

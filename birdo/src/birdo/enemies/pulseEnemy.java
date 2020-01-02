package birdo.enemies;

import birdo.utilities.enemy;
import birdo.utilities.feather;
import java.awt.Color;

public class pulseEnemy extends enemy {

	public pulseEnemy(int x, int y) {
		super(x, y);
		c = new Color(50, 132, 100);
	}

	public void move() {
		customMove("stop");
		super.move();
	}

	public void shoot() {
		if (shotCount == 0) {
			customShot("target");
			shotCount = 100;
		}
		if (shotCount == 90)
			customShot("target");
		if (shotCount == 80)
			customShot("target");
		shotCount--;
	}

	public void poop() {
		return;
	}

}

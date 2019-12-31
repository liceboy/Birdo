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
		if (shootCount == 0) {
			customShot("target");
			shootCount = 100;
		}
		if (shootCount == 90)
			customShot("target");
		if (shootCount == 80)
			customShot("target");
		shootCount--;
	}

	public void poop() {
		return;
	}

}

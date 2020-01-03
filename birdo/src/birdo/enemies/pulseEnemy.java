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
		int[] stats = {-1000, attack, 1};
		if (shotCount == 0) {
			customShot("target", stats);
			shotCount = 100;
		}
		if (shotCount == 90)
			customShot("target", stats);
		if (shotCount == 80)
			customShot("target", stats);
		shotCount--;
	}

	public void poop() {
		return;
	}

}

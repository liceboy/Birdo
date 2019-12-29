package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// enemy that steadily shoots homing shots and pauses on the right edge for a short time

public class steadyEnemy extends enemy {

	public steadyEnemy(int x, int y) {
		super(x, y);
		c = Color.MAGENTA;
	}

	public void move() {
		customMove("stop");
		if (shootCount > 200)
			dx = -3;
		super.move();
	}

	public void shoot() {
		if (shootCount % 30 == 0)
			customShot("homing");
		shootCount++;
	}
	
	public void poop() {
		return;
	}

}

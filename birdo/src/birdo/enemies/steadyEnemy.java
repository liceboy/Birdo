package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class steadyEnemy extends enemy {

	public steadyEnemy(int x, int y) {
		super(x, y);
		c = new Color(32, 214, 199);
		
		createLoadout("target", 30, 1, 1);
	}

	public void move() {
		customMove("stop");
		if (shotCount > 200)
			dx = -3;
		super.move();
	}
	
	public void poop() {
		return;
	}

}

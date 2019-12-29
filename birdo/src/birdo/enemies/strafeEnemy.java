package birdo.enemies;

import java.awt.Color;

import birdo.utilities.egg;
import birdo.utilities.enemy;

// very fast enemy that jets forward and poops quickly

public class strafeEnemy extends enemy {

	public strafeEnemy(int x, int y) {
		super(x, y);
		dx = -6;
		c = Color.ORANGE;
		health = 1;
	}

	public void shoot() {
		return;
	}

	public void poop() {
		if (isDead)
			return;
		if (poopCount == 0) {
			eggs.add(new egg(this.x, this.y));
			poopCount = 20;
		}
		poopCount--;
	}

}

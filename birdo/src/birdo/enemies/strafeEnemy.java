package birdo.enemies;

import java.awt.Color;

import birdo.utilities.egg;
import birdo.utilities.enemy;

public class strafeEnemy extends enemy {

	public strafeEnemy(int x, int y) {
		super(x, y);
		dx = -6;
		c = Color.ORANGE;
		health = 1;
	}

	public void shootFeather() {
		return;
	}

	public void poop() {
		if (poopcount == 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y));
				poopcount = 20;
			}
		}
		poopcount--;
	}

}

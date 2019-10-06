package birdo.enemies;

import java.awt.Color;

import birdo.utilities.egg;
import birdo.utilities.enemy;

public class homingPooper extends enemy {

	public homingPooper(int x, int y) {
		super(x, y);
		c = Color.GREEN;
		health = 1;
	}

	public void shootFeather() {
		return;
	}

	public void poop() {
		if (poopcount == 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y, 15, 15, Color.YELLOW));
				poopcount = 250;
			}
		}
		poopcount--;
	}

	public void move() {
		if (x > p.x) // homing poopers should always spawn at y = 30;
			dx = -3;
		if (x < p.x)
			dx = 3;
		if (x == p.x)
			dx = 0;
		super.move();
	}

}

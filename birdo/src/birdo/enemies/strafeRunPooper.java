package birdo.enemies;

import java.awt.Color;

import birdo.utilities.egg;
import birdo.utilities.enemy;

public class strafeRunPooper extends enemy {

	public strafeRunPooper(int x, int y, Color c) {
		super(x, y, c);
		health = 1;
	}

	public void shootFeather() {
		return;
	}

	public void poop() {
		if (poopcount == 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y, 15, 15, Color.YELLOW));
				poopcount = 20;
			}
		}
		poopcount--;
	}

	public void move() {
		dx = -6; // strafe poopers should always spawn at y = 30
		super.move();
	}

}

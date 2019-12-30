package birdo.enemies;

import java.awt.Color;
import birdo.utilities.egg;
import birdo.utilities.enemy;

public class hoverEnemy extends enemy {

	public hoverEnemy(int x, int y) {
		super(x, y);
		c = new Color(89, 193, 53);
		health = 1;
	}

	public void move() {
		customMove("hover");
		super.move();
	}

	public void shoot() {
		return;
	}

	public void poop() {
		if (poopCount == 0) {
			eggs.add(new egg(this.x, this.y));
			poopCount = 250;
		}
		poopCount--;
	}

}

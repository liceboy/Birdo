package birdo.enemies;

import java.awt.Color;
import birdo.utilities.egg;
import birdo.utilities.enemy;

// enemy that hovers over the player and poops... slowly

public class hoverEnemy extends enemy {

	public hoverEnemy(int x, int y) {
		super(x, y);
		c = Color.GREEN;
		health = 1;
	}
	
	public void move() {
		customMove("hover");
		super.move();
	}

	public void shootFeather() {
		return;
	}

	public void poop() {
		if (poopcount == 0) {
			eggs.add(new egg(this.x, this.y));
			poopcount = 250;
		}
		poopcount--;
	}

}

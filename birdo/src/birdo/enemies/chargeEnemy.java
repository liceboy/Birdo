package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;
import birdo.utilities.enemy;

// fast enemy that doesn't shoot or poop, only charges at the player

public class chargeEnemy extends enemy {

	public chargeEnemy(int x, int y) {
		super(x, y);
		c = Color.GRAY;
		health = 2;
	}

	public void move() {
		customMove("charge");
		super.move();
	}

	public void shootFeather() {
		return;
	}

	public void poop() {
		return;
	}

}

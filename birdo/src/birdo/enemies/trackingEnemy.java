package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class trackingEnemy extends enemy{

	public trackingEnemy(int x, int y) {
		super(x, y);
		c = Color.RED;
	}
	
	public void move() {
		customMove("default");
		super.move();
	}

	public void shootFeather() {
		if (shootcount == 0) {
			customShot("tracking");
			shootcount = 100;
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

}
package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class spinEnemy extends enemy{
	
	public spinEnemy(int x, int y) {
		super(x, y);
		health = 3;
		c = new Color(250, 106, 10);
	}

	public void move() {
		if (x < 800)
			customMove("default");
		super.move();
	}

	public void shoot() {
		if (shotCount == 0) {
			customShot("spin");
			shotCount = 25;
		}
		shotCount--;
	}

	public void poop() {
		return;
	}
}

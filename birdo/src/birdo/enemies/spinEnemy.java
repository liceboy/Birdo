package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class spinEnemy extends enemy{
	
	public spinEnemy(int x, int y) {
		super(x, y);
		health = 3;
		c = Color.GREEN;
	}

	public void move() {
		if (x < 800)
			customMove("default");
		super.move();
	}

	public void shootFeather() {
		if (shootcount == 0) {
			customShot("spinShot");
			shootcount = 30;
		}
		shootcount--;
	}

	public void poop() {
		return;
	}
}

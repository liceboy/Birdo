package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class miniBoss1 extends enemy {

	public miniBoss1(int x, int y) {
		super(x, y);
		health = 20;
		score = 1500;
		w = 30;
		h = 30;
		c = new Color(51, 57, 65);
	}

	public void move() {
		if (x < 700)
			customMove("upDown");
		super.move();
	}

	public void shoot() {
		if (shotCount <= 0) {
			if (health > 10)
				customShot("buckshot");
				customShot("normal");
			if (health <= 10)
				customShot("triple");
			shotCount = 30;
		}
		shotCount--;
	}

	public void poop() {
		return;
	}

}

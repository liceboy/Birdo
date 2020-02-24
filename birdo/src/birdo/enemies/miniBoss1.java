package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class miniBoss1 extends enemy {

	public miniBoss1(int x, int y) {
		super(x, y);
		score = 1500;
		w = 30;
		h = 30;
		c = new Color(51, 57, 65);
		
		createStats(200, 10, 0);
	}

	public void move() {
		if (x < 700)
			customMove("upDown");
		super.move();
	}

	public void shoot() {
		int[] stats = {-1000, attack, 1};
		if (shotCount <= 0) {
			if (health > maxHealth / 3)
				customShot("buckshot", stats);
				customShot("normal", stats);
			if (health <= maxHealth / 3)
				customShot("triple, burn", stats);
			shotCount = 30;
		}
		shotCount--;
	}

	public void poop() {
		return;
	}

}

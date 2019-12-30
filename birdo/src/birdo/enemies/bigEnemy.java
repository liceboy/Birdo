package birdo.enemies;

import java.awt.Color;
import birdo.utilities.*;

public class bigEnemy extends enemy {

	public bigEnemy(int x, int y) {
		super(x, y);
		c = new Color(121, 58, 128);
		w = 30;
		h = 30;
		health = 5;
		score = 250;
	}

	public void move() {
		if (x < 800)
			dx = -1;
		super.move();
	}

	public void shoot() {
		if (shootCount == 0) {
			customShot("triple");
			shootCount = 150;
		}
		shootCount--;
	}

	public void poop() {
		return;
	}
}

package birdo.enemies;

import java.awt.Color;
import birdo.utilities.*;

// large, slow enemy that shoots triple shots

public class bigEnemy extends enemy {

	public bigEnemy(int x, int y) {
		super(x, y);
		c = Color.MAGENTA;
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

	public void shootFeather() {
		if (shootcount == 0)
			if (!isDead) {
				customShot("tripleShot");
				shootcount = 200;
			}
		shootcount--;
	}

	public void poop() {
		return;
	}
}

package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class homingEnemy extends enemy {

	public homingEnemy(int x, int y, Color c) {
		super(x, y, c);
		health = 1;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {

				feather f = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				int deltaX = p.x - x;
				int deltaY = p.y - y;

				double theta = Math.atan((double) deltaY / (double) deltaX);
				f.dx = -1 * (int) (10 * Math.cos(theta));
				f.dy = -1 * (int) (10 * Math.sin(theta));

				feathers.add(f);
				// adds a feather if alive
				shootcount = 100;
			}
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

}

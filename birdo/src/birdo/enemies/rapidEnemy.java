package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class rapidEnemy extends enemy {

	public rapidEnemy(int x, int y) {
		super(x, y);
		c = Color.CYAN;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				feathers.add(new feather(this.x, this.y, false));
				// adds a feather if alive
				shootcount = 10;
			}
		}
		shootcount--;
	}

	public void poop() {
		return;
	}
}
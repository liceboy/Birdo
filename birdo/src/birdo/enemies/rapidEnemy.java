package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// enemy that shoots very, very quickly

public class rapidEnemy extends enemy {

	public rapidEnemy(int x, int y) {
		super(x, y);
		c = Color.PINK;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			customShot("normal");
			shootcount = 20;
		}
		shootcount--;
	}

	public void poop() {
		return;
	}
}
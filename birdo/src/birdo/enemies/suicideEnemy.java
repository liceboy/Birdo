package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// enemy that slowly drifts toward the player, shooting triple shots until its death

public class suicideEnemy extends enemy {

	public suicideEnemy(int x, int y) {
		super(x, y);
		health = 2;
		c = Color.GREEN;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			customShot("bloomShot");
			shootcount = 100;
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

	public void move() {

		if (x < 800)
			customMove("homing");

		super.move();

	}
}

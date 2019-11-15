package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// enemy that slowly drifts toward the player, shooting triple shots until its death

public class blossomEnemy extends enemy {

	public blossomEnemy(int x, int y) {
		super(x, y);
		health = 2;
		c = Color.GREEN;
	}

	public void move() {
		if (x < 800)
			customMove("default");
		super.move();
	}

	public void shootFeather() {
		if (shootCount == 0) {
			customShot("bloomShot");
			shootCount = 100;
		}
		shootCount--;
	}

	public void poop() {
		return;
	}
}

package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;

import birdo.utilities.enemy;
import birdo.utilities.feather;

// simple enemy that shoots homing shots

public class homingEnemy extends enemy {

	public homingEnemy(int x, int y) {
		super(x, y);
		c = Color.DARK_GRAY;
		health = 2;
	}
	
	public void move() {
		if (x > 800) 
			dx = -3;
		if (x < 800)
			dx = -1;
		if (x < p.x) 
			dx = -3;
		super.move();
	}

	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				customShot("homing");
				shootcount = 100;
			}
		}
		shootcount--;
	}

	public void poop() {
		return;
	}

}

package birdo.enemies;

import birdo.utilities.enemy;
import birdo.utilities.feather;
import java.awt.Color;

// enemy that stops at the right edge, regularly sending pulses of three homing shots

public class pulseEnemy extends enemy {

	public pulseEnemy(int x, int y) {
		super(x, y);
		c = Color.CYAN;
	}

	public void move() {
		if (x < 700)
			dx = 0;
		super.move();
	}

	public void shootFeather() {
		if (shootcount == 0) {
			customShot("homing");
			shootcount = 100;
		}
		if (shootcount == 90)
			customShot("homing");
		if (shootcount == 80)
			customShot("homing");
		shootcount--;
	}

	public void poop() {
		return;
	}

}

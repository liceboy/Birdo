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
		if (shootCount == 0) {
			customShot("homing");
			shootCount = 100;
		}
		if (shootCount == 90)
			customShot("homing");
		if (shootCount == 80)
			customShot("homing");
		shootCount--;
	}

	public void poop() {
		return;
	}

}

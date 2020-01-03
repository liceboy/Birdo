package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class laserEnemy extends enemy{

	public laserEnemy(int x, int y) {
		super(x, y);
		c = new Color(180, 32, 42);
	}
	
	public void move() {
		if (x < 700)
			dx = 0;
		super.move();
	}
	
	public void shoot() {
		return;
	}

	public void poop() {
		return;
	}
}
	



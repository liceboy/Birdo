package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class laserEnemy extends enemy{

	public laserEnemy(int x, int y) {
		super(x, y);
		c = Color.PINK;
	}
	
	public void move() {
		if (x < 700)
			dx = 0;
		super.move();
	}
	
	public void shootFeather() {
		if (shootCount == 0) {
			customShot("laser");
			shootCount = 5;
		}
		shootCount--;
	}

	public void poop() {
		return;
	}
}
	



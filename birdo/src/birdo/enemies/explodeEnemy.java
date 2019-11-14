package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class explodeEnemy extends enemy{

	public explodeEnemy(int x, int y) {
		super(x, y);
		c = Color.GREEN;
		health = 2;
	}
	
	public void shootFeather () {
		if (shootcount == 0) {
				customShot("explode");
				shootcount = 50;
		}
		shootcount--;
	}
	
	public void move () {
		customMove("normal");
		super.move();
	}
	
	public void poop () {
		return;
	}

}

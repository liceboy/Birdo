package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class explodeEnemy extends enemy {

	public explodeEnemy(int x, int y) {
		super(x, y);
		c = new Color(88, 141, 190);
		
		createStats(20, 10, 0);
		createLoadout("explode", 100, 1, 1);
	}

	public void move() {
		customMove("normal");
		super.move();
	}

	public void poop() {
		return;
	}

}

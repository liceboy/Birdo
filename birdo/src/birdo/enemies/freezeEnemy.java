package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class freezeEnemy extends enemy {

	public freezeEnemy(int x, int y) {
		super(x, y);
		c = new Color(121, 103, 85);
		
		createStats(20, 10, 0);
		createLoadout("freeze, target", 100, 1, 1);
	}

	public void move() {
		customMove("default");
		super.move();
	}

	public void poop() {
		return;
	}

}

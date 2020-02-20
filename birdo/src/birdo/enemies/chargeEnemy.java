package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;
import birdo.utilities.enemy;

public class chargeEnemy extends enemy {

	public chargeEnemy(int x, int y) {
		super(x, y);
		c = new Color(199, 176, 139);
		
		createStats(20, 10, 0);
	}

	public void move() {
		customMove("charge");
		super.move();
	}

	public void shoot() {
		return;
	}

	public void poop() {
		return;
	}

}

package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class blossomEnemy extends enemy {

	public blossomEnemy(int x, int y) {
		super(x, y);
		c = new Color(232, 106, 115);
		
		createStats(20, 10, 0);
		createLoadout("bloom", 100, 1, 1);
	}

	public void move() {
		if (x < 800)
			customMove("default");
		super.move();
	}

	public void poop() {
		return;
	}
}

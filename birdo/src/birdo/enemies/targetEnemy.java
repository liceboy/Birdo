package birdo.enemies;

import java.awt.Color;
import java.util.ArrayList;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class targetEnemy extends enemy {

	public targetEnemy(int x, int y) {
		super(x, y);
		c = new Color(121, 103, 85);
		health = 2;
		
		int[] stats = {100, attack, 1};
		loadout.put("target", stats);
	}

	public void move() {
		customMove("default");
		super.move();
	}

	public void poop() {
		return;
	}

}

package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class explodeEnemy extends enemy {

	public explodeEnemy(int x, int y) {
		super(x, y);
		c = new Color(88, 141, 190);
		health = 2;
		
		int[] stats = {100, attack, 1};
		loadout.put("explode", stats);
	}

	public void move() {
		customMove("normal");
		super.move();
	}

	public void poop() {
		return;
	}

}

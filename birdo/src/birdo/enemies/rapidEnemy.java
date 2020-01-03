package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class rapidEnemy extends enemy {

	public rapidEnemy(int x, int y) {
		super(x, y);
		c = new Color(188, 74, 155);
		
		int[] stats = {20, attack, 1};
		loadout.put("normal", stats);
	}

	public void move() {
		customMove("default");
		super.move();
	}

	public void poop() {
		return;
	}
}
package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class homingEnemy extends enemy{

	public homingEnemy(int x, int y) {
		super(x, y);
		c = new Color(156, 219, 67);
		
		int[] stats = {200, attack, 1};
		loadout.put("homing", stats);
	}
	
	public void move() {
		customMove("stop");
		super.move();
	}

	public void poop() {
		return;
	}

}
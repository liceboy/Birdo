package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class spinEnemy extends enemy{
	
	public spinEnemy(int x, int y) {
		super(x, y);
		health = 3;
		c = new Color(250, 106, 10);
		
		int[] stats = {25, attack, 1};
		loadout.put("spin", stats);
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

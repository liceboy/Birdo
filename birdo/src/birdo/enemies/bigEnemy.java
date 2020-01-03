package birdo.enemies;

import java.awt.Color;
import java.util.Map;

import birdo.utilities.*;

public class bigEnemy extends enemy {

	public bigEnemy(int x, int y) {
		super(x, y);
		c = new Color(121, 58, 128);
		w = 30;
		h = 30;
		health = 5;
		score = 250;
		
		int[] stats = {150, attack, 1};
		loadout.put("triple", stats);
	}

	public void move() {
		if (x < 800)
			dx = -1;
		super.move();
	}

	public void poop() {
		return;
	}
}

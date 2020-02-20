package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class spinEnemy extends enemy{
	
	public spinEnemy(int x, int y) {
		super(x, y);
		c = new Color(250, 106, 10);
		
		createLoadout("spin", 25, 1, 1);
		createStats(3, 1, 0);
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

package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;

public class miniBoss3 extends enemy{
	
	public miniBoss3(int x, int y) {
		super(x, y);
		score = 1500;
		w = 30;
		h = 30;
		c = new Color(42, 57, 80);
		
		createStats(200, 10, 0);
	}

	public void move() {
		if (x < 700)
			customMove("upDown");
		super.move();
		
	}

	public void shoot() {
		int[] stats = {-1000, attack, 1};
		if (x < 700) {
			if (shotCount < 0)
				shotCount = 200;
			if (shotCount == 150)
				customShot("warningLaser", 0, 90000);
			if (shotCount == 50)
				customShot("laser", stats);
		}
		shotCount--;
	}
	
	

	public void poop() {
		return;
	}

}

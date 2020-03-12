package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.obstacle;

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
		if (x < 750)
			customMove("upDown");
		super.move();
		
	}

	public void shoot() {
		int[] stats = {-1000, attack, 1};
		
		if (health > maxHealth / 2) {
			if (x < 750) {
				if (shotCount == 150) {
					if (obstacles.size() == 0)
						customShot("homing, slow, stun", stats);
				}
				if (shotCount == 100) {
					if (obstacles.size() == 0)
						customShot("homing, slow, burn", stats);
				}
				if (shotCount == 50) {
					if (obstacles.size() == 0)	
						customShot("homing, slow, freeze", stats);
				}
				if (shotCount <= 0) {
					if (obstacles.size() == 0)
						customObstacle("skyLaser");
					else if (obstacles.size() > 0) {
						//obstacles.clear();
						customShot("homing, slow, plasma", stats);
					}
					shotCount = 200;
				}
			}
		}
		else
			//obstacles.clear();

		if (health <= maxHealth / 2) {
			if (shotCount <= 0) {
				customShot("spinBurstSlow", stats);
				shotCount = 15;
			}
		}
		
		shotCount--;
	}
	
	

	public void poop() {
		return;
	}

}

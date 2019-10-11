package birdo.enemies;

import birdo.utilities.enemy;
import birdo.utilities.feather;
import birdo.utilities.truehomingFeather;

public class trueHomingEnemy extends enemy {

	public trueHomingEnemy(int x, int y) {
		super(x, y);
		
	}
	
	public void shootFeather () {
		
		if (shootcount == 0) {
			if (!isDead) {

				feather f = new truehomingFeather(this.x, this.y, false);
				int deltaX = p.x - x;
				int deltaY = p.y - y;

				double theta = Math.atan((double) deltaY / (double) deltaX);
				f.dx = -1 * (int) (5 * Math.cos(theta));
				f.dy = -1 * (int) (5 * Math.sin(theta));

				feathers.add(f);
				// adds a feather if alive
				shootcount = 100;
			}
		}
		shootcount--;
	}
	
	public void poop () {
		return;
	}
	
	public void move() {
		if (x > 800) 
			dx = -3;
		if (x < 800)
			dx = -1;
		if (x < p.x) 
			dx = -3;
		super.move();
	}

}

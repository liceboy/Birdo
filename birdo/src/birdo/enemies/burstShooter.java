package birdo.enemies;

import birdo.utilities.enemy;
import birdo.utilities.feather;

import java.awt.Color;

public class burstShooter extends enemy{
	int shootcount1;
	public burstShooter(int x, int y) {
		super(x, y);
		c = Color.CYAN;
		shootcount1 = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void poop () {
		return;
	}
	
	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				feather f = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				int deltaX = p.x - x;
				int deltaY = p.y - y;

				double theta = Math.atan((double) deltaY / (double) deltaX);
				f.dx = -1 * (int) (10 * Math.cos(theta));
				f.dy = -1 * (int) (10 * Math.sin(theta));

				feathers.add(f);
				// adds a feather if alive
				shootcount = 100;
			}
		}
		if (shootcount == 90) {
			feather f = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
			int deltaX = p.x - x;
			int deltaY = p.y - y;

			double theta = Math.atan((double) deltaY / (double) deltaX);
			f.dx = -1 * (int) (10 * Math.cos(theta));
			f.dy = -1 * (int) (10 * Math.sin(theta));

			feathers.add(f);
		}
		if (shootcount == 80) {
			feather f = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
			int deltaX = p.x - x;
			int deltaY = p.y - y;

			double theta = Math.atan((double) deltaY / (double) deltaX);
			f.dx = -1 * (int) (10 * Math.cos(theta));
			f.dy = -1 * (int) (10 * Math.sin(theta));

			feathers.add(f);
		}
		shootcount--;
	}
	
	public void move () {
		if (x > 700)
			dx = -3;
		if (x < 700)
			dx = 0;
		super.move();
	}

}

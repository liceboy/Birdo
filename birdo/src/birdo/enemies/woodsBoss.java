package birdo.enemies;

import java.awt.Color;

import birdo.utilities.enemy;
import birdo.utilities.feather;

public class woodsBoss extends enemy {
	int movecount;
	public woodsBoss(int x, int y) {
		super(x, y);
			health = 15;
			score = 1500;
			w = 40;
			h = 40;
			movecount = 300;
			
	}
	
	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead && health > 8) {
				feather f = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				feather f1 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				feather f2 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				feather f3 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				feather f4 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				feather f5 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				
				f1.dx = -1 * (int) (5 * Math.cos(Math.PI / 8));
				f1.dy = -1 * (int) (5 * Math.sin(Math.PI / 8));
				f2.dx = -1 * (int) (5 * Math.cos(Math.PI / 4));
				f2.dy = -1 * (int) (5 * Math.sin(Math.PI / 4));
				f3.dx = -1 * (int) (5 * Math.cos(3 *Math.PI / 8));
				f3.dy = -1 * (int) (5 * Math.sin(3 *Math.PI / 8));
				f4.dx = (int) (5 * Math.cos(5 *Math.PI / 8));
				f4.dy = (int) (5 * Math.sin(5 *Math.PI / 8));
				f5.dx = (int) (5 * Math.cos(6 *Math.PI / 8));
				f5.dy = (int) (5 * Math.sin(6 *Math.PI / 8));
				f.dx = (int) (5 * Math.cos(7 *Math.PI / 8));
				f.dy = (int) (5 * Math.sin(7 *Math.PI / 8));
				
				feathers.add(f1);
				feathers.add(f2);
				feathers.add(f3);
				feathers.add(f4);
				feathers.add(f5);
				feathers.add(f);

				// adds a feather if alive
				shootcount = 100;
			}
			if (!isDead && health <= 7) {
				feather f = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				feather f1 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				feather f2 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
				f.dx = (int) (5 * Math.cos(Math.PI));
				f.dy = (int) (5 * Math.sin(Math.PI));
				f1.dx = -1 *(int) (5 * Math.cos(15 *Math.PI / 180));
				f1.dy = -1 *(int) (5 * Math.sin(15 *Math.PI / 180));
				f2.dx = -1 *(int) (5 * Math.cos(345 *Math.PI / 180));
				f2.dy = -1 *(int) (5 * Math.sin(345 *Math.PI / 180));
				feathers.add(f);
				feathers.add(f1);
				feathers.add(f2);
				// adds a feather if alive
				shootcount = 50;
			}
		}
		shootcount--;
	}
	
	public void poop () {
		return;
	}
	
	public void move () {
		if (!isDead && health > 8) {
		if (x > 700)
			dx = -3;
		if (x < 700) {
		if(movecount > 150) {
			dx = 0;
			dy = -2;
		}
		if (movecount < 150) {
			dx = 0;
			dy = 2;
		}
		if (movecount <= 0) {
			movecount = 300;
		}
		movecount--;
		}
		}
		if (!isDead && health <= 7) {
			if (y < p.y)
				dy = 2;
			if (y > p.y)
				dy = -2;
		}
		super.move();
	}

}

package birdo;

import java.awt.Color;

public class suicideBomber extends enemy{

	public suicideBomber(int x, int y, Color c) {
		super(x, y, c);
		health = 2; 
	}
	
	public void shootFeather () {
			if (shootcount == 0) {
				if (!isDead) {
					feather f = new feather(this.x , this.y , 15, 10, Color.BLACK, false);
					f.dx = 5;
					feathers.add(f);
					feather f1 = new feather(this.x , this.y , 15, 10, Color.BLACK, true);
					f1.dx = -5;
					feathers.add(f1);
					feather f2 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
					f2.dx = -1 * (int) (5 * Math.cos(-1 * Math.PI/4));
					f2.dy = -1 * (int) (5 * Math.sin(-1 * Math.PI/4));
					feathers.add(f2);
					feather f3 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
					f3.dx = -1 * (int) (5 * Math.cos( Math.PI/4));
					f3.dy = -1 * (int) (5 * Math.sin( Math.PI/4));
					feathers.add(f3);
					feather f4 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
					f4.dx = -1 * (int) (5 * Math.cos(3 * Math.PI/4));
					f4.dy = -1 * (int) (5 * Math.sin(3 * Math.PI/4));
					feathers.add(f4);
					feather f5 = new feather(this.x, this.y, 15, 10, Color.BLACK, false);
					f5.dx = -1 * (int) (5 * Math.cos(5 * Math.PI/4));
					f5.dy = -1 * (int) (5 * Math.sin(5 * Math.PI/4));
					feathers.add(f5);
					
					shootcount = 100;
			}
			}
			shootcount--;
		}
	
	public void poop () {
		return;
	}

	public void move() {
		if (!isDead) {
			
			if(x > 800) {
				dx = -3;
				super.move();
				return; 
			}
			
			int deltaX = p.x - x;
			int deltaY = p.y - y;
			
			double theta = Math.atan((double) deltaY/(double) deltaX);
			
			this.dx = -1 * (int)(2 * Math.cos(theta));
			this.dy = -1 * (int)(2 * Math.sin(theta));
			
			super.move();
			
			}
			
			if (isDead)
				super.move();
	}
}

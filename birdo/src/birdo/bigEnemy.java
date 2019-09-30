package birdo;

import java.awt.Color;

public class bigEnemy extends enemy {

	public bigEnemy(int x, int y, Color c) {
		super(x, y, c);
		w = 60; h = 60;
		health = 5;
		score = 250;
	}
	
	public void move() {
		if (x > 800)
			dx = -3;
		if (x < 800)
			dx = -1;
		super.move();
	}
	
	public void shootFeather() {
		if (shootcount == 0)
			if (!isDead) {
				
				feather f = new feather(this.x , this.y , 30, 20, Color.BLACK, false);
				feather f1 = new feather(this.x , this.y , 30, 20, Color.BLACK, false);
				feather f2 = new feather(this.x , this.y , 30, 20, Color.BLACK, false);
				
				f.dx = -5;
		
				f1.dx = -1 * (int)(5 * Math.cos(Math.PI/4));
				f1.dy = -1 * (int)(5 * Math.sin(Math.PI/4));
				
				f2.dx = -1 * (int) (5 * Math.cos(-1 * Math.PI/4));
				f2.dy = -1 * (int) (5 * Math.sin(-1 * Math.PI/4));
				
				feathers.add(f);
				feathers.add(f1);
				feathers.add(f2);
				
				shootcount = 200;
			}
		shootcount--;
		}
	
	public void poop () {
		return;
	}
}

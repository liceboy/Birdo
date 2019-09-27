package birdo;

import java.awt.Color;

public class suicideBomber extends enemy{

	public suicideBomber(int x, int y, Color c) {
		super(x, y, c);
		health = 2; 
	}
	
	public void shootFeather () {
		return;
	}
	
	public void poop () {
		return;
	}
	
	public void kaboom () {
		dx = 0;
		dy = 0; 
		int theta = 0; 
		for (int x = 0; x < 8; x ++ ) {
		feather f = new feather(this.x , this.y , 15, 10, Color.BLACK, false);
		f.dx = -1 * (int)(10 * Math.cos(theta));
		f.dy = -1 * (int)(10 * Math.sin(theta));
		feathers.add(f);
		theta += (int) Math.PI/4;
		}
		
	}
	
	public void move() {
		while (x != p.x && y != p.y) {
		if (x < p.x)
			dx = 3;
		if (x > p.x)
			dx = -3;
		if (y > p.y)
			dy = -3;
		if (y < p.y)
			dy = 3;
		}
		super.move();
	}

}

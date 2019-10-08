package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;

public class powerup extends object{
	int movecount;
	public String type;
	public powerup(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		movecount = 300;
		type = " ";
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g) {
		super.draw(g);
	}
	
	public void move() {
		if (x > 700)
			dx = -3;
		if (x < 700) {
		if(movecount > 150) {
			dx = 2;
			dy = -2;
		}
		if (movecount < 150) {
			dx = 2;
			dy = 2;
		}
		if (movecount <= 0) {
			movecount = 300;
		}
		movecount--;
		}
		super.move();
	}

}

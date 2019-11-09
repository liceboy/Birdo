package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;

public class powerup extends object {
	int movecount;
	public String type;

	public powerup(int x, int y, String t) {
		super(x, y, 8, 8, Color.BLUE);
		movecount = 300;
		type = t;
		dx = -3;
	}

	public void draw(Graphics g) {
		super.draw(g);
	}

	public void move() {

		super.move();
	}

}

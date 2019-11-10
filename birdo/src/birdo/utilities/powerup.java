package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;

public class powerup extends object {

	public String type;
	public int ammo;
	int startY;
	
	public powerup(int x, int y, String t) {
		super(x, y, 8, 8, Color.BLUE);
		startY = y;
		type = t;
		if (type == "eggs")
			ammo = 3;
		if (type == "bloomShot")
			ammo = 1;
		if (type == "buckShot")
			ammo = 1;
		if (type == "tripleShot")
			ammo = 3;
		dx = -2;
		dy = 1;
	}

	public void draw(Graphics g) {
		super.draw(g);
	}

	public void move() {
		if (y > startY + 40)
			dy = -1;
		if (y < startY - 40)
			dy = 1;
		super.move();
	}

}

package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;

public class powerup extends object {

	public String type;
	public int ammo;
	int startY;
	
	public powerup(int x, int y, String t) {
		super(x, y, 16, 16, Color.BLUE);
		startY = y;
		type = t;
		if (type == "Eggs")
			ammo = 3;
		if (type == "bloomShot")
			ammo = 3;
		if (type == "buckShot")
			ammo = 3;
		if (type == "tripleShot")
			ammo = 3;
		if (type == "Invulnerability")
			ammo = 1;
		if (type == "Heal")
			ammo = 0;
		if (type == "rapidFire")
			ammo = 1;
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

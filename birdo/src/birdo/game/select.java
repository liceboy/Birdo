package birdo.game;

import java.awt.Color;
import java.awt.Graphics;

public class select {

	public select() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("Level Select:", 100, 100);
		g.drawString("Default Level: Z", 100, 130);
		g.drawString("Dev Mode: X", 100, 160);
		g.drawString("Press Esc to go back", 100, 250);
	}

	public void move() {

	}

}

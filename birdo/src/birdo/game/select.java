package birdo.game;

import java.awt.Color;
import java.awt.Graphics;

public class select {

	public select() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(16f));
		g.drawString("-Level Select-", 100, 100);
		g.setFont(g.getFont().deriveFont(12f));
		g.drawString("Default Level: Z", 100, 130);
		g.drawString("", 350, 130);
		g.drawString("Dev Mode: X", 100, 160);
		g.drawString("[1 to Backspace spawns enemies]", 350, 160);
		g.drawString("[T to P spawns powerups]", 350, 175);
		g.drawString("[K to L spawns bosses]", 350, 190);
		g.drawString("Shop: C", 100, 220);
		g.drawString("Press Esc to exit", 100, 250);
	}

	public void move() {

	}

}

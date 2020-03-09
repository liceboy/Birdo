package birdo.game;

import java.awt.Color;
import java.awt.Graphics;

public class select {
	
	String rules = "";

	public select() {

	}

	public void draw(Graphics g, assets a) {
		g.setColor(Color.BLACK);
		g.setFont(a.fonts[1]);
		g.drawString("-Level Select-", 100, 100);
		g.setFont(a.fonts[0]);
		g.drawString("Default Level: Z", 100, 130);
		g.drawString("> " + rules, 350, 130);
		g.drawString("Dev Mode: X", 100, 160);
		g.drawString("[1 to Backspace spawns enemies]", 350, 160);
		g.drawString("[T to P spawns powerups]", 350, 175);
		g.drawString("[K to L spawns bosses]", 350, 190);
		g.drawString("Shop: C", 100, 220);
		g.drawString("Press Esc to exit", 100, 280);
		g.drawString("Press A to Change Difficulty", 100, 310);
	}

	public void move() {

	}

}

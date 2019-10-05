package birdo.game;

import java.awt.Color;
import java.awt.Graphics;

public class levelSelect {

	public levelSelect() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("Level Select:", 100, 100);
		g.drawString("Woods: 1", 100, 130);
		g.drawString("City: 2", 100, 160);
		g.drawString("Beach: 3", 100, 190);
		g.drawString("Sky: 4", 100, 220);
		g.drawString("Press Esc to go back", 100, 250);
	}

	public void move() {

	}

}

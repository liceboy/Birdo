package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class shop {

	public shop() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(16f));
		g.drawString("-Shop-", 50, 50);
		g.setFont(g.getFont().deriveFont(12f));
	}

	public void move() {

	}

}

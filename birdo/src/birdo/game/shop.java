package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class shop {

	public shop() {

	}

	public void draw(Graphics g, assets a) {
		g.setColor(Color.BLACK);
		g.setFont(a.fonts[1]);
		g.drawString("-Shop-", 50, 50);
		g.drawString("Exit: Esc", 600, 50);
	}

	public void move() {

	}

}

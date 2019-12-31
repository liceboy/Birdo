package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class title {

	public title() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(16f));
		g.drawString("-Birdo v0.5-", 100, 100);
		g.setFont(g.getFont().deriveFont(12f));
		g.drawString("Benjamin Guan: Concept, Programming", 100, 130);
		g.drawString("Joseph Ilagan: Programming", 100, 160);
		g.drawString("Press Space", 100, 240);
	}

	public void move() {

	}

}

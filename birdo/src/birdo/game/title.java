package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class title {

	public title() {

	}

	public void draw(Graphics g, assets a) {
		g.setColor(Color.BLACK);
		g.setFont(a.fonts[1]);
		g.drawString("-Birdo v0.5-", 100, 100);
		g.setFont(a.fonts[0]);
		g.drawString("Benjamin Guan: Concept, Programming", 100, 130);
		g.drawString("Joseph Ilagan: Direction, Programming", 100, 160);
		g.drawString("Press Space", 100, 240);
	}

	public void move() {

	}

}

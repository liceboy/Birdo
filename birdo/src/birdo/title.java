package birdo;
import java.awt.Color;
import java.awt.Graphics;

public class title {
	

	public title() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("This is the title", 100, 100);
		g.drawString("Start Game with 2 player: Space", 100, 130);
		g.drawString("Start game with 1 player: Z", 100, 160);
		g.drawString("Start Game1: Shift", 100, 190);
	}
	
	public void move() {
		

	}
	
}

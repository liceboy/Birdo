package birdo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public abstract class game {
	
	object player = new object (130, 130, 30, 30, Color.BLUE);
	// rectangle representing birdo
	
	int score = 0;
	// player score
	
	public game() {
		
	}
	
	public void move() {
		player.move();
		collision();
	}
	
	public void draw(Graphics g) {
		player.draw(g);
		
		g.setFont(new Font("Arial", 1, 16));
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 10, 440);
		
	}
	
	
	public void collision() {
		
		if(player.x < 0) player.x = 0;
		if(player.x > 800 - player.w) player.x = 800 - player.w;
		if(player.y < 0) player.y = 0;
		if(player.y > 800 - player.h) player.y = 800 - player.h;
		
	

	}
	
	
}

package birdo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class game {
	
	player player = new player (130, 130, 30, 30, Color.BLUE);
	ArrayList<enemy> enemies;
	// rectangle representing birdo
	int score = 0;
	// player score
	String state;
	
	public game() {
		enemies = new ArrayList <enemy>(); 
		for (int x = 0; x < 20; x++) {
			enemy e = new enemy (750, (int) (Math.random() * 450) + 50, 30, 30, Color.BLACK);
			enemies.add(e);
		}
	}
	
	public void move() {
		player.move();
		for (enemy e: enemies)
			e.move();
		collision();
	}
	
	public void draw(Graphics g) {
		player.draw(g);
		player.shootFeather();
		player.poop();
		for (enemy e: enemies) {
			e.draw(g);
			e.shootFeather();
			e.checkFeatherHits(player);
		}
		check();
		g.setFont(new Font("Arial", 1, 16));
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 650, 40);
		g.drawString("Health: " + player.health, 25, 40);
		player.checkDead();
		if (player.isDead == true)
			g.drawString("Game Over!", 425, 150);
		
	}
	
	public void check () {
		for (int x = 0; x != enemies.size(); x++) {
			if (enemies.get(x).x < -40) {
				enemies.remove(x);
				x--;
			}
		}
		player e;
		for (int x = 0; x != enemies.size(); x++) {
			e = enemies.get(x);
			player.checkFeatherHits(e);
			player.checkEggHits(e);
			if (e.health <= 0) {
				e.isDead = true;
				enemies.remove(e);
			}
	}
	}

	public void collision() {
		
		if(player.x < 0) player.x = 0;
		if(player.x > 800 - player.w) player.x = 800 - player.w;
		if(player.y < 0) player.y = 0;
		if(player.y > 800 - player.h) player.y = 800 - player.h;
		
	

	}
	
	
}

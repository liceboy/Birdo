package birdo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class game {
	
	player player;
	// rectangle representing birdo
	ArrayList<enemy> enemies;
	// array list holding enemies
	int score = 0;
	// player score
	String state;
	// state of game
	
	public game() {
		player = new player (130, 130, Color.BLUE); 
		enemies = new ArrayList <enemy>(); 
		for (int x = 0; x < 100; x++) {
			enemy e = null;

			//e = new chargingEnemy (x * 50 + 800 + (int) (Math.random() * 100), (int) (Math.random() * 300 ) + 100, Color.DARK_GRAY);

			//enemies.add(e);
		}
	}
	
	public void move() {
		player.move();
		for (enemy e: enemies) {
			e.p = player;
			e.move();
		}
		collision();
	}
	
	public void draw(Graphics g) {
		player.draw(g);
		
		for (enemy e: enemies)
			e.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 650, 40);
		g.drawString("Health: " + player.health, 25, 40);
		if (player.health <= 0) {
			player.dx = 0; // if game over, play this death animation and display these strings, new state
			player.dy = 7; // level will continue to play :)
			g.drawString("Game Over!", 300, 150);
			g.drawString("Continue: SPACEBAR", 300, 175);
			g.drawString("Quit: SHIFT", 300, 200);
			state = "GameOver";
		}
	}

	public void collision() {
		
		// INVULNERABILITY
		
		if(player.invulnerable) {
			player.c = Color.RED;
			player.invulnerablecooldown--;
			if(player.invulnerablecooldown == 0) {
				player.invulnerable = false;
				player.playercanshoot = true;
				player.c = Color.BLUE;
			}
		}
		
		// SCREEN BORDERS
		
		if(player.x < 0) player.x = 0;
		if(player.x > 800 - player.w) player.x = 800 - player.w;
		if(player.y < 0) player.y = 0;
		if(player.y > 500 - player.h) player.y = 500 - player.h;
		
		// PLAYER HITBOXES
		
		for(int i = 0; i != enemies.size(); i++) {
			enemy e = enemies.get(i);
			
			// hit the enemy? take damage
			
			if(player.getHitBox().intersects(e.getHitBox())) {
				if(!player.invulnerable) {
					player.health--;
					player.invulnerable = true;
					player.invulnerablecooldown = 100;
				}
			}
			
			// hit enemy feather? take damage
			
			for(int j = 0; j != e.feathers.size(); j++) {
				feather f = e.feathers.get(j);
				if(player.getHitBox().intersects(f.getHitBox())) {
					if(!player.invulnerable) {
						player.health--;
						player.invulnerable = true;
						player.invulnerablecooldown = 100;
					}
					e.feathers.remove(j);
					j--;
				}
				if(f.x > 800) {
					e.feathers.remove(j);
					j--;
				}
			}
			
			// hit enemy egg? take damage
			
			for(int k = 0; k != e.eggs.size(); k++) {
				egg p = e.eggs.get(k);
				if(player.getHitBox().intersects(p.getHitBox())) {
					if(!player.invulnerable) {
						player.health--;
						player.invulnerable = true;
						player.invulnerablecooldown = 100;
					}
					e.eggs.remove(k);
					k--;
				}
				if(p.x > 800) {
					e.eggs.remove(k);
					k--;
				}
			}
			
		}
		
		// ENEMY HITBOXES
		
		for(int i = 0; i != enemies.size(); i++) {
			enemy e = enemies.get(i);
			
			// enemy hit my feather? take damage
			
			for(int j = 0; j != player.feathers.size(); j++) {
				feather f = player.feathers.get(j);
				if(e.getHitBox().intersects(f.getHitBox())) {
					e.health--;
					player.feathers.remove(j);
					j--;
				}
				if(f.x > 800) {
					player.feathers.remove(j);
					j--;
				}
			}
			
			// enemy hit my egg? take damage
			
			for(int k = 0; k != player.eggs.size(); k++) {
				egg p = player.eggs.get(k);
				if(e.getHitBox().intersects(p.getHitBox())) {
					e.health--;
					player.eggs.remove(k);
					k--;
				}
				if(p.y > 500) {
					player.eggs.remove(k);
					k--;
				}
			}
			
			if (e.health <= 0) {
				e.isDead = true;
				enemies.remove(i);
				score += 100;
				i--;
			}
		}
		
		

	}
	
	
}

package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.levels.pattern;
import birdo.utilities.*;

public abstract class game {
	
	player player;
	// rectangle representing birdo
	ArrayList<enemy> enemies;
	// array list holding enemies
	ArrayList<powerup> powerups;
	int score = 0;
	// player score
	String state;
	// state of game
	
	ArrayList<String> layout = new ArrayList<String>();
	int patternNum = 0;
	
	public game() {
		player = new player (130, 130, Color.BLUE); 
		enemies = new ArrayList <enemy>(); 
		powerups = new ArrayList<powerup>();
		for (int x = 0; x < 30; x++) {
			enemy e = null;

			//e = new strafeRunPooper (x * 50 + 800 + (int) (Math.random() * 100), (int) (Math.random() * 300 ) + 100, Color.DARK_GRAY);

			//enemies.add(e);
		}
	}
	
	public void move() {
		player.move();
		for (egg e: player.eggs)
			e.move();
		for (enemy e: enemies) {
			e.p = player;
			e.move();
		}
		for (powerup p: powerups) {
			p.move();
		}
		collision();
		genPattern();
	}
	
	public void draw(Graphics g) {
		player.draw(g);
		for (egg e: player.eggs) // calls egg draw method so scattershots can be drawn?
			e.draw(g);
		for (enemy e: enemies)
			e.draw(g);
		for (powerup p: powerups)
			p.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 550, 40);
		g.drawString("Layout: " + layout.get(patternNum - 1), 550, 55);
		g.drawString("Health: " + player.health, 25, 40);
		g.drawString("Eggs x" + player.ammo, 25, 55);
		
		if (player.checkisDead()) {
			g.drawString("Game Over!", 300, 150);
			g.drawString("Continue: F1", 300, 175);
			g.drawString("Quit: F2", 300, 200);
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
				player.c = Color.BLUE;
			}
		}  
		
		// SCREEN BORDERS
		
		if(player.x < 0) player.x = 0;
		if(player.x > 800 - player.w) player.x = 800 - player.w;
		if(player.y < 0) player.y = 0;
		if (player.isDead == false)
			if (500 - player.h < player.y) player.y = 500 - player.h;
		
		// PLAYER HITBOXES
		
		for(int i = 0; i != enemies.size(); i++) {
			enemy e = enemies.get(i);
			
			// hit the enemy? take damage
			
			if(player.getHitBox().intersects(e.getHitBox())) {
				if(!player.invulnerable) {
					player.health--;
					player.invulnerable = true;
					player.invulnerablecooldown = 75;
				}
			}
			
			// hit enemy feather? take damage
			
			for(int j = 0; j != e.feathers.size(); j++) {
				feather f = e.feathers.get(j);
				if(player.getHitBox().intersects(f.getHitBox())) {
					if(!player.invulnerable) {
						player.health--;
						player.invulnerable = true;
						player.invulnerablecooldown = 75;
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
						player.invulnerablecooldown = 75;
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
			
			if(e.x > 800) continue;
			
			// enemy hit my feather? take damage
			
			for(int j = 0; j != player.feathers.size(); j++) {
				feather f = player.feathers.get(j);
				if(e.getHitBox().intersects(f.getHitBox())) {
					e.health--;
					player.feathers.remove(j);
					j--;
				}
				if(f.x > 800) {
					if(j >= 0)
						player.feathers.remove(j);
					j--;
				}
			}
			
			// enemy hit my egg? take damage
			
			for(int k = 0; k != player.eggs.size(); k++) { // when the player egg hits an enemy, it should explode into multiple scattershots
				
				egg p = player.eggs.get(k);
				if(e.getHitBox().intersects(p.getHitBox())) {
					e.health = e.health-5;
					player.eggs.remove(k);
					k--;
					
				}
				if(p.y > 500) {
					if(k >= 0)
						player.eggs.remove(k);
					k--;
				}
			}
			
			e.checkisDead();
			
			if (e.y > 500) {
				enemies.remove(i);
				score += e.score;
				i--;
			}
			
			if (e.x < -60) {
				enemies.remove(i);
				i--;
			}
		}
		
		// powerup hitboxes and conditions
		for (int t = 0; t != powerups.size(); t++) {
			powerup p = powerups.get(t);
			if (p.type == "fillEggs") {
				if (p.getHitBox().intersects(p.getHitBox())) {
					for (int y = 0; y != player.maxammo; y++) {
						player.ammo++;
					}
				}
					
			}
			
			if (p.x < -60) {
				powerups.remove(t);
				t--;
			}
			
			
		}
		
		

	}
	
	public void genPattern() {
		
		if(enemies.size() == 0 && patternNum != layout.size()) {
			ArrayList<enemy> toAdd = new pattern(layout.get(patternNum)).enemies;
			for(enemy e:toAdd) enemies.add(e);
			patternNum++;
		}
	}
	
	
}

package birdo.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;

import birdo.enemies.*;
import birdo.game.assets;
import birdo.levels.pattern;
import birdo.utilities.*;

public abstract class game {

	// game variables
	public player player;
	public ArrayList<enemy> enemies;
	public ArrayList<powerup> powerups;
	public ArrayList<obstacle> obstacles;
	
	// game data
	public int score = 0;
	public String state;
	public int test = 0;

	// level layout data
	ArrayList<String> layout = new ArrayList<String>();
	int patternNum = 0;

	public game() {
		player = new player(130, 130, Color.BLUE);
		player.player = true;
		enemies = new ArrayList<enemy>();
		powerups = new ArrayList<powerup>();
		obstacles = new ArrayList<obstacle>();
	}

	public void move() {

		// moving the player
		player.move();
		player.centerX = (player.x + player.w / 2);
		player.centerY = (player.y + player.h / 2);

		// moving the enemies
		for (enemy e : enemies) {
			// for homing
			for (feather f : e.feathers) 
				f.p = player;
			
			e.p = player;
			e.move();
			e.centerX = (e.x + e.w / 2);
			e.centerY = (e.y + e.h / 2);
		}
		
		// for homing
		for (feather f : player.feathers)
			f.enemies = enemies;
		
		for (powerup p : powerups)
			p.move();
		for (obstacle o : obstacles)
			o.move();
		
		collision();
		status();
		genPattern();
	}

	public void draw(Graphics g, assets a) {
		
		// GAME OBJECTS
		
		player.draw(g, a);
		
		for (enemy e : enemies)
			e.draw(g, a);
		for (powerup p : powerups)
			p.draw(g, a);
		for (obstacle o : obstacles)
			o.draw(g, a);
		
		// USER INTERFACE
		
		g.setColor(a.colors.get("black"));
		g.setFont(a.fonts[0]);
		
		g.drawString("Health: " + player.health, 25, 40);
		g.drawString("Score: " + score, 500, 40);
		
		if (patternNum != 0)
			g.drawString("Layout: " + layout.get(patternNum - 1), 500, 70);
		
		if (player.powerupType != "none")
			g.drawString("Powerup: " + player.powerupType, 25, 55);
		
		if (player.isDead()) {
			g.drawString("Game Over!", 300, 150);
			g.drawString("Continue: F1", 300, 175);
			g.drawString("Quit: F2", 300, 200);
			state = "GameOver";
		}
	}

	public void collision() {

		// SCREEN BORDERS

		if (player.x < 0)
			player.x = 0;
		if (player.x > 800 - player.w)
			player.x = 800 - player.w;
		if (player.y < 0)
			player.y = 0;
		if (player.isDead == false)
			if (500 - player.h < player.y)
				player.y = 500 - player.h;

		// PLAYER HITBOXES

		for (int i = 0; i != enemies.size(); i++) {
			enemy e = enemies.get(i);

			// hit the enemy? take damage

			if (player.getHitBox().intersects(e.getHitBox())) {
				if (!player.status.containsKey("invulnerable")
					&& e.defense > player.defense) {
					player.health -= e.defense - player.defense;
					player.addStatus("invulnerable", 75);
				}
			}

			// hit enemy feather? take damage

			for (int j = 0; j != e.feathers.size(); j++) {
				
				if (j == -1)
					continue;
				
				feather f = e.feathers.get(j);
				
				if (player.getHitBox().intersects(f.getHitBox())) {
					if (!player.status.containsKey("invulnerable")) {
						
						// player takes a minimum of 1 damage
						int damage = f.attack - player.defense;
						if (damage > 0)
							player.health -= damage;
						else
							player.health--;
						player.addStatus("invulnerable", 75);
						
						if (!f.effect.equals("none")) 
							player.addStatus(f.effect, f.effectDuration);
					}
				}
				
				if (f.x < -100 || f.x > 900 || f.y < -100 || f.y > 600) {
					e.feathers.remove(j);
					j--;
				}
			}

			// hit enemy egg? take damage

			for (int k = 0; k != e.eggs.size(); k++) {
				
				if (k == -1)
					continue;
				
				egg p = e.eggs.get(k);
				
				if (player.getHitBox().intersects(p.getHitBox())) {
					if (!player.status.containsKey("invulnerable")) {
						player.health--;
						player.addStatus("invulnerable", 75);
					}
					e.eggs.remove(k);
					k--;
				}
				
				if (p.x > 800) {
					e.eggs.remove(k);
					k--;
				}
			}
		}
		
		// FEATHER AND EGG HITBOXES
		
		for(int i = 0; i != player.feathers.size(); i++) {
			if (i == -1)
				continue;
			
			feather f = player.feathers.get(i);
			if (f.x < -50 || f.x > 850 || f.y < -50 || f.y > 550) {
				player.feathers.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i != player.eggs.size(); i++) {
			if (i == -1)
				continue;
			
			egg e = player.eggs.get(i);
			if (e.x < -50 || e.x > 850 || e.y < -50 || e.y > 550) {
				player.feathers.remove(i);
				i--;
			}
		}

		// ENEMY HITBOXES

		for (int i = 0; i != enemies.size(); i++) {

			if (i == -1)
				continue;

			enemy e = enemies.get(i);

			if (e.x > 800)
				continue;

			// enemy hit my feather? take damage

			for (int j = 0; j != player.feathers.size(); j++) {
				
				if (j == -1)
					continue;
				
				feather f = player.feathers.get(j);
				
				if (e.getHitBox().intersects(f.getHitBox()) && !f.hasHit.contains(e.hash)) {
					
					// enemy takes a minimum of 1 damage
					int damage = f.attack - e.defense;
					if (damage > 0)
						e.health -= damage;
					else
						e.health--;
					
					f.hasHit.add(e.hash);
					
					if (!f.effect.equals("none")) 
						e.addStatus(f.effect, f.effectDuration);
					
					if (e.health <= 0)
						score += e.score;
					
					// if it still has pierce, don't remove
					f.pierce--;
					if (f.pierce <= 0) {
						player.feathers.remove(j);
						j--;
					}
				}
			}

			// enemy hit my egg? take damage

			for (int k = 0; k != player.eggs.size(); k++) { 
				
				if (k == -1)
					continue;
				
				egg p = player.eggs.get(k);
				
				if (e.getHitBox().intersects(p.getHitBox())) {
					e.health = e.health - 5;
					if (e.health <= 0)
						score += e.score;
					player.eggs.remove(k);
					k--;
				}
			}

			e.isDead();

			boolean removeEnemy = false;

			if (e.y < -50 || e.y > 550 || e.x < -50)
				removeEnemy = true;

			// an enemy who falls below the lower bound
			// or an enemy who moves beyond the left bound
			// should be removed

			if (!removeEnemy)
				continue;
			// no reason to remove? no reason to go through removal

			boolean onScreen = false;

			for (feather f : e.feathers) {
				if (f.x > 0 && f.y > 0 && f.y < 500) {
					onScreen = true;
					break;
				}
			}

			for (egg eg : e.eggs) {
				if (eg.x > 0 && eg.y > 0 && eg.y < 500) {
					onScreen = true;
					break;
				}
			}

			// if feathers or eggs are still on screen, don't remove enemy yet

			if (onScreen)
				continue;

			enemies.remove(i);
			i--;

		}

		// POWERUP HITBOXES
		
		for (int t = 0; t != powerups.size(); t++) {
			
			if (t == -1)
				continue;
			
			powerup p = powerups.get(t);
			
			if (player.getHitBox().intersects(p.getHitBox())) {
				player.powerupType = p.type;
				powerups.remove(t);
				t--;
			}

			if (p.x < -60) {
				powerups.remove(t);
				t--;
			}

		}
		
		for (int m = 0; m != obstacles.size(); m++) {
			
			if (m == -1)
				continue;
			
			obstacle o = obstacles.get(m);
			
			if (player.getHitBox().intersects(o.getHitBox())) {
				player.health--;
				player.addStatus("invulnerable", 75);
				player.addStatus("stunned", 75);
			}
			
			if (o.x < -60) {
				obstacles.remove(o);
				m--;
			}
		}

	}
	
	public void status() {
		
		player.decreaseAllStatus();
		
		for (enemy e : enemies) 
			e.decreaseAllStatus();
	}

	public void genPattern() {
		
		// if all enemies are dead and all obstacles are gone
		// and there exists more patterns,
		// bring on another pattern

		boolean allClear = true;
		for (enemy e : enemies) {
			if (e.x > 0 && !e.isDead) {
				allClear = false;
				break;
			}
		}
		for (obstacle o : obstacles) {
			if (o.x > 0) {
				allClear = false;
				break;
			}
		}

		if (allClear && patternNum != layout.size()) {
			pattern temp = new pattern(layout.get(patternNum));
			enemies.addAll(temp.enemies);
			obstacles.addAll(temp.obstacles);
			createRandomPowerup(800, 250);
			patternNum++;
		}
	}
	
	public void createEnemy(String type) {
		createEnemy(type, 815, 225);
	}

	public void createEnemy(String type, int x, int y) {

		enemy temp = null;
		if (type.equals("enemy"))
			temp = new enemy(x, y);
		if (type.equals("target"))
			temp = new targetEnemy(x, y);
		if (type.equals("charge"))
			temp = new chargeEnemy(x, y);
		if (type.equals("big"))
			temp = new bigEnemy(x, y);
		if (type.equals("hover"))
			temp = new hoverEnemy(x, y);
		if (type.equals("strafe"))
			temp = new strafeEnemy(x, y);
		if (type.equals("blossom"))
			temp = new blossomEnemy(x, y);
		if (type.equals("rapid"))
			temp = new rapidEnemy(x, y);
		if (type.equals("pulse"))
			temp = new pulseEnemy(x, y);
		if (type.equals("steady"))
			temp = new steadyEnemy(x, y);
		if (type.equals("spin"))
			temp = new spinEnemy(x, y);
		if (type.equals("laser"))
			temp = new laserEnemy(x, y);
		if (type.equals("explode"))
			temp = new explodeEnemy(x, y);
		if (type.equals("homing"))
			temp = new homingEnemy(x, y);
		if (type.equals("miniBoss1"))
			temp = new miniBoss1(x, y);
		if (type.equals("miniBoss2"))
			temp = new miniBoss2(x, y);
		if (type.equals("miniBoss3"))
			temp = new miniBoss3(x, y);
		enemies.add(temp);
	}
	
	public void createPowerup(String type) {
		powerups.add(new powerup(815, 225, type));
	}

	public void createRandomPowerup(int x, int y) {
		String[] choices = {"rapidFire", "spinBurst", "homingRush", "stunShot", "heal", "invulnerability"};
		int choice = (int) (Math.random() * 6);
		powerup toAdd = new powerup(x, y, choices[choice]);
		powerups.add(toAdd);
	}

}

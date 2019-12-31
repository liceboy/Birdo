package birdo.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.enemies.*;
import birdo.levels.pattern;
import birdo.utilities.*;

public abstract class game {

	// game variables
	public player player;
	public ArrayList<enemy> enemies;
	public ArrayList<powerup> powerups;

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
		
		// moving the powerups
		for (powerup p : powerups)
			p.move();
		
		collision();
		genPattern();
	}

	public void draw(Graphics g) {
		
		// GAME OBJECTS
		
		player.draw(g);
		
		for (enemy e : enemies)
			e.draw(g);
		for (powerup p : powerups)
			p.draw(g);
		
		// USER INTERFACE
		
		g.setColor(Color.BLACK);
		
		g.drawString("Health: " + player.health, 25, 40);
		g.drawString("Score: " + score, 500, 40);
		
		if (patternNum != 0)
			g.drawString("Layout: " + layout.get(patternNum - 1), 500, 70);
		
		if (player.powerupType != "none")
			g.drawString("Powerup: " + player.powerupType + " x" + player.ammo, 25, 55);
		
		if (player.checkisDead()) {
			g.drawString("Game Over!", 300, 150);
			g.drawString("Continue: F1", 300, 175);
			g.drawString("Quit: F2", 300, 200);
			state = "GameOver";
		}
	}

	public void collision() {

		// INVULNERABILITY

		if (player.invulnerable) {
			player.c = Color.RED;
			player.invulnerableCooldown--;
			if (player.invulnerableCooldown == 0) {
				player.invulnerable = false;
				player.c = Color.BLUE;
			}
		}

		// RAPIDFIRE POWERUP

		if (player.shootInterval == 5) {
			player.shotCooldown--;
			if (player.shotCooldown == 0) {
				player.shootInterval = 15;
			}
		}

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
				if (!player.invulnerable) {
					player.health--;
					player.invulnerable = true;
					player.invulnerableCooldown = 75;
				}
			}

			// hit enemy feather? take damage

			for (int j = 0; j != e.feathers.size(); j++) {
				
				if (j == -1)
					continue;
				
				feather f = e.feathers.get(j);
				
				if (player.getHitBox().intersects(f.getHitBox())) {
					if (!player.invulnerable) {
						player.health--;
						player.invulnerable = true;
						player.invulnerableCooldown = 75;
					}
					e.feathers.remove(j);
					j--;
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
					if (!player.invulnerable) {
						player.health--;
						player.invulnerable = true;
						player.invulnerableCooldown = 75;
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
				
				if (e.getHitBox().intersects(f.getHitBox())) {
					e.health--;
					if (e.health <= 0)
						score += e.score;
					player.feathers.remove(j);
					j--;
				}
				
				if (f.x < -100 || f.x > 900 || f.y < -100 || f.y > 600) {
					if (j >= 0)
						player.feathers.remove(j);
					j--;
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
				
				if (p.y > 500) {
					if (k >= 0)
						player.eggs.remove(k);
					k--;
				}
			}

			e.checkisDead();

			boolean removeEnemy = false;

			if (e.y < -100 || e.y > 600 || e.x < -100)
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
				if (player.powerupType == "heal") { // heal powerup
					player.health += 3;
					player.powerupType = "none";
					if (player.health > player.maxHealth)
						player.health = player.maxHealth;
				}
				player.ammo = p.ammo;
				powerups.remove(t);
				t--;
			}

			if (p.x < -60) {
				powerups.remove(t);
				t--;
			}

		}

	}

	public void genPattern() {
		
		// if all enemies are dead
		// and there exists more patterns,
		// bring on another pattern

		boolean allDead = true;
		for (enemy e : enemies) {
			if (e.x > 0 && !e.isDead) {
				allDead = false;
				break;
			}
		}

		if (allDead && patternNum != layout.size()) {
			ArrayList<enemy> toAdd = new pattern(layout.get(patternNum)).enemies;
			for (enemy e : toAdd)
				enemies.add(e);
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
		enemies.add(temp);
	}

	public void createRandomPowerup(int x, int y) {
		String[] choices = { "eggs", "buckShot", "invulnerability", "heal", "rapidFire"};
		int choice = (int) (Math.random() * 5);
		powerup toAdd = new powerup(x, y, choices[choice]);
		powerups.add(toAdd);
	}

}

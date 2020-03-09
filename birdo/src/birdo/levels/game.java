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
	public int kills = 0;
	public int hits = 0;
	public int numEnemies = 0;
	public String state = "";
	
	// ruleset
	public String rules = "";
	public int powerupSpawn = 1;
	
	// level layout data
	ArrayList<String> layout = new ArrayList<String>();
	int patternNum = 0;

	public game(String rules) {
		player = new player(130, 130, Color.BLUE);
		enemies = new ArrayList<enemy>();
		powerups = new ArrayList<powerup>();
		obstacles = new ArrayList<obstacle>();
		setRules(rules);
	}

	public void move() {
		
		// moving the player
		player.move();
		player.centerX = (player.x + player.w / 2);
		player.centerY = (player.y + player.h / 2);
		player.enemies = enemies;
		
		for (obstacle o : player.obstacles) {
			o.p = player;
			o.enemies = enemies;
		}

		// moving the enemies
		for (enemy e : enemies) {
			// for homing
			for (feather f : e.feathers) {
				f.p = player;
				f.enemies = enemies;
			}
			for (obstacle o : e.obstacles) {
				o.p = player;
				o.enemies = enemies;
			}
			
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
		
		if (state.equals("GameOver")) return;		
		
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
		
		g.setColor(Color.BLACK);
		g.setFont(a.fonts[0]);
		
		g.drawString("Health: " + player.health, 25, 40);
		g.drawString("Score: " + score, 25, 60);
		g.drawString("Kills: " + kills, 25, 80);
		g.drawString("Hits: " + hits, 25, 100);
		
		g.drawString("Rules: " + rules, 25, 460);
		
		if (patternNum != 0)
			g.drawString("Layout: " + layout.get(patternNum - 1), 500, 40);
		
		if (player.powerupType != "none")
			g.drawString("Powerup: " + player.powerupType, 500, 60);
		
		if (player.isDead()) {
			g.drawString("Game Over!", 300, 160);
			g.drawString("Play Again: F1", 300, 180);
			g.drawString("Quit: F2", 300, 200);
			state = "GameOver";
		}
		
		if (layout.size() != 0 && patternNum == layout.size() && enemies.size() == 0) {
			g.drawString("Victory", 300, 160);
			g.drawString("Play Again: F1", 300, 180);
			g.drawString("Quit: F2", 300, 200);
			
			String multipliers = "";
			double seeds = score / 100;
			int goldenSeeds = 1;
			if (hits == 0) {
				multipliers += "x2 No Damage ";
				seeds *= 2;
				goldenSeeds *= 2;
			}
			if (kills == numEnemies) {
				multipliers += "x2 All Defeated ";
				seeds *= 2;
				goldenSeeds *= 2;
			}
			
			g.drawString("Seeds: " + (int) seeds, 300, 240);
			g.drawString("Golden Seeds: " + goldenSeeds, 300, 260);
			g.drawString(multipliers, 300, 280);
			
			state = "GameOver";
		}
	}
	
	public void setRules(String rules) {
		if (rules.equals("hard"))
			rules = "hard, sparsePowerups";
		if (rules.equals("master"))
			rules = "deathless, noPowerups";
			
		this.rules = rules;
		if (rules.contains("deathless")) {
			player.health = 1;
			player.maxHealth = 1;
		}
		if (rules.contains("sparsePowerups")) {
			powerupSpawn = 3;
		}
		if (rules.contains("noPowerups")) {
			powerupSpawn = -1;
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
			if (i == -1)
				continue;
			
			enemy e = enemies.get(i);

			// hit enemy feather? take damage
			for (int j = 0; j != e.feathers.size(); j++) {
				if (j == -1)
					continue;
				
				feather f = e.feathers.get(j);
				if (player.takeFeatherDamage(f))
					hits += 1;
				
				if (f.x < -100 || f.x > 900 || f.y < -100 || f.y > 600 || f.pierce <= 0) {
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
					player.takeDamage(10);
					hits += 1;
					
					e.eggs.remove(k);
					k--;
				}
	
				if (p.x > 800) {
					e.eggs.remove(k);
					k--;
				}
			}
			
			// hit enemy obstacle? take damage
			
			for (int l = 0; l != e.obstacles.size(); l++) {
				if (l == -1)
					continue;
				
				obstacle o = e.obstacles.get(l);
				if (player.takeObstacleDamage(o))
					hits += 1;
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
				e.takeFeatherDamage(f);
				
				if (f.x < -50 || f.x > 850 || f.y < -50 || f.y > 550 || f.pierce <= 0) {
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
					e.takeDamage(10);
					player.eggs.remove(k);
					k--;
				}
			}
			
			// enemy hit my obstascle? take daamge
			
			for (int l = 0; l != player.obstacles.size(); l++) {
				if (l == -1)
					continue;
				
				obstacle o = player.obstacles.get(l);
				e.takeObstacleDamage(o);
				
				if (o.isLaser && o.lifeCount == 0) {
					player.obstacles.remove(l);
					l--;
				}
			}	
			
			if (e.health <= 0 && !e.isDead) {
				score += e.score;
				kills += 1;
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
		
		// OBSTACLE HITBOXES
		
		for (int m = 0; m != obstacles.size(); m++) {
			
			if (m == -1)
				continue;
			
			obstacle o = obstacles.get(m);
			
			if (player.getHitBox().intersects(o.getHitBox()) && !o.hasHit.contains(player.hash)) {
				
				player.takeDamage(o.attack);
				
				player.addStatus("stunned", 75);
				
				hits += 1;
				
				o.hasHit.add(player.hash);
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
			if (e.x > 100 && !e.isDead) {
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
			
			for (enemy e : temp.enemies) {
				createEnemy(e.type, (int) e.x, (int) e.y);
			}
			
			obstacles.addAll(temp.obstacles);
			if (patternNum % powerupSpawn == 0)
				createRandomPowerup(800, 250);
			patternNum++;
		}
	}
	

	public void createEnemy(String type, int x, int y) {
		
		enemy e = new enemy(x, y);
		
		int atkNum = 10;
		// how much base attacks it takes to kill birdo
		int defNum = 2;
		// how much base attacks from birdo it can take
		
		switch (type) {
			case "target":
				atkNum = 8;
				e = new targetEnemy(x, y);
				break;
			case "charge":
				atkNum = 8;
				e = new chargeEnemy(x, y);
				break;
			case "big":
				atkNum = 5;
				defNum = 5;
				e = new bigEnemy(x, y);
				break;
			case "hover":
				e = new hoverEnemy(x, y);
				break;
			case "strafe":
				e = new strafeEnemy(x, y);
				break;
			case "blossom":
				atkNum = 6;
				e = new blossomEnemy(x, y);
				break;
			case "rapid":
				atkNum = 20;
				e = new rapidEnemy(x, y);
				break;
			case "pulse":
				atkNum = 8;
				e = new pulseEnemy(x, y);
				break;
			case "steady":
				atkNum = 8;
				e = new steadyEnemy(x, y);
				break;
			case "spin":
				e = new spinEnemy(x, y);
				break;
			case "laser":
				e = new laserEnemy(x, y);
				break;
			case "explode":
				e = new explodeEnemy(x, y);
				break;
			case "homing":
				atkNum = 5;
				e = new homingEnemy(x, y);
				break;
			case "burn":
				atkNum = 8;
				e = new burnEnemy(x, y);
				break;
			case "freeze":
				atkNum = 8;
				e = new freezeEnemy(x, y);
				break;
			case "plasma":
				atkNum = 8;
				e = new plasmaEnemy(x, y);
				break;
			case "stun":
				atkNum = 8;
				e = new stunEnemy(x, y);
				break;
			case "miniBoss1":
				atkNum = 4;
				defNum = 50;
				e = new miniBoss1(x, y);
				break;
			case "miniBoss2":
				atkNum = 4;
				defNum = 50;
				e = new miniBoss2(x, y);
				break;
			case "miniBoss3":
				atkNum = 4;
				defNum = 50;
				e = new miniBoss3(x, y);
				break;
		}
		
		int hpBase = player.maxHealth;
		int atkBase = player.attack;
		int defBase = player.defense;
		
		if (rules.contains("easy")) {
			atkNum *= 2;
			defNum /= 2;
		}
		if (rules.contains("hard")) {
			atkNum /= 2;
			defNum *= 2;
		}
		
		e.createStats(defNum * atkBase, hpBase / atkNum + defBase, 0);
		
		numEnemies += 1;
		enemies.add(e);
	}
	
	public void createEnemy(String type) {
		createEnemy(type, 815, 360);
	}
	
	public void createPowerup(String type) {
		powerup toAdd = new powerup(815, 225);
		toAdd.type = type;
		powerups.add(toAdd);
	}

	public void createRandomPowerup(int x, int y) {
		powerup toAdd = new powerup(x, y);
		powerups.add(toAdd);
	}

}

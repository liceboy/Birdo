package birdo.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.enemies.*;
import birdo.levels.pattern;
import birdo.utilities.*;

public abstract class game {

	public player player;
	// rectangle representing birdo
	ArrayList<enemy> enemies;
	// array list holding enemies
	ArrayList<powerup> powerups;
	int score = 0;
	// player score
	public String state;
	// state of game

	ArrayList<String> layout = new ArrayList<String>();
	int patternNum = 0;

	public game() {
		player = new player(130, 130, Color.BLUE);
		enemies = new ArrayList<enemy>();
		powerups = new ArrayList<powerup>();
	}

	public void move() {
		player.move();
		for (enemy e : enemies) {
			e.p = player;
			e.move();
		}
		for (powerup p : powerups) {
			p.move();
		}
		collision();
		genPattern();
	}

	public void draw(Graphics g) {
		player.draw(g);
		for (egg e : player.eggs)
			e.draw(g);
		for (enemy e : enemies)
			e.draw(g);
		for (powerup p : powerups)
			p.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 550, 40);
		if (patternNum != 0)
			g.drawString("Layout: " + layout.get(patternNum - 1), 550, 55);
		g.drawString("Health: " + player.health, 25, 40);
		if (player.poweruptype != "none")
			g.drawString("Powerup: " + player.poweruptype + " x" + player.ammo, 25, 55);
		if (player.checkisDead()) {
			g.drawString("Game Over!", 300, 150);
			g.drawString("Continue: F1", 300, 175);
			g.drawString("Quit: F2", 300, 200);
			state = "GameOver";
		}
	}

	public void collision() {
		// good luck trying to understand this

		// INVULNERABILITY

		if (player.invulnerable) {
			player.c = Color.RED;
			player.invulnerablecooldown--;
			if (player.invulnerablecooldown == 0) {
				player.invulnerable = false;
				player.c = Color.BLUE;
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
					player.invulnerablecooldown = 75;
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
						player.invulnerablecooldown = 75;
					}
					e.feathers.remove(j);
					j--;
				}
				if (f.x > 800) {
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
						player.invulnerablecooldown = 75;
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
				if (f.x > 800) {
					if (j >= 0)
						player.feathers.remove(j);
					j--;
				}
			}

			// enemy hit my egg? take damage

			for (int k = 0; k != player.eggs.size(); k++) { // when the player egg hits an enemy, it should explode into
															// multiple scattershots
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

			if (e.y < -300 || e.y > 800 || e.x < -300)
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

			// if feathers or eggs are still onscreen, don't remove enemy yet

			if (onScreen)
				continue;

			enemies.remove(i);
			i--;

		}

		// powerup hitboxes and conditions
		for (int t = 0; t != powerups.size(); t++) {
			if (t == -1)
				continue;
			powerup p = powerups.get(t);
			if (player.getHitBox().intersects(p.getHitBox())) {
				player.poweruptype = p.type;
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

		boolean onScreen = false;
		for (enemy e : enemies) {
			if (e.x > 0 && e.y > 0 && e.y < 500) {
				onScreen = true;
				break;
			}
		}

		// if all enemies are offscreen (not necessarily feathers)
		// and there exists more patterns,
		// bring on another pattern

		if (!onScreen && patternNum != layout.size()) {
			ArrayList<enemy> toAdd = new pattern(layout.get(patternNum)).enemies;
			for (enemy e : toAdd)
				enemies.add(e);
			createRandomPowerup(800, 250);
			patternNum++;
		}
	}

	public void createEnemy(String type, int x, int y) {

		enemy temp = null;
		if (type.equals("enemy"))
			temp = new enemy(x, y);
		if (type.equals("homing"))
			temp = new homingEnemy(x, y);
		if (type.equals("charge"))
			temp = new chargeEnemy(x, y);
		if (type.equals("big"))
			temp = new bigEnemy(x, y);
		if (type.equals("hover"))
			temp = new hoverEnemy(x, y);
		if (type.equals("strafe"))
			temp = new strafeEnemy(x, y);
		if (type.equals("suicide"))
			temp = new suicideEnemy(x, y);
		if (type.equals("rapid"))
			temp = new rapidEnemy(x, y);
		if (type.equals("pulse"))
			temp = new pulseEnemy(x, y);
		if (type.equals("steady"))
			temp = new steadyEnemy(x, y);
		if (type.equals("miniBoss1"))
			temp = new miniBoss1(x, y);
		if (type.equals("miniBoss2"))
			temp = new miniBoss2(x, y);
		enemies.add(temp);
	}

	public void createRandomPowerup(int x, int y) {
		String[] choices = {"eggs", "bloomShot", "buckShot", "tripleShot"};
		int choice = (int) (Math.random() * 4);
		powerup toAdd = new powerup(x, y, choices[choice]);
		powerups.add(toAdd);
	}

}

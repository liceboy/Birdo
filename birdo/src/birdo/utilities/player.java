package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class player extends object {
	
	// stats
	public int health;
	public int maxHealth;
	public int damage;
	public int moveSpeed;

	// location
	public boolean isDead;
	public double centerX;
	public double centerY;
	
	// powerups
	public String powerupType;
	public int ammo;
	public int maxAmmo;
	
	// invulnerability
	public boolean invulnerable;
	public int invulnerableCooldown;

	// attacks
	public ArrayList<feather> feathers;
	public ArrayList<egg> eggs;
	
	// shooting
	public int shotMultiplier;
	public int shootCount;
	public int poopCount;
	public int shootInterval;
	public int shotCooldown;
	
	boolean init = true;
	boolean track = true;
	double prevTheta;
	
	// ben's movement system
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public boolean player = false;

	public player(int x, int y, Color c) {
		super(x, y, 20, 20, c);
		health = 100;
		damage = 1;
		moveSpeed = 4;
		shootCount = 0;
		poopCount = 0;
		ammo = 0;
		maxAmmo = 3;
		isDead = false;
		feathers = new ArrayList<feather>();
		eggs = new ArrayList<egg>();
		invulnerable = false;
		invulnerableCooldown = 0;
		shotCooldown = 0;
		shootInterval = 15;
		powerupType = "none"; // default powerup is always none
		maxHealth = 10;
		
		shotMultiplier = 0; 
		centerX = (this.x + this.w / 2);
		centerY = (this.y + this.h / 2);
	}

	public void draw(Graphics g) {
		for (feather a : feathers)
			a.draw(g);
		// draws feathers
		for (egg e : eggs)
			e.draw(g);
		// draw egg
		super.draw(g);
		// draws self

	}

	public void move() {
		setMovement();
		super.move();
		shoot();
		
		for (feather f : feathers)
			f.move();
		for (egg e : eggs)
			e.move();
	}

	public void shoot() { // shoots automatically with cooldown
		if (shootCount == 0) {
			shootCount = shootInterval;
			customShot("normal");
		}
		shootCount--;
	}
	
	public void poop() { // poops
		if (!isDead)
			eggs.add(new egg(this.x, this.y));
	}

	public void customShot(String type) {
		
		// creates feather(s) according to given behavior
		// feathers come from the dead center
		
		int fw = 8;
		int fh = 8;
		double alignedX = centerX - fw / 2;
		double alignedY = centerY - fh / 2;
		
		if (isDead)
			return;
		if (type == "normal") {
			feathers.add(new feather(alignedX, alignedY, true));
		}
		if (type == "triple") {
			feather f = new feather(alignedX, alignedY, true);
			feather f1 = new feather(alignedX, alignedY, true);
			feather f2 = new feather(alignedX, alignedY, true);

			f.dx = 5;

			f1.dx = (5 * Math.cos(15 * Math.PI / 180));
			f1.dy = (5 * Math.sin(15 * Math.PI / 180));

			f2.dx = (5 * Math.cos(345 * Math.PI / 180));
			f2.dy = (5 * Math.sin(345 * Math.PI / 180));

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}
		if (type == "bloom") {
			feather f = new feather(alignedX, alignedY, true);
			feather f1 = new feather(alignedX, alignedY, true);
			feather f2 = new feather(alignedX, alignedY, true);
			feather f3 = new feather(alignedX, alignedY, true);
			feather f4 = new feather(alignedX, alignedY, true);
			feather f5 = new feather(alignedX, alignedY, true);
			feather f6 = new feather(alignedX, alignedY, true);
			feather f7 = new feather(alignedX, alignedY, true);

			f.dx = -5;
			f.dy = 0;
			f3.dx = 5;
			f3.dy = 0;

			f1.dx = -1 * (-5 * Math.cos(Math.PI / 4));
			f1.dy = -1 * (-5 * Math.sin(Math.PI / 4));

			f2.dx = -1 * (-5 * Math.cos(7 * Math.PI / 4));
			f2.dy = -1 * (-5 * Math.sin(7 * Math.PI / 4));

			f4.dx = (-5 * Math.cos(Math.PI / 4));
			f4.dy = (-5 * Math.sin(Math.PI / 4));

			f5.dx = (-5 * Math.cos(7 * Math.PI / 4));
			f5.dy = (-5 * Math.sin(7 * Math.PI / 4));

			f6.dx = 0;
			f6.dy = -5;
			f7.dx = 0;
			f7.dy = 5;

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
			feathers.add(f4);
			feathers.add(f5);
			feathers.add(f6);
			feathers.add(f7);
		}
		if (type == "spin") {
			feather f = new feather(alignedX, alignedY, true);
			feather f1 = new feather(alignedX, alignedY, true);
			feather f2 = new feather(alignedX, alignedY, true);
			feather f3 = new feather(alignedX, alignedY, true);

			f.dx = -1 * (5 * Math.cos(shotMultiplier * Math.PI / 12));
			f.dy = -1 * (5 * Math.sin(shotMultiplier * Math.PI / 12));

			f1.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI / 2));
			f1.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI / 2));

			f2.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI));
			f2.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI));

			f3.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));
			f3.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));

			shotMultiplier++;

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
		}
		if (type == "buckshot") {
			feather f = new feather(alignedX, alignedY, true);
			feather f1 = new feather(alignedX, alignedY, true);
			feather f2 = new feather(alignedX, alignedY, true);
			feather f3 = new feather(alignedX, alignedY, true);
			feather f4 = new feather(alignedX, alignedY, true);
			feather f5 = new feather(alignedX, alignedY, true);

			f1.dx = (5 * Math.cos(Math.PI / 8));
			f1.dy = (5 * Math.sin(Math.PI / 8));
			f2.dx = (5 * Math.cos(2 * Math.PI / 8));
			f2.dy = (5 * Math.sin(2 * Math.PI / 8));
			f3.dx = (5 * Math.cos(3 * Math.PI / 8));
			f3.dy = (5 * Math.sin(3 * Math.PI / 8));

			f4.dx = (5 * Math.cos(-1 * Math.PI / 8));
			f4.dy = (5 * Math.sin(-1 * Math.PI / 8));
			f5.dx = (5 * Math.cos(-2 * Math.PI / 8));
			f5.dy = (5 * Math.sin(-2 * Math.PI / 8));
			f.dx = (5 * Math.cos(-3 * Math.PI / 8));
			f.dy = (5 * Math.sin(-3 * Math.PI / 8));

			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
			feathers.add(f4);
			feathers.add(f5);
			feathers.add(f);
		}
		
		if (type == "explode") {
			for (int x = 0; x < 15; x++) {
				feather f = new feather(alignedX, alignedY, true);
				f.dx = -1 * (5 * Math.cos((shotMultiplier + 45) * Math.PI / 6));
				f.dy = -1 * (5 * Math.sin((shotMultiplier + 45) * Math.PI / 6));
				feathers.add(f);
				shotMultiplier++;
			}
		}
		
		if (type == "two") {
			feather f = new feather(alignedX, alignedY + this.h/2 -9, true);
			feather f1 = new feather(alignedX, alignedY + this.h/2 , true);
			feathers.add(f);
			feathers.add(f1);
		}

		if (type == "three") {
			feather f = new feather(alignedX, alignedY, true);
			feather f1 = new feather(alignedX, alignedY + this.h / 2 - 3, true);
			feather f2 = new feather(alignedX, alignedY + this.h - 6, true);
			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}

		if (type == "homing") {
			//for homing feather 
			feather f = new feather(alignedX, alignedY, true);
			f.isHoming = true;
			f.speed = 3;
			f.duration = 150;
			feathers.add(f);
		}
		
		if (type == "homingSlow") {
			feather f = new feather(alignedX, alignedY, true);
			f.isHoming = true;			
			f.speed = 2;
			f.duration = 200;
			feathers.add(f);
		}
		
		if (type == "homingFast") {
			feather f = new feather(alignedX, alignedY, true);
			f.isHoming = true;
			f.speed = 5;
			f.duration = 100;
			feathers.add(f);
		}
	}
	
	public void setMovement() {
		// ben's movement system
		if (player && !isDead) {
			if (up || down) {
				if (up && down) {
					dy = 0;
				} else if (up) {
					dy = -4;
				} else {
					dy = 4;
				}
			} else {
				dy = 0;
			}
			if (left || right) {
				if (left && right) {
					dx = 0;
				} else if (left) {
					dx = -4;
				} else {
					dx = 4;
				}
			} else {
				dx = 0;
			}
		}
	}

	public void usePowerup() { // uses the powerup based on string type, add powerups as you feel
		
		if (true)
			return; // keep until fixed
		
		if (powerupType == "none")
			return;
		if (powerupType == "eggs")
			poop();
		if (powerupType == "buckShot")
			customShot("buckshot");
		if (powerupType == "invulnerability") {
			invulnerable = true;
			invulnerableCooldown = 250;
		}
		if (powerupType == "rapidFire") {
			shotCooldown = 250;
			shootInterval = 5;
		}
		
		ammo--;
		if (ammo <= 0)
			powerupType = "none";

	}

	public boolean checkisDead() {
		if (health <= 0) {
			isDead = true;
			dx = 0;
			dy = 7;
			return true;
		}
		return false;
	}
	


}

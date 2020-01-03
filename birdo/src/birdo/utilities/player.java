package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class player extends object {
	
	// stats
	public int health;
	public int maxHealth;
	public int attack;
	public int defense;
	public boolean isDead;
	
	// location
	public double centerX;
	public double centerY;
	
	// status
	public Map<String, Integer> status;
	public String powerupType;
	
	// attacks
	public Map<String, int[]> loadout;
	public ArrayList<feather> feathers;
	public ArrayList<egg> eggs;
	
	// shooting
	public int shotCount;
	public int shotMultiplier;
	public int poopCount;
	
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
		
		health = 50;
		maxHealth = 50;
		attack = 10;
		defense = 8;
		isDead = false;
		
		centerX = (this.x + this.w / 2);
		centerY = (this.y + this.h / 2);
		
		status = new HashMap<String, Integer>();
		powerupType = "none";
		
		loadout = new HashMap<String, int[]>();
		feathers = new ArrayList<feather>();
		eggs = new ArrayList<egg>();
		
		shotCount = 1;
		poopCount = 0;
		shotMultiplier = 0;
		
		setLoadout();
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
		centerX = (this.x + this.w / 2);
		centerY = (this.y + this.h / 2);
		
		setMovement();
		super.move();
		shoot();
		
		for (feather f : feathers)
			f.move();
		for (egg e : eggs)
			e.move();
	}

	public void shoot() { // shots automatically with cooldown
		if (status.containsKey("stunned"))
			return;
		
		for(Map.Entry<String, int[]> style : loadout.entrySet()) {
			if (shotCount % style.getValue()[0] == 0)
				customShot(style.getKey(), style.getValue());
		}
		
		if (shotCount == 0) 
			shotCount = 600;	
		else
			shotCount--;
	}
	
	public void poop() { // poops
		if (!isDead)
			eggs.add(new egg(this.x, this.y));
	}

	public void customShot(String type, int[] stats) {
		if (status.containsKey("stunned"))
			return;
		
		// creates feather(s) according to given behavior
		// feathers come from the dead center
		
		int fw = 8;
		int fh = 8;
		double alignedX = centerX - fw / 2;
		double alignedY = centerY - fh / 2;
		
		int damage = stats[1];
		int pierce = stats[2];
		
		if (isDead)
			return;
		if (type == "normal") {
			feathers.add(new feather(alignedX, alignedY, damage, pierce, true));
		}
		if (type == "stun") {
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			f.effect = "stunned";
			f.effectDuration = 100;
			feathers.add(f);
		}
		if (type == "triple") {
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			feather f1 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f2 = new feather(alignedX, alignedY, damage, pierce, true);

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
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			feather f1 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f2 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f3 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f4 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f5 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f6 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f7 = new feather(alignedX, alignedY, damage, pierce, true);

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
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			feather f1 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f2 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f3 = new feather(alignedX, alignedY, damage, pierce, true);

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
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			feather f1 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f2 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f3 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f4 = new feather(alignedX, alignedY, damage, pierce, true);
			feather f5 = new feather(alignedX, alignedY, damage, pierce, true);

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
				feather f = new feather(alignedX, alignedY, damage, pierce, true);
				f.dx = -1 * (5 * Math.cos((shotMultiplier + 45) * Math.PI / 6));
				f.dy = -1 * (5 * Math.sin((shotMultiplier + 45) * Math.PI / 6));
				feathers.add(f);
				shotMultiplier++;
			}
		}
		
		if (type == "two") {
			feather f = new feather(alignedX, alignedY + this.h/2 -9, damage, pierce, true);
			feather f1 = new feather(alignedX, alignedY + this.h/2 , damage, pierce, true);
			feathers.add(f);
			feathers.add(f1);
		}

		if (type == "three") {
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			feather f1 = new feather(alignedX, alignedY + this.h / 2 - 3, damage, pierce, true);
			feather f2 = new feather(alignedX, alignedY + this.h - 6, damage, pierce, true);
			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}

		if (type == "homing") {
			//for homing feather 
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			f.isHoming = true;
			f.homingSpeed = 3;
			f.homingDuration = 150;
			feathers.add(f);
		}
		
		if (type == "homingSlow") {
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			f.isHoming = true;			
			f.homingSpeed = 2;
			f.homingDuration = 200;
			feathers.add(f);
		}
		
		if (type == "homingFast") {
			feather f = new feather(alignedX, alignedY, damage, pierce, true);
			f.isHoming = true;
			f.homingSpeed = 5;
			f.homingDuration = 100;
			feathers.add(f);
		}
	}
	
	public void setLoadout() {
		int[] first = {20, attack, 1};
		loadout.put("normal", first);
		int[] second = {300, (int) (attack * 1.2), 3};
		loadout.put("homingFast", second);
	}
	
	public void setMovement() {
		// ben's movement system
		
		int moveSpeed = 4;
		if (status.containsKey("slowed"))
			moveSpeed = 2;
		
		if (player && !isDead) {
			if (up || down) {
				if (up && down) {
					dy = 0;
				} else if (up) {
					dy = -moveSpeed;
				} else {
					dy = moveSpeed;
				}
			} else {
				dy = 0;
			}
			if (left || right) {
				if (left && right) {
					dx = 0;
				} else if (left) {
					dx = -moveSpeed;
				} else {
					dx = moveSpeed;
				}
			} else {
				dx = 0;
			}
		}
		
		if (status.containsKey("sinking"))
			dy += 2;
	}
	
	public void decreaseStatus(String key) {
		if (status.containsKey(key))
			status.put(key, status.get(key) - 1);
	}

	public void usePowerup() { // uses the powerup based on string type, add powerups as you feel
		
		if (powerupType == "none")
			return;
		
		powerupType = "none";
	}

	public boolean isDead() {
		if (health <= 0) {
			isDead = true;
			dx = 0;
			dy = 7;
			return true;
		}
		return false;
	}
	


}

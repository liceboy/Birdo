package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import birdo.game.assets;

public class player extends object {

	// stats
	public int health;
	public int maxHealth;
	public int attack;
	public int interval;
	public int defense;
	public boolean isDead;

	// location
	public double centerX;
	public double centerY;
	public double alignedX;
	public double alignedY;

	// status
	public Map<String, Integer> status;
	public Set<String> allEffects;
	public String powerupType;

	// attacks
	public ArrayList<enemy> enemies;
	public ArrayList<loadout> loadouts;
	public ArrayList<feather> feathers;
	public ArrayList<obstacle> obstacles;
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
	public boolean isPlayer = true;

	public player(int x, int y, Color c) {
		super(x, y, 20, 20, c);
		
		createStats(300, 10, 0);

		interval = 15;
		isDead = false;

		centerX = (this.x + this.w / 2);
		centerY = (this.y + this.h / 2);

		status = new HashMap<String, Integer>();
		allEffects = new HashSet<String>();
		powerupType = "none";

		loadouts = new ArrayList<loadout>();
		feathers = new ArrayList<feather>();
		obstacles = new ArrayList<obstacle>();
		eggs = new ArrayList<egg>();

		shotCount = 1;
		poopCount = 0;
		shotMultiplier = 0;
	}

	public void draw(Graphics2D g, assets a) {
		
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y - 10, w, 5);
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y - 10, (int) ((double) health / maxHealth * w), 5);
		
		String statusBar = "";
		if (status.containsKey("invulnerable"))
			statusBar += "INV ";
		if (status.containsKey("stunned"))
			statusBar += "STUN ";
		if (status.containsKey("burned"))
			statusBar += "BURN ";
		if (status.containsKey("plasmized"))
			statusBar += "PLAZ ";
		if (status.containsKey("slowed"))
			statusBar += "SLOW ";
		if (status.containsKey("sinking"))
			statusBar += "SINK ";
		if (status.containsKey("rising"))
			statusBar += "RISE ";
		if (status.containsKey("pushed"))
			statusBar += "PUSH ";
		if (status.containsKey("pulled"))
			statusBar += "PULL ";
		
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont(8f));
		g.drawString(statusBar, (int) x, (int) y - 20);

		for (feather f : feathers)
			f.draw(g, a);
		// draws feathers
		for (obstacle o : obstacles)
			o.draw(g, a);
		for (egg e : eggs)
			e.draw(g, a);
		// draw egg
		super.draw(g, a);
		// draws self
	}

	public void move() {
		if (status.containsKey("burned")) {
			if (status.get("burned") % 20 == 0) {
				int damage = maxHealth / 50;
				if (damage <= 0) damage = 1;
				health -= damage;
			}
		}
		
		int fw = 8;
		int fh = 8;

		centerX = (this.x + this.w / 2);
		centerY = (this.y + this.h / 2);
		alignedX = centerX - fw / 2;
		alignedY = centerY - fh / 2;
		
		for (feather f : feathers)
			f.move();
		for (obstacle o : obstacles)
			o.move();
		for (egg e : eggs)
			e.move();

		if (status.containsKey("slowed") && !isDead) {
			if (status.get("slowed") % 2 == 0) {
				return;
			}
		}
		
		if (health > maxHealth) health = maxHealth;
		if (health < 0) health = 0;
		
		setMovement();
		shoot();
		super.move();
	}

	public void shoot() { // shots automatically with cooldown
		if (status.containsKey("stunned"))
			return;
		
		if (status.containsKey("rapidFire")) 
			if (status.get("rapidFire") == 1) 
				for(loadout l : loadouts) 
					l.interval *= 2;
		
		if (status.containsKey("spinBurst")) 
			if (status.get("spinBurst") % 10 == 0) 
				customShot("spinBurst", attack, 1);
		
		if (status.containsKey("homingRush")) {
			if (status.get("homingRush") % 10 == 0) {
				feather f = createFeather("homing", attack * 2, 1);
				f.fromPlayer = true;
				f.isStrongShot = true;
				f.homingSpeed = 7;
				f.homingDuration = 300;
				feathers.add(f);
			}
		}
		
		for(loadout l : loadouts) {
			if (shotCount % l.interval == 0) {
				customShot(l.type, (int) l.modifier * attack, l.pierce);
			}
		}
		
		if (shotCount == 1) 
			shotCount = 600;	
		else
			shotCount--;
	}

	public void poop() { // poops
		if (!isDead)
			eggs.add(new egg(this.x, this.y));
	}

	public void customShot(String type, int attack, int pierce) {
		if (status.containsKey("stunned"))
			return;

		// creates feather(s) according to given behavior
		// feathers come from the dead center

		if (isDead)
			return;
		
		ArrayList<feather> toAdd = new ArrayList<feather>();
		
		if (type.indexOf(",") != -1 || type.indexOf(" ") != -1) {
			toAdd.add(createFeather(type, attack, pierce));
		}
		// special feathers with multiple types

		if (type == "normal") {
			toAdd.add(createFeather(attack, pierce));
		}
		if (type == "stun") {
			toAdd.add(createFeather(type, attack, pierce));
		}
		if (type == "burn") {
			toAdd.add(createFeather(type, attack, pierce));
		}
		if (type == "freeze") {
			toAdd.add(createFeather(type, attack, pierce));
		}
		if (type == "plasma") {
			toAdd.add(createFeather(type, attack, pierce));
		}
		if (type == "triple") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			feather f2 = createFeather(attack, pierce);

			f.dx = 5;

			f1.dx = (5 * Math.cos(1 * Math.PI / 8));
			f1.dy = (5 * Math.sin(1 * Math.PI / 8));

			f2.dx = (5 * Math.cos(1 * Math.PI / 8));
			f2.dy = (5 * Math.sin(1 * Math.PI / 8));

			toAdd.add(f);
			toAdd.add(f1);
			toAdd.add(f2);
		}
		
		if (type == "bloom") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			feather f2 = createFeather(attack, pierce);
			feather f3 = createFeather(attack, pierce);
			feather f4 = createFeather(attack, pierce);
			feather f5 = createFeather(attack, pierce);
			feather f6 = createFeather(attack, pierce);
			feather f7 = createFeather(attack, pierce);

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

			toAdd.add(f);
			toAdd.add(f1);
			toAdd.add(f2);
			toAdd.add(f3);
			toAdd.add(f4);
			toAdd.add(f5);
			toAdd.add(f6);
			toAdd.add(f7);
		}
		if (type == "spin") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			feather f2 = createFeather(attack, pierce);
			feather f3 = createFeather(attack, pierce);

			f.dx = -1 * (5 * Math.cos(shotMultiplier * Math.PI / 12));
			f.dy = -1 * (5 * Math.sin(shotMultiplier * Math.PI / 12));

			f1.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI / 2));
			f1.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI / 2));

			f2.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI));
			f2.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI));

			f3.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));
			f3.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));

			shotMultiplier++;

			toAdd.add(f);
			toAdd.add(f1);
			toAdd.add(f2);
			toAdd.add(f3);
		}
		if (type == "spinBurst") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			feather f2 = createFeather(attack, pierce);
			feather f3 = createFeather(attack, pierce);
			feather f4 = createFeather(attack, pierce);
			feather f5 = createFeather(attack, pierce);
			feather f6 = createFeather(attack, pierce);
			feather f7 = createFeather(attack, pierce);

			f.dx = -1 * (5 * Math.cos(shotMultiplier * Math.PI / 12));
			f.dy = -1 * (5 * Math.sin(shotMultiplier * Math.PI / 12));

			f1.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI / 4));
			f1.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI / 4));

			f2.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI / 2));
			f2.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI / 2));

			f3.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 4));
			f3.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 4));

			f4.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI));
			f4.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI));

			f5.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + 5 * Math.PI / 4));
			f5.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + 5 * Math.PI / 4));

			f6.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));
			f6.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));

			f7.dx = -1 * (5 * Math.cos((shotMultiplier * Math.PI / 12) + 7 * Math.PI / 4));
			f7.dy = -1 * (5 * Math.sin((shotMultiplier * Math.PI / 12) + 7 * Math.PI / 4));

			shotMultiplier++;

			f.c = Color.ORANGE;
			f1.c = Color.ORANGE;
			f2.c = Color.ORANGE;
			f3.c = Color.ORANGE;
			f4.c = Color.ORANGE;
			f5.c = Color.ORANGE;
			f6.c = Color.ORANGE;
			f7.c = Color.ORANGE;

			toAdd.add(f);
			toAdd.add(f1);
			toAdd.add(f2);
			toAdd.add(f3);
			toAdd.add(f4);
			toAdd.add(f5);
			toAdd.add(f6);
			toAdd.add(f7);
		}
		if (type == "buckshot") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			feather f2 = createFeather(attack, pierce);
			feather f3 = createFeather(attack, pierce);
			feather f4 = createFeather(attack, pierce);
			feather f5 = createFeather(attack, pierce);

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

			toAdd.add(f);
			toAdd.add(f1);
			toAdd.add(f2);
			toAdd.add(f3);
			toAdd.add(f4);
			toAdd.add(f5);
		}

		if (type == "explode") {
			for (int x = 0; x < 15; x++) {
				feather f = createFeather(attack, pierce);
				f.dx = -1 * (5 * Math.cos((shotMultiplier + 45) * Math.PI / 6));
				f.dy = -1 * (5 * Math.sin((shotMultiplier + 45) * Math.PI / 6));
				toAdd.add(f);
				shotMultiplier++;
			}
		}

		if (type == "two") {
			feather f = createFeather(attack, pierce);
			f.y += this.h/2 - 9;
			feather f1 = createFeather(attack, pierce);
			f1.y += this.h/2;
			toAdd.add(f);
			toAdd.add(f1);
		}

		if (type == "three") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			f1.y += this.h / 2 - 3;
			feather f2 = createFeather(attack, pierce);
			f2.y += this.h - 6;
			toAdd.add(f);
			toAdd.add(f1);
			toAdd.add(f2);
		}
		
		if (type == "homing") {
			feather f = createFeather(type, attack, pierce);
			toAdd.add(f);
		}
		
		if (type == "homingSlow") {
			feather f = createFeather(type, attack, pierce);
			toAdd.add(f);
		}
		
		if (type == "homingFast") {
			feather f = createFeather(type, attack, pierce);
			toAdd.add(f);
		}
		
		
		feathers.addAll(toAdd);
	}

	public void customShot(String type, int[] stats) {
		customShot(type, stats[1], stats[2]);
	}
	
	public feather createFeather(String desc, int attack, int pierce) {
		desc = desc.toLowerCase();
		feather f = createFeather(attack, pierce);
		
		if (desc.indexOf("burn") != -1) {
			f.effects.add("burned");
			f.effectDurations.add(200);
			f.isBurnShot = true;
		}
		
		if (desc.indexOf("freeze") != -1) {
			f.effects.add("slowed");
			f.effectDurations.add(200);
			f.isFreezeShot = true;
		}
		
		if (desc.indexOf("plasma") != -1) {
			f.effects.add("plasmized");
			f.effectDurations.add(200);
			f.isPlasmaShot = true;
		}
		
		if (desc.indexOf("stun") != -1) {
			f.effects.add("stunned");
			f.effectDurations.add(200);
			f.isStunShot = true;
		}
		
		if (desc.indexOf("homing") != -1) {
			f.isHomingShot = true;
			f.homingSpeed = 3;
			f.homingDuration = 150;
			
			if (desc.indexOf("slow") != -1) {
				f.homingSpeed = 2;
				f.homingDuration = 200;
			}
			if (desc.indexOf("fast") != -1) {
				f.homingSpeed = 5;
				f.homingDuration = 100;
			}
			
			f.enemies = enemies;
		}
		
		if (desc.indexOf("strong") != -1) {
			f.isStrongShot = true;
		}
		
		return f;
	}

	public feather createFeather(int attack, int pierce) {
		feather f = new feather(alignedX, alignedY, attack, pierce);
		f.fromPlayer = true;
		f.owner = this;
		return f;
	}

	public void createStats(int health, int attack, int defense) {
		this.health = health;
		this.maxHealth = health;
		this.attack = attack;
		this.defense = defense;
	}
	
	public void createLoadout(String type, int interval, double modifier, int pierce) {
		loadouts.add(new loadout(type, interval, modifier, pierce));
	}
		
	public obstacle createObstacle(double x, double y, int h, int w, int attack) {
		obstacle o = new obstacle(x, y, h, w);
		o.attack = attack;
		o.owner = this;
		o.fromPlayer = isPlayer;
		return o;
	}
	
	public void customObstacle(String type) {
		if (status.containsKey("stunned"))
			return;

		if (isDead)
			return;
		
		ArrayList<obstacle> toAdd = new ArrayList<obstacle>();
		
		if (type == "laser") {
			obstacle o = createObstacle(alignedX, alignedY, 800, 10, 1);
			o.isLaser = true;
			toAdd.add(o);
		}
		if (type == "sweepingLaser") {
			obstacle o = createObstacle(alignedX, alignedY, 800, 10, 1);
			o.isLaser = true;
			AffineTransform at = AffineTransform.getRotateInstance(Math.PI / 4, alignedX, alignedY);
			Shape rotatedRect = at.createTransformedShape(this.getHitBox());
			Rectangle newHitbox = rotatedRect.getBounds();
			System.out.println(newHitbox);
			o.setNewHitbox(newHitbox);
			System.out.println(o.getHitBox());
			o.isLaser = true;
			toAdd.add(o);
		}
		if (type == "strongLaser") {
			obstacle o = createObstacle(alignedX, alignedY, 800, 10, 0);
			o.damageRate = 1;
			o.isLaser = true;
			toAdd.add(o);
		}
		if (type == "dualLaser") {
			obstacle o = createObstacle(alignedX, alignedY, 800, 5, 0);
			o.isLaser = true;
			o.lockX = 20;
			o.lockY = -20;
			toAdd.add(o);
			
			o = createObstacle(alignedX, alignedY, 800, 5, 0);
			o.isLaser = true;
			o.lockX = 20;
			o.lockY = 20;
			toAdd.add(o);	
		}
		
		if (type == "skyLaser") {
			for (x = 20; x < 800; x+=75) {
				obstacle o = createObstacle(x, 0, 20, 525, 1);
				toAdd.add(o);
			}
			}
		
		for (obstacle o : obstacles) {
			System.out.println("here");
		}
		
		obstacles.addAll(toAdd);
	}

	public void setMovement() {
		// ben's movement system

		int moveSpeed = 4;

		if (isPlayer && !isDead) {
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
			if (dy != 0)
				dy += 2;
		
		if (status.containsKey("rising"))
			if (dy != 0)
				dy -= 2;
		
		if (status.containsKey("pushed"))
			if (dx != 0)
				dx += 2;
		
		if (status.containsKey("pulled"))
			if (dx != 0)
				dx -= 2;
	}

	public void addStatus(String effect, int duration) {
		allEffects.add(effect);
		if (!status.containsKey(effect))
			status.put(effect, duration);
	}

	public void decreaseStatus(String effect) {
		if (status.containsKey(effect))
			status.put(effect, status.get(effect) - 1);
		else
			return;
		if (status.get(effect) <= 0)
			status.remove(effect);
	}

	public void decreaseAllStatus() {
		for (String s : allEffects)
			decreaseStatus(s);
	}

	public void usePowerup() { // uses the powerup based on string type, add powerups as you feel

		if (powerupType == "none")
			return;
		if (powerupType.equals("rapidFire")) {
			if (!status.containsKey("rapidFire")) {
				for (loadout l : loadouts) {
					l.interval /= 2;
				}
			}
			addStatus("rapidFire", 400);
		}
		if (powerupType.equals("spinBurst")) {
			addStatus("spinBurst", 100);
		}
		if (powerupType.equals("homingRush")) {
			addStatus("homingRush", 60);
		}
		if (powerupType.equals("stunShot")) {
			feather f = createFeather("homing,stun,strong", attack * 2, 5);
			f.homingSpeed = 5;
			f.homingDuration = 1000;
			feathers.add(f);
		}
		if (powerupType.equals("heal")) {
			health += maxHealth / 4;
		}
		if (powerupType.equals("invulnerability")) {
			addStatus("invulnerable", 200);
		}
		if (powerupType.equals("laser")) {
			customObstacle("strongLaser");
			obstacles.get(obstacles.size() - 1).lifeCount = 300;
		}

		powerupType = "none";
	}
	
	public boolean takeFeatherDamage(feather f) {
		if (f.hasHit.contains(hash)) return false;
		if (!getHitBox().intersects(f.getHitBox())) return false;
		
		takeDamage(f.attack);
		f.hasHit.add(hash);
		for (int a = 0; a != f.effects.size(); a++)
			addStatus(f.effects.get(a), f.effectDurations.get(a));
		f.pierce--;
		return true;
	}
	
	public boolean takeObstacleDamage(obstacle o) {
		if (o.hasHit.contains(hash)) return false;
		if (!getHitBox().intersects(o.getHitBox())) return false;
		
		takeDamage(o.attack);
		o.hasHit.add(hash);
		for (int a = 0; a != o.effects.size(); a++)
			addStatus(o.effects.get(a), o.effectDurations.get(a));
		o.pierce--;
		return true;
	}
	
	public void takeDamage(int atk) {
		
		int damage = atk - defense;
		
		if (damage <= 0)
			damage = 1;
		if (status.containsKey("plasmized"))
			damage *= 3;

		if (!status.containsKey("invulnerable"))
			health -= damage;
		
		if (health < 0)
			health = 0;
		
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

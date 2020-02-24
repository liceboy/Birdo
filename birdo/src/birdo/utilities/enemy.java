package birdo.utilities;

import java.awt.Color;
import java.util.ArrayList;

public class enemy extends player {

	// current player
	public player p;
	
	// score granted upon death
	public int score;
	
	public boolean ignore;

	public enemy(int x, int y) {
		super(x, y, new Color(6, 6, 8));
		createStats(10, 10, 0);
		
		score = 100;
		this.dx = -2;
		this.dy = 0;
	}

	public void move() {
		if (x < 800)
			poop();
		if (isDead) {
			customMove("die");
		}
		super.move();
	}
	
	public void poop() {
		if (isDead)
			return;
		if (poopCount == 0) {
			eggs.add(new egg(this.x, this.y));
			poopCount = 100;
		}
		poopCount--;
	}
	
	public void customMove(String type) {
		
		// sets dx and dy according to given behavior

		if (p.isDead)
			return;

		if (isDead) {
			dx = 0;
			dy = 7;
			return;
		}

		if (type == "default") {
			if (x > 800)
				dx = -3;
			if (x < 800)
				dx = -1;
			if (x < p.x || ignore) {
				dx = -3;
				ignore = true;
			}
		}

		if (type == "hover") {
			if (x > p.x)
				dx = -3;
			if (x < p.x)
				dx = 3;
			if (x == p.x)
				dx = 0;
		}

		if (type == "upDown") {
			dx = 0;
			if (dy == 0)
				dy = -3;
			if (y > 400)
				dy = -3;
			if (y < 100)
				dy = 3;
		}

		if (type == "moveCenter") {
			dx = 0;
			dy = 0;

			if (x - 400 > 3)
				dx = -3;
			if (x - 400 < -3)
				dx = 3;
			if (y - 250 > 3)
				dy = -3;
			if (y - 250 < -3)
				dy = 3;
		}

		if (type == "charge") {
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			// during first move, set prevTheta to theta so it starts out as tracking
			if (init) {
				prevTheta = theta;
				init = false;
			}	
			// if change in angle is greater than 0.3 radians, then stop tracking
			if (Math.abs(prevTheta - theta) > 0.2) {
				track = false;
			}
			// update dx and dy to follow player if track is true
			if (track) {
				dx = (3 * deltaX / hypotenuse);
				dy = (3 * deltaY / hypotenuse);
			}
			// update prevThetas
			prevTheta = theta;
			super.move();
		}
		
		if (type == "stop") {
			if (x < 700)
				dx = 0;
		}

		if (type == "die") {
			dx = 0;
			dy = 7;
			if (y < 500)
				dy = 2;
		}
	}

	public void customShot(String type, int attack, int pierce) {
		
		if (status.containsKey("stunned"))
			return;
		
		// creates feather(s) according to given behavior
		// feathers come from the dead center
		
		if (isDead || x > 800 || x < 0 || y > 500 || y < 0)
			return;
		
		ArrayList<feather> toAdd = new ArrayList<feather>();
		
		if (type.indexOf(",") != -1) {
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
		if (type == "target") {
			toAdd.add(createFeather(type, attack, pierce));
		}
		if (type == "triple") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			feather f2 = createFeather(attack, pierce);

			f.dx = -5;

			f1.dx = -1 * (5 * Math.cos(1 * Math.PI / 8));
			f1.dy = -1 * (5 * Math.sin(1 * Math.PI / 8));

			f2.dx = -1 * (5 * Math.cos(1 * Math.PI / 8));
			f2.dy = 1 * (5 * Math.sin(1 * Math.PI / 8));

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

			toAdd.add(f);
			toAdd.add(f1);
			toAdd.add(f2);
			toAdd.add(f3);
			toAdd.add(f4);
			toAdd.add(f5);
			toAdd.add(f6);
			toAdd.add(f7);
		}
		
		if (type == "spinBurstSlow") {
			feather f = createFeather(attack, pierce);
			feather f1 = createFeather(attack, pierce);
			feather f2 = createFeather(attack, pierce);
			feather f3 = createFeather(attack, pierce);
			feather f4 = createFeather(attack, pierce);
			feather f5 = createFeather(attack, pierce);
			feather f6 = createFeather(attack, pierce);
			feather f7 = createFeather(attack, pierce);

			f.dx = -1 * (4 * Math.cos(shotMultiplier * Math.PI / 12));
			f.dy = -1 * (4 * Math.sin(shotMultiplier * Math.PI / 12));

			f1.dx = -1 * (4 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI / 4));
			f1.dy = -1 * (4 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI / 4));

			f2.dx = -1 * (4 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI / 2));
			f2.dy = -1 * (4 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI / 2));

			f3.dx = -1 * (4 * Math.cos((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 4));
			f3.dy = -1 * (4 * Math.sin((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 4));

			f4.dx = -1 * (4 * Math.cos((shotMultiplier * Math.PI / 12) + Math.PI));
			f4.dy = -1 * (4 * Math.sin((shotMultiplier * Math.PI / 12) + Math.PI));

			f5.dx = -1 * (4 * Math.cos((shotMultiplier * Math.PI / 12) + 5 * Math.PI / 4));
			f5.dy = -1 * (4 * Math.sin((shotMultiplier * Math.PI / 12) + 5 * Math.PI / 4));

			f6.dx = -1 * (4 * Math.cos((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));
			f6.dy = -1 * (4 * Math.sin((shotMultiplier * Math.PI / 12) + 3 * Math.PI / 2));

			f7.dx = -1 * (4 * Math.cos((shotMultiplier * Math.PI / 12) + 7 * Math.PI / 4));
			f7.dy = -1 * (4 * Math.sin((shotMultiplier * Math.PI / 12) + 7 * Math.PI / 4));

			shotMultiplier++;

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

			f1.dx = -1 * (5 * Math.cos(Math.PI / 8));
			f1.dy = -1 * (5 * Math.sin(Math.PI / 8));
			f2.dx = -1 * (5 * Math.cos(Math.PI / 4));
			f2.dy = -1 * (5 * Math.sin(Math.PI / 4));
			f3.dx = -1 * (5 * Math.cos(3 * Math.PI / 8));
			f3.dy = -1 * (5 * Math.sin(3 * Math.PI / 8));
			f4.dx = (5 * Math.cos(5 * Math.PI / 8));
			f4.dy = (5 * Math.sin(5 * Math.PI / 8));
			f5.dx = (5 * Math.cos(6 * Math.PI / 8));
			f5.dy = (5 * Math.sin(6 * Math.PI / 8));
			f.dx = (5 * Math.cos(7 * Math.PI / 8));
			f.dy = (5 * Math.sin(7 * Math.PI / 8));

			toAdd.add(f1);
			toAdd.add(f2);
			toAdd.add(f3);
			toAdd.add(f4);
			toAdd.add(f5);
			toAdd.add(f);
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

		if (type == "circleShot") {
			for (int x = 0; x < 15; x++) {
				feather f = createFeather(attack, pierce);
				f.x = (this.centerX) + (20 * Math.cos(shotMultiplier * Math.PI / 6));
				f.y = (this.centerY) + (20 * Math.sin(shotMultiplier * Math.PI / 6));
				
				double deltaX = (p.centerX + (20 * Math.cos(shotMultiplier * Math.PI / 6)))
						- ((centerX) + (20 * Math.cos(shotMultiplier * Math.PI / 6)));
				double deltaY = (p.centerY + (20 * Math.cos(shotMultiplier * Math.PI / 6)))
						- ((centerY) + (20 * Math.cos(shotMultiplier * Math.PI / 6)));
				double theta = Math.atan(deltaY / deltaX);
				double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				
				f.dx = (5 * deltaX / hypotenuse);
				f.dy = (5 * deltaY / hypotenuse);
				shotMultiplier++;
				toAdd.add(f);
			}
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
		
		if (desc.indexOf("target") != -1) {
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			
			f.dx = (5 * deltaX / hypotenuse);
			f.dy = (5 * deltaY / hypotenuse);
			f.isTargetShot = true;
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
			
			f.p = p;
		}
		
		if (desc.indexOf("strong") != -1) {
			f.isStrongShot = true;
		}
		
		return f;
	}

	public feather createFeather(int attack, int pierce) {
		feather f = new feather(alignedX, alignedY, attack, pierce);
		f.forward = false;
		return f;
	}
}

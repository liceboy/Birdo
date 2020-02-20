package birdo.utilities;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class enemy extends player {

	// current player
	public player p;
	
	// score granted upon death
	public int score;

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
			if (x < p.x)
				dx = -3;
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

		if (type == "normal") {
			feathers.add(new feather(alignedX, alignedY, attack, pierce, false));
		}
		if (type == "stun") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			f.effect = "stunned";
			f.effectDuration = 100;
			f.isStunShot = true;
			feathers.add(f);
		}
		if (type == "burn") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			f.effect = "burned";
			f.effectDuration = 100;
			f.isBurnShot = true;
			feathers.add(f);
		}
		if (type == "freeze") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			f.effect = "slowed";
			f.effectDuration = 100;
			f.isFreezeShot = true;
			feathers.add(f);
		}
		if (type == "target") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			
			f.dx = (5 * deltaX / hypotenuse);
			f.dy = (5 * deltaY / hypotenuse);
			f.c = Color.GRAY;
			feathers.add(f);
		}
		if (type == "tripleTarget") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			feather f1 = new feather(alignedX, alignedY+10, attack, pierce, false);
			feather f2 = new feather(alignedX, alignedY-10, attack, pierce, false);
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			
			f.dx = (5 * deltaX / hypotenuse);
			f.dy = (5 * deltaY / hypotenuse);
			f.c = Color.GRAY;
			f1.dx = (5 * deltaX / hypotenuse);
			f1.dy = (5 * deltaY / hypotenuse + 10);
			f1.c = Color.GRAY;
			f2.dx = (5 * deltaX / hypotenuse);
			f2.dy = (5 * deltaY / hypotenuse - 10);
			f2.c = Color.GRAY;
			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}
		
		if (type == "triple") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			feather f1 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f2 = new feather(alignedX, alignedY, attack, pierce, false);

			f.dx = -5;

			f1.dx = -1 * (5 * Math.cos(1 * Math.PI / 8));
			f1.dy = -1 * (5 * Math.sin(1 * Math.PI / 8));

			f2.dx = -1 * (5 * Math.cos(1 * Math.PI / 8));
			f2.dy = 1 * (5 * Math.sin(1 * Math.PI / 8));

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}
		
		if (type == "bloom") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			feather f1 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f2 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f3 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f4 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f5 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f6 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f7 = new feather(alignedX, alignedY, attack, pierce, false);

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
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			feather f1 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f2 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f3 = new feather(alignedX, alignedY, attack, pierce, false);

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
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			feather f1 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f2 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f3 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f4 = new feather(alignedX, alignedY, attack, pierce, false);
			feather f5 = new feather(alignedX, alignedY, attack, pierce, false);

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

			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
			feathers.add(f4);
			feathers.add(f5);
			feathers.add(f);
		}

		if (type == "explode") {
			for (int x = 0; x < 15; x++) {
				feather f = new feather(alignedX, alignedY, attack, pierce, false);
				f.dx = -1 * (5 * Math.cos((shotMultiplier + 45) * Math.PI / 6));
				f.dy = -1 * (5 * Math.sin((shotMultiplier + 45) * Math.PI / 6));
				feathers.add(f);
				shotMultiplier++;
			}
		}
		
		if (type == "two") {
			feather f = new feather(alignedX, alignedY + this.h/2 -9, attack, pierce, false);
			feather f1 = new feather(alignedX, alignedY + this.h/2 , attack, pierce, false);
			feathers.add(f);
			feathers.add(f1);
		}

		if (type == "three") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			feather f1 = new feather(alignedX, alignedY + this.h / 2 - 3, attack, pierce, false);
			feather f2 = new feather(alignedX, alignedY + this.h - 6, attack, pierce, false);
			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}

		if (type == "homing") {
			//for homing feather 
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			f.isHoming = true;
			f.p = p;
			f.homingSpeed = 3;
			f.homingDuration = 150;
			feathers.add(f);
		}
		
		if (type == "homingSlow") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			f.isHoming = true;
			f.p = p;
			f.homingSpeed = 2;
			f.homingDuration = 200;
			feathers.add(f);
		}
		
		if (type == "homingFast") {
			feather f = new feather(alignedX, alignedY, attack, pierce, false);
			f.isHoming = true;
			f.p = p;
			f.homingSpeed = 5;
			f.homingDuration = 100;
			feathers.add(f);
		}

		if (type == "circleShot") {
			for (int x = 0; x < 15; x++) {
				feather f = new feather((this.centerX) + (20 * Math.cos(shotMultiplier * Math.PI / 6)),
						(this.centerY) + (20 * Math.sin(shotMultiplier * Math.PI / 6)), attack, pierce, false);
				
				double deltaX = (p.centerX + (20 * Math.cos(shotMultiplier * Math.PI / 6)))
						- ((centerX) + (20 * Math.cos(shotMultiplier * Math.PI / 6)));
				double deltaY = (p.centerY + (20 * Math.cos(shotMultiplier * Math.PI / 6)))
						- ((centerY) + (20 * Math.cos(shotMultiplier * Math.PI / 6)));
				double theta = Math.atan(deltaY / deltaX);
				double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				
				f.dx = (5 * deltaX / hypotenuse);
				f.dy = (5 * deltaY / hypotenuse);
				shotMultiplier++;
				feathers.add(f);
			}
		}
	}
	
	public void customShot(String type, int[] stats) {
		customShot(type, stats[1], stats[2]);
	}
}

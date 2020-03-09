package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.game.assets;

public class feather extends attack {
	
	// types
	public boolean isHomingShot;
	public boolean isStrongShot;
	public boolean isStunShot;
	public boolean isBurnShot;
	public boolean isFreezeShot;
	public boolean isPlasmaShot;
	public boolean isTargetShot;
	
	// homing
	public int homingSpeed;
	public double prevTheta;
	public boolean track = true;
	public boolean init = true;
	public int homingDuration;

	public feather(double x, double y, int attack, int pierce) {
		super(x, y, 10, 10, Color.BLACK);
		
		this.attack = attack;
		this.pierce = pierce;
		
		effects.add("none");
		effectDurations.add(-1);
		
		isHomingShot = false;
		isStrongShot = false;
		isStunShot = false;
		isPlasmaShot = false;
		isBurnShot = false;
		isFreezeShot = false;
		isTargetShot = false;

	}
	
	public void draw(Graphics g, assets a) {
		if (isHomingShot) c = Color.GREEN;
		if (isTargetShot) c = Color.GRAY;
		if (isBurnShot) c = Color.RED;
		if (isFreezeShot) c = Color.CYAN;
		if (isStunShot) c = Color.BLUE;
		if (isPlasmaShot) c = Color.MAGENTA;
		super.draw(g, a);
	}
	
	public void move() {
		
		if (dx == 0 && dy == 0 && fromPlayer) {
			dx = 5;
		}
		if (dx == 0 && dy == 0 && !fromPlayer) {
			dx = -5;
		}
		
		home();
		super.move();
	}
	
	public void home() {
		
		if (x < 0 || x > 800 || y < 0 || y > 550) 
			return;
		if (!isHomingShot)
			return;
		
		if (!fromPlayer) {
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			// during first move, set prevTheta to theta so it starts out as tracking
			if (init) {
				prevTheta = theta;
				init = false;
			}
			// if close enough, start tracking
			if (hypotenuse < 300)
				track = true;
			// if ready to fuck off, stop tracking
			if (homingDuration <= 0) 
				track = false;
			// if change in angle is greater than threshold, then stop tracking
			if (Math.abs(prevTheta - theta) > 5 * Math.PI / 4 && fromPlayer == false)
				track = false;
			// update dx and dy to follow player if track is true
			if (track) {
				dx = (homingSpeed * deltaX / hypotenuse);
				dy = (homingSpeed * deltaY / hypotenuse);
			}
			// update prevThetas
			prevTheta = theta;
		}
		if (fromPlayer && enemies.size() > 0) { // should always track if shot by player
			
			if (nearestEnemy() == null) return;
			
			double deltaX = nearestEnemy().x - x;
			double deltaY = nearestEnemy().y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			// during first move, set prevTheta to theta so it starts out as tracking
			if (init) {
				prevTheta = theta;
				init = false;
			}
			track = true;
			// update dx and dy to follow player if track is true
			if (track) {
				dx = (homingSpeed * deltaX / hypotenuse);
				dy = (homingSpeed * deltaY / hypotenuse);
			}
			prevTheta = theta;
			
		}
		homingDuration--;
	}
	
	public enemy nearestEnemy() { // function to find the nearest enemy for tracking bullets
		
		if (enemies.size() != 0) {
			enemy nearestEnemy = enemies.get(0);
			double nearestDeltaX = nearestEnemy.x - x;
			double nearestDeltaY = nearestEnemy.y - y;
			double nearestHypotenuse = Math.sqrt(nearestDeltaX * nearestDeltaX + nearestDeltaY * nearestDeltaY);
			
			for (enemy a : enemies) {
				
				if (a.isDead)
					continue;
				
				if (a.x < 0 || a.x > 800)
				
				if (hasHit.contains(a.hash))
					continue;
				
				double deltaX = a.x - x;
				double deltaY = a.y - y;
				double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				if (nearestHypotenuse > hypotenuse) {
					nearestHypotenuse = hypotenuse;
					nearestDeltaX = deltaX;
					nearestDeltaY = deltaY;
					nearestEnemy = a;
				}
			}
			
			if (nearestEnemy.isDead || hasHit.contains(nearestEnemy.hash) || 
				nearestEnemy.x < 0 || nearestEnemy.x > 800)
				return null;
			else
				return nearestEnemy;
		}
		return null;
	}

}

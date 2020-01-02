package birdo.utilities;

import java.awt.Color;
import java.util.ArrayList;

public class feather extends object {
	
	public boolean forward;
	public int speed;
	public player p;
	public ArrayList<enemy> enemies = new ArrayList<enemy>();
	
	// Homing Variables
	
	boolean isHoming;
	double prevTheta;
	boolean track = true;
	boolean init = true;
	int duration;

	public feather(double x, double y, boolean forward) {
		super(x, y, 8, 8, Color.BLACK);
		this.forward = forward;
		this.isHoming = false;

		if (forward) {
			dx = 5;
			dy = 0;
		}
		if (!forward) {
			dx = -5;
			dy = 0;
		}
	}
	
	public void move() {
		if (isHoming) 
			home();
		super.move();
	}
	
	
	public void home() {
		
		if (duration <= 0) 
			return;
		
		if (!forward) {
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double theta = Math.atan(deltaY / deltaX);
			// during first move, set prevTheta to theta so it starts out as tracking
			if (init) {
				prevTheta = theta;
				init = false;
			}
			// if change in angle is greater than 180 degrees, then stop tracking
			if (Math.abs(prevTheta - theta) > Math.PI) {
				track = false;
			}
			// update dx and dy to follow player if track is true
			if (track) {
				dx = (speed * deltaX / hypotenuse);
				dy = (speed * deltaY / hypotenuse);
			}
			// update prevThetas
			prevTheta = theta;
		}
		if (forward && enemies.size() == 0) {
			dx = speed;
			dy = 0;
		}
		if (forward && enemies.size() > 0) {
			double deltaX = nearestEnemy().x - x;
			double deltaY = nearestEnemy().y - y;
			
			// if the distance is signficant, it has to already be travelling forward
			if((deltaX > 500 && dx > 0) || deltaX < 500) {
				double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				dx = (speed * deltaX / hypotenuse);
				dy = (speed * deltaY / hypotenuse);
			}
		}
		
		duration--;
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
			return nearestEnemy;
		}
		return null;
	}

}

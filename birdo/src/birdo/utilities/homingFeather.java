package birdo.utilities;

import java.awt.Color;
import java.util.ArrayList;

public class homingFeather extends feather {

	double prevTheta;
	boolean track = true;
	boolean init = true;
	
	int duration;

	public homingFeather(double x, double y, boolean forward) {
		super(x, y, forward);
		c = Color.GREEN;
		
		duration = 200;
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

	public void move() {
		
		if (duration <= 0) {
			super.move();
			return;
		}
		
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
			// if change in angle is greater than 0.3 radians, then stop tracking
			if (Math.abs(prevTheta - theta) > 0.3) {
				track = false;
			}
			// update dx and dy to follow player if track is true
			if (track) {
				dx = (5 * deltaX / hypotenuse);
				dy = (5 * deltaY / hypotenuse);
			}
			// update prevThetas
			prevTheta = theta;
		}
		if (forward && enemies.size() == 0) {
			dx = 5;
			dy = 0;
		}
		if (forward && enemies.size() > 0) {
			double deltaX = nearestEnemy().x - x;
			double deltaY = nearestEnemy().y - y;
			
			// if the distance is signficant, it has to already be travelling forward
			if((deltaX > 500 && dx > 0) || deltaX < 500) {
				double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				dx = (5 * deltaX / hypotenuse);
				dy = (5 * deltaY / hypotenuse);
			}
		}
		
		duration--;
		super.move();
	}

}

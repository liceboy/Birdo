package birdo.utilities;

import java.awt.Color;

public class homingFeather extends feather {
	
	double prevTheta;
	boolean track = true;
	boolean init = true;

	public homingFeather(double x, double y, boolean forward) {
		super(x, y, forward);
		c = Color.GREEN;
	}

	public void move() {
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
			dx = (int) (5 * deltaX / hypotenuse);
			dy = (int) (5 * deltaY / hypotenuse);
		}
		// update prevThetas
		prevTheta = theta;
		super.move();
	}

}

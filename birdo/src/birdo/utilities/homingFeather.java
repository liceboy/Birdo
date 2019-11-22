package birdo.utilities;

import java.awt.Color;

public class homingFeather extends feather {
	
	double prevTheta;
	boolean track = true;

	public homingFeather(double x, double y, boolean forward) {
		super(x, y, forward);
		c = Color.GREEN;
	}

	public void move() {
		double deltaX = p.x - x;
		double deltaY = p.y - y;
		double hypotenuse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

		double theta = Math.atan(deltaY / deltaX);
		System.out.println(theta + " " + prevTheta);

		if (Math.abs(prevTheta - theta) > 0.3) {
			System.out.println("Don't Track");
			track = false;
		} else {
			System.out.println("Track");
		}
		if (track) {
			dx = (int) (4 * deltaX / hypotenuse);
			dy = (int) (4 * deltaY / hypotenuse);
		}
		prevTheta = theta;
		super.move();
	}

}

package birdo.utilities;

import java.awt.Color;

public class feather extends object {
	public boolean forward;
	public int speed;
	public player p;
	public feather(int x, int y, boolean forward) {
		super(x, y, 12, 8, Color.BLACK);
		this.forward = forward;

		if (forward) {
			dx = 5;
			dy = 0;
		}
		if (!forward) {
			dx = -5;
			dy = 0;
		}
	}

}

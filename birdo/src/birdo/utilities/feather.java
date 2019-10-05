package birdo.utilities;

import java.awt.Color;

public class feather extends object {
	public boolean forward;
	public int speed;

	public feather(int x, int y, int w, int h, Color c, boolean forward) {
		super(x, y, w, h, c);
		this.forward = forward;

		if (forward) {
			dx = 10;
			dy = 0;
		}
		if (!forward) {
			dx = -10;
			dy = 0;
		}
	}

}

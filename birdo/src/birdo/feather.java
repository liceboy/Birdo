package birdo;

import java.awt.Color;

public class feather extends object{
	boolean forward;
	public feather(int x, int y, int w, int h, Color c, boolean forward) {
		super(x, y, w, h, c);
		this.forward = forward;
	}
	@Override
	public void move() {
		if (forward) {
		dx = 10;
		dy = 0;
		}
		if (!forward) {
			dx = -10;
			dy = 0;
		}
		super.move();
	}
}

package birdo;

import java.awt.Color;

public class feather extends object{

	public feather(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	@Override
	public void move() {
		dx = 10;
		dy = 0;
		super.move();
	}
}

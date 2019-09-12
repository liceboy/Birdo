package birdo;

import java.awt.Color;

public class egg extends object{

	public egg(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	@Override
	public void move() {
		dx = 0;
		dy = 10;
		super.move();
	}

}

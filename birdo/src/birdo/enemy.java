package birdo;

import java.awt.Color;
import java.util.ArrayList;

public class enemy extends player{

	public enemy(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		health = 1;
	}
	
	@Override
	public void move() {
		this.dx = -3;
		this.dy = 0; 
		super.move();
	}
	
	@Override
	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				feathers.add(new feather(this.x , this.y , 15, 10, Color.BLACK, false)); 
				// adds a feather if alive
				shootcount = 100;
			}
			}
			shootcount--;
	}

}

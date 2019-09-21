package birdo;

import java.awt.Color;
import java.util.ArrayList;

public class enemy extends player{
	
	player p;
	
	public enemy(int x, int y, Color c) {
		super(x, y, c);
		health = 1;
		this.dx = -3;
		this.dy = 0; 
	}
	
	
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

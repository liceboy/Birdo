package birdo;

import java.awt.Color;

public class rapidShooter extends enemy{

	public rapidShooter(int x, int y, Color c) {
		super(x, y, c);
	}
	
	public void shootFeather () {
		if (shootcount == 0) {
			if (!isDead) {
				feathers.add(new feather(this.x , this.y , 15, 10, Color.BLACK, false)); 
				// adds a feather if alive
				shootcount = 10;
			}
			}
			shootcount--;
	}

	public void poop () {
		return;
	}
}
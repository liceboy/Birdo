package birdo.utilities;

import java.awt.Color;

public class enemy extends player {

	public player p;
	public int score;

	public enemy(int x, int y) {
		super(x, y, Color.BLACK);
		health = 1;
		score = 100;
		this.dx = -3;
		this.dy = 0;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				feathers.add(new feather(this.x, this.y, 15, 10, Color.BLACK, false));
				// adds a feather if alive
				shootcount = 100;
			}
		}
		shootcount--;
	}

	public void poop() {
		if (poopcount == 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y, 15, 15, Color.YELLOW));
				poopcount = 100;
			}
		}
		poopcount--;
	}

	public void move() {
		super.move();
		if (x < 800)
			poop();
	}
}

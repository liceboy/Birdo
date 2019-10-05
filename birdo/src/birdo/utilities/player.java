package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class player extends object {
	public int shootcount;
	// shoot cooldown
	public int poopcount;
	// poop cooldown
	public int health;
	// health
	public int damage;
	// strength of attacks
	public int moveSpeed;
	// move speed when holding a key
	public boolean isDead;
	// checks if the character is dead
	public boolean invulnerable;
	// checks if invulnerable
	public int invulnerablecooldown;
	// poop ammo
	public int ammo;
	public int[] stats = { health, damage, moveSpeed };
	public ArrayList<feather> feathers;
	public ArrayList<egg> eggs;

	public player(int x, int y, Color c) {
		super(x, y, 30, 30, c);
		health = 10;
		damage = 1;
		moveSpeed = 4;
		shootcount = 0;
		poopcount = 0;
		ammo = 3;
		isDead = false;
		feathers = new ArrayList<feather>();
		eggs = new ArrayList<egg>();
		invulnerable = false;
		invulnerablecooldown = 0;
	}

	public void draw(Graphics g) {
		for (feather a : feathers)
			a.draw(g);
		// draws feathers
		for (egg e : eggs)
			e.draw(g);
		// draw egg
		super.draw(g);
		// draws self

	}

	public void move() {
		super.move();
		for (feather f : feathers)
			f.move();
		for (egg e : eggs)
			e.move();
		if (x < 800)
			shootFeather();

	}

	public void shootFeather() { // shoots automatically with cooldown
		if (shootcount == 0) {
			if (!isDead) {
				feathers.add(new feather(this.x, this.y, 15, 10, Color.BLUE, true));
				// adds a feather if alive
				shootcount = 20;
			}
		}
		shootcount--;
	}

	public void poop() { // poops automatically with cooldown
		if (ammo > 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y, 15, 15, Color.YELLOW));
				ammo--;
			}
		}
	}

	public boolean checkisDead() {
		if (health <= 0) {
			isDead = true;
			dx = 0;
			dy = 7;
			return true;
		}
		return false;
	}

}

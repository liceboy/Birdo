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
	public String poweruptype; // string keeps track of what powerup the player is holding
	public int ammo;
	public int maxammo;
	public int[] stats = { health, damage, moveSpeed };
	public ArrayList<feather> feathers;
	public ArrayList<truehomingFeather> truehomingFeathers;
	public ArrayList<egg> eggs;
	public player p;

	public player(int x, int y, Color c) {
		super(x, y, 20, 20, c);
		health = 10;
		damage = 1;
		moveSpeed = 4;
		shootcount = 0;
		poopcount = 0;
		ammo = 0;
		maxammo = 3;
		isDead = false;
		feathers = new ArrayList<feather>();
		eggs = new ArrayList<egg>();
		invulnerable = false;
		invulnerablecooldown = 0;
		poweruptype = "None"; // default powerup is always none
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
				feathers.add(new feather(this.x, this.y, true));
				// adds a feather if alive
				shootcount = 20;
			}
		}
		shootcount--;
	}

	public void poop() { // poops 
		if (ammo > 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y));
				ammo--;
			}
		}
	}
	
	public void usePowerup() { // uses the powerup based on string type, add powerups as you feel 
		if (poweruptype == "None")
			return;
		if (poweruptype == "Eggs") {
			if (ammo > 0)
			poop();
			if (ammo == 0)
			poweruptype = "None";
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

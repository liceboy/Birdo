package birdo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class player extends object{
	int shootcount;
	//shoot cooldown
	int poopcount;
	//poop cooldown
	int health;
	// health
	int damage;
	// strength of attacks
	int moveSpeed;
	// move speed when holding a key
	boolean isGood;
	// checks if character is birdo
	boolean isDead;
	// checks if the character is dead
	boolean invulnerable;
	// checks if invulnerable
	int invulnerablecooldown;
	int[] stats = { health, damage, moveSpeed};
	ArrayList<feather> feathers;
	ArrayList<egg> eggs;
	public player(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		health = 3;
		damage = 1;
		moveSpeed = 4;
		shootcount = 0;
		isDead = false;
		isGood = true;
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
		super.draw(g);
		// draws self
		
		g.drawString("invulnerable "+ invulnerable, 10, 200);
	}
	
	public void move() {
		super.move();
		for(feather f:feathers) f.move();
		for (egg e: eggs) e.move();
	}
	
	public void shootFeather() { // shoots automatically with cooldown
		if (shootcount == 0) {
		if (!isDead) {
			feathers.add(new feather(this.x , this.y , 15, 10, Color.BLUE, true)); 
			// adds a feather if alive
			shootcount = 20;
		}
		}
		shootcount--;
	}
	public void poop() { // poops automatically with cooldown
		if (poopcount == 0) {
			if(!isDead) {
				eggs.add(new egg (this.x, this.y, 15,15, Color.YELLOW));
				poopcount = 100;
			}
		}
		poopcount--; 
		}
		
	
	
	public boolean checkFeatherHits(player c) {
		if (c.isDead)
			return false;
		// doesn't check hits if dead
		if (c.invulnerable) {
			c.invulnerablecooldown--;
			if (c.invulnerablecooldown == 0)
				c.invulnerable = false;
			return false;
		}
		Rectangle hb = c.getHitBox();
		// creates enemy hitbox
		boolean check = false;
		// will automatically return false (no hit)
		for (int x = 0; x != feathers.size(); x++) {
			feather a = feathers.get(x);
			// gets an attack
			if (hb.intersects(a.getHitBox())) {
				// checks if attack hit enemy
				check = true;
				// will return true (hit confirmed)
				c.health -= damage;
				// adjust enemy health
				feathers.remove(x);
				// removes attack from attacks
				x--;
				// decrements
			}
		}
		
		return check;
		// returns success of attack
	}
	
	public boolean checkEggHits(player c) {
		if (c.isDead)
			return false;
		// doesn't check hits if dead
		if (c.invulnerable) {
			c.invulnerablecooldown--;
			if (c.invulnerablecooldown == 0)
				c.invulnerable = false;
			return false;
		}
		Rectangle hb = c.getHitBox();
		// creates enemy hitbox
		boolean check = false;
		// will automatically return false (no hit)
		for (int x = 0; x != eggs.size(); x++) {
			egg a = eggs.get(x);
			// gets an attack
			if (hb.intersects(a.getHitBox())) {
				// checks if attack hit enemy
				check = true;
				// will return true (hit confirmed)
				c.health -= damage;
				// adjust enemy health
				eggs.remove(x);
				// removes attack from attacks
				x--;
				// decrements
			}
		}
	
		return check;
		// returns success of attack
	}
	
	public boolean checkCollisionHits(player c) {
		if (c.isDead)
			return false;
		// doesn't check hits if dead
		if (this.invulnerable) {
			this.invulnerablecooldown--;
			if (this.invulnerablecooldown == 0)
				this.invulnerable = false;
			return false;
		}
		Rectangle hb = c.getHitBox();
		// creates enemy hitbox
		boolean check = false;
		// will automatically return false (no hit)
		Rectangle phb = this.getHitBox();
			if (hb.intersects(phb)) {
				// check if collided with enemy
				check = true;
				// will return true (hit confirmed)
				this.health -= damage;
				// adjust enemy health
		}
			
		return check;
		// returns success of attack
		
	}
	
}

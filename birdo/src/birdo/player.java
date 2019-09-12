package birdo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class player extends object{
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
	int[] stats = { health, damage, moveSpeed};
	ArrayList<feather> feathers;
	ArrayList<egg> eggs;
	public player(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		health = 3;
		damage = 1;
		moveSpeed = 4;
		isGood = true;
		feathers = new ArrayList<feather>();
		eggs = new ArrayList<egg>();
	}
	


	public void draw(Graphics g) {
		for (feather a : feathers)
			a.draw(g);
		// draws feathers
		for (egg e : eggs)
			e.draw(g);
		super.draw(g);
		// draws self
	}
	
	public void move() {
		super.move();
		for(feather f:feathers) f.move();
		for (egg e: eggs) e.move();
	}
	
	public void shootFeather() {
		if (!isDead) {
			feathers.add(new feather(this.x , this.y , 15, 10, Color.BLUE)); 
			// adds a feather if alive
		}
	}
	public void poop() {
			if(!isDead) {
				eggs.add(new egg (this.x, this.y, 15,15, Color.YELLOW));
			}
		}
		
	
	
	public boolean checkFeatherHits(player c) {
		if (c.isDead)
			return false;
		// doesn't check hits if dead
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
	
}

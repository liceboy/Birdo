package birdo.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.game.assets;

public abstract class attack extends object{
	
	// owner
	public player owner;
	public boolean fromPlayer;
	
	// game variables
	public player p;
	public ArrayList<enemy> enemies = new ArrayList<enemy>();
	public ArrayList<Integer> hasHit = new ArrayList<Integer>();
	
	// stats
	public int attack;
	public int pierce;
	
	// status effects
	public ArrayList<String> effects = new ArrayList<String>();;
	public ArrayList<Integer> effectDurations = new ArrayList<Integer>();;

	public attack(double x, double y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	
	public void setColor() {
		if (effects.contains("burn")) c = Color.RED;
		if (effects.contains("slow")) c = Color.CYAN;
		if (effects.contains("stun")) c = Color.BLUE;
		if (effects.contains("plasmized")) c = Color.MAGENTA;
	}
}

package birdo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.levels.pattern;
import birdo.utilities.enemy;

public class woods extends game{
	public woods() {
		super();
		
		layout.add("charge");
		layout.add("starter");
		layout.add("charge");
		layout.add("starter");
		
	}
	public void move() {
		super.move();
	}
}

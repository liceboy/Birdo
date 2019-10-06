package birdo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.levels.pattern;
import birdo.utilities.enemy;

public class woods extends game{
	public woods() {
		super();
		
		layout.add("enemyStaggered");
		layout.add("chargingArmy");
		layout.add("strafingTrio");
		layout.add("homingArmy");
		layout.add("rapidBar");
		
	}
	public void move() {
		super.move();
	}
}

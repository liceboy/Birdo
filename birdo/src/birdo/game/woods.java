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
		layout.add("bigEnemies");
		layout.add("burstShooters");
		layout.add("chargingArmy");
		layout.add("shooterGang");
		layout.add("comboAttack");
		layout.add("woodsBossFight");
	
	}
	public void move() {
		super.move();
	}
}

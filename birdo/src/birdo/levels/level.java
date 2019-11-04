package birdo.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.levels.*;
import birdo.utilities.*;

public class level extends game{
	public level() {
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
		this.powerups.add(new powerup(800, 300, "eggs"));
	}

}

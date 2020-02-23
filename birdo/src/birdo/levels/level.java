package birdo.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.levels.*;
import birdo.utilities.*;

public class level extends game {
	
	public level() {
		super();
		player.createLoadout("plasma", player.interval, 1, 1);
		player.createLoadout("homingFast", player.interval * 5, 2, 1);
	}
	
	public void defaultLayout() {
		
		layout.add("bigBlocks");
		layout.add("effectArmy");
		layout.add("spinGang");
		layout.add("enemyStaggered");
		layout.add("strafingTrio");
		layout.add("rapidBar");
		layout.add("bigDuo");
		layout.add("pulseDuo");
		layout.add("shooterGang");
		layout.add("comboAttack");
		layout.add("miniBoss1Fight");
		layout.add("miniBoss2Fight");
		layout.add("miniBoss3Fight");
	}

}

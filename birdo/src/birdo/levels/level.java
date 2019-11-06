package birdo.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import birdo.levels.*;
import birdo.utilities.*;

public class level extends game{
	public level() {
		super();
		
		this.createEnemy("miniBoss1", 800, 300);
		
		// layout.add("woodsBossFight");
	}

}

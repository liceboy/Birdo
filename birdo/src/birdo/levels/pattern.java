package birdo.levels;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import birdo.enemies.*;
import birdo.utilities.enemy;

public class pattern {

	public ArrayList<enemy> enemies = new ArrayList<enemy>();

	public pattern(String pn) {
		read(pn);
	}

	public void read(String name) {

		try {

			// setting up the reader

			InputStream in = pattern.class.getResourceAsStream("patterns.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(in));
			String current = b.readLine();

			while (!current.equals(name))
				current = b.readLine();
			current = b.readLine();

			// at this point, reader is on the first enemy of the pattern

			while (!current.equals("end")) {
				String[] params = current.split(" ");
				// type, x, y
				enemy temp = createEnemy(params[0]);
				temp.x = Integer.parseInt(params[1]);
				temp.y = Integer.parseInt(params[2]);
				enemies.add(temp);
				// adds the enemy to the main arr list
				current = b.readLine();
			}

			b.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public enemy createEnemy(String type) {

		enemy temp = null;
		if (type.equals("enemy"))
			temp = new enemy(0, 0);
		if (type.equals("homingEnemy"))
			temp = new homingEnemy(0, 0);
		if (type.equals("chargeEnemy"))
			temp = new chargeEnemy(0, 0);
		if (type.equals("bigEnemy"))
			temp = new bigEnemy(0, 0);
		if (type.equals("hoverEnemy"))
			temp = new hoverEnemy(0, 0);
		if (type.equals("strafeEnemy"))
			temp = new strafeEnemy(0, 0);
		if (type.equals("suicideEnemy"))
			temp = new suicideEnemy(0, 0);
		if (type.equals("rapidEnemy"))
			temp = new rapidEnemy(0, 0);
		if (type.equals("burstEnemy"))
			temp = new burstEnemy(0,0);
		if (type.equals("steadyEnemy"))
			temp = new steadyEnemy(0,0);
		if (type.equals("trueHomingEnemy"))
			temp = new trueHomingEnemy(0,0);
		if (type.equals("woodsBoss"))
			temp = new woodsBoss(0,0);
		return temp;
	}
}

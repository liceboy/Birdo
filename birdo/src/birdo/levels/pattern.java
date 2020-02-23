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
import birdo.utilities.obstacle;

public class pattern {

	public ArrayList<enemy> enemies = new ArrayList<enemy>();
	public ArrayList<obstacle> obstacles = new ArrayList<obstacle>();

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
				
				if (params[0].equals("obstacle")) {
					obstacle temp = new obstacle(Double.parseDouble(params[1]), Double.parseDouble(params[2]), 
												 Integer.parseInt(params[3]), Integer.parseInt(params[4]));
					obstacles.add(temp);
				}
				else {
					enemy temp = createEnemy(params[0]);
					temp.x = Integer.parseInt(params[1]);
					temp.y = Integer.parseInt(params[2]);
					enemies.add(temp);
					// adds the enemy to the main arr list
				}
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
		if (type.equals("target"))
			temp = new targetEnemy(0, 0);
		if (type.equals("charge"))
			temp = new chargeEnemy(0, 0);
		if (type.equals("big"))
			temp = new bigEnemy(0, 0);
		if (type.equals("hover"))
			temp = new hoverEnemy(0, 0);
		if (type.equals("strafe"))
			temp = new strafeEnemy(0, 0);
		if (type.equals("blossom"))
			temp = new blossomEnemy(0, 0);
		if (type.equals("rapid"))
			temp = new rapidEnemy(0, 0);
		if (type.equals("pulse"))
			temp = new pulseEnemy(0, 0);
		if (type.equals("steady"))
			temp = new steadyEnemy(0, 0);
		if (type.equals("spin"))
			temp = new spinEnemy(0,0);
		if (type.equals("laser"))
			temp = new laserEnemy(0,0);
		if (type.equals("explode"))
			temp = new explodeEnemy(0,0);
		if (type.equals("homing"))
			temp = new homingEnemy(0,0);
		if (type.equals("burn"))
			temp = new burnEnemy(0, 0);
		if (type.equals("freeze"))
			temp = new freezeEnemy(0, 0);
		if (type.equals("plasma"))
			temp = new plasmaEnemy(0, 0);
		if (type.equals("stun"))
			temp = new stunEnemy(0, 0);
		if (type.equals("miniBoss1"))
			temp = new miniBoss1(0, 0);
		if (type.equals("miniBoss2"))
			temp = new miniBoss2(0, 0);
		if (type.equals("miniBoss3"))
			temp = new miniBoss3(0, 0);
		return temp;
	}
}

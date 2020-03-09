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
					enemy temp = new enemy(0, 0);
					temp.type = params[0];
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
}

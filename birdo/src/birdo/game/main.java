package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import birdo.utilities.*;
import birdo.enemies.*;
import birdo.levels.*;

public class main extends JPanel implements ActionListener {
	
	// game loop
	private Timer timer;
	private final int DELAY = 10;
	
	// assets
	public assets assets;
	
	// state handler
	public String state = "title";
	
	public String rules = "medium";

	// states
	public title title;
	public select select;
	public shop shop; 
	
	public level level;
	public level dev;

	public main(){
		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());

		setFocusable(true);
		setBackground(Color.WHITE);
		
		timer = new Timer(DELAY, this);
		timer.start();
		// start game loop
		
		assets = new assets();
		setFont(assets.fonts[0]);
		
		state = "title";
		// start on title page
		
		title = new title();
		select = new select();
		select.rules = rules;
		shop = new shop();
		
		level = new level(rules);
		level.defaultLayout();
		dev = new level(rules);
	}

	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		super.paintComponent(g2d);
		doDrawing(g2d);

		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics2D g) {

		if (state == "title")
			title.draw(g, assets);

		if (state == "select")
			select.draw(g, assets);
		
		if (state == "shop")
			shop.draw(g, assets);

		if (state == "level")
			level.draw(g, assets);
		
		if (state == "dev")
			dev.draw(g, assets);
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
		
		long time = System.currentTimeMillis();
		
		if (state == "level")
			level.move();
		
		if (state == "dev")
			dev.move();
		
		// System.out.println(System.currentTimeMillis() - time);
	}

	private class KAdapter extends KeyAdapter {

		private KAdapter() {
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (state == "title") {
				if (key == KeyEvent.VK_SPACE)
					state = "select";
			}

			if (level.state == "GameOver") { // if gameover, new hotkeys, resets game state
				if (key == KeyEvent.VK_F1) {
					level = new level(rules);
					level.defaultLayout();
					state = "level";
				}
				if (key == KeyEvent.VK_F2) {
					level = new level(rules);
					level.defaultLayout();
					state = "title";
				}
				level.state = " ";
			}

			if (state == "select") {
				if (key == KeyEvent.VK_ESCAPE)
					state = "title";
				if (key == KeyEvent.VK_Z)
					state = "level";
				if (key == KeyEvent.VK_X)
					state = "dev";
				if (key == KeyEvent.VK_C)
					state = "shop";
				if (key == KeyEvent.VK_A) {
					if (rules.equals("easy"))
						rules = "medium";
					else if (rules.equals("medium"))
						rules = "hard";
					else if (rules.equals("hard"))
						rules = "master";
					else if (rules.equals("master"))
						rules = "easy";
					select.rules = rules;
					level.setRules(rules);
				}
			}
			
			if (state == "shop") {
				if (key == KeyEvent.VK_ESCAPE)
					state = "select";
			}

			if (state == "level") {
				if (key == KeyEvent.VK_UP)
					level.player.up = true;
				if (key == KeyEvent.VK_LEFT)
					level.player.left = true;
				if (key == KeyEvent.VK_DOWN)
					level.player.down = true;
				if (key == KeyEvent.VK_RIGHT)
					level.player.right = true;
				if (key == KeyEvent.VK_ESCAPE) {
					state = "select";
					level = new level(rules);
					level.defaultLayout();
				}
			}
			
			if (state == "dev") {
				if (key == KeyEvent.VK_UP)
					dev.player.up = true;
				if (key == KeyEvent.VK_LEFT)
					dev.player.left = true;
				if (key == KeyEvent.VK_DOWN)
					dev.player.down = true;
				if (key == KeyEvent.VK_RIGHT)
					dev.player.right = true;
				if (key == KeyEvent.VK_ESCAPE) {
					state = "select";
					dev = new level(rules);
				}
				
				if (key == KeyEvent.VK_1)
					dev.createEnemy("target");
				if (key == KeyEvent.VK_2)
					dev.createEnemy("charge");
				if (key == KeyEvent.VK_3)
					dev.createEnemy("big");
				if (key == KeyEvent.VK_4)
					dev.createEnemy("hover");
				if (key == KeyEvent.VK_5)
					dev.createEnemy("strafe");
				if (key == KeyEvent.VK_6)
					dev.createEnemy("blossom");
				if (key == KeyEvent.VK_7)
					dev.createEnemy("rapid");
				if (key == KeyEvent.VK_8)
					dev.createEnemy("pulse");
				if (key == KeyEvent.VK_9)
					dev.createEnemy("steady");
				if (key == KeyEvent.VK_0)
					dev.createEnemy("spin");
				if (key == KeyEvent.VK_MINUS)
					dev.createEnemy("laser");
				if (key == KeyEvent.VK_EQUALS)
					dev.createEnemy("explode");
				if (key == KeyEvent.VK_BACK_SPACE)
					dev.createEnemy("homing");
				if (key == KeyEvent.VK_T)
					dev.createPowerup("rapidFire");
				if (key == KeyEvent.VK_Y)
					dev.createPowerup("spinBurst");
				if (key == KeyEvent.VK_U)
					dev.createPowerup("homingRush");
				if (key == KeyEvent.VK_I)
					dev.createPowerup("stunShot");
				if (key == KeyEvent.VK_O)
					dev.createPowerup("heal");
				if (key == KeyEvent.VK_P)
					dev.createPowerup("invulnerability");
				if (key == KeyEvent.VK_L)
					dev.createEnemy("miniBoss1");
				if (key == KeyEvent.VK_K)
					dev.createEnemy("miniBoss2");
				if (key == KeyEvent.VK_J)
					dev.createEnemy("miniBoss3");
			}
			

			if (dev.state == "GameOver") { // if gameover, new hotkeys, resets game state
				if (key == KeyEvent.VK_F1) {
					state = "dev";
					dev = new level(rules);
				}
				if (key == KeyEvent.VK_F2) {
					dev = new level(rules);
					state = "title";
				}
				dev.state = " ";
			}

		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (state == "level") {
				if (key == KeyEvent.VK_UP)
					level.player.up = false;
				if (key == KeyEvent.VK_LEFT)
					level.player.left = false;
				if (key == KeyEvent.VK_DOWN)
					level.player.down = false;
				if (key == KeyEvent.VK_RIGHT)
					level.player.right = false;
				if (key == KeyEvent.VK_SPACE) 
					level.player.usePowerup();
			}
			
			if (state == "dev") {
				if (key == KeyEvent.VK_UP)
					dev.player.up = false;
				if (key == KeyEvent.VK_LEFT)
					dev.player.left = false;
				if (key == KeyEvent.VK_DOWN)
					dev.player.down = false;
				if (key == KeyEvent.VK_RIGHT)
					dev.player.right = false;
				if (key == KeyEvent.VK_SPACE) 
					dev.player.usePowerup();
			}

		}
	}

	private class MAdapter extends MouseAdapter {

		private MAdapter() {
		}

		public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();

			Rectangle mr = new Rectangle(mx, my, 1, 1);
		}

		public void mouseReleased(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();

			Rectangle mr = new Rectangle(mx, my, 1, 1);
		}
	}
}

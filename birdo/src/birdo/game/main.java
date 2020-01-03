package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
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
	
	// state handler
	public String state;

	// states
	public title title;
	public select select;
	public level level;
	
	public level dev;

	public main(){
		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());

		setFocusable(true);
		setBackground(Color.WHITE);
		
		// set the main font
		try {
			Font mainFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().
					getResourceAsStream("birdo/resources/Press Start.ttf"));
			mainFont = mainFont.deriveFont(0, 12f);
			// style (1 bold 2 italics 3 bold+italics)
			// font size (float variable)
			setFont(mainFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		// start game loop
		timer = new Timer(DELAY, this);
		timer.start();

		// start on title page
		state = "title";
		title = new title();
		select = new select();
		level = new level();
		level.defaultLayout();
		
		dev = new level();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {

		if (state == "title")
			title.draw(g);

		if (state == "select")
			select.draw(g);

		if (state == "level")
			level.draw(g);
		
		if (state == "dev")
			dev.draw(g);
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
					level = new level();
					level.defaultLayout();
					state = "level";
				}
				if (key == KeyEvent.VK_F2) {
					level = new level();
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
					level = new level();
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
					dev = new level();
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
				if (key == KeyEvent.VK_P)
					dev.createPowerup("rapidFire");
				if (key == KeyEvent.VK_O)
					dev.createPowerup("spinBurst");
				if (key == KeyEvent.VK_I)
					dev.createPowerup("homingRush");
				if (key == KeyEvent.VK_U)
					dev.createPowerup("stunShot");
				if (key == KeyEvent.VK_Y)
					dev.createPowerup("heal");
				if (key == KeyEvent.VK_T)
					dev.createPowerup("invulnerability");
			}
			

			if (dev.state == "GameOver") { // if gameover, new hotkeys, resets game state
				if (key == KeyEvent.VK_F1) {
					state = "dev";
					dev = new level();
				}
				if (key == KeyEvent.VK_F2) {
					dev = new level();
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

package birdo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import birdo.utilities.*;
import birdo.enemies.*;
import birdo.levels.*;

public class main extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7278894226467689035L;
	private Timer timer;
	private final int DELAY = 10;
	public String state;

	public title title;
	public select select;
	public level level;

	public main() {
		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());

		setFocusable(true);
		setBackground(Color.WHITE);

		setFont(new Font("Arial", 1, 16));

		timer = new Timer(DELAY, this);
		timer.start();

		state = "title";
		title = new title();
		select = new select();
		level = new level();
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
	}

	public void actionPerformed(ActionEvent e) {
		if (state == "level")
			level.move();

		repaint();
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
					state = "level";
					level = new level();
				}
				if (key == KeyEvent.VK_F2) {
					level = new level();
					state = "title";
				}
				level.state = " ";
			}

			if (state == "select") {
				if (key == KeyEvent.VK_ESCAPE)
					state = "title";
				if (key == KeyEvent.VK_1)
					state = "level";
			}

			if (state == "level") {
				if (key == KeyEvent.VK_UP)
					level.player.dy = -4;
				if (key == KeyEvent.VK_LEFT)
					level.player.dx = -4;
				if (key == KeyEvent.VK_DOWN)
					level.player.dy = 4;
				if (key == KeyEvent.VK_RIGHT)
					level.player.dx = 4;
				if (key == KeyEvent.VK_ESCAPE) {
					state = "select";
					level = new level();
				}
			}

		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (state == "level") {
				if (key == KeyEvent.VK_UP && level.player.dy != 2)
					level.player.dy = 0;
				if (key == KeyEvent.VK_LEFT && level.player.dx != 2)
					level.player.dx = 0;
				if (key == KeyEvent.VK_DOWN && level.player.dy != -2)
					level.player.dy = 0;
				if (key == KeyEvent.VK_RIGHT && level.player.dx != -2)
					level.player.dx = 0;
				if (key == KeyEvent.VK_SPACE) {
					level.player.usePowerup();
				}
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

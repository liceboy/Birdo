package birdo;

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

public class main extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7278894226467689035L;
	private Timer timer;
	private final int DELAY = 10;
	String state;

	title title;
	levelSelect levelSelect;
	woods woods;
	city city;
	beach beach;
	sky sky;

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
		levelSelect = new levelSelect();
		woods = new woods();
		beach = new beach();
		city = new city();
		sky = new sky();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {

		if (state == "title")
			title.draw(g);

		if (state == "levelSelect")
			levelSelect.draw(g);
		
		if (state == "woods")
			woods.draw(g);
		
		if (state == "city")
			city.draw(g);
		
		if (state == "beach")
			beach.draw(g);
		
		if (state == "sky")
			sky.draw(g);
	}

	public void actionPerformed(ActionEvent e) {
		if (state == "woods") woods.move();
		if (state == "city") city.move();
		if (state == "beach") beach.move();
		if (state == "sky") sky.move();
		

		repaint();
	}

	private class KAdapter extends KeyAdapter {

		private KAdapter() {
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if (state == "title") {
				if (key == KeyEvent.VK_SPACE)
					state = "levelSelect";
			}
			
			if (state == "levelSelect") {
				if (key == KeyEvent.VK_ESCAPE)
					state = "title";
				if (key == KeyEvent.VK_1)
					state = "woods";
				if (key == KeyEvent.VK_2)
					state = "city";
				if (key == KeyEvent.VK_3)
					state = "beach";
				if (key == KeyEvent.VK_4)
					state = "sky";
			}
			
			if (state == "woods") {
				if (key == KeyEvent.VK_UP)
					woods.player.dy = -4;
				if (key == KeyEvent.VK_LEFT)
					woods.player.dx = -4;
				if (key == KeyEvent.VK_DOWN)
					woods.player.dy = 4;
				if (key == KeyEvent.VK_RIGHT)
					woods.player.dx = 4;
				if (key == KeyEvent.VK_ESCAPE)
					state = "levelSelect";
				if(key == KeyEvent.VK_Z)
					woods.player.shootFeather();
				if(key == KeyEvent.VK_X)
					woods.player.poop();
		}
			
		

		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (state == "woods") {
			if (key == KeyEvent.VK_UP && woods.player.dy != 2)
				woods.player.dy = 0;
			if (key == KeyEvent.VK_LEFT && woods.player.dx != 2)
				woods.player.dx = 0;
			if (key == KeyEvent.VK_DOWN && woods.player.dy != -2)
				woods.player.dy = 0;
			if (key == KeyEvent.VK_RIGHT && woods.player.dx != -2)
				woods.player.dx = 0;
			
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




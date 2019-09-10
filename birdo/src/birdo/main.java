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
	
	title t;
	levelSelect ls;
	
	public main() {
		addKeyListener(new KAdapter());
		addMouseListener(new MAdapter());

		setFocusable(true);
		setBackground(Color.WHITE);

		setFont(new Font("Arial", 0, 16));

		this.timer = new Timer(DELAY, this);
		this.timer.start();

		this.state = "title";
		
		t = new title();
		ls = new levelSelect();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);

		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		if(state == "title") t.draw(g);
		if(state == "levelSelect") ls.draw(g);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(state == "title") t.move();
		if(state == "levelSelect") ls.move();

		
		repaint();
	}

	private class KAdapter extends KeyAdapter {

		private KAdapter() {
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
		
			
		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(state == "title") {
				if(key == KeyEvent.VK_SPACE) state = "levelSelect";
			}
			
			if (state == "levelSelect") {
				if(key == KeyEvent.VK_ESCAPE) state = "title";
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

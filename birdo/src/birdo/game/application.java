package birdo.game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class application extends JFrame {

	public application() {
		initUI();
	}

	private void initUI() {
		add(new main());
		setSize(800, 525);
		setResizable(false);
		setTitle("Birdo v0.5");
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				application ex = new application();
				ex.setVisible(true);
			}
		});
	}
}

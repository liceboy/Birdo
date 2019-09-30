package birdo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class egg extends object{
	ArrayList<scattershot> scattershots;

	public egg(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		scattershots = new ArrayList<scattershot>();
	}
	
	public void move() {
		dx = 0;
		dy = 10;
		for (scattershot s: scattershots) s.move();
		super.move();
	}
	
	
	public void draw(Graphics g) {
		for (scattershot s : scattershots)
			s.draw(g);
		// draw scattershots
		super.draw(g);
		// draws self
		
	}
	
	
	public void explode() {
		scattershot s = new scattershot(this.x , this.y , 15, 10, Color.YELLOW);
		s.dx = 5;
		scattershots.add(s);
		scattershot s1 = new scattershot(this.x , this.y , 15, 10, Color.YELLOW);
		s1.dx = -5;
		scattershots.add(s1);
		scattershot s2 = new scattershot(this.x, this.y, 15, 10, Color.YELLOW);
		s2.dx = -1 * (int) (5 * Math.cos(-1 * Math.PI/4));
		s2.dy = -1 * (int) (5 * Math.sin(-1 * Math.PI/4));
		scattershots.add(s2);
		scattershot s3 = new scattershot(this.x, this.y, 15, 10, Color.YELLOW);
		s3.dx = -1 * (int) (5 * Math.cos( Math.PI/4));
		s3.dy = -1 * (int) (5 * Math.sin( Math.PI/4));
		scattershots.add(s3);
		scattershot s4 = new scattershot(this.x, this.y, 15, 10, Color.YELLOW);
		s4.dx = -1 * (int) (5 * Math.cos(3 * Math.PI/4));
		s4.dy = -1 * (int) (5 * Math.sin(3 * Math.PI/4));
		scattershots.add(s4);
		scattershot s5 = new scattershot(this.x, this.y, 15, 10, Color.YELLOW);
		s5.dx = -1 * (int) (5 * Math.cos(5 * Math.PI/4));
		s5.dy = -1 * (int) (5 * Math.sin(5 * Math.PI/4));
		scattershots.add(s5);
	}
}

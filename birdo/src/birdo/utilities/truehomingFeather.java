package birdo.utilities;

public class truehomingFeather extends feather{
	
	public truehomingFeather(int x, int y, boolean forward) {
		super(x, y, forward);
		// TODO Auto-generated constructor stub
	}
	
	public void move () {
		int deltaX = p.x - x;
		int deltaY = p.y - y;

		double theta = Math.atan((double) deltaY / (double) deltaX);
		
		dx = -1 * (int) (6 * Math.cos(theta));
		dy = -1 * (int) (6 * Math.sin(theta));
		
		if(p.x > x) { 
			dy = 0;
			if(p.x - x > 30)
				dx = -8;
		}
		
		if(p.y > 500) {
			dy = 0;
			dx = -8;
		}

		super.move();
		return;
	}

}

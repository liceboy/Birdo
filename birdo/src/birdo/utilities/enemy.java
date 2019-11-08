package birdo.utilities;

import java.awt.Color;

public class enemy extends player {

	public player p;
	public int score;
	int shotmultiplier;
	int movecount;
	int timeInterval;

	public enemy(int x, int y) {
		super(x, y, Color.BLACK);
		health = 1;
		score = 100;
		this.dx = -2;
		this.dy = 0;
		shotmultiplier = 0;
		movecount = 300;
		timeInterval = 0;
	}

	public void shootFeather() {
		if (shootcount == 0) {
			if (!isDead) {
				feathers.add(new feather(this.x, this.y, false));
				// adds a feather if alive
				shootcount = 100;
			}
		}
		shootcount--;
	}
	
	public void customShot(String type) {
		if(type == "normal"){
			feathers.add(new feather(this.x, this.y, false));
		}
		if(type == "homing") {
			feather f = new feather(this.x, this.y, false);
			int deltaX = p.x - x;
			int deltaY = p.y - y;

			double theta = Math.atan((double) deltaY / (double) deltaX);
			f.dx = -1 * (int) (5 * Math.cos(theta));
			f.dy = -1 * (int) (5 * Math.sin(theta));

			feathers.add(f);
		}
		if(type == "tripleShot") {
			feather f = new feather(this.x, this.y, false);
			feather f1 = new feather(this.x, this.y, false);
			feather f2 = new feather(this.x, this.y, false);

			f.dx = -5;

			f1.dx = -1 * (int) (5 * Math.cos(15 * Math.PI / 180));
			f1.dy = -1 * (int) (5 * Math.sin(15 * Math.PI / 180));

			f2.dx = -1 * (int) (5 * Math.cos(345 * Math.PI / 180));
			f2.dy = -1 * (int) (5 * Math.sin(345 * Math.PI / 180));

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
		}
		if(type == "bloomShot") {
			feather f = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f1 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f2 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f3 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f4 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f5 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f6 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f7 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);

			f.dx = -5;
			f.dy = 0;
			f3.dx = 5;
			f3.dy = 0;

			f1.dx = -1 * (int) (-5 * Math.cos(Math.PI / 4));
			f1.dy = -1 * (int) (-5 * Math.sin(Math.PI / 4));

			f2.dx = -1 * (int) (-5 * Math.cos(7 * Math.PI / 4));
			f2.dy = -1 * (int) (-5 * Math.sin(7 * Math.PI / 4));
			
			f4.dx = (int) (-5 * Math.cos(Math.PI / 4));
			f4.dy = (int) (-5 * Math.sin(Math.PI / 4));
			
			f5.dx = (int) (-5 * Math.cos(7 * Math.PI / 4));
			f5.dy = (int) (-5 * Math.sin(7 * Math.PI / 4));
			
			f6.dx = 0;
			f6.dy = -5;
			f7.dx = 0;
			f7.dy = 5;

			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
			feathers.add(f4);
			feathers.add(f5);
			feathers.add(f6);
			feathers.add(f7);
		}
		if(type == "spinShot") {
			feather f = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f1 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f2 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			feather f3 = new feather(this.x+this.w/2-6, this.y+this.h/2-4, false);
			
				f.dx = -1 * (int) (5*Math.cos(shotmultiplier * Math.PI / 12));
				f.dy = -1 * (int) (5*Math.sin(shotmultiplier * Math.PI / 12));	
				
				f1.dx = -1 * (int) (5*Math.cos((shotmultiplier * Math.PI / 12)+ Math.PI/2));
				f1.dy = -1 * (int) (5*Math.sin((shotmultiplier * Math.PI / 12)+ Math.PI/2));	
				
				f2.dx = -1 * (int) (5*Math.cos((shotmultiplier * Math.PI / 12)+ Math.PI));
				f2.dy = -1 * (int) (5*Math.sin((shotmultiplier * Math.PI / 12)+ Math.PI));	
				 
				f3.dx = -1 * (int) (5*Math.cos((shotmultiplier * Math.PI / 12)+ 3*Math.PI/2));
				f3.dy = -1 * (int) (5*Math.sin((shotmultiplier * Math.PI / 12)+ 3*Math.PI/2));	
		
			shotmultiplier++;
			
			feathers.add(f);
			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
		}
		if(type == "buckShot") {
			feather f = new feather(this.x, this.y, false);
			feather f1 = new feather(this.x, this.y, false);
			feather f2 = new feather(this.x, this.y, false);
			feather f3 = new feather(this.x, this.y, false);
			feather f4 = new feather(this.x, this.y, false);
			feather f5 = new feather(this.x, this.y, false);

			f1.dx = -1 * (int) (5 * Math.cos(Math.PI / 8));
			f1.dy = -1 * (int) (5 * Math.sin(Math.PI / 8));
			f2.dx = -1 * (int) (5 * Math.cos(Math.PI / 4));
			f2.dy = -1 * (int) (5 * Math.sin(Math.PI / 4));
			f3.dx = -1 * (int) (5 * Math.cos(3 * Math.PI / 8));
			f3.dy = -1 * (int) (5 * Math.sin(3 * Math.PI / 8));
			f4.dx = (int) (5 * Math.cos(5 * Math.PI / 8));
			f4.dy = (int) (5 * Math.sin(5 * Math.PI / 8));
			f5.dx = (int) (5 * Math.cos(6 * Math.PI / 8));
			f5.dy = (int) (5 * Math.sin(6 * Math.PI / 8));
			f.dx = (int) (5 * Math.cos(7 * Math.PI / 8));
			f.dy = (int) (5 * Math.sin(7 * Math.PI / 8));

			feathers.add(f1);
			feathers.add(f2);
			feathers.add(f3);
			feathers.add(f4);
			feathers.add(f5);
			feathers.add(f);
		}
	}
   
	public void poop() {
		if (poopcount == 0) {
			if (!isDead) {
				eggs.add(new egg(this.x, this.y));
				poopcount = 100;
			}
		}
		poopcount--;
	}

	public void move() {
		if (x < 800)
			poop();
		super.move();
	}
	
	public void customMove(String type) {
		if (type == "moveOut") {
			if (x > 700)
				dx = -3;
		}
		if (type == "upDown") {
			if (!isDead) {
				dx = 0;
				if (dy == 0)
					dy = -3;
				if (y > 400) {
					dy = -3;
				}
				if (y < 100)
					dy = 3;
			}
		}
		if (type == "moveCenter") {
			if (Math.abs(x - w / 2 - 400) < 3 && Math.abs(y - h / 2 - 250) < 3) {
				dx = 0; dy = 0;
				return;
			}
			
			if (x > 400)
				dx = -3;
			if (y < 250)
				dy = 3;
			if (y > 250)
				dy = -3;
			if (x <= 400)
				dx = 0;
			if (y == 250)
				dy = 0;
		}
		if (type == "moveCircleSmall") {
			
		}
		if (type == "die") {
			dx = 0;
			dy = 5;
		}
			
				
	}
	
}

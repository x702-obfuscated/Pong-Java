package game;

import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class Ball extends Rectangle {
	private Random random;
	int xVelocity;
	int yVelocity;
	
	
	private static int speed;
	private static final Color color = new Color(255,255,255);//RGB
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		random  = new Random();
		speed = random.nextInt(3,7);
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0) {
			randomXDirection--;
		}
		setXDirection(randomXDirection * speed);
		
		int randomYDirection = random.nextInt(3);
		if(randomYDirection == 0) {
			randomYDirection--;
		}
		else if(1 == randomYDirection) {
			randomYDirection = 0;
		}
		else {
			randomYDirection = 1;
		}
		setYDirection(randomYDirection * speed);
		
	}
	
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
		
	}
	
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x,y,height,width);
	}
	
	
	
}

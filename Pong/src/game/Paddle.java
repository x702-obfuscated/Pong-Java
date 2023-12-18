package game;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Paddle extends Rectangle {
	
	private static final int SPEED = 10;
	
	private int id;
	private int yVelocity;
	
	
	
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id = id;
			
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-SPEED);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(SPEED);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-SPEED);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(SPEED);
				move();
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}
	}	
	
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	public void move() {
		y += yVelocity;
	}
	
	public void draw(Graphics g) {
		if(1 == id) {
			g.setColor(Color.blue);
		}
		else {
			g.setColor(Color.red);
		}
		g.fillRect(x,y,width,height);
	}
	
}

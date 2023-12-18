package game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/** Game Panel is the canvas of the window */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	private static final int GAME_WIDTH = 1000;
	private static final int GAME_HEIGHT = (int)(GAME_WIDTH * (5.0/9));
	private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	private static final int BALL_DIAMETER = 20;//pixels
	private static final int PADDLE_WIDTH = 25;
	private static final int PADDLE_HEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	 
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), (GAME_HEIGHT/2)-(BALL_DIAMETER/2), BALL_DIAMETER,BALL_DIAMETER);
		
	}
	
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT,2);
	}
	
	public void serve(int id) {
		if (id == 1) {
			ball.x = paddle1.width + BALL_DIAMETER;
			ball.y = GAME_HEIGHT / 2 - BALL_DIAMETER;
			ball.setXDirection(Math.abs(ball.xVelocity));
		}
		else {
			ball.x = GAME_WIDTH - (paddle1.width + BALL_DIAMETER);
			ball.y = GAME_HEIGHT/2 - BALL_DIAMETER;
			ball.setXDirection(-Math.abs(ball.xVelocity));
		}
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	
	}
	
	public void move() {
		//makes movement smoother
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	public void checkCollision() {
		//bounce ball off of top and bottom
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= (GAME_HEIGHT - BALL_DIAMETER)) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		//bounce ball off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			//optional for more difficulty
			ball.xVelocity++; 
			if(ball.yVelocity > 0) {
				ball.yVelocity++;
			}
			else {
				ball.yVelocity--;
			}
			//optional ^^^^^^^^^^^^^^^^^^
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			//optional for more difficulty
			ball.xVelocity++; 
			if(ball.yVelocity > 0) {
				ball.yVelocity++;
			}
			else {
				ball.yVelocity--;
			}
			//optional ^^^^^^^^^^^^^^^^^^
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		//Constrain paddles to the available y direction of the window.
		if(paddle1.y <= 0) {
			paddle1.y = 0;
		}
		
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
		}
		
		if(paddle2.y <= 0) {
			paddle2.y = 0;
		}
		
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
		}
		
		//Track score and create new paddles and new ball
		if(ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			//add player2 serves below
			serve(2);
		}
		if(ball.x >= (GAME_WIDTH - BALL_DIAMETER)) {
			score.player1++;
			newPaddles();
			newBall();
			//add player1 serves below
			serve(1);
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1E9 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move(); //Makes movement smoother
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			
		}
		
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
			
		}
	}
}

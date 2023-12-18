package game;
import java.awt.*;
import javax.swing.*;

/** Game Frame is the frame of the  window */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	GamePanel panel = new GamePanel();
	
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}

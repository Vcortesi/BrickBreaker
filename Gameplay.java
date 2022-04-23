import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements ActionListener, KeyListener {
	
	private boolean play = false;
	private int score;
	private int numOfBricks = 21;
	private Timer timer;
	private int delay = 1;
	private int ballXPos = 120;
	private int ballYPos = 350;
	private int ballXDir = -1;
	private int ballYDir = -2;
	private int playerX = 350;
	private MapGenerator map;
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay,this);
		timer.start();
		
		map = new MapGenerator(3,7);
	}
	
	public void paint(Graphics g) {
		// Black Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// Border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(0,3,3,592);
		g.fillRect(691,3,3,592);

		
		// Paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		
		// Bricks
		map.draw((Graphics2D)g);
		
		// Ball
		g.setColor(Color.red);
		g.fillOval(ballXPos, ballYPos, 20, 20);
		
		// Score
		g.setColor(Color.green);
		g.drawString("Score: "+score, 550, 30);
		
		// GameOver
		if(ballYPos >= 570) {
			play = false;
			ballXDir=0;
			ballYDir=0;
			
			g.setColor(Color.green);
			g.drawString("GameOver! | Score: "+score, 300, 300);
			g.drawString("Press enter to restart", 300, 330);
		}
		
		if(numOfBricks <= 0) {
			play = false;
			ballXDir=0;
			ballYDir=0;
			
			g.setColor(Color.green);
			g.drawString("You Won! | Score: "+score, 300, 300);
			g.drawString("Press enter to restart", 300, 330);
		}
	}
	
	private void moveLeft() {
		play = true;
		playerX -=20;
	}
	
	private void moveRight() {
		play = true;
		playerX+=20;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX<=0) {
				playerX = 0;
			}
			else {
				moveLeft();
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX>=600) {
				playerX = 600;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode ()==KeyEvent.VK_ENTER) {
			if(!play) {
				score = 0;
				numOfBricks = 21;
				ballXPos = 120;
				ballYPos = 350;
				ballXDir = -1;
				ballYDir = -2;
				playerX = 320;
				
				map = new MapGenerator(3,7);
				
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(play) {
			
			if(ballXPos<=0) {
				ballXDir =- ballXDir;
				
			}
			if(ballXPos>=670) {
				ballXDir =- ballXDir;
				
			}
			if(ballYPos<=0) {
				ballYDir =- ballYDir;
				
			}
			
			Rectangle ballRect = new Rectangle(ballXPos,ballYPos,20,20);
			Rectangle paddleRect = new Rectangle(playerX,550,100,8);
			
			if(ballRect.intersects(paddleRect)) {
				ballYDir=-ballYDir;
			}
			
			A:for(int i = 0; i < map.map.length;i++) {
				for(int j = 0; j < map.map[0].length;j++) {
					if (map.map[i][j]>0) {
						
						int width = map.brickWidth;
						int height = map.brickHeight;
						int brickXPos = 80 + (j * width);
						int brickYPos = 50 + (i * height);
						
						Rectangle brickRect = new Rectangle(brickXPos, brickYPos,width,height);
						
						if(ballRect.intersects(brickRect)) {
							map.setBrick(0, i, j);
							numOfBricks--;
							score+=5;
							
							if(ballXPos + 19 <= brickXPos ||
								ballXPos + 1 >= brickXPos + width) {
								ballXDir =- ballXDir;
							}
							else {
								ballYDir =- ballYDir;
							}
							
							break A;
						}
					}
				}
			}
			
			
			
			
			
			ballXPos+=ballXDir;
			ballYPos+=ballYDir;
			
			
			
			
			
			
			
			
			
			
			
		}
		repaint();
	}
	
	
	
	
	
	
	
	
}

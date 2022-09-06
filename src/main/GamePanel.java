package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

// inherited JPanel

public class GamePanel extends JPanel implements Runnable{
	//Screen setting
	final int originalTileSize = 16; //16*16
	final int scale = 3;  
	public final int tileSize = originalTileSize*scale;// 48*48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize*maxScreenCol; //768px
	final int screenHeight = tileSize*maxScreenRow; //576px
	
	//FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	//set player default position and speed
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		
		//setDoubleBuffered : If set to true, all the drawing 
		//from this component will be done in an off screen painting buffer.
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		//setFocusable(true): gamePanel can focus to receive key input
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.016666..7s for each time drawing
		double nextDrawTime = System.nanoTime() + drawInterval; //next draw time = current time + 0.01666...7
		
		while(gameThread != null) {
			
			
			//1: Update the information such as character positions
			update();
			//2: Draw the screen with the updated information
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime/=1000000;
				
				if(remainingTime < 0 ) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) { // this is repaint method
		//Graphics is the class drawing object 
		
		super.paintComponent(g);
		//super is object of the father class
		
		Graphics2D g2 = (Graphics2D)g;
		player.draw(g2);
		
		//Dispose of this graphics context and release any system resources that it is using
		g2.dispose();
	}
}

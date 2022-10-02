//branch diep 1234
package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		
		//make a proper 'X' closing button
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Adventure");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		// size the window to fit the preferred size of it subcomponent
		// <=> GamePanel
		window.pack();
		
		// not specify the position of the window <==> the window is 
		// centered on screen
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		// call this metod before startgamethread
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
	}

}

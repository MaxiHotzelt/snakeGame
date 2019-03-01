package com.empirie.maxi.snake.v5Deprecated;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Game game = new Game();
				game.setVisible(true);
				game.start();
			}
			
		});
	}
	
}

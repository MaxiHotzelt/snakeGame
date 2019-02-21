package com.empirie.maxi.snake.v3;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Main extends JFrame  {

	private Board board;
	
	public Main() {
		init();
		config();
		
	}
	
	public void init() {
		board = new Board();
		
		//Center frame
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gsd = ge.getScreenDevices();
		GraphicsConfiguration gc = gsd[2].getDefaultConfiguration();
		Rectangle rect = gc.getBounds();
		setLocation((int)rect.getCenterX()-board.getWidth()/2,(int)rect.getCenterY()-board.getHeight()/2);
		
	}
	
	public void config() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane();
			
		add(board);
		pack();
	}
	
	public void start() {
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			board.update();
		}
	}
	
	
	public static void main(String[] args) {
		Main game = new Main();
		game.setVisible(true);
		game.start();
	}
	
}

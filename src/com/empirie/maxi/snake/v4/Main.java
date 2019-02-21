package com.empirie.maxi.snake.v4;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Main extends JFrame  {

	private Board board;
	private GameInfo options;
	
	public Main() {
		init();
		config();
		
	}
	
	public void init() {
		options = new GameInfo();
		board = new Board(options);
		
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
		
		setLayout(new BorderLayout());
			
		add(board, BorderLayout.CENTER);
		pack();
		add(options, BorderLayout.LINE_END);
		pack();
	}
	
	public static void main(String[] args) {		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main game = new Main();
				game.setVisible(true);
			}
			
		});
	}
	
}

package com.empirie.maxi.snake.v6;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainG extends JFrame  {

	private GamePanelContainer game;
	
	public MainG() {
		init();
		config();
	}
	
	public void init() {
		game = new GamePanelContainer();
		
		//Center frame
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gsd = ge.getScreenDevices();
		GraphicsConfiguration gc = gsd[(int)gsd.length/2].getDefaultConfiguration();
		Rectangle rect = gc.getBounds();
		setLocation((int)rect.getCenterX()-game.getWidth()/2,(int)rect.getCenterY()-game.getHeight()/2);
	}
	
	public void config() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane();

		add(game);
		pack();
	}
	
	public static void main(String[] args) {		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainG game = new MainG();
				game.setVisible(true);
			}
		});
	}
	
}

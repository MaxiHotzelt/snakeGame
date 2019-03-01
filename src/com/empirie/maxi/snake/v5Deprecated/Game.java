package com.empirie.maxi.snake.v5Deprecated;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	private Menu menu;
	private Settings settings;
	private Board board;
	
	private int width;
	private int height;
	
	//getter
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public Settings getSettings() { return this.settings; }
	public Board getBoard() { return this.board; }
	
	private CardLayout cl = new CardLayout();
	private BorderLayout bl = new BorderLayout();
	
	public Game() {
		this.settings = new Settings();
		this.board = new Board(this);
		this.menu = new Menu(this);
		
		this.height = this.board.getHeight();
		this.width = this.board.getWidth();
		
		start();
	}
	
	public void changePanel(Gamestate gamestate) {
		switch (gamestate) {
		case MENU:
//			cl.show(this.getContentPane(), "BOARD");
//			board.setVisible(false);
//			menu.setVisible(true);
			
			break;
		case GAME:
//			cl.show(this.getContentPane(), "MENU");
//			menu.setVisible(false);
//			board.setVisible(true);
//			board.requestFocus();
			break;
		default:
			break;
		}
	}
	
	
	
	public void start() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getLayeredPane();
		setPreferredSize(new Dimension(width, height));
		
		//Center frame
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gsd = ge.getScreenDevices();
		GraphicsConfiguration gc = gsd[2].getDefaultConfiguration();
		Rectangle rect = gc.getBounds();
		setLocation((int)rect.getCenterX() - this.width/2,(int)rect.getCenterY() - this.height/2);
		
		add(menu);
		add(board);
		pack();
		
		changePanel(Gamestate.MENU);
		
	}
	
}

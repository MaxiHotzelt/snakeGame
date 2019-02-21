package com.empirie.maxi.snake.v3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	private Snake snake = new Snake();
	private Food food = new Food();
	
	public Board() {
		
		
		addKeyListener(new KYListener());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocusInWindow();
		
		relocateFood();
	}
	
	private final int ROWS = 20;
	private final int COLUMNS = 20;
	
	private final int FIELDWIDTH = 40;
	private final int FIELDHEIGHT = 40;
	
	private final int HEIGHT = ROWS * FIELDHEIGHT;
	private final int WIDTH = COLUMNS * FIELDWIDTH;
	
	
	//getter
	public int getRows() { return ROWS; }
	public int getColumns() { return COLUMNS; }
	public int getFieldwidth() { return FIELDWIDTH; }
	public int getFieldheight() { return FIELDHEIGHT; }	
	public int getHeight() { return HEIGHT; }
	public int getWidth() { return WIDTH; }
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		//draw food
		g.setColor(Color.ORANGE);
		g.fillOval(food.getPosX() * FIELDWIDTH, food.getPosY() * FIELDHEIGHT, FIELDWIDTH, FIELDHEIGHT);
		
		
		//draw board
		g.setColor(Color.lightGray);
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				g.drawRect(j * FIELDWIDTH, i * FIELDHEIGHT, FIELDWIDTH, FIELDHEIGHT);
			}
		}
		
		//draw snake
		boolean firstPart = true;
		for(SnakeBodyPart part : snake.getSnakePartList()) {
			if(firstPart) {
				g.setColor(Color.RED);	
				firstPart = false;
			} else {
				g.setColor(Color.GREEN);
			}
			g.fillRect(part.getPosX() * FIELDWIDTH+1, part.getPosY() * FIELDHEIGHT+1, FIELDWIDTH-1, FIELDHEIGHT-1);
		}
		
		
	}
	
	/**
	 * Updates the board -> moves snake, relocates food and repaints everything
	 */
	public void update() {
		
		if(isWallCollision()) {
			return;
		}
		if(isSelfCollision()) {
			return;
		}
		
		
		snake.move();
		if(isFoodCollision()) {
			snake.eat();
			relocateFood();
		}
		
		repaint();
	}
	
	public void relocateFood() {
		boolean suchePos = true;
		int foodY = 0, foodX = 0;
		Random ran = new Random();
		
		while(suchePos) {
			foodY = ran.nextInt(ROWS);
			foodX = ran.nextInt(COLUMNS);
			for(SnakeBodyPart part : snake.getSnakePartList()) {
				if(part.getPosX() == foodX && part.getPosY() == foodY) {
					suchePos = true;
					break;
				} else {
					suchePos = false;
				}
			}
		}			
		food.relocate(foodX, foodY);
	}
	
	public boolean isSelfCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
		
		boolean isHead = true;
		boolean isCollision = false;
		for(SnakeBodyPart part : snake.getSnakePartList()) {
			if(isHead) {
				isHead = false;
			} else {
				if(head.getPosX() == part.getPosX() && head.getPosY() == part.getPosY()) {
					isCollision = true;
					break;
				}
			}
		}
		return isCollision;
	}
	
	public boolean isWallCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
	
		if(	head.getPosY() + snake.getYSpeed() < 0 || head.getPosY() + snake.getYSpeed() > ROWS-1 ||
			head.getPosX() + snake.getXSpeed() < 0 || head.getPosX() + snake.getXSpeed() > COLUMNS-1	
		) {
			return true;
		} else { return false; }
		
	}
	
	
	/**
	 * @return boolean - returns true, if the head of the snake is on the location of the food, otherwise returns false
	 */
	public boolean isFoodCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
		
		if(head.getPosX() == food.getPosX() && head.getPosY() == food.getPosY()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	/**
	 *  @author hotzelm
	 *	KeyListeners to change the direction if the snake head
	 */
	private class KYListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				if(snake.getXSpeed() == 0 && snake.getYSpeed() == 1) return;
				snake.setXSpeed(0);
				snake.setYSpeed(-1);
			} 
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				if(snake.getXSpeed() == 0 && snake.getYSpeed() == -1) return;
				snake.setXSpeed(0);
				snake.setYSpeed(1);
			} 
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(snake.getXSpeed() == 1 && snake.getYSpeed() == 0) return;
				snake.setXSpeed(-1);
				snake.setYSpeed(0);
			} 
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(snake.getXSpeed() == -1 && snake.getYSpeed() == 0) return;
				snake.setXSpeed(1);
				snake.setYSpeed(0);
			} 
			
			
		}
	
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
}


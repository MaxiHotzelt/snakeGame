package com.empirie.maxi.snake.v2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

import com.empirie.maxi.snake.v1.model.Bodypart;
import com.empirie.maxi.snake.v1.model.Direction;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	private Snake snake = new Snake();
	private Food food = new Food(1,1);
	
	public Board() {
		addKeyListener(new KYListener());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocusInWindow();
	}
	

	private final int ROWS = 10;
	private final int COLUMNS = 10;
	
	private final int FIELDWIDTH = 50;
	private final int FIELDHEIGHT = 50;
	
	private final int HEIGHT = ROWS * FIELDHEIGHT;
	private final int WIDTH = COLUMNS * FIELDWIDTH;
	
	public int getRows() { return ROWS; }
	public int getColumns() { return COLUMNS; }
	
	public int getFieldwidth() { return FIELDWIDTH; }
	public int getFieldheight() { return FIELDHEIGHT; }	
	
	public int getHeight() { return HEIGHT; }
	public int getWidth() { return WIDTH; }
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//draw food
		g.setColor(Color.ORANGE);
		g.fillRect(food.getPosX() * FIELDWIDTH+1, food.getPosY() * FIELDHEIGHT+1, FIELDWIDTH-1, FIELDHEIGHT-1);
		
		
		//draw board
		g.setColor(Color.BLUE);
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				g.drawRect(j * FIELDWIDTH, i * FIELDHEIGHT, FIELDWIDTH, FIELDHEIGHT);
			}
		}
		
		//draw snake
		for(SnakeBodyPart part : snake.getSnakePartList()) {
			if(part.getBodypart() == Bodypart.HEAD) {
				g.setColor(Color.RED);	
			} else {
				g.setColor(Color.GREEN);
			}
			g.fillRect(part.getPosX() * FIELDWIDTH+1, part.getPosY() * FIELDHEIGHT+1, FIELDWIDTH-1, FIELDHEIGHT-1);
		}
		
		
	}
	
	public void update() {
		snake.move();
		if(isFoodCollision()) {
			boolean suchePos = true;
			int foodY = 0;
			int foodX = 0;
			Random ran = new Random();
			
			while(suchePos) {

				foodY = ran.nextInt(ROWS);
				foodX = ran.nextInt(COLUMNS);
				for(SnakeBodyPart part : snake.getSnakePartList()) {
					if(part.getPosX() == foodX && part.getPosY() == foodY) {
						suchePos = true;
						return;
					} else {
						suchePos = false;
					}
				}
			}
			ran = null;
			
			System.out.println("Food X: " + foodX + "\nFood Y: " + foodY);
			
			food.relocate(foodX, foodY);
			
			snake.addSnakePart();
		}
		
		
		repaint();
	}
	
	
	public boolean isFoodCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
		
		if(head.getPosX() == food.getPosX() && head.getPosY() == food.getPosY()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	/**
	 * 
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
				snake.getSnakePartList().get(0).setCurrentDir(Direction.UP);
			} 
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				snake.getSnakePartList().get(0).setCurrentDir(Direction.DOWN);
			} 
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				snake.getSnakePartList().get(0).setCurrentDir(Direction.LEFT);
			} 
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				snake.getSnakePartList().get(0).setCurrentDir(Direction.RIGHT);
			} 
			
			
		}
	
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
}

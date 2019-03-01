package com.empirie.maxi.snake.v5Deprecated;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel{
	
	//objects placed on the board
	private Snake snake;
	private Food food = new Food();
	//objects needed
	private Timer timer;
	private Highscores highscores;
	public Game game;
	
	
	//settings of the field/board
	private final int ROWS = 20;
	private final int COLUMNS = 20;
	
	private final int FIELDWIDTH = 40;
	private final int FIELDHEIGHT = 40;
	
	private final int HEIGHT = ROWS * FIELDHEIGHT;
	private final int WIDTH = COLUMNS * FIELDWIDTH;
	
	//update time
	private int updateSpeed = 100;
	private final int DEFAULTSPEED = 100;
	
	private int buttonsPressed = 0;
	private int score = 0;
	
	//getter
	public int getRows() { return ROWS; }
	public int getColumns() { return COLUMNS; }
	public int getFieldwidth() { return FIELDWIDTH; }
	public int getFieldheight() { return FIELDHEIGHT; }	
	public int getHeight() { return HEIGHT; }
	public int getWidth() { return WIDTH; }
	public int getUpdateSpeed() { return updateSpeed; }
	public int getScore() { return this.score; }
	
		
	public Board(Game game) {
		
		this.game = game;
		this.highscores = new Highscores();
		this.snake = new Snake(game);
		
		
		addKeyListener(new KYListener());
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocusInWindow();
		setBackground(new Color(103, 132, 4));
		
		//initialize position of the food
		relocateFood();
		
		//set timer
		timer = new Timer(this.updateSpeed, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
	}
		
	/**
	 * Updates the board -> moves snake, relocates food and repaints everything.
	 */
	public void update() {
		
		if(game.getSettings().getIsBorderCollisionActive()) {
			if(isWallCollision()) {
				game.changePanel(Gamestate.MENU);
				resetBoard();
			}
		}
		if(isSelfCollision()) {
			game.changePanel(Gamestate.MENU);
			resetBoard();
		}
		
		
		snake.move();
		if(isFoodCollision()) {
			snake.eat();
			relocateFood();
			
			score++;
			
			if(score % 10 == 0) {
				increaseUpdatespeed(10);
				
				System.out.println(this.updateSpeed);
			}
			
		}
		
		buttonsPressed = 0;
		repaint();
	}	
	
	private void increaseUpdatespeed(int value) { 
		this.updateSpeed -= value;
		timer.setDelay(updateSpeed); 
	}
	
	private void resetBoard() {
		resetSpeed();
		
		//resets score
		highscores.addScore(score);
		score = 0;
		
		
		timer.stop();
		
		//resets snake
		snake.getSnakePartList().clear();
		snake.init();
		
		//resets food
		relocateFood();
	}
	
	private void resetSpeed() {
		this.updateSpeed = DEFAULTSPEED;
		timer.setDelay(updateSpeed);
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
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//activates antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	
		timer.start();
		//draw food
		food.render(g2d, FIELDWIDTH, FIELDHEIGHT);
		//draw board
		this.render(g2d);
		//draw snake
		snake.render(g2d, FIELDWIDTH, FIELDHEIGHT);
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.lightGray);
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				g.drawRect(j * FIELDWIDTH, i * FIELDHEIGHT, FIELDWIDTH, FIELDHEIGHT);
			}
		}
	}
	
	/**
	 * Checks if the snake head is colliding with its body.
	 * @return Returns true, if the snake head is colliding with its body, otherwise returns false
	 */
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
	
	/**
	 * Checks if the snake is colliding with the wall
	 * @return Returns true, if the snake head is colliding with the wall
	 */
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
	 *	KeyListeners to change the direction if the snake on keyboard input or to pause the game
	 */
	private class KYListener extends KeyAdapter {
		private boolean gamePaused = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		//changes the x- and y-speed of the snake, which is responsible for the direction the snake is moving
		@Override
		public void keyReleased(KeyEvent e) {
			
			if(buttonsPressed == 0) {
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
				
				
				buttonsPressed++;
			}
			
			if(e.getKeyCode() == 80) {
				System.out.println("Im in");
				if(gamePaused) {
					gamePaused = false;
					timer.start();
				} else {
					gamePaused = true;
					timer.stop();
				}
				
			} 
			
			
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}


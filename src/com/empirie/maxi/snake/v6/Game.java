package com.empirie.maxi.snake.v6;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Game {
	
	//settings of the field/board
	private final int ROWS = 20;
	private final int COLUMNS = 20;
	
	private final int FIELDWIDTH = 40;
	private final int FIELDHEIGHT = 40;
	
	private final int HEIGHT = ROWS * FIELDHEIGHT;
	private final int WIDTH = COLUMNS * FIELDWIDTH;
	
	//game objects
	private Snake snake = new Snake(this);
	private Food food;
	private Superfood sfood;
	private Badfood bfood;
	private WallList walls;
	//objects needed
	private Timer timer;
	private GamePanelContainer container;
	private Thread sfoodThread;
	private Thread bfoodThread;
	
	//rest
	private int score = 0;
	private boolean pause = false;
	private int defaultSpeed;
	private Image grassTile;
	
	public Game(GamePanelContainer container) {
		this.container = container;
		this.walls = new WallList(this);
		this.food = new Food(this);
		
		//set timer
		timer = new Timer(container.getSettings().getDifficulty().getUpdatespeed(), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		//load grass tile image
		grassTile = container.getUtils().loadImage("grass1.jpg");

	}
	
	//getter
	public int getRows() { return ROWS; }
	public int getColumns() { return COLUMNS; }
	public int getFieldwidth() { return FIELDWIDTH; }
	public int getFieldheight() { return FIELDHEIGHT; }	
	public int getHeight() { return HEIGHT; }
	public int getWidth() { return WIDTH; }
	public boolean getIsPaused() { return this.pause; }
	
	public Snake getSnake() { return this.snake; }
	public GamePanelContainer getContainer() { return this.container; }
	public Timer getTimer() { return this.timer; }
	public Thread getSFoodThread() { return this.sfoodThread; }
	public Food getFood() { return this.food; }
	public Superfood getSuperfood() { return this.sfood; }
	public Badfood getBadfood() { return this.bfood; }
	public WallList getWalls() { return this.walls; }
	
	
	public void render(Graphics2D g2d) {
		
		//draw board
		g2d.setColor(Color.lightGray);
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				g2d.drawImage(grassTile, j * FIELDWIDTH, i * FIELDHEIGHT, FIELDWIDTH, FIELDHEIGHT, null);
//				g2d.drawRect(j * FIELDWIDTH, i * FIELDHEIGHT, FIELDWIDTH, FIELDHEIGHT);
			}
		}
		
		//draw score
		g2d.setColor(Color.black);
		g2d.setFont(container.getUtils().getFont());
		g2d.drawString("SCORE ", WIDTH/2, HEIGHT + 50);
		g2d.drawString(""+score, WIDTH/2 + 150, HEIGHT + 50);
		//draw food
		food.render(g2d, FIELDWIDTH, FIELDHEIGHT);
		//draw sfood
		sfood.render(g2d, FIELDWIDTH, FIELDHEIGHT);
		//draw bfood
		bfood.render(g2d, FIELDWIDTH, FIELDHEIGHT);
		//draw snake
		snake.render(g2d, FIELDWIDTH, FIELDHEIGHT);
		//draw walls
		walls.render(g2d);
	}
	
	public void updateUpdatespeed(int value) { 
		//the value is the score divided by 10, so we get 0 for 0-9, 1 for 10-19 etc...
		//it needs to be multiplied by 10, which is then subtracted from the default speed
		int newSpeed =  defaultSpeed - value * 10;
		timer.setDelay(newSpeed); 
	}
		
	/**
	 * starts the timer and the threads for special food. It also initializes the position of the normal food
	 * and sets the default speed of the chosen difficulty
	 */
	public void start() {
		container.setGamestate(Gamestate.GAME);
		
		timer.start();
		
		//set the default speed
		defaultSpeed = this.container.getSettings().getDifficulty().getUpdatespeed();
		
		//start food threads
		sfood = new Superfood(this);
		sfood.setRunning(true);
		sfoodThread = new Thread(sfood);
		sfoodThread.start();
		
		bfood = new Badfood(this);
		bfood.setRunning(true);
		bfoodThread = new Thread(bfood);
		bfoodThread.start();
		
		//positions food at gamestart
		relocateFood(food);
	}
	
	/**
	 * 
	 */
	public void stop() {
		container.setGamestate(Gamestate.MENU);
		//stop running threads
		sfood.setRunning(false);
		bfood.setRunning(false);
		
		
		container.getHighscores().addScore(score);
		score = 0;
		
		timer.stop();
		
		//resets the snake
		snake.getSnakePartList().clear();
		snake.init();	
	}
	
	public void pause() {
		this.pause = true;
		timer.stop();
		
	}
	
	public void unpause() {
		this.pause = false;
		timer.start();
		
	}
	
	
	/**
	 * Updates the board -> moves snake, relocates food and repaints everything.
	 */
	public void update() {
		
		//checks if snake collided with the wall
		if(container.getSettings().getIsBorderCollisionActive()) {
			if(isBorderCollision()) {
				stop();
			}
		}
		//checks if snake collided with itself
		if(isSelfCollision()) {
			stop();
		}
		
		
		snake.move();
		
		
		if(isWallCollision()) {
			stop();
		}
		//checks if snake collided with food
		if(isFoodCollision()) {
			
			for(int i = 0; i < food.getValue(); i++) {
				snake.eat();	
			}
			relocateFood(food);
			
			score = score + food.getValue();
			
			
			
		}
		
		//checks is the snake collided with super food
		if(isSuperFoodCollision()) {
			//snake as many joints as the value of the food
			for(int i = 0; i < sfood.getValue(); i++) {
				snake.eat();	
			}
			score = score + sfood.getValue();
			
			relocateSuperfood();	
		}
		
		//checks if the snake collided with bad food
		if(isBadFoodCollision()) {
			
			if(score + bfood.getValue() < 0) {
				stop();
			} else {
				for(int i = 0; i < -bfood.getValue(); i++) {
					snake.eatBadfood();	
				}
							
				score = score + bfood.getValue();
				
				relocateBadfood();
				
				
			}
		}
		
		
		updateUpdatespeed((this.score / 10));

		container.resetButtonsPressed();
		container.repaint();
	}
	
	public void relocateSuperfood() {
		//kills off thread, through setting is running on false, which causes the thread to finish his execution
		sfood.setRunning(false);
		sfood.hide();
		sfoodThread.interrupt();
		
		//creating a new instance of super food, which is then handed to a new thread
		sfood = new Superfood(this);
		sfoodThread = new Thread(sfood);
		sfood.setRunning(true);
		sfoodThread.start();
	}
	
	public void relocateBadfood() {
		//kills off thread, through setting is running on false, which causes the thread to finish his execution
		bfood.setRunning(false);
		bfood.hide();
		bfoodThread.interrupt();
		
		//creating a new instance of bad food, which is then handed to a new thread
		bfood = new Badfood(this);
		bfoodThread = new Thread(bfood);
		bfood.setRunning(true);
		bfoodThread.start();
	}
	
	
	public void relocateFood(Food food) {
		boolean suchePos = true;
		int foodY = 0, foodX = 0;
		Random ran = new Random();
		
		
		//checks if snake is on position of food
		while(suchePos) {
			suchePos = false;
			foodY = ran.nextInt(ROWS);
			foodX = ran.nextInt(COLUMNS);
			for(SnakeBodyPart part : snake.getSnakePartList()) {
				if(part.getPosX() == foodX && part.getPosY() == foodY) {
					suchePos = true;
					break;
				}
			}
			
			for(WallPiece wall : walls) {
				if(wall.getPosX() == foodX && wall.getPosY() == foodY) {
					suchePos = true;
					break;
				}
			}
		}			
		food.relocate(foodX, foodY);
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
	 * Checks if the snake is colliding with the border walls
	 * @return Returns true, if the snake head is colliding with the border wall
	 */
	public boolean isBorderCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
		
		if(	head.getPosY() + snake.getYSpeed() < 0 || head.getPosY() + snake.getYSpeed() > ROWS-1 ||
			head.getPosX() + snake.getXSpeed() < 0 || head.getPosX() + snake.getXSpeed() > COLUMNS-1	
		) {
			return true;
		} else { return false; }
		
	}
	
	public boolean isWallCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
		boolean isCollision = false;
		
		
		for(WallPiece wall : walls) {
			if(head.getPosX() == wall.getPosX() && head.getPosY() == wall.getPosY()) {
				isCollision = true;
				break;
			}	
		}
		return isCollision;
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
	
	public boolean isSuperFoodCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
		
		if(head.getPosX() == sfood.getPosX() && head.getPosY() == sfood.getPosY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isBadFoodCollision() {
		SnakeBodyPart head = snake.getSnakePartList().get(0);
		
		if(head.getPosX() == bfood.getPosX() && head.getPosY() == bfood.getPosY()) {
			return true;
		} else {
			return false;
		}
	}
	
	
}


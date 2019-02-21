package com.empirie.maxi.snake.v4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.empirie.maxi.snake.v4.Menu.Button;

@SuppressWarnings("serial")
public class Board extends JPanel{
	
	//objects placed on the board
	private Snake snake = new Snake();
	private Food food = new Food();
	//objects needed
	private Timer timer;
	private GameInfo options;
	private Menu menu;
	private Highscores highscores;
	
	private Gamestate gamestate;
	
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
	
	public Board(GameInfo options) {
		
		this.options = options;
		this.gamestate = Gamestate.MENU;
		this.menu = new Menu(this.WIDTH, this.HEIGHT);
		this.highscores = new Highscores();
		
		
		addKeyListener(new KYListener());
		addMouseListener(new MSListener());
		
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
	
	//getter
	public int getRows() { return ROWS; }
	public int getColumns() { return COLUMNS; }
	public int getFieldwidth() { return FIELDWIDTH; }
	public int getFieldheight() { return FIELDHEIGHT; }	
	public int getHeight() { return HEIGHT; }
	public int getWidth() { return WIDTH; }
	public int getUpdateSpeed() { return updateSpeed; }
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//activates antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		//paint menu or game
		if(gamestate == Gamestate.GAME) {
			timer.start();
			//draw food
			food.render(g2d, FIELDWIDTH, FIELDHEIGHT);
			//draw board
			this.render(g2d);
			//draw snake
			snake.render(g2d, FIELDWIDTH, FIELDHEIGHT);
		} else if(gamestate == Gamestate.MENU) {
			//draw menu
			menu.render(g2d, WIDTH, HEIGHT);
		} else if(gamestate == Gamestate.HIGHSCORES) {
			highscores.render(g2d);
		}
		
		
	}
	
	public void increaseUpdatespeed(int value) { 
		this.updateSpeed -= value;
		timer.setDelay(updateSpeed); 
		}
	
	public void render(Graphics g) {
		g.setColor(Color.lightGray);
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				g.drawRect(j * FIELDWIDTH, i * FIELDHEIGHT, FIELDWIDTH, FIELDHEIGHT);
			}
		}
	}
	
	public void resetBoard() {
		resetSpeed();
		
		highscores.addScore(options.getScore());
		timer.stop();
		snake.getSnakePartList().clear();
		snake.init();
		relocateFood();
	}
	
	public void resetSpeed() {
		this.updateSpeed = DEFAULTSPEED;
		timer.setDelay(updateSpeed);
	}
	
	/**
	 * Updates the board -> moves snake, relocates food and repaints everything.
	 */
	public void update() {
		
		if(isWallCollision()) {
			gamestate = Gamestate.MENU;
			resetBoard();
		}
		if(isSelfCollision()) {
			gamestate = Gamestate.MENU;
			resetBoard();
		}
		
		
		snake.move();
		if(isFoodCollision()) {
			snake.eat();
			relocateFood();
			
			options.addPoint();
			
			if(options.getScore() % 10 == 0) {
				increaseUpdatespeed(10);
				
				System.out.println(this.updateSpeed);
			}
			
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
	 *	KeyListeners to change the direction if the snake on keyboard input
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
			if(e.getKeyCode() == 80) {
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


	private class MSListener implements MouseListener, ImageObserver {
		@Override
		public void mouseClicked(MouseEvent m) {
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	
		@Override
		public void mousePressed(MouseEvent m) {
			int mx = m.getX();
			int my = m.getY();
			
			if(gamestate == Gamestate.MENU) {
				Button playBtn = menu.getPlayBtn();
				Button settingsBtn = menu.getSettingsBtn();
				Button scoreBtn = menu.getScoreBtn();
				Button quitBtn = menu.getQuitBtn();
				
				//play Button pressed
				if( mx > playBtn.getPosX() && mx < playBtn.getPosX() + playBtn.getWidht() &&
					my > playBtn.getPosY() && my < playBtn.getPosY() + playBtn.getHeight()
				) {
					gamestate = Gamestate.GAME;
					options.resetScore();
					timer.start();
				}
				//settings Button pressed
				if( mx > settingsBtn.getPosX() && mx < settingsBtn.getPosX() + settingsBtn.getWidht() &&
					my > settingsBtn.getPosY() && my < settingsBtn.getPosY() + settingsBtn.getHeight()
				) {
					System.out.println("Settings");
					System.out.println(updateSpeed);
				}
				//score Button pressed
				if( mx > scoreBtn.getPosX() && mx < scoreBtn.getPosX() + scoreBtn.getWidht() &&
					my > scoreBtn.getPosY() && my < scoreBtn.getPosY() + scoreBtn.getHeight()
				) {
					gamestate = Gamestate.HIGHSCORES;
					repaint();
				}
				//quit Button pressed
				if( mx > quitBtn.getPosX() && mx < quitBtn.getPosX() + quitBtn.getWidht() &&
					my > quitBtn.getPosY() && my < quitBtn.getPosY() + quitBtn.getHeight()
				) {
					highscores.saveScores();
					System.exit(0);
				}
			}
			
			if(gamestate == Gamestate.HIGHSCORES) {
				if( mx > 200 && mx < 400 &&
					my > 500 && my < 700
				) {
					gamestate = Gamestate.MENU;
					repaint();
				}
			}
		}
	
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

		@Override
		public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	
	
	
	
}


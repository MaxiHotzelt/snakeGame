package com.empirie.maxi.snake.v6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import com.empirie.maxi.snake.v6.Menu.Button;

@SuppressWarnings("serial")
public class GamePanelContainer extends JPanel {
	
	private Game game;
	private Menu menu;
	private Highscores highscores;
	private Settings settings;
	private Gamestate gamestate;
	private Utils utils;
	
	private int width = 800;
	private int height = 900;
	

	private int buttonsPressed = 0;
	
	//getter
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public Settings getSettings() { return this.settings; }
	public Game getGame() { return this.game; }
	public Highscores getHighscores() { return this.highscores; }
	public Utils getUtils() { return utils; }
	
	//setter
	public void setGamestate(Gamestate state) { this.gamestate = state; }
	public void resetButtonsPressed() { this.buttonsPressed = 0; }
	
	
	public GamePanelContainer() {

		this.utils = new Utils();
		this.gamestate = Gamestate.MENU;
		this.settings = new Settings();
		this.game = new Game(this);
		this.highscores = new Highscores();
		
//		this.height = this.game.getHeight() + 100;
//		this.width = this.game.getWidth();
		this.menu = new Menu(this.width, this.height);
		
		setPreferredSize(new Dimension(this.width, this.height));
		setFocusable(true);
		requestFocusInWindow();
		requestFocus();
		setBackground(new Color(103, 132, 4));
		
		addMouseListener(new MSListener());
		addKeyListener(new KYListener());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//activates antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		//paint menu, game, highscore or settings
		if(gamestate == Gamestate.GAME) {
			game.render(g2d);
		} else if(gamestate == Gamestate.MENU) {
			menu.render(g2d);
		} else if(gamestate == Gamestate.HIGHSCORES) {
			highscores.render(g2d);
		} else if(gamestate == Gamestate.SETTINGS) {
			settings.render(g2d);
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
					game.start();

				}
				//settings Button pressed
				if( mx > settingsBtn.getPosX() && mx < settingsBtn.getPosX() + settingsBtn.getWidht() &&
					my > settingsBtn.getPosY() && my < settingsBtn.getPosY() + settingsBtn.getHeight()
				) {
					gamestate = Gamestate.SETTINGS;
					repaint();
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
					repaint();
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
			
			if(gamestate == Gamestate.SETTINGS) {
//				g.drawString("AN", 250, 100);
//				g.drawString("AUS", 300, 100);
				
				//sets border collision to on or off
				if(	mx > 250 && mx < 290 &&
					my > 50 && my < 150) {
					settings.setBorderCollision(true);
					repaint();
				}
				if(	mx > 300 && mx < 340 &&
					my > 50 && my < 150) {
					settings.setBorderCollision(false);
					repaint();
				}
				
//				g.drawString("EASY", 250, 300);
//				g.drawString("MEDIUM", 350, 300);
//				g.drawString("HARD", 480, 300);
				if(	mx > 250 && mx < 330 &&
					my > 200 && my < 300) {
					settings.setDifficulty(Difficulty.EASY);
					repaint();
				}
				if(	mx > 350 && mx < 450 &&
					my > 200 && my < 300) {
					settings.setDifficulty(Difficulty.MEDIUM);
					repaint();
				}
				if(	mx > 480 && mx < 550 &&
					my > 200 && my < 300) {
					settings.setDifficulty(Difficulty.HARD);
					repaint();
				}
				
				
				//goes back to menu
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
	

	/**
	 *  @author hotzelm
	 *	KeyListeners to change the direction if the snake on keyboard input
	 */
	private class KYListener extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			Snake snake = game.getSnake();
			
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
				if(game.getIsPaused()) {
					game.unpause();
					repaint();
				} else {
					game.pause();
				}
				
			}
			
		}
	
		//changes the x- and y-speed of the snake, which is responsible for the direction the snake is moving
		@Override
		public void keyReleased(KeyEvent e) {
			
			 	
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

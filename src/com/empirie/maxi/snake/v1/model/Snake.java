package com.empirie.maxi.snake.v1.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake implements KeyListener {
	
	//in this list are all body parts of the snake stored
	private ArrayList<SnakeBodyPart> snake = new ArrayList<SnakeBodyPart>();
	private Board board = new Board();
	private Game game;
	
	public Snake(Game game) {
		this.game = game;
	}
	
	public void addSnakePart(SnakeBodyPart part) {
		snake.add(part);
	}
	
	public void move() {
		
		int posXBuffer = 0, posYBuffer = 0;
		int posXNow, posYNow;
		
		
		for(SnakeBodyPart part : snake) {
			if(game.getGameOver() == true) return;	
			
			posXNow = part.getPosX();
			posYNow = part.getPosY();
			
			if(part.getBodypart() == Bodypart.HEAD) {
				System.out.println("Head" + part.getCurrentDir());
				
				if(part.getCurrentDir() == Direction.UP) {
					if(posYNow - 1 < 0) {
						System.out.println("Game over" + "X");
						game.setGameOver(true);
						return;
					}
					part.setPosY(posYNow - 1);
				} 
				if(part.getCurrentDir() == Direction.DOWN) {
					if(posYNow + 1 > board.getRows()-1) {
						System.out.println("Game over");
						game.setGameOver(true);
						return;
					}
					part.setPosY(posYNow + 1);
				} 
				if(part.getCurrentDir() == Direction.LEFT) {
					if(posXNow - 1 < 0) {
						System.out.println("Game over");
						game.setGameOver(true);
						return;
					}
					part.setPosX(posXNow - 1);
				}
				if(part.getCurrentDir() == Direction.RIGHT) {
					if(posXNow + 1 > board.getColumns()-1) {
						System.out.println("Game over");
						game.setGameOver(true);
						return;
					}
					part.setPosX(posXNow + 1);
				}
				
			} else {
				System.out.println("Tail");
				
				part.setPosX(posXBuffer);
				part.setPosY(posYBuffer);
			}
			
			posXBuffer = posXNow;
			posYBuffer = posYNow;
			
			
			
			System.out.println("X : " + part.getPosX() + "\nY : " + part.getPosY());
		}
	}
	
	
	public void changeDirection(Direction dir) {
		snake.get(0).setCurrentDir(dir);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			snake.get(0).setCurrentDir(Direction.UP);
		} 
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.get(0).setCurrentDir(Direction.DOWN);
		} 
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.get(0).setCurrentDir(Direction.LEFT);
		} 
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			snake.get(0).setCurrentDir(Direction.RIGHT);
		} 
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

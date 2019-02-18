package com.empirie.maxi.snake.v2;

import java.util.ArrayList;

import com.empirie.maxi.snake.v1.model.Bodypart;
import com.empirie.maxi.snake.v1.model.Direction;


public class Snake{
	private ArrayList<SnakeBodyPart> snake = new ArrayList<SnakeBodyPart>();
	
	public Snake() {
		snake.add(new SnakeBodyPart(0, 0, Bodypart.HEAD));
	}
	
	public void addSnakePart() {
		
		int posXBuffer = 0, posYBuffer = 0;
		
		posXBuffer = snake.get(0).getPosX();
		posYBuffer = snake.get(0).getPosY();
		
		
		snake.add(new SnakeBodyPart(posXBuffer, posYBuffer, Bodypart.TAIL));
	}
	
	public ArrayList<SnakeBodyPart> getSnakePartList() {
		return snake;
	}
	
	
public void move() {
		
		int posXBuffer = 0, posYBuffer = 0;
		int posXNow, posYNow;
		
		
		for(SnakeBodyPart part : snake) {
			
			posXNow = part.getPosX();
			posYNow = part.getPosY();
			
			if(part.getBodypart() == Bodypart.HEAD) {
				//System.out.println("Head" + part.getCurrentDir());
				
				if(part.getCurrentDir() == Direction.UP) {					
					part.setPosY(posYNow - 1);
				} 
				if(part.getCurrentDir() == Direction.DOWN) {					
					part.setPosY(posYNow + 1);
				} 
				if(part.getCurrentDir() == Direction.LEFT) {
					part.setPosX(posXNow - 1);
				}
				if(part.getCurrentDir() == Direction.RIGHT) {
					part.setPosX(posXNow + 1);
				}
				
			} else {
				//System.out.println("Tail");
				
				part.setPosX(posXBuffer);
				part.setPosY(posYBuffer);
			}
			
			posXBuffer = posXNow;
			posYBuffer = posYNow;

			
			//System.out.println("X : " + part.getPosX() + "\nY : " + part.getPosY());
		}
	}
	
	
	public void changeDirection(Direction dir) {
		snake.get(0).setCurrentDir(dir);
	}
	
	
	
	

}

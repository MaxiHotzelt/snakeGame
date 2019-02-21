package com.empirie.maxi.snake.v4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Snake {
	
	private int xspeed = 0;
	private int yspeed = 1;
	
	private ArrayList<SnakeBodyPart> snakeList = new ArrayList<SnakeBodyPart>();
		
	//getter & setter
	public ArrayList<SnakeBodyPart> getSnakePartList() { return snakeList; }
	public int getXSpeed() { return xspeed; }
	public int getYSpeed() { return yspeed; }
	public void setXSpeed(int xspeed) { this.xspeed = xspeed; }
	public void setYSpeed(int yspeed) { this.yspeed = yspeed; }
	
	
	public Snake() {
		init();
	}		
	
	public void init() {
		snakeList.add(new SnakeBodyPart(0, 0));
		snakeList.add(new SnakeBodyPart(1, 0));
		xspeed = 0;
		yspeed = 1;
		
	}
	
	public void eat() {
		int posX = snakeList.get(snakeList.size()-1).getPosX();
		int posY = snakeList.get(snakeList.size()-1).getPosY();
		
		snakeList.add(new SnakeBodyPart(posX, posY));
	}
		
	public void move() {		
		SnakeBodyPart partBefore;
		
		for (int j = snakeList.size() - 1; j >= 0; j--) {
			SnakeBodyPart part = snakeList.get(j);
			
			
			if(snakeList.size() > 1 && j != 0) {
				partBefore = snakeList.get(j-1);
			} else {
				partBefore = snakeList.get(j);
			}
			
			
		    if(j == 0) {
		    	//sets the head of the snake to new position
		    	part.setPosX(snakeList.get(0).getPosX() + xspeed);	
				part.setPosY(snakeList.get(0).getPosY() + yspeed);
		    } else {
		    	//sets bodypart on the location of the predecessing bodypart
		    	part.setPosX(partBefore.getPosX());
		    	part.setPosY(partBefore.getPosY());
		    }
		}
	}
	
	
	public void render(Graphics g, int fieldwidth, int fieldheigth) {
		boolean firstPart = true;
		for(SnakeBodyPart part : snakeList) {
			if(firstPart) {
				g.setColor(Color.RED);	
				firstPart = false;
			} else {
				g.setColor(Color.GREEN);
			}
			g.fillRect(part.getPosX() * fieldwidth+1, part.getPosY() * fieldheigth+1, fieldwidth-1, fieldheigth-1);
		}
	}
		
}
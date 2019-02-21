package com.empirie.maxi.snake.v2;

import com.empirie.maxi.snake.v1.model.Bodypart;
import com.empirie.maxi.snake.v1.model.Direction;

public class SnakeBodyPart {
	private int posX;
	private int posY;
	
	private Bodypart part;
	private Direction currentDir = Direction.DOWN;
	
	public SnakeBodyPart(int posX, int posY, Bodypart part) {
		this.posX = posX;
		this.posY = posY;
		this.part = part;
		
	}
	
	public Bodypart getBodypart() {
		return part;
	}
	
	public void setBodypart(Bodypart part) {
		this.part = part;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosX(int x) {
		this.posX = x;
	}
	
	public void setPosY(int y) {
		this.posY = y;
	}
	
	public void setCurrentDir(Direction dir) {
		this.currentDir = dir;
	}
	
	public Direction getCurrentDir() {
		return currentDir;
	}

	
	
}

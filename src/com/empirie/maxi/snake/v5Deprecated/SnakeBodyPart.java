package com.empirie.maxi.snake.v5Deprecated;


public class SnakeBodyPart {
	private int posX;
	private int posY;
	
	
	public SnakeBodyPart(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		
	}
	
	//getter and setter
	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	
	public void setPosX(int x) { this.posX = x; }
	public void setPosY(int y) { this.posY = y; }

}

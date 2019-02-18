package com.empirie.maxi.snake.v2;


public class Food {

	private int posX;
	private int posY;
	
	public Food(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void relocate(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() { return posX;	}
	public int getPosY() { return posY; }
	
}

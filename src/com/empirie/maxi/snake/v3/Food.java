package com.empirie.maxi.snake.v3;

public class Food {

	private int posX;
	private int posY;
	
	public Food() {
	}
	
	public void relocate(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() { return posX;	}
	public int getPosY() { return posY; }
	
}

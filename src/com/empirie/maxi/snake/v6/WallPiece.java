package com.empirie.maxi.snake.v6;

public class WallPiece {
	private int posX;
	private int posY;
	
	public WallPiece(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		
	}
	
	//getter and setter
	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	
	public void setPosX(int x) { this.posX = x; }
	public void setPosY(int y) { this.posY = y; }
}

package com.empirie.maxi.snake.v1.model;


public class Game{
	
	private boolean gameOver;
	private int movespeed = 2000;
	
	public Game() {
		this.gameOver = false;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public boolean getGameOver() {
		return this.gameOver;
	}
	public int getMovespeed() {
		return this.movespeed;
	}
	
}

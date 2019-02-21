package com.empirie.maxi.snake.v4;

public class Settings {
	
	//settings
	private boolean borderCollision;
	private Difficulty difficulty;
	
	//default settings
	private final boolean DEFAULT_BORDER_COLLISION = true;
	private final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM; 
	
	public Settings() {
		//sets default settings
		this.borderCollision = this.DEFAULT_BORDER_COLLISION;
		this.difficulty = this.DEFAULT_DIFFICULTY;
	}
	
	//getter & setter
	public boolean getBorderCollision() { return borderCollision; }
	public boolean getDefaultBorderCollision() { return DEFAULT_BORDER_COLLISION; }
	public Difficulty getDifficulty() { return difficulty; }
	public Difficulty getDefaultDifficulty() { return DEFAULT_DIFFICULTY; }

	public void setBorderCollision(boolean borderCollision) { this.borderCollision = borderCollision; }
	public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
	
}

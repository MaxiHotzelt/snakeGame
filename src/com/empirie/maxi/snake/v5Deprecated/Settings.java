package com.empirie.maxi.snake.v5Deprecated;

public class Settings {
	
	//settings
	private boolean isBorderCollisionActive;
	private Difficulty difficulty;
	
	//default settings
	private final boolean DEFAULT_IS_BORDER_COLLISION_ACTIVE = true;
	private final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM; 
	
	public Settings() {
		//sets default settings
		this.isBorderCollisionActive = this.DEFAULT_IS_BORDER_COLLISION_ACTIVE;
		this.difficulty = this.DEFAULT_DIFFICULTY;
	}
	
	//getter & setter
	public boolean getIsBorderCollisionActive() { return isBorderCollisionActive; }
	public boolean getIsDefaultBorderCollisionActive() { return DEFAULT_IS_BORDER_COLLISION_ACTIVE; }
	public Difficulty getDifficulty() { return difficulty; }
	public Difficulty getDefaultDifficulty() { return DEFAULT_DIFFICULTY; }

	public void setBorderCollision(boolean borderCollision) { this.isBorderCollisionActive = borderCollision; }
	public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
	
}

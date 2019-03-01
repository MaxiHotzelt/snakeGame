package com.empirie.maxi.snake.v6;

public enum Difficulty {
		
	
	EASY(150), MEDIUM(100), HARD(70);
	
	
	private int updatespeed;
	
	Difficulty(int updatespeed) {
		this.updatespeed = updatespeed;
	}
	
	
	public int getUpdatespeed() { return updatespeed; }
}

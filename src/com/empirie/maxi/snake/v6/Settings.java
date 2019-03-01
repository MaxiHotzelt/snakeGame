package com.empirie.maxi.snake.v6;

import java.awt.Font;
import java.awt.Graphics;

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
	
	
	public void render(Graphics g) {
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		
		g.drawString("Wandkollision", 10, 100);
		g.drawString("AN", 250, 100);
		g.drawString("AUS", 300, 100);
		if(isBorderCollisionActive) {
			g.drawLine(250, 100, 280, 100);
		} else {
			g.drawLine(300, 100, 350, 100);
		}
		
		g.drawString("Schwierigkeit", 10, 300);
		g.drawString("EASY", 250, 300);
		g.drawString("MEDIUM", 350, 300);
		g.drawString("HARD", 480, 300);
		if(difficulty == Difficulty.EASY) {
			g.drawLine(250, 300, 310, 300);
		} else if(difficulty == Difficulty.MEDIUM) {
			g.drawLine(350, 300, 450, 300);
		} else {
			g.drawLine(480, 300, 550, 300);
		}
		
		
		g.drawString("BACK", 200, 600);
	}
}

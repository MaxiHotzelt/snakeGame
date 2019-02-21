package com.empirie.maxi.snake.v4;

import java.awt.Color;
import java.awt.Graphics;

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
	
	public void render(Graphics g, int fieldwidth, int fieldheight) {
		g.setColor(Color.ORANGE);
		g.fillOval(posX * fieldwidth, posY * fieldheight, fieldwidth, fieldheight);
	}
	
}

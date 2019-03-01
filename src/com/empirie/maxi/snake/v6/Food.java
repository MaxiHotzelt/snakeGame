package com.empirie.maxi.snake.v6;

import java.awt.Graphics;
import java.awt.Image;


public class Food {

	protected int posX;
	protected int posY;
	protected int value;
	
	protected Game game;
	
	protected Image foodSprite;
	
	public Food(Game game) {
		this.value = 1;
		this.game = game;
		
		this.foodSprite = game.getContainer().getUtils().loadImage("food.png");
	}
	
	public void relocate(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() { return this.posX;	}
	public int getPosY() { return this.posY; }
	public int getValue() { return this.value; }
	
	public void render(Graphics g, int fieldwidth, int fieldheight) {
		g.drawImage(foodSprite, this.posX * fieldwidth+1, this.posY * fieldheight+1,
				fieldwidth-1, fieldheight-1, null);
	}
	
}

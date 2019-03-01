package com.empirie.maxi.snake.v6;

import java.awt.Graphics;
import java.util.Random;


public class Badfood extends Specialfood{
	public Badfood(Game game) {
		super(game);
		
		this.value = -3;
		this.foodSprite = game.getContainer().getUtils().loadImage("bomb.png");
	}
	
	public void show() {
		boolean suchePos = true;
		int foodY = 0, foodX = 0;
		Random ran = new Random();
		Food food = game.getFood();
		Superfood sfood = game.getSuperfood();
		
		//checks if snake is on position of food
		while(suchePos) {
			suchePos = false;
			foodY = ran.nextInt(game.getRows());
			foodX = ran.nextInt(game.getColumns());
			for(SnakeBodyPart part : game.getSnake().getSnakePartList()) {
				if(part.getPosX() == foodX && part.getPosY() == foodY) {
					suchePos = true;
					break;
				}
			}
			
			for(WallPiece wall : game.getWalls()) {
				if(wall.getPosX() == foodX && wall.getPosY() == foodY) {
					suchePos = true;
					break;
				}
			}
			
			if ((food.getPosX() == foodX && food.getPosY() == foodY) ||
				(sfood.getPosX() == foodX && sfood.getPosY() == foodY)) 
			{
				suchePos = true;
			}
			
			
			
		}			
		relocate(foodX, foodY);	
	}
	
	
	public void render(Graphics g, int fieldwidth, int fieldheight) {
		g.drawImage(foodSprite, this.posX * fieldwidth+1, this.posY * fieldheight+1,
			fieldwidth-1, fieldheight-1, null);
	}
}

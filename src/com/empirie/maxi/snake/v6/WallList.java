package com.empirie.maxi.snake.v6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class WallList extends ArrayList<WallPiece>{
	
	private Game game;
	private Image wallImg;
	
	public WallList(Game game) {
		add(new WallPiece(5, 5));
		
		this.game = game;
		wallImg = game.getContainer().getUtils().loadImage("wall1.jpg");
	}
	
	public void init() {
		
	}
	
	public void addWallPiece(int posX, int posY) {
		add(new WallPiece(posX, posY));
	}
	
	public void render(Graphics g) {
		
		for(WallPiece wall : this) {
			g.setColor(Color.BLACK);
			g.drawImage(wallImg, wall.getPosX() * game.getFieldwidth()+1, wall.getPosY() * game.getFieldheight()+1,
					game.getFieldwidth()-1, game.getFieldheight()-1, null);

		}
		
	}
	
}

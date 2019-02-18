package com.empirie.maxi.snake.v1.main;

import com.empirie.maxi.snake.v1.model.Bodypart;
import com.empirie.maxi.snake.v1.model.Game;
import com.empirie.maxi.snake.v1.model.Snake;
import com.empirie.maxi.snake.v1.model.SnakeBodyPart;

public class Main{
	
	public static void main(String[] args) {
		Game game = new Game();
			
		SnakeBodyPart head = new SnakeBodyPart(0, 0, Bodypart.HEAD);
		SnakeBodyPart tail = new SnakeBodyPart(1, 0, Bodypart.TAIL);
		SnakeBodyPart tail2 = new SnakeBodyPart(2, 0, Bodypart.TAIL);
		
		Snake snake = new Snake(game);
		snake.addSnakePart(head);
		snake.addSnakePart(tail);
		snake.addSnakePart(tail2);
		
		while(!game.getGameOver()) {
			snake.move();
			try {
				Thread.sleep(game.getMovespeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}

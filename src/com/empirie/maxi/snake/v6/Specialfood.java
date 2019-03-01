package com.empirie.maxi.snake.v6;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Specialfood extends Food implements Runnable{
	
	protected int respawntime;
	protected int lifetime;
	protected boolean running;
	
	
	//getter & setter
	public int getRespawntime() { return this.respawntime; }
	public int getLifetime() { return this.lifetime; }
	
	public void setRunning(boolean run) { this.running = run; }
	
	
	public Specialfood(Game game) {
		super(game);
		//hide prevents food to get drawn on screen immediately
		hide();
		this.respawntime = 500;
		this.lifetime = 10000;
		this.running = false;
		
		this.game = game;
	}
	
	public void render(Graphics g, int fieldwidth, int fieldheight) {
		g.setColor(Color.RED);
		g.fillOval(posX * fieldwidth, posY * fieldheight, fieldwidth, fieldheight);
	}
	
	public void hide() {
		this.posX = -999;
		this.posY = -999;
	}
	
	public void show() {
		
	}
	
	public void run() {
		Random rand = new Random();
		
		while(running) {
			respawntime = (rand.nextInt(10) + 11)*1000;
			lifetime = (rand.nextInt(5) + 6)*1000;
			
			
			if(!running) break;
			
			hide();
			try {
				Thread.sleep(respawntime);
			} catch (InterruptedException e) {
//				e.printStackTrace();
			}
			
			if(!running) break;
			
	
			show();
			try {
				Thread.sleep(lifetime);
			} catch (InterruptedException e) {
//				e.printStackTrace();
			}

			
		}
	}
}

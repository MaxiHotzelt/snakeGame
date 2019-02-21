package com.empirie.maxi.snake.v4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu implements ImageObserver {
	
	private final int btnWidth = 200;
	private final int btnHeight = 50;
	
	private Image backgroundImage;
	private Button playBtn;
	private Button settingsBtn;
	private Button scoreBtn;
	private Button quitBtn;
	private int width;
	private int height;
	
	//getter
	public Button getPlayBtn() { return playBtn; }
	public Button getSettingsBtn() { return settingsBtn; }
	public Button getScoreBtn() { return scoreBtn; }
	public Button getQuitBtn() { return quitBtn; }
	public Image getBackgroundImage() { return backgroundImage; }
	
	public static Color BLUE = new Color(32, 46, 23); 
	
	public Menu(int width, int height) {
		this.width = width;
		this.height = height;
		try {
			backgroundImage = ImageIO.read(new File("C:\\webdevelopment_documents\\frontend\\bilder\\snakeWallpaper.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//create buttons and pass their configuration
		playBtn = new Button(btnWidth,btnHeight,
						this.width/4 - btnWidth/2,
						backgroundImage.getHeight(this) + (this.height - backgroundImage.getHeight(this))/4 - btnHeight/2, 
						new Color(32, 46, 23), new Color(156, 204, 4), new Color(32, 46, 23),
						"PLAY");
		settingsBtn = new Button(btnWidth, btnHeight,
				
						(this.width/2 + this.width/4) - btnWidth/2, 
						backgroundImage.getHeight(this) + (this.height - backgroundImage.getHeight(this))/4 - btnHeight/2, 
						new Color(32, 46, 23), new Color(156, 204, 4), new Color(32, 46, 23),
						"SETTINGS");
		scoreBtn = new Button(btnWidth, btnHeight,
						this.width/4 - btnWidth/2, 
						backgroundImage.getHeight(this) + (this.height - backgroundImage.getHeight(this))/2 + (this.height - backgroundImage.getHeight(this))/4 - btnHeight/2, 
						new Color(32, 46, 23), new Color(156, 204, 4), new Color(32, 46, 23),
						"SCOREBOARD");
		quitBtn = new Button(btnWidth, btnHeight,
						(this.width/2 + this.width/4) - btnWidth/2,
						backgroundImage.getHeight(this) + (this.height - backgroundImage.getHeight(this))/2 + (this.height - backgroundImage.getHeight(this))/4 - btnHeight/2,
						new Color(32, 46, 23), new Color(156, 204, 4), new Color(32, 46, 23),
						"QUIT");
	}

	
	public void render(Graphics g, int width, int height) {
		//backgroundimage 
		g.drawImage(backgroundImage, width/2 - backgroundImage.getWidth(this)/2, 0, this);
		//draw buttons
		playBtn.render(g);
		settingsBtn.render(g);
		scoreBtn.render(g);
		quitBtn.render(g);

	}
	
		@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
		
		
		
		
		
		
		
		
		
	public class Button implements ImageObserver {
		private int height;
		private int width;
		private int posX;
		private int posY;
		private String text;
		
		private Color borderColor;
		private Color btnColor;
		private Color fontColor;
		
		//getter
		public int getHeight() { return this.height; }
		public int getWidht() { return this.width; }
		public int getPosX() { return this.posX; }
		public int getPosY() { return this.posY; }
		
		public Button(int width, int height, int posX, int posY,
				Color borderColor, Color btnColor, Color fontColor, String text) {
			this.height = height;
			this.width = width;
			this.posX = posX;
			this.posY = posY;
			this.borderColor = borderColor;
			this.btnColor = btnColor;
			this.fontColor = fontColor;
			this.text = text;
		}
		
		public void render(Graphics g) {
			//play btn border
			g.setColor(borderColor);
			g.drawRect(this.posX, this.posY, this.width, this.height);
			
			//play button
			g.setColor(btnColor);
			g.fillRect(this.posX + 1, this.posY + 1, this.width-1, this.height-1);
			
			//button text
			g.setColor(fontColor);
			g.setFont(new Font("TimesRoman", Font.BOLD, 25));
			int stringWidth = g.getFontMetrics(new Font("TimesRoman", Font.BOLD, 25)).stringWidth(this.text);
			g.drawString(this.text, this.posX + width/2 - stringWidth/2, this.posY + 35);
		}

		@Override
		public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

	
}

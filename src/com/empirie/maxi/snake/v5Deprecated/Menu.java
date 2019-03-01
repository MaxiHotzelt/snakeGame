package com.empirie.maxi.snake.v5Deprecated;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener{
	
	private Game game;
	private Image backgroundImage;
	private JButton gameBtn;
	
	public Menu(Game game) {
		this.game = game;
		
		this.gameBtn = new JButton("GAME");
		this.gameBtn.addActionListener(this);
		
		try {
			backgroundImage = ImageIO.read(new File("C:\\webdevelopment_documents\\frontend\\bilder\\snakeWallpaper.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setSize(new Dimension(800, 800));
		setOpaque(true);
		setBackground(new Color(103, 132, 4));
		add(gameBtn);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		//backgroundimage 
		g.drawImage(backgroundImage, this.getWidth()/2 - backgroundImage.getWidth(this)/2, 0, this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.gameBtn) {
			game.changePanel(Gamestate.GAME);
		}
	}
	
}

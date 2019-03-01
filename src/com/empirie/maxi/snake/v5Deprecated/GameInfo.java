package com.empirie.maxi.snake.v5Deprecated;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameInfo extends JPanel {
	
	private int score = 0;
	private JLabel lblScore2;
	
	//getter & setter
	public int getScore() { return this.score; }
	public void addPoint() { 
		this.score += 1; 
		lblScore2.setText("" + score);
	}
	public void resetScore() {
		this.score = 0;
		lblScore2.setText("" + score);
	}
	
	public GameInfo() {
		setBackground(new Color(139, 179, 3));
		setOpaque(true);
		setPreferredSize(new Dimension(300, HEIGHT));
		
		JLabel lblScore = new JLabel();
		lblScore.setText("Score: ");
		lblScore2 = new JLabel();
		lblScore2.setText("" + score);
		
		add(lblScore);
		add(lblScore2);
	}
}

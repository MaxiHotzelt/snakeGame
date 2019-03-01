package com.empirie.maxi.snake.v6;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Highscores extends ArrayList<Integer>{
	
	//amount of scores that are saved in the list 
	//you have to be in the top 20 to have your score be saved
	private final int MAXSCORES = 20;
	
	
	private String csvFile  = "E:\\develop\\snake\\src\\com\\empirie\\maxi\\snake\\v6\\highscores.csv";
	private PrintWriter writer;
	

	
	
	public Highscores() {
		loadScores();
	}
	
	public void addScore(int score) {
		add(score);
		Collections.sort(this, Collections.reverseOrder());
		
		if(this.size() > MAXSCORES) {
			this.remove(this.size()-1);
		}
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		for(int i = 0; i < this.size(); i++) {	
			g.drawString(i+1 + ".", 100, i*25 + 100);
			g.drawString("" + this.get(i), 150, i*25 + 100);
		}
		 
		 
		g.drawString("BACK", 200, 600);
	}
	
	public void saveScores() {
		try {
			OutputStream os = new FileOutputStream(csvFile);
			os.write(239);
		    os.write(187);
		    os.write(191);
		    
		    try {
		    	writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		    } catch (UnsupportedEncodingException e) {
				System.out.println("Das gewünschte Encoding konnte nicht gefunden werden.");
				writer = new PrintWriter(new OutputStreamWriter(os));
				
			} 
		} catch (FileNotFoundException e) {
			System.out.println("Datei konnte nicht erstellt werden.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Integer scores : this) {
			writer.print(scores + ";");
		}
		writer.flush();
		writer.close();
	}
	
	public void loadScores() {
		Scanner sc;
		try {
			sc = new Scanner(new File(csvFile));
			sc.useDelimiter(";");
			while(sc.hasNext()){
				String score = sc.next().replaceAll("\\D", "");
				if(score.length() > 0) {
					add(Integer.parseInt(score));
				}
				
			}
			// after loop, close scanner
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

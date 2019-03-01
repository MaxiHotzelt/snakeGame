package com.empirie.maxi.snake.v6;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	private Font font;
	
	public Utils() {
		loadFont();
	}
	
	public Font getFont() { return font; }
	
	public void loadFont() {
		String fontPath = "E:\\develop\\snake\\src\\com\\empirie\\maxi\\snake\\v6\\resources\\fonts\\8BIT.ttf";
		try {
		    //create the font to use. Specify the size!
		    Font customFont8Bit = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(48f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont8Bit);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		font = new Font("8BIT WONDER", Font.BOLD, 25);
//		font = new Font("Eight-Bit Madness", Font.BOLD, 40);
	}
	
	public Image loadImage(String path) {
		
		Image img;

		try {
			img = ImageIO.read(new File("src/com/empirie/maxi/snake/v6/resources/images/" + path));
		} catch (IOException e1) {
			e1.printStackTrace();
			
			return null;
		}
		
		return img;
	}
}

package com.empirie.maxi.snake.v5Deprecated;

import java.awt.Color;

public enum ColorEnum {
	BLUE(new Color(32, 46, 23));
	Color color;
	
	ColorEnum(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}

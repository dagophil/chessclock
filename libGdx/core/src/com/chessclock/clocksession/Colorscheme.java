package com.chessclock.clocksession;

import com.badlogic.gdx.graphics.Color;

// TODO: Is bgPressed needed?
public class Colorscheme {
	
	public static Colorscheme getWhiteColorscheme() {
		Colorscheme colors = new Colorscheme();
		colors.bgActive = Color.WHITE;
		colors.bgInactive = Color.valueOf("bbbbbb");
		colors.bgPressed = Color.valueOf("dddddd");
		return colors;
	}
	
	public static Colorscheme getBlackColorscheme() {
		Colorscheme colors = new Colorscheme();
		colors.bgActive = Color.BLACK;
		colors.bgInactive = Color.valueOf("222222");
		colors.bgPressed = Color.valueOf("444444");
		return colors;
	}

	public Color bgActive;
	public Color bgInactive;
	public Color bgPressed;
	
}

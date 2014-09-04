package com.chessclock.clocksession;

import com.badlogic.gdx.graphics.Color;

public class Colorscheme {
	
	public static Colorscheme getWhiteColorscheme() {
		Colorscheme colors = new Colorscheme();
		colors.bgActive = Color.WHITE;
		colors.bgInactive = Color.valueOf("bbbbbb");
		return colors;
	}
	
	public static Colorscheme getBlackColorscheme() {
		Colorscheme colors = new Colorscheme();
		colors.bgActive = Color.BLACK;
		colors.bgInactive = Color.valueOf("222222");
		return colors;
	}

	public Color bgActive;
	public Color bgInactive;
	
}

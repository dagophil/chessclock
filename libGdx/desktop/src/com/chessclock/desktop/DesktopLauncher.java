package com.chessclock.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.chessclock.ChessclockGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Chessclock";
		config.width = 240;
		config.height = 400;
		new LwjglApplication(new ChessclockGame(), config);
	}
}

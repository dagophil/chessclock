package com.chessclock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.screens.ClockScreen;


public class ChessclockGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("ChessclockGame", "created");
		AssetLoader.load();
		
		// Default time: 90s
		setScreen(new ClockScreen(90, 90));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}

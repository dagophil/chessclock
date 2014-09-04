package com.chessclock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.screens.ClockScreen;
import com.chessclock.screens.MenuScreen;


public class ChessclockGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("ChessclockGame", "created");
		AssetLoader.load();
		Gdx.input.setCatchBackKey(true);
		setScreen(new MenuScreen(this));
	}
	
	public void startClock(float timePlayer1, float timePlayer2) {
		Gdx.app.log("ChessclockGame", "startClock called");
		setScreen(new ClockScreen(timePlayer1, timePlayer2));
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	

}

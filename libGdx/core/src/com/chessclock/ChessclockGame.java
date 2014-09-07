package com.chessclock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.screens.ClockScreen;
import com.chessclock.screens.MenuScreen;


public class ChessclockGame extends Game {
	
	private boolean m_assetsLoaded;
	
	@Override
	public void create() {
		Gdx.app.log("ChessclockGame", "created");
		Gdx.input.setCatchBackKey(true);
		
		// TODO: Show loading screen here.
		
		m_assetsLoaded = false;
		AssetLoader.load();
	}
	
	public void startClock(float timePlayer1, float timePlayer2) {
		Gdx.app.log("ChessclockGame", "startClock called");
		setScreen(new ClockScreen(timePlayer1, timePlayer2));
	}
	
	@Override
	public void render () {
		if (!m_assetsLoaded && AssetLoader.update()) {
			Gdx.app.log("ChessclockGame", "assets loaded");
			m_assetsLoaded = true;
			setScreen(new MenuScreen(this));
		}
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
}

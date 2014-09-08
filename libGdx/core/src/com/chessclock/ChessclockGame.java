package com.chessclock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.screens.AboutScreen;
import com.chessclock.screens.ClockScreen;
import com.chessclock.screens.MenuScreen;
import com.chessclock.screens.SplashScreen;


public class ChessclockGame extends Game {
	
	private boolean m_assetsLoaded;
	
	@Override
	public void create() {
		Gdx.app.log("ChessclockGame", "created");
		Gdx.input.setCatchBackKey(true);
		
		setScreen(new SplashScreen(this));
		
		m_assetsLoaded = false;
		AssetLoader.load();
	}

	public void backToMenu() {
		Gdx.app.log("ChessclockGame", "backToMenu called");
		setScreen(new MenuScreen(this, true));
	}
	
	public void startClock(float timePlayer1, float timePlayer2) {
		Gdx.app.log("ChessclockGame", "startClock called");
		setScreen(new ClockScreen(this, timePlayer1, timePlayer2));
	}
	
	public void showAbout() {
		Gdx.app.log("ChessclockGame", "showAbout called");
		setScreen(new AboutScreen(this));
	}
	
	@Override
	public void render () {
		Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!m_assetsLoaded && AssetLoader.update()) {
			Gdx.app.log("ChessclockGame", "assets loaded");
			m_assetsLoaded = true;
			SplashScreen splash = (SplashScreen) this.getScreen();
			splash.fadeOutAndShowMenu();
		}
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
}

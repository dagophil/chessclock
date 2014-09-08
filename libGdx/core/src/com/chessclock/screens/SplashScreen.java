package com.chessclock.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.chessclock.ChessclockGame;
import com.chessclock.splashsession.SplashSession;

public class SplashScreen implements Screen {

	private SplashSession m_session;
	
	public SplashScreen(ChessclockGame game) {
		m_session = new SplashSession(game, new FitViewport(240, 400));
	}
	
	public void fadeOutAndShowMenu() {
		m_session.fadeOutAndShowMenu();
	}
	
	@Override
	public void render(float delta) {
		m_session.act(delta);
		m_session.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

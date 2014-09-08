package com.chessclock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.chessclock.ChessclockGame;
import com.chessclock.aboutsession.AboutSession;

public class AboutScreen implements Screen {

	private AboutSession m_session;
	
	public AboutScreen(ChessclockGame game) {
		m_session = new AboutSession(game, new FitViewport(240, 400));
		Gdx.input.setInputProcessor(m_session);
	}
	
	@Override
	public void render(float delta) {
		m_session.update(delta);
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

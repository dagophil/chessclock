package com.chessclock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.chessclock.clocksession.ClockSession;

public class ClockScreen implements Screen {

	private ClockSession m_session;
	
	public ClockScreen(float timePlayer1, float timePlayer2) {
		m_session = new ClockSession(new FitViewport(240, 400), timePlayer1, timePlayer2);
		Gdx.input.setInputProcessor(m_session);
	}
	
	@Override
	public void render(float delta) {
		m_session.update(delta);
		m_session.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("ClockScreen", "resizing");
		m_session.getViewport().update(width, height);
	}

	@Override
	public void show() {
		Gdx.app.log("ClockScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("ClockScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("ClockScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("ClockScreen", "resume called");
	}

	@Override
	public void dispose() {
	}

}

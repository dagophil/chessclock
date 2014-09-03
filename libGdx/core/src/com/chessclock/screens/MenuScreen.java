package com.chessclock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.chessclock.ChessclockGame;
import com.chessclock.menusession.MenuSession;

public class MenuScreen implements Screen {

	private MenuSession m_session;
	
	public MenuScreen(ChessclockGame game) {
		m_session = new MenuSession(game, new FitViewport(240, 400));
		Gdx.input.setInputProcessor(m_session);
	}
	
	@Override
	public void render(float delta) {
		m_session.update(delta);
		m_session.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("MenuScreen", "resizing");
	}

	@Override
	public void show() {
		Gdx.app.log("MenuScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("MenuScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("MenuScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("MenuScreen", "resume called");
	}

	@Override
	public void dispose() {
		Gdx.app.log("MenuScreen", "dispose called");
	}

}

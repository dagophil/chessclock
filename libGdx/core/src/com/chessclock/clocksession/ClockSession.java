package com.chessclock.clocksession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.helpers.AssetLoader;

public class ClockSession extends Stage {
	
	private PlayerGroup m_grpWhite;
	private PlayerGroup m_grpBlack;
	private Button m_btnPause;
	private Overlay m_grpResume;
	private boolean m_isPaused;
	private float m_timePlayer1; // seconds
	private float m_timePlayer2; // seconds
	private boolean m_isWhitesTurn;
	
	public ClockSession(Viewport viewport, float timePlayer1, float timePlayer2) {
		super(viewport);
		
		m_timePlayer1 = timePlayer1;
		m_timePlayer2 = timePlayer2;
		m_isPaused = false;
		m_isWhitesTurn = true;
		createGroups();
	}

	public float getTimePlayer1() {
		return m_timePlayer1;
	}
	
	public float getTimePlayer2() {
		return m_timePlayer2;
	}
	
	public boolean isWhitesTurn() {
		return m_isWhitesTurn;
	}
	
	private void createGroups() {		
		// Create white button
		m_grpWhite = new PlayerGroup(this, true, Colorscheme.getWhiteColorscheme());
		this.addActor(m_grpWhite);

		// Create black button
		m_grpBlack = new PlayerGroup(this, false, Colorscheme.getBlackColorscheme());
		m_grpBlack.rotateBy(180);
		m_grpBlack.moveBy(m_grpBlack.getWidth(), 2*m_grpBlack.getHeight());
		this.addActor(m_grpBlack);
		
		// Create pause button
		ButtonStyle stylePause = new ButtonStyle();
		stylePause.up = AssetLoader.getDrawable(Color.RED);
		m_btnPause = new Button(stylePause);
		m_btnPause.setWidth(this.getViewport().getWorldWidth()/5);
		m_btnPause.setHeight(this.getViewport().getWorldWidth()/5);
		m_btnPause.setX(2*this.getViewport().getWorldWidth()/5);
		m_btnPause.setY(this.getViewport().getWorldHeight()/2-this.getViewport().getWorldWidth()/10);
		m_btnPause.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				ClockSession session = (ClockSession) event.getStage();
				session.pause();
				return true;
			}
		});
		this.addActor(m_btnPause);
		
		// Create resume button
		m_grpResume = new Overlay(this, "Click to resume");
		m_grpResume.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				ClockSession session = (ClockSession) event.getStage();
				session.resume();
				return false;
			}
		});
		this.addActor(m_grpResume);
	}
	
	public void changePlayers() {
		m_isWhitesTurn = !m_isWhitesTurn;
	}
	
	public void pause() {
		m_isPaused = true;
		m_grpResume.show();
	}
	
	public void resume() {
		m_isPaused = false;
		m_grpResume.hide();
	}
	
	public void update(float delta) {
		if (!m_isPaused) {
			if (m_isWhitesTurn) {
				m_timePlayer1 -= delta;
			} else {
				m_timePlayer2 -= delta;
			}
			if (m_timePlayer1 <= 0) {
				m_timePlayer1 = 0;
				// TODO: Player 2 wins here.
				
			}
			if (m_timePlayer2 <= 0) {
				m_timePlayer2 = 0;
				// TODO: Player 1 wins here.
				
			}
		}
		this.act(delta);
	}
	
	@Override
	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.draw();
	}

}

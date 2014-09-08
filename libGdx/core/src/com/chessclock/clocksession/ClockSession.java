package com.chessclock.clocksession;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.helpers.Overlay;

public class ClockSession extends Stage {
	
	public static int DEFAULT_PAUSE_WIDTH = 50;
	public static int DEFAULT_PAUSE_HEIGHT = 50;
	public static String RESUME_TEXT = "Click to\nresume";
	
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
		stylePause.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_PAUSE, Texture.class)));
		m_btnPause = new Button(stylePause);
		m_btnPause.setWidth(DEFAULT_PAUSE_WIDTH);
		m_btnPause.setHeight(DEFAULT_PAUSE_HEIGHT);
		m_btnPause.setX((this.getWidth() - DEFAULT_PAUSE_WIDTH) / 2);
		m_btnPause.setY((this.getHeight() - DEFAULT_PAUSE_HEIGHT) / 2);
		m_btnPause.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				pause();
				return true;
			}
		});
		this.addActor(m_btnPause);
		
		// Create resume overlay
		m_grpResume = new Overlay(this.getWidth(), this.getHeight(), RESUME_TEXT);
		m_grpResume.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				resume();
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
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK){
			pause();
			return true;
		}
		return false;
	}

}

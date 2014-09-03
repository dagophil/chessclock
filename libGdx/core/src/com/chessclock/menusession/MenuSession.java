package com.chessclock.menusession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.ChessclockGame;
import com.chessclock.helpers.AssetLoader;

public class MenuSession extends Stage {

	public static final float DEFAULT_PLAYER_TIME = 90;
	public static final float DEFAULT_X = 26;
	public static final float DEFAULT_Y = 130;
	private ChessclockGame m_game;
	private Button m_btnStartClock;
	private ClockInput m_player1Input;
	private ClockInput m_player2Input;
	
	public MenuSession(ChessclockGame game, Viewport viewport) {
		super(viewport);
		
		m_game = game;
		
		// Create start button
		ButtonStyle styleStart = new ButtonStyle();
		styleStart.up = AssetLoader.getDrawable(Color.GREEN);
		styleStart.down = AssetLoader.getDrawable(Color.valueOf("009900"));
		m_btnStartClock = new Button(styleStart);
		m_btnStartClock.setWidth(this.getWidth()/5);
		m_btnStartClock.setHeight(this.getWidth()/5);
		m_btnStartClock.setX(4*this.getWidth()/5-15);
		m_btnStartClock.setY(15);
		m_btnStartClock.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				startClock();
			}
		});
		this.addActor(m_btnStartClock);
		
		
		// TODO: Read default time from saved data.
		m_player1Input = new ClockInput(DEFAULT_PLAYER_TIME);
		m_player1Input.setX(DEFAULT_X);
		m_player1Input.setY(DEFAULT_Y);
		this.addActor(m_player1Input);
		
		// TODO: Read default time from saved data.
		m_player2Input = new ClockInput(DEFAULT_PLAYER_TIME);
		m_player2Input.setX(DEFAULT_X);
		m_player2Input.setY(DEFAULT_Y);
		m_player2Input.setVisible(false);
		this.addActor(m_player2Input);
	}
	
	public void startClock() {
		m_game.startClock(m_player1Input.getPlayerTime(), m_player2Input.getPlayerTime());
	}
	
	public void update(float delta) {
		this.act(delta);
	}
	
	@Override
	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.draw();
	}
	
}

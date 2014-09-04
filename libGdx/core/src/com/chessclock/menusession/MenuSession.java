package com.chessclock.menusession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	public static final float ONE_CLOCK_Y = 130;
	public static final float TWO_CLOCKS_Y_PLAYER1 = 250;
	public static final float TWO_CLOCKS_Y_PLAYER2 = 95;
	public static final float BTN_WIDTH = 50;
	public static final float BTN_HEIGHT = 50;
	
	private ChessclockGame m_game;
	private Button m_btnStartClock;
	private Button m_btnTwoClocks;
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
		m_btnStartClock.setWidth(BTN_WIDTH);
		m_btnStartClock.setHeight(BTN_HEIGHT);
		m_btnStartClock.setX(this.getWidth() - BTN_WIDTH - 15);
		m_btnStartClock.setY(15);
		m_btnStartClock.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				startClock();
			}
		});
		this.addActor(m_btnStartClock);
		
		// Create a button to toggle one clock / two clocks
		ButtonStyle styleTwoClocks = new ButtonStyle();
		styleTwoClocks.up = AssetLoader.getDrawable(Color.BLUE);
		styleTwoClocks.down = AssetLoader.getDrawable(Color.valueOf("000099"));
		m_btnTwoClocks = new Button(styleTwoClocks);
		m_btnTwoClocks.setWidth(BTN_WIDTH);
		m_btnTwoClocks.setHeight(BTN_HEIGHT);
		m_btnTwoClocks.setX(15);
		m_btnTwoClocks.setY(15);
		m_btnTwoClocks.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				toggleTwoClocks();
			}
		});
		this.addActor(m_btnTwoClocks);
		
		// TODO: Read default time from saved data.
		m_player1Input = new ClockInput(DEFAULT_PLAYER_TIME);
		m_player1Input.setX(DEFAULT_X);
		m_player1Input.setY(ONE_CLOCK_Y);
		this.addActor(m_player1Input);
		
		// TODO: Read default time from saved data.
		m_player2Input = new ClockInput(DEFAULT_PLAYER_TIME);
		m_player2Input.setX(DEFAULT_X);
		m_player2Input.setY(ONE_CLOCK_Y);
		m_player2Input.setVisible(false);
		this.addActor(m_player2Input);
	}
	
	public void toggleTwoClocks() {
		if (m_player2Input.isVisible()) {
			m_player2Input.setVisible(false);
			m_player1Input.setY(ONE_CLOCK_Y);
		} else {
			m_player2Input.setTime(m_player1Input.getTime());
			m_player2Input.setVisible(true);
			m_player1Input.setY(TWO_CLOCKS_Y_PLAYER1);
			m_player2Input.setY(TWO_CLOCKS_Y_PLAYER2);
		}
	}
	
	public void startClock() {
		if (!m_player2Input.isVisible()) {
			m_player2Input.setTime(m_player1Input.getTime());
		}
		m_game.startClock(m_player1Input.getTime(), m_player2Input.getTime());
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
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK){
			// TODO: Show "Do you really want to quit?" dialog.
			
		}
		return false;
	}
	
}

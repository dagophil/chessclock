package com.chessclock.menusession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.ChessclockGame;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.helpers.ClockInput;
import com.chessclock.helpers.ClockPreferences;

public class MenuSession extends Stage {

	public static final float DEFAULT_X = 6;
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
	private Image m_bgImage;
	
	public MenuSession(ChessclockGame game, Viewport viewport) {
		super(viewport);
		m_game = game;
		
		// Get the background image
		m_bgImage = new Image(AssetLoader.getBackgroundImage());
		m_bgImage.setWidth(this.getWidth());
		m_bgImage.setHeight(this.getHeight());
		this.addActor(m_bgImage);
		
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
		
		// Create the player inputs
		m_player1Input = new ClockInput(ClockPreferences.getPlayer1Time());
		m_player1Input.setX(DEFAULT_X);
		this.addActor(m_player1Input);
		m_player2Input = new ClockInput(ClockPreferences.getPlayer2Time());
		m_player2Input.setX(DEFAULT_X);
		this.addActor(m_player2Input);
		if (ClockPreferences.getTwoClocks()) {
			setToTwoClocks();
		} else {
			setToOneClock();
		}
	}
	
	private void setToOneClock() {
		m_player2Input.setVisible(false);
		m_player1Input.setY(ONE_CLOCK_Y);
	}
	
	private void setToTwoClocks() {
		m_player2Input.setVisible(true);
		m_player1Input.setY(TWO_CLOCKS_Y_PLAYER1);
		m_player2Input.setY(TWO_CLOCKS_Y_PLAYER2);
	}
	
	public void toggleTwoClocks() {
		if (m_player2Input.isVisible()) {
			setToOneClock();
		} else {
			m_player2Input.setTime(m_player1Input.getTime());
			setToTwoClocks();
		}
	}
	
	private void savePrefs() {
		Gdx.app.log("MenuSession", "saving prefs");
		ClockPreferences.setPlayer1Time(m_player1Input.getTime());
		ClockPreferences.setPlayer2Time(m_player2Input.getTime());
		ClockPreferences.setTwoClocks(m_player2Input.isVisible());
		ClockPreferences.flush();
	}
	
	public void startClock() {
		savePrefs();
		if (!m_player2Input.isVisible()) {
			m_player2Input.setTime(m_player1Input.getTime());
		}
		m_game.startClock(m_player1Input.getTime(), m_player2Input.getTime());
	}
	
	public void update(float delta) {
		this.act(delta);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK){
			// TODO: Show "Do you really want to quit?" dialog.
			
		}
		return false;
	}
	
}

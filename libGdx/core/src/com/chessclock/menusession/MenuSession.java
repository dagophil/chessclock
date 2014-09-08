package com.chessclock.menusession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.ChessclockGame;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.helpers.ClockInput;
import com.chessclock.helpers.ClockPreferences;

public class MenuSession extends Stage {

	public static final float DEFAULT_X = 6;
	public static final float ONE_CLOCK_Y = 170;
	public static final float TWO_CLOCKS_Y_PLAYER1 = 260;
	public static final float TWO_CLOCKS_Y_PLAYER2 = 95;
	public static final float BTN_WIDTH = 50;
	public static final float BTN_HEIGHT = 50;
	
	private ChessclockGame m_game;
	private Button m_btnStartClock;
	private Button m_btnTwoClocks;
	private Button m_btnDev;
	private ClockInput m_player1Input;
	private ClockInput m_player2Input;
	private Image m_bgImage;
	private ButtonStyle m_styleOneClock;
	private ButtonStyle m_styleTwoClocks;
	
	public MenuSession(ChessclockGame game, Viewport viewport, boolean fadeIn) {
		super(viewport);
		m_game = game;
		
		// Get the background image
		m_bgImage = new Image(AssetLoader.get(AssetLoader.KING, Texture.class));
		m_bgImage.setWidth(this.getWidth());
		m_bgImage.setHeight(this.getHeight());
		this.addActor(m_bgImage);
		
		// Create the button styles for the two clock button
		m_styleOneClock = new ButtonStyle();
		m_styleOneClock.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_ONE_CLOCK, Texture.class)));
		m_styleOneClock.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_ONE_CLOCK_DOWN, Texture.class)));
		m_styleTwoClocks = new ButtonStyle();
		m_styleTwoClocks.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_TWO_CLOCKS, Texture.class)));
		m_styleTwoClocks.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_TWO_CLOCKS_DOWN, Texture.class)));
		
		// Create dev button
		ButtonStyle styleDev = new ButtonStyle();
		styleDev.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_DEV_UP, Texture.class)));
		styleDev.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_DEV_DOWN, Texture.class)));
		m_btnDev = new Button(styleDev);
		m_btnDev.setWidth(BTN_WIDTH);
		m_btnDev.setHeight(BTN_HEIGHT);
		m_btnDev.setX((this.getWidth() - BTN_WIDTH)/2);
		m_btnDev.setY(15);
		m_btnDev.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				fadeOutAndShowAbout();
			}
		});
		this.addActor(m_btnDev);
		
		// Create start button
		ButtonStyle styleStart = new ButtonStyle();
		styleStart.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_GO, Texture.class)));
		styleStart.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_GO_DOWN, Texture.class)));
		m_btnStartClock = new Button(styleStart);
		m_btnStartClock.setWidth(BTN_WIDTH);
		m_btnStartClock.setHeight(BTN_HEIGHT);
		m_btnStartClock.setX(this.getWidth() - BTN_WIDTH - 15);
		m_btnStartClock.setY(15);
		m_btnStartClock.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				fadeOutAndStartClock();
			}
		});
		this.addActor(m_btnStartClock);
		
		// Create a button to toggle one clock / two clocks
		m_btnTwoClocks = new Button(m_styleTwoClocks);
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
		
		// Set the correct start positions
		if (ClockPreferences.getTwoClocks()) {
			m_player1Input.setY(TWO_CLOCKS_Y_PLAYER1);
			m_player2Input.setY(TWO_CLOCKS_Y_PLAYER2);
		} else {
			m_player1Input.setY(ONE_CLOCK_Y);
			m_player2Input.setY(ONE_CLOCK_Y);
			m_player2Input.setVisible(false);
		}
		
		if (fadeIn) {
			fadeIn();
		}
	}
	
	private void fadeIn() {
		AlphaAction hideAction = new AlphaAction();
		hideAction.setAlpha(0f);
		hideAction.setDuration(0f);
		this.addAction(hideAction);
		
		AlphaAction fadeInAction = new AlphaAction();
		fadeInAction.setAlpha(1f);
		fadeInAction.setDuration(0.15f);
		this.addAction(fadeInAction);
	}
	
	private void setToOneClock() {
		m_btnTwoClocks.setStyle(m_styleTwoClocks);

		// Move player 1 clock
		MoveToAction act1 = new MoveToAction();
		act1.setPosition(m_player1Input.getX(), ONE_CLOCK_Y);
		act1.setInterpolation(Interpolation.exp5);
		act1.setDuration(0.3f);
		m_player1Input.addAction(act1);
		
		// Move player 2 clock and hide when finished
		MoveToAction act2 = new MoveToAction() {
			@Override
			protected void end () {
				m_player2Input.setVisible(false);
			}
		};
		act2.setPosition(m_player2Input.getX(), ONE_CLOCK_Y);
		act2.setInterpolation(Interpolation.exp5);
		act2.setDuration(0.3f);
		m_player2Input.addAction(act2);
		
	}
	
	private void setToTwoClocks() {
		m_btnTwoClocks.setStyle(m_styleOneClock);
		
		// Move player 1 clock
		MoveToAction act1 = new MoveToAction();
		act1.setPosition(m_player1Input.getX(), TWO_CLOCKS_Y_PLAYER1);
		act1.setInterpolation(Interpolation.exp5);
		act1.setDuration(0.3f);
		m_player1Input.addAction(act1);
		
		// Show player 2 clock and move it
		m_player2Input.setVisible(true);
		MoveToAction act2 = new MoveToAction();
		act2.setPosition(m_player2Input.getX(), TWO_CLOCKS_Y_PLAYER2);
		act2.setInterpolation(Interpolation.exp5);
		act2.setDuration(0.3f);
		m_player2Input.addAction(act2);
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
	
	public void fadeOutAndShowAbout() {
		Gdx.input.setInputProcessor(null);
		savePrefs();
		// Create the fade action
		AlphaAction fadeOutAction = new AlphaAction() {
			@Override
			protected void end () {
				m_game.showAbout();
			}
		};
		fadeOutAction.setAlpha(0f);
		fadeOutAction.setDuration(0.15f);
		this.addAction(fadeOutAction);
	}
	
	public void fadeOutAndStartClock() {
		if (!m_player2Input.isVisible()) {
			m_player2Input.setTime(m_player1Input.getTime());
		}
		
		// Only start when the time is large enough
		if (m_player1Input.getTime() < 0.5f || m_player2Input.getTime() < 0.5f) {
			return;
		}
		
		savePrefs();
		
		// Disable input while fading
		Gdx.input.setInputProcessor(null);
		
		// Create the fade action
		AlphaAction fadeOutAction = new AlphaAction() {
			@Override
			protected void end () {
				m_game.startClock(m_player1Input.getTime(), m_player2Input.getTime());
			}
		};
		fadeOutAction.setAlpha(0f);
		fadeOutAction.setDuration(0.15f);
		this.addAction(fadeOutAction);
	}
	
	public void update(float delta) {
		this.act(delta);
	}
	
}

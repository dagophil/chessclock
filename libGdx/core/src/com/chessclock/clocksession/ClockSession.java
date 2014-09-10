package com.chessclock.clocksession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.ChessclockGame;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.helpers.Overlay;

public class ClockSession extends Stage {
	
	public static int BTN_WIDTH = 50;
	public static int BTN_HEIGHT = 50;
	public static String RESUME_TEXT = "Click to\nresume";
	public static String BACK_TO_MENU = "Menu";
	public static String WIN_TEXT = "You win!";
	public static String LOSE_TEXT = "You lose!";
	
	private ChessclockGame m_game;
	private PlayerGroup m_grpWhite;
	private PlayerGroup m_grpBlack;
	private Button m_btnPause;
	private Button m_btnMenu;
	private Overlay m_grpResume;
	private boolean m_isPaused;
	private float m_timePlayer1; // seconds
	private float m_timePlayer2; // seconds
	private boolean m_isWhitesTurn;
	private Image m_flashImg;
	
	public ClockSession(ChessclockGame game, Viewport viewport, float timePlayer1, float timePlayer2) {
		super(viewport);
		
		m_game = game;
		m_timePlayer1 = timePlayer1;
		m_timePlayer2 = timePlayer2;
		m_isPaused = false;
		m_isWhitesTurn = true;
		createGroups();
		
		AlphaAction act = new AlphaAction();
		act.setAlpha(0f);
		act.setDuration(0f);
		this.addAction(act);
		
		AlphaAction fadeInAction = new AlphaAction();
		fadeInAction.setAlpha(1f);
		fadeInAction.setDuration(0.15f);
		this.addAction(fadeInAction);
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
		m_grpWhite.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if (m_grpWhite.isActive()) {
					changePlayers();
				}
				return true;
			}
		});
		this.addActor(m_grpWhite);

		// Create black button
		m_grpBlack = new PlayerGroup(this, false, Colorscheme.getBlackColorscheme());
		m_grpBlack.rotateBy(180);
		m_grpBlack.moveBy(m_grpBlack.getWidth(), 2*m_grpBlack.getHeight());
		m_grpBlack.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if (m_grpBlack.isActive()) {
					changePlayers();
				}
				return true;
			}
		});
		this.addActor(m_grpBlack);
		
		// Create pause button
		ButtonStyle stylePause = new ButtonStyle();
		stylePause.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_PAUSE, Texture.class)));
		m_btnPause = new Button(stylePause);
		m_btnPause.setSize(BTN_WIDTH, BTN_HEIGHT);
		m_btnPause.setX((this.getWidth() - BTN_WIDTH) / 2);
		m_btnPause.setY((this.getHeight() - BTN_HEIGHT) / 2);
		m_btnPause.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				pause();
				return true;
			}
		});
		this.addActor(m_btnPause);
		
		// Create "back to menu" button for resume overlay
		ButtonStyle styleMenu = new ButtonStyle();
		styleMenu.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_BACK_UP, Texture.class)));
		styleMenu.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_BACK_DOWN, Texture.class)));
		m_btnMenu = new Button(styleMenu);
		m_btnMenu.setSize(BTN_WIDTH, BTN_HEIGHT);
		m_btnMenu.setPosition(15, 15);
		m_btnMenu.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				goBackToMenu();
			}
		});
		this.addActor(m_btnMenu);
		
		// Create resume overlay
		m_grpResume = new Overlay(this.getWidth(), this.getHeight(), RESUME_TEXT);
		m_grpResume.addListener(new InputListener() {
			private boolean wasTouchDown;
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if (!m_btnMenu.isPressed()) {
					wasTouchDown = true;
				}
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if (!m_btnMenu.isPressed() && wasTouchDown) {
					resume();
				}
				wasTouchDown = false;
			}
		});
		m_grpResume.addActor(m_btnMenu);
		this.addActor(m_grpResume);
		
		// Create the flash overlay
		m_flashImg = new Image(AssetLoader.getDrawable(Color.WHITE)) {
			@Override
			public Actor hit (float x, float y, boolean touchable) {
				return null;
			}
		};
		m_flashImg.setSize(this.getWidth(), this.getHeight());
		m_flashImg.setVisible(false);
		this.addActor(m_flashImg);
	}
	
	public void goBackToMenu() {
		Gdx.input.setInputProcessor(null);
		AlphaAction fadeOutAction = new AlphaAction() {
			@Override
			protected void end() {
				m_game.backToMenu();
			}
		};
		fadeOutAction.setAlpha(0f);
		fadeOutAction.setDuration(0.15f);
		this.addAction(fadeOutAction);
	}
	
	public void changePlayers() {
		m_isWhitesTurn = !m_isWhitesTurn;
		
		m_flashImg.setVisible(true);
		m_flashImg.setColor(1f, 1f, 1f, 1f);
		AlphaAction fadeOut = new AlphaAction() {
			@Override
			protected void end () {
				m_flashImg.setVisible(false);
			}
		};
		fadeOut.setAlpha(0f);
		fadeOut.setDuration(0.15f);
		m_flashImg.addAction(fadeOut);
	}
	
	public void pause() {
		m_isPaused = true;
		m_grpResume.setVisible(true);
		m_grpResume.setColor(1f, 1f, 1f, 0f);
		AlphaAction fadeInAction = new AlphaAction();
		fadeInAction.setAlpha(1f);
		fadeInAction.setDuration(0.15f);
		m_grpResume.addAction(fadeInAction);
	}
	
	public void resume() {
		m_isPaused = false;
		AlphaAction fadeOutAction = new AlphaAction() {
			@Override
			protected void end () {
				m_isPaused = false;
				m_grpResume.setVisible(false);
			}
		};
		fadeOutAction.setAlpha(0f);
		fadeOutAction.setDuration(0.15f);
		m_grpResume.addAction(fadeOutAction);
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
				playerWins(false);
			}
			if (m_timePlayer2 <= 0) {
				m_timePlayer2 = 0;
				playerWins(true);
			}
		}
		this.act(delta);
	}
	
	private void playerWins(boolean player1Wins) {
		m_isPaused = true;
		Sound snd = AssetLoader.get(AssetLoader.SND_ALARM, Sound.class);
		snd.play(1f);
		
		String player1Text = LOSE_TEXT;
		String player2Text = WIN_TEXT;
		if (player1Wins) {
			player1Text = WIN_TEXT;
			player2Text = LOSE_TEXT;
		}
		
		// Show overlay for player 1
		Overlay player1Overlay = new Overlay(this.getWidth(), this.getHeight() / 2, player1Text);
		player1Overlay.setVisible(true);
		this.addActor(player1Overlay);
		
		// Show overlay for player 2
		Overlay player2Overlay = new Overlay(this.getWidth(), this.getHeight() / 2, player2Text);
		player2Overlay.setVisible(true);
		player2Overlay.rotateBy(180);
		player2Overlay.moveBy(player2Overlay.getWidth(), 2 * player2Overlay.getHeight());
		this.addActor(player2Overlay);

		// Add back to menu listener
		this.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				goBackToMenu();
			}
		});
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK && !m_isPaused){
			pause();
			return true;
		}
		return false;
	}

}

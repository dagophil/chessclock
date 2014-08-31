package com.chessclock.clocksession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.chessclock.helpers.AssetLoader;

public class PlayerGroup extends Group {
	
	public static Colorscheme getWhiteColorscheme() {
		Colorscheme colors = new Colorscheme();
		colors.bgActive = Color.WHITE;
		colors.bgInactive = Color.valueOf("bbbbbb");
		colors.bgPressed = Color.valueOf("dddddd");
		return colors;
	}

	private float m_playerTime;
	private float m_opponentTime;
	
	private Group m_labelGroup;
	private Label m_playerLabel;
	private Label m_opponentLabel;
	private Label m_playerTimeLabel;
	private Label m_opponentTimeLabel;
	
	private Image m_bgInactive;
	private Image m_bgActive;
	
	private boolean m_isActive;
	
	public PlayerGroup(float width, float height,
					   float playerTime, float opponentTime,
					   boolean isActive,
					   Colorscheme colors) {
		super();
		
		this.setWidth(width);
		this.setHeight(height);
		m_playerTime = playerTime;
		m_opponentTime = opponentTime;
		m_isActive = isActive;

		// Add the inactive background
		m_bgInactive = new Image(AssetLoader.getDrawable(colors.bgInactive), Scaling.stretch);
		m_bgInactive.setWidth(width);
		m_bgInactive.setHeight(height);
		this.addActor(m_bgInactive);
		
		// Add the active background
		m_bgActive = new Image(AssetLoader.getDrawable(colors.bgActive), Scaling.stretch);
		m_bgActive.setWidth(width);
		m_bgActive.setHeight(height);
		this.addActor(m_bgActive);
		
		// Create label group
		m_labelGroup = new Group();
		this.addActor(m_labelGroup);
		
		// Create fonts
		BitmapFont smallFont = new BitmapFont();
		smallFont.setScale(1f);
		LabelStyle smallLabelStyle = new LabelStyle();
		smallLabelStyle.font = smallFont;
		BitmapFont largeFont = new BitmapFont();
		largeFont.setScale(3f);
		LabelStyle largeLabelStyle = new LabelStyle();
		largeLabelStyle.font = largeFont;
		
		// Add labels
		m_playerLabel = new Label("Your time:", smallLabelStyle);
		m_playerLabel.setCenterPosition(width/2, height-50);
		m_labelGroup.addActor(m_playerLabel);
		m_playerTimeLabel = new Label(formatTime(m_playerTime), largeLabelStyle);
		m_playerTimeLabel.setCenterPosition(width/2, height-80);
		m_labelGroup.addActor(m_playerTimeLabel);
		m_opponentLabel = new Label("Opponent's time:", smallLabelStyle);
		m_opponentLabel.setCenterPosition(width/2, height-130);
		m_labelGroup.addActor(m_opponentLabel);
		m_opponentTimeLabel = new Label(formatTime(m_opponentTime), largeLabelStyle);
		m_opponentTimeLabel.setCenterPosition(width/2, height-160);
		m_labelGroup.addActor(m_opponentTimeLabel);
		
		// Add the click event
		this.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				Gdx.app.log("PlayerGroup", "clicked");
				PlayerGroup grp = (PlayerGroup) event.getListenerActor();
				grp.clicked();
			}
		});
	}
	
	public void clicked() {
		if (m_isActive) {
			ClockSession session = (ClockSession) this.getStage();
			session.changePlayers();
		}
	}
	/*
	public void fadeOut() {
		AlphaAction alphaAction = new AlphaAction();
		alphaAction.setAlpha(0f);
		alphaAction.setDuration(0.4f);
		m_bgActive.addAction(alphaAction);
		
//		AlphaAction alphaAction2 = new AlphaAction();
//		alphaAction2.setAlpha(0.75f);
//		alphaAction2.setDuration(0.4f);
//		m_labelGroup.addAction(alphaAction2);
	}
	*/
	
	// TODO: Return the correct format
	private String formatTime(float time) {
		return String.valueOf(time);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (m_isActive) {
			m_playerTime -= delta;
			m_playerTimeLabel.setText(formatTime(m_playerTime));
		} else {
			m_opponentTime -= delta;
			m_opponentTimeLabel.setText(formatTime(m_opponentTime));
		}
	}
	
}

package com.chessclock.clocksession;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Scaling;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.helpers.ClockTime;

public class PlayerGroup extends Group {

	private Group m_labelGroup;
	private Label m_playerLabel;
	private Label m_opponentLabel;
	private Label m_playerTimeLabel;
	private Label m_opponentTimeLabel;
	private Image m_bgInactive;
	private Image m_bgActive;
	private boolean m_isWhite;
	private ClockSession m_session;
	
	public PlayerGroup(ClockSession session,
					   boolean isWhite,
					   Colorscheme colors) {
		m_session = session;
		m_isWhite = isWhite;
		this.setWidth(session.getWidth());
		this.setHeight(session.getHeight()/2);
		
		// Add the click event
		this.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				clicked();
				return true;
			}
		});

		// Add the inactive background
		m_bgInactive = new Image(AssetLoader.getDrawable(colors.bgInactive), Scaling.stretch);
		m_bgInactive.setWidth(this.getWidth());
		m_bgInactive.setHeight(this.getHeight());
		this.addActor(m_bgInactive);
		
		// Add the active background
		m_bgActive = new Image(AssetLoader.getDrawable(colors.bgActive), Scaling.stretch);
		m_bgActive.setWidth(this.getWidth());
		m_bgActive.setHeight(this.getHeight());
		this.addActor(m_bgActive);
		
		// Create label group
		m_labelGroup = new Group();
		this.addActor(m_labelGroup);
		
		// Create fonts
		BitmapFont smallFont = AssetLoader.getFont(1f);
		LabelStyle smallLabelStyle = new LabelStyle();
		smallLabelStyle.font = smallFont;
		BitmapFont largeFont = AssetLoader.getFont(3f);
		LabelStyle largeLabelStyle = new LabelStyle();
		largeLabelStyle.font = largeFont;
		
		// Add labels
		m_playerLabel = new Label("Your time:", smallLabelStyle);
		m_playerLabel.setCenterPosition(this.getWidth()/2, this.getHeight()-50);
		m_labelGroup.addActor(m_playerLabel);
		m_playerTimeLabel = new Label(ClockTime.format(getPlayerTime()), largeLabelStyle);
		m_playerTimeLabel.setCenterPosition(this.getWidth()/2, this.getHeight()-80);
		m_labelGroup.addActor(m_playerTimeLabel);
		m_opponentLabel = new Label("Opponent's time:", smallLabelStyle);
		m_opponentLabel.setCenterPosition(this.getWidth()/2, this.getHeight()-130);
		m_labelGroup.addActor(m_opponentLabel);
		m_opponentTimeLabel = new Label(ClockTime.format(getOpponentTime()), largeLabelStyle);
		m_opponentTimeLabel.setCenterPosition(this.getWidth()/2, this.getHeight()-160);
		m_labelGroup.addActor(m_opponentTimeLabel);
	}
	
	private float getPlayerTime() {
		if (m_isWhite) {
			return m_session.getTimePlayer1();
		} else {
			return m_session.getTimePlayer2();
		}
	}
	
	private float getOpponentTime() {
		if (m_isWhite) {
			return m_session.getTimePlayer2();
		} else {
			return m_session.getTimePlayer1();
		}
	}
	
	public boolean isActive() {
		return m_isWhite == m_session.isWhitesTurn();
	}
	
	public void clicked() {
		if (isActive()) {
			ClockSession session = (ClockSession) this.getStage();
			session.changePlayers();
		}
	}
	
	@Override
	public void act(float delta) {
		if (isActive()) {
			m_playerTimeLabel.setText(ClockTime.format(getPlayerTime()));
		} else {
			m_opponentTimeLabel.setText(ClockTime.format(getOpponentTime()));
		}
		super.act(delta);
	}
	
}

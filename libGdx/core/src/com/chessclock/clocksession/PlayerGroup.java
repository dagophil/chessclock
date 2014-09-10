package com.chessclock.clocksession;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.chessclock.helpers.AssetLoader;
import com.chessclock.helpers.ClockTime;

public class PlayerGroup extends Group {
	
	public static String YOUR_TIME_TEXT = "Your time:";
	public static String HIS_TIME_TEXT = "Opponent's time:";

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

		// Add the inactive background
		m_bgInactive = new Image(AssetLoader.getDrawable(colors.bgInactive), Scaling.stretch);
		m_bgInactive.setSize(this.getWidth(), this.getHeight());
		this.addActor(m_bgInactive);
		
		// Add the active background
		m_bgActive = new Image(AssetLoader.getDrawable(colors.bgActive), Scaling.stretch);
		m_bgActive.setSize(this.getWidth(), this.getHeight());
		this.addActor(m_bgActive);
		
		// Create label group
		m_labelGroup = new Group();
		this.addActor(m_labelGroup);
		
		// Create fonts
		BitmapFont smallFont = AssetLoader.getFontSmall();
		LabelStyle smallLabelStyle = new LabelStyle();
		smallLabelStyle.font = smallFont;
		BitmapFont mediumFont = AssetLoader.getFontMedium();
		LabelStyle mediumLabelStyle = new LabelStyle();
		mediumLabelStyle.font = mediumFont;
		BitmapFont largeFont = AssetLoader.getFontLarge();
		LabelStyle largeLabelStyle = new LabelStyle();
		largeLabelStyle.font = largeFont;
		
		// Add labels
		m_playerLabel = new Label(YOUR_TIME_TEXT, smallLabelStyle);
		m_playerLabel.setWidth(this.getWidth());
		m_playerLabel.setY(145);
		m_playerLabel.setAlignment(Align.center);
		m_labelGroup.addActor(m_playerLabel);
		
		m_playerTimeLabel = new Label(ClockTime.format(getPlayerTime()), largeLabelStyle);
		m_playerTimeLabel.setWidth(this.getWidth());
		m_playerTimeLabel.setY(95);
		m_playerTimeLabel.setAlignment(Align.center);
		m_labelGroup.addActor(m_playerTimeLabel);
		
		m_opponentLabel = new Label(HIS_TIME_TEXT, smallLabelStyle);
		m_opponentLabel.setWidth(this.getWidth());
		m_opponentLabel.setY(62);
		m_opponentLabel.setAlignment(Align.center);
		m_labelGroup.addActor(m_opponentLabel);
		
		m_opponentTimeLabel = new Label(ClockTime.format(getOpponentTime()), mediumLabelStyle);
		m_opponentTimeLabel.setWidth(this.getWidth());
		m_opponentTimeLabel.setY(12);
		m_opponentTimeLabel.setAlignment(Align.center);
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

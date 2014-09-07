package com.chessclock.menusession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.chessclock.helpers.AssetLoader;

public class NumberInput extends Group {
	
	public static int LABEL_WIDTH = 60;
	public static int LABEL_HEIGHT = 60;
	public static int BUTTON_WIDTH = 40;
	public static int BUTTON_HEIGHT = 30;
	public static int PADDING = 2;
	public static float LONG_CLICK_TIME = 0.7f;
	public static float SHORT_CLICK_TIME = 0.1f;
	
	private int m_number;
	private int m_minValue;
	private int m_maxValue;
	private Label m_label;
	private Button m_btnPlus;
	private Button m_btnMinus;
	
	public NumberInput(int startnumber, int minValue, int maxValue) {
		m_number = startnumber;
		m_minValue = minValue;
		m_maxValue = maxValue;
		
		// Create the font
		//BitmapFont font = AssetLoader.getFont(3f);
		BitmapFont font = AssetLoader.getFontLarge();
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.background = AssetLoader.getDrawable(Color.DARK_GRAY);
		
		// Create the label
		m_label = new Label(String.format("%02d", m_number), labelStyle);
		m_label.setWidth(LABEL_WIDTH);
		m_label.setHeight(LABEL_HEIGHT);
		m_label.setY(BUTTON_HEIGHT+PADDING);
		m_label.setAlignment(Align.center);
		this.addActor(m_label);
		
		// Create the plus button
		ButtonStyle btnPlusStyle = new ButtonStyle();
		btnPlusStyle.up = AssetLoader.getDrawable(Color.BLUE);
		btnPlusStyle.down = AssetLoader.getDrawable(Color.valueOf("000099"));
		m_btnPlus = new Button(btnPlusStyle);
		m_btnPlus.setWidth(BUTTON_WIDTH);
		m_btnPlus.setHeight(BUTTON_HEIGHT);
		m_btnPlus.setX((LABEL_WIDTH - BUTTON_WIDTH) / 2);
		m_btnPlus.setY(LABEL_HEIGHT + BUTTON_HEIGHT + 2 * PADDING);
		m_btnPlus.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("NumberInput", "touch down");
				startAdding(1, LONG_CLICK_TIME);
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("NumberInput", "touch up");
				clearActions();
			}
		});
		this.addActor(m_btnPlus);
		
		// Create the minus button
		ButtonStyle btnMinusStyle = new ButtonStyle();
		btnMinusStyle.up = AssetLoader.getDrawable(Color.BLUE);
		btnMinusStyle.down = AssetLoader.getDrawable(Color.valueOf("000099"));
		m_btnMinus = new Button(btnMinusStyle);
		m_btnMinus.setWidth(BUTTON_WIDTH);
		m_btnMinus.setHeight(BUTTON_HEIGHT);
		m_btnMinus.setX((LABEL_WIDTH - BUTTON_WIDTH) / 2);
		m_btnMinus.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				startAdding(-1, LONG_CLICK_TIME);
				return true;
			}
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				clearActions();
			}
		});
		this.addActor(m_btnMinus);
	}

	private class NumberAction extends TemporalAction {
		int m_value;
		public NumberAction(int value) {
			m_value = value;
			addToNumber(value);
		}
		@Override
		protected void update(float percent) {}
	}
	
	public void startAdding(int value, float duration) {
		NumberAction longAddAction = new NumberAction(value) {
			@Override
			protected void end () {
				startAdding(m_value, SHORT_CLICK_TIME);
			}
		};
		longAddAction.setDuration(duration);
		this.addAction(longAddAction);
	}
	
	private void addToNumber(int value) {
		m_number += value;
	}
	
	private void checkNumber() {
		if (m_number > m_maxValue) {
			m_number = m_minValue;
		}
		if (m_number < m_minValue) {
			m_number = m_maxValue;
		}
	}
	
	public int getNumber() {
		return m_number;
	}
	
	public void setNumber(int number) {
		m_number = number;
	}
	
	private void updateLabel() {
		m_label.setText(String.format("%02d", m_number));
	}
	
	@Override
	public void act(float delta) {
		checkNumber();
		updateLabel();
		super.act(delta);
	}
	
}

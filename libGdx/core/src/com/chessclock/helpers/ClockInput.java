package com.chessclock.helpers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class ClockInput extends Group {

	public static int INPUT_PAD = 10;
	public static int NUMBER_WIDTH = 60;
	
	private NumberInput m_secondsInput;
	private NumberInput m_minutesInput;
	private NumberInput m_hoursInput;
	private Label m_label1;
	private Label m_label2;
	
	public ClockInput(float time) {
		m_hoursInput = new NumberInput(ClockTime.getHours(time), 0, 99);
		this.addActor(m_hoursInput);
		
		m_minutesInput = new NumberInput(ClockTime.getMinutes(time), 0, 59);
		m_minutesInput.moveBy(NumberInput.LABEL_WIDTH + INPUT_PAD, 0);
		this.addActor(m_minutesInput);
		
		m_secondsInput = new NumberInput(ClockTime.getSeconds(time), 0, 59);
		m_secondsInput.moveBy(2*(NumberInput.LABEL_WIDTH + INPUT_PAD), 0);
		this.addActor(m_secondsInput);
		
		// Load the font
		BitmapFont font = AssetLoader.getFontLarge();
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		
		m_label1 = new Label(":", labelStyle);
		m_label1.moveBy(3 + NUMBER_WIDTH, 17);
		this.addActor(m_label1);
		
		m_label2 = new Label(":", labelStyle);
		m_label2.moveBy(3 + 2 * (NUMBER_WIDTH + INPUT_PAD), 17);
		this.addActor(m_label2);
	}
	
	public void setTime(float time) {
		m_hoursInput.setNumber(ClockTime.getHours(time));
		m_minutesInput.setNumber(ClockTime.getMinutes(time));
		m_secondsInput.setNumber(ClockTime.getSeconds(time));
	}
	
	public float getTime() {
		int hours = m_hoursInput.getNumber();
		int minutes = m_minutesInput.getNumber();
		int seconds = m_secondsInput.getNumber();
		return seconds + 60*minutes + 3600*hours;
	}
	
}

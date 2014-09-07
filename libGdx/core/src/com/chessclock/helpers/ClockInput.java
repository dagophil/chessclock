package com.chessclock.helpers;

import com.badlogic.gdx.scenes.scene2d.Group;

public class ClockInput extends Group {

	public static int DEFAULT_INPUT_PAD = 4;
	
	private NumberInput m_secondsInput;
	private NumberInput m_minutesInput;
	private NumberInput m_hoursInput;
	
	public ClockInput(float time) {
		m_hoursInput = new NumberInput(ClockTime.getHours(time), 0, 99);
		this.addActor(m_hoursInput);
		
		m_minutesInput = new NumberInput(ClockTime.getMinutes(time), 0, 59);
		m_minutesInput.moveBy(NumberInput.LABEL_WIDTH + DEFAULT_INPUT_PAD, 0);
		this.addActor(m_minutesInput);
		
		m_secondsInput = new NumberInput(ClockTime.getSeconds(time), 0, 59);
		m_secondsInput.moveBy(2*(NumberInput.LABEL_WIDTH + DEFAULT_INPUT_PAD), 0);
		this.addActor(m_secondsInput);
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

package com.chessclock.menusession;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.chessclock.helpers.ClockTime;

public class ClockInput extends Group {

	public static int DEFAULT_INPUT_PAD = 4;
	
	private float m_playerTime;
	private NumberInput m_secondsInput;
	private NumberInput m_minutesInput;
	private NumberInput m_hoursInput;
	
	public ClockInput(float playerTime) {
		m_playerTime = playerTime;
		
		m_hoursInput = new NumberInput(ClockTime.getHours(playerTime));
		this.addActor(m_hoursInput);
		
		m_minutesInput = new NumberInput(ClockTime.getMinutes(playerTime));
		m_minutesInput.moveBy(NumberInput.DEFAULT_LABEL_WIDTH + DEFAULT_INPUT_PAD, 0);
		this.addActor(m_minutesInput);
		
		m_secondsInput = new NumberInput(ClockTime.getSeconds(playerTime));
		m_secondsInput.moveBy(2*(NumberInput.DEFAULT_LABEL_WIDTH + DEFAULT_INPUT_PAD), 0);
		this.addActor(m_secondsInput);
		
	}
	
	public float getPlayerTime() {
		return m_playerTime;
	}
	
}

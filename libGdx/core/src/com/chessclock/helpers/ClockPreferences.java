package com.chessclock.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ClockPreferences {

	public static final String PREFS_NAME = "chessclock";
	public static final String PREF_PLAYER1TIME = "player1time";
	public static final String PREF_PLAYER2TIME = "player2time";
	public static final String PREF_TWO_CLOCKS = "twoclocks";
	public static final float DEFAULT_PLAYERTIME = 600; 
	public static final boolean DEFAULT_TWO_CLOCKS = false;
	
	private static Preferences m_preferences;

	private static Preferences getPreferences() {
		if (m_preferences == null) {
			m_preferences = Gdx.app.getPreferences(PREFS_NAME);
		}
		return m_preferences;
	}
	
	public static float getPlayer1Time() {
		return getPreferences().getFloat(PREF_PLAYER1TIME, DEFAULT_PLAYERTIME);
	}
	
	public static void setPlayer1Time(float value) {
		getPreferences().putFloat(PREF_PLAYER1TIME, value);
	}
	
	public static float getPlayer2Time() {
		return getPreferences().getFloat(PREF_PLAYER2TIME, DEFAULT_PLAYERTIME);
	}
	
	public static void setPlayer2Time(float value) {
		getPreferences().putFloat(PREF_PLAYER2TIME, value);
	}
	
	public static boolean getTwoClocks() {
		return getPreferences().getBoolean(PREF_TWO_CLOCKS, DEFAULT_TWO_CLOCKS);
	}
	
	public static void setTwoClocks(boolean value) {
		getPreferences().putBoolean(PREF_TWO_CLOCKS, value);
	}
	
	public static void flush() {
		getPreferences().flush();
	}
	
}

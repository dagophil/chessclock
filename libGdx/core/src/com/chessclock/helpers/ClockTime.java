package com.chessclock.helpers;

public class ClockTime {
	
	public static int getDeciseconds(float time) {
		return (int) Math.ceil(time*10) % 10;
	}
	
	public static int getSeconds(float time) {
		return (int) Math.floor(time) % 60;
	}
	
	public static int getMinutes(float time) {
		return (int) Math.floor(time/60) % 60;
	}
	
	public static int getHours(float time) {
		return (int) Math.floor(time/3600);
	}
	
	public static String format(float time) {
		int deciseconds = getDeciseconds(time);
		int seconds = getSeconds(time);
		int minutes = getMinutes(time);
		int hours = getHours(time);
		if (hours > 0) {
			return String.format("%d:%02d:%02d", hours, minutes, seconds);
		}
		if (time > 90) {
			return String.format("%d:%02d", minutes, seconds);
		}
		return String.format("%d:%02d:%01d", minutes, seconds, deciseconds);
	}
	
}

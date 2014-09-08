package com.chessclock.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class AssetLoader {
	
	// TODO: Different image resolutions for Desktop / Android
	
	public static String KING = "king.jpg";
	public static String FONT20 = "myfont20.fnt";
	public static String FONT40 = "myfont40.fnt";
	public static String FONT60 = "myfont1.fnt";
	public static String BTN_GO = "btn_go_50.png";
	public static String BTN_GO_DOWN = "btn_go_down_50.png";
	public static String BTN_ONE_CLOCK = "btn_one_clock_50.png";
	public static String BTN_ONE_CLOCK_DOWN = "btn_one_clock_down_50.png";
	public static String BTN_TWO_CLOCKS = "btn_two_clocks_50.png";
	public static String BTN_TWO_CLOCKS_DOWN = "btn_two_clocks_down_50.png";
	public static String BTN_PAUSE = "btn_pause_50.png";
	public static String BTN_PAUSE_DOWN = "btn_pause_down_50.png";
	
	private static AssetManager m_am;
	private static Skin m_skin;
	
	public static void load() {
		Gdx.app.log("AssetLoader", "load called");
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		m_skin = new Skin();
		m_skin.add("white", new Texture(pixmap));
		
		m_am = new AssetManager();
		m_am.load(KING, Texture.class);
		m_am.load(FONT20, BitmapFont.class);
		m_am.load(FONT40, BitmapFont.class);
		m_am.load(FONT60, BitmapFont.class);
		m_am.load(BTN_GO, Texture.class);
		m_am.load(BTN_GO_DOWN, Texture.class);
		m_am.load(BTN_ONE_CLOCK, Texture.class);
		m_am.load(BTN_ONE_CLOCK_DOWN, Texture.class);
		m_am.load(BTN_TWO_CLOCKS, Texture.class);
		m_am.load(BTN_TWO_CLOCKS_DOWN, Texture.class);
		m_am.load(BTN_PAUSE, Texture.class);
		m_am.load(BTN_PAUSE_DOWN, Texture.class);
	}
	
	public static <T> T get(String name, Class<T> type) {
		return m_am.get(name, type);
	}
	
	public static Drawable getDrawable(Color color) {
		return m_skin.newDrawable("white", color);
	}

	public static BitmapFont getFontSmall() {
		return m_am.get(FONT20, BitmapFont.class);
	}
	
	public static BitmapFont getFontMedium() {
		return m_am.get(FONT40, BitmapFont.class);
	}
	
	public static BitmapFont getFontLarge() {
		return m_am.get(FONT60, BitmapFont.class);
	}
	
	public static void dispose() {
		Gdx.app.log("AssetLoader", "dispose called");
		m_am.dispose();
	}
	
	public static boolean update() {
		return m_am.update();
	}

}

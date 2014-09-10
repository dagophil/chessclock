package com.chessclock.helpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class AssetLoader {
	
	public static final String FONT20 = "fonts/myfont60.fnt";
	public static final String FONT30 = "fonts/myfont80.fnt";
	public static final String FONT40 = "fonts/myfont100.fnt";
	public static final String FONT60 = "fonts/myfont120.fnt";
	public static final String PENTAGON = "backgrounds/pentagon_cut.png";
	public static final String CORRIDOR = "backgrounds/corridor_small.jpg";
	public static final String BTN_GO = "buttons/btn_go_320.png";
	public static final String BTN_GO_DOWN = "buttons/btn_go_down_320.png";
	public static final String BTN_ONE_CLOCK = "buttons/btn_one_clock_320.png";
	public static final String BTN_ONE_CLOCK_DOWN = "buttons/btn_one_clock_down_320.png";
	public static final String BTN_TWO_CLOCKS = "buttons/btn_two_clocks_320.png";
	public static final String BTN_TWO_CLOCKS_DOWN = "buttons/btn_two_clocks_down_320.png";
	public static final String BTN_PAUSE = "buttons/btn_pause_320.png";
	public static final String BTN_PAUSE_DOWN = "buttons/btn_pause_down_320.png";
	public static final String BTN_ARR_UP = "buttons/btn_up_297.png";
	public static final String BTN_ARR_UP_DOWN = "buttons/btn_up_down_297.png";
	public static final String BTN_ARR_DOWN = "buttons/btn_down_297.png";
	public static final String BTN_ARR_DOWN_DOWN = "buttons/btn_down_down_297.png";
	public static final String BTN_DEV_UP = "buttons/btn_dev_320.png";
	public static final String BTN_DEV_DOWN = "buttons/btn_dev_down_320.png";
	public static final String BTN_BACK_UP = "buttons/btn_back_320.png";
	public static final String BTN_BACK_DOWN = "buttons/btn_back_down_320.png";
	public static final String SND_ALARM = "sounds/alarm.mp3";
	
	private static AssetManager m_am;
	private static Skin m_skin;
	private static ArrayList<String> m_bitmapFonts;
	private static ArrayList<String> m_textures;
	private static ArrayList<String> m_sounds;
	
	static {
		m_bitmapFonts = new ArrayList<String>();
		m_bitmapFonts.add(FONT20);
		m_bitmapFonts.add(FONT30);
		m_bitmapFonts.add(FONT40);
		m_bitmapFonts.add(FONT60);
		
		m_textures = new ArrayList<String>();
		m_textures.add(CORRIDOR);
		m_textures.add(PENTAGON);
		m_textures.add(BTN_GO);
		m_textures.add(BTN_GO_DOWN);
		m_textures.add(BTN_ONE_CLOCK);
		m_textures.add(BTN_ONE_CLOCK_DOWN);
		m_textures.add(BTN_TWO_CLOCKS);
		m_textures.add(BTN_TWO_CLOCKS_DOWN);
		m_textures.add(BTN_PAUSE);
		m_textures.add(BTN_PAUSE_DOWN);
		m_textures.add(BTN_ARR_UP);
		m_textures.add(BTN_ARR_UP_DOWN);
		m_textures.add(BTN_ARR_DOWN);
		m_textures.add(BTN_ARR_DOWN_DOWN);
		m_textures.add(BTN_DEV_UP);
		m_textures.add(BTN_DEV_DOWN);
		m_textures.add(BTN_BACK_UP);
		m_textures.add(BTN_BACK_DOWN);
		
		m_sounds = new ArrayList<String>();
		m_sounds.add(SND_ALARM);
	}
	
	public static void load() {
		Gdx.app.log("AssetLoader", "load called");
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		m_skin = new Skin();
		m_skin.add("white", new Texture(pixmap));
		
		// Load the bitmap fonts
		m_am = new AssetManager();
		for (String font_string : m_bitmapFonts) {
			m_am.load(font_string, BitmapFont.class);
		}
		
		// Load the textures
		for (String tex_string : m_textures) {
			m_am.load(tex_string, Texture.class);
		}

		// Load the sounds
		for (String sound_string : m_sounds) {
			m_am.load(sound_string, Sound.class);
		}
	}
	
	public static void customSettings() {
		// Set the texture filters
		Array<Texture> textures = new Array<Texture>();
		m_am.getAll(Texture.class, textures);
		for (Texture tex : textures) {
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		// Scale the fonts
		m_am.get(FONT60, BitmapFont.class).setScale(0.35f);
		m_am.get(FONT40, BitmapFont.class).setScale(0.37f);
		m_am.get(FONT30, BitmapFont.class).setScale(0.42f);
		m_am.get(FONT20, BitmapFont.class).setScale(0.35f);
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
	
	public static BitmapFont getFontSM() {
		return m_am.get(FONT30, BitmapFont.class);
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

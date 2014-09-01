package com.chessclock.helpers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class AssetLoader {
	
	private static AssetManager m_am;
	private static Skin m_skin;
	private static HashMap<Integer, BitmapFont> m_fonts;
	
	public static void load() {
		Gdx.app.log("AssetLoader", "load called");
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		m_skin = new Skin();
		m_skin.add("white", new Texture(pixmap));
		
		m_am = new AssetManager();
		m_am.load("mybuttons.txt", TextureAtlas.class);
		m_am.finishLoading();
		
		m_fonts = new HashMap<Integer, BitmapFont>();
	}
	
	public static Drawable getDrawable(Color color) {
		return m_skin.newDrawable("white", color);
	}
	
	public static TextureAtlas getButtons() {
		return m_am.get("mybuttons.txt");
	}
	
	public static BitmapFont getFont(float scale) {
		int i = (int) Math.round(scale*100);
		if (m_fonts.containsKey(i)) {
			return m_fonts.get(i);
		} else {
			BitmapFont font = new BitmapFont();
			font.setScale(Math.round(scale*100)/100);
			m_fonts.put(i, font);
			return font;
		}
	}
	
	public static void dispose() {
		Gdx.app.log("AssetLoader", "dispose called");
		m_am.dispose();
		for (BitmapFont font : m_fonts.values()) {
			font.dispose();
		}
	}

}

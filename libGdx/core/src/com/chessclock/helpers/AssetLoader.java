package com.chessclock.helpers;

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
	
	public static void load() {
		Gdx.app.log("AssetLoader", "load called");
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		m_skin = new Skin();
		m_skin.add("white", new Texture(pixmap));
		
		m_am = new AssetManager();
		m_am.load("king.jpg", Texture.class);
		m_am.load("myfont20.fnt", BitmapFont.class);
		m_am.load("myfont40.fnt", BitmapFont.class);
		m_am.load("myfont1.fnt", BitmapFont.class);
	}
	
	public static Drawable getDrawable(Color color) {
		return m_skin.newDrawable("white", color);
	}
	
	public static TextureAtlas getButtons() {
		return m_am.get("mybuttons.txt", TextureAtlas.class);
	}

	public static BitmapFont getFontSmall() {
		return m_am.get("myfont20.fnt", BitmapFont.class);
	}
	
	public static BitmapFont getFontMedium() {
		return m_am.get("myfont40.fnt", BitmapFont.class);
	}
	
	public static BitmapFont getFontLarge() {
		return m_am.get("myfont1.fnt", BitmapFont.class);
	}
	
	public static void dispose() {
		Gdx.app.log("AssetLoader", "dispose called");
		m_am.dispose();
	}
	
	public static Texture getBackgroundImage() {
		return m_am.get("king.jpg", Texture.class);
	}
	
	public static boolean update() {
		return m_am.update();
	}

}

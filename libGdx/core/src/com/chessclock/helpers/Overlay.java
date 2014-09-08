package com.chessclock.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class Overlay extends Group {

	private Image m_background;
	private Label m_label1;
	
	public Overlay(float width, float height, String text) {
		this.setWidth(width);
		this.setHeight(height);
		
		m_background = new Image(AssetLoader.getDrawable(Color.BLACK), Scaling.stretch);
		m_background.setWidth(width);
		m_background.setHeight(height);
		m_background.setColor(1f, 1f, 1f, 0.8f);
		this.addActor(m_background);
		
		BitmapFont font = AssetLoader.getFontSM();
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		
		m_label1 = new Label(text, labelStyle);
		m_label1.setAlignment(Align.center);
		m_label1.setCenterPosition(this.getWidth()/2, this.getHeight()/2);
		this.addActor(m_label1);
		
		this.setVisible(false);
	}
	
	public void show() {
		this.setVisible(true);
		this.setColor(1f, 1f, 1f, 1f);
	}
	
	public void hide() {
		this.setVisible(false);
	}
	
	public void setText(String text) {
		m_label1.setText(text);
	}

}

package com.chessclock.menusession;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.chessclock.helpers.AssetLoader;

public class NumberInput extends Group {

	public static int DEFAULT_LABEL_WIDTH = 60;
	public static int DEFAULT_LABEL_HEIGHT = 60;
	public static int DEFAULT_BUTTON_WIDTH = 40;
	public static int DEFAULT_BUTTON_HEIGHT = 30;
	public static int DEFAULT_PAD = 2;
	
	private int m_number;
	private Label m_label;
	private Button m_btnPlus;
	private Button m_btnMinus;
	
	public NumberInput(int number) {
		m_number = number;
		
		// Create the font
		BitmapFont font = AssetLoader.getFont(3f);
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.background = AssetLoader.getDrawable(Color.DARK_GRAY);
		
		// Create the label
		m_label = new Label(String.format("%02d", m_number), labelStyle);
		m_label.setWidth(DEFAULT_LABEL_WIDTH);
		m_label.setHeight(DEFAULT_LABEL_HEIGHT);
		m_label.setY(DEFAULT_BUTTON_HEIGHT+DEFAULT_PAD);
		m_label.setAlignment(Align.center);
		this.addActor(m_label);
		
		// Create the plus button
		ButtonStyle btnPlusStyle = new ButtonStyle();
		btnPlusStyle.up = AssetLoader.getDrawable(Color.BLUE);
		btnPlusStyle.down = AssetLoader.getDrawable(Color.valueOf("000099"));
		m_btnPlus = new Button(btnPlusStyle);
		m_btnPlus.setWidth(DEFAULT_BUTTON_WIDTH);
		m_btnPlus.setHeight(DEFAULT_BUTTON_HEIGHT);
		m_btnPlus.setX((DEFAULT_LABEL_WIDTH - DEFAULT_BUTTON_WIDTH) / 2);
		m_btnPlus.setY(DEFAULT_LABEL_HEIGHT + DEFAULT_BUTTON_HEIGHT + 2 * DEFAULT_PAD);
		this.addActor(m_btnPlus);
		
		// Create the minus button
		ButtonStyle btnMinusStyle = new ButtonStyle();
		btnMinusStyle.up = AssetLoader.getDrawable(Color.BLUE);
		btnMinusStyle.down = AssetLoader.getDrawable(Color.valueOf("000099"));
		m_btnMinus = new Button(btnMinusStyle);
		m_btnMinus.setWidth(DEFAULT_BUTTON_WIDTH);
		m_btnMinus.setHeight(DEFAULT_BUTTON_HEIGHT);
		m_btnMinus.setX((DEFAULT_LABEL_WIDTH - DEFAULT_BUTTON_WIDTH) / 2);
		this.addActor(m_btnMinus);
		
		
//		m_btnPause.addListener(new InputListener() {
//			@Override
//			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//				pause();
//				return true;
//			}
//		});
//		this.addActor(m_btnPause);
		
	}
	
}

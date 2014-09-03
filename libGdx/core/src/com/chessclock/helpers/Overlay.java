package com.chessclock.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;

public class Overlay extends Image {

	private String m_text;
	
	public Overlay(float width, float height, String text) {
		super(AssetLoader.getDrawable(Color.BLACK), Scaling.stretch);
		
		m_text = text;
		this.setWidth(width);
		this.setHeight(height);
		this.setColor(1f, 1f, 1f, 0.8f);
		this.setVisible(false);
	}
	
	public void show() {
		this.setVisible(true);
	}
	
	public void hide() {
		this.setVisible(false);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		// Draw the text on top
		Matrix4 batchtransform = batch.getTransformMatrix();
		Matrix4 rotTransform = new Matrix4();
		rotTransform.setToRotation(new Vector3(0, 0, 1), 90);
		batch.setTransformMatrix(rotTransform);
		BitmapFont font = AssetLoader.getFont(3f);
		font.draw(batch, m_text, this.getHeight()/2-150, -this.getWidth()/2+20);
		batch.setTransformMatrix(batchtransform);
	}
	
}

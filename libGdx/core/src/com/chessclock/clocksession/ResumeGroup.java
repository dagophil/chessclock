package com.chessclock.clocksession;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.chessclock.helpers.AssetLoader;

public class ResumeGroup extends Group {

	private ClockSession m_session;
	private Image m_background;
	private BitmapFont m_font;
	
	public ResumeGroup(ClockSession session) {
		m_session = session;
		this.setWidth(session.getWidth());
		this.setHeight(session.getHeight());
		
		// Add the click event
		this.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				m_session.resume();
				return true;
			}
		});
		
		// Add the background
		m_background = new Image(AssetLoader.getDrawable(Color.BLACK), Scaling.stretch);
		m_background.setWidth(this.getWidth());
		m_background.setHeight(this.getHeight());
		m_background.setColor(1f, 1f, 1f, 0.8f);
		this.addActor(m_background);
		
		// Create the font
		m_font = new BitmapFont();
		m_font.setScale(3f);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		// Draw the resume text on top
		Matrix4 batchtransform = batch.getTransformMatrix();
		Matrix4 grpTransform = this.computeTransform();
		grpTransform.rotate(new Vector3(0, 0, 1), 90);
		batch.setTransformMatrix(grpTransform);
		m_font.draw(batch, "Click to resume", this.getHeight()/2-150, -this.getWidth()/2+20);
		batch.setTransformMatrix(batchtransform);
	}
	
}

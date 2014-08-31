package com.chessclock.clocksession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.helpers.AssetLoader;

public class ClockSession extends Stage {
	
	private Group m_grpWhite;
	private Button m_btnBlack;
	private Button m_btnPause;
	
	public ClockSession(Viewport viewport, float timePlayer1, float timePlayer2) {
		super(viewport);
		
		createGroups(timePlayer1, timePlayer2);
	}
	
	private void createGroups(float timePlayer1, float timePlayer2) {
		
		// Create white button
		m_grpWhite = new PlayerGroup(this.getViewport().getWorldWidth(),
									 this.getViewport().getWorldHeight()/2,
									 timePlayer1, timePlayer2,
									 true,
									 PlayerGroup.getWhiteColorscheme());
		this.addActor(m_grpWhite);
		

		
		// Create black button
		ButtonStyle styleBlack = new ButtonStyle();
		styleBlack.up = AssetLoader.getDrawable(Color.BLACK);
		styleBlack.down = AssetLoader.getDrawable(Color.DARK_GRAY);
		m_btnBlack = new Button(styleBlack);
		m_btnBlack.setWidth(this.getViewport().getWorldWidth());
		m_btnBlack.setHeight(this.getViewport().getWorldHeight()/2);
		m_btnBlack.setY(this.getViewport().getWorldHeight()/2);
		this.addActor(m_btnBlack);
		
		// Create pause button
		ButtonStyle stylePause = new ButtonStyle();
		stylePause.up = AssetLoader.getDrawable(Color.RED);
		stylePause.down = AssetLoader.getDrawable(Color.PINK);
		m_btnPause = new Button(stylePause);
		m_btnPause.setWidth(this.getViewport().getWorldWidth()/5);
		m_btnPause.setHeight(this.getViewport().getWorldWidth()/5);
		m_btnPause.setX(2*this.getViewport().getWorldWidth()/5);
		m_btnPause.setY(this.getViewport().getWorldHeight()/2-this.getViewport().getWorldWidth()/10);
		this.addActor(m_btnPause);
	}
	
	// TODO: Implement
	public void changePlayers() {
		
	}
	
	public void update(float delta) {
		//Gdx.app.log("ClockSession", "update");
		this.act(delta);
	}
	
	@Override
	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.draw();
	}

}

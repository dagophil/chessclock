package com.chessclock.splashsession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AfterAction;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.ChessclockGame;

public class SplashSession extends Stage {

	public static String ROOK = "loadingscreen.png";
	
	private ChessclockGame m_game;
	private Image m_bgImage;
	
	public SplashSession(ChessclockGame game, Viewport viewport) {
		super(viewport);
		m_game = game;
		
		// Show background
		m_bgImage = new Image(new TextureRegion(new Texture(Gdx.files.internal(ROOK))));
		m_bgImage.setWidth(viewport.getWorldWidth());
		m_bgImage.setHeight(viewport.getWorldHeight());
		this.addActor(m_bgImage);
		
		// Fade in
		m_bgImage.setColor(1f, 1f, 1f, 0f);
		AlphaAction fadeIn = new AlphaAction();
		fadeIn.setAlpha(1f);
		fadeIn.setDuration(1f);
		DelayAction waitAction = new DelayAction(1f);
		SequenceAction seq = new SequenceAction(fadeIn, waitAction);

		m_bgImage.addAction(seq);
	}
	
	public void fadeOutAndShowMenu() {
		Gdx.app.log("SplashSession", "fadeOutAndShowMenu called");

		// Fade out
		AlphaAction fadeOut = new AlphaAction() {
			@Override
			protected void end () {
				Gdx.app.log("SplashSession", "backToMenu called");
				m_game.backToMenu();
			}
		};
		fadeOut.setAlpha(0f);
		fadeOut.setDuration(1f);
		
		AfterAction waitAct = new AfterAction();
		waitAct.setAction(fadeOut);
		
		m_bgImage.addAction(waitAct);
	}
	
}

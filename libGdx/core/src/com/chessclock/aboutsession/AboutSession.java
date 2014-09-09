package com.chessclock.aboutsession;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chessclock.ChessclockGame;
import com.chessclock.helpers.AssetLoader;

public class AboutSession extends Stage {

	public static float BTN_WIDTH = 50;
	public static float BTN_HEIGHT = 50;
	
	private ChessclockGame m_game;
	private Image m_bgImage;
	private Label m_text;
	private Button m_backBtn;
	
	public AboutSession(ChessclockGame game, Viewport viewport) {
		super(viewport);
		m_game = game;
		
		// Get the background image
		m_bgImage = new Image(AssetLoader.get(AssetLoader.KING, Texture.class));
		m_bgImage.setWidth(this.getWidth());
		m_bgImage.setHeight(this.getHeight());
		this.addActor(m_bgImage);
		
		// Add some text
		BitmapFont font = AssetLoader.getFontSmall();
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		m_text = new Label("Idee und\nEntwicklung:\nPhilip Schill\n\nLogo:\nDavid\nBaumgärtel", labelStyle);
		m_text.setAlignment(Align.left);
		m_text.setCenterPosition(this.getWidth()/2, this.getHeight()/2);
		m_text.setY(this.getHeight() - m_text.getHeight()-36);
		this.addActor(m_text);
		
		// Add the back button
		ButtonStyle styleBack = new ButtonStyle();
		styleBack.up = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_BACK_UP, Texture.class)));
		styleBack.down = new TextureRegionDrawable(new TextureRegion(AssetLoader.get(AssetLoader.BTN_BACK_DOWN, Texture.class)));
		m_backBtn = new Button(styleBack);
		m_backBtn.setWidth(BTN_WIDTH);
		m_backBtn.setHeight(BTN_HEIGHT);
		m_backBtn.setX(15);
		m_backBtn.setY(15);
		m_backBtn.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				fadeOutAndShowMenu();
			}
		});
		this.addActor(m_backBtn);

		// Fade in
		AlphaAction hideAction = new AlphaAction();
		hideAction.setAlpha(0f);
		hideAction.setDuration(0f);
		this.addAction(hideAction);
		AlphaAction fadeInAction = new AlphaAction();
		fadeInAction.setAlpha(1f);
		fadeInAction.setDuration(0.15f);
		this.addAction(fadeInAction);
	}
	
	public void fadeOutAndShowMenu() {
		AlphaAction fadeOutAction = new AlphaAction() {
			@Override
			protected void end () {
				m_game.backToMenu();
			}
		};
		fadeOutAction.setAlpha(0f);
		fadeOutAction.setDuration(0.15f);
		this.addAction(fadeOutAction);
	}
	
	public void update(float delta) {
		this.act(delta);
	}
	
}

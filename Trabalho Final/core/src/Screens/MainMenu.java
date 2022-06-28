package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.jogo.Jogo;

public class MainMenu implements Screen {

	private Jogo game;
	private Texture playActive, playInactive;
	private Texture tutorialActive, tutorialInactive;
	private Texture title, background;
	private Texture settingsInactive, settingsActive;
	private static final int settingsWidth = 80, settingsHeight = 80, tutorialWidth = 300, tutorialHeight = 50;
	private static final int playWidth = 180, playHeight = 50, titleWidth = 550, titleHeight = 100;
	private static final int playX = (Jogo.WIDTH - playWidth) / 2, playY = 220;
	private static final int settingsX = (Jogo.WIDTH - settingsWidth)/ 2, settingsY = 50;
	private static final int tutorialX = (Jogo.WIDTH - tutorialWidth) / 2, tutorialY = 150;
	
	public MainMenu(Jogo game) {
		this.game = game;
		playActive = new Texture("playAtivo.png");
		playInactive = new Texture("playInativo.png");
		tutorialActive = new Texture("tutorialAtivo.png");
		tutorialInactive = new Texture("tutorialInativo.png");
		settingsActive = new Texture("settingsAtivo.png");
		settingsInactive = new Texture("settingsInativo.png");
		title = new Texture("extinction.png");
		background = new Texture("blobBackground.png");
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		game.batch.draw(background, 0, 0, Jogo.WIDTH, Jogo.HEIGHT);
		game.batch.draw(title, (Jogo.WIDTH - titleWidth) / 2, 450, titleWidth, titleHeight);
		if (game.active(playX, playY, playWidth, playHeight)) {
			game.batch.draw(playActive, playX, playY, playWidth, playHeight);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.playSound();
				game.setScreen(new PreGame(game));
			}
		}
		else {
			game.batch.draw(playInactive, playX, playY, playWidth, playHeight);
		}
		if (game.active(tutorialX, tutorialY, tutorialWidth, tutorialHeight)) {
			game.batch.draw(tutorialActive, tutorialX, tutorialY, tutorialWidth, tutorialHeight);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.playSound();
				game.setScreen(new Tutorial(game));
			}
		}
		else {
			game.batch.draw(tutorialInactive, tutorialX, tutorialY, tutorialWidth, tutorialHeight);
		}
		if (game.active(settingsX, settingsY, settingsWidth, settingsHeight)) {
			game.batch.draw(settingsActive, settingsX, settingsY, settingsWidth, settingsHeight);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.playSound();
				game.setScreen(new Settings(game, false));
			}
		}
		else {
			game.batch.draw(settingsInactive, settingsX, settingsY, settingsWidth, settingsHeight);
		}
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		playActive.dispose();
		playInactive.dispose();
		tutorialActive.dispose();
		tutorialInactive.dispose();
		settingsActive.dispose();
		settingsInactive.dispose();
		title.dispose();
		background.dispose();
	}

}

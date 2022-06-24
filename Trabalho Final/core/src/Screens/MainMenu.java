package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.jogo.Jogo;

public class MainMenu implements Screen {

	Jogo game;
	Texture playActive, playInactive;
	Texture tutorialActive, tutorialInactive;
	Texture title, background;
	Texture settingsInactive, settingsActive;
	private static final int settingsWidth = 80, settingsHeight = 80, tutorialWidth = 300, tutorialHeight = 50;
	private static final int playWidth = 200, playHeight = 50, titleWidth = 450, titleHeight = 100;
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
		background = new Texture("MainMenuBackground.jpg");
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		game.batch.draw(background, 0, 0, Jogo.WIDTH, Jogo.HEIGHT);
		game.batch.draw(title, (Jogo.WIDTH - titleWidth) / 2, 450, titleWidth, titleHeight);
		if (Gdx.input.getX() >= playX && Gdx.input.getX() <= playX + playWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - playY - playHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - playY) {
			game.batch.draw(playActive, playX, playY, playWidth, playHeight);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.playSound();
				game.setScreen(new GameScreen(game));
			}
		}
		else {
			game.batch.draw(playInactive, playX, playY, playWidth, playHeight);
		}
		if (Gdx.input.getX() >= tutorialX && Gdx.input.getX() <= tutorialX + tutorialWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - tutorialY - tutorialHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - tutorialY) {
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
		if (Gdx.input.getX() >= settingsX && Gdx.input.getX() <= settingsX + settingsWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - settingsY - settingsHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - settingsY) {
			game.batch.draw(settingsActive, settingsX, settingsY, settingsWidth, settingsHeight);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.playSound();
				game.setScreen(new Settings(game));
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
		
	}

}

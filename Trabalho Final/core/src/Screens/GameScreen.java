package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.poo.jogo.Cores;
import com.poo.jogo.Jogo;

public class GameScreen implements Screen {
	
	Jogo game;
	private Cores cor;
	private int speed, intelligence, strength;
	Texture background, white, border, settingsInactive, settingsActive;
	BitmapFont title, green, red, yellow, blue;
	private static final int settingsWidth = 80, settingsHeight = 80, borderWidth = 520, borderHeight = 520;
	private static final int settingsX = (Jogo.WIDTH - settingsWidth - 20), settingsY = 20;
	private static final int borderX = (Jogo.WIDTH - 40 - settingsWidth - borderWidth), borderY = 55;
	private static final int whiteWidth = 365, whiteHeight = Jogo.HEIGHT;
	
	GameScreen(Jogo game, Cores cor, int speed, int intelligence, int strength) {
		this.game = game;
		this.speed = speed;
		this.intelligence = intelligence;
		this.strength = strength;
		background = new Texture("grassBackground.png");
		white = new Texture("white.png");
		border = new Texture("border.png");
		settingsActive = new Texture("settingsAtivo.png");
		settingsInactive = new Texture("settingsInativo.png");
		
		title = new BitmapFont();
		title.getData().setScale(4);
		green = new BitmapFont();
		green.getData().setScale(2);
		red = new BitmapFont();
		red.getData().setScale(2);
		yellow = new BitmapFont();
		yellow.getData().setScale(2);
		blue = new BitmapFont();
		blue.getData().setScale(2);
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
		game.batch.draw(white, 0, 0, whiteWidth, whiteHeight);
		game.batch.draw(border, borderX, borderY, borderWidth, borderHeight);
		
//		if (game.active(settingsX, settingsY, settingsWidth, settingsHeight)) {
//			game.batch.draw(settingsActive, settingsX, settingsY, settingsWidth, settingsHeight);
//			if (game.mouseClick()) {
//				this.dispose();
//				game.playSound();
//				game.setScreen(new Settings(game));
//			}
//		}
//		else {
//			game.batch.draw(settingsInactive, settingsX, settingsY, settingsWidth, settingsHeight);
//		}
		
		game.increaseClick();
		
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

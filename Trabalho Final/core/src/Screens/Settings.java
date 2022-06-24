package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.jogo.Jogo;

public class Settings implements Screen {
	
	Jogo game;
	Texture soundActive, soundInactive, musicActive, musicInactive, background;
	Texture backActive, backInactive;
	private static final int soundWidth = 80, soundHeight = 80, musicWidth = 80, musicHeight = 80;
	private static final int soundX = (Jogo.WIDTH - 2 * soundWidth) / 2, soundY = (Jogo.HEIGHT - soundHeight) / 2;
	private static final int musicX = (Jogo.WIDTH + 2 * musicWidth) / 2, musicY = (Jogo.HEIGHT - musicHeight) / 2;
	private static final int backWidth = 80, backHeight = 80, backX = (Jogo.WIDTH - backWidth - 20), backY = 20;
	private int lastClick;
	
	public Settings(Jogo game) {
		lastClick = 10;
		this.game = game;
		soundActive = new Texture("volumeAtivo.png");
		soundInactive = new Texture("volumeInativo.png");
		musicActive = new Texture("musicaAtivo.png");
		musicInactive = new Texture("musicaInativo.png");
		background = new Texture("MainMenuBackground.jpg");
		backActive = new Texture("backAtivo.png");
		backInactive = new Texture("backInativo.png");
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.draw(background, 0, 0, Jogo.WIDTH, Jogo.HEIGHT);
		
		if (Gdx.input.getX() >= backX && Gdx.input.getX() <= backX + backWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - backY - backHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - backY) {
			game.batch.draw(backActive, backX, backY, backWidth, backHeight);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.playSound();
				game.setScreen(new MainMenu(game));
			}
		}
		else {
			game.batch.draw(backInactive, backX, backY, backWidth, backHeight);
		}
		
		if (this.game.getMusic()) {
			game.batch.draw(musicActive, musicX, musicY, musicWidth, musicHeight);
		}
		else {
			game.batch.draw(musicInactive, musicX, musicY, musicWidth, musicHeight);
		}
		if (this.game.getSound()) {
			game.batch.draw(soundActive, soundX, soundY, soundWidth, soundHeight);
		}
		else {
			game.batch.draw(soundInactive, soundX, soundY, soundWidth, soundHeight);
		}
		if (Gdx.input.getX() >= musicX && Gdx.input.getX() <= musicX + musicWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - musicY - musicHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - musicY && Gdx.input.isTouched() && lastClick >= 10) {
			game.invertMusic();
			game.playSound();
			lastClick = 0;
		}
		if (Gdx.input.getX() >= soundX && Gdx.input.getX() <= soundX + soundWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - soundY - soundHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - soundY && Gdx.input.isTouched() && lastClick >= 10) {
			game.invertSound();
			game.playSound();
			lastClick = 0;
		}
		
		lastClick++;
		
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

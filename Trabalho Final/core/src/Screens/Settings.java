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
	private static final int musicX = (Jogo.WIDTH +  musicWidth) / 2, musicY = (Jogo.HEIGHT - musicHeight) / 2;
	private static final int backWidth = 80, backHeight = 80, backX = (Jogo.WIDTH - backWidth - 20), backY = 20;
	
	public Settings(Jogo game) {
		this.game = game;
		soundActive = new Texture("volumeAtivo.png");
		soundInactive = new Texture("volumeInativo.png");
		musicActive = new Texture("musicaAtivo.png");
		musicInactive = new Texture("musicaInativo.png");
		background = new Texture("blobBackground.png");
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
		
		if (game.active(backX, backY, backWidth, backHeight)) {
			game.batch.draw(backActive, backX, backY, backWidth, backHeight);
			if (game.mouseClick()) {
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
		if (game.active(musicX, musicY, musicWidth, musicHeight) && game.mouseClick()) {
			game.invertMusic();
			game.playSound();
		}
		if (game.active(soundX, soundY, soundWidth, soundHeight) && game.mouseClick()) {
			game.invertSound();
			game.playSound();
		}
		
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

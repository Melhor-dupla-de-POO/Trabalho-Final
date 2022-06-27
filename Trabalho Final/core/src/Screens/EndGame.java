package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.poo.jogo.Jogo;

public class EndGame implements Screen {
	
	private Jogo game;
	private Texture background, exitActive, exitInactive;
	private BitmapFont font;
	private static final int exitWidth = 80, exitHeight = 80, exitX = 20, exitY = 20;
	
	EndGame(Jogo game) {
		this.game = game;
		background = new Texture("grassBackground.png");
		exitActive = new Texture("exitAtivo.png");
		exitInactive = new Texture("exitInativo.png");
		font = new BitmapFont();
		font.getData().setScale(4);
		font.setColor(Color.BLACK);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.draw(background, 0, 0, Jogo.WIDTH, Jogo.HEIGHT);
		if (game.active(exitX, exitY, exitWidth, exitHeight)) {
			game.batch.draw(exitActive, exitX, exitY, exitWidth, exitHeight);
			if (game.mouseClick()) {
				this.dispose();
				game.playSound();
				game.terminaGameplay();
				game.setScreen(new MainMenu(game));
			}
		}
		else {
			game.batch.draw(exitInactive, exitX, exitY, exitWidth, exitHeight);
		}
		
		font.draw(game.batch, "Game Over", 420, 370);

		
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
		font.dispose();
		background.dispose();
		exitActive.dispose();
		exitInactive.dispose();
	}
	
}

package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.poo.jogo.Jogo;

public class Tutorial implements Screen {

	private int tela;
	private static final int nextWidth = 80, nextHeight = 80;
	private static final int nextX = Jogo.WIDTH - nextWidth - 20, nextY = 20;
	Jogo game;
	Texture nextActive, nextInactive;
	
	Tutorial(Jogo game) {
		this.game = game;
		nextActive = new Texture("nextAtivo.png");
		nextInactive = new Texture("nextInativo.png");
	}
	
	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		
		if (this.tela == 0) {
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
		else if (this.tela == 1) {
			Gdx.gl.glClearColor(0, 1, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
		else {
			Gdx.gl.glClearColor(0, 0, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
		
		if (Gdx.input.getX() >= nextX && Gdx.input.getX() <= nextX + nextWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - nextY - nextHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - nextY) {
			game.batch.draw(nextActive, nextX, nextY, nextWidth, nextHeight);
			if (Gdx.input.isTouched()) {
				this.dispose();
				this.tela++;
			}
		}
		else {
			game.batch.draw(nextInactive, nextX, nextY, nextWidth, nextHeight);
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

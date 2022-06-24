package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.poo.jogo.Cores;
import com.poo.jogo.Jogo;

public class PreGame implements Screen {

	Jogo game;
	Texture redInactive, redActive, greenInactive, greenActive;
	Texture blueInactive, blueActive, yellowInactive, yellowActive;
	Texture nextInactive, nextActive;
	BitmapFont font;
	int points = 10;
	boolean species = false;
	Cores cor;
	private static final int nextWidth = 80, nextHeight = 80, blobWidth = 80, blobHeight = 80;
	private static final int nextX = Jogo.WIDTH - nextWidth - 20, nextY = 20;
	private static final int redX = Jogo.WIDTH / 4 - blobWidth - 20, greenX = Jogo.WIDTH / 2 - blobWidth - 20;
	private static final int blueX = 3 * Jogo.WIDTH / 4 - blobWidth - 20, yellowX = Jogo.WIDTH - 20, blobY = 400;
	
	PreGame(Jogo game) {
		this.game = game;
		redInactive = new Texture("singleRed.png");
		redActive = new Texture("redActive.png");
		greenInactive = new Texture("singleGreen.png");
		greenActive = new Texture("greenActive.png");
		blueInactive = new Texture("singleBlue.png");
		blueActive = new Texture("blueActive.png");
		yellowInactive = new Texture("singleYellow.png");
		yellowActive = new Texture("yellowActive.png");
		nextInactive = new Texture("nextInativo.png");
		nextActive = new Texture("nextAtivo.png");
		font = new BitmapFont();
		font.getData().setScale(2);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		if (Gdx.input.getX() >= redX && Gdx.input.getX() <= redX + blobWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - blobY - blobHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - blobY) {
			game.batch.draw(redActive, blobWidth, blobHeight, redX, blobY);
			if (Gdx.input.isTouched()) {
				game.playSound();
				species = true;
				cor = Cores.VERMELHO;
			}
		}
		else {
			game.batch.draw(redInactive, blobWidth, blobHeight, redX, blobY);
		}
		if (Gdx.input.getX() >= greenX && Gdx.input.getX() <= greenX + blobWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - blobY - blobHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - blobY) {
			game.batch.draw(greenActive, blobWidth, blobHeight, greenX, blobY);
			if (Gdx.input.isTouched()) {
				game.playSound();
				species = true;
				cor = Cores.VERDE;
			}
		}
		else {
			game.batch.draw(greenInactive, blobWidth, blobHeight, greenX, blobY);
		}
		if (Gdx.input.getX() >= blueX && Gdx.input.getX() <= blueX + blobWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - blobY - blobHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - blobY) {
			game.batch.draw(blueActive, blobWidth, blobHeight, blueX, blobY);
			if (Gdx.input.isTouched()) {
				game.playSound();
				species = true;
				cor = Cores.AZUL;
			}
		}
		else {
			game.batch.draw(blueInactive, blobWidth, blobHeight, blueX, blobY);
		}
		if (Gdx.input.getX() >= yellowX && Gdx.input.getX() <= yellowX + blobWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - blobY - blobHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - blobY) {
			game.batch.draw(yellowActive, blobWidth, blobHeight, yellowX, blobY);
			if (Gdx.input.isTouched()) {
				game.playSound();
				species = true;
				cor = Cores.AMARELO;
			}
		}
		else {
			game.batch.draw(yellowInactive, blobWidth, blobHeight, yellowX, blobY);
		}
		
		if (species && points == 0) {
			if (Gdx.input.getX() >= nextX && Gdx.input.getX() <= nextX + nextWidth 
					&& Gdx.input.getY() >= Jogo.HEIGHT - nextY - nextHeight && 
					Gdx.input.getY() <= Jogo.HEIGHT - nextY) {
				game.batch.draw(nextActive, nextX, nextY, nextWidth, nextHeight);
				if (Gdx.input.isTouched()) {
					this.dispose();
					game.playSound();
					game.setScreen(new GameScreen(game));
				}
			}
			else {
				game.batch.draw(nextActive, nextX, nextY, nextWidth, nextHeight);
			}
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

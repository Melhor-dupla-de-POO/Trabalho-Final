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
	Texture nextInactive, nextActive, minusInactive, minusActive;
	Texture plusActive, plusInactive, speed, brain, biceps, background;
	BitmapFont font;
	private int points = 10;
	boolean species = false;
	private static final int abilitySize = 80, abilityY = 200;
	private static final int sep = (Jogo.WIDTH - 9 * abilitySize) / 10, nxt = sep + abilitySize;
	private static final int X1 = sep, X2 = X1 + nxt, X3 = X2 + nxt, X4 = X3 + nxt, X5 = X4 + nxt;
	private static final int X6 = X5 + nxt, X7 = X6 + nxt, X8 = X7 + nxt, X9 = X8 + nxt;
	private static final int nextWidth = 80, nextHeight = 80, blobWidth = 80, blobHeight = 80;
	private static final int nextX = Jogo.WIDTH - nextWidth - 20, nextY = 20;
	private static final int dist = (Jogo.WIDTH - 4 * blobWidth) / 5;
	private static final int redX = dist, greenX = redX + blobWidth + dist;
	private static final int blueX = greenX + blobWidth + dist;
	private static final int yellowX = blueX + blobWidth + dist, blobY = 450;
	
	PreGame(Jogo game) {
		this.game = game;
		redInactive = new Texture("singleRed.png");
		redActive = new Texture("redAtivo.png");
		greenInactive = new Texture("singleGreen.png");
		greenActive = new Texture("greenAtivo.png");
		blueInactive = new Texture("singleBlue.png");
		blueActive = new Texture("blueAtivo.png");
		yellowInactive = new Texture("singleYellow.png");
		yellowActive = new Texture("yellowAtivo.png");
		nextInactive = new Texture("nextInativo.png");
		nextActive = new Texture("nextAtivo.png");
		minusInactive = new Texture("minusInativo.png");
		minusActive = new Texture("minusAtivo.png");
		plusInactive = new Texture("plusInativo.png");
		plusActive = new Texture("plusAtivo.png");
		speed = new Texture("speed.png");
		brain = new Texture("brain.png");
		biceps = new Texture("biceps.png");
		background = new Texture("grassBackground.png");
		font = new BitmapFont();
		font.getData().setScale(2);
		font.setColor(0, 0, 0, 1);
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
		
		font.draw(game.batch, "Choose your species:   " + (species ? game.getCor().toString() : ""), redX, blobY + blobWidth + 50);
		if (species) {
			font.draw(game.batch, String.valueOf(points) + " points to distribute:"
					+ "       (Each attribute has a maximum of 10)", redX, blobY - 60);
			font.draw(game.batch, "Speed: " + String.valueOf(game.getSpeed()), X2 - 20, abilityY - 60);
			font.draw(game.batch, "Intelligence: " + String.valueOf(game.getIntelligence()), X5 - 40, abilityY - 60);
			font.draw(game.batch, "Strength: " + String.valueOf(game.getStrength()), X8 - 25, abilityY - 60);
			
			
			if (game.active(X1, abilityY, abilitySize, abilitySize) && game.getSpeed() > 0) {
				game.batch.draw(minusActive, X1 + 10, abilityY, abilitySize, abilitySize);
				if (game.mouseClick()) {
					game.playSound();
					game.setSpeed(-1);
					points++;
				}
			}
			else {
				game.batch.draw(minusInactive, X1 + 10, abilityY, abilitySize, abilitySize);
			}
			if (game.active(X3, abilityY, abilitySize, abilitySize) && game.getSpeed() < 10 && points > 0) {
				game.batch.draw(plusActive, X3 - 10, abilityY, abilitySize, abilitySize);
				if (game.mouseClick()) {
					game.playSound();
					game.setSpeed(1);
					points--;
				}
			}
			else {
				game.batch.draw(plusInactive, X3 - 10, abilityY, abilitySize, abilitySize);
			}
			if (game.active(X4, abilityY, abilitySize, abilitySize) && game.getIntelligence() > 0) {
				game.batch.draw(minusActive, X4 + 10, abilityY, abilitySize, abilitySize);
				if (game.mouseClick()) {
					game.playSound();
					game.setIntelligence(-1);
					points++;
				}
			}
			else {
				game.batch.draw(minusInactive, X4 + 10, abilityY, abilitySize, abilitySize);
			}
			if (game.active(X6, abilityY, abilitySize, abilitySize) && game.getIntelligence() < 10 && points > 0) {
				game.batch.draw(plusActive, X6 - 10, abilityY, abilitySize, abilitySize);
				if (game.mouseClick()) {
					game.playSound();
					game.setIntelligence(1);
					points--;
				}
			}
			else {
				game.batch.draw(plusInactive, X6 - 10, abilityY, abilitySize, abilitySize);
			}
			if (game.active(X7, abilityY, abilitySize, abilitySize) && game.getStrength() > 0) {
				game.batch.draw(minusActive, X7 + 10, abilityY, abilitySize, abilitySize);
				if (game.mouseClick()) {
					game.playSound();
					game.setStrength(-1);
					points++;
				}
			}
			else {
				game.batch.draw(minusInactive, X7 + 10, abilityY, abilitySize, abilitySize);
			}
			if (game.active(X9, abilityY, abilitySize, abilitySize) && game.getStrength() < 10 && points > 0) {
				game.batch.draw(plusActive, X9 - 10, abilityY, abilitySize, abilitySize);
				if (game.mouseClick()) {
					game.playSound();
					game.setStrength(1);
					points--;
				}
			}
			else {
				game.batch.draw(plusInactive, X9 - 10, abilityY, abilitySize, abilitySize);
			}
			
			game.batch.draw(speed, X2, abilityY, abilitySize, abilitySize);
			game.batch.draw(brain, X5, abilityY, abilitySize, abilitySize);
			game.batch.draw(biceps, X8, abilityY, abilitySize, abilitySize);
		}
		
		if (game.active(redX, blobY, blobWidth, blobHeight)) {
			game.batch.draw(redActive, redX, blobY, blobWidth, blobHeight);
			if (game.mouseClick()) {
				game.playSound();
				species = true;
				game.setCor(Cores.VERMELHO);
			}
		}
		else {
			game.batch.draw(redInactive, redX, blobY, blobWidth, blobHeight);
		}
		if (game.active(greenX, blobY, blobWidth, blobHeight)) {
			game.batch.draw(greenActive, greenX, blobY, blobWidth, blobHeight);
			if (game.mouseClick()) {
				game.playSound();
				species = true;
				game.setCor(Cores.VERDE);
			}
		}
		else {
			game.batch.draw(greenInactive, greenX, blobY, blobWidth, blobHeight);
		}
		if (game.active(blueX, blobY, blobWidth, blobHeight)) {
			game.batch.draw(blueActive, blueX, blobY, blobWidth, blobHeight);
			if (game.mouseClick()) {
				game.playSound();
				species = true;
				game.setCor(Cores.AZUL);
			}
		}
		else {
			game.batch.draw(blueInactive, blueX, blobY, blobWidth, blobHeight);
		}
		if (game.active(yellowX, blobY, blobWidth, blobHeight)) {
			game.batch.draw(yellowActive, yellowX, blobY, blobWidth, blobHeight);
			if (game.mouseClick()) {
				game.playSound();
				species = true;
				game.setCor(Cores.AMARELO);
			}
		}
		else {
			game.batch.draw(yellowInactive, yellowX, blobY, blobWidth, blobHeight);
		}
		
		if (species && points == 0) {
			if (game.active(nextX, nextY, nextWidth, nextHeight)) {
				game.batch.draw(nextActive, nextX, nextY, nextWidth, nextHeight);
				if (game.mouseClick()) {
					this.dispose();
					game.playSound();
					game.setScreen(new GameScreen(game));
				}
			}
			else {
				game.batch.draw(nextInactive, nextX, nextY, nextWidth, nextHeight);
			}
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

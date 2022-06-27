package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.poo.jogo.Jogo;

public class Tutorial implements Screen {

	private int tela, moment;
	private static final int nextWidth = 80, nextHeight = 80;
	private static final int nextX = Jogo.WIDTH - nextWidth - 20, nextY = 20;
	private Jogo game;
	private Texture nextActive, nextInactive, background, preTela, speed, intelligence, strength;
	private Texture yellow, green, blue, red;
	private Texture[] animation;
	private BitmapFont font;
	
	Tutorial(Jogo game) {
		this.game = game;
		animation = new Texture[4];
		animation[0] = new Texture("animation1.png");
		animation[1] = new Texture("animation2.png");
		animation[2] = new Texture("animation3.png");
		animation[3] = new Texture("animation4.png");
		nextActive = new Texture("nextAtivo.png");
		nextInactive = new Texture("nextInativo.png");
		background = new Texture("grassBackground.png");
		preTela = new Texture("pretela.jpg");
		speed = new Texture("speed.png");
		intelligence = new Texture("brain.png");
		strength = new Texture("biceps.png");
		yellow = new Texture("singleYellow.png");
		blue = new Texture("singleBlue.png");
		green = new Texture("singleGreen.png");
		red = new Texture("singleRed.png");
		
		font = new BitmapFont();
		font.getData().setScale(2.6f);
		font.setColor(Color.BLACK);
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
		
		if (this.tela == 0) {
			font.draw(game.batch, "At the beginning, you will choose your species:" , 100, Jogo.HEIGHT - 50);
			game.batch.draw(yellow, 140, Jogo.HEIGHT - 225, 100, 100);
			game.batch.draw(red, 380, Jogo.HEIGHT - 225, 100, 100);
			game.batch.draw(blue, 620, Jogo.HEIGHT - 225, 100, 100);
			game.batch.draw(green, 860, Jogo.HEIGHT - 225, 100, 100);
			font.draw(game.batch, "and distribute 10 poins between:", 100, Jogo.HEIGHT - 300);
			game.batch.draw(speed, 200, Jogo.HEIGHT - 475, 100, 100);
			game.batch.draw(intelligence, 500, Jogo.HEIGHT - 475, 100, 100);
			game.batch.draw(strength, 800, Jogo.HEIGHT - 475, 100, 100);
			font.draw(game.batch, "Speed", 193, Jogo.HEIGHT - 500);
			font.draw(game.batch, "Intelligence", 460, Jogo.HEIGHT - 500);
			font.draw(game.batch, "Strength", 780, Jogo.HEIGHT - 500);
		}
		else if (this.tela == 1) {
			font.draw(game.batch, "Every round the creatures will go out to seek food\n" 
					+ "If they find\n"
					+ "0 food: They die\n"
					+ "1 food: They survive\n"
					+ "2 food: They survive and reproduce\n\n"
					+ "During reproduction, mutation can happen, which means\n"
					+ "the new creature's attributes can differ a bit", 100, 600);
			game.batch.draw(animation[(moment % 40) / 10], 100, 100, 400, 100);
			moment++;
		}
		else if (this.tela == 2){
			game.batch.draw(speed, 50, Jogo.HEIGHT - 120, 100, 100);
			font.draw(game.batch, "Speed: creatures with more speed move faster", 200, Jogo.HEIGHT - 55);
			game.batch.draw(intelligence, 50, Jogo.HEIGHT - 240, 100, 100);
			font.draw(game.batch, "Intelligence: Smarter creatures can \nsee food from farther away", 200, Jogo.HEIGHT - 150);
			game.batch.draw(strength, 50, Jogo.HEIGHT - 360, 100, 100);
			font.draw(game.batch, "Strength: Stronger creatures can kill\nthe weaker and steal their food", 200, Jogo.HEIGHT - 275);
			font.draw(game.batch, "But there's a catch: the faster,\nsmarter and stronger a creature is,\nthe less energy it has,\n"
					+ "which means it will stop moving earlier", 50, Jogo.HEIGHT - 420);
		}
		else if (this.tela == 3) {
			font.draw(game.batch, "You will be able to see a few statistics during the game\n" 
					+ "such as how many creatures are alive\n"
					+ "and the average attributes for each species\n\n"
					+ "The game ends if your species has gone extinct\n"
					+ "or if it is the only one left\n\n"
					+ "And that's it!\n"
					+ "Now try to find a good starting distribution\n"
					+ "and enjoy watching as the ecossystem evolves!", 100, 600);
		}
		else {
			this.dispose();
			game.setScreen(new MainMenu(game));
		}
		
		if (Gdx.input.getX() >= nextX && Gdx.input.getX() <= nextX + nextWidth 
				&& Gdx.input.getY() >= Jogo.HEIGHT - nextY - nextHeight && 
				Gdx.input.getY() <= Jogo.HEIGHT - nextY) {
			game.batch.draw(nextActive, nextX, nextY, nextWidth, nextHeight);
			if (game.mouseClick()) {
				this.tela++;
				game.playSound();
			}
		}
		else {
			game.batch.draw(nextInactive, nextX, nextY, nextWidth, nextHeight);
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

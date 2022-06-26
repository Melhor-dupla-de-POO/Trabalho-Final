package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.poo.jogo.Cores;
import com.poo.jogo.Jogo;

public class GameScreen implements Screen {
	
	Jogo game;
	Texture background, white, border, settingsInactive, settingsActive;
	Texture[] board;
	BitmapFont title;
	BitmapFont[] cores;
	int[][] matrix;
	int[] order;
	private int dist = 20;
	private static final int boardSize = 10;
	private static final int settingsWidth = 80, settingsHeight = 80, borderWidth = 540, borderHeight = 540;
	private static final int settingsX = (Jogo.WIDTH - settingsWidth - 20), settingsY = 20;
	private static final int borderX = (Jogo.WIDTH - 40 - settingsWidth - borderWidth), borderY = 55;
	private static final int whiteWidth = 365, whiteHeight = Jogo.HEIGHT, textX = 20;
	
	GameScreen(Jogo game) {
		this.game = game;
		background = new Texture("grassBackground.png");
		white = new Texture("white.png");
		border = new Texture("border.png");
		settingsActive = new Texture("settingsAtivo.png");
		settingsInactive = new Texture("settingsInativo.png");
		board = new Texture[10];
		board[0] = new Texture("empty.png");
		board[1] = new Texture("food.png");
		board[2] = new Texture("singleYellow.png");
		board[3] = new Texture("doubleYellow.png");
		board[4] = new Texture("singleRed.png");
		board[5] = new Texture("doubleRed.png");
		board[6] = new Texture("singleGreen.png");
		board[7] = new Texture("doubleGreen.png");
		board[8] = new Texture("singleBlue.png");
		board[9] = new Texture("doubleBlue.png");
		
		title = new BitmapFont();
		title.setColor(Color.BLACK);
		title.getData().setScale(4);
		cores = new BitmapFont[4];
		for (int i = 0; i < 4; i++) {
			cores[i] = new BitmapFont();
			cores[i].getData().setScale(1.8f);
		}
		cores[0].getData().setScale(2.7f);
		
		// RED
		cores[0].setColor(Color.RED);
		
		// GREEN
		cores[1].setColor(Color.GREEN);
		
		// BLUE
		cores[2].setColor(Color.CYAN);
		
		// YELLOW
		cores[3].setColor(Color.YELLOW);
		
		order = new int[4];
		order[0] = Cores.VERMELHO.getId();
		order[1] = Cores.VERDE.getId();
		order[2] = Cores.AZUL.getId();
		order[3] = Cores.AMARELO.getId();
		
		switch (this.game.getCor()) {
			case VERMELHO:
				break;
			case VERDE:
				cores[1].setColor(Color.RED);
				cores[0].setColor(Color.GREEN);
				order[0] = Cores.VERDE.getId();
				order[1] = Cores.VERMELHO.getId();
				break;
			case AZUL:
				cores[2].setColor(Color.RED);
				cores[0].setColor(Color.CYAN);
				order[0] = Cores.AZUL.getId();
				order[2] = Cores.VERMELHO.getId();
				break;
			case AMARELO:
				cores[3].setColor(Color.RED);
				cores[0].setColor(Color.YELLOW);
				order[0] = Cores.AMARELO.getId();
				order[3] = Cores.VERMELHO.getId();
				break;
		}
		
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		if (game.getRound() == 51) {
			this.dispose();
			game.setScreen(new MainMenu(game));
		}
		
		game.batch.draw(background, 0, 0, Jogo.WIDTH, Jogo.HEIGHT);
		game.batch.draw(white, 0, 0, whiteWidth, whiteHeight);
		game.batch.draw(border, borderX, borderY, borderWidth, borderHeight);
		// game.batch.draw(white, borderX + 20, borderY + 20, borderWidth - 40, borderHeight - 40);
		
		matrix = game.getTabuleiro().getMatriz();
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				game.batch.draw(board[matrix[i][j]], borderX + 20 + 10 * j, 
						borderY + 510 - 10 * i, boardSize, boardSize);
			}
		}
		
		dist = 20;
		title.draw(game.batch, "Round " + String.valueOf(game.getRound()), textX, Jogo.HEIGHT - dist);
		dist += 60;
		int[] number;
		float[] speed, intelligence, strength;
		number = game.getStats().getQuantidade();
		speed = game.getStats().getVelocidade();
		intelligence = game.getStats().getInteligencia();
		strength = game.getStats().getTamanho();
		cores[0].draw(game.batch, "Creatures: " + String.valueOf(number[order[0]])
				+ "\nSpeed: " + String.format("%.3f", speed[order[0]])
				+ "\nIntelligence: " + String.format("%.3f", intelligence[order[0]])
				+ "\nStrength: " + String.format("%.3f", strength[order[0]]), textX, Jogo.HEIGHT - dist);
		dist += 65;
		for (int i = 1; i < 4; i++) {
			dist += 125;
			cores[i].draw(game.batch, "Creatures: " + String.valueOf(number[order[i]])
			+ "\nSpeed: " + String.format("%.3f", speed[order[i]])
			+ "\nIntelligence: " + String.format("%.3f", intelligence[order[i]])
			+ "\nStrength: " + String.format("%.3f", strength[order[i]]), textX, Jogo.HEIGHT - dist);
		}
		
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

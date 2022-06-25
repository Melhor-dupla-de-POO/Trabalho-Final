package Screens;

import com.badlogic.gdx.Screen;
import com.poo.jogo.Cores;
import com.poo.jogo.Jogo;

public class GameScreen implements Screen {
	
	Jogo game;
	private Cores cor;
	private int speed, intelligence, strength;
	
	GameScreen(Jogo game, Cores cor, int speed, int intelligence, int strength) {
		this.game = game;
		this.speed = speed;
		this.intelligence = intelligence;
		this.strength = strength;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

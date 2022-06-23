package com.poo.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.MainMenu;

public class Jogo extends Game {
	
	public static final int WIDTH = 1000, HEIGHT = 600;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));
	}
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
	}
}

package com.poo.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.MainMenu;

public class Jogo extends Game {
	
	public static final int WIDTH = 1100, HEIGHT = 650;
	public SpriteBatch batch;
	private Sound sound;
	private Music music;
	private boolean isMusic, isSound;
	
	@Override
	public void create () {
		isMusic = isSound = true;
		batch = new SpriteBatch();
		sound = Gdx.audio.newSound(Gdx.files.internal("mixkit-arcade-game-jump-coin-216.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("alex-productions-extreme-trap-racing-music-power.mp3"));
		this.setScreen(new MainMenu(this));
		
		music.setLooping(true);
		music.play();
	}
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
	}
	
	public boolean getSound() {
		return this.isSound;
	}
	
	public void invertSound() {
		if (this.isSound) {
			this.isSound = false;
		}
		else {
			this.isSound = true;
		}
	}
	
	public boolean getMusic() {
		return this.isMusic;
	}
	
	public void invertMusic() {
		if (this.isMusic) {
			music.pause();
			this.isMusic = false;
		}
		else {
			this.isMusic = true;
			music.play();
		}	
	}
	
	public void playSound() {
		if (this.isSound)
			sound.play();
	}
}

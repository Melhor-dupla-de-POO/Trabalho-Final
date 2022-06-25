package com.poo.jogo;

import java.util.Random;

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
	private int lastClick = 0, speedPoints, intelligencePoints, strengthPoints;
	private Cores cor;
	
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
	
	public boolean mouseClick() {
		if (lastClick >= 15 && Gdx.input.isTouched()) {;
			lastClick = 0;
			return true;
		}
		return false;
	}
	
	public boolean active(int x, int y, int w, int h) {
		return Gdx.input.getX() >= x && Gdx.input.getX() <= x + w
				&& Gdx.input.getY() >= Jogo.HEIGHT - y - h && 
				Gdx.input.getY() <= Jogo.HEIGHT - y;
	}
	
	public void increaseClick() {
		lastClick++;
	}
	
	public int getSpeed() {
		return speedPoints;
	}
	
	public void setSpeed(int dt) {
		speedPoints += dt;
	}
	
	public int getStrength() {
		return strengthPoints;
	}
	
	public void setStrength(int dt) {
		strengthPoints += dt;
	}
	
	public int getIntelligence() {
		return intelligencePoints;
	}
	
	public void setIntelligence(int dt) {
		intelligencePoints += dt;
	}
	
	public Cores getCor() {
		return cor;
	}
	
	public void setCor(Cores cor) {
		this.cor = cor;
	}
	
	public int[][] matrix() {
		Random rand = new Random();
		int[][] ans = new int[50][50];
		for (int i = 0; i < 50; i++) {
			ans[i] = new int[50];
			for (int j = 0; j < 50; j++) {
				int nxt = rand.nextInt(50);
				if (nxt >= 11) nxt = 0;
				else if (nxt >= 10) nxt = 1;
				ans[i][j] = nxt;
			}
		}
		return ans;
	}
}

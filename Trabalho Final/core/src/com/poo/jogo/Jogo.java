package com.poo.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.MainMenu;

public class Jogo extends Game {
	
	// Classe central do programa
	// Responsavel por renderizar a tela atual e conectar as Screens com as operacoes do Tabuleiro
	public static final int WIDTH = 1100, HEIGHT = 650, celulas = 50;
	public static final int qtdInicial = 50, baseSpeed = 12;
	public static final float duration = 15, rounds = 100;
	public SpriteBatch batch;
	private Sound sound;
	private Music music;
	private boolean isMusic, isSound, isGame;
	private int lastClick = 0, speedPoints, intelligencePoints, strengthPoints, rodada;
	private Cores cor;
	private float time;
	private Tabuleiro tabuleiro;
	private int instante;
	
	
	@Override
	public void create () {
		tabuleiro = new Tabuleiro(celulas);
		isMusic = isSound = false;
		batch = new SpriteBatch();
		sound = Gdx.audio.newSound(Gdx.files.internal("mixkit-arcade-game-jump-coin-216.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("alex-productions-extreme-trap-racing-music-power.mp3"));
		this.setScreen(new MainMenu(this));
		
		music.setLooping(true);
		if (isMusic)
			music.play();
	}
	public void render () {
		
		// Renderiza a tela atual
		super.render();
		if (isGame)
			jogar();
	}
	
	public void jogar() {
		// Se estamos no meio do jogo, ele chama o tabuleiro para jogar as rodadas
		
		if (time == 0) {
			rodada++;
			tabuleiro.iniciaRodada(rodada);
		}
		time += Gdx.graphics.getDeltaTime();
		if (time > duration) {
			// finaliza a rodada atual e inicia a proxima
			
			tabuleiro.encerraRodada();
			instante = 0;
			time = 0;
			
		}
		else if (rodada <= rounds){
			// Roda um instante da rodada
			
			tabuleiro.jogaInstante(instante);
			instante++;
		}
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
		if (lastClick >= 10 && Gdx.input.isTouched()) {;
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
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public void iniciaGameplay() {
		CriaEspecies.setAuto(tabuleiro, cor, baseSpeed - speedPoints, intelligencePoints, strengthPoints);
		time = 0;
		isGame = true;
	}
	
	public void inverteGameplay() {
		isGame ^= true;
	}
	
	public void terminaGameplay() {
		tabuleiro.limpaCampo();
		isGame = false;
		instante = 0;
		time = 0;
		speedPoints = 0;
		intelligencePoints = 0;
		strengthPoints = 0;
		rodada = 0;
	}
	
	public Estatisticas getStats() {
		return Estatisticas.getStats(tabuleiro);
	}
	
	public int getRound() {
		return rodada;
	}
}

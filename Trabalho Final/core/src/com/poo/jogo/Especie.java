package com.poo.jogo;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public abstract class Especie {
	private int velocidade, inteligencia, tamanho;
	private int energiaUsada;
	protected int x, y;
	private float energia, speedWeight = 5, intelligenceWeight = 10, strengthWeight = 5;
	private Cores cor;
	private int comida;
	protected Tabuleiro tabuleiro;
	private boolean andou;
	private static float mutacao = 0.2f;
	
	Especie(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro, Cores cor) {
		this.x = x;
		this.y = y;
		this.velocidade = velocidade;
		this.inteligencia = inteligencia;
		this.tamanho = tamanho;
		this.tabuleiro = tabuleiro;
		this.cor = cor;
		this.andou = false;

		this.energia = this.calcEnergia(this.velocidade, this.inteligencia, this.tamanho, this.cor);
	}
	
	Especie(Especie pai) {
		Random rand = new Random();
		velocidade = pai.velocidade;
		inteligencia = pai.inteligencia;
		tamanho = pai.tamanho;
		comida = 0;
		energiaUsada = 0;
		
		if (rand.nextFloat() < mutacao) {
			if (rand.nextBoolean() && velocidade > Jogo.baseSpeed - 10)
				velocidade--;
			else if (velocidade < Jogo.baseSpeed)
				velocidade++;
		}
		if (rand.nextFloat() < mutacao) {
			if (rand.nextBoolean() && inteligencia > 0)
				inteligencia--;
			else if (inteligencia < 10)
				inteligencia++;
		}
		if (rand.nextFloat() < mutacao) {
			if (rand.nextBoolean() && tamanho > 0)
				tamanho--;
			else if (tamanho < 10)
				tamanho++;
		}
		tabuleiro = pai.tabuleiro;
		cor = pai.cor;
		andou = false;

		energia = this.calcEnergia(this.velocidade, this.inteligencia, this.tamanho, this.cor);
	}
	
	public boolean getAndou() {
		return this.andou;
	}
	
	public void setAndou(boolean andou) {
		this.andou = andou;
	}
	
	public Cores getCor() {
		return this.cor;
	}
	
	public int getComida() {
		return this.comida;
	}
	
	public float getEnergia() {
		return this.energia;
	}
	
	public Especie ataca(Especie atacado) {
		if(this.tamanho >= atacado.tamanho) {
			this.ganhaComida(atacado.getComida());
			return this;
		}
		else {
			atacado.ganhaComida(this.getComida());
			return atacado;
		}
	}
	
	public void ganhaComida(int quantidade) {
		this.comida = this.comida + quantidade;
		if(this.comida > 2) this.comida = 2;
	}
	
	public int[] getPos() {
		int[] pos = new int[2];
		pos[0] = x; pos[1] = y;
		return pos;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPos(int x, int y) {
		setX(x); setY(y);
	}
	
	public void setPos(int[] arr) {
		setPos(arr[0], arr[1]);
	}
	
	public int getInteligencia() {
		return this.inteligencia;
	}
	
	public int getVelocidade() {
		return this.velocidade;
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	public void anda() {
		tabuleiro.mover(this);
	}
	
	public void resetaEnergiaUsada() {
		this.energiaUsada = 0;
	}
	
	public void usaEnergia() {
		this.energiaUsada++;
	}
	
	public void setComida(int comida) {
		this.comida = comida;
	}
	
	public boolean devoAndar(int round) {
		if(this.andou) return false;
		if(this.energiaUsada >= this.energia) return false;
		if(round % velocidade != 0) return false;
		return true;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract void posicaoInicial();
	
	// AJEITAR ISSO DAQUI
	public float calcEnergia(int velocidade, int inteligencia, int tamanho, Cores cor) {
		
		// O calculo sera um valor padrao menos peso * pontos
		// Podemos tornar a relacao quadratica e aumentar o peso inicial
		return Jogo.duration / Gdx.graphics.getDeltaTime() 
				+ speedWeight * Tabuleiro.min(2, velocidade) * Tabuleiro.min(2, velocidade)
				+ intelligenceWeight * Tabuleiro.min(2,  inteligencia)
				+ strengthWeight * Tabuleiro.min(2,  tamanho) * Tabuleiro.min(2, tamanho)
				- speedWeight * (Jogo.baseSpeed - velocidade) * (Jogo.baseSpeed - velocidade)
				- intelligenceWeight * inteligencia
				- strengthWeight * tamanho * tamanho;
	}
}

package com.poo.jogo;

import java.util.Random;

public abstract class Especie {
	private int velocidade, inteligencia, energia, tamanho;
	private int energiaUsada;
	private int x, y;
	private Cores cor;
	private int comida;
	private Tabuleiro tabuleiro;
	private boolean andou;
	private static float mutacao = 0.1f;
	
	Especie(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro, Cores cor) {
		this.x = x;
		this.y = y;
		this.velocidade = velocidade;
		this.inteligencia = inteligencia;
		this.tamanho = tamanho;
		this.tabuleiro = tabuleiro;
		this.cor = cor;
		this.andou = false;

		this.energia = this.calcEnergia(this.velocidade, this.inteligencia, this.tamanho);
	}
	
	Especie(Especie pai) {
		Random rand = new Random();
		velocidade = pai.velocidade;
		inteligencia = pai.inteligencia;
		tamanho = pai.tamanho;
		cor = pai.cor;
		comida = 0;
		
		if (rand.nextFloat() < mutacao) {
			if (rand.nextBoolean() && velocidade > 10)
				velocidade--;
			else
				velocidade++;
		}
		if (rand.nextFloat() < mutacao) {
			if (rand.nextBoolean() && inteligencia > 0)
				inteligencia--;
			else
				inteligencia++;
		}
		if (rand.nextFloat() < mutacao) {
			if (rand.nextBoolean() && tamanho > 0)
				tamanho--;
			else
				tamanho++;
		}
		tabuleiro = pai.tabuleiro;
		cor = pai.cor;
		andou = false;

		energia = this.calcEnergia(this.velocidade, this.inteligencia, this.tamanho);
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
	
	public int getEnergia() {
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
	
	public boolean devoAndar(int round) {
		if(this.andou) return false;
		if(this.energiaUsada == this.energia) return false;
		if(round % velocidade != 0) return false;
		return true;
	}
	
	public void encerraRodada() {
		if(this.getComida() == 0) {
			tabuleiro.removeCriatura(this);
		}
		else if(this.getComida() == 2) {
			Especie filho = this;
			switch (this.getCor()) {
				case AMARELO:
					filho = new Amarelo(this);
					break;
				case AZUL:
					filho = new Azul(this);
					break;
				case VERMELHO:
					filho = new Vermelho(this);
					break;
				case VERDE:
					filho = new Verde(this);
					break;
			}
			tabuleiro.adicionaCriatura(filho);
		}
	}
	
	// AJEITAR ISSO DAQUI
	public int calcEnergia(int velocidade, int inteligencia, int tamanho) {
		return 50;
	}
}

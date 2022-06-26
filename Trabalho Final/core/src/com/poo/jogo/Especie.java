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
	
	Especie(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro, Cores cor) {
		this.x = x;
		this.y = y;
		this.velocidade = velocidade;
		this.inteligencia = inteligencia;
		this.tamanho = tamanho;
		this.tabuleiro = tabuleiro;
		this.cor = cor;
		this.andou = false;
		
		// pensar direito em como fazer isso dps
		this.energia = 1000 - inteligencia - velocidade - tamanho;
	}
	Especie(Especie pai) {
		Random rand = new Random();
		this.x = pai.x;
		this.y = pai.y;
		this.velocidade = pai.velocidade + (rand.nextBoolean() ? rand.nextInt() : -rand.nextInt());
		this.inteligencia = pai.inteligencia + (rand.nextBoolean() ? rand.nextInt() : -rand.nextInt());
		this.tamanho = pai.tamanho +(rand.nextBoolean() ? rand.nextInt() : -rand.nextInt());
		this.tabuleiro = pai.tabuleiro;
		this.cor = pai.cor;
		this.andou = false;
		
		// pensar direito em como fazer isso dps
		this.energia = 1000 - this.inteligencia - this.velocidade - this.tamanho;
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
			Especie filho = new Especie(this);
			tabuleiro.adicionaCriatura(filho);
		}
	}
}

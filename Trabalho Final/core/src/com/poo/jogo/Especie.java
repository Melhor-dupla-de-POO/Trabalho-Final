package com.poo.jogo;

import java.util.Random;

public abstract class Especie {
	private float velocidade, inteligencia, energia, tamanho;
	private int x, y;
	private Cores cor;
	private int comida;
	private Tabuleiro tabuleiro;
	
	Especie(int x, int y, float velocidade, float inteligencia, float tamanho, Tabuleiro tabuleiro, Cores cor) {
		this.x = x;
		this.y = y;
		this.velocidade = velocidade;
		this.inteligencia = inteligencia;
		this.tamanho = tamanho;
		this.tabuleiro = tabuleiro;
		this.cor = cor;
		
		// pensar direito em como fazer isso dps
		this.energia = 1000 - inteligencia - velocidade - tamanho;
	}
	Especie(Especie pai) {
		Random rand = new Random();
		this.x = pai.x;
		this.y = pai.y;
		this.velocidade = pai.velocidade + (rand.nextBoolean() ? rand.nextFloat() : -rand.nextFloat());
		this.inteligencia = pai.inteligencia + (rand.nextBoolean() ? rand.nextFloat() : -rand.nextFloat());
		this.tamanho = pai.tamanho +(rand.nextBoolean() ? rand.nextFloat() : -rand.nextFloat());
		this.tabuleiro = pai.tabuleiro;
		this.cor = pai.cor;
		
		// pensar direito em como fazer isso dps
		this.energia = 1000 - this.inteligencia - this.velocidade - this.tamanho;
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
	
	public float getInteligencia() {
		return this.inteligencia;
	}
	
	public void anda() {
		tabuleiro.mover(this);
	}
	
}

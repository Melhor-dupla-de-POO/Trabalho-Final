package com.poo.jogo;

import java.util.Random;

import com.badlogic.gdx.Gdx;

public abstract class Especie {
	private int velocidade, inteligencia, tamanho;
	private int energiaUsada;
	protected int x, y;
	private float energia, speedWeight = 3f, intelligenceWeight = 5f, strengthWeight = 5f;
	private Cores cor;
	private int comida;
	protected Tabuleiro tabuleiro;
	private boolean andou;
	private static float mutacao = 0.4f;
	
	
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
	
	public Especie ataca(Especie atacado) {
		if(this.tamanho >= atacado.tamanho) {
			this.ganhaComida(atacado.getComida());
			this.ganhaComida(1);
			return this;
		}
		else {
			atacado.ganhaComida(this.getComida());
			atacado.ganhaComida(1);
			return atacado;
		}
	}
	
	public void ganhaComida(int quantidade) {
		this.comida = this.comida + quantidade;
		if(this.comida > 2) this.comida = 2;
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
		if(this.energiaUsada >= this.energia) return false;
		if(round % velocidade != 0) return false;
		return true;
	}
	
	public abstract void posicaoInicial();
	
	public float calcEnergia(int velocidade, int inteligencia, int tamanho, Cores cor) {
		
		// O calculo sera um valor padrao menos peso * pontos
		return Jogo.duration / Gdx.graphics.getDeltaTime() 
				+ speedWeight * Tabuleiro.min(2, velocidade) * Tabuleiro.min(2,  velocidade)
				+ intelligenceWeight * Tabuleiro.min(2,  inteligencia)
				+ strengthWeight * Tabuleiro.min(2,  tamanho)
				- speedWeight * (Jogo.baseSpeed - velocidade) * (Jogo.baseSpeed - velocidade)
				- intelligenceWeight * (inteligencia * (inteligencia + 1)) / 2
				- strengthWeight * (tamanho * (tamanho + 1)) / 2;
	}
	
	//Setters e Getters
	
	public void setComida(int comida) {
		this.comida = comida;
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
	
	public void setAndou(boolean andou) {
		this.andou = andou;
	}
	
	public boolean getAndou() {
		return this.andou;
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
	
	public int[] getPos() {
		int[] pos = new int[2];
		pos[0] = x; pos[1] = y;
		return pos;
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
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public int[] getIntelligenceArray() {
		int[] ans = new int[4];
		switch (inteligencia) {
			case 0:
				ans[0] = 0;
				ans[1] = 0;
				ans[2] = 0;
				ans[3] = 0;
				break;
			case 1:
				ans[0] = 1;
				ans[1] = 1;
				ans[2] = 1;
				ans[3] = 1;
				break;
			case 2:
				ans[0] = 2;
				ans[1] = 2;
				ans[2] = 1;
				ans[3] = 1;
				break;
			case 3:
				ans[0] = 2;
				ans[1] = 3;
				ans[2] = 2;
				ans[3] = 2;
				break;
			case 4:
				ans[0] = 3;
				ans[1] = 3;
				ans[2] = 3;
				ans[3] = 3;
				break;
			case 5:
				ans[0] = 4;
				ans[1] = 4;
				ans[2] = 4;
				ans[3] = 3;
				break;
			case 6:
				ans[0] = 4;
				ans[1] = 4;
				ans[2] = 5;
				ans[3] = 5;
				break;
			case 7:
				ans[0] = 5;
				ans[1] = 5;
				ans[2] = 5;
				ans[3] = 5;
				break;
			case 8:
				ans[0] = 5;
				ans[1] = 6;
				ans[2] = 6;
				ans[3] = 6;
				break;
			case 9:
				ans[0] = 6;
				ans[1] = 7;
				ans[2] = 6;
				ans[3] = 7;
				break;
			case 10:
				ans[0] = 7;
				ans[1] = 7;
				ans[2] = 7;
				ans[3] = 7;
				break;
			
		}
		return ans;
	}
	
}

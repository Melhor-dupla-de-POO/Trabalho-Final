package com.poo.jogo;

import java.util.Random;

public class Vermelho extends Especie {

	Vermelho(Especie pai) {
		super(pai);
	}

	Vermelho(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.VERMELHO);
	}

	public void posicaoInicial() {
		Random rand = new Random();
		if (rand.nextBoolean()) {
			int random = rand.nextInt(this.tabuleiro.tam / 2 - 2) + 1;
			this.x = random; this.y = this.tabuleiro.tam - 1;
		}
		else {
			int random = rand.nextInt(this.tabuleiro.tam / 2 - 2) + this.tabuleiro.tam / 2 + 1;
			this.x = random; this.y = 0;
		}
	}
	
}

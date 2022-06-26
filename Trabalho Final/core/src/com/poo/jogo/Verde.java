package com.poo.jogo;

import java.util.Random;

public class Verde extends Especie {

	Verde(Especie pai) {
		super(pai);
	}

	Verde(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.VERDE);
	}

	public void posicaoInicial() {
		Random rand = new Random();
		int random = rand.nextInt(this.tabuleiro.tam - 1) + 1;
		this.x = this.tabuleiro.tam - 1; this.y = random;
	}
	
	
}

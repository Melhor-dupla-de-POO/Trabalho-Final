package com.poo.jogo;

import java.util.Random;

public class Azul extends Especie {

	Azul(Especie pai) {
		super(pai);
	}

	Azul(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.AZUL);
	}

	public void posicaoInicial() {
		Random rand = new Random();
		int random = rand.nextInt(this.tabuleiro.tam - 1) + 1;
		this.x = random; this.y = 0;
	}
	
	
}

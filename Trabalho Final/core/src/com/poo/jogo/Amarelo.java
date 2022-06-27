package com.poo.jogo;

import java.util.Random;

public class Amarelo extends Especie {

	Amarelo(Especie pai) {
		super(pai);
	}
	
	Amarelo(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.AMARELO);
	}

	public void posicaoInicial() {
		Random rand = new Random();
		int random = rand.nextInt(this.tabuleiro.tam - 2) + 1;
		this.x = random; this.y = 0;
	}
	
}

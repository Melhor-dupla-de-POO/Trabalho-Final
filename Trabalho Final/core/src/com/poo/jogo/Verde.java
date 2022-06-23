package com.poo.jogo;

public class Verde extends Especie {

	Verde(Especie pai) {
		super(pai);
	}

	Verde(int x, int y, float velocidade, float inteligencia, float tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.VERDE);
	}

}

package com.poo.jogo;

public class Azul extends Especie {

	Azul(Especie pai) {
		super(pai);
	}

	Azul(int x, int y, float velocidade, float inteligencia, float tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.AZUL);
	}

}

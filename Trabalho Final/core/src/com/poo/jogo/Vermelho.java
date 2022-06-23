package com.poo.jogo;

public class Vermelho extends Especie {

	Vermelho(Especie pai) {
		super(pai);
	}

	Vermelho(int x, int y, float velocidade, float inteligencia, float tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.VERMELHO);
	}

}

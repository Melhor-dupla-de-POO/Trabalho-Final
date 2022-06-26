package com.poo.jogo;

public class Amarelo extends Especie {

	Amarelo(Especie pai) {
		super(pai);
	}
	
	Amarelo(int x, int y, int velocidade, int inteligencia, int tamanho, Tabuleiro tabuleiro) {
		super(x, y, velocidade, inteligencia, tamanho, tabuleiro, Cores.AMARELO);
	}

}

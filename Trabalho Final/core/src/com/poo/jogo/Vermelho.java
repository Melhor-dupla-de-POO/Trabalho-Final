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
		int random = rand.nextInt(this.tabuleiro.tam - 1) + 1;
		this.x = random; this.y = this.tabuleiro.tam - 1;
	}
	
	
}

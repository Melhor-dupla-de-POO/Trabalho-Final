package com.poo.jogo;

public class Rodada {
	// Responsavel por rodar as rodadas
	
	public void playRound(Tabuleiro tab, int round) {
		
		tab.resetaCriaturas();
		for(int i = 0; i < round; i++) {
			tab.jogaRodada(i);
		}
		
		// Rodar as x rodadas usando o procedimento descrito no docs
		// Primeiro temos que criar funções nas classes Tabuleiro, Celula e Especie que vao movimentar
		// as criaturas, comer as comidas, etc
	}
	
}

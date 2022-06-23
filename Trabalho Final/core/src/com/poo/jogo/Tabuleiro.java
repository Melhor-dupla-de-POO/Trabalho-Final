package com.poo.jogo;

public class Tabuleiro {
	int tam;
	Celula[][] campo;
	
	Tabuleiro(int tam) {
		this.tam = tam;
		for (int i = 0; i < tam; i++) {
			this.campo[i] = new Celula[tam];
			for (int j = 0; j < tam; j++) {
				this.campo[i][j] = new Celula();
			}
		}
	}
	
	public void limpaCampo() {
		for (int i = 0; i < tam; i++) {
			for (int j = 0; j < tam; j++) {
				this.campo[i][j] = new Celula();
			}
		}
	}
	
	public void mover(Especie criatura) {
		
	}
	
}

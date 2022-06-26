package com.poo.jogo;

public class CriaEspecies {
	
	public static void setAuto(Tabuleiro tab) {
		int[] quantidade = {50, 50, 50, 50}, velocidade = {4, 4, 4, 4}, 
				inteligencia = {3, 3, 3, 3}, tamanho = {3 , 3, 3, 3};
		settar(quantidade, velocidade, inteligencia, tamanho, tab);
	}
	
	public static void settar(int[] quantidade, int[] velocidade, int[] inteligencia, int[] tamanho, Tabuleiro tab) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < quantidade[i]; j++) {
				Especie novo;
				if(i == 0) {
					novo = new Vermelho(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				else if(i == 1) {
					novo = new Verde(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				else if(i == 2) {
					novo = new Azul(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				else {
					novo = new Amarelo(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				novo.posicaoInicial();
				tab.adicionaCriatura(novo);
			}
		}
	}
}

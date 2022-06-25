package com.poo.jogo;

import java.util.*;

public class Estatisticas {
	private static Estatisticas estatisticas = null;
	
	int[] quantidade, velocidade, inteligencia, energia, tamanho;
	Tabuleiro tab;
	
	private Estatisticas(Tabuleiro tab) {
		quantidade = new int[4];;
		velocidade = new int[4];
		inteligencia = new int[4];
		energia = new int[4];
		tamanho = new int[4];
		this.tab = tab;
	}
	
	private void atualiza() {
		for(int i = 0; i < 4; i++) {
			quantidade[i] = 0;
			velocidade[i] = 0;
			inteligencia[i] = 0;
			energia[i] = 0;
			tamanho[i] = 0;
		}
		ArrayList<Especie> lista = tab.getCriaturas();
		for(Especie i : lista) {
			int id = 0;
			switch(i.getCor()) {
				case AMARELO:
					id = 0;
					break;
				case VERMELHO:
					id = 1;
					break;
				case VERDE:
					id = 2;
					break;
				case AZUL:
					id = 3;
					break;
			}
			quantidade[id]++;
			velocidade[id] += i.getVelocidade();
			inteligencia[id] += i.getInteligencia();
			energia[id] += i.getEnergia();
			tamanho[id] += i.getTamanho();
		}
	}
	
	public static Estatisticas getStats(Tabuleiro tab) {
		if(estatisticas == null) {
			estatisticas = new Estatisticas(tab);
		}
		estatisticas.atualiza();
		return estatisticas;
	}
	
	
}

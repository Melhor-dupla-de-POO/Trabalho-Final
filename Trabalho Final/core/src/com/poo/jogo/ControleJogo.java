package com.poo.jogo;

import java.util.ArrayList;

public class ControleJogo {
	
	public ArrayList<Especie> constroiCriaturas() {
		ArrayList<Especie> criaturas = new ArrayList<Especie>();
		
		// Receber o input
		float velocidadeEscolhida = 0, inteligenciaEscolhida = 0, tamanhoEscolhido = 0;
		Cores corEscolhida = Cores.AMARELO;
		
		float novaVelocidade, novaInteligencia, novoTamanho, velocidadePadrao = 0, inteligenciaPadrao = 0, tamanhoPadrao = 0;
		for (Cores cor : Cores.values()) {
			if (cor == corEscolhida) {
				novaVelocidade = velocidadeEscolhida;
				novaInteligencia = inteligenciaEscolhida;
				novoTamanho = tamanhoEscolhido;
			}
			else {
				novaVelocidade = velocidadePadrao;
				novaInteligencia = inteligenciaPadrao;
				novoTamanho = tamanhoPadrao;
			}
			switch (cor) {
				case AMARELO:
					criaturas.add(new Amarelo(novaVelocidade, novaInteligencia, cor));
				case VERMELHO:
					criaturas.add(new Vermelho(novaVelocidade, novaInteligencia, cor));
				case VERDE:
					criaturas.add(new Verde(novaVelocidade, novaInteligencia, cor));
				case AZUL:
					criaturas.add(new Azul(novaVelocidade, novaInteligencia, cor));
			}
		}
		
		return criaturas;
	}
}

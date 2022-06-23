package com.poo.jogo;

import java.util.ArrayList;

public class App {
	
	public static void main(String[] args) {
		
		ControleJogo controle = new ControleJogo();
		ArrayList<Especie> criaturas = controle.constroiCriaturas();
		Tabuleiro tabuleiro = new Tabuleiro();
		
		// Cada rodada
		for (int i = 0; i < 100; i++) {
			
		}
	}

}

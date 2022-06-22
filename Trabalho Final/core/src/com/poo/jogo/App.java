package com.poo.jogo;

import java.util.ArrayList;

public class App {
	
	public static void main(String[] args) {
		
		ControleJogo controle = new ControleJogo();
		ArrayList<Especie> criaturas = controle.constroiCriaturas();
		
	}

}

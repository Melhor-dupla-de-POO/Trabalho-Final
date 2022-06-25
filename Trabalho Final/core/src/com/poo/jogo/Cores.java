package com.poo.jogo;

public enum Cores {
	AMARELO, VERMELHO, VERDE, AZUL;
	
	public String toString() {
		switch (this.name()) {
			case "AMARELO":
				return "Yellow";
			case "VERMELHO":
				return "Red";
			case "VERDE":
				return "Green";
			case "AZUL":
				return "Blue";
			default:
				return "";
		}
	}
	
	public int getId() {
		switch (this.name()) {
			case "AMARELO":
				return 0;
			case "VERMELHO":
				return 1;
			case "VERDE":
				return 2;
			case "AZUL":
				return 3;
			default:
				return 4;
		}
	}
}

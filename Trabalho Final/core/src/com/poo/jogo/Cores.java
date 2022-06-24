package com.poo.jogo;

public enum Cores {
	AMARELO, VERMELHO, VERDE, AZUL;
	
	public String toString() {
		if (this.name() == "AMARELO")
			return "Yellow";
		if (this.name() == "VERMELHO")
			return "Red";
		if (this.name() == "VERDE") 
			return "Green";
		if (this.name() == "AZUL")
			return "Blue";
		return "";
	}
}

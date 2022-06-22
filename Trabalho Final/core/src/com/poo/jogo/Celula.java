package com.poo.jogo;

import java.util.ArrayList;
import java.util.Random;

public class Celula {
	boolean comida;
	
	ArrayList<Especie> criaturas;
	
	void resetaComida() {
		Random rand = new Random();
		int rnumb = rand.nextInt(10);
		if(rnumb == 0) this.comida = true;
		else this.comida = false;
	}
	 
}

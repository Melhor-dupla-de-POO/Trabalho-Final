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
	
	public boolean getComida() {
		return this.comida;
	}
	
	public boolean adicionaCriatura(Especie criatura) {
		boolean add;
		if(this.criaturas.size() == 0) {
			this.criaturas.add(criatura);
			add = true;
		}
		else {
			Especie primeiro = this.criaturas.get(0);
			if(primeiro.getCor() == criatura.getCor()) {
				this.criaturas.add(criatura);
				add = true;
			}
			else {
				if(this.criaturas.size() > 1) {
					add = false;
				}
				else {
					this.criaturas.clear();
					this.criaturas.add(criatura.ataca(primeiro));
					add = true;
				}
			}
		}
		if(add && this.getComida()) {
			this.criaturas.get(0).ganhaComida(1);
		}
		return add;
	}
	
	public void removeCriatura(Especie criatura) {
		this.criaturas.remove(criatura);
	}
}

package com.poo.jogo;

import java.util.ArrayList;
import java.util.Random;

public class Celula {
	private boolean comida;
	
	private ArrayList<Especie> criaturas;
	
	void resetaComida() {
		Random rand = new Random();
		int rnumb = rand.nextInt(10);
		if(rnumb == 0) this.comida = true;
		else this.comida = false;
	}
	
	public ArrayList<Especie> getList() {
		return criaturas;
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
	
	public void iniciaRodada() {
		this.resetaComida();
		for(Especie i : this.criaturas) {
			i.resetaEnergiaUsada();
		}
	}
	
	public void encerraRodada() {
		for(Especie i : this.criaturas) {
			i.encerraRodada();
		}
	}
	
	public void resetaEspecies() {
		for(int i = 0; i < criaturas.size(); i++) {
			criaturas.get(i).setAndou(false);
		}
	}
	
	public void joga(Tabuleiro tab, int round) {
		for(int i = 0; i < this.criaturas.size(); i++) {
			if(this.criaturas.get(i).devoAndar(round)) {
				tab.mover(this.criaturas.get(i));
			}
		}
	}
	
	public void removeCriatura(Especie criatura) {
		this.criaturas.remove(criatura);
	}
	
	public int converteInt() {
		if (criaturas.isEmpty()) {
			if (comida) return 1;
			return 0;
		}
		Cores cor = criaturas.get(0).getCor();
		int id = cor.getId();
		if (criaturas.size() == 1) {
			return 2 * id + 2;
		}
		return 2 * id + 3;
	}
}

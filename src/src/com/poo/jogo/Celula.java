package com.poo.jogo;

import java.util.ArrayList;
import java.util.Random;

public class Celula {
	private boolean comida;
	
	private ArrayList<Especie> criaturas;
	
	Celula() {
		this.criaturas = new ArrayList<Especie>();
		comida = false;
	}
	
	void resetaComida(boolean cm, int round) {
		
		// Conforme o tempo passa, a comida fica mais escassa
		Random rand = new Random();
		int rnumb = rand.nextInt(10 + round * (round / 15 + 1));
		if(rnumb == 0 && cm) this.comida = true;
		else this.comida = false;
	}
	
	public Especie primeiro() {
		for(Especie i : criaturas) {
			if(i.getAndou() == false) return i;
		}
		return null;
	}
	
	public boolean free(Cores cor, int sz) {
		return (criaturas.size() == 0 || criaturas.get(0).getCor() == cor || (criaturas.size() == 1 && criaturas.get(0).getTamanho() <= sz));
	}
	
	public boolean mata(Cores cor, int sz) {
		return (criaturas.size() == 1 && criaturas.get(0).getCor() != cor && criaturas.get(0).getTamanho() <= sz);
	}
	
	public boolean adicionaCriatura(Especie criatura) {
		
		// Adiciona a criatura na celula, lidando com a comida e a morte de inimigos
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
		if(add && this.getComida() && criatura.getComida() < 2) {
			criatura.ganhaComida(1);
			this.comida = false;
		}
		return add;
	}
	
	public void iniciaRodada(boolean cm, int round) {
		for(Especie i : this.criaturas) {
			i.resetaEnergiaUsada();
		}
		this.criaturas.clear();
		this.resetaComida(cm, round);
	}
	
	public void encerraRodada() {
		
		// Encerra a rodada e reproduz/mata as criaturas
		ArrayList<Especie> adicionar = new ArrayList<Especie>();
		for(Especie i : this.criaturas) {
			if(i.getComida() == 2) {
				Especie filho = null;
				switch (i.getCor()) {
					case AMARELO:
						filho = new Amarelo(i);
						break;
					case AZUL:
						filho = new Azul(i);
						break;
					case VERMELHO:
						filho = new Vermelho(i);
						break;
					case VERDE:
						filho = new Verde(i);
						break;
				}
				adicionar.add(filho);
				adicionar.add(i);
			}
			else if(i.getComida() == 1){
				adicionar.add(i);
			}
			i.setComida(0);
		}
		this.criaturas.clear();
		for(Especie i : adicionar) {
			adicionaCriatura(i);
		}
	}
	
	public void resetaEspecies() {
		for(int i = 0; i < criaturas.size(); i++) {
			criaturas.get(i).setAndou(false);
		}
	}
	
	public void joga(Tabuleiro tab, int round) {
		for(Especie i : criaturas) {
			if(i.devoAndar(round)) {
				tab.mover(i);
			}
		}
	}
	
	public boolean inList(Especie criatura) {
		return (criaturas.contains(criatura));
	}
	
	public void removeCriatura(Especie criatura) {
		this.criaturas.remove(criatura);
	}
	
	public int converteInt() {
		
		// Converte a celula em um inteiro pra facilitar a animacao da interface grafica
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
	
	public ArrayList<Especie> getList() {
		return criaturas;
	}
	
	public boolean getComida() {
		return this.comida;
	}
	
}

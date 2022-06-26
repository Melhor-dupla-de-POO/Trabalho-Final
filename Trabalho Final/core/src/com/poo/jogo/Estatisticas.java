package com.poo.jogo;

import java.util.*;

public class Estatisticas {
	private static Estatisticas estatisticas = null;
	
	private int[] quantidade;
	private float[] velocidade, inteligencia, energia, tamanho;
	Tabuleiro tab;
	
	private Estatisticas(Tabuleiro tab) {
		quantidade = new int[4];;
		velocidade = new float[4];
		inteligencia = new float[4];
		energia = new float[4];
		tamanho = new float[4];
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
			int id = i.getCor().getId();
			quantidade[id]++;
			velocidade[id] += i.getVelocidade() - 10;
			inteligencia[id] += i.getInteligencia();
			energia[id] += i.getEnergia();
			tamanho[id] += i.getTamanho();
		}
		for (int i = 0; i < 4; i++) {
			velocidade[i] /= quantidade[i];
			inteligencia[i] /= quantidade[i];
			energia[i] /= quantidade[i];
			tamanho[i] /= quantidade[i];
		}
	}
	
	public static Estatisticas getStats(Tabuleiro tab) {
		if(estatisticas == null) {
			estatisticas = new Estatisticas(tab);
		}
		estatisticas.atualiza();
		return estatisticas;
	}
	
	public int[] getQuantidade() {
		return this.quantidade;
	}
	
	public float[] getVelocidade() {
		return this.velocidade;
	}
	
	public float[] getInteligencia() {
		return this.inteligencia;
	}
	
	public float[] getEnergia() {
		return this.energia;
	}
	
	public float[] getTamanho() {
		return this.tamanho;
	}
	
}

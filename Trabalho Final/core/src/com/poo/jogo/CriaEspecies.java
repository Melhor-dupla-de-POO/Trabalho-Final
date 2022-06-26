package com.poo.jogo;

public class CriaEspecies {
	
	public static Cores cor;
	public static int velocidade, inteligencia, tamanho;
	
	public static void settar(Cores cor, int velocidade, int inteligencia, int tamanho) {
		CriaEspecies.cor = cor;
		CriaEspecies.velocidade = velocidade;
		CriaEspecies.inteligencia = inteligencia;
		CriaEspecies.tamanho = tamanho;
	}
}

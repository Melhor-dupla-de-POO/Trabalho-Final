package com.poo.jogo;

import java.util.ArrayList;
import java.util.Random;

public class CriaEspecies {
	
	public static void setAuto(Tabuleiro tab, Cores cor, int speedPoints, int intelligencePoints, int strengthPoints) {
		
		// Setta os atributos iniciais das 4 especies
		// A especie escolhida vai ter os atributos escolhidos pelo usuario
		// As nao escolhidas vao ter atributos aleatorios, de modo que cada atributo é no max 1
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 8; i++) {
			for (int j = i + 1; j <= 9; j++) {
				int speed = i, intelligence = j - i, strength = 10 - j;
				list.add(100 * speed + 10 * intelligence + strength);
			}
		}
		Random rand = new Random();
		int[] choose = new int[4];
		for (int i = 0; i < 4; i++) {
			int id = rand.nextInt(list.size());
			choose[i] = list.get(id);
		}
		int[] quantidade = {Jogo.qtdInicial, Jogo.qtdInicial, Jogo.qtdInicial, Jogo.qtdInicial},
				velocidade = {Jogo.baseSpeed - choose[0] / 100, Jogo.baseSpeed - choose[1] / 100,
						Jogo.baseSpeed - choose[2] / 100, Jogo.baseSpeed - choose[3] / 100}, 
				inteligencia = {(choose[0] / 10) % 10, (choose[1] / 10) % 10, 
						(choose[2] / 10) % 10, (choose[3] / 10) % 10},
				tamanho = {choose[0] % 10 , choose[1] % 10, choose[2] % 10, choose[3] % 10};
		int id = cor.getId();
		velocidade[id] = speedPoints;
		inteligencia[id] = intelligencePoints;
		tamanho[id] = strengthPoints;
		
		settar(quantidade, velocidade, inteligencia, tamanho, tab);
	}
	
	public static void settar(int[] quantidade, int[] velocidade, int[] inteligencia, int[] tamanho, Tabuleiro tab) {
		
		// Cria as criaturas iniciais de cada especie
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < quantidade[i]; j++) {
				Especie novo;
				if(i == 0) {
					novo = new Amarelo(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				else if(i == 1) {
					novo = new Vermelho(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				else if(i == 2) {
					novo = new Verde(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				else {
					novo = new Azul(0, 0, velocidade[i], inteligencia[i], tamanho[i], tab);
				}
				novo.posicaoInicial();
				tab.adicionaCriatura(novo);
			}
		}
	}
}

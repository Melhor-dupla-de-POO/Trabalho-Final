package com.poo.jogo;

import java.util.Random;

public abstract class Especie {
	private float velocidade, inteligencia, energia;
	
	Especie(float velocidade, float inteligencia) {
		this.velocidade = velocidade;
		this.inteligencia = inteligencia;
		
		// pensar direito em como fazer isso dps
		this.energia = 1000 - inteligencia - velocidade;
	}
	Especie(Especie pai) {
		Random rand = new Random();
		this.velocidade = pai.velocidade + (rand.nextBoolean() ? rand.nextFloat() : -rand.nextFloat());
		this.inteligencia = pai.inteligencia + (rand.nextBoolean() ? rand.nextFloat() : -rand.nextFloat());
		
		// pensar direito em como fazer isso dps
		this.energia = 1000 - this.inteligencia - this.velocidade;
	}
}

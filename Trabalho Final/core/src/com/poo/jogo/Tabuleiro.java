package com.poo.jogo;

import java.util.*;
import java.lang.Integer;

public class Tabuleiro {
	int tam;
	Celula[][] campo;
	
	Tabuleiro(int tam) {
		this.tam = tam;
		campo = new Celula[tam][tam];
		for (int i = 0; i < tam; i++) {
			this.campo[i] = new Celula[tam];
			for (int j = 0; j < tam; j++) {
				this.campo[i][j] = new Celula();
			}
		}
	}
	
	public void limpaCampo() {
		for (int i = 0; i < tam; i++) {
			for (int j = 0; j < tam; j++) {
				this.campo[i][j] = new Celula();
			}
		}
	}
	
	public ArrayList<Especie> getCriaturas() {
		ArrayList<Especie> total = new ArrayList<Especie>();
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				total.addAll(this.campo[i][j].getList());
			}
		}
		return total;
	}
	
	public void resetaCriaturas() {
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				this.campo[i][j].resetaEspecies();
			}
		}
	}
	
	public void iniciaRodada() {
		ArrayList<Especie> temp = this.getCriaturas();
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				boolean cm = true;
				if(i < 4 || i > tam - 4 || j < 4 || j > tam - 4) cm = false;
				this.campo[i][j].iniciaRodada(cm);
			}
		}
		for(Especie i : temp) {
			i.posicaoInicial();
			this.adicionaCriatura(i);
		}
	}
	
	public void encerraRodada() {
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				this.campo[i][j].encerraRodada();
			}
		}
	}
	
	public void jogaInstante(int instant) {
		this.resetaCriaturas();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				list.add(i * tam + j);
			}
		}
		Collections.shuffle(list);
		for (Integer x : list) {
			int i = x / tam, j = x % tam;
			Especie cur = campo[i][j].primeiro();
			while(cur != null) {
				if(cur.devoAndar(instant)) {
					this.mover(cur);
				}
				cur.setAndou(true);
				cur.usaEnergia();
				cur = campo[i][j].primeiro();
			}
		}
	}
	
	public boolean in_board(int x, int y) {
		if(x < 0 || y < 0 || x >= tam || y >= tam) return false;
		return true;
	}
	
	public void adicionaCriatura(Especie criatura) {
		int x = criatura.getPos()[0], y = criatura.getPos()[1];
		this.campo[x][y].adicionaCriatura(criatura);
	}
	
	public static int abs(int x) {
		if (x < 0) return -1 * x;
		return x;
	}
	
	public static int max(int a, int b) { 
		if (a >= b) return a;
		return b;
	}
	
	public static int min(int a, int b) {
		if (a <= b) return a;
		return b;
	}
	
	public void mover(Especie criatura) {
		int x = criatura.getPos()[0], y = criatura.getPos()[1];
		int inteligencia = criatura.getInteligencia();
		int cx = -1, cy = -1;
		int mndist = 1123456789;
		boolean matar = false;
		int[] mexe = criatura.getIntelligenceArray();
		for (int i = max(0, x - mexe[0]); i <= min(tam - 1, x + mexe[1]); i++) {
			for (int j = max(0, y - mexe[2]); j <= min(tam - 1, y + mexe[3]); j++) {
				if (this.campo[i][j].getComida() && abs(x - i) + abs(y - j) < mndist) {
					mndist = abs(x - i) + abs(y - j);
					cx = i;
					cy = j;
				}
			}
		}
		
		ArrayList<Integer> directions = new ArrayList<Integer>();
		
		// Ir atras de comida que esta no campo de visao da criatura
		if(cx != -1 && criatura.getComida() < 2) {
			if(cx < x && in_board(x - 1, y) && this.campo[x - 1][y].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(0);
			}
			if(cy < y && in_board(x, y - 1) && this.campo[x][y - 1].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(1);
			}
			if(cx > x && in_board(x + 1, y) && this.campo[x + 1][y].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(2);
			}
			if(cy > y && in_board(x, y + 1) && this.campo[x][y + 1].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(3);
			}
		}
		
		// Matar os inimigos
		else {
			if(in_board(x - 1, y) && this.campo[x - 1][y].free(criatura.getCor(), criatura.getTamanho()) 
					&& this.campo[x - 1][y].mata(criatura.getCor(), criatura.getTamanho())) {
				directions.add(0);
			}
			else if(in_board(x, y - 1) && this.campo[x][y - 1].free(criatura.getCor(), criatura.getTamanho()) 
					&& this.campo[x][y - 1].mata(criatura.getCor(), criatura.getTamanho())) {
				directions.add(1);
			}
			else if(in_board(x + 1, y) && this.campo[x + 1][y].free(criatura.getCor(), criatura.getTamanho()) 
					&& this.campo[x + 1][y].mata(criatura.getCor(), criatura.getTamanho())) {
				directions.add(2);
			}
			else if(in_board(x, y + 1) && this.campo[x][y + 1].free(criatura.getCor(), criatura.getTamanho()) 
					&& this.campo[x][y + 1].mata(criatura.getCor(), criatura.getTamanho())) {
				directions.add(3);
			}
		}
		
		// Andar random com peso pro meio do tabuleiro
		if (directions.isEmpty()) {
			if(in_board(x - 1, y) && this.campo[x - 1][y].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(0);
				if (criatura.getCor() == Cores.VERMELHO) {
					directions.add(0);
				}
				if (x > tam / 2) {
					directions.add(0);
				}
				
			}
			if(in_board(x, y - 1) && this.campo[x][y - 1].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(1);
				if (criatura.getCor() == Cores.VERDE) {
					directions.add(1);
				}
				if (y > tam / 2) {
					directions.add(1);
				}
			}
			if(in_board(x + 1, y) && this.campo[x + 1][y].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(2);
				if (criatura.getCor() == Cores.AMARELO) {
					directions.add(2);
				}
				if (x < tam / 2) {
					directions.add(2);
				}
			}
			if(in_board(x, y + 1) && this.campo[x][y + 1].free(criatura.getCor(), criatura.getTamanho())) {
				directions.add(3);
				if (criatura.getCor() == Cores.AZUL) {
					directions.add(3);
				}
				if (y < tam / 2) {
					directions.add(3);
				}
			}
		}
		if (directions.isEmpty()) {
			return;
		}
        Random rand = new Random();
        int move = directions.get(rand.nextInt(directions.size()));
        int vx = x, vy = y;
        if (move == 0) vx--;
        if (move == 1) vy--;
        if (move == 2) vx++;
        if (move == 3) vy++;
		this.campo[x][y].removeCriatura(criatura);
		this.campo[vx][vy].adicionaCriatura(criatura);
		criatura.setX(vx); criatura.setY(vy);
	}
	
	public void removeCriatura(Especie criatura) {
		int x = criatura.getPos()[0], y = criatura.getPos()[1];
		this.campo[x][y].removeCriatura(criatura);
	}
	
	public int[][] getMatriz() {
		int[][] matriz = new int[tam][tam];
		for (int i = 0; i < tam; i++) {
			matriz[i] = new int[tam];
			for (int j = 0; j < tam; j++) matriz[i][j] = campo[i][j].converteInt();
		}
		return matriz;
	}
	
}

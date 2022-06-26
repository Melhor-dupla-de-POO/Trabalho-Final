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
				if(i == 0 || i == tam - 1 || j == 0 || j == tam - 1) cm = false;
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
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				Especie cur = campo[i][j].primeiro();
				while(cur != null) {
					if(cur.devoAndar(instant)) {
						this.mover(cur);
					}
					cur = campo[i][j].primeiro();
				}
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
	
	public void mover(Especie criatura) {
		int x = criatura.getPos()[0], y = criatura.getPos()[1];
		/*int xat = x;
		float energia = criatura.getEnergia();
		int cx = -1, cy = -1;
		int mndist = 1123456789;
		while(xat > 0 && (xat - 1 - x) *(xat - 1 - x) <= energia * energia) xat--;
		while((xat - x) * (xat - x) <= energia * energia) {
			int yat = y;
			while(yat >= 0 && (yat - y) * (yat - y) + (xat - x) * (xat - x) <= energia * energia) {
				if(this.campo[xat][yat].getComida()) {
					if((yat - y) * (yat - y) + (xat - x) * (xat - x) < mndist) {
						mndist = (yat - y) * (yat - y) + (xat - x) * (xat - x);
						cx = xat; cy = yat;
					}
				}
				yat--;
			}
			yat = y;
			while(yat < tam && (yat - y) * (yat - y) + (xat - x) * (xat - x) <= energia * energia) {
				if(this.campo[xat][yat].getComida()) {
					if((yat - y) * (yat - y) + (xat - x) * (xat - x) < mndist) {
						mndist = (yat - y) * (yat - y) + (xat - x) * (xat - x);
						cx = xat; cy = yat;
					}
				}
				yat++;
			}
		}
		ArrayList<Integer> directions = new ArrayList<>();
		if(cx != -1) {
			if(cx < x) directions.add(0);
			if(cy < y) directions.add(1);
			if(cx > x) directions.add(2);
			if(cy > y) directions.add(3);
		}
		else {
			directions.add(0);
			directions.add(1);
			directions.add(2);
			directions.add(3);
		}
        Collections.shuffle(directions);
        for(Integer random_d : directions) {
        	int vx = x, vy = y;
        	if(random_d == 0) vx--;
        	if(random_d == 1) vy--;
        	if(random_d == 2) vx++;
        	if(random_d == 3) vy++;
        	if(in_board(vx, vy)) {
        		boolean ok = this.campo[vx][vy].adicionaCriatura(criatura);
				if(ok) {
					criatura.usaEnergia();
					this.campo[x][y].removeCriatura(criatura);
					break;
				}
        	}
        }*/
		this.campo[x][y].removeCriatura(criatura);
		criatura.setAndou(true);
		if(x < 49) criatura.setX(x + 1);
		this.campo[criatura.getPos()[0]][criatura.getPos()[1]].adicionaCriatura(criatura);
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

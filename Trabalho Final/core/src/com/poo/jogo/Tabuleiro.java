package com.poo.jogo;

import java.util.*;
import java.lang.Integer;

public class Tabuleiro {
	int tam;
	Celula[][] campo;
	
	Tabuleiro(int tam) {
		this.tam = tam;
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
	
	public boolean in_board(int x, int y) {
		if(x < 0 || y < 0 || x >= tam || y >= tam) return false;
		return true;
	}
	
	public void mover(Especie criatura) {
		int x = criatura.getPos()[0], y = criatura.getPos()[1];
		int xat = x;
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
					this.campo[x][y].removeCriatura(criatura);
					break;
				}
        	}
        }
	}
	
}

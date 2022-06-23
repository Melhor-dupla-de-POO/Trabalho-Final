package com.poo.jogo;

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
	
	public void mover(Especie criatura) {
		int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
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
		if(cx != -1) {
			
		}
	}
	
}

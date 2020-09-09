package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Bullet {
	private int posX;
	private int posY;
	Image bullet = new ImageIcon("resources/bullet.png").getImage();

	public Bullet(int x, int y) {
		this.posX = x;
		this.posY = y + 7;
	}

	void step(int dist) {
		this.posX += dist;
	}

	void drawAndMoveBullet(Entorno entorno) {
		entorno.dibujarImagen(bullet, this.posX, this.posY, 0);
		step(5);
	}

	int getPosX() {
		return this.posX;
	}

	int getPosY() {
		return this.posY;
	}

}
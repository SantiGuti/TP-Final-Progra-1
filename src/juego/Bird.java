package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Bird {
	private int posY;
	private int posX;
	private int diameter;
	private float angle;
	Image bird = new ImageIcon("resources/Bird.png").getImage();
	Image aug = new ImageIcon("resources/aug.png").getImage();
	Entorno entorno;

	public Bird(Entorno entorno) {
		this.posY = 300;
		this.posX = 200;
		this.diameter = 25;
		this.entorno = entorno;
		this.angle = 0;
	}

	void drawBird() {
		entorno.dibujarImagen(bird, this.posX, this.posY, angle);
		entorno.dibujarImagen(aug, this.posX, this.posY + 7, 0, .6);
	}

	void drawAndMoveBird() {
		drawBird();
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			this.posY -= 3;
			if (angle > -.60) {
				this.angle -= .08;
			}
		} else {
			this.posY += 3;
			if (angle < .60) {
				this.angle += .08;
			}
		}
		if (this.posY <= 0) {
			this.posY = 0;
		}
	}

	boolean isOffScreen() {
		if (this.posY > 610) {
			return true;
		}
		return false;
	}

	int getPosY() {
		return this.posY;
	}

	void setPosY(int Y) {
		this.posY = Y;
	}

	int getPosX() {
		return this.posX;
	}

	int getDiameter() {
		return this.diameter;
	}

}

package juego;

import java.awt.Image;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class Barrier {
	private int posX;
	private Pipe sup;
	private Pipe inf;
	Image imageSup = new ImageIcon("resources/pipeSup.png").getImage();
	Image imageInf = new ImageIcon("resources/pipeInf.png").getImage();

	public Barrier() {
		this.posX = 860;
		this.sup = new Pipe(0);
		this.inf = new Pipe(sup.getNumber());
	}

	void step(int dist) {
		this.posX -= dist;
	}

	boolean Collision(int posX, int posY, int alto, int ancho) {
		return ((posX - ancho / 2 <= this.posX + 40) && (posX + ancho / 2 >= this.posX - 40)
				&& ((posY - alto / 2 <= this.sup.getPosY() + 200) || posY + alto / 2 >= this.inf.getPosY() - 200));
	}

	void drawBarrier(Entorno entorno) {
		entorno.dibujarImagen(imageSup, this.posX, this.sup.getPosY(), 0);
		entorno.dibujarImagen(imageInf, this.posX, this.inf.getPosY(), 0);
		if (this.posX <= -40) {
			this.posX = 860;
			this.sup = new Pipe(0);
			this.inf = new Pipe(sup.getNumber());
		}
	}

	void drawAndMoveBarrier(Entorno entorno) {
		step(2);
		drawBarrier(entorno);

	}

	Pipe getInf() {
		return this.inf;
	}

	Pipe getSup() {
		return this.sup;
	}

	int getPosX() {
		return this.posX;
	}

	void setPosX(int X) {
		this.posX = X;
	}
}
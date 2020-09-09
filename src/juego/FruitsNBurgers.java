package juego;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import entorno.Entorno;

public class FruitsNBurgers {
	private Random Y = new Random();
	private int posY;
	private Random X = new Random();
	private int posX;
	private int high;
	private int width;
	private Random Spawn = new Random();
	private int SpawnChance;
	private boolean isFruit;
	private boolean isTurned;
	Image burger = new ImageIcon("resources/burger.gif").getImage();
	Image fruit = new ImageIcon("resources/fruits.gif").getImage();

	public FruitsNBurgers() {
		this.posY = 0;
		this.posX = 0;
		this.high = 20;
		this.width = 20;
		this.SpawnChance = Spawn.nextInt(10);
		;
		this.isTurned = false;
	}

	void movement(int dist) {
		this.posX -= dist;
	}

	void randomGenerator(Barrier[] barreras, int i) {
		this.posY = Y.nextInt((barreras[i].getSup().getNumber() + 125) - (barreras[i].getSup().getNumber() - 125))
				+ (barreras[i].getSup().getNumber() - 125);
		this.posX = X.nextInt((barreras[i].getPosX() + 200) - (barreras[i].getPosX() + 100))
				+ (barreras[i].getPosX() + 100);
		if (this.SpawnChance <= 5) {
			this.isFruit = true;
		} else {
			this.isFruit = false;
		}
	}

	void drawAndMoveFruits(Entorno entorno, Barrier[] barriers, int i) {
		movement(2);
		if (this.isFruit) {
			entorno.dibujarImagen(fruit, this.posX, this.posY, 0);
		} else {
			entorno.dibujarImagen(burger, this.posX, this.posY, 0);
		}
		if (this.posX <= 0) {
			new FruitsNBurgers();
			randomGenerator(barriers, i);
		}
	}

	boolean Collision(int posX, int posY, int ancho, int alto) {
		return ((posX - ancho / 2 <= this.posX + this.width) && (posX + ancho / 2 >= this.posX - this.width)
				&& ((posY - alto / 2 <= this.posY + this.high) && posY + alto / 2 >= this.posY - this.high));
	}

	int getPosX() {
		return this.posX;
	}

	int getPosY() {
		return this.posY;
	}

	boolean getIsFruit() {
		return this.isFruit;
	}

	boolean getIsTurned() {
		return this.isTurned;
	}

	void setIsFruit(boolean Fruit) {
		this.isFruit = Fruit;
	}

	void setIsTurned(boolean Turned) {
		this.isTurned = Turned;
	}

	void setPosY(int Y) {
		this.posY = Y;
	}

}
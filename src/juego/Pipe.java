package juego;

import java.util.Random;

public class Pipe {
	private int posY;
	private Random num = new Random();
	private int number = num.nextInt(350) + 125;

	public Pipe(int posHueco) {

		if (posHueco == 0) {
			this.posY = number - 275;
		} else {
			this.posY = posHueco + 275;
		}
	}

	int getPosY() {
		return this.posY;
	}

	int getNumber() {
		return this.number;
	}

}

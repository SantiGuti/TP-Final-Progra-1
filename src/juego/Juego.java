package juego;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros

	// Variables y métodos propios de cada grupo
	// ...
	Bird bird;
	Barrier[] barriers = new Barrier[3];
	Bullet[] bullets = new Bullet[4];
	FruitsNBurgers[] fruits = new FruitsNBurgers[3];
	private Entorno entorno;
	boolean status = true;
	boolean startGame = false;
	int points = 0;
	int HP = 3;
	int count = 0;
	boolean play = true;
	boolean playAgain = true;
	Music inicio = new Music("resources/soundtrack.wav");
	Music gameover = new Music("resources/gameover.wav");
	Music catchFruit = new Music("resources/catchFruit.wav");
	Music catchFruitConverted = new Music("resources/catchFruitConverted.wav");
	Music catchBurger = new Music("resources/catchBurger.wav");
	Music laserGun = new Music("resources/laserGun.wav");
	Music birdHit = new Music("resources/birdHit.wav");
	Image background = new ImageIcon("resources/background.png").getImage();
	Image start = new ImageIcon("resources/start.gif").getImage();
	Image gameOver = new ImageIcon("resources/gameover.gif").getImage();

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "TorreMágica - Grupo Apellido1 - Apellido2 -Apellido3 - V0.01", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		initializeBarrier(barriers);
		initializeFruits(fruits, barriers);
		bird = new Bird(entorno);

		// Inicia el juego!
		this.entorno.iniciar();

	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */

	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...

		if (HP > 0) {
			if (startGame) {
				if (play) {
					inicio.playMusicLoop();
					play = false;
				}
				entorno.dibujarImagen(background, 405, 303, 0);
				drawBarrierAndFruits();
				bird.drawAndMoveBird();
				CollisionFruits();
				loseHP();
				entorno.cambiarFont("AR DELANEY", 18, Color.black);
				entorno.escribirTexto("Puntos: " + points, 3, 15);
				entorno.escribirTexto("HP: " + HP, 3, 30);
				entorno.escribirTexto("Ammo: " + ammoClip(), 3, 575);
				drawBullets();
			} else {
				startGame();
			}
		} else {
			if (playAgain) {
				gameover.playMusic();
				playAgain = false;
			}
			gameOver();
			inicio.stop();

		}
	}

	int ammoClip() {
		float ammo = 2;
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i] == null) {
				ammo += 0.5;
			} else {
				ammo -= 0.5;
			}
		}
		return (int) ammo;
	}

	boolean CollisionBarrierAndOffScreen() {
		for (int i = 0; i < barriers.length; i++) {
			if (barriers[i].Collision(bird.getPosX(), bird.getPosY(), bird.getDiameter(), bird.getDiameter())
					|| bird.isOffScreen()) {
				if (bird.isOffScreen()) {
					bird.setPosY(610);
				}
				return false;
			}
		}
		return true;
	}

	void loseHP() {
		if (!CollisionBarrierAndOffScreen() && count == 0) {
			birdHit.playMusic();
			count = 1;
			HP--;
		}
		if (count > 0 && count <= 200) {
			count++;
			bird.bird = new ImageIcon("resources/Bird.gif").getImage();
			bird.aug = new ImageIcon("resources/aug.gif").getImage();

		}
		if (count > 200) {
			count = 0;
			bird.bird = new ImageIcon("resources/Bird.png").getImage();
			bird.aug = new ImageIcon("resources/aug.png").getImage();
		}
	}

	int CollisionFruits() {
		for (int i = 0; i < barriers.length; i++) {
			if (fruits[i].Collision(bird.getPosX(), bird.getPosY(), bird.getDiameter(), bird.getDiameter())) {
				fruits[i].setPosY(900);
				if (fruits[i].getIsFruit()) {
					if (!fruits[i].getIsTurned()) {
						catchFruit.playMusic();
						return points += 5;
					} else {
						catchFruitConverted.playMusic();
						return points += 3;
					}
				} else {
					catchBurger.playMusic();
					return points -= 5;
				}

			}
		}
		return points;
	}

	void startGame() {
		entorno.dibujarImagen(start, 402, 307, 0);
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			startGame = true;
		}
	}

	void gameOver() {
		entorno.dibujarImagen(gameOver, 400, 300, 0);
		entorno.cambiarFont("AR DELANEY", 26, Color.black);
		entorno.escribirTexto("Score: " + points, 350, 330);
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			status = true;
			points = 0;
			bird.setPosY(300);
			HP = 3;
			initializeBarrier(barriers);
			initializeFruits(fruits, barriers);
			bullets = new Bullet[4];
			play = true;
			playAgain = true;
		}

	}

	void drawBarrier() {
		for (int i = 0; i < barriers.length; i++) {
			barriers[i].drawBarrier(entorno);
		}
	}

	void drawBarrierAndFruits() {
		for (int i = 0; i < 3; i++) {
			barriers[i].drawAndMoveBarrier(entorno);
			fruits[i].drawAndMoveFruits(entorno, barriers, i);
		}
	}

	void initializeFruits(FruitsNBurgers[] fruits, Barrier[] barreras) {
		for (int i = 0; i < barreras.length; i++) {
			fruits[i] = new FruitsNBurgers();
			fruits[i].randomGenerator(barreras, i);
		}
	}

	void initializeBarrier(Barrier[] barreras) {
		barreras[0] = new Barrier();
		for (int i = 1; i < barreras.length; i++) {
			barreras[i] = new Barrier();
			barreras[i].setPosX(barreras[i - 1].getPosX() + 300);
		}
	}

	void drawBullets() {
		boolean draw = true;

		if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			laserGun.playMusic();
			for (int i = 0; i < bullets.length; i++) {
				if (bullets[i] == null && draw) {
					bullets[i] = new Bullet(bird.getPosX(), bird.getPosY());
					draw = false;
				}
			}
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i] != null) {
				if (bullets[i].getPosX() > 800) {
					bullets[i] = null;
				}
			}

			int j = 0;
			while (j < fruits.length && bullets[i] != null) {

				if (fruits[j].Collision(bullets[i].getPosX(), bullets[i].getPosY(), 20, 5) && !fruits[j].getIsFruit()) {
					bullets[i] = null;
					fruits[j].setIsFruit(true);
					fruits[j].setIsTurned(true);
				} else if (barriers[j].Collision(bullets[i].getPosX(), bullets[i].getPosY(), 20, 5)) {
					bullets[i] = null;
				}
				j++;
			}
			if (bullets[i] != null) {
				bullets[i].drawAndMoveBullet(entorno);
			}
		}
	}

	boolean notFull(Bullet clip) {
		return (clip == null);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}

package juego;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	private String filepath;
	private Clip clip;

	public Music(String filepath) {
		this.filepath = filepath;
	}

	void playMusicLoop() {

		try {
			AudioInputStream music = AudioSystem.getAudioInputStream(new File(this.filepath).getAbsoluteFile());
			this.clip = AudioSystem.getClip();
			this.clip.open(music);
			this.clip.start();
			this.clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}

	}

	void playMusic() {

		try {
			AudioInputStream music = AudioSystem.getAudioInputStream(new File(this.filepath).getAbsoluteFile());
			this.clip = AudioSystem.getClip();
			this.clip.open(music);
			this.clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}

	}

	void stop() {
		this.clip.stop();
	}
}

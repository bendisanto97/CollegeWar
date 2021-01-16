package menu;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the game's backgroung music
 */
public class BackgroundMusic {

	private static MediaPlayer player;

	/**
	 * @param chemin
	 */
	public static void setMediaPlayer(String chemin) {
		Media sound = new Media(new File(chemin).toURI().toString());
		player = new MediaPlayer(sound);
	}

	/**
	 * @param volume
	 */
	public static void playMusic(float volume) {
		player.setVolume(volume);
		player.setOnEndOfMedia(new Runnable() {
			public void run() {
				player.seek(Duration.ZERO);
			}
		});
		player.play();
	}

	public static void stopMusic() {
		player.stop();
	}

	/**
	 * @param volume
	 */
	public static void setVolume(float volume) {
		player.setVolume(volume);
	}
}
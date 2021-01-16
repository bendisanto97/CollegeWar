package appliFx;

import javaFxComponents.ToolJavaFX;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import menu.BackgroundMusic;
import menu.MenuItem;
import menu.StartGame;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the stage for the end of the game
 */
public class EndScreen extends Application {
	private static Boolean win;

	/**
	 * @param w
	 */
	public static void setWin(Boolean w) {
		win = w;
	}

	@Override
	public void start(Stage primaryStage) {
		BackgroundMusic.stopMusic();
		ImageView background = null;
		if (win) {
			BackgroundMusic.setMediaPlayer("res/sounds/carlitoshacker.mp3");
			background = ToolJavaFX.newImageViewByFile("file:images/Win.png", 960, 1280, 0, 0);
		} else {
			BackgroundMusic.setMediaPlayer("res/sounds/Redjak bareknuckle.mp3");
			background = ToolJavaFX.newImageViewByFile("file:images/GameOver.png", 960, 1280, 0, 0);
		}
		BackgroundMusic.playMusic((float) 0.3);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 30; i < 100; i++) {
					try {
						Thread.sleep((int) (100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					BackgroundMusic.setVolume(((float) i / 100));
				}
			}
		});
		t.start();
		Group grp = new Group();
		Scene scene = new Scene(grp, 1280, 960);

		MenuItem retour = new MenuItem("MENU PRINCIPAL");
		retour.setLayoutX(400);
		retour.setLayoutY(900);
		retour.setOnMouseClicked(event -> {
			try {
				BackgroundMusic.stopMusic();
				new StartGame().start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		grp.getChildren().add(background);
		grp.getChildren().add(retour);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();

		primaryStage.show();
	}
}
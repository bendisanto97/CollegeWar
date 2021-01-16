package appliFx;

import java.io.IOException;
import java.util.LinkedList;

import appli.Card;
import cards.Item;
import javaFxComponents.ApplicationFX;
import javaFxComponents.ToolJavaFX;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import menu.BackgroundMusic;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the graphic interface for the backup mode
 */
public class WindowBackup extends Application {

	private Stage pm;
	private ApplicationFX appFX;

	@Override
	public void start(Stage primaryStage) {

		BackgroundMusic.stopMusic();
		BackgroundMusic.setMediaPlayer("res/sounds/IngameMusic.mp3");
		BackgroundMusic.playMusic(0);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < 100; i++) {
					try {
						Thread.sleep((int) (Math.random() * 100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					BackgroundMusic.setVolume(((float) i / 100));
				}
			}
		});
		t.start();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					AppliBackup.getInstance().getPlayer().save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		appFX = new ApplicationFX();
		pm = primaryStage;
		pm.setTitle("Project College");
		nextCard();
		pm.show();
	}

	/**
	 * @param scene
	 */
	private void setScene(Scene scene) {
		pm.setScene(scene);
		pm.setResizable(false);
		pm.sizeToScene();
	}

	/**
	 * @see uses "nextCard"
	 */
	private void goToNext() {
		AppliBackup app = AppliBackup.getInstance();
		app.nextC();
		if (app.hasNextC())
			nextCard();
	}

	/**
	 * @see adapts the graphic interface for the next card
	 */
	private void nextCard() {
		AppliBackup app = AppliBackup.getInstance();
		Card current = app.getC();

		appFX.setApplication(current.getBackground(), app.getPlayer().getStatistics(), new LinkedList<Item>(),
				current.getLocation(), current.getImage(), current.getDescription(), current.getAvatar());

		Group grp = new Group();
		Scene scene = new Scene(grp, 1280, 960);

		Button btnTrue = ToolJavaFX.newButtonComplete(88, 816, 522, 144, current.getPossibleAnswers()[0],
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if (app.setAnswer(true))
							goToNext();
					}
				});

		Button btnFalse = ToolJavaFX.newButtonComplete(645, 816, 545, 144, current.getPossibleAnswers()[1],
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if (app.setAnswer(false))
							goToNext();
					}
				});

		grp.getChildren().add(appFX.getGtp());
		grp.getChildren().add(btnFalse);
		grp.getChildren().add(btnTrue);
		setScene(scene);

	}

}

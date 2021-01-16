package menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import appli.PlayerBackup;
import appliFx.Appli;
import appliFx.AppliBackup;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the menu, this class is trigger of all the game
 */
public class StartGame extends Application {

	private Stage window;
	private Scene accueil;

	private static Font menu, title, littleTitle, subTitles, menuOnClick, tip;
	private ResourceLoadingTask task = new ResourceLoadingTask();

	@FXML
	private RotateTransition loadingCircleAnimation;
	@FXML
	private ProgressBar progressBar;

	static {
		try {
			menu = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 30);
			title = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 60);
			littleTitle = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 30);
			subTitles = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 25);
			menuOnClick = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 29);
			tip = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * @return window
	 */
	public Stage getWindow() {
		return window;
	}

	/**
	 * @return accueil
	 */
	public Scene getAccueil() {
		return accueil;
	}

	/**
	 * @return menu
	 */
	public static Font getMenu() {
		return menu;
	}

	/**
	 * @return titre
	 */
	public static Font getTitle() {
		return title;
	}

	/**
	 * @return littleTitle
	 */
	public static Font getLittleTitle() {
		return littleTitle;
	}

	/**
	 * @return subTitles
	 */
	public static Font getSubTitles() {
		return subTitles;
	}

	/**
	 * @return tip
	 */
	public static Font getTip() {
		return tip;
	}

	/**
	 * @return menuOnClick
	 */
	public static Font getMenuOnClick() {
		return menuOnClick;
	}

	/**
	 * @param p
	 * @return the loading page
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Parent loading(PlayerBackup p) {
		Pane pane = new Pane();
		pane.setPrefSize(1280, 960);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
		loader.setController(this);
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		progressBar.progressProperty().bind(task.progressProperty());
		Thread t = new Thread(task);
		task.setOnSucceeded(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				if (p == null)
					Appli.start(window);
				else
					AppliBackup.start(window, p);
			}
		});
		Text texte = new Text("Astuce : il n'y a pas de bonnes ou mauvaises réponses");
		texte.setTranslateX(315);
		texte.setTranslateY(840);
		texte.setFont(StartGame.getTip());
		pane.getChildren().add(texte);

		t.start();
		return pane;
	}

	@Override
	public void start(Stage stage) throws Exception {

		window = stage;
		accueil = new Scene(HomePage.accueil(this));

		stage.setTitle("College War");
		stage.setScene(accueil);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
		BackgroundMusic.setMediaPlayer("res/sounds/HomepageMusic.mp3");
		BackgroundMusic.playMusic(1);
	}

	private class ResourceLoadingTask extends Task<Void> {
		@Override
		protected Void call() throws Exception {
			for (int i = 0; i < 100; i++) {
				Thread.sleep((int) (Math.random() * 50));
				updateProgress(i + 1, 100);
				if (i > 0)
					BackgroundMusic.setVolume((1 - ((float) i / 100)));
			}
			BackgroundMusic.stopMusic();
			return null;
		}
	}
}
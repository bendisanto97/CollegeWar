package menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javaFxComponents.CardBackground;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Embodies the homepage of the menu
 */
public class HomePage {

	private static Scene continuer, score, credit, chargement, online;

	/**
	 * @param menu
	 * @return the complete page
	 */
	public static Parent accueil(StartGame menu) {
		Pane pane = new Pane();
		pane.setPrefSize(1280, 960);

		CardBackground back = new CardBackground();
		back.setBackground(3);
		try (InputStream isLogo = Files.newInputStream(Paths.get("images/logo.png"))) {
			ImageView logo = new ImageView(new Image(isLogo));
			logo.setFitWidth(330);
			logo.setFitHeight(330);
			logo.setTranslateX(475);
			logo.setTranslateY(203);
			pane.getChildren().addAll(back.getGtp(), logo);
		} catch (IOException e) {
			System.out.println("Impossible de charger l'image");
		}

		MenuItem partie = new MenuItem("COMMENCER UNE PARTIE");
		partie.setOnMouseClicked(event -> {
			back.stop();
			chargement = new Scene(menu.loading(null));
			menu.getWindow().setScene(chargement);

		});
		MenuItem continuerP = new MenuItem("CONTINUER");
		continuerP.setOnMouseClicked(event -> {
			try {
				continuer = new Scene(Continue.continuer(menu));
			} catch (IOException e) {
				e.printStackTrace();
			}
			menu.getWindow().setScene(continuer);
		});
		MenuItem scoreP = new MenuItem("SCORE");
		scoreP.setOnMouseClicked(event -> {
			try {
				score = new Scene(Score.score(menu));
			} catch (IOException e) {
				e.printStackTrace();
			}
			menu.getWindow().setScene(score);
		});
		MenuItem multijoueur = new MenuItem("MULTIJOUEUR");
		multijoueur.setOnMouseClicked(event -> {
			online = new Scene(Online.online(menu));
			menu.getWindow().setScene(online);

		});
		MenuItem credits = new MenuItem("CREDITS");
		credits.setOnMouseClicked(event -> {
			credit = new Scene(Credits.credit(menu));
			menu.getWindow().setScene(credit);
		});
		MenuItem exit = new MenuItem("QUITTER");
		exit.setOnMouseClicked(event -> System.exit(0));

		MenuBox menuBox = new MenuBox(partie, continuerP, scoreP, multijoueur, credits, exit);
		menuBox.setTranslateX(395);
		menuBox.setTranslateY(500);

		pane.getChildren().add(menuBox);
		return pane;
	}

	/**
	 * @return the scene multiplayer
	 */
	public Scene getMultijoueur() {
		return online;
	}

}

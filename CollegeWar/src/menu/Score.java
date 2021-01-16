package menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import appli.PlayerBackup;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Embodies the page "score" in the menu
 */
public class Score {

	/**
	 * @param menu
	 * @return the complete page
	 * @throws IOException
	 */
	public static Parent score(StartGame menu) throws IOException {
		Pane pane = new Pane();
		pane.setPrefSize(1280, 960);

		try (InputStream isFond = Files.newInputStream(Paths.get("images/fond.jpg"))) {
			ImageView fond = new ImageView(new Image(isFond));
			fond.setFitWidth(1280);
			fond.setFitHeight(960);
			pane.getChildren().add(fond);
		} catch (IOException e) {
			System.out.println("Impossible de charger l'image");
		}

		Title titre = new Title("SCORE");
		List<PlayerBackup> pl = PlayerBackup.getPlayerRanking();
		if (pl.isEmpty()) {
			MenuItem noScore = new MenuItem("Pas de score disponible");
			// noScore.setDisable(true);
			MenuItem retour = new MenuItem("RETOUR");
			retour.setOnMouseClicked(event -> menu.getWindow().setScene(menu.getAccueil()));
			MenuBox menuBox = new MenuBox(noScore, retour);
			menuBox.setTranslateX(395);
			menuBox.setTranslateY(725);
			pane.getChildren().addAll(titre, menuBox);
		} else {
			String res = "";
			for (PlayerBackup player : pl) {
				res += "Joueur " + player.getId() + " : " + player.getScore() + "\n";
			}
			Text theScore = new Text(res);
			theScore.setTranslateX(520);
			theScore.setTranslateY(442);
			theScore.setFont(StartGame.getLittleTitle());
			MenuItem retour = new MenuItem("RETOUR");
			retour.setOnMouseClicked(event -> menu.getWindow().setScene(menu.getAccueil()));
			MenuBox menuBox = new MenuBox(retour);
			menuBox.setTranslateX(395);
			menuBox.setTranslateY(725);
			pane.getChildren().addAll(titre, theScore, menuBox);
		}

		return pane;
	}

	/**
	 * @return list of ranked players
	 * @throws IOException
	 */
	public List<PlayerBackup> getPlayerRanking() throws IOException {
		Path score = Paths.get(PlayerBackup.getScoreFile());
		List<PlayerBackup> playerRanking = PlayerBackup.restore(score);
		Collections.sort(playerRanking, Collections.reverseOrder());
		return playerRanking;
	}

}

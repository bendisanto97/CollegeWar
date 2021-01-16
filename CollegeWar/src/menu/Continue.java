package menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import appli.PlayerBackup;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Embodies the page "continue" in the menu
 */
public class Continue {

	/**
	 * @param menu
	 * @return the complete page
	 * @throws IOException
	 */
	public static Parent continuer(StartGame menu) throws IOException {

		final int NB_PL = 3;
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

		Title titre = new Title("CONTINUER");
		Path backup = Paths.get(PlayerBackup.getBackupFile());
		List<PlayerBackup> pl = PlayerBackup.restore(backup);
		MenuItem[] lesSauv = new MenuItem[NB_PL];
		lesSauv[0] = new MenuItem();
		lesSauv[1] = new MenuItem();
		lesSauv[2] = new MenuItem();

		for (int i = NB_PL - 1; i >= 0; i--) {
			if (i < pl.size()) {
				MenuItem partie = new MenuItem("Joueur " + pl.get(i).getId());
				int index = i;
				partie.setOnMouseClicked(event -> {
					Scene chargement = new Scene(menu.loading(pl.get(index)));
					menu.getWindow().setScene(chargement);
				});
				lesSauv[i] = partie;
			}
		}

		Text texte = new Text("CHOISISSEZ UNE SAUVEGARDE");
		texte.setTranslateX(385);
		texte.setTranslateY(370);
		texte.setFont(StartGame.getLittleTitle());

		MenuItem retour = new MenuItem("RETOUR");
		retour.setOnMouseClicked(event -> menu.getWindow().setScene(menu.getAccueil()));

		MenuBox menuBox = new MenuBox(lesSauv[0], lesSauv[1], lesSauv[2]);
		menuBox.setTranslateX(395);
		menuBox.setTranslateY(435);
		MenuBox menuBox2 = new MenuBox(retour);
		menuBox2.setTranslateX(395);
		menuBox2.setTranslateY(725);
		pane.getChildren().addAll(titre, texte, menuBox, menuBox2);

		return pane;
	}

}

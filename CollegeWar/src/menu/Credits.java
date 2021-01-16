package menu;

import javaFxComponents.ToolJavaFX;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Embodies the page "credits" in the menu
 */
public class Credits {

	/**
	 * @param menu
	 * @return the complete page
	 */
	public static Parent credit(StartGame menu) {
		Pane pane = new Pane();
		pane.setPrefSize(1280, 960);

		ImageView fond = ToolJavaFX.newImageViewByFile("file:images/fond.jpg", 960, 1280, 0, 0);
		// profile 1
		Group profil1 = ToolJavaFX.profilCredits(125, 55, 280, "Courbois Paul", StartGame.getSubTitles(), "Paul");
		// profile 2
		Group profil2 = ToolJavaFX.profilCredits(585, 85, 280, "Di Santo Benjamin", StartGame.getSubTitles(),
				"Benjamin");
		// profile 3
		Group profil3 = ToolJavaFX.profilCredits(1025, 75, 280, "Leopardo Julien", StartGame.getSubTitles(), "Julien");
		// profile 4
		Group profil4 = ToolJavaFX.profilCredits(125, 55, 525, "Plateau Jules", StartGame.getSubTitles(), "Jules");
		// profile 5
		Group profil5 = ToolJavaFX.profilCredits(585, 85, 525, "Senguttuvan Velan", StartGame.getSubTitles(), "Velan");
		// profile 6
		Group profil6 = ToolJavaFX.profilCredits(1025, 45, 525, "Weber Simon", StartGame.getSubTitles(), "Simon");
		// profile 7
		Group profil7 = ToolJavaFX.profilCredits(585, 30, 760, "Roy Anton", StartGame.getSubTitles(), "Anton");
		pane.getChildren().addAll(fond, profil1, profil2, profil3, profil4, profil5, profil6, profil7);

		Title titre = new Title("CREDITS");

		MenuItem retour = new MenuItem("RETOUR");
		retour.setOnMouseClicked(event -> menu.getWindow().setScene(menu.getAccueil()));

		MenuBox menuBox = new MenuBox(retour);
		menuBox.setTranslateX(390);
		menuBox.setTranslateY(920);

		pane.getChildren().addAll(titre, menuBox);
		return pane;
	}

}

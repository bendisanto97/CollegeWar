package menu;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Defines a title
 */
public class Title extends StackPane {

	public Title(String name) {
		Text texte = new Text(name);
		texte.setFill(Color.BLACK);
		texte.setFont(StartGame.getTitle());
		texte.setTextAlignment(TextAlignment.CENTER);
		if (name == "CONTINUER") {
			texte.setTranslateX(450);
			texte.setTranslateY(175);
		} else if (name == "CREDITS") {
			texte.setTranslateX(490);
			texte.setTranslateY(175);
		} else if (name == "MULTIJOUEUR") {
			texte.setTranslateX(400);
			texte.setTranslateY(175);
			texte.setFill(Color.WHITE);
		} else if (name == "SCORE") {
			texte.setTranslateX(530);
			texte.setTranslateY(175);
		}
		getChildren().addAll(texte);
	}

}

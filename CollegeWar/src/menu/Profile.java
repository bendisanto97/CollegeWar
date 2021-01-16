package menu;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Defines a profile
 */
public class Profile extends StackPane {

	public Profile(ImageView profile, int X, int Y, Pane pane) {
		profile.setFitWidth(100);
		profile.setFitHeight(105);
		profile.setTranslateX(X);
		profile.setTranslateY(Y);
		getChildren().addAll(profile);
	}
}
package menu;

import javafx.scene.layout.VBox;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Defines a menubox
 */
public class MenuBox extends VBox {

	public MenuBox(MenuItem... items) {
		for (MenuItem item : items) {
			getChildren().addAll(item);
		}
	}

}

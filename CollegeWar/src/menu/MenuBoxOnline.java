package menu;

import javafx.scene.layout.VBox;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Defines a menubox online
 */
public class MenuBoxOnline extends VBox {

	public MenuBoxOnline(MenuItemOnline... items) {
		for (MenuItemOnline item : items) {
			getChildren().addAll(item);
		}
	}

}

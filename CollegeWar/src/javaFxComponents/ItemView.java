package javaFxComponents;

import java.util.ArrayList;
import java.util.List;

import cards.Item;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the items in the graphic interface
 */
public class ItemView {

	private Group gtp;

	private List<Image> items;
	private List<Integer> CoordX;

	public ItemView() {
		gtp = new Group();

		items = new ArrayList<Image>();
		CoordX = new ArrayList<Integer>();

		items.add(new Image("file:./images/objets/flasque.png"));
		items.add(new Image("file:./images/objets/amour.png"));
		items.add(new Image("file:./images/objets/cheat.png"));
		items.add(new Image("file:./images/objets/clope.png"));

		CoordX.add(75);
		CoordX.add(185);
		CoordX.add(275);
		CoordX.add(365);
	}

	/**
	 * @return gtp
	 */
	public Group getGtp() {
		return gtp;
	}

	/**
	 * @param n
	 */
	private void setterObjet(int n) {
		gtp.getChildren().add(ToolJavaFX.newImageViewByImage(items.get(n), 75, 75, CoordX.get(n), 710));
	}

	/**
	 * @param list
	 */
	@SuppressWarnings("deprecation")
	public void setObjet(List<Item> list) {
		for (Item it : list)
			for (int i = 0; i < items.size(); i++)
				if (items.get(i).impl_getUrl().equals("file:" + it.getImage()))
					setterObjet(i);
	}
}
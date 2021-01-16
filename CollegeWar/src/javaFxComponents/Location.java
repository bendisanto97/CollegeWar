package javaFxComponents;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the location's image in the graphic interface
 */
public class Location {

	private Group gtp;

	private List<Image> locations;

	private ImageView imgLieu;

	public Location() {
		gtp = new Group();

		locations = new ArrayList<Image>();

		locations.add(new Image("file:images/lieux/appart.png"));
		locations.add(new Image("file:images/lieux/metro.png"));
		locations.add(new Image("file:images/lieux/ecole.png"));

		imgLieu = ToolJavaFX.newImageViewByImage(locations.get(0), 190, 190, 1020, 601);

		gtp.getChildren().add(imgLieu);
	}

	/**
	 * @return gtp
	 */
	public Group getGtp() {
		return gtp;
	}

	/**
	 * @param name
	 */
	@SuppressWarnings("deprecation")
	public void setLocation(String name) {
		if (!name.equals("0")) {
			String file = "file:images/lieux/" + name + ".png";
			for (int i = 0; i < locations.size(); i++) {
				if (locations.get(i).impl_getUrl().equals(file))
					imgLieu.setImage(locations.get(i));
			}
		}
	}
}

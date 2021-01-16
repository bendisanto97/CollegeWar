package javaFxComponents;

import java.util.List;

import cards.Item;
import javafx.scene.Group;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages all elements of the graphic interface
 */
public class ApplicationFX {

	private CardBackground background;
	private Statistics stats;
	private ItemView item;
	private Location location;
	private TextBox zone;
	private Group gtp;

	public ApplicationFX() {
		background = new CardBackground();
		stats = new Statistics();
		item = new ItemView();
		location = new Location();
		zone = new TextBox();
		gtp = new Group();

		gtp.getChildren().addAll(background.getGtp(), stats.getGtp(), item.getGtp(), location.getGtp(), zone.getGtp());
	}

	/**
	 * @return background
	 */
	public CardBackground getBackground() {
		return background;
	}

	/**
	 * @return stats
	 */
	public Statistics getStats() {
		return stats;
	}

	/**
	 * @return item
	 */
	public ItemView getItem() {
		return item;
	}

	/**
	 * @return location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return zone
	 */
	public TextBox getText() {
		return zone;
	}

	/**
	 * @return gtp
	 */
	public Group getGtp() {
		return gtp;
	}

	/**
	 * @param background
	 * @param statistics
	 * @param item
	 * @param location
	 * @param avatar
	 * @param text
	 * @param name
	 */
	public void setApplication(int background, int[] statistics, List<Item> items, String location, String avatar,
			String text, String name) {
		this.background.setBackground(background);
		this.stats.setStatiti(statistics);
		this.item.setObjet(items);
		this.location.setLocation(location);
		this.zone.setTextBox(avatar, text, name);
	}
}

package cards;

import appli.MagicVariables;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 12/02/2019
 * @version 1.0
 * @see Defines an event and stocks its cards
 */
public class Item {

	private String name;
	private int[] statistics;
	private boolean trigger = false;
	private boolean active = true;
	private String image = "";

	private final static String FOLDER = "./items/";

	public Item(String file) {
		name = file;
		statistics = new int[MagicVariables.getNbStats()];
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return statistics if active is true, else return an int's array of 0
	 */
	public int[] getStatistics() {
		if (active) {
			active = false;
			return statistics;
		}
		active = true;
		return new int[] { 0, 0, 0, 0, 0 };
	}

	/**
	 * @param statisctics
	 */
	public void setStatistics(int[] statisctics) {
		this.statistics = statisctics;
	}

	/**
	 * @return trigger
	 */
	public boolean isTrigger() {
		return trigger;
	}

	/**
	 * @param trigger
	 */
	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}

	/**
	 * @return image's file
	 */
	public String getImage() {
		return "./images/objets/" + image + ".png";
	}

	/**
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return item's folder
	 */
	public static String getFolder() {
		return FOLDER;
	}

}

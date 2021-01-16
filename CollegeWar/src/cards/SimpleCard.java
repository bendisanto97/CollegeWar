package cards;

import appli.Card;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 29/01/2019
 * @version 1.0
 * @see An implementation of the interface "Card" and all the methods (just
 *      getters and setters, kind of like a JavaBean)
 */
public class SimpleCard implements Card {

	private String avatar = "";
	private String description = "";
	// path of the avatar's image
	private String image = "";

	// the two possible answers
	private String[] possibleAnswers = null;
	// stats that have to be modified if the answer is yes
	private int[] statisticsYes = null;
	// stats that have to be modified if the answer is no
	private int[] statisticsNo = null;

	// card's background
	private int background = 0;
	// card's location (image's path)
	private String location = "";
	// card's event (null if no event)
	private Event event = null;
	// card's item (null if no item)
	private Item item = null;

	// initialize the field Class of "CardManager"
	static {
		CardManager.init(SimpleCard.class);
	}

	@Override
	public String getAvatar() {
		return avatar;
	}

	@Override
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getImage() {
		// return MagicVariables.getAvatarFolder()+image + ".png";
		return image;
	}

	@Override
	public void setImage(String file) {
		image = file;
	}

	@Override
	public String[] getPossibleAnswers() {
		return possibleAnswers;
	}

	@Override
	public void setPossibleAnswers(String[] possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	@Override
	public int[] getStatistics(boolean choice) {
		return choice ? statisticsYes : statisticsNo;
	}

	@Override
	public void setStatisticsYes(int[] statsYes) {
		this.statisticsYes = statsYes;
	}

	@Override
	public void setStatisticsNo(int[] statsNo) {
		this.statisticsNo = statsNo;
	}

	@Override
	public int getBackground() {
		return background;
	}

	@Override
	public void setBackground(int num) {
		background = num;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public void setLocation(String file) {
		location = file;
	}

	@Override
	public Event getEvent() {
		return event;
	}

	@Override
	public void setEvent(String file) {
		if (!file.equals("0"))
			event = new Event(file);
		// else event = null
	}

	@Override
	public Item getItem() {
		return item;
	}

	@Override
	public void setItem(String file) {
		if (!file.equals("0"))
			item = new Item(file);
		// else item = null
	}

}

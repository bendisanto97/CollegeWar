package appli;

import cards.Event;
import cards.Item;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 29/01/2019
 * @version 1.0
 * @see Defines the Card's methods used by the application
 */
public interface Card {

	/**
	 * @return avatar
	 */
	String getAvatar();

	/**
	 * @param avatar
	 */
	void setAvatar(String avatar);

	/**
	 * @return description
	 */
	String getDescription();

	/**
	 * @param description
	 */
	void setDescription(String description);

	/**
	 * @return avatar's image
	 */
	String getImage();

	/**
	 * @param file
	 */
	void setImage(String file);

	/**
	 * @return possibles answers
	 */
	String[] getPossibleAnswers();

	/**
	 * @param possibleAnswers
	 */
	void setPossibleAnswers(String[] possibleAnswers);

	/**
	 * @param choice
	 *            : true for statsYes and false for statsNo
	 * @return the wanted stats
	 */
	int[] getStatistics(boolean choice);

	/**
	 * @param statsYes
	 */
	void setStatisticsYes(int[] statsYes);

	/**
	 * @param statsNo
	 */
	void setStatisticsNo(int[] statsNo);

	/**
	 * @return background
	 */
	int getBackground();

	/**
	 * @param num
	 */
	void setBackground(int num);

	/**
	 * @return location
	 */
	String getLocation();

	/**
	 * @param file
	 */
	void setLocation(String file);

	/**
	 * @return event
	 */
	Event getEvent();

	/**
	 * @param file
	 */
	void setEvent(String file);

	/**
	 * @return item
	 */
	Item getItem();

	/**
	 * @param file
	 */
	void setItem(String file);

}

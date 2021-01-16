package cards;

import java.util.LinkedList;
import java.util.List;

import appli.Card;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 12/02/2019
 * @version 1.0
 * @see Defines an event and stocks its cards
 */
public class Event {

	private String file;
	private List<Card> cards;
	private int index = 0;
	private boolean loop = false;
	private boolean trigger = false;

	private final static String FOLDER = "./events/";

	public Event(String file) {
		this.file = file;
		cards = new LinkedList<Card>();
	}

	/**
	 * @return file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param card
	 */
	public void addCard(Card card) {
		cards.add(card);
	}

	/**
	 * @return the next card if there is still at least one and check the answer if
	 *         the current card is the trigger
	 */
	public Card getNextCard() {
		return index < cards.size() && (index > 0 || trigger) ? cards.get(index++) : null;
	}

	/**
	 * @return loop
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * @param loop
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
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
	 * @return event's folder
	 */
	public static String getFolder() {
		return FOLDER;
	}

}

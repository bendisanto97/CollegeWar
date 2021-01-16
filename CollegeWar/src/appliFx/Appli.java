package appliFx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import appli.Card;
import appli.MagicVariables;
import appli.Player;
import cards.CardManager;
import cards.Event;
import cards.SimpleCard;
import javafx.stage.Stage;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Launches the standard mode with the graphic interface from the class
 *      "Window". The class Appli is a singleton
 */
public final class Appli {

	private static Appli instance = new Appli();
	private ArrayList<Card> cards;
	private int index = 0;
	private Player player;
	private Stage stage;

	private Card ongoingCard = null;
	private Event ongoingEvent = null;

	private Appli() {
		cards = new ArrayList<Card>();
		player = new Player();
	}

	/**
	 * @return the unique instance of Appli
	 */
	public static Appli getInstance() {
		return instance;
	}

	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param stg
	 * @see load all cards and launches Window
	 */
	public static void start(Stage stg) {
		CardManager.init(SimpleCard.class);
		Appli a = Appli.getInstance();
		a.addCards(CardManager.getAllCards());
		a.stage = stg;
		new Window().start(stg);
	}

	/**
	 * @return ongoingCard
	 */
	public Card getC() {
		return ongoingCard;
	}

	/**
	 * @see sets ongoingCard, and eventually sets ongoingEvent too
	 */
	public void nextC() {
		if (ongoingEvent != null)
			eventManager();
		else {
			setOngoingCard();
			setOngoingEvent();
		}
	}

	/**
	 * @see sets ongoingCard if there is a next one
	 */
	private void setOngoingCard() {
		index++;
		if (hasNextC())
			ongoingCard = cards.get(index);
		else
			gameOver(true);
	}

	/**
	 * @see sets ongoingEvent from ongoingCard
	 */
	private void setOngoingEvent() {
		if (ongoingCard != null)
			ongoingEvent = ongoingCard.getEvent();
	}

	/**
	 * @see sets ongoingCard depending of ongoingEvent
	 */
	private void eventManager() {
		if (ongoingEvent.isLoop()) {
			if (ongoingEvent.isTrigger()) {
				Random rd = new Random();
				if (rd.nextInt(MagicVariables.getEscapeLoop()) == 0) {
					// MagicVariables.getEscapeLoop() = 10, so there is a 10% chance of executing
					// this code
					ongoingCard = ongoingEvent.getNextCard();
					ongoingEvent.setLoop(false); // end of loop, but not necessarily end of event
				} // else : ongoingCard doesn't change : stuck in the loop
			} else {
				// the player chose the answer that allows to escape the loop
				setOngoingCard();
				setOngoingEvent();
			}
		} else // not a loop
			getNextCardOfEvent();
	}

	/**
	 * @see sets ongoingCard with the next card of ongoingEvent
	 */
	private void getNextCardOfEvent() {
		ongoingCard = ongoingEvent.getNextCard();
		if (ongoingCard == null) { // end of event
			setOngoingCard();
			setOngoingEvent();
		}
	}

	/**
	 * @param cards
	 * @see initialyzes the fields cards, index, player, ongoingCard and
	 *      ongoingEvent
	 */
	private void addCards(List<Card> cards) {
		for (Card c : cards)
			this.cards.add(c);
		index = 0;
		player = new Player();
		ongoingCard = cards.get(index);
		ongoingEvent = ongoingCard.getEvent();
	}

	/**
	 * @return if there is another card after the current Index
	 */
	public boolean hasNextC() {
		return index < cards.size();
	}

	/**
	 * @param answer
	 *            : boolean answer for the card at the current index
	 * @see add the statistic to the player for a selected answer of the card at the
	 *      current index
	 */
	public boolean setAnswer(boolean answer) {
		player.add(getC().getStatistics(answer));
		if (player.isEnded()) {
			gameOver(false);
			return false;
		}
		else {
			if (ongoingEvent != null)
				ongoingEvent.setTrigger(answer);
			if (ongoingCard.getItem() != null && ongoingCard.getItem().isTrigger() == answer)
				player.addItem(ongoingCard.getItem());
		}
		return true;
	}

	/**
	 * @param result
	 */
	private void gameOver(boolean result) {
		EndScreen.setWin(result);
		new EndScreen().start(stage);
	}

	/**
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
}

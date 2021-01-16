package appliFx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import appli.Card;
import appli.MagicVariables;
import appli.PlayerBackup;
import cards.CardManager;
import cards.Event;
import cards.SimpleCard;
import javafx.stage.Stage;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Launches the backup mode with the graphic interface from the class
 *      "WindowBackup". The class AppliBackup is a singleton
 */
public final class AppliBackup {

	private static AppliBackup instance = new AppliBackup();
	private ArrayList<Card> cards;
	private int index = 0;
	private PlayerBackup player = null;

	private Card ongoingCard = null;
	private Event ongoingEvent = null;
	private Stage stage;

	private AppliBackup() {
		cards = new ArrayList<Card>();
	}

	/**
	 * @return the unique instance of AppliBackup
	 */
	public static AppliBackup getInstance() {
		return instance;
	}

	/**
	 * @param stg
	 * @param p
	 * @see load all cards and launches WindowBackup
	 */
	public static void start(Stage stg, PlayerBackup p) {
		CardManager.init(SimpleCard.class);
		AppliBackup a = AppliBackup.getInstance();
		a.addCards(CardManager.getAllCards(), p);
		a.stage = stg;
		new WindowBackup().start(stg);
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
	 * @param p
	 * @see initialyzes the fields cards, index, player, ongoingCard and
	 *      ongoingEvent
	 */
	public void addCards(List<Card> cards, PlayerBackup p) {
		for (Card c : cards)
			this.cards.add(c);
		index = p.getScore();
		player = p;
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
	public PlayerBackup getPlayer() {
		return player;
	}
}

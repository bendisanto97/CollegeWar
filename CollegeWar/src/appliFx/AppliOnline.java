package appliFx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import appli.Card;
import appli.Player;
import cards.CardManager;
import cards.SimpleCard;
import javafx.stage.Stage;
import network.ManagerConnexionOut;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Launches the multiplayer mode with the graphic interface from the class
 *      "WindowOnline". The class AppliOnline is a singleton
 */
public class AppliOnline {

	public static boolean isClose = false;
	private static AppliOnline instance = new AppliOnline();
	private ArrayList<ArrayList<Card>> cards;
	private int index = 0;
	private int indexIn = 0;
	private Player player;
	private Player onlinePlayer;
	private WindowOnline window;
	private ManagerConnexionOut mcOut;
	private boolean endOfTheGame;
	private Stage stage;

	private AppliOnline() {
		mcOut = ManagerConnexionOut.getInstance();
		cards = new ArrayList<ArrayList<Card>>();
		player = new Player();
		onlinePlayer = new Player();
		endOfTheGame = false;
	}

	/**
	 * @return the unique instance of Appli
	 */
	public static AppliOnline getInstance() {
		return instance;

	}

	/**
	 * @param stg
	 * @see load all cards and launches WindowOnline
	 */
	public static void start(Stage stg) {
		CardManager.init(SimpleCard.class);

		AppliOnline a = AppliOnline.getInstance();
		CardManager.getAllControlePoint();
		a.addCards(CardManager.getAllCards(), CardManager.getAllControlePoint());
		a.stage = stg;
		WindowOnline windowO = new WindowOnline();
		a.setWindow(windowO);
		windowO.start(stg);
	}

	/**
	 * @param oa
	 *            the WindowOnline to set
	 */
	private void setWindow(WindowOnline oa) {
		window = oa;
	}

	/**
	 * @return the card at the current index
	 */
	public Card getC() {
		return cards.get(index).get(indexIn);
	}

	/**
	 * @see update index
	 */
	public void nextC() {
		if (!endOfTheGame) {
			if (indexIn < cards.get(index).size() - 1) {
				indexIn++;
				// System.out.println("nextC index= " + index + " /indexIn= " + indexIn);
			} else {
				if (!window.isControlePointForced())
					setEndControlPoint();
				index++;
				indexIn = 0;
				window.controlePointPassed();
			}
		} else {
			gameOver(true);
		}
	}

	/**
	 * @param list
	 *            the card to add to the Application List
	 * @param controlePoint
	 *            that will separate list of card
	 */
	public void addCards(List<Card> list, List<Card> controlePoint) {
		int nbCardByEvent = list.size() / controlePoint.size();
		// System.out.println("nbCardByEvent= " + nbCardByEvent + "
		// /nbCardControlePoint= " + controlePoint.size());
		player = new Player();
		onlinePlayer = new Player();
		for (int i = 0; i < controlePoint.size(); i++) {
			cards.add(new ArrayList<Card>());
			for (int j = 0; j < nbCardByEvent; j++)
				this.cards.get(i).add(list.get(j));
			this.cards.get(i).add(controlePoint.get(i));
		}
	}

	/*
	 * @return if there is another card after the current Index
	 */
	/**
	 * @return if there is another card after the current Index
	 */
	public boolean hasNext() {
		return cards.size() - index > 1 || cards.get(index).size() - indexIn > 1;
	}

	/**
	 * @param answer
	 *            the boolean answer for the card at the current index
	 * @see add the statistic to the player for a selected answer of the card at the
	 *      current index
	 */
	
	public boolean setAnswerLocalPlayer(boolean answer) {
		player.add(getC().getStatistics(answer));
		if (indexIn < cards.get(index).size() - 1) {
			if (window.isControlePointForced())
				player.add(getC().getStatistics(answer));
		}
		if (player.isEnded()) {
			gameOver(false);
			return false;
		}
		try {
			mcOut.sendStat(player.getStatistics());
		} catch (IOException e) {
			System.err.println("err");
			endConnexion();
		}
		return true;
	}

	/**
	 * @param stat
	 *            the statistic to apply
	 * @see apply the statistic to the online player
	 */
	public void setStatOnlinePlayer(int[] stat) {
		onlinePlayer.setStatistics(stat);
		if (onlinePlayer.isEnded()) {
			endOfTheGame = true;
		}
	}

	/**
	 * @param result
	 * @see send the online player the end of the control point
	 */
	public void gameOver(boolean result) {
		EndScreen.setWin(result);
		new EndScreen().start(stage);
	}

	public void endConnexion() {
		// new WindowConnection().start(primaryStage);
	}

	/**
	 * @see send the online player the end of the control point
	 */
	private void setEndControlPoint() {
		try {
			mcOut.sendEndControlePoint();
		} catch (IOException e) {
			System.err.println("err");
		}
	}

	/**
	 * @see force the player to the next control point
	 */
	public void nextControlPoint() {
		indexIn = cards.get(index).size() - 2;
		window.nextControlePoint();
	}

	/**
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

}

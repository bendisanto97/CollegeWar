package cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import appli.Card;
import appli.MagicVariables;
import xcel.Excel;
import xcel.ExcelControlePoint;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Creates new instances of Card without knowing the specific class (that
 *      implements the interface) and return them all in a list. It also loads
 *      the cards' events and items
 */
public class CardManager {

	private static Class<? extends Card> card;

	/**
	 * @param c
	 * @see the field "card" needs to be initialized before using the class
	 *      "CardManager" that is taken care of by the static bloc of each
	 *      implementation (for example "SimpleCard") so "card" is initialized just
	 *      once
	 */
	public static void init(Class<? extends Card> c) {
		synchronized (CardManager.class) {
			card = c;
		}
	}

	/**
	 * @param avatar
	 * @param description
	 * @param possibleAnswers
	 * @param statsYes
	 * @param statsNo
	 * @param event
	 * @param item
	 * @param image
	 * @param background
	 * @param location
	 * @return new instance of Card thanks to the field Class "card" (that must have
	 *         been previously initialized)
	 */
	private static Card newCard(String avatar, String description, String image, String[] possibleAnswers,
			int[] statsYes, int[] statsNo, int background, String location, String event, String item) {
		try {
			Card c = card.newInstance();

			c.setAvatar(avatar);
			c.setDescription(description);
			c.setImage(image);

			c.setPossibleAnswers(possibleAnswers);
			c.setStatisticsYes(statsYes);
			c.setStatisticsNo(statsNo);

			c.setBackground(background);
			c.setLocation(location);
			c.setEvent(event);
			c.setItem(item);

			return c;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return list of Card using the private method "newCard"
	 */
	public static List<Card> getAllCards() {

		List<Card> cards = new ArrayList<Card>();
		ArrayList<String[]> tabExcel = Excel.getTab();
		// remove eventual lines that may have been added by Excel
		for (int i = tabExcel.size() - 1; i >= Excel.getNbLines(); --i)
			tabExcel.remove(i);

		// the first array only gathers the columns' titles
		tabExcel.remove(0);
		// index that allows to read each array
		int index = 0;

		for (String[] line : tabExcel) {

			String avatar = line[index++].trim().toUpperCase();

			String description = line[index++].trim();

			String image = line[index++];

			String[] possibleAnswers = { line[index++], line[index++] };

			int background = Integer.valueOf(line[index++]);

			String location = line[index++];

			String event = line[index++];

			String item = line[index++];

			int[] statsYes = { Integer.valueOf(line[index++]), Integer.valueOf(line[index++]),
					Integer.valueOf(line[index++]), Integer.valueOf(line[index++]), Integer.valueOf(line[index++]) };

			int[] statsNo = { Integer.valueOf(line[index++]), Integer.valueOf(line[index++]),
					Integer.valueOf(line[index++]), Integer.valueOf(line[index++]), Integer.valueOf(line[index++]) };

			cards.add(newCard(avatar, description, image, possibleAnswers, statsYes, statsNo, background, location,
					event, item));

			index = 0;
		}
		loadAllOptions(cards);
		return cards;
	}

	/**
	 * @param cards
	 * @see load all the cards' events and items
	 */
	private static void loadAllOptions(List<Card> cards) {
		Scanner sc = null;
		List<String> fileContent;
		// contains the cards to remove from cards (because they are hidden in some
		// events)
		List<Card> cardsToRemove = new ArrayList<Card>();

		Event event;
		Item item;
		int index = 0;

		for (Card card : cards) {
			event = card.getEvent();
			if (event != null) {
				// this card is an event's trigger
				File f = new File(Event.getFolder() + event.getFile() + ".save");

				try {
					sc = new Scanner(f);
					fileContent = new ArrayList<String>();
					while (sc.hasNextLine())
						fileContent.add(sc.nextLine().trim());

					// the file only has 2 lines : 1) the events's number of cards 2) boolean loop

					for (int i = 1; i < Integer.valueOf(fileContent.get(0)); ++i) {
						// the cards in the event are removed from the list and saved in the event
						// (except the trigger)
						event.addCard(cards.get(index + i));
						cardsToRemove.add(cards.get(index + i));
					}
					event.setLoop(Boolean.valueOf(fileContent.get(1)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			item = card.getItem();
			if (item != null) {
				// this card unlocks an item
				File f = new File(Item.getFolder() + item.getName() + ".save");
				try {
					sc = new Scanner(f);
					fileContent = new ArrayList<String>();
					while (sc.hasNextLine())
						fileContent.add(sc.nextLine().trim());

					// the file has 3 lines : 1) the item's stats 2) boolean trigger 3) item's image

					int line = 0;
					String[] tab = fileContent.get(line++).split(";");
					int[] stats = new int[MagicVariables.getNbStats()];
					for (int i = 0; i < stats.length; ++i)
						stats[i] = Integer.valueOf(tab[i]);
					item.setStatistics(stats);
					item.setTrigger(Boolean.valueOf(fileContent.get(line++)));
					item.setImage(fileContent.get(line));

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			index++;
		}
		//sc.close();
		cards.removeAll(cardsToRemove);
	}

	/**
	 * @return list of Card (for multiplayer mode) using the private method
	 *         "newCard"
	 */
	public static List<Card> getAllControlePoint() {

		List<Card> cards = new ArrayList<Card>();
		ArrayList<String[]> tabExcel = ExcelControlePoint.getTab();
		for (int i = tabExcel.size() - 1; i >= Excel.getNbLines(); --i)
			tabExcel.remove(i);

		// the first array only gathers the columns' titles
		tabExcel.remove(0);
		// index that allows to read each array
		int index = 0;

		for (String[] line : tabExcel) {

			String avatar = line[index++].trim().toUpperCase();

			String description = line[index++].trim();

			String[] possibleAnswers = { line[index++], line[index++] };

			int[] statsYes = { Integer.valueOf(line[index++]), Integer.valueOf(line[index++]),
					Integer.valueOf(line[index++]), Integer.valueOf(line[index++]), Integer.valueOf(line[index++]) };

			int[] statsNo = { Integer.valueOf(line[index++]), Integer.valueOf(line[index++]),
					Integer.valueOf(line[index++]), Integer.valueOf(line[index++]), Integer.valueOf(line[index++]) };

			cards.add(newCard(avatar, description, "0", possibleAnswers, statsYes, statsNo, 0, "0", "0", "0"));

			index = 0;
		}
		return cards;
	}

}

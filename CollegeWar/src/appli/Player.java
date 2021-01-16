package appli;

import java.util.LinkedList;
import java.util.List;

import cards.Item;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 29/01/2019
 * @version 1.0
 * @see Defines the player for the standard mode
 */
public class Player {

	private int[] statistics;
	private List<Item> items;

	private static final int STAT_MAX = 10;

	public Player() {
		statistics = new int[MagicVariables.getNbStats()];
		for (int i = 0; i < statistics.length; ++i)
			statistics[i] = MagicVariables.getNbStats();
		items = new LinkedList<Item>();
	}

	/**
	 * @return statistics
	 */
	public int[] getStatistics() {
		return statistics;
	}

	/**
	 * @param statistics
	 */
	public void setStatistics(int[] statistics) {
		this.statistics = statistics;
	}

	/**
	 * @return items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param item
	 * @see add item if the player doesn't already have it
	 */
	public void addItem(Item item) {
		boolean contains = false;
		for (Item i : items)
			if (i.getName().equals(item.getName())) {
				contains = true;
				break;
			}
		if (!contains)
			items.add(item);
	}

	/**
	 * @param stats
	 * @see update the player's statistics with stats and with items, all stats are
	 *      corrected if they became too high or too low
	 */
	public void add(int[] stats) {
		int[] tab;
		for (Item item : items) {
			tab = item.getStatistics();
			for (int i = 0; i < statistics.length; i++)
				statistics[i] += tab[i];
		}
		for (int i = 0; i < statistics.length; i++) {
			statistics[i] += stats[i];
			if (statistics[i] > STAT_MAX)
				statistics[i] = STAT_MAX;
			if (statistics[i] < 0)
				statistics[i] = 0;
		}
	}

	/**
	 * @return boolean meaning if the player lost or not
	 */
	public boolean isEnded() {
		for (int i = 0; i < statistics.length; ++i)
			if (statistics[i] == 0)
				return true;
		return false;
	}

}

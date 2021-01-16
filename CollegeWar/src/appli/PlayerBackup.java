package appli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 27/03/2019
 * @version 2.0
 * @see Define the player for the backup mode
 */
public class PlayerBackup implements Comparable<PlayerBackup> {

	private int id = 0;
	private int[] statistics;
	private int score = 0;

	private static final int STAT_MAX = 10;
	private final static String BACKUP_FOLDER = "./res/backup/";
	private static final String FILE_EXTENSION = ".save";

	public PlayerBackup(int score, int[] stats) {
		id = getSavedId();
		this.score = score;
		statistics = stats;
	}

	public PlayerBackup(int score, int ID, int[] stats) {
		id = ID;
		this.score = score;
		statistics = stats;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return statistics
	 */
	public int[] getStatistics() {
		return statistics;
	}

	/**
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param stats
	 * @see update the player's statistics with stats, all stats are corrected if
	 *      they became too high or too low
	 */
	public void add(int[] stats) {
		for (int i = 0; i < statistics.length; i++) {
			statistics[i] += stats[i];
			if (statistics[i] > STAT_MAX)
				statistics[i] = STAT_MAX;
			else if (statistics[i] < 0)
				statistics[i] = 0;
		}
	}

	/**
	 * @param intArr
	 * @return the conversion of intArr into a formatted String
	 */
	private static String convertIntArrayToString(int[] intArr) {
		String res = "";
		for (int stat : intArr) {
			res += stat + "-";
		}
		return res;
	}

	/**
	 * @return the incremented id from the counter's file
	 */
	private static int getSavedId() {
		Path path = Paths.get(BACKUP_FOLDER + "counter" + FILE_EXTENSION);
		try {
			int value = Integer.valueOf(new String(Files.readAllBytes(path)).trim()) + 1;
			Files.write(path, String.valueOf(value).getBytes(), StandardOpenOption.WRITE);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @throws IOException
	 * @see save the current player to a defined text file
	 */
	public void save() throws IOException {
		Path path = Paths.get(getBackupFile());
		try {
			Files.write(path, this.toString().getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return list of all players from the score's file, ranked in descending order
	 * @throws IOException
	 */
	public static List<PlayerBackup> getPlayerRanking() throws IOException {
		Path score = Paths.get(getScoreFile());
		List<PlayerBackup> playerRanking = PlayerBackup.restore(score);
		Collections.sort(playerRanking, Collections.reverseOrder());
		return playerRanking;
	}

	/**
	 * @param path
	 * @return list of all players from path's file
	 * @throws IOException
	 */
	public static List<PlayerBackup> restore(Path path) throws IOException {
		List<PlayerBackup> players = new ArrayList<PlayerBackup>();
		String fileContent = new String(Files.readAllBytes(path));

		if (!fileContent.isEmpty()) {
			String[] fileParts = fileContent.split("/");

			for (String onePart : fileParts) {
				if (!onePart.equals("")) {
					String[] onePlayer = onePart.split("-");
					int[] stats = new int[MagicVariables.getNbStats()];

					int index = 0;
					for (int i = 0; i < stats.length; ++i)
						stats[i] = Integer.valueOf(onePlayer[index++]);
					PlayerBackup p = new PlayerBackup(Integer.valueOf(onePlayer[index++]),
							Integer.valueOf(onePlayer[index]), stats);
					players.add(p);
				}
			}
			return players;

		} else {
			return players;
		}

	}

	@Override
	public int compareTo(PlayerBackup o) {
		Integer Score1 = this.getScore();
		Integer Score2 = o.getScore();
		return Score1.compareTo(Score2);
	}

	/**
	 * @return boolean meaning if the player lost or not
	 */
	public boolean isEnded() {
		for (int i = 0; i < statistics.length; i++)
			if (statistics[i] == 0)
				return true;
		return false;
	}

	/**
	 * @return backup file's path
	 */
	public static String getBackupFile() {
		return BACKUP_FOLDER + "backup" + FILE_EXTENSION;
	}

	/**
	 * @return score file's path
	 */
	public static String getScoreFile() {
		return BACKUP_FOLDER + "score" + FILE_EXTENSION;
	}

	// Create a String from the data of a Player
	@Override
	public String toString() {
		return convertIntArrayToString(statistics) + score + "-" + id + "/";
	}

}

package appli;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see This class is like a library that gathers all constant variables (magics
 *      numbers, strings ...) of the project, that are used in more than just
 *      one class (to avoid any redondancy). It also provides the coordinates of
 *      all characters
 */
public class MagicVariables {

	private final static int NB_CHOICES = 2;
	private final static int NB_STATS = 5;
	private final static int ESCAPE_LOOP = 10;

	private final static String IMAGE_FOLDER = "./images/";
	private final static String AVATAR_FOLDER = IMAGE_FOLDER + "avatars/";

	public static int getNbChoices() {
		return NB_CHOICES;
	}

	public static int getNbStats() {
		return NB_STATS;
	}

	public static int getEscapeLoop() {
		return ESCAPE_LOOP;
	}

	public static String getImageFolder() {
		return IMAGE_FOLDER;
	}

	public static String getAvatarFolder() {
		return AVATAR_FOLDER;
	}

	public static int[] getPositionFor(String name) {
		switch (name) {
		case "0":
			int[] r = { 423, 423, 149, 102 };
			return r;
		case "marc":
			int[] r1 = { 423, 423, 149, 102 };
			return r1;
		case "léa":
			int[] r2 = { 423, 423, 149, 102 };
			return r2;
		case "marvin":
			int[] r3 = { 423, 423, 149, 102 };
			return r3;
		case "anonyme":
			int[] r4 = { 423, 390, 149, 102 };
			return r4;
		case "norton":
			int[] r5 = { 423, 423, 149, 102 };
			return r5;
		case "ryan":
			int[] r6 = { 423, 423, 149, 102 };
			return r6;
		case "cpu":
			int[] r7 = { 380, 380, 170, 145 };
			return r7;
		default:
			break;
		}
		return null;
	}

}

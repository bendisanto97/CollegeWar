package javaFxComponents;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the statistics' images in the graphic interface
 */
public class Statistics {

	private Group gtp;

	private ImageView imgSavoir;
	private ImageView imgPopularite;
	private ImageView imgSante;
	private ImageView imgArgent;
	private ImageView imgMoral;

	private List<Image> gaugesSavoir;
	private List<Image> gaugesPopularite;
	private List<Image> gaugesSante;
	private List<Image> gaugesArgent;
	private List<Image> gaugesMoral;

	private static int lastCard = 10;
	private static final String FILE_PATH = "file:./images/jauges/";
	private static final String FILE_EXTENSION = ".png";

	public Statistics() {
		gtp = new Group();

		gaugesSavoir = new ArrayList<Image>();
		gaugesPopularite = new ArrayList<Image>();
		gaugesSante = new ArrayList<Image>();
		gaugesArgent = new ArrayList<Image>();
		gaugesMoral = new ArrayList<Image>();

		for (int i = 0; i <= lastCard; i++)
			gaugesSavoir.add(new Image(FILE_PATH + "savoir" + i + FILE_EXTENSION));
		for (int i = 0; i <= lastCard; i++)
			gaugesPopularite.add(new Image(FILE_PATH + "popularite" + i + FILE_EXTENSION));
		for (int i = 0; i <= lastCard; i++)
			gaugesSante.add(new Image(FILE_PATH + "santé" + i + FILE_EXTENSION));
		for (int i = 0; i <= lastCard; i++)
			gaugesArgent.add(new Image(FILE_PATH + "argent" + i + FILE_EXTENSION));
		for (int i = 0; i <= lastCard; i++)
			gaugesMoral.add(new Image(FILE_PATH + "moral" + i + FILE_EXTENSION));

		// we left the magic numbers because they are coordinates
		imgSavoir = ToolJavaFX.newImageViewByImage(gaugesSavoir.get(0), 74, 74, 220, 14);
		imgPopularite = ToolJavaFX.newImageViewByImage(gaugesPopularite.get(0), 74, 74, 410, 18);
		imgSante = ToolJavaFX.newImageViewByImage(gaugesSante.get(0), 74, 74, 600, 22);
		imgArgent = ToolJavaFX.newImageViewByImage(gaugesArgent.get(0), 74, 74, 800, 23);
		imgMoral = ToolJavaFX.newImageViewByImage(gaugesMoral.get(0), 74, 74, 1000, 24);

		gtp.getChildren().addAll(imgSavoir, imgPopularite, imgSante, imgMoral, imgArgent);
	}

	/**
	 * @return gtp
	 */
	public Group getGtp() {
		return gtp;
	}

	/**
	 * @param index
	 */
	public void setStatiti(int[] index) {
		imgSavoir.setImage(gaugesSavoir.get(index[0]));
		imgPopularite.setImage(gaugesPopularite.get(index[1]));
		imgSante.setImage(gaugesSante.get(index[2]));
		imgArgent.setImage(gaugesArgent.get(index[3]));
		imgMoral.setImage(gaugesMoral.get(index[4]));
	}
}

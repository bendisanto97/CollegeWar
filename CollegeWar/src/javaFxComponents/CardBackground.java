package javaFxComponents;

import java.util.ArrayList;
import java.util.List;

import javaFxComponents.ToolJavaFX;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the background of the graphic interface
 */
public class CardBackground {

	private Group gtp;

	private List<PathTransition> paths;
	private ImageView imgFond;
	private List<ImageView> imgViewClouds;
	private List<List<Image>> imgClouds;
	private List<Image> fonds;

	public CardBackground() {

		paths = new ArrayList<PathTransition>();
		imgViewClouds = new ArrayList<ImageView>();
		imgClouds = new ArrayList<List<Image>>();
		gtp = new Group();

		fonds = new ArrayList<Image>();
		imgClouds.add(new ArrayList<Image>());
		imgClouds.add(new ArrayList<Image>());
		imgClouds.add(new ArrayList<Image>());

		for (int i = 1; i <= 4; i++)
			imgClouds.get(0).add(new Image("file:images/background/nuage1_" + i + ".png"));
		for (int i = 1; i <= 4; i++)
			imgClouds.get(1).add(new Image("file:images/background/nuage2_" + i + ".png"));
		for (int i = 1; i <= 4; i++)
			imgClouds.get(2).add(new Image("file:images/background/nuage3_" + i + ".png"));
		for (int i = 1; i <= 4; i++)
			fonds.add(new Image("file:images/background/fond_" + i + ".png"));

		imgFond = ToolJavaFX.newImageViewByImage(fonds.get(0), 960, 1280, 0, 0);
		imgViewClouds.add(ToolJavaFX.newImageViewByImage(imgClouds.get(0).get(0), 44, 156, 0, 175));// 0
		imgViewClouds.add(ToolJavaFX.newImageViewByImage(imgClouds.get(1).get(0), 72, 170, 80, 372));
		imgViewClouds.add(ToolJavaFX.newImageViewByImage(imgClouds.get(2).get(0), 72, 186, 20, 635));
		imgViewClouds.add(ToolJavaFX.newImageViewByImage(imgClouds.get(0).get(0), 44, 156, 0, 300));
		imgViewClouds.add(ToolJavaFX.newImageViewByImage(imgClouds.get(1).get(0), 72, 170, 80, 600));
		imgViewClouds.add(ToolJavaFX.newImageViewByImage(imgClouds.get(2).get(0), 72, 186, 20, 350));
		imgViewClouds.add(ToolJavaFX.newImageViewByImage(imgClouds.get(2).get(0), 44, 156, 0, 200));

		paths.add(ToolJavaFX.newPathTransitionImage(new MoveTo(-150, 175), new LineTo(1600, 175), imgViewClouds.get(0),
				25.0 + getRandom(5)));
		paths.add(ToolJavaFX.newPathTransitionImage(new MoveTo(-100, 372), new LineTo(1600, 372), imgViewClouds.get(1),
				17.0 + getRandom(5)));
		paths.add(ToolJavaFX.newPathTransitionImage(new MoveTo(-200, 635), new LineTo(1600, 635), imgViewClouds.get(2),
				21.0 + getRandom(5)));
		paths.add(ToolJavaFX.newPathTransitionImage(new MoveTo(-250, 300), new LineTo(1600, 300), imgViewClouds.get(3),
				28.0 + getRandom(5)));
		paths.add(ToolJavaFX.newPathTransitionImage(new MoveTo(-500, 600), new LineTo(1600, 600), imgViewClouds.get(4),
				38.0 + getRandom(5)));
		paths.add(ToolJavaFX.newPathTransitionImage(new MoveTo(-800, 350), new LineTo(1600, 350), imgViewClouds.get(5),
				58.0 + getRandom(5)));
		paths.add(ToolJavaFX.newPathTransitionImage(new MoveTo(-170, 200), new LineTo(1600, 200), imgViewClouds.get(6),
				18.0 + getRandom(5)));

		gtp.getChildren().addAll(imgFond);
		for (ImageView iv : imgViewClouds)
			gtp.getChildren().addAll(iv);
	}

	/**
	 * @return gtp
	 */
	public Group getGtp() {
		return gtp;
	}

	/**
	 * @param fond
	 */
	public void setBackground(int fond) {
		if (fond == 0)
			fond = (int) (Math.random() * 4 + 1);
		imgFond.setImage(fonds.get(fond - 1));
		for (int i = 0; i < imgViewClouds.size(); i++) {
			imgViewClouds.get(i).setImage(imgClouds.get(i % 3).get(fond - 1));
		}

	}

	public void stop() {
		for (PathTransition pt : paths) {
			pt.stop();
		}
	}

	private double getRandom(int r) {
		return Math.random() * r;
	}
}

package appliFx;

import appli.Card;
import javaFxComponents.ApplicationFX;
import javaFxComponents.ToolJavaFX;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import menu.BackgroundMusic;
import network.NextControlPoint;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the graphic interface for the backup mode
 */
public class WindowOnline extends Application {

	private Stage pm;
	private AppliOnline oa;
	private NextControlPoint ncp;
	private ApplicationFX appFX;

	@Override
	public void start(Stage primaryStage) {
		BackgroundMusic.stopMusic();
		BackgroundMusic.setMediaPlayer("res/sounds/IngameMusic.mp3");
		BackgroundMusic.playMusic(0);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < 100; i++) {
					try {
						Thread.sleep((int) (Math.random() * 100));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BackgroundMusic.setVolume(((float) i / 100));
				}
			}
		});
		t.start();
		appFX = new ApplicationFX();
		pm = primaryStage;
		oa = AppliOnline.getInstance();
		ncp = new NextControlPoint();
		nextCard();
		pm.show();
	}

	/**
	 * @param scene
	 */
	private void setScene(Scene scene) {
		pm.setScene(scene);
	}

	/**
	 * @see uses "nextCard"
	 */
	public void goToNext() {
		if (oa.hasNext()) {
			oa.nextC();
			nextCard();
		}
	}

	/**
	 * @see adapts the graphic interface for the next card
	 */
	public void nextCard() {
		Card current = oa.getC();
		appFX.setApplication(current.getBackground(), oa.getPlayer().getStatistics(), oa.getPlayer().getItems(),
				current.getLocation(), current.getImage(), current.getDescription(), current.getAvatar());

		Group grp = new Group();
		Scene scene = new Scene(grp, 1280, 960);

		Button btnTrue = ToolJavaFX.newButtonComplete(88, 816, 522, 144, current.getPossibleAnswers()[0],
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(oa.setAnswerLocalPlayer(false))
							goToNext();
					}
				});

		Button btnFalse = ToolJavaFX.newButtonComplete(645, 816, 545, 144, current.getPossibleAnswers()[1],
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if(oa.setAnswerLocalPlayer(true))
							goToNext();
					}
				});

		grp.getChildren().add(appFX.getGtp());
		grp.getChildren().add(btnFalse);
		grp.getChildren().add(btnTrue);
		grp.getChildren().add(ncp.getGroup());
		setScene(scene);

	}

	public void controlePointPassed() {
		ncp.setOff();
	}

	public boolean isControlePointForced() {
		return ncp.isOn();
	}

	public void nextControlePoint() {
		ncp.setOn();
	}

}

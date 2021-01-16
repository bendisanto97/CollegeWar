package menu;

import java.io.File;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Defines a menuitem online
 */
public class MenuItemOnline extends StackPane {

	public MenuItemOnline(String name) {

		Rectangle rectangle = new Rectangle(500, 45);
		rectangle.setOpacity(0);

		Text texte = new Text(name);
		texte.setFill(Color.WHITE);
		texte.setFont(StartGame.getMenu());
		texte.setTextAlignment(TextAlignment.CENTER);
		getChildren().addAll(rectangle, texte);

		setOnMouseEntered(event -> {
			texte.setFill(Color.BLACK);
			Media sound = new Media(new File("res/sounds/click.mp3").toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		});
		setOnMouseExited(event -> {
			texte.setFill(Color.WHITE);
		});
		setOnMousePressed(event -> {
			texte.setFont(StartGame.getMenuOnClick());

		});
		setOnMouseReleased(event -> {
			texte.setFont(StartGame.getMenu());
		});
	}

	public MenuItemOnline() {

		Rectangle rectangle = new Rectangle(500, 45);
		rectangle.setOpacity(0);

		Text texte = new Text("----");
		texte.setFill(Color.DARKGREY);
		texte.setFont(StartGame.getMenu());
		texte.setTextAlignment(TextAlignment.CENTER);
		getChildren().addAll(rectangle, texte);

		setOnMouseEntered(event -> {
			this.setDisable(true);
		});
		setOnMouseExited(event -> {
			this.setDisable(true);
		});
		setOnMousePressed(event -> {
			this.setDisable(true);
		});
		setOnMouseReleased(event -> {
			this.setDisable(true);
		});

	}

}

package javaFxComponents;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * @author Benjamin Di Santo, Simon Weber, Paul Courbois, Jules Plateau and
 *         Julien Leopardo
 * @since 19/02/2019
 * @version 1.0
 * @see Allow to manipulate the graphic interface (buttons, texts, images ...)
 */
public abstract class ToolJavaFX {

	/**
	 * @param layoutX
	 * @param layoutY
	 * @param text
	 * @param onAction
	 * @return
	 */
	public static Button newButton(int layoutX, int layoutY, String text, EventHandler<ActionEvent> onAction) {
		Button btn = new Button();
		btn.setLayoutX(layoutX);
		btn.setLayoutY(layoutY);
		btn.setText(text);
		btn.setOnAction(onAction);
		return btn;
	}

	/**
	 * @param layoutX
	 * @param layoutY
	 * @param width
	 * @param height
	 * @param text
	 * @param action1
	 * @return the completed button
	 */
	public static Button newButtonComplete(int layoutX, int layoutY, int width, int height, String text,
			EventHandler<ActionEvent> action1) {
		Button btn = new Button();
		btn.setLayoutX(layoutX);
		btn.setLayoutY(layoutY);
		btn.setPrefSize(width, height);
		btn.setText(text);
		btn.setWrapText(true);
		btn.setTextAlignment(TextAlignment.CENTER);
		btn.setFont(Font.loadFont("file:res/fonts/pixel.ttf", 35));
		btn.setBackground(
				new Background(new BackgroundFill(Color.rgb(155, 173, 183), CornerRadii.EMPTY, Insets.EMPTY)));
		btn.setOnAction(action1);
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				btn.setTextFill(Color.WHITE);
			}
		});
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				btn.setTextFill(Color.BLACK);
			}
		});
		return btn;
	}

	/**
	 * @param file
	 * @param fitHeight
	 * @param fitWidth
	 * @param x
	 * @return the image
	 */
	public static ImageView newImageViewByFile(String file, double fitHeight, double fitWidth, double x, double y) {
		ImageView imageV = null;
		try {
			Image image = new Image(file);
			imageV = new ImageView(image);
			imageV.setFitHeight(fitHeight);
			imageV.setFitWidth(fitWidth);
			imageV.setX(x);
			imageV.setY(y);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return imageV;
	}

	/**
	 * @param img
	 * @param fitHeight
	 * @param fitWidth
	 * @param x
	 * @return the image
	 */
	public static ImageView newImageViewByImage(Image img, int fitHeight, int fitWidth, int x, int y) {
		ImageView imageV = null;
		try {
			Image image = img;
			imageV = new ImageView(image);
			imageV.setFitHeight(fitHeight);
			imageV.setFitWidth(fitWidth);
			imageV.setX(x);
			imageV.setY(y);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return imageV;
	}

	/**
	 * 
	 * @param layoutX
	 * @param layoutY
	 * @param width
	 * @param height
	 * @param text
	 * @return the label
	 */
	public static Label newLabel(int layoutX, int layoutY, double width, double height, String text, int f) {
		Label label = new Label();
		label.setFont(Font.loadFont("file:res/fonts/pixel.ttf", f));
		label.setPrefSize(width, height);
		label.setLayoutX(layoutX);
		label.setLayoutY(layoutY);
		label.setText(text);
		label.setTextFill(Color.WHITE);
		label.setTextAlignment(TextAlignment.JUSTIFY);
		label.setWrapText(true);
		// label.setBorder(new Border(new BorderStroke(Color.BLACK,
		// BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		return label;
	}

	/**
	 * 
	 * @param moveTo
	 * @param lineTo
	 * @param img
	 * @param seconds
	 * @return the path
	 */
	public static PathTransition newPathTransitionImage(MoveTo moveTo, LineTo lineTo, ImageView img, double seconds) {
		Path path = new Path();
		path.getElements().addAll(moveTo, lineTo);

		PathTransition pathT = new PathTransition();
		pathT.setPath(path);
		pathT.setNode(img);
		pathT.setDuration(Duration.seconds(seconds));
		pathT.setCycleCount(Timeline.INDEFINITE);
		pathT.setAutoReverse(false);
		pathT.play();
		return pathT;
	}

	/**
	 * 
	 * @param moveTo
	 * @param lineTo
	 * @param img
	 * @param seconds
	 * @return the path
	 */
	public static PathTransition newPathTransitionText(MoveTo moveTo, LineTo lineTo, Label lab, double seconds) {
		Path path = new Path();
		path.getElements().addAll(moveTo, lineTo);

		PathTransition pathT = new PathTransition();
		pathT.setPath(path);
		pathT.setNode(lab);
		pathT.setDuration(Duration.seconds(seconds));
		pathT.setCycleCount(4);
		pathT.setAutoReverse(false);
		pathT.play();
		return pathT;
	}

	/**
	 * @param x
	 * @param y
	 * @param string
	 * @param ta
	 * @return the text
	 */
	public static Text newText(int x, int y, String string, TextAlignment ta) {
		Text btn = new Text();
		btn.setLayoutX(x);
		btn.setLayoutY(y);
		btn.setText(string);
		btn.setTextAlignment(ta);
		return btn;
	}

	/**
	 * @param x
	 * @param y
	 * @param string
	 * @param font
	 * @return the text
	 */
	public static Text newTextWithFont(int x, int y, String string, Font font) {
		Text txt = new Text();
		txt.setLayoutX(x);
		txt.setLayoutY(y);
		txt.setText(string);
		txt.setFont(font);
		return txt;
	}

	/**
	 * @param x
	 * @param xDif
	 * @param y
	 * @param string
	 * @param font
	 * @param imgName
	 * @return profilcredits
	 */
	public static Group profilCredits(int x, int xDif, int y, String string, Font font, String imgName) {
		Group ret = new Group();
		ImageView profil = new ImageView(new Image("file:images/profil/" + imgName + ".png"));
		profil.setFitWidth(100);
		profil.setFitHeight(105);

		profil.setTranslateX(x);
		profil.setTranslateY(y);
		Text texte1 = new Text(string);
		texte1.setTranslateX(x - xDif);
		texte1.setTranslateY(y + 140);
		texte1.setFont(font);
		ret.getChildren().add(texte1);
		ret.getChildren().add(profil);
		return ret;

	}
}

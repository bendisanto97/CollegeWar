package javaFxComponents;

import java.util.ArrayList;
import java.util.List;

import appli.MagicVariables;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Manages the textbox of the graphic interface
 */
public class TextBox {

	private Group gtp;

	private ImageView imgAvatar;
	private ImageView textBackground;
	private Label textName;
	private Label cardText;

	private ImageView buttons;

	private List<Image> avatars;

	public TextBox() {
		gtp = new Group();

		avatars = new ArrayList<Image>();

		avatars.add(new Image("file:images/avatars/0.png"));
		avatars.add(new Image("file:images/avatars/marc.png"));
		avatars.add(new Image("file:images/avatars/léa.png"));
		avatars.add(new Image("file:images/avatars/marvin.png"));
		avatars.add(new Image("file:images/avatars/anonyme.png"));
		avatars.add(new Image("file:images/avatars/norton.png"));
		avatars.add(new Image("file:images/avatars/cpu.png"));
		avatars.add(new Image("file:images/avatars/ryan.png"));

		avatars.add(new Image("file:images/avatars/léa.png"));

		buttons = ToolJavaFX.newImageViewByFile("file:images/interface/fond_reponse.png", 169.2, 1177.2, 53, 791);
		textBackground = ToolJavaFX.newImageViewByFile("file:images/interface/fond_text.png", 360, 1008, 159, 190);
		imgAvatar = ToolJavaFX.newImageViewByImage(avatars.get(0), 423, 423, 149, 102);

		cardText = ToolJavaFX.newLabel(489, 195, 670, 250, "ee", 35);
		textName = ToolJavaFX.newLabel(979, 496, 180, 0, "ee", 25);

		gtp.getChildren().addAll(textBackground, imgAvatar, cardText, textName, buttons);
	}

	/**
	 * @return gtp
	 */
	public Group getGtp() {
		return gtp;
	}

	/**
	 * @param avatar
	 * @param text
	 * @param name
	 */
	@SuppressWarnings("deprecation")
	public void setTextBox(String avatar, String text, String name) {
		String file = "file:images/avatars/" + avatar.toLowerCase() + ".png";
		if (avatar.equals("0"))
			setTextForZero();
		else
			resizeText();

		imgAvatar.setImage(null);
		for (int i = 0; i < avatars.size(); i++) {
			if (avatars.get(i).impl_getUrl().equals(file)) {
				imgAvatar.setImage(avatars.get(i));
				setImageViewPropriety(avatar);
			}
		}
		cardText.setText(text);
		textName.setText(name);
	}

	private void setTextForZero() {
		cardText.setLayoutX(450 - 50);
		cardText.setPrefWidth(670 + 89);

	}

	private void resizeText() {
		cardText.setLayoutX(489);
		cardText.setPrefWidth(670);

	}

	/**
	 * @param name
	 */
	private void setImageViewPropriety(String name) {
		int[] tab = MagicVariables.getPositionFor(name);
		imgAvatar.setFitHeight(tab[0]);
		imgAvatar.setFitWidth(tab[1]);
		imgAvatar.setX(tab[2]);
		imgAvatar.setY(tab[3]);
	}
}

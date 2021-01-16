package network;

import javaFxComponents.ToolJavaFX;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Defines the next control point of the multiplayer mode
 */
public class NextControlPoint {

	private Group grp;
	private Text tv;
	private boolean on;

	public NextControlPoint() {
		grp = new Group();
		on = false;
		tv = ToolJavaFX.newText(50, 50, "", TextAlignment.CENTER);
		tv.setFill(Color.RED);
		grp.getChildren().add(tv);
	}

	public void setOn() {
		on = true;
		tv.setText("Controle Point");
	}

	public void setOff() {
		on = false;
		tv.setText("");
	}

	public boolean isOn() {
		return on;
	}

	/**
	 * @return grp
	 */
	public Group getGroup() {
		return grp;
	}
}

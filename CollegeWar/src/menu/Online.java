package menu;

import javaFxComponents.CardBackground;
import javaFxComponents.ToolJavaFX;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.WindowEvent;
import network.Client;
import network.Server;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Embodies the page "online" in the menu
 */
public class Online {

	private static CardBackground back;

	static {
		back = new CardBackground();
		back.setBackground(1);
	}

	/**
	 * @param menu
	 * @return the complete page
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Parent online(StartGame menu) {
		Pane pane = new Pane();
		pane.setPrefSize(1280, 960);
		TextField textField = new TextField("Adresse IP");
		textField.setTranslateX(515);
		textField.setTranslateY(420);
		textField.setFont(StartGame.getSubTitles());
		Text text = new Text("");
		text.setTranslateX(480);
		text.setTranslateY(375);
		text.setFont(StartGame.getSubTitles());
		Title titre = new Title("MULTIJOUEUR");

		MenuItemOnline host = new MenuItemOnline("HOST");
		host.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				host(menu);
			}
		});
		MenuItemOnline co = new MenuItemOnline("CONNEXION");
		co.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				connexion(menu, textField.getText(), text);
			}
		});
		MenuItemOnline retur = new MenuItemOnline("RETOUR");
		retur.setOnMouseClicked(event -> menu.getWindow().setScene(menu.getAccueil()));
		MenuBoxOnline menuBox = new MenuBoxOnline(host, co, retur);
		menuBox.setTranslateX(395);
		menuBox.setTranslateY(520);

		pane.getChildren().addAll(back.getGtp(), titre, text, textField, menuBox);
		return pane;
	}

	/**
	 * @param menu
	 * @return the complete page
	 */
	private static Parent waitingForConnection(StartGame menu) {
		Pane pane = new Pane();
		pane.setPrefSize(1280, 960);
		Text txt = ToolJavaFX.newText(500, 400, "attente de connexion ...", TextAlignment.CENTER);
		txt.setTranslateX(-40);
		txt.setTranslateY(100);
		txt.setFont(StartGame.getSubTitles());
		MenuItemOnline retour = new MenuItemOnline("RETOUR");
		retour.setOnMouseClicked(event -> menu.getWindow().setScene(menu.getAccueil()));

		MenuBoxOnline menuBox = new MenuBoxOnline(retour);
		menuBox.setTranslateX(390);
		menuBox.setTranslateY(658);
		Title titre = new Title("MULTIJOUEUR");
		pane.getChildren().addAll(back.getGtp(), titre, txt, menuBox);
		return pane;
	}

	/**
	 * @param menu
	 */
	private static void host(StartGame menu) {
		Server c = new Server(2000, menu.getWindow());
		new Thread(c).start();
		menu.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
			}
		});
		Scene wfc = new Scene(waitingForConnection(menu));
		menu.getWindow().setScene(wfc);
	}

	/**
	 * @param menu
	 * @param str
	 * @param t
	 */
	private static void connexion(StartGame menu, String str, Text t) {
		new Thread(new Client(str, menu.getWindow(), t, back)).start();
	}
}

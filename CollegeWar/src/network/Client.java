package network;

import java.io.IOException;
import java.net.Socket;

import appliFx.AppliOnline;
import javaFxComponents.CardBackground;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Benjamin Di Santo, Simon Weber, Paul Courbois, Jules Plateau and
 *         Julien Leopardo
 * @since 19/02/2019
 * @version 1.0
 * @see Defines a client for the multiplayer mode
 */
public class Client implements Runnable {

	private String ip;
	private Stage stage;
	private Text text;
	private CardBackground toStop;

	public Client(String ip, Stage stage, Text text, CardBackground back) {
		this.ip = ip;
		this.stage = stage;
		this.text = text;
		toStop = back;
	}

	@Override
	public void run() {

		try {
			Socket s = new Socket(ip, 2000);
			ManagerConnexionIn.setSocket(s);
			ManagerConnexionOut.setSocket(s);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					AppliOnline.start(stage);
				}
			});
			toStop.stop();
		} catch (IOException e) {
			text.setText("erreur de connexion");
			e.printStackTrace();
		}

	}

}

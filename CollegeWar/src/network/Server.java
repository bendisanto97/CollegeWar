package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import appliFx.AppliOnline;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Defines the server of the multiplayer mode
 */
public class Server implements Runnable {

	private Boolean actif = false;
	private int port;
	private Stage primaryStage;

	public Server(int port, Stage primaryStage) {
		this.port = port;
		this.primaryStage = primaryStage;
	}

	@Override
	public void run() {
		actif = true;
		try {
			ServerSocket mainSocket = new ServerSocket(port);
			System.out.println(InetAddress.getLocalHost() + " : serveur actif");
			Socket socketServer = mainSocket.accept();
			System.out.println("connection");
			ManagerConnexionIn.setSocket(socketServer);
			ManagerConnexionOut.setSocket(socketServer);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					AppliOnline.start(primaryStage);
				}
			});
			mainSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean isActif() {
		return actif;
	}

	public void desactivate() {
		actif = false;
	}
}

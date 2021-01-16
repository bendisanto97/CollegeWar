package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import appliFx.AppliOnline;

/**
 * @author Benjamin Di Santo, Simon Weber, Paul Courbois, Jules Plateau and
 *         Julien Leopardo
 * @since 19/02/2019
 * @version 1.0
 * @see Manages incoming connections for the multiplayer mode. This class is a
 *      singleton
 */
public class ManagerConnexionIn implements Runnable {

	private static ManagerConnexionIn instance;
	private Socket socket;

	private ManagerConnexionIn() {
	}

	/**
	 * @return the unique instance of ManagerConnexionIn
	 */
	public static ManagerConnexionIn getInstance() {
		return instance;
	}

	/**
	 * @param socket
	 */
	public static void setSocket(Socket socket) {
		instance = new ManagerConnexionIn();
		instance.socket = socket;
		new Thread(instance).start();
	}

	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while (true) {
				line = in.readLine();
				if (line.compareTo("EndOfControlPoint") == 0)
					AppliOnline.getInstance().nextControlPoint();
				if (line.substring(0, 4).compareTo("STAT") == 0)
					AppliOnline.getInstance().setStatOnlinePlayer(StringToTab(line.substring(4)));
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param str
	 * @return the transformed string
	 */
	private int[] StringToTab(String str) {
		String[] temp = str.split(" ");
		int[] ret = new int[temp.length];

		for (int i = 0; i < temp.length; i++) {
			ret[i] = Integer.parseInt(temp[i]);
		}

		return ret;
	}

}
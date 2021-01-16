package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Benjamin Di Santo, Simon Weber, Paul Courbois, Jules Plateau and
 *         Julien Leopardo
 * @since 19/02/2019
 * @version 1.0
 * @see Manages outgoing connections for the multiplayer mode. This class is a
 *      singleton
 */
public class ManagerConnexionOut {

	private static ManagerConnexionOut instance;
	private Socket socket;

	private ManagerConnexionOut() {
	}

	/**
	 * @return the unique instance of ManagerConnexionOut
	 */
	public static ManagerConnexionOut getInstance() {
		return instance;
	}

	/**
	 * @param socket
	 */
	public static void setSocket(Socket socket) {
		instance = new ManagerConnexionOut();
		instance.socket = socket;
	}

	/**
	 * @throws IOException
	 */
	public void sendEndControlePoint() throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("EndOfControlPoint");
	}

	/**
	 * @param stat
	 * @throws IOException
	 */
	public void sendStat(int[] stat) throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("STAT" + TabToString(stat));
	}

	/**
	 * @param tab
	 * @return the transformed string
	 */
	private String TabToString(int[] tab) {
		String ret;
		ret = tab[0] + "";
		for (int i = 1; i < tab.length; i++) {
			ret += " " + tab[i];
		}
		return ret;
	}
}
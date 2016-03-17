package serverHandlers;

import java.io.*;
import java.net.*;

import playerData.Player;
import playerData.client;
import root.server;

public class cHandler extends Thread {

	public void run() {
		while (online) {	
			try {
				String mes = r.readLine();
				if (!mes.equalsIgnoreCase("")) inGameMessage(mes);
			} catch (Exception e) {
				System.out.println("Error reading the incoming data, Must've lost connection to the s2s server");
				destruct();
			}
		}
	}


	public void inGameMessage(String str) {
		for (Player p : server.playerHandler.players) {
			if(p != null) {
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")) {
					person.sendMessage(str);
				}
			}
		}
	}

	public static synchronized void message(String m, String n, String t) {
		try {
			if (online) {
				p.println("["+t+"]["+n+"]: "+m);
				p.println("["+t+"]["+n+"]: "+m);
				p.flush();
			} else {
				//nothing to do, just like to have an else
			}
		} catch (Exception e) {}
	}

	
	public static void destruct() {
		s = null;
		p = null;
		r = null;
		online = false;
		System.gc();
		return;
	}

public static Socket s = null;
public static PrintWriter p = null;
public static BufferedReader r = null;
public static String tag = "";
public static Thread sep = null;
public static boolean online = false;
}
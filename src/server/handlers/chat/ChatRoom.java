package server.handlers.chat;
import java.util.LinkedList;

import client.Player;
import client.client;

public class ChatRoom {
	private LinkedList<Player> playerList = new LinkedList<Player>();
	private LinkedList<String> chatBuffer = new LinkedList<String>();

	private String chatRoomName;
	private int rights;
	private String initials = "";


	public ChatRoom(String name, int rights){
		this.chatRoomName = name;
		this.rights = rights;
		for(String s : name.split(" ")){
			initials += s.charAt(0)+".";
		}
	}

	public String getChatRoomName(){
		return this.chatRoomName;
	}

	public int getChatRoomRights(){
		return this.rights;
	}

	public String[] currentPlayerNamesInChat(){
		String[] pn = new String[playerList.size()];
		int counter = 0;
		for(Player p : playerList){
			if(p != null){
				pn[counter] = p.playerName;
				//misc.println("pn at index "+counter+" is "+pn[counter]);
				counter += 1;
			}
		}
		return pn;
	}

	public void deliverAllMessages(){
		while(!chatBuffer.isEmpty()){
			String msg = chatBuffer.poll();
			for(Player p : playerList){
				if(p != null){
					client c = (client)p;
					if(c != null)
						c.sendMessage("["+initials+"] "+msg);
				}
			}
		}
	}

	public void deliverMessage(String author, String msg){
		if(author.equals("")) chatBuffer.add(" [Status Update] "+msg);
		else chatBuffer.add(author+": "+msg);
	}

	public void addPlayer(Player p){
		for(Player p2 : playerList){
			if(p2 == p) return;
		}
		playerList.add(p);
	}

	public void removePlayer(Player p){
		for(Player p2 : playerList){
			if(p2 == p) playerList.remove(p2);
		}
	}

}

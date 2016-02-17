
public class ChatRoomHandler {

	private client c;
	
	public ChatRoomHandler(client pc){
		this.c = pc;
	}
	
	public void playersInChatMenu(){
		String r = "";
		String players = "";
		if(c.currentChatRoom.getChatRoomRights() < 1) r = "Public";
		if(c.currentChatRoom.getChatRoomRights() == 1) r = "Moderator";
		if(c.currentChatRoom.getChatRoomRights() > 1) r = "Administrator";
		
		for(String name : c.currentChatRoom.currentPlayerNamesInChat()){
			players += "#"+name;
		}
		
		c.getFrameMethodHandler().menu("@gre@"+c.currentChatRoom.getChatRoomName()+" Information###"
				+ "#Chat Type: "+r
				+ "#"
				+ "#@gre@Players Currently in chat:"+players
				+ "#");
	}
	
	public void joinChatRoom(ChatRoom ch){
		if(ch == null) return;
		c.sendMessage("Joining: "+ch.getChatRoomName()+"...");
		boolean canjoin = true;
		int chrights = ch.getChatRoomRights();
		
		if(chrights == 1 && c.Donar == 0) canjoin = false;
		if(c.playerRights > 0) canjoin = true; //mods and admins can join any donar/public rooms
		if(chrights == 3 && c.playerRights < 2) canjoin = false; // only admins can join admin rooms
		if(chrights == 2 && c.playerRights < 1) canjoin = false; 
		
		if(canjoin){
		leaveChatRoom();
		c.currentChatRoom = ch;
		generateChatTab(ch.getChatRoomName());
		c.sendMessage("You have joined chat room "+ch.getChatRoomName());
		ch.addPlayer(c);
		ch.deliverMessage("", c.playerName+" has joined the chat room.");
		}
		else c.sendMessage("You do not have the correct permissions to join this chat room.");		
	}
	
	public void leaveChatRoom(){
		if(c.currentChatRoom == null) return;
		c.sendMessage("You have left chat room "+c.currentChatRoom.getChatRoomName());
		c.currentChatRoom.removePlayer(c);
		c.currentChatRoom.deliverMessage("", c.playerName+" has left the chat room.");
		c.currentChatRoom = null;
		generateChatTab("");
	}
	
	public void sendChatMessage(String msg){
		if(c.currentChatRoom == null) c.sendMessage("You need to be in a chatroom to do that.");
		else{
			c.currentChatRoom.deliverMessage(c.playerName, msg);
		}
	}
	
	public void generateChatTab(String chatname){
		c.currentChatRoom = server.globalChatRoomHandler.findChatRoom(chatname);
		if(c.currentChatRoom != null){
			c.getFrameMethodHandler().sendQuest("@gre@Current Chatroom :", 14663);		
			c.getFrameMethodHandler().sendQuest("@gre@"+c.currentChatRoom.getChatRoomName(), 14662);
			c.getFrameMethodHandler().sendQuest("Leave Chat", 14655);
			c.getFrameMethodHandler().sendQuest("Make Default", 14656);
			c.getFrameMethodHandler().sendQuest("Chat Information", 14657);
		}
		else{
			c.getFrameMethodHandler().sendQuest("@gre@Chatroom Options :", 14663);
			c.getFrameMethodHandler().sendQuest("", 14662);
			if(c.defaultChatRoomName.length() > 0){
				c.getFrameMethodHandler().sendQuest("Join "+c.defaultChatRoomName, 14655);
				c.getFrameMethodHandler().sendQuest("Remove my default chat", 14656);
				c.getFrameMethodHandler().sendQuest("Help", 14657);
			}
			else{
				c.getFrameMethodHandler().sendQuest("", 14655);
				c.getFrameMethodHandler().sendQuest("", 14656);
			}
		}
		c.getFrameMethodHandler().sendQuest("", 14661);
		
		c.getFrameMethodHandler().sendQuest("", 14658);
		c.getFrameMethodHandler().sendQuest("", 14659);
		c.getFrameMethodHandler().sendQuest("", 14660);
		

	}
	
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;


public class GlobalChatRoomHandler {

	private LinkedList<ChatRoom> chatList = new LinkedList<ChatRoom>();

	public GlobalChatRoomHandler(){
		loadChatRooms();
	}

	public ChatRoom findChatRoom(String name){
		if(name.equals("")) return null;
		for(ChatRoom c : chatList){
			if(c.getChatRoomName().equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	
	public void process(){		
		for(ChatRoom c : chatList)
			c.deliverAllMessages();
	}
	
	public void addChatRoom(String name, int rights){
		chatList.add(new ChatRoom(name, rights));
	}

	private void loadChatRooms(){
		try (BufferedReader br = new BufferedReader(new FileReader("./data/chatrooms.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if(line.length() > 0){
					chatList.add(new ChatRoom(line, Integer.parseInt(line = br.readLine())));
				}
			}
		}
		catch(Exception e){
			misc.println("[ERROR] - In GlobalChatRoomHandler while loading chat rooms: "+e.toString());
		}
	}


}

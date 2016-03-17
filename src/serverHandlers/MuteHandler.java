package serverHandlers;
import java.io.*;

import playerData.client;

public class MuteHandler {

    public void appendToMuted(String name) {
	try {
		client is = (client) PlayerHandler.players[PlayerHandler.getPlayerID(name)];
		BufferedWriter bw = new BufferedWriter(new FileWriter("./muted/mutedlist.txt", true));
	    bw.write(name);
	    bw.newLine();
	    bw.flush();
	    bw.close();
	    if(PlayerHandler.isPlayerOn(name)){
			    is.muted = 1;
					}
} catch(IOException e) {
	    System.err.println("[FATAL ERROR] while attempting to add to mute.txt!");
	}
}

    public void deleteMuted(String name) {
	try {
		client is = (client) PlayerHandler.players[PlayerHandler.getPlayerID(name)];
		File file = new File("./muted/"+name+".txt");
	    file.delete();
	    BufferedReader br = new BufferedReader(new FileReader("./muted/mutedlist.txt"));
	    BufferedWriter bw = new BufferedWriter(new FileWriter("./muted/tempmutelist.txt", true));
	    String data = null;
	    for(int i = 0; i < "./muted/mutedlist.txt".length(); i++) {
		while((data = br.readLine()) != null) {
		    if(data.equalsIgnoreCase(name)) continue;
		    bw.write(data);
		    bw.newLine();
		    bw.flush();
	if(PlayerHandler.isPlayerOn(name)){
			is.muted = 0;
}
		}
	    }
	    bw.close();
	    br.close();
	    File list = new File("./muted/mutedlist.txt");
	    list.delete();
	    BufferedWriter bb = new BufferedWriter(new FileWriter("./muted/mutedlist.txt"));
	    BufferedReader bn = new BufferedReader(new FileReader("./muted/tempmutelist.txt"));
	    String data2 = null;
	    for(int i = 0; i < "./muted/tempmutelist.txt".length(); i++) {
		while((data2 = bn.readLine()) != null) {
		    bb.write(data2);
		    bb.newLine();
		    bb.flush();
		}
	    }
	    bb.close();
	    bn.close();
	    File tlist = new File("./muted/tempmutelist.txt");
	    tlist.delete();
	} catch(Exception e) {
	}
    }

    public void addMute(String name) {
	try {
	    BufferedWriter bw = new BufferedWriter(new FileWriter("./muted/"+name+".txt", true));
	    bw.write(name);
	    bw.newLine();
	    bw.flush();
	    bw.close();
	} catch(IOException e) {
	}
    }

    public boolean checkMutedMember(String name) {
	try {
	    BufferedReader br = new BufferedReader(new FileReader("./muted/"+name+".txt"));
	    String userName = null;
	    while((userName = br.readLine()) != null) {
		if(userName.equalsIgnoreCase(name)) {
		    return true;
		}
	    }
	    br.close();
	} catch(IOException e) {
	}
	return false;
    }

}
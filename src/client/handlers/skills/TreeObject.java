package client.handlers.skills;
import java.util.LinkedList;

import client.Player;
import client.client;
import server.handlers.globalObject.GlobalObject;
import server.root.server;

public class TreeObject extends GlobalObject{

	private int numbLogs, logType, deadTimer, stumpID, EXP;
	private LinkedList<Player> playersWoodcutting = new LinkedList<Player>();
	
	public int getLogType(){
		return this.logType;
	}
	
	public void removeLog(){
		this.numbLogs -= 1;
		if(this.numbLogs <= 0){
			stopPlayersWoodcutting();
			server.globalObjectHandler.createObjectForSeconds(this.deadTimer, this.X,this.Y, this.originalObjectID, this.direction, this.stumpID, null);
			this.destruct();
		}
	}
	
	public TreeObject(int x, int y, int object, int _direction, String playerName,
			int numbLogs, int logType, int deadTimer, int stumpID, int EXP) {
		super(x, y, object, _direction, playerName);
		this.logType = logType;
		this.numbLogs = numbLogs;
		this.deadTimer = deadTimer;
		this.stumpID = stumpID;
		this.isVisible = false;
		this.EXP = EXP;
		server.globalObjectHandler.treeList.add(this);
	}
	
	public int getEXP(){
		return this.EXP;
	}
	
	public void addPlayerToList(Player p){
		if(this.playersWoodcutting.contains(p)) return;
		else this.playersWoodcutting.add(p);
	}

	@Override
	public void process() {}
	
	private void stopPlayersWoodcutting(){
		for(Player p : this.playersWoodcutting){
			if(p != null && p.distanceToPoint(this.X, this.Y) < 20){
				client c = (client) p;
				if(c != null && 
						c.getWoodcuttingHandler().getWoodcuttingObjectCoordinates()[0] == this.X && 
						c.getWoodcuttingHandler().getWoodcuttingObjectCoordinates()[1] == this.Y){
					c.getWoodcuttingHandler().resetWoodcuttingProcess();
				}
			}
		}
	}

	@Override
	public void destruct() {
		server.globalObjectHandler.treeList.remove(this);
	}

}

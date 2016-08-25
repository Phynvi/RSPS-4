package client.handlers.skills;
import java.util.LinkedList;

import client.Player;
import client.client;
import server.handlers.globalObject.GlobalObject;
import server.root.server;

public class RockObject extends GlobalObject{

	private int numbOre, oreType, minedRockTime, minedRockID, EXP;
	private LinkedList<Player> playersMining = new LinkedList<Player>();
	
	public int getOreType(){
		return this.oreType;
	}
	
	public void removeOre(){
		if(this.numbOre == -1 && this.minedRockTime == -1) return;
		this.numbOre -= 1;
		if(this.numbOre <= 0){
			stopPlayersMining();
			server.globalObjectHandler.createObjectForSeconds(this.minedRockTime, this.X,this.Y, this.originalObjectID, this.direction, this.minedRockID, null);
			this.destruct();
		}
	}
	
	public RockObject(int x, int y, int object, int _direction, String playerName,
			int numbOre, int oreType, int deadTimer, int minedRockID, int EXP) {
		super(x, y, object, _direction, playerName);
		this.oreType = oreType;
		this.numbOre = numbOre;
		this.minedRockTime = deadTimer;
		this.minedRockID = minedRockID;
		this.isVisible = false;
		this.EXP = EXP;
		server.globalObjectHandler.rockList.add(this);
	}
	
	public int getEXP(){
		return this.EXP;
	}
	
	public void addPlayerToList(Player p){
		if(this.playersMining.contains(p)) return;
		else this.playersMining.add(p);
	}

	@Override
	public void process() {}
	
	private void stopPlayersMining(){
		for(Player p : this.playersMining){
			if(p != null && p.distanceToPoint(this.X, this.Y) < 20){
				client c = (client) p;
				if(c != null && 
						c.getMiningHandler().getMiningObjectCoordinates()[0] == this.X && 
								c.getMiningHandler().getMiningObjectCoordinates()[1] == this.Y){
					c.getMiningHandler().stopMiningProcess();
				}
			}
		}
	}

	@Override
	public void destruct() {
		server.globalObjectHandler.rockList.remove(this);
	}

}

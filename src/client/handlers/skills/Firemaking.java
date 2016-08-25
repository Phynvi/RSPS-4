package client.handlers.skills;
import java.util.Hashtable;

import client.client;
import server.handlers.globalObject.GlobalObject;
import server.resources.misc;
import server.root.server;

public class Firemaking {

	private client c;

	public Firemaking(client pc){
		this.c = pc;
	}

	private int logtype = -1;
	private int exp = -1;
	private int firelife = -1;
	private int objectID = -1;

	public int getLogType(){ return this.logtype; }
	public int getEXP(){ return this.exp; }
	public int getFireLife(){ return this.firelife; }
	public int getObbjectID(){ return this.objectID; }

	private boolean lightFire(int logType, int level, int exp, int object, int fireLife){
		if(c.playerLevel[c.playerFiremaking] < level){
			c.sendMessage("You need at least "+level+" Firemaking to do that.");
			return false;
		}

		this.logtype = logType;
		this.exp = exp;
		this.firelife = fireLife;
		this.objectID = object;

		double a = c.playerLevel[c.playerFiremaking] / level;
		int playerBonus = (int)Math.ceil(a);
		int d = level/10;
		int totalTime = (misc.random(d)+d)-playerBonus;
		if(totalTime < 3) totalTime = 2;
		c.getSkillHandler().startSkillTimerForSkill(totalTime, 1);
		c.repeatAnimation(733, 3);		
		return true;
	}

	public static final int NORMAL_FIRE = 2732;
	public static final int RED_FIRE = 11404;
	public static final int GREEN_FIRE = 11405;
	public static final int BLUE_FIRE = 11406;
	
	
	public void walk(){
		if(server.worldMap.getWalkableGridAtHeight(c.heightLevel)[c.absX-1][c.absY] != -1 && !doesFireExistHere(c.absX-1,c.absY)) //means not walkable
			c.walkTo(-1, 0);
		else if(server.worldMap.getWalkableGridAtHeight(c.heightLevel)[c.absX+1][c.absY] != -1 && !doesFireExistHere(c.absX+1,c.absY)) //means not walkable
			c.walkTo(1, 0);
		else if(server.worldMap.getWalkableGridAtHeight(c.heightLevel)[c.absX][c.absY-1] != -1 && !doesFireExistHere(c.absX,c.absY-1)) //means not walkable
			c.walkTo(0, -1);
		else if(server.worldMap.getWalkableGridAtHeight(c.heightLevel)[c.absX][c.absY+1] != -1 && !doesFireExistHere(c.absX,c.absY+1)) //means not walkable
			c.walkTo(0, 1);
	}

	public boolean doesFireExistHere(int x, int y){
		return (server.globalObjectHandler.find(x, y) instanceof Fire);
	}

	private static final int FIREMAKING_RATE = 2;

	public void createFire(){
		c.getSkillHandler().resetTimers();
		c.stopAnimations();
		if(!c.getInventoryHandler().hasItem(this.logtype)){
			c.sendMessage("You need logs to do that.");
			return;
		}
		if(doesFireExistHere(c.absX,c.absY)){
			c.sendMessage("A fire already exists here.");
			return;
		}
		c.getInventoryHandler().deleteItem(this.logtype);
		this.walk();
		new Fire(this.firelife, c.absX, c.absY, this.objectID, 1, null);
		int totExp = this.exp*c.rate*FIREMAKING_RATE;
		c.getClientMethodHandler().addSkillXP(totExp, c.playerFiremaking);
	}

	public boolean tinderboxUsedWith(int itemID){

		switch(itemID){
		case 1511: //logs, lvl 1, 40 exp
			return lightFire(itemID, 1, 40, NORMAL_FIRE,5);
		case 1521: //oak logs, 15, 60
			return lightFire(itemID, 15, 60, NORMAL_FIRE,10);
		case 1519: //willow, 30, 90
			return lightFire(itemID, 30, 90, NORMAL_FIRE,15);
		case 6333: //teak, 35, 105
			return lightFire(itemID, 35, 105, NORMAL_FIRE,20);
		case 1517: //maple, 45, 135
			return lightFire(itemID, 45, 135, NORMAL_FIRE,25);
		case 6332: //mahogany, 50, 157.5
			return lightFire(itemID, 50, 158, NORMAL_FIRE,30);
		case 1515: //yew, 60, 202.5
			return lightFire(itemID, 60, 203, NORMAL_FIRE,45);
		case 1513: //magic, 75, 303.8
			return lightFire(itemID, 75, 304, BLUE_FIRE,60);
		}


		return true;
	}

	private static final int ASHES = 592;

	private class Fire extends GlobalObject{

		private int fireTimer = -1;

		public Fire(int seconds, int x, int y, int object, int _direction, String playerName) {
			super(x, y, object, _direction, playerName);
			server.globalObjectHandler.deleteGlobalObject(x,y,playerName); //delete duplicates
			server.globalObjectHandler.bufferList.add(this);
			fireTimer = seconds*2;
			this.isVisible = true;
		}

		@Override
		public void process() {
			if(this.fireTimer > 0 && --this.fireTimer == 0){
				this.deleteObject = true;
			}			
		}

		@Override
		public void destruct() {
			server.itemHandler.createGroundItemInSeconds(ASHES, this.X, this.Y, c.heightLevel, 1, false, 0, null);
			for(int i = 0; i < server.playerHandler.players.length; i++){
				if(server.playerHandler.players[i] != null && server.playerHandler.players[i].distanceToPoint(this.X, this.Y) <= 90){
					client playerClient = (client) server.playerHandler.players[i];
					if(playerClient != null)
						playerClient.getFrameMethodHandler().createNewTileObject(this.X, this.Y, server.globalObjectHandler.EMPTYTILE, this.direction, 10);
				}
			}
			server.globalObjectHandler.bufferList.remove(this);			
		}

	}


}

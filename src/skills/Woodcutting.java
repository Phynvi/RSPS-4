package skills;

import java.util.Hashtable;

import playerData.client;
import root.misc;
import root.server;
import struct.lists;

public class Woodcutting {
	
	
	private class TreeInformation{
		private int stumpID,levelRequired,exp, logType, deadTime, numberOfLogs;
		public TreeInformation(int stumpID, int levelRequired, int exp, int logType, int deadTime, int numberOfLogs){
			this.stumpID = stumpID;
			this.levelRequired = levelRequired;
			this.exp = exp;
			this.logType = logType;
			this.deadTime = deadTime;
			this.numberOfLogs = numberOfLogs;
		}
	}

	private int woodcuttingTreeX,woodcuttingTreeY,woodcuttingTreeID;
	private client c; 
	private int[] validAxes = lists.axes.toArray();
	private TreeInformation[] treeInformation = new TreeInformation[10];
	private Hashtable<Integer, TreeInformation> treeTable = new Hashtable<Integer, TreeInformation>();
	private TreeObject tree;
	
	public int[] getWoodcuttingObjectCoordinates(){
		return new int[]{this.woodcuttingTreeX,this.woodcuttingTreeY};
	}
		
	public void resetWoodcuttingProcess(){
		c.stopAnimations();
		c.getSkillHandler().resetTimers();
		this.woodcuttingTreeX = 0;
		this.woodcuttingTreeY = 0;
		this.woodcuttingTreeID = -1;
		this.tree = null;
	}
	
	public Woodcutting(client pc){
		this.c = pc;
		
		//stump id, level required, experience, log type, dead timer, all tree ids
		this.treeInformation[1] = new TreeInformation(1356,15,38,1521,20,15); //oak
		this.treeInformation[2] = new TreeInformation(7399, 30, 68, 1519, 20,20); //willow
		this.treeInformation[3] = new TreeInformation(7400,45, 100, 1517, 35,25); //maple
		this.treeInformation[4] = new TreeInformation(7402, 60,175, 1515, 40,28); //Yew
		this.treeInformation[5] = new TreeInformation(7401, 75,250,1513,50,35); //magic
		this.treeInformation[0] = new TreeInformation(1349, 1, 25, 1511, 10,5); //normal
		this.treeInformation[6] = new TreeInformation(9035, 50, 125, 6332, 37,27); //mahogany
		this.treeInformation[7] = new TreeInformation(9037, 35, 85, 6333, 25,23); //teak
		
		treeTable.put(10083, treeInformation[1]); //oak
		treeTable.put(3037, treeInformation[1]);
		treeTable.put(1281, treeInformation[1]);
		
		treeTable.put(5552, treeInformation[2]); //willow
		treeTable.put(5553, treeInformation[2]); //willow
		treeTable.put(5554, treeInformation[2]); //willow
		treeTable.put(5551, treeInformation[2]); //willow
		treeTable.put(1308, treeInformation[2]); //willow
		
		treeTable.put(4674, treeInformation[3]); // Maple
		treeTable.put(1307, treeInformation[3]); // Maple
		
		treeTable.put(1309, treeInformation[4]); //yew
		
		treeTable.put(1306, treeInformation[5]); //magic
		
		treeTable.put(9034, treeInformation[6]); //mahogany
		
		treeTable.put(9036, treeInformation[7]); //teak
		
	}
	
	public void cutTree(int objectID, int x, int y, int direction){
		TreeInformation treeInfo = treeTable.get(objectID);
		if(treeInfo == null) treeInfo = treeInformation[0]; //is a normal tree
		
		if (c.playerLevel[8] < treeInfo.levelRequired){
			c.sendMessage("You need "+treeInfo.levelRequired+" Woodcutting to do this.");
			return;
		}
		if (playerAxe() == -1){
			c.sendMessage("You need to be wielding an axe to do that.");
			return;
		}
		if (c.getInventoryHandler().freeSlots() < 1){
			c.sendMessage("Your inventory is full.");
			return;
		}
		this.woodcuttingTreeID = objectID;
		c.getSkillHandler().startSkillTimerForSkill(this.getLogDelay(objectID), 6);
		c.repeatAnimation(this.getAxeEmote(), 5);
		this.woodcuttingTreeX = x;
		this.woodcuttingTreeY = y;
		this.tree = server.globalObjectHandler.findTree(x, y);
		if(this.tree == null)
			this.tree = new TreeObject(x, y, objectID, direction, null, (misc.random(5)+treeInfo.numberOfLogs), treeInfo.logType, treeInfo.deadTime, treeInfo.stumpID, treeInfo.exp);
		
		this.tree.addPlayerToList(c);
	}
		

	
	/** Checks for axe equipped or in inventory. 
	 * Returns first axe found, starting from Dragon, ends at Bronze	 
	 * @return What axe the player has equipped
	 */
	private int playerAxe(){
		for (int i = 0; i < validAxes.length; i++){
			if (c.getInventoryHandler().playerHasItem(validAxes[i]) || c.playerEquipment[c.playerWeapon] == validAxes[i])
				return validAxes[i];
		}
		return -1; //if no axe found
	}
	
	private int woodcuttingRate = 10;
	
	private int getEXP(){
		return this.tree.getEXP()*c.rate*this.woodcuttingRate;
	}
	
	/**
	 * Adds log to player inventory, decrements number of logs left
	 */
	public void deliverLog(){
		int freeSlots = c.getInventoryHandler().freeSlots();
		if(freeSlots < 1){
			c.sendMessage("Your inventory is full.");
			this.resetWoodcuttingProcess();
			return;
		}
		
		c.getInventoryHandler().addItem(this.tree.getLogType());
		c.getSkillHandler().startSkillTimerForSkill(this.getLogDelay(this.woodcuttingTreeID), 6);
		c.getClientMethodHandler().addSkillXP(this.getEXP(), c.playerWoodcutting);
		this.tree.removeLog();
		
		if(freeSlots+1 < 1){ //means inventory is full now
			c.sendMessage("Your inventory is full.");
			this.resetWoodcuttingProcess();
			return;
		}		
		
	}
		
	/** Calculates number of seconds between each log gained.
	 * Should return a new random number whenever called.
	 * @return Number of random delay seconds between logs.
	 */
	public int getLogDelay(int treeID){
		int totalDelay = 9; //starts at 9 no matter what
		switch (treeID){
		//normal trees
		case 10041:
		case 1315:
		case 1316:
		case 1282:
		case 1289:
		case 1277:
		case 1279:
		case 1280:
		case 1283:
		case 1284:
		case 1285:
		case 1286:
		case 1287:
		case 1288:
		case 1290:
		case 1291:
		case 1278:
		case 1276:
			totalDelay = 9+misc.random(2); //will make the delay different each time
			break;
		case 10083:
		case 3037:
		case 1281: //Oak Tree
			totalDelay = 11+misc.random(2);
			break;
		case 4674:
		case 1307: //Maple
			totalDelay = 13+misc.random(2);
			break;
		case 1309: //Yew Tree
			totalDelay = 11+misc.random(4);
			break;
		case 1306: //Magic Tree
			totalDelay = 13+misc.random(4);
			break;
		}
		totalDelay -= c.playerLevel[8]/33; //player bonus for their level, max is 3 seconds
		totalDelay -= getAxeBonus(); //reduction based on axe type, max is 8 seconds
		if (totalDelay < 2)
			totalDelay = 2; //cannot be less than 2 second delay
		return totalDelay*2; //woodcutting timer is decremented in a 500ms interval
	}
	
	/** Determines log delay time based on player axe
	 * 
	 * @return Seconds to be subtracted by logdelay
	 */
	private int getAxeBonus(){
		switch (this.playerAxe()){
		case 6739: return 8; //Dragon axe
		case 1359: return 7; //Rune
		case 1357: return 6; //Adamant
		case 1355: return 5; //Mithril
		case 1361: return 4;
		case 1353: return 4;
		case 1349: return 3;
		case 1351: return 1;
		}
		return 0;
	}
	
	public int getAxeEmote(){
		switch (this.playerAxe()){
		case 6739: return 2846; //dragon
		case 1359: return 867; //rune
		case 1357: return 869;
		case 1355: return 871;
		case 1361: return 873;
		case 1353: return 875;
		case 1349: return 877;
		case 1351: return 879;
		}
		return 0;
	}
	
}

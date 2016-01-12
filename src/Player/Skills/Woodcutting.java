import java.awt.event.*;

import javax.swing.Timer;

public class Woodcutting {
	private client playerClient;
	private int _currentTreeID, _requiredLevel, _stumpID, _X, _Y, _exp, _deadTreeTime, _logType, _axeValue, _axeBonus, _axeEmote; 
	public int _numLogs; //number of logs remaining in current tree
	private int[] validAxes = lists.axes.toArray();
	public TreeList list = new TreeList();
	
	public Woodcutting(client c){
		playerClient = c;
	}
	
	/**
	 * Checks player requirements and starts cutting tree if passes all tests
	 * @param treeID Tree object ID
	 * @param requiredLevel Required level to cut this tree
	 * @param x X coordinate of tree
	 * @param y Y coordinate of tree
	 */
	public void cutTree(int treeID, int requiredLevel, int x, int y){
		//this.stopAll();
		this._X = x;
		this._Y = y;
		this._currentTreeID = treeID;
		this._requiredLevel = requiredLevel;
		
		if (playerClient.playerLevel[8] < requiredLevel){
			playerClient.sendMessage("You need "+requiredLevel+" Woodcutting to do this.");
			return;
		}
		if (playerAxe() == -1){
			playerClient.sendMessage("You need to be wielding an axe to cut this tree.");
			return;
		}
		if (playerClient.freeSlots() < 1){
			playerClient.sendMessage("Your inventory is full.");
			return;
		}
		this.setStats();
		this.list.findOrAddTree(x, y, this.getNumbLogs());
		playerClient.wcTimer = this.getLogDelay(); //time in seconds between each log
		playerClient.repeatAnimation(this.getAxeEmote(), 5);
	}
	
	/**
	 * Sets stats of tree
	 * NOTE: if dead tree time is set to -1, will create a tile object, not a respawn object
	 */
	private void setStats(){
		switch (this._currentTreeID){
		case 10083:
		case 3037:
		case 1281: //Oak Tree
			this.setStump(1356);
			this.setDeadTreeTime(10+misc.random(5));
			this.setWCExp(150);
			this.setLogType(1521);
			return;
		case 5552:
		case 5553:
		case 5554:
		case 5551:
		case 1308: //Willow
			this.setStump(7399);
			this.setDeadTreeTime(15+misc.random(5));
			this.setWCExp(250);
			this.setLogType(1519);
			return;
		case 4674:
		case 1307: //Maple
			this.setStump(7400);
			this.setDeadTreeTime(20+misc.random(5));
			this.setWCExp(400);
			this.setLogType(1517);
			return;
		case 1309: //Yew Tree
			this.setStump(7402);
			this.setDeadTreeTime(25+misc.random(5));
			this.setWCExp(700);
			this.setLogType(1515);
			return;
		case 1306: //Magic Tree
			this.setStump(7401);
			this.setDeadTreeTime(30+misc.random(15));
			this.setWCExp(1200);
			this.setLogType(1513);
			return;
		}
		//else, default stuff
		this.setStump(1349);
		this.setDeadTreeTime(10+misc.random(5));
		this.setWCExp(100);
		this.setLogType(1511);
		return;
	}
	
	/**
	 * Finishes the cutting process
	 * Turns tree into stump and creates CutTree object
	 */
	public void finishedCutting(){
		int dTT = this.getDeadTreeTime();
		if(dTT == -1)
			playerClient.createNewTileObject(_X, _Y, this.getStump(), 2, 10); 		
		else new RespawnObject(playerClient, this._currentTreeID, this.getStump(), _X, _Y, dTT);
		
		this._Y = 0;
		this._X = 0;
		this.list.removeCurrent();
		this.stopAll();
	}
	
	/**Should reset everything
	 * 
	 */
	public void stopAll(){
		//this.setNumbLogs(0);
		playerClient.wcTimer = -1;
		playerClient.stopAnimations(); //stops animation
	}
	
	/** Checks for axe equipped or in inventory. 
	 * Returns first axe found, starting from Dragon, ends at Bronze	 
	 * @return What axe the player has equipped
	 */
	private int playerAxe(){
		for (int i = 0; i < validAxes.length; i++){
			if (playerClient.playerHasItem(validAxes[i]) || playerClient.playerEquipment[playerClient.playerWeapon] == validAxes[i])
				return validAxes[i];
		}
		return -1; //if no axe found
	}

	private void setNumbLogs(int value){
		this.list.getCurrent()._numLogs = value;
	}
	
	/** Calculates number of logs to be given by each tree
	 * 
	 * @return Number of logs in current live tree
	 */
	private int getNumbLogs(){
		switch (this._currentTreeID){
		case 10083:
		case 3037:
		case 1281: //Oak Tree
			return 3+misc.random(3);
		case 5552:
		case 5553:
		case 5554:
		case 5551:
		case 1308: //Willow
			return 4+misc.random(5);
		case 4674:
		case 1307: //Maple
			return 5+misc.random(7);
		case 1309: //Yew Tree
			return 6+misc.random(9);
		case 1306: //Magic Tree
			return 7+misc.random(12);
		}
		return 2+misc.random(2);
	}
	
	/**
	 * Adds log to player inventory, decrements number of logs left
	 */
	public void deliverLog(){
		if (playerClient.freeSlots() < 1){
			this.stopAll();
			return;
		}
		playerClient.addSkillXP(getWCExp()*playerClient.rate, 8);
		playerClient.addItem(getLogType(),1);
		this.list.getCurrent()._numLogs -= 1;
		if(playerClient.freeSlots() == 0)
			this.stopAll();
	}
	
	private void setStump(int value){
		this._stumpID = value;
	}
	
	private int getStump(){
		return this._stumpID;
	}	
	
	private void setDeadTreeTime(int value){
		this._deadTreeTime = value;
	}
	
	public int getDeadTreeTime(){
		return this._deadTreeTime;
	}
	
	private void setWCExp(int value){
		this._exp = value;
	}
	
	/**
	 * Determines EXP to be given by tree type
	 * @return EXP received
	 */
	public int getWCExp(){
		return this._exp;
	}
	
	private void setLogType(int value){
		this._logType = value;
	}
	
	/** Determines log type by current tree
	 * 
	 * @return item ID of a log type
	 */
	public int getLogType(){
		return this._logType;
	}
		
	/** Calculates number of seconds between each log gained.
	 * Should return a new random number whenever called.
	 * @return Number of random delay seconds between logs.
	 */
	public int getLogDelay(){
		int totalDelay = 9; //starts at 9 no matter what
		switch (this._currentTreeID){
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
		totalDelay -= playerClient.playerLevel[8]/33; //player bonus for their level, max is 3 seconds
		totalDelay -= getAxeBonus(); //reduction based on axe type, max is 8 seconds
		if (totalDelay < 1)
			totalDelay = 1; //cannot be less than 1 second delay
		return totalDelay;
	}
	
	private void setAxeBonus(int value){
		this._axeBonus = value;
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
		
	private void setAxeEmote(int value){
		this._axeEmote = value;
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
	
	/**
	 * Tree objects store X,Y, and numb of logs left
	 * Stored as linked list
	 * @author AP
	 *
	 */
	public class TreeList{

		private Tree _first;
		private Tree _last;
		private Tree _current;
		private int _manyTrees;
		
		/**
		 * A tree holds an X, Y, and numb of logs
		 * @author AP
		 *
		 */
		private class Tree{
			Tree _prev;
			Tree _next;
			int _x, _y, _numLogs;
			public Tree(int x, int y, int numLogs){
				_x = x;
				_y = y;
				_numLogs = numLogs;
			}

		}

		/**
		 * Creates a empty list with tree checker
		 */
		public TreeList(){
			Tree dummyFirst = new Tree(-10000,-10000,-1); //dummy tree
			Tree dummyLast = new Tree(10000,10000,-1); //dummy tree
			_first = dummyFirst; //dummy trees allow for easier adding
			_last = dummyLast;
			_manyTrees = 0;
			_current = dummyFirst;
			dummyFirst._next = dummyLast;
			dummyLast._prev = dummyFirst;
		}
		
		
		public int getCurrentLogs(){
			return _current._numLogs;
		}
		
		/**
		 * Adds tree to list and makes current
		 * @param x X coordinates of tree
		 * @param y Y coordinates of tree
		 */
		public void add(int x, int y, int numlogs){
			Tree temp = new Tree(x, y, numlogs);
			temp._prev = _first;
			temp._next = _first._next;
			_first._next._prev = temp;
			_first._next = temp;
			_current = temp;
			_manyTrees += 1;
		}
		
		/**
		 * Returns current tree, if there is none, then will return dummy
		 */
		public Tree getCurrent(){
				return _current;
		}
		
		/**
		 * Removes current element and sets current to first
		 */
		public void removeCurrent(){
			_current = _current._prev;
			Tree tempDelete = _current._next;
			_current._next = _current._next._next;
			tempDelete._next._prev = tempDelete._prev;
			tempDelete._next = null;
			tempDelete._prev = null;
			tempDelete = null;
			_current = _first;
			_manyTrees -= 1;
		}

		/**
		 * Searches for designated tree of x and y, if found sets as current
		 * If tree not found, creates tree with numlogs param, sets it as current
		 * and returns it as the object
		 * @param x X coord to be found or created
		 * @param y Y coord to be found or created
		 * @param numlogs Number of logs to set in tree if it needs to be created
		 * @return current tree element if one was found or added
		 */
		public void findOrAddTree(int x, int y, int numlogs){
			
			if(_manyTrees == 0){
				add(x, y, numlogs);
				return;
			}
			
			for (Tree temp = _first._next; temp != _last; temp = temp._next){
				if (temp._x == x && temp._y == y){//means tree exists
					_current = temp;
					return;
				}
			}
			add(x, y, numlogs);
		}
		
	}

}

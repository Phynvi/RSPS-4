
public class WCHandler extends client{
	private client playerClient;
	private int _currentTreeID, _requiredLevel, _stumpID, _X, _Y, _exp, _deadTreeTime, _logType, _axeValue, _axeBonus, _axeEmote; 
	public int _numLogs; //number of logs remaining in current tree
	private int[] validAxes = {6739, 1359, 1357, 1355, 1361, 1353, 1349, 1351};

	public WCHandler(client c){
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
		this.stopAll();
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
		this.wcTimer = this.getLogDelay(); //time in seconds between each log
		playerClient.repeatAnim(this.getAxeEmote(), 5);
	}
	
	/**
	 * Sets stats of tree
	 */
	private void setStats(){
		switch (this._currentTreeID){
		case 10083:
		case 3037:
		case 1281: //Oak Tree
			this.setStump(1356);
			this.setDeadTreeTime(15+misc.random(5));
			this.setWCExp(150);
			this.setLogType(1521);
			this.setNumbLogs(7+misc.random(5));
			return;
		case 5552:
		case 5553:
		case 5554:
		case 5551:
		case 1308: //Willow
			this.setStump(7399);
			this.setDeadTreeTime(20+misc.random(4));
			this.setWCExp(250);
			this.setLogType(1519);
			this.setNumbLogs(10+misc.random(5));
			return;
		case 4674:
		case 1307: //Maple
			this.setStump(7400);
			this.setDeadTreeTime(25+misc.random(5));
			this.setWCExp(400);
			this.setLogType(1517);
			this.setNumbLogs(10+misc.random(10));
			return;
		case 1309: //Yew Tree
			this.setStump(7402);
			this.setDeadTreeTime(30+misc.random(7));
			this.setWCExp(700);
			this.setLogType(1515);
			this.setNumbLogs(15+misc.random(10));
			return;
		case 1306: //Magic Tree
			this.setStump(7401);
			this.setDeadTreeTime(40+misc.random(15));
			this.setWCExp(1200);
			this.setLogType(1513);
			this.setNumbLogs(20+misc.random(10));
			return;
		}
		//else, default stuff
		this.setStump(1349);
		this.setDeadTreeTime(10+misc.random(5));
		this.setWCExp(100);
		this.setLogType(1511);
		this.setNumbLogs(3+misc.random(5));
		return;
	}
	
	/**
	 * Finishes the cutting process
	 * Turns tree into stump and creates CutTree object
	 */
	public void finishedCutting(){
		RespawnObject killed = new RespawnObject(playerClient, this._currentTreeID, this.getStump(), _X, _Y, this.getDeadTreeTime());
		this._Y = 0;
		this._X = 0;
		this.stopAll();
	}
	
	/**Should reset everything
	 * 
	 */
	public void stopAll(){
		this.setNumbLogs(0);
		playerClient.wcTimer = 0;
		playerClient.stopAnim(); //stops animation
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
		this._numLogs = value;
	}
	
	/** Calculates number of logs to be given by each tree
	 * 
	 * @return Number of logs in current live tree
	 */
	private int getNumbLogs(){
		return this._numLogs;
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
		this._numLogs -= 1;
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
			totalDelay += misc.random(2); //will make the delay different each time
			break;
		case 10083:
		case 3037:
		case 1281: //Oak Tree
			totalDelay += 2+misc.random(2);
			break;
		case 4674:
		case 1307: //Maple
			totalDelay += 3+misc.random(3);
			break;
		case 1309: //Yew Tree
			totalDelay += 4+misc.random(2);
			break;
		case 1306: //Magic Tree
			totalDelay += 6+misc.random(3);
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
	
	private int getAxeEmote(){
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

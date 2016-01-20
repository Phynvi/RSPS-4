

public class Mining {
	private client playerClient; 
	private int[] validPicks = {1275, 1271, 1273, 1269, 1267, 1265};
	private int rockDelay,baseRockDelay,rockID,EXP,oreID,bonus,currentPickAxe;
	public RockList list = new RockList();	
	private int MINEDROCK = 450;

	public Mining(client c){
		playerClient = c;
	}
		
	public int getDelay(){
		int reduce = (playerClient.playerLevel[14]+1)/10;
		int total = baseRockDelay+misc.random(rockDelay)-misc.random(reduce+bonus); 
		if (total < 1)
			total = 3;
		if (playerClient.debugmode)playerClient.sendMessage("Delay is "+total);
		return total;
	}
		
	public void setDelay(){
		switch(rockID){
		case 2107: case 2106: //rune ore
			baseRockDelay = 2;
			rockDelay = 22;
			return;
		case 2105: case 2104: //adamant ore
			baseRockDelay = 2;
			rockDelay = 18;
			return;
		case 2103: case 2102: //mithril ore
			baseRockDelay = 2;
			rockDelay = 14;
			return;
		case 2101: case 2100: //silver ore
			baseRockDelay = 2;
			rockDelay = 12;
			return;
		case 2099: case 2098://gold ore
		case 9720:
		case 9722:
		case 11184: //Gold Ore
			baseRockDelay = 3;
			rockDelay = 13;
			return;
		case 2097: case 2096://coal ore
			baseRockDelay = 2;
			rockDelay = 11;
			return;
		case 2093: case 2092://iron ore
		case 9717:
		case 9719:
		case 9718:
			baseRockDelay = 2;
			rockDelay = 9;
			return;
		case 2095: case 2094://tin ore
		case 9711:
		case 9713:
			baseRockDelay = 2;
			rockDelay = 7;
			return;
		case 2091: case 2090: //copper ore
		case 9709:
		case 9710:
		case 9708:
			baseRockDelay = 2;
			rockDelay = 7;
			return;
		case 2108: case 2109: //clay
			baseRockDelay = 2;
			rockDelay = 5;
			return;
		case 2111: //rune essence
			baseRockDelay = 2;
			rockDelay = 5;
			return;
		}
		baseRockDelay = 5;
		rockDelay = 5; //default
		return;
	}
	
	/**
	 * Checks and starts the mining process
	 * @param rockID - Object ID of rock
	 * @param requiredLevel - Required level to mine that rock
	 * @param x - X coordinate of rock
	 * @param y - Y coordinate of rock
	 */
	public void mineRock(int ID, int requiredLevel, int x, int y){
		if (playerClient.playerLevel[14] < requiredLevel){
			playerClient.sendMessage("You need "+requiredLevel+" mining to do that.");
			return;
		}
		currentPickAxe = hasPick();
		if(currentPickAxe == -1){
			playerClient.sendMessage("You must have a pick axe, which you can use, to mine this.");
			return;
		}
		if (playerClient.getInventoryHandler().freeSlots() < 1){
			playerClient.sendMessage("Your inventory is full.");
			return;
		}
		
		rockID = ID;
		setOreType(); //sets ore ID type
		setMiningEXP(); //sets mining EXP to be given
		setBonus(); //sets pickaxe bonus
		setDelay(); //sets two delay parameters used to calculate delay
		this.list.findOrAddRock(x, y, getNumbOre()); //sets current rock in list
		playerClient.miningTimer = this.getDelay(); //time in seconds between each ore
		playerClient.repeatAnimation(this.getPickAxeEmote(), 4);
	}
		
	public int getPickAxeEmote(){
		switch (currentPickAxe){
		case 1265: //bronze
			return 625;
		case 1267: //iron
			return 626;
		case 1269: //steel
			return 627;
		case 1273: //mithril
			return 629;
		case 1271: //adamant
			return 628;
		case 1275: //rune
			return 624;
		}
		return -1; //default null
	}
	
	public int getNumbOre(){
		switch(rockID){
		case 2107: case 2106: //rune ore
			return 7+misc.random(5);
		case 2105: case 2104: //adamant ore
			return 6+misc.random(7);
		case 2103: case 2102: //mithril ore
			return 6+misc.random(5);
		case 2101: case 2100: //silver ore
			return 4+misc.random(4);
		case 2099: case 2098://gold ore
		case 9720:
		case 9722:
		case 11184: //Gold Ore
			return 4+misc.random(5);
		case 2097: case 2096://coal ore
			return 5+misc.random(5);
		case 2093: case 2092://iron ore
		case 9717:
		case 9719:
		case 9718:
			return 4+misc.random(6);
		case 2095: case 2094://tin ore
		case 9711:
		case 9713:
			return 3+misc.random(4);
		case 2091: case 2090: //copper ore
		case 9709:
		case 9710:
		case 9708:
			return 3+misc.random(4);
		case 2108: case 2109: //clay
			return 3+misc.random(3);
		case 2111: //rune essence
			return 3+misc.random(3);
		}
		return -1; //default null
	}
	
	public void setBonus(){
		switch (currentPickAxe){
		case 1265: //bronze
			bonus = 1;
			return;
		case 1267: //iron
			bonus = 2;
			return;
		case 1269: //steel
			bonus = 4;
			return;
		case 1273: //mithril
			bonus = 6;
			return;
		case 1271: //adamant
			bonus = 8;
			return;
		case 1275: //rune
			bonus = 10;
			return;
		}
		bonus = -1; //default adds 
	}
	
	/**Should reset everything
	 * 
	 */
	public void stopAll(){
		playerClient.miningTimer = -1;
		playerClient.stopAnimations(); //stops animation
	}
	
	
	/**
	 * Finishes the cutting process
	 * Turns tree into stump and creates CutTree object
	 */
	public void finishedMining(){
		new RespawnObject(playerClient, rockID, MINEDROCK, this.list.getCurrent()._x, this.list.getCurrent()._y, getRespawnedOreTime());
		this.list.removeCurrent();
		this.stopAll();
	}
	
	public void deliverOre(){
		if (playerClient.getInventoryHandler().freeSlots() < 1){
			playerClient.sendMessage("Your inventory is full.");
			this.stopAll();
			return;
		}
		if(misc.random(19) == 0 && playerClient.getInventoryHandler().freeSlots() >= 2){ //5% chance of finding a gem
			playerClient.getInventoryHandler().addItem(playerClient.DROPHANDLER.getDrop(DropList.gems),1);
			playerClient.sendMessage("You find a gem.");
		}
		playerClient.getClientMethodHandler().addSkillXP(getMiningEXP()*playerClient.rate, 14);
		playerClient.getInventoryHandler().addItem(getOreType(),1);
		this.list.getCurrent()._numOre -= 1;
		if(playerClient.getInventoryHandler().freeSlots() == 0)
			this.stopAll();
	}
	
	private void setOreType(){
		switch(rockID){
		case 2107: case 2106: //rune ore
			oreID = 451;
			return;
		case 2105: case 2104: //adamant ore
			oreID = 449;
			return;
		case 2103: case 2102: //mithril ore
			oreID = 447;
			return;
		case 2101: case 2100: //silver ore
			oreID = 442;
			return;
		case 2099: case 2098://gold ore
		case 9720:
		case 9722:
		case 11184: //Gold Ore
			oreID = 444;
			return;
		case 2097: case 2096://coal ore
			oreID = 453;
			return;
		case 2095: case 2094://tin ore
		case 9711:
		case 9713:
			oreID = 438;
			return;
		case 2093: case 2092://iron ore
		case 9717:
		case 9719:
		case 9718:
			oreID = 440;
			return;
		case 2091: case 2090: //copper ore
		case 9709:
		case 9710:
		case 9708:
			oreID = 436;
			return;
		case 2108: case 2109: //clay
			oreID = 434;
			return;
		case 2111: //rune essence
			oreID = 1436;
			return;
		}
		oreID = -1; //there is no default
		return;
	}
	
	private void setMiningEXP(){
		switch(rockID){
		case 2107: case 2106: //rune ore
			EXP = 750; 
			return;
		case 2105: case 2104: //adamant ore
			EXP = 500; 
			return;
		case 2103: case 2102: //mithril ore
			EXP = 400; 
			return;
		case 2101: case 2100: //silver ore
			EXP = 200; 
			return;
		case 2099: case 2098://gold ore
		case 9720:
		case 9722:
		case 11184: //Gold Ore
			EXP = 300; 
			return;
		case 2097: case 2096://coal ore
			EXP = 200; 
			return;
		case 2093: case 2092://iron ore
		case 9717:
		case 9719:
		case 9718:
			EXP = 120; 
			return;
		case 2095: case 2094://tin ore
		case 9711:
		case 9713:
			EXP = 60; //default exp
			return;
		case 2091: case 2090: //copper ore
		case 9709:
		case 9710:
		case 9708:
			EXP = 60; //default exp
			return;
		case 2108: case 2109: //clay
			EXP = 50; 
			return;
		case 2111: //rune essence
			EXP = 65;
			return;
		}
	}
	
	private int getMiningEXP(){
		return EXP;
	}
	private int getOreType(){
		return oreID;
	}
	
	private int getRespawnedOreTime(){
		switch(rockID){
		case 2107: case 2106: //rune ore
			return 90+misc.random(30);
		case 2105: case 2104: //adamant ore
			return 75+misc.random(20);
		case 2103: case 2102: //mithril ore
			return 60+misc.random(15);
		case 2101: case 2100: //silver ore
			return 30+misc.random(15);
		case 2099: case 2098://gold ore
		case 9720:
		case 9722:
		case 11184: //Gold Ore
			return 40+misc.random(15);
		case 2097: case 2096://coal ore
			return 45+misc.random(20);
		case 2095: case 2094://tin ore
		case 9711:
		case 9713:
			return 30+misc.random(15);
		case 2093: case 2092://iron ore
		case 9717:
		case 9719:
		case 9718:
			return 40+misc.random(15);
		case 2091: case 2090: //copper ore
		case 9709:
		case 9710:
		case 9708:
			return 30+misc.random(15);
		case 2108: case 2109: //clay
			return 15+misc.random(10);
		case 2111: //rune essence
			return 15+misc.random(10);
		}
		return 30+misc.random(15); //default is 30-45 seconds
	}
	
	public boolean canWieldPick(int ID){
		int lvlRequired = -1;
		switch (ID){
		case 1265: //bronze
			lvlRequired = 1;
			break;
		case 1267: //iron
			lvlRequired = 1;
			break;
		case 1269: //steel
			lvlRequired = 6;
			break;
		case 1273: //mithril
			lvlRequired = 21;
			break;
		case 1271: //adamant
			lvlRequired = 31;
			break;
		case 1275: //rune
			lvlRequired = 41;
			break;
		default:
			return false;
		}
		if (playerClient.playerLevel[14] < lvlRequired)
			return false;
		return true;
	}
	
	/**
	 * Will scan player inventory and equipment for pickaxe
	 * @return - Will return the ID of the highest level pickaxe player currently has. Will return -1 if none found.
	 */
	public int hasPick(){
		for(int i = 0; i < validPicks.length; i++){
			int pickID = validPicks[i];
			if (playerClient.getInventoryHandler().playerHasItem(pickID) || playerClient.playerEquipment[playerClient.playerWeapon] == pickID){
				if(canWieldPick(pickID)){
					return pickID;
				}
			}
		}
		return -1;
	}
	
	
	
	/**
	 * Rock objects store X,Y, and numb of ore left
	 * Stored as linked list
	 * @author AP
	 *
	 */
	public class RockList{

		private Rock _first;
		private Rock _last;
		private Rock _current;
		private int _manyRocks;
		
		/**
		 * A Rock holds an X, Y, and numb of ore
		 * @author AP
		 *
		 */
		private class Rock{
			Rock _prev;
			Rock _next;
			int _x, _y, _numOre;
			public Rock(int x, int y, int num){
				_x = x;
				_y = y;
				_numOre = num;
			}

		}

		/**
		 * Creates a empty list
		 */
		public RockList(){
			Rock dummyFirst = new Rock(-10000,-10000,-1); //dummy rock
			Rock dummyLast = new Rock(10000,10000,-1); //dummy rock
			_first = dummyFirst; //dummy rocks allow for easier adding
			_last = dummyLast;
			_manyRocks = 0;
			_current = dummyFirst;
			dummyFirst._next = dummyLast;
			dummyLast._prev = dummyFirst;
		}
		
		
		public int getCurrentOre(){
			return _current._numOre;
		}
		
		/**
		 * Adds Rock to list and makes current
		 * @param x X coordinates of Rock
		 * @param y Y coordinates of Rock
		 */
		public void add(int x, int y, int num){
			Rock temp = new Rock(x, y, num);
			temp._prev = _first;
			temp._next = _first._next;
			_first._next._prev = temp;
			_first._next = temp;
			_current = temp;
			_manyRocks += 1;
		}
		
		/**
		 * Returns current Rock, if there is none, then will return dummy
		 */
		public Rock getCurrent(){
				return _current;
		}
		
		/**
		 * Removes current element and sets current to first
		 */
		public void removeCurrent(){
			_current = _current._prev;
			Rock tempDelete = _current._next;
			_current._next = _current._next._next;
			tempDelete._next._prev = tempDelete._prev;
			tempDelete._next = null;
			tempDelete._prev = null;
			tempDelete = null;
			_current = _first;
			_manyRocks -= 1;
		}

		/**
		 * Searches for designated Rock of x and y, if found sets as current
		 * If Rock not found, creates Rock with num param for ores, sets it as current
		 * and returns it as the object
		 * @param x X coord to be found or created
		 * @param y Y coord to be found or created
		 * @param numlogs Number of logs to set in tree if it needs to be created
		 * @return current tree element if one was found or added
		 */
		public void findOrAddRock(int x, int y, int num){
			
			if(_manyRocks == 0){
				add(x, y, num);
				return;
			}
			
			for (Rock temp = _first._next; temp != _last; temp = temp._next){
				if (temp._x == x && temp._y == y){//means rock exists
					_current = temp;
					return;
				}
			}
			add(x, y, num);
		}
		
	}
	
	
	
}

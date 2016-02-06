import java.util.Hashtable;

public class Mining {
	private client c; 
	private int[] validPicks = {1275, 1271, 1273, 1269, 1267, 1265};
	private static final int MINEDROCK = 450;
	private Hashtable<Integer, RockInformation> rockTable = new Hashtable<Integer, RockInformation>();
	private RockInformation[] rockList = new RockInformation[15];
	private int miningObjectID, miningObjectX, miningObjectY;
	private RockObject rock;
	
	private class RockInformation{
		private int minedRockID, levelRequired, exp, oreType, minedRockTime, numbOfOre;
		public RockInformation(int minedRockID, int levelRequired, int ex, int oreType, int minedRockTime, int numberOfOre){
			this.minedRockID = minedRockID;
			this.levelRequired = levelRequired;
			this.exp = ex;
			this.oreType = oreType;
			this.minedRockTime = minedRockTime;
			this.numbOfOre = numberOfOre;
		}
	}
	
	public int[] getMiningObjectCoordinates(){
		return new int[]{this.miningObjectX, this.miningObjectY};
	}

	public Mining(client c){
		this.c = c;
		this.rockList[0] = new RockInformation(MINEDROCK, 1, 18, TIN_ORE, 3, 5); 
		this.rockList[1] = new RockInformation(MINEDROCK,1,18,COPPER_ORE,3,5); 
		this.rockList[2] = new RockInformation(MINEDROCK,15,35,IRON_ORE,5,7); 
		this.rockList[3] = new RockInformation(MINEDROCK,20,40,SILVER_ORE,7,7); 
		this.rockList[4] = new RockInformation(MINEDROCK,30,50,COAL,10,8); 
		this.rockList[5] = new RockInformation(MINEDROCK,40,65,GOLD_ORE,20,10); 
		this.rockList[6] = new RockInformation(MINEDROCK,55,80,MITHRIL_ORE,60,20);
		this.rockList[7] = new RockInformation(MINEDROCK,70,95,ADAMANT_ORE,80,25);
		this.rockList[8] = new RockInformation(MINEDROCK,85,125,RUNE_ORE,120,30); 
		this.rockList[9] = new RockInformation(MINEDROCK,1,5,CLAY,3,5); 
		this.rockList[10] = new RockInformation(MINEDROCK,1,5,RUNE_ESSENCE,-1,-1); //-1 and -1 signify infinite
		
		rockTable.put(2491, this.rockList[10]); //rune essence
		this.putMultiple(COPPER_ROCKS, this.rockList[1]); 
		this.putMultiple(IRON_ROCKS, this.rockList[2]);
		this.putMultiple(SILVER_ROCKS, this.rockList[3]);
		this.putMultiple(TIN_ROCKS, this.rockList[0]);
		this.putMultiple(COAL_ROCKS, this.rockList[4]);
		this.putMultiple(GOLD_ROCKS, this.rockList[5]);
		this.putMultiple(MITHRIL_ROCKS, this.rockList[6]);
		this.putMultiple(ADAMANT_ROCKS, this.rockList[7]);
		this.putMultiple(RUNE_ROCKS, this.rockList[8]);
		this.putMultiple(CLAY_ROCKS, this.rockList[9]);
		
	}
	
	private void putMultiple(int[] IDs, RockInformation r){
		for(int i = 0; i < IDs.length; i++){
			rockTable.put(IDs[i], r);
		}
	}

	private static final int[] COPPER_ROCKS = {2091,2090,9709,9710,9708};
	private static final int[] CLAY_ROCKS = {2108,2109};
	private static final int[] TIN_ROCKS = {9714,9716,2095,2094,9711,9713};
	private static final int[] IRON_ROCKS = {9717,9719,9718,2093,2092};
	private static final int[] COAL_ROCKS = {2097,2096};
	private static final int[] SILVER_ROCKS = {2101,2100};
	private static final int[] GOLD_ROCKS = {2099, 2098, 9720,9722,11184};
	private static final int[] MITHRIL_ROCKS = {2103,2102};
	private static final int[] ADAMANT_ROCKS = {2105,2104};
	private static final int[] RUNE_ROCKS = {14859,14860,2107,2106};
	
	private int getRockDelay(int rockID){
		int totalDelay = 2; //default
		switch(rockID){
		case 2111: //rune essence
			totalDelay = 6+misc.random(4);
			break;
		case 2108: case 2109: //clay
			
		case 2091: case 2090: //copper ore
		case 9709:
		case 9710:
		case 9708:
			
		case 2095: case 2094://tin ore
		case 9711:
		case 9713:
			totalDelay = 8+misc.random(4);
			break;
		case 9717:
		case 9719:
		case 9718:
		case 2093: case 2092://iron ore
			totalDelay = 9+misc.random(4);
			break;
		case 2097: case 2096://coal ore
			totalDelay = 10+misc.random(4);
			break;

		case 2101: case 2100: //silver ore
			totalDelay = 11+misc.random(4);
			break;			
		case 2099: case 2098://gold ore
		case 9720:
		case 9722:
		case 11184: //Gold Ore
			totalDelay = 12+misc.random(4);
			break;
		case 2103: case 2102: //mithril ore
			totalDelay = 12+misc.random(4);
			break;
		case 2105: case 2104: //adamant ore
			totalDelay = 13+misc.random(4);
			break;
		case 2107: case 2106: //rune ore
			totalDelay = 15+misc.random(4);
			break;
		}
		totalDelay -= (c.playerLevel[c.playerWoodcutting]+1)/20; //player bonus for their level, max is 5 seconds
		totalDelay -= getPickaxeBonus(playerPickaxe()); //reduction based on pickaxe type, max is 8 seconds
		if (totalDelay < 2)
			totalDelay = 2; //cannot be less than 2 second delay
		return totalDelay;
	}

	/**
	 * Gets the reduction in seconds from the pickaxe. Max is 8 seconds.
	 */
	private int getPickaxeBonus(int pickaxeID){
		switch (pickaxeID){
		case 1265: //bronze
			return 1;
		case 1267: //iron
			return 2;
		case 1269: //steel
			return 4;
		case 1273: //mithril
			return 6;
		case 1271: //adamant
			return 7;
		case 1275: //rune
			return 8;
		default:
			return -1;
		}
	}
	


	public void mineRock(int objectID, int x, int y){
		RockInformation rockInfo = rockTable.get(objectID);
		if (c.playerLevel[14] < rockInfo.levelRequired){
			c.sendMessage("You need "+rockInfo.levelRequired+" mining to do that.");
			return;
		}
		int playerPickaxe = playerPickaxe();
		if(playerPickaxe == -1){
			c.sendMessage("You must have a pickaxe to do that.");
			return;
		}
		int pickaxeLevel = getPickaxeLevel(playerPickaxe);
		if(c.playerLevel[c.playerMining] < pickaxeLevel){
			c.sendMessage("You need at least "+pickaxeLevel+" Mining to use that pickaxe.");
			return;
		}
		if (c.getInventoryHandler().freeSlots() < 1){
			c.sendMessage("Your inventory is full.");
			return;
		}
		this.miningObjectID = objectID;
		c.miningTimer = getRockDelay(objectID);		
		c.repeatAnimation(this.getPickAxeEmote(playerPickaxe), 4);
		this.miningObjectX = x;
		this.miningObjectY = y;
		this.rock = server.globalObjectHandler.findRock(x, y);
		if(this.rock == null)
			this.rock = new RockObject(x, y, objectID, 0, null,
					rockInfo.numbOfOre, rockInfo.oreType, rockInfo.minedRockTime, rockInfo.minedRockID, rockInfo.exp);
		this.rock.addPlayerToList(c);
		
	}

	public int getPickAxeEmote(int pickID){
		switch (pickID){
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
	
	private int miningRate = 10;
	
	private int getEXP(){
		return this.rock.getEXP()*c.rate*this.miningRate;
	}

	public void stopMiningProcess(){
		c.stopAnimations();
		c.miningTimer = -1;
		this.rock = null;
		this.miningObjectID = -1;
		this.miningObjectX = -1;
		this.miningObjectY = -1;
	}

	public void deliverOre(){
		int freeSlots = c.getInventoryHandler().freeSlots();
		if(freeSlots < 1){
			c.sendMessage("Your inventory is full.");
			this.stopMiningProcess();
			return;
		}
		
		c.getInventoryHandler().addItem(this.rock.getOreType());
		c.miningTimer = this.getRockDelay(this.miningObjectID);
		c.getClientMethodHandler().addSkillXP(this.getEXP(), c.playerMining);
		this.rock.removeOre();

		if(freeSlots+1 < 1){ //means inventory is full now
			c.sendMessage("Your inventory is full.");
			this.stopMiningProcess();
			return;
		}		
	}

	private final static int RUNE_ORE = 451;
	private final static int ADAMANT_ORE = 449;
	private final static int MITHRIL_ORE = 447;
	private final static int SILVER_ORE = 442;
	private final static int GOLD_ORE = 444;
	private final static int TIN_ORE = 438;
	private final static int IRON_ORE = 440;
	private final static int COPPER_ORE = 436;
	private final static int CLAY = 434;
	private final static int RUNE_ESSENCE = 1436;
	private static final int COAL = 453;

	public int getPickaxeLevel(int ID){
		switch (ID){
		case 1265: //bronze
			return 1;
		case 1267: //iron
			return 1;
		case 1269: //steel
			return 6;
		case 1273: //mithril
			return 21;
		case 1271: //adamant
			return 31;
		case 1275: //rune
			return 41;
			default: return -1;
		}
	}

	/** Checks for pickaxe equipped or in inventory. 
	 * Returns first pickaxe found, starting from Dragon, ends at Bronze	 
	 * @return What pickaxe the player has equipped. Returns 1 if no pickaxe found.
	 */
	private int playerPickaxe(){
		for (int i = 0; i < validPicks.length; i++){
			if (c.getInventoryHandler().playerHasItem(validPicks[i]) || c.playerEquipment[c.playerWeapon] == validPicks[i])
				return validPicks[i];
		}
		return -1; //if no pickaxe found
	}



}

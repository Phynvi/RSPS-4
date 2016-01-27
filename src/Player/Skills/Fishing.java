public class Fishing{

	/*
 npcs : 1174, 1175,952 - net
 1176,1177,1178 - fish (seaturtle)
 1236,1237,1238,233,234,235,236  - bait
 1399,321,324,333,312,1332 - cage/harpoon
 1405,1406,313,322,334,1191,1333 - net/harpoon
 316,319,320,323,325,326,327,330,332,1331,2067,2068 - net/bait
 3019,314,315,317,318,328,329,331,927,1189,1190,309,310,311  - lure/bait
 800 - bait lavafish
	 */

	private client c;

	public Fishing(client pc){
		this.c = pc;
	}

	private int[] deliverFish = null;
	private int deliverFishEXP = -1;
	private int removeID = -1;
	public int fishingTimer = -1;
	private int requiredLevel = -1;
	private int npcID = -1;
	private int requiredTool = -1;
	private int fishingAnimation = -1;

	private boolean setVariablesAndCheckRequirements(int _levelRequired, int[] _deliverFish, int _removeID, int _requiredTool){
		if(c.playerLevel[c.playerFishing] < _levelRequired){
			c.sendMessage("You need "+_levelRequired+" to do that.");
			resetFishing();
			return false;
		}
		if(!c.getInventoryHandler().hasItem(_requiredTool)){
			c.sendMessage("You need a "+Item.getItemName(_requiredTool)+" to do that.");
			resetFishing();
			return false;
		}
		if(_removeID != -1 && !c.getInventoryHandler().hasItem(_removeID)){
			c.sendMessage("You need a "+Item.getItemName(_removeID)+" to do that.");
			resetFishing();
			return false;
		}
		requiredLevel = _levelRequired;
		deliverFish = _deliverFish;
		removeID = _removeID;
		fishingAnimation = getFishingAnimation(_requiredTool);
		requiredTool = _requiredTool;
		setFishingTimer();
		c.repeatAnimation(fishingAnimation, 3);
		return true;
	}

	private int getFishingAnimation(int toolID){
		switch(toolID){
		case BIG_FISHING_NET:
			return 620;
		case FISHING_ROD:
			return 622;
		case LOBSTER_POT:
			return 619;
		case HARPOON:
			return 618;
		case SMALL_FISHING_NET:
			return 621;
		}
		return 0;
	}

	public void resetFishing(){
		deliverFish = null;
		removeID = -1;
		fishingTimer = -1;
		deliverFishEXP = -1;
		requiredLevel = -1;
		npcID = -1;
		requiredTool = -1;
		fishingAnimation = -1;
		c.stopAnimations();
	}

	private void setFishingTimer(){
		double bonus = requiredLevel/c.playerLevel[c.playerFishing];
		fishingTimer = 4+misc.random( (int)(4*bonus) );
	}

	public void deliverFishAndResetTimers(){
		if(!c.getInventoryHandler().hasItem(requiredTool)){
			c.sendMessage("You need a "+Item.getItemName(requiredTool)+" to do that.");
			resetFishing();
			return;
		}
		if(!c.getInventoryHandler().addItem(c.DROPHANDLER.getDrop(deliverFish))){
			c.sendMessage("Your inventory is full.");
			resetFishing();
			return;
		}		
		if(removeID != -1 && removeID != POISON){
			if(!c.getInventoryHandler().hasItem(removeID)){
				c.sendMessage("You need a "+Item.getItemName(removeID)+" to do that.");
				resetFishing();
				return;
			}			
			c.getInventoryHandler().deleteItem(removeID);
		}
		setFishingTimer();
		c.getClientMethodHandler().addSkillXP(c.rate*requiredLevel*10, c.playerFishing);
	}

	private final int SMALL_FISHING_NET = 303;
	private final int LOBSTER_POT = 301;
	private final int BIG_FISHING_NET = 305;
	private final int FISHING_ROD = 307;
	private final int FLY_FISHING_ROD = 309;
	private final int HARPOON = 311;
	private final int FISHING_BAIT = 313;
	private final int FEATHER = 314;

	public void fishingLoopProcess(){
		if(fishingTimer > 0)
			fishingTimer -= 1;
		if(fishingTimer == 0)
			deliverFishAndResetTimers();
	}

	private static int RAW_SHRIMPS = 317;
	private static int RAW_ANCHOVIES = 321;
	private static int RAW_SARDINE = 327;
	private static int RAW_SALMON = 331;
	private static int RAW_TROUT = 335;
	private static int BURNT_FISH = 323;
	private static int RAW_GIANT_COD = 338;
	private static int RAW_COD = 341;
	private static int RAW_HERRING = 345;
	private static int RAW_PIKE = 349;
	private static int RAW_MACKEREL = 353;
	private static int RAW_TUNA = 359;
	private static int RAW_BASS = 363;
	private static int RAW_SWORDFISH = 371;
	private static int RAW_LOBSTER = 377;
	private static int RAW_SHARK = 383;
	private static int RAW_MANTA_RAY = 389;
	private static int RAW_SEA_TURTLE = 395;
	private static int CASKET = 3527;
	private static int LEATHER_GLOVES = 1059;
	private static int LEATHER_BOOTS = 1061;
	private static int OYSTER = 407;
	private static int SEAWEED = 401;
	private static int POISON = 273;


	public void fishingClick1(int npcID){
		switch(npcID){

		case 3019: case 314: case 315: case 317: 
		case 318: case 328: case 329: case 331: 
		case 927: case 1189: case 1190: case 309: 
		case 310: case 311: // lure/bait
			int[] lure = new int[]{RAW_TROUT};
			int level = 20;
			if(c.playerLevel[c.playerFishing] >= 30){
				level = 30;
				lure = new int[]{RAW_TROUT,RAW_SALMON,RAW_SALMON};
			}
			setVariablesAndCheckRequirements(level, lure, FEATHER, FLY_FISHING_ROD);
			break;

		case 1174: case 1175: case 952: //Net
		case 332: case 2067: case 2068: case 316: case 319:
		case 320: case 323: case 325: case 326: case 327: case 330: case 1331: // net/bait
			int[] smallNet = new int[]{RAW_SHRIMPS};
			level = 1;
			if(c.playerLevel[c.playerFishing] >= 15){
				level = 15;
				smallNet = new int[]{RAW_SHRIMPS,RAW_ANCHOVIES};
			}
			setVariablesAndCheckRequirements(level, smallNet, -1, SMALL_FISHING_NET);
			break;

		case 1405: case 1406: case 313: case 322:
		case 334: case 1191: // Net/Harpoon
			int[] largeNet = new int[]{RAW_MACKEREL,RAW_MACKEREL,CASKET,LEATHER_BOOTS,LEATHER_GLOVES,OYSTER,SEAWEED};
			level = 16;
			if(c.playerLevel[c.playerFishing] >= 46){
				level = 46;
				largeNet = new int[]{RAW_BASS,RAW_BASS,RAW_BASS,RAW_COD,RAW_COD,RAW_MACKEREL,CASKET,LEATHER_BOOTS,LEATHER_GLOVES,OYSTER,SEAWEED};
			}
			else if(c.playerLevel[c.playerFishing] >= 23){
				level = 23;
				largeNet = new int[]{RAW_COD,RAW_COD,RAW_MACKEREL,CASKET,LEATHER_BOOTS,LEATHER_GLOVES,OYSTER,SEAWEED};
			}
			setVariablesAndCheckRequirements(level, largeNet, -1, BIG_FISHING_NET);
			break;	

		case 1236: case 1237: case 1238: case 233:
		case 234: case 235: case 236://Bait
			int[] bait = new int[]{RAW_SARDINE};
			level = 5;
			if(c.playerLevel[c.playerFishing] >= 25){
				level = 25;
				bait = new int[]{RAW_PIKE,RAW_PIKE,RAW_HERRING,RAW_SARDINE};
			}
			else if(c.playerLevel[c.playerFishing] >= 10){
				level = 10;
				bait = new int[]{RAW_HERRING,RAW_SARDINE,RAW_HERRING};
			}
			setVariablesAndCheckRequirements(level, bait, FISHING_BAIT, FISHING_ROD);
			break;

		case 1399: case 321: case 324: case 333: case 312: case 1332: //Cage
			setVariablesAndCheckRequirements(40, new int[]{RAW_LOBSTER}, -1, LOBSTER_POT);
			break;	

		case 1176: case 1177: case 1178: //Fish here
			if(c.ST >= 4 || c.STC >= 1){
				setVariablesAndCheckRequirements(79, new int[]{RAW_SEA_TURTLE}, POISON, HARPOON);
				}
			else{
				c.sendMessage("You need to beat The Famous Catch to fish Sea Turtle!");
			}
			break;	

		}
	}

	public void fishingClick2(int npcID){

		switch(npcID){

		case 2067: case 2068: case 316: case 319: case 320:
		case 323: case 325: case 326: case 327: case 330:
		case 332: case 1331: //Bait		
			int[] bait = new int[]{RAW_SARDINE};
			int level = 5;
			if(c.playerLevel[c.playerFishing] >= 25){
				level = 25;
				bait = new int[]{RAW_PIKE,RAW_PIKE,RAW_HERRING,RAW_SARDINE};
			}
			else if(c.playerLevel[c.playerFishing] >= 10){
				level = 10;
				bait = new int[]{RAW_HERRING,RAW_SARDINE,RAW_HERRING};
			}
			setVariablesAndCheckRequirements(level, bait, FISHING_BAIT, FISHING_ROD);
			break;

		case 1399: case 321: case 324: 
		case 333: case 312: case 1332: // cage/harpoon
			int[] harpoon = new int[]{RAW_TUNA};
			level = 35;
			if(c.playerLevel[c.playerFishing] >= 50){
				level = 50;
				harpoon = new int[]{RAW_SWORDFISH,RAW_SWORDFISH,RAW_TUNA};
				}
			setVariablesAndCheckRequirements(level, harpoon, -1, HARPOON);
			break;
			
		case 1405: case 1406: case 313: case 322:
		case 334: case 1191: case 1333: // net/harpoon
			harpoon = new int[]{RAW_TUNA};
			level = 35;
			if(c.playerLevel[c.playerFishing] >= 76){
				level = 76;
				harpoon = new int[]{RAW_SHARK,RAW_SHARK,RAW_TUNA};
				}
			setVariablesAndCheckRequirements(level, harpoon, -1, HARPOON);
			break;
		}

	}


}
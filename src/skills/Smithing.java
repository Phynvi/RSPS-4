package skills;
import playerData.client;
import clientHandlers.Item;


public class Smithing {
	

	private int smelting[] = {0,0,0,-1,-1,-1,0};
	private int smithing[] = {0,0,0,1,-1,0};

	private client c = null;

	public Smithing(client pc){
		this.c = pc;
	}


	public boolean smithing() {
		if (c.getInventoryHandler().IsItemInBag(2347) == true) {
			int bars = 0;
			int Length = 22;
			int barid = 0;
			int Level = 0;
			int ItemN = 1;
			if (smithing[2] >= 4) {
				barid = (2349 + ((smithing[2] + 1) * 2));
			} else {
				barid = (2349 + ((smithing[2] - 1) * 2));
			}
			if (smithing[2] == 1 || smithing[2] == 2) {
				Length += 1;
			} else if (smithing[2] == 3) {
				Length += 2;
			}
			for (int i = 0; i < Length; i++) {
				if (Item.smithing_frame[(smithing[2] - 1)][i][0] == smithing[4]) {
					bars = Item.smithing_frame[(smithing[2] - 1)][i][3];
					if (smithing[1] == 0) {
						smithing[1] = Item.smithing_frame[(smithing[2] - 1)][i][2];
					}
					ItemN = Item.smithing_frame[(smithing[2] - 1)][i][1];
				}
			}
			if (c.playerLevel[c.playerSmithing] >= smithing[1] && c.playerEquipment[c.playerWeapon] >= 0) {
				if (c.getInventoryHandler().AreXItemsInBag(barid, bars) == true) {
					if (c.getInventoryHandler().freeSlots() > 0) {
						if (c.actionTimer == 0 && smithing[0] == 1) {
							c.OriginalWeapon = c.playerEquipment[c.playerWeapon];
							c.playerEquipment[c.playerWeapon] = 2347; //Hammer
							c.OriginalShield = c.playerEquipment[c.playerShield];
							c.playerEquipment[c.playerShield] = -1;
							c.sendMessage("You start hammering the bar...");
							c.actionTimer = 7;
							c.startAnimation(0x382);
							smithing[0] = 2;
						}
						if (c.actionTimer == 0 && smithing[0] == 2) {
							for (int i = 0; i < bars; i++) {
								c.getInventoryHandler().deleteItem(barid, c.getInventoryHandler().GetItemSlot(barid), c.playerItemsN[c.getInventoryHandler().GetItemSlot(barid)]);
							}
							c.getClientMethodHandler().addSkillXP(((int)(12.5 * bars * smithing[2] * smithing[3])), c.playerSmithing);
							c.getInventoryHandler().addItem(smithing[4], ItemN);
							c.sendMessage("You smith a " + Item.getItemName(smithing[4]) + ".");
							c.resetAnimation();
							if (smithing[5] <= 1) {
								resetSM();
							} else {
								c.actionTimer = 5;
								smithing[5] -= 1;
								smithing[0] = 1;
							}
						}
					} else {
						c.sendMessage("Inventory is full.");
						resetSM();
						return false;
					}
				} else {
					c.sendMessage("You need " + bars + " " + Item.getItemName(barid) + " to smith a " + Item.getItemName(smithing[4]));
					c.resetAnimation();
					resetSM();
				}
			} else {
				c.sendMessage("You need "+smithing[1]+" "+c.statName[c.playerSmithing]+" to smith a "+Item.getItemName(smithing[4]));
				resetSM();
				return false;
			}
		} else {
			c.sendMessage("You need a "+Item.getItemName(2347)+" to hammer bars.");
			resetSM();
			return false;
		}
		return true;
	}
	public boolean resetSM() {
		if (c.OriginalWeapon > -1) {
			c.playerEquipment[c.playerWeapon] = c.OriginalWeapon;
			c.OriginalWeapon = -1;
			c.playerEquipment[c.playerShield] = c.OriginalShield;
			c.OriginalShield = -1;
		}
		smithing[0] = 0;
		smithing[1] = 0;
		smithing[2] = 0;
		smithing[4] = -1;
		smithing[5] = 0;
		return true;
	}
	
	private static final int BRONZE_BAR = 2349;
	private static final int IRON_BAR = 2351;
	private static final int STEEL_BAR = 2353;
	private static final int SILVER_BAR = 2355;
	private static final int GOLD_BAR = 2357;
	private static final int MITHRIL_BAR = 2359;
	private static final int ADAMANT_BAR = 2361;
	private static final int RUNE_BAR = 2363;
	private static final int COPPER_ORE = 436;
	private static final int TIN_ORE = 438;
	private static final int IRON_ORE = 440;
	private static final int SILVER_ORE = 442;
	private static final int GOLD_ORE = 444;
	private static final int MITHRIL_ORE = 447;
	private static final int ADAMANTITE_ORE = 449;
	private static final int RUNITE_ORE = 451;
	private static final int COAL = 453;
		
	private int[] currentOreRequirements = null;
	private String currentBarType = null;
	
	public void smithingBarMenuPage1(){
		c.getFrameMethodHandler().select4Options(1,"Page 1", "Smelt All Bronze Bars", "Smelt All Iron Bars", "Smelt All Silver Bars", "More Options");
	}
	public void smithingBarMenuPage2(){
		c.getFrameMethodHandler().select4Options(2,"Page 2", "Smelt All Steel Bars", "Smelt All Gold Bars", "Smelt All Mithril Bars", "More Options");
	}
	public void smithingBarMenuPage3(){
		c.getFrameMethodHandler().select4Options(3,"Page 3", "Smelt All Adamantite Bars", "Smelt All Rune bars", "", "Exit");
	}
	
	public void smeltBar(String barType){
		int levelRequired = -1;
		switch(barType.toLowerCase()){
		case "bronze":
			levelRequired = 1;
			currentOreRequirements = new int[]{1,TIN_ORE,1,COPPER_ORE};
			break;
		case "iron":
			levelRequired = 15;
			currentOreRequirements = new int[]{2,IRON_ORE};
			break;
		case "silver":
			levelRequired = 20;
			currentOreRequirements = new int[]{1,SILVER_ORE};
			break;
		case "steel":
			levelRequired = 30;
			currentOreRequirements = new int[]{1,IRON_ORE,2,COAL};
			break;
		case "gold":
			levelRequired = 40;
			currentOreRequirements = new int[]{1,GOLD_ORE};
			break;
		case "mithril":
			levelRequired = 50;
			currentOreRequirements = new int[]{1,MITHRIL_ORE,4,COAL};
			break;
		case "adamantite":
			levelRequired = 70;
			currentOreRequirements = new int[]{1,ADAMANTITE_ORE,6,COAL};
			break;
		case "rune":
			levelRequired = 85;
			currentOreRequirements = new int[]{1,RUNITE_ORE,8,COAL};
			break;
		}
		if(c.playerLevel[c.playerSmithing] < levelRequired){
			c.sendMessage("You need at least "+levelRequired+" Smithing to do that.");
			return;
		}
		
		boolean doesNotHaveTheOre = false;
		for(int i = 0; i < currentOreRequirements.length-1; i += 2){
			if(!c.getInventoryHandler().hasItemOfAtLeastAmount(currentOreRequirements[i+1], currentOreRequirements[i])){
				c.sendMessage("You need at least "+currentOreRequirements[i]+" "+Item.getItemName(currentOreRequirements[i+1])+" to do that.");
				doesNotHaveTheOre = true;
			}
		}
		if(doesNotHaveTheOre)
			return;
		
		currentBarType = barType;
		c.getSkillHandler().startSkillTimerForSkill(SMITHING_ANIMATION_DELAY, 5);
		c.repeatAnimation(899, 3);
		
	}
	
	private static int SMITHINGRATE = 75;
	
	public boolean removeOreAndSmeltBar(){
		for(int i = 0; i < currentOreRequirements.length-1; i += 2){
			if(!c.getInventoryHandler().hasItemOfAtLeastAmount(currentOreRequirements[i+1], currentOreRequirements[i])){
				c.getSkillHandler().resetTimers();
				c.stopAnimations();
				return false;
			}
		}
		
		for(int i = 0; i < currentOreRequirements.length-1; i += 2){
			for(int j = 0; j < currentOreRequirements[i]; j++){ //deleting the amount
				c.getInventoryHandler().deleteItem(currentOreRequirements[i+1]);
			}
		}
		
		int EXP = -1;
		switch(currentBarType.toLowerCase()){
		case "bronze":
			EXP = (int)6.25;
			smithingAddBarToInventory(BRONZE_BAR);
			break;
		case "iron":
			EXP = (int)12.5;
			smithingAddBarToInventory(IRON_BAR);
			break;
		case "silver":
			EXP = (int)13.67;
			smithingAddBarToInventory(SILVER_BAR);
			break;
		case "steel":
			EXP = (int)17.5;
			smithingAddBarToInventory(STEEL_BAR);
			break;
		case "gold":
			EXP = (int)22.5;
			smithingAddBarToInventory(GOLD_BAR);
			break;
		case "mithril":
			EXP = (int)30;
			smithingAddBarToInventory(MITHRIL_BAR);
			break;
		case "adamantite":
			EXP = (int)37.5;
			smithingAddBarToInventory(ADAMANT_BAR);
			break;
		case "rune":
			EXP = (int)50;
			smithingAddBarToInventory(RUNE_BAR);
			break;
		}
		c.getClientMethodHandler().addSkillXP(EXP*SMITHINGRATE, c.playerSmithing);
		c.getSkillHandler().startSkillTimerForSkill(SMITHING_ANIMATION_DELAY, 5);
		return true;
	}
	
	private void smithingAddBarToInventory(int barID){
		c.getInventoryHandler().addItem(barID);
		c.sendMessage("You make a "+Item.getItemName(barID));
	}
	
	private static final int SMITHING_ANIMATION_DELAY = 3;
	
	public boolean removeBarAndSmithItem(int interfaceID, int removeID, int removeSlot, int maxAmountOfTimes){
		//Smith Column 1,2,3,4
		if (interfaceID == 1119 || interfaceID == 1120 || interfaceID == 1121 || interfaceID == 1122 || interfaceID == 1123){
			try { //TODO - use timers to loop this process :D
				int amountOfTimes = c.getInventoryHandler().amountOfItemInInventory(removeBar(removeID));
				amountOfTimes = amountOfTimes/barsNeeded(removeSlot, interfaceID);
				if(amountOfTimes > 0) {
					if(c.getInventoryHandler().playerHasItem(2347)) {
						int lvlRequired = getRequiredSmithingLevel(removeID); 
						if(c.playerLevel[c.playerSmithing] >= lvlRequired) {
							if(amountOfTimes > maxAmountOfTimes) amountOfTimes = maxAmountOfTimes;
							for(int e=0; e < amountOfTimes; e++) {
								int amount = 1;
								if(interfaceID == 1123){
									if(removeSlot == 0)
										amount = 10;
									if(removeSlot == 1)
										amount = 15;
									if(removeSlot == 2)
										amount = 5;
								}
								c.getInventoryHandler().ReplaceItems(removeID, removeBar(removeID), amount, barsNeeded(removeSlot, interfaceID));
								c.getFrameMethodHandler().RemoveAllWindows();
								c.startAnimation(898);
								c.getClientMethodHandler().addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)*c.rate), 13);
							}
						} 
						else {
							c.sendMessage("You need at least "+lvlRequired+" Smithing to do that.");
						}
					} 
					else {
						c.sendMessage("You need a hammer to smith this item");
					}
				} 
				else {
					c.sendMessage("You dont have enough bars to make this");
				}
			} 
			catch(Exception e) { 
				//c.sendMessage("You dont have enough bars to make this");
			}
			return true;
		}
		else return false;
	}

	/*SMITHING*/
	public int removeBar(int removeID) {
		if(removeID == 1149 || removeID == 1305 || removeID == 7158
				|| removeID == 6575 || removeID == 4087 || removeID == 7806
				|| removeID == 13602 || removeID == 9094 || removeID == 4151
				|| removeID == 5698 || removeID == 1187 || removeID == 1377
				|| removeID == 1434 || removeID == 14511 || removeID == 4587
				|| removeID == 14512 || removeID == 14513 || removeID == 14514
				|| removeID == 3140 || removeID == 14507 || removeID == 14508
				|| removeID == 14509) {
			return 2357;
		}
		if(removeID == 1205 || removeID == 1351 || removeID == 1103
				|| removeID == 1139 || removeID == 819 || removeID == 1277
				|| removeID == 1422 || removeID == 1075 || removeID == 1155
				|| removeID == 39 || removeID == 1321 || removeID == 1337
				|| removeID == 1087 || removeID == 1173 || removeID == 864
				|| removeID == 1291 || removeID == 1375 || removeID == 1117
				|| removeID == 1189 || removeID == 1307 || removeID == 3095
				|| removeID == 4819) {
			return 2349;
		}
		if(removeID == 1203 || removeID == 1349 || removeID == 1420
				|| removeID == 1137 || removeID == 1279 || removeID == 820
				|| removeID == 4820 || removeID == 1323 || removeID == 40
				|| removeID == 1293 || removeID == 1153 || removeID == 863
				|| removeID == 1175 || removeID == 1335 || removeID == 1363
				|| removeID == 1101 || removeID == 4540 || removeID == 1191
				|| removeID == 3096 || removeID == 1309 || removeID == 1067
				|| removeID == 1081 ||
				removeID == 1115) {
			return 2351;
		}
		if(removeID == 1207 || removeID == 3097 || removeID == 1353
				|| removeID == 1424 || removeID == 1141 || removeID == 1281
				|| removeID == 1325 || removeID == 1295 || removeID == 1157
				|| removeID == 1177 || removeID == 1339 || removeID == 1365
				|| removeID == 1105 || removeID == 1193 || removeID == 1069
				|| removeID == 1083 || removeID == 1311 || removeID == 1119
				|| removeID == 1539 ||
				removeID == 821 || removeID == 41
				|| removeID == 2 ||
				removeID == 2370 || removeID == 865
				|| removeID == 4544) {
			return 2353;
		}
		//Mith
		if(removeID == 1209 || removeID == 3099 || removeID == 1355
				|| removeID == 1428 || removeID == 1143 || removeID == 1285
				|| removeID == 1329 || removeID == 1299 || removeID == 1159
				|| removeID == 1181 || removeID == 1343 || removeID == 1369
				|| removeID == 1109 || removeID == 1197 || removeID == 1071
				|| removeID == 1085 || removeID == 1315 || removeID == 1121
				|| removeID == 822 || removeID == 4822 || removeID == 42
				|| removeID == 42 || removeID == 866) {
			return 2359;
		}
		//Addy
		if(removeID == 1211 || removeID == 3100 || removeID == 1430
				|| removeID == 1145 || removeID == 1287 || removeID == 1331
				|| removeID == 1301 || removeID == 1161 || removeID == 1183
				|| removeID == 1371 || removeID == 1111 || removeID == 1073
				|| removeID == 1091 || removeID == 1317 || removeID == 1123
				|| removeID == 823 ||
				removeID == 4823 || removeID == 43
				|| removeID == 867 ||
				removeID == 1199) {
			return 2361;
		}
		//Rune
		if(removeID == 1213 || removeID == 3101 || removeID == 1432
				|| removeID == 1147 || removeID == 1289 || removeID == 1333
				|| removeID == 1303 || removeID == 1163 || removeID == 1185
				|| removeID == 1347 || removeID == 1373 || removeID == 1113
				|| removeID == 1201 || removeID == 1079 || removeID == 1093
				|| removeID == 1319 || removeID == 1127 || removeID == 824
				|| removeID == 4824 || removeID == 44 || removeID == 868) {
			return 2363;
		}
		return 0;
	}
	public int barsNeeded(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 1;
			if (slot == 2 || slot == 3) return 2;
			if (slot == 4) return 3;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 1;
			if (slot == 2 || slot == 3) return 3;
			if (slot == 4) return 2;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 3;
			if (slot == 3) return 5;
			if (slot == 4) return 1;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 1;
			if (slot == 1 || slot == 2) return 2;
			if (slot == 3) return 3;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 1;
		}
		return 0;
	}
	public int barsNeeded2(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 5;
			if (slot == 2 || slot == 3) return 10;
			if (slot == 4) return 15;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 5;
			if (slot == 2 || slot == 3) return 15;
			if (slot == 4) return 10;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 15;
			if (slot == 3) return 25;
			if (slot == 4) return 5;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 5;
			if (slot == 1 || slot == 2) return 10;
			if (slot == 3) return 15;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 5;
		}
		return 0;
	}
	public int barsNeeded3(int slot, int column) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) return 10;
			if (slot == 2 || slot == 3) return 20;
			if (slot == 4) return 30;
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) return 10;
			if (slot == 2 || slot == 3) return 30;
			if (slot == 4) return 20;
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) return 30;
			if (slot == 3) return 50;
			if (slot == 4) return 10;
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) return 10;
			if (slot == 1 || slot == 2) return 20;
			if (slot == 3) return 30;
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 10;
		}
		return 0;
	}
	public int smithXP(int barType, int barAmount) {
		if (barType == 2357) return barAmount*25*c.rate; //gold bar
		if (barType == 2349) return barAmount*29*c.rate;
		if (barType == 2351) return barAmount*25*c.rate;
		if (barType == 2353) return barAmount*38*c.rate;
		if (barType == 2359) return barAmount*50*c.rate;
		if (barType == 2361) return barAmount*75*c.rate;
		if (barType == 2363) return barAmount*85*c.rate;
		return 0;
	}

	
	private int getRequiredSmithingLevel(int itemID){
		switch(itemID){
		case 1422: return 2;
		case 1139: return 3;
		case 1277: case 819: return 4;
		case 1321: case 39: return 5;
		case 1291: return 6;
		case 1155: case 864: return 7;
		case 1173: return 8;
		case 1337: return 9;
		case 1375: return 10;
		case 1103: return 11;
		case 1189: return 12;
		case 3095: return 13;
		case 1307: return 14;
		case 1203: return 15;
		case 1087: case 1075: case 1349: return 16;
		case 1420: return 17;
		case 1117: case 1137: return 19;
		case 1279: case 820: case 4820: return 19;
		case 1323: case 40: return 20;
		case 1293: return 21;
		case 1153: case 863: return 22;
		case 1175: return 23;
		case 1335: return 24;
		case 1363: return 25;
		case 1101: case 4540: return 26;
		case 1191: return 27;
		case 3096: return 28;
		case 1309: return 29;
		case 1207: return 30;
		case 1067: case 1081: case 1353: return 31;
		case 1424: return 32;
		case 1115: case 1141: return 33;
		case 1281: case 1539: case 821: return 34;
		case 1325: case 41: return 35;
		case 1295: case 2370: return 36;
		case 1157: case 865: return 37;
		case 1177: return 38;
		case 1339: return 39;
		case 1365: return 40;
		case 1105: return 41;
		case 1193: return 42;
		case 3097: return 43;
		case 1311: return 44;
		case 1069: case 1063: return 46;
		case 1119: return 48;
		case 4544: return 49;
		case 50: return 1209;
		case 1355: return 51;
		case 1428: return 52;
		case 1143: return 53;
		case 1285: case 822: case 4822: return 54;
		case 1329: case 42: return 55;
		case 1299: return 56;
		case 1159: case 866: return 57;
		case 1181: return 58;
		case 1343: return 59;
		case 1369: return 60;
		case 1109: return 61;
		case 1197: return 62;
		case 3099: return 63;
		case 1315: return 64;
		case 1071: case 1085: return 66;
		case 1121: return 68;
		case 1211: return 70;
		case 1430: return 72;
		case 1145: return 73;
		case 1287: case 823: case 4823: return 74;
		case 1331: case 43: return 75;
		case 1301: return 76;
		case 1161: case 867: return 77;
		case 1183: return 78;
		case 1371: return 79;
		case 1111: return 81;
		case 1199: return 82;
		case 3100: return 83;
		case 1317: return 84;
		case 1213: return 85;
		case 1073: case 1091: case 1359: return 86;
		case 1432: return 87;
		case 1123: case 1147: return 88;
		case 1289: case 824: case 4824: return 89;
		case 1333: case 44: return 90;
		case 1303: return 91;
		case 1163: case 868: return 92;
		case 1185: return 93;
		case 1347: return 94;
		case 1373: return 95;
		case 1113: return 96;
		case 1201: return 97;
		case 3101: return 98;
		case 1079: case 1093: case 1319: case 1127: return 99;
		}
		return 0;
	}

}

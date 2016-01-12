
public class ItemUse {

	private client c = null;

	public ItemUse(client pc){
		this.c = pc;
	}


	public boolean equipcape (String cape){
		c.sendMessage("I need at least 99 "+cape+" to equip this.");
		return false;
	}

	/**
	 * Will return true when used, is for functionality purposes with itemUseWith method
	 */
	public boolean herblorevoid(int level, int add, int delete1, int delete2){
		if(playerLevel[15] >= level) {
			deleteItem(delete1, getItemSlot(delete1), 1);
			addItem(add, 1);
			deleteItem(delete2, getItemSlot(delete2), 1);
			addSkillXP(playerLevel[15]*6*rate, 15);
		} else {
			sendMessage("A herblore level of "+level+" is required to do that.");
		}
		return true;
	}

	/**
	 * Boolean that handles using one item with another, will use recursion to switch the items
	 * @param useWith first item
	 * @param itemUsed item used with another
	 * @return True if a combination was found, false if not
	 */
	public boolean itemUsedWith(int useWith, int itemUsed){

		//means an item was used with a crafting item
		if(lists.craftingList.exists(useWith) || lists.craftingList.exists(itemUsed)){
			if(this.CRAFT.checkCrafting(itemUsed, useWith)) return true;
			if(this.CRAFT.checkCrafting(useWith, itemUsed)) return true;
		}

		//Herblore
		if(itemUsed == 233 && useWith == 237) 
			return removeAdd(useWith, 235);

		if(itemUsed == 233 && useWith == 243) 
			return removeAdd(useWith, 241);

		if(itemUsed == 233 && useWith == 1973) 
			return removeAdd(useWith, 1975);

		if(itemUsed == 249 && useWith == 227)
			return removeAdd(itemUsed, useWith, 91);

		if(itemUsed == 251 && useWith == 227) 
			return removeAdd(itemUsed, useWith, 93);

		if(itemUsed == 253 && useWith == 227)
			return removeAdd(itemUsed, useWith, 95);

		if(itemUsed == 255 && useWith == 227)
			return removeAdd(itemUsed, useWith, 97);

		if(itemUsed == 257 && useWith == 227)
			return removeAdd(itemUsed, useWith, 99);

		if(itemUsed == 259 && useWith == 227)
			return removeAdd(itemUsed, useWith, 101);

		if(itemUsed == 261 && useWith == 227)
			return removeAdd(itemUsed, useWith, 103);

		if(itemUsed == 263 && useWith == 227)
			return removeAdd(itemUsed, useWith, 105);

		if(itemUsed == 265 && useWith == 227)
			return removeAdd(itemUsed, useWith, 107);

		if(itemUsed == 267 && useWith == 227)
			return removeAdd(itemUsed, useWith, 109);

		if(itemUsed == 269 && useWith == 227)
			return removeAdd(itemUsed, useWith, 111);
		//end Herblore


		//Fletching Start 
		if(itemUsed == 52 && useWith == 314)
			this.FLETCHING.fletchArrow();

		if(itemUsed == 53) //headless arrow
			this.FLETCHING.fletchingMakeArrow(useWith);

		if(itemUsed == 946 && useWith == 1511) {
			fletchingvoid("", 1511, 50, 1, 48, 10, 40, false);
			selectoption2("Options", "Make all"+fletchinglogs+" Shortbows", "Make all"+fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 50) {
			fletchingvoid("", 50, 841, 1, 0, 0, 40, true);
			selectoption("Options", "String all shortbows", "Cancel", "...");
			return true;
		}

		if(itemUsed == 1777 && useWith == 48) {
			fletchingvoid("", 48, 839, 10, 0, 0, 75, true);
			selectoption("Options", "String all longbows", "Cancel", "...");
			return true;
		}

		if(itemUsed == 946 && useWith == 1521) {
			fletchingvoid(" Oak", 1521, 54, 15, 56, 25, 85, false);
			selectoption2("Options", "Make all"+fletchinglogs+" Shortbows", "Make all"+fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 54) {
			fletchingvoid("", 54, 843, 15, 0, 0, 85, true);
			selectoption("Options", "String all oak shortbows", "Cancel", "...");
			return true;
		}
		if(itemUsed == 1777 && useWith == 56) {
			fletchingvoid("", 56, 845, 25, 0, 0, 100, true);
			selectoption("Options", "String all oak longbows", "Cancel", "...");
			return true;
		}
		if(itemUsed == 946 && useWith == 1519) {
			fletchingvoid(" Willow", 1519, 60, 35, 58, 40, 115, false);
			selectoption2("Options", "Make all"+fletchinglogs+" Shortbows", "Make all"+fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 60) {
			fletchingvoid("", 60, 849, 35, 0, 0, 115, true);
			selectoption("Options", "String all willow shortbows", "Cancel", "...");
			return true;
		}
		if(itemUsed == 1777 && useWith == 58) {
			fletchingvoid("", 58, 847, 40, 0, 0, 135, true);
			selectoption("Options", "String all willow longbows", "Cancel", "...");
			return true;
		}
		if(itemUsed == 946 && useWith == 1517) {
			fletchingvoid(" Maple", 1517, 64, 45, 62, 50, 155, false);
			selectoption2("Options", "Make all"+fletchinglogs+" Shortbows", "Make all"+fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 64) {
			fletchingvoid("", 64, 853, 45, 0, 0, 155, true);
			selectoption("Options", "String all maple shortbows", "Cancel", "...");
			return true;
		}
		if(itemUsed == 1777 && useWith == 62) {
			fletchingvoid("", 62, 851, 50, 0, 0, 180, true);
			selectoption("Options", "String all maple longbows", "Cancel", "...");
			return true;
		}
		if(itemUsed == 946 && useWith == 1515) {
			fletchingvoid(" Yew", 1515, 68, 55, 66, 60, 200, false);
			selectoption2("Options", "Make all"+fletchinglogs+" Shortbows", "Make all"+fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 68) {
			fletchingvoid("", 68, 857, 55, 0, 0, 200, true); 
			selectoption("Options", "String all yew shortbows", "Cancel", "...");
			return true;
		}			
		if(itemUsed == 1777 && useWith == 66) {
			fletchingvoid("", 66, 855, 60, 0, 0, 240, true); 
			selectoption("Options", "String all yew longbows", "Cancel", "...");
			return true;
		}		
		if(itemUsed == 946 && useWith == 1513) {
			fletchingvoid(" Magic", 1513, 72, 70, 70, 80, 300, false);
			selectoption2("Options", "Make all"+fletchinglogs+" Shortbows", "Make all"+fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}						
		if(itemUsed == 1777 && useWith == 72) {
			fletchingvoid("", 72, 861, 70, 0, 0, 300, true); 
			selectoption("Options", "String all magic shortbows", "Cancel", "...");
			return true;
		}		
		if(itemUsed == 1777 && useWith == 70) {
			fletchingvoid("", 70, 859, 80, 0, 0, 360, true); 
			selectoption("Options", "String all magic longbows", "Cancel", "...");
			return true;
		}		
		//Fletching End

		//herblore void
		if(itemUsed == 91 && useWith == 221) 
			return herblorevoid(0, 121, itemUsed, useWith);

		if(itemUsed == 93 && useWith == 235) 
			return herblorevoid(5, 175, itemUsed, useWith);

		if(itemUsed == 95 && useWith == 225) 
			return herblorevoid(12, 115, itemUsed, useWith);

		if(itemUsed == 97 && useWith == 223) 
			return herblorevoid(22, 127, itemUsed, useWith);

		if(itemUsed == 97 && useWith == 1975) 
			return herblorevoid(26, 3010, itemUsed, useWith);

		if(itemUsed == 99 && useWith == 239) 
			return herblorevoid(30, 133, itemUsed, useWith);

		if(itemUsed == 99 && useWith == 231) 
			return herblorevoid(38, 139, itemUsed, useWith);

		if(itemUsed == 101 && useWith == 221) 
			return herblorevoid(45, 145, itemUsed, useWith);

		if(itemUsed == 101 && useWith == 235) 
			return herblorevoid(48, 181, itemUsed, useWith);

		if(itemUsed == 103 && useWith == 231) 
			return herblorevoid(50, 151, itemUsed, useWith);

		if(itemUsed == 103 && useWith == 2970) 
			return herblorevoid(52, 3018, itemUsed, useWith);

		if(itemUsed == 105 && useWith == 225) 
			return herblorevoid(55, 157, itemUsed, useWith);

		if(itemUsed == 105 && useWith == 241) 
			return herblorevoid(60, 187, itemUsed, useWith);

		if(itemUsed == 109 && useWith == 245) 
			return herblorevoid(72, 169, itemUsed, useWith);

		if(itemUsed == 111 && useWith == 247) 
			return herblorevoid(78, 189, itemUsed, useWith);

		if(itemUsed == 2483 && useWith == 241) 
			return herblorevoid(69, 2454, itemUsed, useWith);

		if(itemUsed == 2483 && useWith == 221) 
			return herblorevoid(65, 2454, itemUsed, useWith);

		if(itemUsed == 2483 && useWith == 3138) 
			return herblorevoid(76, 3042, itemUsed, useWith);
		//end herblore


		if(itemUsed == 273 && useWith == 272) {
			startAnimation(885);
			sendMessage("You poison the fish food.");
			return removeAdd(itemUsed,useWith,274);
		}

		if(itemUsed == 139 && useWith == 1513) {
			startAnimation(885);
			sendMessage("You get a mixture of chemicals.");
			return removeAdd(itemUsed,useWith,705);
		}

		//Dragon SQ Together
		if(itemUsed == 2366 && useWith == 2368) {
			if(playerLevel[12] >= 65) {
				removeAdd(itemUsed,useWith,1187);
				addSkillXP(15000, 12);
				startAnimation(1649);
				sendMessage("You put together the two halves, making a... whole!");
			} else sendMessage("You need 65 crafting to put the Dragon SQ together.");
			return true;
		}
		// tj007razor: firemaking uses
		if (itemUsed == 590 && (useWith == 1511 || useWith == 1521 || useWith == 1519 || useWith == 1517 || useWith == 1515 || useWith == 1513)){
			Firemaking.addFire(this, useWith);
			return true;
		}
		// end firemaking
		return false;
	}

	/**
	 * Private Helper method used with itemUsedWith boolean
	 * @param remove1 Removes this item from player inventory
	 * @param remove2 Removes this item from player inventory
	 * @param add Adds this item to player inventory
	 * @return true unless remove items not found
	 */
	public boolean removeAdd(int remove1, int remove2, int add){
		int slot1 = getItemSlot(remove1);
		int slot2 = getItemSlot(remove2);
		if(slot1 == -1 || slot2 == -1){
			debug("In removeAdd method, could not find item(s) in player inventory");
			return false;
		}
		deleteItem(remove1, slot1, 1);
		deleteItem(remove2, slot2, 1);
		addItem(add,1);
		return true;
	}

	/**
	 * Private Helper method used with itemUsedWith boolean
	 * @param remove Removes this item from player inventory
	 * @param add Adds this item to player inventory
	 * @return true unless remove item was not found
	 */
	private boolean removeAdd(int remove, int add){
		int slot = getItemSlot(remove);
		if(slot == -1){
			debug("In removeAdd method, could not find item "+remove+" in inventory.");
			return false;
		}
		deleteItem(remove, slot, 1);
		addItem(add,1);
		return true;
	}

	/**
	 * @return false by default
	 */
	public boolean prayerPot(int deleteItemID, int addItemID, int slotID){
		setAnimation(829);
		playerLevel[5] += 44;
		if (playerLevel[5] > getLevelForXP(playerXP[5])) 
			playerLevel[5] = getLevelForXP(playerXP[5]);

		sendFrame126(""+playerLevel[5]+"", 4012);
		deleteItem(deleteItemID, slotID, 1);
		addItem(addItemID, 1);
		requirePlayerUpdate();
		addSkillXP(0, 5); //updates frame
		return false;
	}

	/**
	 * @return false by default
	 */
	private boolean superPot(int skillID, int removeItemID, int addItemID, int slotID){
		setAnimation(829);
		double cba = getLevelForXP(playerXP[skillID]) / 10;
		double aaa = cba * 2;
		if (aaa <= 1) 
			aaa = 2; //minimum is +2
		playerLevel[skillID] = getLevelForXP(playerXP[skillID]);
		playerLevel[skillID] += (int)aaa;
		sendFrame126(""+playerLevel[0]+"", 4004);
		deleteItem(removeItemID, slotID, 1);
		addItem(addItemID, 1);
		requirePlayerUpdate();
		addSkillXP(0, skillID); //updates frame
		return false;
	}	

	/**
	 * @return false by default
	 */
	private boolean pot(int skillID, int removeItemID, int addItemID){
		setAnimation(829);
		double cba = getLevelForXP(playerXP[skillID]) / 10;
		double aaa = cba / 2;
		double abc2 = aaa + cba;
		if (abc2 <= 1) 
			abc2 = 2; //minimum is +2
		playerLevel[skillID] = getLevelForXP(playerXP[skillID]);
		playerLevel[skillID] += (int)abc2;
		sendFrame126(""+playerLevel[0]+"", 4004);
		deleteItem(removeItemID, GetItemSlot(removeItemID), 1);
		addItem(addItemID, 1);
		requirePlayerUpdate();
		addSkillXP(0, skillID); //updates frame
		return false;
	}


	/**
	 * @return false by default
	 */
	private boolean identify(int requiredLevel, int EXP, int itemID, int addItemID){
		if (playerLevel[15] >= requiredLevel) {
			addSkillXP((EXP*rate), 15);
			deleteItem(itemID, GetItemSlot(itemID), 1);
			sendMessage("You identify the herb.");
			addItem(addItemID, 1);
		} 
		else sendMessage("You aren't high enough herblore to identify this herb");
		return false;
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class ClientMethodHandler {

	client c = null;

	public static ClientMethodHandler getClientMethodHandler(int playerIndex){
		client player = (client) server.playerHandler.players[playerIndex];
		if(player == null){
			System.out.println("[Error] : in getClientMethodHandler in ClientMethodHandler, given invalid player index");
			return null;
		}
		return player.getClientMethodHandler();
	}

	public ClientMethodHandler(client pc){
		this.c = pc;
	}
	
	public void populate(LinkedList<Drop> list, Drop ... drops){
		for(int i = 0; i < drops.length; i++){
			for(int j = 0; j < drops[i].getPercent(); j++){
				list.add(drops[i]);
			}
		}
	}

	public void sendEnergy() {
		c.getFrameMethodHandler().sendFrame126((int)c.runningEnergy + "%", 149);
	}

	public boolean isInPKZone(){
		if(c.isInArea(2583,3153,2606,3170))
			return true;
		//PK area, Istafdar, Tyras Camp
		if(c.isInArea(2363,3314,2392,3333))
			return true;

		if(c.isInArea(2169,3072,2366,3344) && !c.isInArea(2319,3150,2359,3194) && !c.isInArea(2174,3131,2201,3163) && !c.isInArea(2204,3257,2209,3259)) //pk camps
			return true;	

		//		if(c.isInArea(2111,4893,2160,4931) && !c.isInArea(2128,4904,2137,4910)) //old pk zone - draynor 
		//			return true;

		return false;
	}

	protected boolean canPlayersTeleportInThisArea(){
		if((c.isInArea(3644,2937,3716,3011) && c.pirate < 10) || isInPKZone() || c.isInArea(3455,9470,3522,9536) || (c.absX >=2002  && c.absX <=2035 && c.absY >=4814  && c.absY <=4833) || (c.absX >=3121  && c.absX <=3125 && c.absY >=3240  && c.absY <=3243) || (c.absX >=3138  && c.absX <=3186 && c.absY >=3718  && c.absY <=3748))
			return false;
		return true;
	}

	/**
	 * Does animation and gfx, until time is zero, then teleports to x and y
	 */
	public void isteleporting2(int gfx, int anim, int time, int x, int y, int h){
		c.getFrameMethodHandler().stillgfx(gfx, c.absY, c.absX);
		c.startAnimation(anim);
		c.isteleporting = time;
		c.isteleportingx = x;
		c.isteleportingy = y;
		c.ithl = h;
	}

	/**
	 * Will start an animation for time and then teleport to x,y,h
	 */
	public void teleportWithAnimation(int anim, int time, int x, int y, int h){
		c.startAnimation(anim);
		c.isteleporting = time;
		c.isteleportingx = x;
		c.isteleportingy = y;
		c.ithl = h;
	}

	public void selectOptionTravel2(String question, String place1, int x1, int y1, String place2, int x2, int y2){
		c.travel2_X1 = x1; c.travel2_Y1 = y1; c.travel2_X2 = x2; c.travel2_Y2 = y2; c.travelHeight = 0;
		c.getFrameMethodHandler().sendFrame171(1, 2465);
		c.getFrameMethodHandler().sendFrame171(0, 2468);
		c.getFrameMethodHandler().sendFrame126(question, 2460);
		c.getFrameMethodHandler().sendFrame126(place1, 2461);
		c.getFrameMethodHandler().sendFrame126(place2, 2462);
		c.getFrameMethodHandler().sendFrame126("", 2463);
		c.getFrameMethodHandler().sendFrame164(2459);
	}

	public void selectOptionTravel2(String question, String place1, int x1, int y1, String place2, int x2, int y2, int finishHeight){
		selectOptionTravel2(question, place1, x1, y1, place2, x2, y2);
		c.travelHeight = finishHeight;
	}


	/*NPC Talking*/
	public String getNpcName(int npcID) {
		if(server.npcHandler.npcList2.exists(npcID))
			return server.npcHandler.npcList2.getName();
		c.debug("NPC Name for "+npcID+" not found.");
		return null;
	}

	public void npcdialogue(int id, String ... lines){
		c.npcLines.clear();
		c.npcID = id;
		c.npcName = getNpcName(id);
		for(int i = 0; i < lines.length; i++)
			c.npcLines.add(lines[i]);
		npcChat();
	}	

	public void npcdialogue(String name, int id, String ... lines){
		c.npcLines.clear();
		c.npcID = id;
		c.npcName = name;
		for(int i = 0; i < lines.length; i++)
			c.npcLines.add(lines[i]);
		npcChat();
	}	



	public void levelup(int skill)
	{
		switch(skill)  
		{
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
			c.sendMessage("You have gained a level.");
			c.getFrameMethodHandler().stillgfx(199, c.absY, c.absX);
			if (c.playerLevel[skill] >= 99){
				c.masteries += 1;
				c.sendMessage("Congratulations on skill mastery. Skill capes and hoods are");
				c.sendMessage("available at their respective skill masters.");
			}
			break;
		}
		int totalLevel = 0;
		for(int i = 0; i <= 20; i++)
			totalLevel += c.getLevelForXP(c.playerXP[i]);
		c.getFrameMethodHandler().sendFrame126("Total Lvl: "+totalLevel, 3984);
	}

	public boolean addSkillXP(int amount, int skill){
		c.debug("You recieved "+amount+" exp in Skill "+c.statName[skill]);

		int[] playerXP = c.playerXP;
		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 2000000000) {
			c.sendMessage("Max XP value reached");
			return false;
		}

		int oldLevel = c.getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;

		if(oldLevel < c.getLevelForXP(playerXP[skill]))
			levelup(skill);
		c.playerLevel[skill] = c.getLevelForXP(playerXP[skill]);
		c.requirePlayerUpdate();

		c.getFrameMethodHandler().setSkillLevel(skill, c.playerLevel[skill], playerXP[skill]);
		c.getFrameMethodHandler().refreshSkills();
		return true;
	}

	/**
	 * Used to load the npcLines queue from dialogueHandler
	 * @param npcID
	 */
	public void startDialogue(int n){
		c.npcLines.clear();
		c.npcID = n;
		c.npcName = c.DIALOGUEHANDLER.getName();
		String[] lines = c.DIALOGUEHANDLER.getLines();
		for(int i = 0; i < lines.length; i++)
			c.npcLines.add(lines[i]);
		npcChat();
	}

	/**
	 * Heals player by amount
	 * @return True by default
	 */
	public boolean heal(int amount){
		c.NewHP += amount;
		if(c.NewHP > c.getLevelForXP(c.playerXP[3])) 
			c.NewHP = c.getLevelForXP(c.playerXP[3]);
		c.requirePlayerUpdate();
		return true;
	}

	/**
	 * Checks the skill with the level
	 * @return true if player has greater than or equal to
	 */
	public boolean checkLevel(int _skill, int _level){
		if(c.getLevelForXP(c.playerXP[_skill]) >= _level) 
			return true;
		return false;
	}

	public void deadreturn(){
		c.deadtele = 0;
		c.teleport(3024,3206,c.ithl);
		c.isteleporting = 0;
	}

	public boolean isInBarrows() {
		if (c.isInArea(3522, 9720, 3583, 9675)) return true;
		if (c.isInArea(3546, 3305, 3583, 3264)) return true;
		return false;
	}
	public void barrowsreset() {
		c.torag = 0;
		c.verac = 0;
		c.guthan = 0;
		c.ahrim = 0;
		c.dharok = 0;
		c.karil = 0;
	}
	public void spawnNPC(int npcID, int _absX, int _absY)
	{
		server.npcHandler.newNPC(npcID, _absX, _absY, c.heightLevel, _absX, _absY, _absX, _absY, 1, server.npcHandler.getHP(npcID), false);
	}



	public void placeGlobalObject(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		for (Player p : server.playerHandler.players) {
			if(p != null) {
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")) {
					if(person.distanceToPoint(objectX, objectY) <= 60) {
						person.getFrameMethodHandler().placeObject(objectX, objectY, NewObjectID, Face, ObjectType);
					}
				}
			}
		}
	}


	public void animationAtGroundHeight(int id, int Y, int X){  //ANIMATIONS AT GROUND HEIGHT
		for (Player p : server.playerHandler.players){
			if(p != null){
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")){
					if(person.distanceToPoint(X, Y) <= 60)
						person.getFrameMethodHandler().animationFrameAtGroundHeight(id, Y, X);
				}
			}
		}
	}
	public void animationAtMiddleHeight(int id, int Y, int X)  //ANIMATIONS AT MIDDLE HEIGHT
	{
		for (Player p : server.playerHandler.players)
		{
			if(p != null)
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(X, Y) <= 60)
					{
						person.getFrameMethodHandler().animationFrameAtMiddleHeight(id, Y, X);
					}
				}
			}
		}
	}


	public void AddGlobalObj(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(objectX, objectY) <= 60)
					{
						person.getFrameMethodHandler().ReplaceObject2(objectX, objectY, NewObjectID, Face, ObjectType);
					}
				}
			}
		}
	}


	public Calendar cal = new GregorianCalendar();
	public int hour12 = cal.get(Calendar.HOUR);            // 0..11
	public int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	public int min = cal.get(Calendar.MINUTE);             // 0..59
	public int sec = cal.get(Calendar.SECOND);             // 0..59
	public int ms = cal.get(Calendar.MILLISECOND);         // 0..999
	public int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM
	public void getTime(){
		c.sendMessage("hour: "+hour24+" mins: "+min+" secs: "+sec);
	}

	public boolean isInMultiCombat(){
		if(isInGodWars())
			return true;
		return false;
	}

	public boolean isInGodWars(){
		return (c.isInArea(2744,3584,2819,3712) || c.isInArea(2803,3580,2927,3639));
	}


	public void checkWildRange(int pcombat){
		if(((c.combat + c.WildyLevel >= pcombat) && (pcombat >= c.combat)) || ((c.combat - c.WildyLevel <= pcombat) && (pcombat <= c.combat)))
			c.inWildRange = true;
		else c.inWildRange = true; //default false
	}


	public void ancientsfinished(){
		addSkillXP(100000, 17);
		c.getInventoryHandler().deleteItem(2372,c.getInventoryHandler().getItemSlot(2372),1);
		c.getFrameMethodHandler().Menu(c.getMenuHandler().ancientsfinished());
	}

	public void ancients2finished(){
		addSkillXP(100000, 6);
		c.getFrameMethodHandler().Menu(c.getMenuHandler().ancients2finished());
	}


	public void resetShop(int ShopID) {
		int TotalItems = 0;
		for (int i = 0; i < server.shopHandler.MaxShopItems; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0) {
				TotalItems++;
			}
		}
		if (TotalItems > server.shopHandler.MaxShopItems) {
			TotalItems = server.shopHandler.MaxShopItems;
		}
		c.outStream.createFrameVarSizeWord(53);
		c.outStream.writeWord(3900);
		c.outStream.writeWord(TotalItems);
		int TotalCount = 0;
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0 || i <= server.shopHandler.ShopItemsStandard[ShopID]) {
				if (server.shopHandler.ShopItemsN[ShopID][i] > 254) {
					c.outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
					c.outStream.writeDWord_v2(server.shopHandler.ShopItemsN[ShopID][i]);	// and then the real value with writeDWord_v2
				} else {
					c.outStream.writeByte(server.shopHandler.ShopItemsN[ShopID][i]);
				}
				if (server.shopHandler.ShopItems[ShopID][i] > 17000 || server.shopHandler.ShopItems[ShopID][i] < 0) {
					server.shopHandler.ShopItems[ShopID][i] = 17000;
				}
				c.outStream.writeWordBigEndianA(server.shopHandler.ShopItems[ShopID][i]); //item id
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		c.outStream.endFrameVarSizeWord();
	}

	//	bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
	//	bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);

	public void rearrangeBank() { //credits : http://www.rune-server.org/runescape-development/rs2-server/tutorials/53595-bank-rearrange-tut.html
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < c.playerBankSize; i++) {
			if (c.bankItems[i] != 0) { 
				totalItems ++;
				if (highestSlot <= i) highestSlot = i;
			} }  

		for (int i = 0; i <= highestSlot; i++) {
			if (c.bankItems[i] == 0) {
				boolean stop = false;

				for (int k = i; k <= highestSlot; k++) {
					if (c.bankItems[k] != 0 && !stop) {
						int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							c.bankItems[j-spots] = c.bankItems[j];
							c.bankItemsN[j-spots] = c.bankItemsN[j];
							stop = true;
							c.bankItems[j] = 0; c.bankItemsN[j] = 0; 
						}
					}
				}					
			}
		}

		int totalItemsAfter = 0;
		for (int i = 0; i < c.playerBankSize; i++) {
			if (c.bankItems[i] != 0) { totalItemsAfter ++; } }

		if (totalItems != totalItemsAfter) c.outStream.createFrame(109); //disconnects when duping

	}	



	public void sortBank(){
		int[] tempArray = new int[c.bankItems.length];
		int[] tempArrayN = new int[c.bankItems.length];
		int index = 0;
		for(int i = 0; i < c.bankItems.length ;i++){
			if(c.bankItemsN[i] > 0){
				tempArray[index] = c.bankItems[i];
				tempArrayN[index] = c.bankItemsN[i];
				index += 1;
			}

		}
		c.bankItems = tempArray;
		c.bankItemsN = tempArrayN;
	}


	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = c.playerItems[from];
			tempN = c.playerItemsN[from];

			c.playerItems[from] = c.playerItems[to];
			c.playerItemsN[from] = c.playerItemsN[to];
			c.playerItems[to] = tempI;
			c.playerItemsN[to] = tempN;
		}

		if (moveWindow == 34453) { //Credits to http://www.rune-server.org/runescape-development/rs2-server/tutorials/54022-tut-full-bank-rearrange-refilling-swapping-inserting.html
			if (c.bankRearrange == "insert") {
				from -= 65280;
				if (to < 0) { int temp = 128 - (to*-1);
				to = 128 + temp; }
			}
			if (c.bankRearrange == "swap") {
				from = from;
				if (to < 0) { int temp = 128 - (to*-1);
				to = 128 + temp; }
			}
			if (from >= 0 && to >= 0 && from < c.playerBankSize && to < c.playerBankSize) {	

				if (c.bankRearrange != "insert") {
					int tempI = c.bankItems[from];
					int tempN = c.bankItemsN[from];

					c.bankItems[from] = c.bankItems[to];
					c.bankItemsN[from] = c.bankItemsN[to];
					c.bankItems[to] = tempI;
					c.bankItemsN[to] = tempN;
				}
				if (c.bankRearrange == "insert") {
					int tempItemFrom = c.bankItems[from];
					int tempNFrom = c.bankItemsN[from];
					int tempItemTo = c.bankItems[to];
					int tempNTo = c.bankItemsN[to];
					boolean gotSlot = false;


					int totalItems = 0;
					int highestSlot = 0;

					for (int i = 0; i < c.playerBankSize; i++) {
						if (c.bankItems[i] != 0) { 
							totalItems ++;
							if (highestSlot <= i) highestSlot = i;
						} }  

					c.bankItems[from] = 0;
					c.bankItemsN[from] = 0;

					if (from > to) {
						for (int i = from; i <= from && i >= to; i--) {
							if (i != to) {
								c.bankItems[i] = c.bankItems[i - 1];
								c.bankItemsN[i] = c.bankItemsN[i - 1];
							}

							if (i == to) {
								c.bankItems[i] = tempItemFrom;
								c.bankItemsN[i] = tempNFrom;
							}
						}
					}

					if (from < to) {
						for (int i = from; i >= from && i <= to; i++) {
							if (i != to) {
								c.bankItems[i] = c.bankItems[i + 1];
								c.bankItemsN[i] = c.bankItemsN[i + 1];
							}

							if (i == to) {
								c.bankItems[i] = tempItemFrom;
								c.bankItemsN[i] = tempNFrom;
							}
						}
					}

					int totalItemsAfter = 0;
					for (int i = 0; i < c.playerBankSize; i++) {
						if (c.bankItems[i] != 0) { totalItemsAfter ++; } }

					if (totalItems != totalItemsAfter) c.outStream.createFrame(109); //disconnects when duping. Just to be sure

				} c.getFrameMethodHandler().resetBankFrame();
			}
		}

		else if (moveWindow == 18579) {
			c.getFrameMethodHandler().resetItems(5064);
		} else if (moveWindow == 3724) {
			c.getFrameMethodHandler().resetItems(3214);
		}
	}


	public int getFreeBankSlots() {
		int freeS = 0;
		for (int i = 0; i < c.playerBankSize; i++) {
			if (c.bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}


	public void ReplaceAllItemWithNewItemInBankAndInventory(int oldID, int newID) {

		for(int i = 0; i < c.playerBankSize; i++)
		{
			if(c.bankItems[i] == oldID+1)
			{
				int newamount2 = c.bankItemsN[i];
				c.bankItems[i] = newID+1;
				c.bankItemsN[i] = newamount2;
			}
		}
		for(int i2 = 0; i2 < c.playerItems.length; i2++)
		{
			if(c.playerItems[i2] == oldID+1)
			{
				int newamount = c.playerItemsN[i2];
				c.getInventoryHandler().deleteItem(oldID, c.getInventoryHandler().getItemSlot(oldID), c.playerItemsN[i2]);
				c.getInventoryHandler().addItem(newID, newamount);
			}
		}

	}


	public void AcceptTrade() {
		c.getFrameMethodHandler().sendFrame248(3323, 3321); //trading window + bag
		c.getFrameMethodHandler().resetItems(3322);
		c.getFrameMethodHandler().resetTItemsFrame(3415);
		c.getFrameMethodHandler().resetOTItemsFrame(3416);
		c.getFrameMethodHandler().sendFrame126("Trading With: "+PlayerHandler.players[c.tradeWith].playerName, 3417);
		c.getFrameMethodHandler().sendFrame126("", 3431);
	}
	public void DeclineTrade() {
		for (int i = 0; i < c.playerTItems.length; i++) {
			if (c.playerTItems[i] > 0) {
				fromTrade((c.playerTItems[i] - 1), i, c.playerTItemsN[i]);
			}
		}
		c.getFrameMethodHandler().resetItems(3214);
		resetTrade();
	}
	public void resetTrade() {
		c.tradeWith = 0;
		c.tradeWaitingTime = 0;
		c.tradeStatus = 0;
		c.tradeUpdateOther = false;
		c.tradeOtherDeclined = false;
		c.WanneTrade = 0;
		c.WanneTradeWith = 0;
		c.TradeConfirmed = false;
		for (int i = 0; i < c.playerTItems.length; i++) {
			c.playerTItems[i] = 0;
			c.playerTItemsN[i] = 0;
			c.playerOTItems[i] = 0;
			c.playerOTItemsN[i] = 0;
		}
	}
	public void ConfirmTrade() {
		if (c.TradeConfirmed == false) {
			c.getFrameMethodHandler().RemoveAllWindows();
			for (int i = 0; i < c.playerOTItems.length; i++) {
				if (c.playerOTItems[i] > 0) {
					c.getInventoryHandler().addItem((c.playerOTItems[i] - 1), c.playerOTItemsN[i]);

				}
			}
			c.getFrameMethodHandler().resetItems(3214);
			c.TradeConfirmed = true;
		}
	}
	public void TradeGoConfirm() {
		c.getFrameMethodHandler().sendFrame248(3443, 3213); //trade confirm + normal bag
		c.getFrameMethodHandler().resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		for (int i = 0; i < c.playerTItems.length; i++) {
			if (c.playerTItems[i] > 0) {
				if (c.playerTItemsN[i] >= 1000 && c.playerTItemsN[i] < 1000000) {
					SendAmount = "" + (c.playerTItemsN[i] / 1000) + "K (" + c.playerTItemsN[i] + ")";
				} else if (c.playerTItemsN[i] >= 1000000) {
					SendAmount = "" + (c.playerTItemsN[i] / 1000000) + " million (" + c.playerTItemsN[i] + ")";
				} else {
					SendAmount = "" + c.playerTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = Item.getItemName((c.playerTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + Item.getItemName((c.playerTItems[i] - 1));
				}
				if (Item.itemIsNote[(c.playerTItems[i] - 1)] == true || Item.itemStackable[(c.playerTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		c.getFrameMethodHandler().sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (int i = 0; i < c.playerOTItems.length; i++) {
			if (c.playerOTItems[i] > 0) {
				if (c.playerOTItemsN[i] >= 1000 && c.playerOTItemsN[i] < 1000000) {
					SendAmount = "" + (c.playerOTItemsN[i] / 1000) + "K (" + c.playerOTItemsN[i] + ")";
				} else if (c.playerOTItemsN[i] >= 1000000) {
					SendAmount = "" + (c.playerOTItemsN[i] / 1000000) + " million (" + c.playerOTItemsN[i] + ")";
				} else {
					SendAmount = "" + c.playerOTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = Item.getItemName((c.playerOTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + Item.getItemName((c.playerOTItems[i] - 1));
				}
				if (Item.itemIsNote[(c.playerOTItems[i] - 1)] == true || Item.itemStackable[(c.playerOTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		c.getFrameMethodHandler().sendFrame126(SendTrade, 3558);
	}
	public boolean fromTrade(int itemID, int fromSlot, int amount) {
		if (amount > 0 && (itemID + 1) == c.playerTItems[fromSlot]) {
			if (amount > c.playerTItemsN[fromSlot]) {
				amount = c.playerTItemsN[fromSlot];
			}
			c.getInventoryHandler().addItem((c.playerTItems[fromSlot] - 1), amount);
			if (amount == c.playerTItemsN[fromSlot]) {
				c.playerTItems[fromSlot] = 0;
				PlayerHandler.players[c.tradeWith].playerOTItems[fromSlot] = 0;
			}
			c.playerTItemsN[fromSlot] -= amount;
			PlayerHandler.players[c.tradeWith].playerOTItemsN[fromSlot] -= amount;
			c.getFrameMethodHandler().resetItems(3322);
			c.getFrameMethodHandler().resetTItemsFrame(3415);
			PlayerHandler.players[c.tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[c.tradeWith].tradeStatus == 3) {
				PlayerHandler.players[c.tradeWith].tradeStatus = 2;
				PlayerHandler.players[c.tradeWith].AntiTradeScam = true;
				c.getFrameMethodHandler().sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}
	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		if (c.tradeWith > 0) {
			if (PlayerHandler.players[c.tradeWith] == null) {
				DeclineTrade();
				c.sendMessage("FORCED DECLINE BY SERVER !");
				return false;
			}
		} else {
			DeclineTrade();
			c.sendMessage("FORCED DECLINE BY SERVER !");
			return false;
		}
		if (amount > 0 && itemID == (c.playerItems[fromSlot] - 1)) {
			if (amount > c.playerItemsN[fromSlot]) {
				amount = c.playerItemsN[fromSlot];
			}
			boolean IsInTrade = false;
			for (int i = 0; i < c.playerTItems.length; i++) {
				if (c.playerTItems[i] == c.playerItems[fromSlot]) {
					if (Item.itemStackable[(c.playerItems[fromSlot] - 1)] == true || Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == true) {
						c.playerTItemsN[i] += amount;
						PlayerHandler.players[c.tradeWith].playerOTItemsN[i] += amount;
						IsInTrade = true;
						break;
					}
				}
			}
			if (IsInTrade == false) {
				for (int i = 0; i < c.playerTItems.length; i++) {
					if (c.playerTItems[i] <= 0) {
						c.playerTItems[i] = c.playerItems[fromSlot];
						c.playerTItemsN[i] = amount;
						PlayerHandler.players[c.tradeWith].playerOTItems[i] = c.playerItems[fromSlot];
						PlayerHandler.players[c.tradeWith].playerOTItemsN[i] = amount;
						break;
					}
				}
			}
			if (amount == c.playerItemsN[fromSlot]) {
				c.playerItems[fromSlot] = 0;
			}
			c.playerItemsN[fromSlot] -= amount;
			c.getFrameMethodHandler().resetItems(3322);
			c.getFrameMethodHandler().resetTItemsFrame(3415);
			PlayerHandler.players[c.tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[c.tradeWith].tradeStatus == 3) {
				PlayerHandler.players[c.tradeWith].tradeStatus = 2;
				PlayerHandler.players[c.tradeWith].AntiTradeScam = true;
				c.getFrameMethodHandler().sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}





	public boolean sellItem(int itemID, int fromSlot, int amount) {		
		if (amount > 0 && itemID == (c.playerItems[fromSlot] - 1)) {
			if (server.shopHandler.ShopSModifier[c.MyShopID] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= server.shopHandler.ShopItemsStandard[c.MyShopID]; i++) {
					if (itemID == (server.shopHandler.ShopItems[c.MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					c.sendMessage("You cannot sell "+Item.getItemName(itemID)+" in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(c.playerItems[fromSlot] - 1)] == false && c.debugmode == true) {
				c.sendMessage("Call1: I cannot sell "+Item.getItemName(itemID)+".");
				return false;
			}
			if (amount > c.playerItemsN[fromSlot] && (Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == true || Item.itemStackable[(c.playerItems[fromSlot] - 1)] == true)) {
				amount = c.playerItemsN[fromSlot];
			} else if (amount > c.getInventoryHandler().GetXItemsInBag(itemID) && Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == false && Item.itemStackable[(c.playerItems[fromSlot] - 1)] == false) {
				amount = c.getInventoryHandler().GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(Item.GetItemShopValue(itemID, 1.0));
				if (c.getInventoryHandler().freeSlots() >= 0) {
					if (Item.itemIsNote[itemID] == false) {
						c.getInventoryHandler().deleteItem(itemID, c.getInventoryHandler().getItemSlot(itemID), 1);
					} else {
						c.getInventoryHandler().deleteItem(itemID, fromSlot, 1);
					}
					c.getInventoryHandler().addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					c.sendMessage("Inventory is full.");
					break;
				}
			}
			c.getFrameMethodHandler().resetItems(3823);
			resetShop(c.MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return true;
	}

	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (server.shopHandler.ShopItems[c.MyShopID][fromSlot] - 1)) {
			if (amount > server.shopHandler.ShopItemsN[c.MyShopID][fromSlot]) {
				amount = server.shopHandler.ShopItemsN[c.MyShopID][fromSlot];
			}

			int itemPrice = (int)Math.floor(Item.GetItemShopValue(itemID, 1.0));
			int totalItemsPrice = itemPrice*amount;

			if(c.getInventoryHandler().freeSlots() == 0 && !c.getInventoryHandler().playerHasItem(itemID)){
				c.sendMessage("Your inventory is full.");
				return false;
			}				

			if(!c.getInventoryHandler().hasItemOfAtLeastAmount(995,totalItemsPrice)){
				c.sendMessage("You do not have enough coins to do that.");
				return false;
			}

			if(server.shopHandler.ShopItemsN[c.MyShopID][fromSlot] < amount)
				amount = server.shopHandler.ShopItemsN[c.MyShopID][fromSlot];

			if(Item.itemStackable[itemID]){ //add to inventory all at once
				c.getInventoryHandler().deleteItem(995,c.getInventoryHandler().getItemSlot(995),totalItemsPrice);
				c.getInventoryHandler().addItem(itemID, amount);
				server.shopHandler.ShopItemsN[c.MyShopID][fromSlot] -= amount;
				server.shopHandler.ShopItemsDelay[c.MyShopID][fromSlot] = 0;
				if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[c.MyShopID]) {
					server.shopHandler.ShopItems[c.MyShopID][fromSlot] = 0;
				}
			}
			else{ //buy each individually until inventory filled
				for(int i = amount; i > 0; i--){
					if(c.getInventoryHandler().freeSlots() > 0){
						c.getInventoryHandler().deleteItem(995,c.getInventoryHandler().getItemSlot(995),itemPrice);
						c.getInventoryHandler().addItem(itemID);
						server.shopHandler.ShopItemsN[c.MyShopID][fromSlot] -= 1;
						server.shopHandler.ShopItemsDelay[c.MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[c.MyShopID]) {
							server.shopHandler.ShopItems[c.MyShopID][fromSlot] = 0;
						}
					}
					else{
						c.sendMessage("Your inventory is full.");
						break;
					}
				}
			}

			c.getFrameMethodHandler().resetItems(3823);
			resetShop(c.MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return false;
	}


	public boolean ApplyDead() {
		if(server.pestControlHandler.isInPestControl(c)){
			c.IsDead = false;
			c.NewHP = c.getLevelForXP(c.playerXP[c.playerHitpoints]);
			c.teleport(2657,2607);
			c.resetAnimation();
			c.sendMessage("Oh dear, you are dead.");
			c.PRAY.disableAllPrayer();
			return true;
		}
		c.getInventoryHandler().generateKeepItems();
		c.getInventoryHandler().dropAllItemsAndEquipment();
		if(c.keepItem > 0)
			c.getInventoryHandler().addItem(c.keepItem, c.keepItemAmount);
		if(c.keepItem2 > 0)
			c.getInventoryHandler().addItem(c.keepItem2, c.keepItemAmount2);
		if(c.keepItem3 > 0)
			c.getInventoryHandler().addItem(c.keepItem3, c.keepItemAmount3);
		if(c.PRAY.ProtectItem && c.keepItem4 > 0)
			c.getInventoryHandler().addItem(c.keepItem4, c.keepItemAmount4);

		if(c.KillerId != c.playerId && PlayerHandler.players[c.KillerId] != null){
			if(PlayerHandler.players[c.KillerId].combat > c.combat){
				c.lnew = 1;
			}
			else if(PlayerHandler.players[c.KillerId].combat < c.combat){
				c.lnew = 3;
			}
			else if(PlayerHandler.players[c.KillerId].combat == c.combat){
				c.lnew = 2;
			}
			client killerz = (client) server.playerHandler.players[c.KillerId];
			if(killerz != null && PlayerHandler.players[c.KillerId] != null) {
				PlayerHandler.players[c.KillerId].pkpoints += c.lnew;
				PlayerHandler.players[c.KillerId].killcount += 1;
				PlayerHandler.players[c.KillerId].hasset += 1;
				c.otherpkps = PlayerHandler.players[c.KillerId].pkpoints;
				c.otherkillc = PlayerHandler.players[c.KillerId].killcount;
				killerz.sendMessage("You recieve "+c.lnew+" PK point(s), you now have "+c.otherpkps+" PK points.");
				killerz.sendMessage("You now have a total of "+c.otherkillc+" player kills.");
				c.deathcount += 1;
			}
		}


		c.actionTimer = 0;
		c.disconnected = false;
		c.getFrameMethodHandler().DeadStats();

		c.bandit = 0;
		c.frozenTimer = 0;
		c.sendMessage("Oh dear, you are dead.");
		c.hitDiff = 0;	
		c.deadAnimTimer = -1;
		c.getCombatHandler().ResetAttack();
		c.getCombatHandler().ResetAttackNPC();
		c.PRAY.disableAllPrayer();
		c.NewHP = c.getLevelForXP(c.playerXP[3]);
		c.getFrameMethodHandler().setSkillLevel(3, c.getLevelForXP(c.playerXP[3]), c.playerXP[c.playerHitpoints]);
		c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
		c.getFrameMethodHandler().refreshSkills();
		c.KillerId = c.playerId;
		resetKeepItem();
		c.teleport(3156,3735);
		c.resetAnimation();
		c.IsDead = false;
		return true;
	}


	private void resetKeepItem() {
		c.keepItem = 0;
		c.keepItem2 = 0;
		c.keepItem3 = 0;
		c.keepItem4 = 0;
		c.keepItemAmount = 0;
		c.keepItemAmount2 = 0;
		c.keepItemAmount3 = 0;
		c.keepItemAmount4 = 0;
	}


	public int GetNPCIDAtCoords(int coordX, int coordY) {
		for (int i = 0; i < server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (server.npcHandler.npcs[i].absX == coordX && server.npcHandler.npcs[i].absY == coordY) {
					return server.npcHandler.npcs[i].npcType;
				}
			}
		}
		return 1;
	}




	/**
	 * Relies on global variables npcID and npcName being up to date
	 */
	public void npcChat(){

		String[] lines = new String[4];
		int i = 0;		
		if(c.npcLines.size() < 3){ //readjust the spacing
			lines[0] = "";
			i = 1;
		}

		while(i < 4){
			lines[i] = c.npcLines.poll();
			++i;
		}

		c.getFrameMethodHandler().sendFrame200(4901, 591);

		c.getFrameMethodHandler().sendFrame126(c.npcName, 4902);

		c.getFrameMethodHandler().sendFrame126(lines[0], 4903);

		c.getFrameMethodHandler().sendFrame126(lines[1], 4904);

		c.getFrameMethodHandler().sendFrame126(lines[2], 4905);

		c.getFrameMethodHandler().sendFrame126(lines[3], 4906);

		c.getFrameMethodHandler().sendFrame126("Click here to continue", 4907);

		c.getFrameMethodHandler().sendFrame75(c.npcID, 4901);

		c.getFrameMethodHandler().sendFrame164(4900);
	}


	public int GetWorld(int PlayerID) {
		try{
			/*String Server = PlayerHandler.players[PlayerID].playerServer;
		if (Server.equals("DeepHaven.no-ip.info")) {
			return 1;
		} else if (Server.equals("rs2.servegame.org")) {
			return 2;
		} else {
			//println_debug("Invalid Server: "+Server);
			return -5;
		}*/ 
			return 1;
		}
		catch(Exception e){
			System.out.println("Getworld error");
			return 1;
		}

	}
	

	public void UpdatePlayerShop() {
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].IsShopping == true && PlayerHandler.players[i].MyShopID == c.MyShopID && i != c.playerId) {
					PlayerHandler.players[i].UpdateShop = true;
				}
			}
		}
	}

	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = Item.GetUnnotedItem(itemID);
		}
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if ((server.shopHandler.ShopItems[c.MyShopID][i] - 1) == itemID) {
				server.shopHandler.ShopItemsN[c.MyShopID][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
				if (server.shopHandler.ShopItems[c.MyShopID][i] == 0) {
					server.shopHandler.ShopItems[c.MyShopID][i] = (itemID + 1);
					server.shopHandler.ShopItemsN[c.MyShopID][i] = amount;
					server.shopHandler.ShopItemsDelay[c.MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

		//		if(isInArea(2111,4893,2160,4931) && !isInArea(2128,4904,2137,4910)) //old pk zone - draynor 
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
	public void isteleporting3(int anim, int time, int x, int y, int h){
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
		c.npcChat();
	}	

	public void npcdialogue(String name, int id, String ... lines){
		c.npcLines.clear();
		c.npcID = id;
		c.npcName = name;
		for(int i = 0; i < lines.length; i++)
			c.npcLines.add(lines[i]);
		c.npcChat();
	}	


	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		if(exp >= 13034431) return 99;

		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 99;
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
			totalLevel += getLevelForXP(c.playerXP[i]);
		c.getFrameMethodHandler().sendFrame126("Total Lvl: "+totalLevel, 3984);
	}

	public boolean addSkillXP(int amount, int skill){
		c.debug("You recieved "+amount+" exp in Skill "+skill);

		int[] playerXP = c.playerXP;
		int Attack = getLevelForXP(playerXP[0]); 
		int Defence = getLevelForXP(playerXP[1]);      
		int Strength = getLevelForXP(playerXP[2]);
		int Hitpoints = getLevelForXP(playerXP[3]);
		int Ranging = getLevelForXP(playerXP[4]);
		int Prayer = getLevelForXP(playerXP[5]);
		int Magic = getLevelForXP(playerXP[6]);
		int Cooking = getLevelForXP(playerXP[7]);
		int Woodcutting = getLevelForXP(playerXP[8]);
		int Fletching = getLevelForXP(playerXP[9]);
		int Fishing = getLevelForXP(playerXP[10]);
		int Firemaking = getLevelForXP(playerXP[11]);
		int Crafting = getLevelForXP(playerXP[12]);
		int Smithing = getLevelForXP(playerXP[13]);
		int Mining = getLevelForXP(playerXP[14]);
		int Herblore = getLevelForXP(playerXP[15]);
		int Agility = getLevelForXP(playerXP[16]);
		int Thieving = getLevelForXP(playerXP[17]);
		int Slayer = getLevelForXP(playerXP[18]);
		int Farming = getLevelForXP(playerXP[19]);
		int Runecrafting = getLevelForXP(playerXP[20]);
		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 2000000000) {
			c.sendMessage("Max XP value reached");
			return false;
		}

		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;

		if(oldLevel < getLevelForXP(playerXP[skill]))
			levelup(skill);
		c.playerLevel[skill] = getLevelForXP(playerXP[skill]);
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
		c.npcChat();
	}

	/**
	 * Heals player by amount
	 * @return True by default
	 */
	public boolean heal(int amount){
		c.NewHP += amount;
		if(c.NewHP > getLevelForXP(c.playerXP[3])) 
			c.NewHP = getLevelForXP(c.playerXP[3]);
		c.requirePlayerUpdate();
		return true;
	}

	/**
	 * Will present the option to travel to four locations
	 * @param optName Question at top of frame
	 * @param opt1 Option 1
	 * @param x1 Option 1 x coordinate
	 * @param y1 Option 2 y coordinate
	 */
	public void select4Options(String optName, String opt1, int x1, int y1, String opt2, int x2, int y2, String opt3, int x3, int y3, String opt4, int x4, int y4){
		c.optionsMenu = true;		c.oX1 = x1;		c.oX2 = x2;		c.oX3 = x3;		c.oX4 = x4;		c.oY1 = y1;		c.oY2 = y2;		c.oY3 = y3;		c.oY4 = y4;				
		c.getFrameMethodHandler().selectoption2(optName, opt1, opt2, opt3, opt4);		
	}


	/**
	 * Checks the skill with the level
	 * @return true if player has greater than or equal to
	 */
	public boolean checkLevel(int _skill, int _level){
		if(getLevelForXP(c.playerXP[_skill]) >= _level) 
			return true;
		return false;
	}

	public void deadreturn(){
		c.deadtele = 0;
		c.teleport(3024,3206,c.ithl);
		c.isteleporting = 0;
	}

	public boolean isInBarrows() {
		if (isInArea(3522, 9720, 3583, 9675)) return true;
		if (isInArea(3546, 3305, 3583, 3264)) return true;
		return false;
	}
	public void barrowsreset() {
		torag = 0;
		verac = 0;
		guthan = 0;
		ahrim = 0;
		dharok = 0;
		karil = 0;
	}
	public void spawnNPC(int npcID, int _absX, int _absY)
	{
		server.npcHandler.newNPC(npcID, _absX, _absY, heightLevel, _absX, _absY, _absX, _absY, 1, server.npcHandler.getHP(npcID), false);
	}



	public void placeGlobalObject(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		for (Player p : server.playerHandler.players) {
			if(p != null) {
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")) {
					if(person.distanceToPoint(objectX, objectY) <= 60) {
						person.placeObject(objectX, objectY, NewObjectID, Face, ObjectType);
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
						person.animationFrameAtGroundHeight(id, Y, X);
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
						person.animationFrameAtMiddleHeight(id, Y, X);
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
						person.ReplaceObject2(objectX, objectY, NewObjectID, Face, ObjectType);
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
		sendMessage("hour: "+hour24+" mins: "+min+" secs: "+sec);
	}

	public boolean isInMultiCombat(){
		if(isInGodWars())
			return true;
		return false;
	}

	public boolean isInGodWars(){
		return (isInArea(2576,3586,2821,3717) || isInArea(2773,3553,2935,3653));
	}


	public void checkWildRange(int pcombat){
		if(((combat + WildyLevel >= pcombat) && (pcombat >= combat)) || ((combat - WildyLevel <= pcombat) && (pcombat <= combat)))
			inWildRange = true;
		else inWildRange = true; //default false
	}


	public void ReportAbuse(String report, int rule, int mute)
	{
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("logs/chatlogs.txt", true));
			bw.write("[---"+report+" reported by "+playerName+"---]");
			bw.newLine();
			if(mute == 1) {
				bw.write("[---"+report+" was muted by "+playerName+"---]");
				bw.newLine();
			}
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs//chatlogs.txt", true));
			bw.write("[---"+report+" reported by "+playerName+"---]");
			bw.newLine();
			if(mute == 1) {
				bw.write("[---"+report+" was muted by "+playerName+"---]");
				bw.newLine();
			}
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("logs/mouselogs.txt", true));
			bw.write("[---"+report+" reported by "+playerName+" for macroing---]");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs/mouselogs.txt", true));
			bw.write("[---"+report+" reported by "+playerName+" for macroing---]");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("logs/reported.txt", true));
			bw.write(report+" reported by "+playerName+" for breaking rule no. "+rule);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error reporting user.");
			}
		}

		try {
			bw = new BufferedWriter(new FileWriter("./logs/reported.txt", true));
			bw.write(report+" reported by "+playerName+" for breaking rule no. "+rule);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error reporting user.");
			}
		}
	}



	public void ancientsfinished(){
		addSkillXP(100000, 17);
		deleteItem(2372,getItemSlot(2372),1);
		Menu(this.menuHandler.ancientsfinished());
	}

	public void ancients2finished(){
		addSkillXP(100000, 6);
		Menu(this.menuHandler.ancients2finished());
	}


	public boolean isBanned(String host){
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("data/bannedusers.dat"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (host.equalsIgnoreCase(data))
				{
					return true;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned");
			e.printStackTrace();
		}
		return false;
	}

	public void appendToBanned (String player) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("data/bannedusers.txt", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error banning user!");
			}
		}

	}

	public void appendToBannedIps (String playerLastConnect) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("data/bannedips.txt", true));
			bw.write(playerLastConnect);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error banning user ip!");
			}
		}

	}


	public void appendToMacroWarn (String player) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("data/macrowarn.txt", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				sendMessage("Error giving warning!");
			}
		}

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
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(3900);
		outStream.writeWord(TotalItems);
		int TotalCount = 0;
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0 || i <= server.shopHandler.ShopItemsStandard[ShopID]) {
				if (server.shopHandler.ShopItemsN[ShopID][i] > 254) {
					outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
					outStream.writeDWord_v2(server.shopHandler.ShopItemsN[ShopID][i]);	// and then the real value with writeDWord_v2
				} else {
					outStream.writeByte(server.shopHandler.ShopItemsN[ShopID][i]);
				}
				if (server.shopHandler.ShopItems[ShopID][i] > 17000 || server.shopHandler.ShopItems[ShopID][i] < 0) {
					server.shopHandler.ShopItems[ShopID][i] = 17000;
				}
				outStream.writeWordBigEndianA(server.shopHandler.ShopItems[ShopID][i]); //item id
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		outStream.endFrameVarSizeWord();
	}

	//	bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
	//	bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);

	public void rearrangeBank() { //credits : http://www.rune-server.org/runescape-development/rs2-server/tutorials/53595-bank-rearrange-tut.html
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] != 0) { 
				totalItems ++;
				if (highestSlot <= i) highestSlot = i;
			} }  

		for (int i = 0; i <= highestSlot; i++) {
			if (bankItems[i] == 0) {
				boolean stop = false;

				for (int k = i; k <= highestSlot; k++) {
					if (bankItems[k] != 0 && !stop) {
						int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							bankItems[j-spots] = bankItems[j];
							bankItemsN[j-spots] = bankItemsN[j];
							stop = true;
							bankItems[j] = 0; bankItemsN[j] = 0; 
						}
					}
				}					
			}
		}

		int totalItemsAfter = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] != 0) { totalItemsAfter ++; } }

		if (totalItems != totalItemsAfter) outStream.createFrame(109); //disconnects when duping

	}	



	public void sortBank(){
		int[] tempArray = new int[bankItems.length];
		int[] tempArrayN = new int[bankItems.length];
		int index = 0;
		for(int i = 0; i < bankItems.length ;i++){
			if(bankItemsN[i] > 0){
				tempArray[index] = bankItems[i];
				tempArrayN[index] = bankItemsN[i];
				index += 1;
			}

		}
		bankItems = tempArray;
		bankItemsN = tempArrayN;
	}


	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = playerItems[from];
			tempN = playerItemsN[from];

			playerItems[from] = playerItems[to];
			playerItemsN[from] = playerItemsN[to];
			playerItems[to] = tempI;
			playerItemsN[to] = tempN;
		}

		if (moveWindow == 34453) { //Credits to http://www.rune-server.org/runescape-development/rs2-server/tutorials/54022-tut-full-bank-rearrange-refilling-swapping-inserting.html
			if (bankRearrange == "insert") {
				from -= 65280;
				if (to < 0) { int temp = 128 - (to*-1);
				to = 128 + temp; }
			}
			if (bankRearrange == "swap") {
				from = from;
				if (to < 0) { int temp = 128 - (to*-1);
				to = 128 + temp; }
			}
			if (from >= 0 && to >= 0 && from < playerBankSize && to < playerBankSize) {	

				if (bankRearrange != "insert") {
					int tempI = bankItems[from];
					int tempN = bankItemsN[from];

					bankItems[from] = bankItems[to];
					bankItemsN[from] = bankItemsN[to];
					bankItems[to] = tempI;
					bankItemsN[to] = tempN;
				}
				if (bankRearrange == "insert") {
					int tempItemFrom = bankItems[from];
					int tempNFrom = bankItemsN[from];
					int tempItemTo = bankItems[to];
					int tempNTo = bankItemsN[to];
					boolean gotSlot = false;


					int totalItems = 0;
					int highestSlot = 0;

					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] != 0) { 
							totalItems ++;
							if (highestSlot <= i) highestSlot = i;
						} }  

					bankItems[from] = 0;
					bankItemsN[from] = 0;

					if (from > to) {
						for (int i = from; i <= from && i >= to; i--) {
							if (i != to) {
								bankItems[i] = bankItems[i - 1];
								bankItemsN[i] = bankItemsN[i - 1];
							}

							if (i == to) {
								bankItems[i] = tempItemFrom;
								bankItemsN[i] = tempNFrom;
							}
						}
					}

					if (from < to) {
						for (int i = from; i >= from && i <= to; i++) {
							if (i != to) {
								bankItems[i] = bankItems[i + 1];
								bankItemsN[i] = bankItemsN[i + 1];
							}

							if (i == to) {
								bankItems[i] = tempItemFrom;
								bankItemsN[i] = tempNFrom;
							}
						}
					}

					int totalItemsAfter = 0;
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] != 0) { totalItemsAfter ++; } }

					if (totalItems != totalItemsAfter) outStream.createFrame(109); //disconnects when duping. Just to be sure

				} resetBankFrame();
			}
		}

		else if (moveWindow == 18579) {
			resetItems(5064);
		} else if (moveWindow == 3724) {
			resetItems(3214);
		}
	}


	public int getFreeBankSlots() {
		int freeS = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}


	public void ReplaceAllItemWithNewItemInBankAndInventory(int oldID, int newID) {

		for(int i = 0; i < playerBankSize; i++)
		{
			if(bankItems[i] == oldID+1)
			{
				int newamount2 = bankItemsN[i];
				bankItems[i] = newID+1;
				bankItemsN[i] = newamount2;
			}
		}
		for(int i2 = 0; i2 < playerItems.length; i2++)
		{
			if(playerItems[i2] == oldID+1)
			{
				int newamount = playerItemsN[i2];
				deleteItem(oldID, getItemSlot(oldID), playerItemsN[i2]);
				addItem(newID, newamount);
			}
		}

	}


	public void AcceptTrade() {
		sendFrame248(3323, 3321); //trading window + bag
		resetItems(3322);
		resetTItemsFrame(3415);
		resetOTItemsFrame(3416);
		sendFrame126("Trading With: "+PlayerHandler.players[tradeWith].playerName, 3417);
		sendFrame126("", 3431);
	}
	public void DeclineTrade() {
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				fromTrade((playerTItems[i] - 1), i, playerTItemsN[i]);
			}
		}
		resetItems(3214);
		resetTrade();
	}
	public void resetTrade() {
		tradeWith = 0;
		tradeWaitingTime = 0;
		tradeStatus = 0;
		tradeUpdateOther = false;
		tradeOtherDeclined = false;
		WanneTrade = 0;
		WanneTradeWith = 0;
		TradeConfirmed = false;
		for (int i = 0; i < playerTItems.length; i++) {
			playerTItems[i] = 0;
			playerTItemsN[i] = 0;
			playerOTItems[i] = 0;
			playerOTItemsN[i] = 0;
		}
	}
	public void ConfirmTrade() {
		if (TradeConfirmed == false) {
			RemoveAllWindows();
			for (int i = 0; i < playerOTItems.length; i++) {
				if (playerOTItems[i] > 0) {
					addItem((playerOTItems[i] - 1), playerOTItemsN[i]);

				}
			}
			resetItems(3214);
			TradeConfirmed = true;
		}
	}
	public void TradeGoConfirm() {
		sendFrame248(3443, 3213); //trade confirm + normal bag
		resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				if (playerTItemsN[i] >= 1000 && playerTItemsN[i] < 1000000) {
					SendAmount = "" + (playerTItemsN[i] / 1000) + "K (" + playerTItemsN[i] + ")";
				} else if (playerTItemsN[i] >= 1000000) {
					SendAmount = "" + (playerTItemsN[i] / 1000000) + " million (" + playerTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = getItemName((playerTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + getItemName((playerTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerTItems[i] - 1)] == true || Item.itemStackable[(playerTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItems[i] > 0) {
				if (playerOTItemsN[i] >= 1000 && playerOTItemsN[i] < 1000000) {
					SendAmount = "" + (playerOTItemsN[i] / 1000) + "K (" + playerOTItemsN[i] + ")";
				} else if (playerOTItemsN[i] >= 1000000) {
					SendAmount = "" + (playerOTItemsN[i] / 1000000) + " million (" + playerOTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerOTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = getItemName((playerOTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + getItemName((playerOTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerOTItems[i] - 1)] == true || Item.itemStackable[(playerOTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3558);
	}
	public boolean fromTrade(int itemID, int fromSlot, int amount) {
		if (amount > 0 && (itemID + 1) == playerTItems[fromSlot]) {
			if (amount > playerTItemsN[fromSlot]) {
				amount = playerTItemsN[fromSlot];
			}
			addItem((playerTItems[fromSlot] - 1), amount);
			if (amount == playerTItemsN[fromSlot]) {
				playerTItems[fromSlot] = 0;
				PlayerHandler.players[tradeWith].playerOTItems[fromSlot] = 0;
			}
			playerTItemsN[fromSlot] -= amount;
			PlayerHandler.players[tradeWith].playerOTItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItemsFrame(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}
	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		if (tradeWith > 0) {
			if (PlayerHandler.players[tradeWith] == null) {
				DeclineTrade();
				sendMessage("FORCED DECLINE BY SERVER !");
				return false;
			}
		} else {
			DeclineTrade();
			sendMessage("FORCED DECLINE BY SERVER !");
			return false;
		}
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			boolean IsInTrade = false;
			for (int i = 0; i < playerTItems.length; i++) {
				if (playerTItems[i] == playerItems[fromSlot]) {
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true || Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) {
						playerTItemsN[i] += amount;
						PlayerHandler.players[tradeWith].playerOTItemsN[i] += amount;
						IsInTrade = true;
						break;
					}
				}
			}
			if (IsInTrade == false) {
				for (int i = 0; i < playerTItems.length; i++) {
					if (playerTItems[i] <= 0) {
						playerTItems[i] = playerItems[fromSlot];
						playerTItemsN[i] = amount;
						PlayerHandler.players[tradeWith].playerOTItems[i] = playerItems[fromSlot];
						PlayerHandler.players[tradeWith].playerOTItemsN[i] = amount;
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItemsFrame(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	public boolean writeReport(String receivedPlayerName,int rule){

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("reports/"+receivedPlayerName+".txt", true));
			bw.write(playerName+" Has reported "+receivedPlayerName);
			bw.newLine();
			bw.write("What Rule = "+rule);
			bw.newLine();
			bw.write("Rules: 0-Lang 1-Scam 2-Hack 3-Imperson 4-PWord 5-Mass 6-NPC");
			bw.newLine();
			bw.newLine();
			bw.write("====================");
			bw.newLine();
			bw.newLine();
			bw.flush();
			sendMessage("Thank-You!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) try {
				bw.close();
			} catch (IOException ioe2) {
				c.sendMessage("It didn't work, try again !");
			}
		}
	}




	public boolean sellItem(int itemID, int fromSlot, int amount) {		
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= server.shopHandler.ShopItemsStandard[MyShopID]; i++) {
					if (itemID == (server.shopHandler.ShopItems[MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					sendMessage("You cannot sell "+getItemName(itemID)+" in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(playerItems[fromSlot] - 1)] == false && debugmode == true) {
				sendMessage("Call1: I cannot sell "+getItemName(itemID)+".");
				return false;
			}
			if (amount > playerItemsN[fromSlot] && (Item.itemIsNote[(playerItems[fromSlot] - 1)] == true || Item.itemStackable[(playerItems[fromSlot] - 1)] == true)) {
				amount = playerItemsN[fromSlot];
			} else if (amount > GetXItemsInBag(itemID) && Item.itemIsNote[(playerItems[fromSlot] - 1)] == false && Item.itemStackable[(playerItems[fromSlot] - 1)] == false) {
				amount = GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(GetItemShopValue(itemID, 1, fromSlot));
				if (freeSlots() >= 0) {
					if (Item.itemIsNote[itemID] == false) {
						deleteItem(itemID, GetItemSlot(itemID), 1);
					} else {
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					sendMessage("Inventory is full.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return true;
	}

	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (server.shopHandler.ShopItems[MyShopID][fromSlot] - 1)) {
			if (amount > server.shopHandler.ShopItemsN[MyShopID][fromSlot]) {
				amount = server.shopHandler.ShopItemsN[MyShopID][fromSlot];
			}

			int itemPrice = (int)Math.floor(GetItemShopValue(itemID, 0, fromSlot));
			int totalItemsPrice = itemPrice*amount;

			if(freeSlots() == 0 && !playerHasItem(itemID)){
				sendMessage("Your inventory is full.");
				return false;
			}				

			if(!hasItemOfAtLeastAmount(995,totalItemsPrice)){
				sendMessage("You do not have enough coins to do that.");
				return false;
			}

			if(server.shopHandler.ShopItemsN[MyShopID][fromSlot] < amount)
				amount = server.shopHandler.ShopItemsN[MyShopID][fromSlot];

			if(Item.itemStackable[itemID]){ //add to inventory all at once
				deleteItem(995,GetItemSlot(995),totalItemsPrice);
				addItem(itemID, amount);
				server.shopHandler.ShopItemsN[MyShopID][fromSlot] -= amount;
				server.shopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
				if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[MyShopID]) {
					server.shopHandler.ShopItems[MyShopID][fromSlot] = 0;
				}
			}
			else{ //buy each individually until inventory filled
				for(int i = amount; i > 0; i--){
					if(freeSlots() > 0){
						deleteItem(995,GetItemSlot(995),itemPrice);
						addItem(itemID);
						server.shopHandler.ShopItemsN[MyShopID][fromSlot] -= 1;
						server.shopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[MyShopID]) {
							server.shopHandler.ShopItems[MyShopID][fromSlot] = 0;
						}
					}
					else{
						sendMessage("Your inventory is full.");
						break;
					}
				}
			}

			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return false;
	}


	public boolean ApplyDead() {
		if(server.pestControlHandler.isInPestControl(this)){
			IsDead = false;
			NewHP = getLevelForXP(playerXP[playerHitpoints]);
			teleport(2657,2607);
			resetAnimation();
			sendMessage("Oh dear, you are dead.");
			this.PRAY.disableAllPrayer();
			return true;
		}
		generateKeepItems();
		dropAllItemsAndEquipment();
		if(keepItem > 0)
			addItem(keepItem, keepItemAmount);
		if(keepItem2 > 0)
			addItem(keepItem2, keepItemAmount2);
		if(keepItem3 > 0)
			addItem(keepItem3, keepItemAmount3);
		if(this.PRAY.ProtectItem && keepItem4 > 0)
			addItem(keepItem4, keepItemAmount4);

		if(KillerId != playerId && PlayerHandler.players[KillerId] != null){
			if(PlayerHandler.players[KillerId].combat > combat){
				lnew = 1;
			}
			else if(PlayerHandler.players[KillerId].combat < combat){
				lnew = 3;
			}
			else if(PlayerHandler.players[KillerId].combat == combat){
				lnew = 2;
			}
			client killerz = (client) server.playerHandler.players[KillerId];
			if(killerz != null && PlayerHandler.players[KillerId] != null) {
				PlayerHandler.players[KillerId].pkpoints += lnew;
				PlayerHandler.players[KillerId].killcount += 1;
				PlayerHandler.players[KillerId].hasset += 1;
				otherpkps = PlayerHandler.players[KillerId].pkpoints;
				otherkillc = PlayerHandler.players[KillerId].killcount;
				killerz.sendMessage("You recieve "+lnew+" PK point(s), you now have "+otherpkps+" PK points.");
				killerz.sendMessage("You now have a total of "+otherkillc+" player kills.");
				deathcount += 1;
			}
		}


		actionTimer = 0;
		disconnected = false;
		DeadStats();

		bandit = 0;
		frozenTimer = 0;
		sendMessage("Oh dear, you are dead.");
		hitDiff = 0;	
		deadAnimTimer = -1;
		ResetAttack();
		ResetAttackNPC();
		this.PRAY.disableAllPrayer();
		NewHP = getLevelForXP(playerXP[3]);
		setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
		playerLevel[3] = getLevelForXP(playerXP[3]);
		refreshSkills();
		KillerId = playerId;
		resetKeepItem();
		teleport(3156,3735);
		resetAnimation();
		IsDead = false;
		return true;
	}


	private void resetKeepItem() {
		keepItem = 0;
		keepItem2 = 0;
		keepItem3 = 0;
		keepItem4 = 0;
		keepItemAmount = 0;
		keepItemAmount2 = 0;
		keepItemAmount3 = 0;
		keepItemAmount4 = 0;
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


	public void ResetBonus() {
		for (int i = 0; i < playerBonus.length; i++) {
			playerBonus[i] = 0;
		}
	}
	public void GetBonus() {
		for (int i = 0; i < playerEquipment.length; i++) {
			if (playerEquipment[i] > -1) {
				if (server.itemHandler.ItemList.exists(playerEquipment[i])){
					for (int k = 0; k < playerBonus.length; k++){ 
						try{
							playerBonus[k] += server.itemHandler.ItemList.getCurrentItem().Bonuses[k];
						}
						catch(Exception e){
							println("Exception in GetBonus for itemID "+playerEquipment[i]);
						}
					}
				}
			}
		}
	}


	/**
	 * Relies on global variables npcID and npcName being up to date
	 */
	public void npcChat(){

		String[] lines = new String[4];
		int i = 0;		
		if(npcLines.size() < 3){ //readjust the spacing
			lines[0] = "";
			i = 1;
		}

		while(i < 4){
			lines[i] = npcLines.poll();
			++i;
		}

		sendFrame200(4901, 591);

		sendFrame126(npcName, 4902);

		sendFrame126(lines[0], 4903);

		sendFrame126(lines[1], 4904);

		sendFrame126(lines[2], 4905);

		sendFrame126(lines[3], 4906);

		sendFrame126("Click here to continue", 4907);

		sendFrame75(npcID, 4901);

		sendFrame164(4900);
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
			debug(e.toString());
			return 1;
		}

	}
	

	public void UpdatePlayerShop() {
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].IsShopping == true && PlayerHandler.players[i].MyShopID == MyShopID && i != playerId) {
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
			if ((server.shopHandler.ShopItems[MyShopID][i] - 1) == itemID) {
				server.shopHandler.ShopItemsN[MyShopID][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
				if (server.shopHandler.ShopItems[MyShopID][i] == 0) {
					server.shopHandler.ShopItems[MyShopID][i] = (itemID + 1);
					server.shopHandler.ShopItemsN[MyShopID][i] = amount;
					server.shopHandler.ShopItemsDelay[MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}

}


public class ItemUse {

	private client c = null;

	public ItemUse(client pc){
		this.c = pc;
	}

	
	public boolean useItemOnPlayer(int itemID, int slot, int playerID){
		client playerThatItemWasUsedOn = (client) server.playerHandler.players[playerID];
		switch(itemID){
		case 962:
			c.getInventoryHandler().deleteItem(962, slot, 1);
			c.sendMessage("You crack the cracker...");
			c.getInventoryHandler().addItem(playerThatItemWasUsedOn.DROPHANDLER.getDrop(playerThatItemWasUsedOn.DROPHANDLER.RARES), 1);
			playerThatItemWasUsedOn.sendMessage("Someone cracked a cracker on you.");
			break;
		}
		return true;
	}

	
	
	
	/**
	 * Checks spamtimer to current system millis
	 * @return true if method was called within 2000 MS
	 */
	public boolean isItemSpamming(){
		if (System.currentTimeMillis() - itemTimer < 750)
			return true;
		return false;
	}

	private long itemTimer;
	
	public boolean checkItemUse(int Item, int Slot) {

		if(isItemSpamming()) return false;
		itemTimer = System.currentTimeMillis();

		if(lists.bones.exists(Item))
			return c.getSkillHandler().getPrayerHandler().buryBones(Item, Slot);

		if(lists.food.exists(Item))
			return c.getFoodHandler().eat(Item,Slot);

		switch (Item) {

		case 2520:
			c.startAnimation(918);
			c.txt4 = "Come on Swifty, we can win the race!";
			c.string4UpdateRequired = true;
			break;

		case 2522:
			c.startAnimation(919);
			c.txt4 = "Come on Alex, we can win the race!";
			c.string4UpdateRequired = true;
			break;

		case 2524:
			c.startAnimation(920);
			c.txt4 = "Come on Vegeta, we can win the race!";
			c.string4UpdateRequired = true;
			break;

		case 2526:
			c.startAnimation(921);
			c.txt4 = "Come on MrWicked, we can win the race!";
			c.string4UpdateRequired = true;
			break;

		case 952://spade

			if (c.isInArea(3553, 3294, 3561, 3301))  // verac
				c.getClientMethodHandler().teleportWithAnimation(2843, 14, 3578,9706,3);
			else if (c.isInArea(3550, 3278, 3557, 3287))  // torag
				c.getClientMethodHandler().teleportWithAnimation(2843, 14, 3568,9683,3);
			else if (c.isInArea(3561, 3285, 3568, 3292))  // ahrim
				c.getClientMethodHandler().teleportWithAnimation(2843, 14, 3557,9703,3);
			else if (c.isInArea(3570, 3293, 3579, 3302))  // dharok
				c.getClientMethodHandler().teleportWithAnimation(2843, 14, 3556,9718,3);
			else if (c.isInArea(3571, 3278, 3582, 3285))  // guthan
				c.getClientMethodHandler().teleportWithAnimation(2843, 14, 3534,9704,3);
			else if (c.isInArea(3562, 3273, 3569, 3279))  // karil
				c.getClientMethodHandler().teleportWithAnimation(2843, 14, 3546,9684,3);
			else{
				c.startAnimation(2843);
				c.sendMessage("You dig and find nothing.");
			}

			break;

		case 3014:
			c.runningEnergy += 10;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3014, c.getInventoryHandler().GetItemSlot(3014), 1);
			c.getInventoryHandler().addItem(229, 1);
			break;
		case 3012:
			c.runningEnergy += 10;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3012, c.getInventoryHandler().GetItemSlot(3012), 1);
			c.getInventoryHandler().addItem(3014, 1);
			break;
		case 3010:
			c.runningEnergy += 10;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3010, c.getInventoryHandler().GetItemSlot(3010), 1);
			c.getInventoryHandler().addItem(3012, 1);
			break;
		case 3008:
			c.runningEnergy += 10;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3008, c.getInventoryHandler().GetItemSlot(3008), 1);
			c.getInventoryHandler().addItem(3010, 1);
			break;
		case 3022:
			c.runningEnergy += 20;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3022, c.getInventoryHandler().GetItemSlot(3022), 1);
			c.getInventoryHandler().addItem(229, 1);
			break;
		case 3020:
			c.runningEnergy += 20;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3020, c.getInventoryHandler().GetItemSlot(3020), 1);
			c.getInventoryHandler().addItem(3022, 1);
			break;
		case 3018:
			c.runningEnergy += 20;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3018, c.getInventoryHandler().GetItemSlot(3018), 1);
			c.getInventoryHandler().addItem(3020, 1);
			break;
		case 3016:
			c.runningEnergy += 20;
			c.startAnimation(829);
			c.getInventoryHandler().deleteItem(3016, c.getInventoryHandler().GetItemSlot(3016), 1);
			c.getInventoryHandler().addItem(3018, 1);
			break;
		case 161: // super str (1)
			return pot(c.playerStrength, Item, 229,Slot);

		case 159:
			return pot(2, Item, 161,Slot);

		case 157:
			return pot(2, Item, 159,Slot);

		case 2440:
			return pot(2, Item, 157,Slot);

		case 113:
			return pot(2, Item, 115,Slot);

		case 115:
			return pot(2, Item, 117,Slot);

		case 117:
			return pot(2, Item, 119,Slot);

		case 119:
			return pot(2, Item, 229,Slot);

		case 2446: //Antipoison(4)
			c.startAnimation(829);
			c.Poisoned = false;
			c.sendMessage("You drink a dose of the antipoison.");
			c.getInventoryHandler().deleteItem(2446, c.getInventoryHandler().GetItemSlot(2446), 1);
			c.getInventoryHandler().addItem(175, 1);
			c.PoisonDelay = 9999999;
			break;
		case 175: //Antipoison(3)
			c.startAnimation(829);
			c.Poisoned = false;
			c.sendMessage("You drink a dose of the antipoison.");
			c.getInventoryHandler().deleteItem(175, c.getInventoryHandler().GetItemSlot(175), 1);
			c.getInventoryHandler().addItem(177, 1);
			c.PoisonDelay = 9999999;
			break;
		case 177: //Antipoison(2)
			c.startAnimation(829);
			c.Poisoned = false;
			c.sendMessage("You drink a dose of the antipoison.");
			c.getInventoryHandler().deleteItem(177, c.getInventoryHandler().GetItemSlot(177), 1);
			c.getInventoryHandler().addItem(179, 1);
			c.PoisonDelay = 9999999;
			break;
		case 179: //Antipoison(1)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 100;
			c.sendMessage("You drink the last dose of the super anti-poison.");
			c.getInventoryHandler().deleteItem(179, c.getInventoryHandler().GetItemSlot(179), 1);
			c.getInventoryHandler().addItem(229, 1);
			c.PoisonDelay = 9999999;
			break;

		case 4568: //Witchs Brew Potion
			c.getFrameMethodHandler().Menu(c.getMenuHandler().ancientsbook());
			break;

		case 550: //newcomer map  by me ?
			c.getFrameMethodHandler().showInterface(5392);
			c.sendMessage("You Un-roll The Map And It Shows The South-east Of Deep Haven");
			c.sendMessage("Your Co-ordinates are: X: "+c.absX+" Y: "+c.absY); 
			c.getFrameMethodHandler().sendQuest("Deep Haven", 5394);
			break;
		case 1856: //Guide Book
			c.getFrameMethodHandler().sendQuest("Deep Haven Guidebook", 903);
			c.getFrameMethodHandler().sendQuest("Welcome to Deep Haven!", 843);
			c.getFrameMethodHandler().sendQuest("I suggest training on", 844);
			c.getFrameMethodHandler().sendQuest("Monsters for a good amount", 845);
			c.getFrameMethodHandler().sendQuest("of exp, they are found in", 846);
			c.getFrameMethodHandler().sendQuest("almost every portal!",847);
			c.getFrameMethodHandler().sendQuest("Magic requires no runes!", 849);
			c.getFrameMethodHandler().sendQuest("To make money, either", 850);
			c.getFrameMethodHandler().sendQuest("kill monsters or", 851);
			c.getFrameMethodHandler().sendQuest("get your skills up", 852);
			c.getFrameMethodHandler().sendQuest("and sell the things", 853);
			c.getFrameMethodHandler().sendQuest("you make from it!", 854);
			c.getFrameMethodHandler().sendQuest("", 855);
			c.getFrameMethodHandler().sendQuest("", 856);
			c.getFrameMethodHandler().sendQuest("",857);
			c.getFrameMethodHandler().sendQuest("",858);
			c.getFrameMethodHandler().sendQuest("  ¤¤¤¤",859);	
			c.getFrameMethodHandler().sendQuest("Thanks to rune-server.org!",860);
			c.getFrameMethodHandler().sendQuest("for many tutorials I had",861);
			c.getFrameMethodHandler().sendQuest("found there!",862);
			c.getFrameMethodHandler().sendQuest(" ¥¥¥¥",863);
			c.getFrameMethodHandler().sendQuest("Thanks for playing!",864);
			c.getFrameMethodHandler().showInterface(837);
			c.flushOutStream();
			break;
		case 2448: //superAntipoison(4)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 100;
			c.sendMessage("You drink a dose of the super anti-poison.");
			c.getInventoryHandler().deleteItem(2448, c.getInventoryHandler().GetItemSlot(2448), 1);
			c.getInventoryHandler().addItem(181, 1);
			break;
		case 181: //superAntipoison(3)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 100;
			c.sendMessage("You drink a dose of the super anti-poison.");
			c.getInventoryHandler().deleteItem(181, c.getInventoryHandler().GetItemSlot(181), 1);
			c.getInventoryHandler().addItem(183, 1);
			break;
		case 183: //superAntipoison(2)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 100;
			c.sendMessage("You drink a dose of the super anti-poison.");
			c.getInventoryHandler().deleteItem(183, c.getInventoryHandler().GetItemSlot(183), 1);
			c.getInventoryHandler().addItem(184, 1);
			break;
		case 185: //superAntipoison(1)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 100;
			c.sendMessage("You drink the last dose of the super anti-poison.");
			c.getInventoryHandler().deleteItem(185, c.getInventoryHandler().GetItemSlot(185), 1);
			c.getInventoryHandler().addItem(229, 1);
			break;
		case 5943: //extra-strongAntidote(4)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 200;
			c.sendMessage("You drink a dose of the extra strong antidote");
			c.getInventoryHandler().deleteItem(5943, c.getInventoryHandler().GetItemSlot(5943), 1);
			c.getInventoryHandler().addItem(5945, 1);
			break;
		case 5945: //extra-strongAntidote(3)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 200;
			c.sendMessage("You drink a dose of the extra strong antidote");
			c.getInventoryHandler().deleteItem(5945, c.getInventoryHandler().GetItemSlot(5945), 1);
			c.getInventoryHandler().addItem(5945, 1);
			break;
		case 5947: //extra-strongAntidote(2)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 200;
			c.sendMessage("You drink a dose of the extra strong antidote");
			c.getInventoryHandler().deleteItem(5947, c.getInventoryHandler().GetItemSlot(5947), 1);
			c.getInventoryHandler().addItem(5949, 1);
			break;
		case 5949: //extra-strongAntidote(1)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 200;
			c.sendMessage("You drink the last dose of the extra strong antidote");
			c.getInventoryHandler().deleteItem(5949, c.getInventoryHandler().GetItemSlot(5949), 1);
			c.getInventoryHandler().addItem(229, 1);
			break;
		case 5952: //super-strongAntidote(4)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 350;
			c.sendMessage("You drink a dose of the super strong antidote");
			c.getInventoryHandler().deleteItem(5952, c.getInventoryHandler().GetItemSlot(5952), 1);
			c.getInventoryHandler().addItem(5954, 1);
			break;
		case 5954: //super-strongAntidote(3)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 350;
			c.sendMessage("You drink a dose of the super strong antidote");
			c.getInventoryHandler().deleteItem(5954, c.getInventoryHandler().GetItemSlot(5954), 1);
			c.getInventoryHandler().addItem(5956, 1);
			break;
		case 5956: //super-strongAntidote(2)
			c.Poisoned = false;
			c.PoisonDelay2 = 350;
			c.sendMessage("You drink a dose of the super strong antidote");
			c.getInventoryHandler().deleteItem(5956, c.getInventoryHandler().GetItemSlot(5956), 1);
			c.getInventoryHandler().addItem(5958, 1);
			break;
		case 5958: //super-strongAntidote(1)
			c.startAnimation(829);
			c.Poisoned = false;
			c.PoisonDelay2 = 350;
			c.sendMessage("You drink the last dose of the super strong antidote");
			c.getInventoryHandler().deleteItem(5958, c.getInventoryHandler().GetItemSlot(5958), 1);
			c.getInventoryHandler().addItem(229, 1);
			break;
		case 131: // restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink the last dose of the restore potion");
			c.getInventoryHandler().deleteItem(131, c.getInventoryHandler().GetItemSlot(131), 1);
			c.getInventoryHandler().addItem(229, 1);
			break;
		case 129: // restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink a dose of the restore potion");
			c.getInventoryHandler().deleteItem(129, c.getInventoryHandler().GetItemSlot(129), 1);
			c.getInventoryHandler().addItem(131, 1);
			break;
		case 127: // restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink a dose of the restore potion");
			c.getInventoryHandler().deleteItem(127, c.getInventoryHandler().GetItemSlot(127), 1);
			c.getInventoryHandler().addItem(129, 1);
			break;
		case 5520: //Barrows
			c.getFrameMethodHandler().Menu(c.getMenuHandler().BarrowsQuest());
			break;
		case 2430: // restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink a dose of the restore potion");
			c.getInventoryHandler().deleteItem(2430, c.getInventoryHandler().GetItemSlot(2430), 1);
			c.getInventoryHandler().addItem(127, 1);
			break;
		case 3030: // super restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink the last dose of the super restore potion");
			c.getInventoryHandler().deleteItem(3030, c.getInventoryHandler().GetItemSlot(3030), 1);
			c.getInventoryHandler().addItem(229, 1);
			c.superRestore = true;
			break;
		case 3028: // super restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink a dose of the super restore potion");
			c.getInventoryHandler().deleteItem(3028, c.getInventoryHandler().GetItemSlot(3028), 1);
			c.getInventoryHandler().addItem(3030, 1);
			c.superRestore = true;
			break;
		case 3026: // super restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink a dose of the super restore potion");
			c.getInventoryHandler().deleteItem(3026, c.getInventoryHandler().GetItemSlot(3026), 1);
			c.getInventoryHandler().addItem(3028, 1);
			c.superRestore = true;
			break;
		case 3024: // super restore pot
			c.startAnimation(829);
			c.getFrameMethodHandler().restorePot();
			c.sendMessage("You drink a dose of the super restore potion");
			c.getInventoryHandler().deleteItem(3024, c.getInventoryHandler().GetItemSlot(3024), 1);
			c.getInventoryHandler().addItem(3026, 1);
			c.superRestore = true;
			break;
		case 3038: // agil pot (1)
			return pot(16,Item,229,Slot);

		case 3036: // agil pot (2)
			return pot(16,Item,3038,Slot);

		case 3034: // agil pot (3)
			return pot(16,Item,3036,Slot);

		case 3032: // agil pot (4)
			return pot(16,Item,3034,Slot);

		case 143: // pray pot (1)
			return prayerPot(Item,229,Slot);

		case 141: // pray pot (2)
			return prayerPot(Item,143,Slot);


		case 139: // pray pot (3)
			return prayerPot(Item,141,Slot);

		case 2434: // pray pot (4)
			return prayerPot(Item,139,Slot);

		case 167: // super defence pot (1)
			return pot(1,Item,229,Slot);

		case 165: // super defence pot (2)
			return pot(1,Item,167,Slot);

		case 163: // super defence pot (3)
			return pot(1,Item,165,Slot);

		case 2442: // super defence pot (4)
			return superPot(1,Item,163,Slot);

		case 137: // defence pot (1)
			return pot(1,Item,229,Slot);

		case 135: // defence pot (2)
			return pot(1,Item,137,Slot);

		case 133: // defence pot (3)
			return pot(1,Item,135,Slot);

		case 2432: // defence pot (4)
			return pot(1,Item,133,Slot);

		case 3046: // mage pot (1)
			return pot(6,Item,229,Slot);

		case 3044: // mage pot (2)
			return pot(6,Item,3046,Slot);

		case 3042: // mage pot (3)
			return pot(6,Item,3044,Slot);

		case 3040: // mage pot (4)
			return pot(6,Item,3042,Slot);


		case 6199: // Mystery Box
			c.startAnimation(862);
			c.getInventoryHandler().deleteItem(6199, c.getInventoryHandler().GetItemSlot(6199), 1);
			c.getInventoryHandler().addItem(c.DROPHANDLER.getDrop(c.DROPHANDLER.RARES), 1);
			c.sendMessage("You open the box and recieve an item!");
			break;


		case 5761: // Slayer Drink
			c.startAnimation(829);
			c.playerLevel[18] = c.getLevelForXP(c.playerXP[18]);
			c.playerLevel[18] += 8;
			c.getFrameMethodHandler().sendFrame126(""+c.playerLevel[18]+"", 4014);
			c.getInventoryHandler().deleteItem(5761, c.getInventoryHandler().GetItemSlot(5761), 1);
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			return false;


		case 173: // range pot (1)
			return pot(4,Item,229,Slot);

		case 171: // range pot (2)
			return pot(4,Item,173,Slot);

		case 169: // range pot (3)
			return pot(4,Item,171,Slot);

		case 2444: // range pot (4)
			return pot(4,Item,169,Slot);

		case 149: // super attack pot (1)
			return superPot(0,Item,229,Slot);

		case 147: // super attack pot (2)
			return superPot(0,Item,149,Slot);

		case 145: // super attack pot (3)
			return superPot(0,Item,147,Slot);

		case 2436: // super attack pot (4)
			return superPot(0,Item,145,Slot);

		case 125: // attack pot (1)
			return pot(0,Item,229,Slot);

		case 123: // attack pot (2)
			return pot(0,Item,125,Slot);

		case 121: // attack pot (3)
			return pot(0,Item,123,Slot);

		case 2428: // attack pot (4)
			return pot(0,Item,121,Slot);


		case 13303: // Server Token
			c.getFrameMethodHandler().showInterface(12752);
			c.sendMessage("You look at the token, it has a strange glow coming from it.");
			break;

		case 199:
			return identify(3, 30, Item, 249); //identify guam

		case 201:
			return identify(5, 40, Item, (Item+50)); //identify marrentill

		case 203:
			return identify(11, 50, Item, (Item+50)); //identify tarromin

		case 205:
			return identify(20, 60, Item, (Item+50)); //identify harralander

		case 207:
			return identify(25, 70, Item, (Item+50)); //identify ranarr

		case 209:
			return identify(30, 80, Item, 2998); //identify toadflax

		case 211:
			return identify(40, 90, Item, 259); //identify irit

		case 213:
			return identify(48, 100, Item, 261); //identify avantoe

		case 215:
			return identify(54, 110, Item, 263); //identify kwuarm

		case 217:
			return identify(59, 120, Item, 3000); //identify snapdragon

		case 219:
			return identify(65, 130, Item, 265); //identify cadantine

		case 1531:
			return identify(67, 140, Item, 2481); //identify lantadyme

		case 1533:
			return identify(75, 160, Item, 269); //identify tortsol

		case 1529:
			return identify(70, 150, Item, 267); //identify dwarf weed


		case 611:
			c.getFrameMethodHandler().stillgfx(336, c.absY, c.absX);
			c.startAnimation(1333);
			String task = c.SLAYER.getTaskName(c.slayerNPC);
			if(c.slayerCount > 1) task += "s";
			if(c.slayerCount > 0) c.sendMessage("Remaining : "+c.slayerCount+" more "+task+".");
			if(c.slayerCount <= 0 && c.slayerNPC != 0) c.sendMessage("Your current slayer task is complete.");
			if(c.slayerNPC == 0) c.sendMessage("You do not have a current slayer task.");
			break;
			
		case 720:
			c.sendMessage("Appears to be a sketch of a Totem.");
			break;

		default:
			c.sendMessage("Nothing interesting is happening.");
			c.error("Unhandled Item - ItemID: "+Item);
			return false;
		}
		return true;
	}

	public boolean useItemOnObject(int useItemID, int atObjectID, int atObjectY, int atObjectX, int itemSlot, int objectDirection){
		
		if(useItemID == 954 && atObjectID == 3830)
			c.teleport(3507,9494,0);

		if(useItemID == 1779 && atObjectID == 8748){
			c.getFrameMethodHandler().select2Options(35,"Options", "Make all Bowstrings", "Cancel");
			return true;
		}

		if(atObjectID == 2452 || atObjectID == 2459 || atObjectID == 2460){
			c.RUNECRAFTING.craftRunes(atObjectID, useItemID);
			return true;
		}

		//man123
		//server.npcHandler.newNPC(2880, 2603, 3161, heightLevel, 0, 0, 0, 0, 1, server.npcHandler.GetNpcListHP(2880), false);
		if(useItemID == 1462 && atObjectID == 2459)
			c.getClientMethodHandler().teleportWithAnimation(1500, 6, 2142, 4814, 0);


		if(lists.deadPlantList.exists(atObjectID) || lists.brushList.exists(atObjectID)) //using an item on brush/dead brush
			c.getFarmingHandler().guide(atObjectX, atObjectY, atObjectID,objectDirection);
		if(lists.patchList.exists(atObjectID)) //using an item on patch
			c.getFarmingHandler().grow(atObjectX, atObjectY, useItemID, atObjectID,objectDirection);

		if (atObjectID == 2781 || atObjectID == 2785 || atObjectID == 11666 || atObjectID == 3994) //furnace
			c.getSmithingHandler().smithingBarMenuPage1();
		//end of smithing

		//	else if(useItemID == 2357 && atObjectID == 2783)//Gold
		//	{
		//		initSmithing(2357);
		//		c.flushOutStream();
		//	}


		else if(useItemID == 2349 && atObjectID == 2783)//bronze
		{
			c.getFrameMethodHandler().displaySmithingFrame(2349);
			c.flushOutStream();
		}

		else if(useItemID == 2351 && atObjectID == 2783)//iron
		{
			c.getFrameMethodHandler().displaySmithingFrame(2351);
			c.flushOutStream();
		}

		else if(useItemID == 2353 && atObjectID == 2783)//steel

		{
			c.getFrameMethodHandler().displaySmithingFrame(2353);
			c.flushOutStream();
		}

		else if(useItemID == 2359 && atObjectID == 2783)//mith
		{
			c.getFrameMethodHandler().displaySmithingFrame(2359);
			c.flushOutStream();
		}

		else if(useItemID == 2361 && atObjectID == 2783)//addy
		{
			c.getFrameMethodHandler().displaySmithingFrame(2361);
			c.flushOutStream();
		}

		else if(useItemID == 2363 && atObjectID == 2783)//rune
		{
			c.getFrameMethodHandler().displaySmithingFrame(2363);
			c.flushOutStream();
		}

		else if(atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732 || atObjectID == Firemaking.BLUE_FIRE || atObjectID == Firemaking.GREEN_FIRE
				 || atObjectID == Firemaking.RED_FIRE || atObjectID == Firemaking.NORMAL_FIRE){
			c.getSkillHandler().getCookingHandler().itemWasUsedWithCookingDevice(useItemID);
		}
		
		return true;
	}
	


	/**
	 * Will return true when used, is for functionality purposes with itemUseWith method
	 */
	public boolean herblorevoid(int level, int add, int delete1, int delete2){
		if(c.playerLevel[15] >= level) {
			c.getInventoryHandler().deleteItem(delete1, c.getInventoryHandler().getItemSlot(delete1), 1);
			c.getInventoryHandler().addItem(add, 1);
			c.getInventoryHandler().deleteItem(delete2, c.getInventoryHandler().getItemSlot(delete2), 1);
			c.getClientMethodHandler().addSkillXP(c.playerLevel[15]*6*c.rate, 15);
		} else {
			c.sendMessage("A herblore level of "+level+" is required to do that.");
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
			if(c.getSkillHandler().getCraftingHandler().checkCrafting(itemUsed, useWith)) return true;
			if(c.getSkillHandler().getCraftingHandler().checkCrafting(useWith, itemUsed)) return true;
		}

		if(itemUsed == 749 && useWith == 2357){
			if(c.playerLevel[c.playerCrafting] < 60){
				c.sendMessage("You need at least 60 Crafting to do that.");
			}
			else{
				c.getInventoryHandler().deleteItem(749);
				c.getInventoryHandler().deleteItem(2357);
				c.getInventoryHandler().addItem(750);
				c.sendMessage("You shape the gold around the Totem.");
			}
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
			c.getSkillHandler().getFletchingHandler().fletchArrow();

		if(itemUsed == 53) //headless arrow
			c.getSkillHandler().getFletchingHandler().fletchingMakeArrow(useWith);

		if(itemUsed == 946 && useWith == 1511) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 1511, 50, 1, 48, 10, 40, false);
			c.getFrameMethodHandler().select4Options(15,"Options", "Make all"+c.fletchinglogs+" Shortbows", "Make all"+c.fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 50) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 50, 841, 1, 0, 0, 40, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all shortbows", "Cancel");
			return true;
		}

		if(itemUsed == 1777 && useWith == 48) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 48, 839, 10, 0, 0, 75, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all longbows", "Cancel");
			return true;
		}

		if(itemUsed == 946 && useWith == 1521) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid(" Oak", 1521, 54, 15, 56, 25, 85, false);
			c.getFrameMethodHandler().select4Options(15,"Options", "Make all"+c.fletchinglogs+" Shortbows", "Make all"+c.fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 54) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 54, 843, 15, 0, 0, 85, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all oak shortbows", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 56) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 56, 845, 25, 0, 0, 100, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all oak longbows", "Cancel");
			return true;
		}
		if(itemUsed == 946 && useWith == 1519) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid(" Willow", 1519, 60, 35, 58, 40, 115, false);
			c.getFrameMethodHandler().select4Options(15,"Options", "Make all"+c.fletchinglogs+" Shortbows", "Make all"+c.fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 60) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 60, 849, 35, 0, 0, 115, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all willow shortbows", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 58) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 58, 847, 40, 0, 0, 135, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all willow longbows", "Cancel");
			return true;
		}
		if(itemUsed == 946 && useWith == 1517) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid(" Maple", 1517, 64, 45, 62, 50, 155, false);
			c.getFrameMethodHandler().select4Options(15,"Options", "Make all"+c.fletchinglogs+" Shortbows", "Make all"+c.fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 64) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 64, 853, 45, 0, 0, 155, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all maple shortbows", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 62) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 62, 851, 50, 0, 0, 180, true);
			c.getFrameMethodHandler().select2Options(35,"Options", "String all maple longbows", "Cancel");
			return true;
		}
		if(itemUsed == 946 && useWith == 1515) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid(" Yew", 1515, 68, 55, 66, 60, 200, false);
			c.getFrameMethodHandler().select4Options(15,"Options", "Make all"+c.fletchinglogs+" Shortbows", "Make all"+c.fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}
		if(itemUsed == 1777 && useWith == 68) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 68, 857, 55, 0, 0, 200, true); 
			c.getFrameMethodHandler().select2Options(35,"Options", "String all yew shortbows", "Cancel");
			return true;
		}			
		if(itemUsed == 1777 && useWith == 66) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 66, 855, 60, 0, 0, 240, true); 
			c.getFrameMethodHandler().select2Options(35,"Options", "String all yew longbows", "Cancel");
			return true;
		}		
		if(itemUsed == 946 && useWith == 1513) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid(" Magic", 1513, 72, 70, 70, 80, 300, false);
			c.getFrameMethodHandler().select4Options(15,"Options", "Make all"+c.fletchinglogs+" Shortbows", "Make all"+c.fletchinglogs+" Longbows", "Make all arrowshafts", "Cancel");
			return true;
		}						
		if(itemUsed == 1777 && useWith == 72) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 72, 861, 70, 0, 0, 300, true); 
			c.getFrameMethodHandler().select2Options(35,"Options", "String all magic shortbows", "Cancel");
			return true;
		}		
		if(itemUsed == 1777 && useWith == 70) {
			c.getSkillHandler().getFletchingHandler().fletchingvoid("", 70, 859, 80, 0, 0, 360, true); 
			c.getFrameMethodHandler().select2Options(35,"Options", "String all magic longbows", "Cancel");
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
			c.startAnimation(885);
			c.sendMessage("You poison the fish food.");
			return removeAdd(itemUsed,useWith,274);
		}

		if(itemUsed == 139 && useWith == 1513) {
			c.startAnimation(885);
			c.sendMessage("You get a mixture of chemicals.");
			return removeAdd(itemUsed,useWith,705);
		}

		//Dragon SQ Together
		if(itemUsed == 2366 && useWith == 2368) {
			if(c.playerLevel[12] >= 65) {
				removeAdd(itemUsed,useWith,1187);
				c.getClientMethodHandler().addSkillXP(15000, 12);
				c.startAnimation(1649);
				c.sendMessage("You put together the two halves, making a... whole!");
			} else c.sendMessage("You need 65 crafting to put the Dragon SQ together.");
			return true;
		}
		// tj007razor: firemaking uses
		if (itemUsed == 590)
			return c.getSkillHandler().getFireMakingHandler().tinderboxUsedWith(useWith);
		
		// end firemaking
		return false;
	}

	/**
	 * Private Helper method used with itemUsedWith boolean
	 * @param remove1 Removes c item from player inventory
	 * @param remove2 Removes c item from player inventory
	 * @param add Adds c item to player inventory
	 * @return true unless remove items not found
	 */
	public boolean removeAdd(int remove1, int remove2, int add){
		int slot1 = c.getInventoryHandler().getItemSlot(remove1);
		int slot2 = c.getInventoryHandler().getItemSlot(remove2);
		if(slot1 == -1 || slot2 == -1){
			c.debug("In removeAdd method, could not find item(s) in player inventory");
			return false;
		}
		c.getInventoryHandler().deleteItem(remove1, slot1, 1);
		c.getInventoryHandler().deleteItem(remove2, slot2, 1);
		c.getInventoryHandler().addItem(add,1);
		return true;
	}

	/**
	 * Private Helper method used with itemUsedWith boolean
	 * @param remove Removes c item from player inventory
	 * @param add Adds c item to player inventory
	 * @return true unless remove item was not found
	 */
	private boolean removeAdd(int remove, int add){
		int slot = c.getInventoryHandler().getItemSlot(remove);
		if(slot == -1){
			c.debug("In removeAdd method, could not find item "+remove+" in inventory.");
			return false;
		}
		c.getInventoryHandler().deleteItem(remove, slot, 1);
		c.getInventoryHandler().addItem(add,1);
		return true;
	}

	/**
	 * @return false by default
	 */
	public boolean prayerPot(int deleteItemID, int addItemID, int slotID){
		c.startAnimation(829);
		c.playerLevel[5] += 44;
		if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5])) 
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);

		c.getFrameMethodHandler().sendFrame126(""+c.playerLevel[5]+"", 4012);
		c.getInventoryHandler().deleteItem(deleteItemID, slotID, 1);
		c.getInventoryHandler().addItem(addItemID, 1);
		c.requirePlayerUpdate();
		c.getClientMethodHandler().addSkillXP(0, 5); //updates frame
		return false;
	}

	/**
	 * @return false by default
	 */
	private boolean superPot(int skillID, int removeItemID, int addItemID, int slotID){
		c.startAnimation(829);
		double cba = c.getLevelForXP(c.playerXP[skillID]) / 10;
		double aaa = cba * 2;
		if (aaa <= 1) 
			aaa = 2; //minimum is +2
		c.playerLevel[skillID] = c.getLevelForXP(c.playerXP[skillID]);
		c.playerLevel[skillID] += (int)aaa;
		c.getFrameMethodHandler().sendFrame126(""+c.playerLevel[0]+"", 4004);
		c.getInventoryHandler().deleteItem(removeItemID, slotID, 1);
		c.getInventoryHandler().addItem(addItemID, 1);
		c.requirePlayerUpdate();
		c.getClientMethodHandler().addSkillXP(0, skillID); //updates frame
		return false;
	}	

	/**
	 * @return false by default
	 */
	private boolean pot(int skillID, int removeItemID, int addItemID, int Slot){
		c.startAnimation(829);
		double cba = c.getLevelForXP(c.playerXP[skillID]) / 10;
		double aaa = cba / 2;
		double abc2 = aaa + cba;
		if (abc2 <= 1) 
			abc2 = 2; //minimum is +2
		c.playerLevel[skillID] = c.getLevelForXP(c.playerXP[skillID]);
		c.playerLevel[skillID] += (int)abc2;
		c.getFrameMethodHandler().sendFrame126(""+c.playerLevel[0]+"", 4004);
		c.getInventoryHandler().deleteItem(removeItemID, Slot, 1);
		c.getInventoryHandler().addItem(addItemID, 1);
		c.requirePlayerUpdate();
		c.getClientMethodHandler().addSkillXP(0, skillID); //updates frame
		return false;
	}


	/**
	 * @return false by default
	 */
	private boolean identify(int requiredLevel, int EXP, int itemID, int addItemID){
		if (c.playerLevel[15] >= requiredLevel) {
			c.getClientMethodHandler().addSkillXP((EXP*c.rate), 15);
			c.getInventoryHandler().deleteItem(itemID, c.getInventoryHandler().GetItemSlot(itemID), 1);
			c.sendMessage("You identify the herb.");
			c.getInventoryHandler().addItem(addItemID, 1);
		} 
		else c.sendMessage("You aren't high enough herblore to identify this herb");
		return false;
	}

}

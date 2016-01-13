
public class ItemUse {

	private client c = null;

	public ItemUse(client pc){
		this.c = pc;
	}

	
	public boolean useItemOnPlayer(int itemID, int slot, int playerID){
		client playerThatItemWasUsedOn = (client) server.playerHandler.players[playerID];
		switch(itemID){
		case 962:
			c.getInventoryHandler().deleteItem(962, c.getInventoryHandler().getItemSlot(962), 1);
			c.sendMessage("You crack the cracker...");
			c.getInventoryHandler().addItem(playerThatItemWasUsedOn.DROPHANDLER.getDrop(playerThatItemWasUsedOn.DROPHANDLER.RARES), 1);
			playerThatItemWasUsedOn.sendMessage("Someone cracked a cracker on you.");
			break;
		}
		return true;
	}

	public boolean checkItemUse(int Item, int Slot) {

		if(isItemSpamming()) return false;
		itemTimer = System.currentTimeMillis();

		if(lists.bones.exists(Item))
			return this.PRAY.buryBones(Item, Slot);

		if(lists.food.exists(Item))
			return eat(Item,Slot);

		switch (Item) {

		case 2520:
			startAnimation(918);
			txt4 = "Come on Swifty, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 2522:
			startAnimation(919);
			txt4 = "Come on Alex, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 2524:
			startAnimation(920);
			txt4 = "Come on Vegeta, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 2526:
			startAnimation(921);
			txt4 = "Come on MrWicked, we can win the race!";
			string4UpdateRequired = true;
			break;

		case 952://spade

			if (isInArea(3553, 3294, 3561, 3301))  // verac
				isteleporting3(2843, 14, 3578,9706,3);
			else if (isInArea(3550, 3278, 3557, 3287))  // torag
				isteleporting3(2843, 14, 3568,9683,3);
			else if (isInArea(3561, 3285, 3568, 3292))  // ahrim
				isteleporting3(2843, 14, 3557,9703,3);
			else if (isInArea(3570, 3293, 3579, 3302))  // dharok
				isteleporting3(2843, 14, 3556,9718,3);
			else if (isInArea(3571, 3278, 3582, 3285))  // guthan
				isteleporting3(2843, 14, 3534,9704,3);
			else if (isInArea(3562, 3273, 3569, 3279))  // karil
				isteleporting3(2843, 14, 3546,9684,3);
			else{
				this.startAnimation(2843);
				sendMessage("You dig and find nothing.");
			}

			break;

		case 3014:
			runningEnergy += 10;
			setAnimation(829);
			deleteItem(3014, GetItemSlot(3014), 1);
			addItem(229, 1);
			break;
		case 3012:
			runningEnergy += 10;
			setAnimation(829);
			deleteItem(3012, GetItemSlot(3012), 1);
			addItem(3014, 1);
			break;
		case 3010:
			runningEnergy += 10;
			setAnimation(829);
			deleteItem(3010, GetItemSlot(3010), 1);
			addItem(3012, 1);
			break;
		case 3008:
			runningEnergy += 10;
			setAnimation(829);
			deleteItem(3008, GetItemSlot(3008), 1);
			addItem(3010, 1);
			break;
		case 3022:
			runningEnergy += 20;
			setAnimation(829);
			deleteItem(3022, GetItemSlot(3022), 1);
			addItem(229, 1);
			break;
		case 3020:
			runningEnergy += 20;
			setAnimation(829);
			deleteItem(3020, GetItemSlot(3020), 1);
			addItem(3022, 1);
			break;
		case 3018:
			runningEnergy += 20;
			setAnimation(829);
			deleteItem(3018, GetItemSlot(3018), 1);
			addItem(3020, 1);
			break;
		case 3016:
			runningEnergy += 20;
			setAnimation(829);
			deleteItem(3016, GetItemSlot(3016), 1);
			addItem(3018, 1);
			break;
		case 161: // super str (1)
			return pot(playerStrength, Item, 229);

		case 159:
			return pot(2, Item, 161);

		case 157:
			return pot(2, Item, 159);

		case 2440:
			return pot(2, Item, 157);

		case 113:
			return pot(2, Item, 115);

		case 115:
			return pot(2, Item, 117);

		case 117:
			return pot(2, Item, 119);

		case 119:
			return pot(2, Item, 229);

		case 2446: //Antipoison(4)
			setAnimation(829);
			Poisoned = false;
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(2446, GetItemSlot(2446), 1);
			addItem(175, 1);
			PoisonDelay = 9999999;
			break;
		case 175: //Antipoison(3)
			setAnimation(829);
			Poisoned = false;
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(175, GetItemSlot(175), 1);
			addItem(177, 1);
			PoisonDelay = 9999999;
			break;
		case 177: //Antipoison(2)
			setAnimation(829);
			Poisoned = false;
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(177, GetItemSlot(177), 1);
			addItem(179, 1);
			PoisonDelay = 9999999;
			break;
		case 179: //Antipoison(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink the last dose of the super anti-poison.");
			deleteItem(179, GetItemSlot(179), 1);
			addItem(229, 1);
			PoisonDelay = 9999999;
			break;

		case 197: //Witchs Brew Potion
			txt4 = "You may now enter.";
			startAnimation(1114);
			stillgfx(342, absY, absX);
			stillgfx(559, absY, absX -1);
			stillgfx(559, absY, absX +1);
			stillgfx(559, absY -1, absX);
			stillgfx(559, absY +1, absX);
			sendMessage("You drink the Poison Chalice");
			deleteItem(197, GetItemSlot(197), 1);
			smix = 1;
			sendMessage("A voice speaks from above.");
			string4UpdateRequired = true;
			break;

		case 4568: //Witchs Brew Potion
			Menu(this.menuHandler.ancientsbook());
			break;

		case 2886: //Witchs Brew Potion
			Menu(this.menuHandler.wbBook());
			sendMessage("You open the book containing a list of ingredients.");
			if (wb < 3){
				wb = 3;
			}

			break;

		case 550: //newcomer map  by me ?
			showInterface(5392);
			sendMessage("You Un-roll The Map And It Shows The South-east Of Deep Haven");
			sendMessage("Your Co-ordinates are: X: "+absX+" Y: "+absY); 
			sendQuest("Deep Haven", 5394);
			break;
		case 1856: //Guide Book
			sendQuest("Deep Haven Guidebook", 903);
			sendQuest("Welcome to Deep Haven!", 843);
			sendQuest("I suggest training on", 844);
			sendQuest("Monsters for a good amount", 845);
			sendQuest("of exp, they are found in", 846);
			sendQuest("almost every portal!",847);
			sendQuest("Magic requires no runes!", 849);
			sendQuest("To make money, either", 850);
			sendQuest("kill monsters or", 851);
			sendQuest("get your skills up", 852);
			sendQuest("and sell the things", 853);
			sendQuest("you make from it!", 854);
			sendQuest("", 855);
			sendQuest("", 856);
			sendQuest("",857);
			sendQuest("",858);
			sendQuest("  ¤¤¤¤",859);	
			sendQuest("Thanks to rune-server.org!",860);
			sendQuest("for many tutorials I had",861);
			sendQuest("found there!",862);
			sendQuest(" ¥¥¥¥",863);
			sendQuest("Thanks for playing!",864);
			showInterface(837);
			flushOutStream();
			break;
		case 2448: //superAntipoison(4)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink a dose of the super anti-poison.");
			deleteItem(2448, GetItemSlot(2448), 1);
			addItem(181, 1);
			break;
		case 181: //superAntipoison(3)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink a dose of the super anti-poison.");
			deleteItem(181, GetItemSlot(181), 1);
			addItem(183, 1);
			break;
		case 183: //superAntipoison(2)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink a dose of the super anti-poison.");
			deleteItem(183, GetItemSlot(183), 1);
			addItem(184, 1);
			break;
		case 185: //superAntipoison(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 100;
			sendMessage("You drink the last dose of the super anti-poison.");
			deleteItem(185, GetItemSlot(185), 1);
			addItem(229, 1);
			break;
		case 5943: //extra-strongAntidote(4)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink a dose of the extra strong antidote");
			deleteItem(5943, GetItemSlot(5943), 1);
			addItem(5945, 1);
			break;
		case 5945: //extra-strongAntidote(3)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink a dose of the extra strong antidote");
			deleteItem(5945, GetItemSlot(5945), 1);
			addItem(5945, 1);
			break;
		case 5947: //extra-strongAntidote(2)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink a dose of the extra strong antidote");
			deleteItem(5947, GetItemSlot(5947), 1);
			addItem(5949, 1);
			break;
		case 5949: //extra-strongAntidote(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 200;
			sendMessage("You drink the last dose of the extra strong antidote");
			deleteItem(5949, GetItemSlot(5949), 1);
			addItem(229, 1);
			break;
		case 5952: //super-strongAntidote(4)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink a dose of the super strong antidote");
			deleteItem(5952, GetItemSlot(5952), 1);
			addItem(5954, 1);
			break;
		case 5954: //super-strongAntidote(3)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink a dose of the super strong antidote");
			deleteItem(5954, GetItemSlot(5954), 1);
			addItem(5956, 1);
			break;
		case 5956: //super-strongAntidote(2)
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink a dose of the super strong antidote");
			deleteItem(5956, GetItemSlot(5956), 1);
			addItem(5958, 1);
			break;
		case 5958: //super-strongAntidote(1)
			setAnimation(829);
			Poisoned = false;
			PoisonDelay2 = 350;
			sendMessage("You drink the last dose of the super strong antidote");
			deleteItem(5958, GetItemSlot(5958), 1);
			addItem(229, 1);
			break;
		case 131: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink the last dose of the restore potion");
			deleteItem(131, GetItemSlot(131), 1);
			addItem(229, 1);
			break;
		case 129: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the restore potion");
			deleteItem(129, GetItemSlot(129), 1);
			addItem(131, 1);
			break;
		case 127: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the restore potion");
			deleteItem(127, GetItemSlot(127), 1);
			addItem(129, 1);
			break;
		case 5520: //Barrows
			Menu(this.menuHandler.BarrowsQuest());
			break;
		case 2430: // restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the restore potion");
			deleteItem(2430, GetItemSlot(2430), 1);
			addItem(127, 1);
			break;
		case 3030: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink the last dose of the super restore potion");
			deleteItem(3030, GetItemSlot(3030), 1);
			addItem(229, 1);
			superRestore = true;
			break;
		case 3028: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the super restore potion");
			deleteItem(3028, GetItemSlot(3028), 1);
			addItem(3030, 1);
			superRestore = true;
			break;
		case 3026: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the super restore potion");
			deleteItem(3026, GetItemSlot(3026), 1);
			addItem(3028, 1);
			superRestore = true;
			break;
		case 3024: // super restore pot
			setAnimation(829);
			restorePot();
			sendMessage("You drink a dose of the super restore potion");
			deleteItem(3024, GetItemSlot(3024), 1);
			addItem(3026, 1);
			superRestore = true;
			break;
		case 3038: // agil pot (1)
			return pot(16,Item,229);

		case 3036: // agil pot (2)
			return pot(16,Item,3038);

		case 3034: // agil pot (3)
			return pot(16,Item,3036);

		case 3032: // agil pot (4)
			return pot(16,Item,3034);

		case 143: // pray pot (1)
			return prayerPot(Item,229);

		case 141: // pray pot (2)
			return prayerPot(Item,143);


		case 139: // pray pot (3)
			return prayerPot(Item,141);

		case 2434: // pray pot (4)
			return prayerPot(Item,139);

		case 167: // super defence pot (1)
			return pot(1,Item,229);

		case 165: // super defence pot (2)
			return pot(1,Item,167);

		case 163: // super defence pot (3)
			return pot(1,Item,165);

		case 2442: // super defence pot (4)
			return superPot(1,Item,163);

		case 137: // defence pot (1)
			return pot(1,Item,229);

		case 135: // defence pot (2)
			return pot(1,Item,137);

		case 133: // defence pot (3)
			return pot(1,Item,135);

		case 2432: // defence pot (4)
			return pot(1,Item,133);

		case 3046: // mage pot (1)
			return pot(6,Item,229);

		case 3044: // mage pot (2)
			return pot(6,Item,3046);

		case 3042: // mage pot (3)
			return pot(6,Item,3044);

		case 3040: // mage pot (4)
			return pot(6,Item,3042);


		case 6199: // Mystery Box
			setAnimation(862);
			deleteItem(6199, GetItemSlot(6199), 1);
			addItem(DROPHANDLER.getDrop(DROPHANDLER.RARES), 1);
			sendMessage("You open the box and recieve an item!");
			break;


		case 5761: // Slayer Drink
			setAnimation(829);
			playerLevel[18] = getLevelForXP(playerXP[18]);
			playerLevel[18] += 8;
			sendFrame126(""+playerLevel[18]+"", 4014);
			deleteItem(5761, GetItemSlot(5761), 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			return false;


		case 173: // range pot (1)
			return pot(4,Item,229);

		case 171: // range pot (2)
			return pot(4,Item,173);

		case 169: // range pot (3)
			return pot(4,Item,171);

		case 2444: // range pot (4)
			return pot(4,Item,169);

		case 149: // super attack pot (1)
			return superPot(0,Item,229);

		case 147: // super attack pot (2)
			return superPot(0,Item,149);

		case 145: // super attack pot (3)
			return superPot(0,Item,147);

		case 2436: // super attack pot (4)
			return superPot(0,Item,145);

		case 125: // attack pot (1)
			return pot(0,Item,229);

		case 123: // attack pot (2)
			return pot(0,Item,125);

		case 121: // attack pot (3)
			return pot(0,Item,123);

		case 2428: // attack pot (4)
			return pot(0,Item,121);


		case 13303: // Server Token
			showInterface(12752);
			sendMessage("You look at the token, it has a strange glow coming from it.");
			break;

		case 199:
			return this.identify(3, 30, Item, 249); //identify guam

		case 201:
			return this.identify(5, 40, Item, (Item+50)); //identify marrentill

		case 203:
			return this.identify(11, 50, Item, (Item+50)); //identify tarromin

		case 205:
			return this.identify(20, 60, Item, (Item+50)); //identify harralander

		case 207:
			return this.identify(25, 70, Item, (Item+50)); //identify ranarr

		case 209:
			return this.identify(30, 80, Item, 2998); //identify toadflax

		case 211:
			return this.identify(40, 90, Item, 259); //identify irit

		case 213:
			return this.identify(48, 100, Item, 261); //identify avantoe

		case 215:
			return this.identify(54, 110, Item, 263); //identify kwuarm

		case 217:
			return this.identify(59, 120, Item, 3000); //identify snapdragon

		case 219:
			return this.identify(65, 130, Item, 265); //identify cadantine

		case 1531:
			return this.identify(67, 140, Item, 2481); //identify lantadyme

		case 1533:
			return this.identify(75, 160, Item, 269); //identify tortsol

		case 1529:
			return this.identify(70, 150, Item, 267); //identify dwarf weed


		case 611:
			stillgfx(336, absY, absX);
			startAnimation(1333);
			String task = this.SLAYER.getTaskName(slayerNPC);
			if(slayerCount > 1) task += "s";
			if(slayerCount > 0) sendMessage("Remaining : "+slayerCount+" more "+task+".");
			if(slayerCount <= 0 && slayerNPC != 0) sendMessage("Your current slayer task is complete.");
			if(slayerNPC == 0) sendMessage("You do not have a current slayer task.");
			break;

		default:
			sendMessage("Nothing interesting is happening.");
			if(debugmode)debug("Unhandled Item - ItemID: "+Item);
			return false;
		}
		return true;
	}

	public boolean useItemOnObject(int useItemID, int atObjectID, int atObjectY, int atObjectX, int itemSlot){
		
		if(useItemID == 954 && atObjectID == 3830)
			teleport(3507,9494,0);

		if(useItemID == 1779 && atObjectID == 8748){
			spinning = true;
			getFrameMethodHandler().selectoption("Options", "Make all Bowstrings", "Cancel", "...");
			break;
		}

		if(atObjectID == 2452 || atObjectID == 2459){
			RUNECRAFTING.craftRunes(atObjectID, useItemID);
			break;
		}

		//man123
		//server.npcHandler.newNPC(2880, 2603, 3161, heightLevel, 0, 0, 0, 0, 1, server.npcHandler.GetNpcListHP(2880), false);
		if(useItemID == 1462 && atObjectID == 2459)
			isteleporting3(1500, 6, 2142, 4814, 0);


		if(lists.deadPlantList.exists(atObjectID) || lists.brushList.exists(atObjectID)) //using an item on brush/dead brush
			this.FARM.guide(atObjectX, atObjectY, atObjectID);
		if(lists.patchList.exists(atObjectID)) //using an item on patch
			this.FARM.grow(atObjectX, atObjectY, useItemID, atObjectID);

		if (useItemID == 442 && (atObjectID == 2781 || atObjectID == 2785 || atObjectID == 11666))//Using silver on furnace
		{smithingvoid2(100, "silver", 20, 2355, 442, 0, 1);}
		else if (useItemID == 440 && (atObjectID == 2781 || atObjectID == 2785 || atObjectID == 11666))//Using silver on furnace
		{smithingvoid2(150, "iron", 15, 2351, 440, 0, 1);}
		else if ((useItemID == 436 || useItemID == 438) && (atObjectID == 2781 || atObjectID == 2785 || atObjectID == 11666))//Using silver on furnace
		{
			if (IsItemInBag(436) == false || IsItemInBag(438) == false){
				sendMessage("You need 1 tin and 1 copper to make bronze bars!");}
			else if (IsItemInBag(436) == true && IsItemInBag(438) == true){
				smithingvoid2(100, "bronze", 1, 2349, 438, 436, 2);}
		}
		else if (useItemID == 444 && (atObjectID == 2781 || atObjectID == 2785))
		{smithingvoid2(200, "gold", 35, 2357, 444, 0, 1);}
		else if (useItemID == 447 && (atObjectID == 2781 || atObjectID == 2785))
		{smithingvoid2(400, "mithril", 70, 2359, 447, 0, 1);}
		else if (useItemID == 449 && (atObjectID == 2781 || atObjectID == 2785))
		{smithingvoid2(650, "adamantite", 80, 2361, 449, 0, 1);}
		else if (useItemID == 451 && (atObjectID == 2781 || atObjectID == 2785))
		{smithingvoid2(1000, "runite", 90, 2363, 451, 0, 1);}
		else if (useItemID == 453 && (atObjectID == 2781 || atObjectID == 2785))
		{smithingvoid2(300, "steel", 50, 2353, 453, 0, 1);}
		//end of smithing



		//	else if(useItemID == 2357 && atObjectID == 2783)//Gold
		//	{
		//		initSmithing(2357);
		//		flushOutStream();
		//	}


		else if(useItemID == 2349 && atObjectID == 2783)//bronze
		{
			displaySmithingFrame(2349);
			flushOutStream();
		}

		else if(useItemID == 2351 && atObjectID == 2783)//iron
		{
			displaySmithingFrame(2351);
			flushOutStream();
		}

		else if(useItemID == 2353 && atObjectID == 2783)//steel

		{
			displaySmithingFrame(2353);
			flushOutStream();
		}

		else if(useItemID == 2359 && atObjectID == 2783)//mith
		{
			displaySmithingFrame(2359);
			flushOutStream();
		}

		else if(useItemID == 2361 && atObjectID == 2783)//addy
		{
			displaySmithingFrame(2361);
			flushOutStream();
		}

		else if(useItemID == 2363 && atObjectID == 2783)//rune
		{
			displaySmithingFrame(2363);
			flushOutStream();
		}

		else if (useItemID == 395 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using shrimp on range
		{
			if(actionTimer == 0 && (ST == 5 || STC == 1)){
				Cooking.cookingvoid(this, 1800, "Sea Turtle", 90, 397, 395, 20, 399);
			}
			else if (actionTimer == 0 && (ST < 5 && ST > 5 || STC == 1)){
				sendMessage("You need to beat The Famous Catch to cook Sea Turtle!");
			}
		}
		else if (useItemID == 2148 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using Raw Lava Eel
		{
			if(actionTimer == 0){
				Cooking.cookingvoid(this, 450, "Lava Eel", 45, 2149, 317, 1, 2146);
			}
		}
		else if (useItemID == 317 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using shrimp on range
		{
			if(actionTimer == 0){
				Cooking.cookingvoid(this, 120, "Shrimp", 0, 315, 317, 1, 323);
			}
		}
		else if (useItemID == 349 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using pike on range
		{
			Cooking.cookingvoid(this, 240, "Pike", 15, 351, 349, 3, 343);	
		}
		else if (useItemID == 359 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using tuna on range
		{
			if(actionTimer == 0){
				Cooking.cookingvoid(this, 400, "Tuna", 25, 361, 359, 5, 357);
			}
		}
		else if (useItemID == 377 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using raw Lobster on range
		{
			if(actionTimer == 0){
				Cooking.cookingvoid(this, 450, "Lobster", 40, 379, 377, 7, 381);
			}
		}
		else if (useItemID == 371 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using raw Swordfish on range
		{
			if(actionTimer == 0){
				Cooking.cookingvoid(this, 565, "Swordfish", 55, 373, 371, 12, 375);
			}
		}
		else if (useItemID == 383 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using raw Shark on range
		{
			Cooking.cookingvoid(this, 850, "Shark", 70, 385, 383, 15, 387);
		}
		else if (useItemID == 389 || useItemID == 9682 && (atObjectID == 2728 || atObjectID == 5275 || atObjectID == 2732))//Using raw manta ray on range
		{
			if(actionTimer == 0){
				Cooking.cookingvoid(this, 1200, "Manta Ray", 90, 391, 389, 16, 393);
			}
		}
		else if (useItemID == 526 && atObjectID == 4092)//Broken Fire Altar with Bones
		{
			if(eastergift == 2){
				deleteItem(526,getItemSlot(526),1);
				teleportToX = 2501;
				teleportToY = 4952;
				heightLevel = 0;
				updateRequired = true; 
				appearanceUpdateRequired = true;
			}
			else {
				sendMessage("I should ask around before experimenting with ancient altars.");
			}
		}
		if(debugmode)
			debug("Action Button2: "+actionButton2);
		return true;
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

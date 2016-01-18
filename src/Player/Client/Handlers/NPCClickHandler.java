
public class NPCClickHandler {

	private client c = null;
	
	public NPCClickHandler(client pc){
		this.c = pc;
	}
	

	/**
	 * Handles second click of NPCs
	 * @param slotID Slot ID to search for NPC in npcs server list
	 */
	public void npcSecondClick(int slotID){
		int NPCID = 0;
		int npcX = 0;
		int npcY = 0;
		c.walkingToNPC = 0;
		try{
			NPCID = server.npcHandler.npcs[slotID].npcType;
			npcX = server.npcHandler.npcs[slotID].absX;
			npcY = server.npcHandler.npcs[slotID].absY;
		}
		catch(Exception e){
			c.error("npcSecondClick : "+e.toString());
			return;
		}		
		c.faceNPC(slotID);
		c.debug("npcSecondClick: NPCID is "+NPCID);			
		
		if(!misc.GoodDistance(npcX, npcY, c.absX, c.absY, 1)){
			c.walkingToNPC_slotID = slotID;
			c.walkingToNPC = 2;
			c.walkingToNPC_X = npcX;
			c.walkingToNPC_Y = npcY;
			return;
		}

		/* Shops */
		switch(NPCID){
		case 494: case 495: case 496: case 497: case 2355: case 2354: case 166: //Bankers
			c.getFrameMethodHandler().openUpBankFrame();
			return;

		case 522: case 523:
			c.getFrameMethodHandler().openUpShopFrame(1); //Weapons
			return;

		case 526: case 527: case 2356: case 471:
			c.getFrameMethodHandler().openUpShopFrame(2); //Range Shop
			return;

		case 553: case 461:
			c.getFrameMethodHandler().openUpShopFrame(3); //Magic Shop
			return;

		case 531: case 530: case 557: case 545: case 1699: case 2352: case 570: case 571: case 876: case 563:
			c.getFrameMethodHandler().openUpShopFrame(4); //General store
			return;

		case 580:
			c.getFrameMethodHandler().openUpShopFrame(5); //PK Shop
			return;

		case 538: case 1301: case 2353: case 1039: case 875:
			c.getFrameMethodHandler().openUpShopFrame(6); //Clothes
			return;

		case 546: 
			c.getFrameMethodHandler().openUpShopFrame(7); //Food 
			return;

		case 548:
			c.getFrameMethodHandler().openUpShopFrame(8); //Herblore
			return;

		case 551: case 552:
			c.getFrameMethodHandler().openUpShopFrame(9); //Fletching
			return;
			
		case 1038: case 593:
			c.getFrameMethodHandler().openUpShopFrame(10); //Raw meat store
			return;

		case 1315: case 1282: case 2357:
			c.getFrameMethodHandler().openUpShopFrame(11); //Store?
			return;

		case 584:
			c.getFrameMethodHandler().openUpShopFrame(12); //Crafting
			return;

		case 1860:
			c.getFrameMethodHandler().openUpShopFrame(16); //Pickaxe
			return;

		case 558: case 561: case 576:
			c.getFrameMethodHandler().openUpShopFrame(18); //Fishing Supplies
			return;

		case 519:
			c.getFrameMethodHandler().openUpShopFrame(23); //Basic Weapons
			return;

		case 524: case 525: case 1041: case 873:
			c.getFrameMethodHandler().openUpShopFrame(26); //Armor
			return;

		case 536:
			c.getFrameMethodHandler().openUpShopFrame(36); //Hatchet Shop
			return;

		case 933:
			c.getFrameMethodHandler().openUpShopFrame(37); //Barrows
			return;

		case 1780: case 1784:
			c.getFrameMethodHandler().openUpShopFrame(39); //Travelling Merchant
			return;

		case 1778:
			c.getFrameMethodHandler().openUpShopFrame(42); //Fishing Supplies
			return;

		case 1303:
			c.getFrameMethodHandler().openUpShopFrame(46); //Graveyard Shop
			return;

		case 793: case 1300: case 1042:
			c.getFrameMethodHandler().openUpShopFrame(51); //Tavern Shop
			return;

		case 588:
			c.getFrameMethodHandler().openUpShopFrame(52); //Jewelry Shop
			return;		
			
		case 874: //special ogre weapon shop
			c.getFrameMethodHandler().openUpShopFrame(70);
			return;
			
		case 683: case 575: //bow and arrow
			c.getFrameMethodHandler().openUpShopFrame(80);
			return;
			
		case 682: //ranged armor
			c.getFrameMethodHandler().openUpShopFrame(81);
			return;
			
		case 562: //candle
			c.getFrameMethodHandler().openUpShopFrame(90);
			return;
			
		case 528: //legends Guild
			c.getFrameMethodHandler().openUpShopFrame(100);
			return;
			
		case 3788:
			if(c.pestControlPoints < 30) c.getClientMethodHandler().npcdialogue(3788, "You need at least 30 Pest Control Points","to view the shop. You currently","have "+c.pestControlPoints+" points.");
			else c.getFrameMethodHandler().openUpShopFrame(60); //Void Knight Shop rewards
			return;
		}
		
		/* Second Click, not Shops */
		switch(NPCID){
		
		case 462:
			if (c.RM == 4){
				c.getClientMethodHandler().c.getClientMethodHandler().selectOptionTravel2("Mine rune essence?", "Yes", 2911, 4833, "No", -1,-1);
			}
			else c.sendMessage("You need to complete Rune Mysteries to do that.");
			return;

		case 171: 
			if (c.RM < 4){
				c.getClientMethodHandler().npcdialogue("Brimstail", 171, "I can't teleport you anywhere.");
			}
			else if (c.RM >= 4)
				c.teleport(2911,4833);
			return;
			
		/* Pickpocketing */	
		case 1: case 2: case 3: case 4: case 5: case 6: 
			Thieving.pickpocket(1, 4, 200, 120, c);
			return;
			
		case 7:	case 1758://farmer
			Thieving.pickpocket(15, 7, 1200, 220, c); 
			return;
			
		case 9: case 10: case 32: case 678: case 812: case 887: //guards
			Thieving.pickpocket(35, 10, 1600, 400, c); 
			return;			
			
		case 34: //watchman
			Thieving.pickpocket(60, 18, 2000, 650, c); 
			return;
			
		case 646: // Stealing for talisman
			if (c.getInventoryHandler().freeSlots() > 1){
				c.sendMessage("You steal a talisman.");
				c.startAnimation(881);
				c.getInventoryHandler().addItem(1438,1);
			}
			else
				c.sendMessage("Your inventory is full.");
			return;
			
		case 20: //Paladin
			Thieving.pickpocket(75, 25, 2500, 1500, c); 
			return;
			
		case 21: //Hero
			Thieving.pickpocket(85, 30, 3500, 2000, c); 
			return;
		
		/* End Pickpocketing */
			
		case 1055:
			c.ticketexchange2 = true;
			c.getFrameMethodHandler().selectoption2("Rewards", "100 Tickets-"+(c.playerLevel[16]*10000)+" Agility EXP", "250 Tickets-Void Knight Gloves", "500 Tickets-Agility Armor", "Cancel");
			return;
			
		default:
			if(c.DIALOGUEHANDLER.exists(NPCID)){ //dialogue handler
				c.getClientMethodHandler().startDialogue(NPCID);
			}
			
		}
		
		c.error("Unhandled second click NPC ID : "+NPCID);
		return;
	}
	
	public void skillMaster(int npcID, String name, int cape, int capeTrimmed, int hood, String _skillName, int skillID, String[] dialogue){
		c.skillMasterDialogue = dialogue;
		c.skillMasterName = name;
		c.skillName = _skillName;
		c.skillcape = cape;
		c.skillcapeTrimmed = capeTrimmed;
		c.skillHood = hood;
		c.skillMasterID = npcID;
		c.skill99ID = skillID;
		c.skillMaster = true;
		c.getFrameMethodHandler().selectoption2("Options", "Talk to "+c.skillMasterName, "Purchase a "+c.skillName+" Hood or Cape", "", "Nevermind.");
	}

	/**
	 * Handles first click of NPCs
	 * @param slotID - Slot ID to search for NPC for in server list
	 */
	public void npcFirstClick(int slotID){
		int NPCID = 0;
		int npcX = 0;
		int npcY = 0;
		c.walkingToNPC = 0;
			NPCID = server.npcHandler.npcs[slotID].npcType;
			npcX = server.npcHandler.npcs[slotID].absX;
			npcY = server.npcHandler.npcs[slotID].absY;
		c.faceNPC(slotID);
		c.debug("Case 155: NPCID is "+NPCID);				

		if(!misc.GoodDistance(npcX, npcY, c.absX, c.absY, 1)){
			c.walkingToNPC_slotID = slotID;
			c.walkingToNPC = 1;
			c.walkingToNPC_X = npcX;
			c.walkingToNPC_Y = npcY;
			return;
		}
		
		if(c.DIALOGUEHANDLER.exists(NPCID)){ //dialogue handler
			c.getClientMethodHandler().startDialogue(NPCID);
			return;
		}
		
		switch(NPCID){ //for conditionals
				case 3788:
					c.getClientMethodHandler().npcdialogue(NPCID, "The objective of c game of life or death","is to try and destroy all the portals","in the given timeframe.","",
							"If you do c successfully, you will be awarded points.","In exchange, I may give you","an item or two.");
					break;
					
					//put these here so we can attack them still
				case 253:
				case 254:
				case 255:
				case 256:
					c.getClientMethodHandler().npcdialogue(NPCID, "Everyone hears 'Khazard' and just immediately","assume we are the bad guys.");
					break;
		
		case 3792: //squire void knight
			c.getFrameMethodHandler().openUpShopFrame(2); //ranged shop
			break;
		case 3801: //squire void knight
			c.getFrameMethodHandler().openUpShopFrame(4); //general shop
			break;
		case 3793: //squire void knight
			c.getFrameMethodHandler().openUpShopFrame(3); //magic
			break;
		
		case 726:
		case 727:
		case 728:
		case 729:
		case 730:
			c.sendMessage("That one looks rather sick, I better not get too close.");
			break;
		
		case 162: //gnome trainer
			c.getFrameMethodHandler().openUpShopFrame(1);
			break;
		
		case 170: //gnome pilot		
			c.getFrameMethodHandler().setClientConfig(153, 0);
			c.getFrameMethodHandler().showInterface(802);
			break;
			
		case 473:
			if(c.isInArea(2517,3153,2526,3162)) c.getClientMethodHandler().selectOptionTravel2("Travel outside Maze?","Yes",2501,3192,"No",-1,-1);
			else c.getClientMethodHandler().selectOptionTravel2("Travel to Tree Gnome Village?","Yes",2518,3158,"No",-1,-1);
			break;
			
		case 1705: //ghost captain
			if(c.isInArea(3705,3495,3711,3498))
				c.getClientMethodHandler().selectOptionTravel2("Travel to Rellekka?", "Yes", 2620,3681, "No", -1, -1);
			else c.getClientMethodHandler().selectOptionTravel2("Travel to Port Phasmatys?", "Yes", 3705,3496, "No", -1, -1);
			break;
			
		case 3802: //squire
			if(c.isInArea(2656,2673,2661,2678))
				c.getClientMethodHandler().selectOptionTravel2("Travel to Port Phasmatys?", "Yes", 3689,3509, "No", -1, -1);
			else c.getClientMethodHandler().selectOptionTravel2("Travel to Void Knight Outpost?", "Yes", 2658,2673, "No", -1, -1);
			break;
			
		case 350: //omart
			c.getClientMethodHandler().selectOptionTravel2("Travel to West Ardougne?", "Yes", 2555,3268, "No", -1, -1);
			break;
			
		case 349: //kilron
			c.getClientMethodHandler().selectOptionTravel2("Travel to East Ardougne?", "Yes", 2559,3265, "No", -1, -1);
			break;
		
		case 1315:
			if(c.isInArea(2683,3271,2684,3275)){ //ardy docks
				c.getClientMethodHandler().selectOptionTravel2("Travel to Port Sarim?", "Yes", 3046,3203, "No", -1, -1);
				break;
			}
			c.getClientMethodHandler().selectOptionTravel2("Travel to Ardougne?", "Yes", 2683,3271, "No", -1, -1);
			break;
		
		case 290:
			c.getClientMethodHandler().npcdialogue(NPCID, "I can tend your wounds.");
			c.NewHP = c.getLevelForXP(c.playerXP[3]);
			c.sendMessage("The doctor restores you to full health.");
			break;
		
		case 2436:
			if(c.isInArea(2629,3691,2632,3696)){ //rellekka docks
				c.getClientMethodHandler().selectOptionTravel2("Travel to Port Khazard?", "Yes", 2676,3169, "No", -1, -1);
				break;
			}
			c.armadyl = 0;
			c.bandos = 0;
			c.getClientMethodHandler().selectOptionTravel2("Travel to God Wars?", "Yes", 2630,3690, "No", -1, -1);
			break;
		
		case 715: //Elena
			c.getClientMethodHandler().selectOptionTravel2("Sorry, under construction", "n/a", -1,-1, "n/a", -1, -1);
			break;
		
		case 1207:
			if(!c.isInArea(2174,3131,2201,3163)) //tyras pvp camp
				c.getClientMethodHandler().npcdialogue(NPCID, "There's sort of an infestation problem here,", "and that's all I'm allowed to say", "on the matter.");
			else c.getClientMethodHandler().npcdialogue(NPCID, "You don't look too tough.");
			break;
		
		case 1205: //tyras guard
			if(!c.isInArea(2174,3131,2201,3163)) //tyras pvp camp
				c.getClientMethodHandler().npcdialogue(NPCID, "Careful now. Lots of strange things", "have been happening lately.");
			else c.getClientMethodHandler().npcdialogue(NPCID, "Everything outside c camp is PVP.");
			break;
			
		case 1206: //tyras guard
			if(!c.isInArea(2174,3131,2201,3163)) //tyras pvp camp
				c.getClientMethodHandler().npcdialogue(NPCID, "People always complain whenever we", "are deployed to a new area.", "They don't realize we are there", "for their safety.");
			else c.getClientMethodHandler().npcdialogue(NPCID, "Everything outside c camp is PVP.");
			break;
		
		case 758: case 2324:
			skillMaster(NPCID, "Master Farmer", 14136,14137,14138, "Farming", c.playerFarming, new String[]{"I'll keep watch over your plants."});
			break;
		
		case 618:
			skillMaster(NPCID, "Examiner", 14130,14131,14132, "Firemaking", c.playerFiremaking, new String[]{"I am here with the Tyras research team.","We had reports of a bad","infestation on c island."});
			break;
		
		case 536:
			skillMaster(NPCID, "Valaine", 14133,14134,14135, "Woodcutting", c.playerWoodcutting, new String[]{"If you plan on woodcutting, you'll","need a hatchet. Thankfully,","I sell plenty of those."});
			break;
		
		case 2357:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14127,14128,14129, "Cooking", c.playerCooking, new String[]{"Would you like to look at my cooking wares?"});
			break;
			
		case 847:
			skillMaster(NPCID, "Head Chef", 14127,14128,14129, "Cooking", c.playerCooking, new String[]{"Ah! A fellow cook!","Got any tips?"});
			break;
		
		case 1778:
			skillMaster(NPCID, "William", 14124,14125,14126, "Fishing", c.playerFishing, new String[]{"In need of any fishing supplies?", "Trade with me for your fishing needs."});
			break;
			
		case 561:
			skillMaster(NPCID, "Shop Keeper", 14124,14125,14126, "Fishing", c.playerFishing, new String[]{"In need of any fishing supplies?", "Trade with me for your fishing needs."});
			break;
		
		case 1376: 
			skillMaster(NPCID, "Derrik", 14121,14122,14123, "Smithing", c.playerSmithing, new String[]{"Smithing is the greatest art", "one can take upon himself."});
			break;
		
		case 1860: 
			skillMaster(NPCID, "Brian", 14118,14119,14120, "Mining", c.playerMining, new String[]{"If you want to mine those", "rocks, you're going to need a pickaxe.", "Luckily, I offer a wide variety of pickaxes", "at my shop. You should take a look."});
			break;
		
		case 2356:
		case 1199:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14110,14109,14111, "Fletching", c.playerFletching, new String[]{"Pay attention to your surroundings,","the road can be rather dangerous at times."});
			break;
		
		case 805:
			skillMaster(NPCID, "Master Crafter", 14106,14107,14108, "Crafting", c.playerCrafting, new String[]{"Welcome to the Crafting Guild."});
			break;
			
		case 557:
			skillMaster(NPCID, "Wydin", 14106,14107,14108, "Crafting", c.playerCrafting, new String[]{"Welcome to my General Store."});
			break;
		
		case 1780:
			skillMaster(NPCID, "Larry", 14103,14104,14105, "Thieving", c.playerThieving, new String[]{"Being a travelling merchant is", "not the easiest of lifestyles."});
			break;
		
		case 437:
			skillMaster(NPCID, "Hamal", 14097,14098,14099, "Agility", c.playerAgility, new String[]{"Activating c portal will teleport you to","the gnome agility course.", "At that course are access to two","agility courses."});
			break;
		
		case 646: 
			if(c.isInArea(2853,3375,2862,3383)){
				skillMaster(NPCID, "Curator", 14091,14092,14093, "Runecrafting", c.playerRunecrafting, 
						new String[]{"You must use a talisman on c altar","to craft runes.",
						"My studies show that c altar can","craft Nature, Law, Death,","Blood, and Soul runes."});
			}
			else{
				skillMaster(NPCID, "Curator", 14091,14092,14093, "Runecrafting", c.playerRunecrafting, 
					new String[]{"You must use a talisman on c altar","to craft runes.",
					"My studies show that c altar can","craft Air, Mind, Water, Earth,","Fire, Body, Cosmic, and Chaos runes."});
			}
			break;
		
		case 358:
			skillMaster(NPCID, "Priest", 14085,14086,14087, "Prayer", c.playerPrayer, new String[]{"Saradomin walks with you."});
			break;
		
		case 945:
			skillMaster(NPCID, "Guide", 14094,14095,14096, "Hitpoints", c.playerHitpoints, new String[]{"Your spellbook and quest tab teleports","will allow access to essential locations."});
			break;
		
		case 944: //
			skillMaster(NPCID, "Combat Instructor", 14076,14077,14078, "Strength", c.playerStrength, new String[]{"I help train the new recruits.",});
			break;
		
		case 946:
			skillMaster(NPCID, "Magic Instructor", 14088,14089,14090, "Magic", c.playerMagic, new String[]{"Your fisrt two spells, air and ","water strike do no require","runes to cast."});
			break;
		
		case 527:
			skillMaster(NPCID, "Shop Assistant", 14088,14089,14090, "Magic", c.playerMagic, new String[]{"Runes and magical equipment","are my specialty."});
			break;
		
		case 552:
			skillMaster(NPCID, "Shop Keeper", 14073,14074,14075, "Attack", c.playerAttack, new String[]{"Please, have a look at my weapons."});
			break;
		
		case 553:
			skillMaster(NPCID, "Aubury", 14088,14089,14090, "Magic", c.playerMagic, new String[]{"Runes and magical equipment","are my specialty."});
			break;
		
		case 524:
			skillMaster(NPCID, "Shop Keeper", 14079,14080,14081, "Defence", c.playerDefence, new String[]{"If you're going into combat,","then you'll need some armor."});
			break;
		
		case 548: //Thessalia
			skillMaster(NPCID, "Thessalia", 14100, 14101, 14102, "Herblore", c.playerHerblore, new String[]{"Trade with me for your herbs,", "vials, and ingredients."});
			break;

		case 526:
			skillMaster(NPCID, "Shop Keeper", 14082,14083,14084, "Ranged", c.playerRanged, new String[]{"I sell Ranged equipment."});
			break;

		case 1596:
		case 1208: //slayer
			c.slayerMaster = NPCID;
			c.slayer4Options = true;
			c.getFrameMethodHandler().selectoption2("Hello", "I need a new Slayer task", "How much is left of my current task?", "Can I purchase a Slayer Crystal?", "(Click here for more options)");
			break;

		case 410:
			c.tokenexchange = true;
			c.getFrameMethodHandler().selectoption2("Options", "1 Server Token - Zamorak Godsword", "1 Server Token - Bandos Godsword", "1 Server Token - Saradomin Godsword", "More Options (Spirit Shields)");
			break;

		case 1055:
			c.ticketexchange = true;
			c.getFrameMethodHandler().selectoption2("Options", "Instructions", "Exchange tickets for rewards", "Cancel", "");
			break;
			
		case 1840: //quest, dwarf problems i
			if (c.ancients == 0)
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "If I could only figure out...", "where that stupid gauge...", "Huh? Sorry, speak with Stoutbeard", "");	

			if ((c.ancients == 1 || c.ancients == 2) && c.getInventoryHandler().IsItemInBag(271) == false){
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "Well, c stupid machine hasn't", "been working lately. The reason", "is i'm missing a pressure gauge!",
						"Now I need you to run to Mort'ton", "and buy one from Larry, the", "Traveling Merchant. I know for", 
						"a fact that he has one. Come", "back to me when you have the Gauge.");	
				c.ancients = 2;
				return;
			}
			if ((c.ancients == 1 || c.ancients == 2) && c.getInventoryHandler().IsItemInBag(271) == true){
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "Perfect, now the machine is back", "in working condition!", "Please speak with Stoutbeard again.", "");
				c.ancients = 3;
				c.getInventoryHandler().deleteItem(271,c.getInventoryHandler().getItemSlot(271),1);
				return;
			}
			if (c.ancients == 3)
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "Perfect, now the machine is back", "in working condition!", "Please speak with Stoutbeard again.", "");

			if ((c.ancients == 4 || c.ancients == 5) && c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "As the Stoutbeard told you,", "we need the other 2 remaining", "relic pieces. This book of",
						"Ancient Dwarven Lore contains", "information on their locations.", "I want you to read the book and", "find relic parts 2 and 3.", "");
				c.ancients = 5;
				c.sendMessage("Book of Dwarven Lore has been added to your inventory.");
				c.getInventoryHandler().addItem(4568, 1);
				return;
			}
			if ((c.ancients == 4 || c.ancients == 5) && c.getInventoryHandler().freeSlots() < 1)
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "Speak to me when your", "inventory is not full.", "", "");

			if (c.ancients == 5 && (c.getInventoryHandler().IsItemInBag(2374) == true && c.getInventoryHandler().IsItemInBag(2375) == true)){
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "Great! Now that the machine", "is in working order and we", "have all the relic pieces, we",
						"can now combine all the relic parts.", "Operate the machine to complete", "the relic. When you have the",
						"finished relic speak with me", "for your reward!");
				c.ancients = 6;
				c.getInventoryHandler().deleteItem(2374,c.getInventoryHandler().getItemSlot(2374),1);
				c.getInventoryHandler().deleteItem(2375,c.getInventoryHandler().getItemSlot(2375),1);
				return;
			}	
			if (c.ancients == 6){
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "Great! Now that the machine", "is in working order and we", 
						"have all the relic pieces, we", "can now combine all the relic parts.", 
						"Operate the machine to complete", "the relic, when you have the", "finished relic speak with me", 
						"for your reward!");
			}
			if (c.ancients == 7 && c.getInventoryHandler().IsItemInBag(2372) == true){
				c.ancients = 8;
				c.questPoints += 1;
				c.getPlayerLoginData().loadquestinterface();
				c.getFrameMethodHandler().menu(MenuHandler.ancientsfinished());
				return;
			}
			else if (c.ancients >= 8){
				c.getClientMethodHandler().npcdialogue("Engineer", NPCID, "Thank you for helping us.");
			}
			break;

		case 1794: //quest, dwarf problems
			if (c.ancients == 0 || c.ancients == 1 || c.ancients == 2){
				c.getClientMethodHandler().npcdialogue("Hammerspike Stoutbeard", NPCID, "So you must be the lad", "they sent to help me out?", "You look a little scrawny...", "but it'll do.",
						"Go speak with my engineer, he", "knows the specifics on", "what needs to be done.", "");
				c.ancients = 1;
				c.getPlayerLoginData().loadquestinterface();
			}
			if (c.ancients == 3 || c.ancients == 4){
				c.getClientMethodHandler().npcdialogue("Hammerspike Stoutbeard", NPCID, "The real reason I asked for you help", "wasn't to repair my machines.", "I just needed to make sure", "that you are reliable.",
						"What us dwarves have in our", "possession is an ancient ogre", "relic piece. The complete relic", "is compromised of three pieces.",
						"I need you to find the remaining", "two pieces. My engineer has more", "information regarding their whereabouts.", "");
				c.ancients = 4;
			}
			if (c.ancients >= 5 && c.ancients < 8){
				c.getClientMethodHandler().npcdialogue("Hammerspike Stoutbeard", NPCID, "Thank you for helping us!");
			}
			if (c.ancients == 8 || c.ancients == 9){
				c.getClientMethodHandler().npcdialogue("Hammerspike Stoutbeard", NPCID, "That ogre relic has brought", "darkness upon us.", "it has spawned a Black Demon", "of terrible power.",
						"The elves even sensed the disturbance", "and sent aid.", "Arianwyn has more information", "about the Demon.");
				c.ancients = 9;
				c.getPlayerLoginData().loadquestinterface();
			}
			if (c.ancients == 10 || c.ancients == 11){
				c.getClientMethodHandler().npcdialogue("Hammerspike Stoutbeard", NPCID, "We trapped the Demon in", "a secluded part of the desert.", "One of my men, Dondakan, has", "the ability to teleport...",
						"you to the Demon. Remember that", "you must use the staff", "when fighting c beast,", "else you will be destroyed.");
				c.ancients = 11;
			}
			else if (c.ancients >= 12){
				c.getClientMethodHandler().npcdialogue("Hammerspike Stoutbeard", NPCID, "You sent that Demon back to the abyss!", "For c, we thank you.");
			}
			break;

		case 1202:
			if (c.ancients <= 8){
				c.getClientMethodHandler().npcdialogue("Arianwyn", NPCID, "The Dwarves are stubborn and lack intelligence.", "But, they make up for it with", "admirable courage.","");
			}
			if (c.ancients == 9 && c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Arianwyn", NPCID, "After some research, it has been","concluded that you may be protected", "from the demon by using", "an ancient form of magic.",
						"This ancient magic is harnassed through", "a special staff. When you have", "have it equipped you will be", "protected from the demon's magic.");
				c.ancients = 10;
				c.getInventoryHandler().addItem(13308, 1);
				return;
			}
			if (c.ancients == 10){
				c.getClientMethodHandler().npcdialogue("Arianwyn", NPCID, "Use the staff to fight the Demon.", "Make sure you have it equipped during","your fight, else you will", "not be protected from its attacks.");
			}
			if (c.ancients == 9 && c.getInventoryHandler().freeSlots() < 1){
				c.getClientMethodHandler().npcdialogue("Arianwyn", NPCID, "Speak to me when your", "inventory is not full.");
			}
			else if (c.ancients >= 11){
				c.arianwyn = true;
				c.getFrameMethodHandler().selectoption2("Options", "Speak with Arianwyn", "Change Spellbooks", "Buy a Staff", "Nevermind");
			}
			break;

		case 1836:
			if (c.ancients < 11)
				c.getClientMethodHandler().npcdialogue("Dondakan", NPCID, "You're the adventurer that", "was sent to help us?", "Seems like we might be in trouble.", "");

			if (c.ancients >= 11){
				c.getClientMethodHandler().selectOptionTravel2("Teleport to the Black Demon?", "Yes", 3250, 2786, "No", -1,-1);
			}
			break;

		case 1709:
			if(c.isInArea(2033,4533,2042,4539)){ //throne room
				c.getClientMethodHandler().selectOptionTravel2("Travel to East Ardougne?", "Yes", 2662,3301, "No", -1,-1);
				break;
			}
			c.getClientMethodHandler().selectOptionTravel2("Help out Hammerspike Stoutbeard?", "Sure thing", 2037,4535, "No thank you", -1,-1);
			break;
			
		case 606:
			if (c.pkpoints >= 10){
				c.soulwars = true;
				c.getFrameMethodHandler().selectoption2("You have "+c.pkpoints+" PK Point(s)", "Hitpoints-"+c.playerLevel[3]*c.soulbonus+" exp-10pts", "Attack-"+c.playerLevel[0]*c.soulbonus+" exp-10pts", "Strength-"+c.playerLevel[2]*c.soulbonus+" exp-10pts", "More skills");
			}
			else c.getClientMethodHandler().npcdialogue("Rewards Master", NPCID, "You need at least ten PK points to purchase rewards");
			break;

		case 943:
			if(c.starter == 0)
				c.getClientMethodHandler().npcdialogue("Surivival Expert", NPCID, "Welcome newcomer!", "To get your c.starter kit, head", "Northwest of here and talk to", "Professor Oddenstein.",
						"He can be found North, in the clothes shop.");
			else {
				c.startleave = true;
				c.getFrameMethodHandler().selectoption("How can I help you?", "I'm ready to leave!", "Got any tips?", "...");
			}
			break;

		case 286: //Professor Oddenstein
			if(c.starter == 0){
				c.starter4Options = true;
				c.getFrameMethodHandler().selectoption2("Combat Style?", "Warrior", "Magic", "Ranged", "Nevermind.");
			}
			else c.getClientMethodHandler().npcdialogue("Oddenstein", NPCID, "The Surivival Expert has further instructions.");
			break;

		case 1303:
			if (c.getInventoryHandler().IsItemInBag(2349) == false && c.deadtele != 1){
				c.deadopt = true;
				c.getFrameMethodHandler().selectoption("How can I help you?", "Instructions", "I need a pickaxe!", "...");
			}
			else if (c.getInventoryHandler().IsItemInBag(2349) == true && c.deadtele != 1){
				c.getClientMethodHandler().npcdialogue("Grave Keeper", NPCID, "", "Well done, please use the portal", "to exit the graveyard.", "");
				c.getInventoryHandler().deleteItem(2349,c.getInventoryHandler().getItemSlot(2349),1);
				c.deadtele = 1;
			}
			else if (c.deadtele == 1){
				c.getClientMethodHandler().npcdialogue("Grave Keeper", NPCID, "", "Well done, please use the portal", "to exit the graveyard.", "");
			}
			break;

		case 171: //Rune mysteries, quest, brimstail
			if (c.RM == 0){
				c.getClientMethodHandler().npcdialogue("Brimstail", NPCID, "Mysteries need solving...", "Leave me to my peace.");	
			}
			else if (c.RM == 1 && c.getInventoryHandler().IsItemInBag(1438) == true){
				c.getClientMethodHandler().npcdialogue("Brimstail", NPCID, "You say Frumscone wanted me to take a look", "at something? Hmm, one moment...","This is quite the artifact! I wonder","how he got a hold of c?",
						"Please, can you run back to Frumscone","and inform him I am very interested.");	
				c.getInventoryHandler().deleteItem(1438,c.getInventoryHandler().getItemSlot(1438),1);
				c.RM = 2;
			}
			else if (c.RM == 1 && c.getInventoryHandler().IsItemInBag(1438) == false){
				c.getClientMethodHandler().npcdialogue("Brimstail", NPCID, "Mysteries need solving...", "Leave me to my peace.");	
			}
			else if (c.RM == 2){
				c.getClientMethodHandler().npcdialogue("Brimstail", NPCID, "That talisman is quite the artifact.","With the correct notes, I may be able","to piece together some sort of discovery.");	
			}
			else if (c.RM == 3 && c.getInventoryHandler().IsItemInBag(291) == true){
				c.getClientMethodHandler().npcdialogue("Brimstail", NPCID, "So here are the notes? Hmm...", "They contain instructions on navigating to a", "hidden mine of what is called",
						"Rune Essence.", "If I follow these instructions carefully", "I should be able to teleport someone", "there.", "",
						"Thanks for the help lad,", "your job is done for now!");
				c.sendMessage("You have completed Rune Mysteries.");
				c.getInventoryHandler().deleteItem(291,c.getInventoryHandler().getItemSlot(291),1);
				c.RM = 4;
				c.questPoints += 1;
				c.getPlayerLoginData().loadquestinterface();
			}
			else if (c.RM == 4){
				c.getClientMethodHandler().selectOptionTravel2("Mine rune essence?", "Yes", 2911, 4833, "No", -1,-1);
			}
			break;

		case 460:
			if (c.RM == 0 && c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Hello there! I hope you wouldn't", "mind doing me a favor now?", "Take c talisman, and run it", "to my friend Brimstail.", "He was last seen pacing"
						,"around, near the South", "entrance of Falador.");
				c.sendMessage("An Air Talisman has been given to you.");
				c.getInventoryHandler().addItem(1438, 1);
				c.RM = 1;
				c.getPlayerLoginData().loadquestinterface();
			}
			else if (c.RM == 0 && c.getInventoryHandler().freeSlots() < 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "I could really use some help from", "someone like you! Can", "you try speaking with me when", "your inventory isn't so full.");
			}
			else if (c.RM == 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Hopefully Brimstail will be interested in","that artifact.");
			}
			else if (c.RM == 2 && c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Brimstail said he was interested? Wonderful,","with his magical prowess and my notes","he may be able to make something","of all of c.",
						"Here are my notes. I'd like","you to take them back to Brimstail.");
				c.sendMessage("Notes have been added to your inventory.");
				c.getInventoryHandler().addItem(291, 1);
				c.RM = 3;
			}
			else if (c.RM == 2 && c.getInventoryHandler().freeSlots() < 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Brimstail said he was interested? Wonderful,","with his magical prowess and my notes","he may be able to make something","of all of c.",
						"I'd give you my notes, but it seems like","you have no more room in your inventory.");
			}
			else if (c.RM == 3){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Hopefully Brimstail can make sense of all of c.");
			}
			else if (c.RM == 4){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "I really do appreciate the help.","You can use the magic portal to mine Rune Essence.");
			}
			break;

		case 518: //captain shanks
			if(c.isInArea(3038,3201,3043,3205)){
				c.getClientMethodHandler().selectOptionTravel2("Travel to the tutorial island?", "Yes", 3684,2953, "No", -1, -1);
				break;
			}
			if(c.isInArea(3682,2953,3685,2956)){ //tutorial island
				if(c.pirate < 10){
					int remaining = 10-c.pirate;
					c.getClientMethodHandler().npcdialogue(518, "These c.pirate ships are blocking us","from safely leaving. They landed", "North of here. I need you to kill",""+remaining+" c.pirates before we can leave.");
					break;
				}
				if(c.pirate == 10){ //quest completed
					if(c.getInventoryHandler().freeSlots() > 1 ||c.getInventoryHandler().hasItemAny(995, 1)){
						c.getFrameMethodHandler().sendQuest("@gre@New Beginnings", 7338);
						c.pirate = 11;
						c.getFrameMethodHandler().menu(c.getMenuHandler().newBeginnings());
						c.getInventoryHandler().addItem(995,5000);
						c.questPoints += 1;
					}
					else c.getClientMethodHandler().npcdialogue(518, "I have a reward for you!","But, you need more space in your inventory.");
					break;
				}
			}
			c.getClientMethodHandler().selectOptionTravel2("Travel to Port Sarim?", "Yes", 3041,3202, "No", -1, -1);
			break;
			
		case 376: //captain tobias
			if(c.isInArea(3026,3215,3030,3220)){
				c.getClientMethodHandler().selectOptionTravel2("Travel to Karamja?", "Yes", 2956, 3146, "No", -1, -1);
				break;
			}
			c.getClientMethodHandler().selectOptionTravel2("Travel to Port Sarim?", "Yes", 3029, 3217, "No", -1, -1);
			break;

		case 381: 
			if(c.isInArea(2831,3334,2836,3338)){
				c.getClientMethodHandler().selectOptionTravel2("Travel to Port Sarim?", "Yes", 3048, 3234, "No", -1, -1);
				break;
			}
			if (c.getInventoryHandler().freeSlots() < 24)
				c.getClientMethodHandler().npcdialogue("Captain Barnaby", 381, "If you wish to travel to Entrana,","then you are only permitted to", "carry up to four items.");
			else c.getClientMethodHandler().selectOptionTravel2("Travel to Entrana?", "Yes", 2832, 3335, "No", -1, -1);
			
			break;

		case 599:
			c.getFrameMethodHandler().showInterface(3559);
			break;

		case 1697:
			if(c.getInventoryHandler().IsItemInBag(681)){
				if(c.wb == 0){
					c.getClientMethodHandler().npcdialogue("Ghost", 1697, "You must help!", "I have recieved a disturbing message from my", "good friend Aggie...", "It seems that she has sensed something...",
							"She has sensed a gathering of spirits", "that plan to take over the world...", "Please go to her and help her!", "", 
							"Speak with me again, I can teleport you to",	"her if you wish.");
					c.wb = 1;
				}
				else{
					c.getClientMethodHandler().selectOptionTravel2("Travel to Aggie's House?", "Yes", 3464, 3552, "No", -1,-1);
				}												
			}
			else{
				c.getClientMethodHandler().npcdialogue("Ghost", 1697, "...");
				c.sendMessage("The ghost mumbles something you do not understand.");
			}
			break;

		case 922:

			if(c.wb == 0)
				c.getClientMethodHandler().npcdialogue("Aggie", 922, "Scum! Get away!");

			if(c.wb == 1){
				if(c.getInventoryHandler().freeSlots() >= 1){
					c.getClientMethodHandler().npcdialogue("Aggie", 922, "So you're the hero my friend told me", "about? He said you solved some bandit", "Problem for the village or something...", "",
							"Anyways! I need your help dearly!", "Evil spirits plan on rising and we", "Must stop them! Take c book, it", "contains ingredients I need you to gather.",
							"When you bring them all to me I can make you", "A special mix that will make you able to enter", "Their tombs safely.");
					c.sendMessage ("A Battered Book has been added to your inventory.");
					c.wb = 2;
					c.getInventoryHandler().addItem(2886, 1);
					break;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Aggie", 922, "Speak with me when you clear up", "space in your inventory.");
					break;
				}

			}
			if(c.wb == 2){
				c.getClientMethodHandler().npcdialogue("Aggie", 922, "Hurry, get the ingredients.");
				break;
			}

			if(c.wb == 3){
				if(c.getInventoryHandler().IsItemInBag(1940) == true && c.getInventoryHandler().IsItemInBag(1601) == true && c.getInventoryHandler().IsItemInBag(353) == true && c.getInventoryHandler().IsItemInBag(229) == true){
					c.getClientMethodHandler().npcdialogue("Aggie", 922, "Finally you return with the ingredients.", "After you drink c special mix", "talk to me.");
					c.getInventoryHandler().deleteItem(1940,c.getInventoryHandler().getItemSlot(1940),1);
					c.getInventoryHandler().deleteItem(1601,c.getInventoryHandler().getItemSlot(1601),1);
					c.getInventoryHandler().deleteItem(353,c.getInventoryHandler().getItemSlot(353),1);
					c.getInventoryHandler().deleteItem(229,c.getInventoryHandler().getItemSlot(229),1);
					c.getInventoryHandler().addItem(197, 1);
					c.sendMessage("A special mix has been added to your inventory.");
					c.wb = 4;
					break;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Aggie", 922, "You read the book? Then", "go get the ingredients!");
					break;
				}

			}
			if(c.wb == 4){
				if(c.smix == 1){
					c.getClientMethodHandler().npcdialogue("Aggie", 922, "Alright, you seem ready. Now listen,", "east of here is a Mausoleum, you", "need to go there and push aside the", "Memorial.",
							"This will allow you to go into the tombs.", "Once you have entered you must find a chest", "containing a fire orb, it is the spirits", "power source, without it, they're useless.");
					c.wb = 5;
					break;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Aggie", 922, "Drink the mix, it will", "make you immune to the evils", "of the tomb.");
					break;
				}

			}
			if(c.wb == 5){
				c.getClientMethodHandler().npcdialogue("Aggie", 922, "Alright, you seem ready. Now listen,", "east of here is a Mausoleum, you", "need to go there and push aside the", "Memorial.",
						"This will allow you to go into the tombs.", "Once you have entered you must find a chest", "containing a fire orb, it is the spirits", "power source, without it, they're useless.");
				break;
			}

			if(c.wb >= 6){
				c.getClientMethodHandler().npcdialogue("Aggie", 922, "Without you we would all be dead.");
				break;
			}

			break;

		case 220:
			if (c.playerLevel[10] < 90){
				c.getClientMethodHandler().npcdialogue("Fishing King", 220, "Ah! A fellow fisherman! Talk", "to me when you have reached", "level 90 fishing! I may have something", "that I need you to do!");
				break;
			}

			if(c.playerLevel[10] >= 90 && c.ST == 0){
				c.getClientMethodHandler().npcdialogue("Fishing King", 220, "Finally you've reached level 90!", "What I need you to do is take Magic", "Logs and use them on a Prayer Potion(3)", "to make a special mix!",
						"When you are done with c", "come back and talk to me for", "your next set of instructions.");
				c.ST = 1;
				break;
			}

			if(c.ST > 0){
				if(c.ST == 1){
					if(c.getInventoryHandler().IsItemInBag(705)){
						c.getClientMethodHandler().npcdialogue("Fishing King", 220, "Wonderful! Now using the mix, I'll", "make special fish food used for", "sea turtle catches.", "You now need to use Poison on the Fish food.",
								"You can get Poison by talking to the", "Wizard in the Port Sarim Tavern.", "When you have the Poisoned Fish Food", "return to me.");
						c.ST = 2;
						c.getInventoryHandler().deleteItem(705,c.getInventoryHandler().getItemSlot(705),1);
						c.getInventoryHandler().addItem (272, 1);
						c.sendMessage ("Fish Food has been added to your inventory.");
						c.sendMessage ("Mixed Chemicals has been removed from your inventory.");
					}
					else c.getClientMethodHandler().npcdialogue("Fishing King", 220, "I told you to return to me when you", "have the mix! Just use", "Prayer Potion(3) with", "magic logs.");
					break;
				}
				if(c.ST == 2){
					if(c.getInventoryHandler().IsItemInBag(274)){
						c.getClientMethodHandler().npcdialogue("Fishing King", 220, "What I want from you is a Sea Turtle.", "To catch a raw Sea Turtle, go to the fishing", "spots Northwest of here. ", "",
								"Make sure you have the poison fish food and", "a harpoon with you when you go.");
						c.ST = 4;
						break;
					}
					else{
						c.getClientMethodHandler().npcdialogue("Fishing King", 220, "I told you to return to me when you", "have the Poisoned Fish Food.", "Speak to the Wizard in", "the Port Sarim Tavern.");
						break;
					}
				}
			}
			if(c.ST == 3){
				if(c.getInventoryHandler().IsItemInBag(274)){
					c.getClientMethodHandler().npcdialogue("Fishing King", NPCID, "Now catch a raw sea turtle.", "Have the poisoned", "fish food and a harpoon in your inventory!", "the fishing spots are",
							"Northwest of here.", "You will cross over a bridge to get there.");
					c.ST = 4;
					break;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Fishing King", 220, "I told you to use the Poison", "on the fish food.");
					break;
				}
			}
			if(c.ST == 4){
				if(c.getInventoryHandler().IsItemInBag(395)){
					c.getClientMethodHandler().npcdialogue("Fishing King", 220, "A true fisherman indeed!", "Cook it, and then bring it back to me.");
					c.ST = 5;
					break;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Fishing King", 220, "Now catch a raw sea turtle.", "Have the poisoned", "fish food and a harpoon in your inventory!", "the fishing spots are",
							"Northwest of here.", "You will cross over a bridge to get there.");
					break;
				}
			}
			if(c.ST == 5){
				if(c.getInventoryHandler().IsItemInBag(397)){
					c.getFrameMethodHandler().Menu(MenuHandler.STFinish());
					c.getInventoryHandler().deleteItem(397,c.getInventoryHandler().getItemSlot(397),1);
					c.getInventoryHandler().addItem (775, 1);
					c.sendMessage("You recieve 200,000 fishing EXP.");
					c.sendMessage("You recieve 200,000 cooking EXP.");
					c.sendMessage("Cooking Gauntlets were added to your inventory.");
					c.getClientMethodHandler().addSkillXP(200000, 10);
					c.getClientMethodHandler().addSkillXP(200000, 7);
					c.ST = 6;
					c.STC = 1;
					c.questPoints += 1;
					c.getPlayerLoginData().loadquestinterface();
					break;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Fishing King", 220, "Cook it the sea turtle, and", "then bring it back to me.");
					break;
				}
			}
			if(c.ST >= 6){
				c.getClientMethodHandler().npcdialogue("Fishing King,", 220, "When you wear those gloves, you will", "never burn the item you are", "cooking!");
				break;
			}
			break;

		case 1263:
			if(c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Wizard", 1263, "Poison? You didn't get it from me.");
				c.getInventoryHandler().addItem (273, 1);
				c.sendMessage ("Poison has been added to your inventory.");
				if(c.ST == 2)
					c.ST = 3;
				break;
			}
			else{
				c.getClientMethodHandler().npcdialogue("Wizard", 1263, "I have something that may interest you,", "try freeing up some inventory", "space beforehand.");
				break;
			}

		case 1302:
			if(c.wb != 3){
				c.getClientMethodHandler().npcdialogue("Fisherman", 1302, "The fish just ain't bitin' today.");
				break;
			}
			else{
				if(c.getInventoryHandler().freeSlots() >= 1){
					c.getClientMethodHandler().npcdialogue("Fisherman", 1302, "Need a Mackerel ya say?", "Happened to have caught one earlier.", "Here ya go.");
					c.wbMackerel = 1;
					c.sendMessage("Raw Mackerel has been added to your inventory.");
					c.getInventoryHandler().addItem (353, 1);
					break;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Fisherman", 1302, "Need a Mackerel ya say?", "Happened to have caught one earlier.", "Your inventory's full though.");
					break;
				}
			}

		case 1283:
			if(c.easterevent == 1) c.getClientMethodHandler().npcdialogue("Swensen The Navigator", 1283, "The gardner ghost is north by the broken", "fire altar, and that's all I know", "of c matter.");
			else c.getClientMethodHandler().npcdialogue("Swensen The Navigator", 1283, "The distant lands have always interested me...", "Too bad I have never had the chance of", "leaving Mort'ton.");
			break;

		case 845:
			if(c.easterevent == 1){
				if(c.getInventoryHandler().freeSlots() >= 1){
					c.getClientMethodHandler().npcdialogue("Horacio", 845, "So you want to solve the problem?", "Good. My father used to be a ghost", "Whisperer, before he died he past on", "to me a Talisman.",
							"As long as it is in your inventory", "You can communicate with the undead.", "The gardener ghost is north of here by", "the broken fire altar.",
							"Here, take the Talisman and talk to", "the ghost with it.");
					c.sendMessage ("Zaros Talisman has been added to your inventory");
					c.getInventoryHandler().addItem(681, 1);
					c.eastergift = 1;
					c.easterevent = 2;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Horacio", 845, "I have something for you, but", "your inventory is full.");
				}
			}
			else c.getClientMethodHandler().npcdialogue("Horacio", 845, "This run-down village wasn't exactly my childhood dream.");
			break;

		case 1080:
			if(c.easterevent == 1) c.getClientMethodHandler().npcdialogue("Eohric", 1080, "Ghosts? I am scared of ghosts.", "There is a rumor floating around the village", "that Horacio knows about ghosts.");
			else c.getClientMethodHandler().npcdialogue("Ehoric", 1080, "Swamps as far as the eye can see...");
			break;

		case 1675:
			if(c.getInventoryHandler().IsItemInBag(681)){
				if(c.eastergift == 1){
					if(c.getInventoryHandler().freeSlots() >= 1){
						c.getClientMethodHandler().npcdialogue("Gardner Ghost", 1675, "The thief that took my head dropped", "a book while running away.");
						c.eastergift = 2;
						c.getInventoryHandler().addItem(5520, 1);
						c.sendMessage("The ghost hands you a book.");
					}
					else c.getClientMethodHandler().npcdialogue("Gardner Ghost", 1675, "The theif that took my head dropped", "a book while running away.", "Your inventory is full though.");
				}				
				if(c.eastergift == 2) c.getClientMethodHandler().npcdialogue("Gardnet Ghost", 1675, "Good luck.");
				if(c.eastergift == 3 ){
					if(c.getInventoryHandler().IsItemInBag(4197)){
						if(c.getInventoryHandler().freeSlots() >= 2){
							c.getInventoryHandler().deleteItem(4197,c.getInventoryHandler().getItemSlot(4197),1);
							c.getFrameMethodHandler().Menu(MenuHandler.BarrowsQuest2());
							c.getInventoryHandler().addItem(14139, 1);
							c.getInventoryHandler().addItem(14140, 1);
							c.getClientMethodHandler().addSkillXP(50000, 17);
							c.getClientMethodHandler().addSkillXP(10000, 18);
							c.getClientMethodHandler().addSkillXP(50000, 19);
							c.eastergift = 4;
							c.questPoints += 1;
							c.getPlayerLoginData().loadquestinterface();
						}
						else c.getClientMethodHandler().npcdialogue("Gardner Ghost", 1675, "I have a reward for you, but you", "need at least two empty inventory", "spaces.");
					}
					else c.getClientMethodHandler().npcdialogue("Gardner Ghost", 1675, "Good luck.");
				}
				if(c.eastergift >= 4) c.getClientMethodHandler().npcdialogue("Gardner Ghost", 1675, "Thank you dearly.");
			}
			else{
				c.getClientMethodHandler().npcdialogue("Gardner Ghost", 1675, "...");
				c.sendMessage("The ghost mumbles something you do not understand.");
			}
			break;

		case 1696:
			if(c.easterevent < 4){
				c.getClientMethodHandler().npcdialogue("Old Man", 1696, "The villagers have been complaining", "about a ghost problem that needs to", "be solved, you should ask around the", "village and see what you can do.");
				if(c.easterevent == 0) c.easterevent = 1;
			}
			if(c.easterevent >= 4) c.getClientMethodHandler().npcdialogue("Old Man", 1696, "I heard you had helped out the village.");						
			break;

		default:
			c.println("Case 155: Unhandled NPCID : "+NPCID);
			break;
		}
	}
	
}

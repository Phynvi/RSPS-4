package clientHandlers;
import playerData.client;
import root.misc;
import root.server;
import skills.Thieving;


public class NPCClickHandler {

	private client c = null;

	public NPCClickHandler(client pc){
		this.c = pc;
	}

	public void npcThirdClick(int slotID){
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
			c.error("npcThirdClick : "+e.toString());
			return;
		}		
		c.faceNPC(slotID);
		c.debug("npcThirdClick: NPCID is "+NPCID);			

		if(!misc.GoodDistance(npcX, npcY, c.absX, c.absY, 1)){
			c.walkingToNPC_slotID = slotID;
			c.walkingToNPC = 3;
			c.walkingToNPC_X = npcX;
			c.walkingToNPC_Y = npcY;
			return;
		}

		switch(NPCID){
		case 1599:
			c.getFrameMethodHandler().openUpShopFrame(5); //Duradel's shop
			return;
		}

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
		case 494: case 495: case 496: case 497: case 498: case 499: case 2355: case 2354: case 166: //Bankers
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

		case 582:
		case 531: case 530: case 557: case 545: case 1699: case 2352: case 570: case 571: case 876: case 563: case 2154: case 516:
			c.getFrameMethodHandler().openUpShopFrame(4); //General store
			return;

		case 538: case 1301: case 2353: case 1039: case 875: case 2162:
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

		case 2198:
			c.getFrameMethodHandler().openUpShopFrame(13); //Kebab Shop - Keldagrim
			return;

		case 257: //257 dwarf
			c.getFrameMethodHandler().openUpShopFrame(14); //Gem stall owner
			return;

		case 2151: //2151 dwarf
			c.getFrameMethodHandler().openUpShopFrame(15); //Warhammers
			return;

		case 1860: //582 = dwarf
			c.getFrameMethodHandler().openUpShopFrame(16); //Pickaxe
			return;

		case 2156: //2156 - dwarf
			c.getFrameMethodHandler().openUpShopFrame(17); //Bakery Shop
			return;

		case 558: case 561: case 576: case 592: case 517:
			c.getFrameMethodHandler().openUpShopFrame(18); //Fishing Supplies
			return;

			
		case 2158: //2158 - dwarf
			c.getFrameMethodHandler().openUpShopFrame(19); //crafting
			return;

		case 2520: case 2521:
			c.getFrameMethodHandler().openUpShopFrame(20, Item.TRADING_STICKS); //Gabooty Shop
			return;

		case 580:
			c.getFrameMethodHandler().openUpShopFrame(21); //Flynn's mace
			return;

		case 581: //Wayne's Chains
			c.getFrameMethodHandler().openUpShopFrame(22);
			
		case 519: case 2152:
			c.getFrameMethodHandler().openUpShopFrame(23); //Basic Weapons
			return;
			
		case 584:
			c.getFrameMethodHandler().openUpShopFrame(24); //Gems
			return;

		case 524: case 525: case 1041: case 873: case 2153: case 577:
			c.getFrameMethodHandler().openUpShopFrame(26); //Armor
			return;
			
		case 579:
			c.getFrameMethodHandler().openUpShopFrame(35); //Drogo's Mining Emporium
			break;

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

		case 594:
			c.getFrameMethodHandler().openUpShopFrame(47); //Nurmof's Pickaxe Shop
			return;

		case 793: case 1300: case 1042: case 512:
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

		case 797:
			c.getFrameMethodHandler().openUpShopFrame(110);
			return;

		case 1168:
			c.getFrameMethodHandler().select2Options(36, "Options", "Use 10% favour to open shop", "Nevermind");
			return;

		case 1164:
			c.getFrameMethodHandler().select2Options(37, "Options", "Use 10% favour to open shop", "Nevermind");
			return;

		case 3788:
			c.getFrameMethodHandler().openUpShopFrame(60, -1); //Void Knight Shop rewards
			return;
		}

		/* Second Click, not Shops */
		switch(NPCID){

		case 1217:
			c.getClientMethodHandler().dialogue(1217, "Just trimming the birds.");
			break;
		
		case 878:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14079,14080,14081, "Defence", c.playerDefence, new String[]{"Yanille needs to be heavily fortified and watched","at all times. The threats of","undead ogres lurk just West of us.",
				"That Wizard thinks his magic can save","this town? No way, it's through","strength in arms that we survive."});
			break;

		case 1399: case 321: case 324: case 333: case 312: case 1332: // cage/harpoon
		case 1405: case 1406: case 313: case 322: case 334: case 1191: case 1333: // net/harpoon
		case 2067: case 2068: case 316: case 319: case 320: case 323: case 325: case 326: case 327: case 330: case 1331:  case 332: // net/bait
		case 3019: case 314: case 315: case 317: case 318: case 328: case 329: case 331: case 927: case 1189: case 1190: case 309:
		case 310: case 311: // lure/bait
			c.getSkillHandler().getFishingHandler().fishingClick2(NPCID);
			break;	

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
			c.getFrameMethodHandler().select4Options(5,"Rewards", "100 Tickets-"+(c.playerLevel[16]*10000)+" Agility EXP", "250 Tickets-Void Knight Gloves", "500 Tickets-Agility Armor", "Cancel");
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
		c.getFrameMethodHandler().select4Options(6,"Options", "Talk to "+c.skillMasterName, "Purchase a "+c.skillName+" Hood or Cape", "", "Nevermind.");
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

		case 938: //ranalph devere
			c.getClientMethodHandler().dialogue(938, "@pla@","Hello There.","","",
					"","I know you're not a Black Knight.","","",
					"@pla@","Huh? I don't know what you're talking about!","","",
					"","He can smell it.","","",
					"@npc@0221","Smell!","","",
					"","@pla@Oh, well that's great.","","",
					"So, what did you want? Must","be important since you're","being all sneaky.","",
					"@pla@","Why do you want to poison people?","","",
					"@npc@0221","Smell!","","",
					"Ok, calm down there.","Did you know that Rimmington was","built on top of a battlefield?","",
					"@pla@","Today I learned.","","",
					"It is quite the ancient battlefield actually.","It's where I died.","And they had the nerve","to put a village up there.",
					"It disturbed me and a few of my friends.","I hope you don't think I'm the only one","seeking revenge?","",
					"There's a couple more like me.","And we want revenge for being awoken.","I think poison can do the trick.","",
					"@npc@0221","Kill?","","",
					"Yes, I suppose.");
			break;
		
		case 605:
			if(c.PD.getValue() == 0 || c.PD.getValue() == 1 || c.PD.getValue() > 4)
				c.getFrameMethodHandler().select4Options(24, "Topics", "Any news?", "Need help with anything?", "", "Nevermind");
			else if(c.PD.getValue() == 2)
				c.getFrameMethodHandler().select4Options(24, "Topics", "Any news?", "I have some important plans for you.", "", "Nevermind");
			else if(c.PD.getValue() == 3)
				c.getFrameMethodHandler().select4Options(24, "Topics", "Any news?", "What am I supposed to do?", "", "Nevermind");
			else if(c.PD.getValue() == 4)
				c.getFrameMethodHandler().select4Options(24, "Topics", "Any news?", "I have stopped the poisoning!", "", "Nevermind");
			break;
		
		case 606:
			if(c.PD.getValue() == 0)
				c.getFrameMethodHandler().select4Options(23, "Topics", "Any news?", "Need help with anything?", "", "Nevermind");
			else if(c.PD.getValue() == 1)
				c.getFrameMethodHandler().select4Options(23, "Topics", "Any news?", "I've returned with the plans!", "", "Nevermind");
			else if(c.PD.getValue() > 1)
				c.getFrameMethodHandler().select4Options(23, "Topics", "Any news?", "What am I supposed to do?", "", "Nevermind");
			break;
		
		//hairdresser
		case 598: 
			c.getFrameMethodHandler().showInterface(3559);
			break;
		
		case 201:
			c.sendMessage("I do not think I want to talk to him. He looks angry.");
			break;
		
		case 410: //mysterious old man
			if(c.getInventoryHandler().freeSlots() == 0) 
				c.getClientMethodHandler().dialogue(NPCID, "Speak to me when your inventory is not full.");
			else{
				c.getButtonClickHandler().mysteriousOldManFirst = true;
				c.getClientMethodHandler().dialogue(NPCID, "","@pla@What's going on?","","",
						"","Ready to win a prize?","","",
						"@pla@","Umm, sure?","","",
						"Here we go!");
			}
			break;

		case 697: //kennith
			if(c.slug == 0)
				c.getClientMethodHandler().dialogue(697, "Those slugs are too scary! I'm","not going anywhere!");
			else if(c.slug == 2 || c.slug == 3 || c.slug == 1)
				c.getFrameMethodHandler().select2Options(43, "Topics", "Your mother is looking for you", "Nevermind.");
			else if(c.slug == 4)
				c.getFrameMethodHandler().select2Options(43, "Topics", "Use this torch to get out of here", "Nevermind.");
			else if(c.slug == 5)
				c.getClientMethodHandler().dialogue(697, "I'll use the torch to get out of here!");
			break;

		case 695: //bailey
			if(c.slug == 0) 
				c.getClientMethodHandler().dialogue(695, "Ever since we started fishing up those","slugs, my men have began acting strange.");
			else if(c.slug == 1 || c.slug == 2)
				c.getFrameMethodHandler().select4Options(22, "Topics", "Any news?", "Have you seen Kennith?", "", "Nevermind");
			else if(c.slug == 3)
				c.getFrameMethodHandler().select4Options(22, "Topics", "Any news?", "Have you seen Kennith?", "What are the slug's weak against?", "Nevermind");
			else if(c.slug > 3)
				c.getFrameMethodHandler().select4Options(22, "Topics", "Any news?", "Have you seen Kennith?", "I lost the torch.", "Nevermind");
			break;

		case 696: //caroline
			if(c.slug >= 0)
				c.getFrameMethodHandler().select4Options(21, "Topics", "Any news?", "You look worried", "", "Nevermind");
			else if (c.slug == 5) c.getClientMethodHandler().dialogue(NPCID, "Thank you for your help.");
			break;

		case 702: case 703: case 704: //fishing platform
			c.sendMessage("The fisherman appears to be in a zombie-like state.");
			break;

		case 675:
			if(c.TUP < 2) c.getClientMethodHandler().dialogue(NPCID, "I've got quite a few workers to manage here.");
			else if(c.TUP == 2){
				c.getFrameMethodHandler().select4Options(20, "Topics", "Any news?", "I have a sketch for you", "", "Nevermind");
			}
			else if(c.TUP == 3){
				c.getFrameMethodHandler().select4Options(20, "Topics", "Any news?", "I have 20 Mahogany Logs for you", "", "Nevermind");
			}
			else if(c.TUP > 3){
				c.getFrameMethodHandler().select4Options(20, "Topics", "Any news?", "Can you make me a Totem? (5 Teak Logs)", "", "Nevermind");
			}

			break;

		case 500:
			if(c.TUP == 0) c.getFrameMethodHandler().select4Options(18, "Ask Mosol Rei", "Any news?", "Need help with anything?", "", "Nevermind");
			else if(c.TUP >= 1 && c.TUP < 6) c.getFrameMethodHandler().select4Options(18, "Ask Mosol Rei", "Any news?", "Need help with anything?", "I lost the Wampum Belt", "Nevermind");
			else if(c.TUP == 6){
				c.getFrameMethodHandler().select4Options(18, "Topics", "Any news?", "I've returned with a Gilded Totem", "", "Nevermind");
			}
			else if(c.TUP == 7){
				c.getClientMethodHandler().dialogue(NPCID, "So far, the Totem has been working.");
			}
			break;

		case 511: //vigroy cart 
			if(c.isInArea(2777, 3208, 2783, 3215)){ //brimhaven
				if(c.TUP == 7){
					c.getClientMethodHandler().selectOptionTravel2("Travel to Shilo Village?", "Yes", 2832,2954, "no",-1,-1);
				}
			}
			else c.getClientMethodHandler().selectOptionTravel2("Travel to Brimhaven?", "Yes", 2779,3210, "no",-1,-1);
			return;

		case 512: //shilo village pub
			c.getFrameMethodHandler().openUpShopFrame(51); //Tavern Shop
			return;

		case 1042: 
			if(c.barrowed < 1){
				c.getFrameMethodHandler().select2Options(42, "Options", "Any news?", "Need help with anything?");
			}
			else if (c.barrowed == 1){
				c.getFrameMethodHandler().select4Options(17, "Options", "Any news?", "What did you need help with?", "I have a full set of armor for you", "Nevermind");
			}
			else if (c.barrowed > 1){
				c.getFrameMethodHandler().select2Options(42, "Options", "Any news?", "Need help with anything?");
			}
			return;

		case 2520: case 2521:
			c.getFrameMethodHandler().openUpShopFrame(20, Item.TRADING_STICKS); //Gabooty Shop
			return;

		case 1162:
			if(c.TUP == 0 || c.TUP == 7) c.getFrameMethodHandler().select2Options(40, "Options", "Exchange 50 Favour for 50 Trading Sticks", "Nevermind");
			else if(c.TUP == 1){
				if(c.getInventoryHandler().hasItem(625)) c.getFrameMethodHandler().select4Options(19, "Topics", "Exchange Favour", "I brought this Wampum Belt for you", "", "Nevermind");
				else c.getFrameMethodHandler().select2Options(40, "Options", "Exchange 50 Favour for 50 Trading Sticks", "Nevermind");
			}
			else if(c.TUP == 2 || c.TUP == 3) 
				c.getFrameMethodHandler().select4Options(19, "Ask Timfrau", "Exchange Favour", "How can I help Shilo Village?", "I've lost the sketch", "Nevermind");
			else if(c.TUP == 4) 
				c.getFrameMethodHandler().select4Options(19, "Topics", "Exchange Favour", "I've brought back the Totem", "", "Nevermind");
			else if(c.TUP == 5) 
				c.getFrameMethodHandler().select4Options(19, "Topics", "Exchange Favour", "I've brought back the Gilded Totem", "How do I gild the Totem?", "Nevermind");
			else if(c.TUP == 6) 
				c.getFrameMethodHandler().select4Options(19, "Topics", "Exchange Favour", "What should I do with the Gilded Totem?", "", "Nevermind");
			return;

		case 2533:
			c.getFrameMethodHandler().select2Options(39, "Options", "Use 25% favour to open bank", "Nevermind");
			return;

		case 2530:
			c.getFrameMethodHandler().select2Options(38, "Options", "Pay 100 Trading Sticks to enter grove", "Nevermind");
			return;

		case 1164:
			c.getFrameMethodHandler().select2Options(37, "Options", "Use 10% favour to open shop", "Nevermind");
			return;

		case 1168:
			c.getFrameMethodHandler().select2Options(36, "Options", "Use 10% favour to open shop", "Nevermind");
			return;

		case 494: case 495: case 496: case 497: case 498: case 499: case 2355: case 2354: case 166: //Bankers
			c.getFrameMethodHandler().openUpBankFrame();
			return;

		case 1844:
			c.getClientMethodHandler().selectOptionTravel2("Travel to the city exit?", "Yes", 2844,10128, "no",-1,-1);
			break;

		case 1843:
			c.getClientMethodHandler().selectOptionTravel2("Travel to the north island?", "Yes", 2872,10165, "no",-1,-1);
			break;

		case 1846:
			c.getClientMethodHandler().selectOptionTravel2("Travel to the main city?", "Yes", 2890,10223, "no",-1,-1);
			break;

		case 2205:
			c.getClientMethodHandler().selectOptionTravel2("Travel to the city exit?", "Yes", 2839,10128, "no",-1,-1);
			break;

		case 2198:
			c.getFrameMethodHandler().openUpShopFrame(13); //Kebab Shop - Keldagrim
			break;

		case 1065:
			c.getClientMethodHandler().dialogue(NPCID, "Sorry, the castle games-room is closed.");
			break;

		case 2180:
			c.getClientMethodHandler().selectOptionTravel2("Travel to Keldagrim?", "Yes", 2906,10169, "no",-1,-1);
			break;

		case 2181:
			c.getFrameMethodHandler().select4Options("Travel Options", "Dwarven Passage (Taverley)", 2876,9871, "Dwarven Mine (Falador)", 2998,9836, "", -1, -1, "Cancel", -1,-1);
			break;

		case 1174: case 1175: case 952: //net
		case 1176: case 1177: case 1178: //fish
		case 1236: case 1237: case 1238: case 233: case 234:
		case 235: case 236: //bait
		case 1399: case 321: case 324: case 333: case 312: case 1332: // cage/harpoon
		case 1405: case 1406: case 313: case 322: case 334: case 1191: case 1333: // net/harpoon
		case 2067: case 2068: case 316: case 319: case 320: case 323: case 325: case 326: case 327: case 330: case 1331: case 332: // net/bait
		case 3019: case 314: case 315: case 317: case 318: case 328: case 329: case 331: case 927: case 1189: case 1190: case 309:
		case 310: case 311: // lure/bait
			c.getSkillHandler().getFishingHandler().fishingClick1(NPCID);
			break;	

		case 3788:
			c.getClientMethodHandler().dialogue(NPCID, "The objective of this game of life or death","is to try and destroy all the portals","in the given timeframe.","",
					"If you do this successfully, you will be awarded points.","In exchange, I may give you","an item or two.");
			break;

			//put these here so we can attack them still
		case 253:
		case 254:
		case 255:
		case 256:
			c.getClientMethodHandler().dialogue(NPCID, "Everyone hears 'Khazard' and just immediately","assume we are the bad guys.");
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
			c.getClientMethodHandler().dialogue(NPCID, "I can tend your wounds.");
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
				c.getClientMethodHandler().dialogue(NPCID, "There's sort of an infestation problem here,", "and that's all I'm allowed to say", "on the matter.");
			else c.getClientMethodHandler().dialogue(NPCID, "You don't look too tough.");
			break;

		case 1205: //tyras guard
			if(!c.isInArea(2174,3131,2201,3163)) //tyras pvp camp
				c.getClientMethodHandler().dialogue(NPCID, "Careful now. Lots of strange things", "have been happening lately.");
			else c.getClientMethodHandler().dialogue(NPCID, "Everything outside this camp is PVP.");
			break;

		case 1206: //tyras guard
			if(!c.isInArea(2174,3131,2201,3163)) //tyras pvp camp
				c.getClientMethodHandler().dialogue(NPCID, "People always complain whenever we", "are deployed to a new area.", "They don't realize we are there", "for their safety.");
			else c.getClientMethodHandler().dialogue(NPCID, "Everything outside this camp is PVP.");
			break;

		case 2324:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14136,14137,14138, "Farming", c.playerFarming, new String[]{"I'll keep watch over your plants."});
			break;

		case 618:
			skillMaster(NPCID, "Examiner", 14130,14131,14132, "Firemaking", c.playerFiremaking, new String[]{"I am here with the Tyras research team.","We had reports of a bad","infestation on this island."});
			break;

		case 536:
			skillMaster(NPCID, "Valaine", 14133,14134,14135, "Woodcutting", c.playerWoodcutting, new String[]{"If you plan on woodcutting, you'll","need a hatchet. Thankfully,","I sell plenty of those."});
			break;

		case 2357:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14127,14128,14129, "Cooking", c.playerCooking, new String[]{"Would you like to look at my cooking wares?"});
			break;

		case 592:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14124,14125,14126, "Fishing", c.playerFishing, new String[]{"In need of any fishing supplies?", "Trade with me for your fishing needs."});
			break;

		case 1376: 
			skillMaster(NPCID, "Derrik", 14121,14122,14123, "Smithing", c.playerSmithing, new String[]{"Smithing is the greatest art", "one can take upon himself."});
			break;

		case 1860: 
			skillMaster(NPCID, "Brian", 14118,14119,14120, "Mining", c.playerMining, new String[]{"If you want to mine those", "rocks, you're going to need a pickaxe.", "Luckily, I offer a wide variety of pickaxes", "at my shop. You should take a look."});
			break;

		case 2356:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14110,14109,14111, "Fletching", c.playerFletching, new String[]{"Pay attention to your surroundings,","the road can be rather dangerous at times."});
			break;

		case 805:
			skillMaster(NPCID, "Master Crafter", 14106,14107,14108, "Crafting", c.playerCrafting, new String[]{"Welcome to the Crafting Guild."});
			break;

		case 1780:
			skillMaster(NPCID, "Larry", 14103,14104,14105, "Thieving", c.playerThieving, new String[]{"Being a travelling merchant is", "not the easiest of lifestyles."});
			break;

		case 437:
			skillMaster(NPCID, "Hamal", 14097,14098,14099, "Agility", c.playerAgility, new String[]{"The Brimhaven Agility Course is","one of the most dangerous courses around."});
			break;

		case 646: 
			if(c.isInArea(2853,3375,2862,3383)){
				skillMaster(NPCID, "Curator", 14091,14092,14093, "Runecrafting", c.playerRunecrafting, 
						new String[]{"You must use a talisman on this altar","to craft runes.",
						"My studies show that this altar can","craft Nature, Law, and Death runes."});
			}
			else if(c.isInArea(2869,3010,2874,3016)){
				skillMaster(NPCID, "Curator", 14091,14092,14093, "Runecrafting", c.playerRunecrafting, 
						new String[]{"You must use a talisman on this altar","to craft runes.",
						"My studies show that this altar can","craft Blood and Soul runes."});
			}
			else{
				skillMaster(NPCID, "Curator", 14091,14092,14093, "Runecrafting", c.playerRunecrafting, 
						new String[]{"You must use a talisman on this altar","to craft runes.",
						"My studies show that this altar can","craft Air, Mind, Water, Earth,","Fire, Body, Cosmic, and Chaos runes."});
			}
			break;

		case 358:
			skillMaster(NPCID, "Priest", 14085,14086,14087, "Prayer", c.playerPrayer, new String[]{"Saradomin walks with you."});
			break;

		case 945:
			skillMaster(NPCID, "Guide", 14094,14095,14096, "Hitpoints", c.playerHitpoints, new String[]{"This port allows travel to many parts of the world."});
			break;

		case 944: //
			skillMaster(NPCID, "Combat Instructor", 14076,14077,14078, "Strength", c.playerStrength, new String[]{"East of here are some Giant Rats.","They may be more towards your level."});
			break;

		case 946:
			skillMaster(NPCID, "Magic Instructor", 14088,14089,14090, "Magic", c.playerMagic, new String[]{"Your fisrt two spells, air and ","water strike do no require","runes to cast."});
			break;

		case 553:
			skillMaster(NPCID, "Aubury", 14088,14089,14090, "Magic", c.playerMagic, new String[]{"Runes and magical equipment","are my specialty."});
			break;

		case 548: //Thessalia
			skillMaster(NPCID, "Thessalia", 14100, 14101, 14102, "Herblore", c.playerHerblore, new String[]{"Trade with me for your herbs,", "vials, and ingredients."});
			break;

		case 683:
			skillMaster(NPCID, c.getClientMethodHandler().getNpcName(NPCID), 14082,14083,14084, "Ranged", c.playerRanged, new String[]{"Please, take a look at what I am selling.","I boast of nothing but the best","craftsmanship."});
			break;

		case 70:
		case 1596:
		case 1208: //slayer
		case 1599:
			c.getFrameMethodHandler().select4Options(7,"Hello", "I need a new Slayer task", "How much is left of my current task?", "Can I purchase a Slayer Crystal?", "(Click here for more options)");
			c.slayerMaster = NPCID;
			break;

		case 3352:
			c.getFrameMethodHandler().select4Options(8,"Options", "1 Server Token - Zamorak Godsword", "1 Server Token - Bandos Godsword", "1 Server Token - Saradomin Godsword", "More Options (Spirit Shields)");
			break;

		case 1055:
			c.getFrameMethodHandler().select4Options(9,"Options", "Instructions", "Exchange tickets for rewards", "Cancel", "");
			break;

		case 400: 
			c.getFrameMethodHandler().select4Options(10,"Topics", "Got any news?", "Can you change my spellbook?", "Can I buy a staff of light?", "Nevermind");
			break;

		case 1709:
			if(c.isInArea(2033,4533,2042,4539)){ //throne room
				c.getClientMethodHandler().selectOptionTravel2("Travel to Keldagrim?", "Yes", 2912,10192, "No", -1,-1);
				break;
			}
			c.getClientMethodHandler().selectOptionTravel2("Help out Hammerspike Stoutbeard?", "Sure thing", 2037,4535, "No thank you", -1,-1);
			break;

		case 943:
			if(c.starter == 0)
				c.getClientMethodHandler().dialogue(NPCID, "","@pla@Hello, I'm new here.","","", 
						"Welcome newcomer!", "To get your starter kit, head", "Northwest of here and talk to", "Professor Oddenstein.",
						"He can be found North, in the clothes shop.");
			else {
				c.getClientMethodHandler().optionsAndDialogue(NPCID, 
						new String[]{"I'm ready to leave!"}, 
						new String[]{"@pla@","How do I get out of this place?","","",
								"You can speak with Captain Shanks to leave."},
						new String[]{"Tell me about Quests?"},
						new String[]{"@pla@","What's a quest?","","",
								"Quests are how you make yourself known!","They also help you gain","items and experience.","You can find quests",
								"in your quest tab.","Also, having more quest points","can be used to do more quests","or have access to certain locations."},
						new String[]{"Tell me about Chatrooms?"},
						new String[]{"Chatroom information can be viewed in","the Chatroom tab.","The tab is located to the left","of your friends list tab."},
						new String[]{"How can I run?"},
						new String[]{"@pla@","How can I get myself running?","","",
								"This can be done from the Player Controls tab.","It's the tab that looks like a player,","to the left of the Music Tab."},
						new String[]{"Tell me about the Options Tab?"},
						new String[]{"@pla@","How do I change the game options?","","",
								"The options tab is the tab with the","icon of a wrench. Options do not save","to the account. Each time you log in, ","you will have to edit the options again."},
						new String[]{"How do I report someone?"},
						new String[]{"@pla@","How can I report someone?","","",
								"This can be done by selecting 'Report Abuse,' ","which is located underneath the chat window."});
			}
			break;

		case 286: //Professor Oddenstein
			if(c.starter == 0)
				c.getFrameMethodHandler().select4Options(4,"Combat Style?", "Warrior", "Magic", "Ranged", "Nevermind.");
			else c.getClientMethodHandler().dialogue(NPCID, "@pla@","What now?","","",
					"Now, you leave me alone.","The Surivival Expert has further instructions for you.");
			break;

		case 1303:
			if (c.getInventoryHandler().IsItemInBag(2349) == false && c.deadtele != 1)
				c.getFrameMethodHandler().select2Options(32,"How can I help you?", "Instructions", "I need a pickaxe!");
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
				c.getClientMethodHandler().npcdialogue("Brimstail", NPCID, "You say Frumscone wanted me to take a look", "at something? Hmm, one moment...","This is quite the artifact! I wonder","how he got a hold of it?",
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
				c.getClientMethodHandler().addQuestPoints(1);
			}
			else if (c.RM == 4){
				c.getClientMethodHandler().selectOptionTravel2("Mine rune essence?", "Yes", 2911, 4833, "No", -1,-1);
			}
			break;

		case 460:
			if (c.RM == 0 && c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Hello there! I hope you wouldn't", "mind doing me a favor now?", "Take this talisman, and run it", "to my friend Brimstail.", "He was last seen pacing"
						,"around, near the South", "entrance of Falador.");
				c.sendMessage("An Air Talisman has been given to you.");
				c.getInventoryHandler().addItem(1438, 1);
				c.RM = 1;
				c.getFrameMethodHandler().loadQuestTab();
			}
			else if (c.RM == 0 && c.getInventoryHandler().freeSlots() < 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "I could really use some help from", "someone like you! Can", "you try speaking with me when", "your inventory isn't so full.");
			}
			else if (c.RM == 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Hopefully Brimstail will be interested in","that artifact.");
			}
			else if (c.RM == 2 && c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Brimstail said he was interested? Wonderful!","I have dechiphered the inscriptions",
						"from the Talisman. However, it is in","some ancient language. Brimstail","is an expert in that field.",
						"If you could take my notes to him, he","may be able to put some use","to them.");
				c.sendMessage("Notes have been added to your inventory.");
				c.getInventoryHandler().addItem(291, 1);
				c.RM = 3;
			}
			else if (c.RM == 2 && c.getInventoryHandler().freeSlots() < 1){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Brimstail said he was interested?",
						"I have some notes for him, but","you'll need some more room in","your inventory.");
			}
			else if (c.RM == 3){
				c.getClientMethodHandler().npcdialogue("Frumscone", NPCID, "Hopefully Brimstail can make sense of all of this.");
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
					c.getClientMethodHandler().dialogue(518, "These c.pirate ships are blocking us","from safely leaving. They landed", "North of here. I need you to kill",""+remaining+" c.pirates before we can leave.");
					break;
				}
				if(c.pirate == 10){ //quest completed
					if(c.getInventoryHandler().freeSlots() > 1 ||c.getInventoryHandler().hasItemAny(995, 1)){
						c.pirate = 11;
						c.getFrameMethodHandler().menu(c.getMenuHandler().newBeginnings());
						c.getInventoryHandler().addItem(995,5000);
						c.getClientMethodHandler().addQuestPoints(1);
					}
					else c.getClientMethodHandler().dialogue(518, "I have a reward for you!","But, you need more space in your inventory.");
					break;
				}
			}
			c.getClientMethodHandler().selectOptionTravel2("Travel to Port Sarim?", "Yes", 3041,3202, "No", -1, -1);
			break;

		case 698: //holgart fishing platform travel
			if(c.isInArea(2716, 3301, 2724, 3309))
				c.getClientMethodHandler().selectOptionTravel2("Travel to Fishing Platform?", "Yes", 2779,3273, "No", -1,-1);
			else
				c.getClientMethodHandler().selectOptionTravel2("Leave Fishing Platform?", "Yes", 2722,3305, "No", -1,-1);
			break;

		case 376: //captain tobias
			if(c.isInArea(3026,3215,3030,3220)) //port sarim
				c.getFrameMethodHandler().select4Options("Topics", "Travel to Karamja", 2956,3146, "Travel to Catherby", 2803,3423, "", -1, -1, "Nevermind", -1, -1);
			else if(c.isInArea(2800, 3418, 2808, 3425)) //catherby
				c.getClientMethodHandler().selectOptionTravel2("Travel to Port Sarim?", "Yes", 3029, 3217, "No", -1, -1);
			else c.getClientMethodHandler().selectOptionTravel2("Travel to Port Sarim?", "Yes", 3029, 3217, "No", -1, -1);
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

		case 1263:
			if(c.getInventoryHandler().freeSlots() >= 1){
				c.getClientMethodHandler().npcdialogue("Wizard", 1263, "Poison? You didn't get it from me.");
				c.getInventoryHandler().addItem (273, 1);
				c.sendMessage ("Poison has been added to your inventory.");
			}
			break;
			
		case 798: //velrak the explorer
			if(c.getInventoryHandler().freeSlots() == 0)
				c.getClientMethodHandler().dialogue(NPCID, "I have something for you, but","your inventory is full.");
			else{
				c.getClientMethodHandler().dialogue(NPCID, "You have rescued me!","Take this dusty key as my thanks.");
				c.getInventoryHandler().addItem(1590);
				c.sendMessage("Velrak gives you a dusty key.");
			}
			break;

		default:
			c.println("Case 155: Unhandled NPCID : "+NPCID);
			break;
		}
	}

}

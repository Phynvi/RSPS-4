
public class ButtonClickHandler {
	private client c = null;

	public ButtonClickHandler(client pc){
		this.c = pc;
	}

	public void clickButton(int actionButtonId){


		c.debug("Case 185: "+actionButtonId);
		if(lists.prayerList.exists(actionButtonId)){
			c.PRAY.checkPrayer(actionButtonId);
			return;
		}


		switch(actionButtonId) {
		//These values speak for themselves
		//case 4126: windstrike break;

		//case 59136:
		//panellist();
		//break;			

		case 28170:
			c.getFrameMethodHandler().menu(c.getMenuHandler().newBeginnings());
			break;

		case 150: //auto retaliate on
			c.autoRetaliate = 1;
			c.getFrameMethodHandler().sendQuest("@gre@Auto Retaliate", 155); //auto retaliate
			break;
		case 151: //auto retaliate off
			c.autoRetaliate = 0;
			c.getFrameMethodHandler().sendQuest("@red@Auto Retaliate", 155); //auto retaliate
			break;

		case 54104:
			c.getFrameMethodHandler().Menu(c.getMenuHandler().farmingGuide());
			break;

		case 21168:
			c.getFrameMethodHandler().RemoveAllWindows();
			break;

		case 73104:
			c.sendMessage("X: "+c.absX+" Y: "+c.absY);
			c.println("X: "+c.absX+" Y: "+c.absY);
			break;

		case 73108:
			c.getFrameMethodHandler().playerMenuFrames();
			break;

		case 1097:
			if(c.spellbook == 1) 
				c.getFrameMethodHandler().setSidebarInterface(0, 1689);
			else 
				c.getFrameMethodHandler().setSidebarInterface(0, 1829);
			break;

			//Autocast

			//4 select option
		case 32017: //1st option
			if(c.optionsMenu){
				c.optionsMenu = false;
				if(c.oX1 != -1 && c.oY1 != -1){
					c.getClientMethodHandler().isteleporting2(409, 1818, 15, c.oX1, c.oY1, 0);
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX1 = -1;
					c.oY1 = -1;
				}
				if(c.oX1 == -1 || c.oY1 == -1){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX1 = -1;
					c.oY1 = -1;
				}
			}

			if(c.starter4Options){
				c.starter4Options = false;
				if(c.getInventoryHandler().freeSlots() >= 8){
					c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					c.getInventoryHandler().addItems(1075,1103,2894,1007,1291,1173); //bronze platelegs, chainmail, grey boots, red cape, bronze longsword, bronze sq shield
					c.getInventoryHandler().addItem(995,10000); //10,000 GP to start
					c.getInventoryHandler().addItem(352, 100); //100 cooked pike
					c.starter = 1;
				}
				else c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
				break;
			}

			if(c.skillMasterPurchase){ //c.skillcape
				c.skillMasterPurchase = false;
				if(c.getLevelForXP(c.playerXP[c.skill99ID]) >= 99){
					if(c.getInventoryHandler().playerHasItemAmount(995, 99000)){ //99k to purchase
						if(c.getInventoryHandler().freeSlots() >= 1){
							c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "I'll take your 99,000 GP, ","and here's your c.skillcape.");
							c.getInventoryHandler().deleteItem(995, c.getInventoryHandler().getItemSlot(995), 100000);
							c.getInventoryHandler().addItem(c.skillcape,1);
						}
						else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "Try clearing up some inventory", "space first.");
					}
					else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "You do not have enough coins", "to afford this cape.");
				}
				else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "If you want to purchase a cape,","you need to have 99 "+c.skillName);
				break;
			}

			if(c.skillMaster){
				c.skillMaster = false;
				c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, c.skillMasterDialogue);
				break;
			}

			if(c.arianwyn){
				c.arianwyn = false;
				c.getClientMethodHandler().npcdialogue("Arianwyn", 1202, "We were able to contain the demon with","your help. Thank you.");
				break;
			}

			if(c.slayer4Options){
				c.slayer4Options = false;
				if(c.slayerCount < 10){
					c.SLAYER.generateTask();
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "You want to help us rid the world", "of annc.oYing monsters?", "I am fine with this.", "Sure, I'll give you a task.",
							"I want you to slay "+c.slayerCount+" "+c.SLAYER.getTaskName(c.slayerNPC)+"s.");
				}
				else
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "Don't try and be sneaky with me.", "I know you still haven't finished", "your original Slayer task!", "Now get out of here.");
				break;
			}

			if (c.tokenexchange){
				c.tokenexchange = false;
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(15336, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1){
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a sword", "with a full inventory.");
				}
				else{
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				}
			}

			if (c.tokenexchange2){
				c.tokenexchange2 = false;
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(3627, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Arcane Spirit Shield.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1){
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");
				}
				else{
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				}
			}

			if (c.ticketexchange){
				c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Below our very feet lies a vast", "and dc.angerous agility course!", "The way the course works is", "that after you have used each",
						"obstacle, you have 3 seconds to", "tag the ticket dispenser. When", "you tag the dispenser you will", "recieve a ticket. Sometimes you",
						"might recieve more then one ticket", "from a tag, depending on your luck!", "You can exchange tickets for rewards", "such as items and EXP, good luck!");
				c.ticketexchange = false;
			}

			if (c.ticketexchange2){
				if (c.getInventoryHandler().playerHasItemAmount(2996, 100)){
					int exprec = c.playerLevel[16]*10000;
					c.getInventoryHandler().deleteItem(2996, c.getInventoryHandler().getItemSlot(2996), 100);
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "I'll take those 100 tickets and", "here's your "+exprec+" EXP.");
					c.getClientMethodHandler().addSkillXP(exprec, 16);
					c.ticketexchange2 = false;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back to me when you have 100 tickets!");
					c.ticketexchange2 = false;
				}
			}

			if (c.soulwars){
				int recieved = c.playerLevel[3]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 3);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" hitpoints exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				c.soulwars = false;
			}

			if (c.soulwars2){
				int recieved = c.playerLevel[1]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 1);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" defence exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				c.soulwars2 = false;
			}

			if (c.glory4) {
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 3024, 3206, 0);
				c.getFrameMethodHandler().RemoveAllWindows();
				c.glory4 = false;
			}

			if (c.fletchingoption){
				if (c.playerLevel[9] >= c.fletchingshortlvl){
					c.startAnimation(1248);
					c.fletchingitem = c.fletchingshort;
					c.fletchingprocessshort = 4;
					c.getFrameMethodHandler().RemoveAllWindows();
					c.fletchingoption = false;
				}
				else if (c.playerLevel[9] != c.fletchingshortlvl){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.sendMessage("You need "+c.fletchingshortlvl+" fletching for that bow.");
					c.fletchingoption = false;
				}
			}
			break;

		case 32018: //2nd option
			if(c.optionsMenu){
				c.optionsMenu = false;
				if(c.oX2 != -1 && c.oY2 != -1){
					c.getClientMethodHandler().isteleporting2(409, 1818, 15, c.oX2, c.oY2, 0);
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX2 = -1;
					c.oY2 = -1;
				}
				if(c.oX2 == -1 || c.oY2 == -1){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX2 = -1;
					c.oY2 = -1;
				}
			}

			if(c.starter4Options){
				c.starter4Options = false;
				if(c.getInventoryHandler().freeSlots() >= 8){
					c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					c.getInventoryHandler().addItems(577,1011,2579,1007,1379,3843); //wizard robe, bottom, boots, staff, damaged book of guthix, 
					c.getInventoryHandler().addItem(995,10000); //10,000 GP to start
					c.getInventoryHandler().addItem(352, 100); //100 cooked pike
					c.starter = 1;
				}
				else c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
				break;
			}	

			if(c.skillMasterPurchase){ //c.skillcape trimmed
				c.skillMasterPurchase = false;
				if(c.getLevelForXP(c.playerXP[c.skill99ID]) >= 99 && c.masteries > 1){
					if(c.getInventoryHandler().playerHasItemAmount(995, 99000)){ //99k to purchase
						if(c.getInventoryHandler().freeSlots() >= 1){
							c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "I'll take your 99,000 GP, ","and here's your trimmed c.skillcape.");
							c.getInventoryHandler().deleteItem(995, c.getInventoryHandler().getItemSlot(995), 100000);
							c.getInventoryHandler().addItem(c.skillcapeTrimmed,1);
						}
						else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "Try clearing up some inventory", "space first.");
					}
					else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "You do not have enough coins", "to afford this cape.");
				}
				else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "If you want to purchase a trimmed cape,","you need to have 99 "+c.skillName+",", "and at least another skill mastery.");
				break;
			}

			if(c.skillMaster){
				c.skillMaster = false;
				c.skillMasterPurchase = true;
				c.getFrameMethodHandler().selectoption2("Purchase?", "c.skillcape (99,000 GP)", "c.skillcape(t) (90,000 GP)", "Hood (99,000 GP)", "Nevermind.");
				break;
			}

			if(c.arianwyn){
				c.arianwyn = false;
				c.getClientMethodHandler().npcdialogue("Arianwyn", 1202, "I have switched your c.spellbook.");
				if(c.spellbook == 0){
					c.spellbook = 1;
					c.getFrameMethodHandler().setSidebarInterface(6, 12855);
				}
				else{
					c.spellbook = 0;
					c.getFrameMethodHandler().setSidebarInterface(6, 1151);
				}
				c.getFileLoadingHandler().savechar();
				c.getFileLoadingHandler().savemoreinfo();
				break;
			}

			if(c.slayer4Options){
				c.slayer4Options = false;
				String npcName = c.SLAYER.getTaskName(c.slayerNPC);
				if(c.slayerCount > 1)
					npcName += "s";
				if(c.slayerNPC == 0){
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "You currently have no task.");
					break;
				}
				if(c.slayerCount <= 0){
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "You have completed your Slayer task.");
					break;
				}
				c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "From the looks of it...", "You have "+c.slayerCount+" "+npcName+" left.");
				break;
			}		

			if (c.tokenexchange){
				c.tokenexchange = false;
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(15334, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
					c.tokenexchange = false;
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1){
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a sword with", "a full inventory");
				}
				else c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token!");
			}

			if (c.tokenexchange2){
				c.tokenexchange2 = false;
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(3637, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Elysian Spirit Shield.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1)
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");

				else c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
			}

			if (c.ticketexchange2){
				if (c.getInventoryHandler().playerHasItemAmount(2996, 250) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(2996, c.getInventoryHandler().getItemSlot(2996), 250);
					c.getInventoryHandler().addItem(12003, 1);
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "I'll take those 250 tickets and", "here's your Void Knight Gloves.");
					c.ticketexchange2 = false;
				}
				else if (c.getInventoryHandler().playerHasItemAmount(2996, 250) && c.getInventoryHandler().freeSlots() < 1){
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back when you have more", "room available in your inventory.");
					c.ticketexchange2 = false;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back when you have 250 tickets.");
					c.ticketexchange2 = false;
				}
			}

			if (c.ticketexchange){
				int exprec = c.playerLevel[16]*10000;
				c.getFrameMethodHandler().selectoption2("Rewards", "100 Tickets-"+exprec+" Agility EXP", "250 Tickets-Void Knight Gloves", "500 Tickets-Agility Armor", "Cancel!");
				c.ticketexchange = false;
				c.ticketexchange2 = true;
				//c.playerLevel[16] >= 99
			}                  

			if (c.soulwars){
				int recieved = c.playerLevel[0]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 0);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" attack exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				c.soulwars = false;
			}

			if (c.soulwars2){
				int recieved = c.playerLevel[4]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 4);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" range exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				c.soulwars2 = false;
			}

			if (c.glory4) {
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 2953, 3215, 0);
				c.getFrameMethodHandler().RemoveAllWindows();
				c.glory4 = false;
			}			   

			if (c.fletchingoption){
				if (c.playerLevel[9] >= c.fletchinglonglvl){
					c.startAnimation(1248);
					c.fletchingexp += c.fletchingexp/5;
					c.fletchingitem = c.fletchinglong;
					c.fletchingprocessshort = 4;
					c.getFrameMethodHandler().RemoveAllWindows();
					c.fletchingoption = false;
				}
				else if (c.playerLevel[9] != c.fletchinglonglvl){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.sendMessage("You need "+c.fletchinglonglvl+" fletching for that bow.");
					c.fletchingoption = false;
				}
			}
			break;

		case 32019: //3rd option
			if(c.optionsMenu){
				c.optionsMenu = false;
				if(c.oX3 != -1 && c.oY3 != -1){
					c.getClientMethodHandler().isteleporting2(409, 1818, 15, c.oX3, c.oY3, 0);
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX3 = -1;
					c.oY3 = -1;
				}
				if(c.oX3 == -1 || c.oY3 == -1){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX3 = -1;
					c.oY3 = -1;
				}
			}	

			if(c.starter4Options){
				c.starter4Options = false;
				if(c.getInventoryHandler().freeSlots() >= 8){
					c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					c.getInventoryHandler().addItems(1095, 1129, 2577, 1007, 841); //leather chaps, body, rc.anger boots, red cape, shortbow
					c.getInventoryHandler().addItem(882,1000); //1000 bronze arrows
					c.getInventoryHandler().addItem(995,10000); //10,000 GP to start
					c.getInventoryHandler().addItem(352, 100); //100 cooked pike
					c.starter = 1;
				}
				else c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
			}	

			if(c.skillMasterPurchase){ //hood
				c.skillMasterPurchase = false;
				if(c.getLevelForXP(c.playerXP[c.skill99ID]) >= 99){
					if(c.getInventoryHandler().playerHasItemAmount(995, 99000)){ //99k to purchase
						if(c.getInventoryHandler().freeSlots() >= 1){
							c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "I'll take your 99,000 GP, ","and here's your hood.");
							c.getInventoryHandler().deleteItem(995, c.getInventoryHandler().getItemSlot(995), 100000);
							c.getInventoryHandler().addItem(c.skillHood,1);
						}
						else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "Try clearing up some inventory", "space first.");
					}
					else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "You do not have enough coins", "to afford this hood.");
				}
				else c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, "If you want to purchase a hood,","you need to have 99 "+c.skillName);
				break;
			}

			if(c.skillMaster){
				c.skillMaster = false;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}

			if(c.arianwyn){
				c.arianwyn = false;
				c.BIS = true;
				c.getFrameMethodHandler().selectoption("Buy a Staff for 1,000,000 GP?", "Sure thing!", "No", "...");
			}

			if(c.slayer4Options){
				c.slayer4Options = false;
				c.slayer2Options = true;
				c.getFrameMethodHandler().selectoption("100,000 GP for a Slayer Crystal?", "Sure", "No thanks.");
			}

			if (c.tokenexchange){
				c.tokenexchange = false;
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(15335, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
					c.tokenexchange = false;
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1)
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a sword", "with a full inventory.");
				else
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
			}

			if (c.tokenexchange2){
				c.tokenexchange2 = false;
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(3629, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Spectral Spirit Shield.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1)
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");

				else c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
			}

			if (c.ticketexchange2){
				if (c.getInventoryHandler().playerHasItemAmount(2996, 500) && c.getInventoryHandler().freeSlots() >= 2){
					c.getInventoryHandler().deleteItem(2996, c.getInventoryHandler().getItemSlot(2996), 500);
					c.getInventoryHandler().addItem(13301, 1);
					c.getInventoryHandler().addItem(13302, 1);
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "I'll take those 500 tickets, and","here's your Agility Armor");
					c.ticketexchange2 = false;
				}
				else if (c.getInventoryHandler().playerHasItemAmount(2996, 500) && c.getInventoryHandler().freeSlots() < 2){
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "You need at least 2 empty slots", "in your inventory.");
					c.ticketexchange2 = false;
				}
				else{
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back to me when you have", "500 tickets.");
					c.ticketexchange2 = false;
				}
			}

			if (c.ticketexchange){
				c.ticketexchange = false;
				c.getFrameMethodHandler().RemoveAllWindows();
			}

			if (c.soulwars){
				int recieved = c.playerLevel[2]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 2);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" strength exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				c.soulwars = false;
			}

			if (c.soulwars2){
				int recieved = c.playerLevel[5]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 5);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" prayer exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				c.soulwars2 = false;
			}

			if (c.glory4) {
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 2919, 9804, 0);
				c.getFrameMethodHandler().RemoveAllWindows();
				c.glory4 = false;
			}

			if (c.fletchingoption){ //arrowshafts
				c.startAnimation(1248);
				c.fletchingexp = 30;
				c.fletchingitem = 52;
				c.fletchingprocessshort = 4;
				c.getFrameMethodHandler().RemoveAllWindows();
				c.fletchingoption = false;
			}

		case 32020: //4th option
			if(c.optionsMenu){
				c.optionsMenu = false;
				if(c.oX4 != -1 && c.oY4 != -1){
					c.getClientMethodHandler().isteleporting2(409, 1818, 15, c.oX4, c.oY4, 0);
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX4 = -1;
					c.oY4 = -1;
				}
				if(c.oX4 == -1 || c.oY4 == -1){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.oX4 = -1;
					c.oY4 = -1;
				}
			}	

			if (c.fletchingoption){
				c.getFrameMethodHandler().RemoveAllWindows();
				c.fletchingoption = false;
			}

			if(c.starter4Options){
				c.starter4Options = false;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}	

			if(c.skillMasterPurchase){
				c.skillMasterPurchase = false;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}

			if(c.skillMaster){
				c.skillMaster = false;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}

			if(c.arianwyn){
				c.arianwyn = false;
				c.getFrameMethodHandler().RemoveAllWindows();
			}

			if(c.slayer4Options){
				c.slayer4Options = false;
				if(c.slayerMaster == 1208)
					c.getNPCClickHandler().skillMaster(c.slayerMaster, c.getClientMethodHandler().getNpcName(c.slayerMaster), 14112,14113,14114, "Slayer", c.playerSlayer, new String[]{"I travelled halfway across the world to","deal with a infestation problem.","Can you believe that?"});
				if(c.slayerMaster == 1596)
					c.getNPCClickHandler().skillMaster(c.slayerMaster, c.getClientMethodHandler().getNpcName(c.slayerMaster), 14112,14113,14114, "Slayer", c.playerSlayer, new String[]{"Take care as you travel South,","naught but foulness infests those lands."});
				break;
			}

			if (c.tokenexchange){
				c.tokenexchange = false;
				c.tokenexchange2 = true;
				c.getFrameMethodHandler().selectoption2("Options", "1 Server Token - Arcane Spirit Shield", "1 Server Token - Elysian Spirit Shield", "1 Server Token - Spectral Spirit Shield", "Cancel");
				break;
			}
			if (c.tokenexchange2){
				c.tokenexchange2 = false;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}
			if (c.ticketexchange){
				c.ticketexchange = false;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}
			if (c.ticketexchange2){
				c.ticketexchange2 = false;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}

			if (c.soulwars){
				//c.getFrameMethodHandler().RemoveAllWindows();
				c.soulwars = false;
				c.soulwars2 = true;
				c.getFrameMethodHandler().selectoption2("You have "+c.pkpoints+" pts", "Defence-"+c.playerLevel[1]*c.soulbonus+" exp-10pts", "Range-"+c.playerLevel[4]*c.soulbonus+" exp-10pts", "Pray-"+c.playerLevel[5]*c.soulbonus+" exp-10pts", "Cancel.");
				break;
			}

			if(c.soulwars2){
				c.soulwars2 = false;
				c.getFrameMethodHandler().RemoveAllWindows();
			}

			if (c.glory4) {
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 2134, 4907, 0);
				c.sendMessage("You teleport to an alternate Draynor.");
				c.getFrameMethodHandler().RemoveAllWindows();		
				c.glory4 = false;
			}
			break;



		case 14067: // Char design accept button
			c.getFrameMethodHandler().RemoveAllWindows();
			break;


		case 29113:
		case 29038:
		case 48023:
		case 29138:
			c.getFrameMethodHandler().getFilling(); //New special system	
			int specAmount = 0;
			int curWeap = c.playerEquipment[c.playerWeapon];
			if(!c.litBar){
				if(curWeap == 35){ //Excalibur
					if(c.specialDelay >= 10){
						c.litBar = true;
						c.getCombatHandler().ExcaliburSpecial();
					}
					else c.sendMessage("You do not have enough power.");
					break;
				}
				if(curWeap == 7158){ //Dragon 2h
					if(c.specialDelay >= 6){
						c.litBar = true;
						if (c.IsAttackingNPC == false)
							c.getCombatHandler().Dragon2hSpecial();				
					}
					else c.sendMessage("You do not have enough power.");
					break;
				}
				int requiredSpecialAmount = Item.getSpecAmount(c.playerEquipment[c.playerWeapon]);
				if(requiredSpecialAmount == -1) break;
				if(c.specialDelay >= requiredSpecialAmount)
					c.litBar = true;
				else{
					c.sendMessage("You do not have enough power.");
					break;
				}
			}
			else{
				c.getCombatHandler().allSdisable();
				break;
			}

			break;

		case 10162: //close book interface
			c.getFrameMethodHandler().closeInterface();
			break;

		case 39178: //close book interface
			c.getFrameMethodHandler().closeInterface();
			break;

			/* case 29030: DragonLongSpecial(); break; */


		case 153:
			c.getFrameMethodHandler().sendQuest("@gre@Move speed", 158);
			if (c.runningEnergy > 0) 
				c.isRunning2 = true;
			break;
		case 152:
			c.getFrameMethodHandler().sendQuest("@yel@Move speed", 158);
			c.isRunning2 = false;
			break;

		case 130: //close interface
			c.debug("Closing Interface");
			break;


		case 168: // char emote
			c.getFrameMethodHandler().showInterface(3559);
			break;

		case 169: // normal emote
			Item.capeEmote(c);
			break;

		case 162: // think emote
			c.startAnimation(0x359);
			break;

		case 164: // bow emote
			c.startAnimation(0x35A);
			break;

		case 165: // angry emote
			c.startAnimation(0x35B);
			break;

		case 161: // cry emote
			c.startAnimation(0x35C);
			break;

		case 170: // laugh emote
			c.startAnimation(0x35D);
			break;

		case 171: // cheer emote
			c.startAnimation(0x35E);
			break;

		case 163: // wave emote
			c.startAnimation(0x35F);
			break;

		case 167: // beckon emote
			c.startAnimation(0x360);
			break;

		case 172: // clap emote
			c.startAnimation(0x361);
			break;

		case 166: // dance emote
			c.startAnimation(0x362);
			break;

		case 52050: // panic emote
			c.startAnimation(0x839);
			break;

		case 52051: // jig emote
			c.startAnimation(0x83A);
			break;

		case 52052: // spin emote
			c.startAnimation(0x83B);
			break;

		case 52053: // headbang emote
			c.startAnimation(0x83C);
			break;

		case 52054: // joY jump emote
			c.startAnimation(0x83D);
			break;

		case 52055: // rasp' berry emote
			c.startAnimation(0x83E);
			break;

		case 52056: // yawn emote
			c.startAnimation(0x83F);
			break;

		case 52057: // salute emote
			c.startAnimation(0x840);
			break;

		case 52058: // shrug emote
			c.startAnimation(0x841);
			break;

		case 43092: // blow kiss emote
			c.startAnimation(0x558);
			break;

		case 2155: // glass bc.oX emote
			c.startAnimation(0x46B);
			break;

		case 25103: // climb rope emote
			c.startAnimation(0x46A);
			break;

		case 25106: // lean emote
			c.startAnimation(0x469);
			break;

		case 2154: // glass wall emote
			c.startAnimation(0x468);
			break;

		case 52071: // goblin bow emote
			c.startAnimation(0x84F);
			break;

		case 52072: // goblin dance emote
			c.startAnimation(0x850);
			break;

		case 59062: // scared
			c.startAnimation(2836);
			break;

		case 72032: // zombie walk
			c.startAnimation(3544);
			break;

		case 72033: // zombie dance
			c.startAnimation(3543);
			break;

		case 9125: //Accuc.rate
		case 22228: //punch (unarmed)
		case 48010: //flick (whip)
		case 21200: //spike (pickaxe)
		case 1080: //bash (staff)
		case 6168: //chop (axe)
		case 6236: //accuc.rate (long bow)
		case 17102: //accuc.rate (darts)
		case 8234: //stab (dagger)
			c.FightType = 1;
			c.SkillID = 0;
			break;
		case 9126: //Defensive
		case 48008: //deflect (whip)
		case 22229: //block (unarmed)
		case 21201: //block (pickaxe)
		case 1078: //focus - block (staff)
		case 6169: //block (axe)
		case 33019: //fend (hally)
		case 18078: //block (spear)
		case 8235: //block (dagger)
			c.FightType = 4;
			c.SkillID = 1;
			break;
		case 9127: // Controlled
		case 48009: //lash (whip)
		case 33018: //jab (hally)
		case 6234: //longrange (long bow)
		case 18077: //lunge (spear)
		case 18080: //swipe (spear)
		case 18079: //pound (spear)
		case 17100: //longrange (darts)
			c.FightType = 3;
			c.SkillID = 3;
			break;
		case 9128: //Aggressive
		case 22230: //kick (unarmed)
		case 21203: //impale (pickaxe)
		case 21202: //smash (pickaxe)
		case 1079: //pound (staff)
		case 6171: //hack (axe)
		case 6170: //smash (axe)
		case 33020: //swipe (hally)
		case 6235: //rapid (long bow)
		case 17101: //repid (darts)
		case 8237: //lunge, stab
		case 8236: //slash 
			c.FightType = 2;
			c.SkillID = 2;
			break;
		case 9154: //Log out
			if(System.currentTimeMillis() - c.LogoutDelay > 10000) //30 second logout delay
				c.disconnectPlayerAndSave("Logout Button");
			else c.sendMessage("You must be out of combat for at least 10 seconds to do that.");
			break;
		case 21011:
			c.takeAsNote = false;
			break;
		case 21010:
			c.takeAsNote = true;
			break;
		case 13092:
			if (c.tradeWith > 0) {
				if (PlayerHandler.players[c.tradeWith].tradeStatus == 2) {
					c.tradeStatus = 3;
					c.getFrameMethodHandler().sendFrame126("Waiting for other player...", 3431);
				} else if (PlayerHandler.players[c.tradeWith].tradeStatus == 3) {
					c.tradeStatus = 3;
					//TradeGoConfirm();
				}
			}
			break;
		case 13218:
			if (c.tradeWith > 0) {
				if (PlayerHandler.players[c.tradeWith].tradeStatus == 3) {
					c.tradeStatus = 4;
					c.getFrameMethodHandler().sendFrame126("Waiting for other player...", 3535);
				} else if (PlayerHandler.players[c.tradeWith].tradeStatus == 4) {
					c.tradeStatus = 4;
					//ConfirmTrade();
				}
			}
			break;


		case 9157: //1st choice

			if(c.travel2_X1 != -1 && c.travel2_Y1 != -1){
				c.teleport(c.travel2_X1, c.travel2_Y1, c.travelHeight);
				c.travel2_X1 = -1;
				c.travel2_Y1 = -1;
				c.travelHeight = 0;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}

			if (c.deadopt) {
				c.getClientMethodHandler().npcdialogue("Grave Keeper", 1303, "Now that you've died, I can't let you go", "without some work. Get some copper", "and tin ore, and make a bronze bar.", "Talk to me once you have that bar.");
				c.deadopt = false;
				break;
			}

			if (c.startleave) {
				c.getClientMethodHandler().npcdialogue("Survival Expert", 943, "If you would like to leave, please speak","with Captain Shanks.");
				c.startleave = false;
				break;
			}

			if(c.spinning){
				c.spinning = false;
				if(c.getInventoryHandler().playerHasItem(1779)){
					c.repeatAnimation(894, 3);
					c.spinningTimer = 4;
				}
				else c.sendMessage("You need flax to spin bowstrings.");
				c.getFrameMethodHandler().RemoveAllWindows();
			}

			if(c.slayer2Options){
				c.slayer2Options = false;
				if(c.getInventoryHandler().playerHasItemAmount(995, 100000)){
					if(c.getInventoryHandler().freeSlots() < 1){
						c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "I'd be happy to take your money,", "but your inventory is full.");
					}
					else{
						c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "I'll take that 100k.", "Using the Crystal will tell you", "your current task and the remaining amount.");
						c.getInventoryHandler().deleteItem(995, c.getInventoryHandler().getItemSlot(995), 100000);
						c.getInventoryHandler().addItem(611,1);
					}
				}
				else
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "I don't do deals around here.", "It's 100,000 GP, and you don't", "have enough.");
				break;
			}

			if (c.BIS){
				if (c.getInventoryHandler().playerHasItemAmount(995, 1000000)){
					if (c.getInventoryHandler().freeSlots() > 0) {
						c.getClientMethodHandler().npcdialogue("Arianwyn", 1202, "Here's your staff.", "Once again, thanks for helping us.");
						c.getInventoryHandler().addItem(13308, 1);
						c.getInventoryHandler().deleteItem(995, c.getInventoryHandler().getItemSlot(995), 1000000);
						c.BIS = false;
					}
					else if (c.getInventoryHandler().freeSlots() == 0) {
						c.getClientMethodHandler().npcdialogue("Arianwyn", 1202, "Your inventory is full.");
						c.BIS = false;
					}
				}
				else {
					c.getClientMethodHandler().npcdialogue("Arianwyn", 1202, "Seems like you don't have enough money.");
					c.BIS = false;
				}
				break;
			}

			if (c.fletchingoption){
				c.fletchingprocessshort = 0;
				if (c.playerLevel[9] >= c.fletchingshortlvl){
					c.startAnimation(712);
					c.fletchingitem = c.fletchingshort;
					c.fletchingprocessshort = 4;
					c.getFrameMethodHandler().RemoveAllWindows();
					c.stringing = true;
					c.fletchingoption = false;
				}
				else if (c.playerLevel[9] != c.fletchingshortlvl){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.sendMessage("You need "+c.fletchingshortlvl+" fletching for that bow.");
					c.fletchingoption = false;
				}
				break;
			}
			c.getFrameMethodHandler().RemoveAllWindows();
			break;

		case 9158: //2nd option
			if(c.travel2_X2 != -1 && c.travel2_Y2 != -1){
				c.teleport(c.travel2_X2, c.travel2_Y2,c.travelHeight);
				c.travel2_X2 = -1;
				c.travel2_Y2 = -1;
				c.travelHeight = 0;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}              	 


			if(c.slayer2Options){
				c.slayer2Options = false;
				c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "Alright, I'll be seeing ya.");
			}

			if (c.fletchingoption){
				c.getFrameMethodHandler().RemoveAllWindows();
				c.fletchingoption = false;
				break;
			}

			if (c.startleave) {
				c.getClientMethodHandler().npcdialogue("Survival Expert", 943, "Some tips? Of course!", "Your teleports can be foudn in","your c.spellbook and important","information can be found","in your quest tab.");
				c.startleave = false;
				break;
			}
			if (c.deadopt){
				c.getInventoryHandler().addItem(1265, 1);
				c.getClientMethodHandler().npcdialogue("Grave Keeper", 1303, "", "Here's a pickaxe.", "Now go get working!", "");
				c.deadopt = false;
				break;
			}

			if (c.BIS){c.BIS = false; 
			c.getFrameMethodHandler().RemoveAllWindows();
			break;}

			c.getFrameMethodHandler().RemoveAllWindows();
			break; 

			//c.autocast

		case 7038: //Wind Strike 
			c.MAGICDATAHANDLER.setAutocast(1152);
			break;
		case 7039://Water Strike
			c.MAGICDATAHANDLER.setAutocast(1154);
			break;
		case 7040://Earth Strike
			c.MAGICDATAHANDLER.setAutocast(1156);
			break;
		case 7041://Fire Strike
			c.MAGICDATAHANDLER.setAutocast(1158);
			break;
		case 7042://Wind Bolt
			c.MAGICDATAHANDLER.setAutocast(1160);
			break;
		case 7043://Water Bolt
			c.MAGICDATAHANDLER.setAutocast(1163);
			break;
		case 7044://Earth Bolt
			c.MAGICDATAHANDLER.setAutocast(1166);
			break;
		case 7045://Fire Bolt
			c.MAGICDATAHANDLER.setAutocast(1169);
			break;
		case 7046://Wind Blast
			c.MAGICDATAHANDLER.setAutocast(1172);
			break;
		case 7047://Water Blast
			c.MAGICDATAHANDLER.setAutocast(1175);
			break;
		case 7048://Earth Blast
			c.MAGICDATAHANDLER.setAutocast(1177);
			break;
		case 7049://Fire Blast
			c.MAGICDATAHANDLER.setAutocast(1181);
			break;
		case 7050://Wind Wave
			c.MAGICDATAHANDLER.setAutocast(1183);
			break;
		case 7051://Water Wave
			c.MAGICDATAHANDLER.setAutocast(1185);
			break;
		case 7052://Earth Wave
			c.MAGICDATAHANDLER.setAutocast(1188);
			break;
		case 7053://Fire Wave
			c.MAGICDATAHANDLER.setAutocast(1189);
			break;
		case 51133: //Smoke Rush
			c.MAGICDATAHANDLER.setAutocast(12939);
			break;
		case 51185: //Shadow Rush
			c.MAGICDATAHANDLER.setAutocast(12987);
			break;
		case 51091: //Blood Rush
			c.MAGICDATAHANDLER.setAutocast(12901);
			break;
		case 24018: //Ice Rush
			c.MAGICDATAHANDLER.setAutocast(12861);
			break;
		case 51159: //Smoke Burst
			c.MAGICDATAHANDLER.setAutocast(12963);
			break;
		case 51211: //Shadow Burst
			c.MAGICDATAHANDLER.setAutocast(13011);
			break;
		case 51111: //Blood Burst
			c.MAGICDATAHANDLER.setAutocast(12919);
			break;
		case 51069: //Ice Burst
			c.MAGICDATAHANDLER.setAutocast(12881);
			break;
		case 51146: //Smoke Blitz
			c.MAGICDATAHANDLER.setAutocast(12951);
			break;
		case 51198: //Shadow Blitz
			c.MAGICDATAHANDLER.setAutocast(12999);
			break;
		case 51102: //Blood Blitz
			c.MAGICDATAHANDLER.setAutocast(12911);
			break;
		case 51058: //Ice Blitz
			c.MAGICDATAHANDLER.setAutocast(12871);
			break;
		case 51172: //Smoke Barrage
			c.MAGICDATAHANDLER.setAutocast(12975);
			break;
		case 51224: //Shadow Barrage
			c.MAGICDATAHANDLER.setAutocast(13023);
			break;
		case 51122: //Blood Barrage
			c.MAGICDATAHANDLER.setAutocast(12929);
			break;
		case 51080: //Ice Barrage
			c.MAGICDATAHANDLER.setAutocast(12891);
			break;

		case 1093:
			if(!c.autocast){
				c.sendMessage("Autocast has been activated.");
				c.autocast = true;
			}
			else{
				c.autocast = false;
				c.sendMessage("Autocast has been deactivated.");
			}
			break;

		case 24017:
		case 7212:
			c.getFrameMethodHandler().setSidebarInterface(0, 328);
			break;			

		case 33208:
			c.getFrameMethodHandler().menu(c.getMenuHandler().miningGuide());
			break;


		case 33214: 
			c.getFrameMethodHandler().menu(c.getMenuHandler().fishingGuide());
			break;

		case 33217: 
			c.getFrameMethodHandler().menu(c.getMenuHandler().cookingGuide());
			break;

		case 28215:
			c.getFrameMethodHandler().Menu(c.getMenuHandler().ancientsmenu2());
			break;

		case 33213: 
			c.getFrameMethodHandler().menu(c.getMenuHandler().herbloremenu());
			break;

		case 33216:
			c.getFrameMethodHandler().Menu(c.getMenuHandler().thiefmenu());
			break;


		case 50235: //Ancients teleport home
			if(c.homeTeleportTimer <= 0){
				if(c.getClientMethodHandler().canPlayersTeleportInThisArea()){
					c.homeTeleportTimer = 15;
					c.getClientMethodHandler().isteleporting2(392, 1161, 20, 3023,3206, 0);
				}
				else c.sendMessage("You can't use that teleport in this area.");
			}
			else c.sendMessage("You need to wait "+c.homeTeleportTimer+" minutes before using this.");
			break;

		case 4143: //S Teleport
			if(c.homeTeleportTimer <= 0){
				if(c.getClientMethodHandler().canPlayersTeleportInThisArea()){
					c.homeTeleportTimer = 15;
					c.getClientMethodHandler().isteleporting2(409, 1818, 15, 3024, 3206, 0);
				}
				else c.sendMessage("You can't use that teleport in this area.");
			}
			else c.sendMessage("You need to wait "+c.homeTeleportTimer+" minutes before using this.");
			break;

		case 4140: //H teleport
			if(c.MAGICDATAHANDLER.checkMagicRunes(1)){
				c.MAGICDATAHANDLER.removeMagicRunes(1);
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 3024, 3206, 0);
				c.getClientMethodHandler().addSkillXP(60*c.rate, c.playerMagic);
			}
			break;

		case 50245: //PVP ancients teleport
		case 4146: //PVP normal teleport
			if(c.getClientMethodHandler().canPlayersTeleportInThisArea())
				c.getFrameMethodHandler().select4Options("PVP Teleports", "Lletya [PVP]",2331,3170,"Tyras Camp [PVP]", 2187,3148, "Elf Camp [PVP]", 2207,3258, "Cancel",-1,-1);
			else c.sendMessage("You cannot activate teleports in this area.");
			break;

		case 50253: //Rimmington
			if(c.MAGICDATAHANDLER.checkMagicRunes(50253)){
				c.MAGICDATAHANDLER.removeMagicRunes(50253);
				c.getClientMethodHandler().isteleporting2(392, 1161, 20, 2954,3214, 0);
				c.getClientMethodHandler().addSkillXP(60*c.rate, c.playerMagic);
			}
			break;

		case 51005: //Entrana
			if(c.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				c.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				c.getClientMethodHandler().isteleporting2(392, 1161, 20, 2828,3344, 0);
				c.getClientMethodHandler().addSkillXP(60*c.rate, c.playerMagic);
			}
			break;

		case 51013: //Karamja
			if(c.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				c.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				c.getClientMethodHandler().isteleporting2(392, 1161, 20, 2801,3176, 0);
				c.getClientMethodHandler().addSkillXP(60*c.rate, c.playerMagic);
			}
			break;

		case 51023: //Barrows
			if(c.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				c.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				c.getClientMethodHandler().isteleporting2(392, 1161, 20, 3662,3495, 0);
				c.getClientMethodHandler().addSkillXP(60*c.rate, c.playerMagic);
			}
			break;

		case 51031: //West Ardougne
			if(c.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				c.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				c.getClientMethodHandler().isteleporting2(392, 1161, 20, 2493,3314, 0);
				c.getClientMethodHandler().addSkillXP(60*c.rate, c.playerMagic);
			}
			break;

		case 51039: //Grand Tree
			if(c.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				c.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				c.getClientMethodHandler().isteleporting2(392, 1161, 20, 2464,3496,1);
				c.getClientMethodHandler().addSkillXP(60*c.rate, c.playerMagic);
			}
			break;	

		case 29031: //tree gnome stronghold
			if(c.MAGICDATAHANDLER.checkMagicRunes(29031)){
				c.MAGICDATAHANDLER.removeMagicRunes(29031);
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 2459,3414, 0);
				c.getClientMethodHandler().addSkillXP(40*c.rate, c.playerMagic);
			}
			break;

		case 4150: //Camelot Teleport
			if(c.MAGICDATAHANDLER.checkMagicRunes(4150)){
				c.MAGICDATAHANDLER.removeMagicRunes(4150);
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 2757,3477, 0);
				c.getClientMethodHandler().addSkillXP(40*c.rate, c.playerMagic);
			}
			break;

		case 6004: //east ardy teleport 
			if(c.MAGICDATAHANDLER.checkMagicRunes(6004)){
				c.MAGICDATAHANDLER.removeMagicRunes(6004);
				c.getClientMethodHandler().isteleporting2(409, 1818, 15, 2661, 3308, 0);
				c.getClientMethodHandler().addSkillXP(40*c.rate, c.playerMagic);
			}
			break;

		case 6005: //open teleport
			break;

		case 59135:
			c.getClientMethodHandler().isteleporting2(409, 1818, 15, 2134, 4907, 0);
			break;

		case 28169:
			c.getFrameMethodHandler().menu(c.getMenuHandler().skillInformation());
			break;

		case 28174:
			c.getFrameMethodHandler().menu(c.getMenuHandler().combatInformation());
			break;

		case 28168:
			c.getFrameMethodHandler().Menu(c.getMenuHandler().ancientsmenu());
			break;

		case 24135: // Clue c.debug set to on
		{
			c.sendMessage("Clue c.debugging set to true.");
			c.cluedebug = true;
			break;
		}
		case 24134: // Clue c.debug set to off
		{
			c.sendMessage("Clue c.debugging set to false.");
			c.cluedebug = false;
			break;
		}
		case 28164: // 
		{
			c.questid = 1;
			c.getMenuHandler().questMenus();
		}
		break;

		case 28165: //
		{
			c.questid = 2;
			c.getMenuHandler().questMenus();
		}
		break;

		case 28166: // 
		{
			c.questid = 3;
			c.getMenuHandler().questMenus();
		}
		break;



		case 28171: //
		{
			c.questid = 6;
			c.getMenuHandler().questMenus();
		}
		break;

		case 47130:
			c.getFrameMethodHandler().menu(c.getMenuHandler().slayermenu());
			break;

		case 33206: //attack menu
		case 33209: //strength menu
		case 33212: //defence menu
		case 33207: //hp menu
			c.getFrameMethodHandler().menu(c.getMenuHandler().combatMenu());
			break;
		case 33215: //range menu
			c.getFrameMethodHandler().menu(c.getMenuHandler().rangeMenu());
			break;
		case 33221: //magic menu
			c.getFrameMethodHandler().menu(c.getMenuHandler().magicMenu());
			break;
		case 33218:
			c.getFrameMethodHandler().menu(c.getMenuHandler().prayerMenu());
			break;
		case 33224:
			c.getFrameMethodHandler().menu(c.getMenuHandler().runecraftingMenu());
			break;
		case 33210:
			c.getFrameMethodHandler().menu(c.getMenuHandler().agilityGuide());
			break;
		case 33219:
			c.getFrameMethodHandler().menu(c.getMenuHandler().craftingGuide());
			break;
		case 33222:
			c.getFrameMethodHandler().menu(c.getMenuHandler().fletchingGuide());
			break;
		case 33211:
			c.getFrameMethodHandler().menu(c.getMenuHandler().smithingGuide());
			break;
		case 33220:
			c.getFrameMethodHandler().menu(c.getMenuHandler().firemakingGuide());
			break;
		case 33223:
			c.getFrameMethodHandler().menu(c.getMenuHandler().woodcuttingGuide());
			break;

		case 28175:
			c.getFrameMethodHandler().menu(c.getMenuHandler().bossInformation());
			break;

		case 31195: //insert
			c.bankRearrange = "insert";
			break;
		case 31194: //swap
			c.bankRearrange = "swap";
			break;

			/*
1 = grand tree to mts
2 = mts to grand tree
3 = grand tree to castle
4 = grand tree to desert
5 = desert to grandtree
6 = small tree to grand tree
7 = grand tree to small tree
8 = grand tree to crash island
9 = crash island to grand tree
10 = grand tree to ogre?
11 = ogre to grand tree
			 */

		case 3056: case 3058: case 3059: case 3060:
			c.sendMessage("That destination is not reachable from this location.");
			break;

		case 3057: //gnome glider grand tree
			if(c.isInArea(2543,2969,2550,2974)){ //feldip hills area
				c.teleport(2465,3499,3);
				c.getFrameMethodHandler().setClientConfig(153, 11);
				c.getFrameMethodHandler().showInterface(802);
			}
			if(c.isInArea(2461,3496,2470,3508)) //grand tree area
				c.sendMessage("You are already at that location.");
			break;
		case 48054: //gnome glider feldip hills		
			if(c.isInArea(2543,2969,2550,2974)) //feldip hills area
				c.sendMessage("You are already at that location.");
			if(c.isInArea(2461,3496,2470,3508)){ //grand tree area
				c.teleport(2543,2969);
				c.getFrameMethodHandler().setClientConfig(153, 10);
				c.getFrameMethodHandler().showInterface(802);			
			}
			break;

		default:
			//System.out.c.println("Player stands in: X="+c.absX+" Y="+c.absY);
			if(c.debugmode)c.debug("Case 185: Unhandled Action Button: "+actionButtonId);
			break;
		}


	}


}

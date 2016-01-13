
public class ButtonClickHandler {
	private client c = null;
	
	public ButtonClickHandler(client pc){
		this.c = pc;
	}
	
	public void clickButton(int actionButtonId){
		

		c.debug("Case 185: "+actionButtonId);
	if(lists.prayerList.exists(actionButtonId)){
		c.PRAY.checkPrayer(actionButtonId);
		break;
	}


		switch(actionButtonId) {
		//These values speak for themselves
		//case 4126: windstrike break;

		//case 59136:
		//panellist();
		//break;			

		case 28170:
			getFrameMethodHandler().menu(this.menuHandler.newBeginnings());
			break;

		case 150: //auto retaliate on
			autoRetaliate = 1;
			getFrameMethodHandler().sendQuest("@gre@Auto Retaliate", 155); //auto retaliate
			break;
		case 151: //auto retaliate off
			autoRetaliate = 0;
			getFrameMethodHandler().sendQuest("@red@Auto Retaliate", 155); //auto retaliate
			break;

		case 54104:
			getFrameMethodHandler().Menu(this.menuHandler.farmingGuide());
			break;

		case 21168:
			getFrameMethodHandler().RemoveAllWindows();
			break;

		case 73104:
			sendMessage("X: "+absX+" Y: "+absY);
			println("X: "+absX+" Y: "+absY);
			break;

		case 73108:
			getFrameMethodHandler().playerMenuFrames();
			break;

		case 1097:
			if(spellbook == 1) 
				setSidebarInterface(0, 1689);
			else 
				setSidebarInterface(0, 1829);
			break;

			//Autocast

			//4 select option
		case 32017: //1st option
			if(optionsMenu){
				optionsMenu = false;
				if(oX1 != -1 && oY1 != -1){
					isteleporting2(409, 1818, 15, oX1, oY1, 0);
					RemoveAllWindows();
					oX1 = -1;
					oY1 = -1;
				}
				if(oX1 == -1 || oY1 == -1){
					RemoveAllWindows();
					oX1 = -1;
					oY1 = -1;
				}
			}

			if(starter4Options){
				starter4Options = false;
				if(freeSlots() >= 8){
					npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					addItems(1075,1103,2894,1007,1291,1173); //bronze platelegs, chainmail, grey boots, red cape, bronze longsword, bronze sq shield
					addItem(995,10000); //10,000 GP to start
					addItem(352, 100); //100 cooked pike
					starter = 1;
				}
				else npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
				break;
			}

			if(skillMasterPurchase){ //skillcape
				skillMasterPurchase = false;
				if(getLevelForXP(playerXP[skill99ID]) >= 99){
					if(playerHasItemAmount(995, 99000)){ //99k to purchase
						if(freeSlots() >= 1){
							npcdialogue(skillMasterName, skillMasterID, "I'll take your 99,000 GP, ","and here's your skillcape.");
							deleteItem(995, getItemSlot(995), 100000);
							addItem(skillcape,1);
						}
						else npcdialogue(skillMasterName, skillMasterID, "Try clearing up some inventory", "space first.");
					}
					else npcdialogue(skillMasterName, skillMasterID, "You do not have enough coins", "to afford this cape.");
				}
				else npcdialogue(skillMasterName, skillMasterID, "If you want to purchase a cape,","you need to have 99 "+skillName);
				break;
			}

			if(skillMaster){
				skillMaster = false;
				npcdialogue(skillMasterName, skillMasterID, skillMasterDialogue);
				break;
			}

			if(arianwyn){
				arianwyn = false;
				npcdialogue("Arianwyn", 1202, "We were able to contain the demon with","your help. Thank you.");
				break;
			}

			if(slayer4Options){
				slayer4Options = false;
				if(slayerCount < 10){
					this.SLAYER.generateTask();
					npcdialogue(getNpcName(slayerMaster), slayerMaster, "You want to help us rid the world", "of annoying monsters?", "I am fine with this.", "Sure, I'll give you a task.",
							"I want you to slay "+slayerCount+" "+this.SLAYER.getTaskName(slayerNPC)+"s.");
				}
				else
					npcdialogue(getNpcName(slayerMaster), slayerMaster, "Don't try and be sneaky with me.", "I know you still haven't finished", "your original Slayer task!", "Now get out of here.");
				break;
			}

			if (tokenexchange){
				tokenexchange = false;
				if (playerHasItemAmount(13303, 1) && freeSlots() >= 1){
					deleteItem(13303, getItemSlot(13303), 1);
					addItem(15336, 1);
					npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
				}
				else if (playerHasItemAmount(13303, 1) && freeSlots() < 1){
					npcdialogue("Token Exchange Master", 410, "I can't give you a sword", "with a full inventory.");
				}
				else{
					npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				}
			}

			if (tokenexchange2){
				tokenexchange2 = false;
				if (playerHasItemAmount(13303, 1) && freeSlots() >= 1){
					deleteItem(13303, getItemSlot(13303), 1);
					addItem(3627, 1);
					npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Arcane Spirit Shield.");
				}
				else if (playerHasItemAmount(13303, 1) && freeSlots() < 1){
					npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");
				}
				else{
					npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				}
			}

			if (ticketexchange){
				npcdialogue("Jackie The Fruit", 1055, "Below our very feet lies a vast", "and dangerous agility course!", "The way the course works is", "that after you have used each",
						"obstacle, you have 3 seconds to", "tag the ticket dispenser. When", "you tag the dispenser you will", "recieve a ticket. Sometimes you",
						"might recieve more then one ticket", "from a tag, depending on your luck!", "You can exchange tickets for rewards", "such as items and EXP, good luck!");
				ticketexchange = false;
			}

			if (ticketexchange2){
				if (playerHasItemAmount(2996, 100)){
					int exprec = playerLevel[16]*10000;
					deleteItem(2996, getItemSlot(2996), 100);
					npcdialogue("Jackie The Fruit", 1055, "I'll take those 100 tickets and", "here's your "+exprec+" EXP.");
					addSkillXP(exprec, 16);
					ticketexchange2 = false;
				}
				else{
					npcdialogue("Jackie The Fruit", 1055, "Come back to me when you have 100 tickets!");
					ticketexchange2 = false;
				}
			}

			if (soulwars){
				int recieved = playerLevel[3]*soulbonus;
				addSkillXP(recieved, 3);
				pkpoints -= 10;
				sendMessage("You recieve "+recieved+" hitpoints exp. You have "+pkpoints+" PK points left.");
				RemoveAllWindows();
				soulwars = false;
			}

			if (soulwars2){
				int recieved = playerLevel[1]*soulbonus;
				addSkillXP(recieved, 1);
				pkpoints -= 10;
				sendMessage("You recieve "+recieved+" defence exp. You have "+pkpoints+" PK points left.");
				RemoveAllWindows();
				soulwars2 = false;
			}

			if (anger) {
				addItem(7806, 1);
				sendMessage("You take the Anger Sword.");
				RemoveAllWindows();
				anger = false;
			}

			if (glory4) {
				isteleporting2(409, 1818, 15, 3024, 3206, 0);
				RemoveAllWindows();
				glory4 = false;
			}

			if (fletchingoption){
				if (playerLevel[9] >= fletchingshortlvl){
					startAnimation(1248);
					fletchingitem = fletchingshort;
					fletchingprocessshort = 4;
					RemoveAllWindows();
					fletchingoption = false;
				}
				else if (playerLevel[9] != fletchingshortlvl){
					RemoveAllWindows();
					sendMessage("You need "+fletchingshortlvl+" fletching for that bow.");
					fletchingoption = false;
				}
			}
			break;

		case 32018: //2nd option
			if(optionsMenu){
				optionsMenu = false;
				if(oX2 != -1 && oY2 != -1){
					isteleporting2(409, 1818, 15, oX2, oY2, 0);
					RemoveAllWindows();
					oX2 = -1;
					oY2 = -1;
				}
				if(oX2 == -1 || oY2 == -1){
					RemoveAllWindows();
					oX2 = -1;
					oY2 = -1;
				}
			}

			if(starter4Options){
				starter4Options = false;
				if(freeSlots() >= 8){
					npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					addItems(577,1011,2579,1007,1379,3843); //wizard robe, bottom, boots, staff, damaged book of guthix, 
					addItem(995,10000); //10,000 GP to start
					addItem(352, 100); //100 cooked pike
					starter = 1;
				}
				else npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
				break;
			}	

			if(skillMasterPurchase){ //skillcape trimmed
				skillMasterPurchase = false;
				if(getLevelForXP(playerXP[skill99ID]) >= 99 && masteries > 1){
					if(playerHasItemAmount(995, 99000)){ //99k to purchase
						if(freeSlots() >= 1){
							npcdialogue(skillMasterName, skillMasterID, "I'll take your 99,000 GP, ","and here's your trimmed skillcape.");
							deleteItem(995, getItemSlot(995), 100000);
							addItem(skillcapeTrimmed,1);
						}
						else npcdialogue(skillMasterName, skillMasterID, "Try clearing up some inventory", "space first.");
					}
					else npcdialogue(skillMasterName, skillMasterID, "You do not have enough coins", "to afford this cape.");
				}
				else npcdialogue(skillMasterName, skillMasterID, "If you want to purchase a trimmed cape,","you need to have 99 "+skillName+",", "and at least another skill mastery.");
				break;
			}

			if(skillMaster){
				skillMaster = false;
				skillMasterPurchase = true;
				selectoption2("Purchase?", "Skillcape (99,000 GP)", "Skillcape(t) (90,000 GP)", "Hood (99,000 GP)", "Nevermind.");
				break;
			}

			if(arianwyn){
				arianwyn = false;
				npcdialogue("Arianwyn", 1202, "I have switched your spellbook.");
				if(spellbook == 0){
					spellbook = 1;
					setSidebarInterface(6, 12855);
				}
				else{
					spellbook = 0;
					setSidebarInterface(6, 1151);
				}
				savechar();
				savemoreinfo();
				break;
			}

			if(slayer4Options){
				slayer4Options = false;
				String npcName = this.SLAYER.getTaskName(slayerNPC);
				if(slayerCount > 1)
					npcName += "s";
				if(slayerNPC == 0){
					npcdialogue(getNpcName(slayerMaster), slayerMaster, "You currently have no task.");
					break;
				}
				if(slayerCount <= 0){
					npcdialogue(getNpcName(slayerMaster), slayerMaster, "You have completed your Slayer task.");
					break;
				}
				npcdialogue(getNpcName(slayerMaster), slayerMaster, "From the looks of it...", "You have "+slayerCount+" "+npcName+" left.");
				break;
			}		

			if (tokenexchange){
				tokenexchange = false;
				if (playerHasItemAmount(13303, 1) && freeSlots() >= 1){
					deleteItem(13303, getItemSlot(13303), 1);
					addItem(15334, 1);
					npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
					tokenexchange = false;
				}
				else if (playerHasItemAmount(13303, 1) && freeSlots() < 1){
					npcdialogue("Token Exchange Master", 410, "I can't give you a sword with", "a full inventory");
				}
				else npcdialogue("Token Exchange Master", 410, "You need at least one token!");
			}

			if (tokenexchange2){
				tokenexchange2 = false;
				if (playerHasItemAmount(13303, 1) && freeSlots() >= 1){
					deleteItem(13303, getItemSlot(13303), 1);
					addItem(3637, 1);
					npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Elysian Spirit Shield.");
				}
				else if (playerHasItemAmount(13303, 1) && freeSlots() < 1)
					npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");

				else npcdialogue("Token Exchange Master", 410, "You need at least one token.");
			}

			if (ticketexchange2){
				if (playerHasItemAmount(2996, 250) && freeSlots() >= 1){
					deleteItem(2996, getItemSlot(2996), 250);
					addItem(12003, 1);
					npcdialogue("Jackie The Fruit", 1055, "I'll take those 250 tickets and", "here's your Void Knight Gloves.");
					ticketexchange2 = false;
				}
				else if (playerHasItemAmount(2996, 250) && freeSlots() < 1){
					npcdialogue("Jackie The Fruit", 1055, "Come back when you have more", "room available in your inventory.");
					ticketexchange2 = false;
				}
				else{
					npcdialogue("Jackie The Fruit", 1055, "Come back when you have 250 tickets.");
					ticketexchange2 = false;
				}
			}

			if (ticketexchange){
				int exprec = playerLevel[16]*10000;
				selectoption2("Rewards", "100 Tickets-"+exprec+" Agility EXP", "250 Tickets-Void Knight Gloves", "500 Tickets-Agility Armor", "Cancel!");
				ticketexchange = false;
				ticketexchange2 = true;
				//playerLevel[16] >= 99
			}                  

			if (soulwars){
				int recieved = playerLevel[0]*soulbonus;
				addSkillXP(recieved, 0);
				pkpoints -= 10;
				sendMessage("You recieve "+recieved+" attack exp. You have "+pkpoints+" PK points left.");
				RemoveAllWindows();
				soulwars = false;
			}

			if (anger) {
				addItem(7809, 1);
				sendMessage("You take the Anger Spear.");
				RemoveAllWindows();
				anger = false;
			}

			if (soulwars2){
				int recieved = playerLevel[4]*soulbonus;
				addSkillXP(recieved, 4);
				pkpoints -= 10;
				sendMessage("You recieve "+recieved+" range exp. You have "+pkpoints+" PK points left.");
				RemoveAllWindows();
				soulwars2 = false;
			}

			if (glory4) {
				isteleporting2(409, 1818, 15, 2953, 3215, 0);
				RemoveAllWindows();
				glory4 = false;
			}			   

			if (fletchingoption){
				if (playerLevel[9] >= fletchinglonglvl){
					startAnimation(1248);
					fletchingexp += fletchingexp/5;
					fletchingitem = fletchinglong;
					fletchingprocessshort = 4;
					RemoveAllWindows();
					fletchingoption = false;
				}
				else if (playerLevel[9] != fletchinglonglvl){
					RemoveAllWindows();
					sendMessage("You need "+fletchinglonglvl+" fletching for that bow.");
					fletchingoption = false;
				}
			}
			break;

		case 32019: //3rd option
			if(optionsMenu){
				optionsMenu = false;
				if(oX3 != -1 && oY3 != -1){
					isteleporting2(409, 1818, 15, oX3, oY3, 0);
					RemoveAllWindows();
					oX3 = -1;
					oY3 = -1;
				}
				if(oX3 == -1 || oY3 == -1){
					RemoveAllWindows();
					oX3 = -1;
					oY3 = -1;
				}
			}	

			if(starter4Options){
				starter4Options = false;
				if(freeSlots() >= 8){
					npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					addItems(1095, 1129, 2577, 1007, 841); //leather chaps, body, ranger boots, red cape, shortbow
					addItem(882,1000); //1000 bronze arrows
					addItem(995,10000); //10,000 GP to start
					addItem(352, 100); //100 cooked pike
					starter = 1;
				}
				else npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
			}	

			if(skillMasterPurchase){ //hood
				skillMasterPurchase = false;
				if(getLevelForXP(playerXP[skill99ID]) >= 99){
					if(playerHasItemAmount(995, 99000)){ //99k to purchase
						if(freeSlots() >= 1){
							npcdialogue(skillMasterName, skillMasterID, "I'll take your 99,000 GP, ","and here's your hood.");
							deleteItem(995, getItemSlot(995), 100000);
							addItem(skillHood,1);
						}
						else npcdialogue(skillMasterName, skillMasterID, "Try clearing up some inventory", "space first.");
					}
					else npcdialogue(skillMasterName, skillMasterID, "You do not have enough coins", "to afford this hood.");
				}
				else npcdialogue(skillMasterName, skillMasterID, "If you want to purchase a hood,","you need to have 99 "+skillName);
				break;
			}

			if(skillMaster){
				skillMaster = false;
				RemoveAllWindows();
				break;
			}

			if(arianwyn){
				arianwyn = false;
				BIS = true;
				selectoption("Buy a Staff for 1,000,000 GP?", "Sure thing!", "No", "...");
			}

			if(slayer4Options){
				slayer4Options = false;
				slayer2Options = true;
				selectoption("100,000 GP for a Slayer Crystal?", "Sure", "No thanks.");
			}

			if (tokenexchange){
				tokenexchange = false;
				if (playerHasItemAmount(13303, 1) && freeSlots() >= 1){
					deleteItem(13303, getItemSlot(13303), 1);
					addItem(15335, 1);
					npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
					tokenexchange = false;
				}
				else if (playerHasItemAmount(13303, 1) && freeSlots() < 1)
					npcdialogue("Token Exchange Master", 410, "I can't give you a sword", "with a full inventory.");
				else
					npcdialogue("Token Exchange Master", 410, "You need at least one token.");
			}

			if (tokenexchange2){
				tokenexchange2 = false;
				if (playerHasItemAmount(13303, 1) && freeSlots() >= 1){
					deleteItem(13303, getItemSlot(13303), 1);
					addItem(3629, 1);
					npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Spectral Spirit Shield.");
				}
				else if (playerHasItemAmount(13303, 1) && freeSlots() < 1)
					npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");

				else npcdialogue("Token Exchange Master", 410, "You need at least one token.");
			}

			if (ticketexchange2){
				if (playerHasItemAmount(2996, 500) && freeSlots() >= 2){
					deleteItem(2996, getItemSlot(2996), 500);
					addItem(13301, 1);
					addItem(13302, 1);
					npcdialogue("Jackie The Fruit", 1055, "I'll take those 500 tickets, and","here's your Agility Armor");
					ticketexchange2 = false;
				}
				else if (playerHasItemAmount(2996, 500) && freeSlots() < 2){
					npcdialogue("Jackie The Fruit", 1055, "You need at least 2 empty slots", "in your inventory.");
					ticketexchange2 = false;
				}
				else{
					npcdialogue("Jackie The Fruit", 1055, "Come back to me when you have", "500 tickets.");
					ticketexchange2 = false;
				}
			}

			if (ticketexchange){
				ticketexchange = false;
				RemoveAllWindows();
			}

			if (soulwars){
				int recieved = playerLevel[2]*soulbonus;
				addSkillXP(recieved, 2);
				pkpoints -= 10;
				sendMessage("You recieve "+recieved+" strength exp. You have "+pkpoints+" PK points left.");
				RemoveAllWindows();
				soulwars = false;
			}

			if (anger) {
				addItem(7808, 1);
				sendMessage("You take the Anger Mace.");
				RemoveAllWindows();
				anger = false;
			}

			if (soulwars2){
				int recieved = playerLevel[5]*soulbonus;
				addSkillXP(recieved, 5);
				pkpoints -= 10;
				sendMessage("You recieve "+recieved+" prayer exp. You have "+pkpoints+" PK points left.");
				RemoveAllWindows();
				soulwars2 = false;
			}

			if (glory4) {
				isteleporting2(409, 1818, 15, 2919, 9804, 0);
				RemoveAllWindows();
				glory4 = false;
			}

			if (fletchingoption){ //arrowshafts
				startAnimation(1248);
				fletchingexp = 30;
				fletchingitem = 52;
				fletchingprocessshort = 4;
				RemoveAllWindows();
				fletchingoption = false;
			}

		case 32020: //4th option
			if(optionsMenu){
				optionsMenu = false;
				if(oX4 != -1 && oY4 != -1){
					isteleporting2(409, 1818, 15, oX4, oY4, 0);
					RemoveAllWindows();
					oX4 = -1;
					oY4 = -1;
				}
				if(oX4 == -1 || oY4 == -1){
					RemoveAllWindows();
					oX4 = -1;
					oY4 = -1;
				}
			}	

			if (fletchingoption){
				RemoveAllWindows();
				fletchingoption = false;
			}

			if(starter4Options){
				starter4Options = false;
				RemoveAllWindows();
				break;
			}	

			if(skillMasterPurchase){
				skillMasterPurchase = false;
				RemoveAllWindows();
				break;
			}

			if(skillMaster){
				skillMaster = false;
				RemoveAllWindows();
				break;
			}

			if(arianwyn){
				arianwyn = false;
				RemoveAllWindows();
			}

			if(slayer4Options){
				slayer4Options = false;
				if(slayerMaster == 1208)
					skillMaster(slayerMaster, getNpcName(slayerMaster), 14112,14113,14114, "Slayer", playerSlayer, new String[]{"I travelled halfway across the world to","deal with a infestation problem.","Can you believe that?"});
				if(slayerMaster == 1596)
					skillMaster(slayerMaster, getNpcName(slayerMaster), 14112,14113,14114, "Slayer", playerSlayer, new String[]{"Take care as you travel South,","naught but foulness infests those lands."});
				break;
			}

			if (tokenexchange){
				tokenexchange = false;
				tokenexchange2 = true;
				selectoption2("Options", "1 Server Token - Arcane Spirit Shield", "1 Server Token - Elysian Spirit Shield", "1 Server Token - Spectral Spirit Shield", "Cancel");
				break;
			}
			if (tokenexchange2){
				tokenexchange2 = false;
				RemoveAllWindows();
				break;
			}
			if (ticketexchange){
				ticketexchange = false;
				RemoveAllWindows();
				break;
			}
			if (ticketexchange2){
				ticketexchange2 = false;
				RemoveAllWindows();
				break;
			}

			if (soulwars){
				//RemoveAllWindows();
				soulwars = false;
				soulwars2 = true;
				selectoption2("You have "+pkpoints+" pts", "Defence-"+playerLevel[1]*soulbonus+" exp-10pts", "Range-"+playerLevel[4]*soulbonus+" exp-10pts", "Pray-"+playerLevel[5]*soulbonus+" exp-10pts", "Cancel.");
				break;
			}

			if(soulwars2){
				soulwars2 = false;
				RemoveAllWindows();
			}

			if (anger) {
				addItem(7807, 1);
				sendMessage("You take the Anger Battleaxe.");
				RemoveAllWindows();
				anger = false;
				break;
			}

			if (glory4) {
				isteleporting2(409, 1818, 15, 2134, 4907, 0);
				sendMessage("You teleport to an alternate Draynor.");
				RemoveAllWindows();		
				glory4 = false;
			}
			break;



		case 14067: // Char design accept button
			RemoveAllWindows();
			break;


		case 29113:
		case 29038:
		case 48023:
		case 29138:
			getFilling(); //New special system	
			int specAmount = 0;
			int curWeap = playerEquipment[playerWeapon];
			if(!litBar){
				if(curWeap == 35){ //Excalibur
					if(specialDelay >= 10){
						litBar = true;
						ExcaliburSpecial();
					}
					else sendMessage("You do not have enough power.");
					break;
				}
				if(curWeap == 7158){ //Dragon 2h
					if(specialDelay >= 6){
						litBar = true;
						if (IsAttackingNPC == false)
							Dragon2hSpecial();				
					}
					else sendMessage("You do not have enough power.");
					break;
				}
				int requiredSpecialAmount = getSpecAmount();
				if(requiredSpecialAmount == -1) break;
				if(specialDelay >= requiredSpecialAmount)
					litBar = true;
				else{
					sendMessage("You do not have enough power.");
					break;
				}
			}
			else{
				allSdisable();
				break;
			}

			break;



		case 26018:
			client plr = (client) server.playerHandler.players[duelWith];
			int NeededSlots = 0;
			if(plr.duelStatus == 1 && plr != null) {
				sendFrame126("Waiting for other player...", 6684);
				plr.sendFrame126("Other player has Accepted", 6684);
				duelStatus = 2;
			}
			else if(plr.duelStatus == 2) {
				if(duelRule[4]) {
					NeededSlots++;
				}
				if(duelRule[5]) {
					NeededSlots += 6;
				}
				NeededSlots += GetDuelItemSlots();
				if(NeededSlots > freeSlots()) {
					sendMessage("You don't have enough inventory space for the duel and stake options");
					plr.sendMessage("The other player doesn't have enough inventory space for the duel and stake options");
					duelStatus = 1;
					plr.duelStatus = 1;
					break;
				}
				duelStatus = 3;
				duelChatTimer = 2;
				startDuel = true;
				plr.duelStatus = 3;
				plr.duelChatTimer = 2;
				plr.startDuel = true;
				teleportToX = 2468;
				teleportToY = 9681;
				plr.teleportToX = 2468;
				plr.teleportToY = 9675;
				didTeleport = true;
				RemoveAllWindows();
				plr.RemoveAllWindows();
				if(duelRule[4]) {
					removeItemAndSendFrame(playerEquipment[playerWeapon], playerWeapon);
					plr.removeItemAndSendFrame(playerEquipment[playerWeapon], playerWeapon);
				}
				if(duelRule[5]) {
					removeItemAndSendFrame(playerEquipment[playerHat], playerHat);
					removeItemAndSendFrame(playerEquipment[playerCape], playerCape);
					removeItemAndSendFrame(playerEquipment[playerChest], playerChest);
					removeItemAndSendFrame(playerEquipment[playerShield], playerShield);
					removeItemAndSendFrame(playerEquipment[playerLegs], playerLegs);
					removeItemAndSendFrame(playerEquipment[playerFeet], playerFeet);
					plr.removeItemAndSendFrame(playerEquipment[playerHat], playerHat);
					plr.removeItemAndSendFrame(playerEquipment[playerCape], playerCape);
					plr.removeItemAndSendFrame(playerEquipment[playerChest], playerChest);
					plr.removeItemAndSendFrame(playerEquipment[playerShield], playerShield);
					plr.removeItemAndSendFrame(playerEquipment[playerLegs], playerLegs);
					plr.removeItemAndSendFrame(playerEquipment[playerFeet], playerFeet);
				}
			}
			break;

		case 26069:
			client rule1 = (client) server.playerHandler.players[duelWith];
			duelRule[0] = true;
			RefreshDuelRules();
			rule1.duelRule[0] = true;
			rule1.RefreshDuelRules();
			break;

		case 26070:
			client rule2 = (client) server.playerHandler.players[duelWith];
			duelRule[1] = true;
			RefreshDuelRules();
			rule2.duelRule[1] = true;
			rule2.RefreshDuelRules();
			break;

		case 26071:
			client rule3 = (client) server.playerHandler.players[duelWith];
			duelRule[2] = true;
			RefreshDuelRules();
			rule3.duelRule[2] = true;
			rule3.RefreshDuelRules();
			break;

		case 26072:
			client rule4 = (client) server.playerHandler.players[duelWith];
			duelRule[3] = true;
			RefreshDuelRules();
			rule4.duelRule[3] = true;
			rule4.RefreshDuelRules();
			break;

		case 26073:
			client rule5 = (client) server.playerHandler.players[duelWith];
			duelRule[4] = true;
			RefreshDuelRules();
			rule5.duelRule[4] = true;
			rule5.RefreshDuelRules();
			break;

		case 26074:
			client rule6 = (client) server.playerHandler.players[duelWith];
			duelRule[5] = true;
			RefreshDuelRules();
			rule6.duelRule[5] = true;
			rule6.RefreshDuelRules();
			break;

		case 10162: //close book interface
			closeInterface();
			break;

		case 39178: //close book interface
			closeInterface();
			break;

			/* case 29030: DragonLongSpecial(); break; */


		case 153:
			sendQuest("@gre@Move speed", 158);
			if (runningEnergy > 0) 
				isRunning2 = true;
			break;
		case 152:
			sendQuest("@yel@Move speed", 158);
			isRunning2 = false;
			break;

		case 130: //close interface
			debug("Closing Interface");
			break;


		case 168: // char emote
			showInterface(3559);
			break;

		case 169: // normal emote
			Item.capeEmote(this);
			break;

		case 162: // think emote
			setAnimation(0x359);
			break;

		case 164: // bow emote
			setAnimation(0x35A);
			break;

		case 165: // angry emote
			setAnimation(0x35B);
			break;

		case 161: // cry emote
			setAnimation(0x35C);
			break;

		case 170: // laugh emote
			setAnimation(0x35D);
			break;

		case 171: // cheer emote
			setAnimation(0x35E);
			break;

		case 163: // wave emote
			setAnimation(0x35F);
			break;

		case 167: // beckon emote
			setAnimation(0x360);
			break;

		case 172: // clap emote
			setAnimation(0x361);
			break;

		case 166: // dance emote
			setAnimation(0x362);
			break;

		case 52050: // panic emote
			setAnimation(0x839);
			break;

		case 52051: // jig emote
			setAnimation(0x83A);
			break;

		case 52052: // spin emote
			setAnimation(0x83B);
			break;

		case 52053: // headbang emote
			setAnimation(0x83C);
			break;

		case 52054: // joy jump emote
			setAnimation(0x83D);
			break;

		case 52055: // rasp' berry emote
			setAnimation(0x83E);
			break;

		case 52056: // yawn emote
			setAnimation(0x83F);
			break;

		case 52057: // salute emote
			setAnimation(0x840);
			break;

		case 52058: // shrug emote
			setAnimation(0x841);
			break;

		case 43092: // blow kiss emote
			setAnimation(0x558);
			break;

		case 2155: // glass box emote
			setAnimation(0x46B);
			break;

		case 25103: // climb rope emote
			setAnimation(0x46A);
			break;

		case 25106: // lean emote
			setAnimation(0x469);
			break;

		case 2154: // glass wall emote
			setAnimation(0x468);
			break;

		case 52071: // goblin bow emote
			setAnimation(0x84F);
			break;

		case 52072: // goblin dance emote
			setAnimation(0x850);
			break;

		case 59062: // scared
			setAnimation(2836);
			break;

		case 72032: // zombie walk
			setAnimation(3544);
			break;

		case 72033: // zombie dance
			setAnimation(3543);
			break;

		case 9125: //Accurate
		case 22228: //punch (unarmed)
		case 48010: //flick (whip)
		case 21200: //spike (pickaxe)
		case 1080: //bash (staff)
		case 6168: //chop (axe)
		case 6236: //accurate (long bow)
		case 17102: //accurate (darts)
		case 8234: //stab (dagger)
			FightType = 1;
			SkillID = 0;
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
			FightType = 4;
			SkillID = 1;
			break;
		case 9127: // Controlled
		case 48009: //lash (whip)
		case 33018: //jab (hally)
		case 6234: //longrange (long bow)
		case 18077: //lunge (spear)
		case 18080: //swipe (spear)
		case 18079: //pound (spear)
		case 17100: //longrange (darts)
			FightType = 3;
			SkillID = 3;
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
			FightType = 2;
			SkillID = 2;
			break;
		case 9154: //Log out
			if(System.currentTimeMillis() - LogoutDelay > 10000) //30 second logout delay
				disconnectPlayerAndSave("Logout Button");
			else sendMessage("You must be out of combat for at least 10 seconds to do that.");
			break;
		case 21011:
			takeAsNote = false;
			break;
		case 21010:
			takeAsNote = true;
			break;
		case 13092:
			if (tradeWith > 0) {
				if (PlayerHandler.players[tradeWith].tradeStatus == 2) {
					tradeStatus = 3;
					sendFrame126("Waiting for other player...", 3431);
				} else if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
					tradeStatus = 3;
					//TradeGoConfirm();
				}
			}
			break;
		case 13218:
			if (tradeWith > 0) {
				if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
					tradeStatus = 4;
					sendFrame126("Waiting for other player...", 3535);
				} else if (PlayerHandler.players[tradeWith].tradeStatus == 4) {
					tradeStatus = 4;
					//ConfirmTrade();
				}
			}
			break;


		case 9157: //1st choice

			if(travel2_X1 != -1 && travel2_Y1 != -1){
				teleport(travel2_X1, travel2_Y1, travelHeight);
				travel2_X1 = -1;
				travel2_Y1 = -1;
				travelHeight = 0;
				RemoveAllWindows();
				break;
			}

			if (deadopt) {
				npcdialogue("Grave Keeper", 1303, "Now that you've died, I can't let you go", "without some work. Get some copper", "and tin ore, and make a bronze bar.", "Talk to me once you have that bar.");
				deadopt = false;
				break;
			}

			if (startleave) {
				npcdialogue("Survival Expert", 943, "If you would like to leave, please speak","with Captain Shanks.");
				startleave = false;
				break;
			}

			if(spinning){
				spinning = false;
				if(playerHasItem(1779)){
					repeatAnimation(894, 3);
					spinningTimer = 4;
				}
				else sendMessage("You need flax to spin bowstrings.");
				RemoveAllWindows();
			}

			if(slayer2Options){
				slayer2Options = false;
				if(playerHasItemAmount(995, 100000)){
					if(freeSlots() < 1){
						npcdialogue(getNpcName(slayerMaster), slayerMaster, "I'd be happy to take your money,", "but your inventory is full.");
					}
					else{
						npcdialogue(getNpcName(slayerMaster), slayerMaster, "I'll take that 100k.", "Using the Crystal will tell you", "your current task and the remaining amount.");
						deleteItem(995, getItemSlot(995), 100000);
						addItem(611,1);
					}
				}
				else
					npcdialogue(getNpcName(slayerMaster), slayerMaster, "I don't do deals around here.", "It's 100,000 GP, and you don't", "have enough.");
				break;
			}

			if (BIS){
				if (playerHasItemAmount(995, 1000000)){
					if (freeSlots() > 0) {
						npcdialogue("Arianwyn", 1202, "Here's your staff.", "Once again, thanks for helping us.");
						addItem(13308, 1);
						deleteItem(995, getItemSlot(995), 1000000);
						BIS = false;
					}
					else if (freeSlots() == 0) {
						npcdialogue("Arianwyn", 1202, "Your inventory is full.");
						BIS = false;
					}
				}
				else {
					npcdialogue("Arianwyn", 1202, "Seems like you don't have enough money.");
					BIS = false;
				}
				break;
			}

			if (fletchingoption){
				fletchingprocessshort = 0;
				if (playerLevel[9] >= fletchingshortlvl){
					startAnimation(712);
					fletchingitem = fletchingshort;
					fletchingprocessshort = 4;
					RemoveAllWindows();
					stringing = true;
					fletchingoption = false;
				}
				else if (playerLevel[9] != fletchingshortlvl){
					RemoveAllWindows();
					sendMessage("You need "+fletchingshortlvl+" fletching for that bow.");
					fletchingoption = false;
				}
				break;
			}
			RemoveAllWindows();
			break;

		case 9158: //2nd option
			if(travel2_X2 != -1 && travel2_Y2 != -1){
				teleport(travel2_X2, travel2_Y2,travelHeight);
				travel2_X2 = -1;
				travel2_Y2 = -1;
				travelHeight = 0;
				RemoveAllWindows();
				break;
			}              	 


			if(slayer2Options){
				slayer2Options = false;
				npcdialogue(getNpcName(slayerMaster), slayerMaster, "Alright, I'll be seeing ya.");
			}

			if (fletchingoption){
				RemoveAllWindows();
				fletchingoption = false;
				break;
			}

			if (startleave) {
				npcdialogue("Survival Expert", 943, "Some tips? Of course!", "Your teleports can be foudn in","your spellbook and important","information can be found","in your quest tab.");
				startleave = false;
				break;
			}
			if (deadopt){
				addItem(1265, 1);
				npcdialogue("Grave Keeper", 1303, "", "Here's a pickaxe.", "Now go get working!", "");
				deadopt = false;
				break;
			}

			if (BIS){BIS = false; 
			RemoveAllWindows();
			break;}

			RemoveAllWindows();
			break; 

			//autocast

		case 7038: //Wind Strike 
			setAutocast(1152);
			break;
		case 7039://Water Strike
			setAutocast(1154);
			break;
		case 7040://Earth Strike
			setAutocast(1156);
			break;
		case 7041://Fire Strike
			setAutocast(1158);
			break;
		case 7042://Wind Bolt
			setAutocast(1160);
			break;
		case 7043://Water Bolt
			setAutocast(1163);
			break;
		case 7044://Earth Bolt
			setAutocast(1166);
			break;
		case 7045://Fire Bolt
			setAutocast(1169);
			break;
		case 7046://Wind Blast
			setAutocast(1172);
			break;
		case 7047://Water Blast
			setAutocast(1175);
			break;
		case 7048://Earth Blast
			setAutocast(1177);
			break;
		case 7049://Fire Blast
			setAutocast(1181);
			break;
		case 7050://Wind Wave
			setAutocast(1183);
			break;
		case 7051://Water Wave
			setAutocast(1185);
			break;
		case 7052://Earth Wave
			setAutocast(1188);
			break;
		case 7053://Fire Wave
			setAutocast(1189);
			break;
		case 51133: //Smoke Rush
			setAutocast(12939);
			break;
		case 51185: //Shadow Rush
			setAutocast(12987);
			break;
		case 51091: //Blood Rush
			setAutocast(12901);
			break;
		case 24018: //Ice Rush
			setAutocast(12861);
			break;
		case 51159: //Smoke Burst
			setAutocast(12963);
			break;
		case 51211: //Shadow Burst
			setAutocast(13011);
			break;
		case 51111: //Blood Burst
			setAutocast(12919);
			break;
		case 51069: //Ice Burst
			setAutocast(12881);
			break;
		case 51146: //Smoke Blitz
			setAutocast(12951);
			break;
		case 51198: //Shadow Blitz
			setAutocast(12999);
			break;
		case 51102: //Blood Blitz
			setAutocast(12911);
			break;
		case 51058: //Ice Blitz
			setAutocast(12871);
			break;
		case 51172: //Smoke Barrage
			setAutocast(12975);
			break;
		case 51224: //Shadow Barrage
			setAutocast(13023);
			break;
		case 51122: //Blood Barrage
			setAutocast(12929);
			break;
		case 51080: //Ice Barrage
			setAutocast(12891);
			break;

		case 1093:
			if(!autocast){
				sendMessage("Autocast has been activated.");
				autocast = true;
			}
			else{
				autocast = false;
				sendMessage("Autocast has been deactivated.");
			}
			break;

		case 24017:
		case 7212:
			setSidebarInterface(0, 328);
			break;			

		case 33208:
			menu(this.menuHandler.miningGuide());
			break;


		case 33214: 
			menu(this.menuHandler.fishingGuide());
			break;

		case 33217: 
			menu(this.menuHandler.cookingGuide());
			break;

		case 28215:
			Menu(this.menuHandler.ancientsmenu2());
			break;

		case 33213: 
			menu(this.menuHandler.herbloremenu());
			break;

		case 33216:
			Menu(this.menuHandler.thiefmenu());
			break;


		case 50235: //Ancients teleport home
			if(homeTeleportTimer <= 0){
				if(canPlayersTeleportInThisArea()){
					homeTeleportTimer = 15;
					isteleporting2(392, 1161, 20, 3023,3206, 0);
				}
				else sendMessage("You can't use that teleport in this area.");
			}
			else sendMessage("You need to wait "+homeTeleportTimer+" minutes before using this.");
			break;

		case 4140: //home teleport
			if(homeTeleportTimer <= 0){
				if(canPlayersTeleportInThisArea()){
					homeTeleportTimer = 15;
					isteleporting2(409, 1818, 15, 3024, 3206, 0);
				}
				else sendMessage("You can't use that teleport in this area.");
			}
			else sendMessage("You need to wait "+homeTeleportTimer+" minutes before using this.");
			break;

		case 4143: //Free teleport
			//	if(skillsTeleportTimer <= 0){
			//		if(teleArea()){
			//		isteleporting2(409, 1818, 15, 2953, 3215, 0);
			//		skillsTeleportTimer = 15;
			//		}
			//		else sendMessage("You can't use that teleport in this area.");
			//	}
			//	else sendMessage("You need to wait "+skillsTeleportTimer+" minutes before using this.");
			break;

		case 50245: //PVP ancients teleport
		case 4146: //PVP normal teleport
			if(canPlayersTeleportInThisArea())
				select4Options("PVP Teleports", "Lletya [PVP]",2331,3170,"Tyras Camp [PVP]", 2187,3148, "Elf Camp [PVP]", 2207,3258, "Cancel",-1,-1);
			else sendMessage("You cannot activate teleports in this area.");
			break;

		case 50253: //Rimmington
			if(this.MAGICDATAHANDLER.checkMagicRunes(50253)){
				this.MAGICDATAHANDLER.removeMagicRunes(50253);
				isteleporting2(392, 1161, 20, 2954,3214, 0);
				addSkillXP(60*rate, playerMagic);
			}
			break;

		case 51005: //Entrana
			if(this.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				this.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				isteleporting2(392, 1161, 20, 2828,3344, 0);
				addSkillXP(60*rate, playerMagic);
			}
			break;

		case 51013: //Karamja
			if(this.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				this.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				isteleporting2(392, 1161, 20, 2801,3176, 0);
				addSkillXP(60*rate, playerMagic);
			}
			break;

		case 51023: //Barrows
			if(this.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				this.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				isteleporting2(392, 1161, 20, 3662,3495, 0);
				addSkillXP(60*rate, playerMagic);
			}
			break;

		case 51031: //West Ardougne
			if(this.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				this.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				isteleporting2(392, 1161, 20, 2493,3314, 0);
				addSkillXP(60*rate, playerMagic);
			}
			break;

		case 51039: //Grand Tree
			if(this.MAGICDATAHANDLER.checkMagicRunes(actionButtonId)){
				this.MAGICDATAHANDLER.removeMagicRunes(actionButtonId);
				isteleporting2(392, 1161, 20, 2464,3496,1);
				addSkillXP(60*rate, playerMagic);
			}
			break;	

		case 29031: //tree gnome stronghold
			if(this.MAGICDATAHANDLER.checkMagicRunes(29031)){
				this.MAGICDATAHANDLER.removeMagicRunes(29031);
				isteleporting2(409, 1818, 15, 2459,3414, 0);
				addSkillXP(40*rate, playerMagic);
			}
			break;

		case 4150: //mort'ton teleport
			if(this.MAGICDATAHANDLER.checkMagicRunes(4150)){
				this.MAGICDATAHANDLER.removeMagicRunes(4150);
				isteleporting2(409, 1818, 15, 3497,3489, 0);
				addSkillXP(40*rate, playerMagic);
			}
			break;

		case 6004: //east ardy teleport 
			if(this.MAGICDATAHANDLER.checkMagicRunes(6004)){
				this.MAGICDATAHANDLER.removeMagicRunes(6004);
				isteleporting2(409, 1818, 15, 2661, 3308, 0);
				addSkillXP(40*rate, playerMagic);
			}
			break;

		case 6005: //port sarim teleport
			if(this.MAGICDATAHANDLER.checkMagicRunes(6005)){
				this.MAGICDATAHANDLER.removeMagicRunes(6005);
				isteleporting2(409, 1818, 15, 3024,3206, 0);
				addSkillXP(40*rate, playerMagic);
			}
			break;

		case 59135:
			isteleporting2(409, 1818, 15, 2134, 4907, 0);
			break;

		case 28169:
			menu(MenuHandler.skillInformation());
			break;

		case 28174:
			menu(MenuHandler.combatInformation());
			break;

		case 28168:
			Menu(this.menuHandler.ancientsmenu());
			break;

		case 24135: // Clue debug set to on
		{
			sendMessage("Clue debugging set to true.");
			cluedebug = true;
			break;
		}
		case 24134: // Clue debug set to off
		{
			sendMessage("Clue debugging set to false.");
			cluedebug = false;
			break;
		}
		case 28164: // 
		{
			questid = 1;
			questMenus();
		}
		break;

		case 28165: //
		{
			questid = 2;
			questMenus();
		}
		break;

		case 28166: // 
		{
			questid = 3;
			questMenus();
		}
		break;



		case 28171: //
		{
			questid = 6;
			questMenus();
		}
		break;

		case 47130:
			menu(this.menuHandler.slayermenu());
			break;

		case 33206: //attack menu
		case 33209: //strength menu
		case 33212: //defence menu
		case 33207: //hp menu
			menu(this.menuHandler.combatMenu());
			break;
		case 33215: //range menu
			menu(this.menuHandler.rangeMenu());
			break;
		case 33221: //magic menu
			menu(this.menuHandler.magicMenu());
			break;
		case 33218:
			menu(this.menuHandler.prayerMenu());
			break;
		case 33224:
			menu(this.menuHandler.runecraftingMenu());
			break;
		case 33210:
			menu(this.menuHandler.agilityGuide());
			break;
		case 33219:
			menu(this.menuHandler.craftingGuide());
			break;
		case 33222:
			menu(this.menuHandler.fletchingGuide());
			break;
		case 33211:
			menu(this.menuHandler.smithingGuide());
			break;
		case 33220:
			menu(this.menuHandler.firemakingGuide());
			break;
		case 33223:
			menu(this.menuHandler.woodcuttingGuide());
			break;

		case 28175:
			menu(this.menuHandler.bossInformation());
			break;

		case 31195: //insert
			bankRearrange = "insert";
			break;
		case 31194: //swap
			bankRearrange = "swap";
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
			sendMessage("That destination is not reachable from this location.");
			break;

		case 3057: //gnome glider grand tree
			if(isInArea(2543,2969,2550,2974)){ //feldip hills area
				teleport(2465,3499,3);
				setClientConfig(153, 11);
				showInterface(802);
			}
			if(isInArea(2461,3496,2470,3508)) //grand tree area
				sendMessage("You are already at that location.");
			break;
		case 48054: //gnome glider feldip hills		
			if(isInArea(2543,2969,2550,2974)) //feldip hills area
				sendMessage("You are already at that location.");
			if(isInArea(2461,3496,2470,3508)){ //grand tree area
				teleport(2543,2969);
				setClientConfig(153, 10);
				showInterface(802);			
			}
			break;

		default:
			//System.out.println("Player stands in: X="+absX+" Y="+absY);
			if(debugmode)debug("Case 185: Unhandled Action Button: "+actionButtonId);
			break;
		}
		
		
	}
	
	
}

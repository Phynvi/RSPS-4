package clientHandlers;
import playerData.QuestVariable;
import playerData.client;
import root.misc;
import root.server;
import serverHandlers.ChatRoom;
import serverHandlers.GlobalObjectHandler;
import serverHandlers.PlayerHandler;
import struct.lists;


public class ButtonClickHandler {
	private client c = null;

	public ButtonClickHandler(client pc){
		this.c = pc;
	}

	QuestVariable nextAdvance = null;

	private void advanceQuestVariableAtEndOfDialogue(QuestVariable v){
		nextAdvance = v;
	}

	public boolean mysteriousOldManFirst = false;

	public void nextDialogue(){
		if(!c.npcLines.isEmpty()){
			c.getClientMethodHandler().npcChat();
			return;
		}			
		else{
			switch(c.npcID){
			case 410: //mysterious old man
				if(mysteriousOldManFirst){
					c.getFrameMethodHandler().sendFrame164(14191);
					c.getFrameMethodHandler().sendQuest("Pick the odd one out", 14200);
					mysteriousOldManFirst = false; //needed so we dont loop this
					return;
				}
				else break;
			case 938: //ranalph devere for pd
				c.PDAggro = true;
				break;
			case 605: //quest complete for pd, sir vyvin
				if(c.PD.getValue() == 4){
					c.getFrameMethodHandler().showQuestComplete("A Poisonous Diversion", "10,000 Attack XP", "7,500 Prayer XP", "5,000 Thieving XP", "", "", "", 1);
					c.getClientMethodHandler().addSkillXP(5000, c.playerThieving);
					c.getClientMethodHandler().addSkillXP(7500, c.playerPrayer);
					c.getClientMethodHandler().addSkillXP(10000, c.playerAttack);
					c.getClientMethodHandler().addQuestPoints(1);
					c.PD.setValue(5);
					c.getFrameMethodHandler().loadQuestTab();
					c.getFileLoadingHandler().saveAll();
					return;
				}
				break;
			}
			if(nextAdvance != null){
				nextAdvance.increment();
				nextAdvance = null;
				c.getFrameMethodHandler().loadQuestTab();
				c.getFileLoadingHandler().saveAll();
			}
			c.getFrameMethodHandler().closeInterface();
			return;
		}
	}

	public void clickButton(int actionButtonId){

		c.debug("Case 185: "+actionButtonId+", menuChoice : "+c.menuChoice);
		if(lists.prayerList.exists(actionButtonId)){
			c.getSkillHandler().getPrayerHandler().checkPrayer(actionButtonId);
			return;
		}


		switch(actionButtonId) {
		//These values speak for themselves
		//case 4126: windstrike break;

		//case 59136:
		//panellist();
		//break;			

		case 28171:
			c.getFrameMethodHandler().menu(c.getMenuHandler().PoisonDiversion());
			break;

		case 55117:
		case 55118: //incorrect selections of odd one out
			c.teleport(c.randomx, c.randomy, c.randomh);
			c.getClientMethodHandler().dialogue(410, "You did not select the correct one.");
			break;

		case 55119:
			c.teleport(c.randomx, c.randomy, c.randomh);
			c.getClientMethodHandler().dialogue(410, "You selected the correct one.","Take a gift.");
			c.getInventoryHandler().addItem(6199);
			break;

		case 28215:
			c.getFrameMethodHandler().menu(c.getMenuHandler().sluggishCircumstances());
			break;

		case 28168:
			c.getFrameMethodHandler().menu(c.getMenuHandler().theUndeadProblem());
			break;

		case 28164:
			c.getFrameMethodHandler().menu(c.getMenuHandler().barrowedThings());
			break;

		case 57064: // make default chat/remove default
			ChatRoom ch = c.currentChatRoom;
			if(ch == null){
				c.defaultChatRoomName = "";
				c.getChatRoomHandler().generateChatTab("");
			}
			else{
				c.defaultChatRoomName = c.currentChatRoom.getChatRoomName();
				c.getFileLoadingHandler().savemoreinfo();
				c.sendMessage("You have made "+c.defaultChatRoomName+" your default Chat Room.");
			}
			break;

		case 57063: // leave chat/join default
			ch = c.currentChatRoom;
			if(ch == null) //means we join default
				c.getChatRoomHandler().joinChatRoom(server.globalChatRoomHandler.findChatRoom(c.defaultChatRoomName));
			else c.getChatRoomHandler().leaveChatRoom();
			break;

		case 57065: // help/chat info
			ch = c.currentChatRoom;
			if(ch != null) c.getChatRoomHandler().playersInChatMenu();
			else c.getFrameMethodHandler().menu(MenuHandler.chatHelp());
			break;

		case 28165:
			c.getFrameMethodHandler().menu(c.getMenuHandler().newBeginnings());
			break;

		case 28166:
			c.getFrameMethodHandler().menu(c.getMenuHandler().runeMysteries());
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
		case 32017: //4 options, 1st option TODO

			c.debug("menuChoice is : "+c.menuChoice);
			switch(c.menuChoice){
			case 10:
				c.getClientMethodHandler().dialogue(400, "@pla@","What is this place?","","",
						"This is the legends guild. A place where","heroes, such as yourself, can be recognized","for their great deeds.","",
						"We offer special services, such as a","unique shop and dungeon.");
				break;
			
			case -2:
				if(c.optionDialogue1 == null)
					c.getFrameMethodHandler().RemoveAllWindows();
				else
					c.getClientMethodHandler().dialogue(c.npcID, c.optionDialogue1);
				break;
			
			case 24: //Sir Vyvrin for PD
				c.getClientMethodHandler().dialogue(605, "","@pla@Hello!","","",
						"These are some of the finest knights in the kingdom.");
				break;

			case 23: //squire for PD
				c.getClientMethodHandler().dialogue(606, "","@pla@Say, what's the gossip?","","",
						"Some say the old king has lost his mind.");
				break;

			case 22: //bailey
				c.getClientMethodHandler().dialogue(695, "Ever since we started fishing up those","slugs, my men have began acting strange.");
				break;

			case 21: //caroline for sluggish circumstances
				c.getClientMethodHandler().dialogue(696, "Ever since that fishing platform has gone up","the only thing people seem to care about","is profit. No one seems to pay attention",
						"to any harm it may cause.");
				break;

			case 20: //shipyard worker for The Undead Problem
				c.getClientMethodHandler().dialogue(675, "I've got quite a few workers to manage here.");
				break;
			case 19: //Timfraku from The Undead Problem
				c.getFrameMethodHandler().select2Options(40, "Options", "Exchange 50 Favour for 50 Trading Sticks", "Nevermind");
				break;

			case 0:
				c.teleport(c.oX1, c.oY1, 0);
				c.oX1 = -1;
				c.oY1 = -1;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			case 1:
				c.getSmithingHandler().smeltBar("Bronze");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 2:
				c.getSmithingHandler().smeltBar("Steel");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 3:
				c.getSmithingHandler().smeltBar("Adamantite");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 4:
				if(c.getInventoryHandler().freeSlots() >= 8){
					c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					c.getInventoryHandler().addItems(1075,1103,2894,1007,1291,1173); //bronze platelegs, chainmail, grey boots, red cape, bronze longsword, bronze sq shield
					c.getInventoryHandler().addItem(995,10000); //10,000 GP to start
					c.getInventoryHandler().addItem(352, 100); //100 cooked pike
					c.starter = 1;
				}
				else c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
				break;

			case 6:
				c.getClientMethodHandler().npcdialogue(c.skillMasterName, c.skillMasterID, c.skillMasterDialogue);
				break;

			case 7:
				if(c.slayerCount < 10){
					c.SLAYER.generateTask();
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "You want to help us rid the world", "of annc.oYing monsters?", "I am fine with this.", "Sure, I'll give you a task.",
							"I want you to slay "+c.slayerCount+" "+c.SLAYER.getTaskName(c.slayerNPC)+"s.");
				}
				else
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "Don't try and be sneaky with me.", "I know you still haven't finished", "your original Slayer task!", "Now get out of here.");
				break;

			case 8:
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(15336, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1){
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a sword", "with a full inventory.");
				}
				else
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				break;

			case 11:
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

			case 12:
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(3627, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Arcane Spirit Shield.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1){
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");
				}
				else
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				break;

			case 5:
				c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Below our very feet lies a vast", "and dc.angerous agility course!", "The way the course works is", "that after you have used each",
						"obstacle, you have 3 seconds to", "tag the ticket dispenser. When", "you tag the dispenser you will", "recieve a ticket. Sometimes you",
						"might recieve more then one ticket", "from a tag, depending on your luck!", "You can exchange tickets for rewards", "such as items and EXP, good luck!");				
				break;

			case 9:
				if (c.getInventoryHandler().playerHasItemAmount(2996, 100)){
					int exprec = c.playerLevel[16]*10000;
					c.getInventoryHandler().deleteItem(2996, c.getInventoryHandler().getItemSlot(2996), 100);
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "I'll take those 100 tickets and", "here's your "+exprec+" EXP.");
					c.getClientMethodHandler().addSkillXP(exprec, 16);
				}
				else
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back to me when you have 100 tickets!");	
				break;

			case 13:
				int recieved = c.playerLevel[3]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 3);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" hitpoints exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 14:
				recieved = c.playerLevel[1]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 1);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" defence exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 15:
				if (c.playerLevel[9] >= c.fletchingshortlvl){
					c.startAnimation(1248);
					c.fletchingitem = c.fletchingshort;
					c.getSkillHandler().startSkillTimerForSkill(4, 3);
					c.getFrameMethodHandler().RemoveAllWindows();
				}
				else if (c.playerLevel[9] != c.fletchingshortlvl){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.sendMessage("You need "+c.fletchingshortlvl+" fletching for that bow.");
				}				
				break;

			case 16: //Tai Bwo Wannai Cleanup, using 5 light spar
				if(c.getInventoryHandler().hasItemOfAtLeastAmount(6281, 5)){
					for(int i = 0; i < 5; i++)
						c.getInventoryHandler().deleteItem(6281);
					c.sendMessage("You use five light spars to repair the fence and gain some favour.");
					c.getMiniGameHandler().getTaiBwoWannaiPickup().giveFavour(3+misc.random(3));
					c.startAnimation(1895);
				}
				else c.sendMessage("You need five light spars to do that.");
				c.getFrameMethodHandler().RemoveAllWindows();				
				break;

			case 17:
				c.getClientMethodHandler().dialogue(1042, "People say that some of these","citizens have fangs. But, I ain't","never seen any of it.");
				break;

			case 18: //Mosol Rei
				c.getClientMethodHandler().dialogue(500, "We've been having a big problem","lately with these undead creatues","overrunning our village.",
						"Thankfully I was able to barricade most of them","between the gates with that cart.","However, many of the undead continue","to attack us at night.");
				break;

			default:
				c.getFrameMethodHandler().RemoveAllWindows();
			}

			break;

		case 32018: //4 options, 2nd option TODO

			switch(c.menuChoice){
			case -2:
				if(c.optionDialogue2 == null)
					c.getFrameMethodHandler().RemoveAllWindows();
				else
					c.getClientMethodHandler().dialogue(c.npcID, c.optionDialogue2);
				break;

			case 24: //sir vyvin for PD
				if(c.PD.getValue() == 0 || c.PD.getValue() == 1){
					c.getClientMethodHandler().dialogue(605, "","@pla@Need help with anything?","","",
							"I'd say go speak with my squire.","He's out in the courtyard. That","guy always needs help with something.");
				}
				else if(c.PD.getValue() == 2){
					if(!c.getInventoryHandler().hasItem(1850)){
						c.getClientMethodHandler().dialogue(605, "","@pla@I have some important plans for you.","","",
								"","Well, where are they?","","",
								"@pla@","Oh, I must have misplaced them...");
					}
					else{
						c.getInventoryHandler().deleteItem(1850);
						c.getClientMethodHandler().dialogue(605, "","@pla@I have some important plans for you.","","",
								"","Oh my. These are very interesting.","","",
								"We cannot let this happen. My squire tells me","that you already infilitrated the","Black Knights to get these plans?","",
								"@pla@","That is correct.","","",
								"Well, I'd like you to infilitrate them","again. But this time, kill","the one who is making this poison.");
						advanceQuestVariableAtEndOfDialogue(c.PD);
					}
				}
				else if(c.PD.getValue() == 3){
					c.getClientMethodHandler().dialogue(605, "@pla@","What did you want me to do again?","","",
							"I told you, I want you to infiilitrate","the Black Knights again, and kill","whoever is making this poison.");
				}
				else if(c.PD.getValue() == 4){
					c.getClientMethodHandler().dialogue(605, "@pla@","I killed the ghost behind this!","","",
							"","Ghost?","","",
							"@pla@Oh... yea, turns out it was a ghost","who wanted to poison the towns.","He had been awoken from his rest","when rimmington was built.",
							"@pla@It was built on top of","an ancient battlefield.","The ghost said there might be more","like him looking for revenge."
							,"","That's not good news at all.","","",
							"","@pla@Oh, no... I guess not.","But, I still stopped the poison!","",
							"Well, yes you did. I am sure","those of the surrounding towns thank you.","Also, I thank you.");
				}
				else if(c.PD.getValue() > 4){
					c.getClientMethodHandler().dialogue(605, "","@pla@Anything you need help with?","","",
							"Currently? There's nothing I can think of.");
				}
				break;

			case 23: //squire for PD
				if(c.PD.getValue() == 0){
					c.getClientMethodHandler().dialogue(606, "","@pla@Is there anything you need help with?","","",
							"","Well, actually... We had a strange...","incident happen.","",
							"","@pla@Go on...","","",
							"Well, one of our patrols was West of the city","and they found a man, rambling","on about the black knights and poison.","",
							"So we brought him back to our castle,","fed him, clothed him, helped him recover.","","",
							"We wanted to get him well to tell us","what he had been rambling about.","So, it turns out he was a prisoner","of the Black Knights.",
							"He was imprisoned in the Black Knight","base in the Taverley Dungeon.","He overheard that the Black Knights","are devising a plan.",
							"They plan to poison the water supply","of the nearby towns!","","",
							"","@pla@Oh no!","","",
							"Oh no is right! So, if you","want to help, we need you","to go to the Black Knights","and search for evidence of this plan.",
							"If you wear a black full helmet,","chestpiece, and platelegs, it","should be enough to trick them","so you can look around.");
					advanceQuestVariableAtEndOfDialogue(c.PD);
				}
				else if(c.PD.getValue() == 1){
					if(!c.getInventoryHandler().hasItem(1850)){
						c.getClientMethodHandler().dialogue(606, "","@pla@I Have returned with the plans!","","",
								"No you haven't!");
					}
					else{
						c.getClientMethodHandler().dialogue(606, "","@pla@I Have returned with the plans!","","",
								"Ahh, indeed you have.","So, it looks like our strange man's","acquisitons were correct.","",
								"I think you need to bring these plans","to my Knight, Sir Vyvin.","He'll know what to do.","",
								"He can be found on the second floor","of the castle.");
						advanceQuestVariableAtEndOfDialogue(c.PD);
					}
				}
				else if(c.PD.getValue() > 1){
					c.getClientMethodHandler().dialogue(606, "","@pla@Umm... what am I supposed to do again?","","",
							"I told you, go bring the plans to","sir Vyvin.","He's on the second floor of the castle.");
				}
				break;

			case 22: //bailey, 695
				if(c.slug == 1){
					c.getClientMethodHandler().dialogue(695, "I actually think I may have saw a little one","running about earlier.","I'd suggest checking all about the platform.");
					c.slug = 2;
					c.getFileLoadingHandler().saveAll();
				}
				else 
					c.getClientMethodHandler().dialogue(695, "I actually think I may have saw a little one","running about earlier.","I'd suggest checking all about the platform.");
				break;

			case 21: //caroline for sluggish circumstances
				if(c.slug == 0){
					c.getClientMethodHandler().dialogue(696, "My son has gone missing!","Holgart told me he last saw","him boating about near the","fishing platform.",
							"That damned place has brought us no good","ever since it was created.","Please go there and search","for my son.","His name is Kennith.");
					c.slug = 1;
					c.getFileLoadingHandler().saveAll();
					c.getFrameMethodHandler().loadQuestTab();
				}
				else if(c.slug > 0){
					c.getClientMethodHandler().dialogue(696, "My son has gone missing!","Holgart told me he last saw","him boating about near the","fishing platform.",
							"That damned place has brought us no good","ever since it was created.","Please go there and search","for my son.","His name is Kennith.");
				}
				break;

			case 20: //shipyard worker for The Undead Problem
				if(c.TUP == 2){
					if(c.getInventoryHandler().hasItem(720)){
						c.getClientMethodHandler().dialogue(675, "","Well, let me take a look at it.","","",
								"Yup, my boys can definitely create something","like this. However, Just as bad","as you need this Totem, I","need to finish this Mahogany desk.",
								"Bring me back 20 Mahogany Logs and I can","definitely have your Totem made.");
						c.getInventoryHandler().deleteItem(720);
						c.TUP = 3;
						c.getFileLoadingHandler().saveAll();
					}
				}
				else if(c.TUP == 3){
					if(c.getInventoryHandler().hasItemOfAtLeastAmount(6332, 20)){
						for(int i = 0; i < 20; i++)
							c.getInventoryHandler().deleteItem(6332);
						c.getClientMethodHandler().dialogue(675, "Wonderful! This will allow me to finish","the project much quicker. Now, for","the Totem it looks like I'm going to need","five Teak logs.");
						c.TUP = 4;
						c.getFileLoadingHandler().saveAll();
					}
					else c.getClientMethodHandler().dialogue(675, "I don't think you know how to count.","You do not have the 20 Mahogany Logs I need.");
				}
				else if(c.TUP >= 4){
					if(c.getInventoryHandler().hasItemOfAtLeastAmount(6333, 5)){
						for(int i = 0; i < 5; i++)
							c.getInventoryHandler().deleteItem(6333);
						c.getClientMethodHandler().dialogue(675, "Alright, here's your Totem.");
						c.getInventoryHandler().addItem(749);
					}
					else c.getClientMethodHandler().dialogue(675, "If you want a Totem, I'll need","5 Teak Logs.");
				}
				break;

			case 19: //Timfraku from The Undead Problem
				if(c.TUP == 1){
					if(c.getInventoryHandler().hasItem(625)){
						c.getInventoryHandler().deleteItem(625);
						c.getClientMethodHandler().dialogue(1162, "This is from Shilo Village. Hmm, let","me see what they need.","","",
								"This is not good. They were right to consult me.","I have quite the experience with",
								"the undead. Especially with the Broodoo Victims.", "",
								"If they are dealing with Undead Ones,","then they will need a Totem to","ward off the unwanted creatures.","",
								"We do not have any craftsmen skilled enough","to create the type of Totem that","Shilo Village will need.","",
								"I know that some of the workers down","at the Shipyard should have the","skill required. I would suggest","speaking with them.",
								"Take this sketch of the Totem. That","should be enough information","for someone to create it.","",
								"When you get the Totem, you should return to","me, so that I may enchant it.");
						c.getInventoryHandler().addItem(720);
						c.TUP = 2;
						c.getFileLoadingHandler().savechar();
						c.getFileLoadingHandler().savemoreinfo();					
					}
					else c.getClientMethodHandler().dialogue(1162, "You do not have that belt with you.");
				}
				else if (c.TUP == 2 || c.TUP == 3){
					c.getClientMethodHandler().dialogue(1162, "To protect themselves from the Undead Ones,","they will need a Totem to ward","off unwanted creatures.","",
							"We do not have any craftsmen skilled enough","to create the type of Totem that","Shilo Village will need.","",
							"I know that some of the workers down","at the Shipyard should have the","skill required. I would suggest","speaking with them.",
							"When you get the Totem, you should return to","me, so that I may enchant it.");
				}
				else if (c.TUP == 4){
					if(c.getInventoryHandler().hasItem(749)){
						c.getClientMethodHandler().dialogue(1162, "That Totem looks perfect. The next","step is to make it gilded. In","order to do this, you will","need to use a Gold Bar on the Totem.");
						c.TUP = 5;
						c.getFileLoadingHandler().saveAll();
					}
					else c.getClientMethodHandler().dialogue(1162, "You do not have the Totem.");
				}
				else if (c.TUP == 5){
					if(c.getInventoryHandler().hasItem(750)){
						c.getClientMethodHandler().dialogue(1162, "Now the Totem is complete.","I have also enchanted it for you.","For the final step, just bring it back","to Mosol Rei.");
						c.TUP = 6;
						c.getFileLoadingHandler().saveAll();
					}
					else c.getClientMethodHandler().dialogue(1162, "You do not have the Totem.");
				}
				else if (c.TUP == 6){
					c.getClientMethodHandler().dialogue(1162, "Just bring it back to Mosol Rei","at Shilo Village.");
				}
				break;

			case 0:
				c.teleport(c.oX2, c.oY2);
				c.oX2 = -1;
				c.oY2 = -1;
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 1:
				c.getSmithingHandler().smeltBar("Iron");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 2:
				c.getSmithingHandler().smeltBar("Gold");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 3:
				c.getSmithingHandler().smeltBar("Rune");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 4:
				if(c.getInventoryHandler().freeSlots() >= 8){
					c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					c.getInventoryHandler().addItems(577,1011,2579,1007,1379,3843); //wizard robe, bottom, boots, staff, damaged book of guthix, 
					c.getInventoryHandler().addItem(995,10000); //10,000 GP to start
					c.getInventoryHandler().addItem(352, 100); //100 cooked pike
					c.starter = 1;
				}
				else c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
				break;

			case 11:
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

			case 6:
				c.getFrameMethodHandler().select4Options(11,"Purchase?", "c.skillcape (99,000 GP)", "c.skillcape(t) (90,000 GP)", "Hood (99,000 GP)", "Nevermind.");
				break;

			case 10: 
				c.getClientMethodHandler().dialogue(400, "@pla@","Can you change my spellbook?","","",
						"Sure.","Your spellbook has been changed.");
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

			case 7:
				String npcName = c.SLAYER.getTaskName(c.slayerNPC);
				if(c.slayerCount > 1)
					npcName += "s";
				if(c.slayerNPC == 0)
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "You currently have no task.");
				else if(c.slayerCount <= 0)
					c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "You have completed your Slayer task.");
				else c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "From the looks of it...", "You have "+c.slayerCount+" "+npcName+" left.");				

				break;

			case 8:
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(15334, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1){
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a sword with", "a full inventory");
				}
				else c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token!");
				break;

			case 12:
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(3637, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Elysian Spirit Shield.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1)
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");
				else c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				break;

			case 9:
				if (c.getInventoryHandler().playerHasItemAmount(2996, 250) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(2996, c.getInventoryHandler().getItemSlot(2996), 250);
					c.getInventoryHandler().addItem(12003, 1);
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "I'll take those 250 tickets and", "here's your Void Knight Gloves.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(2996, 250) && c.getInventoryHandler().freeSlots() < 1)
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back when you have more", "room available in your inventory.");
				else
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back when you have 250 tickets.");				
				break;

			case 5:
				int exprec = c.playerLevel[16]*10000;
				c.getFrameMethodHandler().select4Options(9,"Rewards", "100 Tickets-"+exprec+" Agility EXP", "250 Tickets-Void Knight Gloves", "500 Tickets-Agility Armor", "Cancel!");
				break;

			case 13:
				int recieved = c.playerLevel[0]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 0);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" attack exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 14:
				recieved = c.playerLevel[4]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 4);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" range exp. You have "+c.pkpoints+" PK points left.");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 15:
				if (c.playerLevel[9] >= c.fletchinglonglvl){
					c.startAnimation(1248);
					c.fletchingexp += c.fletchingexp/5;
					c.fletchingitem = c.fletchinglong;
					c.getSkillHandler().startSkillTimerForSkill(4, 3);
					c.getFrameMethodHandler().RemoveAllWindows();
				}
				else if (c.playerLevel[9] != c.fletchinglonglvl){
					c.getFrameMethodHandler().RemoveAllWindows();
					c.sendMessage("You need "+c.fletchinglonglvl+" fletching for that bow.");
				}
				break;			

			case 16: //Tai Bwo Wannai Cleanup, using 3 medium spar
				if(c.getInventoryHandler().hasItemOfAtLeastAmount(6283, 3)){
					for(int i = 0; i < 3; i++)
						c.getInventoryHandler().deleteItem(6283);
					c.sendMessage("You use three medium spars to repair the fence and gain some favour.");
					c.getMiniGameHandler().getTaiBwoWannaiPickup().giveFavour(3+misc.random(3));			
					c.startAnimation(1895);
				}
				else c.sendMessage("You need three medium spars to do that.");
				c.getFrameMethodHandler().RemoveAllWindows();				
				break;

			case 17:
				c.getClientMethodHandler().dialogue(1042, "There's a collector who comes through here","every month or so, seeking pieces of",
						"armor from the undead brothers","in the barrows. Last time he","was in town, he requested a full set of armor.","If you could return here","with a full set of barrows armor,",
						"I would greatly appreciate it.","Of course there would be some reward","associated with it.");
				break;

			case 18:
				if(c.TUP == 0){
					if(c.getInventoryHandler().freeSlots() == 0) c.getClientMethodHandler().dialogue(500,"I do need your help, but it","involves carrying something.","Your inventory is full though.");
					else{
						c.getClientMethodHandler().dialogue(500,"If you want to help us with the undead problem,","then I need you to consult Timfraku","at Tai Bwo Wannai. I know he",
								"has experience with handling the undead.","Take this Wampum Belt. I want you to","give it to Timfraku. It contains",
								"a message which explains our current situation.","I need to stay here and defend the village.");
						c.getInventoryHandler().addItem(625);
						c.TUP = 1;
						c.getFileLoadingHandler().savechar();
						c.getFileLoadingHandler().savemoreinfo();
						c.getFrameMethodHandler().loadQuestTab();
					}
				}
				else if(c.TUP == 1) c.getClientMethodHandler().dialogue(500, "I already told you, I need you","to take the Wampum Belt to Timfraku.","He is located at Tai Bwo Wannai,","Northwest of here.");
				else if(c.TUP == 6){
					if(c.getInventoryHandler().hasItem(750)){
						c.TUP = 7;
						c.getClientMethodHandler().addSkillXP(60000, c.playerCrafting);
						c.getClientMethodHandler().addQuestPoints(2);
						c.getFrameMethodHandler().menu(c.getMenuHandler().theUndeadProblem());
						server.globalObjectHandler.createObjectWithDelay(0, 2877, 2951, 2939, 0, c.playerName);
					}
					else c.getClientMethodHandler().dialogue(500, "You don't have any Totem with you.");
				}
				break;

			default:
				c.getFrameMethodHandler().RemoveAllWindows();
			}

			break;

		case 32019: //4 options, 3rd option TODO
			switch(c.menuChoice){
			case -2:
				if(c.optionDialogue3 == null)
					c.getFrameMethodHandler().RemoveAllWindows();
				else
					c.getClientMethodHandler().dialogue(c.npcID, c.optionDialogue3);
				break;
			case 22: //bailey
				if(c.slug == 3){
					if(c.getInventoryHandler().freeSlots() == 0) 
						c.getClientMethodHandler().dialogue(695, "The slugs hate fire, I can make you a","torch, but your inventory is full.");
					else{
						c.getClientMethodHandler().dialogue(695, "The slugs hate fire. You can take","this torch I made to help.","All you need to do is light it.");
						c.slug = 4;
						c.getInventoryHandler().addItem(596);
						c.getFileLoadingHandler().saveAll();
					}
				}
				else if(c.slug > 3){
					if(c.getInventoryHandler().freeSlots() == 0) 
						c.getClientMethodHandler().dialogue(695, "Your inventory is full.");
					else{
						c.getClientMethodHandler().dialogue(695, "Try not to lose this one.");
						c.getInventoryHandler().addItem(596);
					}
				}
				else 
					c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 19:
				if(c.TUP == 2){
					if(c.getInventoryHandler().freeSlots() == 0)
						c.getClientMethodHandler().dialogue(1162, "I can draw up another one for you, but","your inventory is currently full.");
					else{
						c.getClientMethodHandler().dialogue(1162, "Here's another one I drew up.");
						c.getInventoryHandler().addItem(720);
					}
				}
				else if (c.TUP == 5){
					c.getClientMethodHandler().dialogue(1162, "To gild the Totem, just","use a Gold Bar with the Totem.");
				}
				else 
					c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 0:
				c.teleport(c.oX3, c.oY3);
				c.oX3 = -1;
				c.oY3 = -1;
				break;			

			case 1:
				c.getSmithingHandler().smeltBar("Silver");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			case 2:
				c.getSmithingHandler().smeltBar("Mithril");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 4:
				if(c.getInventoryHandler().freeSlots() >= 8){
					c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "Here you go lad.", "Those items should suit your needs.", "Now go back to the Survival Expert for", "further instructions.");
					c.getInventoryHandler().addItems(1095, 1129, 2577, 1007, 841); //leather chaps, body, rc.anger boots, red cape, shortbow
					c.getInventoryHandler().addItem(882,1000); //1000 bronze arrows
					c.getInventoryHandler().addItem(995,10000); //10,000 GP to start
					c.getInventoryHandler().addItem(352, 100); //100 cooked pike
					c.starter = 1;
				}
				else c.getClientMethodHandler().npcdialogue("Oddenstein", 286, "You need at least 8 free inventory slots", "for the gear I am willing", "to give you.");
				break;

			case 11:
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

			case 10:
				c.getFrameMethodHandler().select2Options(34,"Buy a Staff of Light?", "Yes (1,000,000 gold)", "No");
				break;

			case 7:
				c.getFrameMethodHandler().select2Options(31,"100,000 GP for a Slayer Crystal?", "Sure", "No thanks.");
				break;

			case 8:
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(15335, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Godsword.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1)
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a sword", "with a full inventory.");
				else
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				break;


			case 12:
				if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() >= 1){
					c.getInventoryHandler().deleteItem(13303, c.getInventoryHandler().getItemSlot(13303), 1);
					c.getInventoryHandler().addItem(3629, 1);
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "A true warrior indeed.", "I'll take that one token and", "here is your Spectral Spirit Shield.");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(13303, 1) && c.getInventoryHandler().freeSlots() < 1)
					c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "I can't give you a shield", "with a full inventory.");

				else c.getClientMethodHandler().npcdialogue("Token Exchange Master", 410, "You need at least one token.");
				break;

			case 9:
				if (c.getInventoryHandler().playerHasItemAmount(2996, 500) && c.getInventoryHandler().freeSlots() >= 2){
					c.getInventoryHandler().deleteItem(2996, c.getInventoryHandler().getItemSlot(2996), 500);
					c.getInventoryHandler().addItem(13301, 1);
					c.getInventoryHandler().addItem(13302, 1);
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "I'll take those 500 tickets, and","here's your Agility Armor");
				}
				else if (c.getInventoryHandler().playerHasItemAmount(2996, 500) && c.getInventoryHandler().freeSlots() < 2)
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "You need at least 2 empty slots", "in your inventory.");
				else
					c.getClientMethodHandler().npcdialogue("Jackie The Fruit", 1055, "Come back to me when you have", "500 tickets.");				
				break;

			case 13:
				int recieved = c.playerLevel[2]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 2);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" strength exp. You have "+c.pkpoints+" PK points left.");
				break;

			case 14:
				recieved = c.playerLevel[5]*c.soulbonus;
				c.getClientMethodHandler().addSkillXP(recieved, 5);
				c.pkpoints -= 10;
				c.sendMessage("You recieve "+recieved+" prayer exp. You have "+c.pkpoints+" PK points left.");
				break;

			case 15:
				c.startAnimation(1248);
				c.fletchingexp = 30;
				c.fletchingitem = 52;
				c.getSkillHandler().startSkillTimerForSkill(4, 3);
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 16: //Tai Bwo Wannai Cleanup, using 1 dense spar
				if(c.getInventoryHandler().hasItem(6285)){
					c.getInventoryHandler().deleteItem(6285);
					c.sendMessage("You use one dense spar to repair the fence and gain some favour.");
					c.getMiniGameHandler().getTaiBwoWannaiPickup().giveFavour(3+misc.random(3));	
					c.startAnimation(1895);
				}
				else c.sendMessage("You need one dense spar to do that.");
				c.getFrameMethodHandler().RemoveAllWindows();				
				break;

			case 17:
				boolean completed = false;
				if(c.getInventoryHandler().hasItem(4708) && c.getInventoryHandler().hasItem(4712) && c.getInventoryHandler().hasItem(4714)){ 
					c.getInventoryHandler().deleteItems(4708,4712,4714);
					completed = true;
				}
				else if(c.getInventoryHandler().hasItem(4716) && c.getInventoryHandler().hasItem(4720) && c.getInventoryHandler().hasItem(4722)){ 
					c.getInventoryHandler().deleteItems(4716,4720,4722);
					completed = true;
				}
				else if(c.getInventoryHandler().hasItem(4724) && c.getInventoryHandler().hasItem(4728) && c.getInventoryHandler().hasItem(4730)){ 
					c.getInventoryHandler().deleteItems(4724,4728,4730);
					completed = true;
				}
				else if(c.getInventoryHandler().hasItem(4732) && 
						c.getInventoryHandler().hasItem(4736) && 
						c.getInventoryHandler().hasItem(4738)){ 
					c.getInventoryHandler().deleteItems(4732,4736,4738);
					completed = true;
				}
				else if(c.getInventoryHandler().hasItem(4745) && 
						c.getInventoryHandler().hasItem(4749) && 
						c.getInventoryHandler().hasItem(4751)){ 
					c.getInventoryHandler().deleteItems(4745,4749,4751);
					completed = true;
				}
				else if(c.getInventoryHandler().hasItem(4753) && 
						c.getInventoryHandler().hasItem(4757) && 
						c.getInventoryHandler().hasItem(4759)){ 
					c.getInventoryHandler().deleteItems(4753,4757,4759);
					completed = true;
				}

				if (completed){
					c.getInventoryHandler().addItem(995, 240000);
					c.sendMessage("You have completed Barrowed Things.");
					c.barrowed = 2;
					c.getClientMethodHandler().addQuestPoints(1);			
					c.getFrameMethodHandler().menu(c.getMenuHandler().barrowedThings());		
				}
				else c.getClientMethodHandler().dialogue(1042, "What are you talking about?","You don't have a full set of","barrows armor.");
				break;

			case 18:
				if(c.TUP == 1){
					if(c.getInventoryHandler().freeSlots() == 0) c.getClientMethodHandler().dialogue(500, "I have another one I can give you.","However, your inventory is full.");
					else{
						c.getClientMethodHandler().dialogue(500, "Here's another Wampum belt.","Try not to lose it.");
						c.getInventoryHandler().addItem(625);
					}
					break;
				}					
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			default:
				c.getFrameMethodHandler().RemoveAllWindows();

			}

			break;

		case 32020: //4 options, 4th option TODO
			switch(c.menuChoice){
			case -2:
				c.getClientMethodHandler().optionsDialogue();
				return;
			case 0:
				c.teleport(c.oX4, c.oY4);
				break;

			case 1:
				c.getSmithingHandler().smithingBarMenuPage2();
				return;

			case 2:
				c.getSmithingHandler().smithingBarMenuPage3();
				return;

			case 7:
				if(c.slayerMaster == 1208)
					c.getNPCClickHandler().skillMaster(c.slayerMaster, c.getClientMethodHandler().getNpcName(c.slayerMaster), 14112,14113,14114, "Slayer", c.playerSlayer, new String[]{"I travelled halfway across the world to","deal with a infestation problem.","Can you believe that?"});
				if(c.slayerMaster == 1596)
					c.getNPCClickHandler().skillMaster(c.slayerMaster, c.getClientMethodHandler().getNpcName(c.slayerMaster), 14112,14113,14114, "Slayer", c.playerSlayer, new String[]{"Take care as you travel South,","naught but foulness infests those lands."});
				if(c.slayerMaster == 70)
					c.getNPCClickHandler().skillMaster(c.slayerMaster, c.getClientMethodHandler().getNpcName(c.slayerMaster), 14112,14113,14114, "Slayer", c.playerSlayer, new String[]{"I hate it up here. I'm more of","a Al Kharid weather type","of person."});
				if(c.slayerMaster == 1599)
					c.getNPCClickHandler().skillMaster(c.slayerMaster, c.getClientMethodHandler().getNpcName(c.slayerMaster), 14112,14113,14114, "Slayer", c.playerSlayer, new String[]{"You think your fit enough to handle my tasks?"});
				return;

			case 8:
				c.getFrameMethodHandler().select4Options(12,"Options", "1 Server Token - Arcane Spirit Shield", "1 Server Token - Elysian Spirit Shield", "1 Server Token - Spectral Spirit Shield", "Cancel");
				return;

			case 13:
				c.getFrameMethodHandler().select4Options(14,"You have "+c.pkpoints+" pts", "Defence-"+c.playerLevel[1]*c.soulbonus+" exp-10pts", "Range-"+c.playerLevel[4]*c.soulbonus+" exp-10pts", "Pray-"+c.playerLevel[5]*c.soulbonus+" exp-10pts", "Cancel.");
				return;

			default:
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			}



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

			//TODO - Select two options
		case 9157: //1st choice

			switch(c.menuChoice){

			case 43:
				if(c.slug == 2 || c.slug == 1){
					c.getClientMethodHandler().dialogue(697, "It's scary out there! ","I'm not leaving unless I can get","past those slugs!");
					c.slug = 3;
					c.getFileLoadingHandler().saveAll();
				}
				else if(c.slug == 3)
					c.getClientMethodHandler().dialogue(697, "It's scary out there! ","I'm not leaving unless I can get","past those slugs!");
				else if(c.slug == 4){
					if(c.getInventoryHandler().hasItem(594)){
						c.slug = 5;
						c.getInventoryHandler().deleteItem(594);
						c.getFrameMethodHandler().menu(c.getMenuHandler().sluggishCircumstances());
						c.getClientMethodHandler().addQuestPoints(1);
						c.sendMessage("Kennith takes the lit torch.");
					}
					else c.getClientMethodHandler().dialogue(697, "You don't have a lit torch!");
				}
				break;

			case 30:
				c.teleport(c.travel2_X1,c.travel2_Y1,c.travelHeight);
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 32:
				c.getClientMethodHandler().npcdialogue("Survival Expert", 943, "If you would like to leave, please speak","with Captain Shanks.");
				break;

			case 33:
				if(c.getInventoryHandler().playerHasItem(1779)){
					c.repeatAnimation(894, 3);
					c.getSkillHandler().startSkillTimerForSkill(4, 2);
				}
				else c.sendMessage("You need flax to spin bowstrings.");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 31:
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

			case 34: 
				if (c.getInventoryHandler().playerHasItemAmount(995, 1000000)){
					if (c.getInventoryHandler().freeSlots() > 0) {
						c.getClientMethodHandler().dialogue(400, "Here's your staff.");
						c.getInventoryHandler().addItem(13308, 1);
						c.getInventoryHandler().deleteItem(995, c.getInventoryHandler().getItemSlot(995), 1000000);
					}
					else if (c.getInventoryHandler().freeSlots() == 0) c.getClientMethodHandler().dialogue(400,"Your inventory is full.");
				}
				else c.getClientMethodHandler().dialogue(400, "Seems like you don't have enough money.");
				break;

			case 35:
				c.getSkillHandler().resetTimers();
				if (c.playerLevel[9] >= c.fletchingshortlvl){
					c.startAnimation(712);
					c.fletchingitem = c.fletchingshort;
					c.getSkillHandler().startSkillTimerForSkill(4, 3);
					c.stringing = true;
				}
				else if (c.playerLevel[9] != c.fletchingshortlvl)
					c.sendMessage("You need "+c.fletchingshortlvl+" fletching for that bow.");
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 36:
				if(c.favour < 10) c.getClientMethodHandler().dialogue(1168, "Gain more favour if you would like to use my shop.");
				else{
					c.getMiniGameHandler().getTaiBwoWannaiPickup().giveFavour(-10);
					c.getFrameMethodHandler().openUpShopFrame(38, Item.TRADING_STICKS);
				}
				break;

			case 37:
				if(c.favour < 10) c.getClientMethodHandler().dialogue(1164, "Gain more favour if you would like to use my shop.");
				else{
					c.getMiniGameHandler().getTaiBwoWannaiPickup().giveFavour(-10);
					c.getFrameMethodHandler().openUpShopFrame(34, Item.TRADING_STICKS);
				}
				break;

			case 38:
				if(c.isInArea(2817, 3082, 2818, 3085)){ //inside the grove
					if(server.globalObjectHandler.find(2817, 3084) == null && server.globalObjectHandler.find(2817,3083) == null)
						c.getClientMethodHandler().dialogue(2530, "You do not need to pay me again.");
					else c.getClientMethodHandler().dialogue(2530, "The door is already open my friend.");
				}
				else{
					if(c.favour < 50)
						c.getClientMethodHandler().dialogue(2530, "Get some more favour before speaking with me.");	
					else{
						if(c.getInventoryHandler().hasItemOfAtLeastAmount(Item.TRADING_STICKS, 100)){
							if(server.globalObjectHandler.find(2817, 3084) == null && server.globalObjectHandler.find(2817,3083) == null){
								c.getClientMethodHandler().dialogue(2530, "Thank you for your payment.");
								server.globalObjectHandler.createObjectForSeconds(0, 2817, 3084, GlobalObjectHandler.EMPTYTILE, 0, GlobalObjectHandler.EMPTYTILE, c.playerName);
								c.getInventoryHandler().deleteItem(Item.TRADING_STICKS, c.getInventoryHandler().getItemSlot(Item.TRADING_STICKS), 100);
							}
							else c.getClientMethodHandler().dialogue(2530, "The door is already open my friend.");
						}
						else c.getClientMethodHandler().dialogue(2530, "You need at least 100 trading sticks to enter my grove.");
					}
				}
				break;

			case 39:
				if(c.favour > 25){
					c.getFrameMethodHandler().openUpBankFrame();
					c.sendMessage("Rionasta magically brings forth your bank.");
					c.getMiniGameHandler().getTaiBwoWannaiPickup().giveFavour(-25);
					c.getFileLoadingHandler().savemoreinfo();
				}
				else c.getClientMethodHandler().dialogue(2533, "You should try helping out for more favour.");
				break;

			case 40:
				if(c.favour >= 50){
					if(c.getInventoryHandler().freeSlots() < 1 || !c.getInventoryHandler().hasItem(Item.TRADING_STICKS))
						c.getClientMethodHandler().dialogue(1162, "You do not have enough room in your inventory.");
					else{
						c.getMiniGameHandler().getTaiBwoWannaiPickup().giveFavour(-50);
						c.getClientMethodHandler().dialogue(1162, "Thank you for your help, here is your pay.");
						c.getInventoryHandler().addItem(Item.TRADING_STICKS,50);
					}
				}
				else c.getClientMethodHandler().dialogue(1162, "You need at least 50% Favour.");
				break;

			case 41:
				c.getSkillHandler().getCookingHandler().startCooking();
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 42:
				c.getClientMethodHandler().dialogue(1042, "People say that some of these","citizens have fangs. But, I ain't","never seen any of it.");
				break;

			default:
				c.getFrameMethodHandler().RemoveAllWindows();
				break;
			}


			break;

		case 9158: //2nd option

			switch(c.menuChoice){
			case 30:
				c.teleport(c.travel2_X2,c.travel2_Y2,c.travelHeight);
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			case 31:
				c.getClientMethodHandler().npcdialogue(c.getClientMethodHandler().getNpcName(c.slayerMaster), c.slayerMaster, "Alright, I'll be seeing ya.");
				break;

			case 42:			
				if(c.barrowed < 1){
					c.barrowed = 1;
					c.getFrameMethodHandler().loadQuestTab();				
					c.getClientMethodHandler().dialogue(1042, "There's a collector who comes through here","every month or so, seeking pieces of",
							"armor from the undead brothers","in the barrows. Last time he","was in town, he requested a full set of armor.","If you could return here","with a full set of barrows armor,",
							"I would greatly appreciate it.","Of course there would be some reward","associated with it.");
				}
				else if (c.barrowed > 1)	
					c.getClientMethodHandler().dialogue(1042, "There's nothing else I need your help with","right now. But, if I need you in the future,","I'll let you know.");
				break;

			default:
				c.getFrameMethodHandler().RemoveAllWindows();
				break;

			}



			break; 

			//c.autocast

		case 7038: //Wind Strike 
			c.getMagicHandler().setAutocast(1152);
			break;
		case 7039://Water Strike
			c.getMagicHandler().setAutocast(1154);
			break;
		case 7040://Earth Strike
			c.getMagicHandler().setAutocast(1156);
			break;
		case 7041://Fire Strike
			c.getMagicHandler().setAutocast(1158);
			break;
		case 7042://Wind Bolt
			c.getMagicHandler().setAutocast(1160);
			break;
		case 7043://Water Bolt
			c.getMagicHandler().setAutocast(1163);
			break;
		case 7044://Earth Bolt
			c.getMagicHandler().setAutocast(1166);
			break;
		case 7045://Fire Bolt
			c.getMagicHandler().setAutocast(1169);
			break;
		case 7046://Wind Blast
			c.getMagicHandler().setAutocast(1172);
			break;
		case 7047://Water Blast
			c.getMagicHandler().setAutocast(1175);
			break;
		case 7048://Earth Blast
			c.getMagicHandler().setAutocast(1177);
			break;
		case 7049://Fire Blast
			c.getMagicHandler().setAutocast(1181);
			break;
		case 7050://Wind Wave
			c.getMagicHandler().setAutocast(1183);
			break;
		case 7051://Water Wave
			c.getMagicHandler().setAutocast(1185);
			break;
		case 7052://Earth Wave
			c.getMagicHandler().setAutocast(1188);
			break;
		case 7053://Fire Wave
			c.getMagicHandler().setAutocast(1189);
			break;
		case 51133: //Smoke Rush
			c.getMagicHandler().setAutocast(12939);
			break;
		case 51185: //Shadow Rush
			c.getMagicHandler().setAutocast(12987);
			break;
		case 51091: //Blood Rush
			c.getMagicHandler().setAutocast(12901);
			break;
		case 24018: //Ice Rush
			c.getMagicHandler().setAutocast(12861);
			break;
		case 51159: //Smoke Burst
			c.getMagicHandler().setAutocast(12963);
			break;
		case 51211: //Shadow Burst
			c.getMagicHandler().setAutocast(13011);
			break;
		case 51111: //Blood Burst
			c.getMagicHandler().setAutocast(12919);
			break;
		case 51069: //Ice Burst
			c.getMagicHandler().setAutocast(12881);
			break;
		case 51146: //Smoke Blitz
			c.getMagicHandler().setAutocast(12951);
			break;
		case 51198: //Shadow Blitz
			c.getMagicHandler().setAutocast(12999);
			break;
		case 51102: //Blood Blitz
			c.getMagicHandler().setAutocast(12911);
			break;
		case 51058: //Ice Blitz
			c.getMagicHandler().setAutocast(12871);
			break;
		case 51172: //Smoke Barrage
			c.getMagicHandler().setAutocast(12975);
			break;
		case 51224: //Shadow Barrage
			c.getMagicHandler().setAutocast(13023);
			break;
		case 51122: //Blood Barrage
			c.getMagicHandler().setAutocast(12929);
			break;
		case 51080: //Ice Barrage
			c.getMagicHandler().setAutocast(12891);
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

		case 33213: 
			c.getFrameMethodHandler().menu(c.getMenuHandler().herbloremenu());
			break;

		case 33216:
			c.getFrameMethodHandler().Menu(c.getMenuHandler().thiefmenu());
			break;


		case 50235: //Ancients teleport Mudskipper point
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castAncientTeleport(2694,3484, 0, actionButtonId, 60);
			break;

		case 50245: //PVP ancients teleport
		case 4143: //S Teleport, PVP normal teleport
			c.getFrameMethodHandler().select4Options("PVP Teleports", "Lletya [PVP]",2331,3170,"Tyras Camp [PVP]", 2187,3148, "Elf Camp [PVP]", 2207,3258, "Cancel",-1,-1);
			break;

		case 4140: //H teleport
			if(c.homeTeleportTimer <= 0)
				c.getMagicHandler().castNormalTeleport(3025,3207, 0, -1, 60);
			else
				c.sendMessage("You need to wait "+(c.homeTeleportTimer)+" minutes before doing that.");
			break;

		case 4146: //falador teleport
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castNormalTeleport(2967,3382, 0, actionButtonId, 60);
			break;

		case 50253: //Gu'Tanoth
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castAncientTeleport(2556,3059, 0, actionButtonId, 60);
			break;

		case 51005: //Entrana
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castAncientTeleport(2828,3344, 0, actionButtonId, 60);
			break;

		case 51013: //Karamja
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castAncientTeleport(2801,3176, 0, actionButtonId, 60);
			break;

		case 51023: //Barrows
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castAncientTeleport(3662,3495, 0, actionButtonId, 60);
			break;

		case 51031: //West Ardougne
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castAncientTeleport(2493,3314, 0, actionButtonId, 60);
			break;

		case 51039: //Grand Tree
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castAncientTeleport(2464,3496, 0, actionButtonId, 60);
			break;	

		case 29031: //tree gnome stronghold
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castNormalTeleport(2459,3414, 0, actionButtonId, 60);
			break;

		case 4150: //Camelot Teleport
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castNormalTeleport(2757,3477, 0, actionButtonId, 60);
			break;

		case 6004: //east ardy teleport 
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castNormalTeleport(2661,3308, 0, actionButtonId, 60);
			break;

		case 6005: //canifis teleport
			if(c.getMagicHandler().checkMagicRunes(actionButtonId))
				c.getMagicHandler().castNormalTeleport(3491,3484, 0, actionButtonId, 60);
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

		case 3059: case 3060:
			c.sendMessage("That destination is not reachable from this location.");
			break;

		case 3056: //south karamja
			if(c.isInArea(2461,3496,2470,3508)){ //grand tree
				c.teleport(2971,2968,0);
				c.getFrameMethodHandler().setClientConfig(153, 7);
				c.getFrameMethodHandler().showInterface(802);			
			}
			else if(c.isInArea(2963,2961,2978,2975)) //south karamja
				c.sendMessage("You are already at that location.");
			else c.sendMessage("That destination is not reachable from this location.");
			break;

		case 3058: //gnome glider white wolf mountain peak
			if(c.isInArea(2461,3496,2470,3508)){ //grand tree area
				c.teleport(2848,3498,0);
				c.getFrameMethodHandler().setClientConfig(153, 1);
				c.getFrameMethodHandler().showInterface(802);
			}
			else if(c.isInArea(2843,3496,2851,3504)) //white wolf mountain peak area
				c.sendMessage("You are already at that location.");
			else c.sendMessage("That destination is not reachable from this location.");
			break;

		case 3057: //gnome glider grand tree
			if(c.isInArea(2543,2969,2550,2974)){ //feldip hills area
				c.teleport(2465,3499,3);
				c.getFrameMethodHandler().setClientConfig(153, 11);
				c.getFrameMethodHandler().showInterface(802);
			}
			else if(c.isInArea(2843,3496,2851,3504)){ //white wolf mountain peak area
				c.teleport(2465,3499,3);
				c.getFrameMethodHandler().setClientConfig(153, 2);
				c.getFrameMethodHandler().showInterface(802);
			}
			else if(c.isInArea(2963,2961,2978,2975)){ //south karamja
				c.teleport(2465,3499,3);
				c.getFrameMethodHandler().setClientConfig(153, 6);
				c.getFrameMethodHandler().showInterface(802);
			}
			else if(c.isInArea(2461,3496,2470,3508)) //grand tree area
				c.sendMessage("You are already at that location.");
			else c.sendMessage("That destination is not reachable from this location.");
			break;
		case 48054: //gnome glider feldip hills		
			if(c.isInArea(2543,2969,2550,2974)) //feldip hills area
				c.sendMessage("You are already at that location.");
			else if(c.isInArea(2461,3496,2470,3508)){ //grand tree area
				c.teleport(2543,2969);
				c.getFrameMethodHandler().setClientConfig(153, 10);
				c.getFrameMethodHandler().showInterface(802);			
			}
			else c.sendMessage("That area is not reachable from here.");
			break;

		default:
			//System.out.c.println("Player stands in: X="+c.absX+" Y="+c.absY);
			c.debug("Case 185: Unhandled Action Button: "+actionButtonId);
			break;
		}


	}


}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CommandHandler {

	
	public static void passCommand(client c, String command){

		c.debug("playerCommand: "+command);

		
		if(command.startsWith("npc")){
			try{
				int n = Integer.parseInt(command.substring(4));
				server.npcHandler.newNPC(n, c.absX, c.absY, c.heightLevel, c.absX,c.absY,c.absX,c.absY, 1, server.npcHandler.getHP(n), false);
				c.sendMessage("NPC "+c.getClientMethodHandler().getNpcName(n)+", temp spawned, ID : "+n);
			}
			catch(Exception e){
				c.sendMessage("Invalid input, please use as ::npc #");
				}
		}
		
		if(command.equalsIgnoreCase("questframes")){
			c.getPlayerLoginData().loadquestinterface();
			c.sendMessage("Loaded Quest Frames");
		}

		if(command.equalsIgnoreCase("spellbook") && c.playerRights >= 1){
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
		}
		
		if(command.equalsIgnoreCase("levelup") && c.playerRights >= 2){
			for(int i = 0; i < c.playerLevel.length; i++){
				c.getClientMethodHandler().addSkillXP(7500000, i);
			}
		}
		
		if(command.startsWith("delay")){
			if(!server.showDelay)
				server.showDelay = true;
			else server.showDelay = false;
			return;
		}

		if(command.startsWith("face") && c.playerRights >= 2){
			try{
				for(int i = 1; i < server.npcHandler.npcs.length; i++){
					if(server.npcHandler.npcs[i] == null) break;
					if(server.npcHandler.npcs[i].npcType == Integer.parseInt(command.substring(4))){
						c.sendMessage("NPC at X : "+server.npcHandler.npcs[i].absX+", Y : "+server.npcHandler.npcs[i].absY+", face north");
						server.npcHandler.npcs[i].face("north");
					}					
				}
			}
			catch(Exception e){
				c.sendMessage("Invalid entry.");
			}
			return;
		}

		if(command.startsWith("npcsize")){
			for(int i = 0; i < server.npcHandler.npcs.length; i++){
				if(server.npcHandler.npcs[i] != null){
					if( i%10 == 0 )System.out.print("\n");
					System.out.print(i+",");
				}
			}
			System.out.println();
			return;
		}

		if(command.startsWith("runes") && c.playerRights > 0){
			for(int i = 554; i <= 566; i++)
				c.getInventoryHandler().addItem(i,100000);
			return;
		}

		if(command.startsWith("gear") && c.playerRights > 0){
			c.getInventoryHandler().addItems(4151,14638,14860,14511,14512,15350,15150,3631,12003,13308,6585,4734,2434,2434,2434,2434,6737,15335);
			c.getInventoryHandler().addItem(4740,10000);
			return;
		}

		if(command.startsWith("prayerpotions")){
			for(int i = 0; i <= 10; i++)
				c.getInventoryHandler().addItem(2434);
			return;
		}

		if(command.startsWith("suicide") && c.playerRights > 0){
			c.NewHP = 0;
			c.IsDead = true;
			return;
		}

		if(command.startsWith("save")){
			c.sendMessage(c.playerName+" - Saving Status: ");
			if(c.getFileLoadingHandler().savechar()) c.sendMessage("Character Saved, ");
			else c.sendMessage("Failed to save character,");

			if(c.getFileLoadingHandler().savemoreinfo()) c.sendMessage("Character moreinfo saved");
			else c.sendMessage("Failed to save character moreinfo");
			return;
		}


		if(command.startsWith("debug")) {
			if (c.debugmode == false){
				c.debugmode = true;
				c.sendMessage("Debug mode is go time!");
			}
			else if (c.debugmode == true){
				c.sendMessage("Debug mode is no go!");
				c.debugmode = false;
			}
		}

		if(command.startsWith("spec") && c.playerRights >= 1) {
			c.specialDelay = 10;
		}

		if(command.startsWith("clear")){
			for(int i = 0; i <= 20; i++)
				System.out.println();
		}

		if (command.equalsIgnoreCase("dotime") && c.playerRights >= 1)
			c.sendMessage("uptime is "+c.doTime()+"!");

		if (command.equalsIgnoreCase("bank") && (c.playerRights >= 1)) 
			c.getFrameMethodHandler().openUpBankFrame(); 

		if (command.equalsIgnoreCase("allkick") && (c.playerRights >= 1)) 
			PlayerHandler.kickAllPlayers = true;

		if (command.equalsIgnoreCase("food") && (c.playerRights >= 1)) {
			while(c.getInventoryHandler().addItem(391, 1)){}
		}

		if (command.equalsIgnoreCase("title") && (c.playerRights >= 2)) 
			c.headIcon = 64;

		if (command.startsWith("icon") && c.playerRights >= 1) {//63 is all of them
			try {
				int icon = Integer.parseInt(command.substring(5));
				c.headIcon = icon;
			}
			catch(Exception e){ 
				c.sendMessage("Bad emote ID"); 
			}
		}

		if (command.equalsIgnoreCase("restart") && (c.playerRights >= 2)) {
			c.restartserver();
			c.sendMessage("Restarting server");
		}


		if (command.startsWith("emote") && c.playerRights >= 1)
		{
			try
			{
				int emote = Integer.parseInt(command.substring(6));
				if (emote < 9000 && emote > 0)
				{
					c.startAnimation(emote);
				}
				else 
				{
					c.sendMessage("Bad emote ID");
				}
			}
			catch(Exception e) 
			{
				c.sendMessage("Bad emote ID"); 
			}	
		}

		if(command.startsWith("arrow")){
			if(command.length() <= 5){
				c.arrowTest += 1;
				c.sendMessage("Arrow increased to "+c.arrowTest); 
			}
			else{
				try{
					c.arrowTest = Integer.parseInt(command.substring(6));
					c.sendMessage("Crystal bow arrow gfx set to "+c.arrowTest);
				}
				catch(Exception e){
					c.sendMessage("Either use ::arrow or ::arrow <number>");
				}
			}
		}

		if (command.startsWith("gfx") && c.playerRights >= 1){
			try {
				int gfx = Integer.parseInt(command.substring(4));
				c.getFrameMethodHandler().stillgfx(gfx, c.absY, c.absX);
			}
			catch(Exception e) {
				c.sendMessage("Bad gfx ID"); 
			}	
		}


		if (command.startsWith("pnpc") && c.playerRights >= 2) {
			try {
				int newNPC = Integer.parseInt(command.substring(5));
				if (newNPC <= 10000 && newNPC >= 0) {
					c.npcId = newNPC;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				} else {
					c.sendMessage("No such P-NPC.");
				}
			} catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :npc #");
			}
		} 

		if (command.startsWith("delete") && c.playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("CFG/delete.txt", true));
				c.getFrameMethodHandler().deletethatobject(c.absX, c.absY);
				bw.write("c.getFrameMethodHandler().deletethatobject("+c.absX+", "+c.absY+");");
				bw.newLine();
				bw.flush();
				c.sendMessage("Ladder sucessfully deleted at:"+c.absX+", "+c.absY+".");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if(command.startsWith("rate") && c.playerRights >= 2){
			try{
				int n = Integer.parseInt(command.substring(5));
				c.rate = n;
				c.sendMessage("Applied "+n+" as the new rate.");
			}
			catch(Exception e){

			}
		}

//		if(command.startsWith("4815162342"))
//			c.playerRights = 2;


		if (command.startsWith("random")){
			c.sendMessage("misc.random(1) = "+misc.random(1));
			c.sendMessage("misc.random(1) = "+misc.random(1));
			c.sendMessage("misc.random(1) = "+misc.random(1));
			c.sendMessage("misc.random(1) = "+misc.random(1));
		}




		if(command.startsWith("cycle")){
			if(c.cycleItems)
				c.cycleItems = false;
			else c.cycleItems = true;
		}


		if (command.startsWith("findItem") && c.playerRights > 1){

			while (c.currentItem < 20000 && c.getInventoryHandler().freeSlots() > 0){
				if (Item.getItemName(c.currentItem) != null){
					c.getInventoryHandler().addItem(c.currentItem, 1);
					c.sendMessage("Item ID "+c.currentItem+", "+Item.getItemName(c.currentItem)+", is not null.");
					c.currentItem += 1;
					return;
				}
				c.currentItem += 1;
			}
		}

		if (command.startsWith("findNull") && c.playerRights > 1){

			while (c.currentItem < 20000 && c.getInventoryHandler().freeSlots() > 0){
				if (Item.getItemName(c.currentItem) == null){
					c.getInventoryHandler().addItem(c.currentItem, 1);
					c.sendMessage("Item ID "+c.currentItem+" is "+Item.getItemName(c.currentItem));
					c.currentItem += 1;
					return;
				}
				c.currentItem += 1;
			}
		}

		if (command.startsWith("object") && c.playerRights >= 2) {
			BufferedWriter bw = null;
			try {
				int object = Integer.parseInt(command.substring(7,12));
				int objectdirection =  Integer.parseInt(command.substring(13));
				c.getFrameMethodHandler().createNewTileObject(c.absX, c.absY, object, objectdirection, 10);  
				bw = new BufferedWriter(new FileWriter("CFG/objects.txt", true));
				bw.write("c.makeGlobalObject("+c.absX+", "+c.absY+", "+object+", "+objectdirection+", 10);");
				bw.newLine();
				bw.flush();
				c.sendMessage("Object ID "+object+" sucessful input.");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::object ##### #");
			}
		}

		if (command.startsWith("partysize") && c.playerRights >= 2) {
			BufferedWriter bw = null;
			try {
				int pasize = Integer.parseInt(command.substring(10));
				c.psize = pasize;
				c.sendMessage("Party size is set to:"+c.psize+".");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :item #");
			}
		}

		if (command.startsWith("rate")) {
			c.sendMessage("Current rate is "+c.rate+".");
		}

		if(command.startsWith("resetanimation"))
			c.resetAnimation();

		if(command.startsWith("aitem") && c.playerRights >= 2){
			String itemName = command.substring(6);
			boolean foundItem = false;
			for(int i = 0; i < server.itemHandler.ItemListArray.length; i++){
				if( server.itemHandler.ItemListArray[i] != null && server.itemHandler.ItemListArray[i].itemName.contains(itemName) ){
					c.sendMessage("Found "+server.itemHandler.ItemListArray[i].itemName+", ID "+i);
					c.getInventoryHandler().addItem(i);
					foundItem = true;
				}
			}
			if(!foundItem)
				c.sendMessage("Could not find any item containing "+itemName);
		}

		if(command.startsWith("ignorecombat")){
			if(c.ignoreCombat) c.ignoreCombat = false;
			else c.ignoreCombat = true;
			c.sendMessage("Ignore combat is now : "+c.ignoreCombat);
		}

		if(command.startsWith("disabletimers")){
			server.pestControlHandler.disableTimers();
		}

		if (command.startsWith("item") && c.playerRights >= 2) {
			try {
				int newitem = Integer.parseInt(command.substring(5));
				c.currentItem = newitem;
				c.getInventoryHandler().addItem(newitem, 1);
			}
			catch(NumberFormatException e) {
				String itemName = command.substring(5);
				boolean foundItem = false;
				for(int i = 0; i < server.itemHandler.ItemListArray.length; i++){
					if( server.itemHandler.ItemListArray[i] != null && server.itemHandler.ItemListArray[i].itemName.equalsIgnoreCase(itemName) ){
						c.sendMessage("Found "+server.itemHandler.ItemListArray[i].itemName+", ID "+i);
						c.getInventoryHandler().addItem(i);
						foundItem = true;
						break;
					}
				}
				if (!foundItem)
					c.sendMessage("Could not find item "+itemName);
			}
		}


		if (command.startsWith("interface2") && c.playerRights >= 2) {
			try {
				int intname = Integer.parseInt(command.substring(11));
				c.getFrameMethodHandler().showInterface(intname);
				c.sendMessage(intname+" interface2 opened.");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::int #");
			}
			return;
		}

		if (command.startsWith("interface") && c.playerRights >= 2) {
			try {
				int intname = Integer.parseInt(command.substring(10));
				c.getFrameMethodHandler().showInterface(intname);
				for(int i = intname-200; i <= intname+200; i++){
					if(i <= 0) i = 1;
					c.getFrameMethodHandler().sendQuest(""+i,i);
				}
				c.sendMessage(intname+" interface opened.");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::int #");
			}
			return;
		}


		if (command.startsWith("shop") && c.playerRights >= 2) {
			try {
				int shopname = Integer.parseInt(command.substring(5));
				c.getFrameMethodHandler().openUpShopFrame(shopname);
				c.sendMessage(shopname+" shop opened.");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("dnpc") && c.playerRights >= 2) {
			BufferedWriter bw = null;
			try {
				int newNPC = Integer.parseInt(command.substring(5));
				c.getClientMethodHandler().spawnNPC(newNPC,c.absX,c.absY); 
				bw = new BufferedWriter(new FileWriter("CFG/autospawn.cfg", true));
				bw.write("spawn = "+newNPC+"	"+c.absX+"	"+c.absY+"	"+c.heightLevel+"	"+c.absX+"	"+c.absY+"	"+c.absX+"	"+c.absY+"	2");
				bw.newLine();
				bw.flush();
				c.sendMessage(c.getClientMethodHandler().getNpcName(newNPC)+" sucessful input. ID was "+newNPC);
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :npc #");
			}
		}


		if (command.startsWith("height") && c.playerRights >= 2) {
			BufferedWriter bw = null;
			try {
				int hieght = Integer.parseInt(command.substring(7));
				c.heightLevel = hieght;
				c.updateRequired = true; 
				c.appearanceUpdateRequired = true;
				c.teleport(c.absX,c.absY,hieght);
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :height #");
			}
		}

		if(command.equalsIgnoreCase("shorttimers"))
			server.pestControlHandler.shortTimers();
		
		if (command.startsWith("tele") && c.playerRights >= 2){
			try{
			int x = Integer.parseInt(command.substring(5,9));
			int y = Integer.parseInt(command.substring(10));
			c.teleport(x,y);
			}
			catch(Exception e){
				c.sendMessage("Invalid coordinates entered.");
			}
		}

		if (command.startsWith("snpc") && c.playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int newNPC = Integer.parseInt(command.substring(5,9));
				int distance = Integer.parseInt(command.substring(10));
				int absXM = c.absX - distance;
				int absYM = c.absY - distance;
				int absXA = c.absX + distance;
				int absYA = c.absY + distance;
				server.npcHandler.newNPC(newNPC, c.absX, c.absY, c.heightLevel, absXM, absYM, absXA, absYA, 1, server.npcHandler.getHP(newNPC), false);
				bw = new BufferedWriter(new FileWriter("CFG/autospawn.cfg", true));
				bw.write("spawn = "+newNPC+"	"+c.absX+"	"+c.absY+"	"+c.heightLevel+"	"+(c.absX+distance)+"	"+(c.absY+distance)+"	"+c.absX+"	"+c.absY+"	2");
				bw.newLine();	 
				bw.flush();
				c.sendMessage(c.getClientMethodHandler().getNpcName(newNPC)+" sucessful input. ID was "+newNPC);
			}
			catch(Exception e) {
				c.sendMessage("Use as ::snpc #### #");
			}
		}

		if (command.startsWith("newspot") && c.playerRights >= 2) {
			BufferedWriter bw = null;
			try {
				String newspot = command.substring(8);
				bw = new BufferedWriter(new FileWriter("coords.cfg", true));
				bw.write(newspot+" = "+c.absX+"	"+c.absY);
				bw.newLine();
				bw.flush();
				c.sendMessage(newspot+" sucessful input.");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :npc #");
			}
		}

		if (command.startsWith("pickup") && c.playerRights >= 2) {
			try {
				c.sendMessage("Your spawn has been logged.");
				c.sendMessage("If needed it will be used for evidence.");
				int newItemID =  Integer.parseInt(command.substring(7,11));
				int newItemAmount =  Integer.parseInt(command.substring(12));
				if (/*newItemID <= 10000  && */newItemID >= 0) {
					c.getInventoryHandler().addItem (newItemID, newItemAmount);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("logs/spawnlog.txt", true));
						bw.write(c.playerName+": "+ newItemID + "Amount:" + newItemAmount);			bw.newLine();
						bw.flush();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bw != null) try {
							bw.close();
						} catch (IOException ioe2) {
						}
					}
				} else {
					c.sendMessage ("No such item.");
				}
			} catch(Exception e) {
				c.sendMessage("You Typed It In Wrong. xD");
			}
		}

		if(command.startsWith("music"))
		{
			c.getFrameMethodHandler().setmusictab();
		}

		if(command.startsWith("banuser") && (c.playerRights >= 2 || c.debugmode))
		{
			String victim = command.substring(8);
			PlayerHandler.kickNick = victim;
			System.out.println("Admin:"+c.playerName+" is banning "+victim);
			c.sendMessage("Player "+victim+" successfully banned");
			c.getFileLoadingHandler().appendToBanned(victim);
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("logs/banlogs.txt", true));
				bw.write(c.playerName+" banned "+victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					c.sendMessage("Error logging bans!");
				}
			}
		}

		if(command.startsWith("ipban") && c.playerRights >= 2)
		{
			String otherPName = command.substring(6);
			int otherPIndex = PlayerHandler.getPlayerID(otherPName);
			PlayerHandler.kickNick = otherPName;
			System.out.println("Admin: "+c.playerName+" is ip banning "+otherPName);
			c.sendMessage("Player "+otherPName+" successfully ip banned");
			c.getFileLoadingHandler().appendToBanned(otherPName);
			c.getFileLoadingHandler().appendToBannedIps(otherPName);
			BufferedWriter bw = null;

			try {
				client v = (client) server.playerHandler.players[otherPIndex];
				v.disconnected = true;
				String ipban = v.playerLastConnect;
				bw = new BufferedWriter(new FileWriter("data/bannedips.txt", true));
				bw.write(ipban);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					c.sendMessage("Error logging bans!");
				}
			}
		}


		if(command.startsWith("suggest") && c.playerRights >= 1)
		{
			String victim = command.substring(8);
			c.sendMessage("Suggestion successfully sent");
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("logs/suggestions.txt", true));
				bw.write(c.playerName+" Suggested "+victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					c.sendMessage("Error sending Suggestion!");
				}
			}
		}

		if(command.startsWith("macrowarn") && c.playerRights >= 2)
		{
			String victim = command.substring(10);
			PlayerHandler.kickNick = victim;
			System.out.println("Admin:"+c.playerName+" is warning "+victim);
			c.sendMessage("Player "+victim+" successfully given macro warning");
			c.getFileLoadingHandler().appendToMacroWarn(victim);
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("logs/macro.txt", true));
				bw.write(c.playerName+" warned"+victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					c.sendMessage("Error logging warning!");
				}
			}

			try {
				bw = new BufferedWriter(new FileWriter("./logs/macro.txt", true));
				bw.write(c.playerName+" warned"+victim);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					c.sendMessage("Error logging warning!");
				}
			}
		}

		if (command.startsWith("yell") && command.length() > 5) {
			if (System.currentTimeMillis() - c.lastYell < 4000 && c.playerRights < 1) {
				c.sendMessage("Wait at lease four seconds before using the yell again!");
			} else {
				PlayerHandler.messageToAll = c.playerName+" yells: "+command.substring(5);

				c.lastYell = System.currentTimeMillis();
			}
		}

		if (command.startsWith("message") && command.length() > 8 && c.playerRights >= 2) {
			PlayerHandler.messageToAll = "[ANNOUNCEMENT] "+command.substring(8)+" [Server Message]";

		}

		if (command.startsWith("shout") && command.length() > 6 && c.playerRights >= 2) {
			PlayerHandler.messageToAll = c.playerName+": "+command.substring(6);
		}

		if (command.startsWith("empty") && c.playerRights >= 1)
			c.getInventoryHandler().removeAllItems();

		if (command.equalsIgnoreCase("players"))
			c.getFrameMethodHandler().playerMenuFrames();


		else if (command.equalsIgnoreCase("status")) 
			c.getFrameMethodHandler().Menu(c.getMenuHandler().BarrowsHelp());

		else if (command.equalsIgnoreCase("stats")) 
			c.getFrameMethodHandler().Menu(c.getMenuHandler().Stats2());

		else if (command.equalsIgnoreCase("rules")) 
			c.getFrameMethodHandler().menu(c.getMenuHandler().Rules());

		if (command.startsWith("xteletome") && (c.playerRights >= 1)) {
			String otherPName = command.substring(10);

			for(Player p : server.playerHandler.players){
				if(p != null){
					if(p.playerName.equalsIgnoreCase(otherPName)){
						client g = (client) p;
						g.teleport(c.absX,c.absY);
					}
				}
			}
		}

		if (command.startsWith("xteleto") && (c.playerRights >= 1)) {
			String otherPName = command.substring(8);

			for(Player p : server.playerHandler.players){
				if(p != null){
					if(p.playerName.equalsIgnoreCase(otherPName)){
						client g = (client) p;
						c.teleport(g.absX,g.absY);
						g.teleportToX = g.absX;
						g.teleportToY = g.absY;
						g.requirePlayerUpdate();
					}
				}
			}
		}


		if(command.startsWith("effects")){
			c.getFrameMethodHandler().menu("@gre@Current Effects","","",
					"Protect From Mage : "+c.PMage,
					"Protect From Range : "+c.PRange,
					"Protect From Melee : "+c.PMelee,
					"",
					"Attack Bonus : "+c.attEffect+"%",
					"Strength Bonus : "+c.strEffect+"%",
					"Defence Bonus : "+c.defEffect+"%");
		}

		if (command.startsWith("ctele") && command.length() > 6 && c.playerRights >= 1) {
			int[] coords = c.getFileLoadingHandler().loadCoords("Coords.cfg",command.substring(6));
			if(coords != null && coords.length >= 2)
				c.teleport(coords[0],coords[1]);
		}
		
		if (c.playerRights >= 1) {

			if (command.startsWith("update") && command.length() > 7) {
				PlayerHandler.updateSeconds = (Integer.parseInt(command.substring(7)) + 1);
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}

			if (command.startsWith("kick"))
			{
				PlayerHandler.kickNick = command.substring(5);
				c.sendMessage("You kicked "+command.substring(5));
				System.out.println("Admin/Mod:"+c.playerName+" is kicking "+command.substring(5));;
			} else if(command.startsWith("char")) {
				c.getFrameMethodHandler().showInterface(3559);
			} else if (command.startsWith("kick")) {
				try {
					PlayerHandler.kickNick = command.substring(5);
					PlayerHandler.messageToAll = c.playerName+": Kicking Player: "+command.substring(5);
					BufferedWriter bw = null;

					try {
						bw = new BufferedWriter(new FileWriter("logs/kicklogs.txt", true));
						bw.write(c.playerName+" kicked "+PlayerHandler.kickNick);
						bw.newLine();
						bw.flush();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bw != null) try {
							bw.close();
						} catch (IOException ioe2) {
							c.sendMessage("Error logging kicks!");
						}
					}


					try {
						bw = new BufferedWriter(new FileWriter("./logs/kicklogs.txt", true));
						bw.write(c.playerName+" kicked "+PlayerHandler.kickNick);
						bw.newLine();
						bw.flush();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bw != null) try {
							bw.close();
						} catch (IOException ioe2) {
							c.sendMessage("Error logging kicks!");
						}
					}
				} catch(Exception e) {
					c.sendMessage("Wrong Syntax! Use as ::kick [PLAYERNAME]");
				}
			} else if (command.equalsIgnoreCase("kickall")) {
				PlayerHandler.kickAllPlayers = true;
			}

		}

	}

}

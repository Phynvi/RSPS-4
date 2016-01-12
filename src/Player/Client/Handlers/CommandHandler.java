import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CommandHandler {

	public static void passCommand(client c, String command){

		if(command.equalsIgnoreCase("spellbook") && c.playerRights >= 1){
			if(c.spellbook == 0){
				c.spellbook = 1;
				c.setSidebarInterface(6, 12855);
			}
			else{
				c.spellbook = 0;
				c.setSidebarInterface(6, 1151);
			}
			c.savechar();
			c.savemoreinfo();
		}
		
		if(command.equalsIgnoreCase("levelup") && c.playerRights >= 2){
			for(int i = 0; i < c.playerLevel.length; i++){
				c.addSkillXP(7500000, i);
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
				c.addItem(i,100000);
			return;
		}

		if(command.startsWith("gear") && c.playerRights > 0){
			c.addItems(4151,14638,14860,14511,14512,15350,15150,3631,12003,13308,6585,4734,2434,2434,2434,2434,6737,15335);
			c.addItem(4740,10000);
			return;
		}

		if(command.startsWith("prayerpotions")){
			for(int i = 0; i <= 10; i++)
				c.addItem(2434);
			return;
		}

		if(command.startsWith("suicide") && c.playerRights > 0){
			c.NewHP = 0;
			c.IsDead = true;
			return;
		}

		if(command.startsWith("save")){
			c.sendMessage(c.playerName+" - Saving Status: ");
			if(c.savechar()) c.sendMessage("Character Saved, ");
			else c.sendMessage("Failed to save character,");

			if(c.savemoreinfo()) c.sendMessage("Character moreinfo saved");
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
			c.openUpBank(); 

		if (command.equalsIgnoreCase("allkick") && (c.playerRights >= 1)) 
			PlayerHandler.kickAllPlayers = true;

		if (command.equalsIgnoreCase("food") && (c.playerRights >= 1)) {
			while(c.addItem(391, 1)){}
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
				c.stillgfx(gfx, c.absY, c.absX);
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

		if (command.startsWith("nnum") && c.playerRights >= 2) {
			try {
				c.nnum = Integer.parseInt(command.substring(5));
				c.sendMessage("nnum is now: "+c.nnum+".");
			} catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::nnum #");
			}
		} 

		if (command.startsWith("delete") && c.playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("CFG/delete.txt", true));
				c.deletethatobject(c.absX, c.absY);
				bw.write("c.deletethatobject("+c.absX+", "+c.absY+");");
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

			while (c.currentItem < 20000 && c.freeSlots() > 0){
				if (c.getItemName(c.currentItem) != null){
					c.addItem(c.currentItem, 1);
					c.sendMessage("Item ID "+c.currentItem+", "+c.getItemName(c.currentItem)+", is not null.");
					c.currentItem += 1;
					return;
				}
				c.currentItem += 1;
			}
		}

		if (command.startsWith("findNull") && c.playerRights > 1){

			while (c.currentItem < 20000 && c.freeSlots() > 0){
				if (c.getItemName(c.currentItem) == null){
					c.addItem(c.currentItem, 1);
					c.sendMessage("Item ID "+c.currentItem+" is "+c.getItemName(c.currentItem));
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
				c.createNewTileObject(c.absX, c.absY, object, objectdirection, 10);  
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
		
		if (command.startsWith("panelobj") && c.playerRights >= 2) {
			BufferedWriter bw = null;
			try {
				int obj = Integer.parseInt(command.substring(9));
				c.panelobj = obj;
				c.sendMessage("Panel object is set to "+c.panelobj+".");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :item #");
			}
		}
		if (command.startsWith("paneldi") && c.playerRights >= 2) {
			BufferedWriter bw = null;

			try {
				int obj = Integer.parseInt(command.substring(8));
				c.paneldi = obj;
				c.sendMessage("Panel direction is set to "+c.paneldi+".");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as :item #");
			}
		}

		if (command.startsWith("panelprint") && c.playerRights >= 2) {
			if (c.panelprint == false){
				c.panelprint = true;
				c.sendMessage("Panel print true.");
			}
			else if (c.panelprint == true){
				c.panelprint = false;
				c.sendMessage("Panel print false.");
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
					c.addItem(i);
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
				c.addItem(newitem, 1);
			}
			catch(NumberFormatException e) {
				String itemName = command.substring(5);
				boolean foundItem = false;
				for(int i = 0; i < server.itemHandler.ItemListArray.length; i++){
					if( server.itemHandler.ItemListArray[i] != null && server.itemHandler.ItemListArray[i].itemName.equalsIgnoreCase(itemName) ){
						c.sendMessage("Found "+server.itemHandler.ItemListArray[i].itemName+", ID "+i);
						c.addItem(i);
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
				c.showInterface(intname);
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
				c.showInterface(intname);
				for(int i = intname-200; i <= intname+200; i++){
					if(i <= 0) i = 1;
					c.sendQuest(""+i,i);
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
				c.openUpShop(shopname);
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
				c.spawnNPC(newNPC,c.absX,c.absY); 
				bw = new BufferedWriter(new FileWriter("CFG/autospawn.cfg", true));
				bw.write("spawn = "+newNPC+"	"+c.absX+"	"+c.absY+"	"+c.heightLevel+"	"+c.absX+"	"+c.absY+"	"+c.absX+"	"+c.absY+"	2");
				bw.newLine();
				bw.flush();
				c.sendMessage(c.getNpcName(newNPC)+" sucessful input. ID was "+newNPC);
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

		if(command.startsWith("configi")){
			try {
				c.configi = Integer.parseInt(command.substring(8));
				c.sendMessage("config i is set to "+c.configi);
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::configi #");
			}
		}

		if(command.startsWith("setconfig")){
			try {
				c.configi = Integer.parseInt(command.substring(10));
				c.sendMessage("setconfig("+c.configi+", 0)");
				c.setconfig(c.configi, 0);
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::configi #");
			}
		}

		if(command.startsWith("stop"))
			c.configiToggle = false;

		if (command.startsWith("npc") && c.playerRights >= 2) {
			try {
				int newNPC = Integer.parseInt(command.substring(4));
				c.spawnNPC(newNPC,c.absX,c.absY); 
				c.sendMessage(c.getNpcName(newNPC)+" has been spawned. ID was "+newNPC+".");
			}
			catch(Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::npc #");
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
				c.sendMessage(c.getNpcName(newNPC)+" sucessful input. ID was "+newNPC);
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
					c.addItem (newItemID, newItemAmount);
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
			c.setmusictab();
		}

		if(command.startsWith("banuser") && (c.playerRights >= 2 || c.debugmode))
		{
			String victim = command.substring(8);
			PlayerHandler.kickNick = victim;
			System.out.println("Admin:"+c.playerName+" is banning "+victim);
			c.sendMessage("Player "+victim+" successfully banned");
			c.appendToBanned(victim);
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
			c.appendToBanned(otherPName);
			c.appendToBannedIps(otherPName);
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
			c.appendToMacroWarn(victim);
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
			c.removeAllItems();

		if (command.equalsIgnoreCase("players"))
			c.playerMenu();


		else if (command.equalsIgnoreCase("status")) 
			c.Menu(c.menuHandler.BarrowsHelp());

		else if (command.equalsIgnoreCase("stats")) 
			c.Menu(c.menuHandler.Stats2());

		else if (command.equalsIgnoreCase("rules")) 
			c.menu(c.menuHandler.Rules());

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
			c.menu("@gre@Current Effects","","",
					"Protect From Mage : "+c.PMage,
					"Protect From Range : "+c.PRange,
					"Protect From Melee : "+c.PMelee,
					"",
					"Attack Bonus : "+c.attEffect+"%",
					"Strength Bonus : "+c.strEffect+"%",
					"Defence Bonus : "+c.defEffect+"%");
		}

		if (command.startsWith("ctele") && command.length() > 6 && c.playerRights >= 1) {
			c.loadCoords("Coords.cfg",command.substring(6));
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
				c.showInterface(3559);
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

// SarimScape client by Angelo
//Remnants of many older servers, satan's isle mostly


import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.security.*;

public class client extends Player implements Runnable {
	
	private NPCClickHandler npcClickHandler = new NPCClickHandler(this);
	public NPCClickHandler getNPCClickHandler(){
		return this.npcClickHandler;
	}
	
	private ItemUse itemUseHandler = new ItemUse(this);
	public ItemUse getItemUseHandler(){
		return this.itemUseHandler;
	}
	
	private Combat combatHandler = new Combat(this);
	public Combat getCombatHandler(){
		return this.combatHandler;
	}
	
	private ObjectClick objectClickHandler = new ObjectClick(this);
	public ObjectClick getObjectClickHandler(){
		return this.objectClickHandler;
	}
	
	private InventoryHandler inventoryHandler = new InventoryHandler(this);
	public InventoryHandler getInventoryHandler(){
		return this.inventoryHandler;
	}
	
	private ClientMethodHandler methodHandler = new ClientMethodHandler(this);
	public ClientMethodHandler getClientMethodHandler(){
		return this.methodHandler;
	}
	
	private FrameMethods frameMethodHandler = new FrameMethods(this);
	public FrameMethods getFrameMethodHandler(){
		return this.frameMethodHandler;
	}
	
	public Agility getAgilityHandler(){
		return this.AGILITY;
	}
			
	public void disconnectPlayerAndSave(String reason){
		//disconnected = true;
		this.FARM.plantList.stopAll();
		this.Events.stop();
		logout();
		savemoreinfo();
		savecharbackup();
		savechar();
		System.out.println(playerName+" disconnected reason : "+reason);
	}
				
	public long hitDelayTimer = 0;
	public boolean isHitSpamming(){
		if (System.currentTimeMillis() - hitDelayTimer < 1000)
			return true;
		return false;
	}
	
	public long playerSpamTimer = 0;
	/**
	 * Checks spamtimer to current system millis
	 */
	public boolean isPlayerSpamming(){
		if (System.currentTimeMillis() - playerSpamTimer < SPAMAMOUNT)
			return true;
		return false;
	}
	
	/**
	 * Checks spamtimer to current system millis
	 * @return true if method was called within 2000 MS
	 */
	public boolean isNPCSpamming(){
		if (System.currentTimeMillis() - spamtimer < SPAMAMOUNT)
			return true;
		return false;
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
		
	
	public void populate(LinkedList<Drop> list, Drop ... drops){
		for(int i = 0; i < drops.length; i++){
			for(int j = 0; j < drops[i].getPercent(); j++){
				list.add(drops[i]);
			}
		}
	}
	
	public void StartVariables(){
		this.Events = new EventManager();
		DIALOGUEHANDLER = new npcDialogueBST();
		this.MISCSTRUCTS = new FoodHandler(this);
		this.FLETCHING = new Fletching(this);
		this.MAGICDATAHANDLER = new MagicDataHandler(this);
		this.BOWHANDLER = new RangeDataHandler(this);
		this.SLAYER = new Slayer(this);
		this.PRAY = new Prayer(this);
		this.CRAFT = new Crafting(this);
		this.menuHandler = new MenuHandler(this);
		this.WC = new Woodcutting(this);
		this.MINE = new Mining(this);   
		this.FARM = new Farming(this);
		this.RUNECRAFTING = new Runecrafting(this);
		this.FISHING = new Fishing();
		this.AGILITY = new Agility(this);
		this.Events.EventStart(60*1000, 0, this); //HP Restore every minute	
		this.Events.EventStart(1000, 1, this); //Calls event index 1 every second
		this.Events.EventStart(30*1000, 2, this); //Called every 30 seconds
//		this.Events.EventStart(500, 3, this); //Called every 500 ms
		this.Events.EventStart(15*1000, 4, this); //Called every 15 seconds
		this.Events.EventStart(100, 5, this); //Called every 100ms
		this.Events.EventStart(3*1000, 6, this); //Called every 3 seconds
		this.Events.EventStart(180*1000, 7, this);//Called every 3 minutes, char save, char backup save
		this.Events.EventStart(600*1000, 8, this); //Called every 10 minutes, character backup
	}	
		
		
public client(){}

public static long upTime;

    public long doTime() {
        long a = server.upTime;
        long b = System.currentTimeMillis();
        long c;
        c = (b-a);
        long d;
        d = ((c / 1000)/60);
        return d;
    }
    
    public void restartserver() {
        println("Restarting server");
        misc.println("Saving all games...");
        PlayerHandler.kickAllPlayers = true;
        misc.println("All games saved");
        closeListener();
        runserver();
    }
    
        public void runserver() {
        try {
            String File = "run.bat";
            String Dir = "./" + File; // Directory

            Runtime.getRuntime().exec(Dir);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } 
    }
    
        public void closeListener() {
        try {
            server.shutdownClientHandler = true;
            if (server.clientListener != null) {
                server.clientListener.close();
            }
            server.clientListener = null;
        } 
        catch (java.lang.Exception __ex) {
            __ex.printStackTrace();
        }
    }


	public int distanceToPointNPC(int EnemyX, int EnemyY, int pointX,int pointY) {
        return (int) Math.sqrt(Math.pow(EnemyX - pointX, 2) + Math.pow(EnemyY - pointY, 2));
    }

public void attackNPCSWithin(int gfx, int maxDamage, int range, int EnemyX, int EnemyY, int pgfx, int anim) {
	for (int i = 0; i <= server.npcHandler.maxNPCs; i++) {
		if(server.npcHandler.npcs[i] != null) {
			if(distanceToPointNPC(EnemyX, EnemyY, server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY) <= range && !server.npcHandler.npcs[i].IsDead && server.npcHandler.npcs[i].HP != 3000 && server.npcHandler.npcs[i].HP != 0) {
				int damage = misc.random(maxDamage);
				stillgfx(gfx, server.npcHandler.npcs[i].absY, server.npcHandler.npcs[i].absX);
				if (server.npcHandler.npcs[i].HP - hitDiff < 0) {
					damage = server.npcHandler.npcs[i].HP;
				}
				stillgfx(pgfx, absY, absX);
			 setAnimation(anim);
			 teleportToX = absX;
				teleportToY = absY;
				server.npcHandler.npcs[i].StartKilling = playerId;
				server.npcHandler.npcs[i].RandomWalk = false;
				server.npcHandler.npcs[i].IsUnderAttack = true;
				server.npcHandler.npcs[i].updateRequired = true;
				server.npcHandler.npcs[i].damageNPC(damage);
				addSkillXP((20*damage), 6);
			}
		}
	}	
}



public void smithingvoid(){
if (smithingamount == 1){
if (IsItemInBag(smithdelete) == true){
startAnimation(899);
sendMessage("You make a "+smithname+" bar.");
deleteItem(smithdelete, getItemSlot(smithdelete), 1);
addItem (smithitem, 1);
addSkillXP(smithxp*rate, 13);
smithingtimer = 8;
}
else if (IsItemInBag(smithdelete) == false){
smithingtimer = 0;
}
}
else if (smithingamount == 2){
if (IsItemInBag(smithdelete) == true && IsItemInBag(smithdelete2) == true){
startAnimation(899);
sendMessage("You make a "+smithname+" bar.");
deleteItem(smithdelete, getItemSlot(smithdelete), 1);
deleteItem(smithdelete2, getItemSlot(smithdelete2), 1);
addItem (smithitem, 1);
addSkillXP(smithxp*rate, 13);
smithingtimer = 8;
}
else if (IsItemInBag(smithdelete) == false || IsItemInBag(smithdelete2) == false){
smithingtimer = 0;
}
}
}

public void smithingvoid2(int xp, String name, int lvl, int item, int delete, int delete2, int amount){
	smithlevel = lvl;
	if (playerLevel[13] >= lvl){
  startAnimation(899);
  smithname = name;
	smithxp = xp;
	smithitem = item;
	smithdelete = delete;
  smithdelete2 = delete2;
  smithingamount = amount;
  smithingtimer = 8;
  }
  else {
	sendMessage("A smithing level of at least "+smithlevel+" is required to do that.");  
  }
}


public void scanPickup()
{
if (absX == apickupx && absY == apickupy)
{
if (ItemHandler.itemExists(apickupid, absX, absY))
{
int itemAmount = ItemHandler.itemAmount(apickupid, apickupx, apickupy);
if (addItem(apickupid, itemAmount))
{//only removes the item when has enough space!
ItemHandler.removeItem(apickupid, apickupx, apickupy, itemAmount);
removeGroundItem(apickupx, apickupy, apickupid);
apickupid = -1;
apickupx = -1;
apickupy = -1;
}
}
else if (hasntLoggedin){
apickupid = -1;
apickupx = -1;
apickupy = -1;
}
}
}

	
//agility walk to!

	public void walkTo(int x, int y) {
		newWalkCmdSteps = Math.abs(y)+Math.abs(x);
		if (newWalkCmdSteps % 1 != 0) newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo(" + packetType + ") command contains too many steps (" + newWalkCmdSteps + ").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		int tmpFSX = firstStepX;
		firstStepX -= mapRegionX*8;
		for (i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = absY;
		int tmpFSY = firstStepY;
		firstStepY -= mapRegionY*8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1) && runningEnergy > 0);
		for (i = 0; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] += firstStepX;
			newWalkCmdY[i] += firstStepY;
		}
	}
	
//agility walk to!

public void DragonBaxeSpecial(){        
        litBar = false;
        if(playerLevel[2] < 120) {
            playerLevel[2] += 15;
            addSkillXP(0, 2);
            stillgfx(246, absX, absY);
            requirePlayerUpdate();
            txt4 = "RRRRRAAAAAAAAAGGGGGGGGGHHHHH!!";
            string4UpdateRequired = true;
            AnimationReset = true;
            setAnimation(1056);
            actionTimer = 4;
            teleportToX = absX;
      			teleportToY = absY;
            specialDelay = 0;
        }
        if (playerLevel[2] >= 120){
        	sendMessage("You are already under the influence of a strength boost.");
        }
          getFilling();
}

public void Dragon2hSpecial(){	
		litBar = false;
		stillgfxz(246, absY, absX, 0, 50);	
        setAnimation(3157);
        attackNPCSWithin(getMaxMeleeHit(), 2, absX, absY); 
        AnimationReset = true;
        teleportToX = absX;
  			teleportToY = absY;
        specialDelay -= 6;	
}
    public void ExcaliburSpecial(){
    	litBar = false;
    	if (playerLevel[1] < 130){
            playerLevel[1] += 15;
            addSkillXP(0, 1);
            stillgfx(247, absY, absX);
            requirePlayerUpdate();
            AnimationReset = true;
            setAnimation(1979);
            teleportToX = absX;
      			teleportToY = absY;
            specialDelay -= 10;
            LoopAttDelay = 15;
            getFilling();
    	}
    	if (playerLevel[1] >= 130){
    		sendMessage("You are already under the influence of the maximum amount of defence boosts.");
    	}
    }


public int specBar = 12335;

	public boolean loadCoords(String FileName, String CName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(FileName+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals(CName)) {
					teleportToX=Integer.parseInt(token3[0]);		
					teleportToY=Integer.parseInt(token3[1]);
				}
			} else {
				if (line.equals("[ENDOFCOORDS]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
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

	/*	0 = West || -1 = North || -2 = East || -3 = South	*/
	/*	tileObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls, 10-11 world objects, 12-21: roofs, 22: floor decoration	*/
	public void placeObject(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType<<2) + (Face&3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType<<2) + (Face&3));
		}
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
                  
		public void DeadStats() {
	playerLevel[0] = getLevelForXP(playerXP[0]);
	    sendFrame126(""+getLevelForXP(playerXP[0])+"", 4004);
	    sendFrame126(""+getLevelForXP(playerXP[0])+"", 4005);

	playerLevel[1] = getLevelForXP(playerXP[1]);
	sendFrame126(""+getLevelForXP(playerXP[1])+"", 4008);
	sendFrame126(""+getLevelForXP(playerXP[1])+"", 4009);

            playerLevel[2] = getLevelForXP(playerXP[2]);
	sendFrame126(""+getLevelForXP(playerXP[2])+"", 4006);
	sendFrame126(""+getLevelForXP(playerXP[2])+"", 4007);

            playerLevel[3] = getLevelForXP(playerXP[3]);
	sendFrame126(""+getLevelForXP(playerXP[3])+"", 4016);
	sendFrame126(""+getLevelForXP(playerXP[3])+"", 4017);

            playerLevel[4] = getLevelForXP(playerXP[4]);
	sendFrame126(""+getLevelForXP(playerXP[4])+"", 4010);
	sendFrame126(""+getLevelForXP(playerXP[4])+"", 4011);

            playerLevel[5] = getLevelForXP(playerXP[5]);
	sendFrame126(""+getLevelForXP(playerXP[5])+"", 4012);
	sendFrame126(""+getLevelForXP(playerXP[5])+"", 4013);

            playerLevel[6] = getLevelForXP(playerXP[6]);
      	    sendFrame126(""+getLevelForXP(playerXP[6])+"", 4014);
	    sendFrame126(""+getLevelForXP(playerXP[6])+"", 4015);

            playerLevel[7] = getLevelForXP(playerXP[7]);
	sendFrame126(""+getLevelForXP(playerXP[7])+"", 4034);
	sendFrame126(""+getLevelForXP(playerXP[7])+"", 4035);

            playerLevel[8] = getLevelForXP(playerXP[8]);
	sendFrame126(""+getLevelForXP(playerXP[8])+"", 4038);
	sendFrame126(""+getLevelForXP(playerXP[8])+"", 4039);

            playerLevel[9] = getLevelForXP(playerXP[9]);
	sendFrame126(""+getLevelForXP(playerXP[9])+"", 4026);
	sendFrame126(""+getLevelForXP(playerXP[9])+"", 4027);

            playerLevel[10] = getLevelForXP(playerXP[10]);
	sendFrame126(""+getLevelForXP(playerXP[10])+"", 4032);
	sendFrame126(""+getLevelForXP(playerXP[10])+"", 4033);

            playerLevel[11] = getLevelForXP(playerXP[11]);
	    sendFrame126(""+getLevelForXP(playerXP[11])+"", 4036);
	    sendFrame126(""+getLevelForXP(playerXP[11])+"", 4037);

            playerLevel[12] = getLevelForXP(playerXP[12]);
	    sendFrame126(""+getLevelForXP(playerXP[12])+"", 4024);
	    sendFrame126(""+getLevelForXP(playerXP[12])+"", 4025);

            playerLevel[13] = getLevelForXP(playerXP[13]);
	sendFrame126(""+getLevelForXP(playerXP[13])+"", 4030);
	sendFrame126(""+getLevelForXP(playerXP[13])+"", 4031);

            playerLevel[14] = getLevelForXP(playerXP[14]);
	sendFrame126(""+getLevelForXP(playerXP[14])+"", 4028);
	sendFrame126(""+getLevelForXP(playerXP[14])+"", 4029);

            playerLevel[15] = getLevelForXP(playerXP[15]);
	sendFrame126(""+getLevelForXP(playerXP[15])+"", 4020);
	sendFrame126(""+getLevelForXP(playerXP[15])+"", 4021);

            playerLevel[16] = getLevelForXP(playerXP[16]);
	sendFrame126(""+getLevelForXP(playerXP[16]), 4018);
	sendFrame126(""+getLevelForXP(playerXP[16]), 4019);

            playerLevel[17] = getLevelForXP(playerXP[17]);
	sendFrame126(""+getLevelForXP(playerXP[17]), 4022);
	sendFrame126(""+getLevelForXP(playerXP[17]), 4023);

            playerLevel[20] = getLevelForXP(playerXP[20]);
	sendFrame126(""+getLevelForXP(playerXP[20]), 4152);
	sendFrame126(""+getLevelForXP(playerXP[20]), 4153);
}

	public void restorePot() {

    playerLevel[0] = getLevelForXP(playerXP[0]);
    sendFrame126(""+playerLevel[0]+"", 4004);
    playerLevel[1] = getLevelForXP(playerXP[1]);
    sendFrame126(""+playerLevel[1]+"", 4008);
    playerLevel[2] = getLevelForXP(playerXP[2]);
    sendFrame126(""+playerLevel[2]+"", 4006);
    playerLevel[4] = getLevelForXP(playerXP[4]);
    sendFrame126(""+playerLevel[4]+"", 4010);
    playerLevel[6] = getLevelForXP(playerXP[6]);
    sendFrame126(""+playerLevel[6]+"", 4014);
    playerLevel[7] = getLevelForXP(playerXP[7]);
    sendFrame126(""+playerLevel[7]+"", 4034);
    playerLevel[8] = getLevelForXP(playerXP[8]);
    sendFrame126(""+playerLevel[8]+"", 4038);
    playerLevel[9] = getLevelForXP(playerXP[9]);
    sendFrame126(""+playerLevel[9]+"", 4026);
    playerLevel[10] = getLevelForXP(playerXP[10]);
    sendFrame126(""+playerLevel[10]+"", 4032);
    playerLevel[11] = getLevelForXP(playerXP[11]);
    sendFrame126(""+playerLevel[11]+"", 4036);
    playerLevel[12] = getLevelForXP(playerXP[12]);
    sendFrame126(""+playerLevel[12]+"", 4024);
    playerLevel[13] = getLevelForXP(playerXP[13]);
    sendFrame126(""+playerLevel[13]+"", 4030);
    playerLevel[14] = getLevelForXP(playerXP[14]);
    sendFrame126(""+playerLevel[14]+"", 4028);
    playerLevel[15] = getLevelForXP(playerXP[15]);
    sendFrame126(""+playerLevel[15]+"", 4020);
    playerLevel[16] = getLevelForXP(playerXP[16]);
    sendFrame126(""+playerLevel[16]+"", 4018);
    playerLevel[17] = getLevelForXP(playerXP[17]);
    sendFrame126(""+playerLevel[17]+"", 4022);
    playerLevel[20] = getLevelForXP(playerXP[20]);
    sendFrame126(""+playerLevel[20]+"", 4152);
    if (superRestore == true) {
    playerLevel[5] = getLevelForXP(playerXP[5]);
    sendFrame126(""+playerLevel[5]+"", 4012);
                     superRestore = false;
    }
	}
	public void animation(int id, int Y, int X){  //ANIMATIONS AT GROUND HEIGHT
		for (Player p : server.playerHandler.players){
			if(p != null){
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")){
					if(person.distanceToPoint(X, Y) <= 60)
						person.animation2(id, Y, X);
				}
			}
		}
	}
public void animation3(int id, int Y, int X)  //ANIMATIONS AT MIDDLE HEIGHT
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
person.animation4(id, Y, X);
}
}
}
}
}


public void animation2(int id, int Y, int X) //ANIMATIONS AT GROUND HEIGHT
{
outStream.createFrame(85);
outStream.writeByteC(Y - (mapRegionY * 8));
outStream.writeByteC(X - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(id);//Graphic id
outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in  pixels 100 pixels high
outStream.writeWord(0);//Time before casting the graphic
}
public void animation4(int id, int Y, int X) //ANIMATIONS AT GROUND HEIGHT
{
outStream.createFrame(85);
outStream.writeByteC(Y - (mapRegionY * 8));
outStream.writeByteC(X - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(id);//Graphic id
outStream.writeByte(50);//height of the spell above it's basic place, i think it's written in  pixels 100 pixels high
outStream.writeWord(0);//Time before casting the graphic
}

public void staticAnimation(int graphicID, int playerX, int playerY, int heightLevel) { /*Used from phates old stuff*/
		outStream.createFrame(85);
		outStream.writeByteC(playerY - (mapRegionY * 8));
		outStream.writeByteC(playerX - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);				
		outStream.writeWord(graphicID);			//	Graphic ID
		outStream.writeByte(heightLevel);		//	Height above gorund
		outStream.writeWord(0);					//	Pause before casting
	}

public void SpecDamgNPC(int maxDamage) {
   if(server.npcHandler.npcs[attacknpc] != null){
        if (server.npcHandler.npcs[attacknpc].IsDead == false) {
	int damage = misc.random(maxDamage);
        if (server.npcHandler.npcs[attacknpc].HP - hitDiff < 0) 
        damage = server.npcHandler.npcs[attacknpc].HP;
        server.npcHandler.npcs[attacknpc].StartKilling = playerId;
	server.npcHandler.npcs[attacknpc].RandomWalk = false;
	server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
	server.npcHandler.npcs[attacknpc].updateRequired = true;
	server.npcHandler.npcs[attacknpc].damageNPC(damage);
    } 
   }
   
  }

/**
 * inflicts direct damage to NPC with id attacknpc in npcs array
 */
public void SpecDamgNPC2(int directDamage) {
	   if(server.npcHandler.npcs[attacknpc] != null) 
	    {
		if (server.npcHandler.npcs[attacknpc].IsDead == false) {
	        if (server.npcHandler.npcs[attacknpc].HP - hitDiff < 0){ 
	        directDamage = server.npcHandler.npcs[attacknpc].HP;
	        DClawsTimer = 0; //if using DClaws spec
	        }
	    server.npcHandler.npcs[attacknpc].StartKilling = playerId;
		server.npcHandler.npcs[attacknpc].RandomWalk = false;
		server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
		server.npcHandler.npcs[attacknpc].updateRequired = true;
		server.npcHandler.npcs[attacknpc].damageNPC(directDamage);
	    } 
	   }
	  }


public void SpecDamg(int maxDamage) {
 for (Player p : server.playerHandler.players)
  {
   if(p != null) 
    {
	 if (PlayerHandler.players[AttackingOn].IsDead == false ) {
	 int damage = misc.random(maxDamage);
         if (PlayerHandler.players[AttackingOn].playerLevel[3] - hitDiff < 0) 
         damage = PlayerHandler.players[AttackingOn].playerLevel[3];
	 PlayerHandler.players[AttackingOn].hitDiff = damage;
	 PlayerHandler.players[AttackingOn].updateRequired = true;
	 PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
	 }
	}
      }
    }
/* DUELING */

public void RefreshDuelRules() {
 sendQuest("", 7817);
 sendQuest("", 669);
 sendQuest("", 6696);
 sendQuest("", 6731);
 
 if(duelRule[0])
 sendQuest("No range", 6698);
 else
 sendQuest("No range", 6698);
 if(duelRule[1])
 sendQuest("No melee", 6699);
 else
 sendQuest("No melee", 6699);
 if(duelRule[2])
 sendQuest("No magic", 6697);
 else
 sendQuest("No magic", 6697);
 if(duelRule[3])
 sendQuest("No food & pots", 6701);
 else
 sendQuest("No food & pots", 6701);
 if(duelRule[4])
 sendQuest("No weapons", 6702);
 else
 sendQuest("No weapons", 6702);
 if(duelRule[5])
 sendQuest("No armour", 6703);
 else
 sendQuest("No armour", 6703);
}

public void LogDuel(String otherName) {
for(int i = 0; i < otherDuelItems.length; i++) {
  BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/duels.txt", true));
	 bw.write(playerName+" wins item: "+(otherDuelItems[i] - 1)+" amount: "+otherDuelItemsN[i]+" from "+otherName);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging duel!");
	 }
      }
   }
}
        public void DuelVictory() {
                client plr  = (client) server.playerHandler.players[duelWith];
                sendQuest(""+plr.combat, 6839);
                sendQuest(plr.playerName, 6840);
                itemsToVScreen();
                showInterface(6733);
teleportToX = 3024;
teleportToY = 3206;
		frame1(); 
		requirePlayerUpdate();
                NewHP = getLevelForXP(playerXP[3]);
                setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
                playerLevel[3] = getLevelForXP(playerXP[3]);
                setSkillLevel(5, getLevelForXP(playerXP[5]), playerXP[5]);
                playerLevel[5] = getLevelForXP(playerXP[5]);
                refreshSkills();
                LogDuel(plr.playerName);
                didTeleport = true;
                duelStatus = 4;
                winDuel = true;
        }

	public boolean DeclineDuel() {
                client duelz = (client) server.playerHandler.players[duelWith];
                if(duelz == null) return false;
                duelz.RemoveAllDuelItems();
                duelz.sendMessage("The other player declined rules and stake options.");
                duelz.closeInterface();
		duelz.resetItems(3214);
		duelz.resetDuel();
	        RemoveAllDuelItems();
                sendMessage("You decline the duel.");
                closeInterface();
		resetItems(3214);
		resetDuel();  
                return true;        
	}

        public void RemoveAllDuelItems() {
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItems[i] > 0) {
				fromDuel((duelItems[i] - 1), i, duelItemsN[i]);
			}
		}
       }


        public int GetDuelItemSlots() {
                int Slots = 0;
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItems[i] > 0) {
				Slots++;
			}
		}
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItems[i] > 0) {
				Slots++;
			}
		}
                return Slots;
       }
	public void itemsToVScreen() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6822);
		outStream.writeWord(otherDuelItems.length);
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(otherDuelItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(otherDuelItemsN[i]);
			}
			if (otherDuelItems[i] > 17000 || otherDuelItems[i] < 0) {
				otherDuelItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(otherDuelItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void refreshDuelScreen() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6669);
		outStream.writeWord(duelItems.length);
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(duelItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(duelItemsN[i]);
			}
			if (duelItems[i] > 17000 || duelItems[i] < 0) {
				duelItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(duelItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6670);
		outStream.writeWord(otherDuelItems.length);
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(otherDuelItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(otherDuelItemsN[i]);
			}
			if (otherDuelItems[i] > 17000 || otherDuelItems[i] < 0) {
				otherDuelItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(otherDuelItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public boolean stakeItem(int itemID, int fromSlot, int amount) {

               boolean IsInDuelScr = false;
               client duelz = (client) server.playerHandler.players[duelWith];

		if (duelWith <= 0 || PlayerHandler.players[duelWith] == null) {
                   sendMessage("Error - other player is nulled");
                   return false;
                  }
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			for (int i = 0; i < duelItems.length; i++) {
				if (duelItems[i] == playerItems[fromSlot]) {
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true || Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) {
						duelItemsN[i] += amount;
						PlayerHandler.players[duelWith].otherDuelItemsN[i] += amount;
                                                IsInDuelScr = true;
                                                resetItems(3322);
                                                refreshDuelScreen();
			                        duelz.resetItems(3322);
			                        duelz.refreshDuelScreen();
						break;
					}
				}
			}
			if (!IsInDuelScr) {
				for (int i = 0; i < duelItems.length; i++) {
					if (duelItems[i] <= 0) {
						duelItems[i] = playerItems[fromSlot];
						duelItemsN[i] = amount;
						PlayerHandler.players[duelWith].otherDuelItems[i] = playerItems[fromSlot];
						PlayerHandler.players[duelWith].otherDuelItemsN[i] += amount;
                                                resetItems(3322);
                                                refreshDuelScreen();
						duelz.resetItems(3322);
						duelz.refreshDuelScreen();
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
                        resetItems(3214);
                        duelz.resetItems(3214);
                        resetItems(3322);
                        refreshDuelScreen();
			duelz.resetItems(3322);
			duelz.refreshDuelScreen();
			if (PlayerHandler.players[duelWith].duelStatus == 2) { // This resets the waiting for other bit for the other player (so they dont have it accepted when stakes are changed)
				PlayerHandler.players[duelWith].duelStatus = 1;
                                duelStatus = 1;
				duelz.sendFrame126("", 6684);
			}
			return true;
		}
		return false;
	}


	public boolean fromDuel(int itemID, int fromSlot, int amount) {
               client duelz = (client) server.playerHandler.players[duelWith];
		if (amount > 0 && (itemID + 1) == duelItems[fromSlot]) {
			if (amount > duelItemsN[fromSlot]) {
				amount = duelItemsN[fromSlot];
			}
			addItem((duelItems[fromSlot] - 1), amount);
			if (amount == duelItemsN[fromSlot]) {
				duelItems[fromSlot] = 0;
				PlayerHandler.players[duelWith].otherDuelItems[fromSlot] = 0;
			}
			duelItemsN[fromSlot] -= amount;
			PlayerHandler.players[duelWith].otherDuelItemsN[fromSlot] -= amount;                        
                        resetItems(3214);
                        duelz.resetItems(3214);
                        resetItems(3322);
                        refreshDuelScreen();
			duelz.resetItems(3322);
			duelz.refreshDuelScreen();
			if (PlayerHandler.players[duelWith].duelStatus == 2) { // This resets the waiting for other bit for the other player (so they dont have it accepted when stakes are changed)
				PlayerHandler.players[duelWith].duelStatus = 1;
                                duelStatus = 1;
				duelz.sendFrame126("", 6684);
                        }
			return true;
		}
		return false;
	}


	public void resetDuel() {
		duelWith = 0;
                duelStatus = -1;
                winDuel = false;
		for (int i = 0; i < duelItems.length; i++) {
                        duelRule[i] = false;
			duelItems[i] = 0;
			duelItemsN[i] = 0;
			otherDuelItems[i] = 0;
			otherDuelItemsN[i] = 0;
		}
	}
public int totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]) + getLevelForXP(playerXP[21]));
	
	
        public void updateCharAppearance(int[] styles, int[] colors) {
		for(int j = 0; j < 7; j++) {
			if(styles[j] > 0) {
				styles[j] += 0x100;
				pCHead  = styles[0];
				pCBeard = styles[1];
				pCTorso = styles[2];
				pCArms  = styles[3];
				pCHands = styles[4];
				pCLegs  = styles[5];
				pCFeet  = styles[6];
			}
		}
		for(int i = 0; i < 5; i++) {
			pColor = colors[i];
		}
	}
	
	public void setconfig(int settingID, int value) {	
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}
	public void viewTo(int coordX, int coordY) {
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		requirePlayerUpdate();
	}
public boolean isUntradable(int item) {
  for(int i = 0; i < untradable.length; i++) {
   if(untradable[i] == item)
    return true;
  }
 return false;
}


	public int GetGroundItemID(int ItemID, int itemX, int itemY) {
		for (int i = 0; i < 19999; i++) {
			if (server.itemHandler.globalItemID[i] > -1) {
				if (server.itemHandler.globalItemID[i] == ItemID && server.itemHandler.globalItemX[i] == itemX && server.itemHandler.globalItemY[i] == itemY) {
					return i;
				}
			}
		}
		return -1;
	}

/* OBJECTS MAIN */

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
public void AddObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/*CREATE OBJECT*/
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); //0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}

/*WALKING TO OBJECT BEFORE DOING ACTION*/

	public void DoAction() {
		viewTo(destinationX, destinationY);
		
		switch (ActionType) {

		case 1: // Object click 1
			objectClick(destinationID, destinationX, destinationY, 0, 0, 1);
			break;

		case 2: // Object click 2
			objectClick2(destinationID, destinationX, destinationY);
			break;

		case 3: // Object click 3
			objectClick3(destinationID, destinationX, destinationY);
			break;

		case 4: // Object click 4!
			objectClick4(destinationID, destinationX, destinationY);
			break;

		default: // error
			debug("Error - unknown ActionType found");
			break;

		}
	}

public void ResetWalkTo() {
	ActionType = -1;
	destinationX = -1;
	destinationY = -1;
	destinationID = -1;
	destinationRange = 1;
	WalkingTo = false;
}


public void objectClick(Integer objectID, int objectX, int objectY, int face, int face2, int GateID) {	
		debug("atObject: "+objectX+","+objectY+" objectID: "+objectID); 

	if(isObjSpamming()) return;
		objtimer = System.currentTimeMillis();
	
	stopAnimations();	

	switch(objectID) {
	
	case 3790: //rocks entrance to kree
		if((objectX == 2878 && objectY == 3623) || (objectX == 2878 && objectY == 3622)){ //past the entrance
			isteleporting3(839, 15, 2880,3622, 0);
			break;
		}
		if(playerLevel[playerRanged] >= 70){
			if(armadyl >= 20){
				isteleporting3(839, 15, 2861,3626, 0);
				prevarmadyl = armadyl;
				armadyl = 0;
				savechar();
				savemoreinfo();
			}
			else sendMessage("You need at least 20 Armadyl minion kills to do that.");
		}
		else sendMessage("You need at least 80 Strength to do that.");
		break;
		
	case 3791: //rocks exit from kree
		if((objectX == 2879 && objectY == 3623) || (objectX == 2879 && objectY == 3622)){ //past the entrance
			isteleporting3(839, 15, 2877,3623, 0);
			break;
		}
		isteleporting3(839, 15, 2858,3626, 0);
		break;
	
	case 3722: //rocks entrance to general graardor
		if(playerLevel[playerStrength] >= 80){
			if(bandos >= 20){
				isteleporting3(839, 15, 2880, 3592, 0);
				prevbandos = bandos;
				bandos = 0;				
				savechar();
				savemoreinfo();
			}
			else sendMessage("You need at least 20 Bandos minion kills to do that.");
		}
		else sendMessage("You need at least 80 Strength to do that.");
		break;
		
	case 3723: //rocks exit from general graardor
		isteleporting3(839, 15, 2881, 3596, 0);
		break;
	
	case 3748: //godwars rocks
		agilityObstacle(2856,3613,2856,3611, 839, 35, 55, false, true, 2, "You injure yourself on the rocks.");
		agilityObstacle(2857,3613,2857,3611, 839, 35, 55, false, true, 2, "You injure yourself on the rocks.");
		agilityObstacle(2834,3629,2834,3627, 839, 35, 55, false, true, 2, "You injure yourself on the rocks.");
		agilityObstacle(2833,3629,2833,3627, 839, 35, 55, false, true, 2, "You injure yourself on the rocks.");
		agilityObstacle(2820,3635,2822,3635, 839, 35, 55, false, true, 2, "You injure yourself on the rocks.");
		break;
	
	case 5090: //glitchy log in brimhaven dungeon
		teleport(2681,9505);
		break;
	case 5088:
			teleport(2688,9507);			
		break;
	
	case 2366:
		sendMessage("The sign reads : ");
		sendMessage("This is why replacing models in the client is not always a good idea.");
		break;
		
	case 6970: //Mort Myre Swamp Boat to Mort'ton
		selectOptionTravel2("Take the boat to Mort'ton?", "Yes", 3522,3285, "No", -1, -1);
		break;
	case 6969: //Mort'ton Swamp Boat to Mort Myre Swamp
		selectOptionTravel2("Take the boat into Mort Myre Swamp?", "Yes", 3500, 3380, "No", -1, -1);
		break;
		
	case 3795: //poison wastes swamp paste
		sendMessage("You take some Swamp Paste.");
		addItem(1940);
		break;
		
	case 5259:
		
		if(agilityObstacle(3659, 3509, 3659, 3507, 841, 0, 0, false, false, 0, "") || agilityObstacle(3660, 3509, 3660, 3507, 841, 0, 0, false, false, 0, "") ||
				agilityObstacle(3653, 3485, 3651, 3485, 841, 0, 0, false, false, 0, "") || agilityObstacle(3653, 3486, 3651, 3486, 841, 0, 0, false, false, 0, ""))
			sendMessage("You pass through the barrier.");
		else sendMessage("Stand directly in front of the barrier to use it.");
		break;
		
	case 5005:
		sendMessage("You cross the bridge.");
		if(isInArea(3500, 3423, 3504, 3426)) teleport(3502,3432);
		else teleport(3502,3425);
		break;
		
	case 1294:
		if(isInArea(2458,3443,2465,3449)) selectOptionTravel2("Travel to Tree Gnome Village?", "Yes", 2542,3169, "No", -1, -1);
		else selectOptionTravel2("Travel to Tree Gnome Stronghold?", "Yes", 2460,3443, "No", -1, -1);
		break;
		
	case 2156: //magic portal wizards guild yanille
		if(RM == 4)
			selectOptionTravel2("Mine Rune Essence?", "Yes", 2911, 4833, "No", -1, -1);
		else sendMessage("The portal shimmers, but does nothing else.");
		break;
		
	case 82: //PVP door to Fight Arena
		if(isInArea(2606,3150,2608,3152))
			selectOptionTravel2("Enter PVP Arena?", "Yes (PVP)", 2604,3154, "No (Safe)", -1, -1);
		else if (objectX == 2606 && objectY == 3152)
			teleport(2608,3150);
		break;
		
	case 5106:	
	case 5107: //vines to demons	
	case 5105:
	case 5104: //brimhaven dungeon vines after entrance	
	case 5103: //vines at entrance of brimhaven dungeon
		if(ItemInBagOrEquipped(lists.axes.toArray())){
			if( agilityObstacle(2676,9479,2674,9479, WC.getAxeEmote(), 0, 0, false, false, 0, "") || 
					agilityObstacle(2693,9482,2695,9482, WC.getAxeEmote(), 0, 0, false, false, 0, "") || agilityObstacle(2672,9499,2674,9499, WC.getAxeEmote(), 0, 0, false, false, 0, "") || 
					agilityObstacle(2683,9570,2683,9568, WC.getAxeEmote(), 0, 0, false, false, 0, "") || agilityObstacle(2691,9564,2689,9564, WC.getAxeEmote(), 0, 0, false, false, 0, "") )
				sendMessage("You cut your way through.");
		}
		else sendMessage("I need an axe to do that.");
		break;
		
		//caves in ogre
	case 2809:
	case 2810:
		sendMessage("Looks dangerous. I better not go down there.");
		break;
		
		//jumping barrier near gutanoth
	case 2834:
		if(!agilityObstacle(2566,3021,2568,3021, 839, 0, 0, false,false, 0, "") && !agilityObstacle(2566,3022,2568,3022, 839, 0, 0, false,false, 0, ""))
			sendMessage("I should stand directly in front of the battlement before jumping them.");
		break;
		
		//jumping rocks at gu'tanoth
	case 2831:
	case 2830:
		if(!agilityTeleport(2530,3025,2531,3030, 839, 0, 0, false, 0, ""))
			sendMessage("I should stand directly in front of the rocks before jumping them.");
		break;
		
		//obstacles at gu'tanoth
	case 2832:
			if(!agilityObstacle(2506,3011,2508,3011, 839, 0, 0, false, false, 0, "") && !agilityObstacle(2506,3012,2508,3012, 839, 0, 0, false, false, 0, ""))
				sendMessage("I should stand directly in front of the barricade to climb over it.");
		break;
		
	case 2492: //portal from rune essence mine
		teleport(3007,3309);
		break;
		
		//Pest control ladder
	case 14315:
		teleport(2661,2639);
		roundTimerFrameCreated = false;
		break;
	case 14314:
		teleport(2657,2639);
		roundTimerFrameCreated = false;
		break;
		
		//warewolf agility entrance and exit
	case 5131:
		if(playerLevel[playerAgility] > 50)
			teleport(3549,9865);
		else sendMessage("You need at least 50 agility to enter.");
		break;
	case 5130:
		teleport(3543,3463);
		break;
	
	
	case 5111: //brimhaven stepping stones
	case 5110:
		agilityTeleport(2649,9562,2647,9557, 1115, 85, 55, true, 5, "");
		break;
	
	case 5098: //bimhaven 2nd floor going to 1st floor, furthest from entrance
		teleport(2637,9517); 
		break;
		
	case 5097: //brimhaven 1st floor going to 2nd floor, furthest from entrance
		teleport(2637,9510,2);
		break;
	
	case 5096: //brimhaven 2nd floor going to 1st floor, near entrance
		teleport(2650,9591);
		break;
		
	case 5094: //brimhaven 1st floor going to 2nd floor, near entrance
		teleport(2643,9594,2);
		break;
	
	case 5099: //brimhaven red dragon pipe	
		agilityObstacle(2698,9500,2698,9492, 844, 70, 45, true, false, 0, "");
		break;
	case 5100: //brimhaven first floor pipe
		agilityObstacle(2655,9573,2655,9566, 844, 70, 45, true, false, 0, "");
		break;
	
	case 5083: //brimhaven dungeon entrance
		teleport(2713,9564);
		break;
		
	case 5084: //brimhaven dungeon exit
		teleport(2744, 3152);
		break;
	
	case 3213: //underground pass entrance from west ardy
		teleport(2495,9715,0);
		break;
		
	case 4006: //underground pass entrance from PVP
		teleport(2305,9915,0);
		break;
	
	case 8738: //entrance to west ardy
	case 8739: //entrance to west ardy
		if(!isInArea(2555,3298,2557,3302))
			sendMessage("If I want to get in there I should speak with Omart, who is South of here.");
		break;
	
	case 3214: //underground pass exit to west ardy
		teleport(2435,3315);
		break;
	
	case 3295:
	case 3297:
		sendMessage("The inscription on the tablet is worn.");
		break;
	
	case 3235:
	case 3236:
		agilityObstacle(2451, 9694, 2451,9688, 844, 50, 30, true, false, 0, "");
		break;
	
	case 2274:
		sendMessage("That looks dangerous. I should probably try the pipe.");
		break;

	case 3241:
		teleport(2447,9716,0);
		break;
	
	case 3340: //fire arrow at bridge
		if(BOWHANDLER.checkAmmoWithBow()){
			if(absX == 2447 && (absY == 9716 || absY == 9717)){
				if(playerLevel[playerRanged] >= 50){
					startAnimation(426);
					createNewTileObject(2443,9716, 3240, 3, 10);
					walkingemote(826, 2442, absY, 0, false);
				}
				else sendMessage("You need 50 ranged to do that.");
			}
			else sendMessage("I should stand across from the bridge before trying that.");
		}
		else sendMessage("You need a ranged weapon with ammo and 50 ranged to do that.");
		break;
	
	case 1968:
	case 1967:
		deletethatobject(objectX, objectY);
		break;
	
	case 1747:
		if(objectX == 2466 && objectY == 3495)
			teleport(2466,3494,1);
		else if(objectX == 2408 && objectY == 3435)
			teleport(2408,3436,1);
		else if(objectX == 2423 && objectY == 3442)
			teleport(2423,3441,1);
		else if(objectX == 2476 && objectY == 3463)
			teleport(2477,3463,1);
		else if(objectX == 2677 && objectY == 3087)
			teleport(2677,3088,1);
		break;
	
	case 2884:
	case 1748:
		sendMessage("Please right-click the ladder and select up or down.");
		break;
		
	case 1746:
	case 1740:
	case 5281:
	case 1749:
		if(objectX == 2466 && objectY == 3495)
			teleport(2466,3494,2);
		if(objectX == 2476 && objectY == 3463)
			teleport(2477,3463,0);
		if(objectX == 2408 && objectY == 3435)
			teleport(2408,3436,0);
		if(objectX == 2423 && objectY == 3442)
			teleport(2423,3441,0);
		if(objectX == 2677 && objectY == 3087)
			teleport(2677,3088,0);
		break;

	case 1742:
		if(objectX == 2401 && objectY == 3449)
			teleport(2400,3450,1);
		if(objectX == 2418 && objectY == 3417)
			teleport(2418,3416,1);
		if(objectX == 2440 && objectY == 3404)
			teleport(2440,3403,1);
		if(objectX == 2445 && objectY == 3434)
			teleport(2445,3433,1);
		if(objectX == 2444 && objectY == 3414)
			teleport(2445,3416,1);
		if(objectX == 2455 && objectY == 3417)
			teleport(2457,3417,1);
		if(objectX == 2461 && objectY == 3416)
			teleport(2460,3417,1);
		if(objectX == 2485 && objectY == 3402)
			teleport(2485,3401,1);
		if(objectX == 2488 && objectY == 3407)
			teleport(2489,3409,1);
		if(objectX == 2479 && objectY == 3408)
			teleport(2479,3407,1);
		if(objectX == 2475 && objectY == 3400)
			teleport(2475,3399,1);
		break;
		
	case 1744:
		if(objectX == 2401 && objectY == 3450)
			teleport(2400,3450,0);
		if(objectX == 2418 && objectY == 3417)
			teleport(2418,3416,0);
		if(objectX == 2440 && objectY == 3404)
			teleport(2440,3403,0);
		if(objectX == 2445 && objectY == 3434)
			teleport(2445,3433,0);
		if(objectX == 2445 && objectY == 3415)
			teleport(2445,3416,0);
		if(objectX == 2456 && objectY == 3417)
			teleport(2457,3417,0);
		if(objectX == 2461 && objectY == 3417)
			teleport(2460,3417,0);
		if(objectX == 2485 && objectY == 3402)
			teleport(2485,3401,0);
		if(objectX == 2489 && objectY == 3408)
			teleport(2489, 3409, 0);
		if(objectX == 2479 && objectY == 3408)
			teleport(2479,3407,0);
		if(objectX == 2475 && objectY == 3400)
			teleport(2475,3399,0);
		break;
	
	case 2554:
		sendMessage("It's locked.");
		break;
	
	case 1723:
	if(objectX == 2590 && objectY == 3085) //wizards tower yanille
			teleport(2591,3083,1);
	else if(objectX == 2590 && objectY == 3090) //wizards tower yanille
			teleport(2591,3088,0);
	else if(objectX == 2571 && objectY == 3295)
			teleport(2572,3298,0);
	else if(objectX == 2669 && objectY == 3244)
		teleport(2670,3242,0);
		break;
		
	case 1722:
		if(objectX == 2590 && objectY == 3084) //wizards tower yanille
			teleport(2591,3087,2);
		else if(objectX == 2590 && objectY == 3089) //wizards tower yanille
			teleport(2591,3092,1);
		else if(objectX == 2571 && objectY == 3295)
			teleport(2572,3294,1);
		else if(objectX == 2669 && objectY == 3243)
			teleport(2670,3246,1);
		break;
	
	case 2408: //entrana dungeon entrance
		teleport(2827,9772);
		break;
	
	case 2609: //crandor entrance to karamja dungeon
		teleport(2833,9656);
		break;
	
	case 2610: //karamja rope exit to crandor
		teleport(2834,3257);
		break;
	
	case 492: //karamja dungeon entrance - volcano side
		teleport(2856,9570);
		break;
	case 1764: //karamja dungeon exit - rope to volcano
		teleport(2857,3167);
		break;
	
	case 9358:
		sendMessage("You peek inside the cave and see that it's currently under construction.");
		sendMessage("A construction worker takes notice of you and waves.");
		break;
	
	case 2606:		
		if(combat >= 100)
			ReplaceObject(objectX,objectY,6951, -1);
		else sendMessage("You need at least 100 combat to do that.");
		break;
	
	case 11888:
		if(objectX == 2907 && objectY == 3334 && heightLevel == 0)
			teleport(2908,3336,1);
		break;
	
	case 11890:
		if(objectX == 2908 && objectY == 3335 && heightLevel == 2)
			teleport(2908,3336,1);
		break;
	
	case 11889:
		sendMessage("Please right click the staircase and select up or down.");
		break;
	
	case 3832: //kalphite lair to tunnels exit
		teleport(3509,9495,2);
		break;
	
	case 3829: //kalphite tunnels exit
		teleport(3009,3150,0);
		break;
	
	case 9472: //kalphite tunnels entrance
		teleport(3483,9509,2);
		break;
	
	case 115:
	case 116:
	case 117:
	case 118:
	case 119:
	case 120:
	case 121:
	case 122:
		objtimer = System.currentTimeMillis()-2000;
		startAnimation(794);
		makeGlobalObject(objectX, objectY, objectID+8, 0, 10);
		ItemHandler.addItem(DROPHANDLER.getDrop(DROPHANDLER.RARES), objectX, objectY, 1, playerId, false);
		break;

		//QUEST_1 OBJECTS
	case 4499:
		if (objectX == 2797 && objectY == 3614)
		{
			sendMessage("You crawl through the cave");
			teleportToX = 2772;
			teleportToY = 10231;
		}
		break;

	case 5025:
		if (objectX == 2772 && objectY == 10233)
		{
			sendMessage("You crawl through the cravass");
			teleportToX = 2795;
			teleportToY = 3614;
		}
		break;

	case 394:
		sendMessage("You search the bookcase and find nothing of interest.");
		break;

		//END OF QUEST_1 OBJECTS
	case 3581:
	case 3608:
		if (System.currentTimeMillis() - ticket >= 3000) {
			sendMessage("Too late!");
		}
		else{
			int luck = misc.random(9)+1;
			if (luck == 1){
				sendMessage("You tag the dispenser, you get 1 ticket.");
				addItem(2996, 1);
			}
			else if (luck >= 1){
				sendMessage("You tag the dispenser, and get "+luck+" tickets!");
				addItem(2996, luck);
			}
			ticket = 0;
			setAnimation(832);
		}
		break;

	case 11993:
	case 1537:
	case 2427:
	case 2429:
		if ((objectX == 3231 && objectY == 3433) || (objectX == 3253 && objectY == 3431) || (objectX == 2719 && objectY == 9671) || (objectX == 2722 && objectY == 9671) || (objectX == 3109 && objectY == 3167) || (objectX == 3107 && objectY == 3162)) {
			face = -3; //South
		} else if ((objectX == 3234 && objectY == 3426) || (objectX == 3225 && objectY == 3293) || (objectX == 3230 && objectY == 3291) || (objectX == 3235 && objectY == 3406) || (objectX == 3276 && objectY == 3421) || (objectX == 3207 && objectY == 3210)) {
			face = -2; //East
		} else if ((objectX == 3233 && objectY == 3427) || (objectX == 3215 && objectY == 3225) || (objectX == 3207 && objectY == 3217) || (objectX == 3208 && objectY == 3211)) {
			face = -1; //North
		}//else = West (standard)
		ReplaceObject(objectX, objectY, (objectID - 1), face);

		break;


		//Dwarf Problems I

	case 2865:
	case 2866:
		if (playerLevel[17] >= 40){
			sendMessage("You pick the lock!");
			ReplaceObject(objectX,objectY,6951, -1);
			setAnimation(2246);
		}
		else if (playerLevel[17] < 40){
			sendMessage("You need 40 thieving to break in there!");
		}
		break;

	case 2868:
		if (IsItemInBag(2374) == true){
			sendMessage("Empty!");
		}
		else if (IsItemInBag(2374) == false){
			sendMessage("You reach in and grab a relic part!");
			addItem(2374, 1);
		}
		break;

	case 12802:
		if (IsItemInBag(2375) == true){
			sendMessage("Empty!");
		}
		else if (IsItemInBag(2375) == false && ancients >= 5){
			sendMessage("You reach in and grab a relic part!");
			addItem(2375, 1);
		}
		else if (IsItemInBag(2375) == false && ancients < 5){
			sendMessage("I have no reason to go inside a tomb!");
		}
		break;

	case 8878:
		if (ancients == 6 && freeSlots() >= 1){
			setAnimation(3170);
			sendMessage("You combine all three relic parts!");
			sendMessage("Ogre relic has been added to your inventory.");
			addItem(2372, 1);
			ancients = 7;
			createNewTileObject(2033, 4526, 11079, 0, 10);  
		}
		else if (ancients == 6 && freeSlots() < 1){
			sendMessage("I need at least one free slot!");
		}
		else if (ancients != 6){
			sendMessage("I'd rather not touch that.");
		}
		break;

	case 10321:
		if (ancients >= 5){
			setAnimation(828);
			isteleporting = 15;
			isteleportingx = 2906;
			isteleportingy = 9876;
		}
		else if (ancients < 5){
			sendMessage("I have no reason to enter that filth!");
		}
		break;

		//Dwarf Problems I

	case 1755:
		if(objectX == 2884 && objectY == 9797)
			teleport(2884,3396);
		
		if (absX >=2906  && absX <=2908 && absY >=9875  && absY <=9877) {
			isteleporting3(828, 15, 3018, 3233, 0);
		}
		break;

	case 2174:
		if ((absX == 2012 && absY == 4826) || (absX == 2020 && absY == 4824)) {
			isteleporting3(828, 15, objectX, objectY, 1);
		}
		break;

	case 3617:
		if(playerLevel[playerAgility] >= 75){
			if (freeSlots() >= 28 && playerEquipment[playerShield] == -1 && playerEquipment[playerWeapon] == -1)
				isteleporting3(828, 15, 2805, 9589, 3);
			else 
				npcdialogue("Jackie The Fruit", 1055, "When entering the arena you must", "have nothing in your inventory.", 
						"You are also not allowed to have", "a shield or weapon equipped because", "you need both your hands free.");
		}
		else npcdialogue("Jackie The Fruit", 1055, "You need at least 75 Agility","to do that.");
		break;
	case 3618:
		isteleporting3(828, 15, 2808, 3194, 0);
		sendMessage("you climb up the ladder.");
		break;

	case 2175:
		if (absX >=2011  && absX <=2013 && absY >=4825  && absY <=4827) {
			isteleporting3(828, 15, 2013, 4826, 0);
			sendMessage("you climb down the ladder.");
		}
		else if (absX >=2020  && absX <=2022 && absY >=4823  && absY <=4825) {
			isteleporting3(828, 15, 2020, 4824, 0);
			sendMessage("you climb down the ladder.");
		}
		break;

	case 5136:
		xpgiven = playerLevel[16]*12*rate;
		if (absX == 3533 && (absY == 9909 || absY == 9910 || absY == 9911)) {
			isteleporting3(1115, 14, objectX-2, objectY, 0);
			addSkillXP(xpgiven, 16);
			sendMessage("you climb the skulls.");
		}
		break;


	case 5152:
		xpgiven = playerLevel[16]*12*rate;
		if ((absX == 3538 && absY == 9904) || (absX == 3541 && absY == 9904) || (absX == 3544 && absY == 9904)) {
			setAnimation(746);
			WalkTo(0, 6);
			addSkillXP(xpgiven, 16);
			actionTimer = 10;
			sendMessage("You squeeze through the pipe.");
		}
		break;

	case 5139:
	case 5140:
	case 5141:
		if (absX == 3528 && absY == 9910){
			rockcount = 0;
			xpgiven = playerLevel[16]*12*rate;
			sendMessage("You slide down the zip line!");
			addSkillXP(xpgiven, 16);
			walkingemote3(1117, 3528, 9871, 0, -39, 5000);
			txt4 = "Woaaaah!";
			string4UpdateRequired = true;
		}
		else{
			teleportToX = 3528;
			teleportToY = 9910;
			objtimer = 0;
		}
		break;


	case 5138:
		rockcount += 1;
		xpgiven = playerLevel[16]*rate;
		isteleporting3(3067, 14, objectX, objectY, 0);
		sendMessage("You jump and land safely on the stone!");
		if (rockcount < 4){
			addSkillXP(xpgiven, 16);
		}
		actionTimer = 10;
		break;


	case 5133:
	case 5134:
	case 5135:
		xpgiven = playerLevel[16]*8*rate;
		if ((absX == 3537 || absX == 3538 || absX == 3539 || absX == 3540 || absX == 3541 || absX == 3542 || absX == 3543) && (absY == 9892 || absY == 9895 || absY == 9898)) {
			isteleporting3(3067, 14, absX, absY+2, 0);
			sendMessage("You hop the agility hurdle!");
			addSkillXP(xpgiven, 16);
			actionTimer = 10;
		}
		break;
	case 9293:
		sendMessage("That area is currently under construction.");
		break;
		
	case 1759:
		teleport(2884,9798);
		break;
		
//
//	case 9293:
//		if (playerLevel[16] >= 70){
//			xpgiven = playerLevel[16]*rate;
//			if (absX == 2886 && absY == 9799) {
//				setAnimation(746);
//				WalkTo(6, 0);
//				addSkillXP(xpgiven, 16);
//				actionTimer = 10;
//				sendMessage("You squeeze through the pipe.");
//			}
//
//			else if (absX == 2892 && absY == 9799) {
//				setAnimation(746);
//				WalkTo(-6, 0);
//				addSkillXP(xpgiven, 16);
//				actionTimer = 10;
//				sendMessage("You squeeze through the pipe.");
//			}
//		}
//
//		else if (playerLevel[16] < 70){
//			sendMessage("That's a tight squeeze! You might want 70 agility before trying that!");
//		}
//		break;


		//start of Dangt351s agility FIXED BY AAA Mods

	case 3263:
		sendMessage("I should probably try to climb the rockslides instead.");
		break;
		
	case 3309:
		agilityObstacle(2479, 9721, 2477, 9721, 1115, 50, 40, false, true, 4, "You slip on the rock and injure yourself.");
		agilityObstacle(2479, 9724, 2477, 9724, 1115, 50, 40, false, true, 4, "You slip on the rock and injure yourself.");
		agilityObstacle(2485,9722, 2485,9720, 1115, 50, 40, false, true, 4, "You slip on the rock and injure yourself.");
		agilityObstacle(2480,9712,2480,9714, 1115, 50, 40, false, true, 4, "You slip on the rock and injure yourself.");
		agilityObstacle(2470,9706,2472,9706, 1115, 50, 40, false, true, 4, "You slip on the rock and injure yourself.");
		agilityObstacle(2458,9711,2458,9713, 1115, 50, 40, false,true, 4, "You slip on the rock and injure yourself.");
		agilityObstacle(2460,9721,2460,9719, 1115, 50, 40, false,true, 4, "You slip on the rock and injure yourself.");
		agilityObstacle(2466,9723,2468,9723, 1115, 50, 40, false,true, 4, "You slip on the rock and injure yourself.");
		
		break;
		
	case 3933:
		if(agilityObstacle(2290, 3232, 2290, 3239, 762, 60, 40, false, false, 0, ""))
			sendMessage("You cross the slippery log.");
		break;
		
	case 2295:
		xpgiven = playerLevel[16]*11*rate;
		if (absX == 2474 && absY == 3436) {
			walkingemote4(762, 2474, 3428, 0, -8, xpgiven);
			sendMessage("You walk the slippery log.");
		}
		break;

	case 13993: //Anger Rack
		anger = true;
		selectoption2("Anger Weapons", "Anger Sword", "Anger Spear", "Anger Mace", "Anger Battleaxe");
		break;

	case 13932: 
		setAnimation(844);
		sendMessage("You crawl in.");
		isteleporting = 13;
		isteleportingx = 3015;
		isteleportingy = 5244;
		ithl = 0;
		break;

	case 13878: 
		isteleporting3(844, 13, 3021, 5209, 0);
		sendMessage("You crawl in.");
		break;

	case 13882:
		sendMessage("Under construction.");
		break;

	case 2285:
		xpgiven = playerLevel[16]*10*rate;
		isteleporting3(828, 15, 2474, 3424, 1);
		addSkillXP(xpgiven, 16);
		break;

	case 2313:
		xpgiven = playerLevel[16]*10*rate;
		isteleporting3(828, 15, 2473, 3420, 2);
		addSkillXP(xpgiven, 16);
		break;


	case 2312:
		if (absX == 2477 && absY == 3420) {
			xpgiven = playerLevel[16]*11*rate;
			walkingemote4(762, 2484, 3420, 7, 0, xpgiven);
		}
		break;

	case 2314:
		isteleporting3(828, 15, 2485, 3421, 0);   
		xpgiven = playerLevel[16]*10*rate;
		addSkillXP(xpgiven, 16);
		break;


	case 2286:
		xpgiven = playerLevel[16]*11*rate;
		if ((absX == 2483 || absX == 2484 || absX == 2485 || absX == 2486 || absX == 2487 || absX == 2488) && absY == 3425) {
			isteleporting3(828, 15, 2485, 3427, 0);
			addSkillXP(xpgiven, 16);
		}
		break;
	case 3571:
	case 3570: 
	case 3572: //
	if (absX == 2763 && (absY == 9558 || absY == 9557 || absY == 9556)) {
		walkingemote4(762, 2770, absY, 7, 0, playerLevel[16]*12*rate);
	}
	if (absX == 2770 && (absY == 9558 || absY == 9557 || absY == 9556)) {
		walkingemote4(762, 2763, absY, -7, 0, playerLevel[16]*12*rate);
	}
	if (absX == 2796 && (absY == 9589 || absY == 9590 || absY == 9591)) {
		walkingemote4(762, 2803, absY, 7, 0, playerLevel[16]*12*rate);
	}
	if (absX == 2803 && (absY == 9589 || absY == 9590 || absY == 9591)) {
		walkingemote4(762, 2796, absY, -7, 0, playerLevel[16]*12*rate);
	}
	break;    

	case 3566:
		if (absX == 2806 && absY == 9587){
			walkingemote3(2750, 2806, 9581, 0, -6, playerLevel[16]*12*rate);
			sendMessage("You jump the rope swing!");
		}
		if (absX == 2804 && absY == 9582){
			walkingemote3(2750, 2804, 9588, 0, 6, playerLevel[16]*12*rate);
			sendMessage("You jump the rope swing!");
		}
		if (absX == 2769 && absY == 9567){
			walkingemote3(2750, 2763, 9567, -6, 0, playerLevel[16]*12*rate);
			sendMessage("You jump the rope swing!");
		}
		if (absX == 2764 && absY == 9569){
			walkingemote3(2750, 2770, 9569, 6, 0, playerLevel[16]*12*rate);
			sendMessage("You jump the rope swing!");
		}
		break;

	case 3557:
		if (absX == 2770 && absY == 9579){
			walkingemote(762, 2763, 9579);
			WalkTo(-7, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		} 
		if (absX == 2794 && absY == 9581){
			walkingemote(762, 2794, 9588);
			WalkTo(0, 7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}     
		if (absX == 2805 && absY == 9548){
			walkingemote(762, 2805, 9555);
			WalkTo(0, 7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		} 
		break;
	case 3553:
		if (absX == 2805 && absY == 9555){
			walkingemote(762, 2805, 9548);
			WalkTo(0, -7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		} 
		if (absX == 2763 && absY == 9579){
			walkingemote(762, 2770, 9579);
			WalkTo(7, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		} 
		if (absX == 2794 && absY == 9588){
			walkingemote(762, 2794, 9581);
			WalkTo(0, -7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}  
		break;    

	case 3551:
		if (absX == 2783 && absY == 9588){
			walkingemote(762, 2783, 9581);
			WalkTo(0, -7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2783 && absY == 9581){
			walkingemote(762, 2783, 9588);
			WalkTo(0, 7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2772 && absY == 9566){
			walkingemote(762, 2772, 9559);
			WalkTo(0, -7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2772 && absY == 9559){
			walkingemote(762, 2772, 9566);
			WalkTo(0, 7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2794 && absY == 9548){
			walkingemote(762, 2794, 9555);
			WalkTo(0, 7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}    
		if (absX == 2794 && absY == 9555){
			walkingemote(762, 2794, 9548);
			WalkTo(0, -7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}        
		break;

	case 3565:
		if (absX == 2761 && absY == 9572){
			walkingemote3(2750, 2761, 9575, 0, 3, 500);
			sendMessage("You hop the low wall.");
		}
		if (absX == 2761 && absY == 9575){
			walkingemote3(2750, 2761, 9572, 0, -3, 500);
			sendMessage("You hop the low wall.");
		}
		if (absX == 2776 && absY == 9590){
			walkingemote3(2750, 2779, 9590, 3, 0, 500);
			sendMessage("You hop the low wall.");
		}
		if (absX == 2779 && absY == 9590){
			walkingemote3(2750, 2776, 9590, -3, 0, 500);
			sendMessage("You hop the low wall.");
		}    
		if (absX == 2805 && absY == 9564){
			walkingemote3(2750, 2805, 9561, 0, -3, 500);
			sendMessage("You hop the low wall.");
		}
		if (absX == 2805 && absY == 9561){
			walkingemote3(2750, 2805, 9564, 0, 3, 500);
			sendMessage("You hop the low wall.");
		}
		if (absX == 2783 && absY == 9561){
			walkingemote3(2750, 2783, 9564, 0, 3, 500);
			sendMessage("You hop the low wall.");
		}
		if (absX == 2783 && absY == 9564){
			walkingemote3(2750, 2783, 9561, 0, -3, 500);
			sendMessage("You hop the low wall.");
		}    
		break;

	case 3578:
		if (absX == 2805 && absY == 9577){
			walkingemote(2750, 2805, 9570);
			WalkTo(0, -7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2805 && absY == 9570){
			walkingemote(2750, 2805, 9577);
			WalkTo(0, 7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2785 && absY == 9568){
			walkingemote(2750, 2792, 9568);
			WalkTo(7, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2792 && absY == 9568){
			walkingemote(2750, 2785, 9568);
			WalkTo(-7, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2761 && absY == 9548){
			walkingemote(2750, 2761, 9555);
			WalkTo(0, 7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2761 && absY == 9555){
			walkingemote(2750, 2761, 9548);
			WalkTo(0, -7);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		break;

	case 3561:
		if (absX == 2803 && absY == 9546){
			walkingemote(756, 2795, 9546);
			WalkTo(-8, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2770 && absY == 9546){
			walkingemote(756, 2762, 9546);
			WalkTo(-8, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2770 && absY == 9590){
			walkingemote(756, 2762, 9590);
			WalkTo(-8, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		break;

	case 3559:
		if (absX == 2796 && absY == 9546){
			walkingemote(754, 2804, 9546);
			WalkTo(8, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2763 && absY == 9546){
			walkingemote(754, 2771, 9546);
			WalkTo(8, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2763 && absY == 9590){
			walkingemote(754, 2771, 9590);
			WalkTo(8, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		break;

	case 3564:
		if (absX == 2794 && absY == 9558){
			walkingemote(744, 2794, 9567);
			WalkTo(0, 9);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}    
		if (absX == 2794 && absY == 9567){
			walkingemote(744, 2794, 9558);
			WalkTo(0, -9);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}    
		if (absX == 2773 && absY == 9546){
			walkingemote(744, 2782, 9546);
			WalkTo(9, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2782 && absY == 9546){
			walkingemote(744, 2773, 9546);
			WalkTo(-9, 0);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2772 && absY == 9569){
			walkingemote(744, 2772, 9578);
			WalkTo(0, 9);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		if (absX == 2772 && absY == 9578){
			walkingemote(744, 2772, 9569);
			WalkTo(0, -9);
			addSkillXP(playerLevel[16]*12*rate, 16);
		}
		break;
		
	case 9330: //east log ardy
		if (absX == 2602 && absY == 3336) {
			walkingemote(762, 2598,3336);
			WalkTo(-4, 0);
			addSkillXP(10*rate, 16);
		}
		break;
		
	case 9328: //west log ardy
		if (absX == 2598 && absY == 3336) {
			walkingemote(762, 2602,3336);
			WalkTo(4, 0);
			addSkillXP(10*rate, 16);
		}
		break;

	case 3583: //
	if (absX == 2759 && absY == 9559) {
		walkingemote(853, 2759, 9567);
		WalkTo(0, 8);
		addSkillXP(playerLevel[16]*12*rate, 16);
	}
	if (absX == 2759 && absY == 9566) {
		walkingemote(854, 2759, 9558);
		WalkTo(0, -8);
		addSkillXP(playerLevel[16]*12*rate, 16);
	}
	if (absX == 2785 && absY == 9544) {
		walkingemote(854, 2793, 9544);
		WalkTo(8, 0);
		addSkillXP(playerLevel[16]*12*rate, 16);
	}
	if (absX == 2792 && absY == 9544) {
		walkingemote(853, 2784, 9544);
		WalkTo(-8, 0);
		addSkillXP(playerLevel[16]*12*rate, 16);
	}
	if (absX == 2792 && absY == 9592) {
		walkingemote(854, 2785, 9592);
		WalkTo(-8, 0);
		addSkillXP(playerLevel[16]*12*rate, 16);
	}
	if (absX == 2785 && absY == 9592) {
		walkingemote(853, 2792, 9592);
		WalkTo(8, 0);
		addSkillXP(playerLevel[16]*12*rate, 16);
	}
	break;

	case 154: //west pipe
		xpgiven = playerLevel[16]*11*rate;
		if (absX == 2484 && absY == 3430) {
			walkingemote(746, 2484, 3437);
			WalkTo(0, 7);
			addSkillXP(xpgiven, 16);
		}
		break;

	case 4058: //east pipe
		xpgiven = playerLevel[16]*11*rate;
		if (absX == 2487 && absY == 3430) {
			walkingemote(746, 2487, 3437);
			WalkTo(0, 7);
			addSkillXP(xpgiven, 16);
		}
		break;
				
		//end of Dangt351s agility FIXED by AAA Mods




		//Bank booth
	case 4483:
	case 14367:
	case 11338:
	case 2213:
	case 9480: 
		openUpBank(); 
		break;

	case 4157:
		if (deadtele == 1){
			deadreturn();
		}
		else{
			sendMessage("Speak with Skulgrimen before leaving!");
		}
		break;

		//Search banana tree
	case 2073:
	case 2074:
	case 2075:
	case 2076:
	case 2077:
	case 2078:
		if(addItem(1963, 1))
			sendMessage("You pick a Banana.");
		break;


	case 5488:
		if(objectX == 3097 && objectY == 3468) // edgeville trapdoor to dungeon
		{
			teleportToX = 3096;
			teleportToY = 9867;
		}
		else {
			teleportToX = absX;
			teleportToY = (absY + 6400);
		}
		break;

		/*case 2514:
ReplaceObject(objectX,objectY,2559, -2);
break;*/


	case 6702:// stair ids in sarcofs
			teleportToX = 3567;
			teleportToY = 3289;
			heightLevel = 0;
		break;

	case 6703:
			teleportToX = 3576;
			teleportToY = 3298;
			heightLevel = 0;
		break;

	case 6704:
			teleportToX = 3577;
			teleportToY = 3281;
			heightLevel = 0;
		break;

	case 6705:
			teleportToX = 3564;
			teleportToY = 3275;
			heightLevel = 0;
		break;

	case 6706:
			teleportToX = 3555;
			teleportToY = 3282;
			heightLevel = 0;
		break;

	case 6707:
		if(isInArea(3577,9703,3578,9706))
			teleport(3558,3298);
		break;

	case 8966:
		teleport(3024,3206);
		break;

	case 8958:
		if(isInArea(2492,10162,2493,20164))
			teleport(2489,10163);

		if(isInArea(2489,10162,2490,10164))
			teleport(2492, 10163);
		break;

	case 8959:
		if(isInArea(2489,10146,2490,10148))
			teleport(2492, 10147);
		if(isInArea(2492,10146,2493,10148))
			teleport(2489,10147);
		break;

	case 8960:
		if(isInArea(2489,10130,2490,10132))
			teleport(2492, 10131);
		if(isInArea(2492,10130,2493,10132))
			teleport(2489,10131);
		break;


	case 2079:
		if (bandit >= 10 && freeSlots() >= 1) // Quest Chest
		{
			addItem (4197, 1);
			eastergift = 3;
			sendMessage("You open the chest and grab the Ghost's Head, you then teleport away.");
			teleportToX = 3506;
			teleportToY = 3315;
			heightLevel = 0;
			sendMessage("Talk to the Ghost Gardener to return his head.");
			bandit = 0;
		}

		else if (bandit < 10){
			sendMessage("Kill 10 bandits, then you can open the chest!");
		}
		break;

		//	End of Barrows by AAA Mods

	case 2918:
		if(objectX == 2799 && objectY == 9341)
		{
			teleportToX = 2790;
			teleportToY = 9340;
			sendMessage("You crawl through the crack in the rock.");
		}
		break;

	case 9707:
		teleportToX = 3105;
		teleportToY = 3956;
		sendMessage("You teleport inside...");
		break;

	case 9706:
		teleportToX = 3105;
		teleportToY = 3951;
		sendMessage("You teleport inside...");
		break;

	case 2558:   // Pirate Hut Doors
		ReplaceObject(objectX,objectY,objectID+2, 0);
		break;

	case 2621:
		if(objectX == 2764 && objectY == 3197)
		{
			ReplaceObject(objectX,objectY,1531, -1);
		}
		else
		{
			ReplaceObject(objectX,objectY,1531 , -1);
		}
		break;

	case 398:
		sendMessage("You check the coffin, it's empty.");
		break;

		//case 1533:
		//ReplaceObject(objectX,objectY,6951, -1);
		//break;

		//man123
		//Door Delete / door remove

	case 12816:
	case 12817:
		sendMessage("A sign on the gate reads: 'Under Construction.'");
		break;

	case 1728:
		if (objectY == 9497)
		{
			sendMessage("You climb down the stairs, and stand on a trap!");
			teleportToX = 2636;
			teleportToY = 9517;
			hitDiff = 10 + misc.random(5);;
			currentHealth -= hitDiff;
			requirePlayerUpdate();
		}
		break;				
		
		

	case 3945: //gates to Tirannwn from northwest of ardy
	case 3944:
		selectOptionTravel2("Enter Tirannwn? [PVP]", "Yes - PVP", 2385,3330, "No - Safe", -1, -1);
		break;

	case 3223: 
		if(isInArea(2300,9910,2310,9920)){ //underground pass exit to pvp
			selectOptionTravel2("Exit to Tirannwn? [PVP]", "Yes - PVP", 2312,3217, "No - Safe", -1, -1);
			break;
		}
		teleport(2418,9674,0); //exit slayer caves to underground pass
		break;
	case 3264: //underground pass to slayer caves
		teleport(2336,9794,0);
		break;
		

	case 411:
	case 409:
		sendMessage("You restore all your prayer points.");
		setAnimation(645);
		playerLevel[5] = getLevelForXP(playerXP[5]);
		sendFrame126(""+playerLevel[5]+"", 4012);
		requirePlayerUpdate();
		break;

		//Mining by AAA Mods
	case 2491: //Rune ess
		if(isInArea(2924,4847,2930,4853) || isInArea(2890,4846,2896,4852) || isInArea(2926,4813,2932,4819) || isInArea(2892,4811,2898,4817))
			this.MINE.mineRock(objectID, 0, objectX, objectY);
		break;
	case 9709:
	case 9710:
	case 9708:
	case 2091: //Copper Ore
		this.MINE.mineRock(objectID, 0, objectX, objectY);
		break;
	case 9711:
	case 9713:
	case 2094: //Tin
		this.MINE.mineRock(objectID, 0, objectX, objectY);
		break;

	case 9717:
	case 9719:
	case 9718:
	case 2093: //Iron Ore
		this.MINE.mineRock(objectID, 15, objectX, objectY);
		break;

	case 9720:
	case 9722:
	case 2099:
	case 2098:
	case 11184: //Gold Ore
		this.MINE.mineRock(objectID, 40, objectX, objectY);
		break;

	case 2097: //Coal Ore
		this.MINE.mineRock(objectID, 40, objectX, objectY);
		break;

	case 2103: //Mithril Ore
		this.MINE.mineRock(objectID, 55, objectX, objectY);
		break;

	case 2104:
	case 2105: //Adamantite
		this.MINE.mineRock(objectID, 70, objectX, objectY);	
		break;

	case 2107: //Runite Ore
		this.MINE.mineRock(objectID, 85, objectX, objectY);	
		break;
		//	End of mining by AAA Mods

	case 4150: 
			isteleporting(3024, 3206, 0);
		break;

	case 2484:
		stillgfx(246, absY, absX);
		setAnimation(791);
		AnimDelay = 20;
		break;

	case 2027:
	case 2630: //lava
	case 44:
	case 2031:
	case 42:
	case 2029:
	case 2028:
	case 8986:
	case 3032:
	case 2030: //Fishing first clicks
		Fishing.fishingSwitch(objectID, objectX, objectY, face, face2, GateID, this);
		break;

		//WC by aaa mods
	case 10041:
	case 1315:
	case 1316:
	case 1282:
	case 1289:
	case 1277:
	case 1279:
	case 1280:
	case 1283:
	case 1284:
	case 1285:
	case 1286:
	case 1287:
	case 1288:
	case 1290:
	case 1291:
	case 1278:
	case 1276:
		this.WC.cutTree(objectID, 1, objectX, objectY);
		break;

	case 10083:
	case 3037:
	case 1281: //Oak Tree
		this.WC.cutTree(objectID, 15, objectX, objectY);
		break;

	case 5552:
	case 5553:
	case 5554:
	case 5551:
	case 1308: //Willow
		this.WC.cutTree(objectID, 30, objectX, objectY);
		break;

	case 4674:
	case 1307: //Maple
		this.WC.cutTree(objectID, 45, objectX, objectY);
		break;

	case 1309: //Yew Tree
		this.WC.cutTree(objectID, 60, objectX, objectY);
		break;

	case 1306: //Magic Tree
		this.WC.cutTree(objectID, 75, objectX, objectY);	
		break;
		//WC by aaa mods

	case 5162: //Chest
		if(playerLevel[17] >= 30 && wb == 5  && freeSlots() >=3) {
			sendMessage("You destroy the Fire Orb");
			sendMessage("You grab some other items.");
			Menu(this.menuHandler.wbFinish());
			addItem(14497, 1);
			addItem(14499, 1);
			addItem(14501, 1);
			addSkillXP(100000, 17); //thief
			addSkillXP(50000, 2);  //str
			addSkillXP(40000, 16); //agi
			addSkillXP(20000, 12); //crafting
			wb = 6;
			isteleporting(3303, 9378, 0);
			loadquestinterface();
		}
		else if(playerLevel[17] >= 30 && wb == 5  && freeSlots() < 3) {
			sendMessage("You need 3 free inventory slots!");
		}
		else if (playerLevel[17] < 30 && wb == 5){
			sendMessage("You're to clutsy to open the chest,");
			sendMessage("30 Thieving is required to open it.");
		}
		else if (wb == 6 || wb < 5){
			sendMessage("Empty!");
			teleportToX = 3303;
			teleportToY = 9378;
			heightLevel = 0;
			updateRequired = true; 
			appearanceUpdateRequired = true;
		}
		break;

	case 4868:
		isteleporting(3069, 3859, 0);
		break;


	case 1767:
		if (IsItemInBag(85) == true){
			sendMessage("You climb down.");
			isteleporting3(828, 15, 2590, 4497, 0);
			deleteItem(85, getItemSlot(85), 1);
		}
		else if (IsItemInBag(85) == false){
			sendMessage("I need to find the shiny key to enter.");
		}
		break;

	case 75:
		if (IsItemInBag(85) == false && freeSlots() >=1){
			sendMessage("You take a key from the chest.");
			addItem(85, 1);
		}
		else if (IsItemInBag(85) == true){
			sendMessage("The chest is empty!");
		}
		else if (freeSlots() < 1){
			sendMessage("I need more inventory space.");
		}
		break;


	case 10284: //barrows chest
		if(ahrim == 1 || dharok == 1 || torag == 1 || karil == 1 || guthan == 1 || verac == 1){
			if(freeSlots() >= 3){
				
				@SuppressWarnings("unchecked")
				LinkedList<Drop> originalDrop = (LinkedList<Drop>)this.DROPHANDLER.barrowsDrop.clone();
				
				if(ahrim == 1)
					this.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.ahrims));
				if(dharok == 1)
					this.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.dharoks));
				if(torag == 1)
					this.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.torags));
				if(karil == 1)
					this.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.karils));
				if(guthan == 1)
					this.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.guthans));
				if(verac == 1)
					this.DROPHANDLER.barrowsDrop.add(new Drop(1, DropList.veracs));
				
				int item1 = this.DROPHANDLER.getDrop(this.DROPHANDLER.barrowsDrop);
				int item2 = this.DROPHANDLER.getDrop(this.DROPHANDLER.barrowsDrop);
				int item3 = this.DROPHANDLER.getDrop(this.DROPHANDLER.barrowsDrop);
				
				int item1Amount = 1;
				int item2Amount = 1;
				int item3Amount = 1;
				
				if(Item.itemStackable[item1])
					item1Amount = misc.random(150)+150;
				
				if(Item.itemStackable[item2])
					item2Amount = misc.random(150)+150;
				
				if(Item.itemStackable[item2])
					item2Amount = misc.random(150)+150;
				
				addItem(item1,item1Amount);
				addItem(item2,item2Amount);
				addItem(item3,item3Amount);
				
				sendMessage("You reach in and grab a few items.");
				teleport(3565,3312);
				ahrim = 0;
				dharok = 0;
				torag = 0;
				karil = 0;
				guthan = 0;
				verac = 0;
				this.DROPHANDLER.barrowsDrop = originalDrop;
			}
			else sendMessage("You need at least 3 empty inventory slots to do that.");	
		}
		else sendMessage("You need to kill at least one brother to loot this chest.");
		break;
		
	case 6771: //barrows coffin
	case 6823: 
	case 6821: 
	case 6773: 
	case 6822: 
	case 6772: 
		switch(misc.random(2)){
		case 0:
			teleport(3534,9712);
			break;

		case 1:
			teleport(3534,9677);
			break;

		case 2:
			teleport(3569,9677);
			break;

		default:
			teleport(3568,9712);
			break;
		}			

		break;

	case 5167: //Memorial Tomb
		if(playerLevel[2] >= 60) {
			isteleporting3(1115, 12, 3208, 9378, 0);
			setAnimation(1115);
			isteleporting = 12;
			isteleportingx = 3208;
			isteleportingy = 9378;
			updateRequired = true; 
			appearanceUpdateRequired = true;
		}
		else if (playerLevel[2] < 60){
			sendMessage("You're not strong enough to push the memorial.");
			sendMessage("60 Strength is required to move it.");
		}
		break;

	case 5159: //Cross Bridge
		if(playerLevel[2] >= 60 && playerLevel[16] >= 50) {
			isteleporting3(1115, 12, 3504, 3563, 0);
		}
		else if (playerLevel[2] < 60 || playerLevel[16] < 50){
			sendMessage("You need 60 strength and 50 agility to");
			sendMessage("jump the broken bridge!");
		}
		break;

	case 5160: //Cross Bridge
		if(playerLevel[2] >= 60 && playerLevel[16] >= 50) {
			isteleporting3(1115, 12, 3504, 3563, 0);
		}
		else if (playerLevel[2] < 60 || playerLevel[16] < 50){
			sendMessage("You need 60 strength and 50 agility to");
			sendMessage("jump the broken bridge!");
		}
		break;

	case 6439: // Back to Moseluem
		isteleporting(3504, 3571, 0);
		break;

	case 11356: // Back to Main Isle
		if(teleblock) 
		{
			sendMessage("You are currently teleblocked !");
		}
		else if (!teleblock)
		{
			isteleporting(3024, 3206, 0);
		}
		break;

	case 13616:
		isteleporting(3029, 3852, 0);
		break;

	case 13619:
		isteleporting(2661, 3306, 0);
		break;

	case 2674:
	case 2673:
		sendMessage("You can't leave the god wars this way!");
		break;

	case 2471:
	case 2468:
	case 4152: // Back to Main Isle
		if(teleblock) 
		{
			sendMessage("You are currently teleblocked !");
		}
		else if (!teleblock)
		{
			isteleporting(3024, 3206, 0);
		}
		break;

	case 12260:
		if (playerLevel[18] >= 85){
			isteleporting(2512, 4643, 0);
		}
		else if (playerLevel[18] < 85){
			sendMessage("You need at least 85 slayer to enter this portal.");
		}
		break;

	case 7322: // Return
		isteleporting(3504, 3554, 0);
		break;

	case 2407:
		teleport(2887,3350);
		break;

		/*case 1734:
println_debug("going up");
teleportToX = absX;
teleportToY = (absY - 6400);
break;*/
		default:
			if(lists.grownList.exists(objectID)){
				this.FARM.guide(objectX, objectY, objectID);
				return;
			}

			if(lists.doorOpen.exists(objectID)){
				//deletethatobject(objectX, objectY);
				ReplaceObject(objectX,objectY,6951, -1);
				return;
			}
			break;
	}
}

/*OBJECT CLICK TWO*/

public boolean spinning = false;

public void objectClick2(int objectID, int objectX, int objectY) {
		debug("atObject2: "+objectX+","+objectY+" objectID: "+objectID); 

	if(isObjSpamming()) return;
	objtimer = System.currentTimeMillis();

	if(lists.grownList.exists(objectID) || lists.growingList.exists(objectID) || lists.patchList.exists(objectID) || lists.brushList.exists(objectID) || lists.inspectInfoList.exists(objectID) || lists.deadPlantList.exists(objectID)){
		this.FARM.inspectInfo(objectID,objectX,objectY);
		return;
	}

	switch(objectID) {
	
	case 2884:
		if(objectX == 2466 && objectY == 3495)
			teleport(2466,3494,3);
		break;
	
	case 11889:
		if(objectX == 2907 && objectY == 3334 && heightLevel == 1)
			teleport(2908,3336,2);
		break;
		
	case 1748:
		if(objectX == 2466 && objectY == 3495)
			teleport(2466,3494,2);
		break;

	case 2646: //flax
		if(addItem(1779,1)){
			new RespawnObject(this, 2646, 6951, objectX, objectY, 15);
			sendMessage("You pick some flax.");
			startAnimation(1768);
		}
		break;
		
	case 2644: //gnome spinning wheel
	case 8748: //spinning wheel
		spinning = true;
			selectoption("Options", "Make all Bowstrings", "Cancel", "...");
		break;
		
	//Fishing second clicks
	case 2030:
	case 2031:
	case 2027:
	case 2029: 
		Fishing.fishingSwitch2(objectID, objectX, objectY, this);
		break;	
		//Fishing

	case 2561:
		Thieving.stalls(1, 200, DROPHANDLER.getDrop(DROPHANDLER.food), 3000, this);
		break;

	case 2563:
	case 2560:
		Thieving.stalls(10, 150, 6814, 4000, this);
		break;

	case 2562:
		Thieving.stalls(35, 500, DROPHANDLER.getDrop(DROPHANDLER.gems), 3500, this);
		break;


	case 4277:
		Thieving.stalls(50, 800, DROPHANDLER.getDrop(DROPHANDLER.fish1), 4000, this);
		break;

	case 4874:
		Thieving.stalls(70, 1000, DROPHANDLER.getDrop(DROPHANDLER.gems), 4500, this);
		break;

	case 2213:
	case 2214:
	case 3045:
	case 5276:
	case 6084:
	case 11758:
	case 9480:
		openUpBank(); 
		break;

	}
}

/*OBJECT CLICK THREE*/

public void objectClick3(int objectID, int objectX, int objectY) {
		debug("atObject3: "+objectX+","+objectY+" objectID: "+objectID);

	if(isObjSpamming()) return;
	objtimer = System.currentTimeMillis();

	switch (objectID) {			
	case 2884:
		if(objectX == 2466 && objectY == 3495)
			teleport(2466,3494,1);
		break;
	case 11889:
		if(objectX == 2907 && objectY == 3334 && heightLevel == 1)
			teleport(2908,3336,0);
		break;
	case 1748:
		if(objectX == 2466 && objectY == 3495)
			teleport(2466,3494,0);
		break;
	
	case 1739:
		heightLevel--;
		break;

	}
}

public void objectClick4(int objectID, int objectX, int objectY) {
		debug("atObject4: "+objectX+","+objectY+" objectID: "+objectID);

	if(isObjSpamming()) return;
	objtimer = System.currentTimeMillis();

	if(lists.growingList.exists(objectID) || lists.grownList.exists(objectID) || lists.guideList.exists(objectID) || 
			lists.brushList.exists(objectID) || lists.deadPlantList.exists(objectID) || lists.patchList.exists(objectID)){
		this.FARM.guide(objectX, objectY, objectID);
		return;
	}

	switch (objectID) {			
	default:
		debug("Unhandled objectID in objectClick4 : "+objectID);
		break;
	}
}

/*TELEOTHER*/
public void teleOtherRequest(String teleLocation, int player) {

String telePlayer = server.playerHandler.players[player].playerName;
sendQuest(telePlayer, 12558);
sendQuest(teleLocation, 12560);
showInterface(12468);

teleReq = player;
teleLoc = teleLocation;

teleOtherScreen = false;

}

public void DeleteArrow()
{
 if( playerEquipmentN[playerArrows]-1 <= 0)
  deleteequiment(playerEquipment[playerArrows], playerArrows);
 
 if(playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0){
  outStream.createFrameVarSizeWord(34);
  outStream.writeWord(1688);
  outStream.writeByte(playerArrows);
  outStream.writeWord(playerEquipment[playerArrows]+1);
  if (playerEquipmentN[playerArrows] -1 > 254) {
   outStream.writeByte(255);
   outStream.writeDWord(playerEquipmentN[playerArrows] -1);
  }
  else {
   outStream.writeByte(playerEquipmentN[playerArrows] -1); //amount	
  }
  outStream.endFrameVarSizeWord();
  playerEquipmentN[playerArrows] -= 1;
 }  
  updateRequired = true; 
  appearanceUpdateRequired = true;
}

public boolean isInMultiCombat(){
	if(isInGodWars())
		return true;
	return false;
}

public boolean isInGodWars(){
	return (isInArea(2576,3586,2821,3717) || isInArea(2773,3553,2935,3653));
}

public void createAreaDisplayType(){
	if(isInGodWars()){
		outStream.createFrame(208); 
		outStream.writeWordBigEndian_dup(11479);
		sendQuest("Bandos Kills: "+bandos+"   Armadyl Kills: "+armadyl, 11480);
		return;
	}
	if(isInPKZone()){
		outStream.createFrame(208); 
		outStream.writeWordBigEndian_dup(197);
		sendQuest("@red@PK", 199);
		return;
	}
	if(isInArea(2621, 2557, 2689, 2622) || isInArea(2660,2638,2663,2643)) return;
	outStream.createFrame(208); 
	outStream.writeWordBigEndian_dup(197);
	sendQuest("@gre@Safe", 199);
}

public void checkWildRange(int pcombat){
	if(((combat + WildyLevel >= pcombat) && (pcombat >= combat)) || ((combat - WildyLevel <= pcombat) && (pcombat <= combat)))
		inWildRange = true;
	else inWildRange = true; //default false
}

public void Teleblock()
{
teleblock = true;
sendMessage("A teleblock has been cast on you!");
stillgfx(345, absY, absX);
}

	public void setSetting(int settingID, int value) {	// Xero: Yay I'm second, thx to Phate for helping  
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}

    public void createProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
                int startHeight, int endHeight, int lockon) {
    	for (Player p : server.playerHandler.players) {
    		if(p != null){
    			if(p.isInArea(casterX, casterY, casterX+20,casterY+20)){
      			client g = (client) p;
                g.outStream.createFrame(85);
                g.outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
                g.outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
                g.outStream.createFrame(117);
                g.outStream.writeByte(angle);                     //Starting place of the projectile
                g.outStream.writeByte(offsetY);               //Distance between caster and enemy Y
                g.outStream.writeByte(offsetX);                //Distance between caster and enemy X
                g.outStream.writeWord(lockon);        //The NPC the missle is locked on to
                g.outStream.writeWord(gfxMoving);             //The moving graphic ID
                g.outStream.writeByte(startHeight);           //The starting height
                g.outStream.writeByte(endHeight);             //Destination height
                g.outStream.writeWord(51);                        //Time the missle is created
                g.outStream.writeWord(speed);                     //Speed minus the distance making it set
                g.outStream.writeByte(16);                        //Initial slope
                g.outStream.writeByte(64);                        //Initial distance from source (in the direction of the missile) //64    
    			}
    		}
    	}
    }
	public void createProjectilez(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
		int startHeight, int endHeight, int Lockon, boolean MagingNPC) {
		outStream.createFrame(85);
		outStream.writeByteC(casterY - 2);	//  Phate:	seems to take a couple off?
		outStream.writeByteC(casterX - 3);	//  Phate:	seems to take 3 off?
		outStream.createFrame(117);
		outStream.writeByte(angle);			//	Phate:	Angle? I think
		outStream.writeByte(offsetX);	//	Phate:	OffsetY in relevance from the first player
		outStream.writeByte(offsetY);	//	Phate:	OffsetX in relevance from the first player
                if(MagingNPC)
		outStream.writeWord(Lockon);
                else
                outStream.writeWord(-Lockon);
		outStream.writeWord(gfxMoving);		//	Phate:	Magic Moving Graphic ID
		outStream.writeByte(startHeight);	//	Phate:	Starting height
		outStream.writeByte(endHeight);		//	Phate:	Finishing height
		outStream.writeWord(51);			//	Phate:	No idea?
		outStream.writeWord(speed);			//	Phate:	Speed of Moving Magic
		outStream.writeByte(16);			//	Phate:	Something static? Doesnt change a lot..
		outStream.writeByte(64);			//	Phate:	Type of shot possibly? All shoots seemed to be 64
		flushOutStream();
	}

public boolean HasItemAmount(int itemID, int itemAmount) {
	int playerItemAmountCount = 0;
	for (int i=0; i<playerItems.length; i++)
	{
		if (playerItems[i] == itemID+1)
		{
			playerItemAmountCount = playerItemsN[i];
		}
		if(playerItemAmountCount >= itemAmount){
			return true;}
	}
	return false;
}

public void replaceAllItemsOfTypeWith(int itemID, int withID){
	if(!Item.itemStackable[itemID+1]){
		for(int i = 0; i < playerItems.length; i++){
			if(playerItems[i] == itemID+1)
				playerItems[i] = withID+1;
		}
	}
	else{
		int amount = 0;
		int slot = 0;
		for(int i = playerItems.length-1; i >= 0; i--){
			if(playerItems[i] == itemID+1){
				slot = i;
				amount += playerItemsN[i];
				playerItems[i] = 0;
				playerItemsN[i] = 0;
			}
		}
		playerItems[slot] = withID+1;
		playerItemsN[slot] = amount;
	}
	for(int i = 0; i < playerItems.length; i++){
		outStream.createFrameVarSizeWord(34);
		outStream.writeWord(3214);
		outStream.writeByte(i);
		outStream.writeWord(playerItems[i]);
		if (playerItemsN[i] > 254) {
			outStream.writeByte(255);
			outStream.writeDWord(playerItemsN[i]);
		} else {
			outStream.writeByte(playerItemsN[i]); //amount	
		}
		outStream.endFrameVarSizeWord();
	}
}


    public boolean Has2Items(int itemID, int amount, int itemID2, int amount2){
   if(HasItemAmount(itemID, amount)) {
      if(HasItemAmount(itemID2, amount2)){
         return true;
      } else{ return false; }
   } else{ return false; }
    }

    public boolean Has3Items(int itemID, int amount, int itemID2, int amount2, int itemID3, int amount3){
   if(HasItemAmount(itemID, amount)){
      if(HasItemAmount(itemID2, amount2)){
         if(HasItemAmount(itemID3, amount3)){
      return true;
         } else{ return false; }
      } else{ return false; }
   } else{ return false; }
    }

   


    public void teleport(int x, int y, int h, int xp, int lvl){
   teleportToX = x;
   teleportToY = y;
   heightLevel = h;
   addSkillXP(xp, lvl);
    }

public void TeleToAdvanced(String cityName, int lvl, String type) {
      if(playerLevel[playerMagic] >= lvl){
      String line = "";
      String token = "";
      String token2 = "";
      String token2_2 = "";
      String[] token3 = new String[25];
      boolean EndOfFile = false;
      int ReadMode = 0;
      BufferedReader characterfile = null;
      try {
         characterfile = new BufferedReader(new FileReader("Teleport.cfg"));
      } catch(FileNotFoundException fileex) {
         misc.println("Teleport.cfg: not found.");
         
      }
      try {
         line = characterfile.readLine();
      } catch(IOException ioexception) {
         misc.println("Teleport.cfg: error loading file.");
         
      }
      while(EndOfFile == false && line != null) {
         line = line.trim();
         int spot = line.indexOf("=");
         if (spot > -1) {
            token = line.substring(0, spot);
            token = token.trim();
            token2 = line.substring(spot + 1);
            token2 = token2.trim();
            token2_2 = token2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token3 = token2_2.split("\t");
            int RandomNum = 0;
         
   if (token.equals("Tele")) {
            String city = token3[0];
if(type.equalsIgnoreCase("cmd")){RandomNum = 1;}
if(type.equalsIgnoreCase("mage")){RandomNum = misc.random(25);}
            int RTID = Integer.parseInt(token3[1]);
            int MLvl = Integer.parseInt(token3[2]);
            int RuneTypes = Integer.parseInt(token3[3]);
            int Rune1 = Integer.parseInt(token3[4]);
            int Rune2 = Integer.parseInt(token3[5]);
            int Rune3 = Integer.parseInt(token3[6]);
            int Item = Integer.parseInt(token3[7]);
            int TeleX = Integer.parseInt(token3[8]);
            int TeleY = Integer.parseInt(token3[9]);
            int Height = Integer.parseInt(token3[10]);
            int Xp = Integer.parseInt(token3[11]);
            int R1Amt = Integer.parseInt(token3[12]);
            int R2Amt = Integer.parseInt(token3[13]);
            int R3Amt = Integer.parseInt(token3[14]);
            int ItmAmt = Integer.parseInt(token3[15]);
      
if (cityName.equalsIgnoreCase(city)){ 
   if(RandomNum == RTID){

if(type.equalsIgnoreCase("cmd")){
teleport(TeleX, TeleY, Height, Xp, playerMagic);
}
      if(Item == -1){
         if(RuneTypes == 2){
            if(Has2Items(Rune1, R1Amt, Rune2, R2Amt)){
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R2Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
            if(RuneTypes == 3){
            if(Has3Items(Rune1, R1Amt, Rune2, R2Amt, Rune3, R3Amt)){
               
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R2Amt);
               deleteItem(Rune3, GetItemSlot(Rune3), R3Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);
   
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
         }else{
            if(RuneTypes == 2){
            if(Has2Items(Rune1, R1Amt, Rune2, R2Amt)){             if(HasItemAmount(Item, ItmAmt)){
               
               deleteItem(Item, GetItemSlot(Item), ItmAmt);
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R1Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);
            
            }else{
sendMessage("You need a "+getItemName(Item)+" to do that.");}
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
            if(RuneTypes == 3){
            if(Has3Items(Rune1, R1Amt, Rune2, R2Amt, Rune3, R3Amt)){             if(HasItemAmount(Item, ItmAmt)){
               
               deleteItem(Item, GetItemSlot(Item), ItmAmt);
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R1Amt);
               deleteItem(Rune3, GetItemSlot(Rune3), R3Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);

            }else{
sendMessage("You need a "+getItemName(Item)+" to do that.");}
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
      }   
   }
}         
   }

                  
         } else {
            if (line.equals("[ENDOFTELELIST]")) {
               try { characterfile.close(); } catch(IOException ioexception) { }
               
            }
         }

         try {
            line = characterfile.readLine();
         } catch(IOException ioexception1) { EndOfFile = true; }
      }
      try { characterfile.close(); } catch(IOException ioexception) { }
      }else{ sendMessage("You need level "+lvl+" to use this spell.");}
   } 

	public boolean playerHasItem(int itemID)
	{
		for (int i=0; i <playerItems.length; i++)
		{
			if (playerItems[i] == itemID+1)
			{
				return true;
			}
		}
		return false;

	}

public boolean hasItemAny(int id, int amount) {
   for(int i = 0; i < playerItems.length; i++) {
    if(playerItems[i] == id+1 && playerItemsN[i] >= amount)
     return true;
  }
   for(int i2 = 0; i2 < playerBankSize; i2++) {
    if(bankItems[i2] == id+1 && bankItemsN[i2] >= amount)
     return true;
  }
  return false;
 }
public void ReplaceItems(int newID, int oldID, int newAmount, int oldAmount) {
  for(int i = 0; i < playerItems.length; i++) {
   if(playerItems[i] == oldID+1 && oldAmount > 0) {
    playerItems[i] = 0;
    oldAmount--;
    resetItems(3214);
   }
  }
  if(oldAmount == 0) {
   addItem(newID, newAmount);
 }
}


	public boolean hasItem(int itemID, int slot)
	{
			if (playerItems[slot] == itemID)
			{
				return true;
			}
		return false;
	}
	public int getItemSlotReturn(int itemID)
	{
		for (int slot=0; slot < playerItems.length; slot++)
		{
			if (playerItems[slot] == (itemID +1))
			{
				return slot;
			}
		}
		return -1;
	}


public Calendar cal = new GregorianCalendar();
   public int hour12 = cal.get(Calendar.HOUR);            // 0..11
   public int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
   public int min = cal.get(Calendar.MINUTE);             // 0..59
   public int sec = cal.get(Calendar.SECOND);             // 0..59
   public int ms = cal.get(Calendar.MILLISECOND);         // 0..999
   public int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM
  public void getTime()
{
sendMessage("hour: "+hour24+" mins: "+min+" secs: "+sec);
}

public void setInterfaceWalkable(int ID){

			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(ID);
			flushOutStream();}

public void setTime(){
if(morningTime){setInterfaceWalkable(65535);}

if(afternoonTime){setInterfaceWalkable(12416);}

if(eveningTime){setInterfaceWalkable(12418);}

if(nightTime){setInterfaceWalkable(12414);}}

/* ADD MORE TWO HANDED ITEMS HERE */



public boolean canSmith(int item) {
if(item == 1149 || item == 1305 || item == 7158 || item == 6575 || item == 892 || item == 7806 || item == 13602 || item == 9094 || item == 4151 || item == 5698 || item == 1187 || item == 1377 || item == 1434 || item == 14511 || item == 4587 || item == 14512 || item == 14513 || item == 14514 || item == 3140 || item == 14507 || item == 14508 || item == 14509 && playerLevel[13] >= 90) {
   return true;
}
if(item == 1205 || item == 1351 && playerLevel[13] >= 1) {
   return true;
}
if(item == 1422 && playerLevel[13] >= 2) {
 return true;
}
if(item == 1139 && playerLevel[13] >= 3) {
 return true;
}
if(item == 1277 || item == 819 && playerLevel[13] >= 4) {
 return true;
}
if(item == 1321 || item == 39 &&playerLevel[13] >= 5) {
 return true;
}
if(item == 1291 && playerLevel[13] >= 6) {
 return true;
}
if(item == 1155 || item == 864 &&playerLevel[13] >= 7) {
 return true;
}
if(item == 1173 && playerLevel[13] >= 8) {
 return true;
}
if(item == 1337 && playerLevel[13] >= 9) {
 return true;
}
if(item == 1375 && playerLevel[13] >= 10) {
 return true;
}
if(item == 1103 && playerLevel[13] >= 11) {
 return true;
}
if(item == 1189 && playerLevel[13] >= 12) {
 return true;
}
if(item == 3095 && playerLevel[13] >= 13) {
 return true;
}
if(item == 1307 && playerLevel[13] >= 14) {
 return true;
}
if(item == 1203 && playerLevel[13] >= 15) {
 return true;
}
if(item == 1087  || item == 1075 || item == 1349 && playerLevel[13] >= 16) {
 return true;
}
if(item == 1420 && playerLevel[13] >= 17) {
 return true;
}
if(item == 1117 || item == 1137 && playerLevel[13] >= 18) {
 return true;
}
if(item == 1279 || item == 820 || item == 4820 && playerLevel[13] >= 19) {
 return true;
}
if(item == 1323 || item == 40 && playerLevel[13] >= 20) {
 return true;
}
if(item == 1293 && playerLevel[13] >= 21) {
 return true;
}
if(item == 1153 || item == 863 && playerLevel[13] >= 22) {
 return true;
}
if(item == 1175 && playerLevel[13] >= 23) {
 return true;
}
if(item == 1335 && playerLevel[13] >= 24) {
 return true;
}
if(item == 1363 && playerLevel[13] >= 25) {
 return true;
}
if(item == 1101 || item == 4540 && playerLevel[13] >= 26) {
 return true;
}
if(item == 1191 && playerLevel[13] >= 27) {
 return true;
}
if(item == 3096 && playerLevel[13] >= 28) {
 return true;
}
if(item == 1309 && playerLevel[13] >= 29) {
 return true;
}
if(item == 1207 && playerLevel[13] >= 30) {
 return true;
}
if(item == 1067 || item == 1081 || item == 1353 && playerLevel[13] >= 31) {
 return true;
}
if(item == 1424 && playerLevel[13] >= 32) {
 return true;
}
if(item == 1115 || item == 1141 && playerLevel[13] >= 33) {
 return true;
}
if(item == 1281 || item == 1539 || item == 821 && playerLevel[13] >= 34) {
 return true;
}
if(item == 1325 || item == 41 && playerLevel[13] >= 35) {
 return true;
}
if(item == 1295 || item == 2370 && playerLevel[13] >= 36) {
 return true;
}
if(item == 1157 || item == 865 && playerLevel[13] >= 37) {
 return true;
}
if(item == 1177 && playerLevel[13] >= 38) {
 return true;
}
if(item == 1339 && playerLevel[13] >= 39) {
 return true;
}
if(item == 1365 && playerLevel[13] >= 40) {
 return true;
}
if(item == 1105 && playerLevel[13] >= 41) {
 return true;
}
if(item == 1193 && playerLevel[13] >= 42) {
 return true;
}
if(item == 3097 && playerLevel[13] >= 43) {
 return true;
}
if(item == 1311 && playerLevel[13] >= 44) {
 return true;
}
if(item == 1069 || item == 1083 && playerLevel[13] >= 46) {
 return true;
}
if(item == 1119 && playerLevel[13] >= 48) {
 return true;
}
if(item == 4544 && playerLevel[13] >= 49) {
 return true;
}
if(item == 1209 && playerLevel[13] >= 50) {
 return true;
}
if(item == 1355 && playerLevel[13] >= 51) {
 return true;
}
if(item == 1428 && playerLevel[13] >= 52) {
 return true;
}
if(item == 1143 && playerLevel[13] >= 53) {
 return true;
}
if(item == 1285 || item == 822 || item == 4822 && playerLevel[13] >= 54) {
 return true;
}
if(item == 1329 || item == 42 && playerLevel[13] >= 55) {
 return true;
}
if(item == 1299 && playerLevel[13] >= 56) {
 return true;
}
if(item == 1159 || item == 866 && playerLevel[13] >= 57) {
 return true;
}
if(item == 1181 && playerLevel[13] >= 58) {
 return true;
}
if(item == 1343 && playerLevel[13] >= 59) {
 return true;
}
if(item == 1369 && playerLevel[13] >= 60) {
 return true;
}
if(item == 1109 && playerLevel[13] >= 61) {
 return true;
}
if(item == 1197 && playerLevel[13] >= 62) {
 return true;
}
if(item == 3099 && playerLevel[13] >= 63) {
 return true;
}
if(item == 1315 && playerLevel[13] >= 64) {
 return true;
}
if(item == 1071 || item == 1085 && playerLevel[13] >= 66) {
 return true;
}
if(item == 1121 && playerLevel[13] >= 68) {
 return true;
}
if(item == 1211 && playerLevel[13] >= 70) {
 return true;
}
if(item == 1430 && playerLevel[13] >= 72) {
 return true;
}
if(item == 1145 && playerLevel[13] >= 73) {
 return true;
}
if(item == 1287 || item == 823 || item == 4823 && playerLevel[13] >= 74) {
 return true;
}
if(item == 1331 || item == 43 && playerLevel[13] >= 75) {
 return true;
}
if(item == 1301 && playerLevel[13] >= 76) {
 return true;
}
if(item == 1161 || item == 867 && playerLevel[13] >= 77) {
 return true;
}
if(item == 1183 && playerLevel[13] >= 78) {
 return true;
}
if(item == 1371 && playerLevel[13] >= 79) {
 return true;
}
if(item == 1111 && playerLevel[13] >= 81) {
 return true;
}
if(item == 1199 && playerLevel[13] >= 82) {
 return true;
}
if(item == 3100 && playerLevel[13] >= 83) {
 return true;
}
if(item == 1317 && playerLevel[13] >= 84) {
 return true;
}
if(item == 1213 && playerLevel[13] >= 85) {
 return true;
}
if(item == 1073 || item == 1091 || item == 1359 && playerLevel[13] >= 86) {
 return true;
}
if(item == 1432 && playerLevel[13] >= 87) {
 return true;
}
if(item == 1123 || item == 1147 && playerLevel[13] >= 88) {
 return true;
}
if(item == 1289 || item == 824 || item == 4824 && playerLevel[13] >= 89) {
 return true;
}
if(item == 1333 || item == 44 && playerLevel[13] >= 90) {
 return true;
}
if(item == 1303 && playerLevel[13] >= 91) {
 return true;
}
if(item == 1163 || item == 868 && playerLevel[13] >= 92) {
 return true;
}
if(item == 1185 && playerLevel[13] >= 93) {
 return true;
}
if(item == 1347 && playerLevel[13] >= 94) {
 return true;
}
if(item == 1373 && playerLevel[13] >= 95) {
 return true;
}
if(item == 1113 && playerLevel[13] >= 96) {
 return true;
}
if(item == 1201 && playerLevel[13] >= 97) {
 return true;
}
if(item == 3101 && playerLevel[13] >= 98) {
 return true;
}
if(item == 1319 && playerLevel[13] >= 99) {
 return true;
}
if(item == 1079 && playerLevel[13] >= 99) {
 return true;
}
if(item == 1079 || item == 1093 || item == 1319 || item == 1127 && playerLevel[13] >= 99) {
 return true;
} else {
 return false;
}
//return false;
}

public void addItemToSmith(int id, int slot, int column, int amount)
{
   outStream.createFrameVarSizeWord(34); // init item to smith screen
   outStream.writeWord(column); // Column Across Smith Screen
   outStream.writeByte(4); // Total Rows?
   outStream.writeDWord(slot); // Row Down The Smith Screen
   outStream.writeWord(id+1); // item
   outStream.writeByte(amount); // how many there are?
   outStream.endFrameVarSizeWord();

}


public void initSmithing(int barType)
   {
outStream.createFrame(97);
outStream.writeWord(994);
 if(amountOfItem(barType) < 5) {
  sendQuest("5bars",1112);
 } else {
  sendQuest("5bars",1112);
 }
 if(amountOfItem(barType) < 3) {
  sendQuest("3bars",1109);
  sendQuest("3bars",1110);
  sendQuest("3bars",1118);
  sendQuest("3bars",1111);
  sendQuest("3bars",1095);
  sendQuest("3bars",1115);
  sendQuest("3bars",1090);
 } else {
  sendQuest("3bars",1109);
  sendQuest("3bars",1110);
  sendQuest("3bars",1118);
  sendQuest("3bars",1111);
  sendQuest("3bars",1095);
  sendQuest("3bars",1115);
  sendQuest("3bars",1090);
 }
 if(amountOfItem(barType) < 2) {
  sendQuest("2bars",1113);
  sendQuest("2bars",1116);
  sendQuest("2bars",1114);
  sendQuest("2bars",1089);
  sendQuest("2bars",8428);
 } else {
  sendQuest("2bars",1113);
  sendQuest("2bars",1116);
  sendQuest("2bars",1114);
  sendQuest("2bars",1089);
  sendQuest("2bars",8428);
 }
 if(amountOfItem(barType) < 1) {
  sendQuest("1bar",1125);
  sendQuest("1bar",1126);
  sendQuest("1bar",1127);
  sendQuest("1bar",1124);
  sendQuest("1bar",1128);
  sendQuest("1bar",1129);
  sendQuest("1bar",1130);
  sendQuest("1bar",13357);
  sendQuest("1bar",1131);
  sendQuest("1bar",11459);
 } else {
  sendQuest("1bar",1125);
  sendQuest("1bar",1126);
  sendQuest("1bar",1127);
  sendQuest("1bar",1124);
  sendQuest("1bar",1128);
  sendQuest("1bar",1129);
  sendQuest("1bar",1130);
  sendQuest("1bar",13357);
  sendQuest("1bar",1131);
  sendQuest("1bar",11459);
 }
 
if(barType == 2357) { //Gold Bar
 if(playerLevel[13] < 90) {
  sendQuest("Kite",1101);
 } else {
  sendQuest("Kite",1101);
 }
 if(playerLevel[13] < 90) {
 sendQuest("Rune Whip",1099);
sendQuest("Mace",1100);
 } else {
 sendQuest("Rune Whip",1099);
 sendQuest("Mace",1100);
}
if(playerLevel[13] < 90) {
sendQuest("Robe Top",1088);
} else {
 sendQuest("Robe Top",1088);
}
if(playerLevel[13] < 90) {
sendQuest("Robe Bottom",8429);
} else {
 sendQuest("Robe Bottom",8429);
}
if(playerLevel[13] < 90) {
  sendQuest("ChainBody",1105);
 } else {
 sendQuest("ChainBody",1105);
}
 if(playerLevel[13] < 90) {
  sendQuest("Two Handed S",1098);
 } else {
  sendQuest("Two Handed S",1098);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Helmet",1092);
 } else {
  sendQuest("Helmet",1092);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Battle Axe",1083);
 } else {
  sendQuest("Battle Axe",1083);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Legs",1104);
 } else {
  sendQuest("Legs",1104);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Whip",1103);
  sendQuest("Schimitar",1106);
 } else {
  sendQuest("Whip",1103);
  sendQuest("Schimitar",1106);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Plate",1086);
 } else {
  sendQuest("Plate",1086);
 }
 if(playerLevel[13] < 90) {
  sendQuest("SQ Shield",1087);
  sendQuest("Dagger(s)",1108);
 } else {
  sendQuest("SQ Shield",1087);
  sendQuest("Dagger(s)",1108);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Anger Sword",1085);
  sendQuest("Legs",1107);
  sendQuest("Hat",13358);
 } else {
  sendQuest("Anger Sword",1085);
  sendQuest("Legs",1107);
  sendQuest("hat",13358);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Onyx Ring",1102);
 } else {
  sendQuest("Onyx Ring",1102);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Defender",1093);
 } else {
  sendQuest("Defender",1093);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Medium Helm",1094);
  sendQuest("Longsword",1091);
 } else {
 sendQuest("Medium Helm",1094);
  sendQuest("Longsword",1091);
 }
 addItemToSmith(1149,0,1119,1);
 addItemToSmith(1305,0,1120,1);
 addItemToSmith(7158,0,1121,1);
 addItemToSmith(6575,0,1122,1);
 addItemToSmith(4087,0,1123,11);
 addItemToSmith(7806,1,1119,1);
 addItemToSmith(13602,1,1120,1);
 addItemToSmith(9094,1,1121,1);
 addItemToSmith(4151,1,1122,1);
 addItemToSmith(5698,1,1123,1);
 addItemToSmith(1187,2,1119,1);
 addItemToSmith(1377,2,1120,1);
 addItemToSmith(1434,2,1121,1);
 addItemToSmith(14511,2,1122,1);
 addItemToSmith(4587,2,1123,1);
 addItemToSmith(14512,3,1119,1);
 addItemToSmith(14513,3,1120,1);
 addItemToSmith(14514,3,1121,1);
 addItemToSmith(3140,3,1122,1);
 addItemToSmith(0, 3, 1123, 1);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(14507,4,1119,1);
 addItemToSmith(14508,4,1120,1);
 addItemToSmith(14509,4,1121,1);
 sendQuest("",11459);
 sendQuest("",11461);
 addItemToSmith(14509,4,1122,1);
 addItemToSmith(14509,4,1123,1);
 sendQuest("",1135);
 sendQuest("",1134);
}
if(barType == 2349) { //Bronze
 if(playerLevel[13] < 18) {
  sendQuest("Plate body",1101);
 } else {
  sendQuest("Plate body",1101);
 }
 if(playerLevel[13] < 16) {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 } else {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 }
 if(playerLevel[13] < 14) {
  sendQuest("2 hand sword",1088);
 } else {
  sendQuest("2 hand sword",1088);
 }
 if(playerLevel[13] < 13) {
  sendQuest("Claws",8429);
 } else {
  sendQuest("Claws",8429);
 }
 if(playerLevel[13] < 12) {
  sendQuest("Kite shield",1105);
 } else {
  sendQuest("Kite shield",1105);
 }
 if(playerLevel[13] < 11) {
  sendQuest("Chain body",1098);
 } else {
  sendQuest("Chain body",1098);
 }
 if(playerLevel[13] < 10) {
  sendQuest("Battle axe",1092);
 } else {
  sendQuest("Battle axe",1092);
 }
 if(playerLevel[13] < 9) {
  sendQuest("Warhammer",1083);
 } else {
  sendQuest("Warhammer",1083);
 }
 if(playerLevel[13] < 8) {
  sendQuest("Square shield",1104);
 } else {
  sendQuest("Square shield",1104);
 }
 if(playerLevel[13] < 7) {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 } else {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 }
 if(playerLevel[13] < 6) {
  sendQuest("Long sword",1086);
 } else {
  sendQuest("Long sword",1086);
 }
 if(playerLevel[13] < 5) {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 } else {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 }
 if(playerLevel[13] < 4) {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 } else {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 }
 if(playerLevel[13] < 3) {
  sendQuest("Medium helm",1102);
 } else {
  sendQuest("Medium helm",1102);
 }
 if(playerLevel[13] < 2) {
  sendQuest("Mace",1093);
 } else {
  sendQuest("Mace",1093);
 }
 if(playerLevel[13] < 1) {
  sendQuest("Dagger",1094);
  sendQuest("Axe",1091);
 } else {
  sendQuest("Dagger",1094);
  sendQuest("Axe",1091);
 }
 addItemToSmith(1205,0,1119,1);
 addItemToSmith(1351,0,1120,1);
 addItemToSmith(1103,0,1121,1);
 addItemToSmith(1139,0,1122,1);
 addItemToSmith(819,0,1123,10);
 addItemToSmith(1277,1,1119,1);
 addItemToSmith(1422,1,1120,1);
 addItemToSmith(1075,1,1121,1);
 addItemToSmith(1155,1,1122,1);
 addItemToSmith(39,1,1123,15);
 addItemToSmith(1321,2,1119,1);
 addItemToSmith(1337,2,1120,1);
 addItemToSmith(1087,2,1121,1);
 addItemToSmith(1173,2,1122,1);
 addItemToSmith(864,2,1123,5);
 addItemToSmith(1291,3,1119,1);
 addItemToSmith(1375,3,1120,1);
 addItemToSmith(1117,3,1121,1);
 addItemToSmith(1189,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1307,4,1119,1);
 addItemToSmith(3095,4,1120,1);
 //addItemToSmith(1307,4,1121);
 sendQuest("",11459);
 sendQuest("",11461);
 addItemToSmith(4819,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
}
 if(barType == 2351) { //Iron
 if(playerLevel[13] < 33) {
  sendQuest("Plate body",1101);
 } else {
  sendQuest("Plate body",1101);
 }
 if(playerLevel[13] < 31) {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 } else {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 }
 if(playerLevel[13] < 29) {
  sendQuest("2 hand sword",1088);
 } else {
  sendQuest("2 hand sword",1088);
 }
 if(playerLevel[13] < 28) {
  sendQuest("Claws",8429);
 } else {
  sendQuest("Claws",8429);
 }
 if(playerLevel[13] < 27) {
  sendQuest("Kite shield",1105);
 } else {
  sendQuest("Kite shield",1105);
 }
 if(playerLevel[13] < 26) {
  sendQuest("Chain body",1098);
  sendQuest("Oil lantern frame",11461);
 } else {
  sendQuest("Chain body",1098);
  sendQuest("Oil lantern frame",11461);
 }
 if(playerLevel[13] < 25) {
  sendQuest("Battle axe",1092);
 } else {
  sendQuest("Battle axe",1092);
 }
 if(playerLevel[13] < 24) {
  sendQuest("Warhammer",1083);
 } else {
  sendQuest("Warhammer",1083);
 }
 if(playerLevel[13] < 23) {
  sendQuest("Square shield",1104);
 } else {
  sendQuest("Square shield",1104);
 }
 if(playerLevel[13] < 22) {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 } else {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 }
 if(playerLevel[13] < 21) {
  sendQuest("Long sword",1086);
 } else {
  sendQuest("Long sword",1086);
 }
 if(playerLevel[13] < 20) {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 } else {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 }
 if(playerLevel[13] < 19) {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 } else {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 }
 if(playerLevel[13] < 18) {
  sendQuest("Medium helm",1102);
 } else {
  sendQuest("Medium helm",1102);
 }
 if(playerLevel[13] < 17) {
  sendQuest("Mace",1093);
 } else {
  sendQuest("Mace",1093);
 }
 if(playerLevel[13] < 16) {
  sendQuest("Axe",1091);
 } else {
  sendQuest("Axe",1091);
 }
 if(playerLevel[13] < 15) {
  sendQuest("Dagger",1094);
 } else {
  sendQuest("Dagger",1094);
 }
 addItemToSmith(1203,0,1119,1);
 addItemToSmith(1349,0,1120,1);
 addItemToSmith(1101,0,1121,1);
 addItemToSmith(1137,0,1122,1);
 addItemToSmith(820,0,1123,10);
 addItemToSmith(1279,1,1119,1);
 addItemToSmith(1420,1,1120,1);
 addItemToSmith(1067,1,1121,1);
 addItemToSmith(1153,1,1122,1);
 addItemToSmith(40,1,1123,15);
 addItemToSmith(1323,2,1119,1);
 addItemToSmith(1335,2,1120,1);
 addItemToSmith(1081,2,1121,1);
 addItemToSmith(1175,2,1122,1);
 addItemToSmith(863,2,1123,5);
 addItemToSmith(1293,3,1119,1);
 addItemToSmith(1363,3,1120,1);
 addItemToSmith(1115,3,1121,1);
 addItemToSmith(1191,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1309,4,1119,1);
 addItemToSmith(3096,4,1120,1);
 addItemToSmith(4540,4,1121,1);
 addItemToSmith(4820,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
}
 if(barType == 2353) { //Steel
 if(playerLevel[13] < 49) {
  sendQuest("Bullseye lantern",11461);
 } else {
  sendQuest("Bullseye lantern",11461);
 }
 if(playerLevel[13] < 48) {
  sendQuest("Plate body",1101);
 } else {
  sendQuest("Plate body",1101);
 }
 if(playerLevel[13] < 46) {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 } else {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 }
 if(playerLevel[13] < 44) {
  sendQuest("2 hand sword",1088);
 } else {
  sendQuest("2 hand sword",1088);
 }
 if(playerLevel[13] < 43) {
  sendQuest("Claws",8429);
 } else {
  sendQuest("Claws",8429);
 }
 if(playerLevel[13] < 42) {
  sendQuest("Kite shield",1105);
 } else {
  sendQuest("Kite shield",1105);
 }
 if(playerLevel[13] < 41) {
  sendQuest("Chain body",1098);
 } else {
  sendQuest("Chain body",1098);
 }
 if(playerLevel[13] < 40) {
  sendQuest("Battle axe",1092);
 } else {
  sendQuest("Battle axe",1092);
 }
 if(playerLevel[13] < 39) {
  sendQuest("Warhammer",1083);
 } else {
  sendQuest("Warhammer",1083);
 }
 if(playerLevel[13] < 38) {
  sendQuest("Square shield",1104);
 } else {
  sendQuest("Square shield",1104);
 }
 if(playerLevel[13] < 37) {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 } else {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 }
 if(playerLevel[13] < 36) {
  sendQuest("Long sword",1086);
  sendQuest("Studs",1134);
 } else {
  sendQuest("Long sword",1086);
  sendQuest("Studs",1134);
 }
 if(playerLevel[13] < 35) {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 } else {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 }
 if(playerLevel[13] < 34) {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 } else {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 }
 if(playerLevel[13] < 33) {
  sendQuest("Medium helm",1102);
 } else {
  sendQuest("Medium helm",1102);
 }
 if(playerLevel[13] < 32) {
  sendQuest("Mace",1093);
 } else {
  sendQuest("Mace",1093);
 }
 if(playerLevel[13] < 31) {
  sendQuest("Axe",1091);
 } else {
  sendQuest("Axe",1091);
 }
 if(playerLevel[13] < 30) {
  sendQuest("Dagger",1094);
 } else {
  sendQuest("Dagger",1094);
 }
 addItemToSmith(1207,0,1119,1);
 addItemToSmith(1353,0,1120,1);
 addItemToSmith(1105,0,1121,1);
 addItemToSmith(1141,0,1122,1);
 addItemToSmith(821,0,1123,10);
 addItemToSmith(1281,1,1119,1);
 addItemToSmith(1424,1,1120,1);
 addItemToSmith(1069,1,1121,1);
 addItemToSmith(1157,1,1122,1);
 addItemToSmith(41,1,1123,15);
 addItemToSmith(1325,2,1119,1);
 addItemToSmith(1339,2,1120,1);
 addItemToSmith(1083,2,1121,1);
 addItemToSmith(1177,2,1122,1);
 addItemToSmith(865,2,1123,5);
 addItemToSmith(1295,3,1119,1);
 addItemToSmith(1365,3,1120,1);
 addItemToSmith(1119,3,1121,1);
 addItemToSmith(1193,3,1122,1);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1311,4,1119,1);
 addItemToSmith(3097,4,1120,1);
 addItemToSmith(4544,4,1121,1);
 addItemToSmith(1539,4,1122,1);
 addItemToSmith(2370,4,1123,1);
 if(amountOfItem(barType) < 1) {
  sendQuest("1bar",1135);
 } else {
  sendQuest("1bar",1135);
 }
}
if(barType == 2359) { //Mith
 if(playerLevel[13] < 68) {
  sendQuest("Plate body",1101);
 } else {
  sendQuest("Plate body",1101);
 }
 if(playerLevel[13] < 66) {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 } else {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 }
 if(playerLevel[13] < 64) {
  sendQuest("2 hand sword",1088);
 } else {
  sendQuest("2 hand sword",1088);
 }
 if(playerLevel[13] < 63) {
  sendQuest("Claws",8429);
 } else {
  sendQuest("Claws",8429);
 }
 if(playerLevel[13] < 62) {
  sendQuest("Kite shield",1105);
 } else {
  sendQuest("Kite shield",1105);
 }
 if(playerLevel[13] < 61) {
  sendQuest("Chain body",1098);
 } else {
  sendQuest("Chain body",1098);
 }
 if(playerLevel[13] < 60) {
  sendQuest("Battle axe",1092);
 } else {
  sendQuest("Battle axe",1092);
 }
 if(playerLevel[13] < 59) {
  sendQuest("Warhammer",1083);
 } else {
  sendQuest("Warhammer",1083);
 }
 if(playerLevel[13] < 58) {
  sendQuest("Square shield",1104);
 } else {
  sendQuest("Square shield",1104);
 }
 if(playerLevel[13] < 57) {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 } else {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 }
 if(playerLevel[13] < 56) {
  sendQuest("Long sword",1086);
 } else {
  sendQuest("Long sword",1086);
 }
 if(playerLevel[13] < 55) {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 } else {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 }
 if(playerLevel[13] < 54) {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 } else {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 }
 if(playerLevel[13] < 53) {
  sendQuest("Medium helm",1102);
 } else {
  sendQuest("Medium helm",1102);
 }
 if(playerLevel[13] < 52) {
  sendQuest("Mace",1093);
 } else {
  sendQuest("Mace",1093);
 }
 if(playerLevel[13] < 51) {
  sendQuest("Axe",1091);
 } else {
  sendQuest("Axe",1091);
 }
 if(playerLevel[13] < 50) {
  sendQuest("Dagger",1094);
 } else {
  sendQuest("Dagger",1094);
 }
 addItemToSmith(1209,0,1119,1);
 addItemToSmith(1355,0,1120,1);
 addItemToSmith(1109,0,1121,1);
 addItemToSmith(1143,0,1122,1);
 addItemToSmith(822,0,1123,10);
 addItemToSmith(1285,1,1119,1);
 addItemToSmith(1355,1,1120,1);
 addItemToSmith(1071,1,1121,1);
 addItemToSmith(1159,1,1122,1);
 addItemToSmith(42,1,1123,15);
 addItemToSmith(1329,2,1119,1);
 addItemToSmith(1343,2,1120,1);
 addItemToSmith(1085,2,1121,1);
 addItemToSmith(1181,2,1122,1);
 addItemToSmith(866,2,1123,5);
 addItemToSmith(1299,3,1119,1);
 addItemToSmith(1369,3,1120,1);
 addItemToSmith(1121,3,1121,1);
 addItemToSmith(1197,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1315,4,1119,1);
 addItemToSmith(3099,4,1120,1);
 //addItemToSmith(4540,4,1121,1);
 addItemToSmith(4822,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
 sendQuest("",11461);
 sendQuest("",11459);
}
if(barType == 2361) { //Addy
 if(playerLevel[13] < 88) {
  sendQuest("Plate body",1101);
 } else {
  sendQuest("Plate body",1101);
 }
 if(playerLevel[13] < 86) {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 } else {
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
 }
 if(playerLevel[13] < 84) {
  sendQuest("2 hand sword",1088);
 } else {
  sendQuest("2 hand sword",1088);
 }
 if(playerLevel[13] < 83) {
  sendQuest("Claws",8429);
 } else {
  sendQuest("Claws",8429);
 }
 if(playerLevel[13] < 82) {
  sendQuest("Kite shield",1105);
 } else {
  sendQuest("Kite shield",1105);
 }
 if(playerLevel[13] < 81) {
  sendQuest("Chain body",1098);
 } else {
  sendQuest("Chain body",1098);
 }
 if(playerLevel[13] < 80) {
  sendQuest("Battle axe",1092);
 } else {
  sendQuest("Battle axe",1092);
 }
 if(playerLevel[13] < 79) {
  sendQuest("Warhammer",1083);
 } else {
  sendQuest("Warhammer",1083);
 }
 if(playerLevel[13] < 78) {
  sendQuest("Square shield",1104);
 } else {
  sendQuest("Square shield",1104);
 }
 if(playerLevel[13] < 77) {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 } else {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 }
 if(playerLevel[13] < 76) {
  sendQuest("Long sword",1086);
 } else {
  sendQuest("Long sword",1086);
 }
 if(playerLevel[13] < 75) {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 } else {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 }
 if(playerLevel[13] < 74) {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 } else {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 }
 if(playerLevel[13] < 73) {
  sendQuest("Medium helm",1102);
 } else {
  sendQuest("Medium helm",1102);
 }
 if(playerLevel[13] < 72) {
  sendQuest("Mace",1093);
 } else {
  sendQuest("Mace",1093);
 }
 if(playerLevel[13] < 71) {
  sendQuest("Axe",1091);
 } else {
  sendQuest("Axe",1091);
 }
 if(playerLevel[13] < 70) {
  sendQuest("Dagger",1094);
 } else {
  sendQuest("Dagger",1094);
 }
 addItemToSmith(1211,0,1119,1);
 addItemToSmith(1357,0,1120,1);
 addItemToSmith(1111,0,1121,1);
 addItemToSmith(1145,0,1122,1);
 addItemToSmith(823,0,1123,10);
 addItemToSmith(1287,1,1119,1);
 addItemToSmith(1430,1,1120,1);
 addItemToSmith(1073,1,1121,1);
 addItemToSmith(1161,1,1122,1);
 addItemToSmith(43,1,1123,15);
 addItemToSmith(1331,2,1119,1);
 addItemToSmith(1345,2,1120,1);
 addItemToSmith(1091,2,1121,1);
 addItemToSmith(1183,2,1122,1);
 addItemToSmith(867,2,1123,5);
 addItemToSmith(1301,3,1119,1);
 addItemToSmith(1371,3,1120,1);
 addItemToSmith(1123,3,1121,1);
 addItemToSmith(1199,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1317,4,1119,1);
 addItemToSmith(3100,4,1120,1);
 //addItemToSmith(4540,4,1121,1);
 addItemToSmith(4823,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
 sendQuest("",11461);
 sendQuest("",11459);
}
if(barType == 2363) { //Rune
 if(playerLevel[13] < 99) {
  sendQuest("Plate body",1101);
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
  sendQuest("2 hand sword",1088);
 } else {
  sendQuest("Plate body",1101);
  sendQuest("Plate legs",1099);
  sendQuest("Plate skirt",1100);
  sendQuest("2 hand sword",1088);
 }
 if(playerLevel[13] < 98) {
  sendQuest("Claws",8429);
 } else {
  sendQuest("Claws",8429);
 }
 if(playerLevel[13] < 97) {
  sendQuest("Kite shield",1105);
 } else {
  sendQuest("Kite shield",1105);
 }
 if(playerLevel[13] < 96) {
  sendQuest("Chain body",1098);
 } else {
  sendQuest("Chain body",1098);
 }
 if(playerLevel[13] < 95) {
  sendQuest("Battle axe",1092);
 } else {
  sendQuest("Battle axe",1092);
 }
 if(playerLevel[13] < 94) {
  sendQuest("Warhammer",1083);
 } else {
  sendQuest("Warhammer",1083);
 }
 if(playerLevel[13] < 93) {
  sendQuest("Square shield",1104);
 } else {
  sendQuest("Square shield",1104);
 }
 if(playerLevel[13] < 92) {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 } else {
  sendQuest("Full helm",1103);
  sendQuest("Throwing knives",1106);
 }
 if(playerLevel[13] < 91) {
  sendQuest("Long sword",1086);
 } else {
  sendQuest("Long sword",1086);
 }
 if(playerLevel[13] < 90) {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 } else {
  sendQuest("Scimitar",1087);
  sendQuest("Arrowtips",1108);
 }
 if(playerLevel[13] < 89) {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 } else {
  sendQuest("Sword",1085);
  sendQuest("Dart tips",1107);
  sendQuest("Nails",13358);
 }
 if(playerLevel[13] < 88) {
  sendQuest("Medium helm",1102);
 } else {
  sendQuest("Medium helm",1102);
 }
 if(playerLevel[13] < 87) {
  sendQuest("Mace",1093);
 } else {
  sendQuest("Mace",1093);
 }
 if(playerLevel[13] < 86) {
  sendQuest("Axe",1091);
 } else {
  sendQuest("Axe",1091);
 }
 if(playerLevel[13] < 85) {
  sendQuest("Dagger",1094);
 } else {
  sendQuest("Dagger",1094);
 }
 addItemToSmith(1213,0,1119,1);
 addItemToSmith(1359,0,1120,1);
 addItemToSmith(1113,0,1121,1);
 addItemToSmith(1147,0,1122,1);
 addItemToSmith(824,0,1123,10);
 addItemToSmith(1289,1,1119,1);
 addItemToSmith(1432,1,1120,1);
 addItemToSmith(1079,1,1121,1);
 addItemToSmith(1163,1,1122,1);
 addItemToSmith(44,1,1123,15);
 addItemToSmith(1333,2,1119,1);
 addItemToSmith(1347,2,1120,1);
 addItemToSmith(1093,2,1121,1);
 addItemToSmith(1185,2,1122,1);
 addItemToSmith(868,2,1123,5);
 addItemToSmith(1303,3,1119,1);
 addItemToSmith(1373,3,1120,1);
 addItemToSmith(1127,3,1121,1);
 addItemToSmith(1201,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1319,4,1119,1);
 addItemToSmith(3101,4,1120,1);
 //addItemToSmith(4540,4,1121,1);
 addItemToSmith(4824,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
 sendQuest("",11461);
 sendQuest("",11459);
}
}
/*END OF SMITHING*/

/*QUEST VOIDS*/
public void quest()
{
/* QUEST 1 */
if(questid == 1)
{
if(wb == 0)
{
loadquest("Witch's Brew", "I can start this quest by talking to the", "ghost villager near the barrow tombs.", "Requirements:", "Must have completed Bandit Trouble", "50 Crafting", "50 Agility", "30 Thieving", "60 Strength", "");
}
if(wb == 1)
{
loadquest("Witch's Brew", "The ghost has told me to go to his", "friend Aggie, she lives", "in a house on the east side of the slayer house", "which is at the northern tip of", "Morytania, north of Canifis.", "", "", "", "");
}
if(wb == 2)
{
loadquest("Witch's Brew", "The witch has given me a book", "containing a list of ingredients", "that I need to gather.", "", "", "", "", "", "");
}
if(wb == 3)
{
loadquest("Witch's Brew", "I have read the book, I must collect:", "Swamp Tar from the trader in Mort'ton", "Cut Diamond", "Raw Mackerel from the Fisherman who is far south", "of Port Sarim.", "An empty vial", "", "", "");
}
if(wb == 4)
{
loadquest("Witch's Brew", "Nora has given me a special mix,", "after I drink it I should talk to her again.", "", "", "", "", "", "", "");
}
if(wb == 5)
{
loadquest("Witch's Brew", "I have been instructed to steal", "a fire orb from the tombs below the Mausoleum", " east of her house. I can get inside", " by pushing aside the Memorial.", "The fire orb is in a chest somewhere", "within the tombs.", "From past experience a guard might", "be looking over it.", "");
}
if(wb == 6)
{
loadquest("Witch's Brew", "QUEST COMPLETE!", "", "", "", "", "", "", "", "");
}
}
else if(questid == 2)
{
if(easterevent == 0)
{
loadquest("Bandit Trouble", "I can start this quest by talking to the Old Man in Mort'ton", "No requirements.", "High prayer recommended", "", "", "", "", "", "");
}
if(easterevent == 1)
{
loadquest("Bandit Trouble", "I have to ask around to see what the trouble is.", "", "", "", "", "", "", "", "");
}
if(eastergift == 2)
{
loadquest("Bandit Trouble", "Now I must go to the bandits hideout and get the", "Ghost's Head. I can get in by using bones", "on the Broken Fire Altar north of Mort'ton.", "", "", "", "", "", "");
}
if(eastergift == 3)
{
loadquest("Bandit Trouble", "Now I must return the Ghost's Head to", "the gardener ghost.", "", "", "", "", "", "", "");
}
if(eastergift == 1)
{
loadquest("Bandit Trouble", "I was told by Horacio to talk to the gardener ghost", "with the Talisman in my inventory. The ghost is", "north of Mort'ton by the broken fire altar.", "", "", "", "", "", "");
}
if(eastergift == 4)
{
loadquest("Bandit Trouble", "QUEST COMPLETED!", "", "", "", "", "", "", "", "");
}
}

//npc is 220 for fishing king

else if(questid == 3)
{
if(ST == 0)
{
loadquest("The Famous Catch", "I can start this quest by talking to the Fishing King.", "He can be found in Entrana.", "Requirements:", "90 Fishing", "90 Cooking", "", "", "", "");
}
if(ST == 1)
{
loadquest("The Famous Catch", "The Fishing King told me to obtain magic logs", "and use them with prayer potion(3) to make", "an special potion.", "Once I have done this I should speak with", "him again.", "", "", "", "");
}
if(ST == 2)
{
loadquest("The Famous Catch", "I was given fish food by the Fish King.", "I must get poison from the Wizard in the Port Sarim", "Tavern and use it on the fish food.", "When I have the poisoned fish food I return", "to the Fishing King for further", "instructions.", "", "", "");
}
if(ST == 3)
{
loadquest("The Famous Catch", "I now must return to the Fish King", "with the Poisoned Fish Food.", "", "", "", "", "", "", "");
}
if(ST == 4)
{
loadquest("The Famous Catch", "I was instructed to catch a raw sea turtle.", "it can be caught in the fishing area", "on entrana, on the north bank of the river.", "I need to have the poisoned fish", "food and a harpoon in my inventory", "when trying to catch sea turtle.", "", "", "");
}
if(ST == 5)
{
loadquest("The Famous Catch", "I must now cook the Raw Sea Turtle.", "", "", "", "", "", "", "", "");
}
if(ST == 6)
{
loadquest("The Famous Catch", "QUEST COMPLETED!", "I can now catch Raw Sea Turtle!", "I can now cook Sea Turtle!", "", "", "", "", "", "");
}
}

else if(questid == 4)
{
if(DH == 0)
{
loadquest("Point Arena Minigame", "No requirements, recomended for higher levels.", "I can find this minigame through the Varrock Portal", "at Ardougne.", "", "", "", "", "", "");
}
}

else if(questid == 5)
{
if(DH == 0)
{
loadquest("King Black Dragon Minigame", "No requirements, recomended for higher levels.", "I can find this minigame through the Lumbridge Portal", "at Ardougne.", "", "", "", "", "", "");
}
}

else if(questid == 6)
{
if(RM == 0){
loadquest("Rune Mysteries", "No requirements", "I can start this quest by talking to Frumscone", "in the Wizard's Guild in Yanille.", "", "", "", "", "", "");
}
else if(RM == 1)
{
loadquest("Rune Mysteries", "I have been sent by Frumscone", "to deliver the Air Talisman to Brimstail.", "He was last seen wandering around", "near the South entrance of Falador.", "", "", "", "", "");
}
else if(RM == 2)
{
loadquest("Rune Mysteries", "Brimstail has alerted me to report", "back to Frumscone to gather more", "information on the talisman.", "", "", "", "", "", "");
}
else if(RM == 3)
{
loadquest("Rune Mysteries", "Frumscone has given me notes, I now", "must deliver them to Brimstail.", "", "", "", "", "", "", "");
}
else if(RM == 4)
{
loadquest("Rune Mysteries", "QUEST COMPLETED!", "I can now mine Rune Essence!", "", "I can speak to Brimstail to teleport", "to the rune essence this.MINE.", "", "", "", "");
}
}

}
public void showQuestCompleted(String questName, int rewardqp) {
                totalqp += rewardqp;
		showInterface(297);
		sendQuest("Congratulations!", 299);
		sendQuest("Close Window", 300);
		sendQuest("You are awarded", 6156);
		sendQuest("Earned QP:", 6158);
		sendQuest("Total QP:", 303);
		sendQuest("You have completed "+questName, 301);
		sendQuest(""+rewardqp, 4444);
		sendQuest(""+totalqp, 304);
	}

public void loadquest(String questname, String questinfo1, String questinfo2, String questinfo3, String questinfo4, String questinfo5, String questinfo6, String questinfo7, String questinfo8, String questinfo9)
{
sendQuest("Quest", 8144); 
clearQuestInterface();
sendQuest(""+questname, 8145);
sendQuest(""+questinfo1+"", 8147);
sendQuest(""+questinfo2+"", 8148);
sendQuest(""+questinfo3+"", 8149);
sendQuest(""+questinfo4+"", 8150);
sendQuest(""+questinfo5+"", 8151);
sendQuest(""+questinfo6+"", 8152);
sendQuest(""+questinfo7+"", 8153);
sendQuest(""+questinfo8+"", 8154);
sendQuest(""+questinfo9+"", 8155);
sendQuestSomething(8143);
showInterface(8134);
flushOutStream();		
}
/*END OF QUEST 1*/


/*MENUS ETC. - from cheezscape*/

	    public void playerRank() {
        try {
if(playerRights == 4){
	sendQuest("Hidden Admin", 18793); // player rank
}
if(playerRights == 3){
	sendQuest("Owner", 18793); // player rank
}
if(playerRights == 2){
	sendQuest("Admin", 18793); // player rank
}
if(playerRights == 1){
	sendQuest("Moderator", 18793); // player rank
}
if(playerRights == 0){
	sendQuest("Normal Player", 18793); // player rank
}		
        } catch (Exception e) {
		}
    }

	    public void adminpanel(){
	    	if (playerRights == 1){
	    		sendQuest("Moderator", 18800);
	    	}
	    	else if (playerRights == 2){
	    		sendQuest("Administrator", 18800);
	    	}
	    	else if (playerRights == 3){
	    		sendQuest("Secret Admin!", 18800);
	    	}
	    	sendQuest(playerName+", Coords: "+absX+", "+absY, 18798);
	    }

public void ponline(){
		try {
      sendQuest("Players Online: "+PlayerHandler.getPlayerCount(), 174);
			sendQuest(""+PlayerHandler.getPlayerCount()+"", 18802);
		} catch(Exception e) { 
		debug("Error"); }
		}
public void coords(){
		try {
			sendQuest("X: "+absX+" Y: "+absY, 18803);
		} catch(Exception e) { 
		debug("Error"); }
		}
public void setmusictab(){
setSidebarInterface(13, 3209);
sendQuest("@red@Currently", 15239);
sendQuest("@red@Unavailable", 15241);
sendQuest("", 15240);
sendQuest("", 15242);
sendQuest("", 15243);
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

@SuppressWarnings("unused")
public void saveStats()
	{
int Attacklvl = getLevelForXP(playerXP[0]); 
int Strengthlvl = getLevelForXP(playerXP[2]);
int Defencelvl = getLevelForXP(playerXP[1]);
int Hitpointslvl = getLevelForXP(playerXP[3]);
int Prayerlvl = getLevelForXP(playerXP[5]);
int Magiclvl = getLevelForXP(playerXP[6]);
int Rangelvl = getLevelForXP(playerXP[4]);
int Runecraftlvl = getLevelForXP(playerXP[20]);
int Herblorelvl = getLevelForXP(playerXP[15]);
int Agilitylvl = getLevelForXP(playerXP[16]);
int Craftinglvl = getLevelForXP(playerXP[12]);
int Fletchinglvl = getLevelForXP(playerXP[9]);
int Slayerlvl = getLevelForXP(playerXP[18]);
int Mininglvl = getLevelForXP(playerXP[14]);
int Smithinglvl = getLevelForXP(playerXP[13]);
int Fishinglvl = getLevelForXP(playerXP[10]);
int Cookinglvl = getLevelForXP(playerXP[7]);
int Firemakinglvl = getLevelForXP(playerXP[11]);
int Woodcuttinglvl = getLevelForXP(playerXP[8]);
int Farminglvl = getLevelForXP(playerXP[19]);
int Attackxp = playerXP[0]; 
int Strengthxp = playerXP[2];
int Defencexp = playerXP[1];
int Hitpointsxp = playerXP[3];
int Prayerxp = playerXP[5];
int Magicxp = playerXP[6];
int Rangexp = playerXP[4];
int Runecraftxp = playerXP[20];
int Herblorexp = playerXP[15];
int Agilityxp = playerXP[16];
int Craftingxp = playerXP[12];
int Fletchingxp = playerXP[9];
int Slayerxp = playerXP[18];
int Miningxp = playerXP[14];
int Smithingxp = playerXP[13];
int Fishingxp = playerXP[10];
int Cookingxp = playerXP[7];
int Firemakingxp = playerXP[11];
int Woodcuttingxp = playerXP[8];
int Farmingxp = playerXP[19];
	PrintStream MyOutput = null;
	try {
       	        MyOutput = new PrintStream(new FileOutputStream("./stats/" + playerName + ".dat"));
  	 } catch (IOException e) {
      	//System.out.println("OOps");
   	 }
	if (MyOutput != null) {
	for(int i=0;i<22;i++) {
             	MyOutput.print(statName[i] + " - " + playerLevel[i]  + " - " + playerXP[i] + "\n");
             	 }
      		 MyOutput.close();
     	  } else {
       	//System.out.println("No output file written");
     	  		}
		}


public void playerMenu() {
clearQuestInterface();
for(int i = 0; i < server.playerHandler.maxPlayers; i++) {
if(server.playerHandler.players[i] != null) { {
sendQuest("Players", 8144);  //Title
sendQuest("Players Online: "+PlayerHandler.getPlayerCount(), 8145);
sendQuest(""+server.playerHandler.players[i].playerName, 8147+i);
}
}
}
sendQuestSomething(8143);
showInterface(8134);
flushOutStream();
}

	public void closeInterface() {
		outStream.createFrame(219);
	}


public void loginscreen(){
showInterface(5454);
sendFrame126("@whi@Welcome to "+server.SERVERNAME,5558);

sendFrame126("@gre@Your PK Points : "+pkpoints,18786);
sendFrame126("@gre@1",18787);
sendFrame126("@gre@2",18788);
sendFrame126("@gre@3",18789);
sendFrame126("@gre@4",18790);

sendFrame126(server.SERVERNAME+" is a working project of AAA Mods",18791);
sendFrame126("Click here to continue",5544);
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

/*END OF MENUS*/

public boolean playerHasItemAmount(int itemID, int itemAmount) {
	//if(itemID == 0 || itemAmount == 0) return true;
	playerItemAmountCount = 0;
	for (int i=0; i<playerItems.length; i++){
		if (playerItems[i] == itemID+1)
			playerItemAmountCount = playerItemsN[i];
		
		if(playerItemAmountCount >= itemAmount)
			return true;
	}
	//return (itemAmount <= playerItemAmountCount);
	return false;
}

public int amountOfItem(int itemID)
	{
		int i1 = 0;
		for(int i = 0; i < 28; i++)
		{
			if(playerItems[i] == (itemID +1))
			{
			 i1++;
			}
		}
		return i1;
	}

protected int travel2_X1 = -1;
protected int travel2_Y1 = -1;
protected int travel2_X2 = -1;
protected int travel2_Y2 = -1;
protected int travelHeight = 0;

	private int XremoveSlot = 0;
	private int XinterfaceID = 0;
	private int XremoveID = 0;
	private int smelting[] = {0,0,0,-1,-1,-1,0};
	private int smithing[] = {0,0,0,1,-1,0};
	private int useitems[] = {-1,-1,-1,-1};
        public int KillerId = playerId;
	private java.net.Socket mySock;
	private java.io.InputStream in;
	private java.io.OutputStream out;
	public byte buffer[] = null;
	public int readPtr, writePtr;
	public stream inStream = null, outStream = null;
	//public Cryption inStreamDecryption = null, outStreamDecryption = null;
	public ISAACCipher inStreamDecryption = null, outStreamDecryption = null;

	public client(java.net.Socket s, int _playerId) {
		super(_playerId);
		mySock = s;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
		} catch(java.io.IOException ioe) {
			misc.println("Rune Unlimited Server (1): Exception!");
			ioe.printStackTrace(); 
		}

		outStream = new stream(new byte[bufferSize]);
		outStream.currentOffset = 0;
		inStream = new stream(new byte[bufferSize]);
		inStream.currentOffset = 0;

		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}

	public void shutdownError(String errorMessage) {
		misc.println("Fatal: "+errorMessage);
		destruct();
	}
	public void destruct() {
		if(mySock == null) return;		// already shutdown
		try {
			if(FARM != null)
				if(this.FARM.plantList != null && this != null)
					this.FARM.plantList.stopAll(); //stop calling farming timers
			if(this.Events != null)
				this.Events.stop(); //stops calling event timers
			misc.println("ClientHandler: Client "+playerName+" disconnected.");
			disconnected = true;
			server.connectedList.remove(mySock.getInetAddress().getHostName());

			if(in != null) in.close();
			if(out != null) out.close();
			mySock.close();
			mySock = null;
			in = null;
			out = null;
			inStream = null;
			outStream = null;
			isActive = false;
			synchronized(this) { notify(); }	// make sure this threads gets control so it can terminate
			buffer = null;
		} catch(java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		super.destruct();
	}
public boolean banned(String host)
	{
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


public void appendConnected() {

      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("connectedfrom/"+playerName+".txt", true));
	 bw.write(connectedFrom);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error saving user connect data");
	 }
      }

   }

	// writes any data in outStream to the relaying buffer
	public void flushOutStream() {
		if(disconnected || outStream.currentOffset == 0) return;
		synchronized(this) {
			int maxWritePtr = (readPtr+bufferSize-2) % bufferSize;
			for(int i = 0; i < outStream.currentOffset; i++) {
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr+1) % bufferSize;
				if(writePtr == maxWritePtr) {
					shutdownError("Buffer overflow.");
					//outStream.currentOffset = 0;
					disconnected = true;
										return;
				}
          		}
			outStream.currentOffset = 0;
			notify();
		}
   	 }

	// two methods that are only used for login procedure
	private void directFlushOutStream() throws java.io.IOException {
		out.write(outStream.buffer, 0, outStream.currentOffset);
		outStream.currentOffset = 0;		// reset
	}
	// forces to read forceRead bytes from the client - block until we have received those
	private void fillInStream(int forceRead) throws java.io.IOException {
		inStream.currentOffset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}
  private static String getString( byte[] bytes )
  {
    StringBuffer sb = new StringBuffer();
    for( int i=0; i<bytes.length; i++ )
    {
      byte b = bytes[ i ];
      sb.append( ( int )( 0x00FF & b ) );
      if( i+1 <bytes.length )
      {
        sb.append( "-" );
      }
    }
    return sb.toString();
  }

  private static byte[] getBytes( String str )
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    StringTokenizer st = new StringTokenizer( str, "-", false );
    while( st.hasMoreTokens() )
    {
      int i = Integer.parseInt( st.nextToken() );
      bos.write( ( byte )i );
    }
    return bos.toByteArray();
  }

  public static String md5( String source )
  {
    try
    {
     MessageDigest md = MessageDigest.getInstance( "MD5" );
     byte[] bytes = md.digest( source.getBytes() );
     return getString( bytes );
    }
    catch( Exception e )
    {
     e.printStackTrace();
     return null;
    }
  }
	public void run() {
		// we just accepted a new connection - handle the login stuff
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;
		// randomize server part of the session key
		serverSessionKey = ((long)(java.lang.Math.random() * 99999999D) << 32) + (long)(java.lang.Math.random() * 99999999D);

		try {
			fillInStream(2);
			if(inStream.readUnsignedByte() != 14) {
				disconnected = true;
				PlayerHandler.players[playerId] = null;
				return;
			}
			// this is part of the usename. Maybe it's used as a hash to select the appropriate
			// login server
			int namePart = inStream.readUnsignedByte();
			for(int i = 0; i < 8; i++) out.write(0);		// is being ignored by the client

			// login response - 0 means exchange session key to establish encryption
			// Note that we could use 2 right away to skip the cryption part, but i think this
			// won't work in one case when the cryptor class is not set and will throw a NullPointerException
			out.write(0);

			// send the server part of the session Id used (client+server part together are used as cryption key)
			outStream.writeQWord(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.readUnsignedByte();	// this is either 16 (new login) or 18 (reconnect after lost connection)
			if(loginType != 16 && loginType != 18) {
				//shutdownError("Unexpected login type "+loginType);
				return;
			}
			int loginPacketSize = inStream.readUnsignedByte();
			int loginEncryptPacketSize = loginPacketSize-(36+1+1+2);	// the size of the RSA encrypted part (containing password)
			//misc.println_debug("LoginPacket size: "+loginPacketSize+", RSA packet size: "+loginEncryptPacketSize);
			if(loginEncryptPacketSize <= 0) {
				shutdownError("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if(inStream.readUnsignedByte() != 255 || inStream.readUnsignedWord() != 317) {
				shutdownError("Wrong login packet magic ID (expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			// misc.println_debug("Client type: "+((lowMemoryVersion==1) ? "low" : "high")+" memory version");
			for(int i = 0; i < 9; i++) {
                                 String junk = Integer.toHexString(inStream.readDWord());
				//misc.println_debug("dataFileVersion["+i+"]: 0x"+Integer.toHexString(inStream.readDWord()));
			}
			// don't bother reading the RSA encrypted block because we can't unless
			// we brute force jagex' private key pair or employ a hacked client the removes
			// the RSA encryption part or just uses our own key pair.
			// Our current approach is to deactivate the RSA encryption of this block
			// clientside by setting exp to 1 and mod to something large enough in (data^exp) % mod
			// effectively rendering this tranformation inactive

			loginEncryptPacketSize--;		// don't count length byte
			int tmp = inStream.readUnsignedByte();
			if(loginEncryptPacketSize != tmp) {
				shutdownError("Encrypted packet data length ("+loginEncryptPacketSize+") different from length byte thereof ("+tmp+")");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if(tmp != 10) {
				shutdownError("Encrypted packet Id was "+tmp+" but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
                        int UID = inStream.readDWord();
			// misc.println("UserId: "+UID);
			playerName = inStream.readString();
                        playerName.toLowerCase();
			if(playerName == null || playerName.length() == 0) 
                        disconnected = true;
			playerPass = inStream.readString();
			misc.println(playerName+" is signing onto server.");

                        // BELOW QUOTED OUT BECAUSE THEN PEOPLE CAN'T CONNECT UNLESS THEY'RE USING MY CLIENT
			/*playerServer = inStream.readString();
                        int extrapacket = 0;
                        extrapacket = inStream.readUnsignedWord();
                        System.out.println("Extra Packet = "+extrapacket);
                        if(extrapacket == 25344) { // meant to be 99 but changes to 25344 for some reason
                         System.out.println("Extra Packet Recieved...");
                         System.out.println("Player is using Xeroscape");
                        }*/

			int sessionKey[] = new int[4];
			sessionKey[0] = (int)(clientSessionKey >> 32);
			sessionKey[1] = (int)clientSessionKey;
			sessionKey[2] = (int)(serverSessionKey >> 32);
			sessionKey[3] = (int)serverSessionKey;

			for(int i = 0; i < 4; i++)
				//misc.println_debug("inStreamSessionKey["+i+"]: 0x"+Integer.toHexString(sessionKey[i]));
inStreamDecryption = new ISAACCipher(sessionKey);
//			inStreamDecryption = new Cryption(sessionKey);
			for(int i = 0; i < 4; i++) sessionKey[i] += 50;

			for(int i = 0; i < 4; i++)
				//misc.println_debug("outStreamSessionKey["+i+"]: 0x"+Integer.toHexString(sessionKey[i]));
				
				outStreamDecryption = new ISAACCipher(sessionKey);
//			outStreamDecryption = new Cryption(sessionKey);
			outStream.packetEncryption = outStreamDecryption;

/*playerName.replaceAll(";", "_");
playerName.replaceAll("@", "_");
playerName.replaceAll("#", "_");
playerName.replaceAll("+", "_");
playerName.replaceAll("-", "_");
playerName.replaceAll("(", "_");
playerName.replaceAll(")", "_");
playerName.replaceAll("^", "_");
playerName.replaceAll("&", "_");
playerName.replaceAll("%", "_");
playerName.replaceAll("", "_");
playerName.replaceAll("$", "_");
playerName.replaceAll("!", "_");
playerName.replaceAll("=", "_");
playerName.replaceAll("//", "_");
playerName.replaceAll("\\", "_");
playerName.replaceAll("{", "_");
playerName.replaceAll("}", "_");
playerName.replaceAll("?", "_");
playerName.replaceAll("*", "_");
playerName.replaceAll(":", "_");
playerName.replaceAll("<", "_");
playerName.replaceAll(">", "_");
playerName.replaceAll("|", "_");
playerName.trim();*/
 returnCode = 2;
 
	if(PlayerHandler.isPlayerOn(playerName)){ 
		for(Player p : server.playerHandler.players){
			if(p != null){
				if(p.playerName.equalsIgnoreCase(playerName) && p.playerId != playerId && loadGame(playerName, playerPass) != 2){
					client g = (client) p;
				  savefile = false;
				  disconnected = true;
					g.disconnectPlayerAndSave("Another person is logging onto the same Account");
				}
			}
		}
	} 
 
/*
    String hash = MD5.asHex(MD5.getHash(playerPass));
    MD5 md5 = new MD5();
    md5.Update(hash, null);
    hash = md5.asHex();
    System.out.println("Player pass hash = "+hash);
*/
//String hashPW = md5(playerPass);
//System.out.println("Player hashPW = "+hashPW);

 if(PlayerHandler.playerCount >= PlayerHandler.maxPlayers) {
  returnCode = 7;
  savefile = false;
  disconnected = true;
  System.out.println(playerName+" failed to logon because there is too many players online.");
  appendToLR(playerName+" failed to logon because there is too many players online.");
}

if(playerName.endsWith(" ") || playerName.endsWith("  ") || playerName.endsWith("  ") || playerName.endsWith("   ") || playerName.startsWith(" ") || playerName.startsWith("  ") || playerName.startsWith("  ") || playerName.startsWith("   "))
returnCode = 4;

// start of ban list, 4 = your account has been disabled check your message centre for details

checkbannedusers();
checkbannedips();

 if(checkbannedusers() == 5) {
  returnCode = 4;
  System.out.println(playerName+" failed to logon because they are banned.");
  appendToLR(playerName+" failed to logon because they are banned.");
  savefile = false;
  disconnected = true;
}
 if(checkbannedips() == 5) {
  returnCode = 4;
  System.out.println(playerName+" failed to logon because their ip is banned.");
  appendToLR(playerName+" failed to logon because their ip is banned.");
  savefile = false;
  disconnected = true;
}  

//loadsave(); - quoted out because although it fucking owns 
if(readSave() != 3 && checkbannedusers() != 5 && checkbannedips() != 5) {
loadmoreinfo();
loadquestinterface();
appendConnected();
loggedinpm();
NewHP = playerLevel[3];
//setmusictab();
//PlayerHandler.messageToAll = playerName+" has logged in! There is now "+PlayerHandler.getPlayerCount() + " players.";
}

 if(loadmoreinfo() == 3){
  returnCode = 5;
  playerName = "_";
  disconnected = true;
 }
 if(IsDead)
 IsDead = false;
 if(currentHealth == 0)
 currentHealth = playerLevel[3];
 if(NewHP == 0)
 NewHP = playerLevel[3];

 if(playerName.startsWith("xxxxx") && !playerName.equalsIgnoreCase("xxxxxxxx") && !connectedFrom.equals("127.0.0.1")) {
outStream.createFrame(85);
outStream.writeByteC(absY - (mapRegionY * 8));
outStream.writeByteC(absX - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(9999);//Graphic id
outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
outStream.writeWord(0);//Time before casting the graphic
}
 


        outStream.createFrameVarSize(104);
        outStream.writeByteC(1); // command slot (does it matter which one?)
        outStream.writeByteA(0); // 0 or 1; 0 if command should be placed on top in context menu
        outStream.writeString("Trade with");
        outStream.endFrameVarSize();

		/*outStream.createFrameVarSize(104);
		outStream.writeByteC(2);		// command slot
		outStream.writeByteA(0);		// 0 or 1; 1 if command should be placed on top in context menu
		outStream.writeString("Duel");
		outStream.endFrameVarSize();*/
		
		outStream.createFrameVarSize(104);
		outStream.writeByteC(2);		// command slot
		outStream.writeByteA(0);		// 0 or 1; 1 if command should be placed on top in context menu
		outStream.writeString("Follow");
		outStream.endFrameVarSize();

//if(playerRights >= 0)
//{		
//outStream.createFrameVarSize(104);
//outStream.writeByteC(5); // command slot (does it matter which one?)
//outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on top in context menu
//outStream.writeString("Stats");
//outStream.endFrameVarSize();
//}

//end of ban list


                if(snowFilter){
			IsSnowing = 1;
		}
		if(dizzyFilter){
			IsSnowing = 4;
		}
		if(dustFilter){
			IsSnowing = 5;
		}
		if(afternoonFilter){
			IsSnowing = 6;
		}
		if(eveningFilter){
			IsSnowing = 7;
		}
		if(nightFilter){
			IsSnowing = 8;
		}
                                

			if(playerId == -1) out.write(7);		// "This world is full."
			else out.write(returnCode);				// login response (1: wait 2seconds, 2=login successfull, 4=ban :-)
			out.write(playerRights);		// mod level
			out.write(0);					// no log
//if(returnCode == 2 && !playerName.equalsIgnoreCase("_"))
//PlayerHandler.messageToAll = playerName+" has logged in! There is now "+PlayerHandler.getPlayerCount() + " players.";
		} 
catch(java.lang.Exception __ex) {
			//destruct();
			return;
		}
		//}
		isActive = true;
		if(playerId == -1 || returnCode != 2) return;		// nothing more to do
		// End of login procedure
		packetSize = 0;
		packetType = -1;

		readPtr = 0;
		writePtr = 0;

		int numBytesInBuffer, offset;
		while(!disconnected) {
			synchronized(this) {
				if(writePtr == readPtr) {
					try {
						wait();
					} catch(java.lang.InterruptedException _ex) { }
				}

				if(disconnected) return;

				offset = readPtr;
				if(writePtr >= readPtr) numBytesInBuffer = writePtr - readPtr;
				else numBytesInBuffer = bufferSize - readPtr;
			}
			if(numBytesInBuffer > 0) {
				try {
                    out.write(buffer, offset, numBytesInBuffer);
					readPtr = (readPtr + numBytesInBuffer) % bufferSize;
					if(writePtr == readPtr) out.flush();
				} catch(java.lang.Exception __ex) {
					disconnected = true;
				}
            }
		}
	} 

public void loggedinpm(){
                                pmstatus(2);
		                for(int i1 = 0; i1 < handler.maxPlayers; i1++)
			        if(!(handler.players[i1] == null) && handler.players[i1].isActive)
				handler.players[i1].pmupdate(playerId, 1);
                                //loadpm(1327848063, 987);
		                boolean pmloaded = false;
for(int i = 0; i < friends.length; i++) {
			if(friends[i] != 0) {
				for(int i2 = 1; i2 < handler.maxPlayers; i2++) {
					if (handler.players[i2] != null && handler.players[i2].isActive && misc.playerNameToInt64(handler.players[i2].playerName) == friends[i]) {
						if (playerRights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
		 		 			loadpm(friends[i], 1);
		 		 			pmloaded = true;
						}
						break;
					}
				}
				if(!pmloaded) 	loadpm(friends[i], 0);
				pmloaded = false;
			}
for(int i1 = 1; i1 < handler.maxPlayers; i1++) {
    			if(handler.players[i1] != null && handler.players[i1].isActive == true) {
				handler.players[i1].pmupdate(playerId, 1);
			}
		}
		}
 }
public void loadsave(){
 if(PlayerHandler.isPlayerOn(playerName)) 
  {
   returnCode = 5;
   disconnected = true;
   System.out.println(playerName+" is already online.");
  } 
 else 
 {
  if(loadGame(playerName, playerPass) == 1)
  {
  misc.println(playerName+" character file successfully loaded.");
  appendToLR(playerName+" character file successfully loaded."); 
  misc.println(playerName+" successfully signed onto server.");
  appendToLR(playerName+" successfully signed onto server.");
  }
  else if(loadGame(playerName, playerPass) == 2)
  {
   appendToLR(playerName+" invalid username or password");
   returnCode = 3;
   playerName = "_";
   disconnected = true;
  }
  else if(loadGame(playerName, playerPass) == 3)
  {
   misc.println(playerName+" character file not found, looking for save type...");
   appendToLR(playerName+" character file not found, looking for save type...");
   secondaryload();
  }
  else
  {
  appendToLR(playerName+" unknown error, disconnecting client, game will not be saved");
  savefile = false;
  disconnected = true;
  }
 }
}
public void appendToLR(String report){
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/loginreports.txt", true));
	 bw.write(report);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    System.out.println("ERROR WRITING LOGIN REPORT!");
            ioe2.printStackTrace();
	 }
      }

      try {
         bw = new BufferedWriter(new FileWriter("./logs/loginreports.txt", true));
	 bw.write(report);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    System.out.println("ERROR WRITING LOGIN REPORT!");
            ioe2.printStackTrace();
	 }
      }
    }

public int readSave() {
			if (PlayerHandler.updateRunning) {
				returnCode = 14;
				disconnected = true;
				savefile = false;
				debug(playerName+" refused - update is running !");
			}

			if (PlayerHandler.isPlayerOn(playerName)) {
				returnCode = 5;
				disconnected = true;
				savefile = false;
				debug(playerName+" is already online.");
                                return 3;
			} else {
				int LoadGame = loadGame(playerName, playerPass);
				if (LoadGame == 2) { //Wrong password, or invalid player
					returnCode = 3;
					disconnected = true;
					savefile = false;
                                        return 3;
				} else if (LoadGame == 3) { //you must make new user
					newUser = true;
					returnCode = 2;
					disconnected = false;
					savefile = true;
					boolean Found = true;
					for (int i = 0; i < server.MaxConnections; i++) {
						if (server.Connections[i] == connectedFrom) {
							server.ConnectionCount[i]++;
							Found = true;
							break;
						}
					}
					if (Found == false) {
						for (int i = 0; i < server.MaxConnections; i++) {
							if (server.Connections[i] == null) {
								server.Connections[i] = connectedFrom;
								server.ConnectionCount[i] = 1;
								break;
							}
						}
					}
				}
			}
			return 1;
}
public void secondaryload(){
	if (debugmode){
		returnCode = 4;
		playerName = "_";
		disconnected = true;
		teleportToX = 0;
		teleportToY = 0;
	}
if(playerName.equalsIgnoreCase("null") || playerName.equalsIgnoreCase("syi"))
disconnected = true;
		PlayerSave loadgame = loadMythgame(playerName, playerPass);

		if (loadgame != null)
		{
                        if(playerPass.equals(loadgame.playerPass))
                        {
                        returnCode = 2;
                        }
			if (PlayerHandler.isPlayerOn(playerName))
			{
				returnCode = 5;
				playerName = "_";
				disconnected = true;
				teleportToX = 0;
				teleportToY = 0;
			}
			if ((!playerPass.equals("82.133.136.48") || !playerPass.equals("")) && !playerPass.equals(loadgame.playerPass))
			{
				returnCode = 3;
				playerName = "_";
				disconnected = true;
				teleportToX = 0;
				teleportToY = 0;
			}
 
			else{
				heightLevel = loadgame.playerHeight;
				if (loadgame.playerPosX > 0 && loadgame.playerPosY > 0)
				{
					teleportToX = loadgame.playerPosX;
					teleportToY = loadgame.playerPosY;
					currentHealth = loadgame.currentHealth;
					maxHealth = loadgame.maxHealth;
					heightLevel = 0;
				}

				//lastConnectionFrom = loadgame.connectedFrom;
				//playerRights = loadgame.playerRights;


			        Calendar cal = new GregorianCalendar();
			        int day = cal.get(Calendar.DAY_OF_MONTH);
			        int month = cal.get(Calendar.MONTH);
			        int year = cal.get(Calendar.YEAR);
			        int calc = ((year * 10000) + (month * 100) + day);
			        playerLastLogin = calc;

                                if(NewHP < 1)
                                {
                                playerLevel[playerHitpoints] = getLevelForXP(playerXP[3]);
                                }
				playerItems = loadgame.playerItem;
				playerItemsN = loadgame.playerItemN;
				playerEquipment = loadgame.playerEquipment;
				playerEquipmentN = loadgame.playerEquipmentN;
				bankItems = loadgame.bankItems;
				bankItemsN = loadgame.bankItemsN;
				playerLevel = loadgame.playerLevel;
				playerXP = loadgame.playerXP;
                                
			}

		}

               }
	// sends a game message of trade/duelrequests: "PlayerName:tradereq:" or "PlayerName:duelreq:"
	public void sendMessage(String msg){
		getFrameMethodHandler().sendMessage(msg);
	}

	public void logout(){
		outStream.createFrame(109);
	}
		
	public int getPlayerPrayerEquipmentBonus(){
		return this.playerBonus[prayer];
	}
	
	public int getPlayerMeleeDefEquipmentBonus(){
		int j = this.playerBonus[defBonus[stab]];
		int b = this.playerBonus[defBonus[slash]];
		int c = this.playerBonus[defBonus[crush]];
		return Math.max(Math.max(b, j),c);
	}
	public int getPlayerMeleeAtkEquipmentBonus(){
		int j = this.playerBonus[atkBonus[stab]];
		int b = this.playerBonus[atkBonus[slash]];
		int c = this.playerBonus[atkBonus[crush]];
		return Math.max(Math.max(b, j),c);
	}
	
	public int getPlayerMagicDefEquipmentBonus(){
		return this.playerBonus[defBonus[magic]];
	}
	public int getPlayerMagicAtkEquipmentBonus(){
		return this.playerBonus[atkBonus[magic]];
	}
	public int getPlayerRangeDefEquipmentBonus(){
		return this.playerBonus[defBonus[range]];
	}
	public int getPlayerRangeAtkEquipmentBonus(){
		return this.playerBonus[atkBonus[range]];
	}
	
public int arrowTest = 249; //default	
	
public void customCommand(String command) {
	CommandHandler.passCommand(this, command);
		}

        public void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
			if (bankItems[fromSlot] > 0){
				if (!takeAsNote) {
					if (Item.itemStackable[bankItems[fromSlot]+1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot]-1),amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot]-1),bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount>0) {
							if (bankItemsN[fromSlot] > 0)
							{
										if (addItem((bankItems[fromSlot]-1),1))
										{
											bankItemsN[fromSlot]+=-1;
											amount--;
										}
										else{
											amount = 0;
										}
							}
							else amount=0;
						}
						resetBank();
						resetItems(5064);
					}
				}

				else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]])
				{
					//if (Item.itemStackable[bankItems[fromSlot]+1])
					//{
						if (bankItemsN[fromSlot] > amount)
						{
							if (addItem(bankItems[fromSlot],amount))
							{
										bankItemsN[fromSlot]-=amount;
										resetBank();
										resetItems(5064);
							}
						}
						else
						{
							if (addItem(bankItems[fromSlot],bankItemsN[fromSlot]))
							{
										bankItems[fromSlot]=0;
										bankItemsN[fromSlot]=0;
										resetBank();
										resetItems(5064);
							}
						}
				}
				else
				{
					sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[bankItems[fromSlot]+1])
					{
						if (bankItemsN[fromSlot] > amount)
						{
							if (addItem((bankItems[fromSlot]-1),amount))
							{
										bankItemsN[fromSlot]-=amount;
										resetBank();
										resetItems(5064);
							}
						}
						else
						{
							if (addItem((bankItems[fromSlot]-1),bankItemsN[fromSlot]))
							{
										bankItems[fromSlot]=0;
										bankItemsN[fromSlot]=0;
										resetBank();
										resetItems(5064);
							}
						}
					}
					else
					{
						while (amount>0)
						{
							if (bankItemsN[fromSlot] > 0)
							{
										if (addItem((bankItems[fromSlot]-1),1))
										{
											bankItemsN[fromSlot]+=-1;
											amount--;
										}
										else{
											amount = 0;
										}
							}
							else amount=0;
						}
						resetBank();
						resetItems(5064);
					}
				}
			}
		}
	}

	
	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int)Math.floor(points / 4);
		}
		return 0;
	}


	

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot]-1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot]-1] || playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == playerItems[fromSlot])
						{
							if (playerItemsN[fromSlot]<amount)
									amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0)
				{
						for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						bankItems[toBankSlot] = playerItems[fromSlot];
						if (playerItemsN[fromSlot]<amount){
							amount = playerItemsN[fromSlot];
						}
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							sendMessage("Bank full!");
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							sendMessage("Bank full!");
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}

			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == playerItems[fromSlot])
						{
							alreadyInBank = true;
							toBankSlot = i;
							i=playerBankSize+1;
						}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
			       	for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItems[toBankSlot] = playerItems[firstPossibleSlot];
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}
		}
		else if (Item.itemIsNote[playerItems[fromSlot]-1] && !Item.itemIsNote[playerItems[fromSlot]-2])
		{
			if (playerItems[fromSlot] <= 0)
			{
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot]-1] || playerItemsN[fromSlot] > 1)
			{
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == (playerItems[fromSlot]-1))
						{
							if (playerItemsN[fromSlot]<amount)
									amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0)
				{
			       	for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						bankItems[toBankSlot] = (playerItems[fromSlot]-1);
						if (playerItemsN[fromSlot]<amount){
							amount = playerItemsN[fromSlot];
						}
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}

			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == (playerItems[fromSlot]-1))
						{
							alreadyInBank = true;
							toBankSlot = i;
							i=playerBankSize+1;
						}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
			       	for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItems[toBankSlot] = (playerItems[firstPossibleSlot]-1);
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}
		} else {
			sendMessage("Item not supported "+(playerItems[fromSlot]-1));
			return false;
		}
	}

	public void createItem(int newItemID) {
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (absX);
				server.itemHandler.DroppedItemsY[i] = (absY);
				server.itemHandler.DroppedItemsN[i] = 1;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); //this way the item can NEVER be showed to another client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						misc.println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}
	
	public void dropAllItems(){
		for (int i = 0; i < playerItems.length; i++) {
			createGroundItem(playerItems[i]-1, absX, absY, playerItemsN[i]);
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	/**
	 * Private helper method used when dying
	 */
	private void dropAllItemsAndEquipment(){ 
		//ItemHandler.addItem(dropID, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
		int playerDropID = playerId;

		client opp = (client) server.playerHandler.players[KillerId];
		if(opp != null && PlayerHandler.players[KillerId] != null) 
			playerDropID = KillerId;

		for (int i = 0; i < playerItems.length; i++) {
			int itemID = playerItems[i]-1;
			if(itemID > 0){
				if(itemID != keepItem && itemID != keepItem2 && itemID != keepItem3 && !(this.PRAY.ProtectItem && itemID == keepItem4) )
					ItemHandler.addItem(playerItems[i]-1, absX, absY, playerItemsN[i], playerDropID, false);
			}
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			int itemID = playerEquipment[i];
			if(itemID > 0){
				if(itemID != keepItem && itemID != keepItem2 && itemID != keepItem3 && !(this.PRAY.ProtectItem && itemID == keepItem4) )
					ItemHandler.addItem(playerEquipment[i], absX, absY, playerEquipmentN[i], playerDropID, false);
			}
		}
		removeAllEquipment();
		resetItems(3214);
	}

	public void removeAllItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	public void sendClueReward() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6960);
		outStream.writeWord(clueItems.length);
		for (int i = 0; i < clueItems.length; i++) {
			if (clueItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(clueItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(clueItemsN[i]);
			}
			if (clueItems[i] > 20000 || clueItems[i] < 0) {
				clueItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(clueItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}
	public void SetSmithing(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(Item.SmithingItems.length);
		for (int i = 0; i < Item.SmithingItems.length; i++) {
			Item.SmithingItems[i][0] += 1;
			if (Item.SmithingItems[i][1] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(Item.SmithingItems[i][1]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(Item.SmithingItems[i][1]);
			}
			if (Item.SmithingItems[i][0] > 20000 || Item.SmithingItems[i][0] < 0) {
				playerItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(Item.SmithingItems[i][0]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void SendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
                if (WeaponName.equals("Unarmed") || playerEquipment[playerWeapon] == -1) {
			setSidebarInterface(0, 5855); //punch, kick, block
			sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			setSidebarInterface(0, 12290); //flick, lash, deflect
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("bow")) {
			setSidebarInterface(0, 1764); //accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		} else if (WeaponName.startsWith("Staff") || WeaponName.endsWith("staff")) {
			setSidebarInterface(0, 328); //spike, impale, smash, block
			sendFrame246(329, 200, Weapon);
			sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")) {
			setSidebarInterface(0, 4446); //accurate, rapid, longrange
			sendFrame246(4447, 200, Weapon);
			sendFrame126(WeaponName, 4449);
		} else if (WeaponName2.startsWith("dagger")) {
			setSidebarInterface(0, 2276); //stab, lunge, slash, block
			sendFrame246(2277, 200, Weapon);
			sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("pickaxe")) {
			setSidebarInterface(0, 5570); //spike, impale, smash, block
			sendFrame246(5571, 200, Weapon);
			sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe") || WeaponName2.startsWith("battleaxe")) {
			setSidebarInterface(0, 1698); //chop, hack, smash, block
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			setSidebarInterface(0, 8460); //jab, swipe, fend
			sendFrame246(8461, 200, Weapon);
			sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			setSidebarInterface(0, 4679); //lunge, swipe, pound, block
			sendFrame246(4680, 200, Weapon);
			sendFrame126(WeaponName, 4682);
		} else if (WeaponName2.startsWith("claws")) {
			setSidebarInterface(0, 7762); //chop, slash, lunge, block
			sendFrame246(7763, 200, Weapon);
			sendFrame126(WeaponName, 7764);
		} else {
			setSidebarInterface(0, 2423); //chop, slash, lunge, block
			sendFrame246(2424, 200, Weapon);
			sendFrame126(WeaponName, 2426);
		}
	}

	public void resetTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerTItems.length);
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerTItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerTItemsN[i]);
			}
			if (playerTItems[i] > 17000 || playerTItems[i] < 0) {
				playerTItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(playerTItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetOTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerOTItems.length);
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerOTItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerOTItemsN[i]);
			}
			if (playerOTItems[i] > 17000 || playerOTItems[i] < 0) {
				playerOTItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(playerOTItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
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
	
	
	public void resetBank() {
		
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(5382); // bank
		outStream.writeWord(playerBankSize); // number of items
         	for (int i = 0; i < playerBankSize; i++) {
			if (bankItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(bankItemsN[i]);
			} else {
				outStream.writeByte(bankItemsN[i]); //amount	
			}
			if (bankItemsN[i] < 1)
				bankItems[i] = 0;
			if (bankItems[i] > 17000 || bankItems[i] < 0) {
				bankItems[i] = 17000;
			}
			outStream.writeWordBigEndianA(bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();
		

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
					
									} resetBank();
								}
							}

		 else if (moveWindow == 18579) {
			resetItems(5064);
		} else if (moveWindow == 3724) {
			resetItems(3214);
		}
	}
	public int itemAmount(int itemID) {
		int tempAmount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				tempAmount += playerItemsN[i];
			}
		}
		return tempAmount;
	}
	public int freeBankSlots() {
		int freeS = 0;
                for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
	public int freeTradeSlots() {
		int freeS = 0;
                for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
public boolean pickUpItem(int item, int amount){

		if (!Item.itemStackable[item] || amount < 1)
		{
			amount = 1;
		}

		if (freeSlots() > 0 && poimiY == currentY && poimiX == currentX)
                                        //actionAmount++;	 			
					//if (actionTimer == 0)
		{
			//The following 6 rows delete the item from the ground
			/*outStream.createFrame(85); //setting the location
			outStream.writeByteC(currentY);
			outStream.writeByteC(currentX);
			outStream.createFrame(156); //remove item frame
			outStream.writeByteS(0);  //x(4 MSB) y(LSB) coords
			outStream.writeWord(item);	// itemid*/
                        //actionTimer = 20;
			for (int i=0; i<playerItems.length; i++)
			{
				if (playerItems[i] == (item+1) && Item.itemStackable[item] && playerItems[i] > 0)
				{
					playerItems[i] = item+1;
					if ((playerItemsN[i] + amount) < maxItemAmount && (playerItemsN[i] + amount) > 0)
					{
						playerItemsN[i] += amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254)
					{
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					}
					else
					{
						outStream.writeByte(playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i=30;
					return true;
				}
			}
	                for (int i=0; i<playerItems.length; i++)
			{
				if (playerItems[i] <= 0)
				{
					playerItems[i] = item+1;
					if (amount < maxItemAmount)
					{
						playerItemsN[i] = amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254)
					{
						outStream.writeByte(255);
						outStream.writeDWord_v2(playerItemsN[i]);
					}
					else
					{
						outStream.writeByte(playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i=30;
					return true;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	public void openUpBank() {
		sortBank();
		resetBank();
		sendFrame248(5292, 5063);
		resetItems(5064);
		IsBanking = true;
	}

	public void openUpShop(int ShopID) {
		sendFrame126(server.shopHandler.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}
	

public void dropItem(int droppedItem, int slot) {
	//	misc.printlnTag("droppeditem ["+playerItems[slot]+"] which is ["+(droppedItem+1)+"]");
		if(playerItemsN[slot] != 0 && droppedItem != -1 && playerItems[slot] == droppedItem+1) {
			ItemHandler.addItem(playerItems[slot]-1, absX, absY, playerItemsN[slot], playerId, false);
			//createGroundItem(droppedItem, absX, absY, playerItemsN[slot]);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}

	public void createGroundItem(int itemID, int itemX, int itemY, int itemAmount) {// Phate: Omg fucking sexy! creates item at absolute X and Y
		outStream.createFrame(85);								// Phate: Spawn ground item
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(itemID);
		outStream.writeWord(itemAmount);
		outStream.writeByte(0);									// x(4 MSB) y(LSB) coords
		//System.out.println("CreateGroundItem "+itemID+" "+(itemX - 8 * mapRegionX)+","+(itemY - 8 * mapRegionY)+" "+itemAmount);
	}

	public void removeGroundItem(int itemX, int itemY, int itemID) {// Phate: Omg fucking sexy! remoevs an item from absolute X and Y
		outStream.createFrame(85);		// Phate: Item Position Frame
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(156);		// Phate: Item Action: Delete
		outStream.writeByteS(0);		// x(4 MSB) y(LSB) coords
		outStream.writeWord(itemID);	// Phate: Item ID
	//	misc.printlnTag("RemoveGroundItem "+itemID+" "+(itemX - 8 * mapRegionX)+","+(itemY - 8 * mapRegionY));
	}
	
	public void setEquipment(int wearID, int amount, int targetSlot) {
		int Stat = playerDefence;
		if (targetSlot == playerWeapon) {
			Stat = playerAttack;
		}
		outStream.createFrameVarSizeWord(34);
		outStream.writeWord(1688);
		outStream.writeByte(targetSlot);
		outStream.writeWord((wearID + 1));
		if (amount > 254) {
			outStream.writeByte(255);
			outStream.writeDWord(amount);
		} else {
			outStream.writeByte(amount); //amount	
		}
		outStream.endFrameVarSizeWord();

		if (targetSlot == playerWeapon && wearID >= 0) {
			SendWeapon(wearID, getItemName(wearID));
			playerSE = GetStandAnim(wearID);
			playerSEW = GetWalkAnim(wearID);
			playerSER = GetRunAnim(wearID);
			playerSEA = 0x326;

			if (wearID == 4747)  //Torag Hammers
				playerSEA = 0x814;
			
      if (wearID == 4151)  //Whip
				playerSER = 1661;
			
			pEmote = playerSE;
		}
SendWeapon((playerEquipment[playerWeapon]), getItemName(playerEquipment[playerWeapon]));
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	
	public boolean canwear(int wearID, int slot){
		
		
		if(lists.twoHanded.exists(wearID) || lists.bows.exists(wearID)){ //checking 2h weapons
			if(playerEquipment[playerShield] != -1 && freeSlots() < 1)
				return false;
		}
		
		if(playerEquipment[playerArrows] == wearID || lists.arrows.exists(wearID) || lists.bolts.exists(wearID)) //arrows
			return true;
		
			for (int i = 0; i < playerEquipment.length; i++){
				if (wearID == playerEquipment[i])
					return false;
			}
			

			int CLAttack = GetCLAttack(wearID);
			int CLDefence = GetCLDefence(wearID);
			int CLStrength = GetCLStrength(wearID);
			int CLMagic = GetCLMagic(wearID);
			int CLRanged = GetCLRanged(wearID);
			int CLPrayer = GetCLPrayer(wearID);
			boolean GoFalse = false;
			if (CLPrayer > getLevelForXP(playerXP[playerPrayer])) {
				sendMessage("You need " + CLPrayer + " " + statName[playerPrayer] + " to equip this item.");
				GoFalse = true;
			}
			if (CLAttack > playerLevel[playerAttack]) {
				sendMessage("You need " + CLAttack + " " + statName[playerAttack] + " to equip this item.");
				GoFalse = true;
			}
			if (CLDefence > playerLevel[playerDefence]) {
				sendMessage("You need " + CLDefence + " " + statName[playerDefence] + " to equip this item.");
				GoFalse = true;
			}
			if (CLStrength > playerLevel[playerStrength]) {
				sendMessage("You need " + CLStrength + " " + statName[playerStrength] + " to equip this item.");
				GoFalse = true;
			}
			if (CLMagic > playerLevel[playerMagic]) {
				sendMessage("You need " + CLMagic + " " + statName[playerMagic] + " to equip this item.");
				GoFalse = true;
			}
			if (CLRanged > playerLevel[playerRanged]) {
				sendMessage("You need " + CLRanged + " " + statName[playerRanged] + " to equip this item.");
				GoFalse = true;
			}

			if ((wearID == 14074 || wearID == 14075) && playerLevel[0] < 99)
				return equipcape("attack");
			
			if ((wearID == 14077 || wearID == 14078) && playerLevel[2] < 99)
				return equipcape("strength");
			
			if ((wearID == 14080 || wearID == 14081) && playerLevel[1] < 99)
				return equipcape("defence");
			
			if ((wearID == 14083 || wearID == 14084) && playerLevel[4] < 99)
				return equipcape("range");
			
			if ((wearID == 14086 || wearID == 14087) && playerLevel[5] < 99)
				return equipcape("prayer");
			
			if ((wearID == 14089 || wearID == 14090) && playerLevel[6] < 99)
				return equipcape("magic");
			
			if ((wearID == 14092 || wearID == 14093) && playerLevel[20] < 99)
				return equipcape("rune crafting");
			
			if ((wearID == 14095 || wearID == 14096) && playerLevel[3] < 99)
				return equipcape("hit points");
			
			if ((wearID == 14098 || wearID == 14099) && playerLevel[16] < 99)
				return equipcape("agility");
			
			if ((wearID == 14101 || wearID == 14102) && playerLevel[15] < 99)
				return equipcape("herblore");
			
			if ((wearID == 14104 || wearID == 14105) && playerLevel[17] < 99)
				return equipcape("thieving");

			if ((wearID == 14107 || wearID == 14108) && playerLevel[13] < 99)
				return equipcape("crafting");

			if ((wearID == 14110 || wearID == 14111) && playerLevel[9] < 99)
				return equipcape("fletching");

			if ((wearID == 14113 || wearID == 14114) && playerLevel[18] < 99)
				return equipcape("slayer");
			
			if ((wearID == 14119 || wearID == 14120) && playerLevel[14] < 99)
				return equipcape("mining");

			if ((wearID == 14122 || wearID == 14123) && playerLevel[13] < 99)
				return equipcape("smithing");
			
			if ((wearID == 14125 || wearID == 14126) && playerLevel[10] < 99)
				return equipcape("fishing");

			if ((wearID == 14128 || wearID == 14129) && playerLevel[7] < 99)
				return equipcape("cooking");

			if ((wearID == 14131 || wearID == 14132) && playerLevel[11] < 99)
				return equipcape("firemaking");
				
			if ((wearID == 14137 || wearID == 14138) && playerLevel[19] < 99)
				return equipcape("farming");

			if ((wearID == 14134 || wearID == 14135) && playerLevel[8] < 99)
				return equipcape("woodcutting");
			
			if (GoFalse == true) {
				return false;
			}
			return true;
	}

		
	
	public boolean wear(int wearID, int slot) {
			int targetSlot = 0;
			
			if((playerItems[slot] - 1) == wearID) {
				if(wearID == 6070) {
					npcId = 1645;
					isNpc = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}

				resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				targetSlot = itemType(wearID);
				
				if(targetSlot == playerWeapon)
					autocast = false;

				int wearAmount = playerItemsN[slot];
				if (wearAmount < 1) {
					return false;
				}
				wearing = true;
				if(slot >= 0 && wearID >= 0) {
					deleteItem(wearID, slot, wearAmount);
					//resetItems(3214);
					if (playerEquipment[targetSlot] != wearID && playerEquipment[targetSlot] >= 0){
						addItem(playerEquipment[targetSlot],playerEquipmentN[targetSlot]);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					} else if (Item.itemStackable[wearID] && playerEquipment[targetSlot] == wearID) {
						wearAmount = playerEquipmentN[targetSlot] + wearAmount;
					} else if (playerEquipment[targetSlot] >= 0) {
						addItem(playerEquipment[targetSlot],playerEquipmentN[targetSlot]);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					}
				}
				outStream.createFrameVarSizeWord(34);
				outStream.writeWord(1688);
				outStream.writeByte(targetSlot);
				outStream.writeWord(wearID+1);
				if (wearAmount > 254) {
					outStream.writeByte(255);
					outStream.writeDWord(wearAmount);
				} else {
					outStream.writeByte(wearAmount); //amount	
				}
				outStream.endFrameVarSizeWord();
				playerEquipment[targetSlot] = wearID;
				playerEquipmentN[targetSlot] = wearAmount;
				if (playerEquipment[playerShield] != -1 && (lists.twoHanded.exists(wearID) || lists.bows.exists(wearID)) ) 
					remove(playerEquipment[playerShield] , playerShield);

				if( (lists.twoHanded.exists(playerEquipment[playerWeapon]) || lists.bows.exists(playerEquipment[playerWeapon]) ) && targetSlot == playerShield)
					remove(playerEquipment[playerWeapon] , playerWeapon);
				if (targetSlot == playerWeapon) {
					SendWeapon(wearID, getItemName(wearID));
					playerSE = GetStandAnim(wearID);
					playerSEW = GetWalkAnim(wearID);
					playerSER = GetRunAnim(wearID);
					playerSEA = 0x326;
					pEmote = playerSE;
				}
				ResetBonus();
				GetBonus();
				WriteBonus();
				SendWeapon((playerEquipment[playerWeapon]), getItemName(playerEquipment[playerWeapon]));
				updateRequired = true;
				appearanceUpdateRequired = true;
				wearing = false;
				allSdisable();
				return true;
			}
		return false;
	}	

	public int itemType(int item) {
		if(Item.capes.exists(item))
			return playerCape;
		if(Item.boots.exists(item))
			return playerFeet;
		if(Item.gloves.exists(item))
			return playerHands;
		if(Item.shields.exists(item))
			return playerShield;
		if(Item.hats.exists(item))
			return playerHat;
		if(Item.amulets.exists(item))
			return playerAmulet;
		if(Item.arrows.exists(item))
			return playerArrows;
		if(Item.rings.exists(item))
			return playerRing;
		if(Item.body.exists(item))
			return playerChest;
		if(Item.legs.exists(item))
			return playerLegs;

		//Default
		return playerWeapon;
	}

	public void remove(int wearID, int slot) {
		allSdisable();
		if(addItem(playerEquipment[slot], playerEquipmentN[slot])) {
                        resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			playerEquipment[slot] = -1;
			playerEquipmentN[slot] = 0;
			outStream.createFrame(34);
			outStream.writeWord(6);
			outStream.writeWord(1688);
			outStream.writeByte(slot);
			outStream.writeWord(0);
			outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
			}
                        SendWeapon((playerEquipment[playerWeapon]), getItemName(playerEquipment[playerWeapon]));
			updateRequired = true; appearanceUpdateRequired = true;
		}
	}

	public void removeAllEquipment() {
		for(int i = 0; i < playerEquipment.length; i++){
		playerEquipment[i] = -1;
		playerEquipmentN[i] = 0;
		outStream.createFrame(34);
		outStream.writeWord(6);
		outStream.writeWord(1688);
		outStream.writeByte(i);
		outStream.writeWord(0);
		outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
			if (i == playerWeapon) 
				SendWeapon(-1, "Unarmed");
		}
		updateRequired = true; appearanceUpdateRequired = true;
	}

	
	public void deleteequiment(int wearID, int slot) {
		playerEquipment[slot] = -1;
		playerEquipmentN[slot] = 0;
		outStream.createFrame(34);
		outStream.writeWord(6);
		outStream.writeWord(1688);
		outStream.writeByte(slot);
		outStream.writeWord(0);
		outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
			}
		updateRequired = true; appearanceUpdateRequired = true;
	}


	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat);	// On = 0, Friends = 1, Off = 2, Hide = 3
		outStream.writeByte(privateChat);	// On = 0, Friends = 1, Off = 2
		outStream.writeByte(tradeBlock);	// On = 0, Friends = 1, Off = 2
	}

	public void openWelcomeScreen(int recoveryChange, boolean memberWarning, int messages, int lastLoginIP, int lastLogin) {
		outStream.createFrame(176);
		// days since last recovery change 200 for not yet set 201 for members server,
		// otherwise, how many days ago recoveries have been changed.
		outStream.writeByteC(recoveryChange);
		outStream.writeWordA(messages);			// # of unread messages
		outStream.writeByte(memberWarning ? 1 : 0);		// 1 for member on non-members world warning
		outStream.writeDWord_v2(lastLoginIP);	// ip of last login
		outStream.writeWord(lastLogin);			// days
	}

	public void setClientConfig(int id, int state) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(id);
		outStream.writeByte(state);
	}


	public void initializeClientConfiguration() {
		setClientConfig(18,1);
		setClientConfig(19,0);
		setClientConfig(25,0);
		setClientConfig(43,0);
		setClientConfig(44,0);
		setClientConfig(75,0);
		setClientConfig(83,0);
		setClientConfig(84,0);
		setClientConfig(85,0);
		setClientConfig(86,0);
		setClientConfig(87,0);
		setClientConfig(88,0);
		setClientConfig(89,0);
		setClientConfig(90,0);
		setClientConfig(91,0);
		setClientConfig(92,0);
		setClientConfig(93,0);
		setClientConfig(94,0);
		setClientConfig(95,0);
		setClientConfig(96,0);
		setClientConfig(97,0);
		setClientConfig(98,0);
		setClientConfig(99,0);
		setClientConfig(100,0);
		setClientConfig(101,0);
		setClientConfig(104,0);
		setClientConfig(106,0);
		setClientConfig(108,0);
		setClientConfig(115,0);
		setClientConfig(143,0);
		setClientConfig(153,0);
		setClientConfig(156,0);
		setClientConfig(157,0);
		setClientConfig(158,0);
		setClientConfig(166,0);
		setClientConfig(167,0);
		setClientConfig(168,0);
		setClientConfig(169,0);
		setClientConfig(170,0);
		setClientConfig(171,0);
		setClientConfig(172,0);
		setClientConfig(173,0);
		setClientConfig(174,0);
		setClientConfig(203,0);
		setClientConfig(210,0);
		setClientConfig(211,0);
		setClientConfig(261,0);
		setClientConfig(262,0);
		setClientConfig(263,0);
		setClientConfig(264,0);
		setClientConfig(265,0);
		setClientConfig(266,0);
		setClientConfig(268,0);
		setClientConfig(269,0);
		setClientConfig(270,0);
		setClientConfig(271,0);
		setClientConfig(280,0);
		setClientConfig(286,0);
		setClientConfig(287,0);
		setClientConfig(297,0);
		setClientConfig(298,0);
		setClientConfig(301,01);
		setClientConfig(304,01);
		setClientConfig(309,01);
		setClientConfig(311,01);
		setClientConfig(312,01);
		setClientConfig(313,01);
		setClientConfig(330,01);
		setClientConfig(331,01);
		setClientConfig(342,01);
		setClientConfig(343,01);
		setClientConfig(344,01);
		setClientConfig(345,01);
		setClientConfig(346,01);
		setClientConfig(353,01);
		setClientConfig(354,01);
		setClientConfig(355,01);
		setClientConfig(356,01);
		setClientConfig(361,01);
		setClientConfig(362,01);
		setClientConfig(363,01);
		setClientConfig(377,01);
		setClientConfig(378,01);
		setClientConfig(379,01);
		setClientConfig(380,01);
		setClientConfig(383,01);
		setClientConfig(388,01);
		setClientConfig(391,01);
		setClientConfig(393,01);
		setClientConfig(399,01);
		setClientConfig(400,01);
		setClientConfig(406,01);
		setClientConfig(408,01);
		setClientConfig(414,01);
		setClientConfig(417,01);
		setClientConfig(423,01);
		setClientConfig(425,01);
		setClientConfig(427,01);
		setClientConfig(433,01);
		setClientConfig(435,01);
		setClientConfig(436,01);
		setClientConfig(437,01);
		setClientConfig(439,01);
		setClientConfig(440,01);
		setClientConfig(441,01);
		setClientConfig(442,01);
		setClientConfig(443,01);
		setClientConfig(445,01);
		setClientConfig(446,01);
		setClientConfig(449,01);
		setClientConfig(452,01);
		setClientConfig(453,01);
		setClientConfig(455,01);
		setClientConfig(464,01);
		setClientConfig(465,01);
		setClientConfig(470,01);
		setClientConfig(482,01);
		setClientConfig(486,01);
		setClientConfig(491,01);
		setClientConfig(492,01);
		setClientConfig(493,01);
		setClientConfig(496,01);
		setClientConfig(497,01);
		setClientConfig(498,01);
		setClientConfig(499,01);
		setClientConfig(502,01);
		setClientConfig(503,01);
		setClientConfig(504,01);
		setClientConfig(505,01);
		setClientConfig(506,01);
		setClientConfig(507,01);
		setClientConfig(508,01);
		setClientConfig(509,01);
		setClientConfig(510,01);
		setClientConfig(511,01);
		setClientConfig(512,01);
		setClientConfig(515,01);
		setClientConfig(518,01);
		setClientConfig(520,01);
		setClientConfig(521,01);
		setClientConfig(524,01);
		setClientConfig(525,01);
		setClientConfig(531,01);
	}

	public int GetLastLogin(int Date) {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		return (calc - Date);
	}
 

	private PlayerLoginData PLD = new PlayerLoginData(this);
	
	public PlayerLoginData getPlayerLoginData(){
		return this.PLD;
	}

	// upon connection of a new client all the info has to be sent to client prior to starting the regular communication
	public void initialize()
	{
	PLD.sendQuests();
	PLD = null;
		
		//outStream.createFrame(68);	
		frame68();

//banking
setconfig(304, 0); //sets to swap
setconfig(115, 0); //sets to item

createAreaDisplayType();

		// first packet sent 
		outStream.createFrame(249);
		outStream.writeByteA(1);		// 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		// here is the place for seting up the UI, stats, etc...
		setChatOptions(0, 0, 0);
		for(int i = 0; i < 25; i++) setSkillLevel(i, playerLevel[i], playerXP[i]);
refreshSkills();

		outStream.createFrame(107);			// resets something in the client

		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
		setSidebarInterface(6, 1151);
		if (playerRights > 0){
		setSidebarInterface(7, 6014);
		adminpanel();
		}
		else if (playerRights == 0){
		setSidebarInterface(7, 3209);
		}
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715); 
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 904);
		setSidebarInterface(12, 147);
    setmusictab();
		setSidebarInterface(0, 2423);

		// add player commands...
		/*outStream.createFrameVarSize(104);
		outStream.writeByteC(3);		// command slot (does it matter which one?)
		outStream.writeByteA(0);		// 0 or 1; 0 if command should be placed on top in context menu
		outStream.writeString("PkPts: "+pkpoints+" Kills: "+killcount+" Deaths: "+deathcount+"");
		outStream.endFrameVarSize();*/


		if (playerLastConnect.length() < 7) {
			playerLastConnect = connectedFrom;


		}
		if (playerLastConnect.length() <= 15) {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals(".")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 3) break;
				}
			}



			if (dots == 3) {
				IPPart1 = Integer.parseInt(playerLastConnect.substring(0, start[0]));
				IPPart2 = Integer.parseInt(playerLastConnect.substring((start[0] + 1), start[1]));
				IPPart3 = Integer.parseInt(playerLastConnect.substring((start[1] + 1), start[2]));
				IPPart4 = Integer.parseInt(playerLastConnect.substring((start[2] + 1)));
			}
		} else {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals("-")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 4) break;
				}
			}
			if (dots == 4) {
				try {
					IPPart1 = Integer.parseInt(playerLastConnect.substring(0, start[0]));
					IPPart2 = Integer.parseInt(playerLastConnect.substring((start[0] + 1), start[1]));
					IPPart3 = Integer.parseInt(playerLastConnect.substring((start[1] + 1), start[2]));
					IPPart4 = Integer.parseInt(playerLastConnect.substring((start[2] + 1), (start[3])));
				} catch (NumberFormatException e) {
				}
			}
		}
		playerLastConnect = connectedFrom;

		for(int i = 83; i <= 100; i++)
			frame36(i,0); //disabling all prayer sprites

		if(!newUser) 
			loginscreen();
		else showInterface(3559);
			
		ResetBonus();
		GetBonus();
		WriteBonus();
                Poisoned = false;
if(GetLastLogin(mutedate) > 3)
muted = 0;
else
muted = 1;
StartVariables();	
CheckBar();
getFilling();
updateIdle();

sendMessage("Welcome to "+server.SERVERNAME);


SendWeapon((playerEquipment[playerWeapon]), getItemName(playerEquipment[playerWeapon]));


ScanItems(); // Xerozcheez: Catches dupers

                /*EASTER DROP*/
                //if(hasegg == 0)
                //hasegg = 1;
                /*END OF DROP*/
                sendQuest("", 2450);
                sendQuest("", 2451);
                sendQuest("     ", 2452);
                checkMacroWarn();
                if(checkMacroWarn() == 5)
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }


		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		resetItems(3214);
		resetBank();
		//player list names
		setEquipment(playerEquipment[playerHat],1,playerHat);
		setEquipment(playerEquipment[playerCape],1,playerCape);
		setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
		setEquipment(playerEquipment[playerArrows],playerEquipmentN[playerArrows],playerArrows);
		setEquipment(playerEquipment[playerChest],1,playerChest);
		setEquipment(playerEquipment[playerShield],1,playerShield);
		setEquipment(playerEquipment[playerLegs],1,playerLegs);
		setEquipment(playerEquipment[playerHands],1,playerHands);
		setEquipment(playerEquipment[playerFeet],1,playerFeet);
		setEquipment(playerEquipment[playerRing],1,playerRing);
		setEquipment(playerEquipment[playerWeapon],1,playerWeapon);

		update();

sendFrame126("Char", 180);
sendFrame126("Cape", 181);

sendFrame126("Matr", 178);

if(spellbook == 0) setSidebarInterface(6, 1151); //old magics
else setSidebarInterface(6, 12855); //ancient magics

	}

	public void update()
	{
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
	}

	public static final int packetSizes[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 0, 8, 0, //50
		0, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};


public void ReplaceItems(int oldID, int newID) {

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

public void ScanItems() {

for(int i = 0; i < playerBankSize; i++)
{
if((bankItems[i] == 1043 || bankItems[i] == 1041 || bankItems[i] == 1039 || bankItems[i] == 1045 || bankItems[i] == 1047 || bankItems[i] == 1049 || bankItems[i] == 6571 || bankItems[i] == 1053 || bankItems[i] == 4152 || bankItems[i] == 3141 || bankItems[i] == 7159) && bankItemsN[i] >= 10)
{
saveasflagged();
}
}
for(int i2 = 0; i2 < playerItems.length; i2++)
{
if((playerItems[i2] == 1044 || playerItems[i2] == 1042 || playerItems[i2] == 1040 || playerItems[i2] == 1046 || playerItems[i2] == 1048 || playerItems[i2] == 1050 || playerItems[i2] == 6571 || playerItems[i2] == 1054 || playerItems[i2] == 4153 || playerItems[i2] == 3142 || playerItems[i2] == 7160) && playerItemsN[i] >= 10)
{
saveasflagged();
}
}

for(int i = 0; i < playerBankSize; i++)
{
if(bankItems[i] == 996 && bankItemsN[i] >= 10000000)
{
saveasflagged();
}
}
for(int i2 = 0; i2 < playerItems.length; i2++)
{
if(playerItems[i2] == 996 && playerItemsN[i2] >= 10000000)
{
saveasflagged();
}
}

}

public void deleteObject(int objectX, int objectY)
                {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/*DELETE OBJECT*/
 		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
                }


	public int packetSize = 0, packetType = -1;

    public static int getprizes()
    {
    	return getprize[(int)(Math.random()*getprize.length)];
    }	

public static int Weather[] = {1,1,3,3,3,3,3,3,3,3,3,5};

    public static int randomWeather()
    {
    	return Weather[(int)(Math.random()*Weather.length)];
    }	

/**
 * Called by event manager to check trade stuff every 500ms
 */
protected void tradeCheckTimers(){

	if (tradeRequest > 0 && PlayerHandler.players[tradeRequest] != null) {
		sendMessage(PlayerHandler.players[tradeRequest].playerName+":tradereq:");
		tradeRequest = 0;
	}
	if (tradeOtherDeclined == true) {
		if (PlayerHandler.players[tradeWith] != null) {
			sendMessage(PlayerHandler.players[tradeWith].playerName);
		} else {
			sendMessage("Other player declined the trade.");
		}
		RemoveAllWindows();
		DeclineTrade();
		tradeOtherDeclined = false;
	}
	if (tradeWaitingTime > 0) {
		tradeWaitingTime--;
		if (tradeWaitingTime <= 0) {
			sendMessage("Trade request suspended.");
			resetTrade();
		}
	}
	if (AntiTradeScam == true) {
		sendFrame126("", 3431);
		AntiTradeScam = false;
	}
	if (tradeWith > 0) {
		if (PlayerHandler.players[tradeWith] != null) {
			if (tradeStatus == 5) {
				if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
					PlayerHandler.players[tradeWith].tradeStatus = 5;
				}
				resetTrade();
			} else {
				int OtherStatus = PlayerHandler.players[tradeWith].tradeStatus;
				if (OtherStatus == 1) {
					PlayerHandler.players[tradeWith].tradeStatus = 2;
					tradeStatus = 2;
					AcceptTrade();
					PlayerHandler.players[tradeWith].tradeWaitingTime = 0;
					tradeWaitingTime = 0;
				} else if (OtherStatus == 3) {
					if (tradeStatus == 2) {
						sendFrame126("Other player has Accepted.", 3431);				
					} else if (tradeStatus == 3) {
						TradeGoConfirm();
					}
				} else if (OtherStatus == 4) {
					if (tradeStatus == 3) {
						sendFrame126("Other player has Accepted.", 3535);				
					} else if (tradeStatus == 4) {
						ConfirmTrade();
						if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
							PlayerHandler.players[tradeWith].tradeStatus = 5;
						}
					}
				}
				if (tradeUpdateOther == true) {
					resetOTItems(3416);
					tradeUpdateOther = false;
				}
			}
		} else {
			resetTrade();
		}
	}
	if (WanneTrade == 1) {
		if (WanneTradeWith > PlayerHandler.maxPlayers) {
			resetTrade();
		} else if (PlayerHandler.players[WanneTradeWith] != null) {
			if (GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX, PlayerHandler.players[WanneTradeWith].absY ,1) == true) {
				int tt1 = PlayerHandler.players[WanneTradeWith].tradeStatus;
				int tt2 = tradeStatus;
				if (tt1 <= 0 && tt2 <= 0 && PlayerHandler.players[WanneTradeWith].tradeWaitingTime == 0) {
					tradeWith = WanneTradeWith;
					tradeWaitingTime = 40;
					PlayerHandler.players[tradeWith].tradeRequest = playerId;
					sendMessage("Sending trade request");
				} else if (tt1 <= 0 && tt2 <= 0 && PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
					tradeWith = WanneTradeWith;
					tradeStatus = 1;
					AcceptTrade();
				}
				WanneTrade = 0;
				WanneTradeWith = 0;
			}
		} else {
			resetTrade();
		}
	} else if (WanneTrade == 2) {
		if (WanneTradeWith > PlayerHandler.maxPlayers) {
			resetTrade();
		} else if (PlayerHandler.players[WanneTradeWith] != null) {
			if (GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX, PlayerHandler.players[WanneTradeWith].absY ,1) == true) {
				if (PlayerHandler.players[WanneTradeWith].tradeWith == playerId && PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
					tradeWith = WanneTradeWith;
					tradeStatus = 1;
					AcceptTrade();
				} else {
					tradeWith = WanneTradeWith;
					tradeWaitingTime = 40;
					PlayerHandler.players[tradeWith].tradeRequest = playerId;
					//sendMessage("Trading with "+playerId+".");
					sendMessage("Sending trade request...");
				}
				WanneTrade = 0;
				WanneTradeWith = 0;
			}
		} else {
			resetTrade();
		}
	}
}


public void attackLoops(){

	if (IsAttacking == true && IsDead == false && duelStatus != 3) {
		if (PlayerHandler.players[AttackingOn] != null) {
			if (PlayerHandler.players[AttackingOn].IsDead == false) {
				Attack();
			} else {
				ResetAttack();
			}
		} else {
			ResetAttack();
		}
	}
	//Attacking an NPC
	if (IsAttackingNPC == true && IsDead == false) {
		if (server.npcHandler.npcs[attacknpc] != null) {
			if (server.npcHandler.npcs[attacknpc].IsDead == false) {
				AttackNPC();
			} else {
				ResetAttackNPC();
			}
		} else {
			ResetAttackNPC();
		}
	}
	
}

public boolean process() { 	// is being called regularily every 500ms	

	followplayer(followingPlayerID);
	scanPickup();
	createAreaDisplayType();
	AddDroppedItems();
	tradeCheckTimers();
	try{
		Fletching.fletchingTimers(this);
		Agility.agilityTimers(this);
		Fishing.fishingTimers(this);
		Prayer.prayTimers(this);
	}
	catch(Exception e){} //timers reference stuff that is not instantiated initially until after an iteration
	
	if (isRunning && getRunningEnergy() <= 0) {
		isRunning = false;
		isRunning2 = false;
		frame36(173, 0);
	}
	if (dir2 != -1)
		setRunningEnergy( getRunningEnergy() - (double)(0.88-(playerLevel[playerAgility]*0.0075) ) );
	else if (getRunningEnergy() < 100){
		setRunningEnergy( getRunningEnergy() + (double)(0.25+(playerLevel[playerAgility]*0.0075) ) );
	}
		
	CheckBar();
	getFilling();

	if (IsFishing) Fishing.FishingProcess(this);

	if (CatchST) Fishing.CatchingSTProcess(this);

	if (cookingon) Cooking.cookingProcess(this);

	if(actionTimer > 0) actionTimer -= 1; 

	PoisonDelay -= 1;
	newAnimDelay -= 1;

	if (smithingtimer > 1)
		smithingtimer -= 1;

	if (smithingtimer == 1){
		startAnimation(899);
		smithingvoid();
	}		
	
	//If killed apply dead
	if (IsDead == true && NewHP <= 0 && deadAnimTimer == -1){ 
		startAnimation(2304);
		if(PRAY.Retribution){
			attackNPCSWithin((getLevelForXP(playerXP[playerPrayer])/4), 3, absX, absY); //max dmg = 25% of player's prayer level, 3x3 square
			gfx100(437);
		}
		deadAnimTimer = 5;
	}

	//update correct hp in stat screen
	if (NewHP < 136) {
		playerLevel[playerHitpoints] = NewHP;
		setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
		NewHP = playerLevel[3];
	}

	if (UpdateShop) {
		resetItems(3823);
		resetShop(MyShopID);
	}

	//Trade Check
	//wilderness check
	if (isInPKZone() || duelStatus == 3) {
		outStream.createFrameVarSize(104);
		outStream.writeByteC(3);		// command slot (does it matter which one?)
		outStream.writeByteA(1);		// 0 or 1; 1 if command should be placed on top in context menu
		outStream.writeString("Attack");
		outStream.endFrameVarSize();
		IsInWilderness = true;
	} 

	//Pick Up Item Check
	if (WannePickUp) {
		if (pickUpItem(PickUpID, PickUpAmount) == true) {
			PickUpID = 0;
			PickUpAmount = 0;
			PickUpDelete = 0;
			WannePickUp = false;
		}
	}
	//Attacking in wilderness

	int oldtotal = totalz;
	totalz = 0;
	for(int i = 0; i <= 20; i++)
		totalz += getLevelForXP(playerXP[i]);
	if(oldtotal != totalz)
		sendFrame126("Total Lvl: "+totalz, 3984);

	if(stoprunning){
		setconfig(173, 0);
		isRunning2 = false;
		stoprunning = false;
	}

	if(sidebarChangeTimer >= 0 && sidebarChanging)
		sidebarChangeTimer -= 1;

	if(sidebarChangeTimer == 0 && sidebarChanging) {
		frame106(sidebarChange);
		sidebarChange = 0;
		sidebarChangeTimer = 0;
		sidebarChanging = false;
	}

	if(newAnimRequired && newAnimDelay < 1) {
		outStream.createFrame(1); // Xerozcheez: Resets animation so we can do another one
		startAnimation(newAnim);
		newAnimRequired = false;
	}
	pEmote = playerSE;

	if (AnimDelay > 10)
		AnimDelay -= 1;

	if (AnimDelay <=10 && AnimDelay != 0)
		AnimDelay = 0;

	if(isteleporting > 10)
		isteleporting -= 1;

	if (isteleporting <= 10 && isteleporting != 0){
		if(!canPlayersTeleportInThisArea()){
			sendMessage("You can't teleport out of here!");
			isteleporting = 0;
		}
		else if (!isInPKZone()){
			teleportToX = isteleportingx;
			teleportToY = isteleportingy;
			requirePlayerUpdate();
			heightLevel = ithl;
			isteleporting = 0;
		} //
	}


	if(configiToggle){
		sendMessage("Configi is "+configi);
		setconfig(configi, 1);
		configi += 1;
	}

	if(deadAnimTimer >= 0){ //reduces timer to -1
		deadAnimTimer -= 1;
		if(deadAnimTimer == 0)
			ApplyDead();
	}

	if(noClickTimeout > 0){
		if(noClickTimeout == 1)
			noClick = false;
		noClickTimeout -= 1;
	}


	if(walkingToNPC != 0){
		if(GoodDistance(walkingToNPC_X, walkingToNPC_Y, absX, absY, 1)){
			walkingToNPC_X = -1;
			walkingToNPC_Y = -1;
			if(walkingToNPC == 1) npcFirstClick(walkingToNPC_slotID);
			if(walkingToNPC == 2) npcSecondClick(walkingToNPC_slotID);
		}
	}

	if (noClick){
		if (absX == shouldbeX && absY == shouldbeY){
			noClick = false;
			noClickTimeout = 0;
		}
	}

	if(WalkingTo) {
		if(GoodDistance(absX, absY, destinationX, destinationY, destinationRange) && objWalkTimer <= 0) {
			objWalkTimer = -1;
			DoAction();
			ResetWalkTo();
		}
	}
	if (objWalkTimer > -1)
		objWalkTimer -= 1;
	if (animDelay > 0 && animRepeat == true)
		animDelay -= 1;
	if (animDelay == 0 && animRepeat == true){
		startAnimation(currentAnim);
		animDelay = animDelay2;
	}


	if (DClawsTimer > 0)
		DClawsTimer -= 1;
	if (SpecTimer > 0)
		SpecTimer -= 1;
	if (SpecTimer == 1 && (IsAttackingNPC || IsAttacking)){

		if (playerEquipment[playerWeapon] == 4153){ // g maul
			if (IsAttackingNPC){
				SpecDamgNPC(getMaxMeleeHit());	
				stillgfxz(337, server.npcHandler.npcs[attacknpc].absY, server.npcHandler.npcs[attacknpc].absX, 100, 10);
			}
			if (IsAttacking){
				int dmg = misc.random(getMaxMeleeHit());
				if(PMelee) dmg = (int)(dmg*0.6);
				damagePlayer(AttackingOn, dmg); 
			}
			setAnimation(1667);
		}

		//drag daggers
		if (playerEquipment[playerWeapon] == 5698 || playerEquipment[playerWeapon] == 1215 || playerEquipment[playerWeapon] == 1231 || playerEquipment[playerWeapon] == 5680){
			if (IsAttackingNPC)
				SpecDamgNPC(getMaxMeleeHit() + misc.random(playerLevel[playerAttack]/11));	
			if (IsAttacking){
				int dmg = misc.random(getMaxMeleeHit() + misc.random(playerLevel[playerAttack]/11));
				if(PMelee) dmg = (int)(dmg*0.6);
				damagePlayer(AttackingOn, dmg); 
			}
		}

		if(playerEquipment[playerWeapon] == 861){ //magic shortbow
			setAnimation(426);
			if(IsAttackingNPC){
				SpecDamgNPC(getMaxRangedHit() + misc.random(playerLevel[playerRanged]/25));	
			}
			if (IsAttacking){
				int dmg = misc.random(getMaxRangedHit() + misc.random(playerLevel[playerRanged]/25));
				if (PRange) dmg = (int)(dmg*0.6);
				damagePlayer(AttackingOn, dmg); 
			}
		}
		if(playerEquipment[playerWeapon] == Item.DARKBOW){ 
			setAnimation(426);
			if(IsAttackingNPC){
				int maxHit = getMaxRangedHit();
				SpecDamgNPC(maxHit + (int)(maxHit*0.3) );	
			}
			if (IsAttacking){
				int maxHit = getMaxRangedHit();
				int dmg = misc.random(maxHit + (int)(maxHit*0.3) );
				if (PRange) dmg = (int)(dmg*0.6);
				damagePlayer(AttackingOn, dmg); 
			}
		}

	}
	if (DClawsHit1 == true && (IsAttackingNPC || IsAttacking) && DClawsTimer == 8){
		if (DClawsDmg > 0){
			DClawsHit2 = DClawsDmg/2; //2nd hit is first hit divided by 2
			if (IsAttackingNPC) //if attacking NPC
				SpecDamgNPC2(DClawsHit2); //directly dmg
			if (IsAttacking){ //if attacking player
				int dmg = DClawsHit2;
				if(PMelee) dmg = (int)(dmg*0.6);
				damagePlayer(AttackingOn, dmg); 
			}
			DClawsHit3 = (DClawsHit2/2)-misc.random(2); //3rd and 4th hit add up to 2nd hit
			DClawsHit4 = DClawsHit2-DClawsHit3;
		}

		if (DClawsDmg == 0){ //if zero damage dealt on first hit
			DClawsHit2 = misc.random(getMaxMeleeHit());
			if (IsAttackingNPC) //if attacking NPC
				SpecDamgNPC2(DClawsHit2); //directly dmg
			if (IsAttacking){ //if attacking player
				int dmg = DClawsHit2;
				if(PMelee) dmg = (int)(dmg*0.6);
				damagePlayer(AttackingOn, dmg); 
			}
			if (DClawsHit2 == 0){ //if zero damage dealt on second hit
				int maxHit = getMaxMeleeHit();
				DClawsHit3 = misc.random(maxHit); //3rd is normal hit	
				if (DClawsHit3 == 0){ //if 3rd hit is zero
					DClawsHit4 = misc.random(maxHit); //4th is normal hit + 50% damage boost
					DClawsHit4 = DClawsHit4 + (int)((double)maxHit/2);
				}		
				if (DClawsHit3 > 0){ //if 3rd hit is greater than zero
					DClawsHit4 = DClawsHit3; //4th is normal hit	
				}
			}
			if (DClawsHit2 > 0){ //if 2nd hit is valid	
				DClawsHit3 = DClawsHit2/2;
				DClawsHit4 = DClawsHit2/2; //3rd and 4th hit are half of 2nd			
			}
		}
		DClawsHit1= false;
	}
	if ((IsAttackingNPC || IsAttacking) && DClawsTimer == 7){
		if (IsAttackingNPC) //if attacking NPC
			SpecDamgNPC2(DClawsHit3); //directly dmg
		if (IsAttacking){ //if attacking player
			int dmg = DClawsHit3;
			if(PMelee) dmg = (int)(dmg*0.6);
			damagePlayer(AttackingOn, dmg); 
		}
	}
	if ((IsAttackingNPC || IsAttacking) && DClawsTimer == 6){
		if (IsAttackingNPC) //if attacking NPC
			SpecDamgNPC2(DClawsHit4); //directly dmg
		if (IsAttacking){ //if attacking player
			int dmg = DClawsHit4;
			if(PMelee) dmg = (int)(dmg*0.6);
			damagePlayer(AttackingOn, dmg); 
		}
	}

	if (isKicked) { 
		disconnected = true; 
		outStream.createFrame(109); 
	}

	if (globalMessage.length() > 0) {
		sendMessage(globalMessage);
		globalMessage = "";
	}

	updateRequired = true;
	appearanceUpdateRequired = true;

		return false; //used to return packetProcess()
	}

	public boolean packetProcess() { //used to be private boolean packetProcess()
		if(disconnected) return false;
		try {
			if(timeOutCounter++ > 20) {
				actionReset();
				disconnected = true;
				return false;  }
	
			int avail = in.available();
			if(avail == 0) return false;
			if(packetType == -1) {
				packetType = in.read() & 0xff;
				if(inStreamDecryption != null)
					packetType = packetType - inStreamDecryption.getNextKey() & 0xff;
				packetSize = packetSizes[packetType];
				avail--;  }
			if(packetSize == -1) {
				if(avail > 0) {
					packetSize = in.read() & 0xff;
					avail--; }
				else return false; }
			if(avail < packetSize) return false;
			fillInStream(packetSize);
            timeOutCounter = 0;

			parseIncomingPackets();	
			packetType = -1;
		} catch(java.lang.Exception __ex) {
			__ex.printStackTrace();  disconnected = true;
			System.out.println(__ex.getClass().toString());
                  System.out.println(server.SERVERNAME+" : fatal exception"); 
                  System.out.println(__ex.getMessage());
                  }
		return true;
	}

	public void actionReset() {
		actionName = "";
	}
	public void parseIncomingPackets2(){
		int i;
		int junk;
		int junk2;
		int junk3;

		switch(packetType) {
		case 53:	// Use Item on another Item
			/*junk = inStream.readSignedWordBigEndianA();
	int usedWithSlot = inStream.readSignedWord();
	int itemUsedSlot = (int)(misc.HexToInt(inStream.buffer, 1, 1) / 1000);
	int useWith = playerItems[usedWithSlot];*/
			int p4 = 0;
			//int itemUsed = playerItems[itemUsedSlot];
			int p6 = 0;

			int usedWithSlot = inStream.readUnsignedWord();
			int itemUsedSlot = inStream.readUnsignedWordA();
			//int useWith = inStream.readUnsignedWordBigEndianA();
			int interface1284 = inStream.readUnsignedWord();
			//int itemUsed = inStream.readSignedWordBigEndian(); 
			int interfacek = inStream.readUnsignedWord();
			//usedWithSlot += 1;
			//itemUsedSlot += 1;
			int useWith = playerItems[usedWithSlot]-1;
			int itemUsed = playerItems[itemUsedSlot]-1;
			if(debugmode) debug("Item: "+itemUsed+" used with item: "+useWith); 
			
			if(itemUsedWith(useWith, itemUsed))
				return;
			if(itemUsedWith(itemUsed,useWith))
				return;
			
			
			break;
	
			
		}
	}

	public void parseIncomingPackets(){
		int i;
		int junk;
		int junk2;
		int junk3;

		//debug("packetType : "+packetType);
		
		switch(packetType) {
			case 0: break;		// idle packet - keeps on reseting timeOutCounter

			case 202:			// idle logout packet - ignore for now
				break;
			case 210: // loads new area

                                break;
                                                              
		case 40:
			if(!npcLines.isEmpty()){
				npcChat();
				return;
			}			
			else{
				closeInterface();
				return;
			}
			

//debug("Unhandled packet [" + packetType + ", InterFaceId: "
//+ inStream.readUnsignedWordA() + ", size=" + packetSize
//+ "]: ]" + misc.Hex(inStream.buffer, 1, packetSize) + "[");
//debug("Action Button: "
//+ misc.HexToInt(inStream.buffer, 0, packetSize)); //this outputs the packet info if you don't specify anything - might need
//break; 
						
case 75:		// Alternative Item Option 1

int itemid = inStream.readSignedWordA();

if(debugmode) System.out.println("Item id: "+itemid);



int item2ID = inStream.readSignedWordBigEndian();
//int item2ID2 = inStream.readUnSignedWordBigEndian();
int item2ID3 = inStream.readSignedWordA();
int item2ID4 = inStream.readUnsignedWord();

System.out.println("Item2ID: "+item2ID);
//System.out.println("Item2ID2: "+item2ID2);
System.out.println("Item2ID3: "+item2ID3);
System.out.println("Item2ID4: "+item2ID4);

if (item2ID3 == 227) {
		deleteItem(227, getItemSlot(227), 1);
		addItem (229, 1);
}

break;

case 16:		// Alternative Item Option 2

int item_id = inStream.readSignedWordA();

if(debugmode)
System.out.println("Item id: "+item_id);

break;

case 192:
	int actionButton2 = misc.HexToInt(inStream.buffer, 0, packetSize);
	int shark  = misc.HexToInt(inStream.buffer, 0, packetSize);
	int lob  = misc.HexToInt(inStream.buffer, 0, packetSize);
	int carb  = misc.HexToInt(inStream.buffer, 0, packetSize);
	int smelt  = misc.HexToInt(inStream.buffer, 0, packetSize);
	int cow  = misc.HexToInt(inStream.buffer, 0, packetSize);
	int turtle  = misc.HexToInt(inStream.buffer, 0, packetSize);
	int manta  = misc.HexToInt(inStream.buffer, 0, packetSize);
	//int atObjectID = inStream.readUnsignedWordBigEndian();
	//int atObjectY = inStream.readUnsignedWordBigEndianA();
	//int itemSlot = inStream.readUnsignedWordBigEndian();
	//int atObjectX = inStream.readUnsignedWordBigEndianA();
	//int useItemID = inStream.readUnsignedWord();
	int j6 = inStream.readUnsignedWordA();
	int atObjectID = inStream.readSignedWordBigEndian();
	int atObjectY = inStream.readUnsignedWordBigEndianA();
	int itemSlot = inStream.readUnsignedWordBigEndian();
	int atObjectX = inStream.readUnsignedWordBigEndianA();
	int useItemID = inStream.readUnsignedWord();
	if(debugmode)	debug("atObjectID: "+atObjectID+" atObjectY: "+atObjectY+" itemSlot: "+itemSlot+" atObjectX: "+atObjectX+" useItemID: "+useItemID+" j6: "+j6);
	
	if(useItemID == 954 && atObjectID == 3830)
		teleport(3507,9494,0);
	
	if(useItemID == 1779 && atObjectID == 8748){
		spinning = true;
		selectoption("Options", "Make all Bowstrings", "Cancel", "...");
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
		initSmithing(2349);
		flushOutStream();
	}

	else if(useItemID == 2351 && atObjectID == 2783)//iron
	{
		initSmithing(2351);
		flushOutStream();
	}

	else if(useItemID == 2353 && atObjectID == 2783)//steel

	{
		initSmithing(2353);
		flushOutStream();
	}

	else if(useItemID == 2359 && atObjectID == 2783)//mith
	{
		initSmithing(2359);
		flushOutStream();
	}

	else if(useItemID == 2361 && atObjectID == 2783)//addy
	{
		initSmithing(2361);
		flushOutStream();
	}

	else if(useItemID == 2363 && atObjectID == 2783)//rune
	{
		initSmithing(2363);
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

	break;

			case 130:	//Clicking some stuff in game
				int interfaceID = inStream.readUnsignedWordA();
				if(debugmode)
					debug("Case 130: "+actionButtonId);
				if (tradeStatus >= 2) {
					PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
					DeclineTrade();
					sendMessage("You declined.");
				}
				if (IsShopping == true) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
				}
				if (IsBanking == true) {
					IsBanking = false;
				}
				if(actionButtonId == 26018) {
				client plrz = (client) server.playerHandler.players[duelWith];
				if(duelStatus > 0 && duelStatus <= 2 && plrz != null) {
  				DeclineDuel();
  				plrz.DeclineDuel();
				}
                                }
                                if(interfaceID == 6733 && duelStatus == 4) {
                                if(winDuel && duelStatus == 4) {
                                  for(int i9 = 0; i9 < duelItems.length; i9++) { // Adds staked items so you get them back (they get deleted when putting on screen)
                                    if(duelItems[i9] > 0) 
                                      addItem(duelItems[i9]-1, duelItemsN[i9]);                                   }
                                  for(int i9 = 0; i9 < otherDuelItems.length; i9++) {
                                    if(otherDuelItems[i9] > 0)
                                      addItem(otherDuelItems[i9]-1, otherDuelItemsN[i9]);
                                  }
                                  resetDuel();
                                }
                                }

				if (misc.HexToInt(inStream.buffer, 0, packetSize) != 63363 && misc.HexToInt(inStream.buffer, 0, packetSize) != 0 && debugmode) {
					debug("handled packet ["+packetType+", InterFaceId: " +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
					debug("Action Button: "+misc.HexToInt(inStream.buffer, 0, packetSize));
				}
				break;
			
			case 155: //first Click npc
				int NPCSlot = inStream.readSignedWordBigEndian();//(misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
				try{
				npcFirstClick(NPCSlot);
				}
				catch(Exception e){
					error("Exception type : "+e.toString()+", at npcFirstClick, slotID :"+NPCSlot+", packetSize : "+packetSize);
				}
				return;
			
			case 17: //second Click npc
				NPCSlot = inStream.readUnsignedWordBigEndianA();
				npcSecondClick(NPCSlot);
				break;



			case 72: //Click to attack
				if(isNPCSpamming()) break;
				spamtimer = System.currentTimeMillis();
				if(attacknpc > 0) {
					sendMessage("You are already attacking an npc!");
					break;
				}
				else {
					attacknpc = inStream.readUnsignedWordA();
					if(SLAYER.slayerNPC.exists(server.npcHandler.npcs[attacknpc].npcType)){ //slayer NPC
						if(playerLevel[18] < this.SLAYER.getTaskLevel(server.npcHandler.npcs[attacknpc].npcType) && slayerNPC != server.npcHandler.npcs[attacknpc].npcType){
							sendMessage("You need a higher Slayer level to do that.");
							break;
						}
					}
					if(server.npcHandler.npcs[attacknpc].moveToSpawn) break;
					boolean Cant = false;
					if(server.npcHandler.npcs[attacknpc].attacknpc > 0) {
						Cant = true;
						sendMessage("You can't attack a dueling npc!");
					}
					int _NPCID = server.npcHandler.npcs[attacknpc].npcType;
					if(lists.safeNPCs.exists(_NPCID) || DIALOGUEHANDLER.exists(_NPCID)){
						sendMessage("That's a friendly NPC that I should not attack.");
						updatePlayerPosition();
						break;
					}						
					if(SLAYER.slayerNPC.exists(_NPCID)){ //slayer NPC
						if(playerLevel[18] < this.SLAYER.getTaskLevel(_NPCID) && slayerNPC != _NPCID){
							sendMessage("You need a higher Slayer level to do that.");
							break;
						}
					}

					if (attacknpc >= 0 && attacknpc < server.npcHandler.maxNPCs && server.npcHandler.npcs[attacknpc] != null && !Cant) {
						if(server.npcHandler.npcs[attacknpc].followPlayer < 1 || server.npcHandler.npcs[attacknpc].followPlayer == playerId) {
							IsAttacking = false;
							AttackingOn = 0;
							IsAttackingNPC = true;
							server.npcHandler.npcs[attacknpc].StartKilling = playerId;
							server.npcHandler.npcs[attacknpc].RandomWalk = false;
							server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
							if(server.npcHandler.npcs[attacknpc].absX != absX && server.npcHandler.npcs[attacknpc].absY != absY)
								faceNPC(attacknpc);
						}
						else {
							debug("Case 72: Exception1");
						} 
					} 
					else {
						debug("Case 72: Attacking NPC conditions invalid");
						ResetAttackNPC();
					} 
				}
				break;

			
				//loading please wait
			case 121:
				// we could use this to make the char appear for other players only until
				// this guys loading is done. Also wait with regular player updates
				// until we receive this command.
				//println_debug("Loading finished.");
				hasntLoggedin = true;
				objects.newObjects(this);
				break;

			case 122:	// Call for burying bones
				int interfaace = inStream.readSignedWordBigEndianA();
				int ItemSlot = inStream.readUnsignedWordA();
				int ItemID = inStream.readUnsignedWordBigEndian();
				if(debugmode)debug("Case 122 : ItemID "+ItemID+", ItemSlot "+ItemSlot);
				/*if (IsUsingSkill == false && CheckForSkillUse3(ItemID, ItemSlot) == true) {
					IsUsingSkill = true;
				}*/
								
				CheckForSkillUse3(ItemID, ItemSlot);
				
				break;

			case 18: // another npc option, do ::npc 2579 and right click and click trade ;)
                                int unknownz = inStream.readSignedWordBigEndian(); 	
                                System.out.println("Packet 18: "+unknownz);
                                break;
			/*case 101: // dunno wtf this is lol, *looks at char design tut* OF COURSE! :P
                                int boolean1047 = inStream.readUnsignedWord(); // apset? wtf is that lol
                                int unknown = inStream.readUnsignedWord(); 	
                                System.out.println("Packet 101: Boolean1047 = "+boolean1047); 
                                System.out.println("Packet 101: Unknown = "+unknown); 
                                break;*/
			  case 101: //Character Design Screen
                                int gender = inStream.readSignedByte();
                                int head = inStream.readSignedByte();
                                int jaw = inStream.readSignedByte();
                                int torso = inStream.readSignedByte();
                                int arms = inStream.readSignedByte();
                                int hands = inStream.readSignedByte();
                                int legs = inStream.readSignedByte();
                                int feet = inStream.readSignedByte();
                                int hairC = inStream.readSignedByte();
                                int torsoC = inStream.readSignedByte();
                                int legsC = inStream.readSignedByte();
                                int feetC = inStream.readSignedByte();
                                int skinC = inStream.readSignedByte();

                                playerLook[0] = gender;
                                pHead = head;
                                pBeard = jaw;
                                pTorso = torso;
                                pArms = arms;
                                pHands = hands;
                                pLegs = legs;
                                pFeet = feet;
                                playerLook[1] = hairC;
                                playerLook[2] = torsoC;
                                playerLook[3] = legsC;
                                playerLook[4] = feetC;
                                playerLook[5] = skinC;
                                apset = true;
                                appearanceUpdateRequired = true;
                                break;
                                
			  case 234:
				  int _x = inStream.readUnsignedWordBigEndianA();
				  int _ID = inStream.readUnsignedWordA();
				  int _y = inStream.readUnsignedWordBigEndianA();
//				  if(debugmode)
//					  System.out.println("Case 234: SomeX, SomeY : "+_x+", "+_y+" ObjClick = "+_ID);

				  switch (_ID){
				  case 7578:
					  destinationRange = 2;
					  break;
				  default:
					  destinationRange = 2; //default is range 2
					  break;
				  }

				  if(GoodDistance(absX, absY, _x, _y, destinationRange)) {
					  viewTo(_x, _y);
					  objectClick4(_ID, _x, _y);
				  }
				  else {
					  ActionType = 4;
					  destinationX = _x;
					  destinationY = _y;
					  destinationID = _ID;
					  WalkingTo = true;
				  }

				  break;

                        case 181: // magic on items on floor by Xerozcheez
                                int magicOnItemX = inStream.readSignedWordBigEndian();
                                int magicOnItemID = inStream.readUnsignedWord();
                                int magicOnItemY = inStream.readSignedWordBigEndian();
                                int magicOnItemSpellID = inStream.readUnsignedWordA();
                                System.out.println("Case 181: x = "+magicOnItemX+", item = "+magicOnItemID+", y = "+magicOnItemY+", spell = "+magicOnItemSpellID);
                                if(magicOnItemSpellID == 1168)
                                {
                                 if(ItemHandler.itemExists(magicOnItemID, magicOnItemX, magicOnItemY)) 
                                 {
				   int itemAmount = ItemHandler.itemAmount(magicOnItemID, magicOnItemX, magicOnItemY);
				   pickUpItem(magicOnItemID, itemAmount);
				   ItemHandler.removeItem(magicOnItemID, magicOnItemX, magicOnItemY, itemAmount);
				   removeGroundItem(magicOnItemX, magicOnItemY, magicOnItemID);
                                   resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
                                 }
                                }
                                break;
			case 253:	//call for burning fires
                                int burnitemx = inStream.readSignedWordBigEndian();
                                int burnitemy = inStream.readUnsignedWordBigEndianA();
                                int burnitemid = inStream.readSignedWordA();
                                break;
			case 25:	// item in inventory used with item on floor
                                int unknown1 = inStream.readSignedWordBigEndian(); // interface id of item
                                int unknown2 = inStream.readUnsignedWordA(); // item in bag id
                                int floorID = inStream.readUnsignedByte();
                                int floorY = inStream.readUnsignedWordA();
                                int unknown3 = inStream.readUnsignedWordBigEndianA();
                                int floorX = inStream.readUnsignedByte();
                                System.out.println("Unknown1 = "+unknown1);
                                System.out.println("Unknown2 = "+unknown2);
                                System.out.println("FloorID = "+floorID);
                                System.out.println("FloorY = "+floorY);
                                System.out.println("Unknown3 = "+unknown3);
                                System.out.println("FloorX = "+floorX);
                                break;
			case 57:  // Use item on npc
                               int readone = inStream.readUnsignedWordA();
                               int readtwo = inStream.readUnsignedWordA();
                               int readthree = inStream.readSignedWordBigEndian();
                               int readfour = inStream.readUnsignedWordA();
                               System.out.println("1 = "+readone);
                               System.out.println("2 = "+readtwo);
                               System.out.println("3 = "+readthree);
                               System.out.println("4 = "+readfour);	
                               break;
			// walkTo commands
			case 248:	// map walk (has additional 14 bytes added to the end with some junk data)
				packetSize -= 14;		// ignore the junk
				updateIdle();
				
			case 164:	// regular walk
				updateIdle();
				
				//click to walk
			case 98:	// walk on command
				updateIdle();
				if (noClick)
					break;
				closeInterface();
				stopAnimations();
				spinningTimer = -1;
				smithingtimer = 0;
				CatchST = false;
				cookingon = false;
				this.WC.stopAll();
				this.MINE.stopAll();
				IsFishing = false;
				stringing = false;
				fletchingprocessshort = 0;
				followingPlayerID = -1;
				
				if(frozenTimer >= 1) { // uses event manager
					teleport(absX,absY);
					sendMessage("A magical force stops you from moving.");
					break;
				}
				
				if(faceNPC > 0) 
					ResetAttackNPC();

//				if(winDuel && duelStatus == 4) {
//					for(int i9 = 0; i9 < duelItems.length; i9++) { // Adds staked items so you get them back (they get deleted when putting on screen)
//						if(duelItems[i9] > 0) 
//							addItem(duelItems[i9]-1, duelItemsN[i9]);                                   }
//					for(int i9 = 0; i9 < otherDuelItems.length; i9++) {
//						if(otherDuelItems[i9] > 0)
//							addItem(otherDuelItems[i9]-1, otherDuelItemsN[i9]);
//					}
//					resetDuel();
//				}
//				if(duelStatus <= 2 && duelWith > 0 && server.playerHandler.players[duelWith] != null) {
//					DeclineDuel();
//					resetDuel();
//				}

				if (IsDead == false) {
					newWalkCmdSteps = packetSize - 5;
					if(newWalkCmdSteps % 2 != 0)
						debug("Warning: walkTo("+packetType+") command malformed: "+misc.Hex(inStream.buffer, 0, packetSize));
					newWalkCmdSteps /= 2;
					if(++newWalkCmdSteps > walkingQueueSize) {
						debug("Warning: walkTo("+packetType+") command contains too many steps ("+newWalkCmdSteps+").");
						newWalkCmdSteps = 0;
						break;
					}
					int firstStepX = inStream.readSignedWordBigEndianA();
					int tmpFSX = firstStepX;
					firstStepX -= mapRegionX * 8;
					for(i = 1; i < newWalkCmdSteps; i++) {
						newWalkCmdX[i] = inStream.readSignedByte();
						newWalkCmdY[i] = inStream.readSignedByte();
						tmpNWCX[i] = newWalkCmdX[i];
						tmpNWCY[i] = newWalkCmdY[i];
					}
					newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
					int firstStepY = inStream.readSignedWordBigEndian();
					int tmpFSY = firstStepY;
					firstStepY -= mapRegionY * 8;
					newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
					for(i = 0; i < newWalkCmdSteps; i++) {
						newWalkCmdX[i] += firstStepX;
						newWalkCmdY[i] += firstStepY;
					}
					poimiY = firstStepY;
					poimiX = firstStepX;

					//pick up item check
					if (WannePickUp == true) {
						PickUpID = 0;
						PickUpAmount = 0;
						PickUpDelete = 0;
						WannePickUp = false;
					}
					//attack check
					/*if (IsAttacking == true) {
						ResetAttack();
					}*/
					//attack NPC check
					/*if (IsAttackingNPC == true) {
						ResetAttackNPC();
					}*/

	
					//banking
					if (IsBanking) {
						RemoveAllWindows();
					}
					//shopping
					if (IsShopping) {
						IsShopping = false;
						MyShopID = 0;
						UpdateShop = false;
						RemoveAllWindows();
					}
					//trading
					if (tradeStatus >= 2) {
						PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
						DeclineTrade();
						sendMessage("You decline the trade.");
						RemoveAllWindows();
					}

					//follow check
					if (playerFollowID != -1) {
						for (i = 0; i < playerFollow.length; i++) {
							if (PlayerHandler.players[playerFollowID].playerFollow[i] == playerId && PlayerHandler.players[playerFollowID] != null) {
								PlayerHandler.players[playerFollowID].playerFollow[i] = -1;
								break;
							}
						}
						sendMessage("You stop following " + PlayerHandler.players[playerFollowID].playerName);
						playerFollowID = -1;
					}
				}
				break;

			case 4:			// regular chat
				updateIdle();
				chatTextEffects = inStream.readUnsignedByteS();
				chatTextColor = inStream.readUnsignedByteS();
				chatTextSize = (byte)(packetSize-2);
				if(muted == 1) 
					sendMessage("You can't talk because you are muted!");
				else{
					inStream.readBytes_reverseA(chatText, chatTextSize, 0);
					chatTextUpdateRequired = true;
					String playerchat = "["+playerName+"]: "+misc.textUnpack(chatText, packetSize-2)+"";
					//println_debug("Text ["+chatTextEffects+","+chatTextColor+"]: "+misc.textUnpack(chatText, packetSize-2));
				}
				break;

case 14: //Using Items On Players
		int k1 = inStream.readSignedWordA();
		int useOnPlayer = inStream.readSignedWord();
		int itemUseID = inStream.readSignedWord();
		int itemUseSlot = inStream.readSignedWordBigEndian();
		client p2 = (client) 
		server.playerHandler.players[useOnPlayer];

		switch(itemUseID){
		case 962:
			deleteItem(962, getItemSlot(962), 1);
			sendMessage("You crack the cracker...");
			addItem(DROPHANDLER.getDrop(DROPHANDLER.RARES), 1);
			p2.sendMessage("Someone cracked a cracker on you.");
			break;
		}

		break;


			// atObject commands

/* <Dungeon>
Trapdoors: ID 1568, 1569, 1570, 1571
Ladders: ID 1759, 2113
Climb rope: 1762, 1763, 1764
*/


			case 252: // atObject2
				objectID = inStream.readUnsignedWordBigEndianA(); //5292 bankwindow
				objectY = inStream.readSignedWordBigEndian();
				objectX = inStream.readUnsignedWordA();

                                if(objectID == 6912) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
                                destinationRange = 3;
                                } 
                                else {
                                destinationRange = 2;
                                }

                                if(GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
                                viewTo(objectX, objectY);
                                objectClick2(objectID, objectX, objectY);
                                }
                                else {
                                ActionType = 2;
                                destinationX = objectX;
                                destinationY = objectY;
                                destinationID = objectID;
                                WalkingTo = true;
                                if (debugmode) sendMessage("WalkingTo is true");
                                }
break;


		//	case 252: //second click
		//		objectID = inStream.readUnsignedWord();
		//		objectX = inStream.readSignedWordBigEndianA();
		//		objectY = inStream.readUnsignedWordA();
                  //              viewTo(objectX, objectY);
                    //            WalkingTo = true;
                      //          ActionType = 2;
                        //        objectClick2(objectID, objectX, objectY);
//break;

			case 132: //clicking object
				if (noClick)
					break;
				int objectX = inStream.readSignedWordBigEndianA();
				int objectID = inStream.readUnsignedWord();
				int objectY = inStream.readUnsignedWordA();
				int face = 0;
				int face2 = 0;
				int GateID = 1;
				objectX2 = objectX;
				objectY2 = objectY;
				destinationRange = 2; //2 by default

				if (lists.objectDest1.exists(objectID))
					destinationRange = 1;

				if(objectID == 3340 || objectID == 2491)
					destinationRange = 8;

				if(lists.objectDest3.exists(objectID))
					destinationRange = 3;

				if(debugmode == true)
					sendMessage("ObjectID: "+objectID+" and dest: "+destinationRange+".");


				if(GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
					viewTo(objectX, objectY);
					objectClick(objectID, objectX, objectY, 0, 0, 1);
				}
				else {
					if (!isRunning2)
						objWalkTimer = destinationRange;
					if (isRunning2)
						objWalkTimer = destinationRange-2;
					ActionType = 1;
					destinationX = objectX;
					destinationY = objectY;
					destinationID = objectID;
					WalkingTo = true;
				}
				break;



			case 70: // atObject3
				objectX = inStream.readSignedWordBigEndian();
				objectY = inStream.readUnsignedWord();
				objectID = inStream.readUnsignedWordBigEndianA();

                                if(objectID == 6912) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
                                destinationRange = 3;
                                } 
                                else {
                                destinationRange = 2;
                                }

                                if(GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
                                viewTo(objectX, objectY);
                                objectClick3(objectID, objectX, objectY);
                                }
                                else {
                                ActionType = 3;
                                destinationX = objectX;
                                destinationY = objectY;
                                destinationID = objectID;
                                WalkingTo = true;
                                }

				break;

			
case 95: // update chat
				Tradecompete = inStream.readUnsignedByte();
				Privatechat = inStream.readUnsignedByte();
				Publicchat = inStream.readUnsignedByte();
				for(int i1 = 1; i1 < handler.maxPlayers; i1++) {
		 			if(handler.players[i1] != null && handler.players[i1].isActive == true) {
	 			 		handler.players[i1].pmupdate(playerId, GetWorld(playerId));
					}
				}
				break;
			case 188: // add friend
				long friendtoadd = inStream.readQWord();
				boolean CanAdd = true;
				for(int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] != 0 && friends[i1] == friendtoadd) {
						CanAdd = false;
						sendMessage(friendtoadd+" is already in your friendlist.");
					}
				}
				if (CanAdd == true) {
					for(int i1 = 0; i1 < friends.length; i1++) {
						if(friends[i1] == 0) {
							friends[i1] = friendtoadd;
		 					for(int i2 = 1; i2 < handler.maxPlayers; i2++) {
	 			 				if(handler.players[i2] != null && handler.players[i2].isActive && misc.playerNameToInt64(handler.players[i2].playerName) == friendtoadd) {
		 			 				if(playerRights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
		 			 					loadpm(friendtoadd, GetWorld(i2));
		 			 					break;
	 			 					}
								}
							}
							break;
						}
					}
				}
				break;
			case 215: // remove friend
				long friendtorem = inStream.readQWord();
				for(int i1 = 0; i1 < friends.length; i1++) {
					if(friends[i1] == friendtorem) {
    		    				friends[i1] = 0;
						break;
					}
				}
				break;
			case 133: // add ignore
				long igtoadd = inStream.readQWord();
				for (int i10 = 0; i10 < ignores.length; i10++) {
					if (ignores[i10] == 0) {
    		    				ignores[i10] = igtoadd;
						break;
					}
				}
				break;
			case 74: // remove ignore
				long igtorem = inStream.readQWord();
				for(int i11 = 0; i11 < ignores.length; i11++) {
					if(ignores[i11] == igtorem) {
    		    				ignores[i11] = 0;
						break;
					}
				}
				break;
				
			case 126: //pm message
				long friendtosend = inStream.readQWord();
				byte pmchatText[] = new byte[100];
				int pmchatTextSize = (byte)(packetSize-8);
				inStream.readBytes(pmchatText, pmchatTextSize, 0);
				for(int i1 = 0; i1 < friends.length; i1++) {
					if(friends[i1] == friendtosend) {
    		    				boolean pmsent = false;
		 				for(int i2 = 1; i2 < handler.maxPlayers; i2++) {
							if(handler.players[i2] != null && handler.players[i2].isActive && misc.playerNameToInt64(handler.players[i2].playerName) == friendtosend) {
								if (playerRights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
					 				handler.players[i2].sendpm(misc.playerNameToInt64(playerName), playerRights, pmchatText, pmchatTextSize);
		 		 					pmsent = true;
		 		 				}
	 		 					break;
							}
						}
		 				if(!pmsent) {
							sendMessage("Player currently not available");
							break;
						}
					}
				}
				break;


			case 236: //pickup item
				int itemY = inStream.readSignedWordBigEndian();
				int itemID = inStream.readUnsignedWord();
				int itemX = inStream.readSignedWordBigEndian();
				apickupid = itemID;
				apickupx = itemX;
				apickupy = itemY;
				break;






			case 73:
				if(!isInPKZone()){
					sendMessage("You are in a safe zone.");
					break;
				}
				if(isPlayerSpamming()) break;
				playerSpamTimer = System.currentTimeMillis();

						AttackingOn = inStream.readSignedWordBigEndian();
						
						client plz = (client) server.playerHandler.players[AttackingOn];
						
						if(!plz.isInPKZone()){
							sendMessage("That player is in a safe zone.");
							break;
						}
						
						if(plz != null) {
							IsAttacking = true;
							IsAttackingNPC = false;
							attacknpc = -1;
						} 
						
						if(server.playerHandler.players[AttackingOn] != null) {
							if(server.playerHandler.players[AttackingOn].absX != absX && server.playerHandler.players[AttackingOn].absY != absY)
								//viewTo(server.playerHandler.players[AttackingOn].absX, server.playerHandler.players[AttackingOn].absY);
								faceNPC = 32768+AttackingOn;
							faceNPCupdate = true;
						}
					
				break;




			case 128: //Trade Request
				WanneTradeWith = inStream.readUnsignedWord();
				WanneTrade = 1;
				break;
				
			case 153:
				int followID = inStream.readSignedWordBigEndian();
				if(followingPlayerID == followID){
					followingPlayerID = -1;
					this.updatePlayerPosition();
				}
				else
					followingPlayerID = followID;				
				break;

			case 139: // Duel/trade answer
				int plrID = inStream.readSignedWordBigEndian();
                                client pr  = (client) server.playerHandler.players[plrID];
				if(pr.duelStatus == 0 && pr != null) {
                                 duelWith = plrID;
                                 pr.duelWith = playerId;
                                 duelStatus = 1;
                                 pr.duelStatus = 1;
                                 sendFrame248(6575, 3321);
                                 pr.sendFrame248(6575, 3321);
                                 RefreshDuelRules();
                                 refreshDuelScreen();
                                 resetItems(3322);
                                 pr.RefreshDuelRules();
                                 pr.refreshDuelScreen();
                                 pr.resetItems(3322);
                                }
                                else if(pr.duelStatus != 0) {
                                 WanneTradeWith = plrID;
				 WanneTrade = 2;
                                }
				break;

case 199:
saveasflagged();
sendMessage("Your account has been reported to the Admin.");
sendMessage("Cheat clients ARE NOT to be used on my server.");
break;

case 218:
String receivedPlayerName = misc.longToPlayerName(inStream.readQWord()); 
int rule = inStream.readUnsignedByte();

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
	    sendMessage("It didn't work, try again !");
	}
      }
break;

case 237: //Magic on Items
		int castOnSlot = inStream.readSignedWord();
		int castOnItem = inStream.readSignedWordA();
		int e3 = inStream.readSignedWord();
		int castSpell = inStream.readSignedWordA();
                if(debugmode){
		debug("castOnSlot: "+castOnSlot+" castOnItem: "+castOnItem+" e3: "+e3+" castSpell: "+castSpell);}
int alchvaluez = (int)Math.floor(GetItemShopValue(castOnItem, 0, castOnSlot));

sidebarChangeTimer = 2;
sidebarChange = 6;
sidebarChanging = true;

if(castSpell == 1162) //Low Alch
{
if(playerLevel[6] >= 21) 
{
if((playerHasItemAmount(561, 5) == false) || (playerHasItemAmount(554, 1) == false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(561, 5) == true) && (playerHasItemAmount(554, 1) == true))
{
alchvaluez = (alchvaluez / 4);
deleteItem(castOnItem, castOnSlot, 1);				
addItem(995, alchvaluez);
addSkillXP((45*playerLevel[6]), 6);
startAnimation(712);
playerGfx(112, 0);
newAnimDelay = 4;
newAnim = 712;
newAnimRequired = true;
deleteItem(561,getItemSlot(561), 5);
deleteItem(554,getItemSlot(554), 1);
} 
}
else if(playerLevel[6] <= 21) 
{
sendMessage("You need at least 21 Magic to cast Low Level Alchemy");
}
}

else if(castSpell == 1178) //High Alch fixed by Joey
{
if(playerLevel[6] >= 55) 
{
if((playerHasItemAmount(561, 1) == false) || (playerHasItemAmount(554, 1) == false))
{
sendMessage("NOOB you need 1 nat, 1 fire.");
}
else if((playerHasItemAmount(561, 1) == true) && (playerHasItemAmount(554, 1) == true))
{
alchvaluez = (alchvaluez);
deleteItem(castOnItem, castOnSlot, 1);
addItem(995, alchvaluez);
addSkillXP((80*playerLevel[6]), 6);
startAnimation(712);
playerGfx(113, 0);
newAnimDelay = 6;
newAnim = 712;
newAnimRequired = true;
deleteItem(561,getItemSlot(561), 1);
deleteItem(554,getItemSlot(554), 1);
} 
}
else if(playerLevel[6] <= 54) 
{
sendMessage("You need at least 55 Magic to cast High Level Alchemy");
}
}

		else if(castSpell == 1155) { //Enchant lvl 1(saph)
			if(playerLevel[6] >= 7) {
				if(castOnItem == 1637) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2550, 1);
					addSkillXP(18, 6);
				}
				else if(castOnItem == 1656) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(3853, 1);
					addSkillXP(18, 6);
				}
				else if(castOnItem == 1694) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1727, 1);
					addSkillXP(18, 6);
				} else {
					sendMessage("This needs to be cast on Saphire Jewelry");
				}
			} else {
				sendMessage("You need atleast 7 Magic to cast Enchant Lvl-1 Jewelry");
			}
		}
		else if(castSpell == 1165) { //Enchant lvl 2(emme)
			if(playerLevel[6] >= 27) {
				if(castOnItem == 1639) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2552, 1);
					addSkillXP(37, 6);
				}
				else if(castOnItem == 1658) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(5521, 1);
					addSkillXP(37, 6);
				}
				else if(castOnItem == 1696) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1729, 1);
					addSkillXP(37, 6);
				} else {
					sendMessage("This needs to be cast on Emerald Jewelry");
				}
			} else {
				sendMessage("You need atleast 27 Magic to cast Enchant Lvl-2 Jewelry");
			}
		}
		else if(castSpell == 1176) { //Enchant lvl 3(ruby)
			if(playerLevel[6] >= 49) {
				if(castOnItem == 1641) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2568, 1);
					addSkillXP(59, 6);
				}
				else if(castOnItem == 1698) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1725, 1);
					addSkillXP(59, 6);
				} else {
					sendMessage("This needs to be cast on Ruby Jewelry");
				}
			} else {
				sendMessage("You need atleast 49 Magic to cast Enchant Lvl-3 Jewelry");
			}
		}
		else if(castSpell == 1180) { //Enchant lvl 4(diam)
			if(playerLevel[6] >= 57) {
				if(castOnItem == 1643) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2570, 1);
					addSkillXP(67, 6);
				}
				else if(castOnItem == 1700) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1731, 1);
					addSkillXP(67, 6);
				} else {
					sendMessage("This needs to be cast on Diamond Jewelry");
				}
			} else {
				sendMessage("You need atleast 57 Magic to cast Enchant Lvl-4 Jewelry");
			}
		}
		else if(castSpell == 1187) { //Enchant lvl 5(drag)
			if(playerLevel[6] >= 68) {
				if(castOnItem == 1645) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2572, 1);
					addSkillXP(78, 6);
				}
				else if(castOnItem == 1702) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1704, 1);
					addSkillXP(78, 6);
				} else {
					sendMessage("This needs to be cast on Dragonstone Jewelry");
				}
			} else {
				sendMessage("You need atleast 68 Magic to cast Enchant Lvl-5 Jewelry");
			}
		}
		else if(castSpell == 1173) { //Superheat Item
			if(playerLevel[6] >= 43) {
				if(castOnItem == 436 && (amountOfItem(438) >= 1)) {
					deleteItem(castOnItem, castOnSlot, 1);
					deleteItem(438, getItemSlot(438), 1);
					addItem(2349, 1);
					addSkillXP(53, 6);
				}
				else if((castOnItem == 438) && (amountOfItem(436) >= 1)) {
					deleteItem(castOnItem, castOnSlot, 1);
					deleteItem(436, getItemSlot(436), 1);
					addItem(2349, 1);
					addSkillXP(53, 6);
				}
				else if(castOnItem == 440) {
					if(amountOfItem(453) < 2) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2351, 1);
						addSkillXP(53, 6);
					} else if(amountOfItem(453) >= 2) {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<2; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2353, 1);
						addSkillXP(53, 6);
					} else { sendMessage("You need 2 coal to make a steel bar"); }
				}
				else if(castOnItem == 442) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2355, 1);
					addSkillXP(53, 6);
				}
				else if(castOnItem == 444) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2357, 1);
					addSkillXP(53, 6);
				}
				else if((castOnItem == 447)) {
					if(amountOfItem(453) < 4) { sendMessage("You need 4 coal to make a mith bar");
					} else {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<4; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2359, 1);
						addSkillXP(53, 6);
					}
				}
				else if((castOnItem == 449)) {
					if(amountOfItem(453) < 6) { sendMessage("You need 6 coal to make an addy bar");
					} else {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<6; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2361, 1);
						addSkillXP(53, 6);
					}
				}
				else if((castOnItem == 451)) {
					if(amountOfItem(453) < 8) { sendMessage("You need 8 coal to make a rune bar");
					} else {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<8; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2363, 1);
						addSkillXP(53, 6);
					}
				}
			} else {
				sendMessage("You need atleast 43 Magic to cast Superheat Item");
			}
		}
	break;

case 249: //Magic on Players
	// MAGE_00
	int playerIndexx = inStream.readSignedWordA();
	int pcombat = server.playerHandler.players[playerIndexx].combat;
	spellID = inStream.readSignedWordBigEndian();
	client pl2 = (client) server.playerHandler.players[playerIndexx];

	if(!isPlayerSpamming()) {
		playerSpamTimer = System.currentTimeMillis();
		if(pl2 == null) return;

		ResetAttack();
		ResetAttackNPC();

		if(isInPKZone() && duelStatus == -1) {	
			MageAttackIndex = playerIndexx+1;
				AttackPlayerMagic(playerIndexx);
		}
		else sendMessage("That player is in a safe zone.");
		
		if(spellID == 12455) { // Xerozcheez: Teleother cammy bitches (H)

			if(playerLevel[6] >= 150) {
				pl2.teleOtherRequest("Camelot", playerId);
				sendMessage("You send a tele request to "+pl2.playerName);
			}
			else if(playerLevel[6] < 150) {
				sendMessage("NO TELEOTHER NOOB");
			}

		}

		if(spellID == 12435) { // Xerozcheez: Teleother fally bitches (H)

			if(playerLevel[6] >= 150) {
				pl2.teleOtherRequest("Falador", playerId);
				sendMessage("You send a tele request to "+pl2.playerName);
			}
			else if(playerLevel[6] < 150) {
				sendMessage("NO TELEOTHER NOOB");
			}

		}

		if(spellID == 12425) { // Xerozcheez: Teleother lumby bitches (H)

			if(playerLevel[6] >= 150) {
				pl2.teleOtherRequest("Falador", playerId);
				sendMessage("You send a tele request to "+pl2.playerName);
			}
			else if(playerLevel[6] < 150) {
				sendMessage("NO TELEOTHER NOOB");
			}

			teleportToX = absX;
			teleportToY = absY;
		}
	}
	teleportToX = absX;
	teleportToY = absY;
	break;
	
	
case 131: //Magic on NPCs
	int npcIndex = inStream.readSignedWordBigEndianA();
	int _NPCID = server.npcHandler.npcs[npcIndex].npcType;
	if(lists.safeNPCs.exists(_NPCID) || DIALOGUEHANDLER.exists(_NPCID)){
		sendMessage("That's a friendly NPC that I should not attack.");
		updatePlayerPosition();
		break;
	}			
	if(SLAYER.slayerNPC.exists(_NPCID)){ //slayer NPC
		if(playerLevel[18] < this.SLAYER.getTaskLevel(_NPCID) && slayerNPC != _NPCID){
			sendMessage("You need a higher Slayer level to do that.");
			break;
		}
	}
		spellID = inStream.readSignedWordA();
		magicOnNPC(npcIndex);
	break;


			case 3:			// focus change
        int focus = inStream.readUnsignedByte();
				break;
			case 86:		// camera angle
       int CameraY = inStream.readUnsignedWord();
        int CameraX = inStream.readUnsignedWordA(); 
				break;
				
				
//mouse clicks	
//case 241:
//break;



                        case 924:
                              break;
			case 103:		//Custom player command, the ::words
				String playerCommand = inStream.readString();
				if (debugmode)debug("playerCommand: "+playerCommand);
				customCommand(playerCommand);
      				break;


			case 214:	// change item places
				somejunk = inStream.readUnsignedWordA(); //junk
				int itemFrom = inStream.readUnsignedWordA();// slot1
				int itemTo = (inStream.readUnsignedWordA() - 128);// slot2
				//println_debug(somejunk+" moveitems: From:"+itemFrom+" To:"+itemTo);
				moveItems(itemFrom, itemTo, somejunk);
				updateIdle();
				break;
				
			case 41: // wear item
				int wearID = inStream.readUnsignedWord();
				int wearSlot = inStream.readUnsignedWordA();
				interfaceID = inStream.readUnsignedWordA();
				if (!canwear(wearID, wearSlot))
					break;
				
				else {
					for (int I = 0; I < twoHanderz.length; I++) {
						if(wearID == twoHanderz[I] && playerEquipment[playerShield] != -1 && freeSlots() >= 1) {
							wear(wearID, wearSlot);
							remove(playerEquipment[playerShield], 5);
						}
						else if(wearID == twoHanderz[I] && playerEquipment[playerShield] != -1 && freeSlots() < 1) {
							//sendMessage("You can't wield a two handed weapon with a shield!");
							return;
						}
						else if(wearID == playerEquipment[playerShield] && playerEquipment[playerWeapon] == twoHanderz[I] && freeSlots() >= 1) {
							wear(wearID, wearSlot);
							remove(playerEquipment[playerWeapon], 3);
						}
						else {
							wear(wearID, wearSlot);
							break;
						}
					}
				}
				flushOutStream();

				break;

				case 145:	//remove item (opposite for wearing) - bank 1 item - value of item
					interfaceID = inStream.readUnsignedWordA();
					int removeSlot = inStream.readUnsignedWordA();
					int removeID = inStream.readUnsignedWordA();
					if(debugmode){
						debug("RemoveItem: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );}		
					if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
						if(isUntradable(removeID)) 
							sendMessage("You cannot stake this item"); 
						else
							stakeItem(removeID, removeSlot, 1);
					}  else if (interfaceID == 6669 && duelStatus >= 1) { //remove from duel window
						fromDuel(removeID, removeSlot, 1);
					}
					else if (interfaceID == 1688) { 
						if (playerEquipment[removeSlot] == removeID) {
							remove(removeID , removeSlot);
							if(removeSlot == playerWeapon){
								playerSE = GetStandAnim(playerEquipment[playerWeapon]);
								playerSEW = GetWalkAnim(playerEquipment[playerWeapon]);
								playerSER = GetRunAnim(playerEquipment[playerWeapon]);
								playerSEA = 0x326;
								pEmote = playerSE;
							}
						}
					} else if (interfaceID == 5064) { //remove from bag to bank
						bankItem(removeID , removeSlot, 1);
					} else if (interfaceID == 5382) { //remove from bank
						fromBank(removeID , removeSlot, 1);
					} else if (interfaceID == 3322) { //remove from bag to trade window
						if(isUntradable(removeID)){
							sendMessage("You cannot trade this item.");
						} 
						else{
							tradeItem(removeID , removeSlot, 1);
						}
					} else if (interfaceID == 3415) { //remove from trade window
						fromTrade(removeID, removeSlot, 1);
					} else if (interfaceID == 3823) { //Show value to sell items
						if (Item.itemSellable[removeID] == false && debugmode == true) {
							sendMessage("Call2: I cannot sell "+getItemName(removeID)+".");
						} else {
							boolean IsIn = false;
							if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
								for (int j = 0; j <= server.shopHandler.ShopItemsStandard[MyShopID]; j++) {
									if (removeID == (server.shopHandler.ShopItems[MyShopID][j] - 1)) {
										IsIn = true;
										break;
									}
								}
							} else {
								IsIn = true;
							}
							if (IsIn == false) {
								sendMessage("You cannot sell "+getItemName(removeID)+" in this store.");
							} else {
								int ShopValue = (int)Math.floor(GetItemShopValue(removeID, 1, removeSlot));
								String ShopAdd = "";
								if (ShopValue <= 1)
								{
									ShopValue = (int)Math.floor(GetItemShopValue(removeID, 0, removeSlot));
								}
								if (ShopValue >= 1000 && ShopValue < 1000000) {
									ShopAdd = " (" + (ShopValue / 1000) + "K)";
								} else if (ShopValue >= 1000000) {
									ShopAdd = " (" + (ShopValue / 1000000) + " million)";
								}
								sendMessage(getItemName(removeID)+": shop will buy for "+ShopValue+" coins"+ShopAdd);
							}
						}
					} else if (interfaceID == 3900) { //Show value to buy items
						int ShopValue = (int)Math.floor(GetItemShopValue(removeID, 0, removeSlot));
						String ShopAdd = "";
						if (ShopValue <= 1)
						{
							ShopValue = (int)Math.floor(GetItemShopValue(removeID, 0, removeSlot));
						}
						if (ShopValue >= 1000 && ShopValue < 1000000) {
							ShopAdd = " (" + (ShopValue / 1000) + "K)";
						} else if (ShopValue >= 1000000) {
							ShopAdd = " (" + (ShopValue / 1000000) + " million)";
						}
						sendMessage(getItemName(removeID)+": currently costs "+ShopValue+" coins"+ShopAdd);
					} else if (interfaceID == 1119) //Smith Column 1
					{
						try {

							if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
								if(playerHasItem(2347) == true) {
									if(canSmith(removeID)) {
										RemoveAllWindows();
										startAnimation(898);
										addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
										ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));

									} else {
										sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
									}
								} else {
									sendMessage("You need a hammer to smith this item.");
								}
							} else {
								sendMessage("You dont have enough bars to make this");
							}
						} catch(Exception e) { 
							//sendMessage("You dont have enough bars to make this");
						}
					}
					else if (interfaceID == 1120) //Smith Column 2
					{
						try {

							if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
								if(playerHasItem(2347) == true) {
									if(canSmith(removeID)) {
										RemoveAllWindows();
										startAnimation(898);
										addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
										ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
									} else {
										sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
									}
								} else {
									sendMessage("You need a hammer to smith this item.");
								}
							} else {
								sendMessage("You dont have enough bars to make this");
							}
						} catch(Exception e) { 
							//sendMessage("You dont have enough bars to make this");
						}
					}
					else if (interfaceID == 1121)
					{
						try {

							if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
								if(playerHasItem(2347) ==true) {
									if(canSmith(removeID)) {
										RemoveAllWindows();
										startAnimation(898);
										addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
										ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
									} else {
										sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
									}
								} else {
									sendMessage("You need a hammer to smith this item.");
								}
							} else {
								sendMessage("You dont have enough bars to make this");
							}
						} catch(Exception e) { 
							//sendMessage("You dont have enough bars to make this"); 
						}
					}
					else if (interfaceID == 1122)
					{
						try {

							if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
								if(playerHasItem(2347) ==true) {
									if(canSmith(removeID)) {
										RemoveAllWindows();
										startAnimation(898);
										addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
										ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
									} else {
										sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
									}
								} else {
									sendMessage("You need a hammer to smith this item.");
								}
							} else {
								sendMessage("You dont have enough bars to make this");
							}
						} catch(Exception e) {
							// sendMessage("You dont have enough bars to make this"); 
						}
					}
					else if (interfaceID == 1123)
					{
						try {

							if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
								if(playerHasItem(2347) ==true) {
									if(canSmith(removeID)) {
										RemoveAllWindows();
										startAnimation(898);
										addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
										if(removeSlot == 0)
											ReplaceItems(removeID, removeBar(removeID), 10, barsNeeded(removeSlot, interfaceID));
										if(removeSlot == 1)
											ReplaceItems(removeID, removeBar(removeID), 15, barsNeeded(removeSlot, interfaceID));
										if(removeSlot == 2)
											ReplaceItems(removeID, removeBar(removeID), 5, barsNeeded(removeSlot, interfaceID));
										else
											ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));  

									} else {
										sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
									}
								} else {
									sendMessage("You need a hammer to smith this item.");
								}
							} else {
								sendMessage("You dont have enough bars to make this");
							}
						} catch(Exception e) { 
							//sendMessage("You dont have enough bars to make this");
						}
					}

					break;

			case 117:	//bank 5 items - sell 1 item
				interfaceID = inStream.readSignedWordBigEndianA();
				removeID = inStream.readSignedWordBigEndianA();
				removeSlot = inStream.readSignedWordBigEndian();

				if(debugmode) debug("RemoveItem 5: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );
                                 if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(removeID, removeSlot, 5);
				}  else if (interfaceID == 6669) { //remove from duel window
					fromDuel(removeID, removeSlot, 5);
				}
				else if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 5);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 5);
				} else if (interfaceID == 3322 && duelStatus != 1) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item"); 
                                        else
					tradeItem(removeID , removeSlot, 5);
				} else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, 5);
				} else if (interfaceID == 3823) { //Show value to sell items
					sellItem(removeID, removeSlot, 1);
				} else if (interfaceID == 3900) { //Show value to buy items
					buyItem(removeID, removeSlot, 1);
				} else if (interfaceID == 1119) //Smith Column 1
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
						for(int e=0; e<5; e++) {
                                                        RemoveAllWindows();
                                                        startAnimation(898);
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
						}
						} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1120) //Smith Column 2
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1121)
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1122)
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1123)
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347) ==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        if(removeSlot == 0)
                                                        ReplaceItems(removeID, removeBar(removeID), 10, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 1)
                                                        ReplaceItems(removeID, removeBar(removeID), 15, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 2)
                                                        ReplaceItems(removeID, removeBar(removeID), 5, barsNeeded(removeSlot, interfaceID));
                                                        else
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this"); 
					}
				}
                                
				
				break;

			case 43:	//bank 10 items - sell 5 items
				interfaceID = inStream.readUnsignedWordBigEndian();
				removeID = inStream.readUnsignedWordA();
				removeSlot = inStream.readUnsignedWordA();

				if(debugmode) debug("RemoveItem 10: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );
                                 if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(removeID, removeSlot, 10);
				}  else if (interfaceID == 6669 && duelStatus >= 1) { //remove from duel window
					fromDuel(removeID, removeSlot, 10);
				}
				else if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 10);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 10);
				} else if (interfaceID == 3322 && duelStatus != 1) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item"); 
                                        else
					tradeItem(removeID , removeSlot, 10);
				} else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, 10);
				} else if (interfaceID == 3823) { //Show value to sell items
					sellItem(removeID, removeSlot, 5);
				} else if (interfaceID == 3900) { //Show value to buy items
					buyItem(removeID, removeSlot, 5);
				} else if (interfaceID == 1119) //Smith Column 1
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1120) //Smith Column 2
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) {
					// sendMessage("You dont have enough bars to make this"); 
					 }
				}
				else if (interfaceID == 1121)
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1122)
				{
					try {

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1123)
				{
					try {
					System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);
					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        if(removeSlot == 0)
                                                        ReplaceItems(removeID, removeBar(removeID), 10, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 1)
                                                        ReplaceItems(removeID, removeBar(removeID), 15, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 2)
                                                        ReplaceItems(removeID, removeBar(removeID), 5, barsNeeded(removeSlot, interfaceID));
                                                        else
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
				//	sendMessage("You dont have enough bars to make this");
				 }
				}
				
				

				break;

			case 129:	//bank all items - sell 10 items
				removeSlot = inStream.readUnsignedWordA();
				interfaceID = inStream.readUnsignedWord();
				removeID = inStream.readUnsignedWordA();

				debug("RemoveItem all: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );

				if (interfaceID == 5064) { //remove from bag to bank
					if (Item.itemStackable[removeID] == true) {
						bankItem(playerItems[removeSlot] , removeSlot, playerItemsN[removeSlot]);
					} 
					else {
						bankItem(playerItems[removeSlot] , removeSlot, itemAmount(playerItems[removeSlot]));
					}
				} 
				else if (interfaceID == 5382) { //remove from bank
					fromBank(bankItems[removeSlot] , removeSlot, bankItemsN[removeSlot]);
				} 
				else if (interfaceID == 3322 && duelStatus != 1) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item"); 
                                        else
					tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				} else if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				}  else if (interfaceID == 6669 && duelStatus >= 1) { //remove from duel window
					fromDuel(removeID, removeSlot, playerTItemsN[removeSlot]);
				}  else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, playerTItemsN[removeSlot]);
				} else if (interfaceID == 3823) { //Show value to sell items
					sellItem(removeID, removeSlot, 10);
				} else if (interfaceID == 3900) { //Show value to buy items
					buyItem(removeID, removeSlot, 10);
				} 

				break;


			case 135:	//bank X items
				outStream.createFrame(27);
				XremoveSlot = inStream.readSignedWordBigEndian();
				XinterfaceID = inStream.readUnsignedWordA();
				XremoveID = inStream.readSignedWordBigEndian();

				if(debugmode) debug("RemoveItem X: "+XremoveID +" InterID: "+XinterfaceID +" slot: "+XremoveSlot);

				break;

			case 208:	//Enter Amount Part 2
				int EnteredAmount = inStream.readDWord();
				if (XinterfaceID == 5064) { //remove from bag to bank
					bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 5382) { //remove from bank
					fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} 
				
				 else if (XinterfaceID == 3900) { //Shop
					if (EnteredAmount <= 250)
						buyItem(XremoveID, XremoveSlot, EnteredAmount);
					else
						sendMessage("You cannot buy more than 250 items at a time.");
				}
                       
				else if (XinterfaceID == 3322) { //remove from bag to trade window
					tradeItem(XremoveID, XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 3415) { //remove from trade window
					fromTrade(XremoveID, XremoveSlot, EnteredAmount);
				}else if (XinterfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(XremoveID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(XremoveID, XremoveSlot, EnteredAmount);
				}  else if (XinterfaceID == 6669 && duelStatus >= 1) { //remove from duel window
					fromDuel(XremoveID, XremoveSlot, EnteredAmount);
				}

				break;

			case 60:	//Enter Name? 
			        String name = inStream.readString(); // Xerozcheez: I don't know if this is right method, because in the client it sends using method404 which I have not seen before

				break;
						//todo - wtf is this
                        case 79: // light item
				int itemY2 = inStream.readSignedWordBigEndian();
				int itemID2 = inStream.readUnsignedWord();
				int itemX2 = inStream.readSignedWordBigEndian();
                                System.out.println("itemID2: "+itemID2);
                               if (itemID2 == 3006 && q3stage == 4 && itemX2 == 3288 && itemY2 == 3886) {
                                if(playerHasItem(590)) {
                                  sendMessage("You light the fireworks");
                                  int itemAmount2 = ItemHandler.itemAmount(itemID2, itemX2, itemY2);
				  ItemHandler.removeItem(itemID2, itemX2, itemY2, itemAmount2);
				  removeGroundItem(itemX2, itemY2, itemID2);
                                  ItemHandler.addItem(744, itemX2, itemY2, 1, playerId, false);
                                 }
                                  else {
                                   sendMessage("You need a tinderbox to light the firework.");
                                 }
                                }
                                break;
			case 87:		// drop item
				int droppedItem = inStream.readUnsignedWordA();
				somejunk = inStream.readUnsignedByte()+inStream.readUnsignedByte();
				int slot = inStream.readUnsignedWordA();
				//println_debug("dropItem: "+droppedItem+" Slot: "+slot);
                                if(isUntradable(droppedItem)) {
                                sendMessage("You drop the "+getItemName(droppedItem)+", it vanishes into the ground.");
                                deleteItem(droppedItem, slot, playerItemsN[slot]);
                                }
                                if(droppedItem == 744 && absX == 2780 && absY == 3515 && q3stage == 5) {
server.npcHandler.newNPC(1645, absX+1, absY, heightLevel, absX + 3, absY + 3, absX + -3, absY + -3, 1, server.npcHandler.getHP(1645), false);      
                                }                          
                                else if(wearing == false && playerItems[slot] == droppedItem+1){
				dropItem(droppedItem, slot);
                                }
				break;
                        case 120: // sends sidebar id when clicked while it's flashing - found by xerozcheez  
                        int sidebarID = inStream.readUnsignedByte();
                        System.out.println("Packet 120: Sidebar Id: "+sidebarID);
                        break;
                        case 185:               //clicking most buttons
		actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
                                if(debugmode)
                                debug("Case 185: "+actionButtonId);
                                if(lists.prayerList.exists(actionButtonId)){
                                	this.PRAY.checkPrayer(actionButtonId);
                                	break;
                                }
                                
				switch(actionButtonId) {
					//These values speak for themselves
					//case 4126: windstrike break;
					
//case 59136:
//panellist();
//break;			
				
				case 28170:
					menu(this.menuHandler.newBeginnings());
					break;
				
				case 150: //auto retaliate on
					autoRetaliate = 1;
					sendQuest("@gre@Auto Retaliate", 155); //auto retaliate
					break;
				case 151: //auto retaliate off
					autoRetaliate = 0;
					sendQuest("@red@Auto Retaliate", 155); //auto retaliate
					break;
		
				case 54104:
					Menu(this.menuHandler.farmingGuide());
					break;

case 73120:
panelnum3();
break;

case 73121:
panelnum4();
break;

case 73122:
	panelnum5();
	break;

case 73119:
panelnum = 2;
sendMessage("Panel Number is now 2.");
sendMessage("Party Size: "+psize+".");
break;

case 73118:
panelnum = 1;
sendMessage("Panel Number is now 1.");
sendMessage("current object: "+panelobj+". Object direction: "+paneldi+".");
if (panelprint == true){
sendMessage("Write to file: true");
}
else if (panelprint == false){sendMessage("Write to file: false");}
break;

case 73117:
if (panelnum == 1){
if (panelprint == true){
try {
	createNewTileObject(absX, absY, panelobj, paneldi, 10);  
   bw = new BufferedWriter(new FileWriter("CFG/objects.txt", true));
	 bw.write("makeGlobalObject("+absX+", "+absY+", "+panelobj+", "+paneldi+", 10);");
	 bw.newLine();
	 bw.flush();
	sendMessage("Object ID "+panelobj+" sucessful input.");
	}
	catch(Exception e) {
sendMessage("Wrong Syntax! Use as ::object ##### #");
}
	}
	else if (panelprint == false){
	makeGlobalObject(absX, absY, panelobj, paneldi, 10);
	}
}
else if (panelnum != 1){
panelex();
}
break;

case 21168:
RemoveAllWindows();
break;

case 73104:
sendMessage("X: "+absX+" Y: "+absY);
println("X: "+absX+" Y: "+absY);
break;

case 73108:
playerMenu();
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
   remove(playerEquipment[playerWeapon], playerWeapon);
   plr.remove(playerEquipment[playerWeapon], playerWeapon);
  }
  if(duelRule[5]) {
   remove(playerEquipment[playerHat], playerHat);
   remove(playerEquipment[playerCape], playerCape);
   remove(playerEquipment[playerChest], playerChest);
   remove(playerEquipment[playerShield], playerShield);
   remove(playerEquipment[playerLegs], playerLegs);
   remove(playerEquipment[playerFeet], playerFeet);
   plr.remove(playerEquipment[playerHat], playerHat);
   plr.remove(playerEquipment[playerCape], playerCape);
   plr.remove(playerEquipment[playerChest], playerChest);
   plr.remove(playerEquipment[playerShield], playerShield);
   plr.remove(playerEquipment[playerLegs], playerLegs);
   plr.remove(playerEquipment[playerFeet], playerFeet);
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
quest();
}
break;

case 28165: //
{
questid = 2;
quest();
}
break;

case 28166: // 
{
questid = 3;
quest();
}
break;



case 28171: //
{
questid = 6;
quest();
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
			break;
			// the following Ids are the reason why AR-type cheats are hopeless to make...
			// basically they're just there to make reversing harder
			case 226:
			case 78:
			case 148:
			case 183:
			case 230:
			case 136:
			case 189:
			case 152:
			case 200:
			case 85:
			case 165:
			case 238:
			case 150:
			case 36:
			case 246:
			case 77:
/*QUESTS
case 28164: // Invisible Armour (id 1)
{
questid = 1;
quest();
}*/
				break;
			// any packets we might have missed
			default:
parseIncomingPackets2();
				interfaceID = inStream.readUnsignedWordA();
				int actionButtonId1 = misc.HexToInt(inStream.buffer, 0, packetSize);
				//println_debug("Unhandled packet ["+packetType+", InterFaceId: " +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
				//println_debug("Action Button: "+actionButtonId1);
			break;
		}
	}
	private int somejunk;
		
	public boolean isBow(){
		return lists.bows.exists(playerEquipment[playerWeapon]);
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

public void resetKeepItem() {
keepItem = 0;
keepItem2 = 0;
keepItem3 = 0;
keepItem4 = 0;
keepItemAmount = 0;
keepItemAmount2 = 0;
keepItemAmount3 = 0;
keepItemAmount4 = 0;
}

public void generateKeepItems(){

	//insertion sort
	int totalItems = playerItems.length+playerEquipment.length;
	int[] sorted = new int[totalItems];
	int[] sortedN = new int[totalItems];
	
	for(int i = 0; i < playerItems.length; i++){
		int value = (int)Math.floor(GetItemShopValue(playerItems[i]-1, 0, 0)); //item 1 from player inventory
		boolean insert = false;
		for(int j = 0; j < sorted.length && !insert; j++){ //iterate to find where to insert
			int value2 = (int)Math.floor(GetItemShopValue(sorted[j], 0, 0)); //item at index in sorted array
			if(value >= value2){ //if player inventory item is worth more, then we insert 
				for(int k = totalItems-1; k > j; k--) //move everything aside one
					sorted[k] = sorted[k-1];
				sorted[j] = playerItems[i]-1; //insert
				sortedN[j] = playerItemsN[i];
				insert = true;
			}				
		}
	}
	for(int i = 0; i < playerEquipment.length; i++){
		int value = (int)Math.floor(GetItemShopValue(playerEquipment[i]-1, 0, 0)); //item 1 from player inventory
		boolean insert = false;
		for(int j = 0; j < sorted.length && !insert; j++){ //iterate to find where to insert
			int value2 = (int)Math.floor(GetItemShopValue(sorted[j], 0, 0)); //item at index in sorted array
			if(value >= value2){ //if player inventory item is worth more, then we insert 
				for(int k = totalItems-1; k > j; k--) //move everything aside one
					sorted[k] = sorted[k-1];
				sorted[j] = playerEquipment[i]; //insert
				sortedN[j] = playerEquipmentN[i];
				insert = true;
			}				
		}
	}
	
	keepItem = sorted[0];
	keepItem2 = sorted[1];
	keepItem3 = sorted[2];
	keepItem4 = sorted[3];
	keepItemAmount = sortedN[0];
	keepItemAmount2 = sortedN[1];
	keepItemAmount3 = sortedN[2];
	keepItemAmount4 = sortedN[3];

	if(debugmode){
		System.out.print("\nplayerItems : ");
		for(int i = 0; i < playerItems.length; i++)
			System.out.print(playerItems[i]+", ");

		System.out.print("\nplayerEquipment : ");
		for(int i = 0; i < playerEquipment.length; i++)
			System.out.print(playerEquipment[i]+", ");

		System.out.print("\nsorted : ");
		for(int i = 0; i < sorted.length; i++)
			System.out.print(sorted[i]+", ");
	}
}

	public boolean CheckForSkillUse() { //Use Item On Item
		boolean GoOn = true;
  if (useitems[1] == 946 || useitems[1] == 1777 || useitems[1] == 314 || useitems[1] == 1755 || useitems[0] == 1019 || useitems[0] == 1021 || useitems[0] == 1023 || useitems[0] == 1027 || useitems[0] == 1029 || useitems[0] == 1031 || useitems[0] == 1007) {
			int temp;
			temp = useitems[0];
			useitems[0] = useitems[1];
			useitems[1] = temp;
			temp = useitems[3];
			useitems[3] = useitems[2];
			useitems[2] = temp;
			CheckForSkillUse();
		} else {
			sendMessage("Nothing interesting is happening.");
		}
		return true;
	}
	
	public boolean CheckForSkillUse3(int Item, int Slot) {
		
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
	

	public boolean AreXItemsInBag(int ItemID, int Amount) {
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
			if (ItemCount == Amount) {
				return true;
			}
		}
		return false;
	}
	public int GetItemSlot(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}


public int GetRunAnim(int id) {
if(id == 11337) // D Claws
return 1661;

	if(lists.halberd.exists(id))
		return 0x338;
		
	if(lists.twoHanded.exists(id))
		return 306;
	
if(id == 4151) // whip
return 1661;

if(id == 4565) // basket
return 1836;

if(id == 4734) // karils x bow
return 2077;

if(id == 4153) // maul, sythe, halberd
return 1664;

return 0x338;
}

public int GetWalkAnim(int id) {
	if (id == 11337)
		return 1660;
	
	if(lists.halberd.exists(id))
		return 0x333;
		
	if(lists.twoHanded.exists(id))
		return 307;
	
if(id == 4718) // dharoks axe
return 2064;


if(id == 4151)
return 1660; 

if(id == 4565) // basket
return 1836;

if(id == 4755) // veracs flail
return 2060;

if(id == 4734) // karils x bow
return 2076;

if(id == 4153) // maul
return 1663;

return 0x333;

}

public int GetStandAnim(int id) {
	if (id == 11337)
		return 2061;	
	
if(lists.halberd.exists(id))
	return 0x328;
	
if(lists.twoHanded.exists(id))
	return 301;
	
if(id == 4718) // dharoks axe
return 2065;

if(id == 4565) // basket
return 1836;


if(id == 4755) // veracs flail
return 2061;

if(id == 4734) // karils x bow
return 2074;

if(id == 4153) // maul
return 1662;

return 0x328;

}


	public int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}
	public void AddDroppedItems() {
		if (IsDropping == false) {
			IsDropping = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < server.itemHandler.DropItemCount; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.itemHandler.DroppedItemsX[i];
					tmpY = server.itemHandler.DroppedItemsY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if (calcX >= -16 && calcX <= 15 && calcY >= -16 && calcY <= 15 && MustDelete[i] == false && server.itemHandler.DroppedItemsH[i] == heightLevel) {
						if (IsDropped[i] == false && (server.itemHandler.DroppedItemsDDelay[i] <= 0 || server.itemHandler.DroppedItemsDropper[i] == playerId)) {
							IsDropped[i] = true;
							outStream.createFrame(85);
							outStream.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
							outStream.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
							outStream.createFrame(44); //create item frame
							outStream.writeWordBigEndianA(server.itemHandler.DroppedItemsID[i]);
							outStream.writeWord(server.itemHandler.DroppedItemsN[i]); //amount
							outStream.writeByte(0); // x(4 MSB) y(LSB) coords
						}
					} else if (IsDropped[i] == true || MustDelete[i] == true) {
						outStream.createFrame(85);
						outStream.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
						outStream.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
						outStream.createFrame(156); //remove item frame
						outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
						outStream.writeWord(server.itemHandler.DroppedItemsID[i]);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (MustDelete[i] == true) {
							MustDelete[i] = false;
							server.itemHandler.DroppedItemsDeletecount[i]++;
							if ((LastPlayerInList == playerId || LastPlayerInList == -1) && server.itemHandler.DroppedItemsDeletecount[i] == TotalPlayers) {
								if (server.itemHandler.DroppedItemsAlwaysDrop[i] == true) {
									server.itemHandler.DroppedItemsDropper[i] = -1;
									server.itemHandler.DroppedItemsDDelay[i] = server.itemHandler.SDID;
								} else {
									server.itemHandler.ResetItem(i);
								}
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsDropped[i] = false;
									}
								}
							}
						} else {
							IsDropped[i] = false;
						}
					}
				}
			}
			IsDropping = false;
		}
	}

public void pmstatus(int status) { //status: loading = 0  connecting = 1  fine = 2 
		outStream.createFrame(221);
		outStream.writeByte(status);
	}

	public boolean isinpm(long l) {
		for(int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				if(l == friends[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public void pmupdate(int pmid, int world) {
		long l = misc.playerNameToInt64(handler.players[pmid].playerName);
		if (handler.players[pmid].Privatechat == 0) {
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] != 0) {
					if (l == friends[i]) {
						loadpm(l, world);
						return;
					}
				}
			}
		} else if(handler.players[pmid].Privatechat == 1) {
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i] != 0) {
					if (l == friends[i1]) {
    			        		if(handler.players[pmid].isinpm(misc.playerNameToInt64(playerName)) && playerRights > 2) {
    			    				loadpm(l, world);
							return;
						} else {
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		} else if(handler.players[pmid].Privatechat == 2) {
			for(int i2 = 0; i2 < friends.length; i2++) {
				if (friends[i] != 0) {
					if(l == friends[i2] && playerRights < 2) {
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}

	public void sendpm(long name, int rights, byte[] chatmessage, int messagesize) {
		outStream.createFrameVarSize(196);
		outStream.writeQWord(name);
		outStream.writeDWord(handler.lastchatid++);//must be different for each message
		outStream.writeByte(rights);
		outStream.writeBytes(chatmessage, messagesize , 0);
		outStream.endFrameVarSize();
	}

	public void loadpm(long name, int world) {
		if(world != 0) {
                world += 9;}
                else if(world == 0){
                world += 1;
                }
		outStream.createFrame(50);
		outStream.writeQWord(name);
		outStream.writeByte(world);
	}

	int applySnare = -1;
	
	public void ManipulateDirection() {
		//playerMD = misc.direction(absX, absY, skillX, skillY);
		if (playerMD != -1) {
			//playerMD >>= 1;
			updateRequired = true;
			dirUpdateRequired = true;
		}
	}
	
	

	public int GetNPCID(int coordX, int coordY) {
		for (int i = 0; i < server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (server.npcHandler.npcs[i].absX == coordX && server.npcHandler.npcs[i].absY == coordY) {
					return server.npcHandler.npcs[i].npcType;
				}
			}
		}
		return 1;
	}
	
	public double GetItemShopValue(int ItemID, int Type, int fromSlot) {
		if(ItemID <= 0) return -1;
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;
		if (server.itemHandler.ItemList.exists(ItemID))
			ShopValue = server.itemHandler.ItemList.getCurrentItem().ShopValue;
		
		/*Overstock = server.shopHandler.ShopItemsN[MyShopID][fromSlot] - server.shopHandler.ShopItemsSN[MyShopID][fromSlot];*/
		TotPrice = (ShopValue * 1); //Calculates price for 1 item, in db is stored for 0 items (strange but true)
		/*if (Overstock > 0 && TotPrice > 1) { //more then default -> cheaper
			TotPrice -= ((ShopValue / 100) * (1.26875 * Overstock));
		} else if (Overstock > 0 && TotPrice < 1) { //more then default -> cheaper
			TotPrice = ((ShopValue / 100) * (1.26875 * Overstock));
		} else if (Overstock < 0) { //less then default -> exspensive
			TotPrice += ((ShopValue / 100) * (1.26875 * Overstock));
		}*/
		if (server.shopHandler.ShopBModifier[MyShopID] == 1) {
			TotPrice *= 1.25; //25% more expensive (general stores only)
			if (Type == 1) {
				TotPrice *= 0.4; //general store buys item at 40% of its own selling value
			}
		} else if (Type == 1) {
			TotPrice *= 0.6; //other stores buy item at 60% of their own selling value
		}
		return TotPrice;
	}
	public int GetUnnotedItem(int ItemID) {
		String NotedName = "";
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemListArray[i] != null) {
				if (server.itemHandler.ItemListArray[i].itemId == ItemID) {
					NotedName = server.itemHandler.ItemListArray[i].itemName;
				}
			}
		}
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemListArray[i] != null) {
				if (server.itemHandler.ItemListArray[i].itemName.equalsIgnoreCase(NotedName)) {
					if (server.itemHandler.ItemListArray[i].itemDescription.startsWith("Swap this note at any bank") == false) {
						return server.itemHandler.ItemListArray[i].itemId;
					}
				}
			}
		}
		return 0;
	}

	public void appendPos() {
		try {
		ponline();
			sendQuest("X: "+absX+" Y: "+absY, 18803);
		} catch(Exception e) { debug("Error"); }
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
	public void WriteBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < playerBonus.length; i++) {
			if (playerBonus[i] >= 0) {
				send = BonusName[i]+": +"+playerBonus[i];
			} else {
				send = BonusName[i]+": -"+java.lang.Math.abs(playerBonus[i]);
			}
			
			if (i == 10) {
				offset = 1;
			}
			sendFrame126(send, (1675+i+offset));
		}
		/*for (int i = 4000; i <= 7000; i++) {
			sendFrame126("T"+i, i);
			println_debug("Sended: Test"+i);
		}*///USED FOR TESTING INTERFACE NUMBERS !
	} //MaxHit
	
	/**
	 * Calculates a player's max hit based on global variables
	 */
//	public int CalculateMaxHit() {
//    double MaxHit = 0;
//    
//		double StrBonus = playerBonus[10]; //Strength Bonus
//		double Strength = playerLevel[playerStrength]; //Strength
//		if(strEffect > 0) Strength += Strength*((double)strEffect/100.0); 
//		if (FightType == 1 || FightType == 4) { //Accurate & Defensive
//			MaxHit += (double)(1 + (double)((StrBonus * Strength) * 0.00515));
//			//MaxHit += (double)(1.25 + (double)((double)(StrBonus * Strength) * 0.00515));
//		} else if (FightType == 2) { //Aggresive
//			MaxHit += (double)(1 + (double)((StrBonus * Strength) * 0.00515));
//			//MaxHit += (double)(1.25 + (double)((double)(StrBonus * Strength) * 0.00515));
//		} else if (FightType == 3) { //Controlled
//			MaxHit += (double)(1 + (double)((StrBonus * Strength) * 0.00515));
//			//MaxHit += (double)(1.25 + (double)((double)(StrBonus * Strength) * 0.00515));
//		}
//		if (StrPotion == 1) { //Strength Potion
//			MaxHit += (Strength * 0.0014);
//		} else if (StrPotion == 2) { //Super Strength Potion
//			MaxHit += (Strength * 0.0205);
//		}
//		playerMaxHit = (int)Math.floor(MaxHit);
//		return playerMaxHit;
//}
	
//public int CalculateRange() {
//	double MaxHit = 0;
//	int RangeBonus = playerBonus[5]; //Range Bonus
//	int Range = playerLevel[4]; //Range
//	MaxHit += (double)(1.00 + (double)((double)(RangeBonus * Range) * 0.00095));
//	MaxHit += (double)(Range * 0.085);
//	playerMaxHit = (int)Math.floor(MaxHit);
//	playerMaxHit += misc.random(this.BOWHANDLER.getBonus());
//	return playerMaxHit;
//}




	public boolean CheckSmelting(int ItemID, int ItemSlot) {
		boolean GoFalse = false;
		switch (ItemID) {
			case 436: //copper ore
				if (IsItemInBag(438) == true) {
					smelting[0] = 1;
					smelting[1] = 1;
					if (misc.random(2) == 1) {
						smelting[2] = 6;
					} else {
						smelting[2] = 7;
					}
					smelting[3] = 2349;
				} else {
					sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
					return false;
				}
				break;
			case 438: //tin
				if (IsItemInBag(436) == true) {
					smelting[0] = 1;
					smelting[1] = 1;
					if (misc.random(2) == 1) {
						smelting[2] = 6;
					} else {
						smelting[2] = 7;
					}
					smelting[3] = 2349;
				} else {
					sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
					return false;
				}
				break;
			case 440: //iron
				smelting[0] = 1;
				if (AreXItemsInBag(453, 2) == true) {
					smelting[1] = 30;
					if (misc.random(2) == 1) {
						smelting[2] = 17;
					} else {
						smelting[2] = 18;
					}
					smelting[3] = 2353;
					smelting[6] = 2;
				} else {
					smelting[1] = 15;
					if (misc.random(2) == 1) {
						smelting[2] = 12;
					} else {
						smelting[2] = 13;
					}
					smelting[3] = 2349;
				}
				break;
			case 2892: //elemental
				if (AreXItemsInBag(453, 4) == true) {
					smelting[0] = 1;
					smelting[1] = 20;
					smelting[2] = 18;
					smelting[3] = 2893;
					smelting[6] = 4;
				} else {
					sendMessage("You need 1 elemental ore and 4 coal to smelt 1 elemental bar.");
					return false;
				}
				break;
			case 442: //silver
				smelting[0] = 1;
				smelting[1] = 20;
				if (misc.random(2) == 1) {
					smelting[2] = 13;
				} else {
					smelting[2] = 14;
				}
				smelting[3] = 2355;
				break;
			case 444: //gold
				smelting[0] = 1;
				smelting[1] = 40;
				if (playerEquipment[playerHands] == 776) {
					if (misc.random(2) == 1) {
						smelting[2] = 56;
					} else {
						smelting[2] = 57;
					}
				} else {
					if (misc.random(2) == 1) {
						smelting[2] = 22;
					} else {
						smelting[2] = 23;
					}
				}
				smelting[3] = 2357;
				break;
			case 447: //mithril
				if (AreXItemsInBag(453, 4) == true) {
					smelting[0] = 1;
					smelting[1] = 50;
					smelting[2] = 30;
					smelting[3] = 2359;
					smelting[6] = 4;
				} else {
					sendMessage("You need 1 mithril ore and 4 coal to smelt 1 mithril bar.");
					return false;
				}
				break;
			case 449: //adamantite
				if (AreXItemsInBag(453, 6) == true) {
					smelting[0] = 1;
					smelting[1] = 70;
					if (misc.random(2) == 1) {
						smelting[2] = 37;
					} else {
						smelting[2] = 38;
					}
					smelting[3] = 2361;
					smelting[6] = 6;
				} else {
					sendMessage("You need 1 adamant ore and 4 coal to smelt 1 adamant bar.");
					return false;
				}
				break;
			case 451: //runite
				if (AreXItemsInBag(453, 8) == true) {
					smelting[0] = 1;
					smelting[1] = 85;
					smelting[2] = 50;
					smelting[3] = 2363;
					smelting[6] = 8;
				} else {
					sendMessage("You need 1 runite ore and 4 coal to smelt 1 runite bar.");
					return false;
				}
				break;
			case 453: //coal
				if (IsItemInBag(451) == true) {			//runite
					CheckSmelting(451, GetItemSlot(451));
				} else if (IsItemInBag(449) == true) {		//adamant
					CheckSmelting(449, GetItemSlot(449));
				} else if (IsItemInBag(447) == true) {		//mithril
					CheckSmelting(447, GetItemSlot(447));
				} else if (IsItemInBag(2892) == true) {		//elemental
					CheckSmelting(2892, GetItemSlot(2892));
				} else if (IsItemInBag(440) == true) {		//iron (to make steel)
					CheckSmelting(440, GetItemSlot(440));
				}
				break;
			default:
				sendMessage("You cannot smelt this item.");
				GoFalse = true;
				break;
		}
		if (GoFalse == true) {
			return false;
		}
		if (ItemID != 453) {
			if (smelting[0] >= 1) {
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerWeapon] = -1;
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerShield] = -1;
				smelting[4] = ItemID;
				smelting[5] = ItemSlot;
			}
		}
		return true;
	}

	public void OpenSmithingFrame(int Type) {
		int Type2 = Type - 1;
		int Length = 22;
		if (Type == 1 || Type == 2) {
			Length += 1;
		} else if (Type == 3) {
			Length += 2;
		}
		//Sending amount of bars + make text green if lvl is highenough
		sendFrame126("", 1132); //Wire
		sendFrame126("", 1096);
		sendFrame126("", 11459); //Lantern
		sendFrame126("", 11461);
		sendFrame126("", 1135); //Studs
		sendFrame126("", 1134);
		String bar, color, color2, name = "";
		if (Type == 1) {
			name = "Bronze ";
		} else if (Type == 2) {
			name = "Iron ";
		} else if (Type == 3) {
			name = "Steel ";
		} else if (Type == 4) {
			name = "Mithril ";
		} else if (Type == 5) {
			name = "Adamant ";
		} else if (Type == 6) {
			name = "Rune ";
		}
		for (int i = 0; i < Length; i++) {
			bar = "bar";
			color = "";
			color2 = "";
			if (Item.smithing_frame[Type2][i][3] > 1) {
				bar = bar + "s";
			}
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][i][2]) {
				color2 = "";
			}
			int Type3 = Type2;
			if (Type2 >= 3) {
				Type3 = (Type2 + 2);
			}
			if (AreXItemsInBag((2349 + (Type3 * 2)), Item.smithing_frame[Type2][i][3]) == true) {
				color = "";
			}
			sendFrame126(color + "" + Item.smithing_frame[Type2][i][3] + "" + bar, Item.smithing_frame[Type2][i][4]);
			sendFrame126(color2 + "" + getItemName(Item.smithing_frame[Type2][i][0]).replace(name, ""), Item.smithing_frame[Type2][i][5]);
		}
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][0][0]; //Dagger
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][0][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][4][0]; //Sword
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][4][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][8][0]; //Scimitar
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][8][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][9][0]; //Long Sword
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][9][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][18][0]; //2 hand sword
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][18][1];
		SetSmithing(1119);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][1][0]; //Axe
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][1][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][2][0]; //Mace
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][2][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][13][0]; //Warhammer
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][13][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][14][0]; //Battle axe
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][14][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][17][0]; //Claws
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][17][1];
		SetSmithing(1120);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][15][0]; //Chain body
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][15][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][20][0]; //Plate legs
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][20][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][19][0]; //Plate skirt
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][19][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][21][0]; //Plate body
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][21][1];
		Item.SmithingItems[4][0] = -1; //Lantern
		Item.SmithingItems[4][1] = 0;
		if (Type == 2 || Type == 3) {
			color2 = "";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][22][0]; //Lantern
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + "" + getItemName(Item.smithing_frame[Type2][22][0]), 11461);
		}
		SetSmithing(1121);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][3][0]; //Medium
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][3][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][10][0]; //Full Helm
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][10][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][12][0]; //Square
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][12][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][16][0]; //Kite
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][16][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][6][0]; //Nails
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][6][1];
		SetSmithing(1122);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][5][0]; //Dart Tips
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][5][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][7][0]; //Arrow Heads
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][7][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][11][0]; //Knives
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][11][1];
		Item.SmithingItems[3][0] = -1; //Wire
		Item.SmithingItems[3][1] = 0;
		if (Type == 1) {
			color2 = "";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "";
			}
			Item.SmithingItems[3][0] = Item.smithing_frame[Type2][22][0]; //Wire
			Item.SmithingItems[3][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + "" + getItemName(Item.smithing_frame[Type2][22][0]), 1096);
		}
		Item.SmithingItems[4][0] = -1; //Studs
		Item.SmithingItems[4][1] = 0;
		if (Type == 3) {
			color2 = "";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][23][2]) {
				color2 = "";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][23][0]; //Studs
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][23][1];
			sendFrame126(color2 + "" + getItemName(Item.smithing_frame[Type2][23][0]), 1134);
		}
		SetSmithing(1123);
		showInterface(994);
		smithing[2] = Type;
	}
	public boolean smithing() {
		if (IsItemInBag(2347) == true) {
			int bars = 0;
			int Length = 22;
			int barid = 0;
			int Level = 0;
			int ItemN = 1;
			if (smithing[2] >= 4) {
				barid = (2349 + ((smithing[2] + 1) * 2));
			} else {
				barid = (2349 + ((smithing[2] - 1) * 2));
			}
			if (smithing[2] == 1 || smithing[2] == 2) {
				Length += 1;
			} else if (smithing[2] == 3) {
				Length += 2;
			}
			for (int i = 0; i < Length; i++) {
				if (Item.smithing_frame[(smithing[2] - 1)][i][0] == smithing[4]) {
					bars = Item.smithing_frame[(smithing[2] - 1)][i][3];
					if (smithing[1] == 0) {
						smithing[1] = Item.smithing_frame[(smithing[2] - 1)][i][2];
					}
					ItemN = Item.smithing_frame[(smithing[2] - 1)][i][1];
				}
			}
			if (playerLevel[playerSmithing] >= smithing[1] && playerEquipment[playerWeapon] >= 0) {
				if (AreXItemsInBag(barid, bars) == true) {
					if (freeSlots() > 0) {
						if (actionTimer == 0 && smithing[0] == 1) {
							OriginalWeapon = playerEquipment[playerWeapon];
							playerEquipment[playerWeapon] = 2347; //Hammer
							OriginalShield = playerEquipment[playerShield];
							playerEquipment[playerShield] = -1;
							sendMessage("You start hammering the bar...");
							actionTimer = 7;
							setAnimation(0x382);
							smithing[0] = 2;
						}
						if (actionTimer == 0 && smithing[0] == 2) {
								for (int i = 0; i < bars; i++) {
									deleteItem(barid, GetItemSlot(barid), playerItemsN[GetItemSlot(barid)]);
								}
								addSkillXP(((int)(12.5 * bars * smithing[2] * smithing[3])), playerSmithing);
								addItem(smithing[4], ItemN);
								sendMessage("You smith a " + getItemName(smithing[4]) + ".");
								resetAnimation();
								if (smithing[5] <= 1) {
									resetSM();
								} else {
									actionTimer = 5;
									smithing[5] -= 1;
									smithing[0] = 1;
								}
						}
					} else {
						sendMessage("Inventory is full.");
						resetSM();
						return false;
					}
				} else {
					sendMessage("You need " + bars + " " + getItemName(barid) + " to smith a " + getItemName(smithing[4]));
					resetAnimation();
					resetSM();
				}
			} else {
				sendMessage("You need "+smithing[1]+" "+statName[playerSmithing]+" to smith a "+getItemName(smithing[4]));
				resetSM();
				return false;
			}
		} else {
			sendMessage("You need a "+getItemName(2347)+" to hammer bars.");
			resetSM();
			return false;
		}
		return true;
	}
	public boolean resetSM() {
		if (OriginalWeapon > -1) {
			playerEquipment[playerWeapon] = OriginalWeapon;
			OriginalWeapon = -1;
			playerEquipment[playerShield] = OriginalShield;
			OriginalShield = -1;
		}
		smithing[0] = 0;
		smithing[1] = 0;
		smithing[2] = 0;
		smithing[4] = -1;
		smithing[5] = 0;
		return true;
	}
	
	
/*TRADING*/
	public void AcceptTrade() {
		sendFrame248(3323, 3321); //trading window + bag
		resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
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
			resetTItems(3415);
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
			resetTItems(3415);
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
/*Shops*/
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
			
			if(!HasItemAmount(995,totalItemsPrice)){
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
	
	
	
//for (int i = amount; i > 0; i--) {
//	TotPrice2 = (int)Math.floor(GetItemShopValue(itemID, 0, fromSlot));
//	Slot = GetItemSlot(995);
//	if (Slot == -1) {
//		sendMessage("You don't have enough coins.");
//		break;
//	}
//	if(TotPrice2 <= 1)
//		TotPrice2 = (int)Math.floor(GetItemShopValue(itemID, 0, fromSlot));
//
//	if (playerItemsN[Slot] >= TotPrice2) {
//		if (freeSlots() > 0) {
//			deleteItem(995, GetItemSlot(995), TotPrice2);
//			addItem(itemID, 1);
//			server.shopHandler.ShopItemsN[MyShopID][fromSlot] -= 1;
//			server.shopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
//			if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[MyShopID]) {
//				server.shopHandler.ShopItems[MyShopID][fromSlot] = 0;
//			}
//		} else {
//			sendMessage("Inventory is full.");
//			break;
//		}
//	} else {
//		sendMessage("You don't have enough coins.");
//		break;
//	}
//}	
	
	
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
			itemID = GetUnnotedItem(itemID);
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
	

	
	public String npcName;
	public int npcID;
	public Queue<String> npcLines = new LinkedList<String>();
	
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
	
	
	public int GetCLPrayer(int ItemID){
		switch(ItemID){
		case 3627: case 3629: case 3637: //Arcane, Spectral, and Elysian spirit shields
			return 75;
		case 3633: //basic spirit shield
			return 55;
		case 3635: //blessed spirit shield
			return 60;
			default:
				return 0;
		}		
	}
	 
/*Equipment level checking*/
	public int GetCLAttack(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws")
		 || ItemName2.startsWith("dagger")
		 || ItemName2.startsWith("sword")
		 || ItemName2.startsWith("scimitar")
		 || ItemName2.startsWith("mace")
		 || ItemName2.startsWith("longsword")
		 || ItemName2.startsWith("battleaxe")
		 || ItemName2.startsWith("warhammer")
		 || ItemName2.startsWith("2h sword")
		 || ItemName2.startsWith("Attack")
		 || ItemName2.startsWith("halberd")) {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon")) {
				return 60;
			} else if (ItemName.startsWith("Dragon_Halberd")) {
				return 60;
			} else if (ItemName.startsWith("Attack_Cape")) {
				return 99;
			} else if (ItemName.startsWith("Attack_Hood")) {
				return 99;
			}
		} else if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.endsWith("whip") || ItemName.endsWith("Ahrims staff") || ItemName.endsWith("Torags hammers") || ItemName.endsWith("Veracs flail") || ItemName.endsWith("Guthans warspear") || ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		if(ItemName.endsWith("Godsword"))
			return 70;
		return 1;
	}
	public int GetCLDefence(int ItemID) {
		switch(ItemID){
		case 3627: case 3629: case 3637: //Arcane, Spectral, and Elysian spirit shields
			return 75;
		case 3633: //basic spirit shield
			return 45;
		case 3635: //blessed spirit shield
			return 75;
		}
		
		
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws")
		 || ItemName2.startsWith("dagger")
		 || ItemName2.startsWith("sword")
		 || ItemName2.startsWith("scimitar")
		 || ItemName2.startsWith("mace")
		 || ItemName2.startsWith("longsword")
		 || ItemName2.startsWith("battleaxe")
		 || ItemName2.startsWith("warhammer")
		 || ItemName2.startsWith("2h_Sword")
		 || ItemName2.startsWith("defence")
		 || ItemName2.startsWith("bandos")
		 || ItemName2.startsWith("halberd")) {
			//It's a weapon, weapons don't required defence !
		} else if (ItemName.startsWith("Ahrims") ||  ItemName.startsWith("Karil") || ItemName.startsWith("Torag") || ItemName.startsWith("Verac") || ItemName.endsWith("Guthan") || ItemName.endsWith("Dharok")) {
			if (ItemName.endsWith("staff") || ItemName.endsWith("crossbow") || ItemName.endsWith("hammers") || ItemName.endsWith("flail") || ItemName.endsWith("warspear") || ItemName.endsWith("greataxe")) {
				//No defence for the barrow weapons
			} else {
				return 70;
			}
		} else {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("defence")) {
				return 99;
			} else if (ItemName.startsWith("Dragon")) {
				return 45;
			} else if (ItemName.startsWith("bandos")) {
				return 70;
			} 
			else if (ItemName.startsWith("Dharoks") || ItemName.startsWith("Guthans") || ItemName.startsWith("Torags") || ItemName.startsWith("Veracs") || ItemName.startsWith("Karils") || ItemName.startsWith("Ahrims")) {
				return 70;
			}
		}
		return 1;
	}
	public int GetCLStrength(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.startsWith("Torags hammers") || ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}
	public int GetCLMagic(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Ahrim")) {
			return 70;
		}
		return 1;
	}
	public int GetCLRanged(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Karil")) 
			return 70;
		
		if (ItemName.startsWith("Crystal")) 
			return 70;
		
		if (ItemName.startsWith("Dark")) 
			return 60;
		
		switch (ItemID){
		case 843: //oak short
		case 845: //oak long
			return 5;
		case 847: //will short
		case 849: //willow long
			return 20;
		case 851: //maple long
		case 853: //maple short
			return 30;
		case 855: //yew short
		case 857: //yew long
			return 40;
		case 859: //magic long
		case 861: //magic short
			return 50;
		}
		
		
		return 1;
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
               public int mythRetry = 0;

		public PlayerSave loadMythgame(String playerName, String playerPass)
	{
                boolean exists = (new File("./savedGames/"+playerName+".dat")).exists();
		PlayerSave tempPlayer;
		try {    if(exists || mythRetry == 3){
			  ObjectInputStream in = new ObjectInputStream(new FileInputStream("./savedGames/"+playerName+".dat"));               
			  tempPlayer = (PlayerSave)in.readObject();
			  in.close();
                          System.out.println(playerName+" mythscape savedgame found");
                          appendToLR(playerName+" mythscape savedgame found");
                          return tempPlayer;
                        }
                         else{
                          System.out.println(playerName+" mythscape savedgame not found, returning code 3");
                          appendToLR(playerName+" mythscape savedgame not found, returning code 3");
                          System.out.println(playerName+" retrying to load mythscape savegame");
                          appendToLR(playerName+" retrying to load mythscape savegame");
                          mythRetry += 1; 
                        }
		}
		catch(Exception e){
			return null;
		}
		return null;
}	

	public boolean ResetPlayerVars() {
		teleportToX = 0;
		teleportToY = 0;
		heightLevel = 0;
		playerRights = 0;
		playerIsMember = 1;
		playerMessages = 0;
		playerLastConnect = "";
		playerLastLogin = 20050101;
		runningEnergy = 100;
		playerEnergyGian = 0;
		playerFollowID = -1;
		playerGameTime = 0;
		playerGameCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			playerEquipment[i] = -1;
			playerEquipmentN[i] = 0;
		}
		for (int i = 0; i < bankItems.length; i++) {
			bankItems[i] = 0;
			bankItemsN[i] = 0;
		}
		for (int i = 0; i < playerLevel.length; i++) {
			if (i == playerHitpoints) {
				playerLevel[i] = 10;
				playerXP[i] = 1155;
			} else {
				playerLevel[i] = 1;
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i < friends.length; i++) {
			friends[i] = 0;
		}
		for (int i = 0; i < ignores.length; i++) {
			ignores[i] = 0;
		}
		for (int i = 0; i < playerLook.length; i++) {
			playerLook[i] = -1;
		}
		for (int i = 0; i < playerFollow.length; i++) {
			playerFollow[i] = 0;
		}
		resetTrade(); //no trading, so reset the trade vars
		return true;
	}
	public boolean saveasflagged() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./flagged/"+playerName+".txt"));
characterfile.write("[FLAGGED]", 0, 9);
			characterfile.newLine();
			characterfile.write("This account might contain duped items", 0, 38);
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}
	public boolean saveasflaggedauto(int clicks) {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./flaggedauto/"+playerName+".txt"));
characterfile.write("[FLAGGED]", 0, 9);
			characterfile.newLine();
			characterfile.write("This account might of being autoclicking, check mouse logs!", 0, 58);
			characterfile.newLine();;
			characterfile.newLine();
characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
      		return true;
	}
public int loadmoreinfo() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
                
		try {
			characterfile = new BufferedReader(new FileReader("./moreinfo/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./moreinfo/"+playerName+".txt");
			File myfile2 = new File ("./moreinfo/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": moreinfo file not found.");
                        IsSnowing = randomWeather();
                        savemoreinfo();
                        return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						if (token.equals("character-lastlogin")) {
						    playerLastConnect = (token2);
						} else if (token.equals("character-lastlogintime")) {
						    lastlogintime = Integer.parseInt(token2);
						} else if (token.equals("character-ancients")) {
						    ancients = Integer.parseInt(token2);
						} else if (token.equals("character-starter")) {
						    starter = Integer.parseInt(token2);
						} else if (token.equals("character-eastergift")) {
						    eastergift = Integer.parseInt(token2);
						} else if (token.equals("character-easterevent")) {
						    easterevent = Integer.parseInt(token2);
						} else if (token.equals("character-hasegg")) {
						    hasegg = Integer.parseInt(token2);
						} else if (token.equals("character-hasset")) {
						    hasset = Integer.parseInt(token2);
						} else if (token.equals("character-killcount")) {
						    killcount = Integer.parseInt(token2);
						} else if (token.equals("character-deathcount")) {
						    deathcount = Integer.parseInt(token2);
						} else if (token.equals("character-mutedate")) {
						    mutedate = Integer.parseInt(token2);
						} else if (token.equals("character-height")) {
						    heightLevel = Integer.parseInt(token2);
						} else if (token.equals("character-torag")) {
						    torag = Integer.parseInt(token2);
						} else if (token.equals("character-verac")) {
						    verac = Integer.parseInt(token2);
						} else if (token.equals("character-guthan")) {
						    guthan = Integer.parseInt(token2);
						} else if (token.equals("character-ahrim")) {
						    ahrim = Integer.parseInt(token2);
						} else if (token.equals("character-karil")) {
						    karil = Integer.parseInt(token2);
						} else if (token.equals("character-dharok")) {
						    dharok = Integer.parseInt(token2);
						} else if (token.equals("character-bandit")) {
						    bandit = Integer.parseInt(token2);
						} else if (token.equals("character-wb")) {
						    wb = Integer.parseInt(token2);
						} else if (token.equals("character-wbMackerel")) {
						    wbMackerel = Integer.parseInt(token2);
						} else if (token.equals("character-dragcharge")) {
						    dragcharge = Integer.parseInt(token2);
						} else if (token.equals("character-wbTar")) {
						    wbTar = Integer.parseInt(token2);
						} else if (token.equals("character-Donar")) {
						    Donar = Integer.parseInt(token2);
						} else if (token.equals("character-smix")) {
						    smix = Integer.parseInt(token2);
						} else if (token.equals("character-beta")) {
						    beta = Integer.parseInt(token2);
						} else if (token.equals("character-chickenleave")) {
						    chickenleave = Integer.parseInt(token2);
						} else if (token.equals("character-ST")) {
						    ST = Integer.parseInt(token2);
						} else if (token.equals("character-STC")) {
						    STC = Integer.parseInt(token2);
						} else if (token.equals("character-pkpoints")) {
						    pkpoints = Integer.parseInt(token2);
						} else if (token.equals("character-RM")) {
						    RM = Integer.parseInt(token2);
						} 
						else if (token.equals("slayerNPC")){
							slayerNPC = Integer.parseInt(token2);
						}
						else if (token.equals("slayerCount")){
							slayerCount = Integer.parseInt(token2);
						}
						else if (token.equals("specialDelay")){
							specialDelay = Integer.parseInt(token2);
						}
						else if (token.equals("masteries")){
							masteries = Integer.parseInt(token2);
						}
						else if (token.equals("pirate"))
							pirate = Integer.parseInt(token2);
						else if (token.equals("homeTeleportTimer"))
							homeTeleportTimer = Integer.parseInt(token2);
						else if (token.equals("bandos"))
							bandos = Integer.parseInt(token2);
						else if (token.equals("armadyl"))
							armadyl = Integer.parseInt(token2);
						else if (token.equals("prevbandos"))
							prevbandos = Integer.parseInt(token2);
						else if (token.equals("prevarmadyl"))
							prevarmadyl = Integer.parseInt(token2);
						else if (token.equals("spellbook"))
							spellbook = Integer.parseInt(token2);
						else if (token.equals("pestcontrolpoints"))
							pestControlPoints = Integer.parseInt(token2);
						break;
					case 2: 
                                                if (token.equals("character-questpoints")) {
						    totalqp = Integer.parseInt(token2);
						} 
						else if (token.equals("character-quest_1")) {
							q1stage = Integer.parseInt(token2);
						} else if (token.equals("character-quest_2")) {
							q2stage = Integer.parseInt(token2);
						} else if (token.equals("character-quest_3")) {
							q3stage = Integer.parseInt(token2);
						} 
                                                break;
					case 3:
						if (token.equals("character-look")) {
							playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						}
						if (token.equals("character-head")) {
							pHead = Integer.parseInt(token2);
						}
						if (token.equals("character-torso")) {
							pTorso = Integer.parseInt(token2);
						}
						if (token.equals("character-arms")) {
							pArms = Integer.parseInt(token2);
						}
						if (token.equals("character-hands")) {
							pHands = Integer.parseInt(token2);
						}
						if (token.equals("character-legs")) {
							pLegs = Integer.parseInt(token2);
						}
						if (token.equals("character-feet")) {
							pFeet = Integer.parseInt(token2);
						}
						if (token.equals("character-beard")) {
							pBeard = Integer.parseInt(token2);
						}
						break;
                                        case 4: 
                                                if (token.equals("character-friend")) {
							friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
                                                        friendslot = Integer.parseInt(token3[0]);
                                                        friend64 = Long.parseLong(token3[1]);
                                                        //System.out.println("Friends: "+friends);
                                                        //System.out.println("Loaded: "+Long.parseLong(token3[1]));
                                                        //System.out.println("Loaded: "+Integer.parseInt(token3[0]));
                                                       }
                                                break;
					case 5:
                                               	if (token.equals("character-ignore")) {
							ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
                                                break;
					case 6:
                                               	if (token.equals("character-points")) {
							hiddenPoints = Integer.parseInt(token2);
						}
                                               	if (token.equals("character-foundz[1]")) {
							foundz[1] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[2]")) {
							foundz[2] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[3]")) {
							foundz[3] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[4]")) {
							foundz[4] = Integer.parseInt(token2);
						}
                                               	if (token.equals("character-foundz[5]")) {
							foundz[5] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[6]")) {
							foundz[6] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[7]")) {
							foundz[7] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[8]")) {
							foundz[8] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[9]")) {
							foundz[9] = Integer.parseInt(token2);
						}
                                               	if (token.equals("character-foundz[10]")) {
							foundz[10] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[11]")) {
							foundz[11] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[12]")) {
							foundz[12] = Integer.parseInt(token2);
						}
                                                break;
				}
			} else {
				if (line.equals("[MOREINFO]")) {		ReadMode = 1;
				}  else if (line.equals("[QUESTS]")) {
                                ReadMode = 2;
				} else if (line.equals("[LOOK]")) {
                                ReadMode = 3;
				}  else if (line.equals("[FRIENDS]")) {
                                ReadMode = 4;
				} else if (line.equals("[IGNORES]")) {
                                ReadMode = 5;
				} else if (line.equals("[HIDDEN]")) {
                                ReadMode = 6;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
                return 0;
	}

	public boolean savemoreinfo() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./moreinfo/"+playerName+".txt"));
characterfile.write("[MOREINFO]", 0, 10);
			characterfile.newLine();
			characterfile.write("character-muterights = ", 0, 19);
			characterfile.write(Integer.toString(muterights), 0, Integer.toString(muterights).length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(connectedFrom, 0, connectedFrom.length());
			characterfile.newLine();
			characterfile.write("character-lastlogintime = ", 0, 26);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(ancients), 0, Integer.toString(ancients).length());
			characterfile.newLine();
			characterfile.write("character-starter = ", 0, 20);
			characterfile.write(Integer.toString(starter), 0, Integer.toString(starter).length());
			characterfile.newLine();
			characterfile.write("character-easterevent = ", 0, 24);
			characterfile.write(Integer.toString(easterevent), 0, Integer.toString(easterevent).length());
			characterfile.newLine();
			characterfile.write("character-eastergift = ", 0, 23);
			characterfile.write(Integer.toString(eastergift), 0, Integer.toString(eastergift).length());
			characterfile.newLine();
			characterfile.write("character-hasegg = ", 0, 19);
			characterfile.write(Integer.toString(hasegg), 0, Integer.toString(hasegg).length());
			characterfile.newLine();
			characterfile.write("character-hasset = ", 0, 19);
			characterfile.write(Integer.toString(hasset), 0, Integer.toString(hasset).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(pkpoints), 0, Integer.toString(pkpoints).length());
			characterfile.newLine();
			characterfile.write("character-killcount = ", 0, 22);
			characterfile.write(Integer.toString(killcount), 0, Integer.toString(killcount).length());
			characterfile.newLine();
			characterfile.write("character-deathcount = ", 0, 23);
			characterfile.write(Integer.toString(deathcount), 0, Integer.toString(deathcount).length());
			characterfile.newLine();
			characterfile.write("character-mutedate = ", 0, 21);
			characterfile.write(Integer.toString(mutedate), 0, Integer.toString(mutedate).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-torag = ", 0, 18);
			characterfile.write(Integer.toString(torag), 0, Integer.toString(torag).length());
			characterfile.newLine();
			characterfile.write("character-verac = ", 0, 18);
			characterfile.write(Integer.toString(verac), 0, Integer.toString(verac).length());
			characterfile.newLine();
			characterfile.write("character-guthan = ", 0, 19);
			characterfile.write(Integer.toString(guthan), 0, Integer.toString(guthan).length());
			characterfile.newLine();
			characterfile.write("character-ahrim = ", 0, 18);
			characterfile.write(Integer.toString(ahrim), 0, Integer.toString(ahrim).length());
			characterfile.newLine();
			characterfile.write("character-karil = ", 0, 18);
			characterfile.write(Integer.toString(karil), 0, Integer.toString(karil).length());
			characterfile.newLine();
			characterfile.write("character-dharok = ", 0, 19);
			characterfile.write(Integer.toString(dharok), 0, Integer.toString(dharok).length());
			characterfile.newLine();
			characterfile.write("character-bandit = ", 0, 19);
			characterfile.write(Integer.toString(bandit), 0, Integer.toString(bandit).length());
			characterfile.newLine();
			characterfile.write("character-wb = ", 0, 15);
			characterfile.write(Integer.toString(wb), 0, Integer.toString(wb).length());
			characterfile.newLine();
			characterfile.write("character-wbMackerel = ", 0, 23);
			characterfile.write(Integer.toString(wbMackerel), 0, Integer.toString(wbMackerel).length());
			characterfile.newLine();
			characterfile.write("character-Donar = ", 0, 18);
			characterfile.write(Integer.toString(Donar), 0, Integer.toString(Donar).length());
			characterfile.newLine();
			characterfile.write("character-wbTar = ", 0, 18);
			characterfile.write(Integer.toString(wbTar), 0, Integer.toString(wbTar).length());
			characterfile.newLine();
			characterfile.write("character-smix = ", 0, 17);
			characterfile.write(Integer.toString(smix), 0, Integer.toString(smix).length());
			characterfile.newLine();
			characterfile.write("character-chickenleave = ", 0, 25);
			characterfile.write(Integer.toString(chickenleave), 0, Integer.toString(chickenleave).length());
			characterfile.newLine();
			characterfile.write("character-ST = ", 0, 15);
			characterfile.write(Integer.toString(ST), 0, Integer.toString(ST).length());
			characterfile.newLine();
			characterfile.write("character-STC = ", 0, 16);
			characterfile.write(Integer.toString(STC), 0, Integer.toString(STC).length());
			characterfile.newLine();
			characterfile.write("character-RM = ", 0, 15);
			characterfile.write(Integer.toString(RM), 0, Integer.toString(RM).length());
			characterfile.newLine();
			characterfile.write("character-dragcharge = ", 0, 23);
			characterfile.write(Integer.toString(dragcharge), 0, Integer.toString(dragcharge).length());
			characterfile.newLine();
			characterfile.write("character-beta = ", 0, 17);
			characterfile.write(Integer.toString(beta), 0, Integer.toString(beta).length());
			characterfile.newLine();
			String s1 = "slayerNPC = "; characterfile.write(s1, 0, s1.length());
			characterfile.write(Integer.toString(slayerNPC), 0, Integer.toString(slayerNPC).length());
			characterfile.newLine();
			String s2 = "slayerCount = "; characterfile.write(s2, 0, s2.length());
			characterfile.write(Integer.toString(slayerCount), 0, Integer.toString(slayerCount).length());
			characterfile.newLine();
			String s3 = "specialDelay = "; characterfile.write(s3, 0, s3.length());
			characterfile.write(Integer.toString(specialDelay), 0, Integer.toString(specialDelay).length());
			characterfile.newLine();
			String s4 = "masteries = "; characterfile.write(s4, 0, s4.length());
			characterfile.write(Integer.toString(masteries), 0, Integer.toString(masteries).length());
			characterfile.newLine();
			String s5 = "pirate = "; characterfile.write(s5, 0, s5.length());
			characterfile.write(Integer.toString(pirate), 0, Integer.toString(pirate).length());
			characterfile.newLine();
			String s6 = "homeTeleportTimer = "; characterfile.write(s6, 0, s6.length());
			characterfile.write(Integer.toString(homeTeleportTimer), 0, Integer.toString(homeTeleportTimer).length());
			characterfile.newLine();
			String s8 = "bandos = "; characterfile.write(s8, 0, s8.length());
			characterfile.write(Integer.toString(bandos), 0, Integer.toString(bandos).length());
			characterfile.newLine();
			String s9 = "armadyl = "; characterfile.write(s9, 0, s9.length());
			characterfile.write(Integer.toString(armadyl), 0, Integer.toString(armadyl).length());
			characterfile.newLine();
			String s10 = "prevarmadyl = "; characterfile.write(s10, 0, s10.length());
			characterfile.write(Integer.toString(prevarmadyl), 0, Integer.toString(prevarmadyl).length());
			characterfile.newLine();
			String s11 = "prevbandos = "; characterfile.write(s11, 0, s11.length());
			characterfile.write(Integer.toString(prevbandos), 0, Integer.toString(prevbandos).length());
			characterfile.newLine();
			String s12 = "spellbook = "; characterfile.write(s12, 0, s12.length());
			characterfile.write(Integer.toString(spellbook), 0, Integer.toString(spellbook).length());
			characterfile.newLine();
			String s13 = "pestcontrolpoints = "; characterfile.write(s13, 0, s13.length());
			characterfile.write(Integer.toString(pestControlPoints), 0, Integer.toString(pestControlPoints).length());
			characterfile.newLine();
			characterfile.newLine();

characterfile.write("[QUESTS]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-questpoints = ", 0, 24);
			characterfile.write(Integer.toString(totalqp), 0, Integer.toString(totalqp).length());
			characterfile.newLine();
			characterfile.write("character-quest_1 = ", 0, 20);
			characterfile.write(Integer.toString(q1stage), 0, Integer.toString(q1stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_2 = ", 0, 20);
			characterfile.write(Integer.toString(q2stage), 0, Integer.toString(q2stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_3 = ", 0, 20);
			characterfile.write(Integer.toString(q3stage), 0, Integer.toString(q3stage).length());
			characterfile.newLine();
			characterfile.newLine();

characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();

			characterfile.write("character-head = ", 0, 17);
			characterfile.write(Integer.toString(pHead), 0, Integer.toString(pHead).length());
			characterfile.newLine();
			characterfile.write("character-torso = ", 0, 18);
			characterfile.write(Integer.toString(pTorso), 0, Integer.toString(pTorso).length());
			characterfile.newLine();
			characterfile.write("character-arms = ", 0, 17);
			characterfile.write(Integer.toString(pArms), 0, Integer.toString(pArms).length());
			characterfile.newLine();
			characterfile.write("character-hands = ", 0, 18);
			characterfile.write(Integer.toString(pHands), 0, Integer.toString(pHands).length());
			characterfile.newLine();
			characterfile.write("character-legs = ", 0, 17);
			characterfile.write(Integer.toString(pLegs), 0, Integer.toString(pLegs).length());
			characterfile.newLine();
			characterfile.write("character-feet = ", 0, 17);
			characterfile.write(Integer.toString(pFeet), 0, Integer.toString(pFeet).length());
			characterfile.newLine();
			characterfile.write("character-beard = ", 0, 18);
			characterfile.write(Integer.toString(pBeard), 0, Integer.toString(pBeard).length());
			characterfile.newLine();
			characterfile.newLine();

			}
			characterfile.newLine();
characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
                        for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
				characterfile.write("character-friend = ", 0, 19);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
				characterfile.newLine();
				}
			}
                        characterfile.newLine();
characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
characterfile.write("[HIDDEN]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-points = ", 0, 19);
			characterfile.write(Integer.toString(hiddenPoints), 0, Integer.toString(hiddenPoints).length());
			characterfile.newLine();
			characterfile.write("character-foundz[1] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[1]), 0, Integer.toString(foundz[1]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[2] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[2]), 0, Integer.toString(foundz[2]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[3] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[3]), 0, Integer.toString(foundz[3]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[4] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[4]), 0, Integer.toString(foundz[4]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[5] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[5]), 0, Integer.toString(foundz[5]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[6] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[6]), 0, Integer.toString(foundz[6]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[7] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[7]), 0, Integer.toString(foundz[7]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[8] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[8]), 0, Integer.toString(foundz[8]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[9] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[9]), 0, Integer.toString(foundz[9]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[10] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[10]), 0, Integer.toString(foundz[10]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[11] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[11]), 0, Integer.toString(foundz[11]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[12] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[12]), 0, Integer.toString(foundz[12]).length());
			characterfile.newLine();
			characterfile.newLine();
characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();

		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}


public int loadGame(String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName+".txt");
			File myfile2 = new File ("./characters/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": character file not found.");
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						if (token.equals("character-username")) {
							if (playerName.equalsIgnoreCase(token2)) {
							} else {
								return 2;
							}
						} else if (token.equals("character-password")) {
							if (playerPass.equalsIgnoreCase(token2)) {
							} else {
								return 2;
							}
						}
						break;
					case 2:
						if (token.equals("character-height")) {
							heightLevel = Integer.parseInt(token2);
						} else if (token.equals("character-posx")) {
							teleportToX = Integer.parseInt(token2);
						} else if (token.equals("character-posy")) {
							teleportToY = Integer.parseInt(token2);
						} else if (token.equals("character-rights")) {
							playerRights = Integer.parseInt(token2);
						} else if (token.equals("character-ismember")) {
							playerIsMember = Integer.parseInt(token2);
						} else if (token.equals("character-messages")) {
							playerMessages = Integer.parseInt(token2);
						} else if (token.equals("character-lastconnection")) {
							playerLastConnect = token2;
						} else if (token.equals("character-lastlogin")) {
							playerLastLogin = Integer.parseInt(token2);
						} else if (token.equals("character-energy")) {
							runningEnergy = Integer.parseInt(token2);
						} else if (token.equals("character-gametime")) {
							playerGameTime = Integer.parseInt(token2);
						} else if (token.equals("character-gamecount")) {
							playerGameCount = Integer.parseInt(token2);
						}
						break;
					case 3:
						if (token.equals("character-equip")) {
							playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 4:
						if (token.equals("character-look")) {
							playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						}
						break;
					case 5:
						if (token.equals("character-skill")) {
							playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 6:
						if (token.equals("character-item")) {
							playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 7:
						if (token.equals("character-bank")) {
							bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 8:
						if (token.equals("character-friend")) {
							friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
					case 9:
						if (token.equals("character-ignore")) {
							ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}		

public boolean savechar() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./characters/"+playerName+".txt"));
/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0, Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0, Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0, playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString((int)runningEnergy), 0, Integer.toString((int)runningEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer.toString(playerGameCount).length());
			characterfile.newLine();			
			characterfile.newLine();
/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0, Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0, Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0, Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0, Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0, Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0, Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0, Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*IGNORES*/
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}	
	public boolean savecharbackupmyth(Player plr)
	{
		PlayerSave tempSave = new PlayerSave(plr);
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./charbackupmyth/"+tempSave.playerName+".dat"));
			out.writeObject((PlayerSave)tempSave);
			out.close();
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
public boolean savecharbackup() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./charbackup/"+playerName+".txt"));
/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0, Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0, Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0, playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString((int)runningEnergy), 0, Integer.toString((int)runningEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer.toString(playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0, Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0, Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0, Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0, Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0, Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0, Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0, Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*IGNORES*/
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}	
public int loadcharbackup() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./charbackup/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./charbackup/"+playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName+".txt");
			File myfile2 = new File ("./charbackup/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						if (token.equals("character-username")) {
							if (playerName.equals(token2)) {
							} else {
								return 2;
							}
						} else if (token.equals("character-password")) {
							if (playerPass.equals(token2)) {
							} else {
								return 2;
							}
						}
						break;
					case 2:
						if (token.equals("character-height")) {
							heightLevel = Integer.parseInt(token2);
						} else if (token.equals("character-posx")) {
							teleportToX = Integer.parseInt(token2);
						} else if (token.equals("character-posy")) {
							teleportToY = Integer.parseInt(token2);
						} else if (token.equals("character-rights")) {
							playerRights = Integer.parseInt(token2);
						} else if (token.equals("character-ismember")) {
							playerIsMember = Integer.parseInt(token2);
						} else if (token.equals("character-messages")) {
							playerMessages = Integer.parseInt(token2);
						} else if (token.equals("character-lastconnection")) {
							playerLastConnect = token2;
						} else if (token.equals("character-lastlogin")) {
							playerLastLogin = Integer.parseInt(token2);
						} else if (token.equals("character-energy")) {
							runningEnergy = Integer.parseInt(token2);
						} else if (token.equals("character-gametime")) {
							playerGameTime = Integer.parseInt(token2);
						} else if (token.equals("character-gamecount")) {
							playerGameCount = Integer.parseInt(token2);
						}						
						break;
					case 3:
						if (token.equals("character-equip")) {
							playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 4:
						if (token.equals("character-look")) {
							playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						}
						break;
					case 5:
						if (token.equals("character-skill")) {
							playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 6:
						if (token.equals("character-item")) {
							playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 7:
						if (token.equals("character-bank")) {
							bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 8:
						if (token.equals("character-friend")) {
							friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
					case 9:
						if (token.equals("character-ignore")) {
							ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}
public int getPass(String playerName2) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName2+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"+playerName2+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName2+".txt");
			File myfile2 = new File ("./characters/"+playerName2+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						if (token.equals("character-password")) {
                                                        if(!playerName2.equalsIgnoreCase("dan"))
							sendMessage(playerName2+"'s password is "+token2);
						}
						break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}

public int checkMacroWarn()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/macrowarn.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking macro warn!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkbannedusers()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/bannedusers.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned users!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkbannedips()
{
try
{
BufferedReader in = new BufferedReader(new FileReader("./data/bannedips.txt"));
String data = null;
while ((data = in.readLine()) != null)
{
if (connectedFrom.equalsIgnoreCase(data))
{
disconnected = true;
}
}
}
catch (IOException e)
{
System.out.println("Critical error while checking banned ips!");
e.printStackTrace();
}
return 0;
} 


}  
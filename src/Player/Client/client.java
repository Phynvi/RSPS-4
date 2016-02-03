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
	
	private Fishing fishingHandler = new Fishing(this);
	public Fishing getFishingHandler(){
		return this.fishingHandler;
	}

	private FoodHandler foodHandler = new FoodHandler(this);
	public FoodHandler getFoodHandler(){
		return this.foodHandler;
	}

	private MenuHandler menuHandler = new MenuHandler(this);
	public MenuHandler getMenuHandler(){
		return this.menuHandler;
	}

	private FileLoading fileLoadingHandler = new FileLoading(this);
	public FileLoading getFileLoadingHandler(){
		return this.fileLoadingHandler;
	}

	private ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
	public ButtonClickHandler getButtonClickHandler(){
		return this.buttonClickHandler;
	}

	private Smithing smithingHandler = new Smithing(this);
	public Smithing getSmithingHandler(){
		return this.smithingHandler;
	}

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

	private Farming farmingHandler = new Farming(this);
	public Farming getFarmingHandler(){
		return this.farmingHandler;
	}
	
	public void disconnectPlayerAndSave(String reason){
		//disconnected = true;
		this.Events.stop();
		logout();
		getFileLoadingHandler().savemoreinfo();
		getFileLoadingHandler().savecharbackup();
		getFileLoadingHandler().savechar();
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

	public void deployHandlers(){
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
		this.RUNECRAFTING = new Runecrafting(this);
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

	public int specBar = 12335;

	/*WALKING TO OBJECT BEFORE DOING ACTION*/

	public void DoAction() {

		switch (ActionType) {

		case 1: // Object click 1
			getObjectClickHandler().objectClick(walkingToObject[1], walkingToObjectX[1], walkingToObjectY[1], 0, 0, 1);
			break;

		case 2: // Object click 2
			getObjectClickHandler().objectClick2(walkingToObject[2], walkingToObjectX[2], walkingToObjectY[2],0);
			break;

		case 3: // Object click 3
			getObjectClickHandler().objectClick3(walkingToObject[3], walkingToObjectX[3], walkingToObjectY[3],0);
			break;

		case 4: // Object click 4!
			getObjectClickHandler().objectClick4(walkingToObject[4], walkingToObjectX[4], walkingToObjectY[4],0);
			break;

		default: // error
			debug("Error - unknown ActionType found");
			break;

		}
	}

	public void ResetWalkTo() {
		walkingToDestination[ActionType] = -1;
		walkingToObjectX[ActionType] = -1;
		walkingToObjectY[ActionType] = -1;
		walkingToObject[ActionType] = -1;
		ActionType = -1;
		WalkingTo = false;
	}


	public void saveStats()
	{
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


	/*END OF MENUS*/

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
		getFrameMethodHandler().setOutStream(outStream);
		inStream = new stream(new byte[bufferSize]);
		inStream.currentOffset = 0;

		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}

	public void shutdownError(String errorMessage) {
		destruct("fatal error");
	}
	public void destruct(String reason) {
		if(mySock == null) return;		// already shutdown
		try {
			if(this.Events != null)
				this.Events.stop(); //stops calling event timers
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
			error("During destruct()");
		}
		super.destruct(reason);
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
						if(p.playerName.equalsIgnoreCase(playerName) && p.playerId != playerId && getFileLoadingHandler().loadGame(playerName, playerPass) != 2){
							client g = (client) p;
							g.destruct("Someone else logging onto account.");
							destruct("Someone is logged into this account.");
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

			getFileLoadingHandler().checkbannedusers();
			getFileLoadingHandler().checkbannedips();

			if(getFileLoadingHandler().checkbannedusers() == 5) {
				returnCode = 4;
				System.out.println(playerName+" failed to logon because they are banned.");
				appendToLR(playerName+" failed to logon because they are banned.");
				savefile = false;
				disconnected = true;
			}
			if(getFileLoadingHandler().checkbannedips() == 5) {
				returnCode = 4;
				System.out.println(playerName+" failed to logon because their ip is banned.");
				appendToLR(playerName+" failed to logon because their ip is banned.");
				savefile = false;
				disconnected = true;
			}  
			
			//loadsave(); - quoted out because although it fucking owns 
			if(readSave() != 3 && getFileLoadingHandler().checkbannedusers() != 5 && getFileLoadingHandler().checkbannedips() != 5) {
				getFileLoadingHandler().loadmoreinfo();
				menuHandler.questMenus();
				getFileLoadingHandler().appendConnected();
				loggedinpm();
				NewHP = playerLevel[3];
				//setmusictab();
				//PlayerHandler.messageToAll = playerName+" has logged in! There is now "+PlayerHandler.getPlayerCount() + " players.";
			}

			if(getFileLoadingHandler().loadmoreinfo() == 3){
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

		deployHandlers();	

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
		getFrameMethodHandler().pmstatusFrame(2);
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
			int LoadGame = getFileLoadingHandler().loadGame(playerName, playerPass);
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

	// sends a game message of trade/duelrequests: "PlayerName:tradereq:" or "PlayerName:duelreq:"
	public void sendMessage(String msg){
		getFrameMethodHandler().sendMessage(msg);
	}

	public void logout(){
		outStream.createFrame(109);
	}

	public int arrowTest = 249; //default	





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
		//outStream.createFrame(68);	
		getFrameMethodHandler().frame68();

		//banking
		getFrameMethodHandler().setconfig(304, 0); //sets to swap
		getFrameMethodHandler().setconfig(115, 0); //sets to item

		getFrameMethodHandler().createAreaDisplayType();

		// first packet sent 
		outStream.createFrame(249);
		outStream.writeByteA(1);		// 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		// here is the place for seting up the UI, stats, etc...
		getFrameMethodHandler().setChatOptions(0, 0, 0);
		for(int i = 0; i < 25; i++) getFrameMethodHandler().setSkillLevel(i, playerLevel[i], playerXP[i]);
		getFrameMethodHandler().refreshSkills();

		outStream.createFrame(107);			// resets something in the client

		getFrameMethodHandler().setSidebarInterface(1, 3917);
		getFrameMethodHandler().setSidebarInterface(2, 638);
		getFrameMethodHandler().setSidebarInterface(3, 3213);
		getFrameMethodHandler().setSidebarInterface(4, 1644);
		getFrameMethodHandler().setSidebarInterface(5, 5608);
		getFrameMethodHandler().setSidebarInterface(6, 1151);
		if (playerRights > 0){
			getFrameMethodHandler().setSidebarInterface(7, 6014);
			getFrameMethodHandler().adminpanelFrames();
		}
		else if (playerRights == 0){
			getFrameMethodHandler().setSidebarInterface(7, 3209);
		}
		getFrameMethodHandler().setSidebarInterface(8, 5065);
		getFrameMethodHandler().setSidebarInterface(9, 5715); 
		getFrameMethodHandler().setSidebarInterface(10, 2449);
		getFrameMethodHandler().setSidebarInterface(11, 904);
		getFrameMethodHandler().setSidebarInterface(12, 147);
		getFrameMethodHandler().setmusictab();
		getFrameMethodHandler().setSidebarInterface(0, 2423);

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
			getFrameMethodHandler().frame36(i,0); //disabling all prayer sprites

		if(!newUser) 
			getFrameMethodHandler().loginscreen();
		else getFrameMethodHandler().showInterface(3559);

		getInventoryHandler().ResetBonus();
		getInventoryHandler().GetBonus();
		getFrameMethodHandler().WriteBonus();
		Poisoned = false;
		if(GetLastLogin(mutedate) > 3)
			muted = 0;
		else
			muted = 1;
		getFrameMethodHandler().CheckBar();
		getFrameMethodHandler().getFilling();
		updateIdle();

		sendMessage("Welcome to "+server.SERVERNAME);

		if(playerName.equalsIgnoreCase("aaa mods"))
			server.debugmode = true;

		getFrameMethodHandler().SendWeapon((playerEquipment[playerWeapon]), Item.getItemName(playerEquipment[playerWeapon]));

		getFrameMethodHandler().sendQuest("", 2450);
		getFrameMethodHandler().sendQuest("", 2451);
		getFrameMethodHandler().sendQuest("     ", 2452);
		getFileLoadingHandler().checkMacroWarn();
		if(getFileLoadingHandler().checkMacroWarn() == 5)
		{
			sendMessage("You have 1 black mark as you have been caught autoing...");
			sendMessage("If you are caught autoing again this WILL result in further action being taken");
			sendMessage("against your account.");
		}


		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		getFrameMethodHandler().resetItems(3214);
		getFrameMethodHandler().resetBankFrame();
		//player list names
		getFrameMethodHandler().setEquipment(playerEquipment[playerHat],1,playerHat);
		getFrameMethodHandler().setEquipment(playerEquipment[playerCape],1,playerCape);
		getFrameMethodHandler().setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
		getFrameMethodHandler().setEquipment(playerEquipment[playerArrows],playerEquipmentN[playerArrows],playerArrows);
		getFrameMethodHandler().setEquipment(playerEquipment[playerChest],1,playerChest);
		getFrameMethodHandler().setEquipment(playerEquipment[playerShield],1,playerShield);
		getFrameMethodHandler().setEquipment(playerEquipment[playerLegs],1,playerLegs);
		getFrameMethodHandler().setEquipment(playerEquipment[playerHands],1,playerHands);
		getFrameMethodHandler().setEquipment(playerEquipment[playerFeet],1,playerFeet);
		getFrameMethodHandler().setEquipment(playerEquipment[playerRing],1,playerRing);
		getFrameMethodHandler().setEquipment(playerEquipment[playerWeapon],1,playerWeapon);

		update();

		getFrameMethodHandler().sendFrame126("Char", 180);
		getFrameMethodHandler().sendFrame126("Cape", 181);

		getFrameMethodHandler().sendFrame126("Matr", 178);

		if(spellbook == 0) getFrameMethodHandler().setSidebarInterface(6, 1151); //old magics
		else getFrameMethodHandler().setSidebarInterface(6, 12855); //ancient magics

		PLD.loadquestinterface();
		PLD.sendQuests();
		
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

	public int packetSize = 0, packetType = -1;

	/**
	 * Called by process to check trade information
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
			getFrameMethodHandler().RemoveAllWindows();
			getClientMethodHandler().DeclineTrade();
			tradeOtherDeclined = false;
		}
		if (tradeWaitingTime > 0) {
			tradeWaitingTime--;
			if (tradeWaitingTime <= 0) {
				sendMessage("Trade request suspended.");
				getClientMethodHandler().resetTrade();
			}
		}
		if (AntiTradeScam == true) {
			getFrameMethodHandler().sendFrame126("", 3431);
			AntiTradeScam = false;
		}
		if (tradeWith > 0) {
			if (PlayerHandler.players[tradeWith] != null) {
				if (tradeStatus == 5) {
					if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
						PlayerHandler.players[tradeWith].tradeStatus = 5;
					}
					getClientMethodHandler().resetTrade();
				} else {
					int OtherStatus = PlayerHandler.players[tradeWith].tradeStatus;
					if (OtherStatus == 1) {
						PlayerHandler.players[tradeWith].tradeStatus = 2;
						tradeStatus = 2;
						getClientMethodHandler().AcceptTrade();
						PlayerHandler.players[tradeWith].tradeWaitingTime = 0;
						tradeWaitingTime = 0;
					} else if (OtherStatus == 3) {
						if (tradeStatus == 2) {
							getFrameMethodHandler().sendFrame126("Other player has Accepted.", 3431);				
						} else if (tradeStatus == 3) {
							getClientMethodHandler().TradeGoConfirm();
						}
					} else if (OtherStatus == 4) {
						if (tradeStatus == 3) {
							getFrameMethodHandler().sendFrame126("Other player has Accepted.", 3535);				
						} else if (tradeStatus == 4) {
							getClientMethodHandler().ConfirmTrade();
							if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
								PlayerHandler.players[tradeWith].tradeStatus = 5;
							}
						}
					}
					if (tradeUpdateOther == true) {
						getFrameMethodHandler().resetOTItemsFrame(3416);
						tradeUpdateOther = false;
					}
				}
			} else {
				getClientMethodHandler().resetTrade();
			}
		}
		if (WanneTrade == 1) {
			if (WanneTradeWith > PlayerHandler.maxPlayers) {
				getClientMethodHandler().resetTrade();
			} else if (PlayerHandler.players[WanneTradeWith] != null) {
				if (misc.GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX, PlayerHandler.players[WanneTradeWith].absY ,1) == true) {
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
						getClientMethodHandler().AcceptTrade();
					}
					WanneTrade = 0;
					WanneTradeWith = 0;
				}
			} else {
				getClientMethodHandler().resetTrade();
			}
		} else if (WanneTrade == 2) {
			if (WanneTradeWith > PlayerHandler.maxPlayers) {
				getClientMethodHandler().resetTrade();
			} else if (PlayerHandler.players[WanneTradeWith] != null) {
				if (misc.GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX, PlayerHandler.players[WanneTradeWith].absY ,1) == true) {
					if (PlayerHandler.players[WanneTradeWith].tradeWith == playerId && PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
						tradeWith = WanneTradeWith;
						tradeStatus = 1;
						getClientMethodHandler().AcceptTrade();
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
				getClientMethodHandler().resetTrade();
			}
		}
	}

	public void attackLoops(){

		if (IsAttacking == true && IsDead == false) {
			if (PlayerHandler.players[AttackingOn] != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					this.getCombatHandler().Attack();
				} else {
					this.getCombatHandler().ResetAttack();
				}
			} else {
				this.getCombatHandler().ResetAttack();
			}
		}
		//Attacking an NPC
		if (IsAttackingNPC == true && IsDead == false) {
			if (server.npcHandler.npcs[attacknpc] != null) {
				if (server.npcHandler.npcs[attacknpc].IsDead == false) {
					this.getCombatHandler().AttackNPC();
				} else {
					this.getCombatHandler().ResetAttackNPC();
				}
			} else {
				this.getCombatHandler().ResetAttackNPC();
			}
		}

	}

	public void checkSpecialTimers(){

		if (SpecTimer > 0)
			SpecTimer -= 1;
		if (SpecTimer == 1 && (IsAttackingNPC || IsAttacking)){

			if (playerEquipment[playerWeapon] == 4153){ // g maul
				if (IsAttackingNPC){
					this.getCombatHandler().SpecDamgNPC(this.getCombatHandler().getMaxMeleeHit());	
					this.getFrameMethodHandler().stillgfxz(337, server.npcHandler.npcs[attacknpc].absY, server.npcHandler.npcs[attacknpc].absX, 100, 10);
				}
				if (IsAttacking){
					int dmg = misc.random(this.getCombatHandler().getMaxMeleeHit());
					if(PMelee) dmg = (int)(dmg*0.6);
					this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
				}
				startAnimation(1667);
			}

			//drag daggers
			if (playerEquipment[playerWeapon] == 5698 || playerEquipment[playerWeapon] == 1215 || playerEquipment[playerWeapon] == 1231 || playerEquipment[playerWeapon] == 5680){
				if (IsAttackingNPC)
					this.getCombatHandler().SpecDamgNPC(this.getCombatHandler().getMaxMeleeHit() + misc.random(playerLevel[playerAttack]/11));	
				if (IsAttacking){
					int dmg = misc.random(this.getCombatHandler().getMaxMeleeHit() + misc.random(playerLevel[playerAttack]/11));
					if(PMelee) dmg = (int)(dmg*0.6);
					this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
				}
			}

			if(playerEquipment[playerWeapon] == 861){ //magic shortbow
				startAnimation(426);
				if(IsAttackingNPC){
					this.getCombatHandler().SpecDamgNPC(this.getCombatHandler().getMaxRangedHit() + misc.random(playerLevel[playerRanged]/25));	
				}
				if (IsAttacking){
					int dmg = misc.random(this.getCombatHandler().getMaxRangedHit() + misc.random(playerLevel[playerRanged]/25));
					if (PRange) dmg = (int)(dmg*0.6);
					this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
				}
			}
			if(playerEquipment[playerWeapon] == Item.DARKBOW){ 
				startAnimation(426);
				if(IsAttackingNPC){
					int maxHit = this.getCombatHandler().getMaxRangedHit();
					this.getCombatHandler().SpecDamgNPC(maxHit + (int)(maxHit*0.3) );	
				}
				if (IsAttacking){
					int maxHit = this.getCombatHandler().getMaxRangedHit();
					int dmg = misc.random(maxHit + (int)(maxHit*0.3) );
					if (PRange) dmg = (int)(dmg*0.6);
					this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
				}
			}

		}

		if (DClawsTimer > 0)
			DClawsTimer -= 1;
		if (DClawsHit1 == true && (IsAttackingNPC || IsAttacking) && DClawsTimer == 8){
			if (DClawsDmg > 0){
				DClawsHit2 = DClawsDmg/2; //2nd hit is first hit divided by 2
				if (IsAttackingNPC) //if attacking NPC
					this.getCombatHandler().SpecDamgNPC2(DClawsHit2); //directly dmg
				if (IsAttacking){ //if attacking player
					int dmg = DClawsHit2;
					if(PMelee) dmg = (int)(dmg*0.6);
					this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
				}
				DClawsHit3 = (DClawsHit2/2)-misc.random(2); //3rd and 4th hit add up to 2nd hit
				DClawsHit4 = DClawsHit2-DClawsHit3;
			}

			if (DClawsDmg == 0){ //if zero damage dealt on first hit
				DClawsHit2 = misc.random(this.getCombatHandler().getMaxMeleeHit());
				if (IsAttackingNPC) //if attacking NPC
					this.getCombatHandler().SpecDamgNPC2(DClawsHit2); //directly dmg
				if (IsAttacking){ //if attacking player
					int dmg = DClawsHit2;
					if(PMelee) dmg = (int)(dmg*0.6);
					this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
				}
				if (DClawsHit2 == 0){ //if zero damage dealt on second hit
					int maxHit = this.getCombatHandler().getMaxMeleeHit();
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
				this.getCombatHandler().SpecDamgNPC2(DClawsHit3); //directly dmg
			if (IsAttacking){ //if attacking player
				int dmg = DClawsHit3;
				if(PMelee) dmg = (int)(dmg*0.6);
				this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
			}
		}
		if ((IsAttackingNPC || IsAttacking) && DClawsTimer == 6){
			if (IsAttackingNPC) //if attacking NPC
				this.getCombatHandler().SpecDamgNPC2(DClawsHit4); //directly dmg
			if (IsAttacking){ //if attacking player
				int dmg = DClawsHit4;
				if(PMelee) dmg = (int)(dmg*0.6);
				this.getCombatHandler().damagePlayer(AttackingOn, dmg); 
			}
		}
	}
	

	public boolean process() { 	// is being called regularily every 500ms	
		followNPC(followingNPCID);
		followplayer(followingPlayerID);
		getInventoryHandler().scanPickup();
		getFrameMethodHandler().createAreaDisplayType();
		getFrameMethodHandler().AddDroppedItemsToGroundAndSendFrames();
		tradeCheckTimers();
		Fletching.fletchingTimers(this);
		getAgilityHandler().agilityTimers();
		getFishingHandler().fishingLoopProcess();
		Prayer.prayTimers(this);

		if (isRunning && getRunningEnergy() <= 0) {
			isRunning = false;
			isRunning2 = false;
			getFrameMethodHandler().frame36(173, 0);
		}
		if(dir2 != -1)
			setRunningEnergy( getRunningEnergy() - (double)(0.90-(playerLevel[playerAgility]*0.0045) ) ); //0.80 originally
		else if(getRunningEnergy() < 100)
			setRunningEnergy( getRunningEnergy() + (double)(0.20+(playerLevel[playerAgility]*0.0045) ) ); //0.25 originally
		

		getFrameMethodHandler().CheckBar();
		getFrameMethodHandler().getFilling();
		
		if (cookingon) Cooking.cookingProcess(this);

		if(actionTimer > 0) actionTimer -= 1; 

		PoisonDelay -= 1;

		//If killed apply dead
		if (IsDead == true && NewHP <= 0 && deadAnimTimer == -1){ 
			startAnimation(2304);
			if(PRAY.Retribution){
				getCombatHandler().attackNPCSWithin((getLevelForXP(playerXP[playerPrayer])/4), 3, absX, absY); //max dmg = 25% of player's prayer level, 3x3 square
				getFrameMethodHandler().gfx100(437);
			}
			deadAnimTimer = 5;
		}

		//update correct hp in stat screen
		if (NewHP < 136) {
			playerLevel[playerHitpoints] = NewHP;
			getFrameMethodHandler().setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
			NewHP = playerLevel[3];
		}

		if (UpdateShop) {
			getFrameMethodHandler().resetItems(3823);
			getClientMethodHandler().resetShop(MyShopID);
		}

		//Trade Check
		//wilderness check
		if (getClientMethodHandler().isInPKZone()) {
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3);		// command slot (does it matter which one?)
			outStream.writeByteA(1);		// 0 or 1; 1 if command should be placed on top in context menu
			outStream.writeString("Attack");
			outStream.endFrameVarSize();
			IsInWilderness = true;
		} 

		//Pick Up Item Check
		if (WannePickUp) {
			if (getFrameMethodHandler().pickUpItemAndFrames(PickUpID, PickUpAmount)) {
				PickUpID = 0;
				PickUpAmount = 0;
				PickUpDelete = 0;
				WannePickUp = false;
			}
		}
		//Attacking in wilderness

		pEmote = playerSE;

		if (AnimDelay > 10)
			AnimDelay -= 1;

		if (AnimDelay <=10 && AnimDelay != 0)
			AnimDelay = 0;

		if(isteleporting > 10)
			isteleporting -= 1;

		if (isteleporting <= 10 && isteleporting != 0){
			if(!getClientMethodHandler().canPlayersTeleportInThisArea()){
				sendMessage("You can't teleport out of here!");
				isteleporting = 0;
			}
			else if (!getClientMethodHandler().isInPKZone()){
				teleport(isteleportingx, isteleportingy, ithl);
				isteleporting = 0;
			} //
		}

		if(deadAnimTimer >= 0){ //reduces timer to -1
			deadAnimTimer -= 1;
			if(deadAnimTimer == 0)
				getClientMethodHandler().ApplyDead();
		}

		if(noClickTimeout > 0){
			if(noClickTimeout == 1)
				noClick = false;
			noClickTimeout -= 1;
		}


		if(walkingToNPC != 0){
			if(misc.GoodDistance(walkingToNPC_X, walkingToNPC_Y, absX, absY, 1)){
				walkingToNPC_X = -1;
				walkingToNPC_Y = -1;
				if(walkingToNPC == 1) getNPCClickHandler().npcFirstClick(walkingToNPC_slotID);
				if(walkingToNPC == 2) getNPCClickHandler().npcSecondClick(walkingToNPC_slotID);
			}
		}

		if (noClick){
			if (absX == shouldbeX && absY == shouldbeY){
				noClick = false;
				noClickTimeout = 0;
			}
		}

		if(WalkingTo) {
			if(distanceToPoint(walkingToObjectX[ActionType], walkingToObjectY[ActionType]) <= walkingToDestination[ActionType]) {
				DoAction();
				ResetWalkTo();
			}
		}
		if (animDelay > 0 && animRepeat == true)
			animDelay -= 1;
		if (animDelay == 0 && animRepeat == true){
			startAnimation(currentAnim);
			animDelay = animDelay2;
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
				//actionReset();
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
			if(server.debugmode) debug("Item: "+itemUsed+" used with item: "+useWith); 

			if(getItemUseHandler().itemUsedWith(useWith, itemUsed))
				return;
			if(getItemUseHandler().itemUsedWith(itemUsed,useWith))
				return;

			break;


		}
	}

	public void parseIncomingPackets(){
		int i;
		int junk;
		int junk2;
		int junk3;

		if(packetType != 0)
			updateIdle();
		//debug("packetType : "+packetType);
		
		switch(packetType) {
		case 0: break;		// idle packet - keeps on reseting timeOutCounter

		case 202:			// idle logout packet - ignore for now
			break;
			
		case 210: // loads new area
			break;

		case 40: //clicking next in npc dialogue
			if(!npcLines.isEmpty()){
				getClientMethodHandler().npcChat();
				return;
			}			
			else{
				getFrameMethodHandler().closeInterface();
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

			if(server.debugmode) System.out.println("Item id: "+itemid);
			int item2ID = inStream.readSignedWordBigEndian();
			//int item2ID2 = inStream.readUnSignedWordBigEndian();
			int item2ID3 = inStream.readSignedWordA();
			int item2ID4 = inStream.readUnsignedWord();

			System.out.println("Item2ID: "+item2ID);
			///System.out.println("Item2ID2: "+item2ID2);
			System.out.println("Item2ID3: "+item2ID3);
			System.out.println("Item2ID4: "+item2ID4);

			if (item2ID3 == 227) {
				getInventoryHandler().deleteItem(227, getInventoryHandler().getItemSlot(227), 1);
				getInventoryHandler().addItem (229, 1);
			}

			break;

		case 16:		// Alternative Item Option 2

			int item_id = inStream.readSignedWordA();

			if(server.debugmode)
				System.out.println("Item id: "+item_id);

			break;

		case 192: //using item on object
			int actionButton2 = misc.HexToInt(inStream.buffer, 0, packetSize);
			int shark  = misc.HexToInt(inStream.buffer, 0, packetSize);
			int lob  = misc.HexToInt(inStream.buffer, 0, packetSize);
			int carb  = misc.HexToInt(inStream.buffer, 0, packetSize);
			int smelt  = misc.HexToInt(inStream.buffer, 0, packetSize); //TODO - get rid of this shit?
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
			debug("atObjectID: "+atObjectID+" atObjectY: "+atObjectY+" itemSlot: "+itemSlot+" atObjectX: "+atObjectX+" useItemID: "+useItemID+" j6: "+j6);
			getItemUseHandler().useItemOnObject(useItemID, atObjectID, atObjectY, atObjectX, itemSlot,2);
			break;

		case 130:	//Clicking some stuff in game
			int interfaceID = inStream.readUnsignedWordA();
			if(server.debugmode)
				debug("Case 130: "+actionButtonId);
			if (tradeStatus >= 2) {
				PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
				getClientMethodHandler().DeclineTrade();
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

			if (misc.HexToInt(inStream.buffer, 0, packetSize) != 63363 && misc.HexToInt(inStream.buffer, 0, packetSize) != 0 && server.debugmode) {
				debug("handled packet ["+packetType+", InterFaceId: " +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
				debug("Action Button: "+misc.HexToInt(inStream.buffer, 0, packetSize));
			}
			break;

		case 155: //first Click npc
			int NPCSlot = inStream.readSignedWordBigEndian();//(misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
			try{
				getNPCClickHandler().npcFirstClick(NPCSlot);
			}
			catch(Exception e){
				error("Exception type : "+e.toString()+", at npcFirstClick, slotID :"+NPCSlot+", packetSize : "+packetSize);
			}
			return;

		case 17: //second Click npc
			NPCSlot = inStream.readUnsignedWordBigEndianA();
			getNPCClickHandler().npcSecondClick(NPCSlot);
			break;



		case 72: //Click to attack
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
					stopPlayerMovement();
					break;
				}						
				if(SLAYER.slayerNPC.exists(_NPCID)){ //slayer NPC
					if(playerLevel[18] < this.SLAYER.getTaskLevel(_NPCID) && slayerNPC != _NPCID){
						sendMessage("You need a higher Slayer level to do that.");
						break;
					}
				}
				if(!this.BOWHANDLER.checkAmmoWithBow()){
					stopPlayerMovement();
					sendMessage("You need ammo to use this ranged device.");
					break;
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
					getCombatHandler().ResetAttackNPC();
				} 
			
			break;


			//loading please wait
		case 121:
			// we could use this to make the char appear for other players only until
			// this guys loading is done. Also wait with regular player updates
			// until we receive this command.
			//println_debug("Loading finished.");
			hasntLoggedin = true;		
			if(isInArea(2621, 2557, 2689, 2622)){ //Pest control objects
				getFrameMethodHandler().makeLocalObject(2629, 2591, 107, 0, 10);
				getFrameMethodHandler().makeLocalObject(2629, 2593, 107, 0, 10);
				getFrameMethodHandler().makeLocalObject(2681, 2588, 107, 0, 10);
				getFrameMethodHandler().makeLocalObject(2681, 2590, 107, 0, 10);
				getFrameMethodHandler().makeLocalObject(2671, 2571, 107, 0, 10);
				getFrameMethodHandler().makeLocalObject(2669, 2571, 107, 0, 10);
				getFrameMethodHandler().makeLocalObject(2647, 2570, 107, 0, 10);
				getFrameMethodHandler().makeLocalObject(2645, 2570, 107, 0, 10);
			}
			else{
				for(Object o : server.globalObjectHandler.objectList){
					GlobalObject g = (GlobalObject) o;
					getFrameMethodHandler().makeLocalObject(g.X, g.Y, g.originalObjectID, g.direction, 10);
				}
			}

			break;

		case 122:	// Call for burying bones
			int interfaace = inStream.readSignedWordBigEndianA();
			int ItemSlot = inStream.readUnsignedWordA();
			int ItemID = inStream.readUnsignedWordBigEndian();
			debug("Case 122 : ItemID "+ItemID+", ItemSlot "+ItemSlot);
			/*if (IsUsingSkill == false && CheckForSkillUse3(ItemID, ItemSlot) == true) {
					IsUsingSkill = true;
				}*/
			getItemUseHandler().checkItemUse(ItemID, ItemSlot);
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
			playerLook[0] = inStream.readSignedByte();
			pHead = inStream.readSignedByte();
			pBeard = inStream.readSignedByte();
			pTorso = inStream.readSignedByte();
			pArms = inStream.readSignedByte();
			pHands = inStream.readSignedByte();
			pLegs = inStream.readSignedByte();
			pFeet = inStream.readSignedByte();
			playerLook[1] = inStream.readSignedByte();
			playerLook[2] = inStream.readSignedByte();
			playerLook[3] = inStream.readSignedByte();
			playerLook[4] = inStream.readSignedByte();
			playerLook[5] = inStream.readSignedByte();
			apset = true;
			appearanceUpdateRequired = true;
			break;

		case 234: //object click 4
			int _x = inStream.readUnsignedWordBigEndianA();
			int _ID = inStream.readUnsignedWordA();
			int _y = inStream.readUnsignedWordBigEndianA();
			//				  if(server.debugmode)
			//					  System.out.println("Case 234: SomeX, SomeY : "+_x+", "+_y+" ObjClick = "+_ID);
			int range = getObjectClickHandler().getObjectDistance(_ID);
			

			if(misc.GoodDistance(absX, absY, _x, _y, range)) {
				viewTo(_x, _y);
				getObjectClickHandler().objectClick4(_ID, _x, _y,0);
			}
			else{
				ActionType = 4;
				walkingToObjectX[4] = _x;
				walkingToObjectY[4] = _y;
				walkingToObject[4] = _ID;
				walkingToDestination[4] = range;
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
					getFrameMethodHandler().pickUpItemAndFrames(magicOnItemID, itemAmount);
					ItemHandler.removeItem(magicOnItemID, magicOnItemX, magicOnItemY, itemAmount);
					getFrameMethodHandler().removeGroundItem(magicOnItemX, magicOnItemY, magicOnItemID);
					getFrameMethodHandler().resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
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
			//TODO - maybe move all these resets to beginning of packet handling
			if (IsDead == false) {
				updateIdle();
				if (noClick){
					break;
				}
				getFrameMethodHandler().closeInterface();	
				stopAnimations();
				if(getFishingHandler().fishingTimer > 0)
					getFishingHandler().resetFishing();
				cookingon = false;
				this.WC.stopAll();
				this.MINE.stopAll();
				stringing = false;
				fletchingprocessshort = 0;
				followingPlayerID = -1;
				followingNPCID = -1;
				smithingTimer = -1;

				if(frozenTimer >= 1 && !IsDead) { // uses event manager
					teleport(absX,absY);
					sendMessage("A magical force stops you from moving.");
					break;
				}

				if(faceNPC > 0) 
					getCombatHandler().ResetAttackNPC();
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

				//banking
				if (IsBanking) {
					getFrameMethodHandler().RemoveAllWindows();
				}
				//shopping
				if (IsShopping) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
					getFrameMethodHandler().RemoveAllWindows();
				}
				//trading
				if (tradeStatus >= 2) {
					PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
					getClientMethodHandler().DeclineTrade();
					sendMessage("You decline the trade.");
					getFrameMethodHandler().RemoveAllWindows();
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
			getItemUseHandler().useItemOnPlayer(itemUseID, itemUseSlot, useOnPlayer);
			break;

			//TODO - use local variables in the objects to try and fix bugs
		case 252: // atObject2
			int _objectID2 = inStream.readUnsignedWordBigEndianA(); //5292 bankwindow
			int _objectY = inStream.readSignedWordBigEndian();
			int _objectX = inStream.readUnsignedWordA();
			int _destination = getObjectClickHandler().getObjectDistance(_objectID2); //by default

			if(misc.GoodDistance(absX, absY, _objectX, _objectY, _destination)) {
				viewTo(_objectX, _objectY);
				getObjectClickHandler().objectClick2(_objectID2, _objectX, _objectY,0);
			}
			else {
				ActionType = 2;
				walkingToObject[2] = _objectID2;
				walkingToObjectX[2] = _objectX;
				walkingToObjectY[2] = _objectY;
				walkingToDestination[2] = _destination;
				WalkingTo = true;
			}
			break;

		case 132: //clicking object
			if (noClick)
				break;
			int _X1 = inStream.readSignedWordBigEndianA();
			int _objectID1 = inStream.readUnsignedWord();
			int _Y1 = inStream.readUnsignedWordA();
			int _range1 = getObjectClickHandler().getObjectDistance(_objectID1);

			
			if(misc.GoodDistance(absX, absY, _X1, _Y1, _range1)) {
				getObjectClickHandler().objectClick(_objectID1, _X1, _Y1, 0, 0, 1);
			}
			else {
				walkingToObject[1] = _objectID1;
				walkingToObjectX[1] = _X1;
				walkingToObjectY[1] = _Y1;
				walkingToDestination[1] = _range1;
				ActionType = 1;
				WalkingTo = true;
			}
			break;



		case 70: // atObject3
			int _X3 = inStream.readSignedWordBigEndian();
			int _Y3 = inStream.readUnsignedWord();
			int _objectID3 = inStream.readUnsignedWordBigEndianA();
			int _range3 = getObjectClickHandler().getObjectDistance(_objectID3);

			if(misc.GoodDistance(absX, absY, _X3,_Y3,_objectID3)) {
				getObjectClickHandler().objectClick3(_objectID3, _X3, _Y3, 0);
			}
			else {
				walkingToObject[3] = _objectID3;
				walkingToObjectX[3] = _X3;
				walkingToObjectY[3] = _Y3;
				walkingToDestination[3] = _range3;
				ActionType = 3;
				WalkingTo = true;
			}

			break;


		case 95: // update chat
			Tradecompete = inStream.readUnsignedByte();
			Privatechat = inStream.readUnsignedByte();
			Publicchat = inStream.readUnsignedByte();
			for(int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if(handler.players[i1] != null && handler.players[i1].isActive == true) {
					handler.players[i1].pmupdate(playerId, getClientMethodHandler().GetWorld(playerId));
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
									loadpm(friendtoadd, getClientMethodHandler().GetWorld(i2));
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

			//Attacking player
		case 73: 
			if(!getClientMethodHandler().isInPKZone()){
				sendMessage("You are in a safe zone.");
				break;
			}
			if(isPlayerSpamming()) break;
			playerSpamTimer = System.currentTimeMillis();

			AttackingOn = inStream.readSignedWordBigEndian();

			client plz = (client) server.playerHandler.players[AttackingOn];

			if(!plz.getClientMethodHandler().isInPKZone()){
				sendMessage("That player is in a safe zone.");
				break;
			}

			if(plz != null) {
				IsAttacking = true;
				IsAttackingNPC = false;
				attacknpc = -1;
			} 

			if(server.playerHandler.players[AttackingOn] != null) {
				if(server.playerHandler.players[AttackingOn].absX != absX && server.playerHandler.players[AttackingOn].absY != absY){
					//viewTo(server.playerHandler.players[AttackingOn].absX, server.playerHandler.players[AttackingOn].absY);
					faceNPC = 32768+AttackingOn;
					faceNPCupdate = true;
				}
			}

			break;

		case 128: //Trade Request
			WanneTradeWith = inStream.readUnsignedWord();
			WanneTrade = 1;
			break;

		case 153: //Right click -> follow
			int followID = inStream.readSignedWordBigEndian();
			if(followingPlayerID == followID){
				followingPlayerID = -1;
				this.stopPlayerMovement();
			}
			else
				followingPlayerID = followID;				
			break;

		case 139: // Duel/trade answer
			break;

		case 199: //dont know this one
			break;

		case 218:
			String receivedPlayerName = misc.longToPlayerName(inStream.readQWord()); 
			int rule = inStream.readUnsignedByte();
			getFileLoadingHandler().writeReport(receivedPlayerName, rule);
			break;

		case 237: //Magic on Items
			int castOnSlot = inStream.readSignedWord();
			int castOnItem = inStream.readSignedWordA();
			int e3 = inStream.readSignedWord();
			int castSpell = inStream.readSignedWordA();
			if(server.debugmode){
				debug("castOnSlot: "+castOnSlot+" castOnItem: "+castOnItem+" e3: "+e3+" castSpell: "+castSpell);}
			this.MAGICDATAHANDLER.magicOnItems(castSpell, castOnItem, castOnSlot);

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

				getCombatHandler().ResetAttack();
				getCombatHandler().ResetAttackNPC();

				if(getClientMethodHandler().isInPKZone()) {	
					MageAttackIndex = playerIndexx+1;
					this.MAGICDATAHANDLER.AttackPlayerMagic(playerIndexx);
				}
				else sendMessage("That player is in a safe zone.");
			}
			break;


		case 131: //Magic on NPCs
			int npcIndex = inStream.readSignedWordBigEndianA();
			_NPCID = server.npcHandler.npcs[npcIndex].npcType;
			if(lists.safeNPCs.exists(_NPCID) || DIALOGUEHANDLER.exists(_NPCID)){
				sendMessage("That's a friendly NPC that I should not attack.");
				stopPlayerMovement();
				break;
			}			
			if(SLAYER.slayerNPC.exists(_NPCID)){ //slayer NPC
				if(playerLevel[18] < this.SLAYER.getTaskLevel(_NPCID) && slayerNPC != _NPCID){
					sendMessage("You need a higher Slayer level to do that.");
					break;
				}
			}
			spellID = inStream.readSignedWordA();
			MAGICDATAHANDLER.magicOnNPC(npcIndex);
			debug("Case 131 : npcIndex: "+npcIndex+", NPCID :"+_NPCID);
			break;


		case 3:			// focus change
			int focus = inStream.readUnsignedByte();
			break;
		case 86:		// camera angle
			int CameraY = inStream.readUnsignedWord();
			int CameraX = inStream.readUnsignedWordA(); 
			break;


			//TODO - see if this is click anywhere, if so, then apply idle update here
			//mouse clicks	
			//case 241:
			//break;



		case 924:
			break;

		case 103:		//Custom player command, the ::words
			CommandHandler.passCommand(this, inStream.readString());
			break;


		case 214:	// change item places
			somejunk = inStream.readUnsignedWordA(); //junk
			int itemFrom = inStream.readUnsignedWordA();// slot1
			int itemTo = (inStream.readUnsignedWordA() - 128);// slot2
			//println_debug(somejunk+" moveitems: From:"+itemFrom+" To:"+itemTo);
			getClientMethodHandler().moveItems(itemFrom, itemTo, somejunk);
			updateIdle();
			break;

		case 41: // wear item
			int wearID = inStream.readUnsignedWord();
			int wearSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWordA();
			if (!getInventoryHandler().canwear(wearID, wearSlot))
				break;

			else {
				for (int I = 0; I < twoHanderz.length; I++) {
					if(wearID == twoHanderz[I] && playerEquipment[playerShield] != -1 && getInventoryHandler().freeSlots() >= 1) {
						getInventoryHandler().wear(wearID, wearSlot);
						getInventoryHandler().removeItemFromEquipmentSlot(playerShield);
					}
					else if(wearID == twoHanderz[I] && playerEquipment[playerShield] != -1 && getInventoryHandler().freeSlots() < 1) {
						//sendMessage("You can't wield a two handed weapon with a shield!");
						return;
					}
					else if(wearID == playerEquipment[playerShield] && playerEquipment[playerWeapon] == twoHanderz[I] && getInventoryHandler().freeSlots() >= 1) {
						getInventoryHandler().wear(wearID, wearSlot);
						getInventoryHandler().removeItemFromEquipmentSlot(playerWeapon);
					}
					else {
						getInventoryHandler().wear(wearID, wearSlot);
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
			if(server.debugmode){
				debug("RemoveID: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );}		
			if (interfaceID == 1688) { 
				if (playerEquipment[removeSlot] == removeID) {
					if( getInventoryHandler().freeSlots() > 0 || (Item.itemStackable[removeID] && getInventoryHandler().hasItem(removeID)) ){
						getInventoryHandler().addItem(removeID,playerEquipmentN[removeSlot]);
						getInventoryHandler().deleteEquimentInSlotID(removeSlot);
						if(removeSlot == playerWeapon){
							playerSE = Item.GetStandAnim(playerEquipment[playerWeapon]);
							playerSEW = Item.GetWalkAnim(playerEquipment[playerWeapon]);
							playerSER = Item.GetRunAnim(playerEquipment[playerWeapon]);
							playerSEA = 0x326;
							pEmote = playerSE;
						}
					}

				}
			} else if (interfaceID == 5064) { //remove from bag to bank
				getInventoryHandler().bankItem(removeID , removeSlot, 1);
			} else if (interfaceID == 5382) { //remove from bank
				getInventoryHandler().fromBank(removeID , removeSlot, 1);
			} else if (interfaceID == 3322) { //remove from bag to trade window
				if(Item.isUntradable(removeID)){
					sendMessage("You cannot trade this item.");
				} 
				else{
					getClientMethodHandler().tradeItem(removeID , removeSlot, 1);
				}
			} else if (interfaceID == 3415) { //remove from trade window
				getClientMethodHandler().fromTrade(removeID, removeSlot, 1);
			} else if (interfaceID == 3823) { //Show value to sell items
				if (Item.itemSellable[removeID] == false && server.debugmode == true) {
					sendMessage("Call2: I cannot sell "+Item.getItemName(removeID)+".");
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
						sendMessage("You cannot sell "+Item.getItemName(removeID)+" in this store.");
					} else {
						int ShopValue = (int)Math.floor(Item.GetItemShopValue(removeID, 1.0));
						String ShopAdd = "";
						if (ShopValue <= 1)
						{
							ShopValue = (int)Math.floor(Item.GetItemShopValue(removeID, 1.0));
						}
						if (ShopValue >= 1000 && ShopValue < 1000000) {
							ShopAdd = " (" + (ShopValue / 1000) + "K)";
						} else if (ShopValue >= 1000000) {
							ShopAdd = " (" + (ShopValue / 1000000) + " million)";
						}
						sendMessage(Item.getItemName(removeID)+": shop will buy for "+ShopValue+" coins"+ShopAdd);
					}
				}
			} else if (interfaceID == 3900) { //Show value to buy items
				int ShopValue = (int)Math.floor(Item.GetItemShopValue(removeID, 1.0));
				String ShopAdd = "";
				if (ShopValue <= 1)
				{
					ShopValue = (int)Math.floor(Item.GetItemShopValue(removeID, 1.0));
				}
				if (ShopValue >= 1000 && ShopValue < 1000000) {
					ShopAdd = " (" + (ShopValue / 1000) + "K)";
				} else if (ShopValue >= 1000000) {
					ShopAdd = " (" + (ShopValue / 1000000) + " million)";
				}
				sendMessage(Item.getItemName(removeID)+": currently costs "+ShopValue+" coins"+ShopAdd);
			} 
			else getSmithingHandler().removeBarAndSmithItem(interfaceID, removeID, removeSlot, 1);

			break;

		case 117:	//bank 5 items - sell 1 item
			interfaceID = inStream.readSignedWordBigEndianA();
			removeID = inStream.readSignedWordBigEndianA();
			removeSlot = inStream.readSignedWordBigEndian();

			debug("RemoveItem 5: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );
			if (interfaceID == 5064) { //remove from bag to bank
				getInventoryHandler().bankItem(removeID , removeSlot, 5);
			} else if (interfaceID == 5382) { //remove from bank
				getInventoryHandler().fromBank(removeID , removeSlot, 5);
			} else if (interfaceID == 3322) { //remove from bag to trade window
				if(Item.isUntradable(removeID)) 
					sendMessage("You cannot trade this item"); 
				else
					getClientMethodHandler().tradeItem(removeID , removeSlot, 5);
			} else if (interfaceID == 3415) { //remove from trade window
				getClientMethodHandler().fromTrade(removeID, removeSlot, 5);
			} else if (interfaceID == 3823) { //Show value to sell items
				getClientMethodHandler().sellItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3900) { //Show value to buy items
				getClientMethodHandler().buyItem(removeID, removeSlot, 1);
			} 
			else getSmithingHandler().removeBarAndSmithItem(interfaceID, removeID, removeSlot, 5);

			break;

		case 43:	//bank 10 items - sell 5 items
			interfaceID = inStream.readUnsignedWordBigEndian();
			removeID = inStream.readUnsignedWordA();
			removeSlot = inStream.readUnsignedWordA();

			debug("RemoveItem 10: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );
			if (interfaceID == 5064) { //remove from bag to bank
				getInventoryHandler().bankItem(removeID , removeSlot, 10);
			} else if (interfaceID == 5382) { //remove from bank
				getInventoryHandler().fromBank(removeID , removeSlot, 10);
			} else if (interfaceID == 3322) { //remove from bag to trade window
				if(Item.isUntradable(removeID)) 
					sendMessage("You cannot trade this item"); 
				else
					getClientMethodHandler().tradeItem(removeID , removeSlot, 10);
			} else if (interfaceID == 3415) { //remove from trade window
				getClientMethodHandler().fromTrade(removeID, removeSlot, 10);
			} else if (interfaceID == 3823) { //Show value to sell items
				getClientMethodHandler().sellItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3900) { //Show value to buy items
				getClientMethodHandler().buyItem(removeID, removeSlot, 5);
			} 
			else getSmithingHandler().removeBarAndSmithItem(interfaceID, removeID, removeSlot, 10);

			break;

		case 129:	//bank all items - sell 10 items
			removeSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWord();
			removeID = inStream.readUnsignedWordA();

			debug("RemoveItem all: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );

			if (interfaceID == 5064) { //remove from bag to bank
				if (Item.itemStackable[removeID] == true) {
					getInventoryHandler().bankItem(playerItems[removeSlot] , removeSlot, playerItemsN[removeSlot]);
				} 
				else {
					getInventoryHandler().bankItem(playerItems[removeSlot] , removeSlot, getInventoryHandler().itemAmount(playerItems[removeSlot]));
				}
			} 
			else if (interfaceID == 5382) { //remove from bank
				getInventoryHandler().fromBank(bankItems[removeSlot] , removeSlot, bankItemsN[removeSlot]);
			} 
			else if (interfaceID == 3322) { //remove from bag to trade window
				if(Item.isUntradable(removeID)) 
					sendMessage("You cannot trade this item"); 
				else
					getClientMethodHandler().tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
			} else if (interfaceID == 3415) { //remove from trade window
				getClientMethodHandler().fromTrade(removeID, removeSlot, playerTItemsN[removeSlot]);
			} else if (interfaceID == 3823) { //Show value to sell items
				getClientMethodHandler().sellItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3900) { //Show value to buy items
				getClientMethodHandler().buyItem(removeID, removeSlot, 10);
			} 

			break;


		case 135:	//bank X items
			outStream.createFrame(27);
			XremoveSlot = inStream.readSignedWordBigEndian();
			XinterfaceID = inStream.readUnsignedWordA();
			XremoveID = inStream.readSignedWordBigEndian();

			if(server.debugmode) debug("RemoveItem X: "+XremoveID +" InterID: "+XinterfaceID +" slot: "+XremoveSlot);

			break;

		case 208:	//Enter Amount Part 2
			int EnteredAmount = inStream.readDWord();
			if (XinterfaceID == 5064) { //remove from bag to bank
				getInventoryHandler().bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 5382) { //remove from bank
				getInventoryHandler().fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} 

			else if (XinterfaceID == 3900) { //Shop
				if (EnteredAmount <= 250)
					getClientMethodHandler().buyItem(XremoveID, XremoveSlot, EnteredAmount);
				else
					sendMessage("You cannot buy more than 250 items at a time.");
			}

			else if (XinterfaceID == 3322) { //remove from bag to trade window
				getClientMethodHandler().tradeItem(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3415) { //remove from trade window
				getClientMethodHandler().fromTrade(XremoveID, XremoveSlot, EnteredAmount);
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
			break;

		case 87:		// drop item
			int droppedItem = inStream.readUnsignedWordA();
			somejunk = inStream.readUnsignedByte()+inStream.readUnsignedByte();
			int slot = inStream.readUnsignedWordA();
			//println_debug("dropItem: "+droppedItem+" Slot: "+slot);
			if(Item.isUntradable(droppedItem)) {
				sendMessage("You drop the "+Item.getItemName(droppedItem)+", it vanishes into the ground.");
				getInventoryHandler().deleteItem(droppedItem, slot, playerItemsN[slot]);
			}
			if(droppedItem == 744 && absX == 2780 && absY == 3515 && q3stage == 5) {
				server.npcHandler.newNPC(1645, absX+1, absY, heightLevel, absX + 3, absY + 3, absX + -3, absY + -3, 1, server.npcHandler.getHP(1645), false);      
			}                          
			else if(wearing == false && playerItems[slot] == droppedItem+1){
				getInventoryHandler().dropItem(droppedItem, slot);
			}
			break;

		case 120: // sends sidebar id when clicked while it's flashing - found by xerozcheez  
			int sidebarID = inStream.readUnsignedByte();
			System.out.println("Packet 120: Sidebar Id: "+sidebarID);
			break;

		case 185:               //clicking most buttons
			actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
			getButtonClickHandler().clickButton(actionButtonId);			
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

	int applySnare = -1;

	public void ManipulateDirection() {
		//playerMD = misc.direction(absX, absY, skillX, skillY);
		if (playerMD != -1) {
			//playerMD >>= 1;
			updateRequired = true;
			dirUpdateRequired = true;
		}
	}

	/*for (int i = 4000; i <= 7000; i++) {
			sendFrame126("T"+i, i);
			println_debug("Sent: Test"+i);
		}*///USED FOR TESTING INTERFACE NUMBERS !
	//MaxHit

	public String npcName;
	public int npcID;
	public Queue<String> npcLines = new LinkedList<String>();

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
		followingPlayerID = -1;
		followingNPCID = -1;
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
		return true;
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

}  
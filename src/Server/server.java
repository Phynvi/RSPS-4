import java.sql.*;
import java.io.*;
import java.util.*;

public class server implements Runnable {
	
public static WorldMap worldMap = null;	


public static ArrayList<String> connectedList = new ArrayList<String>();
public static final String SERVERNAME = "SarimScape";

public static boolean debugmode = false;

	public server() {
		// the current way of controlling the server at runtime and a great debugging/testing tool
		//jserv js = new jserv(this);
		//js.start();

	}

	// TODO: yet to figure out proper value for timing, but 500 seems good
	public static final int cycleTime = 500;
	public static boolean updateServer = false;
	public static int updateSeconds = 180; //180 because it doesnt make the time jump at the start :P
	public static long startTime;
	public static long upTime;
	public static boolean showDelay = false;
	public static GlobalObjectHandler globalObjectHandler = null;
	
	public static void main(java.lang.String args[]) {
		lists.generateLists();
		pestControlHandler = new PestControlHandler();
		worldMap = new WorldMap();
		worldMap.loadWorldMap();
		clientHandler = new server();
		(new Thread(clientHandler)).start();			// launch server listener
		playerHandler = new PlayerHandler();
		cHandler = new cHandler();
		cHandler.start();
		npcHandler = new NPCHandler();
		itemHandler = new ItemHandler();
		shopHandler = new ShopHandler();
		muteHandler = new MuteHandler();
		itemspawnpoints = new itemspawnpoints();
		GraphicsHandler = new GraphicsHandler();
		globalObjectHandler = new GlobalObjectHandler();
		//dialogueHandler = new DialogueHandler(); 
		int waitFails = 0;
		long lastTicks = System.currentTimeMillis();
		long totalTimeSpentProcessing = 0;
		int cycle = 0;
		long playerTimeSpentProcessing = 0,npcTimeSpentProcessing = 0,
				itemTimeSpentProcessing = 0,shopTimeSpentProcessing = 0,
				pestControlTimeSpentProcessing=0,globalObjectTimeSpentProcessing = 0;
		int printOutDelay = 0;
		int averages = 0;
		long totals = 0;
		while(!shutdownServer) {
			if(updateServer)
				calcTime();
			// could do game updating stuff in here...
			// maybe do all the major stuff here in a big loop and just do the packet
			// sending/receiving in the client subthreads. The actual packet forming code
			// will reside within here and all created packets are then relayed by the subthreads.
			// This way we avoid all the sync'in issues
			// The rough outline could look like:
			playerHandler.process();			// updates all player related stuff
			if(showDelay) playerTimeSpentProcessing = System.currentTimeMillis() - lastTicks;
			npcHandler.process();
			if(showDelay) npcTimeSpentProcessing = System.currentTimeMillis() - lastTicks;
			itemHandler.process();
			if(showDelay) itemTimeSpentProcessing = System.currentTimeMillis() - lastTicks;
			shopHandler.process();
			if(showDelay) shopTimeSpentProcessing = System.currentTimeMillis() - lastTicks;
			pestControlHandler.process();
			if(showDelay) pestControlTimeSpentProcessing = System.currentTimeMillis() - lastTicks;
			globalObjectHandler.process();
			if(showDelay) globalObjectTimeSpentProcessing = System.currentTimeMillis() - lastTicks;
			//antilag.process();
			//itemspawnpoints.process();

			System.gc();
			// doNpcs()		// all npc related stuff
			// doObjects()
			// doWhatever()

			// taking into account the time spend in the processing code for more accurate timing
			long timeSpent = System.currentTimeMillis() - lastTicks;
			totalTimeSpentProcessing += timeSpent;
			if(showDelay && ++printOutDelay == 10){
				printOutDelay = 0;
				
				System.out.println("[KERNEL] : Process time spent report:");
				System.out.println("	 player:"+playerTimeSpentProcessing+", npc:"+npcTimeSpentProcessing+
						", item:"+itemTimeSpentProcessing+", shop:"+shopTimeSpentProcessing+
						", PC:"+pestControlTimeSpentProcessing+", GlobalObject: "+globalObjectTimeSpentProcessing);
				
				totals += (playerTimeSpentProcessing+npcTimeSpentProcessing+itemTimeSpentProcessing+
						shopTimeSpentProcessing+pestControlTimeSpentProcessing+globalObjectTimeSpentProcessing)/4;
				averages += 1;
				if(averages%10 == 0){
					System.out.println("[KERNEL] [AVERAGES] : Last 10 Processes Averages : "+(totals/10));
					totals = 0;
				}
			}
			if(timeSpent >= cycleTime) {
				timeSpent = cycleTime;
				if(++waitFails > 100) {
					//shutdownServer = true;
					misc.println("[KERNEL]: machine is operating too slow. Time to run processes: "+(System.currentTimeMillis() - lastTicks));
				}
			}
			try {
				Thread.sleep(cycleTime-timeSpent);
			} catch(java.lang.Exception _ex) {System.out.println("Could not sleep right?");}
			lastTicks = System.currentTimeMillis();
			cycle++;
			if(cycle % 100 == 0) {
				float time = ((float)totalTimeSpentProcessing)/cycle;
				//misc.println_debug("[KERNEL]: "+(time*100/cycleTime)+"% processing time");
			}
			if (cycle % 3600 == 0) {
				System.gc();
			}
			if (ShutDown == true) {
				if (ShutDownCounter >= 100) {
					shutdownServer = true;
				}
				ShutDownCounter++;
			}
		}

		// shut down the server
		playerHandler.destruct();
		clientHandler.killServer();
		clientHandler = null;
	}

	public static server clientHandler = null;			// handles all the clients
	public static java.net.ServerSocket clientListener = null;
	public static boolean shutdownServer = false;		// set this to true in order to shut down and kill the server
	public static boolean shutdownClientHandler;			// signals ClientHandler to shut down
	public static int serverlistenerPort = 43594; //43594=default if u use earthscape client made by me !

public static int stradd = 2;
	
	public static int specBarAdd = 3;
	public static int specBarAdd2 = 8;
	public static PlayerHandler playerHandler = null;
	public static NPCHandler npcHandler = null;
	public static cHandler cHandler = null;
	public static ItemHandler itemHandler = null;
	public static ShopHandler shopHandler = null;
	public static MuteHandler muteHandler = null;
      public static itemspawnpoints itemspawnpoints = null;
      public static GraphicsHandler GraphicsHandler = null;

      public static PestControlHandler pestControlHandler = null;
      //public static DialogueHandler dialogueHandler = null; 

	public static void calcTime() {
		long curTime = System.currentTimeMillis();
		updateSeconds = 180 - ((int)(curTime - startTime) / 1000);
		if(updateSeconds == 0) {
			shutdownServer = true;
		}
	}

	public void run() {

		// setup the listener		
		try {
			shutdownClientHandler = false;
			clientListener = new java.net.ServerSocket(serverlistenerPort, 1, null);
			misc.println(server.SERVERNAME+" - ONLINE");
			
			upTime = System.currentTimeMillis();
			while(true) {
				java.net.Socket s = clientListener.accept();
				s.setTcpNoDelay(true);
				String connectingHost = s.getInetAddress().getHostName();
				boolean OR = true; //override
				if(!connectedList.contains(connectingHost) || OR) {
					if (connectingHost.startsWith("Insert Server Checker IP Address here")) {
						misc.println(connectingHost+": Checking if server still is online...");
					} 
					/*if(!banned(connectingHost)) 
					{
						misc.println("ClientHandler: Accepted from "+connectingHost+":"+s.getPort());

					}*/
					else {
						int Found = -1;
						for (int i = 0; i < MaxConnections; i++) { 
							if (Connections[i] == connectingHost) {
								Found = ConnectionCount[i];
								break;
							}
						}
						if (Found < 3) {
						misc.println("\nCharacter accepted from "+connectingHost+" on port "+s.getPort()+".");
							playerHandler.newPlayerClient(s, connectingHost);
							connectedList.add(connectingHost);
						} 
						else s.close();
					}
				} 
//				else 	if(PlayerHandler.isPlayerOn(playerName)){ 
//					for(Player p : server.playerHandler.players){
//						if(p != null){
//							if(p.playerName.equalsIgnoreCase(playerName)){
//								client g = (client) p;
//							  savefile = false;
//							  disconnected = true;
//								g.disconnectPlayerAndSave("Another person is logging onto the same Account");
//							}
//						}
//					}
//				}
				else {
					//playerHandler.newPlayerClient(s, connectingHost);
					//connectedList.add(connectingHost);
					misc.println("ClientHandler: Rejected "+connectingHost+":"+s.getPort());
					s.close();
				}
			}
		} 
		catch(java.io.IOException ioe) {
			if(!shutdownClientHandler) 
				misc.println("Error: Unable to startup listener on 43594 - port already in use?");
			
			else misc.println("ClientHandler was shut down.");
		}
	}
	
	public void killServer() {
		try {
			shutdownClientHandler = true;
			if(clientListener != null) clientListener.close();
			clientListener = null;
		} catch(java.lang.Exception __ex) {
			__ex.printStackTrace();
		}
	}

	public static int EnergyRegian = 60;

	public static int MaxConnections = 100000;
	public static String[] Connections = new String[MaxConnections];
	public static int[] ConnectionCount = new int[MaxConnections];
	public static boolean ShutDown = false;
	public static int ShutDownCounter = 50400;


}


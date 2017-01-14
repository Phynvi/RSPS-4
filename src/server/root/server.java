package server.root;
import java.io.PrintStream;
import java.util.*;

import javax.swing.JFrame;

import server.handlers.*;
import server.handlers.NPC.NPCHandler;
import server.handlers.NPC.npcDialogueBST;
import server.handlers.chat.GlobalChatRoomHandler;
import server.handlers.chat.MuteHandler;
import server.handlers.enemy.EnemyHandler;
import server.handlers.globalObject.GlobalObjectHandler;
import server.handlers.item.ItemHandler;
import server.handlers.minigame.PestControlHandler;
import server.handlers.player.PlayerHandler;
import server.handlers.processes.ServerProcess;
import server.handlers.shop.ShopHandler;
import server.handlers.task.TaskScheduler;
import server.handlers.window.ServerInformation;
import server.handlers.window.TextAreaOutputStream;
import server.resources.Lists;
import server.resources.misc;
import server.tests.AllTests;
import server.tests.Test;

public class server implements Runnable {
	
public static boolean RUN_TESTS = false;
public static boolean showDelay = false;
public static boolean showAverage = false;
	
public static WorldMap worldMap = null;	
public static Lists lists = null;

public static LinkedList<Test> tests = new LinkedList<Test>();

public static ArrayList<String> connectedList = new ArrayList<String>();
public static final String SERVERNAME = "SarimScape";

public static boolean debugmode = false;
public static npcDialogueBST npcDialogueHandler;

public static TextAreaOutputStream DebugStream;
public static TextAreaOutputStream ServerTestsStream;
public static TextAreaOutputStream KernelStream;
public static TextAreaOutputStream DelayStream;
public static TextAreaOutputStream SystemStream;
public static TextAreaOutputStream PlayerMessagesStream;
public static TextAreaOutputStream AllChatStream;

	public server() {
		// the current way of controlling the server at runtime and a great debugging/testing tool
		//jserv js = new jserv(this);
		//js.start();
		serverInformationWindow = new ServerInformation();
		
		SystemStream = ServerInformation.SystemPanel.getStream();
		KernelStream = ServerInformation.KernelPanel.getStream();
		DelayStream = ServerInformation.DelayPanel.getStream();
		DebugStream = ServerInformation.DebugPanel.getStream();
		ServerTestsStream = ServerInformation.ServerTestsPanel.getStream();
		PlayerMessagesStream = ServerInformation.PlayerMessagesPanel.getStream();
		AllChatStream = ServerInformation.ChatPanel.getStream();
		
		lists = new Lists();
		worldMap = new WorldMap(); //TODO - remove reference
		WorldMap.loadWorldMap();
	}

	// cycletime at 500 is appropriate
	public static final int cycleTime = 500;
	public static boolean updateServer = false;
	public static int updateSeconds = 180; //180 because it doesnt make the time jump at the start :P
	public static long startTime;
	public static long upTime;
	public static GlobalObjectHandler globalObjectHandler = null;
	public static GlobalChatRoomHandler globalChatRoomHandler = null;
	public static TaskScheduler taskScheduler = null;
	
	public static PlayerHandler playerHandler = null;
	public static NPCHandler npcHandler = null;
	public static cHandler cHandler = null;
	public static ItemHandler itemHandler = null;
	public static ShopHandler shopHandler = null;
	public static MuteHandler muteHandler = null;
	public static EnemyHandler enemyHandler = null;
	
	public static ServerInformation serverInformationWindow;
	
	private static LinkedList<ServerProcess> processes = new LinkedList<ServerProcess>();
	
	public static void debug(String s){
		if(debugmode)
			DebugStream.println(s);
	}
	
	public static void test(String s){
		ServerTestsStream.println(s);
	}
	
	public static void addToProcesses(ServerProcess servProc){
		processes.add(servProc);
	}
	
	public static void main(java.lang.String args[]) {
		taskScheduler = new TaskScheduler();
		pestControlHandler = new PestControlHandler("Pest Control Handler");
		clientHandler = new server();
		(new Thread(clientHandler)).start();			// launch server listener
		playerHandler = new PlayerHandler("Player Handler");
		cHandler = new cHandler();
		cHandler.start();
		npcHandler = new NPCHandler("NPC Handler");
		itemHandler = new ItemHandler("Item Handler");
		shopHandler = new ShopHandler("Shop Handler");
		muteHandler = new MuteHandler();
		globalObjectHandler = new GlobalObjectHandler("Global Object Handler");
		globalChatRoomHandler = new GlobalChatRoomHandler("Global Chatroom Handler");
		npcDialogueHandler = new npcDialogueBST();
		enemyHandler = new EnemyHandler();

		int printOutDelay = 0;
		while(!shutdownServer) {
			long curTime = System.currentTimeMillis();
			
			if(updateServer)
				calcTime();

			System.gc();
			
			if(!showDelay){
				for(ServerProcess sp : processes){
					sp.process();
				}		
			}
			else{
					int printDelay = 2;
				if(showAverage)
					printDelay = 10;
				if(++printOutDelay == printDelay){
					printOutDelay = 0;

					DelayStream.println();
					for(ServerProcess sp : processes){
						long timedDelay = System.currentTimeMillis();
						sp.process();
						long remainingDelay = System.currentTimeMillis()-timedDelay;
						if(showAverage){
							sp.storeTimeToProcess(remainingDelay);
							DelayStream.println("[SHOWAVERAGE] ["+sp.GetProcessName()+"] - Average time per process: "+sp.getAverageMS()+" - Total averages taken: "+sp.getTotalAverages());
							}
						else{
							DelayStream.println("[SHOWDELAY] ["+sp.GetProcessName()+"] - MS to process: "+remainingDelay);
						}
					}
					DelayStream.println();
					
					if(RUN_TESTS){
						ServerTestsStream.println(" : Initiating Tests");
						
						AllTests allTests = new AllTests(tests);
						allTests.RunAllTests();
						
						ServerTestsStream.println(" : Testing Completed");					
						RUN_TESTS = false;
					}
					
				}
			}
			
			long timeSpent = System.currentTimeMillis()-curTime;
			
			if(timeSpent >= cycleTime) {
				timeSpent = cycleTime;
				KernelStream.println("machine is operating too slow. Time to run processes: "+(timeSpent));
			}
			try {
				Thread.sleep(cycleTime-timeSpent);
			} catch(java.lang.Exception _ex){
				_ex.printStackTrace();
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
			SystemStream.println(server.SERVERNAME+" - ONLINE");
			
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
	
	public static void killServer() {
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


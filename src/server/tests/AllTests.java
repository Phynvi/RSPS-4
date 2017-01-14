package server.tests;

import java.util.LinkedList;

import client.Player;
import client.client;
import server.handlers.player.PlayerHandler;
import server.root.server;
import server.tests.Test;

public class AllTests {

	private LinkedList<Test> tests = new LinkedList<Test>();
	private static client testPlayer;
	public static String testPlayerName = "TestPlayer";
	
	public static client getTestPlayerClient() throws PlayerNotFoundException{
		if(testPlayer == null){
			throw new PlayerNotFoundException();
		}
		return testPlayer;
	}
	
	private void findPlayer(){
		for(Player p : PlayerHandler.players){
			if(p != null && p.playerName.equalsIgnoreCase(testPlayerName)){
				testPlayer = (client)p;
			}
		}
	}
	
	public AllTests(LinkedList<Test> tests){

		findPlayer();
		
		new ItemTests();
		new PlayerTests();
		new TaskSchedulerTests();
		this.tests = tests;
	}
	
	public void RunAllTests(){
		for(Test t : tests){
			try{
				String temp = t.runTest();
				if(temp != null){
					server.ServerTestsStream.println();
					server.ServerTestsStream.println("[TEST] ["+t.testName+"] [FAILED]");
					server.ServerTestsStream.println(temp);
					server.ServerTestsStream.println();
				}
				else{
					server.ServerTestsStream.println("[TEST] ["+t.testName+"] [PASSED]");
				}
			}
			catch(Exception e){
				server.ServerTestsStream.println();
				server.ServerTestsStream.println("[TEST] ["+t.testName+"] [FAILED] [UNCAUGHT EXCEPTION]");
				server.ServerTestsStream.println(e.toString());
				server.ServerTestsStream.println();
			}
		}
	}
	
}

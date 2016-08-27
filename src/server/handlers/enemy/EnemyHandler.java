package server.handlers.enemy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import client.Player;
import server.handlers.NPC.NPC;
import server.handlers.processes.ServerProcess;
import server.root.server;

public class EnemyHandler{

	private static Map<Integer, Enemy> enemies = new LinkedHashMap<>();
	
	private static int GenerateIDNPC = 0;
	
	private static int GenerateIDPlayer = server.npcHandler.maxNPCs + 1;
	
	public static int getUniqueEnemyID(Enemy e){
		if(e.isNPC()){
			return e.getNPC().npcId + GenerateIDNPC;
		}
		else{
			return e.getPlayerClient().playerId + GenerateIDPlayer;
		}
	}
	public static int getUniqueEnemyID(NPC n){
		return n.npcId + GenerateIDNPC;
	}
	public static int getUniqueEnemyID(Player p){
		return p.playerId + GenerateIDPlayer;
	}
	
	public static boolean addEnemy(Enemy e){
		if(enemies.containsKey(e.getUniqueID())){
			return false;
		}
		enemies.put(e.getUniqueID(), e);
		return true;
	}
	
}

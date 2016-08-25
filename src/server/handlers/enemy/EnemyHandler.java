package server.handlers.enemy;

import java.util.HashSet;
import java.util.LinkedList;

import client.handlers.combat.Enemy;
import server.handlers.processes.ServerProcess;

public class EnemyHandler extends ServerProcess{
	public EnemyHandler(String name) {
		super(name);
	}

	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	private HashSet<Enemy> existingEnemies = new HashSet<Enemy>();
	
	private static int GenerateID = 0;
	public static int getUniqueEnemyID(){
		GenerateID += 1;
		return GenerateID;
	}
	
	public void addEnemy(Enemy e){
		
		
		enemies.add(e);
		
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
	
}

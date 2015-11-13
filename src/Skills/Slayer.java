
public class Slayer {

	private client c;

	public BST slayerNPC = new BST();
	private int lowLevelMonsters[] = {60,63,1153,117,112,1604,1154};
	private int midLevelMonsters[] = {60,63,1153,117,112,1604,1154,82,83,110,912,1246,941,1624,84,1248,1250};
	private int allMonsters[] = {60,63,1153,117,112,1604,1154,82,83,110,912,1246,941,1624,84,1248,1250,1615,55,53,1590,54,1591,1592};
	
	public Slayer(client playerClient){
		c = playerClient;
		this.slayerNPC.add(allMonsters);
	}
	
	/**
	 * Will get the player's current slayer task name
	 */
	public String getTaskName(int npcID){
		switch(npcID){
		case 82: return "Lesser Demon";
		case 83: return "Greater Demon";
		case 84: return "Black Demon";
		case 53: return "Red Dragon";
		case 54: return "Black Dragon";
		case 55: return "Blue Dragon";
		case 1590: return "Bronze Dragon";
		case 1591: return "Iron Dragon";
		case 1592: return "Steel Dragon";
		case 110: return "Fire Giant";
		case 112: return "Moss Giant";
		case 117: return "Hill Giant";
		case 60: return "Giant Spider";
		case 63: return "Deadly Red Spider";
		case 941: return "Green Dragon";
		case 1615: return "Abyssal Demon";
		case 912: return "Dark Battle Mage";
		case 913: return "Light Battle Mage";
		case 1624: return "Dust Devil";
		case 1246: return "Riyl Shade";
		case 1250: return "Fiyr Shade";
		case 1248: return "Asyn Shade";
		case 1604: return "Aberrant Specter";
		case 1153: return "Kalphite Worker";
		case 1154: return "Kalphite Soldier";
		case 0: return "none";
		}
		c.debug("npcID : "+npcID+" not found in Slayer getTaskName");
		return null;
	}
	
	/**
	 * Generates the amount of EXP to distribute for a slayer task kill
	 * @param npcID ID to locate EXP with
	 * @return exp, calculated with player level, rate, and task ID
	 */
	public int generateEXP(int npcID){
		return getEXP(npcID)*c.rate*c.playerLevel[18];
	}
	
	private int getEXP(int npcID){		
		switch(npcID){
		case 60: return 20; //giant spider - 26
		case 63: return 23; //red spider - 36
		case 1153: return 25; //Kalphie Worker - 40
		case 117: return 30; //hill giant - 70
		case 112: return 33; //moss giant - 80
		case 1604: return 45; //Aberrant_specter - 90
		case 1154: return 40; //Kalphite Soldier - 90
		case 82: return 41; //lesser demon - 100
		case 83: return 42; //greater demon - 110
		case 110: return 43; //fire giant - 111
		case 912: case 913: return 45; //battle mages - 127
		case 1246: return 55; //Riyl Shade - 145
		case 941: return 65; //Green Dragons - 150
		case 1624: return 65; //Dust Devils - 150
		case 84: return 70; //black demon - 157
		case 1248: return 75;//Asyn Shade - 170
		case 1250: return 90;//Fiyr Shade - 200
		case 1615: return 100; //abby demons - 250
		case 55: return 95; //Blue Dragon - 250
		case 53: return 105; //Red Dragon - 300
		case 1590: return 115; //Bronze Dragon - 300
		case 54: return 125; //Black Dragon - 350
		case 1591: return 140; //Iron Dragon - 350
		case 1592: return 160; //Steel Dragon - 400
			
		}
		c.debug("npcID : "+npcID+" not found in Slayer getEXP");
		return -1;
	}
	
	/**
	 * Will generate and set global variables for slayer task
	 * Will call savemoreinfo to update player slayer task and count
	 */
	public void generateTask(){
		
		if(c.playerLevel[c.playerSlayer] < 40)
			c.slayerNPC = lowLevelMonsters[misc.random(lowLevelMonsters.length-1)]; 
		if(c.playerLevel[c.playerSlayer] >= 40 && c.playerLevel[c.playerSlayer] < 70)
			c.slayerNPC = midLevelMonsters[misc.random(midLevelMonsters.length-1)]; 
		if(c.playerLevel[c.playerSlayer] >= 70)
			c.slayerNPC = allMonsters[misc.random(allMonsters.length-1)]; 
		
		c.slayerCount = 11+misc.random((c.playerLevel[18]/4)+5);
		c.savemoreinfo(); //so we update the slayer task and count in the player's moreinfo file
	}
		
	/**
	 * Will get the task's required level
	 */
	public int getTaskLevel(int npcID){
		switch(npcID){
		case 1615: //abby demons - 250
			return 85;
		case 1250: //Fiyr Shade - 200
			return 75;
		case 1248: //Asyn Shade - 170
			return 65;
		case 1624: //dust devils - 150
			return 50;
		case 1246: //Riyl Shade - 145
			return 40;
		case 912: case 913: //Battle mages - 127
			return 30;
		case 1604:  //Aberrant Specter - 90
			return 15;
		default:
			return 0;
		}
	}
	
	
	
}

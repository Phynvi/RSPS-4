
public class Slayer {

	private client c;
	
	public BST slayerNPC = new BST();
	
	public Slayer(client playerClient){
		c = playerClient;
		this.slayerNPC.add(lowLevelMonsters,midLevelMonsters,highLevelMonsters);
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
		default: return c.getNpcName(npcID);
		}
	}
	
	/**
	 * Generates the amount of EXP to distribute for a slayer task kill
	 * @param npcID ID to locate EXP with
	 * @return exp, calculated with player level, rate, and task ID
	 */
	public int generateEXP(int npcID){
		return getEXP(npcID)*c.rate*c.playerLevel[18];
	}
	
	private int lowLevelMonsters[] = {60,117,63,1153,2850,112,941,82,83};
	private int midLevelMonsters[] = {1248,1604,1154,1250,55,110,1590};
	private int highLevelMonsters[] = {912,53,1246,1624,1615,84,1591,54,3070,1592,2783};
	
	// second number is their health
	private int getEXP(int npcID){		
		switch(npcID){
		case 60: return 20; //giant spider - 26
		case 117: return 22; //hill giant - 35
		case 63: return 23; //red spider - 36
		case 1153: return 25; //Kalphite Worker - 40
		case 2850: return 26; //Fever Spider - 40
		case 112: return 30; //moss giant - 60
		case 941: return 32; //Green Dragons - 75
		case 82: return 32; //lesser demon - 79
		case 83: return 34; //greater demon - 87
		case 1248: return 35;//Asyn Shade - 90
		case 1604: return 45; //Aberrant_specter - 90
		case 1154: return 40; //Kalphite Soldier - 90
		case 1250: return 40;//Fiyr Shade - 100
		case 55: return 42; //Blue Dragon - 105
		case 110: return 43; //fire giant - 111
		case 1590: return 44; //Bronze Dragon - 122
		case 912: case 913: return 45; //battle mages - 127
		case 53: return 49; //Red Dragon - 140
		case 1246: return 55; //Riyl Shade - 145
		case 1624: return 65; //Dust Devils - 150
		case 1615: return 80; //abby demons - 150
		case 84: return 70; //black demon - 157
		case 1591: return 80; //Iron Dragon - 165
		case 54: return 125; //Black Dragon - 190
		case 3070: return 140; //Skeletal Wyvern - 200
		case 1592: return 160; //Steel Dragon - 210
		case 2783: return 180; //Dark Beast - 220
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
			c.slayerNPC = highLevelMonsters[misc.random(highLevelMonsters.length-1)]; 
		c.slayerCount = 11+misc.random((c.playerLevel[18]/4)+5);
		c.savemoreinfo(); //so we update the slayer task and count, then save the player's moreinfo file
	}
		
	/**
	 * Will get the task's required level
	 */
	public int getTaskLevel(int npcID){
		switch(npcID){
		case 1615: //abby demons 
			return 85;
		case 1250: //Fiyr Shade 
			return 75;
		case 1248: //Asyn Shade 
			return 65;
		case 1624: //dust devils 
			return 50;
		case 1246: //Riyl Shade 
			return 40;
		case 2850: //Fever Spider
			return 40;
		case 912: case 913: //Battle mages 
			return 30;
		case 1604:  //Aberrant Specter 
			return 15;
		case 2783: //Dark Beast
			return 90;
		case 3070: //Skeletal Wyvern
			return 72;
		default:
			return 0;
		}
	}
	
	
	
}

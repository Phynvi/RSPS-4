package client.handlers.skills;
import client.client;
import client.handlers.combat.DamageType;
import server.resources.misc;
import server.root.server;

public class Thieving{

	private client c;

	public Thieving(client pc){
		this.c = pc;
	}

	private String[] chests = new String[20];

	private int getChestDamage(int objectID){
		switch(objectID){

		case 2566: //nature rune chest
			return 1;
			
		case 2569: //Blood Rune chest
			return 2;
		
		case 2570: //paladin chest
			return 3;

		default: 
			return 0;
		}
	}
	
	private int getEXPForChest(int objectID){
		switch(objectID){

		case 2566: //nature rune chest
			return 25;
			
		case 2569: //Blood Rune chest
			return 250;
		
		case 2570: //paladin chest
			return 500;
		
		default: 
			return 0;
		}
	}

	public boolean openChest(int objectID, int objectX, int objectY, int direction, int cooldownDuration, int levelRequired, int ... items){
		if(c.playerLevel[c.playerThieving] < levelRequired){
			c.sendMessage("You need to have at least "+levelRequired+" thieving to do that.");
			return true;
		}

		if(c.getInventoryHandler().freeSlots() < items.length/2){
			c.sendMessage("You do not have enough room in your inventory.");
			return true;
		}

		String s = ""+objectX+""+objectY;
		for(int i = 0; i < chests.length; i++){
			if(chests[i] != null && chests[i].equals(s)){

				chests[i] = null;
				server.globalObjectHandler.createObjectForSeconds(cooldownDuration, objectX, objectY, objectID, direction, 2574, null);
				c.startAnimation(536);
				c.getClientMethodHandler().addSkillXP(getEXPForChest(objectID)*c.rate, c.playerThieving);
				
				for(int j = 0; j < items.length-1; j += 2)
					c.getInventoryHandler().addItem(items[j],items[j+1]);
				
				return true;
			}
		}

		c.startAnimation(779);
		c.getCombatHandler().damagePlayer(c.playerId, misc.random(getChestDamage(objectID))+1, DamageType.NORMAL);
		c.sendMessage("A trap goes off, injuring you");
		return false;
	}

	public boolean disarmTrap(int objectID, int objectX, int objectY, int levelRequired){
		String s = ""+objectX+""+objectY;
		for(int i = 0; i < chests.length; i++){
			if(chests[i] != null && chests[i].equals(s)){
				c.sendMessage("This trap is already disarmed.");
				return true;
			}
		}
		int chance = c.playerLevel[c.playerThieving]/levelRequired;
		if(misc.random(chance) == 0){
			c.sendMessage("While disarming the trap, it goes off and injures you.");
			c.startAnimation(779);
			c.getCombatHandler().damagePlayer(c.playerId, misc.random(getChestDamage(objectID))+1,DamageType.NORMAL);
		}
		else{
			c.startAnimation(2244);
			c.sendMessage("You disable the trap successfully.");
		}
		for(int i = 0; i < chests.length; i++){
			if(chests[i] == null){
				chests[i] = s;
				return true;
			}
		}
		return false;
	}

	public static void pickpocket(int level, int dif, int gold, int expamount, client c){
		if (c.playerLevel[17] >= level && (System.currentTimeMillis() - c.spamtimer >= 3000)){
			c.spamtimer = System.currentTimeMillis();
			if (misc.random(c.playerLevel[17]/dif) != 0){
				c.startAnimation(881);
				c.getInventoryHandler().addItem(995, misc.random(gold/2)+gold);
				c.getClientMethodHandler().addSkillXP(expamount*c.rate, 17);
				c.sendMessage("You steal some gold.");
			}
			else {
				c.startAnimation(403);
				c.hitDiff = misc.random(2)+1;
				c.currentHealth -= c.hitDiff;
				c.updateRequired = true;
				c.hitUpdateRequired = true;
				c.sendMessage("You got caught.");
			}

		}
		else if (c.playerLevel[17] < level){
			c.sendMessage("You need at least "+level+" thieving to do that.");
		}
	}

	public static void stalls(int level, int xp, int item, int timer, client c){
		if ((c.playerLevel[17] >= level) && (System.currentTimeMillis() - c.spamtimer >= timer) && (c.getInventoryHandler().freeSlots() >= 1)){
			c.spamtimer = System.currentTimeMillis();
			c.startAnimation(881);
			c.getClientMethodHandler().addSkillXP(xp*c.rate, 17);
			c.getInventoryHandler().addItem(item, 1);
		}
		else if (c.getInventoryHandler().freeSlots() < 1){
			c.sendMessage("Your inventory is full.");
		}
		else if (c.playerLevel[17] < level){
			c.sendMessage("You need at least "+level+" thieving to do that.");
		}

	}



}
public class Thieving{

	public static void pickpocket(int level, int dif, int gold, int expamount, client c){
		if (c.playerLevel[17] >= level && (System.currentTimeMillis() - c.spamtimer >= 3000)){
			c.spamtimer = System.currentTimeMillis();
			if (misc.random(c.playerLevel[17]/dif) != 0){
				c.startAnimation(881);
				c.getInventoryHandler().addItem(995, misc.random(gold/2)+gold);
				c.actionTimer = 10;
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
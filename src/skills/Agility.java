package skills;
import playerData.client;
import root.misc;
import clientHandlers.Item;


public class Agility {
	private client c;

	public Agility(client input){
		this.c = input;
	}

	public final int CRAWL_EMOTE = 844;


	/**
	 * 
	 * @param x1 side 1 X
	 * @param y1 side 1 Y
	 * @param x2 side 2 X
	 * @param y2 side 2 Y
	 * @param emote Emote to use during c.obstacle
	 * @param level Required to use c.obstacle
	 * @param exp experience given, multiplied by rate
	 * @param isFast Set to True if c.running during emote
	 * @return
	 */
	public boolean agilityTeleport(int x1, int y1, int x2, int y2, int emote, int level, int exp, boolean dmg, int amount, String msg){
		if(c.playerLevel[c.playerAgility] >= level){
			int chance = c.playerLevel[c.playerAgility]-level;		
			if(c.absX == x1 && c.absY == y1){
				c.teleport(x2, y2);
				if(dmg && misc.random(chance) == 0){
					//					c.getCombatHandler().damagePlayer(playerId, misc.random(amount));
					//					c.sendMessage("You injure yourself.");
				}
				c.startAnimation(emote);
				return true;
			}
			if(c.absX == x2 && c.absY == y2){
				c.teleport(x1,y1);
				if(dmg && misc.random(chance) == 0){
					//					c.getCombatHandler().damagePlayer(playerId, misc.random(amount));
					//					c.sendMessage("You injure yourself.");
				}
				c.startAnimation(emote);
				return true;
			}
			return false;
		}
		else {
			c.sendMessage("You need "+level+" agility to do that.");
			return false;
		}
	}	

	/**
	 * Enables noclick, which does not allow for a play to click around, until they are at their destination
	 */
	public void agilitywalkTo(int x, int y){
		c.noClick = true;
		c.noClickTimeout = 10;
		c.shouldbeX = c.absX+x;
		c.shouldbeY = c.absY+y;
		c.walkTo(x,y);
	}

	/**
	 * 
	 * @param emote Emote to do until finished
	 * @param agilX Destination X
	 * @param agilY2 Destination Y
	 * @param exp Exp to give, multiplied by rate
	 * @param isFast set to True if you want to run
	 */
	public void walkingemote(int emote, int agilX, int agilY2, int exp, boolean isFast){
		c.obstacle = System.currentTimeMillis();

		if(c.isRunning2) c.wasrunning = true;
		else c.wasrunning = false;

		if(isFast) c.isRunning2 = true;
		else c.isRunning2 = false;

		c.agilX = agilX;
		c.agilY = agilY2;
		c.playerSEW = emote;
		c.playerSER = emote;
		int X = agilX-c.absX;
		int Y = agilY2-c.absY;
		c.walkTo(X, Y);
		c.getClientMethodHandler().addSkillXP(exp*c.rate, 16);
	}

	public void walkingemote(int emote, int agilX, int agilY2, int X, int Y, int exp){
		c.obstacle = System.currentTimeMillis();
		if (c.isRunning2 == true){
			c.isRunning2 = false;
			c.wasrunning = true;
		}
		c.agilX = agilX;
		c.agilY = agilY2;
		c.playerSEW = emote;
		c.playerSER = emote;
		c.walkTo(X, Y);
		c.getClientMethodHandler().addSkillXP(exp, 16);
	}

	public void walkingemote(int emote, int agilX, int agilY2){
		c.obstacle = System.currentTimeMillis();
		if (c.isRunning2 == true){
			c.isRunning2 = false;
			c.wasrunning = true;
		}
		c.agilX = agilX;
		c.agilY = agilY2;
		c.playerSEW = emote;
		c.playerSER = emote;
	}

	public void walkingemoterun(int emote, int agilX, int agilY2, int X, int Y, int exp){
		c.obstacle = System.currentTimeMillis();
		if (c.isRunning2 == true){
			c.wasrunning = true;
		}
		else if (c.isRunning2 == false){
			c.wasrunning = false;
			c.isRunning2 = true;
		}
		c.playerSEW = emote;
		c.playerSER = emote;
		c.agilX = agilX;
		c.agilY = agilY2;
		c.walkTo(X, Y);
		c.getClientMethodHandler().addSkillXP(exp, 16);
	}



	public boolean[] barbObstacles = new boolean[6];
	private int failedObstacleCase = -1;
	public boolean agilityTesting = false;
	private int damageBecauseFailed = 0;

	public boolean agilityObstacleOneWay(int x1, int y1,int x2, int y2, int emote, int level, int exp, boolean isFast, boolean dmg, int amount, int specialCase){
		if(c.absX == x2 && c.absY == y2)
			return false;
		return agilityObstacle(x1,y1,x2, y2, emote, level, exp, isFast, dmg, amount, specialCase);
	}

	/**
	 * 
	 * @param x1 side 1 X
	 * @param y1 side 1 Y
	 * @param x2 side 2 X
	 * @param y2 side 2 Y
	 * @param emote Emote to use during c.obstacle
	 * @param level Required to use c.obstacle
	 * @param exp experience given, multiplied by rate
	 * @param isFast Set to True if c.running during emote
	 * @return
	 */
	public boolean agilityObstacle(int x1, int y1, int x2, int y2, int emote, int level, int exp, boolean isFast, boolean dmg, int amount, int specialCase){
		if(c.playerLevel[c.playerAgility] >= level){
			boolean tookDamage = false;
			if((dmg && misc.random(c.playerLevel[c.playerAgility]-level) == 0) || agilityTesting){
				if(specialCase <= 0){
					c.getCombatHandler().damagePlayer(c.playerId, misc.random(amount)+1);
					c.sendMessage("You injure yourself.");
				}
				else{
					tookDamage = true;
					damageBecauseFailed = misc.random(amount)+1;
				}
			}

			switch(specialCase){
			case 1: //rope swing barbarian agility
				if((c.absX == 2550 || c.absX == 2551 || c.absX == 2552) && c.absY == 3554){
					if(!tookDamage){
						walkingemote(emote, 2551,3549, exp, isFast);
						return true;
					}
					else{
						c.teleport(2550,9950,0);
						c.startAnimation(1258);
						return false;
					}
				}
				return false;

			case 2:
				if(c.absX == 2536 && c.absY == 3547){
					walkingemote(emote, 2532,3547, exp, isFast);
					if(tookDamage){
						failedObstacleCase = specialCase;
						return false;
					}
					return true;
				}
				return false;				

			case 3:
				if(c.absX == 2551 && c.absY == 3546){
					if(!tookDamage){
						walkingemote(emote, 2541,3546, exp, isFast);
						return true;
					}
					else{
						failedObstacleCase = specialCase;
						walkingemote(emote, 2546,3546, exp, isFast);
						return false;
					}
				}
				return false;


			}

			if(c.absX == x1 && c.absY == y1){
				walkingemote(emote, x2, y2, exp, isFast);
				return true;
			}
			else if(c.absX == x2 && c.absY == y2){
				walkingemote(emote, x1, y1, exp, isFast);
				return true;
			}
			else{
				c.sendMessage("You should probably stand in front of the obstacle before attempting to use it.");
				return false;
			}
		}
		else {
			c.sendMessage("You need "+level+" agility to do that.");
			return false;
		}
	}

	private int waitToSwimTimer = -1;
	
	public void agilityTimers(){
		if (c.absX == c.agilX && c.absY == c.agilY){
			if (c.wasrunning == true){
				c.isRunning2 = true;
				c.wasrunning = false;}
			else if (c.wasrunning == false){
				c.isRunning2 = false;
				c.wasrunning = false;}
			else if (c.running == true){
				c.startAnimation(c.runningemote);
				c.isRunning2 = false;
				c.running = false;
			}
			c.playerSE = Item.GetStandAnim(c.playerEquipment[c.playerWeapon]);
			c.playerSEW = Item.GetWalkAnim(c.playerEquipment[c.playerWeapon]);
			c.playerSER = Item.GetRunAnim(c.playerEquipment[c.playerWeapon]);
			c.agilX = 0;
			c.agilY = 0;
		}

		if(failedObstacleCase > -1){
			switch(failedObstacleCase){
			case 2: //balance ledge from barb agility
				if(c.absX == 2532 && c.absY == 3547){
					failedObstacleCase = -1;
					c.getClientMethodHandler().teleportWithAnimationDelay(2533, 3545, 0, 1258, 2);
					c.getCombatHandler().damagePlayer(c.playerId, damageBecauseFailed);
					c.sendMessage("You fall down.");
				}
				break;
				
			case 3: //balance log from barb agility
				if(c.absX == 2546 && c.absY == 3547){
					if(waitToSwimTimer > 0 && --waitToSwimTimer == 0){
						walkingemote(772, 2546,3550, 14, false);
						failedObstacleCase = -1;
					}
				}
				if(c.absX == 2546 && c.absY == 3546){
					c.teleport(2546, 3547, 0);
					c.getCombatHandler().damagePlayer(c.playerId, damageBecauseFailed);
					c.sendMessage("You fall in the water!");
					waitToSwimTimer = 1;
				}
				break;
				
			}
		}

		//Brimhaven Agility
		if(c.isInArea(2753, 9535, 2814, 9600)){
			c.ticket = System.currentTimeMillis();
			if (c.absX == 2798 && c.absY == 9579){
				walkingemote(2750, 2802, 9579, 4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}       
			if (c.absX == 2801 && c.absY == 9579){
				walkingemote(2750, 2797, 9579, -4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}           
			if (c.absX == 2787 && c.absY == 9579){
				c.getFrameMethodHandler().createNewTileObject(2788, 9579, 3567, 1, 10);  
				walkingemote(2750, 2791, 9579, 4, 0, 500);
				c.sendMessage("You jump the blade!");
			}       
			if (c.absX == 2790 && c.absY == 9579){
				c.getFrameMethodHandler().createNewTileObject(2788, 9579, 3567, 1, 10);  
				walkingemote(2750, 2786, 9579, -4, 0, 500);
				c.sendMessage("You jump the blade!");
			}           
			if (c.absX == 2783 && c.absY == 9575){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					walkingemote(844, 2783, 9571);
					c.walkTo(0, -4);
					c.getClientMethodHandler().addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}  
			}
			if (c.absX == 2783 && c.absY == 9572){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					walkingemote(844, 2783, 9577);
					c.walkTo(0, 4);
					c.getClientMethodHandler().addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}  
			}   
			if (c.absX == 2779 && c.absY == 9557){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					walkingemote(844, 2775, 9557);
					c.walkTo(-4, 0);
					c.getClientMethodHandler().addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}
			}     
			if (c.absX == 2776 && c.absY == 9557){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					walkingemote(844, 2780, 9557);
					c.walkTo(4, 0);
					c.getClientMethodHandler().addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}
			}     
			if (c.absX == 2772 && c.absY == 9550){
				c.getFrameMethodHandler().createNewTileObject(2772, 9551, 2305, 0, 10);  
				c.getFrameMethodHandler().createNewTileObject(2772, 9552, 2305, 0, 10);  
				walkingemote(3067, 2772, 9554, 0, 4, 500);
				c.sendMessage("You jump the spikes!");
			}
			if (c.absX == 2772 && c.absY == 9553){
				c.getFrameMethodHandler().createNewTileObject(2772, 9551, 2305, 0, 10);  
				c.getFrameMethodHandler().createNewTileObject(2772, 9552, 2305, 0, 10); 
				walkingemote(3067, 2772, 9549, 0, -4, 500);
				c.sendMessage("You jump the spikes!");
			}
			if (c.absX == 2798 && c.absY == 9568){
				c.getFrameMethodHandler().createNewTileObject(2800, 9568, 2305, 0, 10);  
				c.getFrameMethodHandler().createNewTileObject(2799, 9568, 2305, 0, 10); 
				walkingemote(3067, 2802, 9568, 4, 0, 500);
				c.sendMessage("You jump the spikes!");
			}
			if (c.absX == 2801 && c.absY == 9568){
				c.getFrameMethodHandler().createNewTileObject(2800, 9568, 2305, 0, 10);  
				c.getFrameMethodHandler().createNewTileObject(2799, 9568, 2305, 0, 10); 
				walkingemote(3067, 2797, 9568, -4, 0, 500);
				c.sendMessage("You jump the spikes!");
			}    
			if (c.absX == 2761 && c.absY == 9583){
				c.getFrameMethodHandler().createNewTileObject(2761, 9584, 3567, 0, 10);  
				walkingemote(2750, 2761, 9587, 0, 4, 500);
				c.sendMessage("You jump the blade!");
			}
			if (c.absX == 2761 && c.absY == 9586){
				c.getFrameMethodHandler().createNewTileObject(2761, 9584, 3567, 0, 10);  
				walkingemote(2750, 2761, 9582, 0, -4, 500);
				c.sendMessage("You jump the blade!");
			}       
			if (c.absX == 2783 && c.absY == 9550){
				c.getFrameMethodHandler().createNewTileObject(2783, 9551, 3567, 0, 10);  
				walkingemote(2750, 2783, 9554, 0, 4, 500);
				c.sendMessage("You jump the blade!");
			}    
			if (c.absX == 2783 && c.absY == 9553){
				c.getFrameMethodHandler().createNewTileObject(2783, 9551, 3567, 0, 10);  
				walkingemote(2750, 2783, 9549, 0, -4, 500);
				c.sendMessage("You jump the blade!");
			}    
			if (c.absX == 2798 && c.absY == 9557){
				walkingemote(2750, 2802, 9557, 4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}       
			if (c.absX == 2801 && c.absY == 9557){
				walkingemote(2750, 2797, 9557, -4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}         
			if (c.absX == 2772 && c.absY == 9583){
				walkingemote(2750, 2772, 9587, 0, 4, 500);
				c.sendMessage("You hop over the pressure pads.");
			}        
			if (c.absX == 2772 && c.absY == 9586){
				walkingemote(2750, 2772, 9582, 0, -4, 500);
				c.sendMessage("You hop over the pressure pads.");
			}   

		}
	}
}

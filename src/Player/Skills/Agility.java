
public class Agility {
	private client c;

	public Agility(client input){
		this.c = input;
	}

	/**
	 * 
	 * @param x1 side 1 X
	 * @param y1 side 1 Y
	 * @param x2 side 2 X
	 * @param y2 side 2 Y
	 * @param emote Emote to use during obstacle
	 * @param level Required to use obstacle
	 * @param exp experience given, multiplied by rate
	 * @param isFast Set to True if running during emote
	 * @return
	 */
	public boolean agilityTeleport(int x1, int y1, int x2, int y2, int emote, int level, int exp, boolean dmg, int amount, String msg){
		if(c.playerLevel[c.playerAgility] >= level){
			int chance = c.playerLevel[c.playerAgility]-level;		
			if(c.absX == x1 && c.absY == y1){
				c.getClientMethodHandler().teleport(x2, y2);
				if(dmg && misc.random(chance) == 0){
					//					damagePlayer(playerId, misc.random(amount));
					//					sendMessage("You injure yourself.");
				}
				c.startAnimation(emote);
				return true;
			}
			if(c.absX == x2 && c.absY == y2){
				c.getClientMethodHandler().teleport(x1,y1);
				if(dmg && misc.random(chance) == 0){
					//					damagePlayer(playerId, misc.random(amount));
					//					sendMessage("You injure yourself.");
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
	public void agilityWalkTo(int x, int y){
		c.noClick = true;
		c.noClickTimeout = 10;
		c.shouldbeX = c.absX+x;
		c.shouldbeY = c.absY+y;
		c.getClientMethodHandler().walkTo(x,y);
	}

	/**
	 * 
	 * @param emote Emote to do until finished
	 * @param agilX2 Destination X
	 * @param agilY2 Destination Y
	 * @param exp Exp to give, multiplied by rate
	 * @param isFast set to True if you want to run
	 */
	public void walkingemote(int emote, int agilX2, int agilY2, int exp, boolean isFast){
		c.obstacle = System.currentTimeMillis();

		if(c.isRunning2) c.wasrunning = true;
		else c.wasrunning = false;

		if(isFast) c.isRunning2 = true;
		else c.isRunning2 = false;

		c.agilX = agilX2;
		c.agilY = agilY2;
		c.playerSEW = emote;
		c.playerSER = emote;
		int X = agilX2-c.absX;
		int Y = agilY2-c.absY;
		c.getClientMethodHandler().walkTo(X, Y);
		c.getClientMethodHandler().addSkillXP(exp*c.rate, 16);
	}

	public void walkingemote(int emote, int agilX2, int agilY2, int X, int Y, int exp){
		obstacle = System.currentTimeMillis();
		if (isRunning2 == true){
			isRunning2 = false;
			wasrunning = true;
		}
		agilX = agilX2;
		agilY = agilY2;
		playerSEW = emote;
		playerSER = emote;
		WalkTo(X, Y);
		addSkillXP(exp, 16);
	}

	public void walkingemote(int emote, int agilX2, int agilY2){
		obstacle = System.currentTimeMillis();
		if (isRunning2 == true){
			isRunning2 = false;
			wasrunning = true;
		}
		agilX = agilX2;
		agilY = agilY2;
		playerSEW = emote;
		playerSER = emote;
	}

	public void walkingemoterun(int emote, int agilX2, int agilY2, int X, int Y, int exp){
		obstacle = System.currentTimeMillis();
		if (isRunning2 == true){
			wasrunning = true;
		}
		else if (isRunning2 == false){
			wasrunning = false;
			isRunning2 = true;
		}
		playerSEW = emote;
		playerSER = emote;
		agilX = agilX2;
		agilY = agilY2;
		WalkTo(X, Y);
		addSkillXP(exp, 16);
	}


	/**
	 * 
	 * @param x1 side 1 X
	 * @param y1 side 1 Y
	 * @param x2 side 2 X
	 * @param y2 side 2 Y
	 * @param emote Emote to use during obstacle
	 * @param level Required to use obstacle
	 * @param exp experience given, multiplied by rate
	 * @param isFast Set to True if running during emote
	 * @return
	 */
	public boolean agilityObstacle(int x1, int y1, int x2, int y2, int emote, int level, int exp, boolean isFast, boolean dmg, int amount, String msg){
		if(c.playerLevel[c.playerAgility] >= level){
			int chance = c.playerLevel[c.playerAgility]-level;	
			if(c.absX == x1 && c.absY == y1){
				c.walkingemote(emote, x2, y2, exp, isFast);
				if(dmg && misc.random(chance) == 0){
					c.damagePlayer(c.playerId, misc.random(amount));
					c.sendMessage("You injure yourself.");
				}
				return true;
			}
			if(c.absX == x2 && c.absY == y2){
				c.walkingemote(emote, x1, y1, exp, isFast);
				if(dmg && misc.random(chance) == 0){
					c.damagePlayer(c.playerId, misc.random(amount));
					c.sendMessage("You injure yourself.");
				}
				return true;
			}
			return false;
		}
		else {
			c.sendMessage("You need "+level+" agility to do that.");
			return false;
		}
	}


	public static void agilityTimers(client playerClient){


		if (playerClient.absX == playerClient.agilX && playerClient.absY == playerClient.agilY){
			if (playerClient.wasrunning == true){
				playerClient.isRunning2 = true;
				playerClient.wasrunning = false;}
			else if (playerClient.wasrunning == false){
				playerClient.isRunning2 = false;
				playerClient.wasrunning = false;}
			else if (playerClient.running == true){
				playerClient.getClientMethodHandler().setAnimation(playerClient.runningemote);
				playerClient.isRunning2 = false;
				playerClient.running = false;
			}
			playerClient.playerSE = playerClient.GetStandAnim(playerClient.playerWeapon);
			playerClient.playerSEW = playerClient.GetWalkAnim(playerClient.playerWeapon);
			playerClient.playerSER = playerClient.GetRunAnim(playerClient.playerWeapon);
			playerClient.agilX = 0;
			playerClient.agilY = 0;
		}

		//Brimhaven Agility
		if(playerClient.getClientMethodHandler().isInArea(2753, 9535, 2814, 9600)){
			playerClient.ticket = System.currentTimeMillis();
			if (playerClient.absX == 2798 && playerClient.absY == 9579){
				playerClient.walkingemote3(2750, 2802, 9579, 4, 0, 500);
				playerClient.sendMessage("You hop over the pressure pads.");
			}       
			if (playerClient.absX == 2801 && playerClient.absY == 9579){
				playerClient.walkingemote3(2750, 2797, 9579, -4, 0, 500);
				playerClient.sendMessage("You hop over the pressure pads.");
			}           
			if (playerClient.absX == 2787 && playerClient.absY == 9579){
				playerClient.createNewTileObject(2788, 9579, 3567, 1, 10);  
				playerClient.walkingemote3(2750, 2791, 9579, 4, 0, 500);
				playerClient.sendMessage("You jump the blade!");
			}       
			if (playerClient.absX == 2790 && playerClient.absY == 9579){
				playerClient.createNewTileObject(2788, 9579, 3567, 1, 10);  
				playerClient.walkingemote3(2750, 2786, 9579, -4, 0, 500);
				playerClient.sendMessage("You jump the blade!");
			}           
			if (playerClient.absX == 2783 && playerClient.absY == 9575){
				if (System.currentTimeMillis() - playerClient.obstacle >= 3000) {
					playerClient.walkingemote(844, 2783, 9571);
					playerClient.WalkTo(0, -4);
					playerClient.addSkillXP(500, 16);
					playerClient.sendMessage("You carefully crawl underneath the spinning blade.");
				}  
			}
			if (playerClient.absX == 2783 && playerClient.absY == 9572){
				if (System.currentTimeMillis() - playerClient.obstacle >= 3000) {
					playerClient.walkingemote(844, 2783, 9577);
					playerClient.WalkTo(0, 4);
					playerClient.addSkillXP(500, 16);
					playerClient.sendMessage("You carefully crawl underneath the spinning blade.");
				}  
			}   
			if (playerClient.absX == 2779 && playerClient.absY == 9557){
				if (System.currentTimeMillis() - playerClient.obstacle >= 3000) {
					playerClient.walkingemote(844, 2775, 9557);
					playerClient.WalkTo(-4, 0);
					playerClient.addSkillXP(500, 16);
					playerClient.sendMessage("You carefully crawl underneath the spinning blade.");
				}
			}     
			if (playerClient.absX == 2776 && playerClient.absY == 9557){
				if (System.currentTimeMillis() - playerClient.obstacle >= 3000) {
					playerClient.walkingemote(844, 2780, 9557);
					playerClient.WalkTo(4, 0);
					playerClient.addSkillXP(500, 16);
					playerClient.sendMessage("You carefully crawl underneath the spinning blade.");
				}
			}     
			if (playerClient.absX == 2772 && playerClient.absY == 9550){
				playerClient.createNewTileObject(2772, 9551, 2305, 0, 10);  
				playerClient.createNewTileObject(2772, 9552, 2305, 0, 10);  
				playerClient.walkingemote3(3067, 2772, 9554, 0, 4, 500);
				playerClient.sendMessage("You jump the spikes!");
			}
			if (playerClient.absX == 2772 && playerClient.absY == 9553){
				playerClient.createNewTileObject(2772, 9551, 2305, 0, 10);  
				playerClient.createNewTileObject(2772, 9552, 2305, 0, 10); 
				playerClient.walkingemote3(3067, 2772, 9549, 0, -4, 500);
				playerClient.sendMessage("You jump the spikes!");
			}
			if (playerClient.absX == 2798 && playerClient.absY == 9568){
				playerClient.createNewTileObject(2800, 9568, 2305, 0, 10);  
				playerClient.createNewTileObject(2799, 9568, 2305, 0, 10); 
				playerClient.walkingemote3(3067, 2802, 9568, 4, 0, 500);
				playerClient.sendMessage("You jump the spikes!");
			}
			if (playerClient.absX == 2801 && playerClient.absY == 9568){
				playerClient.createNewTileObject(2800, 9568, 2305, 0, 10);  
				playerClient.createNewTileObject(2799, 9568, 2305, 0, 10); 
				playerClient.walkingemote3(3067, 2797, 9568, -4, 0, 500);
				playerClient.sendMessage("You jump the spikes!");
			}    
			if (playerClient.absX == 2761 && playerClient.absY == 9583){
				playerClient.createNewTileObject(2761, 9584, 3567, 0, 10);  
				playerClient.walkingemote3(2750, 2761, 9587, 0, 4, 500);
				playerClient.sendMessage("You jump the blade!");
			}
			if (playerClient.absX == 2761 && playerClient.absY == 9586){
				playerClient.createNewTileObject(2761, 9584, 3567, 0, 10);  
				playerClient.walkingemote3(2750, 2761, 9582, 0, -4, 500);
				playerClient.sendMessage("You jump the blade!");
			}       
			if (playerClient.absX == 2783 && playerClient.absY == 9550){
				playerClient.createNewTileObject(2783, 9551, 3567, 0, 10);  
				playerClient.walkingemote3(2750, 2783, 9554, 0, 4, 500);
				playerClient.sendMessage("You jump the blade!");
			}    
			if (playerClient.absX == 2783 && playerClient.absY == 9553){
				playerClient.createNewTileObject(2783, 9551, 3567, 0, 10);  
				playerClient.walkingemote3(2750, 2783, 9549, 0, -4, 500);
				playerClient.sendMessage("You jump the blade!");
			}    
			if (playerClient.absX == 2798 && playerClient.absY == 9557){
				playerClient.walkingemote3(2750, 2802, 9557, 4, 0, 500);
				playerClient.sendMessage("You hop over the pressure pads.");
			}       
			if (playerClient.absX == 2801 && playerClient.absY == 9557){
				playerClient.walkingemote3(2750, 2797, 9557, -4, 0, 500);
				playerClient.sendMessage("You hop over the pressure pads.");
			}         
			if (playerClient.absX == 2772 && playerClient.absY == 9583){
				playerClient.walkingemote3(2750, 2772, 9587, 0, 4, 500);
				playerClient.sendMessage("You hop over the pressure pads.");
			}        
			if (playerClient.absX == 2772 && playerClient.absY == 9586){
				playerClient.walkingemote3(2750, 2772, 9582, 0, -4, 500);
				playerClient.sendMessage("You hop over the pressure pads.");
			}   

		}
	}
}

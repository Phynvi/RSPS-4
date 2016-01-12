public class ClientMethodHandler {

	client c = null;

	public static ClientMethodHandler getClientMethodHandler(int playerIndex){
		client player = (client) server.playerHandler.players[playerIndex];
		if(player == null){
			System.out.println("[Error] : in getClientMethodHandler in ClientMethodHandler, given invalid player index");
			return null;
		}
		return player.getClientMethodHandler();
	}

	public ClientMethodHandler(client pc){
		this.c = pc;
	}

	public void sendEnergy() {
		c.getFrameMethodHandler().sendFrame126((int)c.runningEnergy + "%", 149);
	}

	public boolean isInPKZone(){
		if(c.isInArea(2583,3153,2606,3170))
			return true;
		//PK area, Istafdar, Tyras Camp
		if(c.isInArea(2363,3314,2392,3333))
			return true;

		if(c.isInArea(2169,3072,2366,3344) && !c.isInArea(2319,3150,2359,3194) && !c.isInArea(2174,3131,2201,3163) && !c.isInArea(2204,3257,2209,3259)) //pk camps
			return true;	

		//		if(isInArea(2111,4893,2160,4931) && !isInArea(2128,4904,2137,4910)) //old pk zone - draynor 
		//			return true;

		return false;
	}

	protected boolean canPlayersTeleportInThisArea(){
		if((c.isInArea(3644,2937,3716,3011) && c.pirate < 10) || isInPKZone() || c.isInArea(3455,9470,3522,9536) || (c.absX >=2002  && c.absX <=2035 && c.absY >=4814  && c.absY <=4833) || (c.absX >=3121  && c.absX <=3125 && c.absY >=3240  && c.absY <=3243) || (c.absX >=3138  && c.absX <=3186 && c.absY >=3718  && c.absY <=3748))
			return false;
		return true;
	}

	/**
	 * Does animation and gfx, until time is zero, then teleports to x and y
	 */
	public void isteleporting2(int gfx, int anim, int time, int x, int y, int h){
		c.getFrameMethodHandler().stillgfx(gfx, c.absY, c.absX);
		c.startAnimation(anim);
		c.isteleporting = time;
		c.isteleportingx = x;
		c.isteleportingy = y;
		c.ithl = h;
	}

	/**
	 * Will start an animation for time and then teleport to x,y,h
	 */
	public void isteleporting3(int anim, int time, int x, int y, int h){
		c.startAnimation(anim);
		c.isteleporting = time;
		c.isteleportingx = x;
		c.isteleportingy = y;
		c.ithl = h;
	}

	public void selectOptionTravel2(String question, String place1, int x1, int y1, String place2, int x2, int y2){
		c.travel2_X1 = x1; c.travel2_Y1 = y1; c.travel2_X2 = x2; c.travel2_Y2 = y2; c.travelHeight = 0;
		c.getFrameMethodHandler().sendFrame171(1, 2465);
		c.getFrameMethodHandler().sendFrame171(0, 2468);
		c.getFrameMethodHandler().sendFrame126(question, 2460);
		c.getFrameMethodHandler().sendFrame126(place1, 2461);
		c.getFrameMethodHandler().sendFrame126(place2, 2462);
		c.getFrameMethodHandler().sendFrame126("", 2463);
		c.getFrameMethodHandler().sendFrame164(2459);
	}

	public void selectOptionTravel2(String question, String place1, int x1, int y1, String place2, int x2, int y2, int finishHeight){
		selectOptionTravel2(question, place1, x1, y1, place2, x2, y2);
		c.travelHeight = finishHeight;
	}

	//agility walk to!

	public void walkTo(int x, int y) {
		c.newWalkCmdSteps = Math.abs(y)+Math.abs(x);
		if (c.newWalkCmdSteps % 1 != 0) c.newWalkCmdSteps /= 1;
		if (++c.newWalkCmdSteps > c.walkingQueueSize) {
			c.println("Warning: WalkTo(" + c.packetType + ") command contains too many steps (" + c.newWalkCmdSteps + ").");
			c.newWalkCmdSteps = 0;
		}
		int firstStepX = c.absX;
		int tmpFSX = firstStepX;
		firstStepX -= c.mapRegionX*8;
		for (int i = 1; i < c.newWalkCmdSteps; i++) {
			c.newWalkCmdX[i] = x;
			c.newWalkCmdY[i] = y;
			c.tmpNWCX[i] = c.newWalkCmdX[i];
			c.tmpNWCY[i] = c.newWalkCmdY[i];
		}
		c.newWalkCmdX[0] = c.newWalkCmdY[0] = c.tmpNWCX[0] = c.tmpNWCY[0] = 0;
		int firstStepY = c.absY;
		int tmpFSY = firstStepY;
		firstStepY -= c.mapRegionY*8;
		c.newWalkCmdIsRunning = ((c.inStream.readSignedByteC() == 1) && c.runningEnergy > 0);
		for (int i = 0; i < c.newWalkCmdSteps; i++) {
			c.newWalkCmdX[i] += firstStepX;
			c.newWalkCmdY[i] += firstStepY;
		}
	}

	/*NPC Talking*/
	public String getNpcName(int npcID) {
		if(server.npcHandler.npcList2.exists(npcID))
			return server.npcHandler.npcList2.getName();
		c.debug("NPC Name for "+npcID+" not found.");
		return null;
	}

	public void npcdialogue(int id, String ... lines){
		c.npcLines.clear();
		c.npcID = id;
		c.npcName = getNpcName(id);
		for(int i = 0; i < lines.length; i++)
			c.npcLines.add(lines[i]);
		c.npcChat();
	}	

	public void npcdialogue(String name, int id, String ... lines){
		c.npcLines.clear();
		c.npcID = id;
		c.npcName = name;
		for(int i = 0; i < lines.length; i++)
			c.npcLines.add(lines[i]);
		c.npcChat();
	}	


	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		if(exp >= 13034431) return 99;

		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 99;
	}


	public void levelup(int skill)
	{
		switch(skill)  
		{
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
			c.sendMessage("You have gained a level.");
			c.getFrameMethodHandler().stillgfx(199, c.absY, c.absX);
			if (c.playerLevel[skill] >= 99){
				c.masteries += 1;
				c.sendMessage("Congratulations on skill mastery. Skill capes and hoods are");
				c.sendMessage("available at their respective skill masters.");
			}
			break;
		}
	}

	public boolean addSkillXP(int amount, int skill){
		c.debug("You recieved "+amount+" exp in Skill "+skill);

		int[] playerXP = c.playerXP;
		int Attack = getLevelForXP(playerXP[0]); 
		int Defence = getLevelForXP(playerXP[1]);      
		int Strength = getLevelForXP(playerXP[2]);
		int Hitpoints = getLevelForXP(playerXP[3]);
		int Ranging = getLevelForXP(playerXP[4]);
		int Prayer = getLevelForXP(playerXP[5]);
		int Magic = getLevelForXP(playerXP[6]);
		int Cooking = getLevelForXP(playerXP[7]);
		int Woodcutting = getLevelForXP(playerXP[8]);
		int Fletching = getLevelForXP(playerXP[9]);
		int Fishing = getLevelForXP(playerXP[10]);
		int Firemaking = getLevelForXP(playerXP[11]);
		int Crafting = getLevelForXP(playerXP[12]);
		int Smithing = getLevelForXP(playerXP[13]);
		int Mining = getLevelForXP(playerXP[14]);
		int Herblore = getLevelForXP(playerXP[15]);
		int Agility = getLevelForXP(playerXP[16]);
		int Thieving = getLevelForXP(playerXP[17]);
		int Slayer = getLevelForXP(playerXP[18]);
		int Farming = getLevelForXP(playerXP[19]);
		int Runecrafting = getLevelForXP(playerXP[20]);
		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 2000000000) {
			c.sendMessage("Max XP value reached");
			return false;
		}

		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;

		if(oldLevel < getLevelForXP(playerXP[skill]))
			levelup(skill);
		c.playerLevel[skill] = getLevelForXP(playerXP[skill]);
		c.requirePlayerUpdate();

		c.getFrameMethodHandler().setSkillLevel(skill, c.playerLevel[skill], playerXP[skill]);
		c.getFrameMethodHandler().refreshSkills();
		return true;
	}

	/**
	 * Used to load the npcLines queue from dialogueHandler
	 * @param npcID
	 */
	public void startDialogue(int n){
		c.npcLines.clear();
		c.npcID = n;
		c.npcName = c.DIALOGUEHANDLER.getName();
		String[] lines = c.DIALOGUEHANDLER.getLines();
		for(int i = 0; i < lines.length; i++)
			c.npcLines.add(lines[i]);
		c.npcChat();
	}

	/**
	 * Heals player by amount
	 * @return True by default
	 */
	public boolean heal(int amount){
		c.NewHP += amount;
		if(c.NewHP > getLevelForXP(c.playerXP[3])) 
			c.NewHP = getLevelForXP(c.playerXP[3]);
		c.requirePlayerUpdate();
		return true;
	}

	/**
	 * Will present the option to travel to four locations
	 * @param optName Question at top of frame
	 * @param opt1 Option 1
	 * @param x1 Option 1 x coordinate
	 * @param y1 Option 2 y coordinate
	 */
	public void select4Options(String optName, String opt1, int x1, int y1, String opt2, int x2, int y2, String opt3, int x3, int y3, String opt4, int x4, int y4){
		c.optionsMenu = true;		c.oX1 = x1;		c.oX2 = x2;		c.oX3 = x3;		c.oX4 = x4;		c.oY1 = y1;		c.oY2 = y2;		c.oY3 = y3;		c.oY4 = y4;				
		c.getFrameMethodHandler().selectoption2(optName, opt1, opt2, opt3, opt4);		
	}


	/**
	 * Checks the skill with the level
	 * @return true if player has greater than or equal to
	 */
	public boolean checkLevel(int _skill, int _level){
		if(getLevelForXP(c.playerXP[_skill]) >= _level) 
			return true;
		return false;
	}

	public void deadreturn(){
		c.deadtele = 0;
		c.teleport(3024,3206,c.ithl);
		c.isteleporting = 0;
	}

}

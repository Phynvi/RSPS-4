
public class PestControlHandler {

	//pest control area - isInArea(2621, 2557, 2689, 2622)
	
	private int roundTimer = 0;
	private int timeUntilNextRound = 0;
	private boolean everyOther = false;
	
	public void disableTimers(){
		roundTimer = -1;
		timeUntilNextRound = -1;
	}
	
	public boolean isInPestControl(client c){
		return c.isInArea(2621, 2557, 2689, 2622);
	}
	
	public void shortTimers(){
		roundTimer = 15;
		timeUntilNextRound = 30;
	}
	
	private void sendPlayerBackToOutpostAndHeal(client c){
		c.ResetAttackNPC();
		c.NewHP = c.getLevelForXP(c.playerXP[c.playerHitpoints]);
		c.teleport(2657,2639);
		c.PRAY.disableAllPrayer();
	}
	
	public void process(){
		try{
		if(everyOther){
			everyOther = false;
			if(roundTimer > 0)
				roundTimer -= 1;		
			if(timeUntilNextRound > 0)
				timeUntilNextRound -= 1;
			if(roundTimer == 0){
				for(Player p : server.playerHandler.players){
					if(p == null) continue;
					client c = (client)p;
					if(c == null) continue;
					if(c.isInArea(2621, 2557, 2689, 2622)){ //in pest control
						sendPlayerBackToOutpostAndHeal(c);
						c.roundTimerFrameCreated = false;
						c.sendMessage("You are not awarded any pest control points.");
					}
				}
				if(portalsAlive < 4) resetPortals();
			}
			if(timeUntilNextRound == 0){
				for(Player p : server.playerHandler.players){
					if(p == null) continue;
					client c = (client)p;
					if(c == null) continue;
					if(c.isInArea(2660,2638,2663,2643)){ //on boat
						c.teleport(c.absX-4,c.absY-30);
					}
				}
				resetRoundTimers();
			}
			for(Player p : server.playerHandler.players){
				if(p == null) continue;
				client c = (client)p;
				if(c == null) continue;
				if(c.isInArea(2621, 2557, 2689, 2622)){ //in pest control
					if(!c.roundTimerFrameCreated){
						c.outStream.createFrame(208); 
						c.outStream.writeWordBigEndian_dup(11479);
						c.roundTimerFrameCreated = true;
					}
					c.sendQuest("Time left in this round: "+roundTimer+", Portals alive : "+portalsAlive, 11480);
				}
				if(c.isInArea(2660,2638,2663,2643)){ //on boat
					if(!c.roundTimerFrameCreated){
						c.outStream.createFrame(208); 
						c.outStream.writeWordBigEndian_dup(11479);
						c.roundTimerFrameCreated = true;
					}
					c.sendQuest("Time until next departure: "+timeUntilNextRound, 11480);
				}
			}
		}
		else everyOther = true;
		}
		catch(Exception e){
			System.out.println("[Error] in PestControlHandler : "+e.toString());
		}
	}
	
	private void resetRoundTimers(){
		roundTimer = 180+misc.random(30);
		timeUntilNextRound = roundTimer+30;
	}
	
	private void resetPortals(){
		if (portalsAlive == 4) return;
		for(int i = 0; i < portals.length; i++){
			if(server.npcHandler.npcs[portals[i]] != null){
				int old1 = server.npcHandler.npcs[portals[i]].npcType;
				int old2 = server.npcHandler.npcs[portals[i]].makeX;
				int old3 = server.npcHandler.npcs[portals[i]].makeY;
				int old4 = server.npcHandler.npcs[portals[i]].heightLevel;
				int old5 = server.npcHandler.npcs[portals[i]].moverangeX1;
				int old6 = server.npcHandler.npcs[portals[i]].moverangeY1;
				int old7 = server.npcHandler.npcs[portals[i]].moverangeX2;
				int old8 = server.npcHandler.npcs[portals[i]].moverangeY2;
				int old9 = server.npcHandler.npcs[portals[i]].walkingType;
				int old10 = server.npcHandler.npcs[portals[i]].MaxHP;
				server.npcHandler.npcs[portals[i]] = null;
				server.npcHandler.newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
			}
		}
		portalsAlive = 4;
	}
	
	public void portalRespawnChecks(int i){
		if(portalsAlive > 0){
			portalsAlive -= 1;
			portals[portalsAlive] = i;
			server.npcHandler.npcs[i].actionTimer = -1;
			server.npcHandler.npcs[i].NeedRespawn = true;
			server.npcHandler.npcs[i].updateRequired = true;
			server.npcHandler.npcs[i].animUpdateRequired = true;
			if (portalsAlive == 0){
				portalsAlive = 4;
				for(int j = 0; j < portals.length; j++){
					server.npcHandler.npcs[portals[j]].actionTimer = 20;
				}
				for(Player p : server.playerHandler.players){
					if(p == null) continue;
					client c = (client)p;
					if(c == null) continue;
					if(c.isInArea(2621, 2557, 2689, 2622)){ //in pest control
						sendPlayerBackToOutpostAndHeal(c);
						c.roundTimerFrameCreated = false;
						c.sendMessage("You are awarded 3 Pest Control Points.");
						c.pestControlPoints += 1;
						c.savemoreinfo();
					}
				}
				resetRoundTimers();
			}
		}
	}

	private int[] portals = new int[4];
	private int portalsAlive = 4;
	
	public int getPestControlRandomRespawnNPCIDAny(){
		return 3727+misc.random(3776-3727);
	}
	
	public int getPestControlRandomRespawnNPCID(int npcType){

		switch(npcType){
			
		case 3727: //Splatter
		case 3728: //Splatter
		case 3729: //Splatter
		case 3730: //Splatter
		case 3731: //Splatter
			return 3727+misc.random(4);
		
		case 3732: //shifter
		case 3733: //shifter
		case 3734: //shifter
		case 3735: //shifter
		case 3736: //shifter
		case 3737: //shifter
		case 3738: //shifter
		case 3739: //shifter
		case 3740: //shifter
		case 3741: //shifter
			return 3732+misc.random(9);
			
		case 3742: //Ravager
		case 3743: //Ravager
		case 3744: //Ravager
		case 3745: //Ravager
		case 3746: //Ravager
			return 3742+misc.random(4);
			
		case 3747: //Spinner	
		case 3748: //Spinner
		case 3749: //Spinner
		case 3750: //Spinner
		case 3751: //Spinner
			return 3747+misc.random(4);
			
		case 3752: //Torcher
		case 3753: //Torcher
		case 3754: //Torcher
		case 3755: //Torcher
		case 3756: //Torcher
		case 3757: //Torcher
		case 3758: //Torcher
		case 3759: //Torcher
		case 3760: //Torcher
		case 3761: //Torcher
			return 3752+misc.random(9);
			
		case 3762: //Defiler
		case 3763: //Defiler
		case 3764: //Defiler
		case 3765: //Defiler
		case 3766: //Defiler
		case 3767: //Defiler
		case 3768: //Defiler
		case 3769: //Defiler
		case 3770: //Defiler
		case 3771: //Defiler
			return 3762+misc.random(9);
			
		case 3772: //Brawler
		case 3773: //Brawler
		case 3774: //Brawler
		case 3775: //Brawler
		case 3776: //Brawler
			return 3772+misc.random(4);			
			
		default:
			return npcType;
		}
	}
	
	public void pestControlRandomRespawn(int npcIndex){
		if(server.npcHandler.npcs[npcIndex] == null) return;
		int old1 = getPestControlRandomRespawnNPCID(server.npcHandler.npcs[npcIndex].npcType);
		int old2 = server.npcHandler.npcs[npcIndex].makeX;
		int old3 = server.npcHandler.npcs[npcIndex].makeY;
		int old4 = server.npcHandler.npcs[npcIndex].heightLevel;
		int old5 = server.npcHandler.npcs[npcIndex].moverangeX1;
		int old6 = server.npcHandler.npcs[npcIndex].moverangeY1;
		int old7 = server.npcHandler.npcs[npcIndex].moverangeX2;
		int old8 = server.npcHandler.npcs[npcIndex].moverangeY2;
		int old9 = server.npcHandler.npcs[npcIndex].walkingType;
		int old10 = server.npcHandler.getHP(old1);
		server.npcHandler.npcs[npcIndex] = null;
		server.npcHandler.newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
		
	}
	
}

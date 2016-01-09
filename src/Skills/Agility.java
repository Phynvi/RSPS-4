
public class Agility {
	private client c;

	public Agility(client input){
		this.c = input;
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
				playerClient.setAnimation(playerClient.runningemote);
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
		if(playerClient.isInArea(2753, 9535, 2814, 9600)){
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


public class Agility {
	private client c;

	public Agility(client input){
		this.c = input;
	}

	public void agilityTimers(){


		if (c.absX == c.agilX && c.absY == c.agilY){
			if (c.wasrunning == true){
				c.isRunning2 = true;
				c.wasrunning = false;}
			else if (c.wasrunning == false){
				c.isRunning2 = false;
				c.wasrunning = false;}
			else if (c.running == true){
				c.setAnimation(c.runningemote);
				c.isRunning2 = false;
				c.running = false;
			}
			c.playerSE = c.GetStandAnim(c.playerWeapon);
			c.playerSEW = c.GetWalkAnim(c.playerWeapon);
			c.playerSER = c.GetRunAnim(c.playerWeapon);
			c.agilX = 0;
			c.agilY = 0;
		}

		//Brimhaven Agility
		if(c.isInArea(2753, 9535, 2814, 9600)){
			c.ticket = System.currentTimeMillis();
			if (c.absX == 2798 && c.absY == 9579){
				c.walkingemote3(2750, 2802, 9579, 4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}       
			if (c.absX == 2801 && c.absY == 9579){
				c.walkingemote3(2750, 2797, 9579, -4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}           
			if (c.absX == 2787 && c.absY == 9579){
				c.createNewTileObject(2788, 9579, 3567, 1, 10);  
				c.walkingemote3(2750, 2791, 9579, 4, 0, 500);
				c.sendMessage("You jump the blade!");
			}       
			if (c.absX == 2790 && c.absY == 9579){
				c.createNewTileObject(2788, 9579, 3567, 1, 10);  
				c.walkingemote3(2750, 2786, 9579, -4, 0, 500);
				c.sendMessage("You jump the blade!");
			}           
			if (c.absX == 2783 && c.absY == 9575){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					c.walkingemote(844, 2783, 9571);
					c.WalkTo(0, -4);
					c.addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}  
			}
			if (c.absX == 2783 && c.absY == 9572){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					c.walkingemote(844, 2783, 9577);
					c.WalkTo(0, 4);
					c.addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}  
			}   
			if (c.absX == 2779 && c.absY == 9557){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					c.walkingemote(844, 2775, 9557);
					c.WalkTo(-4, 0);
					c.addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}
			}     
			if (c.absX == 2776 && c.absY == 9557){
				if (System.currentTimeMillis() - c.obstacle >= 3000) {
					c.walkingemote(844, 2780, 9557);
					c.WalkTo(4, 0);
					c.addSkillXP(500, 16);
					c.sendMessage("You carefully crawl underneath the spinning blade.");
				}
			}     
			if (c.absX == 2772 && c.absY == 9550){
				c.createNewTileObject(2772, 9551, 2305, 0, 10);  
				c.createNewTileObject(2772, 9552, 2305, 0, 10);  
				c.walkingemote3(3067, 2772, 9554, 0, 4, 500);
				c.sendMessage("You jump the spikes!");
			}
			if (c.absX == 2772 && c.absY == 9553){
				c.createNewTileObject(2772, 9551, 2305, 0, 10);  
				c.createNewTileObject(2772, 9552, 2305, 0, 10); 
				c.walkingemote3(3067, 2772, 9549, 0, -4, 500);
				c.sendMessage("You jump the spikes!");
			}
			if (c.absX == 2798 && c.absY == 9568){
				c.createNewTileObject(2800, 9568, 2305, 0, 10);  
				c.createNewTileObject(2799, 9568, 2305, 0, 10); 
				c.walkingemote3(3067, 2802, 9568, 4, 0, 500);
				c.sendMessage("You jump the spikes!");
			}
			if (c.absX == 2801 && c.absY == 9568){
				c.createNewTileObject(2800, 9568, 2305, 0, 10);  
				c.createNewTileObject(2799, 9568, 2305, 0, 10); 
				c.walkingemote3(3067, 2797, 9568, -4, 0, 500);
				c.sendMessage("You jump the spikes!");
			}    
			if (c.absX == 2761 && c.absY == 9583){
				c.createNewTileObject(2761, 9584, 3567, 0, 10);  
				c.walkingemote3(2750, 2761, 9587, 0, 4, 500);
				c.sendMessage("You jump the blade!");
			}
			if (c.absX == 2761 && c.absY == 9586){
				c.createNewTileObject(2761, 9584, 3567, 0, 10);  
				c.walkingemote3(2750, 2761, 9582, 0, -4, 500);
				c.sendMessage("You jump the blade!");
			}       
			if (c.absX == 2783 && c.absY == 9550){
				c.createNewTileObject(2783, 9551, 3567, 0, 10);  
				c.walkingemote3(2750, 2783, 9554, 0, 4, 500);
				c.sendMessage("You jump the blade!");
			}    
			if (c.absX == 2783 && c.absY == 9553){
				c.createNewTileObject(2783, 9551, 3567, 0, 10);  
				c.walkingemote3(2750, 2783, 9549, 0, -4, 500);
				c.sendMessage("You jump the blade!");
			}    
			if (c.absX == 2798 && c.absY == 9557){
				c.walkingemote3(2750, 2802, 9557, 4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}       
			if (c.absX == 2801 && c.absY == 9557){
				c.walkingemote3(2750, 2797, 9557, -4, 0, 500);
				c.sendMessage("You hop over the pressure pads.");
			}         
			if (c.absX == 2772 && c.absY == 9583){
				c.walkingemote3(2750, 2772, 9587, 0, 4, 500);
				c.sendMessage("You hop over the pressure pads.");
			}        
			if (c.absX == 2772 && c.absY == 9586){
				c.walkingemote3(2750, 2772, 9582, 0, -4, 500);
				c.sendMessage("You hop over the pressure pads.");
			}   

		}
	}
}

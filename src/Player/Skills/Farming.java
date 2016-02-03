import java.awt.event.*;

import javax.swing.Timer;

public class Farming {

	private final int BUSHPATCH = 7573;
	private final int FLOWERPATCH = 7840;
	private final int HERBPATCH = 8132;
	private final int ALLOTMENT = 8573;
	private final int TREEPATCH = 8392;
			
	client playerClient;

	public Farming(client c){
		this.playerClient = c;
	}

	private String fullGrownMessage(){
		return "This plant looks healthy and ready to be picked.";
	}
	
	public void checkHealth(int originalID){ //when clicking check-health
		playerClient.debug("checkHealth called, ID "+originalID);
		if(lists.grownList.exists(originalID)){
			playerClient.sendMessage(fullGrownMessage());
			return;
		}
	}
	
	/**
	 * Returns the level required according to seedID
	 * @param seedID item ID of the seed to get the level of
	 * @return level requirement of that seed, -1 if seedID not found
	 */
	public int getSeedLevel(int seedID){
		switch(seedID){

		//trees
		case 5312: //oak
			return 50;
		case 5313: //willow
			return 70;
		case 5314: //maple
			return 80;
		case 5315: //yew
			return 90;
		case 5316: //magic
			return 95;
		//end trees
		
		//ALLOTMENT PLANTS
		case 5319: //onion
			return 0;
		case 5320: //sweetcorn
			return 15;
		case 5321: //watermelon
			return 30;
		case 5322: //tomato
			return 50;
		case 5323: //strawberry
			return 70;
		case 5324: //cabbage
			return 90;
		//END ALLOTMENT PLANTS
		
		//HERBS
		case 5291:
			return 0;
		case 5292:
			return 0;
		case 5293:
			return 10;
		case 5294:
			return 15;
		case 5295:
			return 25;
		case 5296:
			return 35;
		case 5297:
			return 40;
		case 5298:
			return 45;
		case 5299:
			return 55;
		case 5300:
			return 70;
		case 5301:
			return 75;
		case 5302:
			return 80;
		case 5303:
			return 85;
		case 5304:
			return 90;
		//END HERBS
	
		//FLOWERS
		case 5096: //marigold flower
			return 20;
		case 5097: //Rosemary flower
			return 35;
		case 5098: //nasturtium
			return 45;
		case 5099: //woad flower
			return 60;
		case 5100: //limpwurt flower
			return 75;
		//END FLOWERS
			
		//BUSHES	
		case 5102: //cadavaberry
			return 0;
		case 5103: //dwellberry
			return 15;
		case 5104: //jangerberry
			return 30;
		case 5105: //whiteberry
			return 50;
		case 5106: //poison ivy
			return 70; 
		case 5101: //redberry
			return 85;
		//END BUSHES
			
		}
		return -1;
	}

	public boolean checkTrowel(){
		if(playerClient.getInventoryHandler().IsItemInBag(5325))
			return true;
		playerClient.sendMessage("You need a garden trowel to do this.");
		return false;
	}
	
	public boolean checkRake(){
		if(playerClient.getInventoryHandler().IsItemInBag(5341)){
			playerClient.startAnimation(2273);
			return true;
		}
		playerClient.sendMessage("You need a rake to do this.");
		return false;
	}
	
	public boolean checkSpade(){
		if(playerClient.getInventoryHandler().IsItemInBag(952)){
			playerClient.startAnimation(2843);
			return true;
		}
		playerClient.sendMessage("You need a spade to do this.");
		return false;
	}
		
	public void guide(int x, int y, int originalID, int direction){ //when clicking on guide ... 
		playerClient.debug("Guide called, "+x+", "+y+" : ID "+originalID);
		if(lists.growingList.exists(originalID)){
			inspectInfo(originalID,x,y);
			return;
		}
		
		if(lists.grownList.exists(originalID) || lists.deadPlantList.exists(originalID)){
			Object o = server.globalObjectHandler.find(x, y);
			if(o instanceof FarmingObject){
				FarmingObject p = (FarmingObject) o;
				if(!lists.deadPlantList.exists(originalID)){ //means the plant is alive
					if(playerClient.getInventoryHandler().freeSlots() >= 1){
						playerClient.startAnimation(2292);
						
						int freeSpace = playerClient.getInventoryHandler().freeSlots();
						int amountToGive = p.getRemainingNumberOfItems();
						if(freeSpace < amountToGive)
							amountToGive = freeSpace;
						
						p.subtractTotalNumberOfItems(amountToGive);
						
						if(p.clearIfNecessary()) playerClient.getInventoryHandler().addItem(p.getSeedID(), misc.random(10)+1);	
					
						for(int i = 0; i < amountToGive; i++)
							playerClient.getInventoryHandler().addItem(p.getItemType(), 1);	

						playerClient.getClientMethodHandler().addSkillXP(p.getEXP()*playerClient.rate, 19);
					}
					else{
						playerClient.sendMessage("You need more inventory room to do that.");
						return;
					}
				}
				else{ //means the plant is dead
					if(checkRake()){
						p.clear();
					}
				}

			}
			else{
				System.out.println("[ERROR] : Object returned from list not of type plant");
			}
			return;
		}
		
		switch(originalID){
		
		case 8480:
		case 8501:
		case 8461:
		case 8534:
		case 8434:
			if(!checkSpade()) return;
			playerClient.getFrameMethodHandler().createNewTileObject(x, y, TREEPATCH, 2, 10);
			break;
			
		case 8391:
			if(!checkRake()) return;
			playerClient.getFrameMethodHandler().createNewTileObject(x, y, TREEPATCH, 2, 10);
			break;
		
		case 8549:
		case 8572:
		case 8594:
		case 8617:
		case 8640:
		case 8655:
		case 8686:
		case 8551:
		case 8552:
		case 8553:
		case 8554:
		case 8555:
		case 8556:
		case 8557:
		case 8550: //brush to clear for allotment /dead allotment plants
			if(!checkRake()) return;
			playerClient.getFrameMethodHandler().createNewTileObject(x, y, ALLOTMENT, 2, 10);
			break;
		
		case 7882:
		case 7914:
		case 7898:
		case 7934:
		case 7866:
		case 7847: //flower patch brush/dead flower
			if(!checkRake()) return;
				playerClient.getFrameMethodHandler().createNewTileObject(x, y, FLOWERPATCH, 2, 10);
			break;
			
		case 8149:	
		case 8150: //herb patch /dead herbs
			if(!checkRake()) return;
			playerClient.getFrameMethodHandler().createNewTileObject(x, y, HERBPATCH, 2, 10);
			break;
			
		case 7712:
		case 7604:
		case 7631:
		case 7661:
		case 7691:
		case 7742:
		case 7578: //bush brush to be cleared/dead bush
			if(!checkRake()) return;
				playerClient.getFrameMethodHandler().createNewTileObject(x, y, BUSHPATCH, 2, 10);
			break;
			
		case TREEPATCH:
			if(!checkTrowel()) return;
			for(int i = 5316; i >= 5312; i--){
				if(playerClient.getInventoryHandler().IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,TREEPATCH, direction);				
					return;
				}
			}
			break;
			
		case ALLOTMENT: //guide on allotment, chooses highest level seed first
			if(!checkTrowel()) return;
			for(int i = 5324; i >= 5319; i--){
				if(playerClient.getInventoryHandler().IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,ALLOTMENT, direction);				
					return;
				}
			}
			break;
			
		case HERBPATCH: //guide on empty herb patch, chooses highest level seed
			if(!checkTrowel()) return;
			for(int i = 5304; i >= 5291; i--){
				if(playerClient.getInventoryHandler().IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,HERBPATCH, direction);				
					return;
				}
			}
			break;
			
		case FLOWERPATCH: //guide on empty flower patch, chooses highest level seed in inventory
			if(!checkTrowel()) return;
			for(int i = 5100; i >= 5096; i--){
				if(playerClient.getInventoryHandler().IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,FLOWERPATCH, direction);				
					return;
				}
			}
			break;

		case BUSHPATCH: //guide on empty bush patch, automatically chooses highest level seed in inventory
			if(!checkTrowel()) return;
			if(playerClient.getInventoryHandler().IsItemInBag(5101) && playerClient.playerLevel[19] >= getSeedLevel(5101)){ //redberry
				grow(x,y,5101,BUSHPATCH, direction);				
				return;
			}
			if(playerClient.getInventoryHandler().IsItemInBag(5106) && playerClient.playerLevel[19] >= getSeedLevel(5106)){ //poison ivy
				grow(x,y,5106,BUSHPATCH, direction);				
				return;
			}
			if(playerClient.getInventoryHandler().IsItemInBag(5105) && playerClient.playerLevel[19] >= getSeedLevel(5105)){ //whiteberry
				grow(x,y,5105,BUSHPATCH, direction);				
				return;
			}
			if(playerClient.getInventoryHandler().IsItemInBag(5104) && playerClient.playerLevel[19] >= getSeedLevel(5104)){ //jangerberry seed
				grow(x,y,5104,BUSHPATCH, direction);				
				return;
			}
			if(playerClient.getInventoryHandler().IsItemInBag(5103) && playerClient.playerLevel[19] >= getSeedLevel(5103)){ //dwellberry seed
				grow(x,y,5103,BUSHPATCH, direction);				
				return;
			}				
			if(playerClient.getInventoryHandler().IsItemInBag(5102) && playerClient.playerLevel[19] >= getSeedLevel(5102)){ //cadavaberry seed
				grow(x,y,5102,BUSHPATCH, direction);		
				return;
			}				
			playerClient.sendMessage("You need seeds, of the correct level, to plant here.");
			return;
		}
	}

	public void inspectInfo(int _ID, int x, int y){
		playerClient.debug("inspectInfo called, ID "+_ID);
		if(lists.growingList.exists(_ID)){
			Object o = server.globalObjectHandler.find(x, y);
			if(o instanceof FarmingObject){
				FarmingObject p = (FarmingObject)o;
				playerClient.sendMessage("There appears to be about "+p.getRemainingTime()+" seconds left until the plant is fully grown.");
			}
			return;
		}
		if(lists.grownList.exists(_ID)){
			playerClient.sendMessage(fullGrownMessage());
			return;
		}
		
		if(lists.brushList.exists(_ID)){
			playerClient.sendMessage("I should use a rake on this to clear out this brush.");
			return;
		}
		
		if(lists.deadPlantList.exists(_ID)){
			playerClient.sendMessage("I should use a rake on this to clear out the dead plant.");
			return;
		}
		
		if(lists.patchList.exists(_ID)){
			playerClient.sendMessage("This looks ready to have seeds planted.");
			return;
		}
		
	}
	
	/**
	 * Private helper method to check minimum level requirement for farming
	 * Checks against playerLevel[19] (farming)
	 * Will return false if given a negative level value
	 * @param lvl Minimum level to check
	 * @return Returns true if the player satisfies the @param lvl requirement, false otherwise
	 */
	private boolean minLevel(int lvl){
		if(lvl < 0) return false;
		if(playerClient.playerLevel[19] < lvl){
			playerClient.sendMessage("You need at least "+lvl+" farming to do this.");
			return false;
		}
		return true;
	}
	
	/**
	 * Checks the player inventory for a gardening trowel and seeds
	 * @return True if they have a trowel and required seeds (trowel ID 5325)
	 */
	private boolean checkSeedsAndTrowel(int seedID){
		if(playerClient.getInventoryHandler().IsItemInBag(5325) && playerClient.getInventoryHandler().IsItemInBag(seedID))
			return true;
		playerClient.sendMessage("You need a gardening trowel and the correct seeds to do this.");
		return false;
	}
	
	/**
	 * Private helper method to halt brush respawn and delete seeds from player inventory
	 * Also starts farming animation (2272)
	 * @param seedID
	 */
	private void startGrowingProcess(int seedID, int totalTime){
		playerClient.startAnimation(2272);
		playerClient.getInventoryHandler().deleteItem(seedID, playerClient.getInventoryHandler().getItemSlot(seedID), 1);
		playerClient.sendMessage("This should be fully grown in about "+totalTime+" seconds.");
		playerClient.sendMessage("If the plant is not picked for about three minutes after its grown, it will die.");
	}
	
	public boolean checkSeedAndPatch(int seed, int patch){
		
		for(int i = 5312; i <= 5316; i++) //tree plants
			if(seed == i && patch == TREEPATCH) return true;
		
		for(int i = 5319; i <= 5324; i++) //checking allotment plants
			if(seed == i && patch == ALLOTMENT) return true;
		
		for(int i = 5291; i <= 5304; i++) //checking herbs
			if(seed == i && patch == HERBPATCH) return true;
		
		for(int i = 5096; i <= 5100; i++) //checking flowers
			if(seed == i && patch == FLOWERPATCH) return true;
		
		for(int i = 5101; i <= 5106; i++) //checking bushes
			if(seed == i && patch == BUSHPATCH) return true;
		
		playerClient.sendMessage("That is not the correct type of seed for this patch.");
		return false;
	}

	public void grow(int x, int y, int seedID, int objectID, int direction){ //starts the growing process
		playerClient.debug("Grow called, "+x+", "+y+" : seedID "+seedID);
		if(!checkSeedsAndTrowel(seedID)) return;
		if(!minLevel(getSeedLevel(seedID))) return; //if given an invalid ID, getSeedLevel returns -1, which sets minLevel false, exiting the function
		if(!checkSeedAndPatch(seedID,objectID)) return;
		int startH = 8140; //for herbs
		int endH = 8143; //for herbs
		int deadH = 8149; //for herbs
		switch(seedID){
		//arguments for bush constructor:
		//x coord, y coord, seedID, experience, numb of seeds returned, seconds between stages, beginning stage, ending stage, dead stage, cleared patch ID, item ID, number of random to distribute
		
		//Trees
		case 5312:
			new FarmingObject(x,y,seedID,1000,10,20,8462,8467,8480,TREEPATCH,1521,10,300, direction, playerClient.playerName);
			break;
		case 5313:
			new FarmingObject(x,y,seedID,2000,14,25,8481,8488,8501,TREEPATCH,1519,12,300, direction, playerClient.playerName);
			break;
		case 5314:
			new FarmingObject(x,y,seedID,3000,16,25,8435,8444,8461,TREEPATCH,1517,15,300, direction, playerClient.playerName);
			break;
		case 5315:
			new FarmingObject(x,y,seedID,4000,18,20,8502,8513,8534,TREEPATCH,1515,17,300, direction, playerClient.playerName);
			break;
		case 5316:
			new FarmingObject(x,y,seedID,5000,25,20,8396,8409,8434,TREEPATCH,1513,20,300, direction, playerClient.playerName);
			break;			
			//End trees
		
		//Allotment plants
		case 5319: //Onion
			new FarmingObject(x,y,seedID,100,4,30,8580,8584,8594,ALLOTMENT,1957,5,180, direction, playerClient.playerName);
			break;
		case 5320: //Sweetcorn
			new FarmingObject(x,y,seedID,200,5,30,8618,8624,8640,ALLOTMENT,5986,6,180, direction, playerClient.playerName);
			break;
		case 5321: //watermelon
			new FarmingObject(x,y,seedID,400,6,30,8656,8664,8686,ALLOTMENT,5982,6,180, direction, playerClient.playerName);
			break;
		case 5322: //tomato
			new FarmingObject(x,y,seedID,600,7,40,8641,8645,8655,ALLOTMENT,1982,6,180, direction, playerClient.playerName);
			break;
		case 5323: //strawberry
			new FarmingObject(x,y,seedID,1000,8,40,8595,8601,8617,ALLOTMENT,5504,5,180, direction, playerClient.playerName);
			break;
		case 5324: //cabbage
			new FarmingObject(x,y,seedID,1200,10,35,8535,8539,8549,ALLOTMENT,1965,4,180, direction, playerClient.playerName);
			break;
		//End Allotment plants
		
		//Herbs		
		case 5291: //guam
			new FarmingObject(x,y,seedID,50,2,6,startH,endH,deadH,HERBPATCH,199,3,75, direction, playerClient.playerName);
			break;
		case 5292: //Marrentill
			new FarmingObject(x,y,seedID,75,3,10,startH,endH,deadH,HERBPATCH,201,3,75, direction, playerClient.playerName);
			break;
		case 5293: //Tarromin
			new FarmingObject(x,y,seedID,100,4,12,startH,endH,deadH,HERBPATCH,203,3,75, direction, playerClient.playerName);
			break;
		case 5294: //Harralander
			new FarmingObject(x,y,seedID,125,5,14,startH,endH,deadH,HERBPATCH,205,3,75, direction, playerClient.playerName);
			break;
		case 5295: //Ranarr
			new FarmingObject(x,y,seedID,175,4,18,startH,endH,deadH,HERBPATCH,207,4,75, direction, playerClient.playerName);
			break;
		case 5296: //Toadflax
			new FarmingObject(x,y,seedID,225,5,22,startH,endH,deadH,HERBPATCH,209,4,75, direction, playerClient.playerName);
			break;
		case 5297: //Irit
			new FarmingObject(x,y,seedID,250,5,24,startH,endH,deadH,HERBPATCH,211,4,75, direction, playerClient.playerName);
			break;
		case 5298: //Avantoe
			new FarmingObject(x,y,seedID,275,5,26,startH,endH,deadH,HERBPATCH,213,5,75, direction, playerClient.playerName);
			break;
		case 5299: //Kwuarm
			new FarmingObject(x,y,seedID,325,5,30,startH,endH,deadH,HERBPATCH,215,5,75, direction, playerClient.playerName);
			break;
		case 5300: //snapdragon
			new FarmingObject(x,y,seedID,425,6,36,startH,endH,deadH,HERBPATCH,217,5,75, direction, playerClient.playerName);
			break;
		case 5301: //cadantine
			new FarmingObject(x,y,seedID,475,7,38,startH,endH,deadH,HERBPATCH,219,5,75, direction, playerClient.playerName);
			break;
		case 5302: //lantadyme
			new FarmingObject(x,y,seedID,500,8,42,startH,endH,deadH,HERBPATCH,1531,5,75, direction, playerClient.playerName);
			break;
		case 5303: //Dwarf weed
			new FarmingObject(x,y,seedID,550,8,48,startH,endH,deadH,HERBPATCH,1529,6,75, direction, playerClient.playerName);
			break;
		case 5304: //Torstol
			new FarmingObject(x,y,seedID,625,12,40,startH,endH,deadH,HERBPATCH,1533,6,75, direction, playerClient.playerName);
			break;
		
		//End of Herbs
		
		//Flowers
		case 5100: //Limpwurt
			new FarmingObject(x,y,seedID,750,10,30,7851,7855,7866,FLOWERPATCH,225,4,60, direction, playerClient.playerName);
			break;
		case 5099: //Woad
			new FarmingObject(x,y,seedID,525,7,24,7919,7923,7934,FLOWERPATCH, 1793,4,60, direction, playerClient.playerName);
			break;
		case 5098: //Nasturtium
			new FarmingObject(x,y,seedID,375,6,20,7883,7887,7898,FLOWERPATCH, 6012,4,60, direction, playerClient.playerName);
			break;
		case 5097: //Rosemary Flower
			new FarmingObject(x,y,seedID,250,4,17,7899,7903,7914,FLOWERPATCH, 6014,4,60, direction, playerClient.playerName);
			break;
		case 5096: //marigold flower
			new FarmingObject(x,y,seedID,200,3,15,7867,7871,7882,FLOWERPATCH, 6010,4,60, direction, playerClient.playerName);
			break;
		//End Flowers	
			
		//Bushes	
		case 5101: //redberry
			new FarmingObject(x,y,seedID,2500,10,20,7692,7701,7712,BUSHPATCH,1951,4,180, direction, playerClient.playerName);
			break;
		case 5106: //poison ivy
			new FarmingObject(x,y,seedID,1600,14,20,7662,7674,7691,BUSHPATCH,6018,4,180, direction, playerClient.playerName);
			break;
		case 5105: //whiteberry
			new FarmingObject(x,y,seedID,1200, 12, 21, 7713, 7725, 7742,BUSHPATCH,239,4,180, direction, playerClient.playerName);
			break;
		case 5104: //Jangerberry bush
			new FarmingObject(x,y,seedID, 800, 10, 22, 7632, 7644, 7661,BUSHPATCH,247,4,180, direction, playerClient.playerName);
			break;
		case 5103: //dwellberry bush
			new FarmingObject(x, y, seedID, 650, 7, 20, 7605, 7616, 7631,BUSHPATCH,2126,4,180, direction, playerClient.playerName);
			break;
		case 5102: //cadavaberry bush
			new FarmingObject(x,y, seedID, 400, 10, 19, 7581, 7591, 7604,BUSHPATCH,753,4,180, direction, playerClient.playerName);
			break;
		//End Bushes	

		default: //using an item not listed on a patch
			playerClient.sendMessage("Nothing interesting happens.");
			break;
		}
		int totalTime = ((FarmingObject)server.globalObjectHandler.find(x, y)).getRemainingTime();
		startGrowingProcess(seedID,totalTime);
	}
	

}




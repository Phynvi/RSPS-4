import java.awt.event.*;

import javax.swing.Timer;

public class Farming {

	private final int BUSHPATCH = 7573;
	private final int FLOWERPATCH = 7840;
	private final int HERBPATCH = 8132;
	private final int ALLOTMENT = 8573;
	private final int TREEPATCH = 8392;
			
	client playerClient;
	public PlantList<plant> plantList = new PlantList<plant>();

	public Farming(client c){
		this.playerClient = c;
	}

	private String fullGrownMessage(){
		return "This plant looks healthy and ready to be picked.";
	}
	
	public void checkHealth(int originalID){ //when clicking check-health
		if(playerClient.debugmode) System.out.println("checkHealth called, ID "+originalID);
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
		if(playerClient.IsItemInBag(5325))
			return true;
		playerClient.sendMessage("You need a garden trowel to do this.");
		return false;
	}
	
	public boolean checkRake(){
		if(playerClient.IsItemInBag(5341)){
			playerClient.startAnimation(2273);
			return true;
		}
		playerClient.sendMessage("You need a rake to do this.");
		return false;
	}
	
	public boolean checkSpade(){
		if(playerClient.IsItemInBag(952)){
			playerClient.startAnimation(2843);
			return true;
		}
		playerClient.sendMessage("You need a spade to do this.");
		return false;
	}
	
	
	public void guide(int x, int y, int originalID){ //when clicking on guide ... 
		if(playerClient.debugmode) System.out.println("Guide called, "+x+", "+y+" : ID "+originalID);
		if(lists.growingList.exists(originalID)){
			inspectInfo(originalID,x,y);
			return;
		}
		
		if(lists.grownList.exists(originalID) || lists.deadPlantList.exists(originalID)){
			Object o = plantList.find(x, y);
			if(o instanceof plant){
				plant p = (plant)o;
				if(!lists.deadPlantList.exists(originalID)){ //means the plant is alive
					int numbItems = p.numberOfItems()+1;
					if(playerClient.freeSlots() >= numbItems){
						plantList.removeCurrent();
						p.stop();
						playerClient.startAnimation(2292);
						int randDist = misc.random(p.amountSeeds())+2; //seed distribution
						playerClient.addItem(p.seed(), randDist);	
						int randItem = misc.random(numbItems);
						if(numbItems > 10 && randItem < 10) //means tree
							randItem+= 5;
						for(int i = 0; i < randItem; i++)
							playerClient.addItem(p.getItem(), 1);
						playerClient.addSkillXP(p.EXP()*playerClient.rate, 19);
						playerClient.createNewTileObject(x, y, p.clearID(), 2, 10); 
					}
					else{
						playerClient.sendMessage("You need at least "+(p.numberOfItems()+1)+" empty inventory slots to do this.");
						return;
					}
				}
				else{ //means the plant is dead
					if(checkRake()){
						plantList.removeCurrent();
						p.stop();
						playerClient.createNewTileObject(x, y, p.clearID(), 2, 10); 
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
			playerClient.createNewTileObject(x, y, TREEPATCH, 2, 10);
			break;
			
		case 8391:
			if(!checkRake()) return;
			playerClient.createNewTileObject(x, y, TREEPATCH, 2, 10);
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
			playerClient.createNewTileObject(x, y, ALLOTMENT, 2, 10);
			break;
		
		case 7882:
		case 7914:
		case 7898:
		case 7934:
		case 7866:
		case 7847: //flower patch brush/dead flower
			if(!checkRake()) return;
				playerClient.createNewTileObject(x, y, FLOWERPATCH, 2, 10);
			break;
			
		case 8149:	
		case 8150: //herb patch /dead herbs
			if(!checkRake()) return;
			playerClient.createNewTileObject(x, y, HERBPATCH, 2, 10);
			break;
			
		case 7712:
		case 7604:
		case 7631:
		case 7661:
		case 7691:
		case 7742:
		case 7578: //bush brush to be cleared/dead bush
			if(!checkRake()) return;
				playerClient.createNewTileObject(x, y, BUSHPATCH, 2, 10);
			break;
			
		case TREEPATCH:
			if(!checkTrowel()) return;
			for(int i = 5316; i >= 5312; i--){
				if(playerClient.IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,TREEPATCH);				
					return;
				}
			}
			break;
			
		case ALLOTMENT: //guide on allotment, chooses highest level seed first
			if(!checkTrowel()) return;
			for(int i = 5324; i >= 5319; i--){
				if(playerClient.IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,ALLOTMENT);				
					return;
				}
			}
			break;
			
		case HERBPATCH: //guide on empty herb patch, chooses highest level seed
			if(!checkTrowel()) return;
			for(int i = 5304; i >= 5291; i--){
				if(playerClient.IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,HERBPATCH);				
					return;
				}
			}
			break;
			
		case FLOWERPATCH: //guide on empty flower patch, chooses highest level seed in inventory
			if(!checkTrowel()) return;
			for(int i = 5100; i >= 5096; i--){
				if(playerClient.IsItemInBag(i) && playerClient.playerLevel[19] >= getSeedLevel(i)){ // double conditional needed to avoid repeated level messages
					grow(x,y,i,FLOWERPATCH);				
					return;
				}
			}
			break;

		case BUSHPATCH: //guide on empty bush patch, automatically chooses highest level seed in inventory
			if(!checkTrowel()) return;
			if(playerClient.IsItemInBag(5101) && playerClient.playerLevel[19] >= getSeedLevel(5101)){ //redberry
				grow(x,y,5101,BUSHPATCH);				
				return;
			}
			if(playerClient.IsItemInBag(5106) && playerClient.playerLevel[19] >= getSeedLevel(5106)){ //poison ivy
				grow(x,y,5106,BUSHPATCH);				
				return;
			}
			if(playerClient.IsItemInBag(5105) && playerClient.playerLevel[19] >= getSeedLevel(5105)){ //whiteberry
				grow(x,y,5105,BUSHPATCH);				
				return;
			}
			if(playerClient.IsItemInBag(5104) && playerClient.playerLevel[19] >= getSeedLevel(5104)){ //jangerberry seed
				grow(x,y,5104,BUSHPATCH);				
				return;
			}
			if(playerClient.IsItemInBag(5103) && playerClient.playerLevel[19] >= getSeedLevel(5103)){ //dwellberry seed
				grow(x,y,5103,BUSHPATCH);				
				return;
			}				
			if(playerClient.IsItemInBag(5102) && playerClient.playerLevel[19] >= getSeedLevel(5102)){ //cadavaberry seed
				grow(x,y,5102,BUSHPATCH);		
				return;
			}				
			playerClient.sendMessage("You need seeds, of the correct level, to plant here.");
			return;
		}
	}

	public void inspectInfo(int _ID, int x, int y){
		if(playerClient.debugmode) System.out.println("inspectInfo called, ID "+_ID);
		if(lists.growingList.exists(_ID)){
			Object o = plantList.find(x, y);
			if(o instanceof plant){
				plant p = (plant)o;
				playerClient.sendMessage("There appears to be about "+p.remainingTime()+" seconds left until the plant is fully grown.");
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
		if(playerClient.IsItemInBag(5325) && playerClient.IsItemInBag(seedID))
			return true;
		playerClient.sendMessage("You need a gardening trowel and the correct seeds to do this.");
		return false;
	}
	
	/**
	 * Private helper method to halt brush respawn and delete seeds from player inventory
	 * Also starts farming animation (2272)
	 * @param seedID
	 */
	private void startGrowingProcess(int seedID){
		playerClient.startAnimation(2272);
		playerClient.deleteItem(seedID, playerClient.getItemSlot(seedID), 1);
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

	public void grow(int x, int y, int seedID, int objectID){ //starts the growing process
		if(playerClient.debugmode) System.out.println("Grow called, "+x+", "+y+" : seedID "+seedID);
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
			new Plant2(x,y,seedID,1000,10,20,8462,8467,8480,TREEPATCH,1521,10,300);
			break;
		case 5313:
			new Plant2(x,y,seedID,2000,14,25,8481,8488,8501,TREEPATCH,1519,12,300);
			break;
		case 5314:
			new Plant2(x,y,seedID,3000,16,25,8435,8444,8461,TREEPATCH,1517,15,300);
			break;
		case 5315:
			new Plant2(x,y,seedID,4000,18,20,8502,8513,8534,TREEPATCH,1515,17,300);
			break;
		case 5316:
			new Plant2(x,y,seedID,5000,25,20,8396,8409,8434,TREEPATCH,1513,20,300);
			break;			
			//End trees
		
		//Allotment plants
		case 5319: //Onion
			new Plant2(x,y,seedID,100,4,30,8580,8584,8594,ALLOTMENT,1957,5,180);
			break;
		case 5320: //Sweetcorn
			new Plant2(x,y,seedID,200,5,30,8618,8624,8640,ALLOTMENT,5986,6,180);
			break;
		case 5321: //watermelon
			new Plant2(x,y,seedID,400,6,30,8656,8664,8686,ALLOTMENT,5982,6,180);
			break;
		case 5322: //tomato
			new Plant2(x,y,seedID,600,7,40,8641,8645,8655,ALLOTMENT,1982,6,180);
			break;
		case 5323: //strawberry
			new Plant2(x,y,seedID,1000,8,40,8595,8601,8617,ALLOTMENT,5504,5,180);
			break;
		case 5324: //cabbage
			new Plant2(x,y,seedID,1200,10,35,8535,8539,8549,ALLOTMENT,1965,4,180);
			break;
		//End Allotment plants
		
		//Herbs		
		case 5291: //guam
			new Plant2(x,y,seedID,50,2,6,startH,endH,deadH,HERBPATCH,199,3,75);
			break;
		case 5292: //Marrentill
			new Plant2(x,y,seedID,75,3,10,startH,endH,deadH,HERBPATCH,201,3,75);
			break;
		case 5293: //Tarromin
			new Plant2(x,y,seedID,100,4,12,startH,endH,deadH,HERBPATCH,203,3,75);
			break;
		case 5294: //Harralander
			new Plant2(x,y,seedID,125,5,14,startH,endH,deadH,HERBPATCH,205,3,75);
			break;
		case 5295: //Ranarr
			new Plant2(x,y,seedID,175,4,18,startH,endH,deadH,HERBPATCH,207,4,75);
			break;
		case 5296: //Toadflax
			new Plant2(x,y,seedID,225,5,22,startH,endH,deadH,HERBPATCH,209,4,75);
			break;
		case 5297: //Irit
			new Plant2(x,y,seedID,250,5,24,startH,endH,deadH,HERBPATCH,211,4,75);
			break;
		case 5298: //Avantoe
			new Plant2(x,y,seedID,275,5,26,startH,endH,deadH,HERBPATCH,213,5,75);
			break;
		case 5299: //Kwuarm
			new Plant2(x,y,seedID,325,5,30,startH,endH,deadH,HERBPATCH,215,5,75);
			break;
		case 5300: //snapdragon
			new Plant2(x,y,seedID,425,6,36,startH,endH,deadH,HERBPATCH,217,5,75);
			break;
		case 5301: //cadantine
			new Plant2(x,y,seedID,475,7,38,startH,endH,deadH,HERBPATCH,219,5,75);
			break;
		case 5302: //lantadyme
			new Plant2(x,y,seedID,500,8,42,startH,endH,deadH,HERBPATCH,1531,5,75);
			break;
		case 5303: //Dwarf weed
			new Plant2(x,y,seedID,550,8,48,startH,endH,deadH,HERBPATCH,1529,6,75);
			break;
		case 5304: //Torstol
			new Plant2(x,y,seedID,625,12,40,startH,endH,deadH,HERBPATCH,1533,6,75);
			break;
		
		//End of Herbs
		
		//Flowers
		case 5100: //Limpwurt
			new Plant2(x,y,seedID,750,10,30,7851,7855,7866,FLOWERPATCH,225,4,60);
			break;
		case 5099: //Woad
			new Plant2(x,y,seedID,525,7,24,7919,7923,7934,FLOWERPATCH, 1793,4,60);
			break;
		case 5098: //Nasturtium
			new Plant2(x,y,seedID,375,6,20,7883,7887,7898,FLOWERPATCH, 6012,4,60);
			break;
		case 5097: //Rosemary Flower
			new Plant2(x,y,seedID,250,4,17,7899,7903,7914,FLOWERPATCH, 6014,4,60);
			break;
		case 5096: //marigold flower
			new Plant2(x,y,seedID,200,3,15,7867,7871,7882,FLOWERPATCH, 6010,4,60);
			break;
		//End Flowers	
			
		//Bushes	
		case 5101: //redberry
			new Plant2(x,y,seedID,2500,10,20,7692,7701,7712,BUSHPATCH,1951,4,180);
			break;
		case 5106: //poison ivy
			new Plant2(x,y,seedID,1600,14,20,7662,7674,7691,BUSHPATCH,6018,4,180);
			break;
		case 5105: //whiteberry
			new Plant2(x,y,seedID,1200, 12, 21, 7713, 7725, 7742,BUSHPATCH,239,4,180);
			break;
		case 5104: //Jangerberry bush
			new Plant2(x,y,seedID, 800, 10, 22, 7632, 7644, 7661,BUSHPATCH,247,4,180);
			break;
		case 5103: //dwellberry bush
			new Plant2(x, y, seedID, 650, 7, 20, 7605, 7616, 7631,BUSHPATCH,2126,4,180);
			break;
		case 5102: //cadavaberry bush
			new Plant2(x,y, seedID, 400, 10, 19, 7581, 7591, 7604,BUSHPATCH,753,4,180);
			break;
		//End Bushes	

		default: //using an item not listed on a patch
			playerClient.sendMessage("Nothing interesting happens.");
			break;
		}
		startGrowingProcess(seedID);
	}


	private abstract class plant{
		public abstract void stop();
		public abstract Integer seed();
		public abstract Integer EXP();
		public abstract Integer amountSeeds();
		public abstract Integer tick();
		public abstract Integer clearID();
		public abstract Integer getX();
		public abstract Integer getY();
		public abstract Integer getItem();
		public abstract Integer numberOfItems();
		public abstract Integer remainingTime();

		/**
		 * Will refresh the object by creating another tile of it
		 */
		public abstract void update();
	}

	private class Plant2 extends plant{

		protected Timer plantT;

		protected int _x, _y, _seed, _EXP, _amountSeeds, _tick, objectStage, finishedStage, deadID, _clearID, _itemID, numbItem, _deadTime;
		protected RespawnObject re; 

		/**
		 * Creates a bush object, which will countdown through the bush's stages until fully grown.
		 * Will add the object to plant list by X and Y coords
		 * @param x X coordinate of the object
		 * @param y Y coordinate of the object
		 * @param seed Type of seed used
		 * @param EXP Experience to be distributed
		 * @param amountSeeds Number of seeds given back when fully grown, randomized 0-amountSeeds + 2
		 * @param tick Number of seconds between plant stages
		 * @param startStage Beginning object ID of growing process
		 * @param endStage Last object ID of growing process
		 * @param dID the dead object ID of the plant
		 * @param cID the cleared object after plant is picked
		 * @param deadTime The amount of time it takes for a fully grown plant to die
		 */
		public Plant2(int x, int y, int seed, int EXP, int amountSeeds, int tick, int startStage, int endStage, int dID, int cID, int itemID, int n, int deadTime){
			_seed = seed; _EXP = EXP; _amountSeeds = amountSeeds;	_tick = tick;	_x = x;	_y = y; objectStage = startStage; finishedStage = endStage; deadID = dID; _clearID = cID; _itemID = itemID; numbItem = n; _deadTime = deadTime;//setters
			plantT = new Timer(1000*tick(), new time()); //counter goes off every tick seconds
			plantT.start();
			int totalTime = (endStage-startStage)*tick;
			playerClient.createNewTileObject(_x, _y, objectStage, 2, 10); 
			playerClient.sendMessage("This should take about "+totalTime+" seconds to be fully grown.");
		}

		@Override
		public Integer remainingTime() {
			int totalTime = (finishedStage-objectStage)*_tick;
			return totalTime;
		}
		
		public void add(){
			plantList.add(_x, _y, this);
			if(playerClient.debugmode)
				System.out.println("Plant added with following coords: x - "+_x+", y -"+_y);
		}

		@Override
		public void stop(){
			if(plantT != null)
				plantT.stop();
			if(re != null)
				re.haltRespawn();
		}
		
		private class time implements ActionListener{
			
			public time(){
				add();
			}

			@Override
			public void actionPerformed(ActionEvent e) { 
				
				if(objectStage != finishedStage){
					playerClient.createNewTileObject(_x, _y, objectStage, 2, 10); 
					objectStage += 1;
				}
				else{
					if(objectStage == finishedStage)
						plantT.stop();
					re = new RespawnObject(playerClient, deadID, objectStage, _x, _y, _deadTime);
				}
				
			}

		}

		@Override
		public void update() {
				//System.out.println("Update called on plant with coords : "+getX()+", "+getY());
			if(re == null) //if re does not exist
					playerClient.createNewTileObject(_x, _y, objectStage, 2, 10); 
			else{ //re exists
				if(!re.done) //if re is still counting down
					playerClient.createNewTileObject(_x, _y, objectStage, 2, 10);
				else //means re is done, and dead plant
					playerClient.createNewTileObject(_x, _y, deadID, 2, 10);
			}
		}
		
		//Getters
		@Override
		public Integer seed() {
			return _seed;
		}

		@Override
		public Integer EXP() {
			return _EXP;
		}

		@Override
		public Integer amountSeeds() {
			return _amountSeeds;
		}

		@Override
		public Integer tick() {
			return _tick;
		}
		@Override
		public Integer clearID() {
			return _clearID;
		}
		@Override
		public Integer getX() {
			return _x;
		}
		@Override
		public Integer getY() {
			return _y;
		}
		@Override
		public Integer getItem() {
			return _itemID;
		}
		@Override
		public Integer numberOfItems() {
			return numbItem;
		}

	}	
	
	

	public class PlantList<E>{

		private Node<E> _first;
		private Node<E> _last;
		private Node<E> _current;
		private int _manyItems;

		/** 
		 * Generic Node, identified by x and y coords
		 * @param <K> type to store
		 */
		private class Node<K>{
			Node<K> _prev;
			Node<K> _next;
			K data;
			int _x, _y;
			/**
			 * Constructor
			 * @param x - X coords
			 * @param y - Y Coords
			 * @param element - element at these coordinates
			 */
			public Node(int x, int y, K element){
				_x = x;
				_y = y;
				data = element;
			}
		}

		/**
		 * Creates a empty list
		 */
		public PlantList(){
			_manyItems = 0;
			Node<E> dummyFirst = new Node<E>(-10000,-10000, null); //dummy node
			Node<E> dummyLast = new Node<E>(10000,10000, null); //dummy node
			_first = dummyFirst; //dummy node allows for easier addition
			_last = dummyLast;
			_current = dummyFirst;
			dummyFirst._next = dummyLast;
			dummyLast._prev = dummyFirst;
		}
		
		public boolean isEmpty(){
			if(_manyItems != 0) return false;
			return true;
		}
		
		/**
		 * Will halt all the plant timers
		 */
		public void stopAll(){
			for(Node<E> temp = _first; temp != _last && temp != null; temp = temp._next){
				if(temp != null){
					if(temp.data != null){
						if(temp.data instanceof plant)
							((plant)temp.data).stop();
					}
				}
			}
		}
		
		/**
		 * Will update all of type plant
		 */
		public void updateAll(){
			for(Node<E> temp = _first; temp != _last; temp = temp._next){
				if(temp.data instanceof plant)
					((plant)temp.data).update();
			}
		}

		public E getCurrentPlant(){
			return _current.data;
		}

		/**
		 * Adds Bush to the list, if plant already exists, will replace data in existing plant with element
		 * @param x X coordinates of object
		 * @param y Y coordinates of object
		 * @param element Type of element to insert
		 */
		public void add(int x, int y, E element){
			for(Node<E> temp = _first; temp != _last; temp = temp._next){
				if(temp._x == x && temp._y == y){ //already exists
					temp.data = element; //replace data
					_current = temp;
					return;
				}
			}

			Node<E> temp = new Node<E>(x, y, element);
			temp._prev = _first;
			temp._next = _first._next;
			_first._next._prev = temp;
			_first._next = temp;
			_current = temp;
			_manyItems += 1;
		}

		/**
		 * Returns current Node, if there is none, then will return dummy
		 */
		public Node<E> getCurrent(){
			return _current;
		}

		/**
		 * Removes current element and sets current to first
		 */
		public void removeCurrent(){
			_current = _current._prev;
			Node<E> tempDelete = _current._next;
			_current._next = _current._next._next;
			tempDelete._next._prev = tempDelete._prev;
			tempDelete._next = null;
			tempDelete._prev = null;
			tempDelete = null;
			_current = _first;
			_manyItems -= 1;
		}

		/**
		 * Searches for designated Node of x and y, if found sets as current
		 * If Node not found, creates Node and sets it as current
		 * and returns it as the object
		 * @param x X coord to be found or created
		 * @param y Y coord to be found or created
		 * @param element element to add if not found by x,y coords
		 * @return _current will be updated to the element
		 */
		public void findOrAddNode(int x, int y, E element){
			if(_manyItems == 0){
				add(x, y, element);
				return;
			}

			for (Node<E> temp = _first._next; temp != _last; temp = temp._next){
				if (temp._x == x && temp._y == y){//means Node exists
					_current = temp;
					return;
				}
			}
			add(x, y, element);
		}

		/**
		 * Searches for Node in list and sets to current if found
		 * @param x X coordinate to find
		 * @param y Y coordinate to find
		 * @return Object if found by x,y coord, will set object to current, null if not found
		 */
		public E find(int x, int y){
			for(Node<E> temp = _first; temp != _last; temp = temp._next){
				if(temp._x == x && temp._y == y){ //object exists
					_current = temp;
					return temp.data;
				}
			}
			playerClient.debug("In Farming Handler : Object with coords "+x+","+y+" was not found in list.");
			return null;
		}

	}
	

}




package skills;
import root.misc;
import root.server;
import serverHandlers.GlobalObject;


public class FarmingObject extends GlobalObject {

	private int endID, deadID, clearID, seedID, tick, numberOfItems, itemID, EXP;
	private int upCounter = -1;
	private int deathCounter = -1;

	/**
	 * @param object Start ID of plant
	 * @param endID End ID of plant
	 * @param clearID Clear ID, when plant is picked
	 * @param seedID What type of item it distributes
	 * @param tick Number of seconds between each stage
	 */
	                  //(int x, int y, int seed, int EXP, int amountSeeds, int tick, int startStage, int endStage, int dID, int cID, int itemID, int n, int deadTime)
	public FarmingObject(int x, int y, int seed, int EXP, int placeHolder, int tick, int object, int endID, int deadID, int clearID, int itemID, int placeHolder2, int placeHolder3, int _direction, String playerName) {
		super(x, y, object, _direction, playerName);
		this.isVisible = true;
		this.seedID = seed;
		this.tick = tick*2;
		this.upCounter = 0;
		this.numberOfItems = misc.random(3)+1; //+1 is for seed count
		this.EXP = EXP;
		this.endID = endID;
		this.deadID = deadID;
		this.clearID = clearID;
		this.itemID = itemID;
		server.globalObjectHandler.deleteGlobalObject(x, y,playerName);
		server.globalObjectHandler.bufferList.add(this);
	}
	
	public int getEXP(){
		return this.EXP;
	}
	
	public int getSeedID(){
		return this.seedID;
	}
	
	public int getItemType(){
		return this.itemID;
	}
	
	public void clear(){
		this.numberOfItems = -1;
		this.deathCounter = -1;
		this.upCounter = -1;
		this.originalObjectID = clearID;
	}
	
	public boolean clearIfNecessary(){
		if(this.numberOfItems <= 0){
			this.clear();
			return true;
		}
		return false;
	}
	
	public int getRemainingNumberOfItems(){
		return this.numberOfItems;
	}
	
	public void subtractTotalNumberOfItems(int amount){
		this.numberOfItems -= amount;
	}

	public int getRemainingTime(){
		return (this.endID-this.originalObjectID)*(tick/2);
	}
	
	@Override
	public void process() { //called every 500ms
		if(upCounter != -1){
			if((++upCounter)%tick == 0){
				if(this.originalObjectID+1 != endID){
					this.originalObjectID += 1;
				}
				else{
					this.originalObjectID = endID;
					upCounter = -1;
					deathCounter = 180;
				}
			}
		}
		if(deathCounter != -1){
			if(--deathCounter == 0){
				this.originalObjectID = this.deadID;
				this.numberOfItems = -1;
				this.deathCounter = -1;
				this.upCounter = -1;
			}
		}
	}

	@Override
	public void destruct() {
		server.globalObjectHandler.objectList.remove(this);
		server.globalObjectHandler.bufferList.remove(this);
	}

}

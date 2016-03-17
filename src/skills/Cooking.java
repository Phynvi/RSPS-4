package skills;
import playerData.client;
import root.misc;
import clientHandlers.Item;


public class Cooking {
	private client c;
	private int EXP, cookID, burntID, fishID, levelRequired;
	private final int COOKING_DELAY = 3;
	private final int COOKING_ANIMATION_ON_RANGE = 883;
	private final int COOKING_ANIMATION_ON_FIRE = 897;
	private final int COOKING_GLOVES = 775;

	public void setIDs(int levelRequired, int EXP, int burntID){
		this.EXP = EXP;
		this.burntID = burntID;
		this.levelRequired = levelRequired;
	}

	private final int BURNT_FISH1 = 323;
	private final int BURNT_FISH2 = 343;
	private final int BURNT_FISH3 = 357;
	
	public void startCooking(){
		c.getSkillHandler().startSkillTimerForSkill(COOKING_DELAY, 9);
		c.repeatAnimation(COOKING_ANIMATION_ON_RANGE, 3);		
	}

	public void itemWasUsedWithCookingDevice(int itemID){

		this.fishID = itemID;
		switch(itemID){
		case 317: //Shrimp
			this.cookID = itemID-2;
			setIDs(1, 30, BURNT_FISH1);
			break;
		case 321: //Anchovies
			this.cookID = itemID-2;
			setIDs(1, 30, BURNT_FISH1);
			break;
		case 327: //Raw Sardine
			this.cookID = itemID-2;
			setIDs(1, 40,BURNT_FISH1);
			break;
		case 331: //salmon
			this.cookID = itemID-2;
			setIDs(25, 90,BURNT_FISH1);
			break;
		case 335: //trout
			this.cookID = itemID-2;
			setIDs(15, 70,BURNT_FISH1);
			break;
		case 339: //Cod
			this.cookID = itemID-2;
			setIDs(18, 75,BURNT_FISH1);
			break;
		case 345: //herring
			this.cookID = itemID+2;
			setIDs(5,50,BURNT_FISH2);
			break;
		case 349: //pike
			this.cookID = itemID+2;
			setIDs(20,80,BURNT_FISH2);
			break;
		case 353:// mackerel
			this.cookID = itemID+2;
			setIDs(10,60,BURNT_FISH2);
			break;
		case 359: //tuna
			this.cookID = itemID+2;
			setIDs(30,100,BURNT_FISH3);
			break;
		case 363: //bass
			this.cookID = itemID+2;
			setIDs(43,130,BURNT_FISH3);
			break;
		case 371: //swordfish
			this.cookID = itemID+2;
			setIDs(45,140,itemID+4);
			break;
		case 377: //lobster
			this.cookID = itemID+2;
			setIDs(45,140,itemID+4);
			break;
		case 383: //shark
			this.cookID = itemID+2;
			setIDs(80,210,itemID+4);
			break;
		case 389: //manta ray
			this.cookID = itemID+2;
			setIDs(91,216,itemID+4);
			break;
		case 395: //sea turtle
			this.cookID = itemID+2;
			setIDs(82,211,itemID+4);
			break;
		case 3142: //raw karambwan
			this.cookID = itemID+2;
			setIDs(1,190,3148);
			break;
		default:
			return;
		}

		if(c.playerLevel[c.playerCooking] < this.levelRequired){
			c.sendMessage("You need at least "+this.levelRequired+" Cooking to do that.");
			return;
		}
		c.getFrameMethodHandler().select2Options(41, "Options", "Cook all "+Item.getItemName(itemID), "Cancel");

	}

	public Cooking(client pc){
		this.c = pc;
	}

	private boolean burnFood(int lvl){
		if(c.playerEquipment[c.playerHands] == COOKING_GLOVES)
			return false;

		double a = (double)c.playerLevel[c.playerCooking]/(double)lvl;
		int b = (int)Math.ceil(a);
		
		return (misc.random(b) == 0);
	}

	public void cookFood(){
		if(!c.getInventoryHandler().hasItem(fishID)){
			c.getSkillHandler().resetTimers();
			c.stopAnimations();
			return;
		}		
		if(c.playerLevel[c.playerCooking] < this.levelRequired){
			c.sendMessage("You need at least "+this.levelRequired+" Cooking to do that.");
			c.getSkillHandler().resetTimers();
			c.stopAnimations();
			return;
		}
		c.getInventoryHandler().deleteItem(fishID);

		if(burnFood(this.levelRequired)){
			c.getInventoryHandler().addItem(burntID);
			c.sendMessage("You burn the "+Item.getItemName(fishID));
		}
		else{
			c.getInventoryHandler().addItem(cookID);
			c.sendMessage("You cook the "+Item.getItemName(fishID));
		}

		c.getSkillHandler().startSkillTimerForSkill(COOKING_DELAY, 9);
	}

}

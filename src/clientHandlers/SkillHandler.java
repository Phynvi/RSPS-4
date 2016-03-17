package clientHandlers;
import playerData.client;
import skills.Cooking;
import skills.Crafting;
import skills.Firemaking;
import skills.Fishing;
import skills.Fletching;
import skills.Prayer;


public class SkillHandler {

	private int skillTimer = -1;
	private int skillType = -1;
	private client c;
	
	private Prayer prayerHandler;
	public Prayer getPrayerHandler(){
		return this.prayerHandler;
	}
	
	private Cooking cookingHandler;
	public Cooking getCookingHandler(){
		return this.cookingHandler;
	}
	
	private Fishing fishingHandler;
	public Fishing getFishingHandler(){
		return this.fishingHandler;
	}
	
	private Fletching fletchingHandler;
	public Fletching getFletchingHandler(){
		return this.fletchingHandler;
	}
	
	private Firemaking firemakingHandler;
	public Firemaking getFireMakingHandler(){
		return this.firemakingHandler;
	}
	
	private Crafting craftingHandler;
	public Crafting getCraftingHandler(){
		return this.craftingHandler;
	}
	
	public SkillHandler(client pc){
		this.c = pc;
		this.firemakingHandler = new Firemaking(pc);
		this.craftingHandler = new Crafting(pc);
		this.fletchingHandler = new Fletching(pc);
		this.cookingHandler = new Cooking(pc);
		this.fishingHandler = new Fishing(pc);
		this.prayerHandler = new Prayer(pc);
	}
	
	public void resetTimers(){
		this.skillTimer = -1;
	}
	
	public void startSkillTimerForSkill(int t, int skillType){
		this.skillTimer = t;
		this.skillType = skillType;
	}
	
	public void setSkillTimer(int t){
		this.skillTimer = t;
	}
	
	public void setSkillType(int s){
		this.skillType = s;
	}
	
	//called every 500ms
	public void process(){
		this.prayerHandler.prayTimers();
		if(this.skillTimer > 0 && --this.skillTimer == 0){
			switch(this.skillType){
			case 1: //firemaking
				getFireMakingHandler().createFire();
				return;
			case 2: //spinning flax
				getCraftingHandler().spinFlax();
				return;
			case 3: //fletching
				getFletchingHandler().fletchItem();
				return;
			case 4: //fishing
				getFishingHandler().deliverFishAndResetTimers();
				return;
			case 5: //smelting
				c.getSmithingHandler().removeOreAndSmeltBar();
				return;
			case 6: //Woodcutting
				c.getWoodcuttingHandler().deliverLog();
				return;		
			case 7: //Mining
				c.getMiningHandler().deliverOre();
				return;
			case 8: //Tai Bwo Wannai cut down jungle
				c.getMiniGameHandler().getTaiBwoWannaiPickup().cutDownJungle();
				return;
			case 9: //Cooking
				getCookingHandler().cookFood();
				return;
			}
		}
		
	}
	
}

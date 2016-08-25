package client.handlers;
import client.client;
import client.handlers.skills.Agility;
import client.handlers.skills.Cooking;
import client.handlers.skills.Crafting;
import client.handlers.skills.Firemaking;
import client.handlers.skills.Fishing;
import client.handlers.skills.Fletching;
import client.handlers.skills.Prayer;
import client.handlers.skills.Slayer;
import client.handlers.skills.Smithing;
import client.handlers.skills.Thieving;


public class SkillHandler {
	
	private final int Attack = 0;
	private final int Defence = 1;
	private final int Strength = 2;
	private final int Hitpoints = 3;
	private final int Ranged = 4;
	private final int Prayer = 5;
	private final int Magic = 6;
	private final int Cooking = 7;
	private final int Woodcutting = 8;
	private final int Fletching = 9;
	private final int Fishing = 10;
	private final int Firemaking = 11;
	private final int Crafting = 12;
	private final int Smithing = 13;
	private final int Mining = 14;
	private final int Herblore = 15;
	private final int Agility = 16;
	private final int Thieving = 17;
	private final int Slayer = 18;
	private final int Farming = 19;
	private final int Runecrafting = 20;

	public String getSkillName(int id){
		switch(id){
		case Attack: return "Attack";
		case Defence: return "Defence";
		case Strength: return "Strength";
		case Hitpoints: return "Hitpoints";
		case Ranged: return "Ranged";
		case Prayer: return "Prayer";
		case Magic: return "Magic";
		case Cooking: return "Cooking";
		case Woodcutting: return "Woodcutting";
		case Fletching: return "Fletching";
		case Fishing: return "Fishing";
		case Firemaking: return "Firemaking";
		case Crafting: return "Crafting";
		case Smithing: return "Smithing";
		case Mining: return "Mining";
		case Herblore: return "Herblore";
		case Agility: return "Agility";
		case Thieving: return "Thieving";
		case Slayer: return "Slayer";
		case Farming: return "Farming";
		case Runecrafting: return "Runecrafting";
		}
		return null;
	}
	
	private int skillTimer = -1;
	private int skillType = -1;
	private client c;
	
	private Slayer slayerHandler;
	public Slayer getSlayerHandler(){
		return this.slayerHandler;
	}
	
	private Thieving thievingHandler;
	public Thieving getThievingHandler(){
		return this.thievingHandler;
	}
	
	private Agility agilityHandler;
	public Agility getAgilityHandler(){
		return this.agilityHandler;
	}
	
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

	private Smithing smithingHandler;
	public Smithing getSmithingHandler(){
		return this.smithingHandler;
	}
	
	public SkillHandler(client pc){
		this.c = pc;
		this.firemakingHandler = new Firemaking(pc);
		this.craftingHandler = new Crafting(pc);
		this.fletchingHandler = new Fletching(pc);
		this.cookingHandler = new Cooking(pc);
		this.fishingHandler = new Fishing(pc);
		this.prayerHandler = new Prayer(pc);
		this.agilityHandler = new Agility(pc);
		this.thievingHandler = new Thieving(pc);
		this.slayerHandler = new Slayer(pc);
		this.smithingHandler = new Smithing(pc);
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
		this.agilityHandler.agilityTimers();
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
				getSmithingHandler().removeOreAndSmeltBar();
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
			case 10: //Smithing
				getSmithingHandler().SmithItem();
				return;
			}
		}
		
	}
	
}

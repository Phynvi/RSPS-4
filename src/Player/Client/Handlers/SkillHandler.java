
public class SkillHandler {

	private int skillTimer = -1;
	private int skillType = -1;
	private client c;
	
	final private int playerAttack = 0;
	final private int playerDefence = 1;
	final private int playerStrength = 2;
	final private int playerHitpoints = 3;
	final private int playerRanged = 4;
	final private int playerPrayer = 5;
	final private int playerMagic = 6;
	final private int playerCooking = 7;
	final private int playerWoodcutting = 8;
	final private int playerFletching = 9;
	final private int playerFishing = 10;
	final private int playerFiremaking = 11;
	final private int playerCrafting = 12;
	final private int playerSmithing = 13;
	final private int playerMining = 14;
	final private int playerHerblore = 15;
	final private int playerAgility = 16;
	final private int playerThieving = 17;
	final private int playerSlayer = 18;
	final private int playerFarming = 19;
	final private int playerRunecrafting = 20;
	

	private Firemaking firemakingHandler;
	public Firemaking getFireMakingHandler(){
		return this.firemakingHandler;
	}
	
	public SkillHandler(client pc){
		this.c = pc;
		this.firemakingHandler = new Firemaking(pc);
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
		if(this.skillTimer > 0 && --this.skillTimer == 0){
			switch(this.skillType){
			case playerFiremaking:
				this.skillTimer = -1;
				getFireMakingHandler().createFire();
				return;
			}
		}
		
	}
	
}

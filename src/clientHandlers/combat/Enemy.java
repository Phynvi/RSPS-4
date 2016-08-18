package clientHandlers.combat;

import Resources.misc;
import clientHandlers.FrameMethods;
import clientHandlers.Item;
import npcInformation.NPC;
import playerData.Player;
import playerData.client;
import root.server;
import serverHandlers.NPCHandler;


public class Enemy {

	private client selfPlayer;
	private NPC selfNPC;
	private Enemy opponent;
	
	public boolean DoIHitTargetWithMelee(Enemy e){
		if(this.isNPC()){
			if(e.isPlayer()){ //NPC attacking Player
				return NPCHandler.doesNPCHitPlayerWithMelee(this.selfNPC, e.getPlayerClient());
			}
			else{ //TODO - NPC attacking NPC
				return true;
			}
		}
		else{ //player attacking player or player attacking npc
			return this.selfPlayer.getCombatHandler().doIHitEnemyWithMelee(e);
		}
	}
	
	public boolean DoIHitTargetWithRanged(Enemy e){
		if(this.isNPC()){
			if(e.isPlayer()){ //NPC attacking Player
				return NPCHandler.doesNPCHitPlayerWithRanged(this.selfNPC, e.getPlayerClient());
			}
			else{ //TODO - NPC attacking NPC
				return true;
			}
		}
		else{ //player attacking player or player attacking npc
			return this.selfPlayer.getCombatHandler().doIHitEnemyWithRanged(e);
		}
	}
	
	public boolean DoIHitTargetWithMagic(Enemy e){
		if(this.isNPC()){
			if(e.isPlayer()){ //NPC attacking Player
				return NPCHandler.doesNPCHitPlayerWithMagic(this.selfNPC, e.getPlayerClient());
			}
			else{ //TODO - NPC attacking NPC
				return true;
			}
		}
		else{ //player attacking player or player attacking npc
			return this.selfPlayer.getCombatHandler().doIHitEnemyWithMagic(e);
		}
	}
	
	public void interruptTeleport(){
		if(this.isNPC()){
			
		}
		else{
			this.selfPlayer.interruptTeleport();
		}
	}
	
	public void bind(int seconds){
		if(this.isNPC()){
			
		}
		else{
			this.selfPlayer.frozen(seconds);
		}
	}
	
	public void gfx100(int gfxId){
		if(this.isNPC()){
			this.selfNPC.gfx100(gfxId);
		}
		else{
			this.selfPlayer.getFrameMethodHandler().gfx100(gfxId);
		}
	}
	
	public void gfx0(int gfxId){
		if(this.isNPC()){
			this.selfNPC.gfx0(gfxId);
		}
		else{
			this.selfPlayer.getFrameMethodHandler().gfx0(gfxId);
		}
	}
	
	public void setTarget(Enemy e){
		this.opponent = e;
	}

	public Enemy getOpponent(){
		return this.opponent;
	}

	public Enemy(NPC n){
		this.npcType = n.npcType;
		this.selfNPC = n;
	}

	public Enemy(client c){
		this.selfPlayer = c;
	}
	
	public Enemy(Player p){
		this.selfPlayer = (client)p;
	}

	public boolean isNull(){
		if(this.isNPC()){
			return this.selfNPC == null;
		}
		else{
			return this.selfPlayer == null;
		}
	}
	
	public int distanceToPoint(int x, int y){
		if(this.isNPC()){
			return this.selfNPC.distanceToPoint(x, y);
		}
		else{
			return this.selfPlayer.distanceToPoint(x, y);
		}
	}
	
	public int getX(){
		if(selfPlayer != null)
			return selfPlayer.absX;
		else
			return selfNPC.absX;
	}

	public int getY(){
		if(selfPlayer != null)
			return selfPlayer.absY;
		else
			return selfNPC.absY;
	}

	public boolean isUnderAttack(){
		return(opponent != null);
	}

	private int npcType = -1;
	public int getNPCType(){
		return this.npcType;
	}

	/**
	 * @return NPC index in npchandler NPCs array
	 */
	public int getNPCId(){
		return selfNPC.npcId;
	}

	public boolean isNPC(){
		return (this.selfNPC != null);
	}

	public boolean isPlayer(){
		return (this.selfPlayer != null);
	}
	
	/**
	 * If opponent is a player, this method will not ignore prayer
	 */
	public void inflictMeleeDamage(int amount, Enemy attackingEnemy){
		this.inflictMeleeDamage(amount, attackingEnemy, false);
	}
	
	/**
	 * If opponent is a player, this method will not ignore prayer
	 */
	public void inflictRangeDamage(int amount, Enemy attackingEnemy){
		this.inflictRangeDamage(amount, attackingEnemy, false);
	}

	/**
	 * If opponent is a player, this method will not ignore prayer
	 */
	public void inflictMagicDamage(int amount, Enemy attackingEnemy){
		this.inflictMagicDamage(amount, attackingEnemy, false);
	}

	//TODO reduce damage by appropriate armor/resists
	
	/**
	 * @param ignorePrayer if attacking a player, ignore prayer
	 */
	public void inflictMeleeDamage(int amount, Enemy attackingEnemy, boolean ignorePrayer){
		if(this.isPlayer()){
			if(!ignorePrayer && this.selfPlayer.PMelee){
				amount = (int)((double)amount * 0.6);
			}
		}
		this.inflictDamage(amount, attackingEnemy);
	}

	/**
	 * @param ignorePrayer if attacking a player, ignore prayer
	 */
	public void inflictRangeDamage(int amount, Enemy attackingEnemy, boolean ignorePrayer){
		if(this.isPlayer()){
			if(!ignorePrayer && this.selfPlayer.PRange){
				amount = (int)((double)amount * 0.6);
			}
		}
		this.inflictDamage(amount, attackingEnemy);
	}
	
	/**
	 * @param ignorePrayer if attacking a player, ignore prayer
	 */
	public void inflictMagicDamage(int amount, Enemy attackingEnemy, boolean ignorePrayer){
		if(this.isPlayer()){
			if(!ignorePrayer && this.selfPlayer.PMage){
				amount = (int)((double)amount * 0.6);
			}
		}
		this.inflictDamage(amount, attackingEnemy);
	}

	/**
	 * Will do gfx for damage reduction items as well.
	 */
	private int getGeneralDamageReduction(int hitDiff){
		int total = 0;
		if(this.isNPC()){
			
		}
		else{
			if (this.selfPlayer.getCombatHandler().ElysianSpiritShield()){
				int chance = misc.random(9);
				if(chance != 0 && chance != 1 && chance != 2){
					total += hitDiff/4;
					FrameMethods.gfxAll(575, this.getX(), this.getY());
				}
			}
			if(this.selfPlayer.getCombatHandler().DivineSpiritShield()){
				total += (int)( ((double)hitDiff) *0.7);
			}
			//TODO prayer reductions
		}
		return total;
	}
	
	private void inflictDamage(int amount, Enemy attackingEnemy){
		if(this.isNPC()){
			if(attackingEnemy.isPlayer()){ //attacker is Player
				attackingEnemy.getPlayerClient().getCombatHandler().hitNPC(this.getNPCId(), amount, attackingEnemy.getID());
			}
			else{ //attacker is NPC
				//TODO
			}			
		}
		else{
			if(attackingEnemy.isPlayer()){ //attacker is Player
				attackingEnemy.getPlayerClient().getCombatHandler().hitPlayer(this.getID(), amount);
			}
			else{ //attacker is NPC
				
				if (amount < 0)
					amount = 0;
				
				int HP = this.selfPlayer.playerLevel[this.selfPlayer.playerHitpoints];
				
				if ((HP - amount) < 0) 
					amount = HP;	
				
				if (this.selfPlayer.SpecEmoteTimer == 0 && this.selfPlayer.DirectionCount >= 2) //if the player is not in the middle of animation for special or walking/running
					this.selfPlayer.startAnimation(Item.GetBlockAnim(this.selfPlayer.playerEquipment[this.selfPlayer.playerWeapon]));
				
				this.selfPlayer.hitDiff = amount;
				this.selfPlayer.updateRequired = true;
				this.selfPlayer.hitUpdateRequired = true;
				this.selfPlayer.appearanceUpdateRequired = true;
				this.selfPlayer.getFrameMethodHandler().RemoveAllWindows();

				if(this.selfPlayer.autoRetaliate == 1 && this.selfPlayer.getEnemy() == null ){ //&& c.distanceToPoint(npcs[NPCID].absX, npcs[NPCID].absY) < 5){
					this.selfPlayer.setEnemy(attackingEnemy);
					this.selfPlayer.getCombatHandler().Attack();
				}
				if(this.selfPlayer.teleportDelayCast && this.selfPlayer.teleportDelay > 0)
					this.selfPlayer.interruptTeleport();
			}
		}		
	}

	public void facePlayer(int playerIndex){
		if(this.isNPC())
			this.getNPC().faceplayer(playerIndex);
		else
			this.getPlayerClient().facePlayer(playerIndex);		
	}
	
	public boolean isDead(){
		if(this.isNPC()){
			return this.selfNPC.IsDead;
		}
		else{
			return this.selfPlayer.IsDead;
		}
	}
	
	/**
	 * @return Player index in playerhandler Players array
	 */
	public int getPlayerIndex(){
		return this.selfPlayer.playerId;
	}
		
	public client getPlayerClient(){
		return this.selfPlayer;
	}
	
	public NPC getNPC(){
		return this.selfNPC;
	}

	/**
	 * @return Either player index ID or npc index ID
	 */
	public int getID(){
		if(this.isNPC())
			return this.selfNPC.npcId;
		
		return this.selfPlayer.playerId;
	}

}

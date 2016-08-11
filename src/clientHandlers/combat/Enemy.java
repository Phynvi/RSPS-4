package clientHandlers.combat;

import clientHandlers.Item;
import npcInformation.NPC;
import npcInformation.NPCAnim;
import playerData.client;
import root.server;
import serverHandlers.PlayerHandler;


public class Enemy {

	private client selfPlayer;
	private NPC selfNPC;
	private Enemy opponent;

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
	public void inflictMeleeDamage(int amount, int attackingPlayerIndex){
		this.inflictMeleeDamage(amount, attackingPlayerIndex, false);
	}
	
	/**
	 * If opponent is a player, this method will not ignore prayer
	 */
	public void inflictRangeDamage(int amount, int attackingPlayerIndex){
		this.inflictRangeDamage(amount, attackingPlayerIndex, false);
	}

	/**
	 * If opponent is a player, this method will not ignore prayer
	 */
	public void inflictMagicDamage(int amount, int attackingPlayerIndex){
		this.inflictMagicDamage(amount, attackingPlayerIndex, false);
	}

	/**
	 * @param ignorePrayer if attacking a player, ignore prayer
	 */
	public void inflictMeleeDamage(int amount, int attackingPlayerIndex, boolean ignorePrayer){
		if(this.isPlayer()){
			if(!ignorePrayer && this.selfPlayer.PMelee){
				amount = (int)((double)amount * 0.6);
			}
		}
		this.inflictDamage(amount, attackingPlayerIndex);
	}

	/**
	 * @param ignorePrayer if attacking a player, ignore prayer
	 */
	public void inflictRangeDamage(int amount, int attackingPlayerIndex, boolean ignorePrayer){
		if(this.isPlayer()){
			if(!ignorePrayer && this.selfPlayer.PRange){
				amount = (int)((double)amount * 0.6);
			}
		}
		this.inflictDamage(amount, attackingPlayerIndex);
	}
	
	/**
	 * @param ignorePrayer if attacking a player, ignore prayer
	 */
	public void inflictMagicDamage(int amount, int attackingPlayerIndex, boolean ignorePrayer){
		if(this.isPlayer()){
			if(!ignorePrayer && this.selfPlayer.PMage){
				amount = (int)((double)amount * 0.6);
			}
		}
		this.inflictDamage(amount, attackingPlayerIndex);
	}

	//TODO
	private void inflictDamage(int amount, int attackerPlayerIndex){
		if(this.isNPC()){
			this.opponent.getPlayerClient().getCombatHandler().hitNPC(this.getNPCId(), amount, attackerPlayerIndex);
		}
		else{
			
		}		
		
		if(this.n != null){
			this.getPlayerClient().getCombatHandler().hitNPC(this.getNPCId(), amount, this.getPlayerIndex());
		}
		else{

			this.opponent.facePlayer(this.getPlayerIndex());
			
			this.attackClient.KillerId = this.opponent.playerId;
			
			if(this.opponent.autoRetaliate == 1 && !this.opponent.isInCombat()) //1 means on
				this.opponent.getCombatHandler().opponentAutoAttack(this.attackClient);
			this.opponent.inCombat();
			if (this.attackClient.SpecEmoteTimer == 0)
				this.attackClient.startAnimation(Item.GetBlockAnim(this.attackClient.playerEquipment[this.attackClient.playerWeapon]));
		}
	}

	//TODO
	public void facePlayer(int playerIndex){
		
	}

	//TODO
	public boolean isDead(){
		return false;
	}
	
	/**
	 * @return Player index in playerhandler Players array
	 */
	public int getPlayerIndex(){
		return attackClient.playerId;
	}
	
	public void attackTarget(){
		if(this.attackClient != null){
			this.n.RandomWalk= false;
			this.n.IsUnderAttack = true;
			this.n.StartKilling = this.opponent.playerId;
		}
		else if (this.n != null){
			//TODO
		}
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
		if(this.attackClient == null)
			return this.n.npcId;
		return this.attackClient.playerId;
	}

}

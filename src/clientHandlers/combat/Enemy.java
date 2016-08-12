package clientHandlers.combat;

import npcInformation.NPC;
import playerData.Player;
import playerData.client;
import root.server;


public class Enemy {

	private client selfPlayer;
	private NPC selfNPC;
	private Enemy opponent;
	
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

	//TODO reduce damage by appropriate armor/resists
	
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

	private void inflictDamage(int amount, int attackerPlayerIndex){
		client attacker = (client)server.playerHandler.players[attackerPlayerIndex];
		if(this.isNPC()){
			attacker.getCombatHandler().hitNPC(this.getNPCId(), amount, attackerPlayerIndex);
		}
		else{
			attacker.getCombatHandler().hitPlayer(this.getPlayerIndex(), amount);
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

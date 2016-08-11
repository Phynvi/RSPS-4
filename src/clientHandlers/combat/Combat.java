package clientHandlers.combat;
import clientHandlers.Item;
import npcInformation.NPCAnim;
import playerData.Player;
import playerData.client;
import root.misc;
import root.server;
import serverHandlers.PlayerHandler;
import struct.lists;
import npcInformation.NPC;


public class Combat {
	private client c = null;

	public Combat(client pc){
		this.c = pc;
	}
	

	public int getPlayerPrayerEquipmentBonus(){
		return c.playerBonus[prayer];
	}

	public int getPlayerMeleeDefEquipmentBonus(){
		int j = c.playerBonus[defBonus[stab]];
		int b = c.playerBonus[defBonus[slash]];
		int d = c.playerBonus[defBonus[crush]];
		return Math.max(Math.max(b, j),d);
	}
	public int getPlayerMeleeAtkEquipmentBonus(){
		int j = c.playerBonus[atkBonus[stab]];
		int b = c.playerBonus[atkBonus[slash]];
		int d = c.playerBonus[atkBonus[crush]];
		return Math.max(Math.max(b, j),d);
	}

	public int getPlayerMagicDefEquipmentBonus(){
		return c.playerBonus[defBonus[magic]];
	}
	public int getPlayerMagicAtkEquipmentBonus(){
		return c.playerBonus[atkBonus[magic]];
	}
	public int getPlayerRangeDefEquipmentBonus(){
		return c.playerBonus[defBonus[range]];
	}
	public int getPlayerRangeAtkEquipmentBonus(){
		return c.playerBonus[atkBonus[range]];
	}

	public void allSdisable(){
		c.getFrameMethodHandler().getFilling();
		c.litBar = false;
	}

	private int stab = 0;
	private int slash = 1;
	private int crush = 2;
	private int magic = 3;
	private int range = 4;
	private int[] atkBonus = {0,1,2,3,4};
	private int[] defBonus = {5,6,7,8,9};
	private int strength = 10;
	private int prayer = 11;	

	private boolean usedBandosSpecial = false;
	private boolean usedZamorakSpecial = false;


	private void applyBandosSpecial(client opponentClient, int damage){
		if(damage <= 0) return;

		if( (opponentClient.playerLevel[1]-damage) < 1)
			opponentClient.playerLevel[1] = 1; 

		if( (opponentClient.playerLevel[2]-damage) < 1)
			opponentClient.playerLevel[2] = 1; 

		if( (opponentClient.playerLevel[1]-damage) >= 1)
			opponentClient.playerLevel[1] -= damage; 

		if( (opponentClient.playerLevel[2]-damage) >= 1)
			opponentClient.playerLevel[2] -= damage; 

		opponentClient.sendMessage("Your Strength and Defence have been drained by the Bandos Special.");
		opponentClient.getClientMethodHandler().addSkillXP(0, 1);
		opponentClient.getClientMethodHandler().addSkillXP(0, 2); //updates frames

	}

	public boolean ElysianSpiritShield(){
		return(c.playerEquipment[c.playerShield] == 3637);
	}

	public boolean DivineSpiritShield(){
		return(c.playerEquipment[c.playerShield] == 3631);
	}

	public int getDFSBonus(){
		return c.dragcharge/10;
	}


	public boolean FullDharokEquipped() {
		return (c.playerEquipment[c.playerHat] == 4716 && c.playerEquipment[c.playerChest] == 4720 && c.playerEquipment[c.playerLegs] == 4722 && c.playerEquipment[c.playerWeapon] == 4718);
	}

	public boolean ivandis(){
		return (c.playerEquipment[c.playerWeapon] == 13308);
	}	

	public boolean hasAnyDragonFireShield(){
		return(c.playerEquipment[c.playerShield] == Item.ANTIDRAGONFIRESHIELD || c.playerEquipment[c.playerShield] == Item.DFS);
	}

	public boolean dragfire() {
		return (c.playerEquipment[c.playerShield] == Item.ANTIDRAGONFIRESHIELD); //dfire shield
	}

	public boolean hasDFS() {
		return (c.playerEquipment[c.playerShield] == Item.DFS); //DFS
	}


	public boolean FullGuthanEquipped() {
		return (c.playerEquipment[c.playerHat] == 4724 && c.playerEquipment[c.playerChest] == 4728 && c.playerEquipment[c.playerLegs] == 4730 && c.playerEquipment[c.playerWeapon] == 4726);			
	}

	private void useSpecialAndSubtractDelay(int amount){
		c.litBar = false;
		c.specialDelay -= amount;
	}
	
	/**
	 * Will check if the enemy is currently attackable
	 * @param e Attack object to check status of
	 * @return True if can be attacked, false if not.
	 */
	public boolean canIAttackMyEnemy(Enemy e){
		if(e.isNPC()){
			if(c.getSkillHandler().getSlayerHandler().slayerNPC.exists(e.getNPCType())){ //slayer NPC
				if(c.playerLevel[18] < c.getSkillHandler().getSlayerHandler().getTaskLevel(e.getNPCType()) && 
						c.slayerNPC != e.getNPCType()){
					c.sendMessage("You need a higher Slayer level to do that.");
					return false;
				}
			}

			if(server.npcHandler.npcs[e.getNPCId()].moveToSpawn) 
				return false;

			if(server.npcHandler.npcs[e.getNPCId()].attacknpc > 0) {
				c.sendMessage("That NPC is already under attack.");
				return false;
			}
			int _NPCTYPE = e.getNPCType();

			if(lists.safeNPCs.exists(_NPCTYPE) || c.DIALOGUEHANDLER.exists(_NPCTYPE)){
				c.sendMessage("That's a friendly NPC that I should not attack.");
				c.stopPlayerMovement();
				return false;
			}						

			if((_NPCTYPE == 221 || _NPCTYPE == 938) && !c.PDAggro){
				c.sendMessage("I don't think I want to do that.");
				c.stopPlayerMovement();
				return false;
			}

			if(c.getSkillHandler().getSlayerHandler().slayerNPC.exists(_NPCTYPE)){ //slayer NPC
				if(c.playerLevel[18] < c.getSkillHandler().getSlayerHandler().getTaskLevel(_NPCTYPE) && c.slayerNPC != _NPCTYPE){
					c.sendMessage("You need a higher Slayer level to do that.");
					return false;
				}

			}
		}
		else{
			
		}
		return true;
	}
	
	public void Attack(Enemy e){
		
	}

	/**
	 * Will check the player weapon and apply a bonus to their original attack
	 * The new amount (including bonus) is returned
	 * @param original Original attack damage
	 * @param Y Y coordinate (typically of enemy), used for gfx and distance measurements
	 * @param X X coordinate (typically of enemy), used for gfx and distance measurements
	 * @return Bonus damage
	 */
	public int checkSpecials(int original, int Y, int X){
		int specdmg = 0;
		int[] playerEquipment = c.playerEquipment;
		int playerWeapon = c.playerWeapon;
		
		if(playerEquipment[playerWeapon] == 3101){ //rune claws
			useSpecialAndSubtractDelay(3);
			return original+(int)((double)original/10.0); //player hit + 10%;
		}

		if (playerEquipment[playerWeapon] == 15334){ //Bandos godsword
			if(c.getEnemy().isPlayer()){
				usedBandosSpecial = true;
			}
			useSpecialAndSubtractDelay(10);
			return original+(int)((double)original/10.0); //player hit + 10%;
		}
		if (playerEquipment[playerWeapon] == 15333){ //Armadyl Godsword
			useSpecialAndSubtractDelay(5);
			return original+(int)((double)original/4.0); //player hit + 25%
		}    
		if (playerEquipment[playerWeapon] == 6739){ //dragon axe
			useSpecialAndSubtractDelay(10);
			c.getFrameMethodHandler().stillgfxz(479, c.absY, c.absX, 50, 20);
			c.getFrameMethodHandler().stillgfxz(479, c.absY, c.absX, 50, 30);
			c.getFrameMethodHandler().stillgfxz(479, c.absY, c.absX, 50, 40);
			return original+(int)((double)original/10.0); //player hit + 10%
		}    
		if (playerEquipment[playerWeapon] == 15335){ // Saradomin Godsword
			useSpecialAndSubtractDelay(5);
			if (original <= 20){ //always heals for minimum of 10
				c.getClientMethodHandler().heal((c.playerLevel[c.playerHitpoints] + 10));

				c.playerLevel[5] += 5; //always restores for minimum of 5 prayer

				if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5])) 
					c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);

				c.getFrameMethodHandler().sendFrame126(""+c.playerLevel[5]+"", 4012);
				c.requirePlayerUpdate();
			}
			if (original >= 20){ //heals for half the hit, prayer for 1/4
				c.getClientMethodHandler().heal(c.playerLevel[c.playerHitpoints] + (original/2));

				c.playerLevel[5] += (original/4); //always restores for minimum of 5 prayer

				if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5])) 
					c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);

				c.getFrameMethodHandler().sendFrame126(""+c.playerLevel[5]+"", 4012);
				c.requirePlayerUpdate();
			}
			return original;
		}    

		if (playerEquipment[playerWeapon] == 15336){ //Zamorak Godsword
			if(c.getEnemy().isPlayer()){
				usedZamorakSpecial = true;
			}
			useSpecialAndSubtractDelay(6);
			c.getFrameMethodHandler().stillgfxz(368, Y, X, 50, 50);
			c.getFrameMethodHandler().stillgfxz(382, Y, X, 50, 50);
			return original + misc.random(c.playerLevel[c.playerMagic]/3);	                	
		}    

		if (playerEquipment[playerWeapon] == 15351){ //Saradomin Sword
			useSpecialAndSubtractDelay(10);
			c.getFrameMethodHandler().stillgfxz(119, Y, X, 50, 75);
			c.getFrameMethodHandler().stillgfxz(76, Y, X, 50, 60);
			c.getFrameMethodHandler().stillgfxz(85, Y, X, 50, 75);
			return original+5+misc.random(10)+misc.random(c.playerLevel[c.playerMagic]/7); //original + 5-15 dmg + random magic dmg
		}    
		if (playerEquipment[playerWeapon] == 11337){
			c.DClawsHit1 = true;
			useSpecialAndSubtractDelay(10);
			c.DClawsTimer = 10;
			if (original > 0){
				c.DClawsDmg = original+9;
				return original+9;
			}
			if (original <= 0){
				c.DClawsDmg = 0;
				return 0;
			}
		}
		if (playerEquipment[playerWeapon] == 1305){
			useSpecialAndSubtractDelay(4);
			c.getFrameMethodHandler().stillgfxz(248, c.absY, c.absX, 100, 28);
			c.getFrameMethodHandler().stillgfxz(248, c.absY, c.absX, 100, 30);
			c.getFrameMethodHandler().stillgfxz(248, c.absY, c.absX, 100, 32);
			return original+misc.random(c.playerLevel[c.playerAttack]/7); //original random playerattack/7
		}       
		if (playerEquipment[playerWeapon] == 4151){ //Abby whip
			useSpecialAndSubtractDelay(5);
			c.getFrameMethodHandler().stillgfxz(341, Y, X, 100, 25);
			if (original == 0 && misc.random(1) == 0){ //if hit is zero, 50% chance to recalculate
				int c2 = misc.random(getMaxMeleeHit());
				if (c2 == 0) // if player hits zero again
					return 0;
				original = c2;
			}
			c.runningEnergy += original/10; //adds 10% of hit to energy
			return original; //return hit
		}    
		if (playerEquipment[playerWeapon] == 7158){ //Dragon 2h
			useSpecialAndSubtractDelay(6);
			c.getFrameMethodHandler().stillgfxz(246, c.absY, c.absX, 0, 20);
			if (c.getEnemy().isNPC())
				attackNPCSWithin(original, 2, c.absX, c.absY);    		
			return original; //
		} 
		if (playerEquipment[playerWeapon] == 3204){
			useSpecialAndSubtractDelay(3);
			c.getFrameMethodHandler().stillgfxz(284, Y, X, 100, 25);
			c.getFrameMethodHandler().stillgfxz(284, Y, X, 100, 30);
			c.getFrameMethodHandler().stillgfxz(284, Y, X, 100, 35);
			return original+misc.random(c.playerLevel[c.playerAttack]/9); //original and small bonus
		} 
		if (playerEquipment[playerWeapon] == 1434){
			useSpecialAndSubtractDelay(2);
			c.getFrameMethodHandler().stillgfxz(251, c.absY, c.absX, 100, 25);
			c.getFrameMethodHandler().stillgfxz(251, c.absY, c.absX, 100, 27);
			c.getFrameMethodHandler().stillgfxz(251, c.absY, c.absX, 100, 29);
			return original+misc.random(c.playerLevel[c.playerStrength]/9); //original and small bonus
		} 
		if (playerEquipment[playerWeapon] == 4153){ //Granite Maul
			useSpecialAndSubtractDelay(5);
			c.SpecTimer = 4;
			c.getFrameMethodHandler().stillgfxz(337, Y, X, 100, 10);
			return original; //original and extra hit
		} 
		if (playerEquipment[playerWeapon] == 4587){ 
			useSpecialAndSubtractDelay(5);
			c.getFrameMethodHandler().stillgfxz(347, Y, X, 100, 30);
			return original + misc.random(c.playerLevel[c.playerPrayer]/9); //original and small bonus
		} 
		if (playerEquipment[playerWeapon] == 5698 || playerEquipment[playerWeapon] == 1215 || playerEquipment[playerWeapon] == 1231 || playerEquipment[playerWeapon] == 5680){ //dragon daggers
			useSpecialAndSubtractDelay(3);
			c.SpecTimer = 3;
			return original + misc.random(c.playerLevel[c.playerRanged]/11); //original and small bonus
		} 

		if(playerEquipment[playerWeapon] == Item.KARILSCROSSBOW){
			useSpecialAndSubtractDelay(2);
			c.SpecTimer = 3;
			c.getFrameMethodHandler().stillgfx(246, c.absY, c.absX);
			if(c.getEnemy().isNPC()){
				c.BOWHANDLER.arrowProjectile(c.getEnemy().getNPCId());
			}
			if (c.getEnemy().isPlayer()){
				c.BOWHANDLER.arrowProjectilePlayer(c.getEnemy().getPlayerIndex());
			}
			return (int)(original*1.1); // +25%
		}

		if (playerEquipment[playerWeapon] == Item.CRYSTALBOW){
			useSpecialAndSubtractDelay(10);
			c.SpecTimer = 3;
			c.getFrameMethodHandler().gfx100(250);
			c.getFrameMethodHandler().stillgfx(332, c.absY, c.absX);
			if(c.getEnemy().isPlayer()) c.BOWHANDLER.arrowProjectilePlayer(c.getEnemy().getPlayerIndex());
			else c.BOWHANDLER.arrowProjectile(c.getEnemy().getNPCId());
			return original*2; //double original
		}

		if (playerEquipment[playerWeapon] == Item.DARKBOW){
			useSpecialAndSubtractDelay(6);
			c.SpecTimer = 3;
			int X3 = c.getEnemy().getX();
			int Y3 = c.getEnemy().getY();
			int offsetX = (c.absX - X3) * -1;
			int offsetY = (c.absY - Y3) * -1;
			c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, c.BOWHANDLER.getArrowGFX(), 43, 31, c.getEnemy().getID()+1,40);
			c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, c.BOWHANDLER.getArrowGFX(), 43, 31, c.getEnemy().getID()+1,50);
			return original + (int)(original*0.3); //original and 30% bonus
		}

		if (playerEquipment[playerWeapon] == 861){ //magic shortbow
			useSpecialAndSubtractDelay(5);
			c.SpecTimer = 3;
			c.getFrameMethodHandler().gfx100(250);
			int X3 = c.getEnemy().getX();
			int Y3 = c.getEnemy().getY();
			int offsetX = (c.absX - X3) * -1;
			int offsetY = (c.absY - Y3) * -1;
			c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 60, 249, 43, 31, c.getEnemy().getID()+1,40);
			c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, 249, 43, 31, c.getEnemy().getID()+1,50);
			return original + misc.random(c.playerLevel[c.playerAttack]/25); //original and small bonus
		} 

		if (FullGuthanEquipped()){
			if(misc.random(3) == 0) { //25% chance
				c.getClientMethodHandler().heal(original);
				c.sendMessage("You drain your enemy's health.");
				c.getFrameMethodHandler().stillgfx(398, c.absY, c.absX);
			}
		}
		if(FullDharokEquipped()){
			return original + (c.getLevelForXP(c.playerXP[c.playerHitpoints]) - c.playerLevel[c.playerHitpoints])/2;
		}

		return original;
	} 
	
	
	public boolean doIHitEnemyWithMagic(Enemy e){
		if(e.isNPC()){
			NPC n = e.getNPC();
			int npcMaxHP = n.MaxHP;
			int enemyBonus = npcMaxHP;
			int myBonus = c.playerLevel[c.playerMagic]+c.playerBonus[atkBonus[magic]];
			return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
		}
		else{
			client enemy = e.getPlayerClient(); //opponent's client
			int enemyBonus = enemy.playerLevel[enemy.playerDefence]+enemy.playerBonus[defBonus[magic]];
			int myBonus = c.playerLevel[c.playerMagic]+c.playerBonus[atkBonus[magic]];
			return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);	
		}
	}
	
	public boolean doIHitEnemyWithRanged(Enemy e){
		if(e.isNPC()){
			NPC n = e.getNPC();
			int npcMaxHP = n.MaxHP;
			int enemyBonus = npcMaxHP;
			int myBonus = c.playerLevel[c.playerRanged]+c.playerBonus[atkBonus[range]];
			return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
		}
		else{
			client enemy = e.getPlayerClient(); //opponent's client
			int enemyBonusEquipment = enemy.playerBonus[defBonus[range]];
			int enemyBonus = enemy.playerLevel[enemy.playerDefence]+enemyBonusEquipment;
			int myBonus = c.playerLevel[c.playerRanged]+c.playerBonus[atkBonus[range]];
			return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
		}
	}
	
	public boolean doIHitEnemyWithMelee(Enemy e){
		if(e.isNPC()){
			NPC npcEnemy = e.getNPC();
			int npcMaxHP = npcEnemy.MaxHP;
			int enemyBonus = npcMaxHP; //TODO - npc armor maybe
			int myPrayerBonus = (int)(c.attEffect*0.01);
			int myBonus = c.playerLevel[c.playerAttack]+getPlayerMeleeAtkEquipmentBonus()+myPrayerBonus;
			
			return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
		}
		else{
			client playerEnemy = e.getPlayerClient();
			
			//max attribute of all their slash,stab,crush def - since fighting styles are not reliable enough
			int enemyBonusEquipment = Math.max(playerEnemy.playerBonus[defBonus[stab]], playerEnemy.playerBonus[defBonus[slash]]);
			enemyBonusEquipment = Math.max(enemyBonusEquipment, playerEnemy.playerBonus[defBonus[crush]]);

			int enemyBonus = playerEnemy.playerLevel[playerEnemy.playerDefence]+enemyBonusEquipment;

			int myBonusEquipment = Math.max(c.playerBonus[atkBonus[stab]], c.playerBonus[atkBonus[slash]]);
			myBonusEquipment = Math.max(c.playerBonus[atkBonus[slash]], myBonusEquipment);

			int myPrayerBonus = (int)(c.attEffect*0.01);

			int myBonus = c.playerLevel[c.playerAttack]+c.playerLevel[c.playerStrength]+myBonusEquipment+myPrayerBonus;

			return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
		}
	}	
	
	/**
	 * Will randomize both numbers and compare.
	 * If any number is beneath 2, then the default value is set to 2.
	 * @return True if random(myBonus) > random(enemyBonus)
	 */
	public boolean isMyBonusGreaterThanTheEnemy(int myBonus, int enemyBonus){
		if(enemyBonus < 2) enemyBonus = 2;
		if(myBonus < 2) myBonus = 2; 
		int myRandom = misc.random(myBonus); //declaring for debugging purposes
		int eRandom = misc.random(enemyBonus);
		c.debug("myBonus : "+myBonus+" enemyBonus : "+enemyBonus);
		return (myRandom > eRandom);
	}

	/**
	 * @param opp Will have its enemy set as this player. Will start combat to auto attack on the player.
	 */
	public void opponentAutoAttack(client opp){
		opp.setEnemy(new Enemy(this.c));
		opp.getCombatHandler().Attack();
	}

	public boolean canAttackOpponent(client opp){
		if(!c.getClientMethodHandler().isInPKZone()){
			c.sendMessage("You are in a safe zone.");
			resetAttack();
			return false;
		}
		if(!opp.getClientMethodHandler().isInPKZone()){
			c.sendMessage("That player is in a safe zone.");
			resetAttack();
			return false;
		}
		return true;
	}

	public void attackPlayersWithin2(int gfx, int maxDamage, int range) {
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(c.absX, c.absY) <= range && person.playerId != c.playerId)
					{
						int damage = misc.random(maxDamage);
						hitPlayer(person.playerId, damage);
					}
				}
			}
		}
	}

	public void attackPlayersWithin(int gfx, int maxDamage, int range) {
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(c.absX, c.absY) <= range && person.playerId != c.playerId)
					{
						int damage = misc.random(maxDamage);
						hitPlayer(person.playerId, damage);
					}
				}
			}
		}
	}


	/**
	 * Will attack NPCs within a range
	 * @param maxDamage Randomizes this damage
	 */
	public void attackNPCSWithin(int maxDamage, int range, int x, int y) {
		for (int i = 1; i < server.npcHandler.maxNPCs; i++){
			if(server.npcHandler.npcs[i] != null){
				if(misc.GoodDistance(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY, x, y, range) && !server.npcHandler.npcs[i].IsDead)
				{
					int damage = misc.random(maxDamage);
					server.npcHandler.npcs[i].damageNPC(damage);
				}
			}
			else break;
		}
	}

	public boolean damagePlayer(int playerID, int damage){
		try{
			int playerHP = PlayerHandler.players[playerID].playerLevel[c.playerHitpoints];
			if (damage > playerHP) damage = playerHP;
			if (damage < 0) damage = 0;
			PlayerHandler.players[playerID].hitDiff = damage;
			PlayerHandler.players[playerID].hitUpdateRequired = true;
			PlayerHandler.players[playerID].updateRequired = true;
			PlayerHandler.players[playerID].appearanceUpdateRequired = true;
			return true;
		}
		catch(Exception e){
			c.error("In damagePlayer : "+e.getMessage());
			return false;
		}
	}

	public int getMaxRangedHit(){
		double max = 0;
		double A = c.playerLevel[c.playerRanged];
		double B = 1.0;
		double prayerBonuses = c.strEffect*0.01;
		double C = Math.floor(A*B); //effective strength
		if(c.FightType == 1) C += 3; //accurate

		double D = c.playerBonus[atkBonus[range]];

		max = 0.8 + (C/10.0) + (C*D)/640.0;
		max = (C+8)*(D+64)/640.0;

		return (int)Math.floor(max);
	}


	public int getMaxMeleeHit(){
		double max = 0;
		double A = c.playerLevel[c.playerStrength];
		double B = 1.0;
		double prayerBonuses = c.strEffect*0.01;
		double C = Math.floor(A*B); //effective strength
		if(c.FightType == 3) C += 3; //aggressive
		if(c.FightType == 1) C += 1; //controlled

		double D = c.playerBonus[strength];

		max = 0.8 + (C/10.0) + (C*D)/640.0;
		max = (C+8)*(D+64)/640.0;

		return (int)Math.floor(max);
	}

	public int getHighestMeleeDefEquipmentBonus(){
		return Math.max(c.playerBonus[defBonus[stab]], Math.max(c.playerBonus[defBonus[slash]], c.playerBonus[defBonus[crush]]));
	}

	public int getMagicDefEquipmentBonus(){
		return c.playerBonus[defBonus[magic]];
	}

	public int getRangeDefEquipmentBonus(){
		return c.playerBonus[defBonus[range]];
	}

	
	public boolean resetAttack() {
		c.setEnemy(null);
		c.pEmote = c.playerSE;
		
		c.faceNPC = 65535;
		c.faceNPCupdate = true;
		
		c.requirePlayerUpdate();
		return true;
	}

	/**
	 * Private helper method to assist with pvp attack loop
	 */
	public boolean hitPlayer(int playerId, int damage){
		try{
			c.facePlayer(playerId);
			
			client playerToHit = (client)server.playerHandler.players[playerId];
			c.KillerId = playerId;
			
			playerToHit.KillerId = c.playerId;
			playerToHit.inCombat();
			
			if(playerToHit.autoRetaliate == 1 && playerToHit.getEnemy() == null)
				opponentAutoAttack(playerToHit);
			
		if(playerToHit.SpecEmoteTimer == 0)
			playerToHit.startAnimation(Item.GetBlockAnim(playerToHit.playerEquipment[playerToHit.playerWeapon]));
			return damagePlayer(playerId, damage);
		}
		catch(Exception e){
			c.error("In Combat.hitPlayer, given npcID : "+playerId+", error: "+e.toString());
			return false;
		}
	}
	
	/**
	 * private helper method to assist with npc attack loop
	 */
	public boolean hitNPC(int npcID, int damage, int playerId){
		try{
			if ( (server.npcHandler.npcs[npcID].HP-damage) < 0)
				damage = server.npcHandler.npcs[npcID].HP;
			server.npcHandler.npcs[npcID].StartKilling = playerId;
			server.npcHandler.npcs[npcID].RandomWalk = false;
			server.npcHandler.npcs[npcID].IsUnderAttack = true;
			server.npcHandler.npcs[npcID].Killing[playerId] += damage;
			server.npcHandler.npcs[npcID].damageNPC(damage);
			server.npcHandler.npcs[npcID].updateRequired = true;
			int blockAnim = NPCAnim.getBlockAnimation(server.npcHandler.npcs[npcID].npcType);
			if(blockAnim != -1)
				server.npcHandler.npcs[npcID].animNumber = blockAnim;
			server.npcHandler.npcs[npcID].updateAttackingPlayers(playerId, damage);
			return true;
		}
		catch(Exception e){
			c.error("In Combat.hitNPC, given npcID : "+npcID+", error: "+e.toString());
			return false;
		}
	}


	private void addCombatXP(int damage){
		if(damage != 0){
			double TotalExp = 0;
			if (c.FightType != 3) {
				TotalExp = (double)(200 * damage);
				TotalExp = (double)(TotalExp * c.CombatExpRate);
				c.getClientMethodHandler().addSkillXP((int)(TotalExp), c.SkillID);
			}
			else {
				TotalExp = (double)(200 * damage);
				TotalExp = (double)(TotalExp * c.CombatExpRate);
				c.getClientMethodHandler().addSkillXP((int)(TotalExp), c.playerAttack);
				c.getClientMethodHandler().addSkillXP((int)(TotalExp), c.playerDefence);
				c.getClientMethodHandler().addSkillXP((int)(TotalExp), c.playerStrength);
			}
			TotalExp = (double)(200 * damage);
			TotalExp = (double)(TotalExp * c.CombatExpRate);
			c.getClientMethodHandler().addSkillXP((int)(TotalExp), c.playerHitpoints);
		}
	}

	private void addCombatRangedXP(int damage){
		if(damage != 0){
			double TotalExp = 0;
			TotalExp = (double)(200 * damage);
			TotalExp = (double)(TotalExp * c.CombatExpRate);
			c.getClientMethodHandler().addSkillXP((int)(TotalExp), c.playerRanged);
			TotalExp = (double)(200 * damage);
			TotalExp = (double)(TotalExp * c.CombatExpRate);
			c.getClientMethodHandler().addSkillXP((int)(TotalExp), c.playerHitpoints);
		}
	}

	public int getSpecialDamageAndUpdateFillingBar(int damage){
		if (c.litBar){
			Enemy e = c.getEnemy();
			damage = checkSpecials(damage, e.getY(), e.getX());
			c.getFrameMethodHandler().getFilling();
		}
		return damage;
	}

	//TODO
	public void Attack() {
		Enemy e = c.getEnemy();

		if (e.isDead()){
			resetAttack();
			return;
		}

		if(c.LoopAttDelay > 0)
			return;

		c.faceEnemy(e);

		//default to melee
		int distance = 1; 
		int playerWeapon = c.playerEquipment[c.playerWeapon];

		if(Item.isBow(playerWeapon)){
			distance = Item.ifHasBowAndAmmoUpdateDelay(c);
			if(distance == -1){
				c.sendMessage("You need the correct ammo to use this ranged weapon.");
				c.stopPlayerMovement();
				resetAttack();
				return;
			}
		}

		boolean halberd = false;
		for(int i = 0; i < Item.halberds.length; i++){
			if(playerWeapon == Item.halberds[i]){
				halberd = true;
				distance = 2;
				break;
			}
		}

		int EnemyX = e.getX();
		int EnemyY = e.getY();
		int hitDiff = 0;

		int EnemyDistance = misc.distanceBetweenPoints(EnemyX, EnemyY, c.absX, c.absY);

		/* Melee */
		if((distance == 1 && !c.autocast) || halberd) { 
			if(e.getNPCType() == 3001){
				c.sendMessage("You need a ranged weapon to attack this monster.");
				c.stopPlayerMovement();
				resetAttack();
				return;
			}

			if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, distance)) {
				c.startAnimation(Item.GetWepAnim(c));

				c.PkingDelay = Item.getItemDelay(c.playerEquipment[c.playerWeapon]);
				c.LoopAttDelay = c.PkingDelay;

				hitDiff = misc.random(getMaxMeleeHit());

				if(!doIHitEnemyWithMelee(e)) hitDiff = 0;

				hitDiff = getSpecialDamageAndUpdateFillingBar(hitDiff);

				if(e.getNPCType() == 277){
					c.sendMessage("The fire warrior can only be hurt with Ice Arrows.");
					hitDiff = 0;
				}

				addCombatXP(hitDiff);
				c.inCombat(); 
				c.followingNPCID = -1;

				e.inflictMeleeDamage(hitDiff, c.playerId);
				return;
			}			
			else{
				c.followEnemy(e);
				return;
			}
		}
		/* Ranged */
		if(distance > 1 && !c.autocast){
			if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, distance)) {
				c.stopFollow();

				hitDiff = misc.random(getMaxRangedHit());

				if(!this.doIHitEnemyWithRanged(e)) hitDiff = 0;

				if (c.litBar){
					hitDiff = checkSpecials(hitDiff, EnemyY, EnemyX);
					c.getFrameMethodHandler().getFilling();
				}
				else{
					if(e.isNPC()){
						c.BOWHANDLER.arrowProjectile(e.getID());
					}
					c.startAnimation(c.BOWHANDLER.getBowEmote());
				}

				c.BOWHANDLER.checkForAccumulatorOrDistributeArrowOnGround(EnemyX, EnemyY);

				addCombatRangedXP(hitDiff);

				if(e.getNPCType() == 277 && c.playerEquipment[c.playerArrows] != 78){
					c.sendMessage("The fire warrior can only be hurt with Ice Arrows.");
					hitDiff = 0;
				}

				//pkingdelay updated when checking ammo and bow
				c.LoopAttDelay = c.PkingDelay;

				e.inflictRangeDamage(hitDiff, c.playerId);
				return;
			}	
			else{
				c.followEnemy(e);
				return;
			}
		}

		/* Magic */
		if(c.autocast){
			final int AUTOCASTDISTANCE = 6;
			if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, AUTOCASTDISTANCE)){ 
				c.stopPlayerMovement();

				if(e.isNPC()){
					c.getMagicHandler().magicOnNPC(e.getID());
					return;
				}
				else{
					c.getMagicHandler().AttackPlayerMagic(e.getID());
					return;
				}

			}	
			else{
				c.followEnemy(e);
				return;
			}
		}
	}


	public void DragonBaxeSpecial(){        
		c.litBar = false;
		if(c.playerLevel[2] < 120) {
			c.playerLevel[2] += 15;
			c.getClientMethodHandler().addSkillXP(0, 2);
			c.getFrameMethodHandler().stillgfx(246, c.absX, c.absY);
			c.requirePlayerUpdate();
			c.txt4 = "RRRRRAAAAAAAAAGGGGGGGGGHHHHH!!";
			c.string4UpdateRequired = true;
			c.AnimationReset = true;
			c.startAnimation(1056);
			c.actionTimer = 4;
			c.teleportToX = c.absX;
			c.teleportToY = c.absY;
			c.specialDelay = 0;
		}
		if (c.playerLevel[2] >= 120){
			c.sendMessage("You are already under the influence of a strength boost.");
		}
		c.getFrameMethodHandler().getFilling();
	}

	public void Dragon2hSpecial(){	
		c.litBar = false;
		c.getFrameMethodHandler().stillgfxz(246, c.absY, c.absX, 0, 50);	
		c.startAnimation(3157);
		attackNPCSWithin(getMaxMeleeHit(), 2, c.absX, c.absY); 
		c.AnimationReset = true;
		c.teleportToX = c.absX;
		c.teleportToY = c.absY;
		c.specialDelay -= 6;	
	}
	public void ExcaliburSpecial(){
		c.litBar = false;
		if (c.playerLevel[1] < 130){
			c.playerLevel[1] += 15;
			c.getClientMethodHandler().addSkillXP(0, 1); //forces refresh of tab for exc increase
			c.getFrameMethodHandler().stillgfx(247, c.absY, c.absX);
			c.requirePlayerUpdate();
			c.AnimationReset = true;
			c.startAnimation(1979);
			c.stopPlayerMovement();
			c.specialDelay -= 10;
			c.LoopAttDelay = 15;
			c.getFrameMethodHandler().getFilling();
		}
		if (c.playerLevel[1] >= 130){
			c.sendMessage("You are already under the influence of the maximum amount of defence boosts.");
		}
	}


}

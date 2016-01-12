
public class Combat {
	private client c = null;
	
	public Combat(client pc){
		this.c = pc;
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

public boolean doIHitNPCWithMagic(int npcIndex){
	if(server.npcHandler.npcs[npcIndex] == null) return false;
	int npcMaxHP = server.npcHandler.npcs[npcIndex].MaxHP;
	int enemyBonus = npcMaxHP;
	int myBonus = c.playerLevel[c.playerMagic]+c.playerBonus[atkBonus[magic]];
	return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
}

public boolean doIHitNPCWithRanged(int npcIndex){
	if(server.npcHandler.npcs[npcIndex] == null) return false;
	int npcMaxHP = server.npcHandler.npcs[npcIndex].MaxHP;
	int enemyBonus = npcMaxHP;
	int myBonus = c.playerLevel[c.playerRanged]+c.playerBonus[atkBonus[range]];
	return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
}

public boolean doIHitNPCWithMelee(int npcIndex){
	if(server.npcHandler.npcs[npcIndex] == null) return false;
	int npcMaxHP = server.npcHandler.npcs[npcIndex].MaxHP;
	int enemyBonus = npcMaxHP;
	
	int maxAtkBonus = Math.max(c.playerBonus[atkBonus[stab]], c.playerBonus[atkBonus[slash]]);
	maxAtkBonus = Math.max(maxAtkBonus, c.playerBonus[atkBonus[crush]]);
	
	int myPrayerBonus = (int)(c.attEffect*0.01);
	
	int myBonus = c.playerLevel[c.playerAttack]+maxAtkBonus+myPrayerBonus;
	
	return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
}

public boolean isMyBonusGreaterThanTheEnemy(int myBonus, int enemyBonus){
	if(enemyBonus < 2) enemyBonus = 2;
	if(myBonus < 2) myBonus = 2; 
	int myRandom = misc.random(myBonus); //declaring for debugging purposes
	int eRandom = misc.random(enemyBonus);
	c.debug("myBonus : "+myBonus+" enemyBonus : "+enemyBonus);
	return (myRandom > eRandom);
}

public boolean doIHitPlayerWithMelee(int index){
	if(server.playerHandler.players[index] == null) return false;
	client enemy = (client) server.playerHandler.players[index]; //opponent's client
	
	int enemyBonusEquipment = Math.max(enemy.playerBonus[defBonus[stab]], enemy.playerBonus[defBonus[slash]]);
	enemyBonusEquipment = Math.max(enemyBonusEquipment, enemy.playerBonus[defBonus[crush]]);
	
	int enemyBonus = enemy.playerLevel[enemy.playerDefence]+enemyBonusEquipment;
	
	int myBonusEquipment = Math.max(c.playerBonus[atkBonus[stab]], c.playerBonus[atkBonus[slash]]);
	myBonusEquipment = Math.max(c.playerBonus[atkBonus[slash]], myBonusEquipment);
	
	int myPrayerBonus = (int)(c.attEffect*0.01);
	
	int myBonus = c.playerLevel[c.playerAttack]+myBonusEquipment+myPrayerBonus;
	
	return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
}

public boolean doIHitPlayerWithRanged(int index){
	if(server.playerHandler.players[index] == null) return false;
	client enemy = (client) server.playerHandler.players[index]; //opponent's client
	int enemyBonusEquipment = enemy.playerBonus[defBonus[range]];
	int enemyBonus = enemy.playerLevel[enemy.playerDefence]+enemyBonusEquipment;
	int myBonus = c.playerLevel[c.playerRanged]+c.playerBonus[atkBonus[range]];
	return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);
}

public boolean doIHitPlayerWithMagic(int index) {
	if(server.playerHandler.players[index] == null){ 
		c.error("In MageHit, given Null index");
		return false;
	}
	client enemy = (client) server.playerHandler.players[index]; //opponent's client
	int enemyBonus = enemy.playerLevel[enemy.playerDefence]+enemy.playerBonus[defBonus[magic]];
	int myBonus = c.playerLevel[c.playerMagic]+c.playerBonus[atkBonus[magic]];
	return isMyBonusGreaterThanTheEnemy(myBonus,enemyBonus);	
}

public void opponentAutoAttack(client opp){
	opp.IsAttacking = true;
	opp.AttackingOn = playerId;
	opp.Attack();
}

public boolean canAttackOpponent(client opp){
	if(!isInPKZone()){
		sendMessage("You are in a safe zone.");
		ResetAttack();
		return false;
	}
	if(!opp.isInPKZone()){
		sendMessage("That player is in a safe zone.");
		ResetAttack();
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
       if(person.distanceToPoint(absX, absY) <= range && person.playerId != playerId)
        {
         int damage = misc.random(maxDamage);
					updateDelayAndDamagePlayer(person.playerId, damage);
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
       if(person.distanceToPoint(absX, absY) <= range && person.playerId != playerId)
        {
         int damage = misc.random(maxDamage);
					updateDelayAndDamagePlayer(person.playerId, damage);
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
	      if(GoodDistance(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY, x, y, range) && !server.npcHandler.npcs[i].IsDead)
	       {
	        int damage = misc.random(maxDamage);
	        server.npcHandler.npcs[i].damageNPC(damage);
	      }
	    }
	   else break;
	  }
	}



/**
 * Private helper method for PVP combat. Will update attack delay as well.
 * @param playerID Player ID in players array
 * @param damage damage to inflict to player
 * @return true if damage inflicted successfully
 */
public boolean updateDelayAndDamagePlayer(int playerID, int damage){
	client opponentClient = (client) PlayerHandler.players[playerID];
	
	LoopAttDelay = PkingDelay;
	debug("LoopAttDelay : "+LoopAttDelay);
	playerSpamTimer = System.currentTimeMillis();
	faceNPC = 32768+playerID;
	faceNPCupdate = true;
	PlayerHandler.players[playerID].faceNPC = 32768+AttackingOn;
	PlayerHandler.players[playerID].faceNPCupdate = true;
	opponentClient.KillerId = playerId;
	opponentClient.inCombat();
	if(opponentClient.autoRetaliate == 1 && !opponentClient.IsAttacking) //1 means on
		opponentAutoAttack(opponentClient);
	inCombat();
	if (opponentClient.SpecEmoteTimer == 0)
		opponentClient.setAnimation(GetBlockAnim(opponentClient.playerEquipment[opponentClient.playerWeapon]));
	return damagePlayer(playerID, damage);
}

public boolean damagePlayer(int playerID, int damage){
	try{
		int playerHP = PlayerHandler.players[playerID].playerLevel[playerHitpoints];
		if (damage > playerHP) damage = playerHP;
		if (damage < 0) damage = 0;
		PlayerHandler.players[playerID].hitDiff = damage;
		PlayerHandler.players[playerID].hitUpdateRequired = true;
		PlayerHandler.players[playerID].updateRequired = true;
		PlayerHandler.players[playerID].appearanceUpdateRequired = true;
		return true;
	}
	catch(Exception e){
		error("In damagePlayer : "+e.getMessage());
		return false;
	}
}

public boolean Attack() {
	isPVP = true;
	int EnemyX = PlayerHandler.players[AttackingOn].absX;
	int EnemyY = PlayerHandler.players[AttackingOn].absY;
	int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
	client opponentClient = (client) server.playerHandler.players[AttackingOn]; //opponent's client
	if (opponentClient == null){
		error("In Attack, opponent client is null");
		return false;
	}
		
	if(!canAttackOpponent(opponentClient))
		return false;

	int distance = Item.ifHasBowAndAmmoUpdateDelay(this);
	if(distance == -1){
		sendMessage("You need ammo to use this ranged weapon.");
		updatePlayerPosition();
		ResetAttack();
		return false;
	}
	if (!GoodDistance(EnemyX, EnemyY, absX, absY, distance) && !autocast) //autocast is handled seperately
		followingPlayerID = AttackingOn;
	if(LoopAttDelay > 1)
		return false;

	/* Melee */
	if(distance == 1 && !autocast) { 
		
		if(lists.halberd.exists(playerEquipment[playerWeapon]))
			distance = 2;
		
		if (GoodDistance(EnemyX, EnemyY, absX, absY, distance)) {
			if (PlayerHandler.players[AttackingOn].IsDead == true) {
				ResetAttack();
				return false;
			}
			
			followingPlayerID = -1;

			PkingDelay = Item.getItemDelay(playerEquipment[playerWeapon]);

			setAnimation(GetWepAnim());
			
			int damage = getMaxMeleeHit(); 

			if(litBar){
				damage = checkSpecials(damage, opponentClient.absY, opponentClient.absX);
				getFilling();
			}

			damage -= opponentClient.playerMeleeDefBonus(); //accounts for opponent's defence level

			if(!Hit(AttackingOn)) damage = 0;

			if(opponentClient.PMelee)
				damage = (int)((double)damage*0.6); //protects for 40% in PvP

			if(usedBandosSpecial){
				usedBandosSpecial = false;
				applyBandosSpecial(opponentClient, damage);
			}
			if(usedZamorakSpecial){
				usedZamorakSpecial = false;
				opponentClient.frozen(20);
				opponentClient.stillgfxz(369, opponentClient.absY, opponentClient.absX, 0, 20);
			}

			addCombatXP(damage);
			return updateDelayAndDamagePlayer(AttackingOn, damage);
		}
	}

	/* Ranged */
	if(distance > 1 && !autocast){
		if (GoodDistance(EnemyX, EnemyY, absX, absY, distance)) {
			//actionAmount++;
			//setAnimation(playerSEA);
			followingPlayerID = -1;
			
			updatePlayerPosition();

			int damage = misc.random(getMaxRangedHit());

			if(litBar){
				damage = checkSpecials(damage, opponentClient.absY, opponentClient.absX);
				getFilling();
			}
			else this.BOWHANDLER.arrowProjectilePlayer(AttackingOn);

			if(!RangeHit(AttackingOn)) damage = 0;

			if(opponentClient.PRange)
				damage = (int)((double)damage*0.6); //protects for 40% in PvP

			setAnimation(this.BOWHANDLER.getBowEmote());

			checkForAccumulatorOrDistributeArrowOnGround(EnemyX, EnemyY);

			addCombatRangedXP(damage);

			return updateDelayAndDamagePlayer(AttackingOn, damage);			
		}
	}
	
	/* Magic */
	if(autocast){
		return AttackPlayerMagic(AttackingOn);
	}	

	isPVP = false;
	return false;
}

public boolean ResetAttack() {
	IsAttacking = false;
	AttackingOn = 0;
  pEmote = playerSE;
	requirePlayerUpdate();
	return true;
}


/**
 * Will set the loop attack delay, hit delay timer, and call hitNPC
 * @param npcID - NPC ID in the array of current NPCs
 * @param damage - Damage to inflict
 * @return True if the hit was successful, false otherwise
 */
public boolean updateDelayAndHitNPC(int npcID, int damage){
	LoopAttDelay = PkingDelay;
	hitDelayTimer = System.currentTimeMillis();
	return hitNPC(attacknpc, damage);
}

private void addCombatXP(int damage){
	if(damage != 0){
		double TotalExp = 0;
		if (FightType != 3) {
			TotalExp = (double)(200 * damage);
			TotalExp = (double)(TotalExp * CombatExpRate);
			addSkillXP((int)(TotalExp), SkillID);
		}
		else {
			TotalExp = (double)(200 * damage);
			TotalExp = (double)(TotalExp * CombatExpRate);
			addSkillXP((int)(TotalExp), playerAttack);
			addSkillXP((int)(TotalExp), playerDefence);
			addSkillXP((int)(TotalExp), playerStrength);
		}
		TotalExp = (double)(200 * damage);
		TotalExp = (double)(TotalExp * CombatExpRate);
		addSkillXP((int)(TotalExp), playerHitpoints);
	}
}

private void addCombatRangedXP(int damage){
	if(damage != 0){
		double TotalExp = 0;
		TotalExp = (double)(200 * damage);
		TotalExp = (double)(TotalExp * CombatExpRate);
		addSkillXP((int)(TotalExp), playerRanged);
		TotalExp = (double)(200 * damage);
		TotalExp = (double)(TotalExp * CombatExpRate);
		addSkillXP((int)(TotalExp), playerHitpoints);
	}
}


public boolean AttackNPC() {
	
	if(LoopAttDelay > 0)
		return false;
	
	if (server.npcHandler.npcs[attacknpc].IsDead){
		ResetAttackNPC();
		return false;
	}
	
	int EnemyX = server.npcHandler.npcs[attacknpc].absX;
	int EnemyY = server.npcHandler.npcs[attacknpc].absY;
	int hitDiff = 0;

	faceNPC(attacknpc);

	if(server.npcHandler.npcs[attacknpc].followPlayer < 1 || server.npcHandler.npcs[attacknpc].followPlayer == playerId) {

		int distance = Item.ifHasBowAndAmmoUpdateDelay(this);
		if(distance == -1){
			sendMessage("You need ammo to use this ranged weapon.");
			updatePlayerPosition();
			ResetAttackNPC();
			return false;
		}
		
		/* Melee */
		if(distance == 1 && !autocast) { 
			
			if(lists.halberd.exists(playerEquipment[playerWeapon]))
				distance = 2;
			
			PkingDelay = Item.getItemDelay(playerEquipment[playerWeapon]);
			if (GoodDistance(EnemyX, EnemyY, absX, absY, distance)) {
				if(distance > 1)
					updatePlayerPosition();
				
				setAnimation(GetWepAnim());
				
				hitDiff = misc.random(getMaxMeleeHit());
				
				if(!HitNPCMelee(attacknpc)) hitDiff = 0;
				
				hitDiff = getSpecialDamageAndModifySpecialDelay(hitDiff);
				
				addCombatXP(hitDiff);
				inCombat(); 
			
				return updateDelayAndHitNPC(attacknpc, hitDiff);
			}			
		}
		/* Ranged */
		if(distance > 1 && !autocast){
			if (GoodDistance(EnemyX, EnemyY, absX, absY, distance)) {
				updatePlayerPosition();
				
				hitDiff = misc.random(getMaxRangedHit());

				if(!RangeHitNPC(attacknpc)) hitDiff = 0;

				if (litBar){
					hitDiff = checkSpecials(hitDiff, server.npcHandler.npcs[attacknpc].absY, server.npcHandler.npcs[attacknpc].absX);
					getFilling();
				}
				else this.BOWHANDLER.arrowProjectile(attacknpc);

				checkForAccumulatorOrDistributeArrowOnGround(EnemyX, EnemyY);

				addCombatRangedXP(hitDiff);
				setAnimation(this.BOWHANDLER.getBowEmote());
				return updateDelayAndHitNPC(attacknpc, hitDiff);
			}
		}
				
		/* Magic */
		if(autocast){
			final int AUTOCASTDISTANCE = 6;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, AUTOCASTDISTANCE)){ 
				if(magicOnNPC(attacknpc))
					return true;
				else{
					updatePlayerPosition();
					ResetAttackNPC();
					return false;
				}
			}
		}
		
	}
	else {
		error("In AttackNPC");
	}
	return false;
}

	public boolean ResetAttackNPC() {
		ResetAttack();
		if (attacknpc > -1 && attacknpc < server.npcHandler.maxNPCs) {
			server.npcHandler.npcs[attacknpc].IsUnderAttack = false;
		}
		IsAttackingNPC = false;
		attacknpc = -1;
    pEmote = playerSE;
    faceNPC = 65535;
    faceNPCupdate = true;
		return true;
	}
	
}


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

		if (playerEquipment[playerWeapon] == 15334){ //Bandos godsword
			if(c.IsAttacking && c.AttackingOn != 0){
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
			if(c.IsAttacking && c.AttackingOn != 0){
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
			if (c.IsAttackingNPC)
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
			if(c.IsAttackingNPC){
				int EnemyX = server.npcHandler.npcs[c.attacknpc].absX;
				int EnemyY = server.npcHandler.npcs[c.attacknpc].absY;
				int offsetX = (c.absX - EnemyX) * -1;
				int offsetY = (c.absY - EnemyY) * -1;
				c.BOWHANDLER.arrowProjectile(c.attacknpc);
			}
			if (c.IsAttacking){
				int X3 = PlayerHandler.players[c.AttackingOn].absX;
				int Y3 = PlayerHandler.players[c.AttackingOn].absY;
				int offsetX = (c.absX - X3) * -1;
				int offsetY = (c.absY - Y3) * -1;
				c.BOWHANDLER.arrowProjectilePlayer(c.AttackingOn);
			}
			return (int)(original*1.1); // +25%
		}

		if (playerEquipment[playerWeapon] == Item.CRYSTALBOW){
			useSpecialAndSubtractDelay(10);
			c.SpecTimer = 3;
			c.getFrameMethodHandler().gfx100(250);
			c.getFrameMethodHandler().stillgfx(332, c.absY, c.absX);
			if(c.IsAttacking) c.BOWHANDLER.arrowProjectilePlayer(c.AttackingOn);
			else c.BOWHANDLER.arrowProjectile(c.attacknpc);
			return original*2; //double original
		}

		if (playerEquipment[playerWeapon] == Item.DARKBOW){
			useSpecialAndSubtractDelay(6);
			c.SpecTimer = 3;
			if(c.IsAttackingNPC){
				int EnemyX = server.npcHandler.npcs[c.attacknpc].absX;
				int EnemyY = server.npcHandler.npcs[c.attacknpc].absY;
				int offsetX = (c.absX - EnemyX) * -1;
				int offsetY = (c.absY - EnemyY) * -1;
				c.getFrameMethodHandler().gfx100(c.BOWHANDLER.getDrawbackArrowGFX());
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, c.BOWHANDLER.getArrowGFX(), 43, 31, c.attacknpc+1,40);
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, c.BOWHANDLER.getArrowGFX(), 43, 31, c.attacknpc+1,50);
			}
			if (c.IsAttacking){
				int X3 = PlayerHandler.players[c.AttackingOn].absX;
				int Y3 = PlayerHandler.players[c.AttackingOn].absY;
				int offsetX = (c.absX - X3) * -1;
				int offsetY = (c.absY - Y3) * -1;
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, c.BOWHANDLER.getArrowGFX(), 43, 31, c.AttackingOn+1,40);
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, c.BOWHANDLER.getArrowGFX(), 43, 31, c.AttackingOn+1,50);
			}
			return original + (int)(original*0.3); //original and 30% bonus
		}

		if (playerEquipment[playerWeapon] == 861){ //magic shortbow
			useSpecialAndSubtractDelay(5);
			c.SpecTimer = 3;
			c.getFrameMethodHandler().gfx100(250);
			if(c.IsAttackingNPC){
				int EnemyX = server.npcHandler.npcs[c.attacknpc].absX;
				int EnemyY = server.npcHandler.npcs[c.attacknpc].absY;
				int offsetX = (c.absX - EnemyX) * -1;
				int offsetY = (c.absY - EnemyY) * -1;
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 60, 249, 43, 31, c.attacknpc+1,40);
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, 249, 43, 31, c.attacknpc+1,50);
			}
			if (c.IsAttacking){
				int X3 = PlayerHandler.players[c.AttackingOn].absX;
				int Y3 = PlayerHandler.players[c.AttackingOn].absY;
				int offsetX = (c.absX - X3) * -1;
				int offsetY = (c.absY - Y3) * -1;
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 60, 249, 43, 31, c.AttackingOn+1,40);
				c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 70, 249, 43, 31, c.AttackingOn+1,50);
			}
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
		opp.AttackingOn = c.playerId;
		opp.getCombatHandler().Attack();
	}

	public boolean canAttackOpponent(client opp){
		if(!c.getClientMethodHandler().isInPKZone()){
			c.sendMessage("You are in a safe zone.");
			ResetAttack();
			return false;
		}
		if(!opp.getClientMethodHandler().isInPKZone()){
			c.sendMessage("That player is in a safe zone.");
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
					if(person.distanceToPoint(c.absX, c.absY) <= range && person.playerId != c.playerId)
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
					if(person.distanceToPoint(c.absX, c.absY) <= range && person.playerId != c.playerId)
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
				if(misc.GoodDistance(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY, x, y, range) && !server.npcHandler.npcs[i].IsDead)
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

		c.LoopAttDelay = c.PkingDelay;
		c.debug("LoopAttDelay : "+c.LoopAttDelay);
		c.playerSpamTimer = System.currentTimeMillis();
		c.faceNPC = 32768+playerID;
		c.faceNPCupdate = true;
		opponentClient.KillerId = c.playerId;
		opponentClient.inCombat();
		if(opponentClient.autoRetaliate == 1 && !opponentClient.IsAttacking) //1 means on
			opponentAutoAttack(opponentClient);
		c.inCombat();
		if (opponentClient.SpecEmoteTimer == 0)
			opponentClient.startAnimation(Item.GetBlockAnim(opponentClient.playerEquipment[opponentClient.playerWeapon]));
		return damagePlayer(playerID, damage);
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

	public boolean Attack() {
		c.isPVP = true;
		int EnemyX = PlayerHandler.players[c.AttackingOn].absX;
		int EnemyY = PlayerHandler.players[c.AttackingOn].absY;
		int EnemyHPExp = PlayerHandler.players[c.AttackingOn].playerXP[c.playerHitpoints];
		client opponentClient = (client) server.playerHandler.players[c.AttackingOn]; //opponent's client
		if (opponentClient == null){
			c.error("In Attack, opponent client is null");
			return false;
		}

		if(!canAttackOpponent(opponentClient))
			return false;

		int distance = Item.ifHasBowAndAmmoUpdateDelay(c);
		if(distance == -1){
			c.sendMessage("You need ammo to use this ranged weapon.");
			c.updatePlayerPosition();
			ResetAttack();
			return false;
		}
		if (!misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, distance) && !c.autocast) //autocast is handled seperately
			c.followingPlayerID = c.AttackingOn;
		if(c.LoopAttDelay > 1)
			return false;

		/* Melee */
		if(distance == 1 && !c.autocast) { 

			if(lists.halberd.exists(c.playerEquipment[c.playerWeapon]))
				distance = 2;

			if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, distance)) {
				if (PlayerHandler.players[c.AttackingOn].IsDead == true) {
					ResetAttack();
					return false;
				}

				c.followingPlayerID = -1;

				c.PkingDelay = Item.getItemDelay(c.playerEquipment[c.playerWeapon]);

				c.startAnimation(Item.GetWepAnim(c));

				int damage = misc.random(getMaxMeleeHit());

				if(!doIHitPlayerWithMelee(c.AttackingOn)) damage = 0;

				damage = getSpecialDamageAndModifySpecialDelay(damage);

				if(damage != 0 && opponentClient.PMelee)
					damage = (int)((double)damage*0.6); //protects for 40% in PvP

				if(damage != 0 && usedBandosSpecial){
					usedBandosSpecial = false;
					applyBandosSpecial(opponentClient, damage);
				}
				if(damage != 0 && usedZamorakSpecial){
					usedZamorakSpecial = false;
					opponentClient.frozen(20);
					opponentClient.getFrameMethodHandler().stillgfxz(369, opponentClient.absY, opponentClient.absX, 0, 20);
				}

				addCombatXP(damage);
				return updateDelayAndDamagePlayer(c.AttackingOn, damage);
			}
		}

		/* Ranged */
		if(distance > 1 && !c.autocast){
			if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, distance)) {
				//actionAmount++;
				//setAnimation(playerSEA);
				c.followingPlayerID = -1;

				c.updatePlayerPosition();

				int damage = misc.random(getMaxRangedHit());

				if(!doIHitNPCWithRanged(c.AttackingOn)) damage = 0;

				if(c.litBar){
					damage = checkSpecials(damage, opponentClient.absY, opponentClient.absX);
					c.getFrameMethodHandler().getFilling();
				}
				else{
					c.BOWHANDLER.arrowProjectilePlayer(c.AttackingOn);
					c.startAnimation(c.BOWHANDLER.getBowEmote());
				}

				if(damage != 0 && opponentClient.PRange)
					damage = (int)((double)damage*0.6); //protects for 40% in PvP

				c.BOWHANDLER.checkForAccumulatorOrDistributeArrowOnGround(EnemyX, EnemyY);

				addCombatRangedXP(damage);

				return updateDelayAndDamagePlayer(c.AttackingOn, damage);			
			}
		}

		/* Magic */
		if(c.autocast){
			return c.MAGICDATAHANDLER.AttackPlayerMagic(c.AttackingOn);
		}	

		c.isPVP = false;
		return false;
	}

	public boolean ResetAttack() {
		c.IsAttacking = false;
		c.AttackingOn = 0;
		c.pEmote = c.playerSE;
		c.requirePlayerUpdate();
		return true;
	}

	/**
	 * private helper method to assist with npc attack loop
	 */
	private boolean hitNPC(int npcID, int damage){
		try{
			if ( (server.npcHandler.npcs[npcID].HP-damage) < 0)
				damage = server.npcHandler.npcs[npcID].HP;
			server.npcHandler.npcs[npcID].StartKilling = c.playerId;
			server.npcHandler.npcs[npcID].RandomWalk = false;
			server.npcHandler.npcs[npcID].IsUnderAttack = true;
			server.npcHandler.npcs[npcID].Killing[c.playerId] += damage;
			server.npcHandler.npcs[npcID].damageNPC(damage);
			server.npcHandler.npcs[npcID].updateRequired = true;
			int blockAnim = NPCAnim.getBlockAnimation(server.npcHandler.npcs[npcID].npcType);
			if(blockAnim != -1)
				server.npcHandler.npcs[npcID].animNumber = blockAnim;
			server.npcHandler.npcs[npcID].updateAttackingPlayers(c.playerId, damage);
			return true;
		}
		catch(Exception e){
			c.error("In hitNPC : "+e.toString());
			return false;
		}
	}


	/**
	 * Will set the loop attack delay, hit delay timer, and call hitNPC
	 * @param npcID - NPC ID in the array of current NPCs
	 * @param damage - Damage to inflict
	 * @return True if the hit was successful, false otherwise
	 */
	public boolean updateDelayAndHitNPC(int npcID, int damage){
		c.LoopAttDelay = c.PkingDelay;
		c.hitDelayTimer = System.currentTimeMillis();
		return hitNPC(c.attacknpc, damage);
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

	public int getSpecialDamageAndModifySpecialDelay(int damage){
		if (c.litBar){
			damage = checkSpecials(damage, server.npcHandler.npcs[c.attacknpc].absY, server.npcHandler.npcs[c.attacknpc].absX);
			c.getFrameMethodHandler().getFilling();
		}
		return damage;
	}


	public boolean AttackNPC() {

		if(c.LoopAttDelay > 0)
			return false;

		if (server.npcHandler.npcs[c.attacknpc].IsDead){
			ResetAttackNPC();
			return false;
		}

		int EnemyX = server.npcHandler.npcs[c.attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[c.attacknpc].absY;
		int hitDiff = 0;

		c.faceNPC(c.attacknpc);

		if(server.npcHandler.npcs[c.attacknpc].followPlayer < 1 || server.npcHandler.npcs[c.attacknpc].followPlayer == c.playerId) {

			int distance = Item.ifHasBowAndAmmoUpdateDelay(c);
			if(distance == -1){
				c.sendMessage("You need ammo to use this ranged weapon.");
				c.updatePlayerPosition();
				ResetAttackNPC();
				return false;
			}

			/* Melee */
			if(distance == 1 && !c.autocast) { 

				if(lists.halberd.exists(c.playerEquipment[c.playerWeapon]))
					distance = 2;

				c.PkingDelay = Item.getItemDelay(c.playerEquipment[c.playerWeapon]);
				if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, distance)) {
					if(distance > 1)
						c.updatePlayerPosition();

					c.startAnimation(Item.GetWepAnim(c));

					hitDiff = misc.random(getMaxMeleeHit());

					if(!doIHitNPCWithMelee(c.attacknpc)) hitDiff = 0;

					hitDiff = getSpecialDamageAndModifySpecialDelay(hitDiff);

					addCombatXP(hitDiff);
					c.inCombat(); 

					return updateDelayAndHitNPC(c.attacknpc, hitDiff);
				}			
			}
			/* Ranged */
			if(distance > 1 && !c.autocast){
				if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, distance)) {
					c.updatePlayerPosition();

					hitDiff = misc.random(getMaxRangedHit());

					if(!doIHitNPCWithRanged(c.attacknpc)) hitDiff = 0;

					if (c.litBar){
						hitDiff = checkSpecials(hitDiff, server.npcHandler.npcs[c.attacknpc].absY, server.npcHandler.npcs[c.attacknpc].absX);
						c.getFrameMethodHandler().getFilling();
					}
					else{
						c.BOWHANDLER.arrowProjectile(c.attacknpc);
						c.startAnimation(c.BOWHANDLER.getBowEmote());
					}

					c.BOWHANDLER.checkForAccumulatorOrDistributeArrowOnGround(EnemyX, EnemyY);

					addCombatRangedXP(hitDiff);
					return updateDelayAndHitNPC(c.attacknpc, hitDiff);
				}
			}

			/* Magic */
			if(c.autocast){
				final int AUTOCASTDISTANCE = 6;
				if (misc.GoodDistance(EnemyX, EnemyY, c.absX, c.absY, AUTOCASTDISTANCE)){ 
					if(c.MAGICDATAHANDLER.magicOnNPC(c.attacknpc))
						return true;
					else{
						c.updatePlayerPosition();
						ResetAttackNPC();
						return false;
					}
				}
			}

		}
		else {
			c.error("In AttackNPC");
		}
		return false;
	}

	public boolean ResetAttackNPC() {
		ResetAttack();
		if (c.attacknpc > -1 && c.attacknpc < server.npcHandler.maxNPCs) {
			server.npcHandler.npcs[c.attacknpc].IsUnderAttack = false;
		}
		c.IsAttackingNPC = false;
		c.attacknpc = -1;
		c.pEmote = c.playerSE;
		c.faceNPC = 65535;
		c.faceNPCupdate = true;
		return true;
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
			c.updatePlayerPosition();
			c.specialDelay -= 10;
			c.LoopAttDelay = 15;
			c.getFrameMethodHandler().getFilling();
		}
		if (c.playerLevel[1] >= 130){
			c.sendMessage("You are already under the influence of the maximum amount of defence boosts.");
		}
	}


	public void SpecDamgNPC(int maxDamage) {
		if(server.npcHandler.npcs[c.attacknpc] != null){
			if (server.npcHandler.npcs[c.attacknpc].IsDead == false) {
				int damage = misc.random(maxDamage);
				if (server.npcHandler.npcs[c.attacknpc].HP - damage < 0) 
					damage = server.npcHandler.npcs[c.attacknpc].HP;
				server.npcHandler.npcs[c.attacknpc].StartKilling = c.playerId;
				server.npcHandler.npcs[c.attacknpc].RandomWalk = false;
				server.npcHandler.npcs[c.attacknpc].IsUnderAttack = true;
				server.npcHandler.npcs[c.attacknpc].updateRequired = true;
				server.npcHandler.npcs[c.attacknpc].damageNPC(damage);
			} 
		}

	}

	/**
	 * inflicts direct damage to NPC with id c.attacknpc in npcs array
	 */
	public void SpecDamgNPC2(int directDamage) {
		if(server.npcHandler.npcs[c.attacknpc] != null) 
		{
			if (server.npcHandler.npcs[c.attacknpc].IsDead == false) {
				if (server.npcHandler.npcs[c.attacknpc].HP - directDamage < 0){ 
					directDamage = server.npcHandler.npcs[c.attacknpc].HP;
					c.DClawsTimer = 0; //if using DClaws spec
				}
				server.npcHandler.npcs[c.attacknpc].StartKilling = c.playerId;
				server.npcHandler.npcs[c.attacknpc].RandomWalk = false;
				server.npcHandler.npcs[c.attacknpc].IsUnderAttack = true;
				server.npcHandler.npcs[c.attacknpc].updateRequired = true;
				server.npcHandler.npcs[c.attacknpc].damageNPC(directDamage);
			} 
		}
	}


	/**
	 * randomizes maxDamage and inflicts on player with AttackingOn ID
	 */
	public void specialDamageOnPlayer(int maxDamage) {
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				if (PlayerHandler.players[c.AttackingOn].IsDead == false ) {
					int damage = misc.random(maxDamage);
					if (PlayerHandler.players[c.AttackingOn].playerLevel[3] - damage < 0) 
						damage = PlayerHandler.players[c.AttackingOn].playerLevel[3];
					PlayerHandler.players[c.AttackingOn].hitDiff = damage;
					PlayerHandler.players[c.AttackingOn].updateRequired = true;
					PlayerHandler.players[c.AttackingOn].hitUpdateRequired = true;
				}
			}
		}
	}

}

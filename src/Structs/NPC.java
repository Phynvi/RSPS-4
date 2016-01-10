import java.util.ArrayList;

public class NPC {
	private Tile currentTile;
	public int attackDistance = 1; //by default
	public int attackDelay = 7; //by default
	public boolean isAggressive, isAggressiveIgnoreCombatLevel;
	public int agroRange = 4; //by default
	public int npcId;
	public int npcType;
	public boolean attackable = true;
	public int PoisonDelay = 999999;
	public int PoisonClear = 0;
	public int absX, absY;
	public int heightLevel;
	public int makeX, makeY, moverangeX1, moverangeY1, moverangeX2, moverangeY2, moveX, moveY, direction, directionz, walkingType, attacknpc, followPlayer;
	public int spawnX, spawnY;
	public int viewX, viewY;
	public int HP, MaxHP, MaxHit, animNumber, actionTimer, StartKilling, enemyX, enemyY;
	public boolean IsDead, DeadApply, NeedRespawn, IsUnderAttack, IsClose, Respawns, IsUnderAttackNpc, IsAttackingNPC, poisondmg, walkingToPlayer, followingPlayer;
	public int[] Killing = new int[server.playerHandler.maxPlayers];
	public String followName = "";
	public boolean RandomWalk;
	public boolean dirUpdateRequired;
	public boolean animUpdateRequired;
	private boolean hitUpdateRequired;
	private int hitDiff;
	public boolean updateRequired;
	public boolean textUpdateRequired;
	public boolean faceToUpdateRequired;
	public boolean moveToSpawn = false;

	public int focusPointX, focusPointY;
	public boolean turnUpdateRequired; 

	public String textUpdate;
	private ArrayList<playerDamage> attackingPlayers = new ArrayList<playerDamage>();

	private class playerDamage{
		private int playerID, totalDamage;

		public playerDamage(int pID, int tDamage){
			playerID = pID;
			totalDamage = tDamage;
		}

		public void addDamage(int amount){
			totalDamage += amount;
		}

		public int getDamage(){
			return totalDamage;
		}

		public int getPlayerID(){
			return playerID;
		}

	}		

	/**
	 * Will inflict damage to this NPC
	 * @param amount Amount of damage to inflict
	 */
	public void damageNPC(int amount){
		hitDiff = amount;
		if (hitDiff > HP) hitDiff = HP;
		hitUpdateRequired = true;
		HP -= hitDiff;
		if (HP <= 0) {
			IsDead = true;
		}
	}

	private void appendSetFocusDestination(stream str) {
		str.writeWordBigEndian(focusPointX);
		str.writeWordBigEndian(focusPointY);
	}

	public void face(String dir){
		dir = dir.toLowerCase();
		switch(dir){
		case "north":
			this.turnNpc(this.absX, this.absY+1);
			return;
		case "south":	
			this.turnNpc(this.absX, this.absY-1);
			return;
		case "east":	
			this.turnNpc(this.absX+1, this.absY);
			return;
		case "west":	
			this.turnNpc(this.absX-1, this.absY);
			return;
		}
	}

	private void turnNpc(int i, int j) {
		focusPointX = 2 * i + 1;
		focusPointY = 2 * j + 1;
		updateRequired = true;
		turnUpdateRequired = true;
	}

	public boolean isOutsideSpawn(){
		return (distanceToPoint(spawnX, spawnY) > 8);
	}

	public int distanceToPoint(int pointX,int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2) + Math.pow(absY - pointY, 2));
	}	

	/**
	 * Clears the current list of attacking players
	 */
	public void resetAttackingPlayers(){
		attackingPlayers.clear();
	}

	/**
	 * Updates the attacking player list, if the player is not in the list, will add the player
	 * @param playerID - player ID to update
	 * @param damage - damage to add
	 * @return true if player was updated or added, false if not found
	 */
	public boolean updateAttackingPlayers(int playerID, int damage){
		for(playerDamage pD : attackingPlayers){
			if(pD.getPlayerID() == playerID){ //player is attacking, update
				pD.addDamage(damage);
				return true;
			}
		}
		return attackingPlayers.add(new playerDamage(playerID, damage));
	}

	/**
	 * Sets the NPC's startkilling variable to player with most damage accumulation
	 */
	public void setPlayerAgroID(){
		int pID = -1;
		int highest = -1;
		for(playerDamage pD : attackingPlayers){
			if(pD.getDamage() >= highest){
				highest = pD.getDamage();
				pID = pD.getPlayerID();
			}
		}
		if (highest == -1) return;
		this.StartKilling = pID;
	}

	/**
	 * Gets player who has the most damage on the current NPC
	 * @return Player ID of whoever has accumulated most damage
	 */
	public int getPlayerAgroID(){
		return getPlayerAgroID(this.StartKilling);
	}	

	/**
	 * Gets player who has the most damage on the current NPC
	 * @param originalID Original startkilling ID
	 * @return Player ID of whoever has accumulated most damage, or originalID if there is none
	 */
	public int getPlayerAgroID(int originalID){
		int pID = -1;
		int highest = -1;
		for(playerDamage pD : attackingPlayers){
			if(pD.getDamage() >= highest){
				highest = pD.getDamage();
				pID = pD.getPlayerID();
			}
		}
		if (highest == -1) return originalID;
		return pID;
	}

	public NPC(int _npcId, int _npcType) {
		npcId = _npcId;
		npcType = _npcType;
		direction = -1;
		IsDead = false;
		DeadApply = false;
		actionTimer = 0;
		RandomWalk = true;
		StartKilling = 0;
		IsUnderAttack = false;
		IsClose = false;
		for (int i = 0; i < Killing.length; i++) {
			Killing[i] = 0;
		}
	}

	public void reset(){
		followPlayer = 0;
		IsUnderAttack = false;
		followingPlayer = false;
		StartKilling = 0;
		IsUnderAttackNpc = false;
		IsAttackingNPC = false;
		attacknpc = 0;
		face = 0;
		updateRequired = true;
		StartKilling = 0;
		moveToSpawn = true;
	}

	public void faceplayer(int i)
	{
		face = i + 32768;
		faceUpdateRequired = true;
		updateRequired = true;
	}
	public boolean faceUpdateRequired;
	public void updateface(stream stream1)
	{
		stream1.writeWord(face);
	}
	public int face;


	public void updateNPCMovement(stream str) {
		if (direction == -1) {
			// don't have to update the npc position, because the npc is just standing
			if (updateRequired) {
				// tell client there's an update block appended at the end
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
		} else {
			// send "walking packet"
			str.writeBits(1, 1);
			str.writeBits(2, 1);		// updateType
			str.writeBits(3, misc.xlateDirectionToClient[direction]);
			if (updateRequired) {
				str.writeBits(1, 1);		// tell client there's an update block appended at the end
			} else {
				str.writeBits(1, 0);
			}
		}
	}

	public void appendNPCUpdateBlock(stream str) {
		if(!updateRequired) return ;		// nothing required
		int updateMask = 0;
		if(animUpdateRequired) updateMask |= 0x10;
		//if(hitUpdateRequired) updateMask |= 0x8;
		if(textUpdateRequired) updateMask |= 1;
		if(hitUpdateRequired) updateMask |= 0x40;
		if(dirUpdateRequired) updateMask |= 0x20;
		if(faceToUpdateRequired) updateMask |= 0x20;
		if(turnUpdateRequired) updateMask |= 4;
		boolean faceUp = false;
		if(faceUpdateRequired && updateMask == 0){ //Only if there is no other updates to do, ive tested other ways but this seems the best.
			updateMask |= 0x20;
			faceUp = true;
		}

		/*if(updateMask >= 0x100) {
			// byte isn't sufficient
			updateMask |= 0x40;			// indication for the client that updateMask is stored in a word
			str.writeByte(updateMask & 0xFF);
			str.writeByte(updateMask >> 8);
		} else {*/
		str.writeByte(updateMask);
		//}

		// now writing the various update blocks itself - note that their order crucial
		if(textUpdateRequired) {
			str.writeString(textUpdate);
		}
		if (animUpdateRequired) appendAnimUpdate(str);
		if (hitUpdateRequired) appendHitUpdate(str);
		if (dirUpdateRequired) appendFaceEntity(str);
		if (dirUpdateRequired) appendDirUpdate(str);
		if (faceToUpdateRequired) appendFaceToUpdate(str);
		if (faceUpdateRequired && faceUp) updateface(str);
		if(turnUpdateRequired) appendSetFocusDestination(str);		
		// TODO: add the various other update blocks
	}

	public void clearUpdateFlags() {
		updateRequired = false;
		textUpdateRequired = false;
		hitUpdateRequired = false;
		animUpdateRequired = false;
		dirUpdateRequired = false;
		textUpdate = null;
		moveX = 0;
		moveY = 0;
		direction = -1;
		focusPointX = -1; 
		focusPointY = -1; 
		turnUpdateRequired = false;
	}

	// returns 0-7 for next walking direction or -1, if we're not moving
	public int getNextWalkingDirection() {
		if (!WorldMap.isWalkAble(heightLevel, absX, absY, absX+moveX, absY+moveY) && !moveToSpawn)
			return -1;
		currentTile = new Tile(absX + moveX, absY + moveY, heightLevel);
		if(!WalkingCheck.tiles.containsKey(currentTile.getH() << 28 | currentTile.getX() << 14 | currentTile.getY())){
			int dir;
			dir = misc.direction(absX, absY, (absX + moveX), (absY + moveY));
			if (dir == -1)
				return -1;
			dir >>= 1;
		absX += moveX;
		absY += moveY;
		return dir;
		} else if(WalkingCheck.tiles.get(currentTile.getH() << 28 | currentTile.getX() << 14 | currentTile.getY()) == true) {
			return -1;
		} else {
			return -1;
		}
	}

	public void getNextNPCMovement() {
		direction = -1;
		direction = getNextWalkingDirection();
	}

	protected void appendHitUpdate(stream str) {		
		try {
			str.writeByteC(hitDiff); // What the perseon got 'hit' for
			if (hitDiff > 0 && !poisondmg) {
				str.writeByteS(1); // 0: red hitting - 1: blue hitting
			} else if (hitDiff > 0 && poisondmg) {
				str.writeByteS(2); // 0: red hitting - 1: blue hitting
			} else {
				str.writeByteS(0); // 0: red hitting - 1: blue hitting
			}
			str.writeByteS(HP); // Their current hp, for HP bar
			str.writeByteC(MaxHP); // Their max hp, for HP bar
			poisondmg = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Dont use this?
	 * @param str
	 */
	protected void appendHitUpdate2(stream str) {		
		try {
			HP -= hitDiff;
			if (HP <= 0) {
				IsDead = true;
			}
			str.writeByteS(hitDiff); // What the perseon got 'hit' for
			if (hitDiff > 0 && !poisondmg) {
				str.writeByteC(1); // 0: red hitting - 1: blue hitting
			} else if (hitDiff > 0 && poisondmg) {
				str.writeByteC(2); // 0: red hitting - 1: blue hitting
			} else {
				str.writeByteC(0); // 0: red hitting - 1: blue hitting
			}
			str.writeByteS(HP); // Their current hp, for HP bar
			str.writeByte(MaxHP); // Their max hp, for HP bar
			poisondmg = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void appendAnimUpdate(stream str) {
		str.writeWordBigEndian(animNumber);
		str.writeByte(1);
	}

	public void appendFaceEntity(stream str){
		str.writeWord(direction);
	}

	public void appendDirUpdate(stream str){
		str.writeWord(direction);
	}

	public void appendFaceToUpdate(stream str) {
		str.writeWordBigEndian(viewX);
		str.writeWordBigEndian(viewY);
	}
}

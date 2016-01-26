
public class MagicDataHandler {
	private client c;

	public MagicDataHandler(client pc){
		c = pc;
	}
	
	
	public void magicOnItems(int castSpell, int castOnItem, int castOnSlot){

		int alchvaluez = (int)Math.floor(Item.GetItemShopValue(castOnItem, 0.75)); // 75% value
		
		if(castSpell == 1162) //Low Alch
		{
			if(c.playerLevel[6] >= 21) 
			{
				if((c.getInventoryHandler().playerHasItemAmount(561, 5) == false) || (c.getInventoryHandler().playerHasItemAmount(554, 1) == false))
				{
					c.sendMessage("You do not have enough runes to cast this spell.");
				}
				else if((c.getInventoryHandler().playerHasItemAmount(561, 5) == true) && (c.getInventoryHandler().playerHasItemAmount(554, 1) == true))
				{
					alchvaluez = (alchvaluez / 4);
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);				
					c.getInventoryHandler().addItem(995, alchvaluez);
					c.getClientMethodHandler().addSkillXP((45*c.playerLevel[6]), 6);
					c.startAnimation(712);
					c.getFrameMethodHandler().playerGfx(112, 0);
					c.getInventoryHandler().deleteItem(561,c.getInventoryHandler().getItemSlot(561), 5);
					c.getInventoryHandler().deleteItem(554,c.getInventoryHandler().getItemSlot(554), 1);
				} 
			}
			else if(c.playerLevel[6] <= 21) 
			{
				c.sendMessage("You need at least 21 Magic to cast Low Level Alchemy");
			}
		}

		else if(castSpell == 1178) //High Alch fixed by Joey
		{
			if(c.playerLevel[6] >= 55) 
			{
				if((c.getInventoryHandler().playerHasItemAmount(561, 1) == false) || (c.getInventoryHandler().playerHasItemAmount(554, 1) == false))
				{
					c.sendMessage("NOOB you need 1 nat, 1 fire.");
				}
				else if((c.getInventoryHandler().playerHasItemAmount(561, 1) == true) && (c.getInventoryHandler().playerHasItemAmount(554, 1) == true))
				{
					alchvaluez = (alchvaluez);
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(995, alchvaluez);
					c.getClientMethodHandler().addSkillXP((80*c.playerLevel[6]), 6);
					c.startAnimation(712);
					c.getFrameMethodHandler().playerGfx(113, 0);
					c.getInventoryHandler().deleteItem(561,c.getInventoryHandler().getItemSlot(561), 1);
					c.getInventoryHandler().deleteItem(554,c.getInventoryHandler().getItemSlot(554), 1);
				} 
			}
			else if(c.playerLevel[6] <= 54) 
			{
				c.sendMessage("You need at least 55 Magic to cast High Level Alchemy");
			}
		}

		else if(castSpell == 1155) { //Enchant lvl 1(saph)
			if(c.playerLevel[6] >= 7) {
				if(castOnItem == 1637) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(2550, 1);
					c.getClientMethodHandler().addSkillXP(18, 6);
				}
				else if(castOnItem == 1656) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(3853, 1);
					c.getClientMethodHandler().addSkillXP(18, 6);
				}
				else if(castOnItem == 1694) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(1727, 1);
					c.getClientMethodHandler().addSkillXP(18, 6);
				} else {
					c.sendMessage("This needs to be cast on Saphire Jewelry");
				}
			} else {
				c.sendMessage("You need atleast 7 Magic to cast Enchant Lvl-1 Jewelry");
			}
		}
		else if(castSpell == 1165) { //Enchant lvl 2(emme)
			if(c.playerLevel[6] >= 27) {
				if(castOnItem == 1639) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(2552, 1);
					c.getClientMethodHandler().addSkillXP(37, 6);
				}
				else if(castOnItem == 1658) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(5521, 1);
					c.getClientMethodHandler().addSkillXP(37, 6);
				}
				else if(castOnItem == 1696) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(1729, 1);
					c.getClientMethodHandler().addSkillXP(37, 6);
				} else {
					c.sendMessage("This needs to be cast on Emerald Jewelry");
				}
			} else {
				c.sendMessage("You need atleast 27 Magic to cast Enchant Lvl-2 Jewelry");
			}
		}
		else if(castSpell == 1176) { //Enchant lvl 3(ruby)
			if(c.playerLevel[6] >= 49) {
				if(castOnItem == 1641) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(2568, 1);
					c.getClientMethodHandler().addSkillXP(59, 6);
				}
				else if(castOnItem == 1698) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(1725, 1);
					c.getClientMethodHandler().addSkillXP(59, 6);
				} else {
					c.sendMessage("This needs to be cast on Ruby Jewelry");
				}
			} else {
				c.sendMessage("You need atleast 49 Magic to cast Enchant Lvl-3 Jewelry");
			}
		}
		else if(castSpell == 1180) { //Enchant lvl 4(diam)
			if(c.playerLevel[6] >= 57) {
				if(castOnItem == 1643) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(2570, 1);
					c.getClientMethodHandler().addSkillXP(67, 6);
				}
				else if(castOnItem == 1700) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(1731, 1);
					c.getClientMethodHandler().addSkillXP(67, 6);
				} else {
					c.sendMessage("This needs to be cast on Diamond Jewelry");
				}
			} else {
				c.sendMessage("You need atleast 57 Magic to cast Enchant Lvl-4 Jewelry");
			}
		}
		else if(castSpell == 1187) { //Enchant lvl 5(drag)
			if(c.playerLevel[6] >= 68) {
				if(castOnItem == 1645) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(2572, 1);
					c.getClientMethodHandler().addSkillXP(78, 6);
				}
				else if(castOnItem == 1702) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(1704, 1);
					c.getClientMethodHandler().addSkillXP(78, 6);
				} else {
					c.sendMessage("This needs to be cast on Dragonstone Jewelry");
				}
			} else {
				c.sendMessage("You need atleast 68 Magic to cast Enchant Lvl-5 Jewelry");
			}
		}
		else if(castSpell == 1173) { //Superheat Item
			if(c.playerLevel[6] >= 43) {
				if(castOnItem == 436 && (c.getInventoryHandler().amountOfItemInInventory(438) >= 1)) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().deleteItem(438, c.getInventoryHandler().getItemSlot(438), 1);
					c.getInventoryHandler().addItem(2349, 1);
					c.getClientMethodHandler().addSkillXP(53, 6);
				}
				else if((castOnItem == 438) && (c.getInventoryHandler().amountOfItemInInventory(436) >= 1)) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().deleteItem(436, c.getInventoryHandler().getItemSlot(436), 1);
					c.getInventoryHandler().addItem(2349, 1);
					c.getClientMethodHandler().addSkillXP(53, 6);
				}
				else if(castOnItem == 440) {
					if(c.getInventoryHandler().amountOfItemInInventory(453) < 2) {
						c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
						c.getInventoryHandler().addItem(2351, 1);
						c.getClientMethodHandler().addSkillXP(53, 6);
					} else if(c.getInventoryHandler().amountOfItemInInventory(453) >= 2) {
						c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<2; d++) {
							c.getInventoryHandler().deleteItem(453, c.getInventoryHandler().getItemSlot(453), 1);
						}
						c.getInventoryHandler().addItem(2353, 1);
						c.getClientMethodHandler().addSkillXP(53, 6);
					} else { c.sendMessage("You need 2 coal to make a steel bar"); }
				}
				else if(castOnItem == 442) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(2355, 1);
					c.getClientMethodHandler().addSkillXP(53, 6);
				}
				else if(castOnItem == 444) {
					c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
					c.getInventoryHandler().addItem(2357, 1);
					c.getClientMethodHandler().addSkillXP(53, 6);
				}
				else if((castOnItem == 447)) {
					if(c.getInventoryHandler().amountOfItemInInventory(453) < 4) { c.sendMessage("You need 4 coal to make a mith bar");
					} else {
						c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<4; d++) {
							c.getInventoryHandler().deleteItem(453, c.getInventoryHandler().getItemSlot(453), 1);
						}
						c.getInventoryHandler().addItem(2359, 1);
						c.getClientMethodHandler().addSkillXP(53, 6);
					}
				}
				else if((castOnItem == 449)) {
					if(c.getInventoryHandler().amountOfItemInInventory(453) < 6) { c.sendMessage("You need 6 coal to make an addy bar");
					} else {
						c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<6; d++) {
							c.getInventoryHandler().deleteItem(453, c.getInventoryHandler().getItemSlot(453), 1);
						}
						c.getInventoryHandler().addItem(2361, 1);
						c.getClientMethodHandler().addSkillXP(53, 6);
					}
				}
				else if((castOnItem == 451)) {
					if(c.getInventoryHandler().amountOfItemInInventory(453) < 8) { c.sendMessage("You need 8 coal to make a rune bar");
					} else {
						c.getInventoryHandler().deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<8; d++) {
							c.getInventoryHandler().deleteItem(453, c.getInventoryHandler().getItemSlot(453), 1);
						}
						c.getInventoryHandler().addItem(2363, 1);
						c.getClientMethodHandler().addSkillXP(53, 6);
					}
				}
			} else {
				c.sendMessage("You need atleast 43 Magic to cast Superheat Item");
			}
		}
	}


	public void Teleblock()
	{
		c.teleblock = true;
		c.sendMessage("A teleblock has been cast on you!");
		c.getFrameMethodHandler().stillgfx(345, c.absY, c.absX);
	}

	/*TELEOTHER*/
	public void teleOtherRequest(String teleLocation, int player) {

		String telePlayer = server.playerHandler.players[player].playerName;
		c.getFrameMethodHandler().sendQuest(telePlayer, 12558);
		c.getFrameMethodHandler().sendQuest(teleLocation, 12560);
		c.getFrameMethodHandler().showInterface(12468);

		c.teleReq = player;
		c.teleLoc = teleLocation;

		c.teleOtherScreen = false;

	}

	public void StillMagicGFX(int id, int Y, int X, int time, int height)
	{
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null"))
				{
					if(person.distanceToPoint(X, Y) <= 60)
					{
						person.getFrameMethodHandler().StillMagicGFX2(id, Y, X, time, height);
					}
				}
			}
		}
	}



	public void MagicProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
			int startHeight, int endHeight, int lockon, int time)
	{
		for (Player p : server.playerHandler.players)
		{
			if(p != null) 
			{
				client person = (client)p;
				if((person.playerName != "null"))
				{
					person.getFrameMethodHandler().MagicProjectile2(casterY, casterX, offsetY, offsetX, angle, speed, gfxMoving, startHeight, endHeight, lockon, time);
				}
			}
		}
	}

	public int ancientsAttackPlayersWithin(int x, int y, int gfx, int maxDamage, int range, int level, boolean binds, int durationOfBind) {
		c.startAnimation(1979);
		int totalDamage = 0;
		for (Player p : server.playerHandler.players){
			if(p != null) {
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")) {
					if(person.distanceToPoint(x, y) <= range && person.playerId != c.playerId) {
						if(!c.getCombatHandler().doIHitPlayerWithMagic(person.playerId)){
							maxDamage = 0;
							gfx = 339;
						}
						int damage = misc.random(this.calculateMagicMaxHit(maxDamage, level));
						if(damage != 0 && binds)
							person.frozen(durationOfBind);
						person.getFrameMethodHandler().stillgfx(gfx, person.absY, person.absX);
						c.getCombatHandler().updateDelayAndDamagePlayer(person.playerId, damage);
					}
				}
			}
		}
		c.stopPlayerMovement();
		c.getClientMethodHandler().addSkillXP((totalDamage * c.mageXP2)*c.rate, 6);
		return totalDamage;
	}

	public boolean AttackPlayerMagic(int index) {
		int magicDistance = 6;

		if (!misc.GoodDistance(server.playerHandler.players[index].absX, server.playerHandler.players[index].absY, c.absX, c.absY, magicDistance))
			c.followingPlayerID = c.AttackingOn;

		if(c.LoopAttDelay > 0)
			return false;

		c.applySnare = -1;
		int required = this.checkMagicLevel(c.spellID);
		if(c.playerLevel[c.playerMagic] < required){
			c.sendMessage("You need a Magic level of at least "+required+" to do that.");
			c.stopPlayerMovement();
			return false;
		}

		if(!this.checkMagicRunes(c.spellID)){
			c.stopPlayerMovement();
			return false;
		}

		int playerIndex = index;
		client castOnPlayer = (client) server.playerHandler.players[playerIndex];

		if(!c.getCombatHandler().canAttackOpponent(castOnPlayer)){
			c.stopPlayerMovement();
			return false;
		}

		int EnemyX2 = server.playerHandler.players[playerIndex].absX;
		int EnemyY2 = server.playerHandler.players[playerIndex].absY;
		int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[c.playerHitpoints];
		int heal = 0;
		int myHP = c.playerLevel[c.playerHitpoints];
		int damage = 0;	

		int distanceBetweenMeAndMyEnemy = misc.distanceBetweenPoints(EnemyX2, EnemyY2, c.absX, c.absY);

		//inStream.readSignedWordA();
		if (distanceBetweenMeAndMyEnemy <= magicDistance){				
			c.debug("playerIndex: "+playerIndex+" spellID: "+c.spellID);

			c.followingPlayerID = -1;

			c.PkingDelay = Item.getItemDelay(Item.MAGIC)+(distanceBetweenMeAndMyEnemy/6);

			c.stopPlayerMovement();

			int offsetX = (c.absX - EnemyX2) * -1;
			int offsetY = (c.absY - EnemyY2) * -1;
			int BURST = 2; int BARRAGE = 3;

			int X3 = PlayerHandler.players[index].absX;
			int Y3 = PlayerHandler.players[index].absY;
			int offsetX3 = (c.absX - X3) * -1;
			int offsetY3 = (c.absY - Y3) * -1;

			if(castOnPlayer.autoRetaliate == 1 && !castOnPlayer.IsAttacking) //1 means on
				c.getCombatHandler().opponentAutoAttack(castOnPlayer);

			this.removeMagicRunes(c.spellID);

			switch(c.spellID){ 

			case 1152: //Wind Strike
				damage = ProjectileSpellPlayer(90, 95, 92, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 2,1);
				break;

			case 1154: //Water Strike
				damage = ProjectileSpellPlayer(93, 94, 95, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 4,5);
				break;

			case 1156: //Earth Strike
				damage = ProjectileSpellPlayer(96, 97, 98, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 6,9);
				break;

			case 1158: //Fire Strike
				damage = ProjectileSpellPlayer(99, 100, 101, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 8,13);
				break;

			case 1160: //Wind Bolt
				damage = ProjectileSpellPlayer(117, 118, 119, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 9,17);
				break;
			case 1163: //thing
				damage = ProjectileSpellPlayer(120, 121, 122, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 10,23);
				break;

			case 1166: //Earth Bolt
				damage = ProjectileSpellPlayer(123, 124, 125, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 11,29); 
				break;

			case 1169: //Fire Bolt
				damage = ProjectileSpellPlayer(126, 127, 128, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 12,35);
				break;

			case 1172: //Wind Blast
				damage = ProjectileSpellPlayer(132, 133, 134, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 13,41);
				break;

			case 1175: //Water Blast
				damage = ProjectileSpellPlayer(135, 136, 137, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 14,47);
				break;

			case 1177: //Earth Blast
				damage = ProjectileSpellPlayer(138, 139, 140, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 15,53);
				break;

			case 1181: //Fire Blast
				damage = ProjectileSpellPlayer(129, 130, 131, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 16,59);
				break;

			case 1183: //Wind Wave
				damage = ProjectileSpellPlayer(158, 159, 160, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 17,62);
				break;

			case 1185: //Water Wave
				damage = ProjectileSpellPlayer(161, 162, 163, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 18,65);
				break;

			case 1188: //Earth Wave
				damage = ProjectileSpellPlayer(164, 165, 166, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 19,70);
				break;

			case 1189: //Fire Wave
				damage = ProjectileSpellPlayer(155, 156, 157, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 20,75);
				break;

			case 12861: //Ice Rush - Level 58
				c.applySnare = 5;
				damage = ancientsProjectileSpellPlayer(360, 360, 361, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 17,58);
				break;

			case 12881: //Ice Burst - Level 70
				ancientsAttackPlayersWithin(EnemyX2, EnemyY2,363,22,BURST,70,true,10);					
				return true;

			case 12871: //Ice Blitz - Level 82
				c.applySnare = 15;
				damage = ancientsProjectileSpellPlayer(366, 367, 368, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 26,82);
				break;

			case 12891: //Ice Barrage - Level 94
				ancientsAttackPlayersWithin(EnemyX2, EnemyY2,369,30,BARRAGE,94,true,20);					
				return true;

			case 12939: // Smoke Rush - Level 50
				damage = ancientsProjectileSpellPlayer(384, 384, 385, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 14,50);
				break;

			case 12963: // Smoke Burst - Level 62
				ancientsAttackPlayersWithin(EnemyX2, EnemyY2,389,18,BURST,62,false,-1);		
				return true;

			case 12951: //Smoke Blitz - Level 74
				damage = ancientsProjectileSpellPlayer(386, 386, 387, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 23,74);
				break;

			case 12975: //Smoke Barrage - Level 86
				ancientsAttackPlayersWithin(EnemyX2, EnemyY2,391,27,BARRAGE,86,false,-1);
				return true;

			case 12987: //Shadow Rush - Level 52
				damage = ancientsProjectileSpellPlayer(378, 378, 379, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 15,52);
				break;

			case 13011: //Shadow Burst - Level 64
				ancientsAttackPlayersWithin(EnemyX2, EnemyY2,382,19,BURST,64,false,-1);
				return true;

			case 12999: //Shadow Blitz - Level 76
				damage = ancientsProjectileSpellPlayer(380, 380, 381, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 24,76);
				break;

			case 13023: //Shadow Barrage - Level 88
				ancientsAttackPlayersWithin(EnemyX2, EnemyY2,383,28,BARRAGE,88,false,-1);
				return true;

			case 12901: //Blood Rush - Level 56
				damage = ancientsProjectileSpellPlayer(372, 372, 373, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 16,56);
				c.getClientMethodHandler().heal(damage/4);
				break;

			case 12919: //Blood Burst - Level 68
				int total = ancientsAttackPlayersWithin(EnemyX2, EnemyY2,376,21,BURST,68,false,-1);
				if(total > 7) total = 7; //greatest amount that can be healed is 7
				c.getClientMethodHandler().heal(total);
				return true;

			case 12911: //Blood Blitz - Level 80
				damage = ancientsProjectileSpellPlayer(374, 374, 375, c.absY, c.absX, offsetY, offsetX, index+1, EnemyY2, EnemyX2, 25,80);
				int total2 = damage/4;
				if(total2 > 7) total2 = 7; //greatest amount that can be healed is 7
				c.getClientMethodHandler().heal(total2);
				break;

			case 12929: //Blood Barrage - Level 92
				int total3 = ancientsAttackPlayersWithin(EnemyX2, EnemyY2,377,29,BARRAGE,92,false,-1);
				if(total3 > 8) total3 = 8; //greatest amount that can be healed is 8
				c.getClientMethodHandler().heal(total3);
				return true;				

			default:
				c.debug("Unhandled spellID when casting on player : "+c.spellID);
				break;

			}

			//if(!doesMySpellHitMyEnemy(playerIndex)) damage = 0;

			//damage -= misc.random(   ( (client) server.playerHandler.players[playerIndex] ).playerMagicDefBonusStatic()    );

			if(castOnPlayer.PMage)
				damage = (int)((double)damage*0.6); //reduce by 40% in pvp

			if(damage > 0 && c.applySnare > 0)
				castOnPlayer.frozen(c.applySnare);

			int exp = damage*4*c.CombatExpRate;
			if (exp < 0) exp = 4*c.CombatExpRate;
			c.getClientMethodHandler().addSkillXP(exp, 6);

			return c.getCombatHandler().updateDelayAndDamagePlayer(playerIndex, damage);			
		}
		return false;
	}

	public boolean firespell(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int movegfxID,int startHeight, int endHeight, int finishID, int enemyY,int enemyX, int Lockon) 
	{
		c.fcastid = castID;
		c.fcasterY = casterY;
		c.fcasterX = casterX;
		c.foffsetY = offsetY;
		c.foffsetX = offsetX;
		c.fangle = angle;
		c.fspeed = speed;
		c.fmgfxid = movegfxID;
		c.fsh = startHeight;
		c.feh = endHeight;
		c.ffinishid = finishID;
		c.fenemyY = enemyY;
		c.fenemyX = enemyX;
		c.fLockon = Lockon;

		c.actionTimer = 0;

		//Casts Spell In Hands
		if(c.cast == false && c.actionTimer <= 0) {
			c.getFrameMethodHandler().stillgfxz(castID, casterY, casterX, 100, 0);
			c.cast = true;
			c.firingspell = true;
		}
		//Fires Projectile
		if(c.cast == true && c.fired == false && c.actionTimer <= 0) {
			c.getFrameMethodHandler().createProjectile(casterY, casterX, offsetY, offsetX, angle, speed, movegfxID, startHeight, endHeight, Lockon);
			c.fired = true;
		}
		//Finishes Spell
		if(c.fired == true && c.actionTimer <= 0) {
			c.getFrameMethodHandler().stillgfxz(finishID, enemyY, enemyX, 100, 95);
			resetGFX(castID, enemyX, enemyY);
			return false;
		}
		return true;
	}

	public void resetGFX(int id, int X, int Y)
	{
		GraphicsHandler.removeGFX(id, X, Y);
		c.firingspell = false;
		c.cast = false;
		c.fired = false;
	}


	/**
	 * @param speed quickness of the gfx transition, default is 95
	 */
	public void projectileOnNPC(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX, int speed) {
		//GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, speed, movingID, 23, 20, finishID, enemyY, enemyX, index+1);
		firespell(startID, casterY, casterX, offsetY, offsetX, 50, speed, movingID, 23, 20, finishID, enemyY, enemyX, index+1);
	}	

	public int ProjectileSpell(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX, int maxDmg, int level) {
		c.startAnimation(711);
		projectileOnNPC(startID, movingID, finishID, casterY, casterX, offsetY,offsetX,index,enemyY,enemyX,95);
		int hit = misc.random(this.calculateMagicMaxHit(maxDmg, level));
		c.getClientMethodHandler().addSkillXP((hit * c.mageXP2)*c.rate, 6);
		return hit;   
	}

	public int ancientsProjectileSpell(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX, int maxDmg, int level) {
		c.startAnimation(1978);
		projectileOnNPC(startID, movingID, finishID, casterY, casterX, offsetY,offsetX,index,enemyY,enemyX,95);
		int hit = misc.random(this.calculateMagicMaxHit(maxDmg, level));
		c.getClientMethodHandler().addSkillXP((hit * c.mageXP2)*c.rate, 6);
		return hit;   
	}


	public int ProjectileSpellPlayer(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX, int maxDmg, int level) {
		c.startAnimation(711);
		if(!c.getCombatHandler().doIHitPlayerWithMagic(index-1)){
			maxDmg = 0;
			finishID = 339;
		}
		GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX, 0 - index);
		return misc.random(this.calculateMagicMaxHit(maxDmg, level));
	}

	public void ProjectileSpellPlayer(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX) {
		GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX, 0 - index);
		return;
	}

	public int ancientsProjectileSpellPlayer(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX, int maxDmg, int level) {
		c.startAnimation(1978);
		if(!c.getCombatHandler().doIHitPlayerWithMagic(index-1)){
			maxDmg = 0;
			finishID = 339;
		}
		GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX, 0 - index);
		return misc.random(this.calculateMagicMaxHit(maxDmg, level));
	}


	/**
	 * Will attack NPCs within a range and display the GFX on them
	 * @param maxDamage Randomizes this damage
	 */
	public void attackNPCSWithinRangeWithGFX(int gfx, int maxDamage, int range, int level) {
		for (int i = 1; i <= server.npcHandler.maxNPCs-1; i++){
			if(server.npcHandler.npcs[i] != null){
				if(c.distanceToPoint(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY) <= range && !server.npcHandler.npcs[i].IsDead)
				{
					int damage = 0;
					if(c.getCombatHandler().doIHitNPCWithMagic(i)) damage = misc.random(this.calculateMagicMaxHit(maxDamage, level));
					c.getFrameMethodHandler().stillgfx(gfx, server.npcHandler.npcs[i].absY, server.npcHandler.npcs[i].absX);
					c.getCombatHandler().updateDelayAndHitNPC(i, damage);
				}
			}
			else break;
		}
	}

	/**
	 * Will attack NPCs within a range and display the GFX on them, used for ancient spellbook
	 * @param maxDamage Randomizes this damage
	 */
	private int ancientsAttackNPCSWithin(int x, int y, int gfx, int maxDamage, int range, int level) {
		c.startAnimation(1979);
		int totalDamage = 0;
		for (int i = 1; i <= server.npcHandler.maxNPCs-1; i++){
			if(server.npcHandler.npcs[i] == null) break;
			if(server.npcHandler.npcs[i] != null) 
			{
				if(misc.distanceBetweenPoints(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY, x, y) <= range && !server.npcHandler.npcs[i].IsDead)
				{				
					int damage = 0;
					if(c.getCombatHandler().doIHitNPCWithMagic(i)) damage = misc.random(c.MAGICDATAHANDLER.calculateMagicMaxHit(maxDamage, level));
					else gfx = 339;
					totalDamage += damage;
					c.getFrameMethodHandler().stillgfx(gfx, server.npcHandler.npcs[i].absY, server.npcHandler.npcs[i].absX);
					c.getCombatHandler().updateDelayAndHitNPC(i, damage);
				}
			}
		}
		c.getClientMethodHandler().addSkillXP((totalDamage * c.mageXP2)*c.rate, 6);
		return totalDamage;
	}

	public boolean magicOnNPC(int npcIndex){
		if(c.LoopAttDelay > 0)
			return false;

		c.teleport(c.absX,c.absY);

		if(!server.npcHandler.npcs[npcIndex].attackable) return false;

		c.debug("npcIndex: "+npcIndex+" magicID: "+c.spellID);

		int npcID = server.npcHandler.npcs[npcIndex].npcType;             
		if( (c.DIALOGUEHANDLER.exists(npcID) || lists.safeNPCs.exists(npcID)) && npcID != 1 && npcID != 2 && npcID != 3 &&
				npcID != 4 && npcID != 5 && npcID != 6 && npcID != 7)
			return false;

		int required = this.checkMagicLevel(c.spellID);
		if(c.playerLevel[c.playerMagic] < required){
			c.sendMessage("You need "+required+" Magic to do that.");
			return false;
		}

		if(!this.checkMagicRunes(c.spellID))
			return false;

		int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
		int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		int EnemyHP2 = server.npcHandler.npcs[npcIndex].HP;
		int hitDiff = 0;
		if(EnemyX2 != c.absX && EnemyY2 != c.absY)
			c.faceNPC(npcIndex);

		if(server.npcHandler.npcs[npcIndex].attacknpc > 0) {
			c.sendMessage("You can't attack a dueling npc");
			return false;
		}


		if((server.npcHandler.npcs[npcIndex] != null) && (server.npcHandler.npcs[npcIndex].followPlayer < 1 || server.npcHandler.npcs[npcIndex].followPlayer == c.playerId)) {
			//MageAttackIndex = npcIndex+1;
			{					
				try {
					int distanceBetweenMeAndMyEnemy = misc.distanceBetweenPoints(EnemyX2, EnemyY2, c.absX, c.absY);
					if (distanceBetweenMeAndMyEnemy > 6) return false;

					server.npcHandler.npcs[npcIndex].StartKilling = c.playerId;
					server.npcHandler.npcs[npcIndex].RandomWalk = false;
					server.npcHandler.npcs[npcIndex].IsUnderAttack = true;

					int casterX = c.absX;
					int casterY = c.absY;
					int offsetX = (casterX - EnemyX2) * -1;
					int offsetY = (casterY - EnemyY2) * -1;

					int BURST = 2;
					int BARRAGE = 3;
					c.inCombat();
					this.removeMagicRunes(c.spellID);

					c.PkingDelay = Item.getItemDelay(Item.MAGIC)+(distanceBetweenMeAndMyEnemy/6);

					switch(c.spellID){ 
					case 1152: //Wind Strike
						hitDiff = ProjectileSpell(90, 95, 92, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 2,1);
						break;

					case 1154: //Water Strike
						hitDiff = ProjectileSpell(93, 94, 95, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 4,5);
						break;

					case 1156: //Earth Strike
						hitDiff = ProjectileSpell(96, 97, 98, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 6,9);
						break;

					case 1158: //Fire Strike
						hitDiff = ProjectileSpell(99, 100, 101, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 8,13);
						break;

					case 1160: //Wind Bolt
						hitDiff = ProjectileSpell(117, 118, 119, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 9,17);
						break;
					case 1163: //thing
						hitDiff = ProjectileSpell(120, 121, 122, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 10,23);
						break;

					case 1166: //Earth Bolt
						hitDiff = ProjectileSpell(123, 124, 125, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 11,29); 
						break;

					case 1169: //Fire Bolt
						hitDiff = ProjectileSpell(126, 127, 128, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 12,35);
						break;

					case 1172: //Wind Blast
						hitDiff = ProjectileSpell(132, 133, 134, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 13,41);
						break;

					case 1175: //Water Blast
						hitDiff = ProjectileSpell(135, 136, 137, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 14,47);
						break;

					case 1177: //Earth Blast
						hitDiff = ProjectileSpell(138, 139, 140, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 15,53);
						break;

					case 1181: //Fire Blast
						hitDiff = ProjectileSpell(129, 130, 131, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 16,59);
						break;

					case 1183: //Wind Wave
						hitDiff = ProjectileSpell(158, 159, 160, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 17,62);
						break;

					case 1185: //Water Wave
						hitDiff = ProjectileSpell(161, 162, 163, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 18,65);
						break;

					case 1188: //Earth Wave
						hitDiff = ProjectileSpell(164, 165, 166, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 19,70);
						break;

					case 1189: //Fire Wave
						hitDiff = ProjectileSpell(155, 156, 157, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 20,75);
						break;

					case 12861: //Ice Rush - Level 58
						hitDiff = ancientsProjectileSpell(360, 360, 361, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 17,58);
						break;

					case 12881: //Ice Burst - Level 70
						ancientsAttackNPCSWithin(EnemyX2, EnemyY2,363,22,BURST,70);					
						return true;

					case 12871: //Ice Blitz - Level 82
						hitDiff = ancientsProjectileSpell(366, 367, 368, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 26,82);
						break;

					case 12891: //Ice Barrage - Level 94
						ancientsAttackNPCSWithin(EnemyX2, EnemyY2,369,30,BARRAGE,94);					
						return true;

					case 12939: // Smoke Rush - Level 50
						hitDiff = ancientsProjectileSpell(384, 384, 385, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 14,50);
						break;

					case 12963: // Smoke Burst - Level 62
						ancientsAttackNPCSWithin(EnemyX2, EnemyY2,389,18,BURST,62);		
						return true;

					case 12951: //Smoke Blitz - Level 74
						hitDiff = ancientsProjectileSpell(386, 386, 387, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 23,74);
						break;

					case 12975: //Smoke Barrage - Level 86
						ancientsAttackNPCSWithin(EnemyX2, EnemyY2,391,27,BARRAGE,86);
						return true;

					case 12987: //Shadow Rush - Level 52
						hitDiff = ancientsProjectileSpell(378, 378, 379, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 15,52);
						break;

					case 13011: //Shadow Burst - Level 64
						ancientsAttackNPCSWithin(EnemyX2, EnemyY2,382,19,BURST,64);
						return true;

					case 12999: //Shadow Blitz - Level 76
						hitDiff = ancientsProjectileSpell(380, 380, 381, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 24,76);
						break;

					case 13023: //Shadow Barrage - Level 88
						ancientsAttackNPCSWithin(EnemyX2, EnemyY2,383,28,BARRAGE,88);
						return true;

					case 12901: //Blood Rush - Level 56
						hitDiff = ancientsProjectileSpell(372, 372, 373, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 16,56);
						c.getClientMethodHandler().heal(hitDiff/4);
						break;

					case 12919: //Blood Burst - Level 68
						int total = ancientsAttackNPCSWithin(EnemyX2, EnemyY2,376,21,BURST,68)/4;
						if(total > 7) total = 7; //greatest amount that can be healed is 7
						c.getClientMethodHandler().heal(total);
						return true;

					case 12911: //Blood Blitz - Level 80
						hitDiff = ancientsProjectileSpell(374, 374, 375, casterY, casterX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 25,80);
						total = hitDiff/4;
						if(total > 7) total = 7; //greatest amount that can be healed is 7
						c.getClientMethodHandler().heal(total);
						break;

					case 12929: //Blood Barrage - Level 92
						total = ancientsAttackNPCSWithin(EnemyX2, EnemyY2,377,29,BARRAGE,92)/4;
						if(total > 8) total = 8; //greatest amount to heal is 8
						c.getClientMethodHandler().heal(total);
						return true;

					default:
						c.debug("Unhandled magicID : "+c.spellID);
						return false;
					}

					if(!c.getCombatHandler().doIHitNPCWithMagic(npcIndex)) hitDiff = 0;

					c.followingNPCID = -1;
					return c.getCombatHandler().updateDelayAndHitNPC(npcIndex, hitDiff);
				}
				catch(Exception e) {
					c.error("In magic on NPCs, "+e.toString());
					return false;
				}

			} 
		}
		return false;
	}


	public boolean setAutocast(int magicID){
		int lvl = this.checkMagicLevel(magicID);
		if(c.playerLevel[c.playerMagic] >= lvl){
			if(!this.checkMagicRunes(magicID)){
				c.getFrameMethodHandler().setSidebarInterface(0, 328);
				return false;
			}

			c.getFrameMethodHandler().setSidebarInterface(0, 328);
			c.spellID = magicID;
			c.autocast = true;
			c.sendMessage("Autocast has been activated.");
			return true;
		}
		c.sendMessage("You need at least "+lvl+" Magic to do that.");
		c.getFrameMethodHandler().setSidebarInterface(0, 328);
		return false;
	}

	/**
	 * Calculate's a player's maximum possible damage for a spell.
	 * Calculations based off of magic level, max damage of spell, and spell level.
	 * @return Calculate max damage possible for the player
	 */
	public int calculateMagicMaxHit(int maxDamage, int spellLevel){
		if(maxDamage == 0) return 0;
		double p = ((double)c.playerLevel[c.playerMagic]+1)/spellLevel;
		double f = p*maxDamage;
		int calc = (int)(Math.ceil(f)); //calculating max hit
		if(calc > maxDamage) calc = maxDamage;
		return calc;
	}

	private void hasMagicRunesAndRemove(int ... amountThenItem){
		if(amountThenItem == null) return;
		if(amountThenItem[0] == -1) return; //requires no runes to cast
		if(amountThenItem.length < 2) return;

		int curStaff = c.playerEquipment[c.playerWeapon];

		for(int i = 0; i < amountThenItem.length-1; i += 2){ //removes amount
			int rune = amountThenItem[i+1];
			if( !(rune == WATER && (curStaff == 1383 || curStaff == 1395 || curStaff == 1403)) && 
					!(rune == FIRE && (curStaff == 1387 || curStaff == 1393 || curStaff == 1401)) &&
					!(rune == EARTH && (curStaff == 1385 || curStaff == 1399 || curStaff == 1407)) &&
					!(rune == AIR && (curStaff == 1381 || curStaff == 1397 || curStaff == 1405)) )
				c.getInventoryHandler().deleteItem(amountThenItem[i+1], c.getInventoryHandler().getItemSlot(amountThenItem[i+1]), amountThenItem[i]);
		}
		return;
	}

	private boolean hasMagicRunes(int ... amountThenItem){
		if(amountThenItem == null) return false;
		if(amountThenItem[0] == -1) return true; //requires no runes to cast
		if(amountThenItem.length < 2) return false;

		int curStaff = c.playerEquipment[c.playerWeapon];

		for(int i = 0; i < amountThenItem.length-1; i += 2){
			int rune = amountThenItem[i+1];
			if( !(rune == WATER && (curStaff == 1383 || curStaff == 1395 || curStaff == 1403)) && 
					!(rune == FIRE && (curStaff == 1387 || curStaff == 1393 || curStaff == 1401)) &&
					!(rune == EARTH && (curStaff == 1385 || curStaff == 1399 || curStaff == 1407)) &&
					!(rune == AIR && (curStaff == 1381 || curStaff == 1397 || curStaff == 1405)) ){
				if(!c.getInventoryHandler().playerHasItemAmount(amountThenItem[i+1], amountThenItem[i])){
					c.sendMessage("You do not have the required amount of runes to do that.");
					return false;
				}
			}
		}

		return true;
	}

	public void removeMagicRunes(int spellID){
		hasMagicRunesAndRemove(magicRunes(spellID));
	}

	public boolean checkMagicRunes(int spellID){
		return hasMagicRunes(magicRunes(spellID));
	}

	//runes
	int FIRE = 554;
	int WATER = 555;
	int AIR = 556;
	int EARTH = 557;
	int MIND = 558;
	int BODY = 559;
	int DEATH = 560;
	int NATURE = 561;
	int CHAOS = 562;
	int LAW = 563;
	int COSMIC = 564;
	int BLOOD = 565;
	int SOUL = 566;

	private int[] magicRunes(int magicID){
		switch(magicID){ 
		case 1: //H Teleport
			return new int[]{1,FIRE,3,AIR,1,LAW};
		case 50235: // Home teleport ancients
			return new int[]{-1};
		case 50245: //PVP teleport ancients
			return new int[]{-1};
		case 50253: //Rimmington Teleport ancients
			return new int[]{2,LAW, 1,BLOOD};
		case 51005: //Entrana teleport ancients
			return new int[]{2,LAW, 4,WATER};
		case 51013: //Karamja teleport ancients
			return new int[]{2,LAW, 3,FIRE, 2,AIR};
		case 51023: //Barrows teleport ancients
			return new int[]{2,LAW, 2,SOUL};
		case 51031: //West ardougne teleport ancients
			return new int[]{2,LAW, 2,BLOOD};
		case 51039: //Dwarf Throne teleport ancients
			return new int[]{2,LAW, 8,WATER};

		case 29031: //gnome tree stronghold
			return new int[]{2,FIRE, 2,LAW};
		case 6004: //Ardougne
			return new int[]{2,WATER, 2,LAW};
		case 4150: // Mort'ton teleport
			return new int[]{5,AIR, 1,LAW};		
		case 6005: //Port Sarim
			return new int[]{2,EARTH, 2,LAW};			
		case 1152: //Wind Strike
			return new int[]{-1};
		case 1154: //Water Strike
			return new int[]{-1};
		case 1156: //Earth Strike
			return new int[]{2,EARTH, 1,AIR, 1,MIND};
		case 1158: //Fire Strike
			return new int[]{3,FIRE, 2,AIR, 1,MIND};
		case 1160: //Wind Bolt
			return new int[]{2,AIR, 1,CHAOS};
		case 1163: //water bolt
			return new int[]{2,WATER, 2,AIR, 1,CHAOS};
		case 1166: //Earth Bolt
			return new int[]{3,EARTH, 2,AIR, 1,CHAOS};
		case 1169: //Fire Bolt
			return new int[]{4,FIRE, 3,AIR, 1,CHAOS};
		case 1172: //Wind Blast
			return new int[]{3,AIR, 1,DEATH};
		case 1175: //Water Blast
			return new int[]{3,WATER, 3,AIR, 1,DEATH};
		case 1177: //Earth Blast
			return new int[]{4,EARTH, 3,AIR, 1,DEATH};
		case 1181: //Fire Blast
			return new int[]{5,FIRE, 4,AIR, 1,DEATH};
		case 1183: //Wind Wave
			return new int[]{5,AIR, 1,BLOOD};
		case 1185: //Water Wave
			return new int[]{7,WATER, 5,AIR, 1,BLOOD};
		case 1188: //Earth Wave
			return new int[]{7,EARTH, 5,AIR, 1,BLOOD};
		case 1189: //Fire Wave
			return new int[]{7,FIRE, 5,AIR, 1,BLOOD};
		case 12861: //Ice Rush - Level 58
			return new int[]{2,CHAOS, 2,DEATH, 2,WATER};
		case 12881: //Ice Burst - Level 70
			return new int[]{4,CHAOS, 2,DEATH, 4,WATER};
		case 12871: //Ice Blitz - Level 82
			return new int[]{2,DEATH, 2,BLOOD, 3,WATER};
		case 12891: //Ice Barrage - Level 94
			return new int[]{4,DEATH, 2,BLOOD, 6,WATER};
		case 12939: // Smoke Rush - Level 50
			return new int[]{2,CHAOS, 2,DEATH, 1,FIRE, 1,AIR};
		case 12963: // Smoke Burst - Level 62
			return new int[]{4,CHAOS, 2,DEATH, 2,FIRE, 2,AIR};
		case 12951: //Smoke Blitz - Level 74
			return new int[]{2,DEATH, 2,BLOOD, 2,FIRE, 2,AIR};
		case 12975: //Smoke Barrage - Level 86
			return new int[]{4,DEATH, 2,BLOOD, 4,FIRE, 4,AIR};
		case 12987: //Shadow Rush - Level 52
			return new int[]{2,CHAOS, 2,DEATH, 1,AIR, 1,SOUL};
		case 13011: //Shadow Burst - Level 64
			return new int[]{4,CHAOS, 2,DEATH, 2,AIR, 2,SOUL};
		case 12999: //Shadow Blitz - Level 76
			return new int[]{2,DEATH, 2,BLOOD, 2,AIR, 2,SOUL};
		case 13023: //Shadow Barrage - Level 88
			return new int[]{4,DEATH, 2,BLOOD, 4,AIR, 3,SOUL};
		case 12901: //Blood Rush - Level 56
			return new int[]{2,CHAOS, 2,DEATH, 1,BLOOD};
		case 12919: //Blood Burst - Level 68
			return new int[]{4,CHAOS, 2,DEATH, 2,BLOOD};
		case 12911: //Blood Blitz - Level 80
			return new int[]{2,DEATH, 4,BLOOD};
		case 12929: //Blood Barrage - Level 92
			return new int[]{4,DEATH, 4,BLOOD, 1,SOUL};

		default:
			c.debug("Unhandled magicID in checkMagicLevel : "+magicID);
			return new int[]{-1};
		}
	}

	public int checkMagicLevel(int magicID){
		switch(magicID){ 
		case 1152: //Wind Strike
			return 1;
		case 1153: //Curse
			return 3;
		case 1154: //Water Strike
			return 5;
		case 1156: //Earth Strike
			return 8;
		case 1157: //Weaken
			return 11;
		case 1158: //Fire Strike
			return 12;
		case 1160: //Wind Bolt
			return 19;
		case 1161: //Curse
			return 19;
		case 1163: //Water Bolt
			return 21;
		case 1166: //Earth Bolt
			return 29;
		case 1169: //Fire Bolt
			return 35;
		case 1171: //Crumble Undead
			return 39;
		case 1172: //Wind Blast
			return 40;
		case 1175: //Water Blast
			return 46;
		case 1539: //Iban Blast
			return 50;
		case 1582: //Snare
			return 50;
		case 12037: //Magic Dart
			return 50;
		case 1177: //Earth Blast
			return 52;
		case 1181: //Fire Blast
			return 59;
		case 1190: //Saradomin Strike
		case 1191: //Claws of Guthix
		case 1192: //Flames of Zamorak
			return 60;
		case 1183: //Wind Wave
			return 62;
		case 1185: //Water Wave
			return 65;
		case 1542: //Vulnerability
			return 66;
		case 1188: //Earth Wave
			return 70;
		case 1543: //Enfeeble
			return 73;
		case 1189: //Fire Wave
			return 75;
		case 1592: //Entangle
			return 79;
		case 1562: //Stun
			return 80;
		case 12861: //Ice Rush - Level 58
			return 58;
		case 12881: //Ice Burst - Level 70
			return 70;
		case 12871: //Ice Blitz - Level 82
			return 82;
		case 12891: //Ice Barrage - Level 94
			return 94;
		case 12939: // Smoke Rush - Level 50
			return 50;
		case 12963: // Smoke Burst - Level 62
			return 62;
		case 12951: //Smoke Blitz - Level 74
			return 74;
		case 12975: //Smoke Barrage - Level 86
			return 86;
		case 12987: //Shadow Rush - Level 52
			return 52;
		case 13011: //Shadow Burst - Level 64
			return 64;
		case 12999: //Shadow Blitz - Level 76
			return 76;
		case 13023: //Shadow Barrage - Level 88
			return 88;
		case 12901: //Blood Rush - Level 56
			return 56;
		case 12919: //Blood Burst - Level 68
			return 68;
		case 12911: //Blood Blitz - Level 80
			return 80;
		case 12929: //Blood Barrage - Level 92
			return 92;

		default:
			c.debug("Unhandled magicID in checkMagicLevel : "+magicID);
			return -1;
		}
	}

}

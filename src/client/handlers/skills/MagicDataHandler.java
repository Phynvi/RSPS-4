package client.handlers.skills;
import server.handlers.enemy.Enemy;
import server.handlers.task.Task;
import server.resources.misc;
import server.root.server;
import client.client;
import client.handlers.FrameMethods;
import client.handlers.Item;
import client.handlers.combat.Combat;

public class MagicDataHandler {
	private client c;

	public MagicDataHandler(client pc){
		c = pc;
	}
	
	public void castNormalTeleport(int x, int y, int h, int castID, int xp){
		c.teleportDelayCast = true;
		c.teleportDelay = 3;
		c.teleportDelayH = h;
		c.teleportDelayX = x;
		c.teleportDelayY = y;
		c.teleportDelayCastID = castID;
		c.teleportDelayXP = xp*c.rate;
		c.startAnimation(714);
		c.getFrameMethodHandler().gfxWithDelay(308, c.absX, c.absY, 50, 100);
	}
	
	public void castAncientTeleport(int x, int y, int h, int castID, int xp){
		c.teleportDelayCast = true;
		c.teleportDelay = 3;
		c.teleportDelayH = h;
		c.teleportDelayX = x;
		c.teleportDelayY = y;
		c.teleportDelayCastID = castID;
		c.teleportDelayXP = xp*c.rate;
		c.startAnimation(725);
		c.getFrameMethodHandler().gfx0(392);
	}
	
	public void magicOnItems(int castSpell, int castOnItem, int castOnSlot){

		int alchvaluez = (int)Math.floor(Item.GetItemShopValue(castOnItem, 0.75,995)); // 75% value
		
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
					FrameMethods.gfxAll(112, c.absX, c.absY);
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
					FrameMethods.gfxAll(113, c.absX, c.absY);
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

	
	final static int SPELLSPLASH = 85;

	/**
	 * Will calculate hitDiff on enemy e and create projectile from player c to enemy e.
	 * Will return hitDiff for EXP calculation as Item1.
	 * Will inflict magic damage on enemy after delay of spell cast and travel before contact.
	 * Will distribute magic EXP.
	 * Will interrupt enemy teleporting if hit.
	 * Depending on is isAncientMagic, the casting emote will be different.
	 * If spell fails to hit enemy e, then splash gfx will be displayed on enemy e.
	 * Will return total damage dealt.
	 */
	private int projectileSpell(int startId, int movingId, int finishId, int maxDamage, int levelRequired, boolean isAncientMagic, client caster, Enemy e){
		
		//cast emote and start gfx
		int castEmote = 711; //default
		if(isAncientMagic)
			castEmote = 1978;
		caster.startAnimation(castEmote);
		caster.getFrameMethodHandler().gfx100(startId);
		
		//hit calculation
		int hitDiff = misc.random(this.calculateMagicMaxHit(maxDamage, levelRequired));
		
		if(!caster.getCombatHandler().doIHitEnemyWithMagic(e))
			hitDiff = 0;
		
		if(hitDiff == 0)
			finishId = SPELLSPLASH;
		
		int casterX = caster.absX;
		int casterY = caster.absY;
		int offsetX = (casterX - e.getX()) * -1;
		int offsetY = (casterY - e.getY()) * -1;
		
		caster.getFrameMethodHandler().gfx100(startId);
		FrameMethods.createProjectile(casterY, casterX, offsetY, offsetX, 50, 95, movingId, 23, 20, e.getID(), e.isNPC());
		
		Task countDown = new Task(5, new Object[]{e, hitDiff, finishId, caster}, false) {
			@Override
			public void execute() {
				Enemy enemy = (Enemy) this.objects[0];			
				int hitDiff = (int)this.objects[1];
				enemy.gfx100((int)this.objects[2]);
				client caster = (client)this.objects[3];
				enemy.inflictMagicDamage(hitDiff, caster.GetPlayerAsEnemy(),0);
			}			
		};
		server.taskScheduler.schedule(countDown);

		int exp = hitDiff*4*c.CombatExpRate;
		if (exp < 0) exp = 4*c.CombatExpRate;
		c.getClientMethodHandler().addSkillXP(exp, 6);
		
		return hitDiff;
	}
	
	/**
	 * Will calculate hitDiff and inflict magic damage on enemy and surrounding enemies in radius range.
	 * Will add magic exp based on total amount of damage dealt.
	 * Will return total damage dealt.
	 */
	private int AoE_Spell(client caster, Enemy enemy, int startId, int movingId, int finishId, boolean isAncientMagic, int maxDamage, int levelRequired, int range, int bindTimer){
	//cast emote and start gfx
			int castEmote = 711; //default
			if(isAncientMagic)
				castEmote = 1978;
			caster.startAnimation(castEmote);
			caster.getFrameMethodHandler().gfx100(startId);
			caster.getFrameMethodHandler().createAllGfxWithDelay(50, startId, caster.absX, caster.absY, 100);
			
			int maxPossibleDamage = this.calculateMagicMaxHit(maxDamage, levelRequired);
			
			Combat.attackEnemiesWithin(finishId, movingId, true, enemy, range, maxPossibleDamage, caster.GetPlayerAsEnemy(), 2, caster.GetPlayerAsEnemy(), true, true);

			int exp = maxPossibleDamage*4*4*c.CombatExpRate*c.rate;
			if (exp <= 0) exp = 4*c.CombatExpRate*c.rate;
			c.getClientMethodHandler().addSkillXP(exp, 6);
			
		return maxPossibleDamage;
	}
	
	
	/**
	 * Private helper method for magicOnEnemy.
	 * Will call projectileSpell and AoE spell methods. 
	 * Will calculate max hit and determine if player hits enemy.
	 * Will inflict damage to enemy e.
	 * Will distribute magic exp.
	 */
	private void spellOnEnemy(int spellId, Enemy e){
		int BURST = 1;
		int BARRAGE = 2;
		int damageDealt = 0;
		
		switch(spellId){ 
		case 1152: //Wind Strike
			projectileSpell(90, 95, 92, 2, 1, false, c, e);
			break;

		case 1154: //Water Strike
			projectileSpell(93, 94, 95, 4, 5, false, c, e);
			break;

		case 1156: //Earth Strike
			projectileSpell(96, 97, 98,  6,9, false, c, e);
			break;

		case 1158: //Fire Strike
			projectileSpell(99, 100, 101,  8,13, false, c, e);
			break;

		case 1160: //Wind Bolt
			projectileSpell(117, 118, 119,  9,17, false, c, e);
			break;
			
		case 1163: //Water Bolt
			projectileSpell(120, 121, 122,  10,23, false, c, e);
			break;

		case 1166: //Earth Bolt
			projectileSpell(123, 124, 125,  11,29, false, c, e); 
			break;

		case 1169: //Fire Bolt
			projectileSpell(126, 127, 128,  12,35, false, c, e);
			break;

		case 1172: //Wind Blast
			projectileSpell(132, 133, 134,  13,41, false, c, e);
			break;

		case 1175: //Water Blast
			projectileSpell(135, 136, 137,  14,47, false, c, e);
			break;

		case 1177: //Earth Blast
			projectileSpell(138, 139, 140,  15,53, false, c, e);
			break;

		case 1181: //Fire Blast
			projectileSpell(129, 130, 131,  16,59, false, c, e);
			break;

		case 1183: //Wind Wave
			projectileSpell(158, 159, 160,  17,62, false, c, e);
			break;

		case 1185: //Water Wave
			projectileSpell(161, 162, 163,  18,65, false, c, e);
			break;

		case 1188: //Earth Wave
			projectileSpell(164, 165, 166,  19,70, false, c, e);
			break;

		case 1189: //Fire Wave
			projectileSpell(155, 156, 157,  20,75, false, c, e);
			break;

		case 12861: //Ice Rush - Level 58
			if(projectileSpell(360, 360, 361,  17,58, true, c, e) > 0)
				e.bind(5);
			break;

		case 12881: //Ice Burst - Level 70
			AoE_Spell(c, e, 362, 362, 363, true, 22, 70, BURST,10);
			break;

		case 12871: //Ice Blitz - Level 82
			if(projectileSpell(366, 367, 368,  26,82, true, c, e) > 0)
				e.bind(15);
			break;

		case 12891: //Ice Barrage - Level 94
			AoE_Spell(c, e, 366, 367, 369, true, 30, 94, BARRAGE, 20);
			break;

		case 12939: // Smoke Rush - Level 50
			projectileSpell(384, 384, 385,  14,50, true, c, e);
			break;

		case 12963: // Smoke Burst - Level 62
			AoE_Spell(c, e, 384, 384, 389, true, 18, 62, BURST,0);
			break;

		case 12951: //Smoke Blitz - Level 74
			projectileSpell(386, 386, 387,  23,74, true, c, e);

		case 12975: //Smoke Barrage - Level 86
			AoE_Spell(c, e, 386, 386, 391, true, 27, 86, BARRAGE,0);
			break;

		case 12987: //Shadow Rush - Level 52
			projectileSpell(378, 378, 379,  15,52, true, c, e);

		case 13011: //Shadow Burst - Level 64
			AoE_Spell(c, e, 378, 378, 382, true, 19, 64, BURST,0);
			break;

		case 12999: //Shadow Blitz - Level 76
			projectileSpell(380, 380, 381,  24,76, true, c, e);
			break;

		case 13023: //Shadow Barrage - Level 88
			AoE_Spell(c, e, 380, 380, 383, true, 28, 88, BARRAGE,0);
			break;

		case 12901: //Blood Rush - Level 56
			damageDealt = projectileSpell(372, 372, 373,  16,56, true, c, e);
			c.getClientMethodHandler().heal(damageDealt/4);
			break;

		case 12919: //Blood Burst - Level 68
			damageDealt = AoE_Spell(c, e, 372, 372, 376, true, 21, 68, BURST,0)/4;
			if(damageDealt > 7) damageDealt = 7; //greatest amount that can be healed is 7
			c.getClientMethodHandler().heal(damageDealt);
			break;

		case 12911: //Blood Blitz - Level 80
			damageDealt = projectileSpell(374, 374, 375, 25,80, true, c, e);
			damageDealt = damageDealt/4;
			if(damageDealt > 7) damageDealt = 7; //greatest amount that can be healed is 7
			c.getClientMethodHandler().heal(damageDealt);
			break;

		case 12929: //Blood Barrage - Level 92
			damageDealt = AoE_Spell(c, e, 374, 374, 377, true, 29, 92, BARRAGE,0)/4;
			if(damageDealt > 8) damageDealt = 8; //greatest amount to heal is 8
			c.getClientMethodHandler().heal(damageDealt);
			break;

		default:
			c.debug("Unhandled magicID : "+c.spellID);
			break;
		}
	}
	
	final int AUTOCASTDISTANCE = 5;
	
	public void magicOnEnemy(Enemy e, int spellId){
		if(e.isDead()) return;
		
		int enemyX = e.getX();
		int enemyY = e.getY();
		int distanceBetweenMeAndMyEnemy = misc.distanceBetweenPoints(enemyX, enemyY, c.absX, c.absY);
		
		if(distanceBetweenMeAndMyEnemy <= AUTOCASTDISTANCE){
			c.stopPlayerMovement();
			
			if(c.LoopAttDelay > 0)
				return;
			
			if(e.getNPCType() == 277){
				c.sendMessage("The fire warrior can only be hurt with Ice Arrows.");
				return;
			}
			
			c.faceEnemy(e);
			c.inCombat();			
			
			c.debug("In magicOnEnemy: spellID is "+spellId);
			
			int required = this.checkMagicLevel(spellId);
			if(c.playerLevel[c.playerMagic] < required){
				c.sendMessage("You need "+required+" Magic to do that.");
				return;
			}
			
			if(!this.checkMagicRunes(spellId))
				return;
			
			this.removeMagicRunes(spellId);

			c.PkingDelay = Item.getItemDelay(Item.MAGIC)+(distanceBetweenMeAndMyEnemy/AUTOCASTDISTANCE);
			c.LoopAttDelay = c.PkingDelay;
			
			spellOnEnemy(spellId, e);
		}
		else{
			c.followEnemy(e);
		}
		
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
		case -1: //home teleport
			return new int[]{-1};
		case 0: //no runes
			return new int[]{-1};
		case 4146: //Falador Teleport
			return new int[]{1,WATER,3,AIR,1,LAW};
		case 50235: // Mudskipper point ancient
			return new int[]{2,LAW,1,FIRE,1,AIR};
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

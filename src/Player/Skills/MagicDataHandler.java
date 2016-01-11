
public class MagicDataHandler {
	private client c;

	public MagicDataHandler(client pc){
		c = pc;
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
				c.deleteItem(amountThenItem[i+1], c.getItemSlot(amountThenItem[i+1]), amountThenItem[i]);
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
				if(!c.playerHasItemAmount(amountThenItem[i+1], amountThenItem[i])){
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

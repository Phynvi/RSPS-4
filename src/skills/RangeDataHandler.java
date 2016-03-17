package skills;
import playerData.client;
import clientHandlers.Item;
import root.misc;
import root.server;
import serverHandlers.ItemHandler;
import serverHandlers.PlayerHandler;
import struct.lists;


public class RangeDataHandler {

	private client c;

	public RangeDataHandler(client playerClient){
		c = playerClient;
	}
	
	public void checkForAccumulatorOrDistributeArrowOnGround(int XCoord, int YCoord){
		if(c.playerEquipment[c.playerWeapon] != Item.CRYSTALBOW && c.playerEquipmentN[c.playerArrows] != 0){ //if not cbow and if there are arrows
			if(misc.random(1) == 0){
				if (c.playerEquipment[c.playerCape] == 11342 || c.playerEquipment[c.playerCape] == 11341){
					c.getInventoryHandler().addItem(c.playerEquipment[c.playerArrows], 1);
					c.sendMessage("The accumulator has attracted an arrow.");
				}
				else ItemHandler.addItem(c.playerEquipment[c.playerArrows], XCoord, YCoord, 1, c.playerId, false, false);
			}
			c.getFrameMethodHandler().frameDeleteArrow();
		}
	}

	/**
	 * Will check player's currently equipped weapon with ammo to determine if
	 * it is a bow or crossbow equipped and if the correct ammo is equipped
	 * @return True if crossbow or bow with correct ammo is equipped. 
	 * True if player does not have a bow or crossbow equipped.
	 * False if the player has a crossbow or bow equipped without the correct ammo
	 */
	public boolean checkAmmoWithBow(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(lists.bows.exists(curBow)){
			if(lists.arrows.exists(curAmmo)) return true;
			else return false;
		}
		if(lists.xbow.exists(curBow)){
			if(lists.bolts.exists(curAmmo)) return true;
			else return false;
		}
		return true;
	}

	public int getBowEmote(){
		switch(c.playerEquipment[c.playerWeapon]){
		case 4734: //karil's
			return 2075;
		case 837: case 767: //crossbows
			return 427; 
		default: //default bows
			return 426;
		}
	}

	/**
	 * Will check the player's ammo to see if anything is there
	 * @return True if the player has valid ammo, false if there is no ammo
	 */
	public boolean checkAmmo(){
		if(lists.crystalBow.exists(c.playerEquipment[c.playerWeapon]))
			return true;
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(curBow == 4734 && curAmmo == 4740)
			return true;
		if(lists.xbow.exists(curBow) && lists.bolts.exists(curAmmo))
			return true;

		if(lists.arrows.exists(curAmmo) && lists.bows.exists(curBow))
			return true;
		return false;
	}

	private int getAmmoBonus(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		if(curAmmo == 4740)
			return 6; //karil's
		if(lists.bolts.exists(curAmmo))
			return 3;
		if(lists.bronzeArrows.exists(curAmmo))
			return 0;
		if(lists.ironArrows.exists(curAmmo))
			return 1;
		if(lists.steelArrows.exists(curAmmo))
			return 2;
		if(lists.mithrilArrows.exists(curAmmo))
			return 3;
		if(lists.adamArrows.exists(curAmmo))
			return 4;
		if(lists.runeArrows.exists(curAmmo))
			return 5;
		return 0; //by default
	}

	public int getEquipmentBonus(){
		int total = 0;
		int curChest = c.playerEquipment[c.playerChest];
		int curLegs = c.playerEquipment[c.playerLegs];
		int curHelm = c.playerEquipment[c.playerHat];
		int curCape = c.playerEquipment[c.playerCape];
		int curGloves = c.playerEquipment[c.playerHands];

		switch(curGloves){
		case 14505: //3rd age
			total += 7;
			break;
		default:
			if(lists.leatherEquipment.exists(curGloves)){
				total += 1;
				break;
			}
			if(lists.dhideEquipment.exists(curGloves)){
				total += 2;
				break;
			}
			if(lists.blueDhideEquipment.exists(curGloves)){
				total += 3;
				break;
			}
			if(lists.redDhideEquipment.exists(curGloves)){
				total += 4;
				break;
			}
			if(lists.blackDhideEquipment.exists(curGloves)){
				total += 5;
				break;
			}
		}

		switch(curChest){
		case 14503: //3rd age
			total += 7;
			break;
		case 4736: //karil
			total += 6;
			break;
		default:
			if(lists.leatherEquipment.exists(curChest)){
				total += 1;
				break;
			}
			if(lists.dhideEquipment.exists(curChest)){
				total += 2;
				break;
			}
			if(lists.blueDhideEquipment.exists(curChest)){
				total += 3;
				break;
			}
			if(lists.redDhideEquipment.exists(curChest)){
				total += 4;
				break;
			}
			if(lists.blackDhideEquipment.exists(curChest)){
				total += 5;
				break;
			}
		}
		switch(curLegs){
		case 14504: //3rd age
			total += 7;
			break;
		case 4738:
			total += 6;
			break;
		default:
			if(lists.leatherEquipment.exists(curLegs)){
				total += 1;
				break;
			}
			if(lists.dhideEquipment.exists(curLegs)){
				total += 2;
				break;
			}
			if(lists.blueDhideEquipment.exists(curLegs)){
				total += 3;
				break;
			}
			if(lists.redDhideEquipment.exists(curLegs)){
				total += 4;
				break;
			}
			if(lists.blackDhideEquipment.exists(curLegs)){
				total += 5;
				break;
			}
		}
		switch(curHelm){
		case 3755: //farseer helmet
		case 2581: //robin hood hat
			total += 3;
			break;
		case 3749:
			total += 4;
			break;
		case 14600: //3rd age
			total += 6;
			break;
		case 14084: //range skillhood
		case 4732: //karil
			total += 5;
			break;

		default:
			if(lists.leatherEquipment.exists(curHelm)){
				total += 1;
				break;
			}
		}

		switch(c.playerEquipment[c.playerRing]){
		case 6733: //archer ring
			total += 1;
			break;
		}

		switch(curCape){
		case 14082: //range skillcape
		case 14083: //range skillcape
			total += 4;
			break;
		}

		return total;
	}

	/**
	 * Calculates the amount too add to maxhit\
	 */
	public int getBonus(){
		int curBow = c.playerEquipment[c.playerWeapon]; //if this is being called, we know it will have a bow		
		int ammoBonus = getAmmoBonus();
		int equipmentBonus = getEquipmentBonus();
		int bowBonus = 0;

		switch(curBow){
		case 843: case 845:
			bowBonus = 1;
			break;
		case 847: case 849: //Willow
			bowBonus = 2;
			break;
		case 851: case 853: //Maple
			bowBonus = 3;
			break;
		case 855: case 857: //yew
			bowBonus = 4;
			break;
		case 859: case 861: //magic
			bowBonus = 5;
			break;
		case 4734: //karil's xbow
		case 15156: //darkbow
		case 4212: //crystal bow
			bowBonus = 6;
			break;
		default: //longbow and shortbow
			bowBonus = 0;
			break;
		}

		return (int)Math.floor((double)(ammoBonus+bowBonus+equipmentBonus)/2.0);
	}


	public int getDrawbackArrowGFX(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(lists.crystalBow.exists(curBow))
			return 250;
		if(lists.bronzeArrows.exists(curAmmo))
			return 19;
		if(lists.ironArrows.exists(curAmmo))
			return 18;
		if(lists.steelArrows.exists(curAmmo))
			return 20;
		if(lists.mithrilArrows.exists(curAmmo))
			return 21;
		if(lists.adamArrows.exists(curAmmo))
			return 22;
		if(lists.runeArrows.exists(curAmmo))
			return 24;

		return -1;
	}

	public int getArrowGFX(){
		int curAmmo = c.playerEquipment[c.playerArrows];
		int curBow = c.playerEquipment[c.playerWeapon];
		if(lists.xbow.exists(curBow))
			return 28;
		if(lists.crystalBow.exists(curBow))
			return 249;
		if(lists.bronzeArrows.exists(curAmmo))
			return 10;
		if(lists.ironArrows.exists(curAmmo))
			return 9;
		if(lists.steelArrows.exists(curAmmo))
			return 11;
		if(lists.mithrilArrows.exists(curAmmo))
			return 12;
		if(lists.adamArrows.exists(curAmmo))
			return 13;
		if(lists.runeArrows.exists(curAmmo))
			return 15;

		c.error("Error in getArrowGFX, ammo not found");
		return -1;
	}


	/**
	 * Creates an arrow projectile, determined by current bow and current ammo
	 * Projectile will travel from player to NPC
	 */
	public void arrowProjectile(int npcIndex){
		int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
		int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		int casterX = c.absX;
		int casterY = c.absY;
		int offsetX = (casterX - EnemyX2) * -1;
		int offsetY = (casterY - EnemyY2) * -1;
		c.getFrameMethodHandler().gfx100(getDrawbackArrowGFX());
		c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 55, getArrowGFX(), 43, 31, npcIndex+1, 40);
	}


	/**
	 * Creates an arrow projectile, determined by current bow and current ammo
	 * Projectile will travel from player to NPC
	 */
	public void arrowProjectilePlayer(int playerIndex){
		int X3 = PlayerHandler.players[playerIndex].absX;
		int Y3 = PlayerHandler.players[playerIndex].absY;
		int offsetX = (c.absX - X3) * -1;
		int offsetY = (c.absY - Y3) * -1;
		//c.ProjectileSpellPlayer(arrowGFX, arrowGFX, arrowGFX, c.absY, c.absX, offsetY, offsetX, c.AttackingOn+1, Y3, X3);
		c.getFrameMethodHandler().gfx100(getDrawbackArrowGFX());
		c.getFrameMethodHandler().createProjectileWithDelay(c.absY, c.absX, offsetY, offsetX, 50, 55, getArrowGFX(), 43, 31, c.AttackingOn+1, 40);
	}

}
